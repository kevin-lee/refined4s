package refined4s.modules.cats.derivation

import cats.*
import cats.syntax.all.*
import hedgehog.*
import hedgehog.runner.*
import refined4s.*

/** @author Kevin Lee
  * @since 2023-12-30
  */
object CatsOrderSpec extends Properties {
  override def tests: List[Test] = List(
    property(
      "Given Newtype with CatsOrder. Order[Newtype].compare(Newtype[A](a1), Newtype[A](a2) should be the same as Order[A].compare(a1, a2)",
      testOrderNewtype,
    ),
    property(
      "Given Refined with CatsOrder. Order[Refined].compare(Refined[A](a1), Refined[A](a2) should be the same as Order[A].compare(a1, a2)",
      testOrderRefined,
    ),
    property(
      "Given Newtype[Refined] with CatsOrder. Order[Newtype[Refined]].compare(Newtype[Refined[A]](a1), Newtype[Refined[A]](a2) should be the same as Order[A].compare(a1, a2)",
      testOrderNewtypeRefinedSame,
    ),
  )

  def testOrderNewtype: Property =
    for {
      s1 <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s1")
      s2 <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s2")
    } yield {
      val input1 = MyNewtype(s1)
      val input2 = MyNewtype(s2)

      val expected = Order[String].compare(s1, s2)
      Result.diffNamed("Order[MyNewtype].compare(MyNewtype(s1), MyNewtype(s2))", input1, input2)(
        Order[MyNewtype].compare(_, _) === expected
      )
    }

  def testOrderRefined: Property =
    for {
      s1 <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s1")
      s2 <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s2")
    } yield {
      val input1 = MyRefinedType.unsafeFrom(s1)
      val input2 = MyRefinedType.unsafeFrom(s2)

      val expected = Order[String].compare(s1, s2)
      Result.diffNamed("Order[MyRefinedType].compare(MyRefinedType(s1), MyRefinedType(s2))", input1, input2)(
        Order[MyRefinedType].compare(_, _) === expected
      )
    }

  def testOrderNewtypeRefinedSame: Property =
    for {
      s1 <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s1")
      s2 <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s2")
    } yield {
      val input1 = MyRefinedNewtype(MyRefinedType.unsafeFrom(s1))
      val input2 = MyRefinedNewtype(MyRefinedType.unsafeFrom(s2))

      val expected = Order[String].compare(s1, s2)
      Result.diffNamed(
        "Order[MyRefinedNewtype].compare(MyRefinedNewtype(MyRefinedType(s1)), MyRefinedNewtype(MyRefinedType(s2)))",
        input1,
        input2,
      )(
        Order[MyRefinedNewtype].compare(_, _) === expected
      )
    }

  type MyNewtype = MyNewtype.Type
  object MyNewtype extends Newtype[String], CatsOrder[String]

  type MyRefinedType = MyRefinedType.Type
  @SuppressWarnings(Array("org.wartremover.warts.Equals"))
  object MyRefinedType extends Refined[String], CatsOrder[String] {
    override inline def invalidReason(a: String): String =
      "It has to be a non-empty String but got \"" + a + "\""

    override inline def predicate(a: String): Boolean = a != ""
  }

  type MyRefinedNewtype = MyRefinedNewtype.Type
  object MyRefinedNewtype extends Newtype[MyRefinedType], CatsOrder[MyRefinedType]

}
