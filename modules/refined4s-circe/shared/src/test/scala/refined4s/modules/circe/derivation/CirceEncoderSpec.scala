package refined4s.modules.circe.derivation

import refined4s.*

import hedgehog.*
import hedgehog.runner.*

import io.circe.*
import io.circe.syntax.*

/** @author Kevin Lee
  * @since 2023-12-11
  */
object CirceEncoderSpec extends Properties {
  override def tests: List[Test] = List(
    property("test CirceEncoder for Newtype", testNewtypeEncoder),
    property("test CirceEncoder for Refined", testRefinedEncoder),
    property("test CirceEncoder for Newtype(Refined)", testRefinedNewtypeEncoder),
    property("test CirceEncoder for InlinedRefined", testInlinedRefinedEncoder),
    property("test CirceEncoder for Newtype(InlinedRefined)", testInlinedRefinedNewtypeEncoder),
  )

  def testNewtypeEncoder: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
    } yield {
      val input    = MyNewtype(s)
      val expected = Json.fromString(s)
      val actual   = input.asJson
      Result.all(
        List(
          actual ==== expected,
          actual.noSpaces ==== expected.noSpaces,
        )
      )
    }

  def testRefinedEncoder: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
    } yield {
      val input    = MyRefinedType.unsafeFrom(s)
      val expected = Json.fromString(s)
      val actual   = input.asJson
      Result.all(
        List(
          actual ==== expected,
          actual.noSpaces ==== expected.noSpaces,
        )
      )
    }

  def testRefinedNewtypeEncoder: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
    } yield {
      val input    = MyRefinedNewtype(MyRefinedType.unsafeFrom(s))
      val expected = Json.fromString(s)
      val actual   = input.asJson
      Result.all(
        List(
          actual ==== expected,
          actual.noSpaces ==== expected.noSpaces,
        )
      )
    }

  def testInlinedRefinedEncoder: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
    } yield {
      val input    = MyInlinedRefinedType.unsafeFrom(s)
      val expected = Json.fromString(s)
      val actual   = input.asJson
      Result.all(
        List(
          actual ==== expected,
          actual.noSpaces ==== expected.noSpaces,
        )
      )
    }

  def testInlinedRefinedNewtypeEncoder: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
    } yield {
      val input    = MyInlinedRefinedNewtype(MyInlinedRefinedType.unsafeFrom(s))
      val expected = Json.fromString(s)
      val actual   = input.asJson
      Result.all(
        List(
          actual ==== expected,
          actual.noSpaces ==== expected.noSpaces,
        )
      )
    }

  type MyNewtype = MyNewtype.Type
  object MyNewtype extends Newtype[String] with CirceEncoder[String]

  type MyRefinedType = MyRefinedType.Type
  @SuppressWarnings(Array("org.wartremover.warts.Equals"))
  object MyRefinedType extends Refined[String] with CirceEncoder[String] {
    override inline def invalidReason(a: String): String =
      "It has to be a non-empty String but got \"" + a + "\""

    override inline def predicate(a: String): Boolean = a != ""
  }

  type MyRefinedNewtype = MyRefinedNewtype.Type
  object MyRefinedNewtype extends Newtype[MyRefinedType] with CirceEncoder[MyRefinedType]

  type MyInlinedRefinedType = MyInlinedRefinedType.Type
  @SuppressWarnings(Array("org.wartremover.warts.Equals"))
  object MyInlinedRefinedType extends InlinedRefined[String] with CirceEncoder[String] {

    override inline val inlinedExpectedValue = "a non-empty String"

    override inline def invalidReason(a: String): String =
      "It has to be a non-empty String but got \"" + a + "\""

    override inline def predicate(a: String): Boolean = a != ""

    override inline def inlinedPredicate(inline a: String): Boolean = a != ""
  }

  type MyInlinedRefinedNewtype = MyInlinedRefinedNewtype.Type
  object MyInlinedRefinedNewtype extends Newtype[MyInlinedRefinedType] with CirceEncoder[MyInlinedRefinedType]

}
