package refined4s.modules.pureconfig.derivation

import hedgehog.*
import hedgehog.runner.*
import pureconfig.generic.derivation.default.*
import pureconfig.{ConfigReader, ConfigSource, ConfigWriter}
import refined4s.*
import refined4s.modules.pureconfig.derivation.instances.given
import refined4s.types.all.*
import refined4s.types.networkGens

/** @author Kevin Lee
  * @since 2023-12-13
  */
object existingTypesSpec extends Properties {
  override def tests: List[Test] = List(
    property("test ConfigReader[Uri]", testConfigReaderUri),
    property("test ConfigWriter[Uri]", testConfigWriterUri),
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
        .load[TestConfig] match {
        case Right(TestConfig(actual)) =>
          actual ==== expected

        case Left(err) =>
          Result.failure.log(s"parse String config failed with error: ${err.toString}")
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

  final case class TestConfig(uri: Uri) derives ConfigReader

}
