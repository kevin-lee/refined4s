package refined4s.modules.circe.derivation.types

import hedgehog.*
import hedgehog.runner.*
import io.circe.*
import io.circe.parser.*
import io.circe.syntax.*
import refined4s.types.all.*

import java.util.UUID

/** @author Kevin Lee
  * @since 2024-08-23
  */
trait stringsSpec {

  protected val stringsTypeClasses: refined4s.modules.circe.derivation.types.strings

  import stringsTypeClasses.given

  def allTests: List[Test] = List(
    property("test Encoder[NonEmptyString]", testEncoderNonEmptyString),
    property("test Decoder[NonEmptyString]", testDecoderNonEmptyString),
    //
    property("test Encoder[NonBlankString]", testEncoderNonBlankString),
    property("test Decoder[NonBlankString]", testDecoderNonBlankString),
    //
    property("test Encoder[Uuid]", testEncoderUuid),
    property("test Decoder[Uuid]", testDecoderUuid),
    //
  )

  def testEncoderNonEmptyString: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
    } yield {
      val input = NonEmptyString.unsafeFrom(s)

      val expected = s.asJson
      val actual   = input.asJson

      Result.all(
        List(
          actual ==== expected,
          actual.noSpaces ==== expected.noSpaces,
        )
      )
    }

  def testDecoderNonEmptyString: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
    } yield {
      val input = s.asJson

      val expected = NonEmptyString.from(s)
      val actual   = decode[NonEmptyString](input.noSpaces)

      actual ==== expected
    }

  //

  def testEncoderNonBlankString: Property =
    for {
      nonWhitespaceString <- Gen.string(hedgehog.extra.Gens.genNonWhitespaceChar, Range.linear(1, 10)).log("nonWhitespaceString")
      whitespaceString    <- Gen
                               .string(
                                 hedgehog.extra.Gens.genCharByRange(refined4s.types.strings.WhitespaceCharRange),
                                 Range.linear(1, 10),
                               )
                               .log("whitespaceString")

      s <- Gen.constant(scala.util.Random.shuffle((nonWhitespaceString + whitespaceString).toList).mkString).log("s")
    } yield {
      val input = NonBlankString.unsafeFrom(s)

      val expected = s.asJson
      val actual   = input.asJson

      Result.all(
        List(
          actual ==== expected,
          actual.noSpaces ==== expected.noSpaces,
        )
      )
    }

  def testDecoderNonBlankString: Property =
    for {
      nonWhitespaceString <- Gen.string(hedgehog.extra.Gens.genNonWhitespaceChar, Range.linear(1, 10)).log("nonWhitespaceString")
      whitespaceString    <- Gen
                               .string(
                                 hedgehog.extra.Gens.genCharByRange(refined4s.types.strings.WhitespaceCharRange),
                                 Range.linear(1, 10),
                               )
                               .log("whitespaceString")

      s <- Gen.constant(scala.util.Random.shuffle((nonWhitespaceString + whitespaceString).toList).mkString).log("s")
    } yield {
      val input = s.asJson

      val expected = NonBlankString.from(s)
      val actual   = decode[NonBlankString](input.noSpaces)

      actual ==== expected
    }

  //

  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  def testEncoderUuid: Property =
    for {
      uuid <- Gen.constant(UUID.randomUUID()).log("uuid")
    } yield {
      val s     = uuid.toString
      val input = Uuid.unsafeFrom(s)

      val expected = uuid.asJson
      val actual   = input.asJson

      Result.all(
        List(
          actual ==== expected,
          actual.noSpaces ==== expected.noSpaces,
        )
      )
    }

  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  def testDecoderUuid: Property =
    for {
      uuid <- Gen.constant(UUID.randomUUID()).log("uuid")
    } yield {
      val s     = uuid.toString
      val input = s.asJson

      val expected = Uuid.from(s)
      val actual   = decode[Uuid](input.noSpaces)

      actual ==== expected
    }

  //
}
object stringsSpec extends Properties, stringsSpec {

  override protected val stringsTypeClasses: refined4s.modules.circe.derivation.types.strings =
    refined4s.modules.circe.derivation.types.strings

  override def tests: List[Test] = allTests

}
