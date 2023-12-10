package refined4s.modules.cats.derivation

import cats.Show
import refined4s.*

/** @author Kevin Lee
  * @since 2023-12-07
  */
trait CatsShow[A: Show] {
  self: NewtypeBase[A] =>

  given derivedShow: Show[Type] = deriving[Show]
}
