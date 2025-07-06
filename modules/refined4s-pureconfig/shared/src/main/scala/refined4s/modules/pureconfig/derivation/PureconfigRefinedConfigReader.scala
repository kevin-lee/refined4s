package refined4s.modules.pureconfig.derivation

import pureconfig.ConfigReader
import pureconfig.error.UserValidationFailed
import refined4s.RefinedBase
import refined4s.internal.typeTools.getTypeName

/** @author Kevin Lee
  * @since 2023-12-14
  */
trait PureconfigRefinedConfigReader[A: ConfigReader] {
  self: RefinedBase[A] =>

  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  given derivedConfigReader: ConfigReader[Type] = ConfigReader[A].emap { a =>
    from(a).left.map { err =>
      val expectedType = getTypeName[Type]
      UserValidationFailed(
        s"The value ${a.toString} cannot be created as the expected type, $expectedType, due to the following error: $err"
      )
    }
  }
}
