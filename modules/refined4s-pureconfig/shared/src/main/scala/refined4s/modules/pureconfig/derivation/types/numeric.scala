package refined4s.modules.pureconfig.derivation.types

import com.typesafe.config.ConfigValue
import pureconfig.error.UserValidationFailed
import pureconfig.{ConfigReader, ConfigWriter}
import refined4s.types.numeric.*

/** @author Kevin Lee
  * @since 2025-09-02
  */
trait numeric {

  given derivedNegIntConfigReader: ConfigReader[NegInt] = numeric.derivedNegIntConfigReader
  given derivedNegIntConfigWriter: ConfigWriter[NegInt] = numeric.derivedNegIntConfigWriter

  given derivedNonNegIntConfigReader: ConfigReader[NonNegInt] = numeric.derivedNonNegIntConfigReader
  given derivedNonNegIntConfigWriter: ConfigWriter[NonNegInt] = numeric.derivedNonNegIntConfigWriter

  given derivedPosIntConfigReader: ConfigReader[PosInt] = numeric.derivedPosIntConfigReader
  given derivedPosIntConfigWriter: ConfigWriter[PosInt] = numeric.derivedPosIntConfigWriter

  given derivedNonPosIntConfigReader: ConfigReader[NonPosInt] = numeric.derivedNonPosIntConfigReader
  given derivedNonPosIntConfigWriter: ConfigWriter[NonPosInt] = numeric.derivedNonPosIntConfigWriter

  given derivedNegLongConfigReader: ConfigReader[NegLong] = numeric.derivedNegLongConfigReader
  given derivedNegLongConfigWriter: ConfigWriter[NegLong] = numeric.derivedNegLongConfigWriter

  given derivedNonNegLongConfigReader: ConfigReader[NonNegLong] = numeric.derivedNonNegLongConfigReader
  given derivedNonNegLongConfigWriter: ConfigWriter[NonNegLong] = numeric.derivedNonNegLongConfigWriter

  given derivedPosLongConfigReader: ConfigReader[PosLong] = numeric.derivedPosLongConfigReader
  given derivedPosLongConfigWriter: ConfigWriter[PosLong] = numeric.derivedPosLongConfigWriter

  given derivedNonPosLongConfigReader: ConfigReader[NonPosLong] = numeric.derivedNonPosLongConfigReader
  given derivedNonPosLongConfigWriter: ConfigWriter[NonPosLong] = numeric.derivedNonPosLongConfigWriter

  given derivedNegShortConfigReader: ConfigReader[NegShort] = numeric.derivedNegShortConfigReader
  given derivedNegShortConfigWriter: ConfigWriter[NegShort] = numeric.derivedNegShortConfigWriter

  given derivedNonNegShortConfigReader: ConfigReader[NonNegShort] = numeric.derivedNonNegShortConfigReader
  given derivedNonNegShortConfigWriter: ConfigWriter[NonNegShort] = numeric.derivedNonNegShortConfigWriter

  given derivedPosShortConfigReader: ConfigReader[PosShort] = numeric.derivedPosShortConfigReader
  given derivedPosShortConfigWriter: ConfigWriter[PosShort] = numeric.derivedPosShortConfigWriter

  given derivedNonPosShortConfigReader: ConfigReader[NonPosShort] = numeric.derivedNonPosShortConfigReader
  given derivedNonPosShortConfigWriter: ConfigWriter[NonPosShort] = numeric.derivedNonPosShortConfigWriter

  given derivedNegByteConfigReader: ConfigReader[NegByte] = numeric.derivedNegByteConfigReader
  given derivedNegByteConfigWriter: ConfigWriter[NegByte] = numeric.derivedNegByteConfigWriter

  given derivedNonNegByteConfigReader: ConfigReader[NonNegByte] = numeric.derivedNonNegByteConfigReader
  given derivedNonNegByteConfigWriter: ConfigWriter[NonNegByte] = numeric.derivedNonNegByteConfigWriter

  given derivedPosByteConfigReader: ConfigReader[PosByte] = numeric.derivedPosByteConfigReader
  given derivedPosByteConfigWriter: ConfigWriter[PosByte] = numeric.derivedPosByteConfigWriter

  given derivedNonPosByteConfigReader: ConfigReader[NonPosByte] = numeric.derivedNonPosByteConfigReader
  given derivedNonPosByteConfigWriter: ConfigWriter[NonPosByte] = numeric.derivedNonPosByteConfigWriter

  given derivedNegFloatConfigReader: ConfigReader[NegFloat] = numeric.derivedNegFloatConfigReader
  given derivedNegFloatConfigWriter: ConfigWriter[NegFloat] = numeric.derivedNegFloatConfigWriter

  given derivedNonNegFloatConfigReader: ConfigReader[NonNegFloat] = numeric.derivedNonNegFloatConfigReader
  given derivedNonNegFloatConfigWriter: ConfigWriter[NonNegFloat] = numeric.derivedNonNegFloatConfigWriter

  given derivedPosFloatConfigReader: ConfigReader[PosFloat] = numeric.derivedPosFloatConfigReader
  given derivedPosFloatConfigWriter: ConfigWriter[PosFloat] = numeric.derivedPosFloatConfigWriter

  given derivedNonPosFloatConfigReader: ConfigReader[NonPosFloat] = numeric.derivedNonPosFloatConfigReader
  given derivedNonPosFloatConfigWriter: ConfigWriter[NonPosFloat] = numeric.derivedNonPosFloatConfigWriter

  given derivedNegDoubleConfigReader: ConfigReader[NegDouble] = numeric.derivedNegDoubleConfigReader
  given derivedNegDoubleConfigWriter: ConfigWriter[NegDouble] = numeric.derivedNegDoubleConfigWriter

  given derivedNonNegDoubleConfigReader: ConfigReader[NonNegDouble] = numeric.derivedNonNegDoubleConfigReader
  given derivedNonNegDoubleConfigWriter: ConfigWriter[NonNegDouble] = numeric.derivedNonNegDoubleConfigWriter

  given derivedPosDoubleConfigReader: ConfigReader[PosDouble] = numeric.derivedPosDoubleConfigReader
  given derivedPosDoubleConfigWriter: ConfigWriter[PosDouble] = numeric.derivedPosDoubleConfigWriter

  given derivedNonPosDoubleConfigReader: ConfigReader[NonPosDouble] = numeric.derivedNonPosDoubleConfigReader
  given derivedNonPosDoubleConfigWriter: ConfigWriter[NonPosDouble] = numeric.derivedNonPosDoubleConfigWriter

  given derivedNegBigIntConfigReader: ConfigReader[NegBigInt] = numeric.derivedNegBigIntConfigReader
  given derivedNegBigIntConfigWriter: ConfigWriter[NegBigInt] = numeric.derivedNegBigIntConfigWriter

  given derivedNonNegBigIntConfigReader: ConfigReader[NonNegBigInt] = numeric.derivedNonNegBigIntConfigReader
  given derivedNonNegBigIntConfigWriter: ConfigWriter[NonNegBigInt] = numeric.derivedNonNegBigIntConfigWriter

  given derivedPosBigIntConfigReader: ConfigReader[PosBigInt] = numeric.derivedPosBigIntConfigReader
  given derivedPosBigIntConfigWriter: ConfigWriter[PosBigInt] = numeric.derivedPosBigIntConfigWriter

  given derivedNonPosBigIntConfigReader: ConfigReader[NonPosBigInt] = numeric.derivedNonPosBigIntConfigReader
  given derivedNonPosBigIntConfigWriter: ConfigWriter[NonPosBigInt] = numeric.derivedNonPosBigIntConfigWriter

  given derivedNegBigDecimalConfigReader: ConfigReader[NegBigDecimal] = numeric.derivedNegBigDecimalConfigReader
  given derivedNegBigDecimalConfigWriter: ConfigWriter[NegBigDecimal] = numeric.derivedNegBigDecimalConfigWriter

  given derivedNonNegBigDecimalConfigReader: ConfigReader[NonNegBigDecimal] = numeric.derivedNonNegBigDecimalConfigReader
  given derivedNonNegBigDecimalConfigWriter: ConfigWriter[NonNegBigDecimal] = numeric.derivedNonNegBigDecimalConfigWriter

  given derivedPosBigDecimalConfigReader: ConfigReader[PosBigDecimal] = numeric.derivedPosBigDecimalConfigReader
  given derivedPosBigDecimalConfigWriter: ConfigWriter[PosBigDecimal] = numeric.derivedPosBigDecimalConfigWriter

  given derivedNonPosBigDecimalConfigReader: ConfigReader[NonPosBigDecimal] = numeric.derivedNonPosBigDecimalConfigReader
  given derivedNonPosBigDecimalConfigWriter: ConfigWriter[NonPosBigDecimal] = numeric.derivedNonPosBigDecimalConfigWriter

}
object numeric {

  import refined4s.internal.typeTools.*
  given derivedNegIntConfigReader: ConfigReader[NegInt] = ConfigReader[Int].emap { a =>
    NegInt.from(a).left.map { err =>
      val expectedType = getTypeName[NegInt]
      UserValidationFailed(
        s"The value ${a.toString} cannot be created as the expected type, $expectedType, due to the following error: $err"
      )
    }
  }
  given derivedNegIntConfigWriter: ConfigWriter[NegInt] with {
    override inline def to(a: NegInt): ConfigValue =
      ConfigWriter[Int].to(a.value)
  }

  given derivedNonNegIntConfigReader: ConfigReader[NonNegInt] = ConfigReader[Int].emap { a =>
    NonNegInt.from(a).left.map { err =>
      val expectedType = getTypeName[NonNegInt]
      UserValidationFailed(
        s"The value ${a.toString} cannot be created as the expected type, $expectedType, due to the following error: $err"
      )
    }
  }
  given derivedNonNegIntConfigWriter: ConfigWriter[NonNegInt] with {
    override inline def to(a: NonNegInt): ConfigValue =
      ConfigWriter[Int].to(a.value)
  }

  given derivedPosIntConfigReader: ConfigReader[PosInt] = ConfigReader[Int].emap { a =>
    PosInt.from(a).left.map { err =>
      val expectedType = getTypeName[PosInt]
      UserValidationFailed(
        s"The value ${a.toString} cannot be created as the expected type, $expectedType, due to the following error: $err"
      )
    }
  }
  given derivedPosIntConfigWriter: ConfigWriter[PosInt] with {
    override inline def to(a: PosInt): ConfigValue =
      ConfigWriter[Int].to(a.value)
  }

  given derivedNonPosIntConfigReader: ConfigReader[NonPosInt] = ConfigReader[Int].emap { a =>
    NonPosInt.from(a).left.map { err =>
      val expectedType = getTypeName[NonPosInt]
      UserValidationFailed(
        s"The value ${a.toString} cannot be created as the expected type, $expectedType, due to the following error: $err"
      )
    }
  }
  given derivedNonPosIntConfigWriter: ConfigWriter[NonPosInt] with {
    override inline def to(a: NonPosInt): ConfigValue =
      ConfigWriter[Int].to(a.value)
  }

  given derivedNegLongConfigReader: ConfigReader[NegLong] = ConfigReader[Long].emap { a =>
    NegLong.from(a).left.map { err =>
      val expectedType = getTypeName[NegLong]
      UserValidationFailed(
        s"The value ${a.toString} cannot be created as the expected type, $expectedType, due to the following error: $err"
      )
    }
  }
  given derivedNegLongConfigWriter: ConfigWriter[NegLong] with {
    override inline def to(a: NegLong): ConfigValue =
      ConfigWriter[Long].to(a.value)
  }

  given derivedNonNegLongConfigReader: ConfigReader[NonNegLong] = ConfigReader[Long].emap { a =>
    NonNegLong.from(a).left.map { err =>
      val expectedType = getTypeName[NonNegLong]
      UserValidationFailed(
        s"The value ${a.toString} cannot be created as the expected type, $expectedType, due to the following error: $err"
      )
    }
  }
  given derivedNonNegLongConfigWriter: ConfigWriter[NonNegLong] with {
    override inline def to(a: NonNegLong): ConfigValue =
      ConfigWriter[Long].to(a.value)
  }

  given derivedPosLongConfigReader: ConfigReader[PosLong] = ConfigReader[Long].emap { a =>
    PosLong.from(a).left.map { err =>
      val expectedType = getTypeName[PosLong]
      UserValidationFailed(
        s"The value ${a.toString} cannot be created as the expected type, $expectedType, due to the following error: $err"
      )
    }
  }
  given derivedPosLongConfigWriter: ConfigWriter[PosLong] with {
    override inline def to(a: PosLong): ConfigValue =
      ConfigWriter[Long].to(a.value)
  }

  given derivedNonPosLongConfigReader: ConfigReader[NonPosLong] = ConfigReader[Long].emap { a =>
    NonPosLong.from(a).left.map { err =>
      val expectedType = getTypeName[NonPosLong]
      UserValidationFailed(
        s"The value ${a.toString} cannot be created as the expected type, $expectedType, due to the following error: $err"
      )
    }
  }
  given derivedNonPosLongConfigWriter: ConfigWriter[NonPosLong] with {
    override inline def to(a: NonPosLong): ConfigValue =
      ConfigWriter[Long].to(a.value)
  }

  given derivedNegShortConfigReader: ConfigReader[NegShort] = ConfigReader[Short].emap { a =>
    NegShort.from(a).left.map { err =>
      val expectedType = getTypeName[NegShort]
      UserValidationFailed(
        s"The value ${a.toString} cannot be created as the expected type, $expectedType, due to the following error: $err"
      )
    }
  }
  given derivedNegShortConfigWriter: ConfigWriter[NegShort] with {
    override inline def to(a: NegShort): ConfigValue =
      ConfigWriter[Short].to(a.value)
  }

  given derivedNonNegShortConfigReader: ConfigReader[NonNegShort] = ConfigReader[Short].emap { a =>
    NonNegShort.from(a).left.map { err =>
      val expectedType = getTypeName[NonNegShort]
      UserValidationFailed(
        s"The value ${a.toString} cannot be created as the expected type, $expectedType, due to the following error: $err"
      )
    }
  }
  given derivedNonNegShortConfigWriter: ConfigWriter[NonNegShort] with {
    override inline def to(a: NonNegShort): ConfigValue =
      ConfigWriter[Short].to(a.value)
  }

  given derivedPosShortConfigReader: ConfigReader[PosShort] = ConfigReader[Short].emap { a =>
    PosShort.from(a).left.map { err =>
      val expectedType = getTypeName[PosShort]
      UserValidationFailed(
        s"The value ${a.toString} cannot be created as the expected type, $expectedType, due to the following error: $err"
      )
    }
  }
  given derivedPosShortConfigWriter: ConfigWriter[PosShort] with {
    override inline def to(a: PosShort): ConfigValue =
      ConfigWriter[Short].to(a.value)
  }

  given derivedNonPosShortConfigReader: ConfigReader[NonPosShort] = ConfigReader[Short].emap { a =>
    NonPosShort.from(a).left.map { err =>
      val expectedType = getTypeName[NonPosShort]
      UserValidationFailed(
        s"The value ${a.toString} cannot be created as the expected type, $expectedType, due to the following error: $err"
      )
    }
  }
  given derivedNonPosShortConfigWriter: ConfigWriter[NonPosShort] with {
    override inline def to(a: NonPosShort): ConfigValue =
      ConfigWriter[Short].to(a.value)
  }

  given derivedNegByteConfigReader: ConfigReader[NegByte] = ConfigReader[Byte].emap { a =>
    NegByte.from(a).left.map { err =>
      val expectedType = getTypeName[NegByte]
      UserValidationFailed(
        s"The value ${a.toString} cannot be created as the expected type, $expectedType, due to the following error: $err"
      )
    }
  }
  given derivedNegByteConfigWriter: ConfigWriter[NegByte] with {
    override inline def to(a: NegByte): ConfigValue =
      ConfigWriter[Byte].to(a.value)
  }

  given derivedNonNegByteConfigReader: ConfigReader[NonNegByte] = ConfigReader[Byte].emap { a =>
    NonNegByte.from(a).left.map { err =>
      val expectedType = getTypeName[NonNegByte]
      UserValidationFailed(
        s"The value ${a.toString} cannot be created as the expected type, $expectedType, due to the following error: $err"
      )
    }
  }
  given derivedNonNegByteConfigWriter: ConfigWriter[NonNegByte] with {
    override inline def to(a: NonNegByte): ConfigValue =
      ConfigWriter[Byte].to(a.value)
  }

  given derivedPosByteConfigReader: ConfigReader[PosByte] = ConfigReader[Byte].emap { a =>
    PosByte.from(a).left.map { err =>
      val expectedType = getTypeName[PosByte]
      UserValidationFailed(
        s"The value ${a.toString} cannot be created as the expected type, $expectedType, due to the following error: $err"
      )
    }
  }
  given derivedPosByteConfigWriter: ConfigWriter[PosByte] with {
    override inline def to(a: PosByte): ConfigValue =
      ConfigWriter[Byte].to(a.value)
  }

  given derivedNonPosByteConfigReader: ConfigReader[NonPosByte] = ConfigReader[Byte].emap { a =>
    NonPosByte.from(a).left.map { err =>
      val expectedType = getTypeName[NonPosByte]
      UserValidationFailed(
        s"The value ${a.toString} cannot be created as the expected type, $expectedType, due to the following error: $err"
      )
    }
  }
  given derivedNonPosByteConfigWriter: ConfigWriter[NonPosByte] with {
    override inline def to(a: NonPosByte): ConfigValue =
      ConfigWriter[Byte].to(a.value)
  }

  given derivedNegFloatConfigReader: ConfigReader[NegFloat] = ConfigReader[Float].emap { a =>
    NegFloat.from(a).left.map { err =>
      val expectedType = getTypeName[NegFloat]
      UserValidationFailed(
        s"The value ${a.toString} cannot be created as the expected type, $expectedType, due to the following error: $err"
      )
    }
  }
  given derivedNegFloatConfigWriter: ConfigWriter[NegFloat] with {
    override inline def to(a: NegFloat): ConfigValue =
      ConfigWriter[Float].to(a.value)
  }

  given derivedNonNegFloatConfigReader: ConfigReader[NonNegFloat] = ConfigReader[Float].emap { a =>
    NonNegFloat.from(a).left.map { err =>
      val expectedType = getTypeName[NonNegFloat]
      UserValidationFailed(
        s"The value ${a.toString} cannot be created as the expected type, $expectedType, due to the following error: $err"
      )
    }
  }
  given derivedNonNegFloatConfigWriter: ConfigWriter[NonNegFloat] with {
    override inline def to(a: NonNegFloat): ConfigValue =
      ConfigWriter[Float].to(a.value)
  }

  given derivedPosFloatConfigReader: ConfigReader[PosFloat] = ConfigReader[Float].emap { a =>
    PosFloat.from(a).left.map { err =>
      val expectedType = getTypeName[PosFloat]
      UserValidationFailed(
        s"The value ${a.toString} cannot be created as the expected type, $expectedType, due to the following error: $err"
      )
    }
  }
  given derivedPosFloatConfigWriter: ConfigWriter[PosFloat] with {
    override inline def to(a: PosFloat): ConfigValue =
      ConfigWriter[Float].to(a.value)
  }

  given derivedNonPosFloatConfigReader: ConfigReader[NonPosFloat] = ConfigReader[Float].emap { a =>
    NonPosFloat.from(a).left.map { err =>
      val expectedType = getTypeName[NonPosFloat]
      UserValidationFailed(
        s"The value ${a.toString} cannot be created as the expected type, $expectedType, due to the following error: $err"
      )
    }
  }
  given derivedNonPosFloatConfigWriter: ConfigWriter[NonPosFloat] with {
    override inline def to(a: NonPosFloat): ConfigValue =
      ConfigWriter[Float].to(a.value)
  }

  given derivedNegDoubleConfigReader: ConfigReader[NegDouble] = ConfigReader[Double].emap { a =>
    NegDouble.from(a).left.map { err =>
      val expectedType = getTypeName[NegDouble]
      UserValidationFailed(
        s"The value ${a.toString} cannot be created as the expected type, $expectedType, due to the following error: $err"
      )
    }
  }
  given derivedNegDoubleConfigWriter: ConfigWriter[NegDouble] with {
    override inline def to(a: NegDouble): ConfigValue =
      ConfigWriter[Double].to(a.value)
  }

  given derivedNonNegDoubleConfigReader: ConfigReader[NonNegDouble] = ConfigReader[Double].emap { a =>
    NonNegDouble.from(a).left.map { err =>
      val expectedType = getTypeName[NonNegDouble]
      UserValidationFailed(
        s"The value ${a.toString} cannot be created as the expected type, $expectedType, due to the following error: $err"
      )
    }
  }
  given derivedNonNegDoubleConfigWriter: ConfigWriter[NonNegDouble] with {
    override inline def to(a: NonNegDouble): ConfigValue =
      ConfigWriter[Double].to(a.value)
  }

  given derivedPosDoubleConfigReader: ConfigReader[PosDouble] = ConfigReader[Double].emap { a =>
    PosDouble.from(a).left.map { err =>
      val expectedType = getTypeName[PosDouble]
      UserValidationFailed(
        s"The value ${a.toString} cannot be created as the expected type, $expectedType, due to the following error: $err"
      )
    }
  }
  given derivedPosDoubleConfigWriter: ConfigWriter[PosDouble] with {
    override inline def to(a: PosDouble): ConfigValue =
      ConfigWriter[Double].to(a.value)
  }

  given derivedNonPosDoubleConfigReader: ConfigReader[NonPosDouble] = ConfigReader[Double].emap { a =>
    NonPosDouble.from(a).left.map { err =>
      val expectedType = getTypeName[NonPosDouble]
      UserValidationFailed(
        s"The value ${a.toString} cannot be created as the expected type, $expectedType, due to the following error: $err"
      )
    }
  }
  given derivedNonPosDoubleConfigWriter: ConfigWriter[NonPosDouble] with {
    override inline def to(a: NonPosDouble): ConfigValue =
      ConfigWriter[Double].to(a.value)
  }

  given derivedNegBigIntConfigReader: ConfigReader[NegBigInt] = ConfigReader[BigInt].emap { a =>
    NegBigInt.from(a).left.map { err =>
      val expectedType = getTypeName[NegBigInt]
      UserValidationFailed(
        s"The value ${a.toString} cannot be created as the expected type, $expectedType, due to the following error: $err"
      )
    }
  }
  given derivedNegBigIntConfigWriter: ConfigWriter[NegBigInt] with {
    override inline def to(a: NegBigInt): ConfigValue =
      ConfigWriter[BigInt].to(a.value)
  }

  given derivedNonNegBigIntConfigReader: ConfigReader[NonNegBigInt] = ConfigReader[BigInt].emap { a =>
    NonNegBigInt.from(a).left.map { err =>
      val expectedType = getTypeName[NonNegBigInt]
      UserValidationFailed(
        s"The value ${a.toString} cannot be created as the expected type, $expectedType, due to the following error: $err"
      )
    }
  }
  given derivedNonNegBigIntConfigWriter: ConfigWriter[NonNegBigInt] with {
    override inline def to(a: NonNegBigInt): ConfigValue =
      ConfigWriter[BigInt].to(a.value)
  }

  given derivedPosBigIntConfigReader: ConfigReader[PosBigInt] = ConfigReader[BigInt].emap { a =>
    PosBigInt.from(a).left.map { err =>
      val expectedType = getTypeName[PosBigInt]
      UserValidationFailed(
        s"The value ${a.toString} cannot be created as the expected type, $expectedType, due to the following error: $err"
      )
    }
  }
  given derivedPosBigIntConfigWriter: ConfigWriter[PosBigInt] with {
    override inline def to(a: PosBigInt): ConfigValue =
      ConfigWriter[BigInt].to(a.value)
  }

  given derivedNonPosBigIntConfigReader: ConfigReader[NonPosBigInt] = ConfigReader[BigInt].emap { a =>
    NonPosBigInt.from(a).left.map { err =>
      val expectedType = getTypeName[NonPosBigInt]
      UserValidationFailed(
        s"The value ${a.toString} cannot be created as the expected type, $expectedType, due to the following error: $err"
      )
    }
  }
  given derivedNonPosBigIntConfigWriter: ConfigWriter[NonPosBigInt] with {
    override inline def to(a: NonPosBigInt): ConfigValue =
      ConfigWriter[BigInt].to(a.value)
  }

  given derivedNegBigDecimalConfigReader: ConfigReader[NegBigDecimal] = ConfigReader[BigDecimal].emap { a =>
    NegBigDecimal.from(a).left.map { err =>
      val expectedType = getTypeName[NegBigDecimal]
      UserValidationFailed(
        s"The value ${a.toString} cannot be created as the expected type, $expectedType, due to the following error: $err"
      )
    }
  }
  given derivedNegBigDecimalConfigWriter: ConfigWriter[NegBigDecimal] with {
    override inline def to(a: NegBigDecimal): ConfigValue =
      ConfigWriter[BigDecimal].to(a.value)
  }

  given derivedNonNegBigDecimalConfigReader: ConfigReader[NonNegBigDecimal] = ConfigReader[BigDecimal].emap { a =>
    NonNegBigDecimal.from(a).left.map { err =>
      val expectedType = getTypeName[NonNegBigDecimal]
      UserValidationFailed(
        s"The value ${a.toString} cannot be created as the expected type, $expectedType, due to the following error: $err"
      )
    }
  }
  given derivedNonNegBigDecimalConfigWriter: ConfigWriter[NonNegBigDecimal] with {
    override inline def to(a: NonNegBigDecimal): ConfigValue =
      ConfigWriter[BigDecimal].to(a.value)
  }

  given derivedPosBigDecimalConfigReader: ConfigReader[PosBigDecimal] = ConfigReader[BigDecimal].emap { a =>
    PosBigDecimal.from(a).left.map { err =>
      val expectedType = getTypeName[PosBigDecimal]
      UserValidationFailed(
        s"The value ${a.toString} cannot be created as the expected type, $expectedType, due to the following error: $err"
      )
    }
  }
  given derivedPosBigDecimalConfigWriter: ConfigWriter[PosBigDecimal] with {
    override inline def to(a: PosBigDecimal): ConfigValue =
      ConfigWriter[BigDecimal].to(a.value)
  }

  given derivedNonPosBigDecimalConfigReader: ConfigReader[NonPosBigDecimal] = ConfigReader[BigDecimal].emap { a =>
    NonPosBigDecimal.from(a).left.map { err =>
      val expectedType = getTypeName[NonPosBigDecimal]
      UserValidationFailed(
        s"The value ${a.toString} cannot be created as the expected type, $expectedType, due to the following error: $err"
      )
    }
  }
  given derivedNonPosBigDecimalConfigWriter: ConfigWriter[NonPosBigDecimal] with {
    override inline def to(a: NonPosBigDecimal): ConfigValue =
      ConfigWriter[BigDecimal].to(a.value)
  }

}
