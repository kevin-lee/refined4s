package refined4s.modules.tapir.derivation.types

import refined4s.types.strings.{NonBlankString, NonEmptyString, Uuid}
import sttp.tapir.Schema

import java.util.UUID

/** @author Kevin Lee
  * @since 2024-04-03
  */
trait strings {

  inline given schemaNonEmptyString: Schema[NonEmptyString] = strings.derivedNonEmptyStringSchema

  inline given schemaNonBlankString: Schema[NonBlankString] = strings.derivedNonBlankStringSchema

  inline given schemaUuid: Schema[Uuid] = strings.derivedUuidSchema

}
object strings {
  given derivedNonEmptyStringSchema: Schema[NonEmptyString] =
    summon[Schema[String]].map(NonEmptyString.from(_).toOption)(_.value)

  given derivedNonBlankStringSchema: Schema[NonBlankString] =
    summon[Schema[String]].map(NonBlankString.from(_).toOption)(_.value)

  given derivedUuidSchema: Schema[Uuid] =
    summon[Schema[UUID]].map(uuid => Some(Uuid.apply(uuid)))(_.toUUID)
}
