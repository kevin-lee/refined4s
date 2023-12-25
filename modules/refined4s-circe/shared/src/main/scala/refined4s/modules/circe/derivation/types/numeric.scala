package refined4s.modules.circe.derivation.types

import io.circe.{Decoder, Encoder}
import refined4s.types.all.*

/** @author Kevin Lee
  * @since 2023-12-25
  */
trait numeric {

  inline given derivedNegIntEncoder: Encoder[NegInt] = Encoder[Int].contramap[NegInt](_.value)
  inline given derivedNegIntDecoder: Decoder[NegInt] = Decoder[Int].emap(NegInt.from)

  inline given derivedNonNegIntEncoder: Encoder[NonNegInt] = Encoder[Int].contramap[NonNegInt](_.value)
  inline given derivedNonNegIntDecoder: Decoder[NonNegInt] = Decoder[Int].emap(NonNegInt.from)

  inline given derivedPosIntEncoder: Encoder[PosInt] = Encoder[Int].contramap[PosInt](_.value)
  inline given derivedPosIntDecoder: Decoder[PosInt] = Decoder[Int].emap(PosInt.from)

  inline given derivedNonPosIntEncoder: Encoder[NonPosInt] = Encoder[Int].contramap[NonPosInt](_.value)
  inline given derivedNonPosIntDecoder: Decoder[NonPosInt] = Decoder[Int].emap(NonPosInt.from)

  inline given derivedNegLongEncoder: Encoder[NegLong] = Encoder[Long].contramap[NegLong](_.value)
  inline given derivedNegLongDecoder: Decoder[NegLong] = Decoder[Long].emap(NegLong.from)

  inline given derivedNonNegLongEncoder: Encoder[NonNegLong] = Encoder[Long].contramap[NonNegLong](_.value)
  inline given derivedNonNegLongDecoder: Decoder[NonNegLong] = Decoder[Long].emap(NonNegLong.from)

  inline given derivedPosLongEncoder: Encoder[PosLong] = Encoder[Long].contramap[PosLong](_.value)
  inline given derivedPosLongDecoder: Decoder[PosLong] = Decoder[Long].emap(PosLong.from)

  inline given derivedNonPosLongEncoder: Encoder[NonPosLong] = Encoder[Long].contramap[NonPosLong](_.value)
  inline given derivedNonPosLongDecoder: Decoder[NonPosLong] = Decoder[Long].emap(NonPosLong.from)

  inline given derivedNegShortEncoder: Encoder[NegShort] = Encoder[Short].contramap[NegShort](_.value)
  inline given derivedNegShortDecoder: Decoder[NegShort] = Decoder[Short].emap(NegShort.from)

  inline given derivedNonNegShortEncoder: Encoder[NonNegShort] = Encoder[Short].contramap[NonNegShort](_.value)
  inline given derivedNonNegShortDecoder: Decoder[NonNegShort] = Decoder[Short].emap(NonNegShort.from)

  inline given derivedPosShortEncoder: Encoder[PosShort] = Encoder[Short].contramap[PosShort](_.value)
  inline given derivedPosShortDecoder: Decoder[PosShort] = Decoder[Short].emap(PosShort.from)

  inline given derivedNonPosShortEncoder: Encoder[NonPosShort] = Encoder[Short].contramap[NonPosShort](_.value)
  inline given derivedNonPosShortDecoder: Decoder[NonPosShort] = Decoder[Short].emap(NonPosShort.from)

  inline given derivedNegByteEncoder: Encoder[NegByte] = Encoder[Byte].contramap[NegByte](_.value)
  inline given derivedNegByteDecoder: Decoder[NegByte] = Decoder[Byte].emap(NegByte.from)

  inline given derivedNonNegByteEncoder: Encoder[NonNegByte] = Encoder[Byte].contramap[NonNegByte](_.value)
  inline given derivedNonNegByteDecoder: Decoder[NonNegByte] = Decoder[Byte].emap(NonNegByte.from)

  inline given derivedPosByteEncoder: Encoder[PosByte] = Encoder[Byte].contramap[PosByte](_.value)
  inline given derivedPosByteDecoder: Decoder[PosByte] = Decoder[Byte].emap(PosByte.from)

  inline given derivedNonPosByteEncoder: Encoder[NonPosByte] = Encoder[Byte].contramap[NonPosByte](_.value)
  inline given derivedNonPosByteDecoder: Decoder[NonPosByte] = Decoder[Byte].emap(NonPosByte.from)

  inline given derivedNegFloatEncoder: Encoder[NegFloat] = Encoder[Float].contramap[NegFloat](_.value)
  inline given derivedNegFloatDecoder: Decoder[NegFloat] = Decoder[Float].emap(NegFloat.from)

  inline given derivedNonNegFloatEncoder: Encoder[NonNegFloat] = Encoder[Float].contramap[NonNegFloat](_.value)
  inline given derivedNonNegFloatDecoder: Decoder[NonNegFloat] = Decoder[Float].emap(NonNegFloat.from)

  inline given derivedPosFloatEncoder: Encoder[PosFloat] = Encoder[Float].contramap[PosFloat](_.value)
  inline given derivedPosFloatDecoder: Decoder[PosFloat] = Decoder[Float].emap(PosFloat.from)

  inline given derivedNonPosFloatEncoder: Encoder[NonPosFloat] = Encoder[Float].contramap[NonPosFloat](_.value)
  inline given derivedNonPosFloatDecoder: Decoder[NonPosFloat] = Decoder[Float].emap(NonPosFloat.from)

  inline given derivedNegDoubleEncoder: Encoder[NegDouble] = Encoder[Double].contramap[NegDouble](_.value)
  inline given derivedNegDoubleDecoder: Decoder[NegDouble] = Decoder[Double].emap(NegDouble.from)

  inline given derivedNonNegDoubleEncoder: Encoder[NonNegDouble] = Encoder[Double].contramap[NonNegDouble](_.value)
  inline given derivedNonNegDoubleDecoder: Decoder[NonNegDouble] = Decoder[Double].emap(NonNegDouble.from)

  inline given derivedPosDoubleEncoder: Encoder[PosDouble] = Encoder[Double].contramap[PosDouble](_.value)
  inline given derivedPosDoubleDecoder: Decoder[PosDouble] = Decoder[Double].emap(PosDouble.from)

  inline given derivedNonPosDoubleEncoder: Encoder[NonPosDouble] = Encoder[Double].contramap[NonPosDouble](_.value)
  inline given derivedNonPosDoubleDecoder: Decoder[NonPosDouble] = Decoder[Double].emap(NonPosDouble.from)

  inline given derivedNegBigIntEncoder: Encoder[NegBigInt] = Encoder[BigInt].contramap[NegBigInt](_.value)
  inline given derivedNegBigIntDecoder: Decoder[NegBigInt] = Decoder[BigInt].emap(NegBigInt.from)

  inline given derivedNonNegBigIntEncoder: Encoder[NonNegBigInt] = Encoder[BigInt].contramap[NonNegBigInt](_.value)
  inline given derivedNonNegBigIntDecoder: Decoder[NonNegBigInt] = Decoder[BigInt].emap(NonNegBigInt.from)

  inline given derivedPosBigIntEncoder: Encoder[PosBigInt] = Encoder[BigInt].contramap[PosBigInt](_.value)
  inline given derivedPosBigIntDecoder: Decoder[PosBigInt] = Decoder[BigInt].emap(PosBigInt.from)

  inline given derivedNonPosBigIntEncoder: Encoder[NonPosBigInt] = Encoder[BigInt].contramap[NonPosBigInt](_.value)
  inline given derivedNonPosBigIntDecoder: Decoder[NonPosBigInt] = Decoder[BigInt].emap(NonPosBigInt.from)

  inline given derivedNegBigDecimalEncoder: Encoder[NegBigDecimal] = Encoder[BigDecimal].contramap[NegBigDecimal](_.value)
  inline given derivedNegBigDecimalDecoder: Decoder[NegBigDecimal] = Decoder[BigDecimal].emap(NegBigDecimal.from)

  inline given derivedNonNegBigDecimalEncoder: Encoder[NonNegBigDecimal] = Encoder[BigDecimal].contramap[NonNegBigDecimal](_.value)
  inline given derivedNonNegBigDecimalDecoder: Decoder[NonNegBigDecimal] = Decoder[BigDecimal].emap(NonNegBigDecimal.from)

  inline given derivedPosBigDecimalEncoder: Encoder[PosBigDecimal] = Encoder[BigDecimal].contramap[PosBigDecimal](_.value)
  inline given derivedPosBigDecimalDecoder: Decoder[PosBigDecimal] = Decoder[BigDecimal].emap(PosBigDecimal.from)

  inline given derivedNonPosBigDecimalEncoder: Encoder[NonPosBigDecimal] = Encoder[BigDecimal].contramap[NonPosBigDecimal](_.value)
  inline given derivedNonPosBigDecimalDecoder: Decoder[NonPosBigDecimal] = Decoder[BigDecimal].emap(NonPosBigDecimal.from)

}
