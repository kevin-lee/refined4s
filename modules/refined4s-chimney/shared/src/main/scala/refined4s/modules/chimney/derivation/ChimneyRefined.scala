package refined4s.modules.chimney.derivation

import io.scalaland.chimney
import io.scalaland.chimney.{PartialTransformer, Transformer}
import refined4s.{Coercible, RefinedBase, RefinedCtor}

/** @author Kevin Lee
  * @since 2024-08-04
  */
trait ChimneyRefined[A] {
  self: RefinedBase[A] =>

  given wrapRefinedForChimney: PartialTransformer[A, Type] = PartialTransformer[A, Type] { value =>
    chimney.partial.Result.fromEitherString(from(value))
  }

  given unwrapRefinedForChimney: Transformer[Type, A] = unwrap(_)

  given wrapRefinedToRefinedForChimney[Type2](
    using refinedCtor: RefinedCtor[Type2, A]
  ): PartialTransformer[Type, Type2] = PartialTransformer[Type, Type2] { value =>
    chimney.partial.Result.fromEitherString(refinedCtor.create(value.value))
  }

  /** Refined[A] => Newtype[Refined[A]]
    *
    * @param anotherCoercible to create a Newtype of Newtype[Refined[A]]
    * @param refinedCtor to create a Refined[A] of Newtype[Refined[A]]
    * @tparam Type2 Refined of Newtype[Refined[A]]
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
