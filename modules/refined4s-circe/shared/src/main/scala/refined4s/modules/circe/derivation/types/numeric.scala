package refined4s.modules.circe.derivation.types

import io.circe.{Decoder, Encoder, KeyDecoder, KeyEncoder}
import refined4s.types
import refined4s.types.all.*

/** @author Kevin Lee
  * @since 2023-12-25
  */
trait numeric {

  /* NegInt */
  given derivedNegIntEncoder: Encoder[NegInt] = numeric.derivedNegIntEncoder
  given derivedNegIntDecoder: Decoder[NegInt] = numeric.derivedNegIntDecoder

  given derivedNegIntKeyEncoder: KeyEncoder[NegInt] = numeric.derivedNegIntKeyEncoder
  given derivedNegIntKeyDecoder: KeyDecoder[NegInt] = numeric.derivedNegIntKeyDecoder

  /* NonNegInt */
  given derivedNonNegIntEncoder: Encoder[NonNegInt] = numeric.derivedNonNegIntEncoder
  given derivedNonNegIntDecoder: Decoder[NonNegInt] = numeric.derivedNonNegIntDecoder

  given derivedNonNegIntKeyEncoder: KeyEncoder[NonNegInt] = numeric.derivedNonNegIntKeyEncoder
  given derivedNonNegIntKeyDecoder: KeyDecoder[NonNegInt] = numeric.derivedNonNegIntKeyDecoder

  /* PosInt */
  given derivedPosIntEncoder: Encoder[PosInt] = numeric.derivedPosIntEncoder
  given derivedPosIntDecoder: Decoder[PosInt] = numeric.derivedPosIntDecoder

  given derivedPosIntKeyEncoder: KeyEncoder[PosInt] = numeric.derivedPosIntKeyEncoder
  given derivedPosIntKeyDecoder: KeyDecoder[PosInt] = numeric.derivedPosIntKeyDecoder

  /* NonPosInt */
  given derivedNonPosIntEncoder: Encoder[NonPosInt] = numeric.derivedNonPosIntEncoder
  given derivedNonPosIntDecoder: Decoder[NonPosInt] = numeric.derivedNonPosIntDecoder

  given derivedNonPosIntKeyEncoder: KeyEncoder[NonPosInt] = numeric.derivedNonPosIntKeyEncoder
  given derivedNonPosIntKeyDecoder: KeyDecoder[NonPosInt] = numeric.derivedNonPosIntKeyDecoder

  /* NegLong */
  given derivedNegLongEncoder: Encoder[NegLong] = numeric.derivedNegLongEncoder
  given derivedNegLongDecoder: Decoder[NegLong] = numeric.derivedNegLongDecoder

  given derivedNegLongKeyEncoder: KeyEncoder[NegLong] = numeric.derivedNegLongKeyEncoder
  given derivedNegLongKeyDecoder: KeyDecoder[NegLong] = numeric.derivedNegLongKeyDecoder

  /* NonNegLong */
  given derivedNonNegLongEncoder: Encoder[NonNegLong] = numeric.derivedNonNegLongEncoder
  given derivedNonNegLongDecoder: Decoder[NonNegLong] = numeric.derivedNonNegLongDecoder

  given derivedNonNegLongKeyEncoder: KeyEncoder[NonNegLong] = numeric.derivedNonNegLongKeyEncoder
  given derivedNonNegLongKeyDecoder: KeyDecoder[NonNegLong] = numeric.derivedNonNegLongKeyDecoder

  /* PosLong */
  given derivedPosLongEncoder: Encoder[PosLong] = numeric.derivedPosLongEncoder
  given derivedPosLongDecoder: Decoder[PosLong] = numeric.derivedPosLongDecoder

  given derivedPosLongKeyEncoder: KeyEncoder[PosLong] = numeric.derivedPosLongKeyEncoder
  given derivedPosLongKeyDecoder: KeyDecoder[PosLong] = numeric.derivedPosLongKeyDecoder

  /* NonPosLong */
  given derivedNonPosLongEncoder: Encoder[NonPosLong] = numeric.derivedNonPosLongEncoder
  given derivedNonPosLongDecoder: Decoder[NonPosLong] = numeric.derivedNonPosLongDecoder

  given derivedNonPosLongKeyEncoder: KeyEncoder[NonPosLong] = numeric.derivedNonPosLongKeyEncoder
  given derivedNonPosLongKeyDecoder: KeyDecoder[NonPosLong] = numeric.derivedNonPosLongKeyDecoder

  /* NegShort */
  given derivedNegShortEncoder: Encoder[NegShort] = numeric.derivedNegShortEncoder
  given derivedNegShortDecoder: Decoder[NegShort] = numeric.derivedNegShortDecoder

  given derivedNegShortKeyEncoder: KeyEncoder[NegShort] = numeric.derivedNegShortKeyEncoder
  given derivedNegShortKeyDecoder: KeyDecoder[NegShort] = numeric.derivedNegShortKeyDecoder

  /* NonNegShort */
  given derivedNonNegShortEncoder: Encoder[NonNegShort] = numeric.derivedNonNegShortEncoder
  given derivedNonNegShortDecoder: Decoder[NonNegShort] = numeric.derivedNonNegShortDecoder

  given derivedNonNegShortKeyEncoder: KeyEncoder[NonNegShort] = numeric.derivedNonNegShortKeyEncoder
  given derivedNonNegShortKeyDecoder: KeyDecoder[NonNegShort] = numeric.derivedNonNegShortKeyDecoder

  /* PosShort */
  given derivedPosShortEncoder: Encoder[PosShort] = numeric.derivedPosShortEncoder
  given derivedPosShortDecoder: Decoder[PosShort] = numeric.derivedPosShortDecoder

  given derivedPosShortKeyEncoder: KeyEncoder[PosShort] = numeric.derivedPosShortKeyEncoder
  given derivedPosShortKeyDecoder: KeyDecoder[PosShort] = numeric.derivedPosShortKeyDecoder

  /* NonPosShort */
  given derivedNonPosShortEncoder: Encoder[NonPosShort] = numeric.derivedNonPosShortEncoder
  given derivedNonPosShortDecoder: Decoder[NonPosShort] = numeric.derivedNonPosShortDecoder

  given derivedNonPosShortKeyEncoder: KeyEncoder[NonPosShort] = numeric.derivedNonPosShortKeyEncoder
  given derivedNonPosShortKeyDecoder: KeyDecoder[NonPosShort] = numeric.derivedNonPosShortKeyDecoder

  /* NegByte */
  given derivedNegByteEncoder: Encoder[NegByte] = numeric.derivedNegByteEncoder
  given derivedNegByteDecoder: Decoder[NegByte] = numeric.derivedNegByteDecoder

  given derivedNegByteKeyEncoder: KeyEncoder[NegByte] = numeric.derivedNegByteKeyEncoder
  given derivedNegByteKeyDecoder: KeyDecoder[NegByte] = numeric.derivedNegByteKeyDecoder

  /* NonNegByte */
  given derivedNonNegByteEncoder: Encoder[NonNegByte] = numeric.derivedNonNegByteEncoder
  given derivedNonNegByteDecoder: Decoder[NonNegByte] = numeric.derivedNonNegByteDecoder

  given derivedNonNegByteKeyEncoder: KeyEncoder[NonNegByte] = numeric.derivedNonNegByteKeyEncoder
  given derivedNonNegByteKeyDecoder: KeyDecoder[NonNegByte] = numeric.derivedNonNegByteKeyDecoder

  /* PosByte */
  given derivedPosByteEncoder: Encoder[PosByte] = numeric.derivedPosByteEncoder
  given derivedPosByteDecoder: Decoder[PosByte] = numeric.derivedPosByteDecoder

  given derivedPosByteKeyEncoder: KeyEncoder[PosByte] = numeric.derivedPosByteKeyEncoder
  given derivedPosByteKeyDecoder: KeyDecoder[PosByte] = numeric.derivedPosByteKeyDecoder

  /* NonPosByte */
  given derivedNonPosByteEncoder: Encoder[NonPosByte] = numeric.derivedNonPosByteEncoder
  given derivedNonPosByteDecoder: Decoder[NonPosByte] = numeric.derivedNonPosByteDecoder

  given derivedNonPosByteKeyEncoder: KeyEncoder[NonPosByte] = numeric.derivedNonPosByteKeyEncoder
  given derivedNonPosByteKeyDecoder: KeyDecoder[NonPosByte] = numeric.derivedNonPosByteKeyDecoder

  /* NegFloat */
  given derivedNegFloatEncoder: Encoder[NegFloat] = numeric.derivedNegFloatEncoder
  given derivedNegFloatDecoder: Decoder[NegFloat] = numeric.derivedNegFloatDecoder

  /* NonNegFloat */
  given derivedNonNegFloatEncoder: Encoder[NonNegFloat] = numeric.derivedNonNegFloatEncoder
  given derivedNonNegFloatDecoder: Decoder[NonNegFloat] = numeric.derivedNonNegFloatDecoder

  /* PosFloat */
  given derivedPosFloatEncoder: Encoder[PosFloat] = numeric.derivedPosFloatEncoder
  given derivedPosFloatDecoder: Decoder[PosFloat] = numeric.derivedPosFloatDecoder

  /* NonPosFloat */
  given derivedNonPosFloatEncoder: Encoder[NonPosFloat] = numeric.derivedNonPosFloatEncoder
  given derivedNonPosFloatDecoder: Decoder[NonPosFloat] = numeric.derivedNonPosFloatDecoder

  /* NegDouble */
  given derivedNegDoubleEncoder: Encoder[NegDouble] = numeric.derivedNegDoubleEncoder
  given derivedNegDoubleDecoder: Decoder[NegDouble] = numeric.derivedNegDoubleDecoder

  /* NonNegDouble */
  given derivedNonNegDoubleEncoder: Encoder[NonNegDouble] = numeric.derivedNonNegDoubleEncoder
  given derivedNonNegDoubleDecoder: Decoder[NonNegDouble] = numeric.derivedNonNegDoubleDecoder

  /* PosDouble */
  given derivedPosDoubleEncoder: Encoder[PosDouble] = numeric.derivedPosDoubleEncoder
  given derivedPosDoubleDecoder: Decoder[PosDouble] = numeric.derivedPosDoubleDecoder

  /* NonPosDouble */
  given derivedNonPosDoubleEncoder: Encoder[NonPosDouble] = numeric.derivedNonPosDoubleEncoder
  given derivedNonPosDoubleDecoder: Decoder[NonPosDouble] = numeric.derivedNonPosDoubleDecoder

  /* NegBigInt */
  given derivedNegBigIntEncoder: Encoder[NegBigInt] = numeric.derivedNegBigIntEncoder
  given derivedNegBigIntDecoder: Decoder[NegBigInt] = numeric.derivedNegBigIntDecoder

  given derivedNegBigIntKeyEncoder: KeyEncoder[NegBigInt] = numeric.derivedNegBigIntKeyEncoder
  given derivedNegBigIntKeyDecoder: KeyDecoder[NegBigInt] = numeric.derivedNegBigIntKeyDecoder

  /* NonNegBigInt */
  given derivedNonNegBigIntEncoder: Encoder[NonNegBigInt] = numeric.derivedNonNegBigIntEncoder
  given derivedNonNegBigIntDecoder: Decoder[NonNegBigInt] = numeric.derivedNonNegBigIntDecoder

  given derivedNonNegBigIntKeyEncoder: KeyEncoder[NonNegBigInt] = numeric.derivedNonNegBigIntKeyEncoder
  given derivedNonNegBigIntKeyDecoder: KeyDecoder[NonNegBigInt] = numeric.derivedNonNegBigIntKeyDecoder

  /* PosBigInt */
  given derivedPosBigIntEncoder: Encoder[PosBigInt] = numeric.derivedPosBigIntEncoder
  given derivedPosBigIntDecoder: Decoder[PosBigInt] = numeric.derivedPosBigIntDecoder

  given derivedPosBigIntKeyEncoder: KeyEncoder[PosBigInt] = numeric.derivedPosBigIntKeyEncoder
  given derivedPosBigIntKeyDecoder: KeyDecoder[PosBigInt] = numeric.derivedPosBigIntKeyDecoder

  /* NonPosBigInt */
  given derivedNonPosBigIntEncoder: Encoder[NonPosBigInt] = numeric.derivedNonPosBigIntEncoder
  given derivedNonPosBigIntDecoder: Decoder[NonPosBigInt] = numeric.derivedNonPosBigIntDecoder

  given derivedNonPosBigIntKeyEncoder: KeyEncoder[NonPosBigInt] = numeric.derivedNonPosBigIntKeyEncoder
  given derivedNonPosBigIntKeyDecoder: KeyDecoder[NonPosBigInt] = numeric.derivedNonPosBigIntKeyDecoder

  /* NegBigDecimal */
  given derivedNegBigDecimalEncoder: Encoder[NegBigDecimal] = numeric.derivedNegBigDecimalEncoder
  given derivedNegBigDecimalDecoder: Decoder[NegBigDecimal] = numeric.derivedNegBigDecimalDecoder

  /* NonNegBigDecimal */
  given derivedNonNegBigDecimalEncoder: Encoder[NonNegBigDecimal] = numeric.derivedNonNegBigDecimalEncoder
  given derivedNonNegBigDecimalDecoder: Decoder[NonNegBigDecimal] = numeric.derivedNonNegBigDecimalDecoder

  /* PosBigDecimal */
  given derivedPosBigDecimalEncoder: Encoder[PosBigDecimal] = numeric.derivedPosBigDecimalEncoder
  given derivedPosBigDecimalDecoder: Decoder[PosBigDecimal] = numeric.derivedPosBigDecimalDecoder

  /* NonPosBigDecimal */
  given derivedNonPosBigDecimalEncoder: Encoder[NonPosBigDecimal] = numeric.derivedNonPosBigDecimalEncoder
  given derivedNonPosBigDecimalDecoder: Decoder[NonPosBigDecimal] = numeric.derivedNonPosBigDecimalDecoder

}
object numeric {

  /* NegInt */
  given derivedNegIntEncoder: Encoder[NegInt] = Encoder[Int].contramap[NegInt](_.value)
  given derivedNegIntDecoder: Decoder[NegInt] = Decoder[Int].emap(NegInt.from)

  given derivedNegIntKeyEncoder: KeyEncoder[NegInt] = KeyEncoder[Int].contramap[NegInt](_.value)
  given derivedNegIntKeyDecoder: KeyDecoder[NegInt] with {
    override def apply(key: String): Option[NegInt] =
      KeyDecoder[Int].apply(key).flatMap(NegInt.from(_).toOption)
  }

  /* NonNegInt */
  given derivedNonNegIntEncoder: Encoder[NonNegInt] = Encoder[Int].contramap[NonNegInt](_.value)
  given derivedNonNegIntDecoder: Decoder[NonNegInt] = Decoder[Int].emap(NonNegInt.from)

  given derivedNonNegIntKeyEncoder: KeyEncoder[NonNegInt] = KeyEncoder[Int].contramap[NonNegInt](_.value)
  given derivedNonNegIntKeyDecoder: KeyDecoder[NonNegInt] with {
    override def apply(key: String): Option[NonNegInt] =
      KeyDecoder[Int].apply(key).flatMap(NonNegInt.from(_).toOption)
  }

  /* PosInt */
  given derivedPosIntEncoder: Encoder[PosInt] = Encoder[Int].contramap[PosInt](_.value)
  given derivedPosIntDecoder: Decoder[PosInt] = Decoder[Int].emap(PosInt.from)

  given derivedPosIntKeyEncoder: KeyEncoder[PosInt] = KeyEncoder[Int].contramap[PosInt](_.value)
  given derivedPosIntKeyDecoder: KeyDecoder[PosInt] with {
    override def apply(key: String): Option[PosInt] =
      KeyDecoder[Int].apply(key).flatMap(PosInt.from(_).toOption)
  }

  /* NonPosInt */
  given derivedNonPosIntEncoder: Encoder[NonPosInt] = Encoder[Int].contramap[NonPosInt](_.value)
  given derivedNonPosIntDecoder: Decoder[NonPosInt] = Decoder[Int].emap(NonPosInt.from)

  given derivedNonPosIntKeyEncoder: KeyEncoder[NonPosInt] = KeyEncoder[Int].contramap[NonPosInt](_.value)
  given derivedNonPosIntKeyDecoder: KeyDecoder[NonPosInt] with {
    override def apply(key: String): Option[NonPosInt] =
      KeyDecoder[Int].apply(key).flatMap(NonPosInt.from(_).toOption)
  }

  /* NegLong */
  given derivedNegLongEncoder: Encoder[NegLong] = Encoder[Long].contramap[NegLong](_.value)
  given derivedNegLongDecoder: Decoder[NegLong] = Decoder[Long].emap(NegLong.from)

  given derivedNegLongKeyEncoder: KeyEncoder[NegLong] = KeyEncoder[Long].contramap[NegLong](_.value)
  given derivedNegLongKeyDecoder: KeyDecoder[NegLong] with {
    override def apply(key: String): Option[NegLong] =
      KeyDecoder[Long].apply(key).flatMap(NegLong.from(_).toOption)
  }

  /* NonNegLong */
  given derivedNonNegLongEncoder: Encoder[NonNegLong] = Encoder[Long].contramap[NonNegLong](_.value)
  given derivedNonNegLongDecoder: Decoder[NonNegLong] = Decoder[Long].emap(NonNegLong.from)

  given derivedNonNegLongKeyEncoder: KeyEncoder[NonNegLong] = KeyEncoder[Long].contramap[NonNegLong](_.value)
  given derivedNonNegLongKeyDecoder: KeyDecoder[NonNegLong] with {
    override def apply(key: String): Option[NonNegLong] =
      KeyDecoder[Long].apply(key).flatMap(NonNegLong.from(_).toOption)
  }

  /* PosLong */
  given derivedPosLongEncoder: Encoder[PosLong] = Encoder[Long].contramap[PosLong](_.value)
  given derivedPosLongDecoder: Decoder[PosLong] = Decoder[Long].emap(PosLong.from)

  given derivedPosLongKeyEncoder: KeyEncoder[PosLong] = KeyEncoder[Long].contramap[PosLong](_.value)
  given derivedPosLongKeyDecoder: KeyDecoder[PosLong] with {
    override def apply(key: String): Option[PosLong] =
      KeyDecoder[Long].apply(key).flatMap(PosLong.from(_).toOption)
  }

  /* NonPosLong */
  given derivedNonPosLongEncoder: Encoder[NonPosLong] = Encoder[Long].contramap[NonPosLong](_.value)
  given derivedNonPosLongDecoder: Decoder[NonPosLong] = Decoder[Long].emap(NonPosLong.from)

  given derivedNonPosLongKeyEncoder: KeyEncoder[NonPosLong] = KeyEncoder[Long].contramap[NonPosLong](_.value)
  given derivedNonPosLongKeyDecoder: KeyDecoder[NonPosLong] with {
    override def apply(key: String): Option[NonPosLong] =
      KeyDecoder[Long].apply(key).flatMap(NonPosLong.from(_).toOption)
  }

  /* NegShort */
  given derivedNegShortEncoder: Encoder[NegShort] = Encoder[Short].contramap[NegShort](_.value)
  given derivedNegShortDecoder: Decoder[NegShort] = Decoder[Short].emap(NegShort.from)

  given derivedNegShortKeyEncoder: KeyEncoder[NegShort] = KeyEncoder[Short].contramap[NegShort](_.value)
  given derivedNegShortKeyDecoder: KeyDecoder[NegShort] with {
    override def apply(key: String): Option[NegShort] =
      KeyDecoder[Short].apply(key).flatMap(NegShort.from(_).toOption)
  }

  /* NonNegShort */
  given derivedNonNegShortEncoder: Encoder[NonNegShort] = Encoder[Short].contramap[NonNegShort](_.value)
  given derivedNonNegShortDecoder: Decoder[NonNegShort] = Decoder[Short].emap(NonNegShort.from)

  given derivedNonNegShortKeyEncoder: KeyEncoder[NonNegShort] = KeyEncoder[Short].contramap[NonNegShort](_.value)
  given derivedNonNegShortKeyDecoder: KeyDecoder[NonNegShort] with {
    override def apply(key: String): Option[NonNegShort] =
      KeyDecoder[Short].apply(key).flatMap(NonNegShort.from(_).toOption)
  }

  /* PosShort */
  given derivedPosShortEncoder: Encoder[PosShort] = Encoder[Short].contramap[PosShort](_.value)
  given derivedPosShortDecoder: Decoder[PosShort] = Decoder[Short].emap(PosShort.from)

  given derivedPosShortKeyEncoder: KeyEncoder[PosShort] = KeyEncoder[Short].contramap[PosShort](_.value)
  given derivedPosShortKeyDecoder: KeyDecoder[PosShort] with {
    override def apply(key: String): Option[PosShort] =
      KeyDecoder[Short].apply(key).flatMap(PosShort.from(_).toOption)
  }

  /* NonPosShort */
  given derivedNonPosShortEncoder: Encoder[NonPosShort] = Encoder[Short].contramap[NonPosShort](_.value)
  given derivedNonPosShortDecoder: Decoder[NonPosShort] = Decoder[Short].emap(NonPosShort.from)

  given derivedNonPosShortKeyEncoder: KeyEncoder[NonPosShort] = KeyEncoder[Short].contramap[NonPosShort](_.value)
  given derivedNonPosShortKeyDecoder: KeyDecoder[NonPosShort] with {
    override def apply(key: String): Option[NonPosShort] =
      KeyDecoder[Short].apply(key).flatMap(NonPosShort.from(_).toOption)
  }

  /* NegByte */
  given derivedNegByteEncoder: Encoder[NegByte] = Encoder[Byte].contramap[NegByte](_.value)
  given derivedNegByteDecoder: Decoder[NegByte] = Decoder[Byte].emap(NegByte.from)

  given derivedNegByteKeyEncoder: KeyEncoder[NegByte] = KeyEncoder[Byte].contramap[NegByte](_.value)
  given derivedNegByteKeyDecoder: KeyDecoder[NegByte] with {
    override def apply(key: String): Option[NegByte] =
      KeyDecoder[Byte].apply(key).flatMap(NegByte.from(_).toOption)
  }

  /* NonNegByte */
  given derivedNonNegByteEncoder: Encoder[NonNegByte] = Encoder[Byte].contramap[NonNegByte](_.value)
  given derivedNonNegByteDecoder: Decoder[NonNegByte] = Decoder[Byte].emap(NonNegByte.from)

  given derivedNonNegByteKeyEncoder: KeyEncoder[NonNegByte] = KeyEncoder[Byte].contramap[NonNegByte](_.value)
  given derivedNonNegByteKeyDecoder: KeyDecoder[NonNegByte] with {
    override def apply(key: String): Option[NonNegByte] =
      KeyDecoder[Byte].apply(key).flatMap(NonNegByte.from(_).toOption)
  }

  /* PosByte */
  given derivedPosByteEncoder: Encoder[PosByte] = Encoder[Byte].contramap[PosByte](_.value)
  given derivedPosByteDecoder: Decoder[PosByte] = Decoder[Byte].emap(PosByte.from)

  given derivedPosByteKeyEncoder: KeyEncoder[PosByte] = KeyEncoder[Byte].contramap[PosByte](_.value)
  given derivedPosByteKeyDecoder: KeyDecoder[PosByte] with {
    override def apply(key: String): Option[PosByte] =
      KeyDecoder[Byte].apply(key).flatMap(PosByte.from(_).toOption)
  }

  /* NonPosByte */
  given derivedNonPosByteEncoder: Encoder[NonPosByte] = Encoder[Byte].contramap[NonPosByte](_.value)
  given derivedNonPosByteDecoder: Decoder[NonPosByte] = Decoder[Byte].emap(NonPosByte.from)

  given derivedNonPosByteKeyEncoder: KeyEncoder[NonPosByte] = KeyEncoder[Byte].contramap[NonPosByte](_.value)
  given derivedNonPosByteKeyDecoder: KeyDecoder[NonPosByte] with {
    override def apply(key: String): Option[NonPosByte] =
      KeyDecoder[Byte].apply(key).flatMap(NonPosByte.from(_).toOption)
  }

  /* NegFloat */
  given derivedNegFloatEncoder: Encoder[NegFloat] = Encoder[Float].contramap[NegFloat](_.value)
  given derivedNegFloatDecoder: Decoder[NegFloat] = Decoder[Float].emap(NegFloat.from)

  /* NonNegFloat */
  given derivedNonNegFloatEncoder: Encoder[NonNegFloat] = Encoder[Float].contramap[NonNegFloat](_.value)
  given derivedNonNegFloatDecoder: Decoder[NonNegFloat] = Decoder[Float].emap(NonNegFloat.from)

  /* PosFloat */
  given derivedPosFloatEncoder: Encoder[PosFloat] = Encoder[Float].contramap[PosFloat](_.value)
  given derivedPosFloatDecoder: Decoder[PosFloat] = Decoder[Float].emap(PosFloat.from)

  /* NonPosFloat */
  given derivedNonPosFloatEncoder: Encoder[NonPosFloat] = Encoder[Float].contramap[NonPosFloat](_.value)
  given derivedNonPosFloatDecoder: Decoder[NonPosFloat] = Decoder[Float].emap(NonPosFloat.from)

  /* NegDouble */
  given derivedNegDoubleEncoder: Encoder[NegDouble] = Encoder[Double].contramap[NegDouble](_.value)
  given derivedNegDoubleDecoder: Decoder[NegDouble] = Decoder[Double].emap(NegDouble.from)

  /* NonNegDouble */
  given derivedNonNegDoubleEncoder: Encoder[NonNegDouble] = Encoder[Double].contramap[NonNegDouble](_.value)
  given derivedNonNegDoubleDecoder: Decoder[NonNegDouble] = Decoder[Double].emap(NonNegDouble.from)

  /* PosDouble */
  given derivedPosDoubleEncoder: Encoder[PosDouble] = Encoder[Double].contramap[PosDouble](_.value)
  given derivedPosDoubleDecoder: Decoder[PosDouble] = Decoder[Double].emap(PosDouble.from)

  /* NonPosDouble */
  given derivedNonPosDoubleEncoder: Encoder[NonPosDouble] = Encoder[Double].contramap[NonPosDouble](_.value)
  given derivedNonPosDoubleDecoder: Decoder[NonPosDouble] = Decoder[Double].emap(NonPosDouble.from)

  /* NegBigInt */
  given derivedNegBigIntEncoder: Encoder[NegBigInt] = Encoder[BigInt].contramap[NegBigInt](_.value)
  given derivedNegBigIntDecoder: Decoder[NegBigInt] = Decoder[BigInt].emap(NegBigInt.from)

  given derivedNegBigIntKeyEncoder: KeyEncoder[NegBigInt] with {
    @SuppressWarnings(Array("org.wartremover.warts.ToString"))
    override def apply(key: NegBigInt): String = key.value.toString
  }
  given derivedNegBigIntKeyDecoder: KeyDecoder[NegBigInt] with {
    override def apply(key: String): Option[NegBigInt] =
      scala.util.Try(BigInt(key)).toOption.flatMap(NegBigInt.from(_).toOption)
  }

  /* NonNegBigInt */
  given derivedNonNegBigIntEncoder: Encoder[NonNegBigInt] = Encoder[BigInt].contramap[NonNegBigInt](_.value)
  given derivedNonNegBigIntDecoder: Decoder[NonNegBigInt] = Decoder[BigInt].emap(NonNegBigInt.from)

  given derivedNonNegBigIntKeyEncoder: KeyEncoder[NonNegBigInt] with {
    @SuppressWarnings(Array("org.wartremover.warts.ToString"))
    override def apply(key: NonNegBigInt): String = key.value.toString
  }
  given derivedNonNegBigIntKeyDecoder: KeyDecoder[NonNegBigInt] with {
    override def apply(key: String): Option[NonNegBigInt] =
      scala.util.Try(BigInt(key)).toOption.flatMap(NonNegBigInt.from(_).toOption)
  }

  /* PosBigInt */
  given derivedPosBigIntEncoder: Encoder[PosBigInt] = Encoder[BigInt].contramap[PosBigInt](_.value)
  given derivedPosBigIntDecoder: Decoder[PosBigInt] = Decoder[BigInt].emap(PosBigInt.from)

  given derivedPosBigIntKeyEncoder: KeyEncoder[PosBigInt] with {
    @SuppressWarnings(Array("org.wartremover.warts.ToString"))
    override def apply(key: PosBigInt): String = key.value.toString
  }
  given derivedPosBigIntKeyDecoder: KeyDecoder[PosBigInt] with {
    override def apply(key: String): Option[PosBigInt] =
      scala.util.Try(BigInt(key)).toOption.flatMap(PosBigInt.from(_).toOption)
  }

  /* NonPosBigInt */
  given derivedNonPosBigIntEncoder: Encoder[NonPosBigInt] = Encoder[BigInt].contramap[NonPosBigInt](_.value)
  given derivedNonPosBigIntDecoder: Decoder[NonPosBigInt] = Decoder[BigInt].emap(NonPosBigInt.from)

  given derivedNonPosBigIntKeyEncoder: KeyEncoder[NonPosBigInt] with {
    @SuppressWarnings(Array("org.wartremover.warts.ToString"))
    override def apply(key: NonPosBigInt): String = key.value.toString
  }
  given derivedNonPosBigIntKeyDecoder: KeyDecoder[NonPosBigInt] with {
    override def apply(key: String): Option[NonPosBigInt] =
      scala.util.Try(BigInt(key)).toOption.flatMap(NonPosBigInt.from(_).toOption)
  }

  /* NegBigDecimal */
  given derivedNegBigDecimalEncoder: Encoder[NegBigDecimal] = Encoder[BigDecimal].contramap[NegBigDecimal](_.value)
  given derivedNegBigDecimalDecoder: Decoder[NegBigDecimal] = Decoder[BigDecimal].emap(NegBigDecimal.from)

  /* NonNegBigDecimal */
  given derivedNonNegBigDecimalEncoder: Encoder[NonNegBigDecimal] = Encoder[BigDecimal].contramap[NonNegBigDecimal](_.value)
  given derivedNonNegBigDecimalDecoder: Decoder[NonNegBigDecimal] = Decoder[BigDecimal].emap(NonNegBigDecimal.from)

  /* PosBigDecimal */
  given derivedPosBigDecimalEncoder: Encoder[PosBigDecimal] = Encoder[BigDecimal].contramap[PosBigDecimal](_.value)
  given derivedPosBigDecimalDecoder: Decoder[PosBigDecimal] = Decoder[BigDecimal].emap(PosBigDecimal.from)

  /* NonPosBigDecimal */
  given derivedNonPosBigDecimalEncoder: Encoder[NonPosBigDecimal] = Encoder[BigDecimal].contramap[NonPosBigDecimal](_.value)
  given derivedNonPosBigDecimalDecoder: Decoder[NonPosBigDecimal] = Decoder[BigDecimal].emap(NonPosBigDecimal.from)

}
