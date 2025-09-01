package refined4s.modules.chimney.derivation.types

import io.scalaland.chimney
import io.scalaland.chimney.{PartialTransformer, Transformer}
import refined4s.types.numeric.*

/** @author Kevin Lee
  * @since 2023-12-25
  */
trait numeric {

  inline given derivedNegIntTransformer: Transformer[NegInt, Int]               = numeric.derivedNegIntTransformer
  inline given derivedNegIntPartialTransformer: PartialTransformer[Int, NegInt] = numeric.derivedNegIntPartialTransformer

  inline given derivedNonNegIntTransformer: Transformer[NonNegInt, Int]               = numeric.derivedNonNegIntTransformer
  inline given derivedNonNegIntPartialTransformer: PartialTransformer[Int, NonNegInt] = numeric.derivedNonNegIntPartialTransformer

  inline given derivedPosIntTransformer: Transformer[PosInt, Int]               = numeric.derivedPosIntTransformer
  inline given derivedPosIntPartialTransformer: PartialTransformer[Int, PosInt] = numeric.derivedPosIntPartialTransformer

  inline given derivedNonPosIntTransformer: Transformer[NonPosInt, Int]               = numeric.derivedNonPosIntTransformer
  inline given derivedNonPosIntPartialTransformer: PartialTransformer[Int, NonPosInt] = numeric.derivedNonPosIntPartialTransformer

  inline given derivedNegLongTransformer: Transformer[NegLong, Long]               = numeric.derivedNegLongTransformer
  inline given derivedNegLongPartialTransformer: PartialTransformer[Long, NegLong] = numeric.derivedNegLongPartialTransformer

  inline given derivedNonNegLongTransformer: Transformer[NonNegLong, Long]               = numeric.derivedNonNegLongTransformer
  inline given derivedNonNegLongPartialTransformer: PartialTransformer[Long, NonNegLong] = numeric.derivedNonNegLongPartialTransformer

  inline given derivedPosLongTransformer: Transformer[PosLong, Long]               = numeric.derivedPosLongTransformer
  inline given derivedPosLongPartialTransformer: PartialTransformer[Long, PosLong] = numeric.derivedPosLongPartialTransformer

  inline given derivedNonPosLongTransformer: Transformer[NonPosLong, Long]               = numeric.derivedNonPosLongTransformer
  inline given derivedNonPosLongPartialTransformer: PartialTransformer[Long, NonPosLong] = numeric.derivedNonPosLongPartialTransformer

  inline given derivedNegShortTransformer: Transformer[NegShort, Short]               = numeric.derivedNegShortTransformer
  inline given derivedNegShortPartialTransformer: PartialTransformer[Short, NegShort] = numeric.derivedNegShortPartialTransformer

  inline given derivedNonNegShortTransformer: Transformer[NonNegShort, Short]               = numeric.derivedNonNegShortTransformer
  inline given derivedNonNegShortPartialTransformer: PartialTransformer[Short, NonNegShort] = numeric.derivedNonNegShortPartialTransformer

  inline given derivedPosShortTransformer: Transformer[PosShort, Short]               = numeric.derivedPosShortTransformer
  inline given derivedPosShortPartialTransformer: PartialTransformer[Short, PosShort] = numeric.derivedPosShortPartialTransformer

  inline given derivedNonPosShortTransformer: Transformer[NonPosShort, Short]               = numeric.derivedNonPosShortTransformer
  inline given derivedNonPosShortPartialTransformer: PartialTransformer[Short, NonPosShort] = numeric.derivedNonPosShortPartialTransformer

  inline given derivedNegByteTransformer: Transformer[NegByte, Byte]               = numeric.derivedNegByteTransformer
  inline given derivedNegBytePartialTransformer: PartialTransformer[Byte, NegByte] = numeric.derivedNegBytePartialTransformer

  inline given derivedNonNegByteTransformer: Transformer[NonNegByte, Byte]               = numeric.derivedNonNegByteTransformer
  inline given derivedNonNegBytePartialTransformer: PartialTransformer[Byte, NonNegByte] = numeric.derivedNonNegBytePartialTransformer

  inline given derivedPosByteTransformer: Transformer[PosByte, Byte]               = numeric.derivedPosByteTransformer
  inline given derivedPosBytePartialTransformer: PartialTransformer[Byte, PosByte] = numeric.derivedPosBytePartialTransformer

  inline given derivedNonPosByteTransformer: Transformer[NonPosByte, Byte]               = numeric.derivedNonPosByteTransformer
  inline given derivedNonPosBytePartialTransformer: PartialTransformer[Byte, NonPosByte] = numeric.derivedNonPosBytePartialTransformer

  inline given derivedNegFloatTransformer: Transformer[NegFloat, Float]               = numeric.derivedNegFloatTransformer
  inline given derivedNegFloatPartialTransformer: PartialTransformer[Float, NegFloat] = numeric.derivedNegFloatPartialTransformer

  inline given derivedNonNegFloatTransformer: Transformer[NonNegFloat, Float]               = numeric.derivedNonNegFloatTransformer
  inline given derivedNonNegFloatPartialTransformer: PartialTransformer[Float, NonNegFloat] = numeric.derivedNonNegFloatPartialTransformer

  inline given derivedPosFloatTransformer: Transformer[PosFloat, Float]               = numeric.derivedPosFloatTransformer
  inline given derivedPosFloatPartialTransformer: PartialTransformer[Float, PosFloat] = numeric.derivedPosFloatPartialTransformer

  inline given derivedNonPosFloatTransformer: Transformer[NonPosFloat, Float]               = numeric.derivedNonPosFloatTransformer
  inline given derivedNonPosFloatPartialTransformer: PartialTransformer[Float, NonPosFloat] = numeric.derivedNonPosFloatPartialTransformer

  inline given derivedNegDoubleTransformer: Transformer[NegDouble, Double]               = numeric.derivedNegDoubleTransformer
  inline given derivedNegDoublePartialTransformer: PartialTransformer[Double, NegDouble] = numeric.derivedNegDoublePartialTransformer

  inline given derivedNonNegDoubleTransformer: Transformer[NonNegDouble, Double]               = numeric.derivedNonNegDoubleTransformer
  inline given derivedNonNegDoublePartialTransformer: PartialTransformer[Double, NonNegDouble] =
    numeric.derivedNonNegDoublePartialTransformer

  inline given derivedPosDoubleTransformer: Transformer[PosDouble, Double]               = numeric.derivedPosDoubleTransformer
  inline given derivedPosDoublePartialTransformer: PartialTransformer[Double, PosDouble] = numeric.derivedPosDoublePartialTransformer

  inline given derivedNonPosDoubleTransformer: Transformer[NonPosDouble, Double]               = numeric.derivedNonPosDoubleTransformer
  inline given derivedNonPosDoublePartialTransformer: PartialTransformer[Double, NonPosDouble] =
    numeric.derivedNonPosDoublePartialTransformer

  inline given derivedNegBigIntTransformer: Transformer[NegBigInt, BigInt]               = numeric.derivedNegBigIntTransformer
  inline given derivedNegBigIntPartialTransformer: PartialTransformer[BigInt, NegBigInt] = numeric.derivedNegBigIntPartialTransformer

  inline given derivedNonNegBigIntTransformer: Transformer[NonNegBigInt, BigInt]               = numeric.derivedNonNegBigIntTransformer
  inline given derivedNonNegBigIntPartialTransformer: PartialTransformer[BigInt, NonNegBigInt] =
    numeric.derivedNonNegBigIntPartialTransformer

  inline given derivedPosBigIntTransformer: Transformer[PosBigInt, BigInt]               = numeric.derivedPosBigIntTransformer
  inline given derivedPosBigIntPartialTransformer: PartialTransformer[BigInt, PosBigInt] = numeric.derivedPosBigIntPartialTransformer

  inline given derivedNonPosBigIntTransformer: Transformer[NonPosBigInt, BigInt]               = numeric.derivedNonPosBigIntTransformer
  inline given derivedNonPosBigIntPartialTransformer: PartialTransformer[BigInt, NonPosBigInt] =
    numeric.derivedNonPosBigIntPartialTransformer

  inline given derivedNegBigDecimalTransformer: Transformer[NegBigDecimal, BigDecimal] = numeric.derivedNegBigDecimalTransformer
  inline given derivedNegBigDecimalPartialTransformer: PartialTransformer[BigDecimal, NegBigDecimal] =
    numeric.derivedNegBigDecimalPartialTransformer

  inline given derivedNonNegBigDecimalTransformer: Transformer[NonNegBigDecimal, BigDecimal] = numeric.derivedNonNegBigDecimalTransformer
  inline given derivedNonNegBigDecimalPartialTransformer: PartialTransformer[BigDecimal, NonNegBigDecimal] =
    numeric.derivedNonNegBigDecimalPartialTransformer

  inline given derivedPosBigDecimalTransformer: Transformer[PosBigDecimal, BigDecimal] = numeric.derivedPosBigDecimalTransformer
  inline given derivedPosBigDecimalPartialTransformer: PartialTransformer[BigDecimal, PosBigDecimal] =
    numeric.derivedPosBigDecimalPartialTransformer

  inline given derivedNonPosBigDecimalTransformer: Transformer[NonPosBigDecimal, BigDecimal] = numeric.derivedNonPosBigDecimalTransformer
  inline given derivedNonPosBigDecimalPartialTransformer: PartialTransformer[BigDecimal, NonPosBigDecimal] =
    numeric.derivedNonPosBigDecimalPartialTransformer

}
object numeric {

  given derivedNegIntTransformer: Transformer[NegInt, Int] with {
    override def transform(src: NegInt): Int = src.value
  }
  given derivedNegIntPartialTransformer: PartialTransformer[Int, NegInt] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(NegInt.from(value)))

  given derivedNonNegIntTransformer: Transformer[NonNegInt, Int] with {
    override def transform(src: NonNegInt): Int = src.value
  }
  given derivedNonNegIntPartialTransformer: PartialTransformer[Int, NonNegInt] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(NonNegInt.from(value)))

  given derivedPosIntTransformer: Transformer[PosInt, Int] with {
    override def transform(src: PosInt): Int = src.value
  }
  given derivedPosIntPartialTransformer: PartialTransformer[Int, PosInt] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(PosInt.from(value)))

  given derivedNonPosIntTransformer: Transformer[NonPosInt, Int] with {
    override def transform(src: NonPosInt): Int = src.value
  }
  given derivedNonPosIntPartialTransformer: PartialTransformer[Int, NonPosInt] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(NonPosInt.from(value)))

  given derivedNegLongTransformer: Transformer[NegLong, Long] with {
    override def transform(src: NegLong): Long = src.value
  }
  given derivedNegLongPartialTransformer: PartialTransformer[Long, NegLong] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(NegLong.from(value)))

  given derivedNonNegLongTransformer: Transformer[NonNegLong, Long] with {
    override def transform(src: NonNegLong): Long = src.value
  }
  given derivedNonNegLongPartialTransformer: PartialTransformer[Long, NonNegLong] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(NonNegLong.from(value)))

  given derivedPosLongTransformer: Transformer[PosLong, Long] with {
    override def transform(src: PosLong): Long = src.value
  }
  given derivedPosLongPartialTransformer: PartialTransformer[Long, PosLong] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(PosLong.from(value)))

  given derivedNonPosLongTransformer: Transformer[NonPosLong, Long] with {
    override def transform(src: NonPosLong): Long = src.value
  }
  given derivedNonPosLongPartialTransformer: PartialTransformer[Long, NonPosLong] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(NonPosLong.from(value)))

  given derivedNegShortTransformer: Transformer[NegShort, Short] with {
    override def transform(src: NegShort): Short = src.value
  }
  given derivedNegShortPartialTransformer: PartialTransformer[Short, NegShort] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(NegShort.from(value)))

  given derivedNonNegShortTransformer: Transformer[NonNegShort, Short] with {
    override def transform(src: NonNegShort): Short = src.value
  }
  given derivedNonNegShortPartialTransformer: PartialTransformer[Short, NonNegShort] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(NonNegShort.from(value)))

  given derivedPosShortTransformer: Transformer[PosShort, Short] with {
    override def transform(src: PosShort): Short = src.value
  }
  given derivedPosShortPartialTransformer: PartialTransformer[Short, PosShort] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(PosShort.from(value)))

  given derivedNonPosShortTransformer: Transformer[NonPosShort, Short] with {
    override def transform(src: NonPosShort): Short = src.value
  }
  given derivedNonPosShortPartialTransformer: PartialTransformer[Short, NonPosShort] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(NonPosShort.from(value)))

  given derivedNegByteTransformer: Transformer[NegByte, Byte] with {
    override def transform(src: NegByte): Byte = src.value
  }
  given derivedNegBytePartialTransformer: PartialTransformer[Byte, NegByte] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(NegByte.from(value)))

  given derivedNonNegByteTransformer: Transformer[NonNegByte, Byte] with {
    override def transform(src: NonNegByte): Byte = src.value
  }
  given derivedNonNegBytePartialTransformer: PartialTransformer[Byte, NonNegByte] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(NonNegByte.from(value)))

  given derivedPosByteTransformer: Transformer[PosByte, Byte] with {
    override def transform(src: PosByte): Byte = src.value
  }
  given derivedPosBytePartialTransformer: PartialTransformer[Byte, PosByte] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(PosByte.from(value)))

  given derivedNonPosByteTransformer: Transformer[NonPosByte, Byte] with {
    override def transform(src: NonPosByte): Byte = src.value
  }
  given derivedNonPosBytePartialTransformer: PartialTransformer[Byte, NonPosByte] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(NonPosByte.from(value)))

  given derivedNegFloatTransformer: Transformer[NegFloat, Float] with {
    override def transform(src: NegFloat): Float = src.value
  }
  given derivedNegFloatPartialTransformer: PartialTransformer[Float, NegFloat] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(NegFloat.from(value)))

  given derivedNonNegFloatTransformer: Transformer[NonNegFloat, Float] with {
    override def transform(src: NonNegFloat): Float = src.value
  }
  given derivedNonNegFloatPartialTransformer: PartialTransformer[Float, NonNegFloat] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(NonNegFloat.from(value)))

  given derivedPosFloatTransformer: Transformer[PosFloat, Float] with {
    override def transform(src: PosFloat): Float = src.value
  }
  given derivedPosFloatPartialTransformer: PartialTransformer[Float, PosFloat] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(PosFloat.from(value)))

  given derivedNonPosFloatTransformer: Transformer[NonPosFloat, Float] with {
    override def transform(src: NonPosFloat): Float = src.value
  }
  given derivedNonPosFloatPartialTransformer: PartialTransformer[Float, NonPosFloat] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(NonPosFloat.from(value)))

  given derivedNegDoubleTransformer: Transformer[NegDouble, Double] with {
    override def transform(src: NegDouble): Double = src.value
  }
  given derivedNegDoublePartialTransformer: PartialTransformer[Double, NegDouble] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(NegDouble.from(value)))

  given derivedNonNegDoubleTransformer: Transformer[NonNegDouble, Double] with {
    override def transform(src: NonNegDouble): Double = src.value
  }
  given derivedNonNegDoublePartialTransformer: PartialTransformer[Double, NonNegDouble] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(NonNegDouble.from(value)))

  given derivedPosDoubleTransformer: Transformer[PosDouble, Double] with {
    override def transform(src: PosDouble): Double = src.value
  }
  given derivedPosDoublePartialTransformer: PartialTransformer[Double, PosDouble] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(PosDouble.from(value)))

  given derivedNonPosDoubleTransformer: Transformer[NonPosDouble, Double] with {
    override def transform(src: NonPosDouble): Double = src.value
  }
  given derivedNonPosDoublePartialTransformer: PartialTransformer[Double, NonPosDouble] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(NonPosDouble.from(value)))

  given derivedNegBigIntTransformer: Transformer[NegBigInt, BigInt] with {
    override def transform(src: NegBigInt): BigInt = src.value
  }
  given derivedNegBigIntPartialTransformer: PartialTransformer[BigInt, NegBigInt] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(NegBigInt.from(value)))

  given derivedNonNegBigIntTransformer: Transformer[NonNegBigInt, BigInt] with {
    override def transform(src: NonNegBigInt): BigInt = src.value
  }
  given derivedNonNegBigIntPartialTransformer: PartialTransformer[BigInt, NonNegBigInt] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(NonNegBigInt.from(value)))

  given derivedPosBigIntTransformer: Transformer[PosBigInt, BigInt] with {
    override def transform(src: PosBigInt): BigInt = src.value
  }
  given derivedPosBigIntPartialTransformer: PartialTransformer[BigInt, PosBigInt] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(PosBigInt.from(value)))

  given derivedNonPosBigIntTransformer: Transformer[NonPosBigInt, BigInt] with {
    override def transform(src: NonPosBigInt): BigInt = src.value
  }
  given derivedNonPosBigIntPartialTransformer: PartialTransformer[BigInt, NonPosBigInt] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(NonPosBigInt.from(value)))

  given derivedNegBigDecimalTransformer: Transformer[NegBigDecimal, BigDecimal] with {
    override def transform(src: NegBigDecimal): BigDecimal = src.value
  }
  given derivedNegBigDecimalPartialTransformer: PartialTransformer[BigDecimal, NegBigDecimal] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(NegBigDecimal.from(value)))

  given derivedNonNegBigDecimalTransformer: Transformer[NonNegBigDecimal, BigDecimal] with {
    override def transform(src: NonNegBigDecimal): BigDecimal = src.value
  }
  given derivedNonNegBigDecimalPartialTransformer: PartialTransformer[BigDecimal, NonNegBigDecimal] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(NonNegBigDecimal.from(value)))

  given derivedPosBigDecimalTransformer: Transformer[PosBigDecimal, BigDecimal] with {
    override def transform(src: PosBigDecimal): BigDecimal = src.value
  }
  given derivedPosBigDecimalPartialTransformer: PartialTransformer[BigDecimal, PosBigDecimal] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(PosBigDecimal.from(value)))

  given derivedNonPosBigDecimalTransformer: Transformer[NonPosBigDecimal, BigDecimal] with {
    override def transform(src: NonPosBigDecimal): BigDecimal = src.value
  }
  given derivedNonPosBigDecimalPartialTransformer: PartialTransformer[BigDecimal, NonPosBigDecimal] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(NonPosBigDecimal.from(value)))

}
