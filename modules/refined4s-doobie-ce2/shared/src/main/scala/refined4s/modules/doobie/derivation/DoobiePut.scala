package refined4s.modules.doobie.derivation

import doobie.Put
import refined4s.NewtypeBase

/** @author Kevin Lee
  * @since 2023-12-16
  */
trait DoobiePut[A: Put] {
  self: NewtypeBase[A] =>

  given derivedPut: Put[Type] = deriving[Put]
}
