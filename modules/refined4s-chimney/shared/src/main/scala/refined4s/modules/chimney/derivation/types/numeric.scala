package refined4s.modules.chimney.derivation.types

import io.scalaland.chimney
import io.scalaland.chimney.{PartialTransformer, Transformer}
import refined4s.types.numeric.*

/** @author Kevin Lee
  * @since 2023-12-25
  */
trait numeric {

  inline given derivedNegIntTransformer: Transformer[NegInt, Int] with {
    override def transform(src: NegInt): Int = src.value
  }
  inline given derivedNegIntPartialTransformer: PartialTransformer[Int, NegInt] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(NegInt.from(value)))

  inline given derivedNonNegIntTransformer: Transformer[NonNegInt, Int] with {
    override def transform(src: NonNegInt): Int = src.value
  }
  inline given derivedNonNegIntPartialTransformer: PartialTransformer[Int, NonNegInt] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(NonNegInt.from(value)))

  inline given derivedPosIntTransformer: Transformer[PosInt, Int] with {
    override def transform(src: PosInt): Int = src.value
  }
  inline given derivedPosIntPartialTransformer: PartialTransformer[Int, PosInt] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(PosInt.from(value)))

  inline given derivedNonPosIntTransformer: Transformer[NonPosInt, Int] with {
    override def transform(src: NonPosInt): Int = src.value
  }
  inline given derivedNonPosIntPartialTransformer: PartialTransformer[Int, NonPosInt] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(NonPosInt.from(value)))

  inline given derivedNegLongTransformer: Transformer[NegLong, Long] with {
    override def transform(src: NegLong): Long = src.value
  }
  inline given derivedNegLongPartialTransformer: PartialTransformer[Long, NegLong] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(NegLong.from(value)))

  inline given derivedNonNegLongTransformer: Transformer[NonNegLong, Long] with {
    override def transform(src: NonNegLong): Long = src.value
  }
  inline given derivedNonNegLongPartialTransformer: PartialTransformer[Long, NonNegLong] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(NonNegLong.from(value)))

  inline given derivedPosLongTransformer: Transformer[PosLong, Long] with {
    override def transform(src: PosLong): Long = src.value
  }
  inline given derivedPosLongPartialTransformer: PartialTransformer[Long, PosLong] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(PosLong.from(value)))

  inline given derivedNonPosLongTransformer: Transformer[NonPosLong, Long] with {
    override def transform(src: NonPosLong): Long = src.value
  }
  inline given derivedNonPosLongPartialTransformer: PartialTransformer[Long, NonPosLong] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(NonPosLong.from(value)))

  inline given derivedNegShortTransformer: Transformer[NegShort, Short] with {
    override def transform(src: NegShort): Short = src.value
  }
  inline given derivedNegShortPartialTransformer: PartialTransformer[Short, NegShort] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(NegShort.from(value)))

  inline given derivedNonNegShortTransformer: Transformer[NonNegShort, Short] with {
    override def transform(src: NonNegShort): Short = src.value
  }
  inline given derivedNonNegShortPartialTransformer: PartialTransformer[Short, NonNegShort] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(NonNegShort.from(value)))

  inline given derivedPosShortTransformer: Transformer[PosShort, Short] with {
    override def transform(src: PosShort): Short = src.value
  }
  inline given derivedPosShortPartialTransformer: PartialTransformer[Short, PosShort] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(PosShort.from(value)))

  inline given derivedNonPosShortTransformer: Transformer[NonPosShort, Short] with {
    override def transform(src: NonPosShort): Short = src.value
  }
  inline given derivedNonPosShortPartialTransformer: PartialTransformer[Short, NonPosShort] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(NonPosShort.from(value)))

  inline given derivedNegByteTransformer: Transformer[NegByte, Byte] with {
    override def transform(src: NegByte): Byte = src.value
  }
  inline given derivedNegBytePartialTransformer: PartialTransformer[Byte, NegByte] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(NegByte.from(value)))

  inline given derivedNonNegByteTransformer: Transformer[NonNegByte, Byte] with {
    override def transform(src: NonNegByte): Byte = src.value
  }
  inline given derivedNonNegBytePartialTransformer: PartialTransformer[Byte, NonNegByte] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(NonNegByte.from(value)))

  inline given derivedPosByteTransformer: Transformer[PosByte, Byte] with {
    override def transform(src: PosByte): Byte = src.value
  }
  inline given derivedPosBytePartialTransformer: PartialTransformer[Byte, PosByte] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(PosByte.from(value)))

  inline given derivedNonPosByteTransformer: Transformer[NonPosByte, Byte] with {
    override def transform(src: NonPosByte): Byte = src.value
  }
  inline given derivedNonPosBytePartialTransformer: PartialTransformer[Byte, NonPosByte] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(NonPosByte.from(value)))

  inline given derivedNegFloatTransformer: Transformer[NegFloat, Float] with {
    override def transform(src: NegFloat): Float = src.value
  }
  inline given derivedNegFloatPartialTransformer: PartialTransformer[Float, NegFloat] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(NegFloat.from(value)))

  inline given derivedNonNegFloatTransformer: Transformer[NonNegFloat, Float] with {
    override def transform(src: NonNegFloat): Float = src.value
  }
  inline given derivedNonNegFloatPartialTransformer: PartialTransformer[Float, NonNegFloat] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(NonNegFloat.from(value)))

  inline given derivedPosFloatTransformer: Transformer[PosFloat, Float] with {
    override def transform(src: PosFloat): Float = src.value
  }
  inline given derivedPosFloatPartialTransformer: PartialTransformer[Float, PosFloat] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(PosFloat.from(value)))

  inline given derivedNonPosFloatTransformer: Transformer[NonPosFloat, Float] with {
    override def transform(src: NonPosFloat): Float = src.value
  }
  inline given derivedNonPosFloatPartialTransformer: PartialTransformer[Float, NonPosFloat] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(NonPosFloat.from(value)))

  inline given derivedNegDoubleTransformer: Transformer[NegDouble, Double] with {
    override def transform(src: NegDouble): Double = src.value
  }
  inline given derivedNegDoublePartialTransformer: PartialTransformer[Double, NegDouble] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(NegDouble.from(value)))

  inline given derivedNonNegDoubleTransformer: Transformer[NonNegDouble, Double] with {
    override def transform(src: NonNegDouble): Double = src.value
  }
  inline given derivedNonNegDoublePartialTransformer: PartialTransformer[Double, NonNegDouble] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(NonNegDouble.from(value)))

  inline given derivedPosDoubleTransformer: Transformer[PosDouble, Double] with {
    override def transform(src: PosDouble): Double = src.value
  }
  inline given derivedPosDoublePartialTransformer: PartialTransformer[Double, PosDouble] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(PosDouble.from(value)))

  inline given derivedNonPosDoubleTransformer: Transformer[NonPosDouble, Double] with {
    override def transform(src: NonPosDouble): Double = src.value
  }
  inline given derivedNonPosDoublePartialTransformer: PartialTransformer[Double, NonPosDouble] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(NonPosDouble.from(value)))

  inline given derivedNegBigIntTransformer: Transformer[NegBigInt, BigInt] with {
    override def transform(src: NegBigInt): BigInt = src.value
  }
  inline given derivedNegBigIntPartialTransformer: PartialTransformer[BigInt, NegBigInt] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(NegBigInt.from(value)))

  inline given derivedNonNegBigIntTransformer: Transformer[NonNegBigInt, BigInt] with {
    override def transform(src: NonNegBigInt): BigInt = src.value
  }
  inline given derivedNonNegBigIntPartialTransformer: PartialTransformer[BigInt, NonNegBigInt] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(NonNegBigInt.from(value)))

  inline given derivedPosBigIntTransformer: Transformer[PosBigInt, BigInt] with {
    override def transform(src: PosBigInt): BigInt = src.value
  }
  inline given derivedPosBigIntPartialTransformer: PartialTransformer[BigInt, PosBigInt] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(PosBigInt.from(value)))

  inline given derivedNonPosBigIntTransformer: Transformer[NonPosBigInt, BigInt] with {
    override def transform(src: NonPosBigInt): BigInt = src.value
  }
  inline given derivedNonPosBigIntPartialTransformer: PartialTransformer[BigInt, NonPosBigInt] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(NonPosBigInt.from(value)))

  inline given derivedNegBigDecimalTransformer: Transformer[NegBigDecimal, BigDecimal] with {
    override def transform(src: NegBigDecimal): BigDecimal = src.value
  }
  inline given derivedNegBigDecimalPartialTransformer: PartialTransformer[BigDecimal, NegBigDecimal] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(NegBigDecimal.from(value)))

  inline given derivedNonNegBigDecimalTransformer: Transformer[NonNegBigDecimal, BigDecimal] with {
    override def transform(src: NonNegBigDecimal): BigDecimal = src.value
  }
  inline given derivedNonNegBigDecimalPartialTransformer: PartialTransformer[BigDecimal, NonNegBigDecimal] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(NonNegBigDecimal.from(value)))

  inline given derivedPosBigDecimalTransformer: Transformer[PosBigDecimal, BigDecimal] with {
    override def transform(src: PosBigDecimal): BigDecimal = src.value
  }
  inline given derivedPosBigDecimalPartialTransformer: PartialTransformer[BigDecimal, PosBigDecimal] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(PosBigDecimal.from(value)))

  inline given derivedNonPosBigDecimalTransformer: Transformer[NonPosBigDecimal, BigDecimal] with {
    override def transform(src: NonPosBigDecimal): BigDecimal = src.value
  }
  inline given derivedNonPosBigDecimalPartialTransformer: PartialTransformer[BigDecimal, NonPosBigDecimal] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(NonPosBigDecimal.from(value)))

}
object numeric extends numeric
