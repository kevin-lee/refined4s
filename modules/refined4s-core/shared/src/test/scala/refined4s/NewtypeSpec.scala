package refined4s

import cats.*
import cats.syntax.all.*

import hedgehog.*
import hedgehog.runner.*

/** @author Kevin Lee
  * @since 2023-12-03
  */
object NewtypeSpec extends Properties {
  override def tests: List[Test] = List(
    example("""Newtype("blah") should be equal to Newtype("blah")""", testApply),
    property("test Newtype.value", testValue),
    property("test Newtype.unapply", testUnapplyWithPatternMatching),
  )

  def testApply: Result = {
    /* The actual test is whether this compiles or not actual ==== expected is meaningless here */
    val expected = MyType("blah")
    val actual   = MyType("blah")
    actual ==== expected
  }

  def testValue: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
    } yield {
      val expected = s
      val actual   = MyType(s)
      actual.value ==== expected
    }

  def testUnapplyWithPatternMatching: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
    } yield {
      val expected = s
      val myType   = MyType(s)
      myType match {
        case MyType(actual) =>
          actual ==== expected
      }
    }

  type MyType = MyType.Type
  object MyType extends Newtype[String] {
    given eqMyType: Eq[MyType]     = deriving[Eq]
    given showMyType: Show[MyType] = deriving[Show]
  }
}
