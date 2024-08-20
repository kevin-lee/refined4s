package refined4s.modules.circe.derivation

import cats.syntax.all.*
import hedgehog.*
import hedgehog.runner.*
import io.circe.*
import io.circe.CursorOp.DownField
import io.circe.parser.*
import io.circe.syntax.*
import refined4s.*

/** @author Kevin Lee
  * @since 2023-12-11
  */
object CirceKeyDecoderSpec extends Properties {
  override def tests: List[Test] = List(
    property("test Circe*KeyDecoder for Newtype", testNewtypeDecoder),
    property("test Circe*KeyDecoder for Refined", testRefinedDecoder),
    example("test Circe*KeyDecoder for Refined with invalid value", testRefinedDecoderInvalid),
    property("test Circe*KeyDecoder for Newtype(Refined)", testRefinedNewtypeDecoder),
    example("test Circe*KeyDecoder for Newtype(Refined) with invalid value", testRefinedNewtypeDecoderInvalid),
    property("test Circe*KeyDecoder for InlinedRefined", testInlinedRefinedDecoder),
    example("test Circe*KeyDecoder for InlinedRefined with invalid value", testInlinedRefinedDecoderInvalid),
    property("test Circe*KeyDecoder for Newtype(InlinedRefined)", testInlinedRefinedNewtypeDecoder),
    example("test Circe*KeyDecoder for Newtype(InlinedRefined) with invalid value", testInlinedRefinedNewtypeDecoderInvalid),
  )

  def testNewtypeDecoder: Property =
    for {
      ss       <- Gen
                    .string(Gen.unicode, Range.linear(1, 10))
                    .list(Range.linear(1, 10))
                    .log("ss")
      ns       <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(ss.length)).log("ns")
      map      <- Gen.constant(ss.zip(ns).toMap).log("map")
      expected <- Gen.constant(map.map { case (key, value) => MyNewtype(key) -> value }).log("expected")
    } yield {
      val input  = Json.fromFields(map.map { case (key, value) => key -> value.asJson })
      val actual = decode[Map[MyNewtype, Int]](input.noSpaces)
      Result.all(
        List(
          actual ==== expected.asRight
        )
      )
    }

  def testRefinedDecoder: Property =
    for {
      ss       <- Gen
                    .string(Gen.unicode, Range.linear(1, 10))
                    .list(Range.linear(1, 10))
                    .log("ss")
      ns       <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(ss.length)).log("ns")
      map      <- Gen.constant(ss.zip(ns).toMap).log("map")
      expected <- Gen.constant(map.map { case (key, value) => MyRefinedType.unsafeFrom(key) -> value }).log("expected")
    } yield {
      val input  = Json.fromFields(map.map { case (key, value) => key -> value.asJson })
      val actual = decode[Map[MyRefinedType, Int]](input.noSpaces)
      Result.all(
        List(
          actual ==== expected.asRight
        )
      )
    }

  def testRefinedDecoderInvalid: Result = {
    val expected = MyRefinedType.from("").leftMap(_ => io.circe.DecodingFailure("Couldn't decode key.", List(DownField(""))))
    val input    = Json.fromFields(Map("" -> 1.asJson))
    val actual   = decode[Map[MyRefinedType, Int]](input.noSpaces)
    Result.all(
      List(
        actual ==== expected
      )
    )
  }

  def testRefinedNewtypeDecoder: Property =
    for {
      ss       <- Gen
                    .string(Gen.unicode, Range.linear(1, 10))
                    .list(Range.linear(1, 10))
                    .log("ss")
      ns       <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(ss.length)).log("ns")
      map      <- Gen.constant(ss.zip(ns).toMap).log("map")
      expected <- Gen.constant(map.map { case (key, value) => MyRefinedNewtype(MyRefinedType.unsafeFrom(key)) -> value }).log("expected")
    } yield {
      val input  = Json.fromFields(map.map { case (key, value) => key -> value.asJson })
      val actual = decode[Map[MyRefinedNewtype, Int]](input.noSpaces)
      Result.all(
        List(
          actual ==== expected.asRight
        )
      )
    }

  def testRefinedNewtypeDecoderInvalid: Result = {
    val expected =
      MyRefinedType
        .from("")
        .leftMap(_ => io.circe.DecodingFailure("Couldn't decode key.", List(DownField(""))))
        .map(MyRefinedNewtype(_))
    val input    = Json.fromFields(Map("" -> 1.asJson))
    val actual   = decode[Map[MyRefinedNewtype, Int]](input.noSpaces)
    Result.all(
      List(
        actual ==== expected
      )
    )
  }

  def testInlinedRefinedDecoder: Property =
    for {
      ss       <- Gen
                    .string(Gen.unicode, Range.linear(1, 10))
                    .list(Range.linear(1, 10))
                    .log("ss")
      ns       <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(ss.length)).log("ns")
      map      <- Gen.constant(ss.zip(ns).toMap).log("map")
      expected <- Gen.constant(map.map { case (key, value) => MyInlinedRefinedType.unsafeFrom(key) -> value }).log("expected")
    } yield {
      val input  = Json.fromFields(map.map { case (key, value) => key -> value.asJson })
      val actual = decode[Map[MyInlinedRefinedType, Int]](input.noSpaces)
      Result.all(
        List(
          actual ==== expected.asRight
        )
      )
    }

  def testInlinedRefinedDecoderInvalid: Result = {
    val expected =
      MyInlinedRefinedType
        .from("")
        .leftMap(_ => io.circe.DecodingFailure("Couldn't decode key.", List(DownField(""))))
    val input    = Json.fromFields(Map("" -> 1.asJson))
    val actual   = decode[Map[MyInlinedRefinedType, Int]](input.noSpaces)
    Result.all(
      List(
        actual ==== expected
      )
    )
  }

  def testInlinedRefinedNewtypeDecoder: Property =
    for {
      ss       <- Gen
                    .string(Gen.unicode, Range.linear(1, 10))
                    .list(Range.linear(1, 10))
                    .log("ss")
      ns       <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(ss.length)).log("ns")
      map      <- Gen.constant(ss.zip(ns).toMap).log("map")
      expected <- Gen
                    .constant(map.map { case (key, value) => MyInlinedRefinedNewtype(MyInlinedRefinedType.unsafeFrom(key)) -> value })
                    .log("expected")
    } yield {
      val input  = Json.fromFields(map.map { case (key, value) => key -> value.asJson })
      val actual = decode[Map[MyInlinedRefinedNewtype, Int]](input.noSpaces)
      Result.all(
        List(
          actual ==== expected.asRight
        )
      )
    }

  def testInlinedRefinedNewtypeDecoderInvalid: Result = {
    val expected = MyInlinedRefinedType
      .from("")
      .leftMap(_ => io.circe.DecodingFailure("Couldn't decode key.", List(DownField(""))))
      .map(MyInlinedRefinedNewtype(_))
    val input    = Json.fromFields(Map("" -> 1.asJson))
    val actual   = decode[Map[MyInlinedRefinedNewtype, Int]](input.noSpaces)
    Result.all(
      List(
        actual ==== expected
      )
    )
  }

  type MyNewtype = MyNewtype.Type
  object MyNewtype extends Newtype[String], CirceNewtypeKeyDecoder[String]

  type MyRefinedType = MyRefinedType.Type
  @SuppressWarnings(Array("org.wartremover.warts.Equals"))
  object MyRefinedType extends Refined[String], CirceRefinedKeyDecoder[String], CirceRefinedDecoder[String] {
    override inline def invalidReason(a: String): String =
      "It has to be a non-empty String but got \"" + a + "\""

    override inline def predicate(a: String): Boolean = a != ""
  }

  type MyRefinedNewtype = MyRefinedNewtype.Type
  object MyRefinedNewtype extends Newtype[MyRefinedType], CirceNewtypeKeyDecoder[MyRefinedType]

  type MyInlinedRefinedType = MyInlinedRefinedType.Type
  @SuppressWarnings(Array("org.wartremover.warts.Equals"))
  object MyInlinedRefinedType extends InlinedRefined[String], CirceRefinedKeyDecoder[String], CirceRefinedDecoder[String] {

    override inline val inlinedExpectedValue = "a non-empty String"

    override inline def invalidReason(a: String): String =
      "It has to be a non-empty String but got \"" + a + "\""

    override inline def predicate(a: String): Boolean = a != ""

    override inline def inlinedPredicate(inline a: String): Boolean = a != ""
  }

  type MyInlinedRefinedNewtype = MyInlinedRefinedNewtype.Type
  object MyInlinedRefinedNewtype
      extends Newtype[MyInlinedRefinedType],
        CirceNewtypeKeyDecoder[MyInlinedRefinedType],
        CirceNewtypeDecoder[MyInlinedRefinedType]

}
