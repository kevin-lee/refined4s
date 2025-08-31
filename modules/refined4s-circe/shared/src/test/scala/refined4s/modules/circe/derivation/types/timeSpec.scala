package refined4s.modules.circe.derivation.types

import cats.syntax.all.*
import hedgehog.*
import hedgehog.runner.*
import io.circe.*
import io.circe.parser.*
import io.circe.syntax.*
import refined4s.types.TimeGens
import refined4s.types.all.*

/** @author Kevin Lee
  * @since 2025-08-30
  */
trait timeSpec {
  protected val timeTypeClasses: refined4s.modules.circe.derivation.types.time

  import timeTypeClasses.given

  def allTests: List[Test] = monthSpec.tests ++ daySpec.tests ++ hourSpec.tests ++ minuteSpec.tests ++ secondSpec.tests ++ millisSpec.tests

  object monthSpec {

    def tests: List[Test] = List(
      property("test    Encoder[Month]", testEncoder),
      property("test    Decoder[Month]", testDecoder),
      property("test KeyEncoder[Month]", testKeyEncoder),
      property("test KeyDecoder[Month]", testKeyDecoder),
    )

    def testEncoder: Property =
      for {
        monthIntAndMonth <- TimeGens.genMonth.map(month => month.value -> month).log("(monthInt, month)")
        (monthInt, month) = monthIntAndMonth
      } yield {
        val expected = monthInt.asJson
        val actual   = month.asJson

        Result.all(
          List(
            actual ==== expected,
            actual.noSpaces ==== expected.noSpaces,
          )
        )
      }

    def testDecoder: Property =
      for {
        monthIntAndMonth <- TimeGens.genMonth.map(month => month.value -> month).log("(monthInt, month)")
        (monthInt, month) = monthIntAndMonth
      } yield {
        val input = month.asJson

        val expected = month.asRight[io.circe.Error]
        val actual   = decode[Month](input.noSpaces)

        actual ==== expected
      }

    def testKeyEncoder: Property =
      for {
        keys   <- TimeGens.genMonth.list(Range.linear(1, 20)).log("keys")
        values <- Gen.string(Gen.alpha, Range.linear(1, 10)).list(Range.singleton(keys.length)).log("values")
        map    <- Gen.constant(keys.zip(values).toMap).log("map")
      } yield {
        val expected = Json.fromFields(map.map { case (key, value) => key.value.toString -> value.asJson })

        val actual = map.asJson
        Result.all(
          List(
            actual ==== expected,
            actual.noSpacesSortKeys ==== expected.noSpacesSortKeys,
          )
        )
      }

    def testKeyDecoder: Property =
      for {
        keys     <- TimeGens.genMonth.list(Range.linear(1, 20)).log("keys")
        values   <- Gen.string(Gen.alpha, Range.linear(1, 10)).list(Range.singleton(keys.length)).log("values")
        expected <- Gen.constant(keys.zip(values).toMap).log("expected")
      } yield {
        val input = Json.fromFields(expected.map { case (key, value) => key.value.toString -> value.asJson })

        val actual = decode[Map[Month, String]](input.noSpaces)

        actual ==== expected.asRight
      }

  }

  object daySpec {
    def tests: List[Test] = List(
      property("test    Encoder[Day]", testEncoder),
      property("test    Decoder[Day]", testDecoder),
      property("test KeyEncoder[Day]", testKeyEncoder),
      property("test KeyDecoder[Day]", testKeyDecoder),
    )

    def testEncoder: Property =
      for {
        dayIntAndDay <- TimeGens.genDay.map(day => day.value -> day).log("(dayInt, day)")
        (dayInt, day) = dayIntAndDay
      } yield {
        val expected = dayInt.asJson
        val actual   = day.asJson

        Result.all(
          List(
            actual ==== expected,
            actual.noSpaces ==== expected.noSpaces,
          )
        )
      }

    def testDecoder: Property =
      for {
        dayIntAndDay <- TimeGens.genDay.map(day => day.value -> day).log("(dayInt, day)")
        (dayInt, day) = dayIntAndDay
      } yield {
        val input = day.asJson

        val expected = day.asRight[io.circe.Error]
        val actual   = decode[Day](input.noSpaces)

        actual ==== expected
      }

    def testKeyEncoder: Property =
      for {
        keys   <- TimeGens.genDay.list(Range.linear(1, 20)).log("keys")
        values <- Gen.string(Gen.alpha, Range.linear(1, 10)).list(Range.singleton(keys.length)).log("values")
        map    <- Gen.constant(keys.zip(values).toMap).log("map")
      } yield {
        val expected = Json.fromFields(map.map { case (key, value) => key.value.toString -> value.asJson })

        val actual = map.asJson
        Result.all(
          List(
            actual ==== expected,
            actual.noSpacesSortKeys ==== expected.noSpacesSortKeys,
          )
        )
      }

    def testKeyDecoder: Property =
      for {
        keys     <- TimeGens.genDay.list(Range.linear(1, 20)).log("keys")
        values   <- Gen.string(Gen.alpha, Range.linear(1, 10)).list(Range.singleton(keys.length)).log("values")
        expected <- Gen.constant(keys.zip(values).toMap).log("expected")
      } yield {
        val input = Json.fromFields(expected.map { case (key, value) => key.value.toString -> value.asJson })

        val actual = decode[Map[Day, String]](input.noSpaces)

        actual ==== expected.asRight
      }

  }

  object hourSpec {
    def tests: List[Test] = List(
      property("test    Encoder[Hour]", testEncoder),
      property("test    Decoder[Hour]", testDecoder),
      property("test KeyEncoder[Hour]", testKeyEncoder),
      property("test KeyDecoder[Hour]", testKeyDecoder),
    )

    def testEncoder: Property =
      for {
        hourIntAndHour <- TimeGens.genHour.map(hour => hour.value -> hour).log("(hourInt, hour)")
        (hourInt, hour) = hourIntAndHour
      } yield {
        val expected = hourInt.asJson
        val actual   = hour.asJson

        Result.all(
          List(
            actual ==== expected,
            actual.noSpaces ==== expected.noSpaces,
          )
        )
      }

    def testDecoder: Property =
      for {
        hourIntAndHour <- TimeGens.genHour.map(hour => hour.value -> hour).log("(hourInt, hour)")
        (hourInt, hour) = hourIntAndHour
      } yield {
        val input = hour.asJson

        val expected = hour.asRight[io.circe.Error]
        val actual   = decode[Hour](input.noSpaces)

        actual ==== expected
      }

    def testKeyEncoder: Property =
      for {
        keys   <- TimeGens.genHour.list(Range.linear(1, 20)).log("keys")
        values <- Gen.string(Gen.alpha, Range.linear(1, 10)).list(Range.singleton(keys.length)).log("values")
        map    <- Gen.constant(keys.zip(values).toMap).log("map")
      } yield {
        val expected = Json.fromFields(map.map { case (key, value) => key.value.toString -> value.asJson })

        val actual = map.asJson
        Result.all(
          List(
            actual ==== expected,
            actual.noSpacesSortKeys ==== expected.noSpacesSortKeys,
          )
        )
      }

    def testKeyDecoder: Property =
      for {
        keys     <- TimeGens.genHour.list(Range.linear(1, 20)).log("keys")
        values   <- Gen.string(Gen.alpha, Range.linear(1, 10)).list(Range.singleton(keys.length)).log("values")
        expected <- Gen.constant(keys.zip(values).toMap).log("expected")
      } yield {
        val input = Json.fromFields(expected.map { case (key, value) => key.value.toString -> value.asJson })

        val actual = decode[Map[Hour, String]](input.noSpaces)

        actual ==== expected.asRight
      }

  }

  object minuteSpec {
    def tests: List[Test] = List(
      property("test    Encoder[Minute]", testEncoder),
      property("test    Decoder[Minute]", testDecoder),
      property("test KeyEncoder[Minute]", testKeyEncoder),
      property("test KeyDecoder[Minute]", testKeyDecoder),
    )

    def testEncoder: Property =
      for {
        minuteIntAndMinute <- TimeGens.genMinute.map(minute => minute.value -> minute).log("(minuteInt, minute)")
        (minuteInt, minute) = minuteIntAndMinute
      } yield {
        val expected = minuteInt.asJson
        val actual   = minute.asJson

        Result.all(
          List(
            actual ==== expected,
            actual.noSpaces ==== expected.noSpaces,
          )
        )
      }

    def testDecoder: Property =
      for {
        minuteIntAndMinute <- TimeGens.genMinute.map(minute => minute.value -> minute).log("(minuteInt, minute)")
        (minuteInt, minute) = minuteIntAndMinute
      } yield {
        val input = minute.asJson

        val expected = minute.asRight[io.circe.Error]
        val actual   = decode[Minute](input.noSpaces)

        actual ==== expected
      }

    def testKeyEncoder: Property =
      for {
        keys   <- TimeGens.genMinute.list(Range.linear(1, 20)).log("keys")
        values <- Gen.string(Gen.alpha, Range.linear(1, 10)).list(Range.singleton(keys.length)).log("values")
        map    <- Gen.constant(keys.zip(values).toMap).log("map")
      } yield {
        val expected = Json.fromFields(map.map { case (key, value) => key.value.toString -> value.asJson })

        val actual = map.asJson
        Result.all(
          List(
            actual ==== expected,
            actual.noSpacesSortKeys ==== expected.noSpacesSortKeys,
          )
        )
      }

    def testKeyDecoder: Property =
      for {
        keys     <- TimeGens.genMinute.list(Range.linear(1, 20)).log("keys")
        values   <- Gen.string(Gen.alpha, Range.linear(1, 10)).list(Range.singleton(keys.length)).log("values")
        expected <- Gen.constant(keys.zip(values).toMap).log("expected")
      } yield {
        val input = Json.fromFields(expected.map { case (key, value) => key.value.toString -> value.asJson })

        val actual = decode[Map[Minute, String]](input.noSpaces)

        actual ==== expected.asRight
      }

  }

  object secondSpec {
    def tests: List[Test] = List(
      property("test    Encoder[Second]", testEncoder),
      property("test    Decoder[Second]", testDecoder),
      property("test KeyEncoder[Second]", testKeyEncoder),
      property("test KeyDecoder[Second]", testKeyDecoder),
    )

    def testEncoder: Property =
      for {
        secondIntAndSecond <- TimeGens.genSecond.map(second => second.value -> second).log("(secondInt, second)")
        (secondInt, second) = secondIntAndSecond
      } yield {
        val expected = secondInt.asJson
        val actual   = second.asJson

        Result.all(
          List(
            actual ==== expected,
            actual.noSpaces ==== expected.noSpaces,
          )
        )
      }

    def testDecoder: Property =
      for {
        secondIntAndSecond <- TimeGens.genSecond.map(second => second.value -> second).log("(secondInt, second)")
        (secondInt, second) = secondIntAndSecond
      } yield {
        val input = second.asJson

        val expected = second.asRight[io.circe.Error]
        val actual   = decode[Second](input.noSpaces)

        actual ==== expected
      }

    def testKeyEncoder: Property =
      for {
        keys   <- TimeGens.genSecond.list(Range.linear(1, 20)).log("keys")
        values <- Gen.string(Gen.alpha, Range.linear(1, 10)).list(Range.singleton(keys.length)).log("values")
        map    <- Gen.constant(keys.zip(values).toMap).log("map")
      } yield {
        val expected = Json.fromFields(map.map { case (key, value) => key.value.toString -> value.asJson })

        val actual = map.asJson
        Result.all(
          List(
            actual ==== expected,
            actual.noSpacesSortKeys ==== expected.noSpacesSortKeys,
          )
        )
      }

    def testKeyDecoder: Property =
      for {
        keys     <- TimeGens.genSecond.list(Range.linear(1, 20)).log("keys")
        values   <- Gen.string(Gen.alpha, Range.linear(1, 10)).list(Range.singleton(keys.length)).log("values")
        expected <- Gen.constant(keys.zip(values).toMap).log("expected")
      } yield {
        val input = Json.fromFields(expected.map { case (key, value) => key.value.toString -> value.asJson })

        val actual = decode[Map[Second, String]](input.noSpaces)

        actual ==== expected.asRight
      }

  }

  object millisSpec {
    def tests: List[Test] = List(
      property("test    Encoder[Millis]", testEncoder),
      property("test    Decoder[Millis]", testDecoder),
      property("test KeyEncoder[Millis]", testKeyEncoder),
      property("test KeyDecoder[Millis]", testKeyDecoder),
    )

    def testEncoder: Property =
      for {
        millisIntAndMillis <- TimeGens.genMillis.map(millis => millis.value -> millis).log("(millisInt, millis)")
        (millisInt, millis) = millisIntAndMillis
      } yield {
        val expected = millisInt.asJson
        val actual   = millis.asJson

        Result.all(
          List(
            actual ==== expected,
            actual.noSpaces ==== expected.noSpaces,
          )
        )
      }

    def testDecoder: Property =
      for {
        millisIntAndMillis <- TimeGens.genMillis.map(millis => millis.value -> millis).log("(millisInt, millis)")
        (millisInt, millis) = millisIntAndMillis
      } yield {
        val input = millis.asJson

        val expected = millis.asRight[io.circe.Error]
        val actual   = decode[Millis](input.noSpaces)

        actual ==== expected
      }

    def testKeyEncoder: Property =
      for {
        keys   <- TimeGens.genMillis.list(Range.linear(1, 20)).log("keys")
        values <- Gen.string(Gen.alpha, Range.linear(1, 10)).list(Range.singleton(keys.length)).log("values")
        map    <- Gen.constant(keys.zip(values).toMap).log("map")
      } yield {
        val expected = Json.fromFields(map.map { case (key, value) => key.value.toString -> value.asJson })

        val actual = map.asJson
        Result.all(
          List(
            actual ==== expected,
            actual.noSpacesSortKeys ==== expected.noSpacesSortKeys,
          )
        )
      }

    def testKeyDecoder: Property =
      for {
        keys     <- TimeGens.genMillis.list(Range.linear(1, 20)).log("keys")
        values   <- Gen.string(Gen.alpha, Range.linear(1, 10)).list(Range.singleton(keys.length)).log("values")
        expected <- Gen.constant(keys.zip(values).toMap).log("expected")
      } yield {
        val input = Json.fromFields(expected.map { case (key, value) => key.value.toString -> value.asJson })

        val actual = decode[Map[Millis, String]](input.noSpaces)

        actual ==== expected.asRight
      }

  }

}
object timeSpec extends Properties, timeSpec {
  override protected val timeTypeClasses: refined4s.modules.circe.derivation.types.time =
    refined4s.modules.circe.derivation.types.time

  override def tests: List[Test] = allTests
}
