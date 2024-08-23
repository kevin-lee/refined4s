package refined4s.modules.circe.derivation.types

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
    //
    property("test Encoder[Url]", testEncoderUrl),
    property("test Decoder[Url]", testDecoderUrl),
    //
    property("test Encoder[PortNumber]", testEncoderPortNumber),
    property("test Decoder[PortNumber]", testDecoderPortNumber),
    //
    property("test Encoder[SystemPortNumber]", testEncoderSystemPortNumber),
    property("test Decoder[SystemPortNumber]", testDecoderSystemPortNumber),
    //
    property("test Encoder[NonSystemPortNumber]", testEncoderNonSystemPortNumber),
    property("test Decoder[NonSystemPortNumber]", testDecoderNonSystemPortNumber),
    //
    property("test Encoder[UserPortNumber]", testEncoderUserPortNumber),
    property("test Decoder[UserPortNumber]", testDecoderUserPortNumber),
    //
    property("test Encoder[DynamicPortNumber]", testEncoderDynamicPortNumber),
    property("test Decoder[DynamicPortNumber]", testDecoderDynamicPortNumber),
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

  //

  def testEncoderUrl: Property =
    for {
      uri <- networkGens.genUrlString.log("uri")
    } yield {
      val input = Url.unsafeFrom(uri)

      val expected = uri.asJson
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
      uri <- networkGens.genUrlString.log("uri")
    } yield {

      val input = uri.asJson

      val expected = Url.from(uri)
      val actual   = decode[Url](input.noSpaces)

      actual ==== expected
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

}
object networkSpec extends Properties, networkSpec {

  override protected val networkTypeClasses: refined4s.modules.circe.derivation.types.network =
    refined4s.modules.circe.derivation.types.network

  override def tests: List[Test] = allTests

}
