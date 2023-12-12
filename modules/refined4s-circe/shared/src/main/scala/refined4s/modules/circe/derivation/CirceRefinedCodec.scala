package refined4s.modules.circe.derivation

import io.circe.*
import refined4s.*

/** @author Kevin Lee
  * @since 2023-12-11
  */
trait CirceRefinedCodec[A: Encoder: Decoder] extends CirceEncoder[A] with CirceRefinedDecoder[A] {
  self: RefinedBase[A] =>
}
