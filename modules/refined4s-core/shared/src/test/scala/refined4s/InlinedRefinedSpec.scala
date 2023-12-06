package refined4s

import cats.syntax.all.*
import hedgehog.*
import hedgehog.runner.*
import refined4s.InlinedRefinedType.Something

/** @author Kevin Lee
  * @since 2023-08-14
  */
object InlinedInlinedRefinedSpec extends Properties {
  override def tests: List[Test] = List(
    example("test InlinedRefined.apply", testApply),
    property("test InlinedRefined.from(valid)", testFromValid),
    example("test InlinedRefined.from(invalid)", testFromInvalid),
    property("test InlinedRefined.unsafeFrom(valid)", testUnsafeFromValid),
    example("test InlinedRefined.unsafeFrom(invalid)", testUnsafeFromInvalid),
    property("test InlinedRefined.value", testValue),
    property("test InlinedRefined.unapply", testUnapplyWithPatternMatching),
    property("test unwrap InlinedRefined[String] with Coercible", testCoercibleUnwrappingString),
    property("test unwrap InlinedRefined[Int] with Coercible", testCoercibleUnwrappingInt),
    property(
      "Given InlinedRefined[A], RefinedCtor[InlinedRefined[A]#Type, A].create with a valid input should return Either[String, InlinedRefined[A]#Type] = Right(InlinedRefined[A]#Type)",
      testRefinedCtorCreate,
    ),
    example(
      "Given InlinedRefined[A], RefinedCtor[InlinedRefined[A]#Type, A].create with an invalid input should return Either[String, InlinedRefined[A]#Type] = Left(String)",
      testRefinedCtorCreateInvalid,
    ),
  )

  def testApply: Result = {
    /* The actual test is whether this compiles or not actual ==== expected is meaningless here */
    val expected = MyType("blah")
    val actual   = MyType("blah")
    actual ==== expected
  }

  def testFromValid: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
    } yield {
      val expected = MyType.unsafeFrom(s)
      val actual   = MyType.from(s)
      actual ==== Right(expected)
    }

  def testFromInvalid: Result = {
    val expected = "Invalid value: []. It has to be a non-empty String but got \"\""
    val actual   = MyType.from("")
    actual ==== Left(expected)
  }

  def testUnsafeFromValid: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
    } yield {
      val expected = MyType.unsafeFrom(s)
      val actual   = MyType.unsafeFrom(s)
      actual ==== expected
    }

  def testUnsafeFromInvalid: Result = {
    val expected = "Invalid value: []. It has to be a non-empty String but got \"\""
    try {
      MyType.unsafeFrom("")
      Result.failure.log("""IllegalArgumentException was expected from MyType.unsafeFrom(""), but it was not thrown.""")
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
      val actual   = MyType.unsafeFrom(s)
      actual.value ==== expected
    }

  def testUnapplyWithPatternMatching: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
    } yield {
      val expected = s
      val myType   = MyType.unsafeFrom(s)
      myType match {
        case MyType(actual) =>
          actual ==== expected
      }
    }

  def testCoercibleUnwrappingString: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
    } yield {

      def unwrap[A, B](a: A)(using coercible: Coercible[A, B]): B =
        coercible(a)

      val expected = s
      val myType   = MyType.unsafeFrom(s)

      val actual: String = unwrap(myType)
      actual ==== expected
    }

  def testCoercibleUnwrappingInt: Property =
    for {
      n <- Gen.int(Range.linear(1, Int.MaxValue)).log("n")
    } yield {

      def unwrap[A, B](a: A)(using coercible: Coercible[A, B]): B =
        coercible(a)

      val expected = n
      val myType   = Something.unsafeFrom(n)

      val actual: Int = unwrap(myType)
      actual ==== expected
    }

  def testRefinedCtorCreate: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
    } yield {
      val expected = s.asRight[String]
      val actual   = RefinedCtor[MyType, String].create(s)
      actual ==== expected
    }

  def testRefinedCtorCreateInvalid: Result = {
    val expected = "Invalid value: []. It has to be a non-empty String but got \"\"".asLeft[MyType]
    val actual   = RefinedCtor[MyType, String].create("")
    actual ==== expected
  }

  type MyType = MyType.Type
  @SuppressWarnings(Array("org.wartremover.warts.Equals"))
  object MyType extends InlinedRefined[String] {

    override inline def invalidReason(a: String): String =
      "It has to be a non-empty String but got \"" + a + "\""

    override inline def predicate(a: String): Boolean = a != ""

    override inline def inlinedPredicate(inline a: String): Boolean = a != ""
  }

}
