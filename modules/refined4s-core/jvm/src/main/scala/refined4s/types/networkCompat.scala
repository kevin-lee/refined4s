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
object networkCompat {

  type Url = Url.Type
  object Url extends InlinedRefined[String], UrlTypeClassInstances {

    override def invalidReason(a: String): String =
      expectedMessage(inlinedExpectedValue)

    @SuppressWarnings(Array("org.wartremover.warts.JavaNetURLConstructors"))
    override def predicate(a: String): Boolean =
      try {
        new URL(a)
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
      @SuppressWarnings(Array("org.wartremover.warts.JavaNetURLConstructors"))
      def toURL: URL = new URL(url.value)

      def toUri: Uri = Uri(toURL.toURI)

      def toURI: URI = toURL.toURI
    }

  }
  private[types] trait UrlTypeClassInstances extends UrlTypeClassInstance1 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    given derivedUrlEq[F[*]: CatsEq, G[*]: CatsEq](using eqActual: G[String]): F[Url] = {
      internalDef.contraCoercible[cats.Eq, Url, String, cats.Contravariant](eqActual.asInstanceOf[cats.Eq[String]])
    }.asInstanceOf[F[Url]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait UrlTypeClassInstance1 extends UrlTypeClassInstance2 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    given derivedUrlHash[F[*]: CatsHash, G[*]: CatsHash](using hashActual: G[String]): F[Url] = {
      internalDef.contraCoercible[cats.Hash, Url, String, cats.Contravariant](hashActual.asInstanceOf[cats.Hash[String]])
    }.asInstanceOf[F[Url]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait UrlTypeClassInstance2 extends OrphanCats, OrphanCatsKernel {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    given derivedUrlShow[F[*]: CatsShow, G[*]: CatsShow](using showActual: G[String]): F[Url] = {
      internalDef.contraCoercible[cats.Show, Url, String, cats.Contravariant](showActual.asInstanceOf[cats.Show[String]])
    }.asInstanceOf[F[Url]] // scalafix:ok DisableSyntax.asInstanceOf
  }

  @SuppressWarnings(Array("org.wartremover.warts.JavaNetURLConstructors"))
  def isValidateUrl(urlExpr: Expr[String])(using Quotes): Expr[Boolean] = {
    import quotes.reflect.*
    urlExpr.asTerm match {
      case Inlined(_, _, Literal(StringConstant(urlStr))) =>
        try {
          new java.net.URL(urlStr)
          Expr(true)
        } catch {
          case ex: Throwable =>
            report.error(
              "Invalid Url value: " + ex.getMessage,
              urlExpr,
            )
            Expr(false)
        }
      case _ =>
        report.error(
          UriValidator.UnexpectedLiteralErrorMessageForUrl,
          urlExpr,
        )
        Expr(false)
    }
  }

}
