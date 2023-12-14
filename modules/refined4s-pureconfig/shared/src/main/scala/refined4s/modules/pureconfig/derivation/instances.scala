package refined4s.modules.pureconfig.derivation

import pureconfig.error.UserValidationFailed
import pureconfig.{ConfigReader, ConfigWriter}
import refined4s.*

/** @author Kevin Lee
  * @since 2023-12-13
  */
trait instances {

  given derivedNewtypeConfigReader[A, B](using coercible: Coercible[A, B], configReader: ConfigReader[A]): ConfigReader[B] =
    Coercible.unsafeWrapM(configReader)

//  import refined4s.internal.typeTools.*

  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  given derivedRefinedConfigReader[A, B](using refinedCtor: RefinedCtor[B, A], configReader: ConfigReader[A]): ConfigReader[B] =
    configReader.emap { a =>
      refinedCtor.create(a).left.map { err =>
//        val expectedType = getTypeName[B]
        UserValidationFailed(
          s"Invalid value found: ${a.toString} with error: $err"
        )
      }
    }

  given derivedConfigWriter[A, B](using coercible: Coercible[A, B], configWriter: ConfigWriter[B]): ConfigWriter[A] =
    a => configWriter.to(coercible(a))

}
object instances extends instances
