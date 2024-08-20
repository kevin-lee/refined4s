package refined4s.modules.circe.derivation

import refined4s.*

/** @author Kevin Lee
  * @since 2023-12-11
  */
trait CirceNewtypeKeyCodec[A] extends CirceKeyEncoder[A] with CirceNewtypeKeyDecoder[A] {
  self: NewtypeBase[A] =>
}