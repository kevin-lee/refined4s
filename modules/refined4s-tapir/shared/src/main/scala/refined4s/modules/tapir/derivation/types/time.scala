package refined4s.modules.tapir.derivation.types

import sttp.tapir.Schema
import refined4s.types.time.*

/** @author Kevin Lee
  * @since 2025-09-03
  */
trait time {

  given derivedMonthSchema: Schema[Month] = time.derivedMonthSchema

  given derivedDaySchema: Schema[Day] = time.derivedDaySchema

  given derivedHourSchema: Schema[Hour] = time.derivedHourSchema

  given derivedMinuteSchema: Schema[Minute] = time.derivedMinuteSchema

  given derivedSecondSchema: Schema[Second] = time.derivedSecondSchema

  given derivedMillisSchema: Schema[Millis] = time.derivedMillisSchema

}
object time {

  given derivedMonthSchema(using actualSchema: Schema[Int]): Schema[Month] = actualSchema.map(Month.from(_).toOption)(_.value)

  given derivedDaySchema(using actualSchema: Schema[Int]): Schema[Day] = actualSchema.map(Day.from(_).toOption)(_.value)

  given derivedHourSchema(using actualSchema: Schema[Int]): Schema[Hour] = actualSchema.map(Hour.from(_).toOption)(_.value)

  given derivedMinuteSchema(using actualSchema: Schema[Int]): Schema[Minute] = actualSchema.map(Minute.from(_).toOption)(_.value)

  given derivedSecondSchema(using actualSchema: Schema[Int]): Schema[Second] = actualSchema.map(Second.from(_).toOption)(_.value)

  given derivedMillisSchema(using actualSchema: Schema[Int]): Schema[Millis] = actualSchema.map(Millis.from(_).toOption)(_.value)

}
