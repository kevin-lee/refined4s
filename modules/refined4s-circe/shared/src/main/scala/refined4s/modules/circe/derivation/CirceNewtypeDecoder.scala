package refined4s.modules.circe.derivation

import io.circe.Decoder
import refined4s.*

/** @author Kevin Lee
  * @since 2023-12-11
  */
trait CirceNewtypeDecoder[A: Decoder] {
  self: NewtypeBase[A] =>

  given derivedDecoder: Decoder[Type] = deriving[Decoder]
}
