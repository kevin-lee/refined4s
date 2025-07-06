package refined4s.modules.tapir.derivation

import hedgehog.*
import hedgehog.runner.*
import refined4s.*
import sttp.tapir.Schema

/** @author Kevin Lee
  * @since 2024-04-03
  */
object TapirSchemaSpec extends Properties {
  override def tests: List[Test] = List(
    property(
      "Given Newtype with TapirNewtypeSchema. summon[Schema[Newtype[A]]].applyValidation(a) where a is Newtype[A] should return an empty List[ValidationError[_]]",
      testRenderNewtype,
    ),
    property(
      "Given Refined with TapirRefinedSchema. summon[Schema[Refined[A]]].applyValidation(a) where a is Refined[A] should return an empty List[ValidationError[_]]",
      testRenderRefined,
    ),
    property(
      "Given Newtype[Refined] with TapirNewtypeSchema. summon[Schema[Newtype[Refined[A]]]].applyValidation(a) where a is Newtype[Refined[A]] should return an empty List[ValidationError[_]]",
      testRenderNewtypeRefined,
    ),
  )

  def testRenderNewtype: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(0, 10)).log("s")
    } yield {
      val a      = MyNewtype(s)
      val actual = summon[Schema[MyNewtype]].applyValidation(a)
      actual ==== List.empty
    }

  def testRenderRefined: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
    } yield {
      val a      = MyRefinedType.unsafeFrom(s)
      val actual = summon[Schema[MyRefinedType]].applyValidation(a)
      actual ==== List.empty
    }

  def testRenderNewtypeRefined: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
    } yield {
      val a      = MyRefinedNewtype(MyRefinedType.unsafeFrom(s))
      val actual = summon[Schema[MyRefinedNewtype]].applyValidation(a)
      actual ==== List.empty
    }

  type MyNewtype = MyNewtype.Type
  object MyNewtype extends Newtype[String] with TapirNewtypeSchema[String]

  type MyRefinedType = MyRefinedType.Type
  @SuppressWarnings(Array("org.wartremover.warts.Equals"))
  object MyRefinedType extends Refined[String] with TapirRefinedSchema[String] {
    override inline def invalidReason(a: String): String =
      "It has to be a non-empty String but got \"" + a + "\""

    override inline def predicate(a: String): Boolean = a != ""
  }

  type MyRefinedNewtype = MyRefinedNewtype.Type
  object MyRefinedNewtype extends Newtype[MyRefinedType] with TapirNewtypeSchema[MyRefinedType]

}
