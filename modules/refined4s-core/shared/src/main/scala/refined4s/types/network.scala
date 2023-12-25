package refined4s.types

import refined4s.*

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
      expectedMessage(inlinedExpectedValue)

    override def predicate(a: String): Boolean =
      try {
        new URI(a)
        true
      } catch {
        case NonFatal(_) =>
          false
      }

    override inline val inlinedExpectedValue = "a URI String"

    override inline def inlinedPredicate(inline uri: String): Boolean = ${ isValidateUri('uri) }

    extension (uri: Type) {
      def toURI: URI = new URI(uri.value)
    }

  }

  type PortNumber = PortNumber.Type
  object PortNumber extends Refined[Int] {

    override inline def invalidReason(a: Int): String =
      "It has to be Int between 0 and 65535 (0 <= PortNumber <= 65535)"

    override inline def predicate(a: Int): Boolean =
      0 <= a && a <= 65535
  }

  type SystemPortNumber = SystemPortNumber.Type
  object SystemPortNumber extends Refined[Int] {

    override inline def invalidReason(a: Int): String =
      "It has to be Int between 0 and 1023 (0 <= SystemPortNumber <= 1023)"

    override inline def predicate(a: Int): Boolean =
      0 <= a && a <= 1023
  }

  type NonSystemPortNumber = NonSystemPortNumber.Type
  object NonSystemPortNumber extends Refined[Int] {

    override inline def invalidReason(a: Int): String =
      "It has to be Int between 1024 and 65535 (1024 <= NonSystemPortNumber <= 65535)"

    override inline def predicate(a: Int): Boolean =
      1024 <= a && a <= 65535
  }

  type UserPortNumber = UserPortNumber.Type
  object UserPortNumber extends Refined[Int] {

    override inline def invalidReason(a: Int): String =
      "It has to be Int between 1024 and 49151 (1024 <= UserPortNumber <= 49151)"

    override inline def predicate(a: Int): Boolean =
      1024 <= a && a <= 49151
  }

  type DynamicPortNumber = DynamicPortNumber.Type
  object DynamicPortNumber extends Refined[Int] {

    override inline def invalidReason(a: Int): String =
      "It has to be Int between 49152 and 65535 (49152 <= DynamicPortNumber <= 65535)"

    override inline def predicate(a: Int): Boolean =
      49152 <= a && a <= 65535
  }

}
object network {
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
