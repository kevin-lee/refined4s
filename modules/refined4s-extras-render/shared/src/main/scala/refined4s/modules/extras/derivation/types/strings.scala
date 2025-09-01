package refined4s.modules.extras.derivation.types

import extras.render.Render
import refined4s.types.strings.*

/** @author Kevin Lee
  * @since 2025-09-02
  */
trait strings {

  inline given derivedNonEmptyStringRender: Render[NonEmptyString] = strings.derivedNonEmptyStringRender

  inline given derivedNonBlankStringRender: Render[NonBlankString] = strings.derivedNonBlankStringRender

  inline given derivedUuidRender: Render[Uuid] = strings.derivedUuidRender

}
object strings {

  given derivedNonEmptyStringRender(using renderActual: Render[String]): Render[NonEmptyString] =
    renderActual.contramap(_.value)

  given derivedNonBlankStringRender(using renderActual: Render[String]): Render[NonBlankString] =
    renderActual.contramap(_.value)

  given derivedUuidRender(using renderActual: Render[String]): Render[Uuid] =
    renderActual.contramap(_.value)

}
