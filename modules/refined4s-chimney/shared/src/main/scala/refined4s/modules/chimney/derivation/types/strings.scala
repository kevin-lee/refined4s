package refined4s.modules.chimney.derivation.types

import io.scalaland.chimney
import io.scalaland.chimney.{PartialTransformer, Transformer}
import refined4s.types.strings.*
import java.util.UUID

/** @author Kevin Lee
  * @since 2023-12-25
  */
trait strings {

  /* NonEmptyString */
  given derivedNonEmptyStringToStringTransformer: Transformer[NonEmptyString, String] =
    strings.derivedNonEmptyStringToStringTransformer

  given derivedStringToNonEmptyStringPartialTransformer: PartialTransformer[String, NonEmptyString] =
    strings.derivedStringToNonEmptyStringPartialTransformer

  /* NonBlankString */
  given derivedNonBlankStringToStringTransformer: Transformer[NonBlankString, String] =
    strings.derivedNonBlankStringToStringTransformer

  given derivedStringToNonBlankStringPartialTransformer: PartialTransformer[String, NonBlankString] =
    strings.derivedStringToNonBlankStringPartialTransformer

  /* Uuid */
  given derivedUuidToStringTransformer: Transformer[Uuid, String] = strings.derivedUuidToStringTransformer

  given derivedStringToUuidPartialTransformer: PartialTransformer[String, Uuid] = strings.derivedStringToUuidPartialTransformer

  /* UuidV7 */
  given derivedUuidV7ToStringTransformer: Transformer[UuidV7, String] = strings.derivedUuidV7ToStringTransformer

  given derivedStringToUuidV7PartialTransformer: PartialTransformer[String, UuidV7] = strings.derivedStringToUuidV7PartialTransformer

  given derivedUuidV7ToJvmUUIDTransformer: Transformer[UuidV7, UUID] = strings.derivedUuidV7ToJvmUUIDTransformer

  given derivedJvmUUIDToUuidV7PartialTransformer: PartialTransformer[UUID, UuidV7] = strings.derivedJvmUUIDToUuidV7PartialTransformer

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

  given derivedUuidV7ToStringTransformer: Transformer[UuidV7, String] with {
    override def transform(src: UuidV7): String = src.value.toString
  }
  given derivedStringToUuidV7PartialTransformer: PartialTransformer[String, UuidV7] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(UuidV7.fromString(value)))

  given derivedUuidV7ToJvmUUIDTransformer: Transformer[UuidV7, UUID] with {
    override def transform(src: UuidV7): UUID = src.value
  }
  given derivedJvmUUIDToUuidV7PartialTransformer: PartialTransformer[UUID, UuidV7] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(UuidV7.from(value)))

}
