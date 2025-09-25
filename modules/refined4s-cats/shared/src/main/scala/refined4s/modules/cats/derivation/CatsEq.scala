package refined4s.modules.cats.derivation

import cats.Eq
import refined4s.*

/** @author Kevin Lee
  * @since 2023-12-07
  */
trait CatsEq[A: Eq] {
  self: NewtypeBase[A] =>

  given derivedEq: Eq[Type] = deriving[Eq]
}
