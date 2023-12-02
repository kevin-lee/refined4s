package refined4s

import cats.*
import cats.syntax.all.*
import hedgehog.*
import hedgehog.runner.*
import refined4s.InlinedRefinedType.*

/** @author Kevin Lee
  * @since 2023-04-24
  */
object InlinedRefinedCatsSpec extends Properties {
  override def tests: List[Test] = List(
    example("test Eq[Something]", testEqSomething),
    property("Something(value) === Something(value) should be true", testEqSomethings),
    property("Something(value) === Something(different value) should be false", testEqSomethings2),
    example("test Show[Something]", testShowSomething),
    property("Something(value).show should be value.toString", testShowSomethings),
  )

  def testEqSomething: Result = {
    val a = Something(999)
    val b = a
    Result.diffNamed("Something(value) === Something(value)", a, b)(_ === _)
  }

  def testEqSomethings: Property =
    for {
      input <- Gen.int(Range.linear(0, Int.MaxValue)).log("input")
    } yield {
      val a = Something.unsafeFrom(input)
      val b = Something.unsafeFrom(input)
      Result.diffNamed("Something(input) === Something(input)", a, b)(_ === _)
    }

  def testEqSomethings2: Property =
    for {
      input1 <- Gen.int(Range.linear(0, Int.MaxValue)).log("input1")
      input2 <- Gen
                  .int(Range.linear(0, Int.MaxValue))
                  .map(n =>
                    if (input1 === n)
                      if n < Int.MaxValue then n + 1 else n - 1
                    else
                      n
                  )
                  .log("input2")
    } yield {
      val a = Something.unsafeFrom(input1)
      val b = Something.unsafeFrom(input2)
      Result.diffNamed("Something(input1) === Something(input2)", a, b)(_ =!= _)
    }

  def testShowSomething: Result = {
    val expected = 999.toString
    val actual   = Something(999).show
    Result.diffNamed("Something(value).show should be the same as the value", actual, expected)(_ === _)
  }

  def testShowSomethings: Property =
    for {
      input <- Gen.int(Range.linear(0, Int.MaxValue)).log("input")
    } yield {
      val expected = input.toString
      val actual   = Something.unsafeFrom(input).show
      Result.diffNamed("Something(input).show should be the same as the value.toString", actual, expected)(_ === _)
    }

}
