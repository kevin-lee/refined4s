package refined4s.modules.pureconfig.derivation.types

import cats.syntax.all.*
import hedgehog.*
import hedgehog.runner.*
import pureconfig.error.{ConfigReaderFailures, ConvertFailure, UserValidationFailed}
import pureconfig.generic.derivation.default.*
import pureconfig.{ConfigReader, ConfigSource, ConfigWriter}
import refined4s.internal.typeTools
import refined4s.types.time
import refined4s.types.time.*
import refined4s.types.TimeGens

/** @author Kevin Lee
  * @since 2025-09-02
  */
trait timeSpec {

  protected val timeTypeClasses: refined4s.modules.pureconfig.derivation.types.time
  import timeTypeClasses.given

  def allTests: List[Test] = List(
    //
    property("test ConfigReader[Month]", testConfigReaderMonth),
    property("test ConfigReader[Month] with invalid value", testConfigReaderMonthInvalid),
    property("test ConfigWriter[Month]", testConfigWriterMonth),
    //
    property("test ConfigReader[Day]", testConfigReaderDay),
    property("test ConfigReader[Day] with invalid value", testConfigReaderDayInvalid),
    property("test ConfigWriter[Day]", testConfigWriterDay),
    //
    property("test ConfigReader[Hour]", testConfigReaderHour),
    property("test ConfigReader[Hour] with invalid value", testConfigReaderHourInvalid),
    property("test ConfigWriter[Hour]", testConfigWriterHour),
    //
    property("test ConfigReader[Minute]", testConfigReaderMinute),
    property("test ConfigReader[Minute] with invalid value", testConfigReaderMinuteInvalid),
    property("test ConfigWriter[Minute]", testConfigWriterMinute),
    //
    property("test ConfigReader[Second]", testConfigReaderSecond),
    property("test ConfigReader[Second] with invalid value", testConfigReaderSecondInvalid),
    property("test ConfigWriter[Second]", testConfigWriterSecond),
    //
    property("test ConfigReader[Millis]", testConfigReaderMillis),
    property("test ConfigReader[Millis] with invalid value", testConfigReaderMillisInvalid),
    property("test ConfigWriter[Millis]", testConfigWriterMillis),
  )

  def testConfigReaderMonth: Property =
    for {
      monthInt <- TimeGens.genMonthInt.log("monthInt")
    } yield {

      val confString =
        raw"""
             |month = $monthInt
             |""".stripMargin

      val expected = Month.unsafeFrom(monthInt)

      ConfigSource
        .string(confString)
        .load[MonthConfig] match {
        case Right(MonthConfig(actual)) =>
          actual ==== expected

        case Left(err) =>
          Result.failure.log(s"parse config failed with error: ${err.toString}")
      }

    }

  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  def testConfigReaderMonthInvalid: Property =
    for {
      monthInt <- TimeGens.genInvalidMonthInt.log("monthInt")
    } yield {

      val confString = raw"""month = $monthInt"""

      val expected = Month.from(monthInt).leftMap { err =>
        s"The value $monthInt cannot be created as the expected type, ${typeTools.getTypeName[Month]}, due to the following error: $err"
      }

      ConfigSource
        .string(confString)
        .load[MonthConfig] match {
        case Right(MonthConfig(actual)) =>
          Result.failure.log(s"It should have failed to parse the config but got $actual")

        case Left(ConfigReaderFailures(ConvertFailure(UserValidationFailed(err), _, _))) =>
          err.asLeft ==== expected

        case unexpected =>
          Result.failure.log(s"Unexpected result: ${unexpected.toString}")
      }

    }

  def testConfigWriterMonth: Property =
    for {
      monthInt <- TimeGens.genMonthInt.log("monthInt")
    } yield {

      val input = Month.unsafeFrom(monthInt)

      val expected = ConfigWriter[Int].to(monthInt)
      val actual   = ConfigWriter[Month].to(input)

      actual ==== expected

    }
  final case class MonthConfig(month: Month) derives ConfigReader

  def testConfigReaderDay: Property =
    for {
      dayInt <- TimeGens.genDayInt.log("dayInt")
    } yield {

      val confString =
        raw"""
             |day = $dayInt
             |""".stripMargin

      val expected = Day.unsafeFrom(dayInt)

      ConfigSource
        .string(confString)
        .load[DayConfig] match {
        case Right(DayConfig(actual)) =>
          actual ==== expected

        case Left(err) =>
          Result.failure.log(s"parse config failed with error: ${err.toString}")
      }

    }

  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  def testConfigReaderDayInvalid: Property =
    for {
      dayInt <- TimeGens.genInvalidDayInt.log("dayInt")
    } yield {

      val confString = raw"""day = $dayInt"""

      val expected = Day.from(dayInt).leftMap { err =>
        s"The value $dayInt cannot be created as the expected type, ${typeTools.getTypeName[Day]}, due to the following error: $err"
      }

      ConfigSource
        .string(confString)
        .load[DayConfig] match {
        case Right(DayConfig(actual)) =>
          Result.failure.log(s"It should have failed to parse the config but got $actual")

        case Left(ConfigReaderFailures(ConvertFailure(UserValidationFailed(err), _, _))) =>
          err.asLeft ==== expected

        case unexpected =>
          Result.failure.log(s"Unexpected result: ${unexpected.toString}")
      }

    }

  def testConfigWriterDay: Property =
    for {
      dayInt <- TimeGens.genDayInt.log("dayInt")
    } yield {

      val input = Day.unsafeFrom(dayInt)

      val expected = ConfigWriter[Int].to(dayInt)
      val actual   = ConfigWriter[Day].to(input)

      actual ==== expected

    }
  final case class DayConfig(day: Day) derives ConfigReader

  def testConfigReaderHour: Property =
    for {
      hourInt <- TimeGens.genHourInt.log("hourInt")
    } yield {

      val confString =
        raw"""
             |hour = $hourInt
             |""".stripMargin

      val expected = Hour.unsafeFrom(hourInt)

      ConfigSource
        .string(confString)
        .load[HourConfig] match {
        case Right(HourConfig(actual)) =>
          actual ==== expected

        case Left(err) =>
          Result.failure.log(s"parse config failed with error: ${err.toString}")
      }

    }

  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  def testConfigReaderHourInvalid: Property =
    for {
      hourInt <- TimeGens.genInvalidHourInt.log("hourInt")
    } yield {

      val confString = raw"""hour = $hourInt"""

      val expected = Hour.from(hourInt).leftMap { err =>
        s"The value $hourInt cannot be created as the expected type, ${typeTools.getTypeName[Hour]}, due to the following error: $err"
      }

      ConfigSource
        .string(confString)
        .load[HourConfig] match {
        case Right(HourConfig(actual)) =>
          Result.failure.log(s"It should have failed to parse the config but got $actual")

        case Left(ConfigReaderFailures(ConvertFailure(UserValidationFailed(err), _, _))) =>
          err.asLeft ==== expected

        case unexpected =>
          Result.failure.log(s"Unexpected result: ${unexpected.toString}")
      }

    }

  def testConfigWriterHour: Property =
    for {
      hourInt <- TimeGens.genHourInt.log("hourInt")
    } yield {

      val input = Hour.unsafeFrom(hourInt)

      val expected = ConfigWriter[Int].to(hourInt)
      val actual   = ConfigWriter[Hour].to(input)

      actual ==== expected

    }
  final case class HourConfig(hour: Hour) derives ConfigReader

  def testConfigReaderMinute: Property =
    for {
      minuteInt <- TimeGens.genMinuteInt.log("minuteInt")
    } yield {

      val confString =
        raw"""
             |minute = $minuteInt
             |""".stripMargin

      val expected = Minute.unsafeFrom(minuteInt)

      ConfigSource
        .string(confString)
        .load[MinuteConfig] match {
        case Right(MinuteConfig(actual)) =>
          actual ==== expected

        case Left(err) =>
          Result.failure.log(s"parse config failed with error: ${err.toString}")
      }

    }

  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  def testConfigReaderMinuteInvalid: Property =
    for {
      minuteInt <- TimeGens.genInvalidMinuteInt.log("minuteInt")
    } yield {

      val confString = raw"""minute = $minuteInt"""

      val expected = Minute.from(minuteInt).leftMap { err =>
        s"The value $minuteInt cannot be created as the expected type, ${typeTools.getTypeName[Minute]}, due to the following error: $err"
      }

      ConfigSource
        .string(confString)
        .load[MinuteConfig] match {
        case Right(MinuteConfig(actual)) =>
          Result.failure.log(s"It should have failed to parse the config but got $actual")

        case Left(ConfigReaderFailures(ConvertFailure(UserValidationFailed(err), _, _))) =>
          err.asLeft ==== expected

        case unexpected =>
          Result.failure.log(s"Unexpected result: ${unexpected.toString}")
      }

    }

  def testConfigWriterMinute: Property =
    for {
      minuteInt <- TimeGens.genMinuteInt.log("minuteInt")
    } yield {

      val input = Minute.unsafeFrom(minuteInt)

      val expected = ConfigWriter[Int].to(minuteInt)
      val actual   = ConfigWriter[Minute].to(input)

      actual ==== expected

    }
  final case class MinuteConfig(minute: Minute) derives ConfigReader

  def testConfigReaderSecond: Property =
    for {
      secondInt <- TimeGens.genSecondInt.log("secondInt")
    } yield {

      val confString =
        raw"""
             |second = $secondInt
             |""".stripMargin

      val expected = Second.unsafeFrom(secondInt)

      ConfigSource
        .string(confString)
        .load[SecondConfig] match {
        case Right(SecondConfig(actual)) =>
          actual ==== expected

        case Left(err) =>
          Result.failure.log(s"parse config failed with error: ${err.toString}")
      }

    }

  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  def testConfigReaderSecondInvalid: Property =
    for {
      secondInt <- TimeGens.genInvalidSecondInt.log("secondInt")
    } yield {

      val confString = raw"""second = $secondInt"""

      val expected = Second.from(secondInt).leftMap { err =>
        s"The value $secondInt cannot be created as the expected type, ${typeTools.getTypeName[Second]}, due to the following error: $err"
      }

      ConfigSource
        .string(confString)
        .load[SecondConfig] match {
        case Right(SecondConfig(actual)) =>
          Result.failure.log(s"It should have failed to parse the config but got $actual")

        case Left(ConfigReaderFailures(ConvertFailure(UserValidationFailed(err), _, _))) =>
          err.asLeft ==== expected

        case unexpected =>
          Result.failure.log(s"Unexpected result: ${unexpected.toString}")
      }

    }

  def testConfigWriterSecond: Property =
    for {
      secondInt <- TimeGens.genSecondInt.log("secondInt")
    } yield {

      val input = Second.unsafeFrom(secondInt)

      val expected = ConfigWriter[Int].to(secondInt)
      val actual   = ConfigWriter[Second].to(input)

      actual ==== expected

    }
  final case class SecondConfig(second: Second) derives ConfigReader

  def testConfigReaderMillis: Property =
    for {
      millisInt <- TimeGens.genMillisInt.log("millisInt")
    } yield {

      val confString =
        raw"""
             |millis = $millisInt
             |""".stripMargin

      val expected = Millis.unsafeFrom(millisInt)

      ConfigSource
        .string(confString)
        .load[MillisConfig] match {
        case Right(MillisConfig(actual)) =>
          actual ==== expected

        case Left(err) =>
          Result.failure.log(s"parse config failed with error: ${err.toString}")
      }

    }

  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  def testConfigReaderMillisInvalid: Property =
    for {
      millisInt <- TimeGens.genInvalidMillisInt.log("millisInt")
    } yield {

      val confString = raw"""millis = $millisInt"""

      val expected = Millis.from(millisInt).leftMap { err =>
        s"The value $millisInt cannot be created as the expected type, ${typeTools.getTypeName[Millis]}, due to the following error: $err"
      }

      ConfigSource
        .string(confString)
        .load[MillisConfig] match {
        case Right(MillisConfig(actual)) =>
          Result.failure.log(s"It should have failed to parse the config but got $actual")

        case Left(ConfigReaderFailures(ConvertFailure(UserValidationFailed(err), _, _))) =>
          err.asLeft ==== expected

        case unexpected =>
          Result.failure.log(s"Unexpected result: ${unexpected.toString}")
      }

    }

  def testConfigWriterMillis: Property =
    for {
      millisInt <- TimeGens.genMillisInt.log("millisInt")
    } yield {

      val input = Millis.unsafeFrom(millisInt)

      val expected = ConfigWriter[Int].to(millisInt)
      val actual   = ConfigWriter[Millis].to(input)

      actual ==== expected

    }
  final case class MillisConfig(millis: Millis) derives ConfigReader

  ///
}
object timeSpec extends Properties, timeSpec {

  override protected object timeTypeClasses extends refined4s.modules.pureconfig.derivation.types.time

  override def tests: List[Test] = allTests

}
