package refined4s.modules.chimney.derivation.types

import hedgehog.*
import hedgehog.extra.refined4s.gens.StringGens
import hedgehog.runner.*
import io.scalaland.chimney
import io.scalaland.chimney.dsl.*
import refined4s.types.UuidV7TestTools
import refined4s.types.numeric.PosInt
import refined4s.types.strings.*

import java.util.UUID

/** @author Kevin Lee
  * @since 2024-08-06
  */
trait stringsSpec extends uuidV7Spec {

  protected val stringsTypeClasses: refined4s.modules.chimney.derivation.types.strings

  import stringsTypeClasses.given

  override def allTests: List[Test] = List(
    // NonEmptyString
    property("test from NonEmptyString to String", testFromNonEmptyString),
    property("test from String to NonEmptyString", testToNonEmptyString),
    // NonBlankString
    property("test from NonBlankString to String", testFromNonBlankString),
    property("test from String to NonBlankString", testToNonBlankString),
    // Uuid
    property("test from Uuid to String", testFromUuid),
    property("test from String to Uuid", testToUuid),
  ) ++ super[uuidV7Spec].allTests

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

      val actual = input.intoPartial[Uuid].transform

      actual ==== expected
    }

}
object stringsSpec extends Properties, stringsSpec {

  override protected object stringsTypeClasses extends refined4s.modules.chimney.derivation.types.strings

  override def tests: List[Test] = allTests

}
trait uuidV7Spec {

  protected val stringsTypeClasses: refined4s.modules.chimney.derivation.types.strings

  import stringsTypeClasses.given

  def allTests: List[Test] = List(
    property("test from UuidV7 to String", testFromUuidV7),
    property("test from String to UuidV7", testToUuidV7),
    property("test from UuidV7 to UUID", testFromUuidV7ToJavaUuid),
    property("test from UUID to UuidV7", testFromJavaUuidToUuidV7),
  )

  def testFromUuidV7: Property =
    for {
      uuid <- Gen.elementUnsafe(UuidV7TestTools.validUuidV7Strings).log("uuid")
    } yield {
      val input = UuidV7.unsafeFromString(uuid)

      val expected = uuid
      val actual   = input.into[String].transform

      actual ==== expected
    }

  def testToUuidV7: Property =
    for {
      uuid <- Gen.elementUnsafe(UuidV7TestTools.validUuidV7Strings).log("uuid")
    } yield {
      val input = uuid

      val expected = chimney.partial.Result.fromEitherString(UuidV7.fromString(uuid))
      val actual   = input.intoPartial[UuidV7].transform

      actual ==== expected
    }

  def testFromUuidV7ToJavaUuid: Property =
    for {
      uuid <- Gen.elementUnsafe(UuidV7TestTools.validUuidV7Strings).log("uuid")
    } yield {
      val input = UuidV7.unsafeFromString(uuid)

      val expected = UUID.fromString(uuid)
      val actual   = input.into[UUID].transform

      actual ==== expected
    }

  def testFromJavaUuidToUuidV7: Property =
    for {
      uuid <- Gen.elementUnsafe(UuidV7TestTools.validUuidV7Strings).log("uuid")
    } yield {
      val input = UUID.fromString(uuid)

      val expected = chimney.partial.Result.fromEitherString(UuidV7.fromString(uuid))
      val actual   = input.intoPartial[UuidV7].transform

      actual ==== expected
    }

}
