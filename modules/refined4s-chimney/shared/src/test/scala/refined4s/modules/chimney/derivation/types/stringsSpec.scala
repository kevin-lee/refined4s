package refined4s.modules.chimney.derivation.types

import hedgehog.*
import hedgehog.extra.refined4s.gens.StringGens
import hedgehog.runner.*
import io.scalaland.chimney
import io.scalaland.chimney.dsl.*
import refined4s.types.numeric.PosInt
import refined4s.types.strings.*

/** @author Kevin Lee
  * @since 2024-08-06
  */
object stringsSpec extends Properties {
  override def tests: List[Test] = List(
    // NonEmptyString
    property("test from NonEmptyString to String", testFromNonEmptyString),
    property("test from String to NonEmptyString", testToNonEmptyString),
    // NonBlankString
    property("test from NonBlankString to String", testFromNonBlankString),
    property("test from String to NonBlankString", testToNonBlankString),
    // Uuid
    property("test from Uuid to String", testFromUuid),
    property("test from String to Uuid", testToUuid),
  )

  def testFromNonEmptyString: Property =
    for {
      s <- StringGens.genNonEmptyString(Gen.alphaNum, PosInt(10)).log("s")
    } yield {
      val input = s

      val expected = s.value
      val actual   = input.into[String].transform

      actual ==== expected
    }

  def testToNonEmptyString: Property =
    for {
      s <- StringGens.genNonEmptyString(Gen.alphaNum, PosInt(10)).log("s")
    } yield {
      val input = s.value

      val expected = chimney.partial.Result.fromValue(s)

      import refined4s.modules.chimney.derivation.types.strings.derivedStringToNonEmptyStringPartialTransformer
      val actual = input.intoPartial[NonEmptyString].transform

      actual ==== expected
    }

  //

  def testFromNonBlankString: Property =
    for {
      s <- StringGens.genNonBlankString(PosInt(10)).log("s")
    } yield {
      val input = s

      val expected = s.value
      val actual   = input.into[String].transform

      actual ==== expected
    }

  def testToNonBlankString: Property =
    for {
      s <- StringGens.genNonBlankString(PosInt(10)).log("s")
    } yield {
      val input = s.value

      val expected = chimney.partial.Result.fromValue(s)

      import refined4s.modules.chimney.derivation.types.strings.derivedStringToNonBlankStringPartialTransformer
      val actual = input.intoPartial[NonBlankString].transform

      actual ==== expected
    }

  //

  def testFromUuid: Property =
    for {
      uuid <- StringGens.genUuid.log("uuid")
    } yield {
      val input = uuid

      val expected = uuid.value
      val actual   = input.into[String].transform

      actual ==== expected
    }

  def testToUuid: Property =
    for {
      uuid <- StringGens.genUuid.log("uuid")
    } yield {
      val input = uuid.value

      val expected = chimney.partial.Result.fromValue(uuid)

      import refined4s.modules.chimney.derivation.types.strings.derivedStringToUuidPartialTransformer
      val actual = input.intoPartial[Uuid].transform

      actual ==== expected
    }

}
