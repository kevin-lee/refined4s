package refined4s.modules.circe.derivation

import io.circe.{Decoder, Encoder}
import refined4s.{Coercible, RefinedCtor}

/** @author Kevin Lee
  * @since 2023-12-11
  */
trait instances {

  inline given derivedEncoder[A, B](using coercible: Coercible[A, B], encoder: Encoder[B]): Encoder[A] =
    refined4s.modules.cats.derivation.instances.contraCoercible(encoder)

  inline given derivedNewtypeDecoder[A, B](using coercible: Coercible[A, B], decoder: Decoder[A]): Decoder[B] =
    Coercible.unsafeWrapM(decoder)

  inline given derivedRefinedDecoder[A, B](using refinedCtor: RefinedCtor[B, A], decoder: Decoder[A]): Decoder[B] =
    decoder.emap(refinedCtor.create)
}
object instances extends instances
