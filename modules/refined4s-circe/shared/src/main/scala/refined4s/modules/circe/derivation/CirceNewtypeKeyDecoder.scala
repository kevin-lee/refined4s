package refined4s.modules.circe.derivation

import io.circe.KeyDecoder
import refined4s.*

/** @author Kevin Lee
  * @since 2023-12-11
  */
trait CirceNewtypeKeyDecoder[A: KeyDecoder] {
  self: NewtypeBase[A] =>

  given derivedKeyDecoder: KeyDecoder[Type] = deriving[KeyDecoder]
}
