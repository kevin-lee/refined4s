package refined4s.modules.extras.derivation.types

import extras.render.Render
import refined4s.types.strings.*

/** @author Kevin Lee
  * @since 2025-09-02
  */
trait strings {

  given derivedNonEmptyStringRender: Render[NonEmptyString] = strings.derivedNonEmptyStringRender

  given derivedNonBlankStringRender: Render[NonBlankString] = strings.derivedNonBlankStringRender

  given derivedUuidRender: Render[Uuid] = strings.derivedUuidRender

  /* TODO: #597 - Temporarily hide UuidV7 until it's ready for use.
  given derivedUuidV7Render: Render[UuidV7] = strings.derivedUuidV7Render
   */

}
object strings {

  given derivedNonEmptyStringRender(using renderActual: Render[String]): Render[NonEmptyString] =
    renderActual.contramap(_.value)

  given derivedNonBlankStringRender(using renderActual: Render[String]): Render[NonBlankString] =
    renderActual.contramap(_.value)

  given derivedUuidRender(using renderActual: Render[String]): Render[Uuid] =
    renderActual.contramap(_.value)

  /* TODO: #597 - Temporarily hide UuidV7 until it's ready for use.
  given derivedUuidV7Render: Render[UuidV7] = Render.uuidRender.contramap(_.value)
   */

}
