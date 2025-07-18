package refined4s.types

import refined4s.InlinedRefined
import refined4s.types.network.Uri
import refined4s.types.network.UnexpectedLiteralErrorMessage

import scala.quoted.*

import java.net.URI
import scala.quoted.{Expr, Quotes}
import scala.util.control.NonFatal

/** @author Kevin Lee
  * @since 2024-09-04
  */
object networkCompat {
  val validUrlSchemes = Set("http", "https", "ftp", "file")

  type Url = Url.Type
  object Url extends InlinedRefined[String] {

    override def invalidReason(a: String): String =
      expectedMessage(inlinedExpectedValue)

    @SuppressWarnings(Array("org.wartremover.warts.JavaNetURLConstructors"))
    override def predicate(a: String): Boolean =
      validate(a) match {
        case Left(err) =>
//          println(err)
          false
        case Right(_) =>
          true
      }

    def validate(url: String): Either[String, String] =
      try {
        val uri = new URI(url)

        val isValidSchemas   = validUrlSchemes.contains(uri.getScheme)
        /* URI.getHost works different from Java.'s URI.getHost */
//        val isValidHost      = Option(uri.getHost).isDefined
        val isValidAuthority = Option(uri.getAuthority).isDefined
        val isAbsolute       = uri.isAbsolute
        val isValidPath      = {
          val path = uri.getPath
          path.isEmpty || path.startsWith("/")
        }

        val errors = List(
          if isValidSchemas then None else Some("Schema"),
//          if isValidHost then None else Some("Host"),
          if isValidAuthority then None else Some("Authority"),
          if isAbsolute then None else Some("Absolute URL"),
          if isValidPath then None else Some("Path"),
        ).flatten

        if errors.isEmpty
        then Right(url)
        else
          Left(
            s"Invalid URL. URL: $url, The following propert${if errors.lengthIs > 1 then "ies are" else "y is"} invalid: ${errors.mkString("[", ", ", "]")}"
          )
      } catch {
        case NonFatal(err) =>
          Left(s"Invalid URL. URL: $url, Error: ${err.getMessage}")
      }

    override inline val inlinedExpectedValue = "a URL String"

    override inline def inlinedPredicate(inline url: String): Boolean = ${ isValidateUrl('url) }

//    @SuppressWarnings(Array("org.wartremover.warts.ToString"))
//    def apply(a: URL): Type = unsafeFrom(a.toString)

    extension (url: Type) {
//      @SuppressWarnings(Array("org.wartremover.warts.JavaNetURLConstructors"))
//      def toURL: URL = new URL(url.value)

      def toUri: Uri = Uri(toURI)

      def toURI: URI = new URI(url.value)
    }

  }

  @SuppressWarnings(Array("org.wartremover.warts.JavaNetURLConstructors"))
  def isValidateUrl(urlExpr: Expr[String])(using Quotes): Expr[Boolean] = {
    import quotes.reflect.*

    def debug(isValid: Boolean, message: String): Unit = if !isValid then report.info(message + " is invalid", urlExpr) else ()

    urlExpr.asTerm match {
      case Inlined(_, _, Literal(StringConstant(urlStr))) =>
        try {
          val uri = new URI(urlStr)

          val isValidSchemas   = validUrlSchemes.contains(uri.getScheme)
//          val isValidHost      = Option(uri.getHost).isDefined
          val isValidAuthority = Option(uri.getAuthority).isDefined
          val isAbsolute       = uri.isAbsolute
          val isValidPath      = {
            val path = uri.getPath
            path.isEmpty || path.startsWith("/")
          }

          debug(isValidSchemas, "Schema")
//          debug(isValidHost, "Host")
          debug(isValidAuthority, "Authority")
          debug(isAbsolute, "It should be absolute but ")
          debug(isValidPath, "Path")

          val validity = isValidSchemas &&
//            isValidHost &&
            isValidAuthority &&
            isAbsolute && isValidPath
          Expr(validity)
        } catch {
          case _: Throwable => Expr(false)
        }
      case _ =>
        report.error(
          UnexpectedLiteralErrorMessage,
          urlExpr,
        )
        Expr(false)
    }
  }

}
