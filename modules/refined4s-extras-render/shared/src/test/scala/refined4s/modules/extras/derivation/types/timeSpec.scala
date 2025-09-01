package refined4s.modules.extras.derivation.types

import extras.render.syntax.*
import hedgehog.*
import hedgehog.runner.*
import refined4s.modules.extras.derivation.types.all.given
import refined4s.types.time.*

object timeSpec extends Properties {
  def tests: List[Test] = List(
    property("test Render[Month]", testRenderMonth),
    property("test Render[Day]", testRenderDay),
    property("test Render[Hour]", testRenderHour),
    property("test Render[Minute]", testRenderMinute),
    property("test Render[Second]", testRenderSecond),
    property("test Render[Millis]", testRenderMillis),
  )

  def testRenderMonth: Property =
    for {
      monthInt <- Gen.int(Range.linear(1, 12)).log("monthInt")
    } yield {
      val input = Month.unsafeFrom(monthInt)

      val expected = monthInt.render
      val actual   = input.render

      actual ==== expected
    }

  def testRenderDay: Property =
    for {
      dayInt <- Gen.int(Range.linear(1, 31)).log("dayInt")
    } yield {
      val input = Day.unsafeFrom(dayInt)

      val expected = dayInt.render
      val actual   = input.render

      actual ==== expected
    }

  def testRenderHour: Property =
    for {
      hourInt <- Gen.int(Range.linear(0, 23)).log("hourInt")
    } yield {
      val input = Hour.unsafeFrom(hourInt)

      val expected = hourInt.render
      val actual   = input.render

      actual ==== expected
    }

  def testRenderMinute: Property =
    for {
      minuteInt <- Gen.int(Range.linear(0, 59)).log("minuteInt")
    } yield {
      val input = Minute.unsafeFrom(minuteInt)

      val expected = minuteInt.render
      val actual   = input.render

      actual ==== expected
    }

  def testRenderSecond: Property =
    for {
      secondInt <- Gen.int(Range.linear(0, 59)).log("secondInt")
    } yield {
      val input = Second.unsafeFrom(secondInt)

      val expected = secondInt.render
      val actual   = input.render

      actual ==== expected
    }

  def testRenderMillis: Property =
    for {
      millisInt <- Gen.int(Range.linear(0, 999)).log("millisInt")
    } yield {
      val input = Millis.unsafeFrom(millisInt)

      val expected = millisInt.render
      val actual   = input.render

      actual ==== expected
    }
}
