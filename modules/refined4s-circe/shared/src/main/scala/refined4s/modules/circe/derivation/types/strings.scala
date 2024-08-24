package refined4s.modules.circe.derivation.types

import io.circe.{Decoder, Encoder, KeyDecoder, KeyEncoder}
import refined4s.types
import refined4s.types.all.*

/** @author Kevin Lee
  * @since 2023-12-25
  */
trait strings {

  /* NonEmptyString */
  inline given derivedNonEmptyStringEncoder: Encoder[NonEmptyString] = Encoder[String].contramap[NonEmptyString](_.value)
  inline given derivedNonEmptyStringDecoder: Decoder[NonEmptyString] = Decoder[String].emap(NonEmptyString.from)

  inline given derivedNonEmptyStringKeyEncoder: KeyEncoder[NonEmptyString] with {
    override def apply(key: NonEmptyString): String = key.value
  }
  inline given derivedNonEmptyStringKeyDecoder: KeyDecoder[NonEmptyString] with {
    override def apply(key: String): Option[NonEmptyString] = NonEmptyString.from(key).toOption
  }

  /* NonBlankString */
  inline given derivedNonBlankStringEncoder: Encoder[NonBlankString] = Encoder[String].contramap[NonBlankString](_.value)
  inline given derivedNonBlankStringDecoder: Decoder[NonBlankString] = Decoder[String].emap(NonBlankString.from)

  inline given derivedNonBlankStringKeyEncoder: KeyEncoder[NonBlankString] with {
    override def apply(key: NonBlankString): String = key.value
  }
  inline given derivedNonBlankStringKeyDecoder: KeyDecoder[NonBlankString] with {
    override def apply(key: String): Option[NonBlankString] = NonBlankString.from(key).toOption
  }

  /* Uuid */
  inline given derivedUuidEncoder: Encoder[Uuid] = Encoder[String].contramap[Uuid](_.value)
  inline given derivedUuidDecoder: Decoder[Uuid] = Decoder[String].emap(Uuid.from)

  inline given derivedUuidKeyEncoder: KeyEncoder[Uuid] with {
    override def apply(key: Uuid): String = key.value
  }
  inline given derivedUuidKeyDecoder: KeyDecoder[Uuid] with {
    override def apply(key: String): Option[Uuid] = Uuid.from(key).toOption
  }

}
object strings extends strings
