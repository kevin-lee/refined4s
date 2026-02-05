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
      testInlinedNumericMinLong ++
      testInlinedNumericMaxLong ++
      testInlinedNumericMinMaxLong

  ///

  def testInlinedNumericMinInt: List[Test] = {
    val name = "TestInlinedNumericMinInt"
    checkMin(
      name,
      TestInlinedNumericMinInt,
      Gen.int(Range.linear(10, 1000)).map(TestInlinedNumericMinInt.unsafeFrom),
      _.value,
      10,
    ) ++ List(
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

          val expectedErrorMessage = """Invalid value: [9]. It must be >= 10."""

          Result.all(
            List(
              Result.assert(shouldNotCompile).log("""TestInlinedNumericMinInt(10 - 1) should have failed compilation but it succeeded."""),
              (shouldNotCompileError ==== expectedErrorMessage).log("""The error message doesn't match with the expected error message."""),
            )
          )
        },
      ),
      example(
        s"test $name.from with invalid value", {
          val expected = s"Invalid value: [${10 - 1}]. It must be >= 10."
          val actual   = TestInlinedNumericMinInt.from(10 - 1)
          actual ==== Left(expected)
        },
      ),
      example(
        s"test $name.unsafeFrom with invalid value", {
          val expected =
            s"Invalid value: [${10 - 1}]. It must be >= 10."
          val actual   = scala.util.Try(TestInlinedNumericMinInt.unsafeFrom(10 - 1)).toEither
          Result.all(
            List(
              actual.matchPattern {
                case Left(err: IllegalArgumentException) =>
              },
              actual.left.map(_.getMessage) ==== Left(expected),
            )
          )
        },
      ),
    )
  }

  def testInlinedNumericMaxInt: List[Test] = {
    val name = "TestInlinedNumericMaxInt"
    checkMax(
      name,
      TestInlinedNumericMaxInt,
      Gen.int(Range.linear(-1000, 100)).map(TestInlinedNumericMaxInt.unsafeFrom),
      _.value,
      100,
    ) ++ List(
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

          val expectedErrorMessage = """Invalid value: [101]. It must be <= 100."""

          Result.all(
            List(
              Result.assert(shouldNotCompile).log("""TestInlinedNumericMaxInt(100 + 1) should have failed compilation but it succeeded."""),
              (shouldNotCompileError ==== expectedErrorMessage).log("""The error message doesn't match with the expected error message."""),
            )
          )
        },
      ),
      example(
        s"test $name.from with invalid value", {
          val expected = s"Invalid value: [${100 + 1}]. It must be <= 100."
          val actual   = TestInlinedNumericMaxInt.from(100 + 1)
          actual ==== Left(expected)
        },
      ),
      example(
        s"test $name.unsafeFrom with invalid value", {
          val expected = s"Invalid value: [${100 + 1}]. It must be <= 100."
          val actual   = scala.util.Try(TestInlinedNumericMaxInt.unsafeFrom(100 + 1)).toEither
          Result.all(
            List(
              actual.matchPattern {
                case Left(err: IllegalArgumentException) =>
              },
              actual.left.map(_.getMessage) ==== Left(expected),
            )
          )
        },
      ),
    )
  }

  def testInlinedNumericMinMaxInt: List[Test] = {
    val name = "TestInlinedNumericMinMaxInt"
    checkMinMax(
      name,
      TestInlinedNumericMinMaxInt,
      Gen.int(Range.linear(10, 100)).map(TestInlinedNumericMinMaxInt.unsafeFrom),
      _.value,
      10,
      100,
    ) ++ List(
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

          val expectedErrorMessage = """Invalid value: [9]. It must be >= 10 && <= 100."""

          Result.all(
            List(
              Result
                .assert(shouldNotCompile)
                .log("""TestInlinedNumericMinMaxInt(10 - 1) should have failed compilation but it succeeded."""),
              (shouldNotCompileError ==== expectedErrorMessage).log("""The error message doesn't match with the expected error message."""),
            )
          )
        },
      ),
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

          val expectedErrorMessage = """Invalid value: [101]. It must be >= 10 && <= 100."""

          Result.all(
            List(
              Result
                .assert(shouldNotCompile)
                .log("""TestInlinedNumericMinMaxInt(100 + 1) should have failed compilation but it succeeded."""),
              (shouldNotCompileError ==== expectedErrorMessage).log("""The error message doesn't match with the expected error message."""),
            )
          )
        },
      ),
      example(
        s"test $name.from with invalid value", {
          val expected = s"Invalid value: [${10 - 1}]. It must be >= 10 && <= 100."
          val actual   = TestInlinedNumericMinMaxInt.from(10 - 1)
          actual ==== Left(expected)
        },
      ),
      example(
        s"test $name.unsafeFrom with invalid value", {
          val expected =
            s"Invalid value: [${10 - 1}]. It must be >= 10 && <= 100."
          val actual   = scala.util.Try(TestInlinedNumericMinMaxInt.unsafeFrom(10 - 1)).toEither
          Result.all(
            List(
              actual.matchPattern {
                case Left(err: IllegalArgumentException) =>
              },
              actual.left.map(_.getMessage) ==== Left(expected),
            )
          )
        },
      ),
      example(
        s"test $name.from with invalid value", {
          val expected = s"Invalid value: [${100 + 1}]. It must be >= 10 && <= 100."
          val actual   = TestInlinedNumericMinMaxInt.from(100 + 1)
          actual ==== Left(expected)
        },
      ),
      example(
        s"test $name.unsafeFrom with invalid value", {
          val expected = s"Invalid value: [${100 + 1}]. It must be >= 10 && <= 100."
          val actual   = scala.util.Try(TestInlinedNumericMinMaxInt.unsafeFrom(100 + 1)).toEither
          Result.all(
            List(
              actual.matchPattern {
                case Left(err: IllegalArgumentException) =>
              },
              actual.left.map(_.getMessage) ==== Left(expected),
            )
          )
        },
      ),
    )
  }

  ///

  def testInlinedNumericMinLong: List[Test] = {
    val name = "TestInlinedNumericMinLong"

    checkMin(
      name,
      TestInlinedNumericMinLong,
      Gen.long(Range.linear(Long.MinValue + 1L, Long.MaxValue)).map(TestInlinedNumericMinLong.unsafeFrom),
      _.value,
      Long.MinValue + 1L,
    ) ++ List(
      example(
        "Test TestInlinedNumericMinLong with min - 1 value", {

          import scala.compiletime.testing.{typeChecks, typeCheckErrors}

          val shouldNotCompile = !typeChecks(
            """
            TestInlinedNumericMinLong(0x8000_0000_0000_0000L)
          """
          )

          val shouldNotCompileError = typeCheckErrors(
            """
            TestInlinedNumericMinLong(0x8000_0000_0000_0000L)
          """
          ).map(_.message).mkString

          val expectedErrorMessage =
            """Invalid value: [-9223372036854775808L]. It must be >= -9223372036854775807L."""

          Result.all(
            List(
              Result
                .assert(shouldNotCompile)
                .log("""TestInlinedNumericMinLong(0x8000_0000_0000_0000L) should have failed compilation but it succeeded."""),
              (shouldNotCompileError ==== expectedErrorMessage).log("""The error message doesn't match with the expected error message."""),
            )
          )
        },
      ),
      example(
        s"test $name.from with invalid value", {
          val expected = s"Invalid value: [${Long.MinValue.toString}]. It must be >= ${(Long.MinValue + 1L).toString}."
          val actual   = TestInlinedNumericMinLong.from(Long.MinValue)
          actual ==== Left(expected)
        },
      ),
      example(
        s"test $name.unsafeFrom with invalid value", {
          val expected =
            s"Invalid value: [${Long.MinValue.toString}]. It must be >= ${(Long.MinValue + 1L).toString}."
          val actual   = scala.util.Try(TestInlinedNumericMinLong.unsafeFrom(Long.MinValue)).toEither
          Result.all(
            List(
              actual.matchPattern {
                case Left(err: IllegalArgumentException) =>
              },
              actual.left.map(_.getMessage) ==== Left(expected),
            )
          )
        },
      ),
    )
  }

  def testInlinedNumericMaxLong: List[Test] = {
    val name = "TestInlinedNumericMaxLong"
    checkMax(
      name,
      TestInlinedNumericMaxLong,
      Gen.long(Range.linear(Long.MinValue, Long.MaxValue - 1)).map(TestInlinedNumericMaxLong.unsafeFrom),
      _.value,
      Long.MaxValue - 1,
    ) ++ List(
      {
        example(
          "Test TestInlinedNumericMaxLong with max + 1 value", {

            import scala.compiletime.testing.{typeChecks, typeCheckErrors}

            val shouldNotCompile = !typeChecks(
              """
            TestInlinedNumericMaxLong(0x7fff_ffff_ffff_ffffL)
          """
            )

            val shouldNotCompileError = typeCheckErrors(
              """
            TestInlinedNumericMaxLong(0x7fff_ffff_ffff_ffffL)
          """
            ).map(_.message).mkString

            val expectedErrorMessage =
              """Invalid value: [9223372036854775807L]. It must be <= 9223372036854775806L."""

            Result.all(
              List(
                Result
                  .assert(shouldNotCompile)
                  .log("""TestInlinedNumericMaxLong(0x7fff_ffff_ffff_ffffL) should have failed compilation but it succeeded."""),
                (shouldNotCompileError ==== expectedErrorMessage).log(
                  """The error message doesn't match with the expected error message."""
                ),
              )
            )
          },
        )
      },
      example(
        s"test $name.from with invalid value", {
          val expected = s"Invalid value: [${Long.MaxValue.toString}]. It must be <= ${(Long.MaxValue - 1L).toString}."
          val actual   = TestInlinedNumericMaxLong.from(Long.MaxValue)
          actual ==== Left(expected)
        },
      ),
      example(
        s"test $name.unsafeFrom with invalid value", {
          val expected = s"Invalid value: [${Long.MaxValue.toString}]. It must be <= ${(Long.MaxValue - 1L).toString}."
          val actual   = scala.util.Try(TestInlinedNumericMaxLong.unsafeFrom(Long.MaxValue)).toEither
          Result.all(
            List(
              actual.matchPattern {
                case Left(err: IllegalArgumentException) =>
              },
              actual.left.map(_.getMessage) ==== Left(expected),
            )
          )
        },
      ),
    )
  }

  def testInlinedNumericMinMaxLong: List[Test] = {
    val name = "TestInlinedNumericMinMaxLong"
    checkMinMax(
      name,
      TestInlinedNumericMinMaxLong,
      Gen.long(Range.linear(Long.MinValue + 1, Long.MaxValue - 1)).map(TestInlinedNumericMinMaxLong.unsafeFrom),
      _.value,
      Long.MinValue + 1,
      Long.MaxValue - 1,
    ) ++ List(
      {
        example(
          "Test TestInlinedNumericMinMaxLong with min - 1 value", {

            import scala.compiletime.testing.{typeChecks, typeCheckErrors}

            val shouldNotCompile = !typeChecks(
              """
            TestInlinedNumericMinMaxLong(0x8000_0000_0000_0000L)
          """
            )

            val shouldNotCompileError = typeCheckErrors(
              """
            TestInlinedNumericMinMaxLong(0x8000_0000_0000_0000L)
          """
            ).map(_.message).mkString

            val expectedErrorMessage =
              """Invalid value: [-9223372036854775808L]. It must be >= -9223372036854775807L && <= 9223372036854775806L."""

            Result.all(
              List(
                Result
                  .assert(shouldNotCompile)
                  .log("""TestInlinedNumericMinMaxLong(10L - 1L) should have failed compilation but it succeeded."""),
                (shouldNotCompileError ==== expectedErrorMessage).log(
                  """The error message doesn't match with the expected error message."""
                ),
              )
            )
          },
        )
      }, {
        example(
          "Test TestInlinedNumericMinMaxLong with max + 1 value", {

            import scala.compiletime.testing.{typeChecks, typeCheckErrors}

            val shouldNotCompile = !typeChecks(
              """
            TestInlinedNumericMinMaxLong(0x7fff_ffff_ffff_ffffL)
          """
            )

            val shouldNotCompileError = typeCheckErrors(
              """
            TestInlinedNumericMinMaxLong(0x7fff_ffff_ffff_ffffL)
          """
            ).map(_.message).mkString

            val expectedErrorMessage =
              """Invalid value: [9223372036854775807L]. It must be >= -9223372036854775807L && <= 9223372036854775806L."""

            Result.all(
              List(
                Result
                  .assert(shouldNotCompile)
                  .log("""TestInlinedNumericMinMaxLong(100L + 1L) should have failed compilation but it succeeded."""),
                (shouldNotCompileError ==== expectedErrorMessage).log(
                  """The error message doesn't match with the expected error message."""
                ),
              )
            )
          },
        )
      },
      example(
        s"test $name.from with invalid value", {
          val expected =
            s"Invalid value: [${Long.MinValue.toString}]. It must be >= ${(Long.MinValue + 1L).toString} && <= ${(Long.MaxValue - 1L).toString}."
          val actual   = TestInlinedNumericMinMaxLong.from(Long.MinValue)
          actual ==== Left(expected)
        },
      ),
      example(
        s"test $name.unsafeFrom with invalid value", {
          val expected =
            s"Invalid value: [${Long.MinValue.toString}]. It must be >= ${(Long.MinValue + 1L).toString} && <= ${(Long.MaxValue - 1L).toString}."
          val actual   = scala.util.Try(TestInlinedNumericMinMaxLong.unsafeFrom(Long.MinValue)).toEither
          Result.all(
            List(
              actual.matchPattern {
                case Left(err: IllegalArgumentException) =>
              },
              actual.left.map(_.getMessage) ==== Left(expected),
            )
          )
        },
      ),
      example(
        s"test $name.from with invalid value", {
          val expected =
            s"Invalid value: [${Long.MaxValue.toString}]. It must be >= ${(Long.MinValue + 1L).toString} && <= ${(Long.MaxValue - 1L).toString}."
          val actual   = TestInlinedNumericMinMaxLong.from(Long.MaxValue)
          actual ==== Left(expected)
        },
      ),
      example(
        s"test $name.unsafeFrom with invalid value", {
          val expected =
            s"Invalid value: [${Long.MaxValue.toString}]. It must be >= ${(Long.MinValue + 1L).toString} && <= ${(Long.MaxValue - 1L).toString}."
          val actual   = scala.util.Try(TestInlinedNumericMinMaxLong.unsafeFrom(Long.MaxValue)).toEither
          Result.all(
            List(
              actual.matchPattern {
                case Left(err: IllegalArgumentException) =>
              },
              actual.left.map(_.getMessage) ==== Left(expected),
            )
          )
        },
      ),
    )
  }

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

    override inline def minValue: Int = 10

  }

  type TestInlinedNumericMaxInt = TestInlinedNumericMaxInt.Type
  object TestInlinedNumericMaxInt extends InlinedNumericMax[Int] {

    override inline def maxValue: Int = 100

  }

  type TestInlinedNumericMinMaxInt = TestInlinedNumericMinMaxInt.Type
  object TestInlinedNumericMinMaxInt extends InlinedNumericMinMax[Int] {

    override inline def minValue: Int = 10
    override inline def maxValue: Int = 100

  }

  ///

  type TestInlinedNumericMinLong = TestInlinedNumericMinLong.Type
  object TestInlinedNumericMinLong extends InlinedNumericMin[Long] {

    override inline def minValue = 0x8000_0000_0000_0001L

  }

  type TestInlinedNumericMaxLong = TestInlinedNumericMaxLong.Type
  object TestInlinedNumericMaxLong extends InlinedNumericMax[Long] {

    override inline def maxValue = 0x7fff_ffff_ffff_fffeL

  }

  type TestInlinedNumericMinMaxLong = TestInlinedNumericMinMaxLong.Type
  object TestInlinedNumericMinMaxLong extends InlinedNumericMinMax[Long] {

    override inline def minValue: Long = 0x8000_0000_0000_0001L

    override inline def maxValue: Long = 0x7fff_ffff_ffff_fffeL

  }

}
