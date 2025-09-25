package refined4s.modules.doobie.derivation.types

import cats.Show
import doobie.{Get, Put}
import refined4s.types.time.*

/** @author Kevin Lee
  * @since 2025-09-03
  */
trait time {

  given derivedMonthGet: Get[Month] = time.derivedMonthGet
  given derivedMonthPut: Put[Month] = time.derivedMonthPut

  given derivedDayGet: Get[Day] = time.derivedDayGet
  given derivedDayPut: Put[Day] = time.derivedDayPut

  given derivedHourGet: Get[Hour] = time.derivedHourGet
  given derivedHourPut: Put[Hour] = time.derivedHourPut

  given derivedMinuteGet: Get[Minute] = time.derivedMinuteGet
  given derivedMinutePut: Put[Minute] = time.derivedMinutePut

  given derivedSecondGet: Get[Second] = time.derivedSecondGet
  given derivedSecondPut: Put[Second] = time.derivedSecondPut

  given derivedMillisGet: Get[Millis] = time.derivedMillisGet
  given derivedMillisPut: Put[Millis] = time.derivedMillisPut

}
object time {

  given derivedMonthGet: Get[Month] = Get[Int].temap(Month.from)
  given derivedMonthPut: Put[Month] = Put[Int].contramap(_.value)

  given derivedDayGet: Get[Day] = Get[Int].temap(Day.from)
  given derivedDayPut: Put[Day] = Put[Int].contramap(_.value)

  given derivedHourGet: Get[Hour] = Get[Int].temap(Hour.from)
  given derivedHourPut: Put[Hour] = Put[Int].contramap(_.value)

  given derivedMinuteGet: Get[Minute] = Get[Int].temap(Minute.from)
  given derivedMinutePut: Put[Minute] = Put[Int].contramap(_.value)

  given derivedSecondGet: Get[Second] = Get[Int].temap(Second.from)
  given derivedSecondPut: Put[Second] = Put[Int].contramap(_.value)

  given derivedMillisGet: Get[Millis] = Get[Int].temap(Millis.from)
  given derivedMillisPut: Put[Millis] = Put[Int].contramap(_.value)

}
