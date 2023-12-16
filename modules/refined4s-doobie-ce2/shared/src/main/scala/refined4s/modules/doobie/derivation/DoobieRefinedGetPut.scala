package refined4s.modules.doobie.derivation

import doobie.Get
import refined4s.{NewtypeBase, RefinedBase}

/** @author Kevin Lee
  * @since 2023-12-16
  */
trait DoobieRefinedGetPut[A] extends DoobieRefinedGet[A] with DoobiePut[A] {
  self: RefinedBase[A] =>
}
