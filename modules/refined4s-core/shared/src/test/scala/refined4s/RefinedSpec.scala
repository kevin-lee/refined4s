package refined4s

import cats.syntax.all.*

import hedgehog.*
import hedgehog.runner.*

/** @author Kevin Lee
  * @since 2023-04-24
  */
object RefinedSpec extends Properties {
  override def tests: List[Test] = List(
    example("test Refined.apply", testApply),
    property("test Refined.from(valid)", testFromValid),
    example("test Refined.from(invalid)", testFromInvalid),
    property("test Refined.unsafeFrom(valid)", testUnsafeFromValid),
    example("test Refined.unsafeFrom(invalid)", testUnsafeFromInvalid),
    property("test Refined.value", testValue),
    property("test Refined.unapply", testUnapplyWithPatternMatching),
    property("test unwrap Refined with Coercible", testCoercibleUnwrapping),
    property(
      "Given Refined[A], RefinedCtor[Refined[A]#Type, A].create with a valid input should return Either[String, Refined[A]#Type] = Right(Refined[A]#Type)",
      testRefinedCtorCreate,
    ),
    example(
      "Given Refined[A], RefinedCtor[Refined[A]#Type, A].create with an invalid input should return Either[String, Refined[A]#Type] = Left(String)",
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
    val expected = "Invalid value: []. It has to be non-empty String but got \"\""
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
    val expected = "Invalid value: []. It has to be non-empty String but got \"\""
    try {
      val _ = MyType.unsafeFrom("")
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

  def testCoercibleUnwrapping: Property =
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

  def testRefinedCtorCreate: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
    } yield {
      val expected = s.asRight[String]
      val actual   = RefinedCtor[MyType, String].create(s)
      actual ==== expected
    }

  def testRefinedCtorCreateInvalid: Result = {
    val expected = "Invalid value: []. It has to be non-empty String but got \"\"".asLeft[MyType]
    val actual   = RefinedCtor[MyType, String].create("")
    actual ==== expected
  }

  type MyType = MyType.Type
  @SuppressWarnings(Array("org.wartremover.warts.Equals"))
  object MyType extends Refined[String] {

    override inline def invalidReason(a: String): String =
      "It has to be non-empty String but got \"" + a + "\""

    override inline def predicate(a: String): Boolean = a != ""
  }

}
