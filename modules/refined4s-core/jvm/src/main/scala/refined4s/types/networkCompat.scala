package refined4s.types

import orphan.{OrphanCats, OrphanCatsKernel}
import refined4s.InlinedRefined
import refined4s.types.network.Uri

import scala.quoted.*
import java.net.{URI, URL}
import scala.quoted.{Expr, Quotes}
import scala.util.control.NonFatal

/** @author Kevin Lee
  * @since 2024-09-04
  */
object networkCompat extends OrphanCats, OrphanCatsKernel {

  type Url = Url.Type
  object Url extends InlinedRefined[String] {

    override def invalidReason(a: String): String =
      expectedMessage(inlinedExpectedValue)

    override def predicate(a: String): Boolean =
      try {
        new URI(a).toURL
        true
      } catch {
        case NonFatal(_) =>
          false
      }

    override inline val inlinedExpectedValue = "a URL String"

    override inline def inlinedPredicate(inline url: String): Boolean = ${ isValidateUrl('url) }

    @SuppressWarnings(Array("org.wartremover.warts.ToString", "org.wartremover.warts.Overloading"))
    def apply(a: URL): Type = unsafeFrom(a.toString)

    extension (url: Type) {
      def toURL: URL = new URI(url.value).toURL

      def toUri: Uri = Uri(toURL.toURI)

      def toURI: URI = toURL.toURI
    }

    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedUrlEq[F[*]: CatsEq, G[*]: CatsEq](using eqActual: G[String]): F[Url] = {
      internalDef.contraCoercible(eqActual.asInstanceOf[cats.Eq[String]])
    }.asInstanceOf[F[Url]] // scalafix:ok DisableSyntax.asInstanceOf

    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedUrlShow[F[*]: CatsShow, G[*]: CatsShow](using showActual: G[String]): F[Url] = {
      internalDef.contraCoercible(showActual.asInstanceOf[cats.Show[String]])
    }.asInstanceOf[F[Url]] // scalafix:ok DisableSyntax.asInstanceOf

  }

  def isValidateUrl(urlExpr: Expr[String])(using Quotes): Expr[Boolean] = {
    import quotes.reflect.*
    urlExpr.asTerm match {
      case Inlined(_, _, Literal(StringConstant(urlStr))) =>
        try {
          new java.net.URI(urlStr).toURL
          Expr(true)
        } catch {
          case _: Throwable => Expr(false)
        }
      case _ =>
        report.error(
          UriValidator.UnexpectedLiteralErrorMessage,
          urlExpr,
        )
        Expr(false)
    }
  }

}
