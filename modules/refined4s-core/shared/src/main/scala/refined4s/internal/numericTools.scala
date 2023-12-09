package refined4s.internal

import scala.quoted.*

/** @author Kevin Lee
  * @since 2023-12-09
  */
object numericTools {

  val BigInt0: BigInt = BigInt(0)

  def isNegativeBigInt(value: Expr[BigInt])(using Quotes): Expr[Boolean] = {
    import quotes.reflect.*

    value.asTerm match {
      case Inlined(_, _, Inlined(_, _, Apply(Select(Ident("BigInt"), _), List(Literal(StringConstant(bigIntValue)))))) =>
        Expr(BigInt(bigIntValue) < BigInt0)

      case Inlined(_, _, Inlined(_, _, Apply(Select(Ident("BigInt"), _), List(Inlined(_, _, Literal(StringConstant(bigIntValue))))))) =>
        Expr(BigInt(bigIntValue) < BigInt0)

      case Inlined(_, _, Inlined(_, _, Apply(Select(Ident("BigInt"), _), List(Literal(IntConstant(bigIntValue)))))) =>
        Expr(BigInt(bigIntValue) < BigInt0)

      case Inlined(_, _, Inlined(_, _, Apply(Select(Ident("BigInt"), _), List(Inlined(_, _, Literal(IntConstant(bigIntValue))))))) =>
        Expr(BigInt(bigIntValue) < BigInt0)

      case Inlined(_, _, Inlined(_, _, Apply(Select(Ident("BigInt"), _), List(Literal(LongConstant(bigIntValue)))))) =>
        Expr(BigInt(bigIntValue) < BigInt0)

      case Inlined(_, _, Inlined(_, _, Apply(Select(Ident("BigInt"), _), List(Inlined(_, _, Literal(LongConstant(bigIntValue))))))) =>
        Expr(BigInt(bigIntValue) < BigInt0)

      // $COVERAGE-OFF$
      case _ =>
        Expr(false)
      // $COVERAGE-ON$
    }
  }

  def isNonNegativeBigInt(value: Expr[BigInt])(using Quotes): Expr[Boolean] = {
    import quotes.reflect.*

    value.asTerm match {
      case Inlined(_, _, Inlined(_, _, Apply(Select(Ident("BigInt"), _), List(Literal(StringConstant(bigIntValue)))))) =>
        Expr(BigInt(bigIntValue) >= BigInt0)

      case Inlined(_, _, Inlined(_, _, Apply(Select(Ident("BigInt"), _), List(Inlined(_, _, Literal(StringConstant(bigIntValue))))))) =>
        Expr(BigInt(bigIntValue) >= BigInt0)

      case Inlined(_, _, Inlined(_, _, Apply(Select(Ident("BigInt"), _), List(Literal(IntConstant(bigIntValue)))))) =>
        Expr(BigInt(bigIntValue) >= BigInt0)

      case Inlined(_, _, Inlined(_, _, Apply(Select(Ident("BigInt"), _), List(Inlined(_, _, Literal(IntConstant(bigIntValue))))))) =>
        Expr(BigInt(bigIntValue) >= BigInt0)

      case Inlined(_, _, Inlined(_, _, Apply(Select(Ident("BigInt"), _), List(Literal(LongConstant(bigIntValue)))))) =>
        Expr(BigInt(bigIntValue) >= BigInt0)

      case Inlined(_, _, Inlined(_, _, Apply(Select(Ident("BigInt"), _), List(Inlined(_, _, Literal(LongConstant(bigIntValue))))))) =>
        Expr(BigInt(bigIntValue) >= BigInt0)

      // $COVERAGE-OFF$
      case _ =>
        Expr(false)
      // $COVERAGE-ON$
    }
  }

  def isPositiveBigInt(value: Expr[BigInt])(using Quotes): Expr[Boolean] = {
    import quotes.reflect.*

    value.asTerm match {
      case Inlined(_, _, Inlined(_, _, Apply(Select(Ident("BigInt"), _), List(Literal(StringConstant(bigIntValue)))))) =>
        Expr(BigInt(bigIntValue) > BigInt0)

      case Inlined(_, _, Inlined(_, _, Apply(Select(Ident("BigInt"), _), List(Inlined(_, _, Literal(StringConstant(bigIntValue))))))) =>
        Expr(BigInt(bigIntValue) > BigInt0)

      case Inlined(_, _, Inlined(_, _, Apply(Select(Ident("BigInt"), _), List(Literal(IntConstant(bigIntValue)))))) =>
        Expr(BigInt(bigIntValue) > BigInt0)

      case Inlined(_, _, Inlined(_, _, Apply(Select(Ident("BigInt"), _), List(Inlined(_, _, Literal(IntConstant(bigIntValue))))))) =>
        Expr(BigInt(bigIntValue) > BigInt0)

      case Inlined(_, _, Inlined(_, _, Apply(Select(Ident("BigInt"), _), List(Literal(LongConstant(bigIntValue)))))) =>
        Expr(BigInt(bigIntValue) > BigInt0)

      case Inlined(_, _, Inlined(_, _, Apply(Select(Ident("BigInt"), _), List(Inlined(_, _, Literal(LongConstant(bigIntValue))))))) =>
        Expr(BigInt(bigIntValue) > BigInt0)

      // $COVERAGE-OFF$
      case _ =>
        Expr(false)
      // $COVERAGE-ON$
    }
  }

  def isNonPositiveBigInt(value: Expr[BigInt])(using Quotes): Expr[Boolean] = {
    import quotes.reflect.*

    value.asTerm match {
      case Inlined(_, _, Inlined(_, _, Apply(Select(Ident("BigInt"), _), List(Literal(StringConstant(bigIntValue)))))) =>
        Expr(BigInt(bigIntValue) <= BigInt0)

      case Inlined(_, _, Inlined(_, _, Apply(Select(Ident("BigInt"), _), List(Inlined(_, _, Literal(StringConstant(bigIntValue))))))) =>
        Expr(BigInt(bigIntValue) <= BigInt0)

      case Inlined(_, _, Inlined(_, _, Apply(Select(Ident("BigInt"), _), List(Literal(IntConstant(bigIntValue)))))) =>
        Expr(BigInt(bigIntValue) <= BigInt0)

      case Inlined(_, _, Inlined(_, _, Apply(Select(Ident("BigInt"), _), List(Inlined(_, _, Literal(IntConstant(bigIntValue))))))) =>
        Expr(BigInt(bigIntValue) <= BigInt0)

      case Inlined(_, _, Inlined(_, _, Apply(Select(Ident("BigInt"), _), List(Literal(LongConstant(bigIntValue)))))) =>
        Expr(BigInt(bigIntValue) <= BigInt0)

      case Inlined(_, _, Inlined(_, _, Apply(Select(Ident("BigInt"), _), List(Inlined(_, _, Literal(LongConstant(bigIntValue))))))) =>
        Expr(BigInt(bigIntValue) <= BigInt0)

      // $COVERAGE-OFF$
      case _ =>
        Expr(false)
      // $COVERAGE-ON$
    }
  }

}
