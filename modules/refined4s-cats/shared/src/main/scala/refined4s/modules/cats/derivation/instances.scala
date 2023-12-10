package refined4s.modules.cats.derivation

import cats.*
import refined4s.*
import refined4s.types.all.*

/** @author Kevin Lee
  * @since 2023-12-07
  */

trait instances {

  given eqNegInt: Eq[NegInt]     = NegInt.deriving[Eq]
  given showNegInt: Show[NegInt] = NegInt.deriving[Show]

  given eqNonNegInt: Eq[NonNegInt]     = NonNegInt.deriving[Eq]
  given showNonNegInt: Show[NonNegInt] = NonNegInt.deriving[Show]

  given eqPosInt: Eq[PosInt]     = PosInt.deriving[Eq]
  given showPosInt: Show[PosInt] = PosInt.deriving[Show]

  given eqNonPosInt: Eq[NonPosInt]     = NonPosInt.deriving[Eq]
  given showNonPosInt: Show[NonPosInt] = NonPosInt.deriving[Show]

  given eqNegLong: Eq[NegLong]     = NegLong.deriving[Eq]
  given showNegLong: Show[NegLong] = NegLong.deriving[Show]

  given eqNonNegLong: Eq[NonNegLong]     = NonNegLong.deriving[Eq]
  given showNonNegLong: Show[NonNegLong] = NonNegLong.deriving[Show]

  given eqPosLong: Eq[PosLong]     = PosLong.deriving[Eq]
  given showPosLong: Show[PosLong] = PosLong.deriving[Show]

  given eqNonPosLong: Eq[NonPosLong]     = NonPosLong.deriving[Eq]
  given showNonPosLong: Show[NonPosLong] = NonPosLong.deriving[Show]

  given eqNegShort: Eq[NegShort]     = NegShort.deriving[Eq]
  given showNegShort: Show[NegShort] = NegShort.deriving[Show]

  given eqNonNegShort: Eq[NonNegShort]     = NonNegShort.deriving[Eq]
  given showNonNegShort: Show[NonNegShort] = NonNegShort.deriving[Show]

  given eqPosShort: Eq[PosShort]     = PosShort.deriving[Eq]
  given showPosShort: Show[PosShort] = PosShort.deriving[Show]

  given eqNonPosShort: Eq[NonPosShort]     = NonPosShort.deriving[Eq]
  given showNonPosShort: Show[NonPosShort] = NonPosShort.deriving[Show]

  given eqNegByte: Eq[NegByte]     = NegByte.deriving[Eq]
  given showNegByte: Show[NegByte] = NegByte.deriving[Show]

  given eqNonNegByte: Eq[NonNegByte]     = NonNegByte.deriving[Eq]
  given showNonNegByte: Show[NonNegByte] = NonNegByte.deriving[Show]

  given eqPosByte: Eq[PosByte]     = PosByte.deriving[Eq]
  given showPosByte: Show[PosByte] = PosByte.deriving[Show]

  given eqNonPosByte: Eq[NonPosByte]     = NonPosByte.deriving[Eq]
  given showNonPosByte: Show[NonPosByte] = NonPosByte.deriving[Show]

  given eqNegFloat: Eq[NegFloat]     = NegFloat.deriving[Eq]
  given showNegFloat: Show[NegFloat] = NegFloat.deriving[Show]

  given eqNonNegFloat: Eq[NonNegFloat]     = NonNegFloat.deriving[Eq]
  given showNonNegFloat: Show[NonNegFloat] = NonNegFloat.deriving[Show]

  given eqPosFloat: Eq[PosFloat]     = PosFloat.deriving[Eq]
  given showPosFloat: Show[PosFloat] = PosFloat.deriving[Show]

  given eqNonPosFloat: Eq[NonPosFloat]     = NonPosFloat.deriving[Eq]
  given showNonPosFloat: Show[NonPosFloat] = NonPosFloat.deriving[Show]

  given eqNegDouble: Eq[NegDouble]     = NegDouble.deriving[Eq]
  given showNegDouble: Show[NegDouble] = NegDouble.deriving[Show]

  given eqNonNegDouble: Eq[NonNegDouble]     = NonNegDouble.deriving[Eq]
  given showNonNegDouble: Show[NonNegDouble] = NonNegDouble.deriving[Show]

  given eqPosDouble: Eq[PosDouble]     = PosDouble.deriving[Eq]
  given showPosDouble: Show[PosDouble] = PosDouble.deriving[Show]

  given eqNonPosDouble: Eq[NonPosDouble]     = NonPosDouble.deriving[Eq]
  given showNonPosDouble: Show[NonPosDouble] = NonPosDouble.deriving[Show]

  given eqNegBigInt: Eq[NegBigInt]     = NegBigInt.deriving[Eq]
  given showNegBigInt: Show[NegBigInt] = NegBigInt.deriving[Show]

  given eqNonNegBigInt: Eq[NonNegBigInt]     = NonNegBigInt.deriving[Eq]
  given showNonNegBigInt: Show[NonNegBigInt] = NonNegBigInt.deriving[Show]

  given eqPosBigInt: Eq[PosBigInt]     = PosBigInt.deriving[Eq]
  given showPosBigInt: Show[PosBigInt] = PosBigInt.deriving[Show]

  given eqNonPosBigInt: Eq[NonPosBigInt]     = NonPosBigInt.deriving[Eq]
  given showNonPosBigInt: Show[NonPosBigInt] = NonPosBigInt.deriving[Show]

  given eqNegBigDecimal: Eq[NegBigDecimal]     = NegBigDecimal.deriving[Eq]
  given showNegBigDecimal: Show[NegBigDecimal] = NegBigDecimal.deriving[Show]

  given eqNonNegBigDecimal: Eq[NonNegBigDecimal]     = NonNegBigDecimal.deriving[Eq]
  given showNonNegBigDecimal: Show[NonNegBigDecimal] = NonNegBigDecimal.deriving[Show]

  given eqPosBigDecimal: Eq[PosBigDecimal]     = PosBigDecimal.deriving[Eq]
  given showPosBigDecimal: Show[PosBigDecimal] = PosBigDecimal.deriving[Show]

  given eqNonPosBigDecimal: Eq[NonPosBigDecimal]     = NonPosBigDecimal.deriving[Eq]
  given showNonPosBigDecimal: Show[NonPosBigDecimal] = NonPosBigDecimal.deriving[Show]

  given eqNonEmptyString: Eq[NonEmptyString]     = NonEmptyString.deriving[Eq]
  given showNonEmptyString: Show[NonEmptyString] = NonEmptyString.deriving[Show]

  given eqUri: Eq[Uri]     = Uri.deriving[Eq]
  given showUri: Show[Uri] = Uri.deriving[Show]

}
object instances extends instances
