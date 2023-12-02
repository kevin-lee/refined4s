package refined4s

import cats.*

object InlinedRefinedType {
  type Something = Something.Type
  object Something extends InlinedRefined[Int] {
    import scala.compiletime.*
    import scala.quoted.*
    override inline def inlinedInvalidReason(inline a: Int): String =
      "The number is a negative Int. [a: " + codeOf(a) + "]"

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

}
