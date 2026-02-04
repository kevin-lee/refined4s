package refined4s.types

import orphan.{OrphanCats, OrphanCatsKernel}
import refined4s.types
import refined4s.types.numeric.{InlinedNumeric, InlinedNumericMinMax}

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
  object Month extends InlinedNumericMinMax[Int], MonthTypeClassInstances {

    override inline def minValue: Int = 1
    override inline def maxValue: Int = 12

    override inline val inlinedExpectedValue = "in the range from 1 to 12."

    override def invalidReason(a: Int): String = expectedMessage(inlinedExpectedValue)

  }
  private[types] trait MonthTypeClassInstances extends MonthTypeClassInstance1 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    given derivedMonthEq[F[*]: CatsEq, G[*]: CatsEq](using eqActual: G[Int]): F[Month] = {
      internalDef.contraCoercible[cats.Eq, Month, Int, cats.Contravariant](eqActual.asInstanceOf[cats.Eq[Int]])
    }.asInstanceOf[F[Month]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait MonthTypeClassInstance1 extends MonthTypeClassInstance2 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    given derivedMonthHash[F[*]: CatsHash, G[*]: CatsHash](using hashActual: G[Int]): F[Month] = {
      internalDef.contraCoercible[cats.Hash, Month, Int, cats.Contravariant](hashActual.asInstanceOf[cats.Hash[Int]])
    }.asInstanceOf[F[Month]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait MonthTypeClassInstance2 extends OrphanCats, OrphanCatsKernel {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    given derivedMonthShow[F[*]: CatsShow, G[*]: CatsShow](using showActual: G[Int]): F[Month] = {
      internalDef.contraCoercible[cats.Show, Month, Int, cats.Contravariant](showActual.asInstanceOf[cats.Show[Int]])
    }.asInstanceOf[F[Month]] // scalafix:ok DisableSyntax.asInstanceOf
  }

  type Day = Day.Type
  object Day extends InlinedNumericMinMax[Int], DayTypeClassInstances {

    override inline def minValue: Int = 1
    override inline def maxValue: Int = 31

    override inline val inlinedExpectedValue = "in the range from 1 to 31."

    override def invalidReason(a: Int): String = expectedMessage(inlinedExpectedValue)

  }
  private[types] trait DayTypeClassInstances extends DayTypeClassInstance1 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    given derivedDayEq[F[*]: CatsEq, G[*]: CatsEq](using eqActual: G[Int]): F[Day] = {
      internalDef.contraCoercible[cats.Eq, Day, Int, cats.Contravariant](eqActual.asInstanceOf[cats.Eq[Int]])
    }.asInstanceOf[F[Day]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait DayTypeClassInstance1 extends DayTypeClassInstance2 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    given derivedDayHash[F[*]: CatsHash, G[*]: CatsHash](using hashActual: G[Int]): F[Day] = {
      internalDef.contraCoercible[cats.Hash, Day, Int, cats.Contravariant](hashActual.asInstanceOf[cats.Hash[Int]])
    }.asInstanceOf[F[Day]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait DayTypeClassInstance2 extends OrphanCats, OrphanCatsKernel {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    given derivedDayShow[F[*]: CatsShow, G[*]: CatsShow](using showActual: G[Int]): F[Day] = {
      internalDef.contraCoercible[cats.Show, Day, Int, cats.Contravariant](showActual.asInstanceOf[cats.Show[Int]])
    }.asInstanceOf[F[Day]] // scalafix:ok DisableSyntax.asInstanceOf
  }

  type Hour = Hour.Type
  object Hour extends InlinedNumericMinMax[Int], HourTypeClassInstances {

    override inline def minValue: Int = 0
    override inline def maxValue: Int = 23

    override inline val inlinedExpectedValue = "in the range from 0 to 23."

    override def invalidReason(a: Int): String = expectedMessage(inlinedExpectedValue)

  }
  private[types] trait HourTypeClassInstances extends HourTypeClassInstance1 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    given derivedHourEq[F[*]: CatsEq, G[*]: CatsEq](using eqActual: G[Int]): F[Hour] = {
      internalDef.contraCoercible[cats.Eq, Hour, Int, cats.Contravariant](eqActual.asInstanceOf[cats.Eq[Int]])
    }.asInstanceOf[F[Hour]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait HourTypeClassInstance1 extends HourTypeClassInstance2 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    given derivedHourHash[F[*]: CatsHash, G[*]: CatsHash](using hashActual: G[Int]): F[Hour] = {
      internalDef.contraCoercible[cats.Hash, Hour, Int, cats.Contravariant](hashActual.asInstanceOf[cats.Hash[Int]])
    }.asInstanceOf[F[Hour]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait HourTypeClassInstance2 extends OrphanCats, OrphanCatsKernel {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    given derivedHourShow[F[*]: CatsShow, G[*]: CatsShow](using showActual: G[Int]): F[Hour] = {
      internalDef.contraCoercible[cats.Show, Hour, Int, cats.Contravariant](showActual.asInstanceOf[cats.Show[Int]])
    }.asInstanceOf[F[Hour]] // scalafix:ok DisableSyntax.asInstanceOf
  }

  type Minute = Minute.Type
  object Minute extends InlinedNumericMinMax[Int], MinuteTypeClassInstances {

    override inline def minValue: Int = 0
    override inline def maxValue: Int = 59

    override inline val inlinedExpectedValue = "in the range from 0 to 59."

    override def invalidReason(a: Int): String = expectedMessage(inlinedExpectedValue)

  }
  private[types] trait MinuteTypeClassInstances extends MinuteTypeClassInstance1 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    given derivedMinuteEq[F[*]: CatsEq, G[*]: CatsEq](using eqActual: G[Int]): F[Minute] = {
      internalDef.contraCoercible[cats.Eq, Minute, Int, cats.Contravariant](eqActual.asInstanceOf[cats.Eq[Int]])
    }.asInstanceOf[F[Minute]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait MinuteTypeClassInstance1 extends MinuteTypeClassInstance2 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    given derivedMinuteHash[F[*]: CatsHash, G[*]: CatsHash](using hashActual: G[Int]): F[Minute] = {
      internalDef.contraCoercible[cats.Hash, Minute, Int, cats.Contravariant](hashActual.asInstanceOf[cats.Hash[Int]])
    }.asInstanceOf[F[Minute]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait MinuteTypeClassInstance2 extends OrphanCats, OrphanCatsKernel {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    given derivedMinuteShow[F[*]: CatsShow, G[*]: CatsShow](using showActual: G[Int]): F[Minute] = {
      internalDef.contraCoercible[cats.Show, Minute, Int, cats.Contravariant](showActual.asInstanceOf[cats.Show[Int]])
    }.asInstanceOf[F[Minute]] // scalafix:ok DisableSyntax.asInstanceOf
  }

  type Second = Second.Type
  object Second extends InlinedNumericMinMax[Int], SecondTypeClassInstances {

    override inline def minValue: Int = 0
    override inline def maxValue: Int = 59

    override inline val inlinedExpectedValue = "in the range from 0 to 59."

    override def invalidReason(a: Int): String = expectedMessage(inlinedExpectedValue)

  }
  private[types] trait SecondTypeClassInstances extends SecondTypeClassInstance1 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    given derivedSecondEq[F[*]: CatsEq, G[*]: CatsEq](using eqActual: G[Int]): F[Second] = {
      internalDef.contraCoercible[cats.Eq, Second, Int, cats.Contravariant](eqActual.asInstanceOf[cats.Eq[Int]])
    }.asInstanceOf[F[Second]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait SecondTypeClassInstance1 extends SecondTypeClassInstance2 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    given derivedSecondHash[F[*]: CatsHash, G[*]: CatsHash](using hashActual: G[Int]): F[Second] = {
      internalDef.contraCoercible[cats.Hash, Second, Int, cats.Contravariant](hashActual.asInstanceOf[cats.Hash[Int]])
    }.asInstanceOf[F[Second]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait SecondTypeClassInstance2 extends OrphanCats, OrphanCatsKernel {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    given derivedSecondShow[F[*]: CatsShow, G[*]: CatsShow](using showActual: G[Int]): F[Second] = {
      internalDef.contraCoercible[cats.Show, Second, Int, cats.Contravariant](showActual.asInstanceOf[cats.Show[Int]])
    }.asInstanceOf[F[Second]] // scalafix:ok DisableSyntax.asInstanceOf
  }

  type Millis = Millis.Type
  object Millis extends InlinedNumericMinMax[Int], MillisTypeClassInstances {

    override inline def minValue: Int = 0
    override inline def maxValue: Int = 999

    override inline val inlinedExpectedValue = "in the range from 0 to 999."

    override def invalidReason(a: Int): String = expectedMessage(inlinedExpectedValue)

  }
  private[types] trait MillisTypeClassInstances extends MillisTypeClassInstance1 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    given derivedMillisEq[F[*]: CatsEq, G[*]: CatsEq](using eqActual: G[Int]): F[Millis] = {
      internalDef.contraCoercible[cats.Eq, Millis, Int, cats.Contravariant](eqActual.asInstanceOf[cats.Eq[Int]])
    }.asInstanceOf[F[Millis]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait MillisTypeClassInstance1 extends MillisTypeClassInstance2 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    given derivedMillisHash[F[*]: CatsHash, G[*]: CatsHash](using hashActual: G[Int]): F[Millis] = {
      internalDef.contraCoercible[cats.Hash, Millis, Int, cats.Contravariant](hashActual.asInstanceOf[cats.Hash[Int]])
    }.asInstanceOf[F[Millis]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait MillisTypeClassInstance2 extends OrphanCats, OrphanCatsKernel {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    given derivedMillisShow[F[*]: CatsShow, G[*]: CatsShow](using showActual: G[Int]): F[Millis] = {
      internalDef.contraCoercible[cats.Show, Millis, Int, cats.Contravariant](showActual.asInstanceOf[cats.Show[Int]])
    }.asInstanceOf[F[Millis]] // scalafix:ok DisableSyntax.asInstanceOf
  }

}
