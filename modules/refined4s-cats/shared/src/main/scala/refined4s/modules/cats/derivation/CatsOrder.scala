package refined4s.modules.cats.derivation

import cats.Order
import refined4s.*

/** @author Kevin Lee
  * @since 2023-12-30
  */
trait CatsOrder[A: Order] {
  self: NewtypeBase[A] =>

  given derivedOrder: Order[Type] = deriving[Order]
}
