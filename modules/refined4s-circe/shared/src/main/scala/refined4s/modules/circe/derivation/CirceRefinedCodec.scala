package refined4s.modules.circe.derivation

import refined4s.*

/** @author Kevin Lee
  * @since 2023-12-11
  */
trait CirceRefinedCodec[A] extends CirceEncoder[A] with CirceRefinedDecoder[A] {
  self: RefinedBase[A] =>
}
