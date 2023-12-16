package refined4s.modules.doobie.derivation

import cats.Show
import doobie.Get
import refined4s.RefinedBase

/** @author Kevin Lee
  * @since 2023-12-16
  */
trait DoobieRefinedGet[A: Get: Show] {
  self: RefinedBase[A] =>

  given derivedGet: Get[Type] = Get[A].temap(from)
}
