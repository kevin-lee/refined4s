package refined4s.modules.chimney.derivation.types

import hedgehog.*
import hedgehog.runner.*
import io.scalaland.chimney
import io.scalaland.chimney.dsl.*
import refined4s.types.TimeGens
import refined4s.types.time.*

/** @author Kevin Lee
  * @since 2025-09-01
  */
trait timeSpec {

  protected val timeTypeClasses: refined4s.modules.chimney.derivation.types.time
  import timeTypeClasses.given

  def allTests: List[Test] = List(
    // Month
    property("test from Month to Int", testFromMonthToInt),
    property("test from Int to Month", testToIntToMonth),
    // Day
    property("test from Day to Int", testFromDayToInt),
    property("test from Int to Day", testToIntToDay),
    // Hour
    property("test from Hour to Int", testFromHourToInt),
    property("test from Int to Hour", testToIntToHour),
    // Minute
    property("test from Minute to Int", testFromMinuteToInt),
    property("test from Int to Minute", testToIntToMinute),
    // Second
    property("test from Second to Int", testFromSecondToInt),
    property("test from Int to Second", testToIntToSecond),
    // Millis
    property("test from Millis to Int", testFromMillisToInt),
    property("test from Int to Millis", testToIntToMillis),
  )

  def testFromMonthToInt: Property =
    for {
      month <- TimeGens.genMonth.log("month")
    } yield {
      val input = month

      val expected = month.value
      val actual   = input.into[Int].transform

      actual ==== expected
    }

  def testToIntToMonth: Property =
    for {
      month <- TimeGens.genMonth.log("month")
    } yield {
      val input = month.value

      val expected = chimney.partial.Result.fromValue(month)

      val actual = input.intoPartial[Month].transform

      actual ==== expected
    }

  def testFromDayToInt: Property =
    for {
      day <- TimeGens.genDay.log("day")
    } yield {
      val input = day

      val expected = day.value
      val actual   = input.into[Int].transform

      actual ==== expected
    }

  def testToIntToDay: Property =
    for {
      day <- TimeGens.genDay.log("day")
    } yield {
      val input = day.value

      val expected = chimney.partial.Result.fromValue(day)

      val actual = input.intoPartial[Day].transform

      actual ==== expected
    }

  def testFromHourToInt: Property =
    for {
      hour <- TimeGens.genHour.log("hour")
    } yield {
      val input = hour

      val expected = hour.value
      val actual   = input.into[Int].transform

      actual ==== expected
    }

  def testToIntToHour: Property =
    for {
      hour <- TimeGens.genHour.log("hour")
    } yield {
      val input = hour.value

      val expected = chimney.partial.Result.fromValue(hour)

      val actual = input.intoPartial[Hour].transform

      actual ==== expected
    }

  def testFromMinuteToInt: Property =
    for {
      minute <- TimeGens.genMinute.log("minute")
    } yield {
      val input = minute

      val expected = minute.value
      val actual   = input.into[Int].transform

      actual ==== expected
    }

  def testToIntToMinute: Property =
    for {
      minute <- TimeGens.genMinute.log("minute")
    } yield {
      val input = minute.value

      val expected = chimney.partial.Result.fromValue(minute)

      val actual = input.intoPartial[Minute].transform

      actual ==== expected
    }

  def testFromSecondToInt: Property =
    for {
      second <- TimeGens.genSecond.log("second")
    } yield {
      val input = second

      val expected = second.value
      val actual   = input.into[Int].transform

      actual ==== expected
    }

  def testToIntToSecond: Property =
    for {
      second <- TimeGens.genSecond.log("second")
    } yield {
      val input = second.value

      val expected = chimney.partial.Result.fromValue(second)

      val actual = input.intoPartial[Second].transform

      actual ==== expected
    }

  def testFromMillisToInt: Property =
    for {
      millis <- TimeGens.genMillis.log("millis")
    } yield {
      val input = millis

      val expected = millis.value
      val actual   = input.into[Int].transform

      actual ==== expected
    }

  def testToIntToMillis: Property =
    for {
      millis <- TimeGens.genMillis.log("millis")
    } yield {
      val input = millis.value

      val expected = chimney.partial.Result.fromValue(millis)

      val actual = input.intoPartial[Millis].transform

      actual ==== expected
    }

}
object timeSpec extends Properties, timeSpec {

  override protected object timeTypeClasses extends refined4s.modules.chimney.derivation.types.time

  override def tests: List[Test] = allTests

}
