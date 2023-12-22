package refined4s.modules.doobie.derivation

import refined4s.RefinedBase

/** @author Kevin Lee
  * @since 2023-12-16
  */
trait DoobieRefinedGetPut[A] extends DoobieRefinedGet[A] with DoobiePut[A] {
  self: RefinedBase[A] =>
}
