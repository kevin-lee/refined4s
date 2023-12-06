package refined4s.cats

import cats.*
import cats.syntax.all.*
import extras.core.syntax.all.*
import hedgehog.*
import hedgehog.runner.*
import refined4s.*
import refined4s.InlinedRefinedType.MoreThan2CharsString
import refined4s.cats.syntax.*

/** @author Kevin Lee
  * @since 2023-12-06
  */
object syntaxSpec extends Properties {
  override def tests: List[Test] = List(
    ///
    property(
      "For type T = Refined[A] and type N = NewType[T], a.validateAs[N] with a valid `a` should return EitherNec[String, N] = Right(N)",
      testAValidateAsT,
    ),
    example(
      "For type T = Refined[A] and type N = NewType[T], a.validateAs[N] with an invalid `a` should return EitherNec[String, N] = Left(String)",
      testAValidateAsTInvalid,
    ),
    property(
      "For type T = Refined[A] and type N = NewType[T], validateAs(a)[N] with a valid `a` should return EitherNec[String, N] = Right(N)",
      testValidateAsTA,
    ),
    example(
      "For type T = Refined[A] and type N = NewType[T], validateAs(a)[N] with an invalid `a` should return EitherNec[String, N] = Left(String)",
      testValidateAsTAInvalid,
    ),
    ///
    property(
      "For type T = InlinedRefined[A] and type N = NewType[T], a.validateAs[N] with a valid `a` should return EitherNec[String, N] = Right(N)",
      testInlinedRefined_AValidateAsT,
    ),
    property(
      "For type T = InlinedRefined[A] and type N = NewType[T], a.validateAs[N] with an invalid `a` should return EitherNec[String, N] = Left(String)",
      testInlinedRefined_AValidateAsTInvalid,
    ),
    property(
      "For type T = InlinedRefined[A] and type N = NewType[T], validateAs(a)[N] with a valid `a` should return EitherNec[String, N] = Right(N)",
      testInlinedRefined_ValidateAsTA,
    ),
    property(
      "For type T = InlinedRefined[A] and type N = NewType[T], validateAs(a)[N] with an invalid `a` should return EitherNec[String, N] = Left(String)",
      testInlinedRefined_ValidateAsTAInvalid,
    ),
  )

  def testAValidateAsT: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
    } yield {

      val expected = NewMyType(MyType.unsafeFrom(s)).rightNec[String]
      val actual   = s.validateAs[NewMyType]
      actual ==== expected
    }

  def testAValidateAsTInvalid: Result = {
    val expected =
      "Failed to create refined4s.cats.syntaxSpec.NewMyType: Invalid value: []. It has to be a non-empty String but got \"\""
        .leftNec[NewMyType]
    val actual   = "".validateAs[NewMyType]
    actual ==== expected
  }

  def testValidateAsTA: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
    } yield {

      val expected = NewMyType(MyType.unsafeFrom(s)).rightNec[String]
      val actual   = validateAs(s)[NewMyType]
      actual ==== expected
    }

  def testValidateAsTAInvalid: Result = {
    val expected =
      "Failed to create refined4s.cats.syntaxSpec.NewMyType: Invalid value: []. It has to be a non-empty String but got \"\""
        .leftNec[NewMyType]
    val actual   = validateAs("")[NewMyType]
    actual ==== expected
  }

  def testInlinedRefined_AValidateAsT: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(3, 10)).log("s")
    } yield {

      val expected = NewMoreThan2CharsString(MoreThan2CharsString.unsafeFrom(s)).rightNec[String]
      val actual   = s.validateAs[NewMoreThan2CharsString]
      (actual ==== expected).log(
        raw"""       s: ${s.encodeToUnicode}
             |  actual: ${actual.leftMap(_.map(_.encodeToUnicode))}
             |expected: ${expected.leftMap(_.map(_.encodeToUnicode))}
             |""".stripMargin
      )
    }

  def testInlinedRefined_AValidateAsTInvalid: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(0, 2)).log("s")
    } yield {
      val expected =
        s"Failed to create refined4s.cats.syntaxSpec.NewMoreThan2CharsString: Invalid value: [$s]. The String should have more than 2 chars but got $s instead"
          .leftNec[NewMoreThan2CharsString]

      val actual = s.validateAs[NewMoreThan2CharsString]
      (actual ==== expected).log(
        raw"""       s: ${s.encodeToUnicode}
             |  actual: ${actual.leftMap(_.map(_.encodeToUnicode))}
             |expected: ${expected.leftMap(_.map(_.encodeToUnicode))}
             |""".stripMargin
      )
    }

  def testInlinedRefined_ValidateAsTA: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(3, 10)).log("s")
    } yield {

      val expected = NewMoreThan2CharsString(MoreThan2CharsString.unsafeFrom(s)).rightNec[String]
      val actual   = validateAs(s)[NewMoreThan2CharsString]
      (actual ==== expected).log(
        raw"""       s: ${s.encodeToUnicode}
             |  actual: ${actual.leftMap(_.map(_.encodeToUnicode))}
             |expected: ${expected.leftMap(_.map(_.encodeToUnicode))}
             |""".stripMargin
      )
    }

  def testInlinedRefined_ValidateAsTAInvalid: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(0, 2)).log("s")
    } yield {
      val expected =
        s"Failed to create refined4s.cats.syntaxSpec.NewMoreThan2CharsString: Invalid value: [$s]. The String should have more than 2 chars but got $s instead"
          .leftNec[NewMoreThan2CharsString]
      val actual   = validateAs(s)[NewMoreThan2CharsString]
      (actual ==== expected).log(
        raw"""       s: ${s.encodeToUnicode}
             |  actual: ${actual.leftMap(_.map(_.encodeToUnicode))}
             |expected: ${expected.leftMap(_.map(_.encodeToUnicode))}
             |""".stripMargin
      )
    }

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
