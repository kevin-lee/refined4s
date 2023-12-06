package refined4s

import cats.syntax.all.*
import hedgehog.*
import hedgehog.runner.*

/** @author Kevin Lee
  * @since 2023-12-05
  */
object RefinedCtorSpec extends Properties {
  override def tests: List[Test] = List(
    property(
      "RefinedCtor[Type, A].create with a valid input should return Either[String, T] = Right(T)",
      testRefinedCtorCreate,
    ),
    example(
      "RefinedCtor[Type, A].create with an invalid input should return Either[String, T] = Left(String)",
      testRefinedCtorCreateInvalid,
    ),
  )

  def testRefinedCtorCreate: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
    } yield {
      val expected = s.asRight[String]
      val actual   = RefinedCtor[MyType, String].create(s)
      actual ==== expected
    }

  def testRefinedCtorCreateInvalid: Result = {
    val expected = "It has to be non-empty String but got \"\"".asLeft[MyType]
    val actual   = RefinedCtor[MyType, String].create("")
    actual ==== expected
  }

  type MyType = MyType.Type
  @SuppressWarnings(Array("org.wartremover.warts.Equals"))
  object MyType {
    opaque type Type = String

    def invalidReason(a: String): String =
      "It has to be non-empty String but got \"" + a + "\""

    def predicate(a: String): Boolean = a != ""

    given refinedCtor: RefinedCtor[Type, String] with {
      override def create(a: String): Either[String, MyType] =
        Either.cond(predicate(a), (a: Type), invalidReason(a))
    }
  }

}
