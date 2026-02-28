package refined4s.modules.tapir.derivation.types

import refined4s.types.strings.{NonBlankString, NonEmptyString, Uuid, UuidV7}
import sttp.tapir.Codec.PlainCodec
import sttp.tapir.{Codec, DecodeResult, Schema}

import java.util.UUID

/** @author Kevin Lee
  * @since 2024-04-03
  */
trait strings {

  given schemaNonEmptyString: Schema[NonEmptyString] = strings.derivedNonEmptyStringSchema

  given schemaNonBlankString: Schema[NonBlankString] = strings.derivedNonBlankStringSchema

  given schemaUuid: Schema[Uuid] = strings.derivedUuidSchema

  given schemaUuidV7: Schema[UuidV7] = strings.derivedUuidV7Schema

  given codecUuidV7: PlainCodec[UuidV7] = strings.derivedUuidV7Codec

}
object strings {
  given derivedNonEmptyStringSchema: Schema[NonEmptyString] =
    summon[Schema[String]].map(NonEmptyString.from(_).toOption)(_.value)

  given derivedNonBlankStringSchema: Schema[NonBlankString] =
    summon[Schema[String]].map(NonBlankString.from(_).toOption)(_.value)

  given derivedUuidSchema: Schema[Uuid] =
    summon[Schema[UUID]].map(uuid => Some(Uuid.apply(uuid)))(_.toUUID)

  given derivedUuidV7Schema: Schema[UuidV7] =
    summon[Schema[UUID]].map(uuid => UuidV7.from(uuid).toOption)(_.value)

  given derivedUuidV7Codec: PlainCodec[UuidV7] = Codec
    .uuid
    .mapDecode { uuid =>
      DecodeResult.fromEitherString(uuid.toString, UuidV7.from(uuid))
    }(_.toUUID)

}
