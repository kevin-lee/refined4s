package refined4s.modules.doobie.derivation.types

import cats.Show
import doobie.{Get, Put}
import refined4s.types.numeric.*

/** @author Kevin Lee
  * @since 2025-09-02
  */
trait numeric {

  inline given derivedNegIntGet: Get[NegInt] = numeric.derivedNegIntGet
  inline given derivedNegIntPut: Put[NegInt] = numeric.derivedNegIntPut

  inline given derivedNonNegIntGet: Get[NonNegInt] = numeric.derivedNonNegIntGet
  inline given derivedNonNegIntPut: Put[NonNegInt] = numeric.derivedNonNegIntPut

  inline given derivedPosIntGet: Get[PosInt] = numeric.derivedPosIntGet
  inline given derivedPosIntPut: Put[PosInt] = numeric.derivedPosIntPut

  inline given derivedNonPosIntGet: Get[NonPosInt] = numeric.derivedNonPosIntGet
  inline given derivedNonPosIntPut: Put[NonPosInt] = numeric.derivedNonPosIntPut

  inline given derivedNegLongGet: Get[NegLong] = numeric.derivedNegLongGet
  inline given derivedNegLongPut: Put[NegLong] = numeric.derivedNegLongPut

  inline given derivedNonNegLongGet: Get[NonNegLong] = numeric.derivedNonNegLongGet
  inline given derivedNonNegLongPut: Put[NonNegLong] = numeric.derivedNonNegLongPut

  inline given derivedPosLongGet: Get[PosLong] = numeric.derivedPosLongGet
  inline given derivedPosLongPut: Put[PosLong] = numeric.derivedPosLongPut

  inline given derivedNonPosLongGet: Get[NonPosLong] = numeric.derivedNonPosLongGet
  inline given derivedNonPosLongPut: Put[NonPosLong] = numeric.derivedNonPosLongPut

  inline given derivedNegShortGet: Get[NegShort] = numeric.derivedNegShortGet
  inline given derivedNegShortPut: Put[NegShort] = numeric.derivedNegShortPut

  inline given derivedNonNegShortGet: Get[NonNegShort] = numeric.derivedNonNegShortGet
  inline given derivedNonNegShortPut: Put[NonNegShort] = numeric.derivedNonNegShortPut

  inline given derivedPosShortGet: Get[PosShort] = numeric.derivedPosShortGet
  inline given derivedPosShortPut: Put[PosShort] = numeric.derivedPosShortPut

  inline given derivedNonPosShortGet: Get[NonPosShort] = numeric.derivedNonPosShortGet
  inline given derivedNonPosShortPut: Put[NonPosShort] = numeric.derivedNonPosShortPut

  inline given derivedNegByteGet: Get[NegByte] = numeric.derivedNegByteGet
  inline given derivedNegBytePut: Put[NegByte] = numeric.derivedNegBytePut

  inline given derivedNonNegByteGet: Get[NonNegByte] = numeric.derivedNonNegByteGet
  inline given derivedNonNegBytePut: Put[NonNegByte] = numeric.derivedNonNegBytePut

  inline given derivedPosByteGet: Get[PosByte] = numeric.derivedPosByteGet
  inline given derivedPosBytePut: Put[PosByte] = numeric.derivedPosBytePut

  inline given derivedNonPosByteGet: Get[NonPosByte] = numeric.derivedNonPosByteGet
  inline given derivedNonPosBytePut: Put[NonPosByte] = numeric.derivedNonPosBytePut

  inline given derivedNegFloatGet: Get[NegFloat] = numeric.derivedNegFloatGet
  inline given derivedNegFloatPut: Put[NegFloat] = numeric.derivedNegFloatPut

  inline given derivedNonNegFloatGet: Get[NonNegFloat] = numeric.derivedNonNegFloatGet
  inline given derivedNonNegFloatPut: Put[NonNegFloat] = numeric.derivedNonNegFloatPut

  inline given derivedPosFloatGet: Get[PosFloat] = numeric.derivedPosFloatGet
  inline given derivedPosFloatPut: Put[PosFloat] = numeric.derivedPosFloatPut

  inline given derivedNonPosFloatGet: Get[NonPosFloat] = numeric.derivedNonPosFloatGet
  inline given derivedNonPosFloatPut: Put[NonPosFloat] = numeric.derivedNonPosFloatPut

  inline given derivedNegDoubleGet: Get[NegDouble] = numeric.derivedNegDoubleGet
  inline given derivedNegDoublePut: Put[NegDouble] = numeric.derivedNegDoublePut

  inline given derivedNonNegDoubleGet: Get[NonNegDouble] = numeric.derivedNonNegDoubleGet
  inline given derivedNonNegDoublePut: Put[NonNegDouble] = numeric.derivedNonNegDoublePut

  inline given derivedPosDoubleGet: Get[PosDouble] = numeric.derivedPosDoubleGet
  inline given derivedPosDoublePut: Put[PosDouble] = numeric.derivedPosDoublePut

  inline given derivedNonPosDoubleGet: Get[NonPosDouble] = numeric.derivedNonPosDoubleGet
  inline given derivedNonPosDoublePut: Put[NonPosDouble] = numeric.derivedNonPosDoublePut

  inline given derivedNegBigIntGet: Get[NegBigInt] = numeric.derivedNegBigIntGet
  inline given derivedNegBigIntPut: Put[NegBigInt] = numeric.derivedNegBigIntPut

  inline given derivedNonNegBigIntGet: Get[NonNegBigInt] = numeric.derivedNonNegBigIntGet
  inline given derivedNonNegBigIntPut: Put[NonNegBigInt] = numeric.derivedNonNegBigIntPut

  inline given derivedPosBigIntGet: Get[PosBigInt] = numeric.derivedPosBigIntGet
  inline given derivedPosBigIntPut: Put[PosBigInt] = numeric.derivedPosBigIntPut

  inline given derivedNonPosBigIntGet: Get[NonPosBigInt] = numeric.derivedNonPosBigIntGet
  inline given derivedNonPosBigIntPut: Put[NonPosBigInt] = numeric.derivedNonPosBigIntPut

  inline given derivedNegBigDecimalGet: Get[NegBigDecimal] = numeric.derivedNegBigDecimalGet
  inline given derivedNegBigDecimalPut: Put[NegBigDecimal] = numeric.derivedNegBigDecimalPut

  inline given derivedNonNegBigDecimalGet: Get[NonNegBigDecimal] = numeric.derivedNonNegBigDecimalGet
  inline given derivedNonNegBigDecimalPut: Put[NonNegBigDecimal] = numeric.derivedNonNegBigDecimalPut

  inline given derivedPosBigDecimalGet: Get[PosBigDecimal] = numeric.derivedPosBigDecimalGet
  inline given derivedPosBigDecimalPut: Put[PosBigDecimal] = numeric.derivedPosBigDecimalPut

  inline given derivedNonPosBigDecimalGet: Get[NonPosBigDecimal] = numeric.derivedNonPosBigDecimalGet
  inline given derivedNonPosBigDecimalPut: Put[NonPosBigDecimal] = numeric.derivedNonPosBigDecimalPut

}
object numeric {

  given derivedNegIntGet(using Show[Int]): Get[NegInt] = Get[Int].temap(NegInt.from)
  given derivedNegIntPut: Put[NegInt]                  = Put[Int].contramap(_.value)

  given derivedNonNegIntGet(using Show[Int]): Get[NonNegInt] = Get[Int].temap(NonNegInt.from)
  given derivedNonNegIntPut: Put[NonNegInt]                  = Put[Int].contramap(_.value)

  given derivedPosIntGet(using Show[Int]): Get[PosInt] = Get[Int].temap(PosInt.from)
  given derivedPosIntPut: Put[PosInt]                  = Put[Int].contramap(_.value)

  given derivedNonPosIntGet(using Show[Int]): Get[NonPosInt] = Get[Int].temap(NonPosInt.from)
  given derivedNonPosIntPut: Put[NonPosInt]                  = Put[Int].contramap(_.value)

  given derivedNegLongGet(using Show[Long]): Get[NegLong] = Get[Long].temap(NegLong.from)
  given derivedNegLongPut: Put[NegLong]                   = Put[Long].contramap(_.value)

  given derivedNonNegLongGet(using Show[Long]): Get[NonNegLong] = Get[Long].temap(NonNegLong.from)
  given derivedNonNegLongPut: Put[NonNegLong]                   = Put[Long].contramap(_.value)

  given derivedPosLongGet(using Show[Long]): Get[PosLong] = Get[Long].temap(PosLong.from)
  given derivedPosLongPut: Put[PosLong]                   = Put[Long].contramap(_.value)

  given derivedNonPosLongGet(using Show[Long]): Get[NonPosLong] = Get[Long].temap(NonPosLong.from)
  given derivedNonPosLongPut: Put[NonPosLong]                   = Put[Long].contramap(_.value)

  given derivedNegShortGet(using Show[Short]): Get[NegShort] = Get[Short].temap(NegShort.from)
  given derivedNegShortPut: Put[NegShort]                    = Put[Short].contramap(_.value)

  given derivedNonNegShortGet(using Show[Short]): Get[NonNegShort] = Get[Short].temap(NonNegShort.from)
  given derivedNonNegShortPut: Put[NonNegShort]                    = Put[Short].contramap(_.value)

  given derivedPosShortGet(using Show[Short]): Get[PosShort] = Get[Short].temap(PosShort.from)
  given derivedPosShortPut: Put[PosShort]                    = Put[Short].contramap(_.value)

  given derivedNonPosShortGet(using Show[Short]): Get[NonPosShort] = Get[Short].temap(NonPosShort.from)
  given derivedNonPosShortPut: Put[NonPosShort]                    = Put[Short].contramap(_.value)

  given derivedNegByteGet(using Show[Byte]): Get[NegByte] = Get[Byte].temap(NegByte.from)
  given derivedNegBytePut: Put[NegByte]                   = Put[Byte].contramap(_.value)

  given derivedNonNegByteGet(using Show[Byte]): Get[NonNegByte] = Get[Byte].temap(NonNegByte.from)
  given derivedNonNegBytePut: Put[NonNegByte]                   = Put[Byte].contramap(_.value)

  given derivedPosByteGet(using Show[Byte]): Get[PosByte] = Get[Byte].temap(PosByte.from)
  given derivedPosBytePut: Put[PosByte]                   = Put[Byte].contramap(_.value)

  given derivedNonPosByteGet(using Show[Byte]): Get[NonPosByte] = Get[Byte].temap(NonPosByte.from)
  given derivedNonPosBytePut: Put[NonPosByte]                   = Put[Byte].contramap(_.value)

  given derivedNegFloatGet(using Show[BigDecimal]): Get[NegFloat] = Get[BigDecimal].temap(n => NegFloat.from(n.toFloat))
  given derivedNegFloatPut: Put[NegFloat]                         = Put[BigDecimal].contramap(n => BigDecimal(n.value))

  given derivedNonNegFloatGet(using Show[BigDecimal]): Get[NonNegFloat] = Get[BigDecimal].temap(n => NonNegFloat.from(n.toFloat))
  given derivedNonNegFloatPut: Put[NonNegFloat]                         = Put[BigDecimal].contramap(n => BigDecimal(n.value))

  given derivedPosFloatGet(using Show[BigDecimal]): Get[PosFloat] = Get[BigDecimal].temap(n => PosFloat.from(n.toFloat))
  given derivedPosFloatPut: Put[PosFloat]                         = Put[BigDecimal].contramap(n => BigDecimal(n.value))

  given derivedNonPosFloatGet(using Show[BigDecimal]): Get[NonPosFloat] = Get[BigDecimal].temap(n => NonPosFloat.from(n.toFloat))
  given derivedNonPosFloatPut: Put[NonPosFloat]                         = Put[BigDecimal].contramap(n => BigDecimal(n.value))

  given derivedNegDoubleGet(using Show[BigDecimal]): Get[NegDouble] = Get[BigDecimal].temap(n => NegDouble.from(n.toDouble))
  given derivedNegDoublePut: Put[NegDouble]                         = Put[BigDecimal].contramap(n => BigDecimal(n.value))

  given derivedNonNegDoubleGet(using Show[BigDecimal]): Get[NonNegDouble] = Get[BigDecimal].temap(n => NonNegDouble.from(n.toDouble))
  given derivedNonNegDoublePut: Put[NonNegDouble]                         = Put[BigDecimal].contramap(n => BigDecimal(n.value))

  given derivedPosDoubleGet(using Show[BigDecimal]): Get[PosDouble] = Get[BigDecimal].temap(n => PosDouble.from(n.toDouble))
  given derivedPosDoublePut: Put[PosDouble]                         = Put[BigDecimal].contramap(n => BigDecimal(n.value))

  given derivedNonPosDoubleGet(using Show[BigDecimal]): Get[NonPosDouble] = Get[BigDecimal].temap(n => NonPosDouble.from(n.toDouble))
  given derivedNonPosDoublePut: Put[NonPosDouble]                         = Put[BigDecimal].contramap(n => BigDecimal(n.value))

  given derivedNegBigIntGet(using Show[BigDecimal]): Get[NegBigInt] = Get[BigDecimal].temap(n => NegBigInt.from(n.toBigInt))
  given derivedNegBigIntPut: Put[NegBigInt]                         = Put[BigDecimal].contramap(n => BigDecimal(n.value))

  given derivedNonNegBigIntGet(using Show[BigInt]): Get[NonNegBigInt] = Get[BigDecimal].temap(n => NonNegBigInt.from(n.toBigInt))
  given derivedNonNegBigIntPut: Put[NonNegBigInt]                     = Put[BigDecimal].contramap(n => BigDecimal(n.value))

  given derivedPosBigIntGet(using Show[BigInt]): Get[PosBigInt] = Get[BigDecimal].temap(n => PosBigInt.from(n.toBigInt))
  given derivedPosBigIntPut: Put[PosBigInt]                     = Put[BigDecimal].contramap(n => BigDecimal(n.value))

  given derivedNonPosBigIntGet(using Show[BigInt]): Get[NonPosBigInt] = Get[BigDecimal].temap(n => NonPosBigInt.from(n.toBigInt))
  given derivedNonPosBigIntPut: Put[NonPosBigInt]                     = Put[BigDecimal].contramap(n => BigDecimal(n.value))

  given derivedNegBigDecimalGet(using Show[BigDecimal]): Get[NegBigDecimal] = Get[BigDecimal].temap(NegBigDecimal.from)
  given derivedNegBigDecimalPut: Put[NegBigDecimal]                         = Put[BigDecimal].contramap(_.value)

  given derivedNonNegBigDecimalGet(using Show[BigDecimal]): Get[NonNegBigDecimal] = Get[BigDecimal].temap(NonNegBigDecimal.from)
  given derivedNonNegBigDecimalPut: Put[NonNegBigDecimal]                         = Put[BigDecimal].contramap(_.value)

  given derivedPosBigDecimalGet(using Show[BigDecimal]): Get[PosBigDecimal] = Get[BigDecimal].temap(PosBigDecimal.from)
  given derivedPosBigDecimalPut: Put[PosBigDecimal]                         = Put[BigDecimal].contramap(_.value)

  given derivedNonPosBigDecimalGet(using Show[BigDecimal]): Get[NonPosBigDecimal] = Get[BigDecimal].temap(NonPosBigDecimal.from)
  given derivedNonPosBigDecimalPut: Put[NonPosBigDecimal]                         = Put[BigDecimal].contramap(_.value)

}
