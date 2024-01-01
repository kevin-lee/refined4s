package refined4s.modules.extras.derivation

import extras.render.Render
import refined4s.NewtypeBase

/** @author Kevin Lee
  * @since 2024-01-01
  */
trait ExtrasRender[A: Render] {
  self: NewtypeBase[A] =>

  given derivedRender: Render[Type] = deriving[Render]
}
