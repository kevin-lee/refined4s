package refined4s.modules.chimney.derivation

import io.scalaland.chimney.Transformer
import refined4s.{Coercible, Newtype}

/** @author Kevin Lee
  * @since 2024-08-04
  */
trait ChimneyNewtype[A] {
  self: Newtype[A] =>

  given wrapNewTypeForChimney: Transformer[A, self.Type] = wrap(_)

  given unwrapNewTypeForChimney: Transformer[self.Type, A] = unwrap(_)

  given unwrapAndWrapNewTypeForChimney[C](
    using anotherCoercible: Coercible[A, C]
  ): Transformer[self.Type, C] =
    b => anotherCoercible(unwrap(b))

}
