package refined4s

import cats.*
import scala.compiletime.*
import scala.quoted.*

object InlinedRefinedType {
  type Something = Something.Type
  object Something extends InlinedRefined[Int] {

    private def inlinedPredicate0(a: Expr[Int])(using Quotes): Expr[Boolean] = {
      import quotes.reflect.*
      a.asTerm match {
        case Inlined(_, _, Literal(IntConstant(num))) =>
          try {
            validate(num)
            Expr(true)
          } catch {
            case _: Throwable => Expr(false)
          }
        case _ =>
          report.error(
            "Something must be a Int literal.",
            a,
          )
          Expr(false)
      }

    }

    override inline def inlinedPredicate(inline a: Int): Boolean = ${ inlinedPredicate0('a) }

    override def invalidReason(a: Int): String = s"The number is a negative Int. [a: ${a.toString}"

    override def predicate(a: Int): Boolean =
      try {
        validate(a)
        true
      } catch {
        case _: Throwable => false
      }

    given eqSomething: Eq[Something] = deriving[Eq]

    given showSomething: Show[Something] = deriving[Show]
  }

  def validate(a: Int): Int =
    if (a >= 0) a else sys.error("The number should be non-negative number.")

  type MoreThan2CharsString = MoreThan2CharsString.Type
  @SuppressWarnings(Array("org.wartremover.warts.Equals"))
  object MoreThan2CharsString extends InlinedRefined[String] {

    override inline def invalidReason(a: String): String =
      "The String should have more than 2 chars but got " + a + " instead"

    override def predicate(a: String): Boolean = a.length > 2

    override inline def inlinedPredicate(inline a: String): Boolean =
      ${ checkStringLength('a) }

    private def checkStringLength(strExpr: Expr[String])(using Quotes): Expr[Boolean] = {
      val str = strExpr.valueOrAbort
      if predicate(str) then Expr(true) else Expr(false)
    }

    given eqMoreThan2CharsString: Eq[MoreThan2CharsString]     = deriving[Eq]
    given showMoreThan2CharsString: Show[MoreThan2CharsString] = deriving[Show]
  }

}
