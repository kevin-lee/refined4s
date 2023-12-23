package refined4s.modules.circe.derivation

import cats.syntax.all.*

import io.circe.*
import io.circe.syntax.*
import io.circe.parser.*

import refined4s.*
import refined4s.modules.circe.derivation.instances.given

import hedgehog.*
import hedgehog.runner.*

/** @author Kevin Lee
  * @since 2023-12-11
  */
object instancesSpec extends Properties {
  override def tests: List[Test] = List(
    property("test CirceEncoder for Newtype", testNewtypeEncoder),
    property("test CirceEncoder for Refined", testRefinedEncoder),
    property("test CirceEncoder for Newtype(Refined)", testRefinedNewtypeEncoder),
    property("test CirceEncoder for InlinedRefined", testInlinedRefinedEncoder),
    property("test CirceEncoder for Newtype(InlinedRefined)", testInlinedRefinedNewtypeEncoder),
    //
    property("test CirceDecoder for Newtype", testNewtypeDecoder),
    property("test CirceDecoder for Refined", testRefinedDecoder),
    example("test CirceDecoder for Refined with invalid value", testRefinedDecoderInvalid),
    property("test CirceDecoder for Newtype(Refined)", testRefinedNewtypeDecoder),
    example("test CirceDecoder for Newtype(Refined) with invalid value", testRefinedNewtypeDecoderInvalid),
    property("test CirceDecoder for InlinedRefined", testInlinedRefinedDecoder),
    example("test CirceDecoder for InlinedRefined with invalid value", testInlinedRefinedDecoderInvalid),
    property("test CirceDecoder for Newtype(InlinedRefined)", testInlinedRefinedNewtypeDecoder),
    example("test CirceDecoder for Newtype(InlinedRefined) with invalid value", testInlinedRefinedNewtypeDecoderInvalid),
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

  def testNewtypeDecoder: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
    } yield {
      val expected = MyNewtype(s).asRight[String]
      val input    = s.asJson
      val actual   = decode[MyNewtype](input.noSpaces)
      Result.all(
        List(
          actual ==== expected
        )
      )
    }

  def testRefinedDecoder: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
    } yield {
      val expected = MyRefinedType.from(s)
      val input    = s.asJson
      val actual   = decode[MyRefinedType](input.noSpaces)
      Result.all(
        List(
          actual ==== expected
        )
      )
    }

  def testRefinedDecoderInvalid: Result = {
    val expected = MyRefinedType.from("").leftMap(io.circe.DecodingFailure(_, List.empty))
    val input    = "".asJson
    val actual   = decode[MyRefinedType](input.noSpaces)
    Result.all(
      List(
        actual ==== expected
      )
    )
  }

  def testRefinedNewtypeDecoder: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
    } yield {
      val expected = MyRefinedType.from(s).map(MyRefinedNewtype(_))
      val input    = s.asJson
      val actual   = decode[MyRefinedNewtype](input.noSpaces)
      Result.all(
        List(
          actual ==== expected
        )
      )
    }

  def testRefinedNewtypeDecoderInvalid: Result = {
    val expected = MyRefinedType
      .from("")
      .leftMap(io.circe.DecodingFailure(_, List.empty))
      .map(MyRefinedNewtype(_))
    val input    = "".asJson
    val actual   = decode[MyRefinedNewtype](input.noSpaces)
    Result.all(
      List(
        actual ==== expected
      )
    )
  }

  def testInlinedRefinedDecoder: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
    } yield {
      val expected = MyInlinedRefinedType.from(s)
      val input    = s.asJson
      val actual   = decode[MyInlinedRefinedType](input.noSpaces)
      Result.all(
        List(
          actual ==== expected
        )
      )
    }

  def testInlinedRefinedDecoderInvalid: Result = {
    val expected = MyInlinedRefinedType.from("").leftMap(io.circe.DecodingFailure(_, List.empty))
    val input    = "".asJson
    val actual   = decode[MyInlinedRefinedType](input.noSpaces)
    Result.all(
      List(
        actual ==== expected
      )
    )
  }

  def testInlinedRefinedNewtypeDecoder: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
    } yield {
      val expected = MyInlinedRefinedType.from(s).map(MyInlinedRefinedNewtype(_))
      val input    = s.asJson
      val actual   = decode[MyInlinedRefinedNewtype](input.noSpaces)
      Result.all(
        List(
          actual ==== expected
        )
      )
    }

  def testInlinedRefinedNewtypeDecoderInvalid: Result = {
    val expected = MyInlinedRefinedType
      .from("")
      .leftMap(io.circe.DecodingFailure(_, List.empty))
      .map(MyInlinedRefinedNewtype(_))
    val input    = "".asJson
    val actual   = decode[MyInlinedRefinedNewtype](input.noSpaces)
    Result.all(
      List(
        actual ==== expected
      )
    )
  }

  type MyNewtype = MyNewtype.Type
  object MyNewtype extends Newtype[String]

  type MyRefinedType = MyRefinedType.Type
  @SuppressWarnings(Array("org.wartremover.warts.Equals"))
  object MyRefinedType extends Refined[String] {
    override inline def invalidReason(a: String): String =
      "It has to be a non-empty String but got \"" + a + "\""

    override inline def predicate(a: String): Boolean = a != ""
  }

  type MyRefinedNewtype = MyRefinedNewtype.Type
  object MyRefinedNewtype extends Newtype[MyRefinedType]

  type MyInlinedRefinedType = MyInlinedRefinedType.Type
  @SuppressWarnings(Array("org.wartremover.warts.Equals"))
  object MyInlinedRefinedType extends InlinedRefined[String] {

    override inline val inlinedExpectedValue             = "a non-empty String"
    override inline def invalidReason(a: String): String =
      "It has to be a non-empty String but got \"" + a + "\""

    override inline def predicate(a: String): Boolean = a != ""

    override inline def inlinedPredicate(inline a: String): Boolean = a != ""
  }

  type MyInlinedRefinedNewtype = MyInlinedRefinedNewtype.Type
  object MyInlinedRefinedNewtype extends Newtype[MyInlinedRefinedType] // with CirceEncoder[MyInlinedRefinedType]

}
