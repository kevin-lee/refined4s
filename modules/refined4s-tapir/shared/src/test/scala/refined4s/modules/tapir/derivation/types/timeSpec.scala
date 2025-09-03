package refined4s.modules.tapir.derivation.types

import hedgehog.*
import hedgehog.runner.*
import refined4s.types.TimeGens
import refined4s.types.time.*
import sttp.tapir.{Schema, ValidationError}

/** @author Kevin Lee
  * @since 2025-09-03
  */
trait timeSpec {

  protected val timeTypeClasses: refined4s.modules.tapir.derivation.types.time
  import timeTypeClasses.given

  def allTests: List[Test] = List(
    property("test Schema[Month]", testSchemaMonth),
    property("test Schema[Day]", testSchemaDay),
    property("test Schema[Hour]", testSchemaHour),
    property("test Schema[Minute]", testSchemaMinute),
    property("test Schema[Second]", testSchemaSecond),
    property("test Schema[Millis]", testSchemaMillis),
  )

  def testSchemaMonth: Property =
    for {
      month <- TimeGens.genMonthInt.log("month")
    } yield {

      val input = Month.unsafeFrom(month)

      val expected = List.empty[ValidationError[?]]
      val actual   = summon[Schema[Month]].applyValidation(input)

      actual ==== expected
    }

  def testSchemaDay: Property =
    for {
      day <- TimeGens.genDayInt.log("day")
    } yield {

      val input = Day.unsafeFrom(day)

      val expected = List.empty[ValidationError[?]]
      val actual   = summon[Schema[Day]].applyValidation(input)

      actual ==== expected
    }

  def testSchemaHour: Property =
    for {
      hour <- TimeGens.genHourInt.log("hour")
    } yield {

      val input = Hour.unsafeFrom(hour)

      val expected = List.empty[ValidationError[?]]
      val actual   = summon[Schema[Hour]].applyValidation(input)

      actual ==== expected
    }

  def testSchemaMinute: Property =
    for {
      minute <- TimeGens.genMinuteInt.log("minute")
    } yield {

      val input = Minute.unsafeFrom(minute)

      val expected = List.empty[ValidationError[?]]
      val actual   = summon[Schema[Minute]].applyValidation(input)

      actual ==== expected
    }

  def testSchemaSecond: Property =
    for {
      second <- TimeGens.genSecondInt.log("second")
    } yield {

      val input = Second.unsafeFrom(second)

      val expected = List.empty[ValidationError[?]]
      val actual   = summon[Schema[Second]].applyValidation(input)

      actual ==== expected
    }

  def testSchemaMillis: Property =
    for {
      millis <- TimeGens.genMillisInt.log("millis")
    } yield {

      val input = Millis.unsafeFrom(millis)

      val expected = List.empty[ValidationError[?]]
      val actual   = summon[Schema[Millis]].applyValidation(input)

      actual ==== expected
    }

}
object timeSpec extends Properties, timeSpec {

  override protected object timeTypeClasses extends refined4s.modules.tapir.derivation.types.time

  override def tests: List[Test] = allTests

}
