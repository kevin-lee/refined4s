package refined4s.modules.extras.derivation.types

import extras.render.Render

import refined4s.types.all.*

/** @author Kevin Lee
  * @since 2024-01-01
  */
trait all {

  inline given derivedNegIntRender(using renderActual: Render[Int]): Render[NegInt] = renderActual.contramap(_.value)

  inline given derivedNonNegIntRender(using renderActual: Render[Int]): Render[NonNegInt] =
    renderActual.contramap(_.value)

  inline given derivedPosIntRender(using renderActual: Render[Int]): Render[PosInt] = renderActual.contramap(_.value)

  inline given derivedNonPosIntRender(using renderActual: Render[Int]): Render[NonPosInt] =
    renderActual.contramap(_.value)

  inline given derivedNegLongRender(using renderActual: Render[Long]): Render[NegLong] = renderActual.contramap(_.value)

  inline given derivedNonNegLongRender(using renderActual: Render[Long]): Render[NonNegLong] =
    renderActual.contramap(_.value)

  inline given derivedPosLongRender(using renderActual: Render[Long]): Render[PosLong] = renderActual.contramap(_.value)

  inline given derivedNonPosLongRender(using renderActual: Render[Long]): Render[NonPosLong] =
    renderActual.contramap(_.value)

  inline given derivedNegShortRender(using renderActual: Render[Short]): Render[NegShort] =
    renderActual.contramap(_.value)

  inline given derivedNonNegShortRender(using renderActual: Render[Short]): Render[NonNegShort] =
    renderActual.contramap(_.value)

  inline given derivedPosShortRender(using renderActual: Render[Short]): Render[PosShort] =
    renderActual.contramap(_.value)

  inline given derivedNonPosShortRender(using renderActual: Render[Short]): Render[NonPosShort] =
    renderActual.contramap(_.value)

  inline given derivedNegByteRender(using renderActual: Render[Byte]): Render[NegByte] = renderActual.contramap(_.value)

  inline given derivedNonNegByteRender(using renderActual: Render[Byte]): Render[NonNegByte] =
    renderActual.contramap(_.value)

  inline given derivedPosByteRender(using renderActual: Render[Byte]): Render[PosByte] = renderActual.contramap(_.value)

  inline given derivedNonPosByteRender(using renderActual: Render[Byte]): Render[NonPosByte] =
    renderActual.contramap(_.value)

  inline given derivedNegFloatRender(using renderActual: Render[Float]): Render[NegFloat] =
    renderActual.contramap(_.value)

  inline given derivedNonNegFloatRender(using renderActual: Render[Float]): Render[NonNegFloat] =
    renderActual.contramap(_.value)

  inline given derivedPosFloatRender(using renderActual: Render[Float]): Render[PosFloat] =
    renderActual.contramap(_.value)

  inline given derivedNonPosFloatRender(using renderActual: Render[Float]): Render[NonPosFloat] =
    renderActual.contramap(_.value)

  inline given derivedNegDoubleRender(using renderActual: Render[Double]): Render[NegDouble] =
    renderActual.contramap(_.value)

  inline given derivedNonNegDoubleRender(using renderActual: Render[Double]): Render[NonNegDouble] =
    renderActual.contramap(_.value)

  inline given derivedPosDoubleRender(using renderActual: Render[Double]): Render[PosDouble] =
    renderActual.contramap(_.value)

  inline given derivedNonPosDoubleRender(using renderActual: Render[Double]): Render[NonPosDouble] =
    renderActual.contramap(_.value)

  inline given derivedNegBigIntRender(using renderActual: Render[BigInt]): Render[NegBigInt] =
    renderActual.contramap(_.value)

  inline given derivedNonNegBigIntRender(using renderActual: Render[BigInt]): Render[NonNegBigInt] =
    renderActual.contramap(_.value)

  inline given derivedPosBigIntRender(using renderActual: Render[BigInt]): Render[PosBigInt] =
    renderActual.contramap(_.value)

  inline given derivedNonPosBigIntRender(using renderActual: Render[BigInt]): Render[NonPosBigInt] =
    renderActual.contramap(_.value)

  inline given derivedNegBigDecimalRender(using renderActual: Render[BigDecimal]): Render[NegBigDecimal] =
    renderActual.contramap(_.value)

  inline given derivedNonNegBigDecimalRender(using renderActual: Render[BigDecimal]): Render[NonNegBigDecimal] =
    renderActual.contramap(_.value)

  inline given derivedPosBigDecimalRender(using renderActual: Render[BigDecimal]): Render[PosBigDecimal] =
    renderActual.contramap(_.value)

  inline given derivedNonPosBigDecimalRender(using renderActual: Render[BigDecimal]): Render[NonPosBigDecimal] =
    renderActual.contramap(_.value)

  // strings

  inline given derivedNonEmptyStringRender(using renderActual: Render[String]): Render[NonEmptyString] =
    renderActual.contramap(_.value)

  inline given derivedNonBlankStringRender(using renderActual: Render[String]): Render[NonBlankString] =
    renderActual.contramap(_.value)

  inline given derivedUuidRender(using renderActual: Render[String]): Render[Uuid] =
    renderActual.contramap(_.value)

  // network

  inline given derivedUriRender(using renderActual: Render[String]): Render[Uri] = renderActual.contramap(_.value)

  inline given derivedUrlRender(using renderActual: Render[String]): Render[Url] = renderActual.contramap(_.value)

  inline given derivedPortNumberRender(using renderActual: Render[Int]): Render[PortNumber] =
    renderActual.contramap(_.value)

  inline given derivedSystemPortNumberRender(using renderActual: Render[Int]): Render[SystemPortNumber] =
    renderActual.contramap(_.value)

  inline given derivedNonSystemPortNumberRender(using renderActual: Render[Int]): Render[NonSystemPortNumber] =
    renderActual.contramap(_.value)

  inline given derivedUserPortNumberRender(using renderActual: Render[Int]): Render[UserPortNumber] =
    renderActual.contramap(_.value)

  inline given derivedDynamicPortNumberRender(using renderActual: Render[Int]): Render[DynamicPortNumber] =
    renderActual.contramap(_.value)

}
object all extends all
