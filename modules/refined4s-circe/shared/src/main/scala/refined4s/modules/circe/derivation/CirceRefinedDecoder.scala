package refined4s.modules.circe.derivation

import io.circe.Decoder
import refined4s.*

/** @author Kevin Lee
  * @since 2023-12-11
  */
trait CirceRefinedDecoder[A: Decoder] {
  self: RefinedBase[A] =>

  given derivedDecoder: Decoder[Type] = Decoder[A].emap(from)
}
