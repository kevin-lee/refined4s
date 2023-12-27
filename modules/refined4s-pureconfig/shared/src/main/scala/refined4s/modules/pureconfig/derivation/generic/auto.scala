package refined4s.modules.pureconfig.derivation.generic

import com.typesafe.config.ConfigValue
import pureconfig.error.UserValidationFailed
import pureconfig.{ConfigReader, ConfigWriter}
import refined4s.*

/** @author Kevin Lee
  * @since 2023-12-13
  */
trait auto {

  inline given derivedNewtypeConfigReader[A, B](using coercible: Coercible[A, B], configReader: ConfigReader[A]): ConfigReader[B] =
    Coercible.unsafeWrapTC(configReader)

  import refined4s.internal.typeTools.*

  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  inline given derivedRefinedConfigReader[A, B](using refinedCtor: RefinedCtor[B, A], configReader: ConfigReader[A]): ConfigReader[B] =
    configReader.emap { a =>
      refinedCtor.create(a).left.map { err =>
        val expectedType = getTypeName[B]
        UserValidationFailed(
          s"The value ${a.toString} cannot be created as the expected type, $expectedType, due to the following error: $err"
        )
      }
    }

  inline given derivedConfigWriter[A, B](using coercible: Coercible[A, B], configWriter: ConfigWriter[B]): ConfigWriter[A] with {
    override inline def to(a: A): ConfigValue =
      configWriter.to(coercible(a))
  }

}
object auto extends auto
