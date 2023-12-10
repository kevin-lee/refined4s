package refined4s.modules.cats.derivation

import refined4s.NewtypeBase

/** @author Kevin Lee
  * @since 2023-12-07
  */
trait CatsEqShow[A] extends CatsEq[A] with CatsShow[A] {
  self: NewtypeBase[A] =>
}
