package refined4s.modules.tapir.derivation.types

import hedgehog.*
import hedgehog.runner.*
import refined4s.types.strings.*
import sttp.tapir.{Schema, ValidationError}

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

}
object stringsSpec extends Properties, stringsSpec {

  override protected object stringsTypeClasses extends refined4s.modules.tapir.derivation.types.strings

  override def tests: List[Test] = allTests

}
