package refined4s

import cats.*
import cats.syntax.all.*

import hedgehog.*
import hedgehog.runner.*

/** @author Kevin Lee
  * @since 2023-12-03
  */
object NewtypeSpec extends Properties {
  override def tests: List[Test] = List(
    example("""Newtype("blah") should be equal to Newtype("blah")""", testApply),
    property("test Newtype.value", testValue),
    property("test Newtype.unapply", testUnapplyWithPatternMatching),
    property("test Newtype.wrap", testWrap),
    property("test Newtype.wrapTC", testWrapTC),
    property("test Newtype.unwrap", testUnwrap),
    property("test Newtype.unwrapTC", testUnwrapTC),
  )

  def testApply: Result = {
    /* The actual test is whether this compiles or not actual ==== expected is meaningless here */
    val expected = MyType("blah")
    val actual   = MyType("blah")
    actual ==== expected
  }

  def testValue: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
    } yield {
      val expected = s
      val actual   = MyType(s)
      actual.value ==== expected
    }

  def testUnapplyWithPatternMatching: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
    } yield {
      val expected = s
      val myType   = MyType(s)
      myType match {
        case MyType(actual) =>
          actual ==== expected
      }
    }

  def testWrap: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
    } yield {
      val myType   = MyType(s)
      val expected = myType

      def wrapIt[A, B](a: A)(using coercible: Coercible[A, B]): B =
        coercible(a)

      val actual = wrapIt[String, MyType](s)

      actual ==== expected
    }

  def testWrapTC: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
    } yield {
      val myType   = MyType(s)
      val expected = myType.some

      def wrapIt[F[*], A, B](fa: F[A])(using coercible: Coercible[F[A], F[B]]): F[B] =
        coercible(fa)

      val actual = wrapIt[Option, String, MyType](s.some)

      actual ==== expected
    }

  def testUnwrap: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
    } yield {
      val expected = s
      val myType   = MyType(s)

      def unwrapIt[A, B](a: A)(using coercible: Coercible[A, B]): B =
        coercible(a)

      val actual = unwrapIt(myType)

      actual ==== expected
    }

  def testUnwrapTC: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
    } yield {
      val expected = s.some
      val myType   = MyType(s)

      def unwrapIt[F[*], A, B](fa: F[A])(using coercible: Coercible[F[A], F[B]]): F[B] =
        coercible(fa)

      val actual = unwrapIt(myType.some)

      actual ==== expected
    }

  type MyType = MyType.Type
  object MyType extends Newtype[String] {
    given eqMyType: Eq[MyType]     = deriving[Eq]
    given showMyType: Show[MyType] = deriving[Show]
  }
}
