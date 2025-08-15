package refined4s.types

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
  object Url extends InlinedRefined[String] {

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

  @SuppressWarnings(Array("org.wartremover.warts.JavaNetURLConstructors"))
  def isValidateUrl(urlExpr: Expr[String])(using Quotes): Expr[Boolean] = {
    import quotes.reflect.*
    urlExpr.asTerm match {
      case Inlined(_, _, Literal(StringConstant(urlStr))) =>
        try {
          new java.net.URL(urlStr)
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
