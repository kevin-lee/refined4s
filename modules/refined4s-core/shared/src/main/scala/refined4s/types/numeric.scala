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

  }

  type NonNegInt = NonNegInt.Type
  object NonNegInt extends Numeric[Int], MinMax[Int] {
    override def min: Type = apply(0)
    override def max: Type = apply(Int.MaxValue)

    override inline def invalidReason(a: Int): String = expectedMessage("a non-negative Int")

    override inline def predicate(a: Int): Boolean = a >= 0

  }

  type PosInt = PosInt.Type
  object PosInt extends Numeric[Int], MinMax[Int] {
    override def min: Type = apply(1)
    override def max: Type = apply(Int.MaxValue)

    override inline def invalidReason(a: Int): String = expectedMessage("a positive Int")

    override inline def predicate(a: Int): Boolean = a > 0

  }

  type NonPosInt = NonPosInt.Type
  object NonPosInt extends Numeric[Int], MinMax[Int] {
    override def min: Type = apply(Int.MinValue)
    override def max: Type = apply(0)

    override inline def invalidReason(a: Int): String = expectedMessage("a non-positive Int")

    override inline def predicate(a: Int): Boolean = a <= 0

  }

  type NegLong = NegLong.Type
  object NegLong extends Numeric[Long], MinMax[Long] {
    override def min: Type = apply(Long.MinValue)
    override def max: Type = apply(-1L)

    override inline def invalidReason(a: Long): String = expectedMessage("a negative Long")

    override inline def predicate(a: Long): Boolean = a < 0L

  }

  type NonNegLong = NonNegLong.Type
  object NonNegLong extends Numeric[Long], MinMax[Long] {
    override def min: Type = apply(0L)
    override def max: Type = apply(Long.MaxValue)

    override inline def invalidReason(a: Long): String = expectedMessage("a non-negative Long")

    override inline def predicate(a: Long): Boolean = a >= 0L

  }

  type PosLong = PosLong.Type
  object PosLong extends Numeric[Long], MinMax[Long] {
    override def min: Type = apply(1L)
    override def max: Type = apply(Long.MaxValue)

    override inline def invalidReason(a: Long): String = expectedMessage("a positive Long")

    override inline def predicate(a: Long): Boolean = a > 0L

  }

  type NonPosLong = NonPosLong.Type
  object NonPosLong extends Numeric[Long], MinMax[Long] {
    override def min: Type = apply(Long.MinValue)
    override def max: Type = apply(0L)

    override inline def invalidReason(a: Long): String = expectedMessage("a non-positive Long")

    override inline def predicate(a: Long): Boolean = a <= 0L

  }

  type NegShort = NegShort.Type
  object NegShort extends Numeric[Short], MinMax[Short] {
    override def min: Type = apply(Short.MinValue)
    override def max: Type = apply(-1)

    override inline def invalidReason(a: Short): String = expectedMessage("a negative Short")

    override inline def predicate(a: Short): Boolean = a < 0

  }

  type NonNegShort = NonNegShort.Type
  object NonNegShort extends Numeric[Short], MinMax[Short] {
    override def min: Type = apply(0)
    override def max: Type = apply(Short.MaxValue)

    override inline def invalidReason(a: Short): String = expectedMessage("a non-negative Short")

    override inline def predicate(a: Short): Boolean = a >= 0

  }

  type PosShort = PosShort.Type
  object PosShort extends Numeric[Short], MinMax[Short] {
    override def min: Type = apply(1)
    override def max: Type = apply(Short.MaxValue)

    override inline def invalidReason(a: Short): String = expectedMessage("a positive Short")

    override inline def predicate(a: Short): Boolean = a > 0

  }

  type NonPosShort = NonPosShort.Type
  object NonPosShort extends Numeric[Short], MinMax[Short] {
    override def min: Type = apply(Short.MinValue)
    override def max: Type = apply(0)

    override inline def invalidReason(a: Short): String = expectedMessage("a non-positive Short")

    override inline def predicate(a: Short): Boolean = a <= 0

  }

  type NegByte = NegByte.Type
  object NegByte extends Numeric[Byte], MinMax[Byte] {
    override def min: Type = apply(Byte.MinValue)
    override def max: Type = apply(-1)

    override inline def invalidReason(a: Byte): String = expectedMessage("a negative Byte")

    override inline def predicate(a: Byte): Boolean = a < 0

  }

  type NonNegByte = NonNegByte.Type
  object NonNegByte extends Numeric[Byte], MinMax[Byte] {
    override def min: Type = apply(0)
    override def max: Type = apply(Byte.MaxValue)

    override inline def invalidReason(a: Byte): String = expectedMessage("a non-negative Byte")

    override inline def predicate(a: Byte): Boolean = a >= 0

  }

  type PosByte = PosByte.Type
  object PosByte extends Numeric[Byte], MinMax[Byte] {
    override def min: Type = apply(1)
    override def max: Type = apply(Byte.MaxValue)

    override inline def invalidReason(a: Byte): String = expectedMessage("a positive Byte")

    override inline def predicate(a: Byte): Boolean = a > 0

  }

  type NonPosByte = NonPosByte.Type
  object NonPosByte extends Numeric[Byte], MinMax[Byte] {
    override def min: Type = apply(Byte.MinValue)
    override def max: Type = apply(0)

    override inline def invalidReason(a: Byte): String = expectedMessage("a non-positive Byte")

    override inline def predicate(a: Byte): Boolean = a <= 0

  }

  type NegFloat = NegFloat.Type
  object NegFloat extends Numeric[Float], MinMax[Float] {
    override def min: Type = apply(-java.lang.Float.MAX_VALUE) // Float.MinValue
    override def max: Type = apply(-1.4e-45f) // math.nextDown(0f)

    override inline def invalidReason(a: Float): String = expectedMessage("a negative Float")

    override inline def predicate(a: Float): Boolean = a < 0f

  }

  type NonNegFloat = NonNegFloat.Type
  object NonNegFloat extends Numeric[Float], MinMax[Float] {
    override def min: Type = apply(0f)
    override def max: Type = apply(Float.MaxValue)

    override inline def invalidReason(a: Float): String = expectedMessage("a non-negative Float")

    override inline def predicate(a: Float): Boolean = a >= 0f

  }

  type PosFloat = PosFloat.Type
  object PosFloat extends Numeric[Float], MinMax[Float] {
    override def min: Type = apply(1.4e-45f) // math.nextUp(0f)
    override def max: Type = apply(Float.MaxValue)

    override inline def invalidReason(a: Float): String = expectedMessage("a positive Float")

    override inline def predicate(a: Float): Boolean = a > 0f

  }

  type NonPosFloat = NonPosFloat.Type
  object NonPosFloat extends Numeric[Float], MinMax[Float] {
    override def min: Type = apply(-java.lang.Float.MAX_VALUE)
    override def max: Type = apply(0f)

    override inline def invalidReason(a: Float): String = expectedMessage("a non-positive Float")

    override inline def predicate(a: Float): Boolean = a <= 0f

  }

  type NegDouble = NegDouble.Type
  object NegDouble extends Numeric[Double], MinMax[Double] {
    override def min: Type = apply(-java.lang.Double.MAX_VALUE) // Double.MinValue
    override def max: Type = apply(-4.9e-324d) // math.nextDown(0d)

    override inline def invalidReason(a: Double): String = expectedMessage("a negative Double")

    override inline def predicate(a: Double): Boolean = a < 0d

  }

  type NonNegDouble = NonNegDouble.Type
  object NonNegDouble extends Numeric[Double], MinMax[Double] {
    override def min: Type = apply(0d)
    override def max: Type = apply(Double.MaxValue)

    override inline def invalidReason(a: Double): String = expectedMessage("a non-negative Double")

    override inline def predicate(a: Double): Boolean = a >= 0d

  }

  type PosDouble = PosDouble.Type
  object PosDouble extends Numeric[Double], MinMax[Double] {
    override def min: Type = apply(4.9e-324d) // math.nextUp(0d)
    override def max: Type = apply(Double.MaxValue)

    override inline def invalidReason(a: Double): String = expectedMessage("a positive Double")

    override inline def predicate(a: Double): Boolean = a > 0d

  }

  type NonPosDouble = NonPosDouble.Type
  object NonPosDouble extends Numeric[Double], MinMax[Double] {
    override def min: Type = apply(-java.lang.Double.MAX_VALUE) // Double.MinValue
    override def max: Type = apply(0d)

    override inline def invalidReason(a: Double): String = expectedMessage("a non-positive Double")

    override inline def predicate(a: Double): Boolean = a <= 0d

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
  }

}
