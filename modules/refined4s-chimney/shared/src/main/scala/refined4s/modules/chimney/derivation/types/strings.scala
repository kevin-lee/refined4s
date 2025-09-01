package refined4s.modules.chimney.derivation.types

import io.scalaland.chimney
import io.scalaland.chimney.{PartialTransformer, Transformer}
import refined4s.types.strings.*

/** @author Kevin Lee
  * @since 2023-12-25
  */
trait strings {

  /* NonEmptyString */
  inline given derivedNonEmptyStringToStringTransformer: Transformer[NonEmptyString, String] =
    strings.derivedNonEmptyStringToStringTransformer

  inline given derivedStringToNonEmptyStringPartialTransformer: PartialTransformer[String, NonEmptyString] =
    strings.derivedStringToNonEmptyStringPartialTransformer

  /* NonBlankString */
  inline given derivedNonBlankStringToStringTransformer: Transformer[NonBlankString, String] =
    strings.derivedNonBlankStringToStringTransformer

  inline given derivedStringToNonBlankStringPartialTransformer: PartialTransformer[String, NonBlankString] =
    strings.derivedStringToNonBlankStringPartialTransformer

  /* Uuid */
  inline given derivedUuidToStringTransformer: Transformer[Uuid, String] = strings.derivedUuidToStringTransformer

  inline given derivedStringToUuidPartialTransformer: PartialTransformer[String, Uuid] = strings.derivedStringToUuidPartialTransformer

}
object strings {

  given derivedNonEmptyStringToStringTransformer: Transformer[NonEmptyString, String] with {
    override def transform(src: NonEmptyString): String = src.value
  }
  given derivedStringToNonEmptyStringPartialTransformer: PartialTransformer[String, NonEmptyString] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(NonEmptyString.from(value)))

  given derivedNonBlankStringToStringTransformer: Transformer[NonBlankString, String] with {
    override def transform(src: NonBlankString): String = src.value
  }
  given derivedStringToNonBlankStringPartialTransformer: PartialTransformer[String, NonBlankString] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(NonBlankString.from(value)))

  given derivedUuidToStringTransformer: Transformer[Uuid, String] with {
    override def transform(src: Uuid): String = src.value
  }
  given derivedStringToUuidPartialTransformer: PartialTransformer[String, Uuid] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(Uuid.from(value)))

}
