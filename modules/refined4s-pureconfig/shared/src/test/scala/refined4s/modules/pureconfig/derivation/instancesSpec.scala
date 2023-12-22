package refined4s.modules.pureconfig.derivation

import com.typesafe.config.{ConfigRenderOptions, ConfigValue, ConfigValueFactory}
import hedgehog.*
import hedgehog.runner.*
import pureconfig.error.{ConfigReaderFailures, ConvertFailure, UserValidationFailed}
import pureconfig.{ConfigReader, ConfigSource, ConfigWriter}
import pureconfig.generic.derivation.default.*
import refined4s.*
import refined4s.types.all.*
import refined4s.modules.pureconfig.derivation.instances.given
import refined4s.types.networkGens

/** @author Kevin Lee
  * @since 2023-12-13
  */
object instancesSpec extends Properties {
  override def tests: List[Test] = List(
    property("test ConfigReader[Newtype], ConfigReader[Refined] and ConfigReader[InlinedRefined] all together", testConfigReaderAll),
    property("test ConfigReader[Refined] with invalid value", testConfigReaderInvalid),
    property("test ConfigWriter[Newtype], ConfigWriter[Refined] and ConfigWriter[InlinedRefined] all together", testConfigWriterAll),
  )

  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  def testConfigReaderAll: Property =
    for {
      id           <- Gen.long(Range.linear(1L, Long.MaxValue)).log("id")
      uri          <- networkGens.genUriStringWithoutPath.log("uri")
      endpointPath <- Gen.string(Gen.alphaNum, Range.linear(1, 7)).list(Range.linear(1, 5)).map(_.mkString("/")).log("endpointPath")
      additionalId <- Gen.long(Range.linear(1L, Long.MaxValue)).log("additionalId")
    } yield {

      val confString =
        raw"""api {
             |  id = ${id.toString}
             |  base-uri = "$uri"
             |  endpoint-path = "$endpointPath"
             |  additional-id = $additionalId
             |}
             |""".stripMargin

      val expected = NewtypeApiConfig(
        NewtypeApiConfig.Api(
          Id.unsafeFrom(id),
          NewtypeBaseUri(Uri.unsafeFrom(uri)),
          RefinedEndpointPath.unsafeFrom(endpointPath),
          InlinedRefinedNewtypeId(Id.unsafeFrom(additionalId)),
        )
      )

      ConfigSource
        .string(confString)
        .load[NewtypeApiConfig] match {
        case Right(actual) =>
          actual ==== expected

        case Left(err) =>
          Result.failure.log(s"parse String config failed with error: ${err.toString}")
      }

    }

  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  def testConfigReaderInvalid: Property =
    for {
      id           <- Gen.long(Range.linear(Long.MinValue, 0)).log("id")
      uri          <- networkGens.genUriStringWithoutPath.log("uri")
      endpointPath <- Gen.string(Gen.alphaNum, Range.linear(1, 7)).list(Range.linear(1, 5)).map(_.mkString("/")).log("endpointPath")
      additionalId <- Gen.long(Range.linear(1L, Long.MaxValue)).log("additionalId")
    } yield {

      val confString =
        raw"""api {
             |  id = $id
             |  base-uri = "$uri"
             |  endpoint-path = "$endpointPath"
             |  additional-id = $additionalId
             |}
             |""".stripMargin

      val expected = s"Invalid value found: ${id.toString} with error: Invalid value: [${id.toString}]. It must be a positive Long"

      ConfigSource
        .string(confString)
        .load[NewtypeApiConfig] match {
        case Right(actual) =>
          Result.failure.log(s"It should have failed to parse the config but got $actual")

        case Left(ConfigReaderFailures(ConvertFailure(UserValidationFailed(err), _, _))) =>
          err ==== expected

        case unexpected =>
          Result.failure.log(s"Unexpected result: ${unexpected.toString}")
      }

    }

  import scala.jdk.CollectionConverters.*
  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  def testConfigWriterAll: Property =
    for {
      id           <- Gen.long(Range.linear(1L, Long.MaxValue)).log("id")
      uri          <- networkGens.genUriStringWithoutPath.log("uri")
      endpointPath <- Gen.string(Gen.alphaNum, Range.linear(1, 7)).list(Range.linear(1, 5)).map(_.mkString("/")).log("endpointPath")
      additionalId <- Gen.long(Range.linear(1L, Long.MaxValue)).log("additionalId")
    } yield {

      val expected = ConfigValueFactory.fromMap(
        Map(
          "api" -> Map(
            "id"            -> id,
            "base-uri"      -> uri,
            "endpoint-path" -> endpointPath,
            "additional-id" -> additionalId,
          ).asJava
        ).asJava
      )

      val input = NewtypeApiConfig(
        NewtypeApiConfig.Api(
          Id.unsafeFrom(id),
          NewtypeBaseUri(Uri.unsafeFrom(uri)),
          RefinedEndpointPath.unsafeFrom(endpointPath),
          InlinedRefinedNewtypeId(Id.unsafeFrom(additionalId)),
        )
      )

      val actual = ConfigWriter[NewtypeApiConfig].to(input)

      (actual ==== expected).log(
        s"""
           | >>   actual: ${actual.render(ConfigRenderOptions.concise())}
           | >> ---------
           | >> expected: ${expected.render(ConfigRenderOptions.concise())}
           |""".stripMargin
      )

    }

  final case class NewtypeApiConfig(api: NewtypeApiConfig.Api) derives ConfigReader
  object NewtypeApiConfig {

    given configWriterNewtypeApiConfig: ConfigWriter[NewtypeApiConfig] = a =>
      ConfigValueFactory.fromMap(
        Map("api" -> ConfigWriter[NewtypeApiConfig.Api].to(a.api)).asJava
      )

    final case class Api(
      id: Id,
      baseUri: NewtypeBaseUri,
      endpointPath: RefinedEndpointPath,
      additionalId: InlinedRefinedNewtypeId,
    ) derives ConfigReader
    object Api {
      given configWriterApi: ConfigWriter[Api] = a =>
        ConfigValueFactory.fromMap(
          Map(
            "id"            -> ConfigWriter[Id].to(a.id),
            "base-uri"      -> ConfigWriter[NewtypeBaseUri].to(a.baseUri),
            "endpoint-path" -> ConfigWriter[RefinedEndpointPath].to(a.endpointPath),
            "additional-id" -> ConfigWriter[InlinedRefinedNewtypeId].to(a.additionalId),
          ).asJava
        )
    }
  }

  type Id = Id.Type
  object Id extends InlinedRefined[Long] {

    override inline def invalidReason(a: Long): String =
      "It must be a positive Long"

    override inline def predicate(a: Long): Boolean = a > 0L

    override inline def inlinedPredicate(inline a: Long): Boolean = a > 0L
  }

  type NewtypeBaseUri = NewtypeBaseUri.Type
  object NewtypeBaseUri extends Newtype[Uri]

  type RefinedEndpointPath = RefinedEndpointPath.Type
  @SuppressWarnings(Array("org.wartremover.warts.Equals"))
  object RefinedEndpointPath extends Refined[String] {
    override inline def invalidReason(a: String): String =
      "It must be a non-empty String"

    override inline def predicate(a: String): Boolean = a != ""
  }

  type InlinedRefinedNewtypeId = InlinedRefinedNewtypeId.Type
  object InlinedRefinedNewtypeId extends Newtype[Id]
}
