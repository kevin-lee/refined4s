package refined4s

import cats.*
import cats.syntax.all.*
import hedgehog.*
import hedgehog.runner.*

/** @author Kevin Lee
  * @since 2023-12-03
  */
object NewtypeCatsSpec extends Properties {
  override def tests: List[Test] = List(
    example("test Eq[MyType]", testEqMyType),
    property("MyType(value) === MyType(value) should be true", testEqMyTypes),
    property("MyType(value) === MyType(different value) should be false", testEqMyTypes2),
    example("test Show[MyType]", testShowMyType),
    property("MyType(value).show should be value", testShowMyTypes),
  )

  def testEqMyType: Result = {
    val a = MyType("blah")
    val b = a
    Result.diffNamed("MyType(value) === MyType(value)", a, b)(_ === _)
  }

  def testEqMyTypes: Property =
    for {
      input <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("input")
    } yield {
      val a = MyType(input)
      val b = MyType(input)
      Result.diffNamed("MyType(input) === MyType(input)", a, b)(_ === _)
    }

  def testEqMyTypes2: Property =
    for {
      input1 <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("input1")
      input2 <- Gen.string(Gen.unicode, Range.linear(1, 10)).map(input1 + _).log("input2")
    } yield {
      val a = MyType(input1)
      val b = MyType(input2)
      Result.diffNamed("MyType(input1) === MyType(input2)", a, b)(_ =!= _)
    }

  def testShowMyType: Result = {
    val expected = "blah"
    val actual   = MyType("blah").show
    Result.diffNamed("MyType(value).show should be the same as the value", actual, expected)(_ === _)
  }

  def testShowMyTypes: Property =
    for {
      input <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("input")
    } yield {
      val expected = input
      val actual   = MyType(input).show
      Result.diffNamed("MyType(input).show should be the same as the value", actual, expected)(_ === _)
    }

  type MyType = MyType.Type
  object MyType extends Newtype[String] {

    given eqMyType: Eq[MyType]     = deriving[Eq]
    given showMyType: Show[MyType] = deriving[Show]
  }

}
