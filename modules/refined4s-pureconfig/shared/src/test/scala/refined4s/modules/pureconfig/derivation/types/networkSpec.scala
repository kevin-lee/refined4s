package refined4s.modules.pureconfig.derivation.types

import cats.syntax.all.*
import hedgehog.*
import hedgehog.runner.*
import pureconfig.error.{ConfigReaderFailures, ConvertFailure, UserValidationFailed}
import pureconfig.generic.derivation.default.*
import pureconfig.{ConfigReader, ConfigSource, ConfigWriter}
import refined4s.*
import refined4s.internal.typeTools
import refined4s.types.network.*
import refined4s.types.networkGens

/** @author Kevin Lee
  * @since 2025-09-02
  */
trait networkSpec {

  protected val networkTypeClasses: refined4s.modules.pureconfig.derivation.types.network
  import networkTypeClasses.given

  def allTests: List[Test] = List(
    //
    property("test ConfigReader[Uri]", testConfigReaderUri),
    property("test ConfigReader[Uri] with invalid value", testConfigReaderUriInvalid),
    property("test ConfigWriter[Uri]", testConfigWriterUri),

    //
    property("test ConfigReader[Url]", testConfigReaderUrl),
    property("test ConfigReader[Url] with invalid value", testConfigReaderUrlInvalid),
    property("test ConfigWriter[Url]", testConfigWriterUrl),

    //
    property("test ConfigReader[PortNumber]", testConfigReaderPortNumber),
    property("test ConfigReader[PortNumber] with invalid value", testConfigReaderPortNumberInvalid),
    property("test ConfigWriter[PortNumber]", testConfigWriterPortNumber),
    //
    property("test ConfigReader[SystemPortNumber]", testConfigReaderSystemPortNumber),
    property("test ConfigReader[SystemPortNumber] with invalid value", testConfigReaderSystemPortNumberInvalid),
    property("test ConfigWriter[SystemPortNumber]", testConfigWriterSystemPortNumber),
    //
    property("test ConfigReader[NonSystemPortNumber]", testConfigReaderNonSystemPortNumber),
    property("test ConfigReader[NonSystemPortNumber] with invalid value", testConfigReaderNonSystemPortNumberInvalid),
    property("test ConfigWriter[NonSystemPortNumber]", testConfigWriterNonSystemPortNumber),
    //
    property("test ConfigReader[UserPortNumber]", testConfigReaderUserPortNumber),
    property("test ConfigReader[UserPortNumber] with invalid value", testConfigReaderUserPortNumberInvalid),
    property("test ConfigWriter[UserPortNumber]", testConfigWriterUserPortNumber),
    //
    property("test ConfigReader[DynamicPortNumber]", testConfigReaderDynamicPortNumber),
    property("test ConfigReader[DynamicPortNumber] with invalid value", testConfigReaderDynamicPortNumberInvalid),
    property("test ConfigWriter[DynamicPortNumber]", testConfigWriterDynamicPortNumber),
  )

  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  def testConfigReaderUri: Property =
    for {
      uri <- networkGens.genUriString.log("uri")
    } yield {

      val confString =
        raw"""
             |uri = "$uri"
             |""".stripMargin

      val expected = Uri.unsafeFrom(uri)

      ConfigSource
        .string(confString)
        .load[UriConfig] match {
        case Right(UriConfig(actual)) =>
          actual ==== expected

        case Left(err) =>
          Result.failure.log(s"parse String config failed with error: ${err.toString}")
      }

    }

  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  def testConfigReaderUriInvalid: Property =
    for {
      uri <- Gen.string(Gen.element1('%', '^', '<', '>', '[', ']', '`', '{', '}'), Range.linear(1, 5)).log("uri")
    } yield {

      val confString = raw"""uri = "$uri""""

      val expected = Uri.from(uri).leftMap { err =>
        s"The value $uri cannot be created as the expected type, ${typeTools.getTypeName[Uri]}, due to the following error: $err"
      }

      ConfigSource
        .string(confString)
        .load[UriConfig] match {
        case Right(UriConfig(actual)) =>
          Result.failure.log(s"It should have failed to parse the config but got $actual")

        case Left(ConfigReaderFailures(ConvertFailure(UserValidationFailed(err), _, _))) =>
          err.asLeft ==== expected

        case unexpected =>
          Result.failure.log(s"Unexpected result: ${unexpected.toString}")
      }

    }

  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  def testConfigWriterUri: Property =
    for {
      uri <- networkGens.genUriString.log("uri")
    } yield {

      val input  = Uri.unsafeFrom(uri)
      val actual = ConfigWriter[Uri].to(input)

      val expected = ConfigWriter[String].to(uri)

      actual ==== expected

    }

  final case class UriConfig(uri: Uri) derives ConfigReader

  //

  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  def testConfigReaderUrl: Property =
    for {
      url <- networkGens.genUrlString.log("url")
    } yield {

      val confString =
        raw"""
             |url = "$url"
             |""".stripMargin

      val expected = Url.unsafeFrom(url)

      ConfigSource
        .string(confString)
        .load[UrlConfig] match {
        case Right(UrlConfig(actual)) =>
          actual ==== expected

        case Left(err) =>
          Result.failure.log(s"parse String config failed with error: ${err.toString}")
      }

    }

  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  def testConfigReaderUrlInvalid: Property =
    for {
      url <- Gen.string(Gen.element1('%', '^', '<', '>', '[', ']', '`', '{', '}'), Range.linear(1, 5)).log("url")
    } yield {

      val confString = raw"""url = "$url""""

      val expected = Url.from(url).leftMap { err =>
        s"The value $url cannot be created as the expected type, ${typeTools.getTypeName[Url]}, due to the following error: $err"
      }

      ConfigSource
        .string(confString)
        .load[UrlConfig] match {
        case Right(UrlConfig(actual)) =>
          Result.failure.log(s"It should have failed to parse the config but got $actual")

        case Left(ConfigReaderFailures(ConvertFailure(UserValidationFailed(err), _, _))) =>
          err.asLeft ==== expected

        case unexpected =>
          Result.failure.log(s"Unexpected result: ${unexpected.toString}")
      }

    }

  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  def testConfigWriterUrl: Property =
    for {
      url <- networkGens.genUrlString.log("url")
    } yield {

      val input  = Url.unsafeFrom(url)
      val actual = ConfigWriter[Url].to(input)

      val expected = ConfigWriter[String].to(url)

      actual ==== expected

    }

  final case class UrlConfig(url: Url) derives ConfigReader

  /* network ports */

  def testConfigReaderPortNumber: Property =
    for {
      portNumber <- networkGens.genPortNumberInt.log("portNumber")
    } yield {

      val confString =
        raw"""
             |port = $portNumber
             |""".stripMargin

      val expected = PortNumber.unsafeFrom(portNumber)

      ConfigSource
        .string(confString)
        .load[PortNumberConfig] match {
        case Right(PortNumberConfig(actual)) =>
          actual ==== expected

        case Left(err) =>
          Result.failure.log(s"parse config failed with error: ${err.toString}")
      }

    }

  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  def testConfigReaderPortNumberInvalid: Property =
    for {
      portNumber <- networkGens.genInvalidPortNumberInt.log("portNumber")
    } yield {

      val confString = raw"""port = $portNumber"""

      val expected = PortNumber.from(portNumber).leftMap { err =>
        s"The value $portNumber cannot be created as the expected type, ${typeTools.getTypeName[PortNumber]}, due to the following error: $err"
      }

      ConfigSource
        .string(confString)
        .load[PortNumberConfig] match {
        case Right(PortNumberConfig(actual)) =>
          Result.failure.log(s"It should have failed to parse the config but got $actual")

        case Left(ConfigReaderFailures(ConvertFailure(UserValidationFailed(err), _, _))) =>
          err.asLeft ==== expected

        case unexpected =>
          Result.failure.log(s"Unexpected result: ${unexpected.toString}")
      }

    }

  def testConfigWriterPortNumber: Property =
    for {
      portNumber <- networkGens.genPortNumberInt.log("portNumber")
    } yield {

      val input = PortNumber.unsafeFrom(portNumber)

      val expected = ConfigWriter[Int].to(portNumber)
      val actual   = ConfigWriter[PortNumber].to(input)

      actual ==== expected

    }
  final case class PortNumberConfig(port: PortNumber) derives ConfigReader

  ///

  def testConfigReaderSystemPortNumber: Property =
    for {
      systemPortNumber <- networkGens.genSystemPortNumberInt.log("systemPortNumber")
    } yield {

      val confString =
        raw"""
             |port = $systemPortNumber
             |""".stripMargin

      val expected = SystemPortNumber.unsafeFrom(systemPortNumber)

      ConfigSource
        .string(confString)
        .load[SystemPortNumberConfig] match {
        case Right(SystemPortNumberConfig(actual)) =>
          actual ==== expected

        case Left(err) =>
          Result.failure.log(s"parse config failed with error: ${err.toString}")
      }

    }

  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  def testConfigReaderSystemPortNumberInvalid: Property =
    for {
      systemPortNumber <- networkGens.genInvalidSystemPortNumberInt.log("systemPortNumber")
    } yield {

      val confString = raw"""port = $systemPortNumber"""

      val expected = SystemPortNumber.from(systemPortNumber).leftMap { err =>
        s"The value $systemPortNumber cannot be created as the expected type, ${typeTools.getTypeName[SystemPortNumber]}, due to the following error: $err"
      }

      ConfigSource
        .string(confString)
        .load[SystemPortNumberConfig] match {
        case Right(SystemPortNumberConfig(actual)) =>
          Result.failure.log(s"It should have failed to parse the config but got $actual")

        case Left(ConfigReaderFailures(ConvertFailure(UserValidationFailed(err), _, _))) =>
          err.asLeft ==== expected

        case unexpected =>
          Result.failure.log(s"Unexpected result: ${unexpected.toString}")
      }

    }

  def testConfigWriterSystemPortNumber: Property =
    for {
      systemPortNumber <- networkGens.genSystemPortNumberInt.log("systemPortNumber")
    } yield {

      val input = SystemPortNumber.unsafeFrom(systemPortNumber)

      val expected = ConfigWriter[Int].to(systemPortNumber)
      val actual   = ConfigWriter[SystemPortNumber].to(input)

      actual ==== expected

    }
  final case class SystemPortNumberConfig(port: SystemPortNumber) derives ConfigReader

  ///

  def testConfigReaderNonSystemPortNumber: Property =
    for {
      nonSystemPortNumber <- networkGens.genNonSystemPortNumberInt.log("nonSystemPortNumber")
    } yield {

      val confString =
        raw"""
             |port = $nonSystemPortNumber
             |""".stripMargin

      val expected = NonSystemPortNumber.unsafeFrom(nonSystemPortNumber)

      ConfigSource
        .string(confString)
        .load[NonSystemPortNumberConfig] match {
        case Right(NonSystemPortNumberConfig(actual)) =>
          actual ==== expected

        case Left(err) =>
          Result.failure.log(s"parse config failed with error: ${err.toString}")
      }

    }

  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  def testConfigReaderNonSystemPortNumberInvalid: Property =
    for {
      nonSystemPortNumber <- networkGens.genInvalidNonSystemPortNumberInt.log("nonSystemPortNumber")
    } yield {

      val confString = raw"""port = $nonSystemPortNumber"""

      val expected = NonSystemPortNumber.from(nonSystemPortNumber).leftMap { err =>
        s"The value $nonSystemPortNumber cannot be created as the expected type, ${typeTools.getTypeName[NonSystemPortNumber]}, due to the following error: $err"
      }

      ConfigSource
        .string(confString)
        .load[NonSystemPortNumberConfig] match {
        case Right(NonSystemPortNumberConfig(actual)) =>
          Result.failure.log(s"It should have failed to parse the config but got $actual")

        case Left(ConfigReaderFailures(ConvertFailure(UserValidationFailed(err), _, _))) =>
          err.asLeft ==== expected

        case unexpected =>
          Result.failure.log(s"Unexpected result: ${unexpected.toString}")
      }

    }

  def testConfigWriterNonSystemPortNumber: Property =
    for {
      nonSystemPortNumber <- networkGens.genNonSystemPortNumberInt.log("nonSystemPortNumber")
    } yield {

      val input = NonSystemPortNumber.unsafeFrom(nonSystemPortNumber)

      val expected = ConfigWriter[Int].to(nonSystemPortNumber)
      val actual   = ConfigWriter[NonSystemPortNumber].to(input)

      actual ==== expected

    }
  final case class NonSystemPortNumberConfig(port: NonSystemPortNumber) derives ConfigReader

  ///

  def testConfigReaderUserPortNumber: Property =
    for {
      userPortNumber <- networkGens.genUserPortNumberInt.log("userPortNumber")
    } yield {

      val confString =
        raw"""
             |port = $userPortNumber
             |""".stripMargin

      val expected = UserPortNumber.unsafeFrom(userPortNumber)

      ConfigSource
        .string(confString)
        .load[UserPortNumberConfig] match {
        case Right(UserPortNumberConfig(actual)) =>
          actual ==== expected

        case Left(err) =>
          Result.failure.log(s"parse config failed with error: ${err.toString}")
      }

    }

  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  def testConfigReaderUserPortNumberInvalid: Property =
    for {
      userPortNumber <- networkGens.genInvalidUserPortNumberInt.log("userPortNumber")
    } yield {

      val confString = raw"""port = $userPortNumber"""

      val expected = UserPortNumber.from(userPortNumber).leftMap { err =>
        s"The value $userPortNumber cannot be created as the expected type, ${typeTools.getTypeName[UserPortNumber]}, due to the following error: $err"
      }

      ConfigSource
        .string(confString)
        .load[UserPortNumberConfig] match {
        case Right(UserPortNumberConfig(actual)) =>
          Result.failure.log(s"It should have failed to parse the config but got $actual")

        case Left(ConfigReaderFailures(ConvertFailure(UserValidationFailed(err), _, _))) =>
          err.asLeft ==== expected

        case unexpected =>
          Result.failure.log(s"Unexpected result: ${unexpected.toString}")
      }

    }

  def testConfigWriterUserPortNumber: Property =
    for {
      userPortNumber <- networkGens.genUserPortNumberInt.log("userPortNumber")
    } yield {

      val input = UserPortNumber.unsafeFrom(userPortNumber)

      val expected = ConfigWriter[Int].to(userPortNumber)
      val actual   = ConfigWriter[UserPortNumber].to(input)

      actual ==== expected

    }
  final case class UserPortNumberConfig(port: UserPortNumber) derives ConfigReader

  ///

  def testConfigReaderDynamicPortNumber: Property =
    for {
      dynamicPortNumber <- networkGens.genDynamicPortNumberInt.log("dynamicPortNumber")
    } yield {

      val confString =
        raw"""
             |port = $dynamicPortNumber
             |""".stripMargin

      val expected = DynamicPortNumber.unsafeFrom(dynamicPortNumber)

      ConfigSource
        .string(confString)
        .load[DynamicPortNumberConfig] match {
        case Right(DynamicPortNumberConfig(actual)) =>
          actual ==== expected

        case Left(err) =>
          Result.failure.log(s"parse config failed with error: ${err.toString}")
      }

    }

  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  def testConfigReaderDynamicPortNumberInvalid: Property =
    for {
      dynamicPortNumber <- networkGens.genInvalidDynamicPortNumberInt.log("dynamicPortNumber")
    } yield {

      val confString = raw"""port = $dynamicPortNumber"""

      val expected = DynamicPortNumber.from(dynamicPortNumber).leftMap { err =>
        s"The value $dynamicPortNumber cannot be created as the expected type, ${typeTools.getTypeName[DynamicPortNumber]}, due to the following error: $err"
      }

      ConfigSource
        .string(confString)
        .load[DynamicPortNumberConfig] match {
        case Right(DynamicPortNumberConfig(actual)) =>
          Result.failure.log(s"It should have failed to parse the config but got $actual")

        case Left(ConfigReaderFailures(ConvertFailure(UserValidationFailed(err), _, _))) =>
          err.asLeft ==== expected

        case unexpected =>
          Result.failure.log(s"Unexpected result: ${unexpected.toString}")
      }

    }

  def testConfigWriterDynamicPortNumber: Property =
    for {
      dynamicPortNumber <- networkGens.genDynamicPortNumberInt.log("dynamicPortNumber")
    } yield {

      val input = DynamicPortNumber.unsafeFrom(dynamicPortNumber)

      val expected = ConfigWriter[Int].to(dynamicPortNumber)
      val actual   = ConfigWriter[DynamicPortNumber].to(input)

      actual ==== expected

    }
  final case class DynamicPortNumberConfig(port: DynamicPortNumber) derives ConfigReader

}
object networkSpec extends Properties, networkSpec {

  override protected object networkTypeClasses extends refined4s.modules.pureconfig.derivation.types.network

  override def tests: List[Test] = allTests

}
