package refined4s.modules.circe.derivation.types

/** @author Kevin Lee
  * @since 2023-12-25
  */
trait all extends numeric, strings, network, time
object all extends all
