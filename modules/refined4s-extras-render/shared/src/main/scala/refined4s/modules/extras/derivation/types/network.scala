package refined4s.modules.extras.derivation.types

import extras.render.Render

import refined4s.types.network.*

/** @author Kevin Lee
  * @since 2025-09-02
  */
trait network {

  inline given derivedUriRender: Render[Uri] = network.derivedUriRender

  inline given derivedUrlRender: Render[Url] = network.derivedUrlRender

  inline given derivedPortNumberRender: Render[PortNumber] = network.derivedPortNumberRender

  inline given derivedSystemPortNumberRender: Render[SystemPortNumber] = network.derivedSystemPortNumberRender

  inline given derivedNonSystemPortNumberRender: Render[NonSystemPortNumber] = network.derivedNonSystemPortNumberRender

  inline given derivedUserPortNumberRender: Render[UserPortNumber] = network.derivedUserPortNumberRender

  inline given derivedDynamicPortNumberRender: Render[DynamicPortNumber] = network.derivedDynamicPortNumberRender

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
