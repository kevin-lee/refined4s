package refined4s.modules.circe.derivation.types

import cats.syntax.all.*
import hedgehog.*
import hedgehog.runner.*
import io.circe.*
import io.circe.parser.*
import io.circe.syntax.*
import refined4s.types.all.*
import refined4s.types.networkGens

/** @author Kevin Lee
  * @since 2024-08-23
  */
trait networkSpec {

  protected val networkTypeClasses: refined4s.modules.circe.derivation.types.network

  import networkTypeClasses.given

  def allTests: List[Test] = List(
    property("test Encoder[Uri]", testEncoderUri),
    property("test Decoder[Uri]", testDecoderUri),
    property("test KeyEncoder[Uri]", testKeyEncoderUri),
    property("test KeyDecoder[Uri]", testKeyDecoderUri),
    //
    property("test Encoder[Url]", testEncoderUrl),
    property("test Decoder[Url]", testDecoderUrl),
    property("test KeyEncoder[Url]", testKeyEncoderUrl),
    property("test KeyDecoder[Url]", testKeyDecoderUrl),
    //
    property("test Encoder[PortNumber]", testEncoderPortNumber),
    property("test Decoder[PortNumber]", testDecoderPortNumber),
    property("test KeyEncoder[PortNumber]", testKeyEncoderPortNumber),
    property("test KeyDecoder[PortNumber]", testKeyDecoderPortNumber),
    //
    property("test Encoder[SystemPortNumber]", testEncoderSystemPortNumber),
    property("test Decoder[SystemPortNumber]", testDecoderSystemPortNumber),
    property("test KeyEncoder[SystemPortNumber]", testKeyEncoderSystemPortNumber),
    property("test KeyDecoder[SystemPortNumber]", testKeyDecoderSystemPortNumber),
    //
    property("test Encoder[NonSystemPortNumber]", testEncoderNonSystemPortNumber),
    property("test Decoder[NonSystemPortNumber]", testDecoderNonSystemPortNumber),
    property("test KeyEncoder[NonSystemPortNumber]", testKeyEncoderNonSystemPortNumber),
    property("test KeyDecoder[NonSystemPortNumber]", testKeyDecoderNonSystemPortNumber),
    //
    property("test Encoder[UserPortNumber]", testEncoderUserPortNumber),
    property("test Decoder[UserPortNumber]", testDecoderUserPortNumber),
    property("test KeyEncoder[UserPortNumber]", testKeyEncoderUserPortNumber),
    property("test KeyDecoder[UserPortNumber]", testKeyDecoderUserPortNumber),
    //
    property("test Encoder[DynamicPortNumber]", testEncoderDynamicPortNumber),
    property("test Decoder[DynamicPortNumber]", testDecoderDynamicPortNumber),
    property("test KeyEncoder[DynamicPortNumber]", testKeyEncoderDynamicPortNumber),
    property("test KeyDecoder[DynamicPortNumber]", testKeyDecoderDynamicPortNumber),
  )

  def testEncoderUri: Property =
    for {
      uri <- networkGens.genUriString.log("uri")
    } yield {
      val input = Uri.unsafeFrom(uri)

      val expected = uri.asJson
      val actual   = input.asJson

      Result.all(
        List(
          actual ==== expected,
          actual.noSpaces ==== expected.noSpaces,
        )
      )
    }

  def testDecoderUri: Property =
    for {
      uri <- networkGens.genUriString.log("uri")
    } yield {

      val input = uri.asJson

      val expected = Uri.from(uri)
      val actual   = decode[Uri](input.noSpaces)

      actual ==== expected
    }

  def testKeyEncoderUri: Property =
    for {
      uris  <- networkGens.genUriString.list(Range.linear(1, 10)).log("uris")
      ns2   <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(uris.length)).log("ns2")
      map   <- Gen.constant(uris.zip(ns2).toMap).log("map")
      input <- Gen.constant(map.map { case (key, value) => Uri.unsafeFrom(key) -> value }).log("input")
    } yield {
      val expected = Json.fromFields(map.map { case (key, value) => key -> value.asJson })

      val actual = input.asJson

      Result.all(
        List(
          actual ==== expected,
          actual.noSpaces ==== expected.noSpaces,
        )
      )
    }

  def testKeyDecoderUri: Property =
    for {
      uris     <- networkGens.genUriString.list(Range.linear(1, 10)).log("uris")
      ns2      <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(uris.length)).log("ns2")
      map      <- Gen.constant(uris.zip(ns2).toMap).log("map")
      expected <- Gen.constant(map.map { case (key, value) => Uri.unsafeFrom(key) -> value }).log("expected")
    } yield {
      val input  = Json.fromFields(map.map { case (key, value) => key -> value.asJson })
      val actual = decode[Map[Uri, Int]](input.noSpaces)

      actual ==== expected.asRight
    }

  //

  def testEncoderUrl: Property =
    for {
      url <- networkGens.genUrlString.log("url")
    } yield {
      val input = Url.unsafeFrom(url)

      val expected = url.asJson
      val actual   = input.asJson

      Result.all(
        List(
          actual ==== expected,
          actual.noSpaces ==== expected.noSpaces,
        )
      )
    }

  def testDecoderUrl: Property =
    for {
      url <- networkGens.genUrlString.log("url")
    } yield {

      val input = url.asJson

      val expected = Url.from(url)
      val actual   = decode[Url](input.noSpaces)

      actual ==== expected
    }

  def testKeyEncoderUrl: Property =
    for {
      urls  <- networkGens.genUrlString.list(Range.linear(1, 10)).log("urls")
      ns2   <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(urls.length)).log("ns2")
      map   <- Gen.constant(urls.zip(ns2).toMap).log("map")
      input <- Gen.constant(map.map { case (key, value) => Url.unsafeFrom(key) -> value }).log("input")
    } yield {
      val expected = Json.fromFields(map.map { case (key, value) => key -> value.asJson })

      val actual = input.asJson

      Result.all(
        List(
          actual ==== expected,
          actual.noSpaces ==== expected.noSpaces,
        )
      )
    }

  def testKeyDecoderUrl: Property =
    for {
      urls     <- networkGens.genUrlString.list(Range.linear(1, 10)).log("urls")
      ns2      <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(urls.length)).log("ns2")
      map      <- Gen.constant(urls.zip(ns2).toMap).log("map")
      expected <- Gen.constant(map.map { case (key, value) => Url.unsafeFrom(key) -> value }).log("expected")
    } yield {
      val input  = Json.fromFields(map.map { case (key, value) => key -> value.asJson })
      val actual = decode[Map[Url, Int]](input.noSpaces)

      actual ==== expected.asRight
    }

  //

  def testEncoderPortNumber: Property =
    for {
      portNumber <- networkGens.genPortNumberInt.log("portNumber")
    } yield {
      val input = PortNumber.unsafeFrom(portNumber)

      val expected = portNumber.asJson
      val actual   = input.asJson

      Result.all(
        List(
          actual ==== expected,
          actual.noSpaces ==== expected.noSpaces,
        )
      )
    }

  def testDecoderPortNumber: Property =
    for {
      portNumber <- networkGens.genPortNumberInt.log("portNumber")
    } yield {

      val input = portNumber.asJson

      val expected = PortNumber.from(portNumber)
      val actual   = decode[PortNumber](input.noSpaces)

      actual ==== expected
    }

  def testKeyEncoderPortNumber: Property =
    for {
      portNumbers <- networkGens.genPortNumberInt.list(Range.linear(1, 10)).log("portNumbers")
      ns2         <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(portNumbers.length)).log("ns2")
      map         <- Gen.constant(portNumbers.zip(ns2).toMap).log("map")
      input       <- Gen.constant(map.map { case (key, value) => PortNumber.unsafeFrom(key) -> value }).log("input")
    } yield {
      val expected = Json.fromFields(map.map { case (key, value) => key.toString -> value.asJson })

      val actual = input.asJson

      Result.all(
        List(
          actual ==== expected,
          actual.noSpacesSortKeys ==== expected.noSpacesSortKeys,
        )
      )
    }

  def testKeyDecoderPortNumber: Property =
    for {
      portNumbers <- networkGens.genPortNumberInt.list(Range.linear(1, 10)).log("portNumbers")
      ns2         <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(portNumbers.length)).log("ns2")
      map         <- Gen.constant(portNumbers.zip(ns2).toMap).log("map")
      expected    <- Gen.constant(map.map { case (key, value) => PortNumber.unsafeFrom(key) -> value }).log("expected")
    } yield {
      val input  = Json.fromFields(map.map { case (key, value) => key.toString -> value.asJson })
      val actual = decode[Map[PortNumber, Int]](input.noSpaces)

      actual ==== expected.asRight
    }

  //

  def testEncoderSystemPortNumber: Property =
    for {
      systemPortNumber <- networkGens.genSystemPortNumberInt.log("systemPortNumber")
    } yield {
      val input = SystemPortNumber.unsafeFrom(systemPortNumber)

      val expected = systemPortNumber.asJson
      val actual   = input.asJson

      Result.all(
        List(
          actual ==== expected,
          actual.noSpaces ==== expected.noSpaces,
        )
      )
    }

  def testDecoderSystemPortNumber: Property =
    for {
      systemPortNumber <- networkGens.genSystemPortNumberInt.log("systemPortNumber")
    } yield {

      val input = systemPortNumber.asJson

      val expected = SystemPortNumber.from(systemPortNumber)
      val actual   = decode[SystemPortNumber](input.noSpaces)

      actual ==== expected
    }

  def testKeyEncoderSystemPortNumber: Property =
    for {
      systemPortNumbers <- networkGens.genSystemPortNumberInt.list(Range.linear(1, 10)).log("systemPortNumbers")
      ns2               <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(systemPortNumbers.length)).log("ns2")
      map               <- Gen.constant(systemPortNumbers.zip(ns2).toMap).log("map")
      input             <- Gen.constant(map.map { case (key, value) => SystemPortNumber.unsafeFrom(key) -> value }).log("input")
    } yield {
      val expected = Json.fromFields(map.map { case (key, value) => key.toString -> value.asJson })

      val actual = input.asJson

      Result.all(
        List(
          actual ==== expected,
          actual.noSpacesSortKeys ==== expected.noSpacesSortKeys,
        )
      )
    }

  def testKeyDecoderSystemPortNumber: Property =
    for {
      systemPortNumbers <- networkGens.genSystemPortNumberInt.list(Range.linear(1, 10)).log("systemPortNumbers")
      ns2               <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(systemPortNumbers.length)).log("ns2")
      map               <- Gen.constant(systemPortNumbers.zip(ns2).toMap).log("map")
      expected          <- Gen.constant(map.map { case (key, value) => SystemPortNumber.unsafeFrom(key) -> value }).log("expected")
    } yield {
      val input  = Json.fromFields(map.map { case (key, value) => key.toString -> value.asJson })
      val actual = decode[Map[SystemPortNumber, Int]](input.noSpaces)

      actual ==== expected.asRight
    }

  //

  def testEncoderNonSystemPortNumber: Property =
    for {
      nonSystemPortNumber <- networkGens.genNonSystemPortNumberInt.log("nonSystemPortNumber")
    } yield {
      val input = NonSystemPortNumber.unsafeFrom(nonSystemPortNumber)

      val expected = nonSystemPortNumber.asJson
      val actual   = input.asJson

      Result.all(
        List(
          actual ==== expected,
          actual.noSpaces ==== expected.noSpaces,
        )
      )
    }

  def testDecoderNonSystemPortNumber: Property =
    for {
      nonSystemPortNumber <- networkGens.genNonSystemPortNumberInt.log("nonSystemPortNumber")
    } yield {

      val input = nonSystemPortNumber.asJson

      val expected = NonSystemPortNumber.from(nonSystemPortNumber)
      val actual   = decode[NonSystemPortNumber](input.noSpaces)

      actual ==== expected
    }

  def testKeyEncoderNonSystemPortNumber: Property =
    for {
      nonSystemPortNumbers <- networkGens.genNonSystemPortNumberInt.list(Range.linear(1, 10)).log("nonSystemPortNumbers")
      ns2                  <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(nonSystemPortNumbers.length)).log("ns2")
      map                  <- Gen.constant(nonSystemPortNumbers.zip(ns2).toMap).log("map")
      input                <- Gen.constant(map.map { case (key, value) => NonSystemPortNumber.unsafeFrom(key) -> value }).log("input")
    } yield {
      val expected = Json.fromFields(map.map { case (key, value) => key.toString -> value.asJson })

      val actual = input.asJson

      Result.all(
        List(
          actual ==== expected,
          actual.noSpacesSortKeys ==== expected.noSpacesSortKeys,
        )
      )
    }

  def testKeyDecoderNonSystemPortNumber: Property =
    for {
      nonSystemPortNumbers <- networkGens.genNonSystemPortNumberInt.list(Range.linear(1, 10)).log("nonSystemPortNumbers")
      ns2                  <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(nonSystemPortNumbers.length)).log("ns2")
      map                  <- Gen.constant(nonSystemPortNumbers.zip(ns2).toMap).log("map")
      expected             <- Gen.constant(map.map { case (key, value) => NonSystemPortNumber.unsafeFrom(key) -> value }).log("expected")
    } yield {
      val input  = Json.fromFields(map.map { case (key, value) => key.toString -> value.asJson })
      val actual = decode[Map[NonSystemPortNumber, Int]](input.noSpaces)

      actual ==== expected.asRight
    }

  //

  def testEncoderUserPortNumber: Property =
    for {
      userPortNumber <- networkGens.genUserPortNumberInt.log("userPortNumber")
    } yield {
      val input = UserPortNumber.unsafeFrom(userPortNumber)

      val expected = userPortNumber.asJson
      val actual   = input.asJson

      Result.all(
        List(
          actual ==== expected,
          actual.noSpaces ==== expected.noSpaces,
        )
      )
    }

  def testDecoderUserPortNumber: Property =
    for {
      userPortNumber <- networkGens.genUserPortNumberInt.log("userPortNumber")
    } yield {

      val input = userPortNumber.asJson

      val expected = UserPortNumber.from(userPortNumber)
      val actual   = decode[UserPortNumber](input.noSpaces)

      actual ==== expected
    }

  def testKeyEncoderUserPortNumber: Property =
    for {
      userPortNumbers <- networkGens.genUserPortNumberInt.list(Range.linear(1, 10)).log("userPortNumbers")
      ns2             <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(userPortNumbers.length)).log("ns2")
      map             <- Gen.constant(userPortNumbers.zip(ns2).toMap).log("map")
      input           <- Gen.constant(map.map { case (key, value) => UserPortNumber.unsafeFrom(key) -> value }).log("input")
    } yield {
      val expected = Json.fromFields(map.map { case (key, value) => key.toString -> value.asJson })

      val actual = input.asJson

      Result.all(
        List(
          actual ==== expected,
          actual.noSpacesSortKeys ==== expected.noSpacesSortKeys,
        )
      )
    }

  def testKeyDecoderUserPortNumber: Property =
    for {
      userPortNumbers <- networkGens.genUserPortNumberInt.list(Range.linear(1, 10)).log("userPortNumbers")
      ns2             <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(userPortNumbers.length)).log("ns2")
      map             <- Gen.constant(userPortNumbers.zip(ns2).toMap).log("map")
      expected        <- Gen.constant(map.map { case (key, value) => UserPortNumber.unsafeFrom(key) -> value }).log("expected")
    } yield {
      val input  = Json.fromFields(map.map { case (key, value) => key.toString -> value.asJson })
      val actual = decode[Map[UserPortNumber, Int]](input.noSpaces)

      actual ==== expected.asRight
    }

  //

  def testEncoderDynamicPortNumber: Property =
    for {
      dynamicPortNumber <- networkGens.genDynamicPortNumberInt.log("dynamicPortNumber")
    } yield {
      val input = DynamicPortNumber.unsafeFrom(dynamicPortNumber)

      val expected = dynamicPortNumber.asJson
      val actual   = input.asJson

      Result.all(
        List(
          actual ==== expected,
          actual.noSpaces ==== expected.noSpaces,
        )
      )
    }

  def testDecoderDynamicPortNumber: Property =
    for {
      dynamicPortNumber <- networkGens.genDynamicPortNumberInt.log("dynamicPortNumber")
    } yield {

      val input = dynamicPortNumber.asJson

      val expected = DynamicPortNumber.from(dynamicPortNumber)
      val actual   = decode[DynamicPortNumber](input.noSpaces)

      actual ==== expected
    }

  def testKeyEncoderDynamicPortNumber: Property =
    for {
      dynamicPortNumbers <- networkGens.genDynamicPortNumberInt.list(Range.linear(1, 10)).log("dynamicPortNumbers")
      ns2                <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(dynamicPortNumbers.length)).log("ns2")
      map                <- Gen.constant(dynamicPortNumbers.zip(ns2).toMap).log("map")
      input              <- Gen.constant(map.map { case (key, value) => DynamicPortNumber.unsafeFrom(key) -> value }).log("input")
    } yield {
      val expected = Json.fromFields(map.map { case (key, value) => key.toString -> value.asJson })

      val actual = input.asJson

      Result.all(
        List(
          actual ==== expected,
          actual.noSpacesSortKeys ==== expected.noSpacesSortKeys,
        )
      )
    }

  def testKeyDecoderDynamicPortNumber: Property =
    for {
      dynamicPortNumbers <- networkGens.genDynamicPortNumberInt.list(Range.linear(1, 10)).log("dynamicPortNumbers")
      ns2                <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(dynamicPortNumbers.length)).log("ns2")
      map                <- Gen.constant(dynamicPortNumbers.zip(ns2).toMap).log("map")
      expected           <- Gen.constant(map.map { case (key, value) => DynamicPortNumber.unsafeFrom(key) -> value }).log("expected")
    } yield {
      val input  = Json.fromFields(map.map { case (key, value) => key.toString -> value.asJson })
      val actual = decode[Map[DynamicPortNumber, Int]](input.noSpaces)

      actual ==== expected.asRight
    }

}
object networkSpec extends Properties, networkSpec {

  override protected object networkTypeClasses extends refined4s.modules.circe.derivation.types.network

  override def tests: List[Test] = allTests

}
