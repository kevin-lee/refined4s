package refined4s.modules.tapir.derivation.generic

import refined4s.*
import sttp.tapir.Schema

/** @author Kevin Lee
  * @since 2024-04-03
  */
trait auto {
  inline given derivedNewtypeSchema[A, B](
    using coercibleA2B: Coercible[A, B],
    coercibleB2A: Coercible[B, A],
    schemaB: Schema[B],
  ): Schema[A] = auto.derivedNewtypeSchema

  inline given derivedRefinedSchema[A, B](
    using refinedCtor: RefinedCtor[A, B],
    coercibleA2B: Coercible[A, B],
    schemaB: Schema[B],
  ): Schema[A] = auto.derivedRefinedSchema

}
object auto {

  inline given derivedNewtypeSchema[A, B](
    using coercibleA2B: Coercible[A, B],
    coercibleB2A: Coercible[B, A],
    schemaB: Schema[B],
  ): Schema[A] =
    schemaB.map(b => Some(coercibleB2A(b)))(coercibleA2B(_))

  inline given derivedRefinedSchema[A, B](
    using refinedCtor: RefinedCtor[A, B],
    coercibleA2B: Coercible[A, B],
    schemaB: Schema[B],
  ): Schema[A] =
    schemaB.map(refinedCtor.create(_).toOption)(coercibleA2B(_))

}
