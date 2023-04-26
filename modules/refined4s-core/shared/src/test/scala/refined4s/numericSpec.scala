package refined4s

import hedgehog.*
import hedgehog.runner.*

/** @author Kevin Lee
  * @since 2023-04-26
  */
object numericSpec extends Properties {
  override def tests: List[Test] = NegIntSpec.tests ++ NonNegIntSpec.tests

  object NegIntSpec {

    import numeric.NegInt

    def tests: List[Test] = List(
      example("test NegInt.apply", testApply),
      property("test NegInt.from(valid)", testFromValid),
      property("test NegInt.from(invalid)", testFromInvalid),
      property("test NegInt.unsafeFrom(valid)", testUnsafeFromValid),
      property("test NegInt.unsafeFrom(invalid)", testUnsafeFromInvalid),
      property("test NegInt.value", testValue),
      property("test NegInt.unapply", testUnapplyWithPatternMatching),
      property("test Ordering[NegInt]", testOrdering),
    )

    def testApply: Result = {
      /* The actual test is whether this compiles or not actual ==== expected is meaningless here */
      val expected = NegInt(-1)
      val actual   = NegInt(-1)
      actual ==== expected
    }

    def testFromValid: Property =
      for {
        n <- Gen.int(Range.linear(Int.MinValue, -1)).log("n")
      } yield {
        val expected = NegInt.unsafeFrom(n)
        val actual   = NegInt.from(n)
        actual ==== Right(expected)
      }

    def testFromInvalid: Property =
      for {
        n <- Gen.int(Range.linear(0, Int.MaxValue)).log("n")
      } yield {
        val expected = s"Invalid value: [${n.toString}]. It should be a negative Int value but got $n instead"
        val actual   = NegInt.from(n)
        actual ==== Left(expected)
      }

    def testUnsafeFromValid: Property =
      for {
        n <- Gen.int(Range.linear(Int.MinValue, -1)).log("n")
      } yield {
        val expected = NegInt.unsafeFrom(n)
        val actual   = NegInt.unsafeFrom(n)
        actual ==== expected
      }

    def testUnsafeFromInvalid: Property =
      for {
        n <- Gen.int(Range.linear(0, Int.MaxValue)).log("n")
      } yield {
        val expected = s"Invalid value: [$n]. It should be a negative Int value but got $n instead"
        try {
          NegInt.unsafeFrom(n)
          Result
            .failure
            .log(
              s"""IllegalArgumentException was expected from NegInt.unsafeFrom(${n.toString}), but it was not thrown."""
            )
        } catch {
          case ex: IllegalArgumentException =>
            ex.getMessage ==== expected

        }
      }

    def testValue: Property =
      for {
        n <- Gen.int(Range.linear(Int.MinValue, -1)).log("n")
      } yield {
        val expected = n
        val actual   = NegInt.unsafeFrom(n)
        actual.value ==== expected
      }

    def testUnapplyWithPatternMatching: Property =
      for {
        n <- Gen.int(Range.linear(Int.MinValue, -1)).log("n")
      } yield {
        val expected = n
        val nes      = NegInt.unsafeFrom(n)
        nes match {
          case NegInt(actual) =>
            actual ==== expected
        }
      }

    def testOrdering: Property =
      for {
        n1 <- Gen.int(Range.linear(Int.MinValue, -1)).log("n1")
        n2 <- Gen.int(Range.linear(Int.MinValue, -1)).log("n2")
      } yield {
        import scala.math.Numeric.IntIsIntegral
        val input1 = NegInt.unsafeFrom(n1)
        val input2 = NegInt.unsafeFrom(n2)
        Ordering[NegInt].compare(input1, input2) ==== Ordering[Int].compare(input1.value, input2.value)
      }

  }

  object NonNegIntSpec {

    import numeric.NonNegInt

    def tests: List[Test] = List(
      example("test NonNegInt.apply", testApply),
      property("test NonNegInt.from(valid)", testFromValid),
      property("test NonNegInt.from(invalid)", testFromInvalid),
      property("test NonNegInt.unsafeFrom(valid)", testUnsafeFromValid),
      property("test NonNegInt.unsafeFrom(invalid)", testUnsafeFromInvalid),
      property("test NonNegInt.value", testValue),
      property("test NonNegInt.unapply", testUnapplyWithPatternMatching),
      property("test Ordering[NonNegInt]", testOrdering),
    )

    def testApply: Result = {
      /* The actual test is whether this compiles or not actual ==== expected is meaningless here */
      val expected = NonNegInt(0)
      val actual   = NonNegInt(0)
      actual ==== expected
    }

    def testFromValid: Property =
      for {
        n <- Gen.int(Range.linear(0, Int.MaxValue)).log("n")
      } yield {
        val expected = NonNegInt.unsafeFrom(n)
        val actual   = NonNegInt.from(n)
        actual ==== Right(expected)
      }

    def testFromInvalid: Property =
      for {
        n <- Gen.int(Range.linear(Int.MinValue, -1)).log("n")
      } yield {
        val expected = s"Invalid value: [${n.toString}]. It should be a non-negative Int value but got $n instead"
        val actual   = NonNegInt.from(n)
        actual ==== Left(expected)
      }

    def testUnsafeFromValid: Property =
      for {
        n <- Gen.int(Range.linear(0, Int.MaxValue)).log("n")
      } yield {
        val expected = NonNegInt.unsafeFrom(n)
        val actual   = NonNegInt.unsafeFrom(n)
        actual ==== expected
      }

    def testUnsafeFromInvalid: Property =
      for {
        n <- Gen.int(Range.linear(-1, Int.MinValue)).log("n")
      } yield {
        val expected = s"Invalid value: [$n]. It should be a non-negative Int value but got $n instead"
        try {
          NonNegInt.unsafeFrom(n)
          Result
            .failure
            .log(
              s"""IllegalArgumentException was expected from NonNegInt.unsafeFrom(${n.toString}), but it was not thrown."""
            )
        } catch {
          case ex: IllegalArgumentException =>
            ex.getMessage ==== expected

        }
      }

    def testValue: Property =
      for {
        n <- Gen.int(Range.linear(0, Int.MaxValue)).log("n")
      } yield {
        val expected = n
        val actual   = NonNegInt.unsafeFrom(n)
        actual.value ==== expected
      }

    def testUnapplyWithPatternMatching: Property =
      for {
        n <- Gen.int(Range.linear(0, Int.MaxValue)).log("n")
      } yield {
        val expected = n
        val nes      = NonNegInt.unsafeFrom(n)
        nes match {
          case NonNegInt(actual) =>
            actual ==== expected
        }
      }

    def testOrdering: Property =
      for {
        n1 <- Gen.int(Range.linear(0, Int.MaxValue)).log("n1")
        n2 <- Gen.int(Range.linear(0, Int.MaxValue)).log("n2")
      } yield {
        import scala.math.Numeric.IntIsIntegral
        val input1 = NonNegInt.unsafeFrom(n1)
        val input2 = NonNegInt.unsafeFrom(n2)
        Ordering[NonNegInt].compare(input1, input2) ==== Ordering[Int].compare(input1.value, input2.value)
      }

  }

}
