package refined4s.modules.circe.derivation

import io.circe.{Decoder, Encoder}
import refined4s.{Coercible, RefinedCtor}
import refined4s.syntax.*

/** @author Kevin Lee
  * @since 2023-12-11
  */
trait instances {

  given derivedEncoder[A, B](using coercible: Coercible[A, B], encoder: Encoder[B]): Encoder[A] =
    a => encoder(coercible(a))

  given derivedNewtypeDecoder[A, B](using coercible: Coercible[A, B], decoder: Decoder[A]): Decoder[B] =
    Coercible.unsafeWrapM(decoder)

  given derivedRefinedDecoder[A, B](using refinedCtor: RefinedCtor[B, A], decoder: Decoder[A]): Decoder[B] =
    decoder.emap(refinedCtor.create)
}
object instances extends instances
