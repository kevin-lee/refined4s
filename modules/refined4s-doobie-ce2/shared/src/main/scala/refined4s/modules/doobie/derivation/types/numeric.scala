package refined4s.modules.doobie.derivation.types

import cats.Show
import doobie.{Get, Put}
import refined4s.types.numeric.*

/** @author Kevin Lee
  * @since 2025-09-02
  */
trait numeric {

  given derivedNegIntGet: Get[NegInt] = numeric.derivedNegIntGet
  given derivedNegIntPut: Put[NegInt] = numeric.derivedNegIntPut

  given derivedNonNegIntGet: Get[NonNegInt] = numeric.derivedNonNegIntGet
  given derivedNonNegIntPut: Put[NonNegInt] = numeric.derivedNonNegIntPut

  given derivedPosIntGet: Get[PosInt] = numeric.derivedPosIntGet
  given derivedPosIntPut: Put[PosInt] = numeric.derivedPosIntPut

  given derivedNonPosIntGet: Get[NonPosInt] = numeric.derivedNonPosIntGet
  given derivedNonPosIntPut: Put[NonPosInt] = numeric.derivedNonPosIntPut

  given derivedNegLongGet: Get[NegLong] = numeric.derivedNegLongGet
  given derivedNegLongPut: Put[NegLong] = numeric.derivedNegLongPut

  given derivedNonNegLongGet: Get[NonNegLong] = numeric.derivedNonNegLongGet
  given derivedNonNegLongPut: Put[NonNegLong] = numeric.derivedNonNegLongPut

  given derivedPosLongGet: Get[PosLong] = numeric.derivedPosLongGet
  given derivedPosLongPut: Put[PosLong] = numeric.derivedPosLongPut

  given derivedNonPosLongGet: Get[NonPosLong] = numeric.derivedNonPosLongGet
  given derivedNonPosLongPut: Put[NonPosLong] = numeric.derivedNonPosLongPut

  given derivedNegShortGet: Get[NegShort] = numeric.derivedNegShortGet
  given derivedNegShortPut: Put[NegShort] = numeric.derivedNegShortPut

  given derivedNonNegShortGet: Get[NonNegShort] = numeric.derivedNonNegShortGet
  given derivedNonNegShortPut: Put[NonNegShort] = numeric.derivedNonNegShortPut

  given derivedPosShortGet: Get[PosShort] = numeric.derivedPosShortGet
  given derivedPosShortPut: Put[PosShort] = numeric.derivedPosShortPut

  given derivedNonPosShortGet: Get[NonPosShort] = numeric.derivedNonPosShortGet
  given derivedNonPosShortPut: Put[NonPosShort] = numeric.derivedNonPosShortPut

  given derivedNegByteGet: Get[NegByte] = numeric.derivedNegByteGet
  given derivedNegBytePut: Put[NegByte] = numeric.derivedNegBytePut

  given derivedNonNegByteGet: Get[NonNegByte] = numeric.derivedNonNegByteGet
  given derivedNonNegBytePut: Put[NonNegByte] = numeric.derivedNonNegBytePut

  given derivedPosByteGet: Get[PosByte] = numeric.derivedPosByteGet
  given derivedPosBytePut: Put[PosByte] = numeric.derivedPosBytePut

  given derivedNonPosByteGet: Get[NonPosByte] = numeric.derivedNonPosByteGet
  given derivedNonPosBytePut: Put[NonPosByte] = numeric.derivedNonPosBytePut

  given derivedNegFloatGet: Get[NegFloat] = numeric.derivedNegFloatGet
  given derivedNegFloatPut: Put[NegFloat] = numeric.derivedNegFloatPut

  given derivedNonNegFloatGet: Get[NonNegFloat] = numeric.derivedNonNegFloatGet
  given derivedNonNegFloatPut: Put[NonNegFloat] = numeric.derivedNonNegFloatPut

  given derivedPosFloatGet: Get[PosFloat] = numeric.derivedPosFloatGet
  given derivedPosFloatPut: Put[PosFloat] = numeric.derivedPosFloatPut

  given derivedNonPosFloatGet: Get[NonPosFloat] = numeric.derivedNonPosFloatGet
  given derivedNonPosFloatPut: Put[NonPosFloat] = numeric.derivedNonPosFloatPut

  given derivedNegDoubleGet: Get[NegDouble] = numeric.derivedNegDoubleGet
  given derivedNegDoublePut: Put[NegDouble] = numeric.derivedNegDoublePut

  given derivedNonNegDoubleGet: Get[NonNegDouble] = numeric.derivedNonNegDoubleGet
  given derivedNonNegDoublePut: Put[NonNegDouble] = numeric.derivedNonNegDoublePut

  given derivedPosDoubleGet: Get[PosDouble] = numeric.derivedPosDoubleGet
  given derivedPosDoublePut: Put[PosDouble] = numeric.derivedPosDoublePut

  given derivedNonPosDoubleGet: Get[NonPosDouble] = numeric.derivedNonPosDoubleGet
  given derivedNonPosDoublePut: Put[NonPosDouble] = numeric.derivedNonPosDoublePut

  given derivedNegBigIntGet: Get[NegBigInt] = numeric.derivedNegBigIntGet
  given derivedNegBigIntPut: Put[NegBigInt] = numeric.derivedNegBigIntPut

  given derivedNonNegBigIntGet: Get[NonNegBigInt] = numeric.derivedNonNegBigIntGet
  given derivedNonNegBigIntPut: Put[NonNegBigInt] = numeric.derivedNonNegBigIntPut

  given derivedPosBigIntGet: Get[PosBigInt] = numeric.derivedPosBigIntGet
  given derivedPosBigIntPut: Put[PosBigInt] = numeric.derivedPosBigIntPut

  given derivedNonPosBigIntGet: Get[NonPosBigInt] = numeric.derivedNonPosBigIntGet
  given derivedNonPosBigIntPut: Put[NonPosBigInt] = numeric.derivedNonPosBigIntPut

  given derivedNegBigDecimalGet: Get[NegBigDecimal] = numeric.derivedNegBigDecimalGet
  given derivedNegBigDecimalPut: Put[NegBigDecimal] = numeric.derivedNegBigDecimalPut

  given derivedNonNegBigDecimalGet: Get[NonNegBigDecimal] = numeric.derivedNonNegBigDecimalGet
  given derivedNonNegBigDecimalPut: Put[NonNegBigDecimal] = numeric.derivedNonNegBigDecimalPut

  given derivedPosBigDecimalGet: Get[PosBigDecimal] = numeric.derivedPosBigDecimalGet
  given derivedPosBigDecimalPut: Put[PosBigDecimal] = numeric.derivedPosBigDecimalPut

  given derivedNonPosBigDecimalGet: Get[NonPosBigDecimal] = numeric.derivedNonPosBigDecimalGet
  given derivedNonPosBigDecimalPut: Put[NonPosBigDecimal] = numeric.derivedNonPosBigDecimalPut

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
