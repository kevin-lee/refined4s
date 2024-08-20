package refined4s.modules.circe.derivation

import io.circe.KeyDecoder
import refined4s.*

/** @author Kevin Lee
  * @since 2023-12-11
  */
trait CirceRefinedKeyDecoder[A: KeyDecoder] {
  self: RefinedBase[A] =>

  given derivedKeyDecoder: KeyDecoder[Type] = KeyDecoder[A].apply(_).flatMap(from(_).toOption)
}
