package refined4s.modules.extras.derivation

import extras.render.*
import extras.render.syntax.*
import hedgehog.*
import hedgehog.runner.*
import refined4s.*

/** @author Kevin Lee
  * @since 2024-01-01
  */
object ExtrasRenderSpec extends Properties {
  override def tests: List[Test] = List(
    property(
      "Given Newtype with ExtrasRender. Newtype[A].render(a) should return the same String as Render[A].render(a)",
      testRenderNewtype,
    ),
    property(
      "Given Refined with ExtrasRender. Refined[A].render(a) should return the same String as Render[A].render(a)",
      testRenderRefined,
    ),
    property(
      "Given Newtype[Refined] with ExtrasRender. Newtype[Refined[A]].render(a) should return the same String as Render[A].render(a)",
      testRenderNewtypeRefined,
    ),
  )

  def testRenderNewtype: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(0, 10)).log("s")
    } yield {
      val a = MyNewtype(s)
      a.render ==== Render[String].render(s)
    }

  def testRenderRefined: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
    } yield {
      val a = MyRefinedType.unsafeFrom(s)
      a.render ==== Render[String].render(s)
    }

  def testRenderNewtypeRefined: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
    } yield {
      val a = MyRefinedNewtype(MyRefinedType.unsafeFrom(s))
      a.render ==== Render[String].render(s)
    }

  type MyNewtype = MyNewtype.Type
  object MyNewtype extends Newtype[String] with ExtrasRender[String]

  type MyRefinedType = MyRefinedType.Type
  @SuppressWarnings(Array("org.wartremover.warts.Equals"))
  object MyRefinedType extends Refined[String] with ExtrasRender[String] {
    override inline def invalidReason(a: String): String =
      "It has to be a non-empty String but got \"" + a + "\""

    override inline def predicate(a: String): Boolean = a != ""
  }

  type MyRefinedNewtype = MyRefinedNewtype.Type
  object MyRefinedNewtype extends Newtype[MyRefinedType] with ExtrasRender[MyRefinedType]

}
