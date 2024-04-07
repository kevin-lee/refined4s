package refined4s.modules.cats.derivation.types

import cats.*
import refined4s.*
import refined4s.types.all.*
import refined4s.modules.cats.syntax.contraCoercible

/** @author Kevin Lee
  * @since 2023-12-07
  */
trait all {
  inline given derivedNegIntEq(using eqActual: Eq[Int]): Eq[NegInt]         = contraCoercible(eqActual)
  inline given derivedNegIntShow(using showActual: Show[Int]): Show[NegInt] = contraCoercible(showActual)

  inline given derivedNonNegIntEq(using eqActual: Eq[Int]): Eq[NonNegInt]         = contraCoercible(eqActual)
  inline given derivedNonNegIntShow(using showActual: Show[Int]): Show[NonNegInt] = contraCoercible(showActual)

  inline given derivedPosIntEq(using eqActual: Eq[Int]): Eq[PosInt]         = contraCoercible(eqActual)
  inline given derivedPosIntShow(using showActual: Show[Int]): Show[PosInt] = contraCoercible(showActual)

  inline given derivedNonPosIntEq(using eqActual: Eq[Int]): Eq[NonPosInt]         = contraCoercible(eqActual)
  inline given derivedNonPosIntShow(using showActual: Show[Int]): Show[NonPosInt] = contraCoercible(showActual)

  inline given derivedNegLongEq(using eqActual: Eq[Long]): Eq[NegLong]         = contraCoercible(eqActual)
  inline given derivedNegLongShow(using showActual: Show[Long]): Show[NegLong] = contraCoercible(showActual)

  inline given derivedNonNegLongEq(using eqActual: Eq[Long]): Eq[NonNegLong]         = contraCoercible(eqActual)
  inline given derivedNonNegLongShow(using showActual: Show[Long]): Show[NonNegLong] = contraCoercible(showActual)

  inline given derivedPosLongEq(using eqActual: Eq[Long]): Eq[PosLong]         = contraCoercible(eqActual)
  inline given derivedPosLongShow(using showActual: Show[Long]): Show[PosLong] = contraCoercible(showActual)

  inline given derivedNonPosLongEq(using eqActual: Eq[Long]): Eq[NonPosLong]         = contraCoercible(eqActual)
  inline given derivedNonPosLongShow(using showActual: Show[Long]): Show[NonPosLong] = contraCoercible(showActual)

  inline given derivedNegShortEq(using eqActual: Eq[Short]): Eq[NegShort]         = contraCoercible(eqActual)
  inline given derivedNegShortShow(using showActual: Show[Short]): Show[NegShort] = contraCoercible(showActual)

  inline given derivedNonNegShortEq(using eqActual: Eq[Short]): Eq[NonNegShort]         = contraCoercible(eqActual)
  inline given derivedNonNegShortShow(using showActual: Show[Short]): Show[NonNegShort] = contraCoercible(showActual)

  inline given derivedPosShortEq(using eqActual: Eq[Short]): Eq[PosShort]         = contraCoercible(eqActual)
  inline given derivedPosShortShow(using showActual: Show[Short]): Show[PosShort] = contraCoercible(showActual)

  inline given derivedNonPosShortEq(using eqActual: Eq[Short]): Eq[NonPosShort]         = contraCoercible(eqActual)
  inline given derivedNonPosShortShow(using showActual: Show[Short]): Show[NonPosShort] = contraCoercible(showActual)

  inline given derivedNegByteEq(using eqActual: Eq[Byte]): Eq[NegByte]         = contraCoercible(eqActual)
  inline given derivedNegByteShow(using showActual: Show[Byte]): Show[NegByte] = contraCoercible(showActual)

  inline given derivedNonNegByteEq(using eqActual: Eq[Byte]): Eq[NonNegByte]         = contraCoercible(eqActual)
  inline given derivedNonNegByteShow(using showActual: Show[Byte]): Show[NonNegByte] = contraCoercible(showActual)

  inline given derivedPosByteEq(using eqActual: Eq[Byte]): Eq[PosByte]         = contraCoercible(eqActual)
  inline given derivedPosByteShow(using showActual: Show[Byte]): Show[PosByte] = contraCoercible(showActual)

  inline given derivedNonPosByteEq(using eqActual: Eq[Byte]): Eq[NonPosByte]         = contraCoercible(eqActual)
  inline given derivedNonPosByteShow(using showActual: Show[Byte]): Show[NonPosByte] = contraCoercible(showActual)

  inline given derivedNegFloatEq(using eqActual: Eq[Float]): Eq[NegFloat]         = contraCoercible(eqActual)
  inline given derivedNegFloatShow(using showActual: Show[Float]): Show[NegFloat] = contraCoercible(showActual)

  inline given derivedNonNegFloatEq(using eqActual: Eq[Float]): Eq[NonNegFloat]         = contraCoercible(eqActual)
  inline given derivedNonNegFloatShow(using showActual: Show[Float]): Show[NonNegFloat] = contraCoercible(showActual)

  inline given derivedPosFloatEq(using eqActual: Eq[Float]): Eq[PosFloat]         = contraCoercible(eqActual)
  inline given derivedPosFloatShow(using showActual: Show[Float]): Show[PosFloat] = contraCoercible(showActual)

  inline given derivedNonPosFloatEq(using eqActual: Eq[Float]): Eq[NonPosFloat]         = contraCoercible(eqActual)
  inline given derivedNonPosFloatShow(using showActual: Show[Float]): Show[NonPosFloat] = contraCoercible(showActual)

  inline given derivedNegDoubleEq(using eqActual: Eq[Double]): Eq[NegDouble]         = contraCoercible(eqActual)
  inline given derivedNegDoubleShow(using showActual: Show[Double]): Show[NegDouble] = contraCoercible(showActual)

  inline given derivedNonNegDoubleEq(using eqActual: Eq[Double]): Eq[NonNegDouble]         = contraCoercible(eqActual)
  inline given derivedNonNegDoubleShow(using showActual: Show[Double]): Show[NonNegDouble] = contraCoercible(showActual)

  inline given derivedPosDoubleEq(using eqActual: Eq[Double]): Eq[PosDouble]         = contraCoercible(eqActual)
  inline given derivedPosDoubleShow(using showActual: Show[Double]): Show[PosDouble] = contraCoercible(showActual)

  inline given derivedNonPosDoubleEq(using eqActual: Eq[Double]): Eq[NonPosDouble]         = contraCoercible(eqActual)
  inline given derivedNonPosDoubleShow(using showActual: Show[Double]): Show[NonPosDouble] = contraCoercible(showActual)

  inline given derivedNegBigIntEq(using eqActual: Eq[BigInt]): Eq[NegBigInt]         = contraCoercible(eqActual)
  inline given derivedNegBigIntShow(using showActual: Show[BigInt]): Show[NegBigInt] = contraCoercible(showActual)

  inline given derivedNonNegBigIntEq(using eqActual: Eq[BigInt]): Eq[NonNegBigInt]         = contraCoercible(eqActual)
  inline given derivedNonNegBigIntShow(using showActual: Show[BigInt]): Show[NonNegBigInt] = contraCoercible(showActual)

  inline given derivedPosBigIntEq(using eqActual: Eq[BigInt]): Eq[PosBigInt]         = contraCoercible(eqActual)
  inline given derivedPosBigIntShow(using showActual: Show[BigInt]): Show[PosBigInt] = contraCoercible(showActual)

  inline given derivedNonPosBigIntEq(using eqActual: Eq[BigInt]): Eq[NonPosBigInt]         = contraCoercible(eqActual)
  inline given derivedNonPosBigIntShow(using showActual: Show[BigInt]): Show[NonPosBigInt] = contraCoercible(showActual)

  inline given derivedNegBigDecimalEq(using eqActual: Eq[BigDecimal]): Eq[NegBigDecimal]         = contraCoercible(eqActual)
  inline given derivedNegBigDecimalShow(using showActual: Show[BigDecimal]): Show[NegBigDecimal] = contraCoercible(showActual)

  inline given derivedNonNegBigDecimalEq(using eqActual: Eq[BigDecimal]): Eq[NonNegBigDecimal]         = contraCoercible(eqActual)
  inline given derivedNonNegBigDecimalShow(using showActual: Show[BigDecimal]): Show[NonNegBigDecimal] = contraCoercible(showActual)

  inline given derivedPosBigDecimalEq(using eqActual: Eq[BigDecimal]): Eq[PosBigDecimal]         = contraCoercible(eqActual)
  inline given derivedPosBigDecimalShow(using showActual: Show[BigDecimal]): Show[PosBigDecimal] = contraCoercible(showActual)

  inline given derivedNonPosBigDecimalEq(using eqActual: Eq[BigDecimal]): Eq[NonPosBigDecimal]         = contraCoercible(eqActual)
  inline given derivedNonPosBigDecimalShow(using showActual: Show[BigDecimal]): Show[NonPosBigDecimal] = contraCoercible(showActual)

  // strings

  inline given derivedNonEmptyStringEq(using eqActual: Eq[String]): Eq[NonEmptyString]         = contraCoercible(eqActual)
  inline given derivedNonEmptyStringShow(using showActual: Show[String]): Show[NonEmptyString] = contraCoercible(showActual)

  inline given derivedNonBlankStringEq(using eqActual: Eq[String]): Eq[NonBlankString]         = contraCoercible(eqActual)
  inline given derivedNonBlankStringShow(using showActual: Show[String]): Show[NonBlankString] = contraCoercible(showActual)

  inline given derivedUuidEq(using eqActual: Eq[String]): Eq[Uuid]         = contraCoercible(eqActual)
  inline given derivedUuidShow(using showActual: Show[String]): Show[Uuid] = contraCoercible(showActual)

  // network

  inline given derivedUriEq(using eqActual: Eq[String]): Eq[Uri]         = contraCoercible(eqActual)
  inline given derivedUriShow(using showActual: Show[String]): Show[Uri] = contraCoercible(showActual)

  inline given derivedUrlEq(using eqActual: Eq[String]): Eq[Url]         = contraCoercible(eqActual)
  inline given derivedUrlShow(using showActual: Show[String]): Show[Url] = contraCoercible(showActual)

  inline given derivedPortNumberEq(using eqActual: Eq[Int]): Eq[PortNumber]         = contraCoercible(eqActual)
  inline given derivedPortNumberShow(using showActual: Show[Int]): Show[PortNumber] = contraCoercible(showActual)

  inline given derivedSystemPortNumberEq(using eqActual: Eq[Int]): Eq[SystemPortNumber]         = contraCoercible(eqActual)
  inline given derivedSystemPortNumberShow(using showActual: Show[Int]): Show[SystemPortNumber] = contraCoercible(showActual)

  inline given derivedNonSystemPortNumberEq(using eqActual: Eq[Int]): Eq[NonSystemPortNumber]         = contraCoercible(eqActual)
  inline given derivedNonSystemPortNumberShow(using showActual: Show[Int]): Show[NonSystemPortNumber] = contraCoercible(showActual)

  inline given derivedUserPortNumberEq(using eqActual: Eq[Int]): Eq[UserPortNumber]         = contraCoercible(eqActual)
  inline given derivedUserPortNumberShow(using showActual: Show[Int]): Show[UserPortNumber] = contraCoercible(showActual)

  inline given derivedDynamicPortNumberEq(using eqActual: Eq[Int]): Eq[DynamicPortNumber]         = contraCoercible(eqActual)
  inline given derivedDynamicPortNumberShow(using showActual: Show[Int]): Show[DynamicPortNumber] = contraCoercible(showActual)

}
object all extends all
