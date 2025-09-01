package refined4s.modules.extras.derivation.types

import extras.render.syntax.*
import hedgehog.*
import hedgehog.runner.*
import refined4s.types.all.{NonBlankString, NonEmptyString, Uuid}

import java.nio.charset.StandardCharsets
import java.util.UUID

trait stringsSpec {

  protected val stringsTypeClasses: refined4s.modules.extras.derivation.types.strings
  import stringsTypeClasses.given

  def allTests: List[Test] = List(
    //
    property("test Render[NonEmptyString]", testRenderNonEmptyString),
    //
    property("test Render[NonBlankString]", testRenderNonBlankString),
    //
    property("test Render[Uuid]", testRenderUuid),
  )

  def testRenderNonEmptyString: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
    } yield {
      val input = NonEmptyString.unsafeFrom(s)

      val expected = s
      val actual   = input.render

      actual ==== expected
    }

  def testRenderNonBlankString: Property =
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

      val expected = s
      val actual   = input.render

      actual ==== expected
    }

  def testRenderUuid: Property =
    for {
      uuid <- Gen.constant(UUID.randomUUID()).log("uuid")
    } yield {
      val input = Uuid(uuid)

      val expected = uuid.toString
      val actual   = input.render

      actual ==== expected
    }

}
object stringsSpec extends Properties, stringsSpec {

  override protected object stringsTypeClasses extends refined4s.modules.extras.derivation.types.strings

  override def tests: List[Test] = allTests

}
