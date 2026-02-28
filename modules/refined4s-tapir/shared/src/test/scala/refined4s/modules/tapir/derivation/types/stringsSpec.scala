package refined4s.modules.tapir.derivation.types

import hedgehog.*
import hedgehog.runner.*
import refined4s.types.UuidV7TestTools
import refined4s.types.strings.*
import sttp.tapir.Codec.PlainCodec
import sttp.tapir.{Codec, DecodeResult, Schema, ValidationError}

import java.nio.charset.StandardCharsets
import java.util.UUID

/** @author Kevin Lee
  * @since 2025-09-02
  */
trait stringsSpec {

  protected val stringsTypeClasses: refined4s.modules.tapir.derivation.types.strings
  import stringsTypeClasses.given

  def allTests: List[Test] = List(
    //
    property("test Schema[NonEmptyString]", testSchemaNonEmptyString),
    //
    property("test Schema[NonBlankString]", testSchemaNonBlankString),
    //
    property("test Schema[Uuid]", testSchemaUuid),
    //
    property("test Schema[UuidV7]", testSchemaUuidV7),
    property("test Codec[String, UuidV7, CodecFormat.TextPlain]", testCodecUuidV7),
  )

  def testSchemaNonEmptyString: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
    } yield {
      val input = NonEmptyString.unsafeFrom(s)

      val expected = List.empty[ValidationError[?]]
      val actual   = summon[Schema[NonEmptyString]].applyValidation(input)

      actual ==== expected
    }

  def testSchemaNonBlankString: Property =
    for {
      nonWhitespaceString <- Gen
                               .string(hedgehog.extra.Gens.genNonWhitespaceChar, Range.linear(1, 10))
                               .map(s => new String(s.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8))
                               .log("nonWhitespaceString")
      whitespaceString    <- Gen
                               .string(
                                 hedgehog.extra.Gens.genCharByRange(refined4s.types.strings.WhitespaceCharRange),
                                 Range.linear(1, 10),
                               )
                               .log("whitespaceString")

      s <- Gen.constant(scala.util.Random.shuffle((nonWhitespaceString + whitespaceString).toList).mkString).log("s")
    } yield {
      val input = NonBlankString.unsafeFrom(s)

      val expected = List.empty[ValidationError[?]]
      val actual   = summon[Schema[NonBlankString]].applyValidation(input)

      actual ==== expected
    }

  def testSchemaUuid: Property =
    for {
      uuid <- Gen.constant(UUID.randomUUID()).log("uuid")
    } yield {
      val input = Uuid(uuid)

      val expected = List.empty[ValidationError[?]]
      val actual   = summon[Schema[Uuid]].applyValidation(input)

      actual ==== expected
    }

  ///

  def testSchemaUuidV7: Property =
    for {
      uuid <- Gen.elementUnsafe(UuidV7TestTools.validUuidV7Strings).log("uuid")
    } yield {
      val input = UuidV7.unsafeFromString(uuid)

      val expected = Schema.schemaForUUID.map(uuid => Some(UuidV7.unsafeFrom(uuid)))(_.toUUID)
      val actual   = summon[Schema[UuidV7]]

      Result.all(
        List(
          actual.schemaType ==== expected.schemaType,
          actual.format ==== expected.format,
          actual.isOptional ==== expected.isOptional,
          actual.description ==== expected.description,
          actual.default ==== expected.default,
          actual.encodedExample ==== expected.encodedExample,
          actual.deprecated ==== expected.deprecated,
          actual.validator(input) ==== expected.validator(input),
        )
      )
    }

  def testCodecUuidV7: Property =
    for {
      uuid <- Gen.elementUnsafe(UuidV7TestTools.validUuidV7Strings).log("uuid")
    } yield {
      val input = UuidV7.unsafeFromString(uuid)

      val expected = Codec
        .uuid
        .mapDecode { uuid =>
          DecodeResult.Value(UuidV7.unsafeFrom(uuid))
        }(_.toUUID)

      val actual = summon[PlainCodec[UuidV7]]

      Result.all(
        List(
          actual.encode(input) ==== expected.encode(input),
          actual.decode(uuid) ==== expected.decode(uuid),
          actual.schema.schemaType ==== expected.schema.schemaType,
          actual.schema.format ==== expected.schema.format,
          actual.schema.isOptional ==== expected.schema.isOptional,
          actual.schema.description ==== expected.schema.description,
          actual.schema.default ==== expected.schema.default,
          actual.schema.encodedExample ==== expected.schema.encodedExample,
          actual.schema.deprecated ==== expected.schema.deprecated,
          actual.schema.validator(input) ==== expected.schema.validator(input),
          actual.format ==== expected.format,
        )
      )
    }

}
object stringsSpec extends Properties, stringsSpec {

  override protected object stringsTypeClasses extends refined4s.modules.tapir.derivation.types.strings

  override def tests: List[Test] = allTests

}
