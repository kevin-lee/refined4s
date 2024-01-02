package refined4s.modules.extras.derivation.generic

import refined4s.*
import extras.render.Render

/** @author Kevin Lee
  * @since 2024-01-01
  */
trait auto {

  /** `Render` instance for Newtype, Refined and InlinedRefined types that delegates from the `Render`
    * instance of the base type.
    */
  inline given derivedRender[A, B](using coercible: Coercible[A, B], renderB: Render[B]): Render[A] =
    renderB.contramap[A](coercible(_))

}
object auto extends auto
