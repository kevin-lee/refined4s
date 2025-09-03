package refined4s.modules.pureconfig.derivation.types

import pureconfig.error.UserValidationFailed

/** @author Kevin Lee
  * @since 2025-09-03
  */
object internalDef {

  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  def userValidationFailed[A](value: A, expectedType: String, err: String): UserValidationFailed =
    UserValidationFailed(
      s"The value ${value.toString} cannot be created as the expected type, $expectedType, due to the following error: $err"
    )
}
