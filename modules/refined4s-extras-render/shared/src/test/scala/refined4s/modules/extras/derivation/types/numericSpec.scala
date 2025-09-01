package refined4s.modules.extras.derivation.types

import extras.render.syntax.*
import hedgehog.*
import hedgehog.runner.*
import refined4s.types.numeric.*

trait numericSpec {

  protected val numericTypeClasses: refined4s.modules.extras.derivation.types.numeric
  import numericTypeClasses.given

  def allTests: List[Test] = List(
    property("test Render[NegInt]", testRenderNegInt),
    //
    property("test Render[NonNegInt]", testRenderNonNegInt),
    //
    property("test Render[PosInt]", testRenderPosInt),
    //
    property("test Render[NonPosInt]", testRenderNonPosInt),
    //
    property("test Render[NegLong]", testRenderNegLong),
    //
    property("test Render[NonNegLong]", testRenderNonNegLong),
    //
    property("test Render[PosLong]", testRenderPosLong),
    //
    property("test Render[NonPosLong]", testRenderNonPosLong),
    //
    property("test Render[NegShort]", testRenderNegShort),
    //
    property("test Render[NonNegShort]", testRenderNonNegShort),
    //
    property("test Render[PosShort]", testRenderPosShort),
    //
    property("test Render[NonPosShort]", testRenderNonPosShort),
    //
    property("test Render[NegByte]", testRenderNegByte),
    //
    property("test Render[NonNegByte]", testRenderNonNegByte),
    //
    property("test Render[PosByte]", testRenderPosByte),
    //
    property("test Render[NonPosByte]", testRenderNonPosByte),
    //
    property("test Render[NegFloat]", testRenderNegFloat),
    //
    property("test Render[NonNegFloat]", testRenderNonNegFloat),
    //
    property("test Render[PosFloat]", testRenderPosFloat),
    //
    property("test Render[NonPosFloat]", testRenderNonPosFloat),
    //
    property("test Render[NegDouble]", testRenderNegDouble),
    //
    property("test Render[NonNegDouble]", testRenderNonNegDouble),
    //
    property("test Render[PosDouble]", testRenderPosDouble),
    //
    property("test Render[NonPosDouble]", testRenderNonPosDouble),
    //
    property("test Render[NegBigInt]", testRenderNegBigInt),
    //
    property("test Render[NonNegBigInt]", testRenderNonNegBigInt),
    //
    property("test Render[PosBigInt]", testRenderPosBigInt),
    //
    property("test Render[NonPosBigInt]", testRenderNonPosBigInt),
    //
    property("test Render[NegBigDecimal]", testRenderNegBigDecimal),
    //
    property("test Render[NonNegBigDecimal]", testRenderNonNegBigDecimal),
    //
    property("test Render[PosBigDecimal]", testRenderPosBigDecimal),
    //
    property("test Render[NonPosBigDecimal]", testRenderNonPosBigDecimal),
  )

  def testRenderNegInt: Property =
    for {
      n <- Gen.int(Range.linear(-1, Int.MinValue)).log("n")
    } yield {
      val input = NegInt.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.render

      actual ==== expected
    }

  def testRenderNonNegInt: Property =
    for {
      n <- Gen.int(Range.linear(0, Int.MaxValue)).log("n")
    } yield {
      val input = NonNegInt.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.render

      actual ==== expected
    }

  def testRenderPosInt: Property =
    for {
      n <- Gen.int(Range.linear(1, Int.MaxValue)).log("n")
    } yield {
      val input = PosInt.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.render

      actual ==== expected
    }

  def testRenderNonPosInt: Property =
    for {
      n <- Gen.int(Range.linear(0, Int.MinValue)).log("n")
    } yield {
      val input = NonPosInt.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.render

      actual ==== expected
    }

  def testRenderNegLong: Property =
    for {
      n <- Gen.long(Range.linear(-1L, Long.MinValue)).log("n")
    } yield {
      val input = NegLong.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.render

      actual ==== expected
    }

  def testRenderNonNegLong: Property =
    for {
      n <- Gen.long(Range.linear(0L, Long.MaxValue)).log("n")
    } yield {
      val input = NonNegLong.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.render

      actual ==== expected
    }

  def testRenderPosLong: Property =
    for {
      n <- Gen.long(Range.linear(1L, Long.MaxValue)).log("n")
    } yield {
      val input = PosLong.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.render

      actual ==== expected
    }

  def testRenderNonPosLong: Property =
    for {
      n <- Gen.long(Range.linear(0, Long.MinValue)).log("n")
    } yield {
      val input = NonPosLong.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.render

      actual ==== expected
    }

  def testRenderNegShort: Property =
    for {
      n <- Gen.short(Range.linear(-1, Short.MinValue)).log("n")
    } yield {
      val input = NegShort.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.render

      actual ==== expected
    }

  def testRenderNonNegShort: Property =
    for {
      n <- Gen.short(Range.linear(0, Short.MaxValue)).log("n")
    } yield {
      val input = NonNegShort.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.render

      actual ==== expected
    }

  def testRenderPosShort: Property =
    for {
      n <- Gen.short(Range.linear(1, Short.MaxValue)).log("n")
    } yield {
      val input = PosShort.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.render

      actual ==== expected
    }

  def testRenderNonPosShort: Property =
    for {
      n <- Gen.short(Range.linear(0, Short.MinValue)).log("n")
    } yield {
      val input = NonPosShort.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.render

      actual ==== expected
    }

  def testRenderNegByte: Property =
    for {
      n <- Gen.byte(Range.linear(-1, Byte.MinValue)).log("n")
    } yield {
      val input = NegByte.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.render

      actual ==== expected
    }

  def testRenderNonNegByte: Property =
    for {
      n <- Gen.byte(Range.linear(0, Byte.MaxValue)).log("n")
    } yield {
      val input = NonNegByte.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.render

      actual ==== expected
    }

  def testRenderPosByte: Property =
    for {
      n <- Gen.byte(Range.linear(1, Byte.MaxValue)).log("n")
    } yield {
      val input = PosByte.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.render

      actual ==== expected
    }

  def testRenderNonPosByte: Property =
    for {
      n <- Gen.byte(Range.linear(0, Byte.MinValue)).log("n")
    } yield {
      val input = NonPosByte.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.render

      actual ==== expected
    }

  def testRenderNegFloat: Property =
    for {
      n <- Gen.double(Range.linearFrac(-0.00001d, Float.MinValue)).map(_.toFloat).log("n")
    } yield {
      val input = NegFloat.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.render

      actual ==== expected
    }

  def testRenderNonNegFloat: Property =
    for {
      n <- Gen.double(Range.linearFrac(0d, Float.MaxValue)).map(_.toFloat).log("n")
    } yield {
      val input = NonNegFloat.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.render

      actual ==== expected
    }

  def testRenderPosFloat: Property =
    for {
      n <- Gen.double(Range.linearFrac(0.0001d, Float.MaxValue)).map(_.toFloat).log("n")
    } yield {
      val input = PosFloat.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.render

      actual ==== expected
    }

  def testRenderNonPosFloat: Property =
    for {
      n <- Gen.double(Range.linearFrac(0d, Float.MinValue)).map(_.toFloat).log("n")
    } yield {
      val input = NonPosFloat.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.render

      actual ==== expected
    }

  def testRenderNegDouble: Property =
    for {
      n <- Gen.double(Range.linearFrac(-0.000001d, Double.MinValue)).log("n")
    } yield {
      val input = NegDouble.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.render

      actual ==== expected
    }

  def testRenderNonNegDouble: Property =
    for {
      n <- Gen.double(Range.linearFrac(0d, Double.MaxValue)).log("n")
    } yield {
      val input = NonNegDouble.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.render

      actual ==== expected
    }

  def testRenderPosDouble: Property =
    for {
      n <- Gen.double(Range.linearFrac(0.000001d, Double.MaxValue)).log("n")
    } yield {
      val input = PosDouble.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.render

      actual ==== expected
    }

  def testRenderNonPosDouble: Property =
    for {
      n <- Gen.double(Range.linearFrac(0d, Double.MinValue)).log("n")
    } yield {
      val input = NonPosDouble.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.render

      actual ==== expected
    }

  def testRenderNegBigInt: Property =
    for {
      n <- Gen.long(Range.linear(-1L, Long.MinValue)).map(BigInt(_)).log("n")
    } yield {
      val input = NegBigInt.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.render

      actual ==== expected
    }

  def testRenderNonNegBigInt: Property =
    for {
      n <- Gen.long(Range.linear(0L, Long.MaxValue)).map(BigInt(_)).log("n")
    } yield {
      val input = NonNegBigInt.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.render

      actual ==== expected
    }

  def testRenderPosBigInt: Property =
    for {
      n <- Gen.long(Range.linear(1L, Long.MaxValue)).map(BigInt(_)).log("n")
    } yield {
      val input = PosBigInt.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.render

      actual ==== expected
    }

  def testRenderNonPosBigInt: Property =
    for {
      n <- Gen.long(Range.linear(0L, Long.MinValue)).map(BigInt(_)).log("n")
    } yield {
      val input = NonPosBigInt.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.render

      actual ==== expected
    }

  def testRenderNegBigDecimal: Property =
    for {
      n <- Gen.double(Range.linearFrac(-0.0000001d, Double.MinValue)).map(BigDecimal(_)).log("n")
    } yield {
      val input = NegBigDecimal.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.render

      actual ==== expected
    }

  def testRenderNonNegBigDecimal: Property =
    for {
      n <- Gen.double(Range.linearFrac(0d, Double.MaxValue)).map(BigDecimal(_)).log("n")
    } yield {
      val input = NonNegBigDecimal.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.render

      actual ==== expected
    }

  def testRenderPosBigDecimal: Property =
    for {
      n <- Gen.double(Range.linearFrac(0.0000001d, Double.MaxValue)).map(BigDecimal(_)).log("n")
    } yield {
      val input = PosBigDecimal.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.render

      actual ==== expected
    }

  def testRenderNonPosBigDecimal: Property =
    for {
      n <- Gen.double(Range.linearFrac(0d, Double.MinValue)).map(BigDecimal(_)).log("n")
    } yield {
      val input = NonPosBigDecimal.unsafeFrom(n)

      val expected = n.toString
      val actual   = input.render

      actual ==== expected
    }

}
object numericSpec extends Properties, numericSpec {

  override protected object numericTypeClasses extends refined4s.modules.extras.derivation.types.numeric

  override def tests: List[Test] = allTests

}
