package refined4s.modules.circe.derivation.generic

import hedgehog.*
import hedgehog.runner.*
import cats.syntax.all.*
import io.circe.*
import io.circe.parser.*
import io.circe.syntax.*
import refined4s.modules.circe.derivation.types.NumericTestValues
import refined4s.types.all.*

/** @author Kevin Lee
  * @since 2024-08-23
  */
object numericSpec extends NumericTestValues {

  import refined4s.modules.circe.derivation.generic.auto.given

  def allTests: List[Test] = List(
    property("test Encoder[NegInt]", testEncoderNegInt),
    property("test Decoder[NegInt]", testDecoderNegInt),
    property("test KeyEncoder[NegInt]", testKeyEncoderNegInt),
    property("test KeyDecoder[NegInt]", testKeyDecoderNegInt),
    //
    property("test Encoder[NonNegInt]", testEncoderNonNegInt),
    property("test Decoder[NonNegInt]", testDecoderNonNegInt),
    property("test KeyEncoder[NonNegInt]", testKeyEncoderNonNegInt),
    property("test KeyDecoder[NonNegInt]", testKeyDecoderNonNegInt),
    //
    property("test Encoder[PosInt]", testEncoderPosInt),
    property("test Decoder[PosInt]", testDecoderPosInt),
    property("test KeyEncoder[PosInt]", testKeyEncoderPosInt),
    property("test KeyDecoder[PosInt]", testKeyDecoderPosInt),
    //
    property("test Encoder[NonPosInt]", testEncoderNonPosInt),
    property("test Decoder[NonPosInt]", testDecoderNonPosInt),
    property("test KeyEncoder[NonPosInt]", testKeyEncoderNonPosInt),
    property("test KeyDecoder[NonPosInt]", testKeyDecoderNonPosInt),
    //
    property("test Encoder[NegLong]", testEncoderNegLong),
    property("test Decoder[NegLong]", testDecoderNegLong),
    property("test KeyEncoder[NegLong]", testKeyEncoderNegLong),
    property("test KeyDecoder[NegLong]", testKeyDecoderNegLong),
    //
    property("test Encoder[NonNegLong]", testEncoderNonNegLong),
    property("test Decoder[NonNegLong]", testDecoderNonNegLong),
    property("test KeyEncoder[NonNegLong]", testKeyEncoderNonNegLong),
    property("test KeyDecoder[NonNegLong]", testKeyDecoderNonNegLong),
    //
    property("test Encoder[PosLong]", testEncoderPosLong),
    property("test Decoder[PosLong]", testDecoderPosLong),
    property("test KeyEncoder[PosLong]", testKeyEncoderPosLong),
    property("test KeyDecoder[PosLong]", testKeyDecoderPosLong),
    //
    property("test Encoder[NonPosLong]", testEncoderNonPosLong),
    property("test Decoder[NonPosLong]", testDecoderNonPosLong),
    property("test KeyEncoder[NonPosLong]", testKeyEncoderNonPosLong),
    property("test KeyDecoder[NonPosLong]", testKeyDecoderNonPosLong),
    //
    property("test Encoder[NegShort]", testEncoderNegShort),
    property("test Decoder[NegShort]", testDecoderNegShort),
    property("test KeyEncoder[NegShort]", testKeyEncoderNegShort),
    property("test KeyDecoder[NegShort]", testKeyDecoderNegShort),
    //
    property("test Encoder[NonNegShort]", testEncoderNonNegShort),
    property("test Decoder[NonNegShort]", testDecoderNonNegShort),
    property("test KeyEncoder[NonNegShort]", testKeyEncoderNonNegShort),
    property("test KeyDecoder[NonNegShort]", testKeyDecoderNonNegShort),
    //
    property("test Encoder[PosShort]", testEncoderPosShort),
    property("test Decoder[PosShort]", testDecoderPosShort),
    property("test KeyEncoder[PosShort]", testKeyEncoderPosShort),
    property("test KeyDecoder[PosShort]", testKeyDecoderPosShort),
    //
    property("test Encoder[NonPosShort]", testEncoderNonPosShort),
    property("test Decoder[NonPosShort]", testDecoderNonPosShort),
    property("test KeyEncoder[NonPosShort]", testKeyEncoderNonPosShort),
    property("test KeyDecoder[NonPosShort]", testKeyDecoderNonPosShort),
    //
    property("test Encoder[NegByte]", testEncoderNegByte),
    property("test Decoder[NegByte]", testDecoderNegByte),
    property("test KeyEncoder[NegByte]", testKeyEncoderNegByte),
    property("test KeyDecoder[NegByte]", testKeyDecoderNegByte),
    //
    property("test Encoder[NonNegByte]", testEncoderNonNegByte),
    property("test Decoder[NonNegByte]", testDecoderNonNegByte),
    property("test KeyEncoder[NonNegByte]", testKeyEncoderNonNegByte),
    property("test KeyDecoder[NonNegByte]", testKeyDecoderNonNegByte),
    //
    property("test Encoder[PosByte]", testEncoderPosByte),
    property("test Decoder[PosByte]", testDecoderPosByte),
    property("test KeyEncoder[PosByte]", testKeyEncoderPosByte),
    property("test KeyDecoder[PosByte]", testKeyDecoderPosByte),
    //
    property("test Encoder[NonPosByte]", testEncoderNonPosByte),
    property("test Decoder[NonPosByte]", testDecoderNonPosByte),
    property("test KeyEncoder[NonPosByte]", testKeyEncoderNonPosByte),
    property("test KeyDecoder[NonPosByte]", testKeyDecoderNonPosByte),
    //
    property("test Encoder[NegFloat]", testEncoderNegFloat),
    property("test Decoder[NegFloat]", testDecoderNegFloat),
    //
    property("test Encoder[NonNegFloat]", testEncoderNonNegFloat),
    property("test Decoder[NonNegFloat]", testDecoderNonNegFloat),
    //
    property("test Encoder[PosFloat]", testEncoderPosFloat),
    property("test Decoder[PosFloat]", testDecoderPosFloat),
    //
    property("test Encoder[NonPosFloat]", testEncoderNonPosFloat),
    property("test Decoder[NonPosFloat]", testDecoderNonPosFloat),
    //
    property("test Encoder[NegDouble]", testEncoderNegDouble),
    property("test Decoder[NegDouble]", testDecoderNegDouble),
    //
    property("test Encoder[NonNegDouble]", testEncoderNonNegDouble),
    property("test Decoder[NonNegDouble]", testDecoderNonNegDouble),
    //
    property("test Encoder[PosDouble]", testEncoderPosDouble),
    property("test Decoder[PosDouble]", testDecoderPosDouble),
    //
    property("test Encoder[NonPosDouble]", testEncoderNonPosDouble),
    property("test Decoder[NonPosDouble]", testDecoderNonPosDouble),
    //
    property("test Encoder[NegBigInt]", testEncoderNegBigInt),
    property("test Decoder[NegBigInt]", testDecoderNegBigInt),
    property("test KeyEncoder[NegBigInt]", testKeyEncoderNegBigInt),
    property("test KeyDecoder[NegBigInt]", testKeyDecoderNegBigInt),
    //
    property("test Encoder[NonNegBigInt]", testEncoderNonNegBigInt),
    property("test Decoder[NonNegBigInt]", testDecoderNonNegBigInt),
    property("test KeyEncoder[NonNegBigInt]", testKeyEncoderNonNegBigInt),
    property("test KeyDecoder[NonNegBigInt]", testKeyDecoderNonNegBigInt),
    //
    property("test Encoder[PosBigInt]", testEncoderPosBigInt),
    property("test Decoder[PosBigInt]", testDecoderPosBigInt),
    property("test KeyEncoder[PosBigInt]", testKeyEncoderPosBigInt),
    property("test KeyDecoder[PosBigInt]", testKeyDecoderPosBigInt),
    //
    property("test Encoder[NonPosBigInt]", testEncoderNonPosBigInt),
    property("test Decoder[NonPosBigInt]", testDecoderNonPosBigInt),
    property("test KeyEncoder[NonPosBigInt]", testKeyEncoderNonPosBigInt),
    property("test KeyDecoder[NonPosBigInt]", testKeyDecoderNonPosBigInt),
    //
    property("test Encoder[NegBigDecimal]", testEncoderNegBigDecimal),
    property("test Decoder[NegBigDecimal]", testDecoderNegBigDecimal),
    //
    property("test Encoder[NonNegBigDecimal]", testEncoderNonNegBigDecimal),
    property("test Decoder[NonNegBigDecimal]", testDecoderNonNegBigDecimal),
    //
    property("test Encoder[PosBigDecimal]", testEncoderPosBigDecimal),
    property("test Decoder[PosBigDecimal]", testDecoderPosBigDecimal),
    //
    property("test Encoder[NonPosBigDecimal]", testEncoderNonPosBigDecimal),
    property("test Decoder[NonPosBigDecimal]", testDecoderNonPosBigDecimal),
    //
  )

  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  given bigIntKeyEncoder: KeyEncoder[BigInt] = _.toString

  given bigIntKeyDecoder: KeyDecoder[BigInt] = s => scala.util.Try(BigInt(s)).toOption

  def testEncoderNegInt: Property =
    for {
      n <- Gen.int(Range.linear(-1, Int.MinValue)).log("n")
    } yield {
      val input = NegInt.unsafeFrom(n)

      val expected = n.asJson
      val actual   = input.asJson
      Result.all(
        List(
          actual ==== expected,
          actual.noSpaces ==== expected.noSpaces,
        )
      )
    }

  def testDecoderNegInt: Property =
    for {
      n <- Gen.int(Range.linear(-1, Int.MinValue)).log("n")
    } yield {
      val input = n.asJson

      val expected = NegInt.from(n)
      val actual   = decode[NegInt](input.noSpaces)
      actual ==== expected
    }

  def testKeyEncoderNegInt: Property =
    for {
      ns1   <- Gen.int(Range.linear(-1, Int.MinValue)).list(Range.linear(1, 10)).log("ns1")
      ns2   <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(ns1.length)).log("ns2")
      map   <- Gen.constant(ns1.zip(ns2).toMap).log("map")
      input <- Gen.constant(map.map { case (key, value) => NegInt.unsafeFrom(key) -> value }).log("input")
    } yield {
      val expected = Json.fromFields(map.map { case (key, value) => key.toString -> value.asJson })

      val actual = input.asJson
      Result.all(
        List(
          actual ==== expected,
          actual.noSpacesSortKeys ==== expected.noSpacesSortKeys,
        )
      )
    }

  def testKeyDecoderNegInt: Property =
    for {
      ns1      <- Gen.int(Range.linear(-1, Int.MinValue)).list(Range.linear(1, 10)).log("ns1")
      ns2      <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(ns1.length)).log("ns2")
      map      <- Gen.constant(ns1.zip(ns2).toMap).log("map")
      expected <- Gen.constant(map.map { case (key, value) => NegInt.unsafeFrom(key) -> value }).log("expected")
    } yield {
      val input  = Json.fromFields(map.map { case (key, value) => key.toString -> value.asJson })
      val actual = decode[Map[NegInt, Int]](input.noSpaces)

      actual ==== expected.asRight
    }

  //

  def testEncoderNonNegInt: Property =
    for {
      n <- Gen.int(Range.linear(0, Int.MaxValue)).log("n")
    } yield {
      val input = NonNegInt.unsafeFrom(n)

      val expected = n.asJson
      val actual   = input.asJson
      Result.all(
        List(
          actual ==== expected,
          actual.noSpaces ==== expected.noSpaces,
        )
      )
    }

  def testDecoderNonNegInt: Property =
    for {
      n <- Gen.int(Range.linear(0, Int.MaxValue)).log("n")
    } yield {
      val input = n.asJson

      val expected = NonNegInt.from(n)
      val actual   = decode[NonNegInt](input.noSpaces)
      actual ==== expected
    }

  def testKeyEncoderNonNegInt: Property =
    for {
      ns1   <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.linear(1, 10)).log("ns1")
      ns2   <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(ns1.length)).log("ns2")
      map   <- Gen.constant(ns1.zip(ns2).toMap).log("map")
      input <- Gen.constant(map.map { case (key, value) => NonNegInt.unsafeFrom(key) -> value }).log("input")
    } yield {
      val expected = Json.fromFields(map.map { case (key, value) => key.toString -> value.asJson })

      val actual = input.asJson
      Result.all(
        List(
          actual ==== expected,
          actual.noSpacesSortKeys ==== expected.noSpacesSortKeys,
        )
      )
    }

  def testKeyDecoderNonNegInt: Property =
    for {
      ns1      <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.linear(1, 10)).log("ns1")
      ns2      <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(ns1.length)).log("ns2")
      map      <- Gen.constant(ns1.zip(ns2).toMap).log("map")
      expected <- Gen.constant(map.map { case (key, value) => NonNegInt.unsafeFrom(key) -> value }).log("expected")
    } yield {
      val input  = Json.fromFields(map.map { case (key, value) => key.toString -> value.asJson })
      val actual = decode[Map[NonNegInt, Int]](input.noSpaces)

      actual ==== expected.asRight
    }

  //

  def testEncoderPosInt: Property =
    for {
      n <- Gen.int(Range.linear(1, Int.MaxValue)).log("n")
    } yield {
      val input = PosInt.unsafeFrom(n)

      val expected = n.asJson
      val actual   = input.asJson
      Result.all(
        List(
          actual ==== expected,
          actual.noSpaces ==== expected.noSpaces,
        )
      )
    }

  def testDecoderPosInt: Property =
    for {
      n <- Gen.int(Range.linear(1, Int.MaxValue)).log("n")
    } yield {
      val input = n.asJson

      val expected = PosInt.from(n)
      val actual   = decode[PosInt](input.noSpaces)
      actual ==== expected
    }

  def testKeyEncoderPosInt: Property =
    for {
      ns1   <- Gen.int(Range.linear(1, Int.MaxValue)).list(Range.linear(1, 10)).log("ns1")
      ns2   <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(ns1.length)).log("ns2")
      map   <- Gen.constant(ns1.zip(ns2).toMap).log("map")
      input <- Gen.constant(map.map { case (key, value) => PosInt.unsafeFrom(key) -> value }).log("input")
    } yield {
      val expected = Json.fromFields(map.map { case (key, value) => key.toString -> value.asJson })

      val actual = input.asJson
      Result.all(
        List(
          actual ==== expected,
          actual.noSpacesSortKeys ==== expected.noSpacesSortKeys,
        )
      )
    }

  def testKeyDecoderPosInt: Property =
    for {
      ns1      <- Gen.int(Range.linear(1, Int.MaxValue)).list(Range.linear(1, 10)).log("ns1")
      ns2      <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(ns1.length)).log("ns2")
      map      <- Gen.constant(ns1.zip(ns2).toMap).log("map")
      expected <- Gen.constant(map.map { case (key, value) => PosInt.unsafeFrom(key) -> value }).log("expected")
    } yield {
      val input  = Json.fromFields(map.map { case (key, value) => key.toString -> value.asJson })
      val actual = decode[Map[PosInt, Int]](input.noSpaces)

      actual ==== expected.asRight
    }

  //

  def testEncoderNonPosInt: Property =
    for {
      n <- Gen.int(Range.linear(0, Int.MinValue)).log("n")
    } yield {
      val input = NonPosInt.unsafeFrom(n)

      val expected = n.asJson
      val actual   = input.asJson
      Result.all(
        List(
          actual ==== expected,
          actual.noSpaces ==== expected.noSpaces,
        )
      )
    }

  def testDecoderNonPosInt: Property =
    for {
      n <- Gen.int(Range.linear(0, Int.MinValue)).log("n")
    } yield {
      val input = n.asJson

      val expected = NonPosInt.from(n)
      val actual   = decode[NonPosInt](input.noSpaces)
      actual ==== expected
    }

  def testKeyEncoderNonPosInt: Property =
    for {
      ns1   <- Gen.int(Range.linear(0, Int.MinValue)).list(Range.linear(1, 10)).log("ns1")
      ns2   <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(ns1.length)).log("ns2")
      map   <- Gen.constant(ns1.zip(ns2).toMap).log("map")
      input <- Gen.constant(map.map { case (key, value) => NonPosInt.unsafeFrom(key) -> value }).log("input")
    } yield {
      val expected = Json.fromFields(map.map { case (key, value) => key.toString -> value.asJson })

      val actual = input.asJson
      Result.all(
        List(
          actual ==== expected,
          actual.noSpacesSortKeys ==== expected.noSpacesSortKeys,
        )
      )
    }

  def testKeyDecoderNonPosInt: Property =
    for {
      ns1      <- Gen.int(Range.linear(0, Int.MinValue)).list(Range.linear(1, 10)).log("ns1")
      ns2      <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(ns1.length)).log("ns2")
      map      <- Gen.constant(ns1.zip(ns2).toMap).log("map")
      expected <- Gen.constant(map.map { case (key, value) => NonPosInt.unsafeFrom(key) -> value }).log("expected")
    } yield {
      val input  = Json.fromFields(map.map { case (key, value) => key.toString -> value.asJson })
      val actual = decode[Map[NonPosInt, Int]](input.noSpaces)

      actual ==== expected.asRight
    }

  //

  def testEncoderNegLong: Property =
    for {
      n <- Gen.long(Range.linear(-1L, MinLongValue)).log("n")
    } yield {
      val input = NegLong.unsafeFrom(n)

      val expected = n.asJson
      val actual   = input.asJson
      Result.all(
        List(
          actual ==== expected,
          actual.noSpaces ==== expected.noSpaces,
        )
      )
    }

  def testDecoderNegLong: Property =
    for {
      n <- Gen.long(Range.linear(-1L, MinLongValue)).log("n")
    } yield {
      val input = n.asJson

      val expected = NegLong.from(n)
      val actual   = decode[NegLong](input.noSpaces)
      actual ==== expected
    }

  def testKeyEncoderNegLong: Property =
    for {
      ns1   <- Gen.long(Range.linear(-1L, MinLongValue)).list(Range.linear(1, 10)).log("ns1")
      ns2   <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(ns1.length)).log("ns2")
      map   <- Gen.constant(ns1.zip(ns2).toMap).log("map")
      input <- Gen.constant(map.map { case (key, value) => NegLong.unsafeFrom(key) -> value }).log("input")
    } yield {
      val expected = Json.fromFields(map.map { case (key, value) => key.toString -> value.asJson })

      val actual = input.asJson
      Result.all(
        List(
          actual ==== expected,
          actual.noSpacesSortKeys ==== expected.noSpacesSortKeys,
        )
      )
    }

  def testKeyDecoderNegLong: Property =
    for {
      ns1      <- Gen.long(Range.linear(-1L, MinLongValue)).list(Range.linear(1, 10)).log("ns1")
      ns2      <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(ns1.length)).log("ns2")
      map      <- Gen.constant(ns1.zip(ns2).toMap).log("map")
      expected <- Gen.constant(map.map { case (key, value) => NegLong.unsafeFrom(key) -> value }).log("expected")
    } yield {
      val input  = Json.fromFields(map.map { case (key, value) => key.toString -> value.asJson })
      val actual = decode[Map[NegLong, Int]](input.noSpaces)

      actual ==== expected.asRight
    }

  //

  def testEncoderNonNegLong: Property =
    for {
      n <- Gen.long(Range.linear(0L, MaxLongValue)).log("n")
    } yield {
      val input = NonNegLong.unsafeFrom(n)

      val expected = n.asJson
      val actual   = input.asJson
      Result.all(
        List(
          actual ==== expected,
          actual.noSpaces ==== expected.noSpaces,
        )
      )
    }

  def testDecoderNonNegLong: Property =
    for {
      n <- Gen.long(Range.linear(0L, MaxLongValue)).log("n")
    } yield {
      val input = n.asJson

      val expected = NonNegLong.from(n)
      val actual   = decode[NonNegLong](input.noSpaces)
      actual ==== expected
    }

  def testKeyEncoderNonNegLong: Property =
    for {
      ns1   <- Gen.long(Range.linear(0L, MaxLongValue)).list(Range.linear(1, 10)).log("ns1")
      ns2   <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(ns1.length)).log("ns2")
      map   <- Gen.constant(ns1.zip(ns2).toMap).log("map")
      input <- Gen.constant(map.map { case (key, value) => NonNegLong.unsafeFrom(key) -> value }).log("input")
    } yield {
      val expected = Json.fromFields(map.map { case (key, value) => key.toString -> value.asJson })

      val actual = input.asJson
      Result.all(
        List(
          actual ==== expected,
          actual.noSpacesSortKeys ==== expected.noSpacesSortKeys,
        )
      )
    }

  def testKeyDecoderNonNegLong: Property =
    for {
      ns1      <- Gen.long(Range.linear(0L, MaxLongValue)).list(Range.linear(1, 10)).log("ns1")
      ns2      <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(ns1.length)).log("ns2")
      map      <- Gen.constant(ns1.zip(ns2).toMap).log("map")
      expected <- Gen.constant(map.map { case (key, value) => NonNegLong.unsafeFrom(key) -> value }).log("expected")
    } yield {
      val input  = Json.fromFields(map.map { case (key, value) => key.toString -> value.asJson })
      val actual = decode[Map[NonNegLong, Int]](input.noSpaces)

      actual ==== expected.asRight
    }

  //

  def testEncoderPosLong: Property =
    for {
      n <- Gen.long(Range.linear(1L, MaxLongValue)).log("n")
    } yield {
      val input = PosLong.unsafeFrom(n)

      val expected = n.asJson
      val actual   = input.asJson
      Result.all(
        List(
          actual ==== expected,
          actual.noSpaces ==== expected.noSpaces,
        )
      )
    }

  def testDecoderPosLong: Property =
    for {
      n <- Gen.long(Range.linear(1L, MaxLongValue)).log("n")
    } yield {
      val input = n.asJson

      val expected = PosLong.from(n)
      val actual   = decode[PosLong](input.noSpaces)
      actual ==== expected
    }

  def testKeyEncoderPosLong: Property =
    for {
      ns1   <- Gen.long(Range.linear(1L, MaxLongValue)).list(Range.linear(1, 10)).log("ns1")
      ns2   <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(ns1.length)).log("ns2")
      map   <- Gen.constant(ns1.zip(ns2).toMap).log("map")
      input <- Gen.constant(map.map { case (key, value) => PosLong.unsafeFrom(key) -> value }).log("input")
    } yield {
      val expected = Json.fromFields(map.map { case (key, value) => key.toString -> value.asJson })

      val actual = input.asJson
      Result.all(
        List(
          actual ==== expected,
          actual.noSpacesSortKeys ==== expected.noSpacesSortKeys,
        )
      )
    }

  def testKeyDecoderPosLong: Property =
    for {
      ns1      <- Gen.long(Range.linear(1L, MaxLongValue)).list(Range.linear(1, 10)).log("ns1")
      ns2      <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(ns1.length)).log("ns2")
      map      <- Gen.constant(ns1.zip(ns2).toMap).log("map")
      expected <- Gen.constant(map.map { case (key, value) => PosLong.unsafeFrom(key) -> value }).log("expected")
    } yield {
      val input  = Json.fromFields(map.map { case (key, value) => key.toString -> value.asJson })
      val actual = decode[Map[PosLong, Int]](input.noSpaces)

      actual ==== expected.asRight
    }

  //

  def testEncoderNonPosLong: Property =
    for {
      n <- Gen.long(Range.linear(0L, MinLongValue)).log("n")
    } yield {
      val input = NonPosLong.unsafeFrom(n)

      val expected = n.asJson
      val actual   = input.asJson
      Result.all(
        List(
          actual ==== expected,
          actual.noSpaces ==== expected.noSpaces,
        )
      )
    }

  def testDecoderNonPosLong: Property =
    for {
      n <- Gen.long(Range.linear(0L, MinLongValue)).log("n")
    } yield {
      val input = n.asJson

      val expected = NonPosLong.from(n)
      val actual   = decode[NonPosLong](input.noSpaces)
      actual ==== expected
    }

  def testKeyEncoderNonPosLong: Property =
    for {
      ns1   <- Gen.long(Range.linear(0L, MinLongValue)).list(Range.linear(1, 10)).log("ns1")
      ns2   <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(ns1.length)).log("ns2")
      map   <- Gen.constant(ns1.zip(ns2).toMap).log("map")
      input <- Gen.constant(map.map { case (key, value) => NonPosLong.unsafeFrom(key) -> value }).log("input")
    } yield {
      val expected = Json.fromFields(map.map { case (key, value) => key.toString -> value.asJson })

      val actual = input.asJson
      Result.all(
        List(
          actual ==== expected,
          actual.noSpacesSortKeys ==== expected.noSpacesSortKeys,
        )
      )
    }

  def testKeyDecoderNonPosLong: Property =
    for {
      ns1      <- Gen.long(Range.linear(0L, MinLongValue)).list(Range.linear(1, 10)).log("ns1")
      ns2      <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(ns1.length)).log("ns2")
      map      <- Gen.constant(ns1.zip(ns2).toMap).log("map")
      expected <- Gen.constant(map.map { case (key, value) => NonPosLong.unsafeFrom(key) -> value }).log("expected")
    } yield {
      val input  = Json.fromFields(map.map { case (key, value) => key.toString -> value.asJson })
      val actual = decode[Map[NonPosLong, Int]](input.noSpaces)

      actual ==== expected.asRight
    }

  //

  def testEncoderNegShort: Property =
    for {
      n <- Gen.short(Range.linear(-1, Short.MinValue)).log("n")
    } yield {
      val input = NegShort.unsafeFrom(n)

      val expected = n.asJson
      val actual   = input.asJson
      Result.all(
        List(
          actual ==== expected,
          actual.noSpaces ==== expected.noSpaces,
        )
      )
    }

  def testDecoderNegShort: Property =
    for {
      n <- Gen.short(Range.linear(-1, Short.MinValue)).log("n")
    } yield {
      val input = n.asJson

      val expected = NegShort.from(n)
      val actual   = decode[NegShort](input.noSpaces)
      actual ==== expected
    }

  def testKeyEncoderNegShort: Property =
    for {
      ns1   <- Gen.short(Range.linear(-1, Short.MinValue)).list(Range.linear(1, 10)).log("ns1")
      ns2   <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(ns1.length)).log("ns2")
      map   <- Gen.constant(ns1.zip(ns2).toMap).log("map")
      input <- Gen.constant(map.map { case (key, value) => NegShort.unsafeFrom(key) -> value }).log("input")
    } yield {
      val expected = Json.fromFields(map.map { case (key, value) => key.toString -> value.asJson })

      val actual = input.asJson
      Result.all(
        List(
          actual ==== expected,
          actual.noSpacesSortKeys ==== expected.noSpacesSortKeys,
        )
      )
    }

  def testKeyDecoderNegShort: Property =
    for {
      ns1      <- Gen.short(Range.linear(-1, Short.MinValue)).list(Range.linear(1, 10)).log("ns1")
      ns2      <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(ns1.length)).log("ns2")
      map      <- Gen.constant(ns1.zip(ns2).toMap).log("map")
      expected <- Gen.constant(map.map { case (key, value) => NegShort.unsafeFrom(key) -> value }).log("expected")
    } yield {
      val input  = Json.fromFields(map.map { case (key, value) => key.toString -> value.asJson })
      val actual = decode[Map[NegShort, Int]](input.noSpaces)

      actual ==== expected.asRight
    }

  //

  def testEncoderNonNegShort: Property =
    for {
      n <- Gen.short(Range.linear(0, Short.MaxValue)).log("n")
    } yield {
      val input = NonNegShort.unsafeFrom(n)

      val expected = n.asJson
      val actual   = input.asJson
      Result.all(
        List(
          actual ==== expected,
          actual.noSpaces ==== expected.noSpaces,
        )
      )
    }

  def testDecoderNonNegShort: Property =
    for {
      n <- Gen.short(Range.linear(0, Short.MaxValue)).log("n")
    } yield {
      val input = n.asJson

      val expected = NonNegShort.from(n)
      val actual   = decode[NonNegShort](input.noSpaces)
      actual ==== expected
    }

  def testKeyEncoderNonNegShort: Property =
    for {
      ns1   <- Gen.short(Range.linear(0, Short.MaxValue)).list(Range.linear(1, 10)).log("ns1")
      ns2   <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(ns1.length)).log("ns2")
      map   <- Gen.constant(ns1.zip(ns2).toMap).log("map")
      input <- Gen.constant(map.map { case (key, value) => NonNegShort.unsafeFrom(key) -> value }).log("input")
    } yield {
      val expected = Json.fromFields(map.map { case (key, value) => key.toString -> value.asJson })

      val actual = input.asJson
      Result.all(
        List(
          actual ==== expected,
          actual.noSpacesSortKeys ==== expected.noSpacesSortKeys,
        )
      )
    }

  def testKeyDecoderNonNegShort: Property =
    for {
      ns1      <- Gen.short(Range.linear(0, Short.MaxValue)).list(Range.linear(1, 10)).log("ns1")
      ns2      <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(ns1.length)).log("ns2")
      map      <- Gen.constant(ns1.zip(ns2).toMap).log("map")
      expected <- Gen.constant(map.map { case (key, value) => NonNegShort.unsafeFrom(key) -> value }).log("expected")
    } yield {
      val input  = Json.fromFields(map.map { case (key, value) => key.toString -> value.asJson })
      val actual = decode[Map[NonNegShort, Int]](input.noSpaces)

      actual ==== expected.asRight
    }

  //

  def testEncoderPosShort: Property =
    for {
      n <- Gen.short(Range.linear(1, Short.MaxValue)).log("n")
    } yield {
      val input = PosShort.unsafeFrom(n)

      val expected = n.asJson
      val actual   = input.asJson
      Result.all(
        List(
          actual ==== expected,
          actual.noSpaces ==== expected.noSpaces,
        )
      )
    }

  def testDecoderPosShort: Property =
    for {
      n <- Gen.short(Range.linear(1, Short.MaxValue)).log("n")
    } yield {
      val input = n.asJson

      val expected = PosShort.from(n)
      val actual   = decode[PosShort](input.noSpaces)
      actual ==== expected
    }

  def testKeyEncoderPosShort: Property =
    for {
      ns1   <- Gen.short(Range.linear(1, Short.MaxValue)).list(Range.linear(1, 10)).log("ns1")
      ns2   <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(ns1.length)).log("ns2")
      map   <- Gen.constant(ns1.zip(ns2).toMap).log("map")
      input <- Gen.constant(map.map { case (key, value) => PosShort.unsafeFrom(key) -> value }).log("input")
    } yield {
      val expected = Json.fromFields(map.map { case (key, value) => key.toString -> value.asJson })

      val actual = input.asJson
      Result.all(
        List(
          actual ==== expected,
          actual.noSpacesSortKeys ==== expected.noSpacesSortKeys,
        )
      )
    }

  def testKeyDecoderPosShort: Property =
    for {
      ns1      <- Gen.short(Range.linear(1, Short.MaxValue)).list(Range.linear(1, 10)).log("ns1")
      ns2      <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(ns1.length)).log("ns2")
      map      <- Gen.constant(ns1.zip(ns2).toMap).log("map")
      expected <- Gen.constant(map.map { case (key, value) => PosShort.unsafeFrom(key) -> value }).log("expected")
    } yield {
      val input  = Json.fromFields(map.map { case (key, value) => key.toString -> value.asJson })
      val actual = decode[Map[PosShort, Int]](input.noSpaces)

      actual ==== expected.asRight
    }

  //

  def testEncoderNonPosShort: Property =
    for {
      n <- Gen.short(Range.linear(0, Short.MinValue)).log("n")
    } yield {
      val input = NonPosShort.unsafeFrom(n)

      val expected = n.asJson
      val actual   = input.asJson
      Result.all(
        List(
          actual ==== expected,
          actual.noSpaces ==== expected.noSpaces,
        )
      )
    }

  def testDecoderNonPosShort: Property =
    for {
      n <- Gen.short(Range.linear(0, Short.MinValue)).log("n")
    } yield {
      val input = n.asJson

      val expected = NonPosShort.from(n)
      val actual   = decode[NonPosShort](input.noSpaces)
      actual ==== expected
    }

  def testKeyEncoderNonPosShort: Property =
    for {
      ns1   <- Gen.short(Range.linear(0, Short.MinValue)).list(Range.linear(1, 10)).log("ns1")
      ns2   <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(ns1.length)).log("ns2")
      map   <- Gen.constant(ns1.zip(ns2).toMap).log("map")
      input <- Gen.constant(map.map { case (key, value) => NonPosShort.unsafeFrom(key) -> value }).log("input")
    } yield {
      val expected = Json.fromFields(map.map { case (key, value) => key.toString -> value.asJson })

      val actual = input.asJson
      Result.all(
        List(
          actual ==== expected,
          actual.noSpacesSortKeys ==== expected.noSpacesSortKeys,
        )
      )
    }

  def testKeyDecoderNonPosShort: Property =
    for {
      ns1      <- Gen.short(Range.linear(0, Short.MinValue)).list(Range.linear(1, 10)).log("ns1")
      ns2      <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(ns1.length)).log("ns2")
      map      <- Gen.constant(ns1.zip(ns2).toMap).log("map")
      expected <- Gen.constant(map.map { case (key, value) => NonPosShort.unsafeFrom(key) -> value }).log("expected")
    } yield {
      val input  = Json.fromFields(map.map { case (key, value) => key.toString -> value.asJson })
      val actual = decode[Map[NonPosShort, Int]](input.noSpaces)

      actual ==== expected.asRight
    }

  //

  def testEncoderNegByte: Property =
    for {
      n <- Gen.byte(Range.linear(-1, Byte.MinValue)).log("n")
    } yield {
      val input = NegByte.unsafeFrom(n)

      val expected = n.asJson
      val actual   = input.asJson
      Result.all(
        List(
          actual ==== expected,
          actual.noSpaces ==== expected.noSpaces,
        )
      )
    }

  def testDecoderNegByte: Property =
    for {
      n <- Gen.byte(Range.linear(-1, Byte.MinValue)).log("n")
    } yield {
      val input = n.asJson

      val expected = NegByte.from(n)
      val actual   = decode[NegByte](input.noSpaces)
      actual ==== expected
    }

  def testKeyEncoderNegByte: Property =
    for {
      ns1   <- Gen.byte(Range.linear(-1, Byte.MinValue)).list(Range.linear(1, 10)).log("ns1")
      ns2   <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(ns1.length)).log("ns2")
      map   <- Gen.constant(ns1.zip(ns2).toMap).log("map")
      input <- Gen.constant(map.map { case (key, value) => NegByte.unsafeFrom(key) -> value }).log("input")
    } yield {
      val expected = Json.fromFields(map.map { case (key, value) => key.toString -> value.asJson })

      val actual = input.asJson
      Result.all(
        List(
          actual ==== expected,
          actual.noSpacesSortKeys ==== expected.noSpacesSortKeys,
        )
      )
    }

  def testKeyDecoderNegByte: Property =
    for {
      ns1      <- Gen.byte(Range.linear(-1, Byte.MinValue)).list(Range.linear(1, 10)).log("ns1")
      ns2      <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(ns1.length)).log("ns2")
      map      <- Gen.constant(ns1.zip(ns2).toMap).log("map")
      expected <- Gen.constant(map.map { case (key, value) => NegByte.unsafeFrom(key) -> value }).log("expected")
    } yield {
      val input  = Json.fromFields(map.map { case (key, value) => key.toString -> value.asJson })
      val actual = decode[Map[NegByte, Int]](input.noSpaces)

      actual ==== expected.asRight
    }

  //

  def testEncoderNonNegByte: Property =
    for {
      n <- Gen.byte(Range.linear(0, Byte.MaxValue)).log("n")
    } yield {
      val input = NonNegByte.unsafeFrom(n)

      val expected = n.asJson
      val actual   = input.asJson
      Result.all(
        List(
          actual ==== expected,
          actual.noSpaces ==== expected.noSpaces,
        )
      )
    }

  def testDecoderNonNegByte: Property =
    for {
      n <- Gen.byte(Range.linear(0, Byte.MaxValue)).log("n")
    } yield {
      val input = n.asJson

      val expected = NonNegByte.from(n)
      val actual   = decode[NonNegByte](input.noSpaces)
      actual ==== expected
    }

  def testKeyEncoderNonNegByte: Property =
    for {
      ns1   <- Gen.byte(Range.linear(0, Byte.MaxValue)).list(Range.linear(1, 10)).log("ns1")
      ns2   <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(ns1.length)).log("ns2")
      map   <- Gen.constant(ns1.zip(ns2).toMap).log("map")
      input <- Gen.constant(map.map { case (key, value) => NonNegByte.unsafeFrom(key) -> value }).log("input")
    } yield {
      val expected = Json.fromFields(map.map { case (key, value) => key.toString -> value.asJson })

      val actual = input.asJson
      Result.all(
        List(
          actual ==== expected,
          actual.noSpacesSortKeys ==== expected.noSpacesSortKeys,
        )
      )
    }

  def testKeyDecoderNonNegByte: Property =
    for {
      ns1      <- Gen.byte(Range.linear(0, Byte.MaxValue)).list(Range.linear(1, 10)).log("ns1")
      ns2      <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(ns1.length)).log("ns2")
      map      <- Gen.constant(ns1.zip(ns2).toMap).log("map")
      expected <- Gen.constant(map.map { case (key, value) => NonNegByte.unsafeFrom(key) -> value }).log("expected")
    } yield {
      val input  = Json.fromFields(map.map { case (key, value) => key.toString -> value.asJson })
      val actual = decode[Map[NonNegByte, Int]](input.noSpaces)

      actual ==== expected.asRight
    }

  //

  def testEncoderPosByte: Property =
    for {
      n <- Gen.byte(Range.linear(1, Byte.MaxValue)).log("n")
    } yield {
      val input = PosByte.unsafeFrom(n)

      val expected = n.asJson
      val actual   = input.asJson
      Result.all(
        List(
          actual ==== expected,
          actual.noSpaces ==== expected.noSpaces,
        )
      )
    }

  def testDecoderPosByte: Property =
    for {
      n <- Gen.byte(Range.linear(1, Byte.MaxValue)).log("n")
    } yield {
      val input = n.asJson

      val expected = PosByte.from(n)
      val actual   = decode[PosByte](input.noSpaces)
      actual ==== expected
    }

  def testKeyEncoderPosByte: Property =
    for {
      ns1   <- Gen.byte(Range.linear(1, Byte.MaxValue)).list(Range.linear(1, 10)).log("ns1")
      ns2   <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(ns1.length)).log("ns2")
      map   <- Gen.constant(ns1.zip(ns2).toMap).log("map")
      input <- Gen.constant(map.map { case (key, value) => PosByte.unsafeFrom(key) -> value }).log("input")
    } yield {
      val expected = Json.fromFields(map.map { case (key, value) => key.toString -> value.asJson })

      val actual = input.asJson
      Result.all(
        List(
          actual ==== expected,
          actual.noSpacesSortKeys ==== expected.noSpacesSortKeys,
        )
      )
    }

  def testKeyDecoderPosByte: Property =
    for {
      ns1      <- Gen.byte(Range.linear(1, Byte.MaxValue)).list(Range.linear(1, 10)).log("ns1")
      ns2      <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(ns1.length)).log("ns2")
      map      <- Gen.constant(ns1.zip(ns2).toMap).log("map")
      expected <- Gen.constant(map.map { case (key, value) => PosByte.unsafeFrom(key) -> value }).log("expected")
    } yield {
      val input  = Json.fromFields(map.map { case (key, value) => key.toString -> value.asJson })
      val actual = decode[Map[PosByte, Int]](input.noSpaces)

      actual ==== expected.asRight
    }

  //

  def testEncoderNonPosByte: Property =
    for {
      n <- Gen.byte(Range.linear(0, Byte.MinValue)).log("n")
    } yield {
      val input = NonPosByte.unsafeFrom(n)

      val expected = n.asJson
      val actual   = input.asJson
      Result.all(
        List(
          actual ==== expected,
          actual.noSpaces ==== expected.noSpaces,
        )
      )
    }

  def testDecoderNonPosByte: Property =
    for {
      n <- Gen.byte(Range.linear(0, Byte.MinValue)).log("n")
    } yield {
      val input = n.asJson

      val expected = NonPosByte.from(n)
      val actual   = decode[NonPosByte](input.noSpaces)
      actual ==== expected
    }

  def testKeyEncoderNonPosByte: Property =
    for {
      ns1   <- Gen.byte(Range.linear(0, Byte.MinValue)).list(Range.linear(1, 10)).log("ns1")
      ns2   <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(ns1.length)).log("ns2")
      map   <- Gen.constant(ns1.zip(ns2).toMap).log("map")
      input <- Gen.constant(map.map { case (key, value) => NonPosByte.unsafeFrom(key) -> value }).log("input")
    } yield {
      val expected = Json.fromFields(map.map { case (key, value) => key.toString -> value.asJson })

      val actual = input.asJson
      Result.all(
        List(
          actual ==== expected,
          actual.noSpacesSortKeys ==== expected.noSpacesSortKeys,
        )
      )
    }

  def testKeyDecoderNonPosByte: Property =
    for {
      ns1      <- Gen.byte(Range.linear(0, Byte.MinValue)).list(Range.linear(1, 10)).log("ns1")
      ns2      <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(ns1.length)).log("ns2")
      map      <- Gen.constant(ns1.zip(ns2).toMap).log("map")
      expected <- Gen.constant(map.map { case (key, value) => NonPosByte.unsafeFrom(key) -> value }).log("expected")
    } yield {
      val input  = Json.fromFields(map.map { case (key, value) => key.toString -> value.asJson })
      val actual = decode[Map[NonPosByte, Int]](input.noSpaces)

      actual ==== expected.asRight
    }

  //

  def testEncoderNegFloat: Property =
    for {
      n <- Gen.double(Range.linearFrac(-0.00001f, Float.MinValue)).map(_.toFloat).log("n")
    } yield {
      val input = NegFloat.unsafeFrom(n)

      val expected = n.asJson
      val actual   = input.asJson
      Result.all(
        List(
          actual ==== expected,
          actual.noSpaces ==== expected.noSpaces,
        )
      )
    }

  def testDecoderNegFloat: Property =
    for {
      n <- Gen.double(Range.linearFrac(-0.00001f, Float.MinValue)).map(_.toFloat).log("n")
    } yield {
      val input = n.asJson

      val expected = NegFloat.from(n)
      val actual   = decode[NegFloat](input.noSpaces)
      actual ==== expected
    }

  //

  def testEncoderNonNegFloat: Property =
    for {
      n <- Gen.double(Range.linearFrac(0f, Float.MaxValue)).map(_.toFloat).log("n")
    } yield {
      val input = NonNegFloat.unsafeFrom(n)

      val expected = n.asJson
      val actual   = input.asJson
      Result.all(
        List(
          actual ==== expected,
          actual.noSpaces ==== expected.noSpaces,
        )
      )
    }

  def testDecoderNonNegFloat: Property =
    for {
      n <- Gen.double(Range.linearFrac(0f, Float.MaxValue)).map(_.toFloat).log("n")
    } yield {
      val input = n.asJson

      val expected = NonNegFloat.from(n)
      val actual   = decode[NonNegFloat](input.noSpaces)
      actual ==== expected
    }

  //

  def testEncoderPosFloat: Property =
    for {
      n <- Gen.double(Range.linearFrac(0.00001f, Float.MaxValue)).map(_.toFloat).log("n")
    } yield {
      val input = PosFloat.unsafeFrom(n)

      val expected = n.asJson
      val actual   = input.asJson
      Result.all(
        List(
          actual ==== expected,
          actual.noSpaces ==== expected.noSpaces,
        )
      )
    }

  def testDecoderPosFloat: Property =
    for {
      n <- Gen.double(Range.linearFrac(0.00001f, Float.MaxValue)).map(_.toFloat).log("n")
    } yield {
      val input = n.asJson

      val expected = PosFloat.from(n)
      val actual   = decode[PosFloat](input.noSpaces)
      actual ==== expected
    }

  //

  def testEncoderNonPosFloat: Property =
    for {
      n <- Gen.double(Range.linearFrac(0f, Float.MinValue)).map(_.toFloat).log("n")
    } yield {
      val input = NonPosFloat.unsafeFrom(n)

      val expected = n.asJson
      val actual   = input.asJson
      Result.all(
        List(
          actual ==== expected,
          actual.noSpaces ==== expected.noSpaces,
        )
      )
    }

  def testDecoderNonPosFloat: Property =
    for {
      n <- Gen.double(Range.linearFrac(0f, Float.MinValue)).map(_.toFloat).log("n")
    } yield {
      val input = n.asJson

      val expected = NonPosFloat.from(n)
      val actual   = decode[NonPosFloat](input.noSpaces)
      actual ==== expected
    }

  //

  def testEncoderNegDouble: Property =
    for {
      n <- Gen.double(Range.linearFrac(-0.0000001d, Double.MinValue)).log("n")
    } yield {
      val input = NegDouble.unsafeFrom(n)

      val expected = n.asJson
      val actual   = input.asJson
      Result.all(
        List(
          actual ==== expected,
          actual.noSpaces ==== expected.noSpaces,
        )
      )
    }

  def testDecoderNegDouble: Property =
    for {
      n <- Gen.double(Range.linearFrac(-0.0000001d, Double.MinValue)).log("n")
    } yield {
      val input = n.asJson

      val expected = NegDouble.from(n)
      val actual   = decode[NegDouble](input.noSpaces)
      actual ==== expected
    }

  //

  def testEncoderNonNegDouble: Property =
    for {
      n <- Gen.double(Range.linearFrac(0d, Double.MaxValue)).log("n")
    } yield {
      val input = NonNegDouble.unsafeFrom(n)

      val expected = n.asJson
      val actual   = input.asJson
      Result.all(
        List(
          actual ==== expected,
          actual.noSpaces ==== expected.noSpaces,
        )
      )
    }

  def testDecoderNonNegDouble: Property =
    for {
      n <- Gen.double(Range.linearFrac(0d, Double.MaxValue)).log("n")
    } yield {
      val input = n.asJson

      val expected = NonNegDouble.from(n)
      val actual   = decode[NonNegDouble](input.noSpaces)
      actual ==== expected
    }

  //

  def testEncoderPosDouble: Property =
    for {
      n <- Gen.double(Range.linearFrac(0.0000001d, Double.MaxValue)).log("n")
    } yield {
      val input = PosDouble.unsafeFrom(n)

      val expected = n.asJson
      val actual   = input.asJson
      Result.all(
        List(
          actual ==== expected,
          actual.noSpaces ==== expected.noSpaces,
        )
      )
    }

  def testDecoderPosDouble: Property =
    for {
      n <- Gen.double(Range.linearFrac(0.0000001d, Double.MaxValue)).log("n")
    } yield {
      val input = n.asJson

      val expected = PosDouble.from(n)
      val actual   = decode[PosDouble](input.noSpaces)
      actual ==== expected
    }

  //

  def testEncoderNonPosDouble: Property =
    for {
      n <- Gen.double(Range.linearFrac(0d, Double.MinValue)).log("n")
    } yield {
      val input = NonPosDouble.unsafeFrom(n)

      val expected = n.asJson
      val actual   = input.asJson
      Result.all(
        List(
          actual ==== expected,
          actual.noSpaces ==== expected.noSpaces,
        )
      )
    }

  def testDecoderNonPosDouble: Property =
    for {
      n <- Gen.double(Range.linearFrac(0d, Double.MinValue)).log("n")
    } yield {
      val input = n.asJson

      val expected = NonPosDouble.from(n)
      val actual   = decode[NonPosDouble](input.noSpaces)
      actual ==== expected
    }

  //

  def testEncoderNegBigInt: Property =
    for {
      n <- Gen.long(Range.linear(-1L, MinLongValue)).map(BigInt(_)).log("n")
    } yield {
      val input = NegBigInt.unsafeFrom(n)

      val expected = n.asJson
      val actual   = input.asJson
      Result.all(
        List(
          actual ==== expected,
          actual.noSpaces ==== expected.noSpaces,
        )
      )
    }

  def testDecoderNegBigInt: Property =
    for {
      n <- Gen.long(Range.linear(-1L, MinLongValue)).map(BigInt(_)).log("n")
    } yield {
      val input = n.asJson

      val expected = NegBigInt.from(n)
      val actual   = decode[NegBigInt](input.noSpaces)
      actual ==== expected
    }

  def testKeyEncoderNegBigInt: Property =
    for {
      ns1   <- Gen.long(Range.linear(-1L, MinLongValue)).list(Range.linear(1, 10)).log("ns1")
      ns2   <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(ns1.length)).log("ns2")
      map   <- Gen.constant(ns1.zip(ns2).toMap).log("map")
      input <- Gen.constant(map.map { case (key, value) => NegBigInt.unsafeFrom(key) -> value }).log("input")
    } yield {
      val expected = Json.fromFields(map.map { case (key, value) => key.toString -> value.asJson })

      val actual = input.asJson
      Result.all(
        List(
          actual ==== expected,
          actual.noSpacesSortKeys ==== expected.noSpacesSortKeys,
        )
      )
    }

  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  def testKeyDecoderNegBigInt: Property =
    for {
      ns1      <- Gen.long(Range.linear(-1L, MinLongValue)).map(BigInt(_)).list(Range.linear(1, 10)).log("ns1")
      ns2      <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(ns1.length)).log("ns2")
      map      <- Gen.constant(ns1.zip(ns2).toMap).log("map")
      expected <- Gen.constant(map.map { case (key, value) => NegBigInt.unsafeFrom(key) -> value }).log("expected")
    } yield {
      val input  = Json.fromFields(map.map { case (key, value) => key.toString -> value.asJson })
      val actual = decode[Map[NegBigInt, Int]](input.noSpaces)

      actual ==== expected.asRight
    }

  //

  def testEncoderNonNegBigInt: Property =
    for {
      n <- Gen.long(Range.linear(0L, MaxLongValue)).map(BigInt(_)).log("n")
    } yield {
      val input = NonNegBigInt.unsafeFrom(n)

      val expected = n.asJson
      val actual   = input.asJson
      Result.all(
        List(
          actual ==== expected,
          actual.noSpaces ==== expected.noSpaces,
        )
      )
    }

  def testDecoderNonNegBigInt: Property =
    for {
      n <- Gen.long(Range.linear(0L, MaxLongValue)).map(BigInt(_)).log("n")
    } yield {
      val input = n.asJson

      val expected = NonNegBigInt.from(n)
      val actual   = decode[NonNegBigInt](input.noSpaces)
      actual ==== expected
    }

  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  def testKeyEncoderNonNegBigInt: Property =
    for {
      ns1   <- Gen.long(Range.linear(0L, MaxLongValue)).map(BigInt(_)).list(Range.linear(1, 10)).log("ns1")
      ns2   <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(ns1.length)).log("ns2")
      map   <- Gen.constant(ns1.zip(ns2).toMap).log("map")
      input <- Gen.constant(map.map { case (key, value) => NonNegBigInt.unsafeFrom(key) -> value }).log("input")
    } yield {
      val expected = Json.fromFields(map.map { case (key, value) => key.toString -> value.asJson })

      val actual = input.asJson
      Result.all(
        List(
          actual ==== expected,
          actual.noSpacesSortKeys ==== expected.noSpacesSortKeys,
        )
      )
    }

  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  def testKeyDecoderNonNegBigInt: Property =
    for {
      ns1      <- Gen.long(Range.linear(0L, MaxLongValue)).map(BigInt(_)).list(Range.linear(1, 10)).log("ns1")
      ns2      <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(ns1.length)).log("ns2")
      map      <- Gen.constant(ns1.zip(ns2).toMap).log("map")
      expected <- Gen.constant(map.map { case (key, value) => NonNegBigInt.unsafeFrom(key) -> value }).log("expected")
    } yield {
      val input  = Json.fromFields(map.map { case (key, value) => key.toString -> value.asJson })
      val actual = decode[Map[NonNegBigInt, Int]](input.noSpaces)

      actual ==== expected.asRight
    }

  //

  def testEncoderPosBigInt: Property =
    for {
      n <- Gen.long(Range.linear(1L, MaxLongValue)).map(BigInt(_)).log("n")
    } yield {
      val input = PosBigInt.unsafeFrom(n)

      val expected = n.asJson
      val actual   = input.asJson
      Result.all(
        List(
          actual ==== expected,
          actual.noSpaces ==== expected.noSpaces,
        )
      )
    }

  def testDecoderPosBigInt: Property =
    for {
      n <- Gen.long(Range.linear(1L, MaxLongValue)).map(BigInt(_)).log("n")
    } yield {
      val input = n.asJson

      val expected = PosBigInt.from(n)
      val actual   = decode[PosBigInt](input.noSpaces)
      actual ==== expected
    }

  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  def testKeyEncoderPosBigInt: Property =
    for {
      ns1   <- Gen.long(Range.linear(1L, MaxLongValue)).map(BigInt(_)).list(Range.linear(1, 10)).log("ns1")
      ns2   <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(ns1.length)).log("ns2")
      map   <- Gen.constant(ns1.zip(ns2).toMap).log("map")
      input <- Gen.constant(map.map { case (key, value) => PosBigInt.unsafeFrom(key) -> value }).log("input")
    } yield {
      val expected = Json.fromFields(map.map { case (key, value) => key.toString -> value.asJson })

      val actual = input.asJson
      Result.all(
        List(
          actual ==== expected,
          actual.noSpacesSortKeys ==== expected.noSpacesSortKeys,
        )
      )
    }

  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  def testKeyDecoderPosBigInt: Property =
    for {
      ns1      <- Gen.long(Range.linear(1L, MaxLongValue)).map(BigInt(_)).list(Range.linear(1, 10)).log("ns1")
      ns2      <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(ns1.length)).log("ns2")
      map      <- Gen.constant(ns1.zip(ns2).toMap).log("map")
      expected <- Gen.constant(map.map { case (key, value) => PosBigInt.unsafeFrom(key) -> value }).log("expected")
    } yield {
      val input  = Json.fromFields(map.map { case (key, value) => key.toString -> value.asJson })
      val actual = decode[Map[PosBigInt, Int]](input.noSpaces)

      actual ==== expected.asRight
    }

  //

  def testEncoderNonPosBigInt: Property =
    for {
      n <- Gen.long(Range.linear(0L, MinLongValue)).map(BigInt(_)).log("n")
    } yield {
      val input = NonPosBigInt.unsafeFrom(n)

      val expected = n.asJson
      val actual   = input.asJson
      Result.all(
        List(
          actual ==== expected,
          actual.noSpaces ==== expected.noSpaces,
        )
      )
    }

  def testDecoderNonPosBigInt: Property =
    for {
      n <- Gen.long(Range.linear(0L, MinLongValue)).map(BigInt(_)).log("n")
    } yield {
      val input = n.asJson

      val expected = NonPosBigInt.from(n)
      val actual   = decode[NonPosBigInt](input.noSpaces)
      actual ==== expected
    }

  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  def testKeyEncoderNonPosBigInt: Property =
    for {
      ns1   <- Gen.long(Range.linear(0L, MinLongValue)).map(BigInt(_)).list(Range.linear(1, 10)).log("ns1")
      ns2   <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(ns1.length)).log("ns2")
      map   <- Gen.constant(ns1.zip(ns2).toMap).log("map")
      input <- Gen.constant(map.map { case (key, value) => NonPosBigInt.unsafeFrom(key) -> value }).log("input")
    } yield {
      val expected = Json.fromFields(map.map { case (key, value) => key.toString -> value.asJson })

      val actual = input.asJson
      Result.all(
        List(
          actual ==== expected,
          actual.noSpacesSortKeys ==== expected.noSpacesSortKeys,
        )
      )
    }

  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  def testKeyDecoderNonPosBigInt: Property =
    for {
      ns1      <- Gen.long(Range.linear(0L, MinLongValue)).map(BigInt(_)).list(Range.linear(1, 10)).log("ns1")
      ns2      <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(ns1.length)).log("ns2")
      map      <- Gen.constant(ns1.zip(ns2).toMap).log("map")
      expected <- Gen.constant(map.map { case (key, value) => NonPosBigInt.unsafeFrom(key) -> value }).log("expected")
    } yield {
      val input  = Json.fromFields(map.map { case (key, value) => key.toString -> value.asJson })
      val actual = decode[Map[NonPosBigInt, Int]](input.noSpaces)

      actual ==== expected.asRight
    }

  //

  def testEncoderNegBigDecimal: Property =
    for {
      n <- Gen.double(Range.linearFrac(-0.0000001d, Double.MinValue)).map(BigDecimal(_)).log("n")
    } yield {
      val input = NegBigDecimal.unsafeFrom(n)

      val expected = n.asJson
      val actual   = input.asJson
      Result.all(
        List(
          actual ==== expected,
          actual.noSpaces ==== expected.noSpaces,
        )
      )
    }

  def testDecoderNegBigDecimal: Property =
    for {
      n <- Gen.double(Range.linearFrac(-0.0000001d, Double.MinValue)).map(BigDecimal(_)).log("n")
    } yield {
      val input = n.asJson

      val expected = NegBigDecimal.from(n)
      val actual   = decode[NegBigDecimal](input.noSpaces)
      actual ==== expected
    }

  //

  def testEncoderNonNegBigDecimal: Property =
    for {
      n <- Gen.double(Range.linearFrac(0d, Double.MaxValue)).map(BigDecimal(_)).log("n")
    } yield {
      val input = NonNegBigDecimal.unsafeFrom(n)

      val expected = n.asJson
      val actual   = input.asJson
      Result.all(
        List(
          actual ==== expected,
          actual.noSpaces ==== expected.noSpaces,
        )
      )
    }

  def testDecoderNonNegBigDecimal: Property =
    for {
      n <- Gen.double(Range.linearFrac(0d, Double.MaxValue)).map(BigDecimal(_)).log("n")
    } yield {
      val input = n.asJson

      val expected = NonNegBigDecimal.from(n)
      val actual   = decode[NonNegBigDecimal](input.noSpaces)
      actual ==== expected
    }

  //

  def testEncoderPosBigDecimal: Property =
    for {
      n <- Gen.double(Range.linearFrac(0.0000001d, Double.MaxValue)).map(BigDecimal(_)).log("n")
    } yield {
      val input = PosBigDecimal.unsafeFrom(n)

      val expected = n.asJson
      val actual   = input.asJson
      Result.all(
        List(
          actual ==== expected,
          actual.noSpaces ==== expected.noSpaces,
        )
      )
    }

  def testDecoderPosBigDecimal: Property =
    for {
      n <- Gen.double(Range.linearFrac(0.0000001d, Double.MaxValue)).map(BigDecimal(_)).log("n")
    } yield {
      val input = n.asJson

      val expected = PosBigDecimal.from(n)
      val actual   = decode[PosBigDecimal](input.noSpaces)
      actual ==== expected
    }

  //

  def testEncoderNonPosBigDecimal: Property =
    for {
      n <- Gen.double(Range.linearFrac(0d, Double.MinValue)).map(BigDecimal(_)).log("n")
    } yield {
      val input = NonPosBigDecimal.unsafeFrom(n)

      val expected = n.asJson
      val actual   = input.asJson
      Result.all(
        List(
          actual ==== expected,
          actual.noSpaces ==== expected.noSpaces,
        )
      )
    }

  def testDecoderNonPosBigDecimal: Property =
    for {
      n <- Gen.double(Range.linearFrac(0d, Double.MinValue)).map(BigDecimal(_)).log("n")
    } yield {
      val input = n.asJson

      val expected = NonPosBigDecimal.from(n)
      val actual   = decode[NonPosBigDecimal](input.noSpaces)
      actual ==== expected
    }

  //

}
