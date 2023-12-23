package refined4s.modules.pureconfig.derivation

import com.typesafe.config.{ConfigRenderOptions, ConfigValueFactory}
import hedgehog.*
import hedgehog.runner.*
import pureconfig.ConfigWriter
import refined4s.types.all.Uri
import refined4s.types.networkGens
import refined4s.{InlinedRefined, Newtype, Refined}

/** @author Kevin Lee
  * @since 2023-12-15
  */
object PureconfigConfigWriterSpec extends Properties {
  override def tests: List[Test] = List(
    property("test PureconfigConfigWriter", testPureconfigConfigWriter)
  )

  import scala.jdk.CollectionConverters.*
  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  def testPureconfigConfigWriter: Property =
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

  final case class NewtypeApiConfig(api: NewtypeApiConfig.Api)
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
    )
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
  object Id extends InlinedRefined[Long] with PureconfigConfigWriter[Long] {

    override inline val inlinedExpectedValue = "a positive Long"

    override inline def invalidReason(a: Long): String =
      "It must be a positive Long"

    override inline def predicate(a: Long): Boolean = a > 0L

    override inline def inlinedPredicate(inline a: Long): Boolean = a > 0L
  }

  given configWriterUri: ConfigWriter[Uri] = Uri.deriving[ConfigWriter]

  type NewtypeBaseUri = NewtypeBaseUri.Type
  object NewtypeBaseUri extends Newtype[Uri] with PureconfigConfigWriter[Uri]

  type RefinedEndpointPath = RefinedEndpointPath.Type
  @SuppressWarnings(Array("org.wartremover.warts.Equals"))
  object RefinedEndpointPath extends Refined[String] with PureconfigConfigWriter[String] {
    override inline def invalidReason(a: String): String =
      "It must be a non-empty String"

    override inline def predicate(a: String): Boolean = a != ""
  }

  type InlinedRefinedNewtypeId = InlinedRefinedNewtypeId.Type
  object InlinedRefinedNewtypeId extends Newtype[Id] with PureconfigConfigWriter[Id]
}
