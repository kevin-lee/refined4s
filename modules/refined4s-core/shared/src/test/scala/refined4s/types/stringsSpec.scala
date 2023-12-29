package refined4s.types

import refined4s.*

import hedgehog.*
import hedgehog.runner.*

/** @author Kevin Lee
  * @since 2023-04-25
  */
object stringsSpec extends Properties {
  override def tests: List[Test] = NonEmptyStringSpec.tests

  object NonEmptyStringSpec {
    import all.NonEmptyString

    def tests: List[Test] = List(
      example("test NonEmptyString.apply", testApply),
      property("test NonEmptyString.from(valid)", testFromValid),
      example("test NonEmptyString.from(invalid)", testFromInvalid),
      property("test NonEmptyString.unsafeFrom(valid)", testUnsafeFromValid),
      example("test NonEmptyString.unsafeFrom(invalid)", testUnsafeFromInvalid),
      property("test NonEmptyString.value", testValue),
      property("test NonEmptyString.unapply", testUnapplyWithPatternMatching),
      property("test NonEmptyString ++ NonEmptyString", testNonEmptyStringPlusNonEmptyString),
      property("test Ordering[NonEmptyString]", testOrdering),
      property("test Ordered[NonEmptyString]", testOrdered),
    )

    def testApply: Result = {
      /* The actual test is whether this compiles or not actual ==== expected is meaningless here */
      val expected = NonEmptyString("blah")
      val actual   = NonEmptyString("blah")
      actual ==== expected
    }

    def testFromValid: Property =
      for {
        s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
      } yield {
        val expected = NonEmptyString.unsafeFrom(s)
        val actual   = NonEmptyString.from(s)
        actual ==== Right(expected)
      }

    def testFromInvalid: Result = {
      val expected = "Invalid value: []. It must be a non-empty String"
      val actual   = NonEmptyString.from("")
      actual ==== Left(expected)
    }

    def testUnsafeFromValid: Property =
      for {
        s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
      } yield {
        val expected = NonEmptyString.unsafeFrom(s)
        val actual   = NonEmptyString.unsafeFrom(s)
        actual ==== expected
      }

    def testUnsafeFromInvalid: Result = {
      val expected = "Invalid value: []. It must be a non-empty String"
      try {
        NonEmptyString.unsafeFrom("")
        Result
          .failure
          .log("""IllegalArgumentException was expected from NonEmptyString.unsafeFrom(""), but it was not thrown.""")
      } catch {
        case ex: IllegalArgumentException =>
          ex.getMessage ==== expected

      }
    }

    def testValue: Property =
      for {
        s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
      } yield {
        val expected = s
        val actual   = NonEmptyString.unsafeFrom(s)
        actual.value ==== expected
      }

    def testUnapplyWithPatternMatching: Property =
      for {
        s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
      } yield {
        val expected = s
        val nes      = NonEmptyString.unsafeFrom(s)
        nes match {
          case NonEmptyString(actual) =>
            actual ==== expected
        }
      }

    def testNonEmptyStringPlusNonEmptyString: Property =
      for {
        s1 <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s1")
        s2 <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s2")

        expected <- Gen.constant(s1 + s2).map(NonEmptyString.unsafeFrom).log("expected")
      } yield {
        val nes1   = NonEmptyString.unsafeFrom(s1)
        val nes2   = NonEmptyString.unsafeFrom(s2)
        val actual = nes1 ++ nes2
        actual ==== expected
      }

    def testOrdering: Property =
      for {
        s1 <- Gen.string(Gen.alphaNum, Range.linear(1, 10)).log("s1")
        s2 <- Gen.string(Gen.alphaNum, Range.linear(1, 10)).log("s2")
      } yield {
        val input1   = NonEmptyString.unsafeFrom(s1)
        val input2   = NonEmptyString.unsafeFrom(s2)
        val expected = s1.compare(s2)
        Result.diff(input1, input2)(Ordering[NonEmptyString].compare(_, _) == expected)
      }

    def testOrdered: Property =
      for {
        s1 <- Gen.string(Gen.alphaNum, Range.linear(1, 10)).log("s1")
        s2 <- Gen.string(Gen.alphaNum, Range.linear(1, 10)).log("s2")
      } yield {
        val input1   = NonEmptyString.unsafeFrom(s1)
        val input2   = NonEmptyString.unsafeFrom(s2)
        val expected = s1.compare(s2)
        Result.diff(input1: Ordered[NonEmptyString], input2: NonEmptyString)(_.compare(_) == expected)
      }

  }
}
