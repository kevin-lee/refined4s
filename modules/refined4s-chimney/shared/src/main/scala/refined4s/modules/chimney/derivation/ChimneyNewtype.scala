package refined4s.modules.chimney.derivation

import io.scalaland.chimney
import io.scalaland.chimney.{PartialTransformer, Transformer}
import refined4s.{Coercible, Newtype, RefinedCtor}

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

  /** Newtype[A] => Newtype[Refined[A]]
    *
    * @param anotherCoercible to create a Newtype of Newtype[Refined[A]]
    * @param refinedCtor to create a Refined[A] of Newtype[Refined[A]]
    * @tparam Type2 Refined[A] of Newtype[Refined[A]]
    * @tparam Type3 Newtype of Newtype[Refined[A]]
    * @return
    */
  given wrapAToNewtypeOfRefinedForChimney[Type2, Type3](
    using anotherCoercible: Coercible[Type2, Type3],
    refinedCtor: RefinedCtor[Type2, A],
  ): PartialTransformer[Type, Type3] = PartialTransformer { value =>
    val a = value.value
    chimney.partial.Result.fromEitherString(refinedCtor.create(a).map(anotherCoercible(_)))
  }

}
