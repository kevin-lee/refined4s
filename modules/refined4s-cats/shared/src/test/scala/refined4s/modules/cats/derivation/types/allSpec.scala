package refined4s.modules.cats.derivation.types

import cats.syntax.all.*
import hedgehog.*
import hedgehog.runner.*
import refined4s.modules.cats.derivation.types.all.given
import refined4s.types.numeric.*
import refined4s.types.network.*
import refined4s.types.strings.*
import refined4s.types.networkGens

import java.util.UUID

/** @author Kevin Lee
  * @since 2023-12-10
  */
@SuppressWarnings(Array("org.wartremover.warts.ToString"))
object allSpec extends Properties {

  override def tests: List[Test] = List(
    property("test Eq[NegInt]", testEqNegInt),
    property("test Show[NegInt]", testShowNegInt),
    //
    property("test Eq[NonNegInt]", testEqNonNegInt),
    property("test Show[NonNegInt]", testShowNonNegInt),
    //
    property("test Eq[PosInt]", testEqPosInt),
    property("test Show[PosInt]", testShowPosInt),
    //
    property("test Eq[NonPosInt]", testEqNonPosInt),
    property("test Show[NonPosInt]", testShowNonPosInt),
    //
    property("test Eq[NegLong]", testEqNegLong),
    property("test Show[NegLong]", testShowNegLong),
    //
    property("test Eq[NonNegLong]", testEqNonNegLong),
    property("test Show[NonNegLong]", testShowNonNegLong),
    //
    property("test Eq[PosLong]", testEqPosLong),
    property("test Show[PosLong]", testShowPosLong),
    //
    property("test Eq[NonPosLong]", testEqNonPosLong),
    property("test Show[NonPosLong]", testShowNonPosLong),
    //
    property("test Eq[NegShort]", testEqNegShort),
    property("test Show[NegShort]", testShowNegShort),
    //
    property("test Eq[NonNegShort]", testEqNonNegShort),
    property("test Show[NonNegShort]", testShowNonNegShort),
    //
    property("test Eq[PosShort]", testEqPosShort),
    property("test Show[PosShort]", testShowPosShort),
    //
    property("test Eq[NonPosShort]", testEqNonPosShort),
    property("test Show[NonPosShort]", testShowNonPosShort),
    //
    property("test Eq[NegByte]", testEqNegByte),
    property("test Show[NegByte]", testShowNegByte),
    //
    property("test Eq[NonNegByte]", testEqNonNegByte),
    property("test Show[NonNegByte]", testShowNonNegByte),
    //
    property("test Eq[PosByte]", testEqPosByte),
    property("test Show[PosByte]", testShowPosByte),
    //
    property("test Eq[NonPosByte]", testEqNonPosByte),
    property("test Show[NonPosByte]", testShowNonPosByte),
    //
    property("test Eq[NegFloat]", testEqNegFloat),
    property("test Show[NegFloat]", testShowNegFloat),
    //
    property("test Eq[NonNegFloat]", testEqNonNegFloat),
    property("test Show[NonNegFloat]", testShowNonNegFloat),
    //
    property("test Eq[PosFloat]", testEqPosFloat),
    property("test Show[PosFloat]", testShowPosFloat),
    //
    property("test Eq[NonPosFloat]", testEqNonPosFloat),
    property("test Show[NonPosFloat]", testShowNonPosFloat),
    //
    property("test Eq[NegDouble]", testEqNegDouble),
    property("test Show[NegDouble]", testShowNegDouble),
    //
    property("test Eq[NonNegDouble]", testEqNonNegDouble),
    property("test Show[NonNegDouble]", testShowNonNegDouble),
    //
    property("test Eq[PosDouble]", testEqPosDouble),
    property("test Show[PosDouble]", testShowPosDouble),
    //
    property("test Eq[NonPosDouble]", testEqNonPosDouble),
    property("test Show[NonPosDouble]", testShowNonPosDouble),
    //
    property("test Eq[NegBigInt]", testEqNegBigInt),
    property("test Show[NegBigInt]", testShowNegBigInt),
    //
    property("test Eq[NonNegBigInt]", testEqNonNegBigInt),
    property("test Show[NonNegBigInt]", testShowNonNegBigInt),
    //
    property("test Eq[PosBigInt]", testEqPosBigInt),
    property("test Show[PosBigInt]", testShowPosBigInt),
    //
    property("test Eq[NonPosBigInt]", testEqNonPosBigInt),
    property("test Show[NonPosBigInt]", testShowNonPosBigInt),
    //
    property("test Eq[NegBigDecimal]", testEqNegBigDecimal),
    property("test Show[NegBigDecimal]", testShowNegBigDecimal),
    //
    property("test Eq[NonNegBigDecimal]", testEqNonNegBigDecimal),
    property("test Show[NonNegBigDecimal]", testShowNonNegBigDecimal),
    //
    property("test Eq[PosBigDecimal]", testEqPosBigDecimal),
    property("test Show[PosBigDecimal]", testShowPosBigDecimal),
    //
    property("test Eq[NonPosBigDecimal]", testEqNonPosBigDecimal),
    property("test Show[NonPosBigDecimal]", testShowNonPosBigDecimal),
    //
    property("test Eq[NonEmptyString]", testEqNonEmptyString),
    property("test Show[NonEmptyString]", testShowNonEmptyString),
    //
    property("test Eq[NonBlankString]", testEqNonBlankString),
    property("test Show[NonBlankString]", testShowNonBlankString),
    //
    property("test Eq[Uuid]", testEqUuid),
    property("test Show[Uuid]", testShowUuid),
    //
    property("test Eq[Uri]", testEqUri),
    property("test Show[Uri]", testShowUri),
    //
    property("test Eq[Url]", testEqUrl),
    property("test Show[Url]", testShowUrl),

    //
    property("test Eq[PortNumber]", testEqPortNumber),
    property("test Show[PortNumber]", testShowPortNumber),
    //
    property("test Eq[SystemPortNumber]", testEqSystemPortNumber),
    property("test Show[SystemPortNumber]", testShowSystemPortNumber),
    //
    property("test Eq[NonSystemPortNumber]", testEqNonSystemPortNumber),
    property("test Show[NonSystemPortNumber]", testShowNonSystemPortNumber),
    //
    property("test Eq[UserPortNumber]", testEqUserPortNumber),
    property("test Show[UserPortNumber]", testShowUserPortNumber),
    //
    property("test Eq[DynamicPortNumber]", testEqDynamicPortNumber),
    property("test Show[DynamicPortNumber]", testShowDynamicPortNumber),
  )

  def testEqNegInt: Property =
    for {
      n <- Gen.int(Range.linear(-1, Int.MinValue)).log("n")
    } yield {
      val input = NegInt.unsafeFrom(n)

      val expected = input
      val actual   = input

      Result.diffNamed("NegInt(value) === NegInt(value)", actual, expected)(_ === _)
    }

  def testShowNegInt: Property =
    for {
      n <- Gen.int(Range.linear(-1, Int.MinValue)).log("n")
    } yield {
      val input = NegInt.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.show

      actual ==== expected
    }

  def testEqNonNegInt: Property =
    for {
      n <- Gen.int(Range.linear(0, Int.MaxValue)).log("n")
    } yield {
      val input = NonNegInt.unsafeFrom(n)

      val expected = input
      val actual   = input

      Result.diffNamed("NegInt(value) === NegInt(value)", actual, expected)(_ === _)
    }

  def testShowNonNegInt: Property =
    for {
      n <- Gen.int(Range.linear(0, Int.MaxValue)).log("n")
    } yield {
      val input = NonNegInt.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.show

      actual ==== expected
    }

  def testEqPosInt: Property =
    for {
      n <- Gen.int(Range.linear(1, Int.MaxValue)).log("n")
    } yield {
      val input = PosInt.unsafeFrom(n)

      val expected = input
      val actual   = input

      Result.diffNamed("PosInt(value) === PosInt(value)", actual, expected)(_ === _)
    }

  def testShowPosInt: Property =
    for {
      n <- Gen.int(Range.linear(1, Int.MaxValue)).log("n")
    } yield {
      val input = PosInt.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.show

      actual ==== expected
    }

  def testEqNonPosInt: Property =
    for {
      n <- Gen.int(Range.linear(0, Int.MinValue)).log("n")
    } yield {
      val input = NonPosInt.unsafeFrom(n)

      val expected = input
      val actual   = input

      Result.diffNamed("NonPosInt(value) === NonPosInt(value)", actual, expected)(_ === _)
    }

  def testShowNonPosInt: Property =
    for {
      n <- Gen.int(Range.linear(0, Int.MinValue)).log("n")
    } yield {
      val input = NonPosInt.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.show

      actual ==== expected
    }

  def testEqNegLong: Property =
    for {
      n <- Gen.long(Range.linear(-1L, Long.MinValue)).log("n")
    } yield {
      val input = NegLong.unsafeFrom(n)

      val expected = input
      val actual   = input

      Result.diffNamed("NegLong(value) === NegLong(value)", actual, expected)(_ === _)
    }

  def testShowNegLong: Property =
    for {
      n <- Gen.long(Range.linear(-1L, Long.MinValue)).log("n")
    } yield {
      val input = NegLong.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.show

      actual ==== expected
    }

  def testEqNonNegLong: Property =
    for {
      n <- Gen.long(Range.linear(0L, Long.MaxValue)).log("n")
    } yield {
      val input = NonNegLong.unsafeFrom(n)

      val expected = input
      val actual   = input

      Result.diffNamed("NonNegLong(value) === NonNegLong(value)", actual, expected)(_ === _)
    }

  def testShowNonNegLong: Property =
    for {
      n <- Gen.long(Range.linear(0L, Long.MaxValue)).log("n")
    } yield {
      val input = NonNegLong.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.show

      actual ==== expected
    }

  def testEqPosLong: Property =
    for {
      n <- Gen.long(Range.linear(1L, Long.MaxValue)).log("n")
    } yield {
      val input = PosLong.unsafeFrom(n)

      val expected = input
      val actual   = input

      Result.diffNamed("PosLong(value) === PosLong(value)", actual, expected)(_ === _)
    }

  def testShowPosLong: Property =
    for {
      n <- Gen.long(Range.linear(1L, Long.MaxValue)).log("n")
    } yield {
      val input = PosLong.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.show

      actual ==== expected
    }

  def testEqNonPosLong: Property =
    for {
      n <- Gen.long(Range.linear(0, Long.MinValue)).log("n")
    } yield {
      val input = NonPosLong.unsafeFrom(n)

      val expected = input
      val actual   = input

      Result.diffNamed("NonPosLong(value) === NonPosLong(value)", actual, expected)(_ === _)
    }

  def testShowNonPosLong: Property =
    for {
      n <- Gen.long(Range.linear(0, Long.MinValue)).log("n")
    } yield {
      val input = NonPosLong.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.show

      actual ==== expected
    }

  def testEqNegShort: Property =
    for {
      n <- Gen.short(Range.linear(-1, Short.MinValue)).log("n")
    } yield {
      val input = NegShort.unsafeFrom(n)

      val expected = input
      val actual   = input

      Result.diffNamed("NegShort(value) === NegShort(value)", actual, expected)(_ === _)
    }

  def testShowNegShort: Property =
    for {
      n <- Gen.short(Range.linear(-1, Short.MinValue)).log("n")
    } yield {
      val input = NegShort.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.show

      actual ==== expected
    }

  def testEqNonNegShort: Property =
    for {
      n <- Gen.short(Range.linear(0, Short.MaxValue)).log("n")
    } yield {
      val input = NonNegShort.unsafeFrom(n)

      val expected = input
      val actual   = input

      Result.diffNamed("NonNegShort(value) === NonNegShort(value)", actual, expected)(_ === _)
    }

  def testShowNonNegShort: Property =
    for {
      n <- Gen.short(Range.linear(0, Short.MaxValue)).log("n")
    } yield {
      val input = NonNegShort.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.show

      actual ==== expected
    }

  def testEqPosShort: Property =
    for {
      n <- Gen.short(Range.linear(1, Short.MaxValue)).log("n")
    } yield {
      val input = PosShort.unsafeFrom(n)

      val expected = input
      val actual   = input

      Result.diffNamed("PosShort(value) === PosShort(value)", actual, expected)(_ === _)
    }

  def testShowPosShort: Property =
    for {
      n <- Gen.short(Range.linear(1, Short.MaxValue)).log("n")
    } yield {
      val input = PosShort.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.show

      actual ==== expected
    }

  def testEqNonPosShort: Property =
    for {
      n <- Gen.short(Range.linear(0, Short.MinValue)).log("n")
    } yield {
      val input = NonPosShort.unsafeFrom(n)

      val expected = input
      val actual   = input

      Result.diffNamed("NonPosShort(value) === NonPosShort(value)", actual, expected)(_ === _)
    }

  def testShowNonPosShort: Property =
    for {
      n <- Gen.short(Range.linear(0, Short.MinValue)).log("n")
    } yield {
      val input = NonPosShort.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.show

      actual ==== expected
    }

  def testEqNegByte: Property =
    for {
      n <- Gen.byte(Range.linear(-1, Byte.MinValue)).log("n")
    } yield {
      val input = NegByte.unsafeFrom(n)

      val expected = input
      val actual   = input

      Result.diffNamed("NegByte(value) === NegByte(value)", actual, expected)(_ === _)
    }

  def testShowNegByte: Property =
    for {
      n <- Gen.byte(Range.linear(-1, Byte.MinValue)).log("n")
    } yield {
      val input = NegByte.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.show

      actual ==== expected
    }

  def testEqNonNegByte: Property =
    for {
      n <- Gen.byte(Range.linear(0, Byte.MaxValue)).log("n")
    } yield {
      val input = NonNegByte.unsafeFrom(n)

      val expected = input
      val actual   = input

      Result.diffNamed("NonNegByte(value) === NonNegByte(value)", actual, expected)(_ === _)
    }

  def testShowNonNegByte: Property =
    for {
      n <- Gen.byte(Range.linear(0, Byte.MaxValue)).log("n")
    } yield {
      val input = NonNegByte.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.show

      actual ==== expected
    }

  def testEqPosByte: Property =
    for {
      n <- Gen.byte(Range.linear(1, Byte.MaxValue)).log("n")
    } yield {
      val input = PosByte.unsafeFrom(n)

      val expected = input
      val actual   = input

      Result.diffNamed("PosByte(value) === PosByte(value)", actual, expected)(_ === _)
    }

  def testShowPosByte: Property =
    for {
      n <- Gen.byte(Range.linear(1, Byte.MaxValue)).log("n")
    } yield {
      val input = PosByte.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.show

      actual ==== expected
    }

  def testEqNonPosByte: Property =
    for {
      n <- Gen.byte(Range.linear(0, Byte.MinValue)).log("n")
    } yield {
      val input = NonPosByte.unsafeFrom(n)

      val expected = input
      val actual   = input

      Result.diffNamed("NonPosByte(value) === NonPosByte(value)", actual, expected)(_ === _)
    }

  def testShowNonPosByte: Property =
    for {
      n <- Gen.byte(Range.linear(0, Byte.MinValue)).log("n")
    } yield {
      val input = NonPosByte.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.show

      actual ==== expected
    }

  def testEqNegFloat: Property =
    for {
      n <- Gen.double(Range.linearFrac(-0.00001d, Float.MinValue)).map(_.toFloat).log("n")
    } yield {
      val input = NegFloat.unsafeFrom(n)

      val expected = input
      val actual   = input

      Result.diffNamed("NegFloat(value) === NegFloat(value)", actual, expected)(_ === _)
    }

  def testShowNegFloat: Property =
    for {
      n <- Gen.double(Range.linearFrac(-0.00001d, Float.MinValue)).map(_.toFloat).log("n")
    } yield {
      val input = NegFloat.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.show

      actual ==== expected
    }

  def testEqNonNegFloat: Property =
    for {
      n <- Gen.double(Range.linearFrac(0d, Float.MaxValue)).map(_.toFloat).log("n")
    } yield {
      val input = NonNegFloat.unsafeFrom(n)

      val expected = input
      val actual   = input

      Result.diffNamed("NonNegFloat(value) === NonNegFloat(value)", actual, expected)(_ === _)
    }

  def testShowNonNegFloat: Property =
    for {
      n <- Gen.double(Range.linearFrac(0d, Float.MaxValue)).map(_.toFloat).log("n")
    } yield {
      val input = NonNegFloat.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.show

      actual ==== expected
    }

  def testEqPosFloat: Property =
    for {
      n <- Gen.double(Range.linearFrac(0.0001d, Float.MaxValue)).map(_.toFloat).log("n")
    } yield {
      val input = PosFloat.unsafeFrom(n)

      val expected = input
      val actual   = input

      Result.diffNamed("PosFloat(value) === PosFloat(value)", actual, expected)(_ === _)
    }

  def testShowPosFloat: Property =
    for {
      n <- Gen.double(Range.linearFrac(0.0001d, Float.MaxValue)).map(_.toFloat).log("n")
    } yield {
      val input = PosFloat.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.show

      actual ==== expected
    }

  def testEqNonPosFloat: Property =
    for {
      n <- Gen.double(Range.linearFrac(0d, Float.MinValue)).map(_.toFloat).log("n")
    } yield {
      val input = NonPosFloat.unsafeFrom(n)

      val expected = input
      val actual   = input

      Result.diffNamed("NonPosFloat(value) === NonPosFloat(value)", actual, expected)(_ === _)
    }

  def testShowNonPosFloat: Property =
    for {
      n <- Gen.double(Range.linearFrac(0d, Float.MinValue)).map(_.toFloat).log("n")
    } yield {
      val input = NonPosFloat.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.show

      actual ==== expected
    }

  def testEqNegDouble: Property =
    for {
      n <- Gen.double(Range.linearFrac(-0.000001d, Double.MinValue)).log("n")
    } yield {
      val input = NegDouble.unsafeFrom(n)

      val expected = input
      val actual   = input

      Result.diffNamed("NegDouble(value) === NegDouble(value)", actual, expected)(_ === _)
    }

  def testShowNegDouble: Property =
    for {
      n <- Gen.double(Range.linearFrac(-0.000001d, Double.MinValue)).log("n")
    } yield {
      val input = NegDouble.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.show

      actual ==== expected
    }

  def testEqNonNegDouble: Property =
    for {
      n <- Gen.double(Range.linearFrac(0d, Double.MaxValue)).log("n")
    } yield {
      val input = NonNegDouble.unsafeFrom(n)

      val expected = input
      val actual   = input

      Result.diffNamed("NonNegDouble(value) === NonNegDouble(value)", actual, expected)(_ === _)
    }

  def testShowNonNegDouble: Property =
    for {
      n <- Gen.double(Range.linearFrac(0d, Double.MaxValue)).log("n")
    } yield {
      val input = NonNegDouble.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.show

      actual ==== expected
    }

  def testEqPosDouble: Property =
    for {
      n <- Gen.double(Range.linearFrac(0.000001d, Double.MaxValue)).log("n")
    } yield {
      val input = PosDouble.unsafeFrom(n)

      val expected = input
      val actual   = input

      Result.diffNamed("PosDouble(value) === PosDouble(value)", actual, expected)(_ === _)
    }

  def testShowPosDouble: Property =
    for {
      n <- Gen.double(Range.linearFrac(0.000001d, Double.MaxValue)).log("n")
    } yield {
      val input = PosDouble.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.show

      actual ==== expected
    }

  def testEqNonPosDouble: Property =
    for {
      n <- Gen.double(Range.linearFrac(0d, Double.MinValue)).log("n")
    } yield {
      val input = NonPosDouble.unsafeFrom(n)

      val expected = input
      val actual   = input

      Result.diffNamed("NonPosDouble(value) === NonPosDouble(value)", actual, expected)(_ === _)
    }

  def testShowNonPosDouble: Property =
    for {
      n <- Gen.double(Range.linearFrac(0d, Double.MinValue)).log("n")
    } yield {
      val input = NonPosDouble.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.show

      actual ==== expected
    }

  def testEqNegBigInt: Property =
    for {
      n <- Gen.long(Range.linear(-1L, Long.MinValue)).map(BigInt(_)).log("n")
    } yield {
      val input = NegBigInt.unsafeFrom(n)

      val expected = input
      val actual   = input

      Result.diffNamed("NegBigInt(value) === NegBigInt(value)", actual, expected)(_ === _)
    }

  def testShowNegBigInt: Property =
    for {
      n <- Gen.long(Range.linear(-1L, Long.MinValue)).map(BigInt(_)).log("n")
    } yield {
      val input = NegBigInt.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.show

      actual ==== expected
    }

  def testEqNonNegBigInt: Property =
    for {
      n <- Gen.long(Range.linear(0L, Long.MaxValue)).map(BigInt(_)).log("n")
    } yield {
      val input = NonNegBigInt.unsafeFrom(n)

      val expected = input
      val actual   = input

      Result.diffNamed("NonNegBigInt(value) === NonNegBigInt(value)", actual, expected)(_ === _)
    }

  def testShowNonNegBigInt: Property =
    for {
      n <- Gen.long(Range.linear(0L, Long.MaxValue)).map(BigInt(_)).log("n")
    } yield {
      val input = NonNegBigInt.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.show

      actual ==== expected
    }

  def testEqPosBigInt: Property =
    for {
      n <- Gen.long(Range.linear(1L, Long.MaxValue)).map(BigInt(_)).log("n")
    } yield {
      val input = PosBigInt.unsafeFrom(n)

      val expected = input
      val actual   = input

      Result.diffNamed("PosBigInt(value) === PosBigInt(value)", actual, expected)(_ === _)
    }

  def testShowPosBigInt: Property =
    for {
      n <- Gen.long(Range.linear(1L, Long.MaxValue)).map(BigInt(_)).log("n")
    } yield {
      val input = PosBigInt.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.show

      actual ==== expected
    }

  def testEqNonPosBigInt: Property =
    for {
      n <- Gen.long(Range.linear(0L, Long.MinValue)).map(BigInt(_)).log("n")
    } yield {
      val input = NonPosBigInt.unsafeFrom(n)

      val expected = input
      val actual   = input

      Result.diffNamed("NonPosBigInt(value) === NonPosBigInt(value)", actual, expected)(_ === _)
    }

  def testShowNonPosBigInt: Property =
    for {
      n <- Gen.long(Range.linear(0L, Long.MinValue)).map(BigInt(_)).log("n")
    } yield {
      val input = NonPosBigInt.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.show

      actual ==== expected
    }

  def testEqNegBigDecimal: Property =
    for {
      n <- Gen.double(Range.linearFrac(-0.0000001d, Double.MinValue)).map(BigDecimal(_)).log("n")
    } yield {
      val input = NegBigDecimal.unsafeFrom(n)

      val expected = input
      val actual   = input

      Result.diffNamed("NegBigDecimal(value) === NegBigDecimal(value)", actual, expected)(_ === _)
    }

  def testShowNegBigDecimal: Property =
    for {
      n <- Gen.double(Range.linearFrac(-0.0000001d, Double.MinValue)).map(BigDecimal(_)).log("n")
    } yield {
      val input = NegBigDecimal.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.show

      actual ==== expected
    }

  def testEqNonNegBigDecimal: Property =
    for {
      n <- Gen.double(Range.linearFrac(0d, Double.MaxValue)).map(BigDecimal(_)).log("n")
    } yield {
      val input = NonNegBigDecimal.unsafeFrom(n)

      val expected = input
      val actual   = input

      Result.diffNamed("NonNegBigDecimal(value) === NonNegBigDecimal(value)", actual, expected)(_ === _)
    }

  def testShowNonNegBigDecimal: Property =
    for {
      n <- Gen.double(Range.linearFrac(0d, Double.MaxValue)).map(BigDecimal(_)).log("n")
    } yield {
      val input = NonNegBigDecimal.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.show

      actual ==== expected
    }

  def testEqPosBigDecimal: Property =
    for {
      n <- Gen.double(Range.linearFrac(0.0000001d, Double.MaxValue)).map(BigDecimal(_)).log("n")
    } yield {
      val input = PosBigDecimal.unsafeFrom(n)

      val expected = input
      val actual   = input

      Result.diffNamed("PosBigDecimal(value) === PosBigDecimal(value)", actual, expected)(_ === _)
    }

  def testShowPosBigDecimal: Property =
    for {
      n <- Gen.double(Range.linearFrac(0.0000001d, Double.MaxValue)).map(BigDecimal(_)).log("n")
    } yield {
      val input = PosBigDecimal.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.show

      actual ==== expected
    }

  def testEqNonPosBigDecimal: Property =
    for {
      n <- Gen.double(Range.linearFrac(0d, Double.MinValue)).map(BigDecimal(_)).log("n")
    } yield {
      val input = NonPosBigDecimal.unsafeFrom(n)

      val expected = input
      val actual   = input

      Result.diffNamed("NonPosBigDecimal(value) === NonPosBigDecimal(value)", actual, expected)(_ === _)
    }

  def testShowNonPosBigDecimal: Property =
    for {
      n <- Gen.double(Range.linearFrac(0d, Double.MinValue)).map(BigDecimal(_)).log("n")
    } yield {
      val input = NonPosBigDecimal.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.show

      actual ==== expected
    }

  ///

  def testEqNonEmptyString: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
    } yield {
      val input = NonEmptyString.unsafeFrom(s)

      val expected = input
      val actual   = input

      Result.diffNamed("NonEmptyString(value) === NonEmptyString(value)", actual, expected)(_ === _)
    }

  def testShowNonEmptyString: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
    } yield {
      val input = NonEmptyString.unsafeFrom(s)

      val expected = s
      val actual   = input.show

      actual ==== expected
    }

  ///

  def testEqNonBlankString: Property =
    for {
      nonWhitespaceString <- Gen.string(hedgehog.extra.Gens.genNonWhitespaceChar, Range.linear(1, 10)).log("nonWhitespaceString")
      whitespaceString    <- Gen
                               .string(
                                 hedgehog.extra.Gens.genCharByRange(refined4s.types.strings.WhitespaceCharRange),
                                 Range.linear(1, 10),
                               )
                               .log("whitespaceString")

      s <- Gen.constant(scala.util.Random.shuffle((nonWhitespaceString + whitespaceString).toList).mkString).log("s")
    } yield {
      val input = NonBlankString.unsafeFrom(s)

      val expected = input
      val actual   = input

      Result.diffNamed("NonEmptyString(value) === NonEmptyString(value)", actual, expected)(_ === _)
    }

  def testShowNonBlankString: Property =
    for {
      nonWhitespaceString <- Gen.string(hedgehog.extra.Gens.genNonWhitespaceChar, Range.linear(1, 10)).log("nonWhitespaceString")
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
      val actual   = input.show

      actual ==== expected
    }

  ///

  def testEqUuid: Property =
    for {
      uuid1 <- Gen.constant(UUID.randomUUID()).log("uuid1")
      uuid2 <- Gen.constant(UUID.randomUUID()).log("uuid2")
    } yield {
      val input1 = Uuid(uuid1)
      val input2 = Uuid(uuid2)

      val expected1 = input1
      val actual1   = input1

      Result.all(
        List(
          Result.diffNamed("Uuid(value) === Uuid(value)", actual1, expected1)(_ === _),
          Result.diffNamed("Uuid(value) =!= Uuid(different value)", input1, input2)(_ =!= _),
        )
      )
    }

  def testShowUuid: Property =
    for {
      uuid <- Gen.constant(UUID.randomUUID()).log("uuid")
    } yield {
      val input = Uuid(uuid)

      val expected = uuid.show
      val actual   = input.show

      actual ==== expected
    }

  /* network.Uri */

  def testEqUri: Property =
    for {
      uri <- networkGens.genUriString.log("uri")
    } yield {
      val input = Uri.unsafeFrom(uri)

      val expected = input
      val actual   = input

      Result.diffNamed("Uri(value) === Uri(value)", actual, expected)(_ === _)
    }

  def testShowUri: Property =
    for {
      uri <- networkGens.genUriString.log("uri")
    } yield {
      val input = Uri.unsafeFrom(uri)

      val expected = uri
      val actual   = input.show

      actual ==== expected
    }

  /* network.Url */

  def testEqUrl: Property =
    for {
      url <- networkGens.genUrlString.log("url")
    } yield {
      val input = Url.unsafeFrom(url)

      val expected = input
      val actual   = input

      Result.diffNamed("Uri(value) === Uri(value)", actual, expected)(_ === _)
    }

  def testShowUrl: Property =
    for {
      url <- networkGens.genUrlString.log("url")
    } yield {
      val input = Url.unsafeFrom(url)

      val expected = url
      val actual   = input.show

      actual ==== expected
    }

  /* network ports */

  def testEqPortNumber: Property =
    for {
      portNumber1 <- networkGens.genPortNumberInt.log("portNumber1")
      portNumber2 <- Gen
                       .constant(portNumber1)
                       .map { n =>
                         if n === 0 then n + 1 else n - 1
                       }
                       .log("portNumber2")
    } yield {
      val input1 = PortNumber.unsafeFrom(portNumber1)
      val input2 = PortNumber.unsafeFrom(portNumber2)
      Result.all(
        List(
          Result.diffNamed("Comparing the same objects with ===", input1, input1)(_ === _),
          Result.diffNamed("Comparing the different objects with =!=", input2, input2)(_ === _),
        )
      )
    }

  def testShowPortNumber: Property =
    for {
      portNumber <- networkGens.genPortNumberInt.log("portNumber")
    } yield {

      val input = PortNumber.unsafeFrom(portNumber)

      val expected = portNumber.toString
      val actual   = input.show

      actual ==== expected
    }

  def testEqSystemPortNumber: Property =
    for {
      systemPortNumber1 <- networkGens.genSystemPortNumberInt.log("systemPortNumber1")
      systemPortNumber2 <- Gen
                             .constant(systemPortNumber1)
                             .map { n =>
                               if n === 0 then n + 1 else n - 1
                             }
                             .log("systemPortNumber2")
    } yield {
      val input1 = SystemPortNumber.unsafeFrom(systemPortNumber1)
      val input2 = SystemPortNumber.unsafeFrom(systemPortNumber2)
      Result.all(
        List(
          Result.diffNamed("Comparing the same objects with ===", input1, input1)(_ === _),
          Result.diffNamed("Comparing the different objects with =!=", input2, input2)(_ === _),
        )
      )
    }

  def testShowSystemPortNumber: Property =
    for {
      systemPortNumber <- networkGens.genSystemPortNumberInt.log("systemPortNumber")
    } yield {

      val input = SystemPortNumber.unsafeFrom(systemPortNumber)

      val expected = systemPortNumber.toString
      val actual   = input.show

      actual ==== expected
    }

  def testEqNonSystemPortNumber: Property =
    for {
      nonSystemPortNumber1 <- networkGens.genNonSystemPortNumberInt.log("nonSystemPortNumber1")
      nonSystemPortNumber2 <- Gen
                                .constant(nonSystemPortNumber1)
                                .map { n =>
                                  if n === 1024 then n + 1 else n - 1
                                }
                                .log("nonSystemPortNumber2")
    } yield {
      val input1 = NonSystemPortNumber.unsafeFrom(nonSystemPortNumber1)
      val input2 = NonSystemPortNumber.unsafeFrom(nonSystemPortNumber2)
      Result.all(
        List(
          Result.diffNamed("Comparing the same objects with ===", input1, input1)(_ === _),
          Result.diffNamed("Comparing the different objects with =!=", input2, input2)(_ === _),
        )
      )
    }

  def testShowNonSystemPortNumber: Property =
    for {
      nonSystemPortNumber <- networkGens.genNonSystemPortNumberInt.log("nonSystemPortNumber")
    } yield {

      val input = NonSystemPortNumber.unsafeFrom(nonSystemPortNumber)

      val expected = nonSystemPortNumber.toString
      val actual   = input.show

      actual ==== expected
    }

  def testEqUserPortNumber: Property =
    for {
      userPortNumber1 <- networkGens.genUserPortNumberInt.log("userPortNumber1")
      userPortNumber2 <- Gen
                           .constant(userPortNumber1)
                           .map { n =>
                             if n === 1024 then n + 1 else n - 1
                           }
                           .log("userPortNumber2")
    } yield {
      val input1 = UserPortNumber.unsafeFrom(userPortNumber1)
      val input2 = UserPortNumber.unsafeFrom(userPortNumber2)
      Result.all(
        List(
          Result.diffNamed("Comparing the same objects with ===", input1, input1)(_ === _),
          Result.diffNamed("Comparing the different objects with =!=", input2, input2)(_ === _),
        )
      )
    }

  def testShowUserPortNumber: Property =
    for {
      userPortNumber <- networkGens.genUserPortNumberInt.log("userPortNumber")
    } yield {

      val input = UserPortNumber.unsafeFrom(userPortNumber)

      val expected = userPortNumber.toString
      val actual   = input.show

      actual ==== expected
    }

  def testEqDynamicPortNumber: Property =
    for {
      dynamicPortNumber1 <- networkGens.genDynamicPortNumberInt.log("dynamicPortNumber1")
      dynamicPortNumber2 <- Gen
                              .constant(dynamicPortNumber1)
                              .map { n =>
                                if n === 49152 then n + 1 else n - 1
                              }
                              .log("userPortNumber2")
    } yield {
      val input1 = DynamicPortNumber.unsafeFrom(dynamicPortNumber1)
      val input2 = DynamicPortNumber.unsafeFrom(dynamicPortNumber2)
      Result.all(
        List(
          Result.diffNamed("Comparing the same objects with ===", input1, input1)(_ === _),
          Result.diffNamed("Comparing the different objects with =!=", input2, input2)(_ === _),
        )
      )
    }

  def testShowDynamicPortNumber: Property =
    for {
      dynamicPortNumber <- networkGens.genDynamicPortNumberInt.log("dynamicPortNumber")
    } yield {

      val input = DynamicPortNumber.unsafeFrom(dynamicPortNumber)

      val expected = dynamicPortNumber.toString
      val actual   = input.show

      actual ==== expected
    }

}
