package refined4s.modules.circe.derivation.generic

import io.circe.{Decoder, Encoder, KeyDecoder, KeyEncoder}
import refined4s.modules.cats.syntax.contraCoercible
import refined4s.{Coercible, RefinedCtor}

/** @author Kevin Lee
  * @since 2023-12-11
  */
trait auto {

  given derivedEncoder[A, B](using coercible: Coercible[A, B], encoder: Encoder[B]): Encoder[A] =
    contraCoercible(encoder)

  given derivedNewtypeDecoder[A, B](using coercible: Coercible[A, B], decoder: Decoder[A]): Decoder[B] =
    Coercible.unsafeWrapTC(decoder)

  given derivedRefinedDecoder[A, B](using refinedCtor: RefinedCtor[B, A], decoder: Decoder[A]): Decoder[B] =
    decoder.emap(refinedCtor.create)

  given derivedKeyEncoder[A, B](using coercible: Coercible[A, B], encoder: KeyEncoder[B]): KeyEncoder[A] =
    contraCoercible(encoder)

  given derivedNewtypeKeyDecoder[A, B](using coercible: Coercible[A, B], decoder: KeyDecoder[A]): KeyDecoder[B] =
    Coercible.unsafeWrapTC(decoder)

  given derivedRefinedKeyDecoder[A, B](using refinedCtor: RefinedCtor[B, A], decoder: KeyDecoder[A]): KeyDecoder[B] with {
    override def apply(key: String): Option[B] =
      decoder.apply(key).flatMap(refinedCtor.create(_).toOption)
  }

  /** Without this, Circe's decode uses an incorrect Decoder for the value.
    * @example
    * {{{
    *   // when
    *   decode[Map[PortNumber, Int]]("""{ "0": 65536 }""")
    *
    *   // The expected result is
    *   Map(PortNumber(0), 65536)
    *
    *   // But the actual result is
    *   // Either[DecodingFailure, Map[PortNumber, Int]] =
    *   //   Left(DecodingFailure(Invalid value: [65536]. It has to be Int between 0 and 65535 (0 <= PortNumber <= 65535), List(DownField(0))))
    *   // The KeyDecoder is fine here, but the value Decoder `decode` uses is not Decoder[Int] but Decoder[PortNumber].
    * }}}
    */
  given derivedMapDecoder[A, B](using KeyDecoder[A], Decoder[B]): Decoder[Map[A, B]] = io.circe.Decoder.decodeMap

}
object auto extends auto
