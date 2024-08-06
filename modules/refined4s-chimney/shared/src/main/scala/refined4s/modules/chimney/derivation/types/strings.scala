package refined4s.modules.chimney.derivation.types

import io.scalaland.chimney
import io.scalaland.chimney.{PartialTransformer, Transformer}
import refined4s.types.strings.*

/** @author Kevin Lee
  * @since 2023-12-25
  */
trait strings {

  inline given derivedNonEmptyStringToStringTransformer: Transformer[NonEmptyString, String] with {
    override def transform(src: NonEmptyString): String = src.value
  }
  inline given derivedStringToNonEmptyStringPartialTransformer: PartialTransformer[String, NonEmptyString] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(NonEmptyString.from(value)))

  inline given derivedNonBlankStringToStringTransformer: Transformer[NonBlankString, String] with {
    override def transform(src: NonBlankString): String = src.value
  }
  inline given derivedStringToNonBlankStringPartialTransformer: PartialTransformer[String, NonBlankString] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(NonBlankString.from(value)))

  inline given derivedUuidToStringTransformer: Transformer[Uuid, String] with {
    override def transform(src: Uuid): String = src.value
  }
  inline given derivedStringToUuidPartialTransformer: PartialTransformer[String, Uuid] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(Uuid.from(value)))
}
object strings extends strings
