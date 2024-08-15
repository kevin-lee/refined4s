package refined4s.modules.chimney.derivation

import io.scalaland.chimney
import io.scalaland.chimney.{PartialTransformer, Transformer}
import refined4s.{RefinedBase, RefinedCtor}

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

}
