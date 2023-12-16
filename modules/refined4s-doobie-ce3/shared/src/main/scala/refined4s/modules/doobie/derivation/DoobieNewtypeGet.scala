package refined4s.modules.doobie.derivation

import doobie.Get
import refined4s.NewtypeBase

/** @author Kevin Lee
  * @since 2023-12-16
  */
trait DoobieNewtypeGet[A: Get] {
  self: NewtypeBase[A] =>

  given derivedGet: Get[Type] = deriving[Get]
}
