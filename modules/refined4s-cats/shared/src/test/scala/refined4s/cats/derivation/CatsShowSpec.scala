package refined4s.cats.derivation

import cats.*
import cats.syntax.all.*
import hedgehog.*
import hedgehog.runner.*
import refined4s.*

/** @author Kevin Lee
  * @since 2023-12-07
  */
object CatsShowSpec extends Properties {
  override def tests: List[Test] = List(
    property(
      "Given Newtype with CatsShow. Newtype[A].show(a) should return the same String as Show[A].show(a)",
      testShowNewtype,
    ),
    property(
      "Given Refined with CatsShow. Refined[A].show(a) should return the same String as Show[A].show(a)",
      testShowRefined,
    ),
    property(
      "Given Newtype[Refined] with CatsShow. Newtype[Refined[A]].show(a) should return the same String as Show[A].show(a)",
      testShowNewtypeRefined,
    ),
  )

  def testShowNewtype: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(0, 10)).log("s")
    } yield {
      val a = MyNewtype(s)
      a.show ==== Show[String].show(s)
    }

  def testShowRefined: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
    } yield {
      val a = MyRefinedType.unsafeFrom(s)
      a.show ==== Show[String].show(s)
    }

  def testShowNewtypeRefined: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
    } yield {
      val a = MyRefinedNewtype(MyRefinedType.unsafeFrom(s))
      a.show ==== Show[String].show(s)
    }

  type MyNewtype = MyNewtype.Type
  object MyNewtype extends Newtype[String] with CatsShow[String]

  type MyRefinedType = MyRefinedType.Type
  @SuppressWarnings(Array("org.wartremover.warts.Equals"))
  object MyRefinedType extends Refined[String] with CatsShow[String] {
    override inline def invalidReason(a: String): String =
      "It has to be a non-empty String but got \"" + a + "\""

    override inline def predicate(a: String): Boolean = a != ""
  }

  type MyRefinedNewtype = MyRefinedNewtype.Type
  object MyRefinedNewtype extends Newtype[MyRefinedType] with CatsShow[MyRefinedType]

}
