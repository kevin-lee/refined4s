package refined4s.modules.circe.derivation.types

import io.circe.{Decoder, Encoder, KeyDecoder, KeyEncoder}
import refined4s.types
import refined4s.types.strings.*

/** @author Kevin Lee
  * @since 2023-12-25
  */
trait strings {

  /* NonEmptyString */
  inline given derivedNonEmptyStringEncoder: Encoder[NonEmptyString] = strings.derivedNonEmptyStringEncoder
  inline given derivedNonEmptyStringDecoder: Decoder[NonEmptyString] = strings.derivedNonEmptyStringDecoder

  inline given derivedNonEmptyStringKeyEncoder: KeyEncoder[NonEmptyString] = strings.derivedNonEmptyStringKeyEncoder
  inline given derivedNonEmptyStringKeyDecoder: KeyDecoder[NonEmptyString] = strings.derivedNonEmptyStringKeyDecoder

  /* NonBlankString */
  inline given derivedNonBlankStringEncoder: Encoder[NonBlankString] = strings.derivedNonBlankStringEncoder
  inline given derivedNonBlankStringDecoder: Decoder[NonBlankString] = strings.derivedNonBlankStringDecoder

  inline given derivedNonBlankStringKeyEncoder: KeyEncoder[NonBlankString] = strings.derivedNonBlankStringKeyEncoder
  inline given derivedNonBlankStringKeyDecoder: KeyDecoder[NonBlankString] = strings.derivedNonBlankStringKeyDecoder

  /* Uuid */
  inline given derivedUuidEncoder: Encoder[Uuid] = strings.derivedUuidEncoder
  inline given derivedUuidDecoder: Decoder[Uuid] = strings.derivedUuidDecoder

  inline given derivedUuidKeyEncoder: KeyEncoder[Uuid] = strings.derivedUuidKeyEncoder
  inline given derivedUuidKeyDecoder: KeyDecoder[Uuid] = strings.derivedUuidKeyDecoder
}
object strings {

  /* NonEmptyString */
  given derivedNonEmptyStringEncoder: Encoder[NonEmptyString] = Encoder[String].contramap[NonEmptyString](_.value)
  given derivedNonEmptyStringDecoder: Decoder[NonEmptyString] = Decoder[String].emap(NonEmptyString.from)

  given derivedNonEmptyStringKeyEncoder: KeyEncoder[NonEmptyString] with {
    override def apply(key: NonEmptyString): String = key.value
  }
  given derivedNonEmptyStringKeyDecoder: KeyDecoder[NonEmptyString] with {
    override def apply(key: String): Option[NonEmptyString] = NonEmptyString.from(key).toOption
  }

  /* NonBlankString */
  given derivedNonBlankStringEncoder: Encoder[NonBlankString] = Encoder[String].contramap[NonBlankString](_.value)
  given derivedNonBlankStringDecoder: Decoder[NonBlankString] = Decoder[String].emap(NonBlankString.from)

  given derivedNonBlankStringKeyEncoder: KeyEncoder[NonBlankString] with {
    override def apply(key: NonBlankString): String = key.value
  }
  given derivedNonBlankStringKeyDecoder: KeyDecoder[NonBlankString] with {
    override def apply(key: String): Option[NonBlankString] = NonBlankString.from(key).toOption
  }

  /* Uuid */
  given derivedUuidEncoder: Encoder[Uuid] = Encoder[String].contramap[Uuid](_.value)
  given derivedUuidDecoder: Decoder[Uuid] = Decoder[String].emap(Uuid.from)

  given derivedUuidKeyEncoder: KeyEncoder[Uuid] with {
    override def apply(key: Uuid): String = key.value
  }
  given derivedUuidKeyDecoder: KeyDecoder[Uuid] with {
    override def apply(key: String): Option[Uuid] = Uuid.from(key).toOption
  }

}
