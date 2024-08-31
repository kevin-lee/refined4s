package refined4s.modules.circe.derivation.generic

import cats.syntax.all.*
import hedgehog.*
import hedgehog.runner.*
import io.circe.*
import io.circe.parser.*
import io.circe.syntax.*
import refined4s.*

/** @author Kevin Lee
  * @since 2024-08-24
  */

object CustomTypeSpec {

  import refined4s.modules.circe.derivation.generic.auto.given

  def allTests: List[Test] = List(
    property("test CirceEncoder for Newtype", testNewtypeEncoder),
    property("test CirceKeyEncoder for Newtype", testNewtypeKeyEncoder),
    //
    property("test CirceEncoder for Refined", testRefinedEncoder),
    property("test CirceKeyEncoder for Refined", testRefinedKeyEncoder),
    //
    property("test CirceEncoder for Newtype(Refined)", testRefinedNewtypeEncoder),
    property("test CirceKeyEncoder for Newtype(Refined)", testRefinedNewtypeKeyEncoder),
    //
    property("test CirceEncoder for InlinedRefined", testInlinedRefinedEncoder),
    property("test CirceKeyEncoder for InlinedRefined", testInlinedRefinedKeyEncoder),
    //
    property("test CirceEncoder for Newtype(InlinedRefined)", testInlinedRefinedNewtypeEncoder),
    property("test CirceKeyEncoder for Newtype(InlinedRefined)", testInlinedRefinedNewtypeKeyEncoder),
    //
    property("test CirceDecoder for Newtype", testNewtypeDecoder),
    property("test CirceKeyDecoder for Newtype", testNewtypeKeyDecoder),
    //
    property("test CirceDecoder for Refined", testRefinedDecoder),
    property("test CirceKeyDecoder for Refined", testRefinedKeyDecoder),
    //
    example("test CirceDecoder for Refined with invalid value", testRefinedDecoderInvalid),
    example("test CirceKeyDecoder for Refined with invalid value", testRefinedKeyDecoderInvalid),
    //
    property("test CirceDecoder for Newtype(Refined)", testRefinedNewtypeDecoder),
    property("test CirceKeyDecoder for Newtype(Refined)", testRefinedNewtypeKeyDecoder),
    //
    example("test CirceDecoder for Newtype(Refined) with invalid value", testRefinedNewtypeDecoderInvalid),
    example("test CirceKeyDecoder for Newtype(Refined) with invalid value", testRefinedNewtypeKeyDecoderInvalid),
    //
    property("test CirceDecoder for InlinedRefined", testInlinedRefinedDecoder),
    property("test CirceKeyDecoder for InlinedKeyRefined", testInlinedRefinedKeyDecoder),
    //
    example("test CirceDecoder for InlinedRefined with invalid value", testInlinedRefinedDecoderInvalid),
    example("test CirceKeyDecoder for InlinedRefined with invalid value", testInlinedRefinedKeyDecoderInvalid),
    //
    property("test CirceDecoder for Newtype(InlinedRefined)", testInlinedRefinedNewtypeDecoder),
    property("test CirceKeyDecoder for Newtype(InlinedRefined)", testInlinedRefinedNewtypeKeyDecoder),
    //
    example("test CirceDecoder for Newtype(InlinedRefined) with invalid value", testInlinedRefinedNewtypeDecoderInvalid),
    example("test CirceKeyDecoder for Newtype(InlinedRefined) with invalid value", testInlinedRefinedNewtypeKeyDecoderInvalid),
    //
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

  def testNewtypeKeyEncoder: Property =
    for {
      ss    <- Gen.string(Gen.unicode, Range.linear(1, 10)).list(Range.linear(1, 10)).log("ss")
      ns2   <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(ss.length)).log("ns2")
      map   <- Gen.constant(ss.zip(ns2).toMap).log("map")
      input <- Gen.constant(map.map { case (key, value) => MyNewtype(key) -> value }).log("input")
    } yield {
      val expected = Json.fromFields(map.map { case (key, value) => key -> value.asJson })

      val actual = input.asJson
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

  def testRefinedKeyEncoder: Property =
    for {
      ss    <- Gen.string(Gen.unicode, Range.linear(1, 10)).list(Range.linear(1, 10)).log("ss")
      ns2   <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(ss.length)).log("ns2")
      map   <- Gen.constant(ss.zip(ns2).toMap).log("map")
      input <- Gen.constant(map.map { case (key, value) => MyRefinedType.unsafeFrom(key) -> value }).log("input")
    } yield {
      val expected = Json.fromFields(map.map { case (key, value) => key -> value.asJson })

      val actual = input.asJson
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

  def testRefinedNewtypeKeyEncoder: Property =
    for {
      ss    <- Gen.string(Gen.unicode, Range.linear(1, 10)).list(Range.linear(1, 10)).log("ss")
      ns2   <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(ss.length)).log("ns2")
      map   <- Gen.constant(ss.zip(ns2).toMap).log("map")
      input <- Gen.constant(map.map { case (key, value) => MyRefinedNewtype(MyRefinedType.unsafeFrom(key)) -> value }).log("input")
    } yield {
      val expected = Json.fromFields(map.map { case (key, value) => key -> value.asJson })

      val actual = input.asJson
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

  def testInlinedRefinedKeyEncoder: Property =
    for {
      ss    <- Gen.string(Gen.unicode, Range.linear(1, 10)).list(Range.linear(1, 10)).log("ss")
      ns2   <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(ss.length)).log("ns2")
      map   <- Gen.constant(ss.zip(ns2).toMap).log("map")
      input <- Gen.constant(map.map { case (key, value) => MyInlinedRefinedType.unsafeFrom(key) -> value }).log("input")
    } yield {
      val expected = Json.fromFields(map.map { case (key, value) => key -> value.asJson })

      val actual = input.asJson
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

  def testInlinedRefinedNewtypeKeyEncoder: Property =
    for {
      ss    <- Gen.string(Gen.unicode, Range.linear(1, 10)).list(Range.linear(1, 10)).log("ss")
      ns2   <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(ss.length)).log("ns2")
      map   <- Gen.constant(ss.zip(ns2).toMap).log("map")
      input <-
        Gen.constant(map.map { case (key, value) => MyInlinedRefinedNewtype(MyInlinedRefinedType.unsafeFrom(key)) -> value }).log("input")
    } yield {
      val expected = Json.fromFields(map.map { case (key, value) => key -> value.asJson })

      val actual = input.asJson
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

  def testNewtypeKeyDecoder: Property =
    for {
      ss       <- Gen.string(Gen.unicode, Range.linear(1, 10)).list(Range.linear(1, 10)).log("ss")
      ns2      <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(ss.length)).log("ns2")
      map      <- Gen.constant(ss.zip(ns2).toMap).log("map")
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

  def testRefinedKeyDecoder: Property =
    for {
      ss       <- Gen.string(Gen.unicode, Range.linear(1, 10)).list(Range.linear(1, 10)).log("ss")
      ns2      <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(ss.length)).log("ns2")
      map      <- Gen.constant(ss.zip(ns2).toMap).log("map")
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
    val expected = MyRefinedType.from("").leftMap(io.circe.DecodingFailure(_, List.empty))
    val input    = "".asJson
    val actual   = decode[MyRefinedType](input.noSpaces)
    Result.all(
      List(
        actual ==== expected
      )
    )
  }

  def testRefinedKeyDecoderInvalid: Result = {
    val expected = io.circe.DecodingFailure("Couldn't decode key.", List(CursorOp.DownField(""))).asLeft[Map[MyRefinedType, Int]]
    val input    = Json.fromFields(List("" -> 123.asJson))
    val actual   = decode[Map[MyRefinedType, Int]](input.noSpaces)
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

  def testRefinedNewtypeKeyDecoder: Property =
    for {
      ss       <- Gen.string(Gen.unicode, Range.linear(1, 10)).list(Range.linear(1, 10)).log("ss")
      ns2      <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(ss.length)).log("ns2")
      map      <- Gen.constant(ss.zip(ns2).toMap).log("map")
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

  def testRefinedNewtypeKeyDecoderInvalid: Result = {
    val expected = io.circe.DecodingFailure("Couldn't decode key.", List(CursorOp.DownField(""))).asLeft[Map[MyRefinedType, Int]]
    val input    = Json.fromFields(List("" -> 123.asJson))
    val actual   = decode[Map[MyRefinedNewtype, Int]](input.noSpaces)
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

  def testInlinedRefinedKeyDecoder: Property =
    for {
      ss       <- Gen.string(Gen.unicode, Range.linear(1, 10)).list(Range.linear(1, 10)).log("ss")
      ns2      <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(ss.length)).log("ns2")
      map      <- Gen.constant(ss.zip(ns2).toMap).log("map")
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
    val expected = MyInlinedRefinedType.from("").leftMap(io.circe.DecodingFailure(_, List.empty))
    val input    = "".asJson
    val actual   = decode[MyInlinedRefinedType](input.noSpaces)
    Result.all(
      List(
        actual ==== expected
      )
    )
  }

  def testInlinedRefinedKeyDecoderInvalid: Result = {
    val expected = io.circe.DecodingFailure("Couldn't decode key.", List(CursorOp.DownField(""))).asLeft[Map[MyInlinedRefinedType, Int]]
    val input    = Json.fromFields(List("" -> 123.asJson))

    val actual = decode[Map[MyInlinedRefinedType, Int]](input.noSpaces)
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

  def testInlinedRefinedNewtypeKeyDecoder: Property =
    for {
      ss       <- Gen.string(Gen.unicode, Range.linear(1, 10)).list(Range.linear(1, 10)).log("ss")
      ns2      <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(ss.length)).log("ns2")
      map      <- Gen.constant(ss.zip(ns2).toMap).log("map")
      expected <- Gen
                    .constant(map.map {
                      case (key, value) =>
                        MyInlinedRefinedNewtype(MyInlinedRefinedType.unsafeFrom(key)) -> value
                    })
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

  def testInlinedRefinedNewtypeKeyDecoderInvalid: Result = {
    val expected = io.circe.DecodingFailure("Couldn't decode key.", List(CursorOp.DownField(""))).asLeft[Map[MyInlinedRefinedNewtype, Int]]
    val input    = Json.fromFields(List("" -> 123.asJson))

    val actual = decode[Map[MyInlinedRefinedNewtype, Int]](input.noSpaces)
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
