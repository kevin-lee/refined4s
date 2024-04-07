package refined4s.modules.circe.derivation.types

import io.circe.{Decoder, Encoder}
import refined4s.types.all.*

/** @author Kevin Lee
  * @since 2023-12-25
  */
trait strings {

  inline given derivedNonEmptyStringEncoder: Encoder[NonEmptyString] = Encoder[String].contramap[NonEmptyString](_.value)
  inline given derivedNonEmptyStringDecoder: Decoder[NonEmptyString] = Decoder[String].emap(NonEmptyString.from)

  inline given derivedNonBlankStringEncoder: Encoder[NonBlankString] = Encoder[String].contramap[NonBlankString](_.value)
  inline given derivedNonBlankStringDecoder: Decoder[NonBlankString] = Decoder[String].emap(NonBlankString.from)

  inline given derivedUuidEncoder: Encoder[Uuid] = Encoder[String].contramap[Uuid](_.value)
  inline given derivedUuidDecoder: Decoder[Uuid] = Decoder[String].emap(Uuid.from)

}
