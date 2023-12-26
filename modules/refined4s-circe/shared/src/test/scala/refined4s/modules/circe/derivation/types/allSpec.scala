package refined4s.modules.circe.derivation.types

import hedgehog.*
import hedgehog.runner.*
import io.circe.*
import io.circe.parser.*
import io.circe.syntax.*
import refined4s.modules.circe.derivation.types.all.given
import refined4s.types.all.*
import refined4s.types.networkGens

/** @author Kevin Lee
  * @since 2023-12-11
  */
object allSpec extends Properties {

  override def tests: List[Test] = List(
    property("test Encoder[NegInt]", testEncoderNegInt),
    property("test Decoder[NegInt]", testDecoderNegInt),
    //
    property("test Encoder[NonNegInt]", testEncoderNonNegInt),
    property("test Decoder[NonNegInt]", testDecoderNonNegInt),
    //
    property("test Encoder[PosInt]", testEncoderPosInt),
    property("test Decoder[PosInt]", testDecoderPosInt),
    //
    property("test Encoder[NonPosInt]", testEncoderNonPosInt),
    property("test Decoder[NonPosInt]", testDecoderNonPosInt),
    //
    property("test Encoder[NegLong]", testEncoderNegLong),
    property("test Decoder[NegLong]", testDecoderNegLong),
    //
    property("test Encoder[NonNegLong]", testEncoderNonNegLong),
    property("test Decoder[NonNegLong]", testDecoderNonNegLong),
    //
    property("test Encoder[PosLong]", testEncoderPosLong),
    property("test Decoder[PosLong]", testDecoderPosLong),
    //
    property("test Encoder[NonPosLong]", testEncoderNonPosLong),
    property("test Decoder[NonPosLong]", testDecoderNonPosLong),
    //
    property("test Encoder[NegShort]", testEncoderNegShort),
    property("test Decoder[NegShort]", testDecoderNegShort),
    //
    property("test Encoder[NonNegShort]", testEncoderNonNegShort),
    property("test Decoder[NonNegShort]", testDecoderNonNegShort),
    //
    property("test Encoder[PosShort]", testEncoderPosShort),
    property("test Decoder[PosShort]", testDecoderPosShort),
    //
    property("test Encoder[NonPosShort]", testEncoderNonPosShort),
    property("test Decoder[NonPosShort]", testDecoderNonPosShort),
    //
    property("test Encoder[NegByte]", testEncoderNegByte),
    property("test Decoder[NegByte]", testDecoderNegByte),
    //
    property("test Encoder[NonNegByte]", testEncoderNonNegByte),
    property("test Decoder[NonNegByte]", testDecoderNonNegByte),
    //
    property("test Encoder[PosByte]", testEncoderPosByte),
    property("test Decoder[PosByte]", testDecoderPosByte),
    //
    property("test Encoder[NonPosByte]", testEncoderNonPosByte),
    property("test Decoder[NonPosByte]", testDecoderNonPosByte),
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
    //
    property("test Encoder[NonNegBigInt]", testEncoderNonNegBigInt),
    property("test Decoder[NonNegBigInt]", testDecoderNonNegBigInt),
    //
    property("test Encoder[PosBigInt]", testEncoderPosBigInt),
    property("test Decoder[PosBigInt]", testDecoderPosBigInt),
    //
    property("test Encoder[NonPosBigInt]", testEncoderNonPosBigInt),
    property("test Decoder[NonPosBigInt]", testDecoderNonPosBigInt),
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
    property("test Encoder[NonEmptyString]", testEncoderNonEmptyString),
    property("test Decoder[NonEmptyString]", testDecoderNonEmptyString),
    //
    property("test Encoder[Uri]", testEncoderUri),
    property("test Decoder[Uri]", testDecoderUri),
    //
    property("test Encoder[PortNumber]", testEncoderPortNumber),
    property("test Decoder[PortNumber]", testDecoderPortNumber),
    //
    property("test Encoder[SystemPortNumber]", testEncoderSystemPortNumber),
    property("test Decoder[SystemPortNumber]", testDecoderSystemPortNumber),
    //
    property("test Encoder[NonSystemPortNumber]", testEncoderNonSystemPortNumber),
    property("test Decoder[NonSystemPortNumber]", testDecoderNonSystemPortNumber),
    //
    property("test Encoder[UserPortNumber]", testEncoderUserPortNumber),
    property("test Decoder[UserPortNumber]", testDecoderUserPortNumber),
    //
    property("test Encoder[DynamicPortNumber]", testEncoderDynamicPortNumber),
    property("test Decoder[DynamicPortNumber]", testDecoderDynamicPortNumber),
  )

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

  //

  def testEncoderNegLong: Property =
    for {
      n <- Gen.long(Range.linear(-1L, Long.MinValue)).log("n")
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
      n <- Gen.long(Range.linear(-1L, Long.MinValue)).log("n")
    } yield {
      val input = n.asJson

      val expected = NegLong.from(n)
      val actual   = decode[NegLong](input.noSpaces)
      actual ==== expected
    }

  //

  def testEncoderNonNegLong: Property =
    for {
      n <- Gen.long(Range.linear(0L, Long.MaxValue)).log("n")
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
      n <- Gen.long(Range.linear(0L, Long.MaxValue)).log("n")
    } yield {
      val input = n.asJson

      val expected = NonNegLong.from(n)
      val actual   = decode[NonNegLong](input.noSpaces)
      actual ==== expected
    }

  //

  def testEncoderPosLong: Property =
    for {
      n <- Gen.long(Range.linear(1L, Long.MaxValue)).log("n")
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
      n <- Gen.long(Range.linear(1L, Long.MaxValue)).log("n")
    } yield {
      val input = n.asJson

      val expected = PosLong.from(n)
      val actual   = decode[PosLong](input.noSpaces)
      actual ==== expected
    }

  //

  def testEncoderNonPosLong: Property =
    for {
      n <- Gen.long(Range.linear(0L, Long.MinValue)).log("n")
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
      n <- Gen.long(Range.linear(0L, Long.MinValue)).log("n")
    } yield {
      val input = n.asJson

      val expected = NonPosLong.from(n)
      val actual   = decode[NonPosLong](input.noSpaces)
      actual ==== expected
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
      n <- Gen.long(Range.linear(-1L, Long.MinValue)).map(BigInt(_)).log("n")
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
      n <- Gen.long(Range.linear(-1L, Long.MinValue)).map(BigInt(_)).log("n")
    } yield {
      val input = n.asJson

      val expected = NegBigInt.from(n)
      val actual   = decode[NegBigInt](input.noSpaces)
      actual ==== expected
    }

  //

  def testEncoderNonNegBigInt: Property =
    for {
      n <- Gen.long(Range.linear(0L, Long.MaxValue)).map(BigInt(_)).log("n")
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
      n <- Gen.long(Range.linear(0L, Long.MaxValue)).map(BigInt(_)).log("n")
    } yield {
      val input = n.asJson

      val expected = NonNegBigInt.from(n)
      val actual   = decode[NonNegBigInt](input.noSpaces)
      actual ==== expected
    }

  //

  def testEncoderPosBigInt: Property =
    for {
      n <- Gen.long(Range.linear(1L, Long.MaxValue)).map(BigInt(_)).log("n")
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
      n <- Gen.long(Range.linear(1L, Long.MaxValue)).map(BigInt(_)).log("n")
    } yield {
      val input = n.asJson

      val expected = PosBigInt.from(n)
      val actual   = decode[PosBigInt](input.noSpaces)
      actual ==== expected
    }

  //

  def testEncoderNonPosBigInt: Property =
    for {
      n <- Gen.long(Range.linear(0L, Long.MinValue)).map(BigInt(_)).log("n")
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
      n <- Gen.long(Range.linear(0L, Long.MinValue)).map(BigInt(_)).log("n")
    } yield {
      val input = n.asJson

      val expected = NonPosBigInt.from(n)
      val actual   = decode[NonPosBigInt](input.noSpaces)
      actual ==== expected
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

  def testEncoderUri: Property =
    for {
      uri <- networkGens.genUriString.log("uri")
    } yield {
      val input = Uri.unsafeFrom(uri)

      val expected = uri.asJson
      val actual   = input.asJson

      Result.all(
        List(
          actual ==== expected,
          actual.noSpaces ==== expected.noSpaces,
        )
      )
    }

  def testDecoderUri: Property =
    for {
      uri <- networkGens.genUriString.log("uri")
    } yield {

      val input = uri.asJson

      val expected = Uri.from(uri)
      val actual   = decode[Uri](input.noSpaces)

      actual ==== expected
    }

  def testEncoderPortNumber: Property =
    for {
      portNumber <- networkGens.genPortNumberInt.log("portNumber")
    } yield {
      val input = PortNumber.unsafeFrom(portNumber)

      val expected = portNumber.asJson
      val actual   = input.asJson

      Result.all(
        List(
          actual ==== expected,
          actual.noSpaces ==== expected.noSpaces,
        )
      )
    }

  def testDecoderPortNumber: Property =
    for {
      portNumber <- networkGens.genPortNumberInt.log("portNumber")
    } yield {

      val input = portNumber.asJson

      val expected = PortNumber.from(portNumber)
      val actual   = decode[PortNumber](input.noSpaces)

      actual ==== expected
    }

  def testEncoderSystemPortNumber: Property =
    for {
      systemPortNumber <- networkGens.genSystemPortNumberInt.log("systemPortNumber")
    } yield {
      val input = SystemPortNumber.unsafeFrom(systemPortNumber)

      val expected = systemPortNumber.asJson
      val actual   = input.asJson

      Result.all(
        List(
          actual ==== expected,
          actual.noSpaces ==== expected.noSpaces,
        )
      )
    }

  def testDecoderSystemPortNumber: Property =
    for {
      systemPortNumber <- networkGens.genSystemPortNumberInt.log("systemPortNumber")
    } yield {

      val input = systemPortNumber.asJson

      val expected = SystemPortNumber.from(systemPortNumber)
      val actual   = decode[SystemPortNumber](input.noSpaces)

      actual ==== expected
    }

  def testEncoderNonSystemPortNumber: Property =
    for {
      nonSystemPortNumber <- networkGens.genNonSystemPortNumberInt.log("nonSystemPortNumber")
    } yield {
      val input = NonSystemPortNumber.unsafeFrom(nonSystemPortNumber)

      val expected = nonSystemPortNumber.asJson
      val actual   = input.asJson

      Result.all(
        List(
          actual ==== expected,
          actual.noSpaces ==== expected.noSpaces,
        )
      )
    }

  def testDecoderNonSystemPortNumber: Property =
    for {
      nonSystemPortNumber <- networkGens.genNonSystemPortNumberInt.log("nonSystemPortNumber")
    } yield {

      val input = nonSystemPortNumber.asJson

      val expected = NonSystemPortNumber.from(nonSystemPortNumber)
      val actual   = decode[NonSystemPortNumber](input.noSpaces)

      actual ==== expected
    }

  def testEncoderUserPortNumber: Property =
    for {
      userPortNumber <- networkGens.genUserPortNumberInt.log("userPortNumber")
    } yield {
      val input = UserPortNumber.unsafeFrom(userPortNumber)

      val expected = userPortNumber.asJson
      val actual   = input.asJson

      Result.all(
        List(
          actual ==== expected,
          actual.noSpaces ==== expected.noSpaces,
        )
      )
    }

  def testDecoderUserPortNumber: Property =
    for {
      userPortNumber <- networkGens.genUserPortNumberInt.log("userPortNumber")
    } yield {

      val input = userPortNumber.asJson

      val expected = UserPortNumber.from(userPortNumber)
      val actual   = decode[UserPortNumber](input.noSpaces)

      actual ==== expected
    }

  def testEncoderDynamicPortNumber: Property =
    for {
      dynamicPortNumber <- networkGens.genDynamicPortNumberInt.log("dynamicPortNumber")
    } yield {
      val input = DynamicPortNumber.unsafeFrom(dynamicPortNumber)

      val expected = dynamicPortNumber.asJson
      val actual   = input.asJson

      Result.all(
        List(
          actual ==== expected,
          actual.noSpaces ==== expected.noSpaces,
        )
      )
    }

  def testDecoderDynamicPortNumber: Property =
    for {
      dynamicPortNumber <- networkGens.genDynamicPortNumberInt.log("dynamicPortNumber")
    } yield {

      val input = dynamicPortNumber.asJson

      val expected = DynamicPortNumber.from(dynamicPortNumber)
      val actual   = decode[DynamicPortNumber](input.noSpaces)

      actual ==== expected
    }

}
