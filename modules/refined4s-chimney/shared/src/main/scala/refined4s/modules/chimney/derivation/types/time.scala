package refined4s.modules.chimney.derivation.types

import io.scalaland.chimney
import io.scalaland.chimney.{PartialTransformer, Transformer}

import refined4s.types.time.*

/** @author Kevin Lee
  * @since 2025-09-01
  */
trait time {
  given derivedMonthToIntTransformer: Transformer[Month, Int]               = time.derivedMonthToIntTransformer
  given derivedIntToMonthPartialTransformer: PartialTransformer[Int, Month] = time.derivedIntToMonthPartialTransformer

  given derivedDayToIntTransformer: Transformer[Day, Int]               = time.derivedDayToIntTransformer
  given derivedIntToDayPartialTransformer: PartialTransformer[Int, Day] = time.derivedIntToDayPartialTransformer

  given derivedHourToIntTransformer: Transformer[Hour, Int]               = time.derivedHourToIntTransformer
  given derivedIntToHourPartialTransformer: PartialTransformer[Int, Hour] = time.derivedIntToHourPartialTransformer

  given derivedMinuteToIntTransformer: Transformer[Minute, Int]               = time.derivedMinuteToIntTransformer
  given derivedIntToMinutePartialTransformer: PartialTransformer[Int, Minute] = time.derivedIntToMinutePartialTransformer

  given derivedSecondToIntTransformer: Transformer[Second, Int]               = time.derivedSecondToIntTransformer
  given derivedIntToSecondPartialTransformer: PartialTransformer[Int, Second] = time.derivedIntToSecondPartialTransformer

  given derivedMillisToIntTransformer: Transformer[Millis, Int]               = time.derivedMillisToIntTransformer
  given derivedIntToMillisPartialTransformer: PartialTransformer[Int, Millis] = time.derivedIntToMillisPartialTransformer

}
object time {

  given derivedMonthToIntTransformer: Transformer[Month, Int] with {
    override def transform(src: Month): Int = src.value
  }
  given derivedIntToMonthPartialTransformer: PartialTransformer[Int, Month] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(Month.from(value)))

  given derivedDayToIntTransformer: Transformer[Day, Int] with {
    override def transform(src: Day): Int = src.value
  }
  given derivedIntToDayPartialTransformer: PartialTransformer[Int, Day] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(Day.from(value)))

  given derivedHourToIntTransformer: Transformer[Hour, Int] with {
    override def transform(src: Hour): Int = src.value
  }
  given derivedIntToHourPartialTransformer: PartialTransformer[Int, Hour] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(Hour.from(value)))

  given derivedMinuteToIntTransformer: Transformer[Minute, Int] with {
    override def transform(src: Minute): Int = src.value
  }
  given derivedIntToMinutePartialTransformer: PartialTransformer[Int, Minute] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(Minute.from(value)))

  given derivedSecondToIntTransformer: Transformer[Second, Int] with {
    override def transform(src: Second): Int = src.value
  }
  given derivedIntToSecondPartialTransformer: PartialTransformer[Int, Second] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(Second.from(value)))

  given derivedMillisToIntTransformer: Transformer[Millis, Int] with {
    override def transform(src: Millis): Int = src.value
  }
  given derivedIntToMillisPartialTransformer: PartialTransformer[Int, Millis] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(Millis.from(value)))

}
