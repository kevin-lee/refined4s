package refined4s.types

import hedgehog.*
import hedgehog.runner.*
import refined4s.*

import java.util.UUID

/** @author Kevin Lee
  * @since 2023-04-25
  */
object stringsSpec extends Properties {
  override def tests: List[Test] = NonEmptyStringSpec.tests ++ UuidSpec.tests

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

  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  object UuidSpec {

    import all.Uuid

    def tests: List[Test] = List(
      example("test strings.isValidateUuid(valid UUID String)", testStringsIsValidateUuidValid),
      example("test strings.isValidateUuid(invalid UUID String)", testStringsIsValidateUuidInvalid),
      example("test strings.isValidateUuid(invalid String literal)", testStringsIsValidateUuidWithInvalidLiteral),
      example("test Uuid.apply", testApply),
      example("test Uuid.apply(UUID)", testApplyUUID),
      example("test Uuid.apply(Invalid)", testApplyInvalid),
      property("test Uuid.from(valid)", testFromValid),
      example("test Uuid.from(invalid)", testFromInvalid),
      property("test Uuid.unsafeFrom(valid)", testUnsafeFromValid),
      example("test Uuid.unsafeFrom(invalid)", testUnsafeFromInvalid),
      property("test Uuid.value", testValue),
      property("test Uuid.unapply", testUnapplyWithPatternMatching),
      property("test Uuid.toUUID", testToUUID),
      property("test Ordering[Uuid]", testOrdering),
      property("test Ordered[Uuid]", testOrdered),
    )

    inline def runStringsIsValidateUuid(inline a: String): Boolean = ${ strings.isValidateUuid('a) }

    def testStringsIsValidateUuidValid: Result = {
      val expected = true
      val actual   = runStringsIsValidateUuid("3108715a-6477-4cd1-9ace-85b1260be03d")
      (actual ==== expected)
        .log("""strings.isValidateUuid("3108715a-6477-4cd1-9ace-85b1260be03d") should return true but it returned false.""")
    }

    def testStringsIsValidateUuidInvalid: Result = {
      val expected = false
      val actual   = runStringsIsValidateUuid("blah")
      (actual ==== expected)
        .log("""strings.isValidateUuid("blah") should return false but it returned true""")
    }

    def testStringsIsValidateUuidWithInvalidLiteral: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expectedMessage = strings.UnexpectedLiteralErrorMessage

      val actual = typeCheckErrors(
        """
          val a = "blah"
          runStringsIsValidateUuid(a)
        """
      )

      val actualErrorMessage = actual.map(_.message).mkString
      (actualErrorMessage ==== expectedMessage)
    }

    def testApply: Result = {
      val expected = UUID.fromString("3108715a-6477-4cd1-9ace-85b1260be03d")
      val actual   = Uuid("3108715a-6477-4cd1-9ace-85b1260be03d")
      actual.value ==== expected.toString
    }

    def testApplyUUID: Result = {
      val uuid     = UUID.fromString("3108715a-6477-4cd1-9ace-85b1260be03d")
      val expected = uuid
      val actual   = Uuid(uuid)
      actual.value ==== expected.toString
    }

    def testApplyInvalid: Result = {
      import scala.compiletime.testing.typeChecks

      val shouldCompile1 = typeChecks(
        """
          import strings.*
          Uuid("3108715a-6477-4cd1-9ace-85b1260be03d")
        """
      )

      val shouldNotCompile1 = !typeChecks(
        """
          import strings.*
          Uuid("blah")
        """
      )

      val shouldNotCompile2 = !typeChecks(
        """
          import strings.*
          Uuid("")
        """
      )

      val shouldNotCompile3 = !typeChecks(
        """
          import strings.*
          Uuid(123)
        """
      )

      Result.all(
        List(
          Result
            .assert(shouldCompile1)
            .log("""Uuid("3108715a-6477-4cd1-9ace-85b1260be03d") should have compiled without any compile-time error but it failed."""),
          Result.assert(shouldNotCompile1).log("""Uuid("blah") should have failed compilation but it succeeded."""),
          Result.assert(shouldNotCompile2).log("""Uuid("") should have failed compilation but it succeeded."""),
          Result.assert(shouldNotCompile3).log("""Uuid(123) should have failed compilation but it succeeded."""),
        )
      )
    }

    def testFromValid: Property =
      for {
        uuid <- Gen.constant(UUID.randomUUID()).log("uuid")
      } yield {
        val s        = uuid.toString
        val expected = Uuid.unsafeFrom(s)
        val actual   = Uuid.from(s)
        actual ==== Right(expected)
      }

    def testFromInvalid: Result = {
      val expected1 = "Invalid value: [blah]. It must be UUID"
      val expected2 = "Invalid value: []. It must be UUID"
      val actual1   = Uuid.from("blah")
      val actual2   = Uuid.from("")
      Result.all(
        List(
          actual1 ==== Left(expected1),
          actual2 ==== Left(expected2),
        )
      )
    }

    def testUnsafeFromValid: Property =
      for {
        uuid <- Gen.constant(UUID.randomUUID()).log("uuid")
      } yield {
        val s        = uuid.toString
        val expected = Uuid.unsafeFrom(s)
        val actual   = Uuid.unsafeFrom(s)
        actual ==== expected
      }

    def testUnsafeFromInvalid: Result = {
      val expected = "Invalid value: [blah]. It must be UUID"
      try {
        Uuid.unsafeFrom("blah")
        Result
          .failure
          .log("""IllegalArgumentException was expected from Uuid.unsafeFrom("blah"), but it was not thrown.""")
      } catch {
        case ex: IllegalArgumentException =>
          ex.getMessage ==== expected

      }
    }

    def testValue: Property =
      for {
        uuid <- Gen.constant(UUID.randomUUID()).log("uuid")
      } yield {
        val s        = uuid.toString
        val expected = s
        val actual   = Uuid.unsafeFrom(s)
        actual.value ==== expected
      }

    def testUnapplyWithPatternMatching: Property =
      for {
        uuid <- Gen.constant(UUID.randomUUID()).log("uuid")
      } yield {
        val s        = uuid.toString
        val expected = s
        val nes      = Uuid.unsafeFrom(s)
        nes match {
          case Uuid(actual) =>
            actual ==== expected
        }
      }

    def testToUUID: Property =
      for {
        uuid <- Gen.constant(UUID.randomUUID()).log("uuid")

      } yield {
        val s        = uuid.toString
        val nes1     = Uuid.unsafeFrom(s)
        val actual   = nes1.toUUID
        val expected = uuid
        actual ==== expected
      }

    def testOrdering: Property =
      for {
        uuid1 <- Gen.constant(UUID.randomUUID()).log("uuid1")
        uuid2 <- Gen.constant(UUID.randomUUID()).log("uuid2")
      } yield {
        val s1       = uuid1.toString
        val s2       = uuid2.toString
        val input1   = Uuid.unsafeFrom(s1)
        val input2   = Uuid.unsafeFrom(s2)
        val expected = s1.compare(s2)
        Result.diff(input1, input2)(Ordering[Uuid].compare(_, _) == expected)
      }

    def testOrdered: Property =
      for {
        uuid1 <- Gen.constant(UUID.randomUUID()).log("uuid1")
        uuid2 <- Gen.constant(UUID.randomUUID()).log("uuid2")
      } yield {
        val s1       = uuid1.toString
        val s2       = uuid2.toString
        val input1   = Uuid.unsafeFrom(s1)
        val input2   = Uuid.unsafeFrom(s2)
        val expected = s1.compare(s2)
        Result.diff(input1: Ordered[Uuid], input2: Uuid)(_.compare(_) == expected)
      }

  }
}
