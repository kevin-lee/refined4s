package refined4s.modules.extras.derivation.types

import extras.render.Render
import refined4s.types.numeric.*

/** @author Kevin Lee
  * @since 2025-09-02
  */
trait numeric {

  inline given derivedNegIntRender: Render[NegInt] = numeric.derivedNegIntRender

  inline given derivedNonNegIntRender: Render[NonNegInt] = numeric.derivedNonNegIntRender

  inline given derivedPosIntRender: Render[PosInt] = numeric.derivedPosIntRender

  inline given derivedNonPosIntRender: Render[NonPosInt] = numeric.derivedNonPosIntRender

  inline given derivedNegLongRender: Render[NegLong] = numeric.derivedNegLongRender

  inline given derivedNonNegLongRender: Render[NonNegLong] = numeric.derivedNonNegLongRender

  inline given derivedPosLongRender: Render[PosLong] = numeric.derivedPosLongRender

  inline given derivedNonPosLongRender: Render[NonPosLong] = numeric.derivedNonPosLongRender

  inline given derivedNegShortRender: Render[NegShort] = numeric.derivedNegShortRender

  inline given derivedNonNegShortRender: Render[NonNegShort] = numeric.derivedNonNegShortRender

  inline given derivedPosShortRender: Render[PosShort] = numeric.derivedPosShortRender

  inline given derivedNonPosShortRender: Render[NonPosShort] = numeric.derivedNonPosShortRender

  inline given derivedNegByteRender: Render[NegByte] = numeric.derivedNegByteRender

  inline given derivedNonNegByteRender: Render[NonNegByte] = numeric.derivedNonNegByteRender

  inline given derivedPosByteRender: Render[PosByte] = numeric.derivedPosByteRender

  inline given derivedNonPosByteRender: Render[NonPosByte] = numeric.derivedNonPosByteRender

  inline given derivedNegFloatRender: Render[NegFloat] = numeric.derivedNegFloatRender

  inline given derivedNonNegFloatRender: Render[NonNegFloat] = numeric.derivedNonNegFloatRender

  inline given derivedPosFloatRender: Render[PosFloat] = numeric.derivedPosFloatRender

  inline given derivedNonPosFloatRender: Render[NonPosFloat] = numeric.derivedNonPosFloatRender

  inline given derivedNegDoubleRender: Render[NegDouble] = numeric.derivedNegDoubleRender

  inline given derivedNonNegDoubleRender: Render[NonNegDouble] = numeric.derivedNonNegDoubleRender

  inline given derivedPosDoubleRender: Render[PosDouble] = numeric.derivedPosDoubleRender

  inline given derivedNonPosDoubleRender: Render[NonPosDouble] = numeric.derivedNonPosDoubleRender

  inline given derivedNegBigIntRender: Render[NegBigInt] = numeric.derivedNegBigIntRender

  inline given derivedNonNegBigIntRender: Render[NonNegBigInt] = numeric.derivedNonNegBigIntRender

  inline given derivedPosBigIntRender: Render[PosBigInt] = numeric.derivedPosBigIntRender

  inline given derivedNonPosBigIntRender: Render[NonPosBigInt] = numeric.derivedNonPosBigIntRender

  inline given derivedNegBigDecimalRender: Render[NegBigDecimal] = numeric.derivedNegBigDecimalRender

  inline given derivedNonNegBigDecimalRender: Render[NonNegBigDecimal] = numeric.derivedNonNegBigDecimalRender

  inline given derivedPosBigDecimalRender: Render[PosBigDecimal] = numeric.derivedPosBigDecimalRender

  inline given derivedNonPosBigDecimalRender: Render[NonPosBigDecimal] = numeric.derivedNonPosBigDecimalRender

}
object numeric {

  given derivedNegIntRender(using renderActual: Render[Int]): Render[NegInt] = renderActual.contramap(_.value)

  given derivedNonNegIntRender(using renderActual: Render[Int]): Render[NonNegInt] =
    renderActual.contramap(_.value)

  given derivedPosIntRender(using renderActual: Render[Int]): Render[PosInt] = renderActual.contramap(_.value)

  given derivedNonPosIntRender(using renderActual: Render[Int]): Render[NonPosInt] =
    renderActual.contramap(_.value)

  given derivedNegLongRender(using renderActual: Render[Long]): Render[NegLong] = renderActual.contramap(_.value)

  given derivedNonNegLongRender(using renderActual: Render[Long]): Render[NonNegLong] =
    renderActual.contramap(_.value)

  given derivedPosLongRender(using renderActual: Render[Long]): Render[PosLong] = renderActual.contramap(_.value)

  given derivedNonPosLongRender(using renderActual: Render[Long]): Render[NonPosLong] =
    renderActual.contramap(_.value)

  given derivedNegShortRender(using renderActual: Render[Short]): Render[NegShort] =
    renderActual.contramap(_.value)

  given derivedNonNegShortRender(using renderActual: Render[Short]): Render[NonNegShort] =
    renderActual.contramap(_.value)

  given derivedPosShortRender(using renderActual: Render[Short]): Render[PosShort] =
    renderActual.contramap(_.value)

  given derivedNonPosShortRender(using renderActual: Render[Short]): Render[NonPosShort] =
    renderActual.contramap(_.value)

  given derivedNegByteRender(using renderActual: Render[Byte]): Render[NegByte] = renderActual.contramap(_.value)

  given derivedNonNegByteRender(using renderActual: Render[Byte]): Render[NonNegByte] =
    renderActual.contramap(_.value)

  given derivedPosByteRender(using renderActual: Render[Byte]): Render[PosByte] = renderActual.contramap(_.value)

  given derivedNonPosByteRender(using renderActual: Render[Byte]): Render[NonPosByte] =
    renderActual.contramap(_.value)

  given derivedNegFloatRender(using renderActual: Render[Float]): Render[NegFloat] =
    renderActual.contramap(_.value)

  given derivedNonNegFloatRender(using renderActual: Render[Float]): Render[NonNegFloat] =
    renderActual.contramap(_.value)

  given derivedPosFloatRender(using renderActual: Render[Float]): Render[PosFloat] =
    renderActual.contramap(_.value)

  given derivedNonPosFloatRender(using renderActual: Render[Float]): Render[NonPosFloat] =
    renderActual.contramap(_.value)

  given derivedNegDoubleRender(using renderActual: Render[Double]): Render[NegDouble] =
    renderActual.contramap(_.value)

  given derivedNonNegDoubleRender(using renderActual: Render[Double]): Render[NonNegDouble] =
    renderActual.contramap(_.value)

  given derivedPosDoubleRender(using renderActual: Render[Double]): Render[PosDouble] =
    renderActual.contramap(_.value)

  given derivedNonPosDoubleRender(using renderActual: Render[Double]): Render[NonPosDouble] =
    renderActual.contramap(_.value)

  given derivedNegBigIntRender(using renderActual: Render[BigInt]): Render[NegBigInt] =
    renderActual.contramap(_.value)

  given derivedNonNegBigIntRender(using renderActual: Render[BigInt]): Render[NonNegBigInt] =
    renderActual.contramap(_.value)

  given derivedPosBigIntRender(using renderActual: Render[BigInt]): Render[PosBigInt] =
    renderActual.contramap(_.value)

  given derivedNonPosBigIntRender(using renderActual: Render[BigInt]): Render[NonPosBigInt] =
    renderActual.contramap(_.value)

  given derivedNegBigDecimalRender(using renderActual: Render[BigDecimal]): Render[NegBigDecimal] =
    renderActual.contramap(_.value)

  given derivedNonNegBigDecimalRender(using renderActual: Render[BigDecimal]): Render[NonNegBigDecimal] =
    renderActual.contramap(_.value)

  given derivedPosBigDecimalRender(using renderActual: Render[BigDecimal]): Render[PosBigDecimal] =
    renderActual.contramap(_.value)

  given derivedNonPosBigDecimalRender(using renderActual: Render[BigDecimal]): Render[NonPosBigDecimal] =
    renderActual.contramap(_.value)

}
