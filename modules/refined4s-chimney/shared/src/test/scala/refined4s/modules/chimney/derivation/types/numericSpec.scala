package refined4s.modules.chimney.derivation.types

import hedgehog.*
import hedgehog.runner.*
import hedgehog.extra.refined4s.gens.NumGens

import refined4s.*
import refined4s.types.numeric.*

import io.scalaland.chimney
import io.scalaland.chimney.dsl.*

/** @author Kevin Lee
  * @since 2024-08-09
  */
trait numericSpec {

  protected val numericTypeClasses: refined4s.modules.chimney.derivation.types.numeric

  def allTests: List[Test] = List(
    property("test Transformer[NegInt, Int]", testTransformerNegInt),
    property("test PartialTransformer[Int, NegInt]", testPartialTransformerNegInt),
    //
    property("test Transformer[NonNegInt, Int]", testTransformerNonNegInt),
    property("test PartialTransformer[Int, NonNegInt]", testPartialTransformerNonNegInt),
    //
    property("test Transformer[PosInt, Int]", testTransformerPosInt),
    property("test PartialTransformer[Int, PosInt]", testPartialTransformerPosInt),
    //
    property("test Transformer[NonPosInt, Int]", testTransformerNonPosInt),
    property("test PartialTransformer[Int, NonPosInt]", testPartialTransformerNonPosInt),
    //
    property("test Transformer[NegLong, Long]", testTransformerNegLong),
    property("test PartialTransformer[Long, NegLong]", testPartialTransformerNegLong),
    //
    property("test Transformer[NonNegLong, Long]", testTransformerNonNegLong),
    property("test PartialTransformer[Long, NonNegLong]", testPartialTransformerNonNegLong),
    //
    property("test Transformer[PosLong, Long]", testTransformerPosLong),
    property("test PartialTransformer[Long, PosLong]", testPartialTransformerPosLong),
    //
    property("test Transformer[NonPosLong, Long]", testTransformerNonPosLong),
    property("test PartialTransformer[Long, NonPosLong]", testPartialTransformerNonPosLong),
    //
    property("test Transformer[NegShort, Short]", testTransformerNegShort),
    property("test PartialTransformer[Short, NegShort]", testPartialTransformerNegShort),
    //
    property("test Transformer[NonNegShort, Short]", testTransformerNonNegShort),
    property("test PartialTransformer[Short, NonNegShort]", testPartialTransformerNonNegShort),
    //
    property("test Transformer[PosShort, Short]", testTransformerPosShort),
    property("test PartialTransformer[Short, PosShort]", testPartialTransformerPosShort),
    //
    property("test Transformer[NonPosShort, Short]", testTransformerNonPosShort),
    property("test PartialTransformer[Short, NonPosShort]", testPartialTransformerNonPosShort),
    //
    property("test Transformer[NegByte, Byte]", testTransformerNegByte),
    property("test PartialTransformer[Byte, NegByte]", testPartialTransformerNegByte),
    //
    property("test Transformer[NonNegByte, Byte]", testTransformerNonNegByte),
    property("test PartialTransformer[Byte, NonNegByte]", testPartialTransformerNonNegByte),
    //
    property("test Transformer[PosByte, Byte]", testTransformerPosByte),
    property("test PartialTransformer[Byte, PosByte]", testPartialTransformerPosByte),
    //
    property("test Transformer[NonPosByte, Byte]", testTransformerNonPosByte),
    property("test PartialTransformer[Byte, NonPosByte]", testPartialTransformerNonPosByte),
    //
    property("test Transformer[NegFloat, Float]", testTransformerNegFloat),
    property("test PartialTransformer[Float, NegFloat]", testPartialTransformerNegFloat),
    //
    property("test Transformer[NonNegFloat, Float]", testTransformerNonNegFloat),
    property("test PartialTransformer[Float, NonNegFloat]", testPartialTransformerNonNegFloat),
    //
    property("test Transformer[PosFloat, Float]", testTransformerPosFloat),
    property("test PartialTransformer[Float, PosFloat]", testPartialTransformerPosFloat),
    //
    property("test Transformer[NonPosFloat, Float]", testTransformerNonPosFloat),
    property("test PartialTransformer[Float, NonPosFloat]", testPartialTransformerNonPosFloat),
    //
    property("test Transformer[NegDouble, Double]", testTransformerNegDouble),
    property("test PartialTransformer[Double, NegDouble]", testPartialTransformerNegDouble),
    //
    property("test Transformer[NonNegDouble, Double]", testTransformerNonNegDouble),
    property("test PartialTransformer[Double, NonNegDouble]", testPartialTransformerNonNegDouble),
    //
    property("test Transformer[PosDouble, Double]", testTransformerPosDouble),
    property("test PartialTransformer[Double, PosDouble]", testPartialTransformerPosDouble),
    //
    property("test Transformer[NonPosDouble, Double]", testTransformerNonPosDouble),
    property("test PartialTransformer[Double, NonPosDouble]", testPartialTransformerNonPosDouble),
    //
    property("test Transformer[NegBigInt, BigInt]", testTransformerNegBigInt),
    property("test PartialTransformer[BigInt, NegBigInt]", testPartialTransformerNegBigInt),
    //
    property("test Transformer[NonNegBigInt, BigInt]", testTransformerNonNegBigInt),
    property("test PartialTransformer[BigInt, NonNegBigInt]", testPartialTransformerNonNegBigInt),
    //
    property("test Transformer[PosBigInt, BigInt]", testTransformerPosBigInt),
    property("test PartialTransformer[BigInt, PosBigInt]", testPartialTransformerPosBigInt),
    //
    property("test Transformer[NonPosBigInt, BigInt]", testTransformerNonPosBigInt),
    property("test PartialTransformer[BigInt, NonPosBigInt]", testPartialTransformerNonPosBigInt),
    //
    property("test Transformer[NegBigDecimal, BigDecimal]", testTransformerNegBigDecimal),
    property("test PartialTransformer[BigDecimal, NegBigDecimal]", testPartialTransformerNegBigDecimal),
    //
    property("test Transformer[NonNegBigDecimal, BigDecimal]", testTransformerNonNegBigDecimal),
    property("test PartialTransformer[BigDecimal, NonNegBigDecimal]", testPartialTransformerNonNegBigDecimal),
    //
    property("test Transformer[PosBigDecimal, BigDecimal]", testTransformerPosBigDecimal),
    property("test PartialTransformer[BigDecimal, PosBigDecimal]", testPartialTransformerPosBigDecimal),
    //
    property("test Transformer[NonPosBigDecimal, BigDecimal]", testTransformerNonPosBigDecimal),
    property("test PartialTransformer[BigDecimal, NonPosBigDecimal]", testPartialTransformerNonPosBigDecimal),
  )

  import numericTypeClasses.given

  def testTransformerNegInt: Property =
    for {
      n <- NumGens.genNegIntMinTo(NegInt(Int.MinValue)).log("n")
    } yield {
      val input = n

      val expected = n.value

      val actual = input.into[Int].transform

      actual ==== expected
    }

  def testPartialTransformerNegInt: Property =
    for {
      n <- NumGens.genNegIntMinTo(NegInt(Int.MinValue)).log("n")
    } yield {
      val input = n.value

      val expected = chimney.partial.Result.fromValue(n)

      val actual = input.intoPartial[NegInt].transform

      actual ==== expected
    }

  //

  def testTransformerNonNegInt: Property =
    for {
      n <- NumGens.genNonNegIntMaxTo(NonNegInt(Int.MaxValue)).log("n")
    } yield {
      val input = n

      val expected = n.value
      val actual   = input.into[Int].transform

      actual ==== expected
    }

  def testPartialTransformerNonNegInt: Property =
    for {
      n <- NumGens.genNonNegIntMaxTo(NonNegInt(Int.MaxValue)).log("n")
    } yield {
      val input = n.value

      val expected = chimney.partial.Result.fromValue(n)
      val actual   = input.intoPartial[NonNegInt].transform

      actual ==== expected
    }

  //

  def testTransformerPosInt: Property =
    for {
      n <- NumGens.genPosIntMaxTo(PosInt(Int.MaxValue)).log("n")
    } yield {
      val input = n

      val expected = n.value
      val actual   = input.into[Int].transform

      actual ==== expected
    }

  def testPartialTransformerPosInt: Property =
    for {
      n <- NumGens.genPosIntMaxTo(PosInt(Int.MaxValue)).log("n")
    } yield {
      val input = n.value

      val expected = chimney.partial.Result.fromValue(n)
      val actual   = input.intoPartial[PosInt].transform
      actual ==== expected
    }

  //

  def testTransformerNonPosInt: Property =
    for {
      n <- NumGens.genNonPosIntMinTo(NonPosInt(Int.MinValue)).log("n")
    } yield {
      val input = n

      val expected = n.value
      val actual   = input.into[Int].transform

      actual ==== expected
    }

  def testPartialTransformerNonPosInt: Property =
    for {
      n <- NumGens.genNonPosIntMinTo(NonPosInt(Int.MinValue)).log("n")
    } yield {
      val input = n.value

      val expected = chimney.partial.Result.fromValue(n)
      val actual   = input.intoPartial[NonPosInt].transform

      actual ==== expected
    }

  //

  def testTransformerNegLong: Property =
    for {
      n <- NumGens.genNegLongMinTo(NegLong(Long.MinValue)).log("n")
    } yield {
      val input = n

      val expected = n.value
      val actual   = input.into[Long].transform

      actual ==== expected
    }

  def testPartialTransformerNegLong: Property =
    for {
      n <- NumGens.genNegLongMinTo(NegLong(Long.MinValue)).log("n")
    } yield {
      val input = n.value

      val expected = chimney.partial.Result.fromValue(n)
      val actual   = input.intoPartial[NegLong].transform

      actual ==== expected
    }

  //

  def testTransformerNonNegLong: Property =
    for {
      n <- NumGens.genNonNegLongMaxTo(NonNegLong(Long.MaxValue)).log("n")
    } yield {
      val input = n

      val expected = n.value
      val actual   = input.into[Long].transform

      actual ==== expected
    }

  def testPartialTransformerNonNegLong: Property =
    for {
      n <- NumGens.genNonNegLongMaxTo(NonNegLong(Long.MaxValue)).log("n")
    } yield {
      val input = n.value

      val expected = chimney.partial.Result.fromValue(n)
      val actual   = input.intoPartial[NonNegLong].transform
      actual ==== expected
    }

  //

  def testTransformerPosLong: Property =
    for {
      n <- NumGens.genPosLongMaxTo(PosLong(Long.MaxValue)).log("n")
    } yield {
      val input = n

      val expected = n.value
      val actual   = input.into[Long].transform

      actual ==== expected
    }

  def testPartialTransformerPosLong: Property =
    for {
      n <- NumGens.genPosLongMaxTo(PosLong(Long.MaxValue)).log("n")
    } yield {
      val input = n.value

      val expected = chimney.partial.Result.fromValue(n)
      val actual   = input.intoPartial[PosLong].transform

      actual ==== expected
    }

  //

  def testTransformerNonPosLong: Property =
    for {
      n <- NumGens.genNonPosLongMinTo(NonPosLong(Long.MinValue)).log("n")
    } yield {
      val input = n

      val expected = n.value
      val actual   = input.into[Long].transform

      actual ==== expected
    }

  def testPartialTransformerNonPosLong: Property =
    for {
      n <- NumGens.genNonPosLongMinTo(NonPosLong(Long.MinValue)).log("n")
    } yield {
      val input = n.value

      val expected = chimney.partial.Result.fromValue(n)
      val actual   = input.intoPartial[NonPosLong].transform

      actual ==== expected
    }

  //

  def testTransformerNegShort: Property =
    for {
      n <- Gen.short(Range.linear(-1, Short.MinValue)).log("n")
    } yield {
      val input = NegShort.unsafeFrom(n)

      val expected = n
      val actual   = input.into[Short].transform

      actual ==== expected
    }

  def testPartialTransformerNegShort: Property =
    for {
      n <- Gen.short(Range.linear(-1, Short.MinValue)).log("n")
    } yield {
      val input = n

      val expected = chimney.partial.Result.fromValue(NegShort.unsafeFrom(n))
      val actual   = input.intoPartial[NegShort].transform

      actual ==== expected
    }

  //

  def testTransformerNonNegShort: Property =
    for {
      n <- Gen.short(Range.linear(0, Short.MaxValue)).log("n")
    } yield {
      val input = NonNegShort.unsafeFrom(n)

      val expected = n
      val actual   = input.into[Short].transform

      actual ==== expected
    }

  def testPartialTransformerNonNegShort: Property =
    for {
      n <- Gen.short(Range.linear(0, Short.MaxValue)).log("n")
    } yield {
      val input = n

      val expected = chimney.partial.Result.fromValue(NonNegShort.unsafeFrom(n))
      val actual   = input.intoPartial[NonNegShort].transform

      actual ==== expected
    }

  //

  def testTransformerPosShort: Property =
    for {
      n <- Gen.short(Range.linear(1, Short.MaxValue)).log("n")
    } yield {
      val input = PosShort.unsafeFrom(n)

      val expected = n
      val actual   = input.into[Short].transform

      actual ==== expected
    }

  def testPartialTransformerPosShort: Property =
    for {
      n <- Gen.short(Range.linear(1, Short.MaxValue)).log("n")
    } yield {
      val input = n

      val expected = chimney.partial.Result.fromValue(PosShort.unsafeFrom(n))
      val actual   = input.intoPartial[PosShort].transform

      actual ==== expected
    }

  //

  def testTransformerNonPosShort: Property =
    for {
      n <- Gen.short(Range.linear(0, Short.MinValue)).log("n")
    } yield {
      val input = NonPosShort.unsafeFrom(n)

      val expected = n
      val actual   = input.into[Short].transform

      actual ==== expected
    }

  def testPartialTransformerNonPosShort: Property =
    for {
      n <- Gen.short(Range.linear(0, Short.MinValue)).log("n")
    } yield {
      val input = n

      val expected = chimney.partial.Result.fromValue(NonPosShort.unsafeFrom(n))
      val actual   = input.intoPartial[NonPosShort].transform

      actual ==== expected
    }

  //

  def testTransformerNegByte: Property =
    for {
      n <- Gen.byte(Range.linear(-1, Byte.MinValue)).log("n")
    } yield {
      val input = NegByte.unsafeFrom(n)

      val expected = n
      val actual   = input.into[Byte].transform

      actual ==== expected
    }

  def testPartialTransformerNegByte: Property =
    for {
      n <- Gen.byte(Range.linear(-1, Byte.MinValue)).log("n")
    } yield {
      val input = n

      val expected = chimney.partial.Result.fromValue(NegByte.unsafeFrom(n))
      val actual   = input.intoPartial[NegByte].transform

      actual ==== expected
    }

  //

  def testTransformerNonNegByte: Property =
    for {
      n <- Gen.byte(Range.linear(0, Byte.MaxValue)).log("n")
    } yield {
      val input = NonNegByte.unsafeFrom(n)

      val expected = n
      val actual   = input.into[Byte].transform

      actual ==== expected
    }

  def testPartialTransformerNonNegByte: Property =
    for {
      n <- Gen.byte(Range.linear(0, Byte.MaxValue)).log("n")
    } yield {
      val input = n

      val expected = chimney.partial.Result.fromValue(NonNegByte.unsafeFrom(n))
      val actual   = input.intoPartial[NonNegByte].transform

      actual ==== expected
    }

  //

  def testTransformerPosByte: Property =
    for {
      n <- Gen.byte(Range.linear(1, Byte.MaxValue)).log("n")
    } yield {
      val input = PosByte.unsafeFrom(n)

      val expected = n
      val actual   = input.into[Byte].transform

      actual ==== expected
    }

  def testPartialTransformerPosByte: Property =
    for {
      n <- Gen.byte(Range.linear(1, Byte.MaxValue)).log("n")
    } yield {
      val input = n

      val expected = chimney.partial.Result.fromValue(PosByte.unsafeFrom(n))
      val actual   = input.intoPartial[PosByte].transform

      actual ==== expected
    }

  //

  def testTransformerNonPosByte: Property =
    for {
      n <- Gen.byte(Range.linear(0, Byte.MinValue)).log("n")
    } yield {
      val input = NonPosByte.unsafeFrom(n)

      val expected = n
      val actual   = input.into[Byte].transform

      actual ==== expected
    }

  def testPartialTransformerNonPosByte: Property =
    for {
      n <- Gen.byte(Range.linear(0, Byte.MinValue)).log("n")
    } yield {
      val input = n

      val expected = chimney.partial.Result.fromValue(NonPosByte.unsafeFrom(n))
      val actual   = input.intoPartial[NonPosByte].transform

      actual ==== expected
    }

  //

  def testTransformerNegFloat: Property =
    for {
      n <- Gen.double(Range.linearFrac(-0.00001f, Float.MinValue)).map(_.toFloat).log("n")
    } yield {
      val input = NegFloat.unsafeFrom(n)

      val expected = n
      val actual   = input.into[Float].transform

      actual ==== expected
    }

  def testPartialTransformerNegFloat: Property =
    for {
      n <- Gen.double(Range.linearFrac(-0.00001f, Float.MinValue)).map(_.toFloat).log("n")
    } yield {
      val input = n

      val expected = chimney.partial.Result.fromValue(NegFloat.unsafeFrom(n))
      val actual   = input.intoPartial[NegFloat].transform

      actual ==== expected
    }

  //

  def testTransformerNonNegFloat: Property =
    for {
      n <- Gen.double(Range.linearFrac(0f, Float.MaxValue)).map(_.toFloat).log("n")
    } yield {
      val input = NonNegFloat.unsafeFrom(n)

      val expected = n
      val actual   = input.into[Float].transform

      actual ==== expected
    }

  def testPartialTransformerNonNegFloat: Property =
    for {
      n <- Gen.double(Range.linearFrac(0f, Float.MaxValue)).map(_.toFloat).log("n")
    } yield {
      val input = n

      val expected = chimney.partial.Result.fromValue(NonNegFloat.unsafeFrom(n))
      val actual   = input.intoPartial[NonNegFloat].transform

      actual ==== expected
    }

  //

  def testTransformerPosFloat: Property =
    for {
      n <- Gen.double(Range.linearFrac(0.00001f, Float.MaxValue)).map(_.toFloat).log("n")
    } yield {
      val input = PosFloat.unsafeFrom(n)

      val expected = n
      val actual   = input.into[Float].transform

      actual ==== expected
    }

  def testPartialTransformerPosFloat: Property =
    for {
      n <- Gen.double(Range.linearFrac(0.00001f, Float.MaxValue)).map(_.toFloat).log("n")
    } yield {
      val input = n

      val expected = chimney.partial.Result.fromValue(PosFloat.unsafeFrom(n))
      val actual   = input.intoPartial[PosFloat].transform

      actual ==== expected
    }

  //

  def testTransformerNonPosFloat: Property =
    for {
      n <- Gen.double(Range.linearFrac(0f, Float.MinValue)).map(_.toFloat).log("n")
    } yield {
      val input = NonPosFloat.unsafeFrom(n)

      val expected = n
      val actual   = input.into[Float].transform

      actual ==== expected
    }

  def testPartialTransformerNonPosFloat: Property =
    for {
      n <- Gen.double(Range.linearFrac(0f, Float.MinValue)).map(_.toFloat).log("n")
    } yield {
      val input = n

      val expected = chimney.partial.Result.fromValue(NonPosFloat.unsafeFrom(n))
      val actual   = input.intoPartial[NonPosFloat].transform

      actual ==== expected
    }

  //

  def testTransformerNegDouble: Property =
    for {
      n <- Gen.double(Range.linearFrac(-0.0000001d, Double.MinValue)).log("n")
    } yield {
      val input = NegDouble.unsafeFrom(n)

      val expected = n
      val actual   = input.into[Double].transform

      actual ==== expected
    }

  def testPartialTransformerNegDouble: Property =
    for {
      n <- Gen.double(Range.linearFrac(-0.0000001d, Double.MinValue)).log("n")
    } yield {
      val input = n

      val expected = chimney.partial.Result.fromValue(NegDouble.unsafeFrom(n))
      val actual   = input.intoPartial[NegDouble].transform

      actual ==== expected
    }

  //

  def testTransformerNonNegDouble: Property =
    for {
      n <- Gen.double(Range.linearFrac(0d, Double.MaxValue)).log("n")
    } yield {
      val input = NonNegDouble.unsafeFrom(n)

      val expected = n
      val actual   = input.into[Double].transform

      actual ==== expected
    }

  def testPartialTransformerNonNegDouble: Property =
    for {
      n <- Gen.double(Range.linearFrac(0d, Double.MaxValue)).log("n")
    } yield {
      val input = n

      val expected = chimney.partial.Result.fromValue(NonNegDouble.unsafeFrom(n))
      val actual   = input.intoPartial[NonNegDouble].transform

      actual ==== expected
    }

  //

  def testTransformerPosDouble: Property =
    for {
      n <- Gen.double(Range.linearFrac(0.0000001d, Double.MaxValue)).log("n")
    } yield {
      val input = PosDouble.unsafeFrom(n)

      val expected = n
      val actual   = input.into[Double].transform

      actual ==== expected
    }

  def testPartialTransformerPosDouble: Property =
    for {
      n <- Gen.double(Range.linearFrac(0.0000001d, Double.MaxValue)).log("n")
    } yield {
      val input = n

      val expected = chimney.partial.Result.fromValue(PosDouble.unsafeFrom(n))
      val actual   = input.intoPartial[PosDouble].transform

      actual ==== expected
    }

  //

  def testTransformerNonPosDouble: Property =
    for {
      n <- Gen.double(Range.linearFrac(0d, Double.MinValue)).log("n")
    } yield {
      val input = NonPosDouble.unsafeFrom(n)

      val expected = n
      val actual   = input.into[Double].transform

      actual ==== expected
    }

  def testPartialTransformerNonPosDouble: Property =
    for {
      n <- Gen.double(Range.linearFrac(0d, Double.MinValue)).log("n")
    } yield {
      val input = n

      val expected = chimney.partial.Result.fromValue(NonPosDouble.unsafeFrom(n))
      val actual   = input.intoPartial[NonPosDouble].transform

      actual ==== expected
    }

  //

  def testTransformerNegBigInt: Property =
    for {
      n <- Gen.long(Range.linear(-1L, Long.MinValue)).map(BigInt(_)).log("n")
    } yield {
      val input = NegBigInt.unsafeFrom(n)

      val expected = n
      val actual   = input.into[BigInt].transform

      actual ==== expected
    }

  def testPartialTransformerNegBigInt: Property =
    for {
      n <- Gen.long(Range.linear(-1L, Long.MinValue)).map(BigInt(_)).log("n")
    } yield {
      val input = n

      val expected = chimney.partial.Result.fromValue(NegBigInt.unsafeFrom(n))
      val actual   = input.intoPartial[NegBigInt].transform

      actual ==== expected
    }

  //

  def testTransformerNonNegBigInt: Property =
    for {
      n <- Gen.long(Range.linear(0L, Long.MaxValue)).map(BigInt(_)).log("n")
    } yield {
      val input = NonNegBigInt.unsafeFrom(n)

      val expected = n
      val actual   = input.into[BigInt].transform

      actual ==== expected
    }

  def testPartialTransformerNonNegBigInt: Property =
    for {
      n <- Gen.long(Range.linear(0L, Long.MaxValue)).map(BigInt(_)).log("n")
    } yield {
      val input = n

      val expected = chimney.partial.Result.fromValue(NonNegBigInt.unsafeFrom(n))
      val actual   = input.intoPartial[NonNegBigInt].transform

      actual ==== expected
    }

  //

  def testTransformerPosBigInt: Property =
    for {
      n <- Gen.long(Range.linear(1L, Long.MaxValue)).map(BigInt(_)).log("n")
    } yield {
      val input = PosBigInt.unsafeFrom(n)

      val expected = n
      val actual   = input.into[BigInt].transform

      actual ==== expected
    }

  def testPartialTransformerPosBigInt: Property =
    for {
      n <- Gen.long(Range.linear(1L, Long.MaxValue)).map(BigInt(_)).log("n")
    } yield {
      val input = n

      val expected = chimney.partial.Result.fromValue(PosBigInt.unsafeFrom(n))
      val actual   = input.intoPartial[PosBigInt].transform

      actual ==== expected
    }

  //

  def testTransformerNonPosBigInt: Property =
    for {
      n <- Gen.long(Range.linear(0L, Long.MinValue)).map(BigInt(_)).log("n")
    } yield {
      val input = NonPosBigInt.unsafeFrom(n)

      val expected = n
      val actual   = input.into[BigInt].transform

      actual ==== expected
    }

  def testPartialTransformerNonPosBigInt: Property =
    for {
      n <- Gen.long(Range.linear(0L, Long.MinValue)).map(BigInt(_)).log("n")
    } yield {
      val input = n

      val expected = chimney.partial.Result.fromValue(NonPosBigInt.unsafeFrom(n))
      val actual   = input.intoPartial[NonPosBigInt].transform

      actual ==== expected
    }

  //

  def testTransformerNegBigDecimal: Property =
    for {
      n <- Gen.double(Range.linearFrac(-0.0000001d, Double.MinValue)).map(BigDecimal(_)).log("n")
    } yield {
      val input = NegBigDecimal.unsafeFrom(n)

      val expected = n
      val actual   = input.into[BigDecimal].transform

      actual ==== expected
    }

  def testPartialTransformerNegBigDecimal: Property =
    for {
      n <- Gen.double(Range.linearFrac(-0.0000001d, Double.MinValue)).map(BigDecimal(_)).log("n")
    } yield {
      val input = n

      val expected = chimney.partial.Result.fromValue(NegBigDecimal.unsafeFrom(n))
      val actual   = input.intoPartial[NegBigDecimal].transform

      actual ==== expected
    }

  //

  def testTransformerNonNegBigDecimal: Property =
    for {
      n <- Gen.double(Range.linearFrac(0d, Double.MaxValue)).map(BigDecimal(_)).log("n")
    } yield {
      val input = NonNegBigDecimal.unsafeFrom(n)

      val expected = n
      val actual   = input.into[BigDecimal].transform

      actual ==== expected
    }

  def testPartialTransformerNonNegBigDecimal: Property =
    for {
      n <- Gen.double(Range.linearFrac(0d, Double.MaxValue)).map(BigDecimal(_)).log("n")
    } yield {
      val input = n

      val expected = chimney.partial.Result.fromValue(NonNegBigDecimal.unsafeFrom(n))
      val actual   = input.intoPartial[NonNegBigDecimal].transform

      actual ==== expected
    }

  //

  def testTransformerPosBigDecimal: Property =
    for {
      n <- Gen.double(Range.linearFrac(0.0000001d, Double.MaxValue)).map(BigDecimal(_)).log("n")
    } yield {
      val input = PosBigDecimal.unsafeFrom(n)

      val expected = n
      val actual   = input.into[BigDecimal].transform

      actual ==== expected
    }

  def testPartialTransformerPosBigDecimal: Property =
    for {
      n <- Gen.double(Range.linearFrac(0.0000001d, Double.MaxValue)).map(BigDecimal(_)).log("n")
    } yield {
      val input = n

      val expected = chimney.partial.Result.fromValue(PosBigDecimal.unsafeFrom(n))
      val actual   = input.intoPartial[PosBigDecimal].transform

      actual ==== expected
    }

  //

  def testTransformerNonPosBigDecimal: Property =
    for {
      n <- Gen.double(Range.linearFrac(0d, Double.MinValue)).map(BigDecimal(_)).log("n")
    } yield {
      val input = NonPosBigDecimal.unsafeFrom(n)

      val expected = n
      val actual   = input.into[BigDecimal].transform

      actual ==== expected
    }

  def testPartialTransformerNonPosBigDecimal: Property =
    for {
      n <- Gen.double(Range.linearFrac(0d, Double.MinValue)).map(BigDecimal(_)).log("n")
    } yield {
      val input = n

      val expected = chimney.partial.Result.fromValue(NonPosBigDecimal.unsafeFrom(n))
      val actual   = input.intoPartial[NonPosBigDecimal].transform

      actual ==== expected
    }

  //

}
object numericSpec extends Properties, numericSpec {

  override protected val numericTypeClasses: refined4s.modules.chimney.derivation.types.numeric =
    refined4s.modules.chimney.derivation.types.numeric

  override def tests: List[Prop] = allTests

}
