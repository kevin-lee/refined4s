package refined4s.modules.pureconfig.derivation.types

import com.typesafe.config.ConfigValue
import pureconfig.error.UserValidationFailed
import pureconfig.{ConfigReader, ConfigWriter}
import refined4s.*
import refined4s.types.all.*

/** @author Kevin Lee
  * @since 2023-12-26
  */
trait all {

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

  // strings

  given derivedNonEmptyStringConfigReader: ConfigReader[NonEmptyString] = ConfigReader[String].emap { a =>
    NonEmptyString.from(a).left.map { err =>
      val expectedType = getTypeName[NonEmptyString]
      UserValidationFailed(
        s"The value $a cannot be created as the expected type, $expectedType, due to the following error: $err"
      )
    }
  }
  given derivedNonEmptyStringConfigWriter: ConfigWriter[NonEmptyString] with {
    override inline def to(a: NonEmptyString): ConfigValue =
      ConfigWriter[String].to(a.value)
  }

  // network

  given derivedUriConfigReader: ConfigReader[Uri] = ConfigReader[String].emap { a =>
    Uri.from(a).left.map { err =>
      val expectedType = getTypeName[Uri]
      UserValidationFailed(
        s"The value $a cannot be created as the expected type, $expectedType, due to the following error: $err"
      )
    }
  }
  given derivedUriConfigWriter: ConfigWriter[Uri] with {
    override inline def to(a: Uri): ConfigValue =
      ConfigWriter[String].to(a.value)
  }

  given derivedUrlConfigReader: ConfigReader[Url] = ConfigReader[String].emap { a =>
    Url.from(a).left.map { err =>
      val expectedType = getTypeName[Url]
      UserValidationFailed(
        s"The value $a cannot be created as the expected type, $expectedType, due to the following error: $err"
      )
    }
  }
  given derivedUrlConfigWriter: ConfigWriter[Url] with {
    override inline def to(a: Url): ConfigValue =
      ConfigWriter[String].to(a.value)
  }

  given derivedPortNumberConfigReader: ConfigReader[PortNumber] = ConfigReader[Int].emap { a =>
    PortNumber.from(a).left.map { err =>
      val expectedType = getTypeName[PortNumber]
      UserValidationFailed(
        s"The value ${a.toString} cannot be created as the expected type, $expectedType, due to the following error: $err"
      )
    }
  }
  given derivedPortNumberConfigWriter: ConfigWriter[PortNumber] with {
    override inline def to(a: PortNumber): ConfigValue =
      ConfigWriter[Int].to(a.value)
  }

  given derivedSystemPortNumberConfigReader: ConfigReader[SystemPortNumber] = ConfigReader[Int].emap { a =>
    SystemPortNumber.from(a).left.map { err =>
      val expectedType = getTypeName[SystemPortNumber]
      UserValidationFailed(
        s"The value ${a.toString} cannot be created as the expected type, $expectedType, due to the following error: $err"
      )
    }
  }
  given derivedSystemPortNumberConfigWriter: ConfigWriter[SystemPortNumber] with {
    override inline def to(a: SystemPortNumber): ConfigValue =
      ConfigWriter[Int].to(a.value)
  }

  given derivedNonSystemPortNumberConfigReader: ConfigReader[NonSystemPortNumber] = ConfigReader[Int].emap { a =>
    NonSystemPortNumber.from(a).left.map { err =>
      val expectedType = getTypeName[NonSystemPortNumber]
      UserValidationFailed(
        s"The value ${a.toString} cannot be created as the expected type, $expectedType, due to the following error: $err"
      )
    }
  }
  given derivedNonSystemPortNumberConfigWriter: ConfigWriter[NonSystemPortNumber] with {
    override inline def to(a: NonSystemPortNumber): ConfigValue =
      ConfigWriter[Int].to(a.value)
  }

  given derivedUserPortNumberConfigReader: ConfigReader[UserPortNumber] = ConfigReader[Int].emap { a =>
    UserPortNumber.from(a).left.map { err =>
      val expectedType = getTypeName[UserPortNumber]
      UserValidationFailed(
        s"The value ${a.toString} cannot be created as the expected type, $expectedType, due to the following error: $err"
      )
    }
  }
  given derivedUserPortNumberConfigWriter: ConfigWriter[UserPortNumber] with {
    override inline def to(a: UserPortNumber): ConfigValue =
      ConfigWriter[Int].to(a.value)
  }

  given derivedDynamicPortNumberConfigReader: ConfigReader[DynamicPortNumber] = ConfigReader[Int].emap { a =>
    DynamicPortNumber.from(a).left.map { err =>
      val expectedType = getTypeName[DynamicPortNumber]
      UserValidationFailed(
        s"The value ${a.toString} cannot be created as the expected type, $expectedType, due to the following error: $err"
      )
    }
  }
  given derivedDynamicPortNumberConfigWriter: ConfigWriter[DynamicPortNumber] with {
    override inline def to(a: DynamicPortNumber): ConfigValue =
      ConfigWriter[Int].to(a.value)
  }

}
object all extends all
