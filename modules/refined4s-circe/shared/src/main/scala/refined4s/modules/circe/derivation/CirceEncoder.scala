package refined4s.modules.circe.derivation

import io.circe.Encoder
import refined4s.NewtypeBase

/** @author Kevin Lee
  * @since 2023-12-11
  */
trait CirceEncoder[A: Encoder] {
  self: NewtypeBase[A] =>

  given derivedEncoder: Encoder[Type] = deriving[Encoder]
}
