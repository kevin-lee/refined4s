package refined4s.modules.extras.derivation.types

import extras.render.Render

import refined4s.types.network.*

/** @author Kevin Lee
  * @since 2025-09-02
  */
trait network {

  given derivedUriRender: Render[Uri] = network.derivedUriRender

  given derivedUrlRender: Render[Url] = network.derivedUrlRender

  given derivedPortNumberRender: Render[PortNumber] = network.derivedPortNumberRender

  given derivedSystemPortNumberRender: Render[SystemPortNumber] = network.derivedSystemPortNumberRender

  given derivedNonSystemPortNumberRender: Render[NonSystemPortNumber] = network.derivedNonSystemPortNumberRender

  given derivedUserPortNumberRender: Render[UserPortNumber] = network.derivedUserPortNumberRender

  given derivedDynamicPortNumberRender: Render[DynamicPortNumber] = network.derivedDynamicPortNumberRender

}
object network {

  given derivedUriRender(using renderActual: Render[String]): Render[Uri] = renderActual.contramap(_.value)

  given derivedUrlRender(using renderActual: Render[String]): Render[Url] = renderActual.contramap(_.value)

  given derivedPortNumberRender(using renderActual: Render[Int]): Render[PortNumber] =
    renderActual.contramap(_.value)

  given derivedSystemPortNumberRender(using renderActual: Render[Int]): Render[SystemPortNumber] =
    renderActual.contramap(_.value)

  given derivedNonSystemPortNumberRender(using renderActual: Render[Int]): Render[NonSystemPortNumber] =
    renderActual.contramap(_.value)

  given derivedUserPortNumberRender(using renderActual: Render[Int]): Render[UserPortNumber] =
    renderActual.contramap(_.value)

  given derivedDynamicPortNumberRender(using renderActual: Render[Int]): Render[DynamicPortNumber] =
    renderActual.contramap(_.value)

}
