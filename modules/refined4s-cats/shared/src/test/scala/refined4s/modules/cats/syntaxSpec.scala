package refined4s.modules.cats

import cats.*
import cats.syntax.all.*
import extras.core.syntax.all.*
import hedgehog.*
import hedgehog.runner.*
import refined4s.*
import refined4s.InlinedRefinedType.MoreThan2CharsString
import refined4s.modules.cats.syntax.*

/** @author Kevin Lee
  * @since 2023-12-06
  */
object syntaxSpec extends Properties {
  override def tests: List[Test] =
    RefinedNewtypeNec.tests ++
      RefinedNewtypeNel.tests ++
      ValidateAsSpec.tests ++
      ValidateNecAsSpec.tests

  object RefinedNewtypeNec {

    def tests: List[Test] = List(
      ///
      property(
        "For type T = Refined[A] and type N = NewType[T], a.refinedNewtypeNec[N] with a valid `a` should return EitherNec[String, N] = Right(N)",
        testARefinedNewtypeNecT,
      ),
      example(
        "For type T = Refined[A] and type N = NewType[T], a.refinedNewtypeNec[N] with an invalid `a` should return EitherNec[String, N] = Left(String)",
        testARefinedNewtypeNecTInvalid,
      ),
      property(
        "For type T = Refined[A] and type N = NewType[T], refinedNewtypeNec(a)[N] with a valid `a` should return EitherNec[String, N] = Right(N)",
        testRefinedNewtypeNecTA,
      ),
      example(
        "For type T = Refined[A] and type N = NewType[T], refinedNewtypeNec(a)[N] with an invalid `a` should return EitherNec[String, N] = Left(String)",
        testRefinedNewtypeNecTAInvalid,
      ),
      ///
      property(
        "For type T = InlinedRefined[A] and type N = NewType[T], a.refinedNewtypeNec[N] with a valid `a` should return EitherNec[String, N] = Right(N)",
        testInlinedRefined_ARefinedNewtypeNecT,
      ),
      property(
        "For type T = InlinedRefined[A] and type N = NewType[T], a.refinedNewtypeNec[N] with an invalid `a` should return EitherNec[String, N] = Left(String)",
        testInlinedRefined_ARefinedNewtypeNecTInvalid,
      ),
      property(
        "For type T = InlinedRefined[A] and type N = NewType[T], refinedNewtypeNec(a)[N] with a valid `a` should return EitherNec[String, N] = Right(N)",
        testInlinedRefined_RefinedNewtypeNecTA,
      ),
      property(
        "For type T = InlinedRefined[A] and type N = NewType[T], refinedNewtypeNec(a)[N] with an invalid `a` should return EitherNec[String, N] = Left(String)",
        testInlinedRefined_RefinedNewtypeNecTAInvalid,
      ),
    )

    def testARefinedNewtypeNecT: Property =
      for {
        s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
      } yield {

        val expected = NewMyType(MyType.unsafeFrom(s)).rightNec[String]
        val actual   = s.refinedNewtypeNec[NewMyType]
        actual ==== expected
      }

    def testARefinedNewtypeNecTInvalid: Result = {
      val expected =
        "Failed to create refined4s.modules.cats.syntaxSpec.NewMyType: Invalid value: []. It has to be a non-empty String but got \"\""
          .leftNec[NewMyType]
      val actual   = "".refinedNewtypeNec[NewMyType]
      actual ==== expected
    }

    def testRefinedNewtypeNecTA: Property =
      for {
        s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
      } yield {

        val expected = NewMyType(MyType.unsafeFrom(s)).rightNec[String]
        val actual   = refinedNewtypeNec(s)[NewMyType]
        actual ==== expected
      }

    def testRefinedNewtypeNecTAInvalid: Result = {
      val expected =
        "Failed to create refined4s.modules.cats.syntaxSpec.NewMyType: Invalid value: []. It has to be a non-empty String but got \"\""
          .leftNec[NewMyType]
      val actual   = refinedNewtypeNec("")[NewMyType]
      actual ==== expected
    }

    def testInlinedRefined_ARefinedNewtypeNecT: Property =
      for {
        s <- Gen.string(Gen.unicode, Range.linear(3, 10)).log("s")
      } yield {

        val expected = NewMoreThan2CharsString(MoreThan2CharsString.unsafeFrom(s)).rightNec[String]
        val actual   = s.refinedNewtypeNec[NewMoreThan2CharsString]
        (actual ==== expected).log(
          raw"""       s: ${s.encodeToUnicode}
               |  actual: ${actual.leftMap(_.map(_.encodeToUnicode))}
               |expected: ${expected.leftMap(_.map(_.encodeToUnicode))}
               |""".stripMargin
        )
      }

    def testInlinedRefined_ARefinedNewtypeNecTInvalid: Property =
      for {
        s <- Gen.string(Gen.unicode, Range.linear(0, 2)).log("s")
      } yield {
        val expected =
          s"Failed to create refined4s.modules.cats.syntaxSpec.NewMoreThan2CharsString: Invalid value: [$s]. The String should have more than 2 chars but got $s instead"
            .leftNec[NewMoreThan2CharsString]

        val actual = s.refinedNewtypeNec[NewMoreThan2CharsString]
        (actual ==== expected).log(
          raw"""       s: ${s.encodeToUnicode}
               |  actual: ${actual.leftMap(_.map(_.encodeToUnicode))}
               |expected: ${expected.leftMap(_.map(_.encodeToUnicode))}
               |""".stripMargin
        )
      }

    def testInlinedRefined_RefinedNewtypeNecTA: Property =
      for {
        s <- Gen.string(Gen.unicode, Range.linear(3, 10)).log("s")
      } yield {

        val expected = NewMoreThan2CharsString(MoreThan2CharsString.unsafeFrom(s)).rightNec[String]
        val actual   = refinedNewtypeNec(s)[NewMoreThan2CharsString]
        (actual ==== expected).log(
          raw"""       s: ${s.encodeToUnicode}
               |  actual: ${actual.leftMap(_.map(_.encodeToUnicode))}
               |expected: ${expected.leftMap(_.map(_.encodeToUnicode))}
               |""".stripMargin
        )
      }

    def testInlinedRefined_RefinedNewtypeNecTAInvalid: Property =
      for {
        s <- Gen.string(Gen.unicode, Range.linear(0, 2)).log("s")
      } yield {
        val expected =
          s"Failed to create refined4s.modules.cats.syntaxSpec.NewMoreThan2CharsString: Invalid value: [$s]. The String should have more than 2 chars but got $s instead"
            .leftNec[NewMoreThan2CharsString]
        val actual   = refinedNewtypeNec(s)[NewMoreThan2CharsString]
        (actual ==== expected).log(
          raw"""       s: ${s.encodeToUnicode}
               |  actual: ${actual.leftMap(_.map(_.encodeToUnicode))}
               |expected: ${expected.leftMap(_.map(_.encodeToUnicode))}
               |""".stripMargin
        )
      }
  }

  object RefinedNewtypeNel {

    def tests: List[Test] = List(
      ///
      property(
        "For type T = Refined[A] and type N = NewType[T], a.refinedNewtypeNel[N] with a valid `a` should return EitherNel[String, N] = Right(N)",
        testARefinedNewtypeNelT,
      ),
      example(
        "For type T = Refined[A] and type N = NewType[T], a.refinedNewtypeNel[N] with an invalid `a` should return EitherNel[String, N] = Left(String)",
        testARefinedNewtypeNelTInvalid,
      ),
      property(
        "For type T = Refined[A] and type N = NewType[T], refinedNewtypeNel(a)[N] with a valid `a` should return EitherNel[String, N] = Right(N)",
        testRefinedNewtypeNelTA,
      ),
      example(
        "For type T = Refined[A] and type N = NewType[T], refinedNewtypeNel(a)[N] with an invalid `a` should return EitherNel[String, N] = Left(String)",
        testRefinedNewtypeNelTAInvalid,
      ),
      ///
      property(
        "For type T = InlinedRefined[A] and type N = NewType[T], a.refinedNewtypeNel[N] with a valid `a` should return EitherNel[String, N] = Right(N)",
        testInlinedRefined_ARefinedNewtypeNelT,
      ),
      property(
        "For type T = InlinedRefined[A] and type N = NewType[T], a.refinedNewtypeNel[N] with an invalid `a` should return EitherNel[String, N] = Left(String)",
        testInlinedRefined_ARefinedNewtypeNelTInvalid,
      ),
      property(
        "For type T = InlinedRefined[A] and type N = NewType[T], refinedNewtypeNel(a)[N] with a valid `a` should return EitherNel[String, N] = Right(N)",
        testInlinedRefined_RefinedNewtypeNelTA,
      ),
      property(
        "For type T = InlinedRefined[A] and type N = NewType[T], refinedNewtypeNel(a)[N] with an invalid `a` should return EitherNel[String, N] = Left(String)",
        testInlinedRefined_RefinedNewtypeNelTAInvalid,
      ),
    )

    def testARefinedNewtypeNelT: Property =
      for {
        s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
      } yield {

        val expected = NewMyType(MyType.unsafeFrom(s)).rightNel[String]
        val actual   = s.refinedNewtypeNel[NewMyType]
        actual ==== expected
      }

    def testARefinedNewtypeNelTInvalid: Result = {
      val expected =
        "Failed to create refined4s.modules.cats.syntaxSpec.NewMyType: Invalid value: []. It has to be a non-empty String but got \"\""
          .leftNel[NewMyType]
      val actual   = "".refinedNewtypeNel[NewMyType]
      actual ==== expected
    }

    def testRefinedNewtypeNelTA: Property =
      for {
        s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
      } yield {

        val expected = NewMyType(MyType.unsafeFrom(s)).rightNel[String]
        val actual   = refinedNewtypeNel(s)[NewMyType]
        actual ==== expected
      }

    def testRefinedNewtypeNelTAInvalid: Result = {
      val expected =
        "Failed to create refined4s.modules.cats.syntaxSpec.NewMyType: Invalid value: []. It has to be a non-empty String but got \"\""
          .leftNel[NewMyType]
      val actual   = refinedNewtypeNel("")[NewMyType]
      actual ==== expected
    }

    def testInlinedRefined_ARefinedNewtypeNelT: Property =
      for {
        s <- Gen.string(Gen.unicode, Range.linear(3, 10)).log("s")
      } yield {

        val expected = NewMoreThan2CharsString(MoreThan2CharsString.unsafeFrom(s)).rightNel[String]
        val actual   = s.refinedNewtypeNel[NewMoreThan2CharsString]
        (actual ==== expected).log(
          raw"""       s: ${s.encodeToUnicode}
               |  actual: ${actual.leftMap(_.map(_.encodeToUnicode))}
               |expected: ${expected.leftMap(_.map(_.encodeToUnicode))}
               |""".stripMargin
        )
      }

    def testInlinedRefined_ARefinedNewtypeNelTInvalid: Property =
      for {
        s <- Gen.string(Gen.unicode, Range.linear(0, 2)).log("s")
      } yield {
        val expected =
          s"Failed to create refined4s.modules.cats.syntaxSpec.NewMoreThan2CharsString: Invalid value: [$s]. The String should have more than 2 chars but got $s instead"
            .leftNel[NewMoreThan2CharsString]

        val actual = s.refinedNewtypeNel[NewMoreThan2CharsString]
        (actual ==== expected).log(
          raw"""       s: ${s.encodeToUnicode}
               |  actual: ${actual.leftMap(_.map(_.encodeToUnicode))}
               |expected: ${expected.leftMap(_.map(_.encodeToUnicode))}
               |""".stripMargin
        )
      }

    def testInlinedRefined_RefinedNewtypeNelTA: Property =
      for {
        s <- Gen.string(Gen.unicode, Range.linear(3, 10)).log("s")
      } yield {

        val expected = NewMoreThan2CharsString(MoreThan2CharsString.unsafeFrom(s)).rightNel[String]
        val actual   = refinedNewtypeNel(s)[NewMoreThan2CharsString]
        (actual ==== expected).log(
          raw"""       s: ${s.encodeToUnicode}
               |  actual: ${actual.leftMap(_.map(_.encodeToUnicode))}
               |expected: ${expected.leftMap(_.map(_.encodeToUnicode))}
               |""".stripMargin
        )
      }

    def testInlinedRefined_RefinedNewtypeNelTAInvalid: Property =
      for {
        s <- Gen.string(Gen.unicode, Range.linear(0, 2)).log("s")
      } yield {
        val expected =
          s"Failed to create refined4s.modules.cats.syntaxSpec.NewMoreThan2CharsString: Invalid value: [$s]. The String should have more than 2 chars but got $s instead"
            .leftNel[NewMoreThan2CharsString]
        val actual   = refinedNewtypeNel(s)[NewMoreThan2CharsString]
        (actual ==== expected).log(
          raw"""       s: ${s.encodeToUnicode}
               |  actual: ${actual.leftMap(_.map(_.encodeToUnicode))}
               |expected: ${expected.leftMap(_.map(_.encodeToUnicode))}
               |""".stripMargin
        )
      }
  }

  object ValidateAsSpec {

    def tests: List[Test] = List(
      ///
      property(
        "For type T = Refined[A] and type N = NewType[T], a.validateAs[N] with a valid `a` should return EitherNel[String, N] = Right(N)",
        testAValidateAsT,
      ),
      example(
        "For type T = Refined[A] and type N = NewType[T], a.validateAs[N] with an invalid `a` should return EitherNel[String, N] = Left(String)",
        testAValidateAsTInvalid,
      ),
      property(
        "For type T = Refined[A] and type N = NewType[T], validateAs(a)[N] with a valid `a` should return EitherNel[String, N] = Right(N)",
        testValidateAsTA,
      ),
      example(
        "For type T = Refined[A] and type N = NewType[T], validateAs(a)[N] with an invalid `a` should return EitherNel[String, N] = Left(String)",
        testValidateAsTAInvalid,
      ),
      ///
      property(
        "For type T = InlinedRefined[A] and type N = NewType[T], a.validateAs[N] with a valid `a` should return EitherNel[String, N] = Right(N)",
        testInlinedRefined_AValidateAsT,
      ),
      property(
        "For type T = InlinedRefined[A] and type N = NewType[T], a.validateAs[N] with an invalid `a` should return EitherNel[String, N] = Left(String)",
        testInlinedRefined_AValidateAsTInvalid,
      ),
      property(
        "For type T = InlinedRefined[A] and type N = NewType[T], validateAs(a)[N] with a valid `a` should return EitherNel[String, N] = Right(N)",
        testInlinedRefined_ValidateAsTA,
      ),
      property(
        "For type T = InlinedRefined[A] and type N = NewType[T], validateAs(a)[N] with an invalid `a` should return EitherNel[String, N] = Left(String)",
        testInlinedRefined_ValidateAsTAInvalid,
      ),
    )

    def testAValidateAsT: Property =
      for {
        s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
      } yield {

        val expected = NewMyType(MyType.unsafeFrom(s)).valid[String]
        val actual   = s.validateAs[NewMyType]
        actual ==== expected
      }

    def testAValidateAsTInvalid: Result = {
      val expected =
        "Failed to create refined4s.modules.cats.syntaxSpec.NewMyType: Invalid value: []. It has to be a non-empty String but got \"\""
          .invalid[NewMyType]
      val actual   = "".validateAs[NewMyType]
      actual ==== expected
    }

    def testValidateAsTA: Property =
      for {
        s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
      } yield {

        val expected = NewMyType(MyType.unsafeFrom(s)).valid[String]
        val actual   = validateAs(s)[NewMyType]
        actual ==== expected
      }

    def testValidateAsTAInvalid: Result = {
      val expected =
        "Failed to create refined4s.modules.cats.syntaxSpec.NewMyType: Invalid value: []. It has to be a non-empty String but got \"\""
          .invalid[NewMyType]
      val actual   = validateAs("")[NewMyType]
      actual ==== expected
    }

    def testInlinedRefined_AValidateAsT: Property =
      for {
        s <- Gen.string(Gen.unicode, Range.linear(3, 10)).log("s")
      } yield {

        val expected = NewMoreThan2CharsString(MoreThan2CharsString.unsafeFrom(s)).valid[String]
        val actual   = s.validateAs[NewMoreThan2CharsString]
        (actual ==== expected).log(
          raw"""       s: ${s.encodeToUnicode}
               |  actual: ${actual.leftMap(_.encodeToUnicode)}
               |expected: ${expected.leftMap(_.encodeToUnicode)}
               |""".stripMargin
        )
      }

    def testInlinedRefined_AValidateAsTInvalid: Property =
      for {
        s <- Gen.string(Gen.unicode, Range.linear(0, 2)).log("s")
      } yield {
        val expected =
          s"Failed to create refined4s.modules.cats.syntaxSpec.NewMoreThan2CharsString: Invalid value: [$s]. The String should have more than 2 chars but got $s instead"
            .invalid[NewMoreThan2CharsString]

        val actual = s.validateAs[NewMoreThan2CharsString]
        (actual ==== expected).log(
          raw"""       s: ${s.encodeToUnicode}
               |  actual: ${actual.leftMap(_.encodeToUnicode)}
               |expected: ${expected.leftMap(_.encodeToUnicode)}
               |""".stripMargin
        )
      }

    def testInlinedRefined_ValidateAsTA: Property =
      for {
        s <- Gen.string(Gen.unicode, Range.linear(3, 10)).log("s")
      } yield {

        val expected = NewMoreThan2CharsString(MoreThan2CharsString.unsafeFrom(s)).valid[String]
        val actual   = validateAs(s)[NewMoreThan2CharsString]
        (actual ==== expected).log(
          raw"""       s: ${s.encodeToUnicode}
               |  actual: ${actual.leftMap(_.encodeToUnicode)}
               |expected: ${expected.leftMap(_.encodeToUnicode)}
               |""".stripMargin
        )
      }

    def testInlinedRefined_ValidateAsTAInvalid: Property =
      for {
        s <- Gen.string(Gen.unicode, Range.linear(0, 2)).log("s")
      } yield {
        val expected =
          s"Failed to create refined4s.modules.cats.syntaxSpec.NewMoreThan2CharsString: Invalid value: [$s]. The String should have more than 2 chars but got $s instead"
            .invalid[NewMoreThan2CharsString]
        val actual   = validateAs(s)[NewMoreThan2CharsString]
        (actual ==== expected).log(
          raw"""       s: ${s.encodeToUnicode}
               |  actual: ${actual.leftMap(_.encodeToUnicode)}
               |expected: ${expected.leftMap(_.encodeToUnicode)}
               |""".stripMargin
        )
      }
  }

  object ValidateNecAsSpec {

    def tests: List[Test] = List(
      ///
      property(
        "For type T = Refined[A] and type N = NewType[T], a.validateNecAs[N] with a valid `a` should return EitherNel[String, N] = Right(N)",
        testAValidateNecAsT,
      ),
      example(
        "For type T = Refined[A] and type N = NewType[T], a.validateNecAs[N] with an invalid `a` should return EitherNel[String, N] = Left(String)",
        testAValidateNecAsTInvalid,
      ),
      property(
        "For type T = Refined[A] and type N = NewType[T], validateNecAs(a)[N] with a valid `a` should return EitherNel[String, N] = Right(N)",
        testValidateNecAsTA,
      ),
      example(
        "For type T = Refined[A] and type N = NewType[T], validateNecAs(a)[N] with an invalid `a` should return EitherNel[String, N] = Left(String)",
        testValidateNecAsTAInvalid,
      ),
      ///
      property(
        "For type T = InlinedRefined[A] and type N = NewType[T], a.validateNecAs[N] with a valid `a` should return EitherNel[String, N] = Right(N)",
        testInlinedRefined_AValidateNecAsT,
      ),
      property(
        "For type T = InlinedRefined[A] and type N = NewType[T], a.validateNecAs[N] with an invalid `a` should return EitherNel[String, N] = Left(String)",
        testInlinedRefined_AValidateNecAsTInvalid,
      ),
      property(
        "For type T = InlinedRefined[A] and type N = NewType[T], validateNecAs(a)[N] with a valid `a` should return EitherNel[String, N] = Right(N)",
        testInlinedRefined_ValidateNecAsTA,
      ),
      property(
        "For type T = InlinedRefined[A] and type N = NewType[T], validateNecAs(a)[N] with an invalid `a` should return EitherNel[String, N] = Left(String)",
        testInlinedRefined_ValidateNecAsTAInvalid,
      ),
    )

    def testAValidateNecAsT: Property =
      for {
        s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
      } yield {

        val expected = NewMyType(MyType.unsafeFrom(s)).validNec[String]
        val actual   = s.validateNecAs[NewMyType]
        actual ==== expected
      }

    def testAValidateNecAsTInvalid: Result = {
      val expected =
        "Failed to create refined4s.modules.cats.syntaxSpec.NewMyType: Invalid value: []. It has to be a non-empty String but got \"\""
          .invalidNec[NewMyType]
      val actual   = "".validateNecAs[NewMyType]
      actual ==== expected
    }

    def testValidateNecAsTA: Property =
      for {
        s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
      } yield {

        val expected = NewMyType(MyType.unsafeFrom(s)).validNec[String]
        val actual   = validateNecAs(s)[NewMyType]
        actual ==== expected
      }

    def testValidateNecAsTAInvalid: Result = {
      val expected =
        "Failed to create refined4s.modules.cats.syntaxSpec.NewMyType: Invalid value: []. It has to be a non-empty String but got \"\""
          .invalidNec[NewMyType]
      val actual   = validateNecAs("")[NewMyType]
      actual ==== expected
    }

    def testInlinedRefined_AValidateNecAsT: Property =
      for {
        s <- Gen.string(Gen.unicode, Range.linear(3, 10)).log("s")
      } yield {

        val expected = NewMoreThan2CharsString(MoreThan2CharsString.unsafeFrom(s)).validNec[String]
        val actual   = s.validateNecAs[NewMoreThan2CharsString]
        (actual ==== expected).log(
          raw"""       s: ${s.encodeToUnicode}
               |  actual: ${actual.leftMap(_.map(_.encodeToUnicode))}
               |expected: ${expected.leftMap(_.map(_.encodeToUnicode))}
               |""".stripMargin
        )
      }

    def testInlinedRefined_AValidateNecAsTInvalid: Property =
      for {
        s <- Gen.string(Gen.unicode, Range.linear(0, 2)).log("s")
      } yield {
        val expected =
          s"Failed to create refined4s.modules.cats.syntaxSpec.NewMoreThan2CharsString: Invalid value: [$s]. The String should have more than 2 chars but got $s instead"
            .invalidNec[NewMoreThan2CharsString]

        val actual = s.validateNecAs[NewMoreThan2CharsString]
        (actual ==== expected).log(
          raw"""       s: ${s.encodeToUnicode}
               |  actual: ${actual.leftMap(_.map(_.encodeToUnicode))}
               |expected: ${expected.leftMap(_.map(_.encodeToUnicode))}
               |""".stripMargin
        )
      }

    def testInlinedRefined_ValidateNecAsTA: Property =
      for {
        s <- Gen.string(Gen.unicode, Range.linear(3, 10)).log("s")
      } yield {

        val expected = NewMoreThan2CharsString(MoreThan2CharsString.unsafeFrom(s)).validNec[String]
        val actual   = validateNecAs(s)[NewMoreThan2CharsString]
        (actual ==== expected).log(
          raw"""       s: ${s.encodeToUnicode}
               |  actual: ${actual.leftMap(_.map(_.encodeToUnicode))}
               |expected: ${expected.leftMap(_.map(_.encodeToUnicode))}
               |""".stripMargin
        )
      }

    def testInlinedRefined_ValidateNecAsTAInvalid: Property =
      for {
        s <- Gen.string(Gen.unicode, Range.linear(0, 2)).log("s")
      } yield {
        val expected =
          s"Failed to create refined4s.modules.cats.syntaxSpec.NewMoreThan2CharsString: Invalid value: [$s]. The String should have more than 2 chars but got $s instead"
            .invalidNec[NewMoreThan2CharsString]
        val actual   = validateNecAs(s)[NewMoreThan2CharsString]
        (actual ==== expected).log(
          raw"""       s: ${s.encodeToUnicode}
               |  actual: ${actual.leftMap(_.map(_.encodeToUnicode))}
               |expected: ${expected.leftMap(_.map(_.encodeToUnicode))}
               |""".stripMargin
        )
      }
  }

  ///

  type MyType = MyType.Type
  @SuppressWarnings(Array("org.wartremover.warts.Equals"))
  object MyType extends Refined[String] {

    override inline def invalidReason(a: String): String =
      "It has to be a non-empty String but got \"" + a + "\""

    override inline def predicate(a: String): Boolean = a != ""

    given eqMyType: Eq[MyType] = deriving[Eq]

    given showMyType: Show[MyType] = deriving[Show]
  }

  type NewMyType = NewMyType.Type
  object NewMyType extends Newtype[MyType]

  type NewMoreThan2CharsString = NewMoreThan2CharsString.Type
  object NewMoreThan2CharsString extends Newtype[MoreThan2CharsString]
}
