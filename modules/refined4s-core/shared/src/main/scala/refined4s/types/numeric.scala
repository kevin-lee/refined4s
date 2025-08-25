package refined4s.types

import refined4s.*
import refined4s.internal.numericTools

/** @author Kevin Lee
  * @since 2023-04-26
  */
trait numeric {

  // scalafix:off DisableSyntax.noFinalVal

  final type NegInt = numeric.NegInt
  final val NegInt = numeric.NegInt

  final type NonNegInt = numeric.NonNegInt
  final val NonNegInt = numeric.NonNegInt

  final type PosInt = numeric.PosInt
  final val PosInt = numeric.PosInt

  final type NonPosInt = numeric.NonPosInt
  final val NonPosInt = numeric.NonPosInt

  final type NegLong = numeric.NegLong
  final val NegLong = numeric.NegLong

  final type NonNegLong = numeric.NonNegLong
  final val NonNegLong = numeric.NonNegLong

  final type PosLong = numeric.PosLong
  final val PosLong = numeric.PosLong

  final type NonPosLong = numeric.NonPosLong
  final val NonPosLong = numeric.NonPosLong

  final type NegShort = numeric.NegShort
  final val NegShort = numeric.NegShort

  final type NonNegShort = numeric.NonNegShort
  final val NonNegShort = numeric.NonNegShort

  final type PosShort = numeric.PosShort
  final val PosShort = numeric.PosShort

  final type NonPosShort = numeric.NonPosShort
  final val NonPosShort = numeric.NonPosShort

  final type NegByte = numeric.NegByte
  final val NegByte = numeric.NegByte

  final type NonNegByte = numeric.NonNegByte
  final val NonNegByte = numeric.NonNegByte

  final type PosByte = numeric.PosByte
  final val PosByte = numeric.PosByte

  final type NonPosByte = numeric.NonPosByte
  final val NonPosByte = numeric.NonPosByte

  final type NegFloat = numeric.NegFloat
  final val NegFloat = numeric.NegFloat

  final type NonNegFloat = numeric.NonNegFloat
  final val NonNegFloat = numeric.NonNegFloat

  final type PosFloat = numeric.PosFloat
  final val PosFloat = numeric.PosFloat

  final type NonPosFloat = numeric.NonPosFloat
  final val NonPosFloat = numeric.NonPosFloat

  final type NegDouble = numeric.NegDouble
  final val NegDouble = numeric.NegDouble

  final type NonNegDouble = numeric.NonNegDouble
  final val NonNegDouble = numeric.NonNegDouble

  final type PosDouble = numeric.PosDouble
  final val PosDouble = numeric.PosDouble

  final type NonPosDouble = numeric.NonPosDouble
  final val NonPosDouble = numeric.NonPosDouble

  final type NegBigInt = numeric.NegBigInt
  final val NegBigInt = numeric.NegBigInt

  final type NonNegBigInt = numeric.NonNegBigInt
  final val NonNegBigInt = numeric.NonNegBigInt

  final type PosBigInt = numeric.PosBigInt
  final val PosBigInt = numeric.PosBigInt

  final type NonPosBigInt = numeric.NonPosBigInt
  final val NonPosBigInt = numeric.NonPosBigInt

  final type NegBigDecimal = numeric.NegBigDecimal
  final val NegBigDecimal = numeric.NegBigDecimal

  final type NonNegBigDecimal = numeric.NonNegBigDecimal
  final val NonNegBigDecimal = numeric.NonNegBigDecimal

  final type PosBigDecimal = numeric.PosBigDecimal
  final val PosBigDecimal = numeric.PosBigDecimal

  final type NonPosBigDecimal = numeric.NonPosBigDecimal
  final val NonPosBigDecimal = numeric.NonPosBigDecimal

  // scalafix:on
}
object numeric {

  trait Numeric[@specialized(Int, Long, Short, Byte, Float, Double) A: math.Ordering] extends Refined[A], CanBeOrdered[A]

  trait InlinedNumeric[@specialized(Int, Long, Short, Byte, Float, Double) A: math.Ordering] extends InlinedRefined[A], CanBeOrdered[A]

  trait Min[@specialized(Int, Long, Short, Byte, Float, Double) A] {
    self: NewtypeBase[A] =>
    def min: Type

    val MinValue: Type = min
  }

  trait Max[@specialized(Int, Long, Short, Byte, Float, Double) A] {
    self: NewtypeBase[A] =>
    def max: Type

    val MaxValue: Type = max
  }

  trait MinMax[@specialized(Int, Long, Short, Byte, Float, Double) A] extends Min[A], Max[A] {
    self: NewtypeBase[A] =>
  }

  type NegInt = NegInt.Type
  object NegInt extends Numeric[Int], MinMax[Int] {
    override def min: Type = apply(Int.MinValue)
    override def max: Type = apply(-1)

    override inline def invalidReason(a: Int): String = expectedMessage("a negative Int")

    override inline def predicate(a: Int): Boolean = a < 0

    inline given derivedNegIntEq(using eqActual: cats.Eq[Int]): cats.Eq[NegInt]         = internalDef.contraCoercible(eqActual)
    inline given derivedNegIntShow(using showActual: cats.Show[Int]): cats.Show[NegInt] = internalDef.contraCoercible(showActual)

  }

  type NonNegInt = NonNegInt.Type
  object NonNegInt extends Numeric[Int], MinMax[Int] {
    override def min: Type = apply(0)
    override def max: Type = apply(Int.MaxValue)

    override inline def invalidReason(a: Int): String = expectedMessage("a non-negative Int")

    override inline def predicate(a: Int): Boolean = a >= 0

    inline given derivedNonNegIntEq(using eqActual: cats.Eq[Int]): cats.Eq[NonNegInt]         = internalDef.contraCoercible(eqActual)
    inline given derivedNonNegIntShow(using showActual: cats.Show[Int]): cats.Show[NonNegInt] = internalDef.contraCoercible(showActual)

  }

  type PosInt = PosInt.Type
  object PosInt extends Numeric[Int], MinMax[Int] {
    override def min: Type = apply(1)
    override def max: Type = apply(Int.MaxValue)

    override inline def invalidReason(a: Int): String = expectedMessage("a positive Int")

    override inline def predicate(a: Int): Boolean = a > 0

    inline given derivedPosIntEq(using eqActual: cats.Eq[Int]): cats.Eq[PosInt]         = internalDef.contraCoercible(eqActual)
    inline given derivedPosIntShow(using showActual: cats.Show[Int]): cats.Show[PosInt] = internalDef.contraCoercible(showActual)

  }

  type NonPosInt = NonPosInt.Type
  object NonPosInt extends Numeric[Int], MinMax[Int] {
    override def min: Type = apply(Int.MinValue)
    override def max: Type = apply(0)

    override inline def invalidReason(a: Int): String = expectedMessage("a non-positive Int")

    override inline def predicate(a: Int): Boolean = a <= 0

    inline given derivedNonPosIntEq(using eqActual: cats.Eq[Int]): cats.Eq[NonPosInt]         = internalDef.contraCoercible(eqActual)
    inline given derivedNonPosIntShow(using showActual: cats.Show[Int]): cats.Show[NonPosInt] = internalDef.contraCoercible(showActual)

  }

  type NegLong = NegLong.Type
  object NegLong extends Numeric[Long], MinMax[Long] {
    override def min: Type = apply(Long.MinValue)
    override def max: Type = apply(-1L)

    override inline def invalidReason(a: Long): String = expectedMessage("a negative Long")

    override inline def predicate(a: Long): Boolean = a < 0L

    inline given derivedNegLongEq(using eqActual: cats.Eq[Long]): cats.Eq[NegLong]         = internalDef.contraCoercible(eqActual)
    inline given derivedNegLongShow(using showActual: cats.Show[Long]): cats.Show[NegLong] = internalDef.contraCoercible(showActual)

  }

  type NonNegLong = NonNegLong.Type
  object NonNegLong extends Numeric[Long], MinMax[Long] {
    override def min: Type = apply(0L)
    override def max: Type = apply(Long.MaxValue)

    override inline def invalidReason(a: Long): String = expectedMessage("a non-negative Long")

    override inline def predicate(a: Long): Boolean = a >= 0L

    inline given derivedNonNegLongEq(using eqActual: cats.Eq[Long]): cats.Eq[NonNegLong]         = internalDef.contraCoercible(eqActual)
    inline given derivedNonNegLongShow(using showActual: cats.Show[Long]): cats.Show[NonNegLong] = internalDef.contraCoercible(showActual)

  }

  type PosLong = PosLong.Type
  object PosLong extends Numeric[Long], MinMax[Long] {
    override def min: Type = apply(1L)
    override def max: Type = apply(Long.MaxValue)

    override inline def invalidReason(a: Long): String = expectedMessage("a positive Long")

    override inline def predicate(a: Long): Boolean = a > 0L

    inline given derivedPosLongEq(using eqActual: cats.Eq[Long]): cats.Eq[PosLong]         = internalDef.contraCoercible(eqActual)
    inline given derivedPosLongShow(using showActual: cats.Show[Long]): cats.Show[PosLong] = internalDef.contraCoercible(showActual)

  }

  type NonPosLong = NonPosLong.Type
  object NonPosLong extends Numeric[Long], MinMax[Long] {
    override def min: Type = apply(Long.MinValue)
    override def max: Type = apply(0L)

    override inline def invalidReason(a: Long): String = expectedMessage("a non-positive Long")

    override inline def predicate(a: Long): Boolean = a <= 0L

    inline given derivedNonPosLongEq(using eqActual: cats.Eq[Long]): cats.Eq[NonPosLong]         = internalDef.contraCoercible(eqActual)
    inline given derivedNonPosLongShow(using showActual: cats.Show[Long]): cats.Show[NonPosLong] = internalDef.contraCoercible(showActual)

  }

  type NegShort = NegShort.Type
  object NegShort extends Numeric[Short], MinMax[Short] {
    override def min: Type = apply(Short.MinValue)
    override def max: Type = apply(-1)

    override inline def invalidReason(a: Short): String = expectedMessage("a negative Short")

    override inline def predicate(a: Short): Boolean = a < 0

    inline given derivedNegShortEq(using eqActual: cats.Eq[Short]): cats.Eq[NegShort]         = internalDef.contraCoercible(eqActual)
    inline given derivedNegShortShow(using showActual: cats.Show[Short]): cats.Show[NegShort] = internalDef.contraCoercible(showActual)

  }

  type NonNegShort = NonNegShort.Type
  object NonNegShort extends Numeric[Short], MinMax[Short] {
    override def min: Type = apply(0)
    override def max: Type = apply(Short.MaxValue)

    override inline def invalidReason(a: Short): String = expectedMessage("a non-negative Short")

    override inline def predicate(a: Short): Boolean = a >= 0

    inline given derivedNonNegShortEq(using eqActual: cats.Eq[Short]): cats.Eq[NonNegShort]         = internalDef.contraCoercible(eqActual)
    inline given derivedNonNegShortShow(using showActual: cats.Show[Short]): cats.Show[NonNegShort] =
      internalDef.contraCoercible(showActual)

  }

  type PosShort = PosShort.Type
  object PosShort extends Numeric[Short], MinMax[Short] {
    override def min: Type = apply(1)
    override def max: Type = apply(Short.MaxValue)

    override inline def invalidReason(a: Short): String = expectedMessage("a positive Short")

    override inline def predicate(a: Short): Boolean = a > 0

    inline given derivedPosShortEq(using eqActual: cats.Eq[Short]): cats.Eq[PosShort]         = internalDef.contraCoercible(eqActual)
    inline given derivedPosShortShow(using showActual: cats.Show[Short]): cats.Show[PosShort] = internalDef.contraCoercible(showActual)

  }

  type NonPosShort = NonPosShort.Type
  object NonPosShort extends Numeric[Short], MinMax[Short] {
    override def min: Type = apply(Short.MinValue)
    override def max: Type = apply(0)

    override inline def invalidReason(a: Short): String = expectedMessage("a non-positive Short")

    override inline def predicate(a: Short): Boolean = a <= 0

    inline given derivedNonPosShortEq(using eqActual: cats.Eq[Short]): cats.Eq[NonPosShort]         = internalDef.contraCoercible(eqActual)
    inline given derivedNonPosShortShow(using showActual: cats.Show[Short]): cats.Show[NonPosShort] =
      internalDef.contraCoercible(showActual)

  }

  type NegByte = NegByte.Type
  object NegByte extends Numeric[Byte], MinMax[Byte] {
    override def min: Type = apply(Byte.MinValue)
    override def max: Type = apply(-1)

    override inline def invalidReason(a: Byte): String = expectedMessage("a negative Byte")

    override inline def predicate(a: Byte): Boolean = a < 0

    inline given derivedNegByteEq(using eqActual: cats.Eq[Byte]): cats.Eq[NegByte]         = internalDef.contraCoercible(eqActual)
    inline given derivedNegByteShow(using showActual: cats.Show[Byte]): cats.Show[NegByte] = internalDef.contraCoercible(showActual)

  }

  type NonNegByte = NonNegByte.Type
  object NonNegByte extends Numeric[Byte], MinMax[Byte] {
    override def min: Type = apply(0)
    override def max: Type = apply(Byte.MaxValue)

    override inline def invalidReason(a: Byte): String = expectedMessage("a non-negative Byte")

    override inline def predicate(a: Byte): Boolean = a >= 0

    inline given derivedNonNegByteEq(using eqActual: cats.Eq[Byte]): cats.Eq[NonNegByte]         = internalDef.contraCoercible(eqActual)
    inline given derivedNonNegByteShow(using showActual: cats.Show[Byte]): cats.Show[NonNegByte] = internalDef.contraCoercible(showActual)

  }

  type PosByte = PosByte.Type
  object PosByte extends Numeric[Byte], MinMax[Byte] {
    override def min: Type = apply(1)
    override def max: Type = apply(Byte.MaxValue)

    override inline def invalidReason(a: Byte): String = expectedMessage("a positive Byte")

    override inline def predicate(a: Byte): Boolean = a > 0

    inline given derivedPosByteEq(using eqActual: cats.Eq[Byte]): cats.Eq[PosByte]         = internalDef.contraCoercible(eqActual)
    inline given derivedPosByteShow(using showActual: cats.Show[Byte]): cats.Show[PosByte] = internalDef.contraCoercible(showActual)

  }

  type NonPosByte = NonPosByte.Type
  object NonPosByte extends Numeric[Byte], MinMax[Byte] {
    override def min: Type = apply(Byte.MinValue)
    override def max: Type = apply(0)

    override inline def invalidReason(a: Byte): String = expectedMessage("a non-positive Byte")

    override inline def predicate(a: Byte): Boolean = a <= 0

    inline given derivedNonPosByteEq(using eqActual: cats.Eq[Byte]): cats.Eq[NonPosByte]         = internalDef.contraCoercible(eqActual)
    inline given derivedNonPosByteShow(using showActual: cats.Show[Byte]): cats.Show[NonPosByte] = internalDef.contraCoercible(showActual)

  }

  type NegFloat = NegFloat.Type
  object NegFloat extends Numeric[Float], MinMax[Float] {
    override def min: Type = apply(-java.lang.Float.MAX_VALUE) // Float.MinValue
    override def max: Type = apply(-1.4e-45f) // math.nextDown(0f)

    override inline def invalidReason(a: Float): String = expectedMessage("a negative Float")

    override inline def predicate(a: Float): Boolean = a < 0f

    inline given derivedNegFloatEq(using eqActual: cats.Eq[Float]): cats.Eq[NegFloat]         = internalDef.contraCoercible(eqActual)
    inline given derivedNegFloatShow(using showActual: cats.Show[Float]): cats.Show[NegFloat] = internalDef.contraCoercible(showActual)

  }

  type NonNegFloat = NonNegFloat.Type
  object NonNegFloat extends Numeric[Float], MinMax[Float] {
    override def min: Type = apply(0f)
    override def max: Type = apply(Float.MaxValue)

    override inline def invalidReason(a: Float): String = expectedMessage("a non-negative Float")

    override inline def predicate(a: Float): Boolean = a >= 0f

    inline given derivedNonNegFloatEq(using eqActual: cats.Eq[Float]): cats.Eq[NonNegFloat]         = internalDef.contraCoercible(eqActual)
    inline given derivedNonNegFloatShow(using showActual: cats.Show[Float]): cats.Show[NonNegFloat] =
      internalDef.contraCoercible(showActual)

  }

  type PosFloat = PosFloat.Type
  object PosFloat extends Numeric[Float], MinMax[Float] {
    override def min: Type = apply(1.4e-45f) // math.nextUp(0f)
    override def max: Type = apply(Float.MaxValue)

    override inline def invalidReason(a: Float): String = expectedMessage("a positive Float")

    override inline def predicate(a: Float): Boolean = a > 0f

    inline given derivedPosFloatEq(using eqActual: cats.Eq[Float]): cats.Eq[PosFloat]         = internalDef.contraCoercible(eqActual)
    inline given derivedPosFloatShow(using showActual: cats.Show[Float]): cats.Show[PosFloat] = internalDef.contraCoercible(showActual)

  }

  type NonPosFloat = NonPosFloat.Type
  object NonPosFloat extends Numeric[Float], MinMax[Float] {
    override def min: Type = apply(-java.lang.Float.MAX_VALUE)
    override def max: Type = apply(0f)

    override inline def invalidReason(a: Float): String = expectedMessage("a non-positive Float")

    override inline def predicate(a: Float): Boolean = a <= 0f

    inline given derivedNonPosFloatEq(using eqActual: cats.Eq[Float]): cats.Eq[NonPosFloat]         = internalDef.contraCoercible(eqActual)
    inline given derivedNonPosFloatShow(using showActual: cats.Show[Float]): cats.Show[NonPosFloat] =
      internalDef.contraCoercible(showActual)

  }

  type NegDouble = NegDouble.Type
  object NegDouble extends Numeric[Double], MinMax[Double] {
    override def min: Type = apply(-java.lang.Double.MAX_VALUE) // Double.MinValue
    override def max: Type = apply(-4.9e-324d) // math.nextDown(0d)

    override inline def invalidReason(a: Double): String = expectedMessage("a negative Double")

    override inline def predicate(a: Double): Boolean = a < 0d

    inline given derivedNegDoubleEq(using eqActual: cats.Eq[Double]): cats.Eq[NegDouble]         = internalDef.contraCoercible(eqActual)
    inline given derivedNegDoubleShow(using showActual: cats.Show[Double]): cats.Show[NegDouble] = internalDef.contraCoercible(showActual)

  }

  type NonNegDouble = NonNegDouble.Type
  object NonNegDouble extends Numeric[Double], MinMax[Double] {
    override def min: Type = apply(0d)
    override def max: Type = apply(Double.MaxValue)

    override inline def invalidReason(a: Double): String = expectedMessage("a non-negative Double")

    override inline def predicate(a: Double): Boolean = a >= 0d

    inline given derivedNonNegDoubleEq(using eqActual: cats.Eq[Double]): cats.Eq[NonNegDouble] = internalDef.contraCoercible(eqActual)
    inline given derivedNonNegDoubleShow(using showActual: cats.Show[Double]): cats.Show[NonNegDouble] =
      internalDef.contraCoercible(showActual)

  }

  type PosDouble = PosDouble.Type
  object PosDouble extends Numeric[Double], MinMax[Double] {
    override def min: Type = apply(4.9e-324d) // math.nextUp(0d)
    override def max: Type = apply(Double.MaxValue)

    override inline def invalidReason(a: Double): String = expectedMessage("a positive Double")

    override inline def predicate(a: Double): Boolean = a > 0d

    inline given derivedPosDoubleEq(using eqActual: cats.Eq[Double]): cats.Eq[PosDouble]         = internalDef.contraCoercible(eqActual)
    inline given derivedPosDoubleShow(using showActual: cats.Show[Double]): cats.Show[PosDouble] = internalDef.contraCoercible(showActual)

  }

  type NonPosDouble = NonPosDouble.Type
  object NonPosDouble extends Numeric[Double], MinMax[Double] {
    override def min: Type = apply(-java.lang.Double.MAX_VALUE) // Double.MinValue
    override def max: Type = apply(0d)

    override inline def invalidReason(a: Double): String = expectedMessage("a non-positive Double")

    override inline def predicate(a: Double): Boolean = a <= 0d

    inline given derivedNonPosDoubleEq(using eqActual: cats.Eq[Double]): cats.Eq[NonPosDouble] = internalDef.contraCoercible(eqActual)
    inline given derivedNonPosDoubleShow(using showActual: cats.Show[Double]): cats.Show[NonPosDouble] =
      internalDef.contraCoercible(showActual)

  }

  val BigInt0: BigInt = numericTools.BigInt0

  val BigDecimal0: BigDecimal = numericTools.BigDecimal0

  type NegBigInt = NegBigInt.Type
  @SuppressWarnings(Array("org.wartremover.warts.Overloading"))
  object NegBigInt extends InlinedNumeric[BigInt] {
    override inline def invalidReason(a: BigInt): String = expectedMessage("a negative BigInt")

    override def predicate(a: BigInt): Boolean = a < BigInt0

    override inline val inlinedExpectedValue = "a negative BigInt"

    override inline def inlinedPredicate(inline a: BigInt): Boolean = ${ numericTools.isNegativeBigInt('a) }

    inline def apply(inline a: Int): Type = apply(BigInt(a))

    inline def apply(inline a: Long): Type = apply(BigInt(a))

    inline def apply(inline a: BigDecimal): Type = apply(a.toBigInt)

    inline def apply(inline a: String): Type = apply(BigInt(a))

    inline given derivedNegBigIntEq(using eqActual: cats.Eq[BigInt]): cats.Eq[NegBigInt]         = internalDef.contraCoercible(eqActual)
    inline given derivedNegBigIntShow(using showActual: cats.Show[BigInt]): cats.Show[NegBigInt] = internalDef.contraCoercible(showActual)

  }

  type NonNegBigInt = NonNegBigInt.Type
  @SuppressWarnings(Array("org.wartremover.warts.Overloading"))
  object NonNegBigInt extends InlinedNumeric[BigInt] {
    override inline def invalidReason(a: BigInt): String = expectedMessage("a non-negative BigInt")

    override def predicate(a: BigInt): Boolean = a >= BigInt0

    override inline val inlinedExpectedValue = "a non-negative BigInt"

    override inline def inlinedPredicate(inline a: BigInt): Boolean = ${ numericTools.isNonNegativeBigInt('a) }

    inline def apply(inline a: Int): Type = apply(BigInt(a))

    inline def apply(inline a: Long): Type = apply(BigInt(a))

    inline def apply(inline a: String): Type = apply(BigInt(a))

    inline given derivedNonNegBigIntEq(using eqActual: cats.Eq[BigInt]): cats.Eq[NonNegBigInt] = internalDef.contraCoercible(eqActual)
    inline given derivedNonNegBigIntShow(using showActual: cats.Show[BigInt]): cats.Show[NonNegBigInt] =
      internalDef.contraCoercible(showActual)

  }

  type PosBigInt = PosBigInt.Type
  @SuppressWarnings(Array("org.wartremover.warts.Overloading"))
  object PosBigInt extends InlinedNumeric[BigInt] {
    override inline def invalidReason(a: BigInt): String = expectedMessage("a positive BigInt")

    override def predicate(a: BigInt): Boolean = a > BigInt0

    override inline val inlinedExpectedValue = "a positive BigInt"

    override inline def inlinedPredicate(inline a: BigInt): Boolean = ${ numericTools.isPositiveBigInt('a) }

    inline def apply(inline a: Int): Type = apply(BigInt(a))

    inline def apply(inline a: Long): Type = apply(BigInt(a))

    inline def apply(inline a: String): Type = apply(BigInt(a))

    inline given derivedPosBigIntEq(using eqActual: cats.Eq[BigInt]): cats.Eq[PosBigInt]         = internalDef.contraCoercible(eqActual)
    inline given derivedPosBigIntShow(using showActual: cats.Show[BigInt]): cats.Show[PosBigInt] = internalDef.contraCoercible(showActual)

  }

  type NonPosBigInt = NonPosBigInt.Type
  @SuppressWarnings(Array("org.wartremover.warts.Overloading"))
  object NonPosBigInt extends InlinedNumeric[BigInt] {
    override inline def invalidReason(a: BigInt): String = expectedMessage("a non-positive BigInt")

    override def predicate(a: BigInt): Boolean = a <= BigInt0

    override inline val inlinedExpectedValue = "a non-positive BigInt"

    override inline def inlinedPredicate(inline a: BigInt): Boolean = ${ numericTools.isNonPositiveBigInt('a) }

    inline def apply(inline a: Int): Type = apply(BigInt(a))

    inline def apply(inline a: Long): Type = apply(BigInt(a))

    inline def apply(inline a: String): Type = apply(BigInt(a))

    inline given derivedNonPosBigIntEq(using eqActual: cats.Eq[BigInt]): cats.Eq[NonPosBigInt] = internalDef.contraCoercible(eqActual)
    inline given derivedNonPosBigIntShow(using showActual: cats.Show[BigInt]): cats.Show[NonPosBigInt] =
      internalDef.contraCoercible(showActual)

  }

  type NegBigDecimal = NegBigDecimal.Type
  @SuppressWarnings(Array("org.wartremover.warts.Overloading"))
  object NegBigDecimal extends InlinedNumeric[BigDecimal] {
    override inline def invalidReason(a: BigDecimal): String = expectedMessage("a negative BigDecimal")

    override def predicate(a: BigDecimal): Boolean = a < BigDecimal0

    override inline val inlinedExpectedValue = "a negative BigDecimal"

    override inline def inlinedPredicate(inline a: BigDecimal): Boolean = ${ numericTools.isNegativeBigDecimal('a) }

    inline def apply(inline a: Int): Type = apply(BigDecimal(a))

    inline def apply(inline a: Long): Type = apply(BigDecimal(a))

    inline def apply(inline a: Float): Type = apply(BigDecimal(a))

    inline def apply(inline a: Double): Type = apply(BigDecimal(a))

    inline def apply(inline a: String): Type = apply(BigDecimal(a))

    inline given derivedNegBigDecimalEq(using eqActual: cats.Eq[BigDecimal]): cats.Eq[NegBigDecimal] = internalDef.contraCoercible(eqActual)
    inline given derivedNegBigDecimalShow(using showActual: cats.Show[BigDecimal]): cats.Show[NegBigDecimal] =
      internalDef.contraCoercible(showActual)

  }

  type NonNegBigDecimal = NonNegBigDecimal.Type
  @SuppressWarnings(Array("org.wartremover.warts.Overloading"))
  object NonNegBigDecimal extends InlinedNumeric[BigDecimal] {
    override inline def invalidReason(a: BigDecimal): String = expectedMessage("a non-negative BigDecimal")

    override def predicate(a: BigDecimal): Boolean = a >= BigDecimal0

    override inline val inlinedExpectedValue = "a non-negative BigDecimal"

    override inline def inlinedPredicate(inline a: BigDecimal): Boolean = ${ numericTools.isNonNegativeBigDecimal('a) }

    inline def apply(inline a: Int): Type = apply(BigDecimal(a))

    inline def apply(inline a: Long): Type = apply(BigDecimal(a))

    inline def apply(inline a: Float): Type = apply(BigDecimal(a))

    inline def apply(inline a: Double): Type = apply(BigDecimal(a))

    inline def apply(inline a: String): Type = apply(BigDecimal(a))

    inline given derivedNonNegBigDecimalEq(using eqActual: cats.Eq[BigDecimal]): cats.Eq[NonNegBigDecimal]         =
      internalDef.contraCoercible(eqActual)
    inline given derivedNonNegBigDecimalShow(using showActual: cats.Show[BigDecimal]): cats.Show[NonNegBigDecimal] =
      internalDef.contraCoercible(showActual)

  }

  type PosBigDecimal = PosBigDecimal.Type
  @SuppressWarnings(Array("org.wartremover.warts.Overloading"))
  object PosBigDecimal extends InlinedNumeric[BigDecimal] {
    override inline def invalidReason(a: BigDecimal): String = expectedMessage("a positive BigDecimal")

    override def predicate(a: BigDecimal): Boolean = a > BigDecimal0

    override inline val inlinedExpectedValue = "a positive BigDecimal"

    override inline def inlinedPredicate(inline a: BigDecimal): Boolean = ${ numericTools.isPositiveBigDecimal('a) }

    inline def apply(inline a: Int): Type = apply(BigDecimal(a))

    inline def apply(inline a: Long): Type = apply(BigDecimal(a))

    inline def apply(inline a: Float): Type = apply(BigDecimal(a))

    inline def apply(inline a: Double): Type = apply(BigDecimal(a))

    inline def apply(inline a: String): Type = apply(BigDecimal(a))

    inline given derivedPosBigDecimalEq(using eqActual: cats.Eq[BigDecimal]): cats.Eq[PosBigDecimal] = internalDef.contraCoercible(eqActual)
    inline given derivedPosBigDecimalShow(using showActual: cats.Show[BigDecimal]): cats.Show[PosBigDecimal] =
      internalDef.contraCoercible(showActual)

  }

  type NonPosBigDecimal = NonPosBigDecimal.Type
  @SuppressWarnings(Array("org.wartremover.warts.Overloading"))
  object NonPosBigDecimal extends InlinedNumeric[BigDecimal] {
    override inline def invalidReason(a: BigDecimal): String = expectedMessage("a non-positive BigDecimal")

    override def predicate(a: BigDecimal): Boolean = a <= BigDecimal0

    override inline val inlinedExpectedValue = "a non-positive BigDecimal"

    override inline def inlinedPredicate(inline a: BigDecimal): Boolean = ${ numericTools.isNonPositiveBigDecimal('a) }

    inline def apply(inline a: Int): Type = apply(BigDecimal(a))

    inline def apply(inline a: Long): Type = apply(BigDecimal(a))

    inline def apply(inline a: Float): Type = apply(BigDecimal(a))

    inline def apply(inline a: Double): Type = apply(BigDecimal(a))

    inline def apply(inline a: String): Type = apply(BigDecimal(a))

    inline given derivedNonPosBigDecimalEq(using eqActual: cats.Eq[BigDecimal]): cats.Eq[NonPosBigDecimal]         =
      internalDef.contraCoercible(eqActual)
    inline given derivedNonPosBigDecimalShow(using showActual: cats.Show[BigDecimal]): cats.Show[NonPosBigDecimal] =
      internalDef.contraCoercible(showActual)

  }

}
