package refined4s

import cats.syntax.all.*

import hedgehog.*
import hedgehog.runner.*

/** @author Kevin Lee
  * @since 2023-12-02
  */
object CoercibleSpec extends Properties {
  override def tests: List[Test] = List(
    property("test Coercible[A, B].apply", testApply),
    property("test Coercible[A, B].instance.apply", testApply),
    property("Coercible.unsafeWrapM[M[*], A, B](M[A]) should return M[B]", testUnsafeWrapM),
    property("Coercible.unsafeWrapMOfM[M1[*], M2[*], A, B](M1[M2[A]]) should return M1[M2[B]]", testUnsafeWrapMOfM),
  )

  def testApply: Property =
    for {
      s        <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
      testType <- Gen.constant(TestType(s)).log("testType")
    } yield {
      val expected = s
      val actual   = Coercible[TestType, String](testType)
      actual ==== expected
    }

  def testInstanceApply: Property =
    for {
      s        <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
      testType <- Gen.constant(TestType(s)).log("testType")
    } yield {
      val expected  = s
      val coercible = Coercible.instance[TestType, String]

      val actual = coercible(testType)
      actual ==== expected
    }

  def testUnsafeWrapM: Property =
    for {
      s        <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
      testType <- Gen.constant(TestType(s).some).log("testType")
    } yield {
      val expected  = s.some
      val coercible = Coercible.unsafeWrapM[Option, TestType, String]
      val actual    = coercible(testType)
      actual ==== expected
    }

  def testUnsafeWrapMOfM: Property =
    for {
      s        <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
      testType <- Gen.constant(List(TestType(s).some)).log("testType")
    } yield {
      val expected  = List(s.some)
      val coercible = Coercible.unsafeWrapMOfM[List, Option, TestType, String]
      val actual    = coercible(testType)
      actual ==== expected
    }

  trait TestNewtype[A] {
    type Type

    inline given unwrap: Coercible[Type, A] = Coercible.instance
  }
  type TestType = TestType.Type
  object TestType extends TestNewtype[String] {
    override opaque type Type = String

    def apply(s: String): Type = s

  }
}
