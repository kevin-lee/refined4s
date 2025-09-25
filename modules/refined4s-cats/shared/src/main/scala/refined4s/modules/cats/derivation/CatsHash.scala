package refined4s.modules.cats.derivation

import cats.Hash
import refined4s.*

/** @author Kevin Lee
  * @since 2025-09-03
  */
trait CatsHash[A: Hash] {
  self: NewtypeBase[A] =>

  given derivedHash: Hash[Type] = deriving[Hash]
}
