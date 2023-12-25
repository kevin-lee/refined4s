package refined4s.modules.circe.derivation

import cats.*
import extras.hedgehog.circe.RoundTripTester
import hedgehog.*
import hedgehog.runner.*
import io.circe.{Codec, Decoder, Encoder}
import refined4s.*
import refined4s.modules.cats.derivation.*
import refined4s.modules.cats.derivation.instances.given
import refined4s.modules.circe.derivation.types.all.given
import refined4s.types.all.*

import java.time.Instant

/** @author Kevin Lee
  * @since 2023-12-25
  */
object CirceCodecWithInstancesSpec extends Properties {

  override def tests: List[Test] = List(
    property("round-trip test Newtype and Refined with derived and custom Codec", roundTripTest),
    property("test Encoder with Newtype and Refined with derived and custom Codec", testEncoder),
  )

  def roundTripTest: Property =
    for {
      name     <- Gen
                    .string(Gen.alpha, Range.linear(1, 10))
                    .list(Range.singleton(2))
                    .map(_.mkString(" "))
                    .map(NonEmptyString.unsafeFrom)
                    .map(Name(_))
                    .log("name")
      created  <- Gen
                    .long(Range.linear(0, Long.MaxValue))
                    .map(Instant.ofEpochMilli)
                    .map(Created(_))
                    .log("created")
      expected <- Gen.constant(TestData(name, created)).log("expected")
    } yield {
      RoundTripTester(expected).test()
    }

  def testEncoder: Property =
    for {
      name     <- Gen
                    .string(Gen.alpha, Range.linear(1, 10))
                    .list(Range.singleton(2))
                    .map(_.mkString(" "))
                    .map(NonEmptyString.unsafeFrom)
                    .map(Name(_))
                    .log("name")
      created  <- Gen
                    .long(Range.linear(0, Long.MaxValue))
                    .map(Instant.ofEpochMilli)
                    .map(Created(_))
                    .log("created")
      testData <- Gen.constant(TestData(name, created)).log("expected")
    } yield {
      import io.circe.literal.*
      val expected =
        json"""{
          "name": ${testData.name},
          "created": ${testData.created.value.toEpochMilli}
        }"""
      import io.circe.syntax.*
      val actual   = testData.asJson
      (actual.noSpaces ==== expected.noSpaces).log(
        s"""
           |> Actual:
           |${actual.spaces2}
           |> ---
           |> Expected:
           |${expected.spaces2}
           |""".stripMargin
      )
    }

  final case class TestData(name: Name, created: Created) derives Codec.AsObject
  object TestData {
    given testDataEq: Eq[TestData]     = Eq.fromUniversalEquals
    given testDataShow: Show[TestData] = Show.fromToString
  }

  type Name = Name.Type
  object Name extends Newtype[NonEmptyString], CatsEqShow[NonEmptyString], CirceNewtypeCodec[NonEmptyString]

  type Created = Created.Type
  object Created extends Newtype[Instant] {

    given createdEq: Eq[Created]     = Eq.fromUniversalEquals
    given createdShow: Show[Created] = Show.fromToString

    given encoder: Encoder[Created] = Encoder[Long].contramap(_.value.toEpochMilli)
    given decoder: Decoder[Created] = Decoder[Long].map(apply.compose(Instant.ofEpochMilli))

  }
}
