package refined4s.types

import refined4s.types
import refined4s.types.numeric.{InlinedNumeric, MinMax}

/** @author Kevin Lee
  * @since 2025-08-09
  */
trait time {
  final type Month = time.Month
  final val Month = time.Month // scalafix:ok DisableSyntax.noFinalVal

  final type Day = time.Day
  final val Day = time.Day // scalafix:ok DisableSyntax.noFinalVal

  final type Hour = time.Hour
  final val Hour = time.Hour // scalafix:ok DisableSyntax.noFinalVal

  final type Minute = time.Minute
  final val Minute = time.Minute // scalafix:ok DisableSyntax.noFinalVal

  final type Second = time.Second
  final val Second = time.Second // scalafix:ok DisableSyntax.noFinalVal

  final type Millis = time.Millis
  final val Millis = time.Millis // scalafix:ok DisableSyntax.noFinalVal

}
object time {
  type Month = Month.Type
  object Month extends InlinedNumeric[Int], MinMax[Int] {

    override inline val inlinedExpectedValue = "in the range from 1 to 12."

    override inline def inlinedPredicate(inline a: Int): Boolean = a >= 1 && a <= 12

    override def min: Type = apply(1)

    override def max: Type = apply(12)

    override def invalidReason(a: Int): String = expectedMessage(inlinedExpectedValue)

    override def predicate(a: Int): Boolean = a >= 1 && a <= 12
  }

  type Day = Day.Type
  object Day extends InlinedNumeric[Int], MinMax[Int] {

    override inline val inlinedExpectedValue = "in the range from 1 to 31."

    override inline def inlinedPredicate(inline a: Int): Boolean = a >= 1 && a <= 31

    override def min: Type = apply(1)

    override def max: Type = apply(31)

    override def invalidReason(a: Int): String = expectedMessage(inlinedExpectedValue)

    override def predicate(a: Int): Boolean = a >= 1 && a <= 31
  }

  type Hour = Hour.Type
  object Hour extends InlinedNumeric[Int], MinMax[Int] {

    override inline val inlinedExpectedValue = "in the range from 0 to 23."

    override inline def inlinedPredicate(inline a: Int): Boolean = a >= 0 && a <= 23

    override def min: Type = apply(0)

    override def max: Type = apply(23)

    override def invalidReason(a: Int): String = expectedMessage(inlinedExpectedValue)

    override def predicate(a: Int): Boolean = a >= 0 && a <= 23
  }

  type Minute = Minute.Type
  object Minute extends InlinedNumeric[Int], MinMax[Int] {

    override inline val inlinedExpectedValue = "in the range from 0 to 59."

    override inline def inlinedPredicate(inline a: Int): Boolean = a >= 0 && a <= 59

    override def min: Type = apply(0)

    override def max: Type = apply(59)

    override def invalidReason(a: Int): String = expectedMessage(inlinedExpectedValue)

    override def predicate(a: Int): Boolean = a >= 0 && a <= 59
  }

  type Second = Second.Type
  object Second extends InlinedNumeric[Int], MinMax[Int] {

    override inline val inlinedExpectedValue = "in the range from 0 to 59."

    override inline def inlinedPredicate(inline a: Int): Boolean = a >= 0 && a <= 59

    override def min: Type = apply(0)

    override def max: Type = apply(59)

    override def invalidReason(a: Int): String = expectedMessage(inlinedExpectedValue)

    override def predicate(a: Int): Boolean = a >= 0 && a <= 59
  }

  type Millis = Millis.Type
  object Millis extends InlinedNumeric[Int], MinMax[Int] {

    override inline val inlinedExpectedValue = "in the range from 0 to 999."

    override inline def inlinedPredicate(inline a: Int): Boolean = a >= 0 && a <= 999

    override def min: Type = apply(0)

    override def max: Type = apply(999)

    override def invalidReason(a: Int): String = expectedMessage(inlinedExpectedValue)

    override def predicate(a: Int): Boolean = a >= 0 && a <= 999
  }

}
