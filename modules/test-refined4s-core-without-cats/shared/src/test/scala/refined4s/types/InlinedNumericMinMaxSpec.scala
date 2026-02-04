package refined4s.types

import hedgehog.*
import hedgehog.runner.*
import refined4s.*
import refined4s.types.numeric.*

/** @author Kevin Lee
  * @since 2026-02-02
  */
object InlinedNumericMinMaxSpec extends Properties {

  import InlinedNumericMinMaxTestTypes.*

  override def tests: List[Test] =
    testInlinedNumericMinInt ++
      testInlinedNumericMaxInt ++
      testInlinedNumericMinMaxInt ++
      ///
      // NOTE: apply in InlinedNumericMinMax can't handle non-primitive numeric types yet
      // So InlinedNumericMinMax[BigInt].apply() does not work.
      testInlinedNumericMinBigInt ++
      testInlinedNumericMaxBigInt ++
      testInlinedNumericMinMaxBigInt ++
      ///
      testInlinedNumericMinLong ++
      testInlinedNumericMaxLong ++
      testInlinedNumericMinMaxLong

  ///

  def testInlinedNumericMinInt: List[Test] = checkMin(
    "TestInlinedNumericMinInt",
    TestInlinedNumericMinInt,
    Gen.int(Range.linear(10, 1000)).map(TestInlinedNumericMinInt.unsafeFrom),
    _.value,
    10,
  ) ++ List(
    {
      example(
        "Test TestInlinedNumericMinInt with min - 1 value", {

          import scala.compiletime.testing.{typeChecks, typeCheckErrors}

          val shouldNotCompile = !typeChecks(
            """
            TestInlinedNumericMinInt(10 - 1)
          """
          )

          val shouldNotCompileError = typeCheckErrors(
            """
            TestInlinedNumericMinInt(10 - 1)
          """
          ).map(_.message).mkString

          val expectedErrorMessage = """Invalid value: [9]. It must be >= 10"""

          Result.all(
            List(
              Result.assert(shouldNotCompile).log("""TestInlinedNumericMinInt(10 - 1) should have failed compilation but it succeeded."""),
              (shouldNotCompileError ==== expectedErrorMessage).log("""The error message doesn't match with the expected error message."""),
            )
          )
        },
      )
    }
  )

  def testInlinedNumericMaxInt: List[Test] = checkMax(
    "TestInlinedNumericMaxInt",
    TestInlinedNumericMaxInt,
    Gen.int(Range.linear(-1000, 100)).map(TestInlinedNumericMaxInt.unsafeFrom),
    _.value,
    100,
  ) ++ List(
    {
      example(
        "Test TestInlinedNumericMaxInt with max + 1 value", {

          import scala.compiletime.testing.{typeChecks, typeCheckErrors}

          val shouldNotCompile = !typeChecks(
            """
            TestInlinedNumericMaxInt(100 + 1)
          """
          )

          val shouldNotCompileError = typeCheckErrors(
            """
            TestInlinedNumericMaxInt(100 + 1)
          """
          ).map(_.message).mkString

          val expectedErrorMessage = """Invalid value: [101]. It must be <= 100"""

          Result.all(
            List(
              Result.assert(shouldNotCompile).log("""TestInlinedNumericMaxInt(100 + 1) should have failed compilation but it succeeded."""),
              (shouldNotCompileError ==== expectedErrorMessage).log("""The error message doesn't match with the expected error message."""),
            )
          )
        },
      )
    }
  )

  def testInlinedNumericMinMaxInt: List[Test] = checkMinMax(
    "TestInlinedNumericMinMaxInt",
    TestInlinedNumericMinMaxInt,
    Gen.int(Range.linear(10, 100)).map(TestInlinedNumericMinMaxInt.unsafeFrom),
    _.value,
    10,
    100,
  ) ++ List(
    {
      example(
        "Test TestInlinedNumericMinMaxInt with min - 1 value", {

          import scala.compiletime.testing.{typeChecks, typeCheckErrors}

          val shouldNotCompile = !typeChecks(
            """
            TestInlinedNumericMinMaxInt(10 - 1)
          """
          )

          val shouldNotCompileError = typeCheckErrors(
            """
            TestInlinedNumericMinMaxInt(10 - 1)
          """
          ).map(_.message).mkString

          val expectedErrorMessage = """Invalid value: [9]. It must be >= 10 && <= 100"""

          Result.all(
            List(
              Result
                .assert(shouldNotCompile)
                .log("""TestInlinedNumericMinMaxInt(10 - 1) should have failed compilation but it succeeded."""),
              (shouldNotCompileError ==== expectedErrorMessage).log("""The error message doesn't match with the expected error message."""),
            )
          )
        },
      )
    }, {
      example(
        "Test TestInlinedNumericMinMaxInt with max + 1 value", {

          import scala.compiletime.testing.{typeChecks, typeCheckErrors}

          val shouldNotCompile = !typeChecks(
            """
            TestInlinedNumericMinMaxInt(100 + 1)
          """
          )

          val shouldNotCompileError = typeCheckErrors(
            """
            TestInlinedNumericMinMaxInt(100 + 1)
          """
          ).map(_.message).mkString

          val expectedErrorMessage = """Invalid value: [101]. It must be >= 10 && <= 100"""

          Result.all(
            List(
              Result
                .assert(shouldNotCompile)
                .log("""TestInlinedNumericMinMaxInt(100 + 1) should have failed compilation but it succeeded."""),
              (shouldNotCompileError ==== expectedErrorMessage).log("""The error message doesn't match with the expected error message."""),
            )
          )
        },
      )
    },
  )

  ///

  def testInlinedNumericMinBigInt: List[Test] = checkMin(
    "TestInlinedNumericMinBigInt",
    TestInlinedNumericMinBigInt,
    Gen.int(Range.linear(10, 1000)).map(BigInt(_)).map(TestInlinedNumericMinBigInt.unsafeFrom),
    _.value,
    10,
  )

  def testInlinedNumericMaxBigInt: List[Test] = checkMax(
    "TestInlinedNumericMaxBigInt",
    TestInlinedNumericMaxBigInt,
    Gen
      .long(Range.linear(-1000L, 100L))
      .map { n =>
        if n > 0 then BigInt(n) + BigInt(n)
        else BigInt(n)
      }
      .map(TestInlinedNumericMaxBigInt.unsafeFrom),
    _.value,
    BigInt(Long.MaxValue) + BigInt(Long.MaxValue),
  )

  def testInlinedNumericMinMaxBigInt: List[Test] = checkMinMax(
    "TestInlinedNumericMinMaxBigInt",
    TestInlinedNumericMinMaxBigInt,
    Gen.int(Range.linear(10, 100)).map(BigInt(_)).map(TestInlinedNumericMinMaxBigInt.unsafeFrom),
    _.value,
    10,
    100,
  )

  ///

  def testInlinedNumericMinLong: List[Test] = checkMin(
    "TestInlinedNumericMinLong",
    TestInlinedNumericMinLong,
    Gen.long(Range.linear(10L, 1000L)).map(TestInlinedNumericMinLong.unsafeFrom),
    _.value,
    10L,
  )

  def testInlinedNumericMaxLong: List[Test] = checkMax(
    "TestInlinedNumericMaxLong",
    TestInlinedNumericMaxLong,
    Gen.long(Range.linear(-1000L, 100L)).map(TestInlinedNumericMaxLong.unsafeFrom),
    _.value,
    100L,
  )

  def testInlinedNumericMinMaxLong: List[Test] = checkMinMax(
    "TestInlinedNumericMinMaxLong",
    TestInlinedNumericMinMaxLong,
    Gen.long(Range.linear(10L, 100L)).map(TestInlinedNumericMinMaxLong.unsafeFrom),
    _.value,
    10L,
    100L,
  )

  /* Test helpers */

  private def checkMin[A: Ordering, T](
    name: String,
    min: Min[A] { type Type = T },
    gen: Gen[T],
    toValue: T => A,
    expectedMin: A,
  ): List[Test] = {
    List(
      example(s"test $name.min", Result.diff(toValue(min.min), expectedMin)(summon[Ordering[A]].equiv)),
      example(s"test $name.MinValue", Result.diff(toValue(min.MinValue), expectedMin)(summon[Ordering[A]].equiv)),
      property(
        s"test $name min property",
        gen.log("n").map { n =>
          Result
            .diff(toValue(n), toValue(min.min))(summon[Ordering[A]].gteq)
            .log(s"value: ${toValue(n)}, min: ${toValue(min.min)}")
        },
      ),
    )
  }

  private def checkMax[A: Ordering, T](
    name: String,
    max: Max[A] { type Type = T },
    gen: Gen[T],
    toValue: T => A,
    expectedMax: A,
  ): List[Test] = {
    List(
      example(s"test $name.max", Result.diff(toValue(max.max), expectedMax)(summon[Ordering[A]].equiv)),
      example(s"test $name.MaxValue", Result.diff(toValue(max.MaxValue), expectedMax)(summon[Ordering[A]].equiv)),
      property(
        s"test $name max property",
        gen.log("n").map { n =>
          Result
            .diff(toValue(n), toValue(max.max))(summon[Ordering[A]].lteq)
            .log(s"value: ${toValue(n)}, max: ${toValue(max.max)}")
        },
      ),
    )
  }

  private def checkMinMax[A: Ordering, T](
    name: String,
    minMax: MinMax[A] { type Type = T },
    gen: Gen[T],
    toValue: T => A,
    expectedMin: A,
    expectedMax: A,
  ): List[Test] =
    checkMin(name, minMax, gen, toValue, expectedMin) ++
      checkMax(name, minMax, gen, toValue, expectedMax)

}
object InlinedNumericMinMaxTestTypes {

  // Custom types for testing

  type TestInlinedNumericMinInt = TestInlinedNumericMinInt.Type
  object TestInlinedNumericMinInt extends InlinedNumericMin[Int] {

    import scala.compiletime.*
    import scala.compiletime.ops.any.ToString
    import scala.compiletime.ops.string.*

    private type MinV          = 10
    private type ExpectedValue = ">= " + ToString[MinV]

    override inline def minValue: Int = constValue[MinV]

    override inline def inlinedExpectedValue: String = constValue[ExpectedValue]

    override inline def invalidReason(a: Int): String = "It must be >= " + minValue.toString

  }

  type TestInlinedNumericMaxInt = TestInlinedNumericMaxInt.Type
  object TestInlinedNumericMaxInt extends InlinedNumericMax[Int] {
    import scala.compiletime.*
    import scala.compiletime.ops.any.ToString
    import scala.compiletime.ops.string.*

    private type MaxV          = 100
    private type ExpectedValue = "<= " + ToString[MaxV]

    override inline def maxValue: Int = constValue[MaxV]

    @SuppressWarnings(Array("org.wartremover.warts.StringPlusAny"))
    override inline def inlinedExpectedValue: String = constValue[ExpectedValue]

    override inline def invalidReason(a: Int): String = "It must be <= " + maxValue.toString

  }

  type TestInlinedNumericMinMaxInt = TestInlinedNumericMinMaxInt.Type
  object TestInlinedNumericMinMaxInt extends InlinedNumericMinMax[Int] {

    import scala.compiletime.*
    import scala.compiletime.ops.any.ToString
    import scala.compiletime.ops.string.*

    private type MinV = 10
    private type MaxV = 100

    override inline def minValue: Int = constValue[MinV]
    override inline def maxValue: Int = constValue[MaxV]

    private type ExpectedValue = ">= " + ToString[MinV] + " && <= " + ToString[MaxV]

    override inline def inlinedExpectedValue: String = constValue[ExpectedValue]

    override inline def invalidReason(a: Int): String = "It must be >= " + minValue.toString + " && <= " + maxValue.toString

  }

  ///

  type TestInlinedNumericMinBigInt = TestInlinedNumericMinBigInt.Type
  object TestInlinedNumericMinBigInt extends InlinedNumericMin[BigInt] {

    override inline def minValue = BigInt(10)

    override inline def inlinedExpectedValue: String = ">= 10"

    override inline def invalidReason(a: BigInt): String = "It must be >= 10"

  }

  type TestInlinedNumericMaxBigInt = TestInlinedNumericMaxBigInt.Type
  object TestInlinedNumericMaxBigInt extends InlinedNumericMax[BigInt] {

    override inline def maxValue: BigInt = BigInt(Long.MaxValue) + BigInt(Long.MaxValue)

    override inline def inlinedExpectedValue: String = "<= 100"

    override inline def invalidReason(a: BigInt): String = "It must be <= " + maxValue.toString

  }

  type TestInlinedNumericMinMaxBigInt = TestInlinedNumericMinMaxBigInt.Type
  object TestInlinedNumericMinMaxBigInt extends InlinedNumericMinMax[BigInt] {

    override inline def minValue: BigInt = BigInt(10)
    override inline def maxValue: BigInt = BigInt(100)

    override inline def inlinedExpectedValue: String = ">= 10 && <= 100"

    override inline def invalidReason(a: BigInt): String = "It must be >= 10 && <= 100"

  }

  ///

  type TestInlinedNumericMinLong = TestInlinedNumericMinLong.Type
  object TestInlinedNumericMinLong extends InlinedNumericMin[Long] {

    override inline def minValue = 10L

    override inline def inlinedExpectedValue: String = ">= 10"

    override inline def invalidReason(a: Long): String = "It must be >= 10"
  }

  type TestInlinedNumericMaxLong = TestInlinedNumericMaxLong.Type
  object TestInlinedNumericMaxLong extends InlinedNumericMax[Long] {

    override inline def maxValue = 100L

    override inline def inlinedExpectedValue: String = "<= 100"

    override inline def invalidReason(a: Long): String = "It must be <= 100"

  }

  type TestInlinedNumericMinMaxLong = TestInlinedNumericMinMaxLong.Type
  object TestInlinedNumericMinMaxLong extends InlinedNumericMinMax[Long] {

    override inline def minValue = 10L
    override inline def maxValue = 100L

    override inline def inlinedExpectedValue: String = ">= 10 && <= 100"

    override inline def invalidReason(a: Long): String = "It must be >= 10 && <= 100"

  }

}
