package refined4s

import scala.quoted.*
import scala.util.control.NonFatal

import network.*

import java.net.URI

/** @author Kevin Lee
  * @since 2023-12-09
  */
trait network {

  type Uri = Uri.Type
  object Uri extends InlinedRefined[String] {

    override def invalidReason(a: String): String =
      "It has to be a URI but got [" + a + "]"

    override def predicate(a: String): Boolean =
      try {
        new URI(a)
        true
      } catch {
        case NonFatal(_) =>
          false
      }

    override inline def inlinedPredicate(inline uri: String): Boolean = ${ isValidateUri('uri) }

    extension (uri: Type) {
      def toURI: URI = new URI(uri.value)
    }

  }
}
object network extends network {
  val UnexpectedLiteralErrorMessage: String =
    """Uri must be a string literal.
      |If it's unknown in compile-time, use `Uri.from` or `Uri.unsafeFrom` instead.
      |(unsafeFrom is not recommended)""".stripMargin

  def isValidateUri(uriExpr: Expr[String])(using Quotes): Expr[Boolean] = {
    import quotes.reflect.*
    uriExpr.asTerm match {
      case Inlined(_, _, Literal(StringConstant(uriStr))) =>
        try {
          new java.net.URI(uriStr)
          Expr(true)
        } catch {
          case _: Throwable => Expr(false)
        }
      case _ =>
        report.error(
          UnexpectedLiteralErrorMessage,
          uriExpr,
        )
        Expr(false)
    }
  }

}
