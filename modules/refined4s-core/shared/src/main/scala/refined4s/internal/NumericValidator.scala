package refined4s.internal

import scala.quoted.*

/** @author Kevin Lee
  * @since 2026-02-02
  */
//object NumericValidator {
//  inline def lt[A](inline a1: A, inline a2: A)(using inline ordering: Ordering[A]): Boolean =
//    ${ NumericValidatorImpl.ltImpl[A]('a1, 'a2, 'ordering) }
//
////  inline def lt2[A](inline a1: A, inline a2: A): Boolean =
////    ${ NumericValidatorImpl.ltImpl2[A]('a1, 'a2) }
////
////  inline def lt3[A](inline a1: A, inline a2: A): Boolean =
////    ${ NumericValidatorImpl.ltImpl3[A]('a1, 'a2) }
//
//  inline def lteq[A](inline a1: A, inline a2: A)(using inline ordering: Ordering[A]): Boolean =
//    ${ NumericValidatorImpl.lteqImpl[A]('a1, 'a2) }
//
//  inline def equiv[A](inline a1: A, inline a2: A)(using inline ordering: Ordering[A]): Boolean =
//    ${ NumericValidatorImpl.equivImpl[A]('a1, 'a2, 'ordering) }
//
//  inline def gt[A](inline a1: A, inline a2: A)(using inline ordering: Ordering[A]): Boolean =
//    ${ NumericValidatorImpl.gtImpl[A]('a1, 'a2, 'ordering) }
//
//  inline def gteq[A](inline a1: A, inline a2: A)(using inline ordering: Ordering[A]): Boolean =
//    ${ NumericValidatorImpl.gteqImpl[A]('a1, 'a2) }
//
//  inline def withinMinMax[A](inline a1: A, inline minVal: A, inline maxVal: A)(using Ordering[A]): Boolean =
//    ${ NumericValidatorImpl.withinMinMaxImpl[A]('a1, 'minVal, 'maxVal) }
//
//}
object NumericValidator {
//  def ltImpl2[A: Type](
//    expr1: Expr[A],
//    expr2: Expr[A],
//    orderingExpr: Expr[Ordering[A]],
//  )(using Quotes): Expr[Boolean] =
//    '{ $orderingExpr.lt($expr1, $expr2) }
//
//  def ltImpl3[A: Type](expr1: Expr[A], expr2: Expr[A])(using Quotes): Expr[Boolean] = {
//    val orderingExpr = Expr.summon[Ordering[A]].getOrElse {
//      quotes.reflect.report.errorAndAbort(s"Ordering[${Type.show[A]}] not found")
//    }
//    '{ $orderingExpr.lt($expr1, $expr2) }
//  }

  def ltImpl[A: Type](expr1: Expr[A], expr2: Expr[A])(using Quotes): Expr[Boolean] = {
    import quotes.reflect.*

    def runtimeFallback: Expr[Boolean] = {
      val orderingExpr = Expr.summon[Ordering[A]].getOrElse {
        report.errorAndAbort(s"Ordering[${Type.show[A]}] not found")
      }
      // TODO: Improve this. This doesn't work well in compile-time evaluation for predicate
      '{ $orderingExpr.lt($expr1, $expr2) }
    }

    TypeRepr.of[A] match {
      case t if t =:= TypeRepr.of[Int] =>
        (expr1.asExprOf[Int].value, expr2.asExprOf[Int].value) match {
          case (Some(x), Some(y)) => Expr(x < y)
          case _ => runtimeFallback
        }
      case t if t =:= TypeRepr.of[Long] =>
        (expr1.asExprOf[Long].value, expr2.asExprOf[Long].value) match {
          case (Some(x), Some(y)) => Expr(x < y)
          case _ => runtimeFallback
        }
      case t if t =:= TypeRepr.of[Double] =>
        (expr1.asExprOf[Double].value, expr2.asExprOf[Double].value) match {
          case (Some(x), Some(y)) => Expr(x < y)
          case _ => runtimeFallback
        }
      case t if t =:= TypeRepr.of[Float] =>
        (expr1.asExprOf[Float].value, expr2.asExprOf[Float].value) match {
          case (Some(x), Some(y)) => Expr(x < y)
          case _ => runtimeFallback
        }
      case t if t =:= TypeRepr.of[Short] =>
        (expr1.asExprOf[Short].value, expr2.asExprOf[Short].value) match {
          case (Some(x), Some(y)) => Expr(x < y)
          case _ => runtimeFallback
        }
      case t if t =:= TypeRepr.of[Byte] =>
        (expr1.asExprOf[Byte].value, expr2.asExprOf[Byte].value) match {
          case (Some(x), Some(y)) => Expr(x < y)
          case _ => runtimeFallback
        }
      case t if t =:= TypeRepr.of[Char] =>
        (expr1.asExprOf[Char].value, expr2.asExprOf[Char].value) match {
          case (Some(x), Some(y)) => Expr(x < y)
          case _ => runtimeFallback
        }
      case _ => runtimeFallback
    }
  }

  def lteqImpl[A: Type](
    expr1: Expr[A],
    expr2: Expr[A],
  )(using Quotes): Expr[Boolean] = {
    import quotes.reflect.*

    def runtimeFallback: Expr[Boolean] = {
      val orderingExpr = Expr.summon[Ordering[A]].getOrElse {
        report.errorAndAbort(s"Ordering[${Type.show[A]}] not found")
      }
      // TODO: Improve this. This doesn't work well in compile-time evaluation for predicate
      '{ $orderingExpr.lteq($expr1, $expr2) }
    }

    TypeRepr.of[A] match {
      case t if t =:= TypeRepr.of[Int] =>
        (expr1.asExprOf[Int].value, expr2.asExprOf[Int].value) match {
          case (Some(x), Some(y)) => Expr(x <= y)
          case _ => runtimeFallback
        }
      case t if t =:= TypeRepr.of[Long] =>
        (expr1.asExprOf[Long].value, expr2.asExprOf[Long].value) match {
          case (Some(x), Some(y)) => Expr(x <= y)
          case _ => runtimeFallback
        }
      case t if t =:= TypeRepr.of[Double] =>
        (expr1.asExprOf[Double].value, expr2.asExprOf[Double].value) match {
          case (Some(x), Some(y)) => Expr(x <= y)
          case _ => runtimeFallback
        }
      case t if t =:= TypeRepr.of[Float] =>
        (expr1.asExprOf[Float].value, expr2.asExprOf[Float].value) match {
          case (Some(x), Some(y)) => Expr(x <= y)
          case _ => runtimeFallback
        }
      case t if t =:= TypeRepr.of[Short] =>
        (expr1.asExprOf[Short].value, expr2.asExprOf[Short].value) match {
          case (Some(x), Some(y)) => Expr(x <= y)
          case _ => runtimeFallback
        }
      case t if t =:= TypeRepr.of[Byte] =>
        (expr1.asExprOf[Byte].value, expr2.asExprOf[Byte].value) match {
          case (Some(x), Some(y)) => Expr(x <= y)
          case _ => runtimeFallback
        }
      case t if t =:= TypeRepr.of[Char] =>
        (expr1.asExprOf[Char].value, expr2.asExprOf[Char].value) match {
          case (Some(x), Some(y)) => Expr(x <= y)
          case _ => runtimeFallback
        }
      case _ => runtimeFallback
    }
  }

  def equivImpl[A: Type](
    expr1: Expr[A],
    expr2: Expr[A],
  )(using Quotes): Expr[Boolean] = {
    import quotes.reflect.*

    def runtimeFallback: Expr[Boolean] = {
      val orderingExpr = Expr.summon[Ordering[A]].getOrElse {
        report.errorAndAbort(s"Ordering[${Type.show[A]}] not found")
      }
      // TODO: Improve this. This doesn't work well in compile-time evaluation for predicate
      '{ $orderingExpr.equiv($expr1, $expr2) }
    }

    TypeRepr.of[A] match {
      case t if t =:= TypeRepr.of[Int] =>
        (expr1.asExprOf[Int].value, expr2.asExprOf[Int].value) match {
          case (Some(x), Some(y)) => Expr(x == y)
          case _ => runtimeFallback
        }
      case t if t =:= TypeRepr.of[Long] =>
        (expr1.asExprOf[Long].value, expr2.asExprOf[Long].value) match {
          case (Some(x), Some(y)) => Expr(x == y)
          case _ => runtimeFallback
        }
      case t if t =:= TypeRepr.of[Double] =>
        (expr1.asExprOf[Double].value, expr2.asExprOf[Double].value) match {
          case (Some(x), Some(y)) => Expr(x == y)
          case _ => runtimeFallback
        }
      case t if t =:= TypeRepr.of[Float] =>
        (expr1.asExprOf[Float].value, expr2.asExprOf[Float].value) match {
          case (Some(x), Some(y)) => Expr(x == y)
          case _ => runtimeFallback
        }
      case t if t =:= TypeRepr.of[Short] =>
        (expr1.asExprOf[Short].value, expr2.asExprOf[Short].value) match {
          case (Some(x), Some(y)) => Expr(x == y)
          case _ => runtimeFallback
        }
      case t if t =:= TypeRepr.of[Byte] =>
        (expr1.asExprOf[Byte].value, expr2.asExprOf[Byte].value) match {
          case (Some(x), Some(y)) => Expr(x == y)
          case _ => runtimeFallback
        }
      case t if t =:= TypeRepr.of[Char] =>
        (expr1.asExprOf[Char].value, expr2.asExprOf[Char].value) match {
          case (Some(x), Some(y)) => Expr(x == y)
          case _ => runtimeFallback
        }
      case _ => runtimeFallback
    }
  }

  def gtImpl[A: Type](
    expr1: Expr[A],
    expr2: Expr[A],
  )(using Quotes): Expr[Boolean] = {
    import quotes.reflect.*

    def runtimeFallback: Expr[Boolean] = {
      val orderingExpr = Expr.summon[Ordering[A]].getOrElse {
        report.errorAndAbort(s"Ordering[${Type.show[A]}] not found")
      }
      // TODO: Improve this. This doesn't work well in compile-time evaluation for predicate
      '{ $orderingExpr.gt($expr1, $expr2) }
    }

    TypeRepr.of[A] match {
      case t if t =:= TypeRepr.of[Int] =>
        (expr1.asExprOf[Int].value, expr2.asExprOf[Int].value) match {
          case (Some(x), Some(y)) => Expr(x > y)
          case _ => runtimeFallback
        }
      case t if t =:= TypeRepr.of[Long] =>
        (expr1.asExprOf[Long].value, expr2.asExprOf[Long].value) match {
          case (Some(x), Some(y)) => Expr(x > y)
          case _ => runtimeFallback
        }
      case t if t =:= TypeRepr.of[Double] =>
        (expr1.asExprOf[Double].value, expr2.asExprOf[Double].value) match {
          case (Some(x), Some(y)) => Expr(x > y)
          case _ => runtimeFallback
        }
      case t if t =:= TypeRepr.of[Float] =>
        (expr1.asExprOf[Float].value, expr2.asExprOf[Float].value) match {
          case (Some(x), Some(y)) => Expr(x > y)
          case _ => runtimeFallback
        }
      case t if t =:= TypeRepr.of[Short] =>
        (expr1.asExprOf[Short].value, expr2.asExprOf[Short].value) match {
          case (Some(x), Some(y)) => Expr(x > y)
          case _ => runtimeFallback
        }
      case t if t =:= TypeRepr.of[Byte] =>
        (expr1.asExprOf[Byte].value, expr2.asExprOf[Byte].value) match {
          case (Some(x), Some(y)) => Expr(x > y)
          case _ => runtimeFallback
        }
      case t if t =:= TypeRepr.of[Char] =>
        (expr1.asExprOf[Char].value, expr2.asExprOf[Char].value) match {
          case (Some(x), Some(y)) => Expr(x > y)
          case _ => runtimeFallback
        }
      case _ => runtimeFallback
    }
  }

  def gteqImpl[A: Type](
    expr1: Expr[A],
    expr2: Expr[A],
  )(using Quotes): Expr[Boolean] = {
    import quotes.reflect.*

    def runtimeFallback: Expr[Boolean] = {
      val orderingExpr = Expr.summon[Ordering[A]].getOrElse {
        report.errorAndAbort(s"Ordering[${Type.show[A]}] not found")
      }
      // TODO: Improve this. This doesn't work well in compile-time evaluation for predicate
      '{ $orderingExpr.gteq($expr1, $expr2) }
    }

    TypeRepr.of[A] match {
      case t if t =:= TypeRepr.of[Int] =>
        (expr1.asExprOf[Int].value, expr2.asExprOf[Int].value) match {
          case (Some(x), Some(y)) => Expr(x >= y)
          case _ => runtimeFallback
        }
      case t if t =:= TypeRepr.of[Long] =>
        (expr1.asExprOf[Long].value, expr2.asExprOf[Long].value) match {
          case (Some(x), Some(y)) => Expr(x >= y)
          case _ => runtimeFallback
        }
      case t if t =:= TypeRepr.of[Double] =>
        (expr1.asExprOf[Double].value, expr2.asExprOf[Double].value) match {
          case (Some(x), Some(y)) => Expr(x >= y)
          case _ => runtimeFallback
        }
      case t if t =:= TypeRepr.of[Float] =>
        (expr1.asExprOf[Float].value, expr2.asExprOf[Float].value) match {
          case (Some(x), Some(y)) => Expr(x >= y)
          case _ => runtimeFallback
        }
      case t if t =:= TypeRepr.of[Short] =>
        (expr1.asExprOf[Short].value, expr2.asExprOf[Short].value) match {
          case (Some(x), Some(y)) => Expr(x >= y)
          case _ => runtimeFallback
        }
      case t if t =:= TypeRepr.of[Byte] =>
        (expr1.asExprOf[Byte].value, expr2.asExprOf[Byte].value) match {
          case (Some(x), Some(y)) => Expr(x >= y)
          case _ => runtimeFallback
        }
      case t if t =:= TypeRepr.of[Char] =>
        (expr1.asExprOf[Char].value, expr2.asExprOf[Char].value) match {
          case (Some(x), Some(y)) => Expr(x >= y)
          case _ => runtimeFallback
        }
      case _ => runtimeFallback
    }
  }

  def withinMinMaxImpl[A: Type](
    expr1: Expr[A],
    exprMin: Expr[A],
    exprMax: Expr[A],
  )(using Quotes): Expr[Boolean] = {
    import quotes.reflect.*

    inline def runtimeFallback: Expr[Boolean] = {
      val orderingExpr = Expr.summon[Ordering[A]].getOrElse {
        report.errorAndAbort(s"Ordering[${Type.show[A]}] not found")
      }
//      println(s">> Ordering[${Type.show[A]}] ${expr1.show}")
      // TODO: Improve this. This doesn't work well in compile-time evaluation for predicate
      '{ $orderingExpr.gteq($expr1, $exprMin) && $orderingExpr.lteq($expr1, $exprMax) }
    }

    TypeRepr.of[A] match {
      case t if t =:= TypeRepr.of[Int] =>
//        println(">> TypeRepr.of[Int]")
        (expr1.asExprOf[Int].value, exprMin.asExprOf[Int].value, exprMax.asExprOf[Int].value) match {
          case (Some(n), Some(min), Some(max)) => Expr(n >= min && n <= max)
          case _ => runtimeFallback
        }
      case t if t =:= TypeRepr.of[Long] =>
//        println(">> TypeRepr.of[Long]")
        (expr1.asExprOf[Long].value, exprMin.asExprOf[Long].value, exprMax.asExprOf[Long].value) match {
          case (Some(n), Some(min), Some(max)) => Expr(n >= min && n <= max)
          case _ => runtimeFallback
        }
      case t if t =:= TypeRepr.of[Double] =>
//        println(">> TypeRepr.of[Double]")
        (expr1.asExprOf[Double].value, exprMin.asExprOf[Double].value, exprMax.asExprOf[Double].value) match {
          case (Some(n), Some(min), Some(max)) => Expr(n >= min && n <= max)
          case _ => runtimeFallback
        }
      case t if t =:= TypeRepr.of[Float] =>
//        println(">> TypeRepr.of[Float]")
        (expr1.asExprOf[Float].value, exprMin.asExprOf[Float].value, exprMax.asExprOf[Float].value) match {
          case (Some(n), Some(min), Some(max)) => Expr(n >= min && n <= max)
          case _ => runtimeFallback
        }
      case t if t =:= TypeRepr.of[Short] =>
//        println(">> TypeRepr.of[Short]")
        (expr1.asExprOf[Short].value, exprMin.asExprOf[Short].value, exprMax.asExprOf[Short].value) match {
          case (Some(n), Some(min), Some(max)) => Expr(n >= min && n <= max)
          case _ => runtimeFallback
        }
      case t if t =:= TypeRepr.of[Byte] =>
//        println(">> TypeRepr.of[Byte]")
        (expr1.asExprOf[Byte].value, exprMin.asExprOf[Byte].value, exprMax.asExprOf[Byte].value) match {
          case (Some(n), Some(min), Some(max)) => Expr(n >= min && n <= max)
          case _ => runtimeFallback
        }
      case t if t =:= TypeRepr.of[Char] =>
//        println(">> TypeRepr.of[Char]")
        (expr1.asExprOf[Char].value, exprMin.asExprOf[Char].value, exprMax.asExprOf[Char].value) match {
          case (Some(n), Some(min), Some(max)) => Expr(n >= min && n <= max)
          case _ => runtimeFallback
        }
      case _ => runtimeFallback
    }
  }

}
