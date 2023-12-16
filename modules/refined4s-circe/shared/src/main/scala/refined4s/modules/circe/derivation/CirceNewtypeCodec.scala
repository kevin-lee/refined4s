package refined4s.modules.circe.derivation

import refined4s.*

/** @author Kevin Lee
  * @since 2023-12-11
  */
trait CirceNewtypeCodec[A] extends CirceEncoder[A] with CirceNewtypeDecoder[A] {
  self: NewtypeBase[A] =>
}
