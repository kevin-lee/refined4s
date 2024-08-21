package refined4s.modules.circe.derivation.types

import io.circe.{Decoder, Encoder, KeyDecoder, KeyEncoder}
import refined4s.types
import refined4s.types.all.*

/** @author Kevin Lee
  * @since 2023-12-25
  */
trait numeric {

  /* NegInt */
  inline given derivedNegIntEncoder: Encoder[NegInt] = Encoder[Int].contramap[NegInt](_.value)
  inline given derivedNegIntDecoder: Decoder[NegInt] = Decoder[Int].emap(NegInt.from)

  inline given derivedNegIntKeyEncoder: KeyEncoder[NegInt] = KeyEncoder[Int].contramap[NegInt](_.value)
  inline given derivedNegIntKeyDecoder: KeyDecoder[NegInt] with {
    override def apply(key: String): Option[NegInt] =
      KeyDecoder[Int].apply(key).flatMap(NegInt.from(_).toOption)
  }

  /* NonNegInt */
  inline given derivedNonNegIntEncoder: Encoder[NonNegInt] = Encoder[Int].contramap[NonNegInt](_.value)
  inline given derivedNonNegIntDecoder: Decoder[NonNegInt] = Decoder[Int].emap(NonNegInt.from)

  inline given derivedNonNegIntKeyEncoder: KeyEncoder[NonNegInt] = KeyEncoder[Int].contramap[NonNegInt](_.value)
  inline given derivedNonNegIntKeyDecoder: KeyDecoder[NonNegInt] with {
    override def apply(key: String): Option[NonNegInt] =
      KeyDecoder[Int].apply(key).flatMap(NonNegInt.from(_).toOption)
  }

  /* PosInt */
  inline given derivedPosIntEncoder: Encoder[PosInt] = Encoder[Int].contramap[PosInt](_.value)
  inline given derivedPosIntDecoder: Decoder[PosInt] = Decoder[Int].emap(PosInt.from)

  inline given derivedPosIntKeyEncoder: KeyEncoder[PosInt] = KeyEncoder[Int].contramap[PosInt](_.value)
  inline given derivedPosIntKeyDecoder: KeyDecoder[PosInt] with {
    override def apply(key: String): Option[PosInt] =
      KeyDecoder[Int].apply(key).flatMap(PosInt.from(_).toOption)
  }

  /* NonPosInt */
  inline given derivedNonPosIntEncoder: Encoder[NonPosInt] = Encoder[Int].contramap[NonPosInt](_.value)
  inline given derivedNonPosIntDecoder: Decoder[NonPosInt] = Decoder[Int].emap(NonPosInt.from)

  inline given derivedNonPosIntKeyEncoder: KeyEncoder[NonPosInt] = KeyEncoder[Int].contramap[NonPosInt](_.value)
  inline given derivedNonPosIntKeyDecoder: KeyDecoder[NonPosInt] with {
    override def apply(key: String): Option[NonPosInt] =
      KeyDecoder[Int].apply(key).flatMap(NonPosInt.from(_).toOption)
  }

  /* NegLong */
  inline given derivedNegLongEncoder: Encoder[NegLong] = Encoder[Long].contramap[NegLong](_.value)
  inline given derivedNegLongDecoder: Decoder[NegLong] = Decoder[Long].emap(NegLong.from)

  inline given derivedNegLongKeyEncoder: KeyEncoder[NegLong] = KeyEncoder[Long].contramap[NegLong](_.value)
  inline given derivedNegLongKeyDecoder: KeyDecoder[NegLong] with {
    override def apply(key: String): Option[NegLong] =
      KeyDecoder[Long].apply(key).flatMap(NegLong.from(_).toOption)
  }

  /* NonNegLong */
  inline given derivedNonNegLongEncoder: Encoder[NonNegLong] = Encoder[Long].contramap[NonNegLong](_.value)
  inline given derivedNonNegLongDecoder: Decoder[NonNegLong] = Decoder[Long].emap(NonNegLong.from)

  inline given derivedNonNegLongKeyEncoder: KeyEncoder[NonNegLong] = KeyEncoder[Long].contramap[NonNegLong](_.value)
  inline given derivedNonNegLongKeyDecoder: KeyDecoder[NonNegLong] with {
    override def apply(key: String): Option[NonNegLong] =
      KeyDecoder[Long].apply(key).flatMap(NonNegLong.from(_).toOption)
  }

  /* PosLong */
  inline given derivedPosLongEncoder: Encoder[PosLong] = Encoder[Long].contramap[PosLong](_.value)
  inline given derivedPosLongDecoder: Decoder[PosLong] = Decoder[Long].emap(PosLong.from)

  inline given derivedPosLongKeyEncoder: KeyEncoder[PosLong] = KeyEncoder[Long].contramap[PosLong](_.value)
  inline given derivedPosLongKeyDecoder: KeyDecoder[PosLong] with {
    override def apply(key: String): Option[PosLong] =
      KeyDecoder[Long].apply(key).flatMap(PosLong.from(_).toOption)
  }

  /* NonPosLong */
  inline given derivedNonPosLongEncoder: Encoder[NonPosLong] = Encoder[Long].contramap[NonPosLong](_.value)
  inline given derivedNonPosLongDecoder: Decoder[NonPosLong] = Decoder[Long].emap(NonPosLong.from)

  inline given derivedNonPosLongKeyEncoder: KeyEncoder[NonPosLong] = KeyEncoder[Long].contramap[NonPosLong](_.value)
  inline given derivedNonPosLongKeyDecoder: KeyDecoder[NonPosLong] with {
    override def apply(key: String): Option[NonPosLong] =
      KeyDecoder[Long].apply(key).flatMap(NonPosLong.from(_).toOption)
  }

  /* NegShort */
  inline given derivedNegShortEncoder: Encoder[NegShort] = Encoder[Short].contramap[NegShort](_.value)
  inline given derivedNegShortDecoder: Decoder[NegShort] = Decoder[Short].emap(NegShort.from)

  inline given derivedNegShortKeyEncoder: KeyEncoder[NegShort] = KeyEncoder[Short].contramap[NegShort](_.value)
  inline given derivedNegShortKeyDecoder: KeyDecoder[NegShort] with {
    override def apply(key: String): Option[NegShort] =
      KeyDecoder[Short].apply(key).flatMap(NegShort.from(_).toOption)
  }

  /* NonNegShort */
  inline given derivedNonNegShortEncoder: Encoder[NonNegShort] = Encoder[Short].contramap[NonNegShort](_.value)
  inline given derivedNonNegShortDecoder: Decoder[NonNegShort] = Decoder[Short].emap(NonNegShort.from)

  inline given derivedNonNegShortKeyEncoder: KeyEncoder[NonNegShort] = KeyEncoder[Short].contramap[NonNegShort](_.value)
  inline given derivedNonNegShortKeyDecoder: KeyDecoder[NonNegShort] with {
    override def apply(key: String): Option[NonNegShort] =
      KeyDecoder[Short].apply(key).flatMap(NonNegShort.from(_).toOption)
  }

  /* PosShort */
  inline given derivedPosShortEncoder: Encoder[PosShort] = Encoder[Short].contramap[PosShort](_.value)
  inline given derivedPosShortDecoder: Decoder[PosShort] = Decoder[Short].emap(PosShort.from)

  inline given derivedPosShortKeyEncoder: KeyEncoder[PosShort] = KeyEncoder[Short].contramap[PosShort](_.value)
  inline given derivedPosShortKeyDecoder: KeyDecoder[PosShort] with {
    override def apply(key: String): Option[PosShort] =
      KeyDecoder[Short].apply(key).flatMap(PosShort.from(_).toOption)
  }

  /* NonPosShort */
  inline given derivedNonPosShortEncoder: Encoder[NonPosShort] = Encoder[Short].contramap[NonPosShort](_.value)
  inline given derivedNonPosShortDecoder: Decoder[NonPosShort] = Decoder[Short].emap(NonPosShort.from)

  inline given derivedNonPosShortKeyEncoder: KeyEncoder[NonPosShort] = KeyEncoder[Short].contramap[NonPosShort](_.value)
  inline given derivedNonPosShortKeyDecoder: KeyDecoder[NonPosShort] with {
    override def apply(key: String): Option[NonPosShort] =
      KeyDecoder[Short].apply(key).flatMap(NonPosShort.from(_).toOption)
  }

  /* NegByte */
  inline given derivedNegByteEncoder: Encoder[NegByte] = Encoder[Byte].contramap[NegByte](_.value)
  inline given derivedNegByteDecoder: Decoder[NegByte] = Decoder[Byte].emap(NegByte.from)

  inline given derivedNegByteKeyEncoder: KeyEncoder[NegByte] = KeyEncoder[Byte].contramap[NegByte](_.value)
  inline given derivedNegByteKeyDecoder: KeyDecoder[NegByte] with {
    override def apply(key: String): Option[NegByte] =
      KeyDecoder[Byte].apply(key).flatMap(NegByte.from(_).toOption)
  }

  /* NonNegByte */
  inline given derivedNonNegByteEncoder: Encoder[NonNegByte] = Encoder[Byte].contramap[NonNegByte](_.value)
  inline given derivedNonNegByteDecoder: Decoder[NonNegByte] = Decoder[Byte].emap(NonNegByte.from)

  inline given derivedNonNegByteKeyEncoder: KeyEncoder[NonNegByte] = KeyEncoder[Byte].contramap[NonNegByte](_.value)
  inline given derivedNonNegByteKeyDecoder: KeyDecoder[NonNegByte] with {
    override def apply(key: String): Option[NonNegByte] =
      KeyDecoder[Byte].apply(key).flatMap(NonNegByte.from(_).toOption)
  }

  /* PosByte */
  inline given derivedPosByteEncoder: Encoder[PosByte] = Encoder[Byte].contramap[PosByte](_.value)
  inline given derivedPosByteDecoder: Decoder[PosByte] = Decoder[Byte].emap(PosByte.from)

  inline given derivedPosByteKeyEncoder: KeyEncoder[PosByte] = KeyEncoder[Byte].contramap[PosByte](_.value)
  inline given derivedPosByteKeyDecoder: KeyDecoder[PosByte] with {
    override def apply(key: String): Option[PosByte] =
      KeyDecoder[Byte].apply(key).flatMap(PosByte.from(_).toOption)
  }

  /* NonPosByte */
  inline given derivedNonPosByteEncoder: Encoder[NonPosByte] = Encoder[Byte].contramap[NonPosByte](_.value)
  inline given derivedNonPosByteDecoder: Decoder[NonPosByte] = Decoder[Byte].emap(NonPosByte.from)

  inline given derivedNonPosByteKeyEncoder: KeyEncoder[NonPosByte] = KeyEncoder[Byte].contramap[NonPosByte](_.value)
  inline given derivedNonPosByteKeyDecoder: KeyDecoder[NonPosByte] with {
    override def apply(key: String): Option[NonPosByte] =
      KeyDecoder[Byte].apply(key).flatMap(NonPosByte.from(_).toOption)
  }

  /* NegFloat */
  inline given derivedNegFloatEncoder: Encoder[NegFloat] = Encoder[Float].contramap[NegFloat](_.value)
  inline given derivedNegFloatDecoder: Decoder[NegFloat] = Decoder[Float].emap(NegFloat.from)

  /* NonNegFloat */
  inline given derivedNonNegFloatEncoder: Encoder[NonNegFloat] = Encoder[Float].contramap[NonNegFloat](_.value)
  inline given derivedNonNegFloatDecoder: Decoder[NonNegFloat] = Decoder[Float].emap(NonNegFloat.from)

  /* PosFloat */
  inline given derivedPosFloatEncoder: Encoder[PosFloat] = Encoder[Float].contramap[PosFloat](_.value)
  inline given derivedPosFloatDecoder: Decoder[PosFloat] = Decoder[Float].emap(PosFloat.from)

  /* NonPosFloat */
  inline given derivedNonPosFloatEncoder: Encoder[NonPosFloat] = Encoder[Float].contramap[NonPosFloat](_.value)
  inline given derivedNonPosFloatDecoder: Decoder[NonPosFloat] = Decoder[Float].emap(NonPosFloat.from)

  /* NegDouble */
  inline given derivedNegDoubleEncoder: Encoder[NegDouble] = Encoder[Double].contramap[NegDouble](_.value)
  inline given derivedNegDoubleDecoder: Decoder[NegDouble] = Decoder[Double].emap(NegDouble.from)

  /* NonNegDouble */
  inline given derivedNonNegDoubleEncoder: Encoder[NonNegDouble] = Encoder[Double].contramap[NonNegDouble](_.value)
  inline given derivedNonNegDoubleDecoder: Decoder[NonNegDouble] = Decoder[Double].emap(NonNegDouble.from)

  /* PosDouble */
  inline given derivedPosDoubleEncoder: Encoder[PosDouble] = Encoder[Double].contramap[PosDouble](_.value)
  inline given derivedPosDoubleDecoder: Decoder[PosDouble] = Decoder[Double].emap(PosDouble.from)

  /* NonPosDouble */
  inline given derivedNonPosDoubleEncoder: Encoder[NonPosDouble] = Encoder[Double].contramap[NonPosDouble](_.value)
  inline given derivedNonPosDoubleDecoder: Decoder[NonPosDouble] = Decoder[Double].emap(NonPosDouble.from)

  /* NegBigInt */
  inline given derivedNegBigIntEncoder: Encoder[NegBigInt] = Encoder[BigInt].contramap[NegBigInt](_.value)
  inline given derivedNegBigIntDecoder: Decoder[NegBigInt] = Decoder[BigInt].emap(NegBigInt.from)

  inline given derivedNegBigIntKeyEncoder: KeyEncoder[NegBigInt] with {
    @SuppressWarnings(Array("org.wartremover.warts.ToString"))
    override def apply(key: NegBigInt): String = key.value.toString
  }
  inline given derivedNegBigIntKeyDecoder: KeyDecoder[NegBigInt] with {
    override def apply(key: String): Option[NegBigInt] =
      scala.util.Try(BigInt(key)).toOption.flatMap(NegBigInt.from(_).toOption)
  }

  /* NonNegBigInt */
  inline given derivedNonNegBigIntEncoder: Encoder[NonNegBigInt] = Encoder[BigInt].contramap[NonNegBigInt](_.value)
  inline given derivedNonNegBigIntDecoder: Decoder[NonNegBigInt] = Decoder[BigInt].emap(NonNegBigInt.from)

  inline given derivedNonNegBigIntKeyEncoder: KeyEncoder[NonNegBigInt] with {
    @SuppressWarnings(Array("org.wartremover.warts.ToString"))
    override def apply(key: NonNegBigInt): String = key.value.toString
  }
  inline given derivedNonNegBigIntKeyDecoder: KeyDecoder[NonNegBigInt] with {
    override def apply(key: String): Option[NonNegBigInt] =
      scala.util.Try(BigInt(key)).toOption.flatMap(NonNegBigInt.from(_).toOption)
  }

  /* PosBigInt */
  inline given derivedPosBigIntEncoder: Encoder[PosBigInt] = Encoder[BigInt].contramap[PosBigInt](_.value)
  inline given derivedPosBigIntDecoder: Decoder[PosBigInt] = Decoder[BigInt].emap(PosBigInt.from)

  inline given derivedPosBigIntKeyEncoder: KeyEncoder[PosBigInt] with {
    @SuppressWarnings(Array("org.wartremover.warts.ToString"))
    override def apply(key: PosBigInt): String = key.value.toString
  }
  inline given derivedPosBigIntKeyDecoder: KeyDecoder[PosBigInt] with {
    override def apply(key: String): Option[PosBigInt] =
      scala.util.Try(BigInt(key)).toOption.flatMap(PosBigInt.from(_).toOption)
  }

  /* NonPosBigInt */
  inline given derivedNonPosBigIntEncoder: Encoder[NonPosBigInt] = Encoder[BigInt].contramap[NonPosBigInt](_.value)
  inline given derivedNonPosBigIntDecoder: Decoder[NonPosBigInt] = Decoder[BigInt].emap(NonPosBigInt.from)

  inline given derivedNonPosBigIntKeyEncoder: KeyEncoder[NonPosBigInt] with {
    @SuppressWarnings(Array("org.wartremover.warts.ToString"))
    override def apply(key: NonPosBigInt): String = key.value.toString
  }
  inline given derivedNonPosBigIntKeyDecoder: KeyDecoder[NonPosBigInt] with {
    override def apply(key: String): Option[NonPosBigInt] =
      scala.util.Try(BigInt(key)).toOption.flatMap(NonPosBigInt.from(_).toOption)
  }

  /* NegBigDecimal */
  inline given derivedNegBigDecimalEncoder: Encoder[NegBigDecimal] = Encoder[BigDecimal].contramap[NegBigDecimal](_.value)
  inline given derivedNegBigDecimalDecoder: Decoder[NegBigDecimal] = Decoder[BigDecimal].emap(NegBigDecimal.from)

  /* NonNegBigDecimal */
  inline given derivedNonNegBigDecimalEncoder: Encoder[NonNegBigDecimal] = Encoder[BigDecimal].contramap[NonNegBigDecimal](_.value)
  inline given derivedNonNegBigDecimalDecoder: Decoder[NonNegBigDecimal] = Decoder[BigDecimal].emap(NonNegBigDecimal.from)

  /* PosBigDecimal */
  inline given derivedPosBigDecimalEncoder: Encoder[PosBigDecimal] = Encoder[BigDecimal].contramap[PosBigDecimal](_.value)
  inline given derivedPosBigDecimalDecoder: Decoder[PosBigDecimal] = Decoder[BigDecimal].emap(PosBigDecimal.from)

  /* NonPosBigDecimal */
  inline given derivedNonPosBigDecimalEncoder: Encoder[NonPosBigDecimal] = Encoder[BigDecimal].contramap[NonPosBigDecimal](_.value)
  inline given derivedNonPosBigDecimalDecoder: Decoder[NonPosBigDecimal] = Decoder[BigDecimal].emap(NonPosBigDecimal.from)

}
object numeric extends numeric
