package refined4s.internal

import hedgehog.*
import hedgehog.runner.*
import scala.quoted.*

/** @author Kevin Lee
  * @since 2026-02-04
  */
object NumericValidatorSpec extends Properties {
  inline def lt[A](inline a: A, inline b: A): Boolean =
    ${ NumericValidator.ltImpl[A]('a, 'b) }

  inline def lteq[A](inline a: A, inline b: A): Boolean =
    ${ NumericValidator.lteqImpl[A]('a, 'b) }

  inline def equiv[A](inline a: A, inline b: A): Boolean =
    ${ NumericValidator.equivImpl[A]('a, 'b) }

  inline def gt[A](inline a: A, inline b: A): Boolean =
    ${ NumericValidator.gtImpl[A]('a, 'b) }

  inline def gteq[A](inline a: A, inline b: A): Boolean =
    ${ NumericValidator.gteqImpl[A]('a, 'b) }

  inline def minMaxToString[A](inline min: A, inline max: A): String =
    ${ NumericValidator.minMaxToStringImpl[A]('min, 'max) }

  inline def minToString[A](inline min: A): String =
    ${ NumericValidator.minToStringImpl[A]('min) }

  inline def maxToString[A](inline max: A): String =
    ${ NumericValidator.maxToStringImpl[A]('max) }

  inline def withinMinMax[A](inline a: A, inline min: A, inline max: A): Boolean =
    ${ NumericValidator.withinMinMaxImpl[A]('a, 'min, 'max) }

  final case class NoOrdering(value: Int)

  private[internal] inline def intA: Int   = 1
  private[internal] inline def intB: Int   = 2
  private[internal] inline def intMin: Int = 0
  private[internal] inline def intMax: Int = 3

  private[internal] inline def longA: Long   = 1L
  private[internal] inline def longB: Long   = 2L
  private[internal] inline def longMin: Long = 0L
  private[internal] inline def longMax: Long = 3L

  private[internal] inline def doubleA: Double   = 1.0
  private[internal] inline def doubleB: Double   = 2.0
  private[internal] inline def doubleMin: Double = 0.0
  private[internal] inline def doubleMax: Double = 3.0

  private[internal] inline def floatA: Float   = 1.0f
  private[internal] inline def floatB: Float   = 2.0f
  private[internal] inline def floatMin: Float = 0.0f
  private[internal] inline def floatMax: Float = 3.0f

  private[internal] inline def shortA: Short   = 1.toShort
  private[internal] inline def shortB: Short   = 2.toShort
  private[internal] inline def shortMin: Short = 0.toShort
  private[internal] inline def shortMax: Short = 3.toShort

  private[internal] inline def byteA: Byte   = 1.toByte
  private[internal] inline def byteB: Byte   = 2.toByte
  private[internal] inline def byteMin: Byte = 0.toByte
  private[internal] inline def byteMax: Byte = 3.toByte

  private[internal] inline def charA: Char   = 65.toChar
  private[internal] inline def charB: Char   = 66.toChar
  private[internal] inline def charMin: Char = 64.toChar
  private[internal] inline def charMax: Char = 67.toChar

  private[internal] val intGen: Gen[Int]                  = Gen.int(Range.linear(-1000, 1000))
  private[internal] val longGen: Gen[Long]                = Gen.long(Range.linear(-1000L, 1000L))
  private[internal] val doubleGen: Gen[Double]            = Gen.double(Range.linearFrac(-1000.0, 1000.0))
  private[internal] val floatGen: Gen[Float]              = Gen.double(Range.linearFrac(-1000f, 1000f)).map(_.toFloat)
  private[internal] val shortGen: Gen[Short]              = Gen.short(Range.linear(Short.MinValue, Short.MaxValue))
  private[internal] val byteGen: Gen[Byte]                = Gen.byte(Range.linear(Byte.MinValue, Byte.MaxValue))
  private[internal] val charGen: Gen[Char]                = Gen.char(0.toChar, 200.toChar)
  private[internal] val expectedNoOrderingMessage: String =
    "Ordering[refined4s.internal.NumericValidatorSpec.NoOrdering] not found"

  override def tests: List[Test] =
    compileTimeTests("Int", intA, intB, intMin, intMax) ++
      runtimeTests("Int", intGen, intMin, intMax) ++
      compileTimeTests("Long", longA, longB, longMin, longMax) ++
      runtimeTests("Long", longGen, longMin, longMax) ++
      compileTimeTests("Double", doubleA, doubleB, doubleMin, doubleMax) ++
      runtimeTests("Double", doubleGen, doubleMin, doubleMax) ++
      compileTimeTests("Float", floatA, floatB, floatMin, floatMax) ++
      runtimeTests("Float", floatGen, floatMin, floatMax) ++
      compileTimeTests("Short", shortA, shortB, shortMin, shortMax) ++
      runtimeTests("Short", shortGen, shortMin, shortMax) ++
      compileTimeTests("Byte", byteA, byteB, byteMin, byteMax) ++
      runtimeTests("Byte", byteGen, byteMin, byteMax) ++
      compileTimeTests("Char", charA, charB, charMin, charMax) ++
      runtimeTests("Char", charGen, charMin, charMax) ++
      errorMessageTests ++
      toStringTests

  private[internal] inline def compileTimeTests[A](
    name: String,
    inline a: A,
    inline b: A,
    inline min: A,
    inline max: A,
  ): List[Test] =
    List(
      example(s"$name lt compile-time", Result.assert(lt(a, b)).log(s"lt($a, $b) should return true")),
      example(s"$name lteq compile-time", Result.assert(lteq(a, b)).log(s"lteq($a, $b) should return true")),
      example(s"$name lteq compile-time", Result.assert(lteq(a, a)).log(s"lteq($a, $a) should return true")),
      example(s"$name equiv compile-time", Result.assert(equiv(a, a)).log(s"equiv($a, $a) should return true")),
      example(s"$name equiv compile-time", (equiv(a, b) ==== false).log(s"equiv($a, $b) should return false")),
      example(s"$name gt compile-time", Result.assert(gt(b, a)).log(s"gt($b, $a) should return true")),
      example(s"$name gteq compile-time", Result.assert(gteq(b, a)).log(s"gteq($b, $a) should return true")),
      example(s"$name gteq compile-time", Result.assert(gteq(a, a)).log(s"gteq($a, $a) should return true")),
      example(
        s"$name withinMinMax compile-time",
        Result
          .assert(withinMinMax(a, min, max))
          .log(s"withinMinMax($a, $min, $max) should return true"),
      ),
    )

  private def runtimeTests[A: Ordering](
    name: String,
    gen: Gen[A],
    @annotation.unused min: A,
    @annotation.unused max: A,
  ): List[Test] = {
    val ordering = Ordering[A]
    List(
      property(
        s"$name lt runtime",
        for {
          a <- gen.log("a")
          b <- gen.log("b")
        } yield lt(a, b) ==== ordering.lt(a, b),
      ),
      property(
        s"$name lteq runtime",
        for {
          a <- gen.log("a")
          b <- gen.log("b")
        } yield lteq(a, b) ==== ordering.lteq(a, b),
      ),
      property(
        s"$name equiv runtime",
        for {
          a <- gen.log("a")
          b <- gen.log("b")
        } yield equiv(a, b) ==== ordering.equiv(a, b),
      ),
      property(
        s"$name gt runtime",
        for {
          a <- gen.log("a")
          b <- gen.log("b")
        } yield gt(a, b) ==== ordering.gt(a, b),
      ),
      property(
        s"$name gteq runtime",
        for {
          a <- gen.log("a")
          b <- gen.log("b")
        } yield gteq(a, b) ==== ordering.gteq(a, b),
      ),
      property(
        s"$name withinMinMax runtime",
        for {
          a <- gen.log("a")
        } yield withinMinMax(a, min, max) ==== (ordering.gteq(a, min) && ordering.lteq(a, max)),
      ),
    )
  }

  private[internal] def errorMessageTests: List[Test] = {
    import scala.compiletime.testing.{typeCheckErrors, typeChecks}

    inline def checkError(inline name: String, inline code: String): Test =
      example(
        name, {
          val shouldNotCompile      = !typeChecks(code)
          val shouldNotCompileError = typeCheckErrors(code).map(_.message).mkString
          Result.all(
            List(
              Result
                .assert(shouldNotCompile)
                .log(s"$name should have failed compilation but it succeeded."),
              (shouldNotCompileError ==== expectedNoOrderingMessage)
                .log(s"$name error message does not match expected message."),
            )
          )
        },
      )

    List(
      checkError(
        "lt should fail without Ordering",
        """refined4s.internal.NumericValidatorSpec.lt[refined4s.internal.NumericValidatorSpec.NoOrdering](
          refined4s.internal.NumericValidatorSpec.NoOrdering(1),
          refined4s.internal.NumericValidatorSpec.NoOrdering(2)
        )""",
      ),
      checkError(
        "lteq should fail without Ordering",
        """refined4s.internal.NumericValidatorSpec.lteq[refined4s.internal.NumericValidatorSpec.NoOrdering](
          refined4s.internal.NumericValidatorSpec.NoOrdering(1),
          refined4s.internal.NumericValidatorSpec.NoOrdering(2)
        )""",
      ),
      checkError(
        "equiv should fail without Ordering",
        """refined4s.internal.NumericValidatorSpec.equiv[refined4s.internal.NumericValidatorSpec.NoOrdering](
          refined4s.internal.NumericValidatorSpec.NoOrdering(1),
          refined4s.internal.NumericValidatorSpec.NoOrdering(2)
        )""",
      ),
      checkError(
        "gt should fail without Ordering",
        """refined4s.internal.NumericValidatorSpec.gt[refined4s.internal.NumericValidatorSpec.NoOrdering](
          refined4s.internal.NumericValidatorSpec.NoOrdering(1),
          refined4s.internal.NumericValidatorSpec.NoOrdering(2)
        )""",
      ),
      checkError(
        "gteq should fail without Ordering",
        """refined4s.internal.NumericValidatorSpec.gteq[refined4s.internal.NumericValidatorSpec.NoOrdering](
          refined4s.internal.NumericValidatorSpec.NoOrdering(1),
          refined4s.internal.NumericValidatorSpec.NoOrdering(2)
        )""",
      ),
      checkError(
        "withinMinMax should fail without Ordering",
        """refined4s.internal.NumericValidatorSpec.withinMinMax[refined4s.internal.NumericValidatorSpec.NoOrdering](
          refined4s.internal.NumericValidatorSpec.NoOrdering(1),
          refined4s.internal.NumericValidatorSpec.NoOrdering(0),
          refined4s.internal.NumericValidatorSpec.NoOrdering(2)
        )""",
      ),
    )
  }

  private[internal] def toStringTests: List[Test] =
    List(
      example("minMaxToString[Int](0, 10)", minMaxToString(0, 10) ==== ">= 0 && <= 10"),
      example("minToString[Int](0)", minToString(0) ==== ">= 0"),
      example("maxToString[Int](10)", maxToString(10) ==== "<= 10"),
      // Long
      example("minMaxToString[Long](0L, 10L)", minMaxToString(0L, 10L) ==== ">= 0L && <= 10L"),
      example("minToString[Long](0L)", minToString(0L) ==== ">= 0L"),
      example("maxToString[Long](10L)", maxToString(10L) ==== "<= 10L"),
      // Double
      example("minMaxToString[Double](0.0, 10.0)", minMaxToString(0.0, 10.0) ==== ">= 0.0d && <= 10.0d"),
      example("minToString[Double](0.0)", minToString(0.0) ==== ">= 0.0d"),
      example("maxToString[Double](10.0)", maxToString(10.0) ==== "<= 10.0d"),
      // Float
      example("minMaxToString[Float](0.0f, 10.0f)", minMaxToString(0.0f, 10.0f) ==== ">= 0.0f && <= 10.0f"),
      example("minToString[Float](0.0f)", minToString(0.0f) ==== ">= 0.0f"),
      example("maxToString[Float](10.0f)", maxToString(10.0f) ==== "<= 10.0f"),
      // Short
      example("minMaxToString[Short](0.toShort, 10.toShort)", minMaxToString(0.toShort, 10.toShort) ==== ">= 0 && <= 10"),
      example("minToString[Short](0.toShort)", minToString(0.toShort) ==== ">= 0"),
      example("maxToString[Short](10.toShort)", maxToString(10.toShort) ==== "<= 10"),
      // Byte
      example("minMaxToString[Byte](0.toByte, 10.toByte)", minMaxToString(0.toByte, 10.toByte) ==== ">= 0 && <= 10"),
      example("minToString[Byte](0.toByte)", minToString(0.toByte) ==== ">= 0"),
      example("maxToString[Byte](10.toByte)", maxToString(10.toByte) ==== "<= 10"),
      // Char
      example("minMaxToString[Char]('a', 'z')", minMaxToString('a', 'z') ==== ">= a && <= z"),
      example("minToString[Char]('a')", minToString('a') ==== ">= a"),
      example("maxToString[Char]('z')", maxToString('z') ==== "<= z"),
    )

}
