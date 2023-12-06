package refined4s

import cats.*
import cats.syntax.all.*
import extras.core.syntax.all.*
import hedgehog.*
import hedgehog.runner.*
import refined4s.syntax.*

/** @author Kevin Lee
  * @since 2023-12-05
  */
object syntaxSpec extends Properties {

  override def tests: List[Test] = List(
    property(
      "For type T = Refined[A] and t: T, t.coerce[A] should return A",
      testTCoerceA,
    ),
    property(
      "For type T = Refined[A], a.refinedTo[T] with a valid `a` should return Either[String, T] = Right(T)",
      testARefinedT,
    ),
    example(
      "For type T = Refined[A], a.refinedTo[T] with an invalid `a` should return Either[String, T] = Left(String)",
      testARefinedTVInvalid,
    ),
    property(
      "For type T = Refined[A], refinedTo(a)[T] with a valid `a` should return Either[String, T] = Right(T)",
      testRefinedTA,
    ),
    example(
      "For type T = Refined[A], refinedTo(a)[T] with an invalid `a` should return Either[String, T] = Left(String)",
      testRefinedTAInvalid,
    ),
    property(
      "For type T = InlinedRefined[A] and t: T, t.coerce[A] should return A",
      testInlinedRefined_TCoerceA,
    ),
    property(
      "For type T = InlinedRefined[A], a.refinedTo[T] with a valid `a` should return Either[String, T] = Right(T)",
      testInlinedRefined_ARefinedT,
    ),
    property(
      "For type T = InlinedRefined[A], a.refinedTo[T] with an invalid `a` should return Either[String, T] = Left(String)",
      testInlinedRefined_ARefinedTVInvalid,
    ),
    property(
      "For type T = InlinedRefined[A], refinedTo(a)[T] with a valid `a` should return Either[String, T] = Right(T)",
      testInlinedRefined_RefinedTA,
    ),
    property(
      "For type T = InlinedRefined[A], refinedTo(a)[T] with an invalid `a` should return Either[String, T] = Left(String)",
      testInlinedRefined_RefinedTAInvalid,
    ),
    ///
    property(
      "For type T = Refined[A] and type N = NewType[T], a.refinedNewtype[N] with a valid `a` should return Either[String, N] = Right(N)",
      testARefinedNewtypeT,
    ),
    example(
      "For type T = Refined[A] and type N = NewType[T], a.refinedNewtype[N] with an invalid `a` should return Either[String, N] = Left(String)",
      testARefinedNewtypeTInvalid,
    ),
    property(
      "For type T = Refined[A] and type N = NewType[T], refinedNewtype(a)[N] with a valid `a` should return Either[String, N] = Right(N)",
      testRefinedNewtypeTA,
    ),
    example(
      "For type T = Refined[A] and type N = NewType[T], refinedNewtype(a)[N] with an invalid `a` should return Either[String, N] = Left(String)",
      testRefinedNewtypeTAInvalid,
    ),
    ///
    property(
      "For type T = InlinedRefined[A] and type N = NewType[T], a.refinedNewtype[N] with a valid `a` should return Either[String, N] = Right(N)",
      testInlinedRefined_ARefinedNewtypeT,
    ),
    property(
      "For type T = InlinedRefined[A] and type N = NewType[T], a.refinedNewtype[N] with an invalid `a` should return Either[String, N] = Left(String)",
      testInlinedRefined_ARefinedNewtypeTInvalid,
    ),
    property(
      "For type T = InlinedRefined[A] and type N = NewType[T], refinedNewtype(a)[N] with a valid `a` should return Either[String, N] = Right(N)",
      testInlinedRefined_RefinedNewtypeTA,
    ),
    property(
      "For type T = InlinedRefined[A] and type N = NewType[T], refinedNewtype(a)[N] with an invalid `a` should return Either[String, N] = Left(String)",
      testInlinedRefined_RefinedNewtypeTAInvalid,
    ),
    ///
    property(
      "For type T = Refined[A] and type N = NewType[T], (n: N).toValue should return A",
      testRefinedNewtypeToValueA,
    ),
    property(
      "For type T = InlinedRefined[A] and type N = NewType[T], (n: N).toValue should return A",
      testInlinedRefinedNewtypeToValueA,
    ),
  )

  def testTCoerceA: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
    } yield {
      val myType = MyType.unsafeFrom(s)

      val expected = s
      val actual   = myType.coerce[String]
      actual ==== expected
    }

  def testARefinedT: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
    } yield {

      val expected = s.asRight[String]
      val actual   = s.refinedTo[MyType]
      actual ==== expected
    }

  def testARefinedTVInvalid: Result = {
    val expected = "Invalid value: []. It has to be a non-empty String but got \"\"".asLeft[MyType]
    val actual   = "".refinedTo[MyType]
    actual ==== expected
  }

  def testRefinedTA: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
    } yield {

      val expected = s.asRight[String]
      val actual   = refinedTo(s)[MyType]
      actual ==== expected
    }

  def testRefinedTAInvalid: Result = {
    val expected = "Invalid value: []. It has to be a non-empty String but got \"\"".asLeft[MyType]
    val actual   = refinedTo("")[MyType]
    actual ==== expected
  }

  import InlinedRefinedType.*

  def testInlinedRefined_ARefinedT: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(3, 10)).log("s")
    } yield {

      val expected = s.asRight[String]
      val actual   = s.refinedTo[MoreThan2CharsString]
      actual ==== expected
    }

  def testInlinedRefined_TCoerceA: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(3, 10)).log("s")
    } yield {
      val moreThan2CharsString = MoreThan2CharsString.unsafeFrom(s)

      val expected = s
      val actual   = moreThan2CharsString.coerce[String]
      actual ==== expected
    }

  def testInlinedRefined_ARefinedTVInvalid: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(0, 2)).log("s")
    } yield {
      val expected =
        s"Invalid value: [$s]. The String should have more than 2 chars but got $s instead".asLeft[MoreThan2CharsString]
      val actual   = s.refinedTo[MoreThan2CharsString]
      (actual ==== expected).log(
        raw"""       s: ${s.encodeToUnicode}
             |  actual: ${actual.leftMap(_.encodeToUnicode)}
             |expected: ${expected.leftMap(_.encodeToUnicode)}
             |""".stripMargin
      )
    }

  def testInlinedRefined_RefinedTA: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(3, 10)).log("s")
    } yield {

      val expected = s.asRight[String]
      val actual   = refinedTo(s)[MoreThan2CharsString]
      actual ==== expected
    }

  def testInlinedRefined_RefinedTAInvalid: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(0, 2)).log("s")
    } yield {
      val expected =
        s"Invalid value: [$s]. The String should have more than 2 chars but got $s instead".asLeft[MoreThan2CharsString]
      val actual   = refinedTo(s)[MoreThan2CharsString]
      (actual ==== expected).log(
        raw"""       s: ${s.encodeToUnicode}
             |  actual: ${actual.leftMap(_.encodeToUnicode)}
             |expected: ${expected.leftMap(_.encodeToUnicode)}
             |""".stripMargin
      )
    }

  def testARefinedNewtypeT: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
    } yield {

      val expected = NewMyType(MyType.unsafeFrom(s)).asRight[String]
      val actual   = s.refinedNewtype[NewMyType]
      actual ==== expected
    }

  def testARefinedNewtypeTInvalid: Result = {
    val expected =
      "Failed to create refined4s.syntaxSpec.NewMyType: Invalid value: []. It has to be a non-empty String but got \"\""
        .asLeft[NewMyType]
    val actual   = "".refinedNewtype[NewMyType]
    actual ==== expected
  }

  def testRefinedNewtypeTA: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
    } yield {

      val expected = NewMyType(MyType.unsafeFrom(s)).asRight[String]
      val actual   = refinedNewtype(s)[NewMyType]
      actual ==== expected
    }

  def testRefinedNewtypeTAInvalid: Result = {
    val expected =
      "Failed to create refined4s.syntaxSpec.NewMyType: Invalid value: []. It has to be a non-empty String but got \"\""
        .asLeft[NewMyType]
    val actual   = refinedNewtype("")[NewMyType]
    actual ==== expected
  }

  def testInlinedRefined_ARefinedNewtypeT: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(3, 10)).log("s")
    } yield {

      val expected = NewMoreThan2CharsString(MoreThan2CharsString.unsafeFrom(s)).asRight[String]
      val actual   = s.refinedNewtype[NewMoreThan2CharsString]
      (actual ==== expected).log(
        raw"""       s: ${s.encodeToUnicode}
             |  actual: ${actual.leftMap(_.encodeToUnicode)}
             |expected: ${expected.leftMap(_.encodeToUnicode)}
             |""".stripMargin
      )
    }

  def testInlinedRefined_ARefinedNewtypeTInvalid: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(0, 2)).log("s")
    } yield {
      val expected =
        s"Failed to create refined4s.syntaxSpec.NewMoreThan2CharsString: Invalid value: [$s]. The String should have more than 2 chars but got $s instead"
          .asLeft[NewMoreThan2CharsString]

      val actual = s.refinedNewtype[NewMoreThan2CharsString]
      (actual ==== expected).log(
        raw"""       s: ${s.encodeToUnicode}
             |  actual: ${actual.leftMap(_.encodeToUnicode)}
             |expected: ${expected.leftMap(_.encodeToUnicode)}
             |""".stripMargin
      )
    }

  def testInlinedRefined_RefinedNewtypeTA: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(3, 10)).log("s")
    } yield {

      val expected = NewMoreThan2CharsString(MoreThan2CharsString.unsafeFrom(s)).asRight[String]
      val actual   = refinedNewtype(s)[NewMoreThan2CharsString]
      (actual ==== expected).log(
        raw"""       s: ${s.encodeToUnicode}
             |  actual: ${actual.leftMap(_.encodeToUnicode)}
             |expected: ${expected.leftMap(_.encodeToUnicode)}
             |""".stripMargin
      )
    }

  def testRefinedNewtypeToValueA: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
    } yield {
      val newMyType = NewMyType(MyType.unsafeFrom(s))

      val expected       = s
      val actual: String = newMyType.toValue
      actual ==== expected
    }

  def testInlinedRefinedNewtypeToValueA: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(3, 10)).log("s")
    } yield {

      val newMoreThan2CharsString = NewMoreThan2CharsString(MoreThan2CharsString.unsafeFrom(s))

      val expected       = s
      val actual: String = newMoreThan2CharsString.toValue
      actual ==== expected
    }

  def testInlinedRefined_RefinedNewtypeTAInvalid: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(0, 2)).log("s")
    } yield {
      val expected =
        s"Failed to create refined4s.syntaxSpec.NewMoreThan2CharsString: Invalid value: [$s]. The String should have more than 2 chars but got $s instead"
          .asLeft[NewMoreThan2CharsString]
      val actual   = refinedNewtype(s)[NewMoreThan2CharsString]
      (actual ==== expected).log(
        raw"""       s: ${s.encodeToUnicode}
             |  actual: ${actual.leftMap(_.encodeToUnicode)}
             |expected: ${expected.leftMap(_.encodeToUnicode)}
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
