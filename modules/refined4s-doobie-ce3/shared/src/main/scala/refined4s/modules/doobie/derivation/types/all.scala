package refined4s.modules.doobie.derivation.types

import cats.Show
import doobie.{Get, Put}
import refined4s.types.all.*

/** @author Kevin Lee
  * @since 2023-12-27
  */
trait all {

  inline given derivedNegIntGet(using Show[Int]): Get[NegInt] = Get[Int].temap(NegInt.from)
  inline given derivedNegIntPut: Put[NegInt]                  = Put[Int].contramap(_.value)

  inline given derivedNonNegIntGet(using Show[Int]): Get[NonNegInt] = Get[Int].temap(NonNegInt.from)
  inline given derivedNonNegIntPut: Put[NonNegInt]                  = Put[Int].contramap(_.value)

  inline given derivedPosIntGet(using Show[Int]): Get[PosInt] = Get[Int].temap(PosInt.from)
  inline given derivedPosIntPut: Put[PosInt]                  = Put[Int].contramap(_.value)

  inline given derivedNonPosIntGet(using Show[Int]): Get[NonPosInt] = Get[Int].temap(NonPosInt.from)
  inline given derivedNonPosIntPut: Put[NonPosInt]                  = Put[Int].contramap(_.value)

  inline given derivedNegLongGet(using Show[Long]): Get[NegLong] = Get[Long].temap(NegLong.from)
  inline given derivedNegLongPut: Put[NegLong]                   = Put[Long].contramap(_.value)

  inline given derivedNonNegLongGet(using Show[Long]): Get[NonNegLong] = Get[Long].temap(NonNegLong.from)
  inline given derivedNonNegLongPut: Put[NonNegLong]                   = Put[Long].contramap(_.value)

  inline given derivedPosLongGet(using Show[Long]): Get[PosLong] = Get[Long].temap(PosLong.from)
  inline given derivedPosLongPut: Put[PosLong]                   = Put[Long].contramap(_.value)

  inline given derivedNonPosLongGet(using Show[Long]): Get[NonPosLong] = Get[Long].temap(NonPosLong.from)
  inline given derivedNonPosLongPut: Put[NonPosLong]                   = Put[Long].contramap(_.value)

  inline given derivedNegShortGet(using Show[Short]): Get[NegShort] = Get[Short].temap(NegShort.from)
  inline given derivedNegShortPut: Put[NegShort]                    = Put[Short].contramap(_.value)

  inline given derivedNonNegShortGet(using Show[Short]): Get[NonNegShort] = Get[Short].temap(NonNegShort.from)
  inline given derivedNonNegShortPut: Put[NonNegShort]                    = Put[Short].contramap(_.value)

  inline given derivedPosShortGet(using Show[Short]): Get[PosShort] = Get[Short].temap(PosShort.from)
  inline given derivedPosShortPut: Put[PosShort]                    = Put[Short].contramap(_.value)

  inline given derivedNonPosShortGet(using Show[Short]): Get[NonPosShort] = Get[Short].temap(NonPosShort.from)
  inline given derivedNonPosShortPut: Put[NonPosShort]                    = Put[Short].contramap(_.value)

  inline given derivedNegByteGet(using Show[Byte]): Get[NegByte] = Get[Byte].temap(NegByte.from)
  inline given derivedNegBytePut: Put[NegByte]                   = Put[Byte].contramap(_.value)

  inline given derivedNonNegByteGet(using Show[Byte]): Get[NonNegByte] = Get[Byte].temap(NonNegByte.from)
  inline given derivedNonNegBytePut: Put[NonNegByte]                   = Put[Byte].contramap(_.value)

  inline given derivedPosByteGet(using Show[Byte]): Get[PosByte] = Get[Byte].temap(PosByte.from)
  inline given derivedPosBytePut: Put[PosByte]                   = Put[Byte].contramap(_.value)

  inline given derivedNonPosByteGet(using Show[Byte]): Get[NonPosByte] = Get[Byte].temap(NonPosByte.from)
  inline given derivedNonPosBytePut: Put[NonPosByte]                   = Put[Byte].contramap(_.value)

  inline given derivedNegFloatGet(using Show[BigDecimal]): Get[NegFloat] = Get[BigDecimal].temap(n => NegFloat.from(n.toFloat))
  inline given derivedNegFloatPut: Put[NegFloat]                         = Put[BigDecimal].contramap(n => BigDecimal(n.value))

  inline given derivedNonNegFloatGet(using Show[BigDecimal]): Get[NonNegFloat] = Get[BigDecimal].temap(n => NonNegFloat.from(n.toFloat))
  inline given derivedNonNegFloatPut: Put[NonNegFloat]                         = Put[BigDecimal].contramap(n => BigDecimal(n.value))

  inline given derivedPosFloatGet(using Show[BigDecimal]): Get[PosFloat] = Get[BigDecimal].temap(n => PosFloat.from(n.toFloat))
  inline given derivedPosFloatPut: Put[PosFloat]                         = Put[BigDecimal].contramap(n => BigDecimal(n.value))

  inline given derivedNonPosFloatGet(using Show[BigDecimal]): Get[NonPosFloat] = Get[BigDecimal].temap(n => NonPosFloat.from(n.toFloat))
  inline given derivedNonPosFloatPut: Put[NonPosFloat]                         = Put[BigDecimal].contramap(n => BigDecimal(n.value))

  inline given derivedNegDoubleGet(using Show[BigDecimal]): Get[NegDouble] = Get[BigDecimal].temap(n => NegDouble.from(n.toDouble))
  inline given derivedNegDoublePut: Put[NegDouble]                         = Put[BigDecimal].contramap(n => BigDecimal(n.value))

  inline given derivedNonNegDoubleGet(using Show[BigDecimal]): Get[NonNegDouble] = Get[BigDecimal].temap(n => NonNegDouble.from(n.toDouble))
  inline given derivedNonNegDoublePut: Put[NonNegDouble]                         = Put[BigDecimal].contramap(n => BigDecimal(n.value))

  inline given derivedPosDoubleGet(using Show[BigDecimal]): Get[PosDouble] = Get[BigDecimal].temap(n => PosDouble.from(n.toDouble))
  inline given derivedPosDoublePut: Put[PosDouble]                         = Put[BigDecimal].contramap(n => BigDecimal(n.value))

  inline given derivedNonPosDoubleGet(using Show[BigDecimal]): Get[NonPosDouble] = Get[BigDecimal].temap(n => NonPosDouble.from(n.toDouble))
  inline given derivedNonPosDoublePut: Put[NonPosDouble]                         = Put[BigDecimal].contramap(n => BigDecimal(n.value))

  inline given derivedNegBigIntGet(using Show[BigDecimal]): Get[NegBigInt] = Get[BigDecimal].temap(n => NegBigInt.from(n.toBigInt))
  inline given derivedNegBigIntPut: Put[NegBigInt]                         = Put[BigDecimal].contramap(n => BigDecimal(n.value))

  inline given derivedNonNegBigIntGet(using Show[BigInt]): Get[NonNegBigInt] = Get[BigDecimal].temap(n => NonNegBigInt.from(n.toBigInt))
  inline given derivedNonNegBigIntPut: Put[NonNegBigInt]                     = Put[BigDecimal].contramap(n => BigDecimal(n.value))

  inline given derivedPosBigIntGet(using Show[BigInt]): Get[PosBigInt] = Get[BigDecimal].temap(n => PosBigInt.from(n.toBigInt))
  inline given derivedPosBigIntPut: Put[PosBigInt]                     = Put[BigDecimal].contramap(n => BigDecimal(n.value))

  inline given derivedNonPosBigIntGet(using Show[BigInt]): Get[NonPosBigInt] = Get[BigDecimal].temap(n => NonPosBigInt.from(n.toBigInt))
  inline given derivedNonPosBigIntPut: Put[NonPosBigInt]                     = Put[BigDecimal].contramap(n => BigDecimal(n.value))

  inline given derivedNegBigDecimalGet(using Show[BigDecimal]): Get[NegBigDecimal] = Get[BigDecimal].temap(NegBigDecimal.from)
  inline given derivedNegBigDecimalPut: Put[NegBigDecimal]                         = Put[BigDecimal].contramap(_.value)

  inline given derivedNonNegBigDecimalGet(using Show[BigDecimal]): Get[NonNegBigDecimal] = Get[BigDecimal].temap(NonNegBigDecimal.from)
  inline given derivedNonNegBigDecimalPut: Put[NonNegBigDecimal]                         = Put[BigDecimal].contramap(_.value)

  inline given derivedPosBigDecimalGet(using Show[BigDecimal]): Get[PosBigDecimal] = Get[BigDecimal].temap(PosBigDecimal.from)
  inline given derivedPosBigDecimalPut: Put[PosBigDecimal]                         = Put[BigDecimal].contramap(_.value)

  inline given derivedNonPosBigDecimalGet(using Show[BigDecimal]): Get[NonPosBigDecimal] = Get[BigDecimal].temap(NonPosBigDecimal.from)
  inline given derivedNonPosBigDecimalPut: Put[NonPosBigDecimal]                         = Put[BigDecimal].contramap(_.value)

  // strings
  inline given derivedNonEmptyStringGet(using Show[String]): Get[NonEmptyString] = Get[String].temap(NonEmptyString.from)
  inline given derivedNonEmptyStringPut: Put[NonEmptyString]                     = Put[String].contramap(_.value)

  // network
  inline given derivedUriGet(using Show[String]): Get[Uri] = Get[String].temap(Uri.from)
  inline given derivedUriPut: Put[Uri]                     = Put[String].contramap(_.value)

  inline given derivedUrlGet(using Show[String]): Get[Url] = Get[String].temap(Url.from)
  inline given derivedUrlPut: Put[Url]                     = Put[String].contramap(_.value)

  inline given derivedPortNumberGet(using Show[Int]): Get[PortNumber] = Get[Int].temap(PortNumber.from)
  inline given derivedPortNumberPut: Put[PortNumber]                  = Put[Int].contramap(_.value)

  inline given derivedSystemPortNumberGet(using Show[Int]): Get[SystemPortNumber] = Get[Int].temap(SystemPortNumber.from)
  inline given derivedSystemPortNumberPut: Put[SystemPortNumber]                  = Put[Int].contramap(_.value)

  inline given derivedNonSystemPortNumberGet(using Show[Int]): Get[NonSystemPortNumber] = Get[Int].temap(NonSystemPortNumber.from)
  inline given derivedNonSystemPortNumberPut: Put[NonSystemPortNumber]                  = Put[Int].contramap(_.value)

  inline given derivedUserPortNumberGet(using Show[Int]): Get[UserPortNumber] = Get[Int].temap(UserPortNumber.from)
  inline given derivedUserPortNumberPut: Put[UserPortNumber]                  = Put[Int].contramap(_.value)

  inline given derivedDynamicPortNumberGet(using Show[Int]): Get[DynamicPortNumber] = Get[Int].temap(DynamicPortNumber.from)
  inline given derivedDynamicPortNumberPut: Put[DynamicPortNumber]                  = Put[Int].contramap(_.value)

}
object all extends all
