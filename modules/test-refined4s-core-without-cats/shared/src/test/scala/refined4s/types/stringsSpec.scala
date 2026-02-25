package refined4s.types

import hedgehog.*
import hedgehog.runner.*
import refined4s.*

import java.util.UUID
import refined4s.internal.UuidV7Macros

/** @author Kevin Lee
  * @since 2023-04-25
  */
object stringsSpec extends Properties {
  override def tests: List[Test] = NonEmptyStringSpec.tests ++ NonBlankStringSpec.tests ++ UuidSpec.tests ++ UuidV7Spec.tests

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
      val expected = "Invalid value: []. It must be a non-empty String."
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
      val expected = "Invalid value: []. It must be a non-empty String."
      try {
        val _ = NonEmptyString.unsafeFrom("")
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

  object NonBlankStringSpec {
    import all.NonBlankString

    def tests: List[Test] = List(
      example("test NonBlankString.apply", testApply),
      example("test NonBlankString.apply (2)", testApply2),
      example("test NonBlankString.apply with invalid", testApplyWithInvalid),
      property("test NonBlankString.from(valid)", testFromValid),
      property("test NonBlankString.from(invalid)", testFromInvalid),
      property("test NonBlankString.unsafeFrom(valid)", testUnsafeFromValid),
      property("test NonBlankString.unsafeFrom(invalid)", testUnsafeFromInvalid),
      property("test NonBlankString.value", testValue),
      property("test NonBlankString.unapply", testUnapplyWithPatternMatching),
      property("test NonBlankString ++ NonBlankString", testNonBlankStringPlusNonBlankString),
      property("test NonBlankString.prependString(String)", testNonBlankStringPrependStringString),
      property("test NonBlankString.appendString(String)", testNonBlankStringAppendStringString),
      property("test Ordering[NonBlankString]", testOrdering),
      property("test Ordered[NonBlankString]", testOrdered),
      example(
        "test strings.isValidNotAllWhitespaceNonEmptyString with a valid value",
        testStringsIsValidNotAllWhitespaceNonEmptyStringValid,
      ),
      example(
        "test strings.isValidNotAllWhitespaceNonEmptyString with an invalid value",
        testStringsIsValidNotAllWhitespaceNonEmptyStringInvalid,
      ),
      example(
        "test strings.isValidNotAllWhitespaceNonEmptyString with an invalid String literal param value",
        testStringsIsValidNotAllWhitespaceNonEmptyStringWithInvalidLiteral,
      ),
    )

    def testApply: Result = {
      /* The actual test is whether this compiles or not actual ==== expected is meaningless here */
      val expected = NonBlankString("blah")
      val actual   = NonBlankString("blah")
      actual ==== expected
    }

    def testApply2: Result = {

      import scala.compiletime.testing.typeChecks

      val shouldCompile1 = typeChecks(
        """
          import strings.*
          NonBlankString("blah")
        """
      )

      Result.assert(shouldCompile1)
    }

    @SuppressWarnings(Array("org.wartremover.warts.ToString"))
    def testApplyWithInvalid: Result = {

      import scala.compiletime.testing.{typeChecks, typeCheckErrors}

      val shouldFail1 = typeChecks(
        """
          import strings.*
          NonBlankString("")
        """
      )

      val shouldFail2 = typeChecks(
        """
          import strings.*
          NonBlankString(" ")
        """
      )

      val shouldFail3 = typeChecks(
        """
          import strings.*
          NonBlankString("\n")
        """
      )

      val shouldFail4 = typeChecks(
        """
          import strings.*
          NonBlankString("\t")
        """
      )

      val shouldFail5 = typeCheckErrors(
        """
          import strings.*
          val s = "abc"
          NonBlankString(s)
        """
      )

      Result.all(
        List(
          (shouldFail1 ==== false).log("Compilation should have been failed but it didn't for NonBlankString(\"\")"),
          (shouldFail2 ==== false).log("Compilation should have been failed but it didn't for NonBlankString(\" \")"),
          (shouldFail3 ==== false).log("Compilation should have been failed but it didn't for NonBlankString(\"\\n\")"),
          (shouldFail4 ==== false).log("Compilation should have been failed but it didn't for NonBlankString(\"\\t\")"),
          (shouldFail5
            .matchPattern {
              case List(
                    scala
                      .compiletime
                      .testing
                      .Error(
                        "The argument passed to NotAllWhitespaceNonEmptyString.apply must be a string literal.",
                        _,
                        _,
                        _,
                      )
                  ) =>
            })
            .log(
              "Compilation should have been failed but it didn't for NonBlankString(s) (non-literal String). " +
                s"Errors: ${shouldFail5.map(_.toString).mkString("[", ", ", "]")}"
            ),
        )
      )
    }

    def testFromValid: Property =
      for {
        nonWhitespaceString <- Gen.string(hedgehog.extra.Gens.genNonWhitespaceChar, Range.linear(1, 10)).log("nonWhitespaceString")
        whitespaceString    <-
          Gen.string(hedgehog.extra.Gens.genCharByRange(strings.WhitespaceCharRange), Range.linear(1, 10)).log("whitespaceString")

        s <- Gen.constant(scala.util.Random.shuffle((nonWhitespaceString + whitespaceString).toList).mkString).log("s")
      } yield {
        val expected = NonBlankString.unsafeFrom(s)
        val actual   = NonBlankString.from(s)
        actual ==== Right(expected)
      }

    def testFromInvalid: Property =
      for {
        s <-
          Gen
            .frequency1(
              95 -> Gen.string(hedgehog.extra.Gens.genCharByRange(strings.WhitespaceCharRange), Range.linear(1, 10)),
              5  -> Gen.constant(""),
            )
            .log("s")
      } yield {
        val expected =
          s"Invalid value: [$s], unicode=[${s.map(c => "\\u%04x".format(c.toInt)).mkString}]. " +
            "It must be not all whitespace non-empty String."
        val actual   = NonBlankString.from(s)
        actual ==== Left(expected)
      }

    def testUnsafeFromValid: Property =
      for {
        nonWhitespaceString <- Gen.string(hedgehog.extra.Gens.genNonWhitespaceChar, Range.linear(1, 10)).log("nonWhitespaceString")
        whitespaceString    <-
          Gen.string(hedgehog.extra.Gens.genCharByRange(strings.WhitespaceCharRange), Range.linear(1, 10)).log("whitespaceString")

        s <- Gen.constant(scala.util.Random.shuffle((nonWhitespaceString + whitespaceString).toList).mkString).log("s")
      } yield {
        val expected = NonBlankString.unsafeFrom(s)
        val actual   = NonBlankString.unsafeFrom(s)
        actual ==== expected
      }

    def testUnsafeFromInvalid: Property =
      for {
        s <-
          Gen
            .frequency1(
              5  -> Gen.constant(""),
              95 -> Gen.string(hedgehog.extra.Gens.genCharByRange(strings.WhitespaceCharRange), Range.linear(1, 10)),
            )
            .log("s")
      } yield {
        val expected =
          s"Invalid value: [$s], unicode=[${s.map(c => "\\u%04x".format(c.toInt)).mkString}]. " +
            "It must be not all whitespace non-empty String."
        try {
          val _ = NonBlankString.unsafeFrom(s)
          Result
            .failure
            .log("""IllegalArgumentException was expected from NonBlankString.unsafeFrom(""), but it was not thrown.""")
        } catch {
          case ex: IllegalArgumentException =>
            ex.getMessage ==== expected

        }
      }

    def testValue: Property =
      for {
        nonWhitespaceString <- Gen.string(hedgehog.extra.Gens.genNonWhitespaceChar, Range.linear(1, 10)).log("nonWhitespaceString")
        whitespaceString    <-
          Gen.string(hedgehog.extra.Gens.genCharByRange(strings.WhitespaceCharRange), Range.linear(1, 10)).log("whitespaceString")

        s <- Gen.constant(scala.util.Random.shuffle((nonWhitespaceString + whitespaceString).toList).mkString).log("s")
      } yield {
        val expected = s
        val actual   = NonBlankString.unsafeFrom(s)
        actual.value ==== expected
      }

    def testUnapplyWithPatternMatching: Property =
      for {
        nonWhitespaceString <- Gen.string(hedgehog.extra.Gens.genNonWhitespaceChar, Range.linear(1, 10)).log("nonWhitespaceString")
        whitespaceString    <-
          Gen.string(hedgehog.extra.Gens.genCharByRange(strings.WhitespaceCharRange), Range.linear(1, 10)).log("whitespaceString")

        s <- Gen.constant(scala.util.Random.shuffle((nonWhitespaceString + whitespaceString).toList).mkString).log("s")
      } yield {
        val expected = s
        val nes      = NonBlankString.unsafeFrom(s)
        nes match {
          case NonBlankString(actual) =>
            actual ==== expected
        }
      }

    def testNonBlankStringPlusNonBlankString: Property =
      for {
        s1 <- Gen.string(hedgehog.extra.Gens.genNonWhitespaceChar, Range.linear(1, 10)).log("s1")
        s2 <- Gen.string(hedgehog.extra.Gens.genNonWhitespaceChar, Range.linear(1, 10)).log("s2")

        expected <- Gen.constant(s1 + s2).map(NonBlankString.unsafeFrom).log("expected")
      } yield {
        val nbs1   = NonBlankString.unsafeFrom(s1)
        val nbs2   = NonBlankString.unsafeFrom(s2)
        val actual = nbs1 ++ nbs2
        actual ==== expected
      }

    def testNonBlankStringPrependStringString: Property =
      for {
        s1 <- Gen.string(Gen.unicode, Range.linear(1, 3)).log("s1")
        s2 <- Gen.string(hedgehog.extra.Gens.genNonWhitespaceChar, Range.linear(1, 10)).log("s2")

        expected <- Gen.constant(s1 + s2).map(NonBlankString.unsafeFrom).log("expected")
      } yield {
        val nbs    = NonBlankString.unsafeFrom(s2)
        val actual = nbs.prependString(s1)
        actual ==== expected
      }

    def testNonBlankStringAppendStringString: Property =
      for {
        s1 <- Gen.string(hedgehog.extra.Gens.genNonWhitespaceChar, Range.linear(1, 10)).log("s1")
        s2 <- Gen.string(Gen.unicode, Range.linear(1, 3)).log("s2")

        expected <- Gen.constant(s1 + s2).map(NonBlankString.unsafeFrom).log("expected")
      } yield {
        val nbs1   = NonBlankString.unsafeFrom(s1)
        val actual = nbs1.appendString(s2)
        actual ==== expected
      }

    def testOrdering: Property =
      for {
        s1 <- Gen.string(hedgehog.extra.Gens.genNonWhitespaceChar, Range.linear(1, 10)).log("s1")
        s2 <- Gen.string(hedgehog.extra.Gens.genNonWhitespaceChar, Range.linear(1, 10)).log("s2")
      } yield {
        val input1   = NonBlankString.unsafeFrom(s1)
        val input2   = NonBlankString.unsafeFrom(s2)
        val expected = s1.compare(s2)
        Result.diff(input1, input2)(Ordering[NonBlankString].compare(_, _) == expected)
      }

    def testOrdered: Property =
      for {
        s1 <- Gen.string(hedgehog.extra.Gens.genNonWhitespaceChar, Range.linear(1, 10)).log("s1")
        s2 <- Gen.string(hedgehog.extra.Gens.genNonWhitespaceChar, Range.linear(1, 10)).log("s2")
      } yield {
        val input1   = NonBlankString.unsafeFrom(s1)
        val input2   = NonBlankString.unsafeFrom(s2)
        val expected = s1.compare(s2)
        Result.diff(input1: Ordered[NonBlankString], input2: NonBlankString)(_.compare(_) == expected)
      }

    inline def runStringIsValidNotAllWhitespaceNonEmptyString(inline a: String): Boolean =
      ${ strings.isValidNotAllWhitespaceNonEmptyString('a) }

    def testStringsIsValidNotAllWhitespaceNonEmptyStringValid: Result = {
      val expected = true
      val actual   = runStringIsValidNotAllWhitespaceNonEmptyString("blah")
      (actual ==== expected)
        .log("""strings.runStringIsValidNotAllWhitespaceNonEmptyString("blah") should return true but it returned false.""")
    }

    def testStringsIsValidNotAllWhitespaceNonEmptyStringInvalid: Result = {
      val expected = false
      val actual   = runStringIsValidNotAllWhitespaceNonEmptyString(" \n\t\r")
      (actual ==== expected)
        .log("""strings.runStringIsValidNotAllWhitespaceNonEmptyString(" \n\t\r") should return false but it returned true""")
    }

    def testStringsIsValidNotAllWhitespaceNonEmptyStringWithInvalidLiteral: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expectedMessage = "The argument passed to NotAllWhitespaceNonEmptyString.apply must be a string literal."

      val actual = typeCheckErrors(
        """
          val s = "blah blah"
          runStringIsValidNotAllWhitespaceNonEmptyString(s)
        """
      )

      val actualErrorMessage = actual.map(_.message).mkString
      (actualErrorMessage ==== expectedMessage)
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
      val expected1 = "Invalid value: [blah]. It must be UUID."
      val expected2 = "Invalid value: []. It must be UUID."
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
      val expected = "Invalid value: [blah]. It must be UUID."
      try {
        val _ = Uuid.unsafeFrom("blah")
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

  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  object UuidV7Spec {

    import all.UuidV7

    def tests: List[Test] = List(
      example("test UuidV7.apply(valid)", testApplyValid),
      example("test UuidV7.apply(UUID.fromString(String literal))", testApplyUUIDFromStringWithStringLiteral),
      example("test val uuid: UUID ; UuidV7.apply(uuid)", testApplyUUIDVal),
      example("test UuidV7.apply(invalid)", testApplyInvalid),
      property("test UuidV7.fromString(valid)", testFromStringValid),
      example("test UuidV7.fromString(invalid)", testFromStringInvalid),
      property("test UuidV7.from(valid UUID v7)", testFromUuidV7),
      example("test UuidV7.from(UUID v4)", testFromUuidV4),
      property("test UuidV7.unsafeFromString(valid)", testUnsafeFromStringValid),
      example("test UuidV7.unsafeFromString(invalid)", testUnsafeFromStringInvalid),
      property("test UuidV7.unsafeFrom(valid UUID v7)", testUnsafeFromUuidV7),
      example("test UuidV7.unsafeFrom(UUID v4)", testUnsafeFromUuidV4),
      property("test UuidV7.value", testValue),
      property("test UuidV7.unapply", testUnapplyWithPatternMatching),
      property("test UuidV7.toUuid", testToUuid),
      property("test UuidV7.toUUID", testToUUID),
      property("test Ordering[UuidV7]", testOrdering),
      property("test Ordered[UuidV7]", testOrdered),
      property("test UuidV7.generate() valid format", testGenerateValidFormat),
      property("test UuidV7.generate() should return unique UUID v7", testGenerateUnique),
      property("test UuidV7.generate() is monotonic", testGenerateMonotonic),
      property("test UuidV7.generate() is monotonic (using UuidV7.compareTo)", testGenerateMonotonicWithUuidV7CompareTo),
    )

    def testApplyValid: Result = {
      val expected = UUID.fromString("018f2f2c-e160-7000-8000-000000000000")
      val actual   = UuidV7("018f2f2c-e160-7000-8000-000000000000") // Needs to match validUuidV7 literal exactly
      actual.value ==== expected
    }

    def testApplyUUIDFromStringWithStringLiteral: Result = {
      val expected = UUID.fromString("018f2f2c-e160-7000-8000-000000000000")
      val actual   = UuidV7(UUID.fromString("018f2f2c-e160-7000-8000-000000000000"))
      actual.value ==== expected
    }

    def testApplyUUIDVal: Result = {
      import compiletime.testing.typeCheckErrors

      val expected =
        s"""${UuidV7Macros.UnexpectedUuidWithLiteralErrorMessage}
           |
           |If you did something like this
           |  ```
           |  val uuid = UUID.fromString("018e6c7a-36db-79b8-a15d-852438885cb1")
           |  UuidV7(uuid)
           |  ```
           |  NOTE: "018e6c7a-36db-79b8-a15d-852438885cb1" is just an example value, not yours.
           |
           |please do this instead.
           |  ```
           |  UuidV7(UUID.fromString("018e6c7a-36db-79b8-a15d-852438885cb1"))
           |  ```
           |
           |NOTE: UuidV7(java.util.UUID) only works with String literal.
           |      So the following will not work.
           |      ```
           |      val uuid = "018e6c7a-36db-79b8-a15d-852438885cb1"
           |      UuidV7(UUID.fromString(uuid))
           |      ```
           |""".stripMargin

      val actual = typeCheckErrors(
        """
          val uuid = UUID.fromString("018f2f2c-e160-7000-8000-000000000000")

          UuidV7(uuid)
        """
      ).map(_.message).mkString

      actual ==== expected
    }

    def testApplyInvalid: Result = {
      import scala.compiletime.testing.typeCheckErrors

      val shouldCompile1 = typeCheckErrors(
        """
          UuidV7("018f2f2c-e160-7000-8000-000000000000")
        """
      ).map(_.message).mkString

      val expectedError1    = "Invalid UuidV7 value: Invalid UUID string: blah"
      val shouldNotCompile1 = typeCheckErrors(
        """
          UuidV7("blah")
        """
      ).map(_.message).mkString

      val expectedError2 =
        """Invalid UuidV7 value: "3108715a-6477-4cd1-9ace-85b1260be03d"
          |  It is a valid UUID string, but it is not a valid UUID v7 value.
          |    - Expected version=7 and variant=2
          |    -   Actual version=4 and variant=2
          |""".stripMargin

      val shouldNotCompile2 = typeCheckErrors(
        """
          UuidV7("3108715a-6477-4cd1-9ace-85b1260be03d") // v4 UUID
        """
      ).map(_.message).mkString

      Result.all(
        List(
          (shouldCompile1 ==== "").log("UuidV7 with valid UUIDv7 literal should compile"),
          (shouldNotCompile1 ==== expectedError1).log("UuidV7 with invalid string should not compile"),
          (shouldNotCompile2 ==== expectedError2).log("UuidV7 with v4 UUID literal should not compile"),
        )
      )
    }

    def testFromStringValid: Property =
      for {
        uuid <- Gen
                  .elementUnsafe(UuidV7TestTools.validUuidV7Strings)
                  .map(UUID.fromString)
                  .log("uuid")
      } yield {
        val s        = uuid.toString
        val expected = UuidV7.unsafeFromString(s)
        val actual   = UuidV7.fromString(s)
        actual ==== Right(expected)
      }

    def testFromStringInvalid: Result = {
      val expected1 =
        "Invalid value: [blah]. It must be UUID v7 (RFC 9562) - For more info, see https://www.rfc-editor.org/rfc/rfc9562#name-uuid-version-7."
      val expected2 =
        s"Invalid value: [${UuidV7TestTools.InvalidUuidVersion}]. It must be UUID v7 (RFC 9562) - For more info, see https://www.rfc-editor.org/rfc/rfc9562#name-uuid-version-7."
      val actual1   = UuidV7.fromString("blah")
      val actual2   = UuidV7.fromString(UuidV7TestTools.InvalidUuidVersion)
      Result.all(
        List(
          actual1 ==== Left(expected1),
          actual2 ==== Left(expected2),
        )
      )
    }

    def testFromUuidV7: Property =
      for {
        uuid <- Gen
                  .elementUnsafe(UuidV7TestTools.validUuidV7Strings)
                  .map(UUID.fromString)
                  .log("uuid")
      } yield {
        val expected = UuidV7.unsafeFromString(uuid.toString)
        val actual   = UuidV7.from(uuid)
        actual ==== Right(expected)
      }

    def testFromUuidV4: Result = {
      val expected =
        s"Invalid value: [${UuidV7TestTools.InvalidUuidVersion}]. It must be UUID v7 (RFC 9562) - For more info, see https://www.rfc-editor.org/rfc/rfc9562#name-uuid-version-7."
      val actual   = UuidV7.from(UUID.fromString(UuidV7TestTools.InvalidUuidVersion))

      actual ==== Left(expected)
    }

    def testUnsafeFromStringValid: Property =
      for {
        uuid <- Gen.constant(UUID.fromString(UuidV7TestTools.ValidUuidV7)).log("uuid")
      } yield {
        val s        = uuid.toString
        val expected = UuidV7.unsafeFromString(s)
        val actual   = UuidV7.unsafeFromString(s)
        actual ==== expected
      }

    def testUnsafeFromStringInvalid: Result = {
      val expected =
        "Invalid value: [blah]. It must be UUID v7 (RFC 9562) - For more info, see https://www.rfc-editor.org/rfc/rfc9562#name-uuid-version-7."
      try {
        val _ = UuidV7.unsafeFromString("blah")
        Result.failure.log("""IllegalArgumentException was expected from UuidV7.unsafeFrom("blah")""")
      } catch {
        case ex: IllegalArgumentException =>
          ex.getMessage ==== expected
      }
    }

    def testUnsafeFromUuidV7: Property =
      for {
        uuid <- Gen
                  .elementUnsafe(UuidV7TestTools.validUuidV7Strings)
                  .map(UUID.fromString)
                  .log("uuid")
      } yield {
        val expected = UuidV7.unsafeFromString(uuid.toString)
        val actual   = UuidV7.unsafeFrom(uuid)
        actual ==== expected
      }

    def testUnsafeFromUuidV4: Result = {
      val expected =
        s"Invalid value: [${UuidV7TestTools.InvalidUuidVersion}]. It must be UUID v7 (RFC 9562) - For more info, see https://www.rfc-editor.org/rfc/rfc9562#name-uuid-version-7."

      try {
        val _ = UuidV7.unsafeFrom(UUID.fromString(UuidV7TestTools.InvalidUuidVersion))
        Result
          .failure
          .log(
            s"""IllegalArgumentException was expected from UuidV7.unsafeFromJavaUuid(UUID.fromString(${UuidV7TestTools.InvalidUuidVersion}))"""
          )
      } catch {
        case ex: IllegalArgumentException =>
          ex.getMessage ==== expected
      }
    }

    def testValue: Property =
      for {
        uuid <- Gen.elementUnsafe(UuidV7TestTools.validUuidV7Strings).log("uuid")
      } yield {
        val expected = UUID.fromString(uuid)
        val actual   = UuidV7.unsafeFromString(uuid).value
        actual ==== expected
      }

    def testUnapplyWithPatternMatching: Property =
      for {
        uuid <- Gen.elementUnsafe(UuidV7TestTools.validUuidV7Strings).log("uuid")
      } yield {
        val expected = UUID.fromString(uuid)
        val uuidV7   = UuidV7.unsafeFromString(uuid)
        uuidV7 match {
          case UuidV7(actual) =>
            actual ==== expected
        }
      }

    def testToUuid: Property =
      for {
        uuid <- Gen.elementUnsafe(UuidV7TestTools.validUuidV7Strings).log("uuid")
      } yield {
        val uuidV7 = UuidV7.unsafeFromString(uuid)

        val expected = refined4s.types.all.Uuid(UUID.fromString(uuid))
        val actual   = uuidV7.toUuid
        actual ==== expected
      }

    def testToUUID: Property =
      for {
        uuid <- Gen.elementUnsafe(UuidV7TestTools.validUuidV7Strings).log("uuid")
      } yield {
        val uuidV7 = UuidV7.unsafeFromString(uuid)

        val expected = UUID.fromString(uuid)
        val actual   = uuidV7.toUUID
        actual ==== expected
      }

    def testOrdering: Property =
      for {
        uuid1 <- Gen.constant(UUID.fromString("018f2f2c-e160-7000-8000-000000000000")).log("uuid1")
        uuid2 <- Gen.constant(UUID.fromString("018f2f2c-e160-7000-8000-000000000001")).log("uuid2")
      } yield {
        val s1       = uuid1.toString
        val s2       = uuid2.toString
        val input1   = UuidV7.unsafeFromString(s1)
        val input2   = UuidV7.unsafeFromString(s2)
        val expected = s1.compare(s2)
        Result.diff(input1, input2)(Ordering[UuidV7].compare(_, _) == expected)
      }

    def testOrdered: Property =
      for {
        uuid1 <- Gen.constant(UUID.fromString("018f2f2c-e160-7000-8000-000000000000")).log("uuid1")
        uuid2 <- Gen.constant(UUID.fromString("018f2f2c-e160-7000-8000-000000000001")).log("uuid2")
      } yield {
        val s1       = uuid1.toString
        val s2       = uuid2.toString
        val input1   = UuidV7.unsafeFromString(s1)
        val input2   = UuidV7.unsafeFromString(s2)
        val expected = s1.compare(s2)
        Result.diff(input1: Ordered[UuidV7], input2: UuidV7)(_.compare(_) == expected)
      }

    def testGenerateValidFormat: Property = for {
      generated <- Gen.constant(UuidV7.generate()).log("generated")
    } yield {
      val uuid = generated.toUUID
      Result.all(
        List(
          (uuid.version() ==== 7).log("Version should be 7"),
          (uuid.variant() ==== 2).log("Variant should be 2"),
        )
      )
    }

    def testGenerateUnique: Property = for {
      generated <- Gen.constant((1 to 10_000).map(_ => UuidV7.generate())).log("generated")
    } yield {
      val uuids = generated.distinct
      Result.diffNamed("UuidV7.generate() should return a unique UUID v7", uuids.length, 10_000)(_ == _)
    }

    def testGenerateMonotonic: Property = for {
      generated <- Gen.constant((1 to 10_000).map(_ => UuidV7.generate())).log("generated")
    } yield {

      val nonMonotonicIdPairs = generated.sliding(2).flatMap {
        case Seq(prev, next) =>
          val prevUUID    = prev.toUUID
          val nextUUID    = next.toUUID
          /* UUIDv7 is lexicographically sortable, so compareTo works naturally */
          val isMonotonic = prevUUID.compareTo(nextUUID) < 0
          if isMonotonic then Option.empty else Some((prev, next))
        case somethingElse =>
          sys.error(s"It should not reach here. ${somethingElse}")
      }

      Result
        .assert(nonMonotonicIdPairs.isEmpty)
        .log(
          s"""Generated UUIDv7 should be strictly monotonically increasing
           |  Invalid monotonic pairs: (left should be less than right)
           |  ${nonMonotonicIdPairs.mkString("  ", "\n    ", "")}
           |""".stripMargin
        )
    }

    def testGenerateMonotonicWithUuidV7CompareTo: Property = for {
      generated <- Gen.constant((1 to 10_000).map(_ => UuidV7.generate())).log("generated")
    } yield {

      val nonMonotonicIdPairs = generated.sliding(2).flatMap {
        case Seq(prev, next) =>
          /* UUIDv7 is lexicographically sortable, so compareTo works naturally */
          val isMonotonic = prev.compareTo(next) < 0
          if isMonotonic then Option.empty else Some((prev, next))
        case somethingElse =>
          sys.error(s"It should not reach here. ${somethingElse}")
      }

      Result
        .assert(nonMonotonicIdPairs.isEmpty)
        .log(
          s"""Generated UUIDv7 should be strictly monotonically increasing
           |  Invalid monotonic pairs: (left should be less than right)
           |  ${nonMonotonicIdPairs.mkString("  ", "\n    ", "")}
           |""".stripMargin
        )
    }

  }

}
