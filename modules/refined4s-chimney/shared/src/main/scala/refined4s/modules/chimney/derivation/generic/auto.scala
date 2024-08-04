package refined4s.modules.chimney.derivation.generic

import io.scalaland.chimney
import io.scalaland.chimney.{PartialTransformer, Transformer}
import refined4s.{Coercible, RefinedCtor}

/** @author Kevin Lee
  * @since 2024-08-03
  */
trait auto {

  given unwrapNewTypeForChimney[Type, A](using coercible: Coercible[Type, A]): Transformer[Type, A] = coercible(_)

  given unwrapAndWrapNewTypeForChimney[B, A, C](using coercible1: Coercible[B, A], coercible2: Coercible[A, C]): Transformer[B, C] =
    b => coercible2(coercible1(b))

  given wrapRefinedForChimney[A, Type](
    using refinedCtor: RefinedCtor[Type, A]
  ): PartialTransformer[A, Type] = PartialTransformer[A, Type] { value =>
    chimney.partial.Result.fromEitherString(refinedCtor.create(value))
  }

  given wrapRefinedToRefinedForChimney[A, Type1, Type2](
    using refinedCtor: RefinedCtor[Type2, A],
    coercible: Coercible[Type1, A],
  ): PartialTransformer[Type1, Type2] = PartialTransformer[Type1, Type2] { value =>
    chimney.partial.Result.fromEitherString(refinedCtor.create(coercible(value)))
  }

}
object auto extends auto
