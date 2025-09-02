package refined4s.modules.tapir.derivation.types

import refined4s.types.numeric.*
import sttp.tapir.Schema

/** @author Kevin Lee
  * @since 2024-04-03
  */
trait numeric {

  inline given schemaNegInt: Schema[NegInt] = numeric.derivedNegIntSchema

  inline given schemaNonNegInt: Schema[NonNegInt] = numeric.derivedNonNegIntSchema

  inline given schemaPosInt: Schema[PosInt] = numeric.derivedPosIntSchema

  inline given schemaNonPosInt: Schema[NonPosInt] = numeric.derivedNonPosIntSchema

  inline given schemaNegLong: Schema[NegLong] = numeric.derivedNegLongSchema

  inline given schemaNonNegLong: Schema[NonNegLong] = numeric.derivedNonNegLongSchema

  inline given schemaPosLong: Schema[PosLong] = numeric.derivedPosLongSchema

  inline given schemaNonPosLong: Schema[NonPosLong] = numeric.derivedNonPosLongSchema

  inline given schemaNegShort: Schema[NegShort] = numeric.derivedNegShortSchema

  inline given schemaNonNegShort: Schema[NonNegShort] = numeric.derivedNonNegShortSchema

  inline given schemaPosShort: Schema[PosShort] = numeric.derivedPosShortSchema

  inline given schemaNonPosShort: Schema[NonPosShort] = numeric.derivedNonPosShortSchema

  inline given schemaNegByte: Schema[NegByte] = numeric.derivedNegByteSchema

  inline given schemaNonNegByte: Schema[NonNegByte] = numeric.derivedNonNegByteSchema

  inline given schemaPosByte: Schema[PosByte] = numeric.derivedPosByteSchema

  inline given schemaNonPosByte: Schema[NonPosByte] = numeric.derivedNonPosByteSchema

  inline given schemaNegFloat: Schema[NegFloat] = numeric.derivedNegFloatSchema

  inline given schemaNonNegFloat: Schema[NonNegFloat] = numeric.derivedNonNegFloatSchema

  inline given schemaPosFloat: Schema[PosFloat] = numeric.derivedPosFloatSchema

  inline given schemaNonPosFloat: Schema[NonPosFloat] = numeric.derivedNonPosFloatSchema

  inline given schemaNegDouble: Schema[NegDouble] = numeric.derivedNegDoubleSchema

  inline given schemaNonNegDouble: Schema[NonNegDouble] = numeric.derivedNonNegDoubleSchema

  inline given schemaPosDouble: Schema[PosDouble] = numeric.derivedPosDoubleSchema

  inline given schemaNonPosDouble: Schema[NonPosDouble] = numeric.derivedNonPosDoubleSchema

  inline given schemaNegBigInt: Schema[NegBigInt] = numeric.derivedNegBigIntSchema

  inline given schemaNonNegBigInt: Schema[NonNegBigInt] = numeric.derivedNonNegBigIntSchema

  inline given schemaPosBigInt: Schema[PosBigInt] = numeric.derivedPosBigIntSchema

  inline given schemaNonPosBigInt: Schema[NonPosBigInt] = numeric.derivedNonPosBigIntSchema

  inline given schemaNegBigDecimal: Schema[NegBigDecimal] = numeric.derivedNegBigDecimalSchema

  inline given schemaNonNegBigDecimal: Schema[NonNegBigDecimal] = numeric.derivedNonNegBigDecimalSchema

  inline given schemaPosBigDecimal: Schema[PosBigDecimal] = numeric.derivedPosBigDecimalSchema

  inline given schemaNonPosBigDecimal: Schema[NonPosBigDecimal] = numeric.derivedNonPosBigDecimalSchema

}
object numeric {
  given derivedNegIntSchema(using actualSchema: Schema[Int]): Schema[NegInt] = actualSchema.map(NegInt.from(_).toOption)(_.value)

  given derivedNonNegIntSchema(using actualSchema: Schema[Int]): Schema[NonNegInt] =
    actualSchema.map(NonNegInt.from(_).toOption)(_.value)

  given derivedPosIntSchema(using actualSchema: Schema[Int]): Schema[PosInt] = actualSchema.map(PosInt.from(_).toOption)(_.value)

  given derivedNonPosIntSchema(using actualSchema: Schema[Int]): Schema[NonPosInt] =
    actualSchema.map(NonPosInt.from(_).toOption)(_.value)

  given derivedNegLongSchema(using actualSchema: Schema[Long]): Schema[NegLong] = actualSchema.map(NegLong.from(_).toOption)(_.value)

  given derivedNonNegLongSchema(using actualSchema: Schema[Long]): Schema[NonNegLong] =
    actualSchema.map(NonNegLong.from(_).toOption)(_.value)

  given derivedPosLongSchema(using actualSchema: Schema[Long]): Schema[PosLong] = actualSchema.map(PosLong.from(_).toOption)(_.value)

  given derivedNonPosLongSchema(using actualSchema: Schema[Long]): Schema[NonPosLong] =
    actualSchema.map(NonPosLong.from(_).toOption)(_.value)

  given derivedNegShortSchema(using actualSchema: Schema[Short]): Schema[NegShort] =
    actualSchema.map(NegShort.from(_).toOption)(_.value)

  given derivedNonNegShortSchema(using actualSchema: Schema[Short]): Schema[NonNegShort] =
    actualSchema.map(NonNegShort.from(_).toOption)(_.value)

  given derivedPosShortSchema(using actualSchema: Schema[Short]): Schema[PosShort] =
    actualSchema.map(PosShort.from(_).toOption)(_.value)

  given derivedNonPosShortSchema(using actualSchema: Schema[Short]): Schema[NonPosShort] =
    actualSchema.map(NonPosShort.from(_).toOption)(_.value)

  given derivedNegByteSchema(using actualSchema: Schema[Byte]): Schema[NegByte] = actualSchema.map(NegByte.from(_).toOption)(_.value)

  given derivedNonNegByteSchema(using actualSchema: Schema[Byte]): Schema[NonNegByte] =
    actualSchema.map(NonNegByte.from(_).toOption)(_.value)

  given derivedPosByteSchema(using actualSchema: Schema[Byte]): Schema[PosByte] = actualSchema.map(PosByte.from(_).toOption)(_.value)

  given derivedNonPosByteSchema(using actualSchema: Schema[Byte]): Schema[NonPosByte] =
    actualSchema.map(NonPosByte.from(_).toOption)(_.value)

  given derivedNegFloatSchema(using actualSchema: Schema[Float]): Schema[NegFloat] =
    actualSchema.map(NegFloat.from(_).toOption)(_.value)

  given derivedNonNegFloatSchema(using actualSchema: Schema[Float]): Schema[NonNegFloat] =
    actualSchema.map(NonNegFloat.from(_).toOption)(_.value)

  given derivedPosFloatSchema(using actualSchema: Schema[Float]): Schema[PosFloat] =
    actualSchema.map(PosFloat.from(_).toOption)(_.value)

  given derivedNonPosFloatSchema(using actualSchema: Schema[Float]): Schema[NonPosFloat] =
    actualSchema.map(NonPosFloat.from(_).toOption)(_.value)

  given derivedNegDoubleSchema(using actualSchema: Schema[Double]): Schema[NegDouble] =
    actualSchema.map(NegDouble.from(_).toOption)(_.value)

  given derivedNonNegDoubleSchema(using actualSchema: Schema[Double]): Schema[NonNegDouble] =
    actualSchema.map(NonNegDouble.from(_).toOption)(_.value)

  given derivedPosDoubleSchema(using actualSchema: Schema[Double]): Schema[PosDouble] =
    actualSchema.map(PosDouble.from(_).toOption)(_.value)

  given derivedNonPosDoubleSchema(using actualSchema: Schema[Double]): Schema[NonPosDouble] =
    actualSchema.map(NonPosDouble.from(_).toOption)(_.value)

  given derivedNegBigIntSchema(using actualSchema: Schema[BigInt]): Schema[NegBigInt] =
    actualSchema.map(NegBigInt.from(_).toOption)(_.value)

  given derivedNonNegBigIntSchema(using actualSchema: Schema[BigInt]): Schema[NonNegBigInt] =
    actualSchema.map(NonNegBigInt.from(_).toOption)(_.value)

  given derivedPosBigIntSchema(using actualSchema: Schema[BigInt]): Schema[PosBigInt] =
    actualSchema.map(PosBigInt.from(_).toOption)(_.value)

  given derivedNonPosBigIntSchema(using actualSchema: Schema[BigInt]): Schema[NonPosBigInt] =
    actualSchema.map(NonPosBigInt.from(_).toOption)(_.value)

  given derivedNegBigDecimalSchema(using actualSchema: Schema[BigDecimal]): Schema[NegBigDecimal] =
    actualSchema.map(NegBigDecimal.from(_).toOption)(_.value)

  given derivedNonNegBigDecimalSchema(using actualSchema: Schema[BigDecimal]): Schema[NonNegBigDecimal] =
    actualSchema.map(NonNegBigDecimal.from(_).toOption)(_.value)

  given derivedPosBigDecimalSchema(using actualSchema: Schema[BigDecimal]): Schema[PosBigDecimal] =
    actualSchema.map(PosBigDecimal.from(_).toOption)(_.value)

  given derivedNonPosBigDecimalSchema(using actualSchema: Schema[BigDecimal]): Schema[NonPosBigDecimal] =
    actualSchema.map(NonPosBigDecimal.from(_).toOption)(_.value)

}
