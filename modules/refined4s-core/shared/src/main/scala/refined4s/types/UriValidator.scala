package refined4s.types

import scala.quoted.*

/** @author Kevin Lee
  * @since 2025-08-15
  */
object UriValidator {

  val UnexpectedLiteralErrorMessage: String =
    """Uri must be a string literal.
      |If it's unknown in compile-time, use `Uri.from` or `Uri.unsafeFrom` instead.
      |(unsafeFrom is not recommended)""".stripMargin

  val UnexpectedLiteralErrorMessageForUrl: String =
    """Url must be a string literal.
      |If it's unknown in compile-time, use `Url.from` or `Url.unsafeFrom` instead.
      |(unsafeFrom is not recommended)""".stripMargin

  def isValidateUri(uriExpr: Expr[String])(using Quotes): Expr[Boolean] = {
    import quotes.reflect.*
    uriExpr.asTerm match {
      case Inlined(_, _, Literal(StringConstant(uriStr))) =>
        try {
          new java.net.URI(uriStr)
          Expr(true)
        } catch {
          case ex: Throwable =>
            report.error(
              "Invalid Uri value: " + ex.getMessage,
              uriExpr,
            )
            Expr(false)
        }
      case _ =>
        report.error(
          UriValidator.UnexpectedLiteralErrorMessage,
          uriExpr,
        )
        Expr(false)
    }
  }

}
