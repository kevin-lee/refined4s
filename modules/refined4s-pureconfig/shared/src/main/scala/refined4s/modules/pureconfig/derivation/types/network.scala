package refined4s.modules.pureconfig.derivation.types

import com.typesafe.config.ConfigValue
import pureconfig.{ConfigReader, ConfigWriter}
import pureconfig.error.UserValidationFailed
import refined4s.types.network.*

/** @author Kevin Lee
  * @since 2025-09-02
  */
trait network {

  inline given derivedUriConfigReader: ConfigReader[Uri] = network.derivedUriConfigReader
  inline given derivedUriConfigWriter: ConfigWriter[Uri] = network.derivedUriConfigWriter

  inline given derivedUrlConfigReader: ConfigReader[Url] = network.derivedUrlConfigReader
  inline given derivedUrlConfigWriter: ConfigWriter[Url] = network.derivedUrlConfigWriter

  inline given derivedPortNumberConfigReader: ConfigReader[PortNumber] = network.derivedPortNumberConfigReader
  inline given derivedPortNumberConfigWriter: ConfigWriter[PortNumber] = network.derivedPortNumberConfigWriter

  inline given derivedSystemPortNumberConfigReader: ConfigReader[SystemPortNumber] = network.derivedSystemPortNumberConfigReader
  inline given derivedSystemPortNumberConfigWriter: ConfigWriter[SystemPortNumber] = network.derivedSystemPortNumberConfigWriter

  inline given derivedNonSystemPortNumberConfigReader: ConfigReader[NonSystemPortNumber] = network.derivedNonSystemPortNumberConfigReader
  inline given derivedNonSystemPortNumberConfigWriter: ConfigWriter[NonSystemPortNumber] = network.derivedNonSystemPortNumberConfigWriter

  inline given derivedUserPortNumberConfigReader: ConfigReader[UserPortNumber] = network.derivedUserPortNumberConfigReader
  inline given derivedUserPortNumberConfigWriter: ConfigWriter[UserPortNumber] = network.derivedUserPortNumberConfigWriter

  inline given derivedDynamicPortNumberConfigReader: ConfigReader[DynamicPortNumber] = network.derivedDynamicPortNumberConfigReader
  inline given derivedDynamicPortNumberConfigWriter: ConfigWriter[DynamicPortNumber] = network.derivedDynamicPortNumberConfigWriter

}
object network {

  import refined4s.internal.typeTools.*

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
