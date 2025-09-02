package refined4s.modules.pureconfig.derivation.types

import com.typesafe.config.ConfigValue
import pureconfig.error.UserValidationFailed
import pureconfig.{ConfigReader, ConfigWriter}
import refined4s.types.strings.*

/** @author Kevin Lee
  * @since 2025-09-02
  */
trait strings {

  inline given derivedNonEmptyStringConfigReader: ConfigReader[NonEmptyString] = strings.derivedNonEmptyStringConfigReader
  inline given derivedNonEmptyStringConfigWriter: ConfigWriter[NonEmptyString] = strings.derivedNonEmptyStringConfigWriter

  inline given derivedNonBlankStringConfigReader: ConfigReader[NonBlankString] = strings.derivedNonBlankStringConfigReader
  inline given derivedNonBlankStringConfigWriter: ConfigWriter[NonBlankString] = strings.derivedNonBlankStringConfigWriter

  inline given derivedUuidConfigReader: ConfigReader[Uuid] = strings.derivedUuidConfigReader
  inline given derivedUuidConfigWriter: ConfigWriter[Uuid] = strings.derivedUuidConfigWriter

}
object strings {

  import refined4s.internal.typeTools.*

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

  given derivedNonBlankStringConfigReader: ConfigReader[NonBlankString] = ConfigReader[String].emap { a =>
    NonBlankString.from(a).left.map { err =>
      val expectedType = getTypeName[NonBlankString]
      UserValidationFailed(
        s"The value $a cannot be created as the expected type, $expectedType, due to the following error: $err"
      )
    }
  }

  given derivedNonBlankStringConfigWriter: ConfigWriter[NonBlankString] with {
    override inline def to(a: NonBlankString): ConfigValue =
      ConfigWriter[String].to(a.value)
  }

  given derivedUuidConfigReader: ConfigReader[Uuid] = ConfigReader[String].emap { a =>
    Uuid.from(a).left.map { err =>
      val expectedType = getTypeName[Uuid]
      UserValidationFailed(
        s"The value $a cannot be created as the expected type, $expectedType, due to the following error: $err"
      )
    }
  }

  given derivedUuidConfigWriter: ConfigWriter[Uuid] with {
    override inline def to(a: Uuid): ConfigValue =
      ConfigWriter[String].to(a.value)
  }

}
