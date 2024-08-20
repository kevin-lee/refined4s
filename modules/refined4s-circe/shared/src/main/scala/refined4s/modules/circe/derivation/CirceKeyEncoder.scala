package refined4s.modules.circe.derivation

import io.circe.KeyEncoder
import refined4s.NewtypeBase

/** @author Kevin Lee
  * @since 2023-12-11
  */
trait CirceKeyEncoder[A: KeyEncoder] {
  self: NewtypeBase[A] =>

  given derivedKeyEncoder: KeyEncoder[Type] = deriving[KeyEncoder]
}
