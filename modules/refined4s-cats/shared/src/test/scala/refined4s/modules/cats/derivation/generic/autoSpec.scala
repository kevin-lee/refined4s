package refined4s.modules.cats.derivation.generic

import cats.*
import cats.syntax.all.*
import hedgehog.*
import hedgehog.runner.*
import refined4s.*
import refined4s.modules.cats.derivation.generic.auto.given
import refined4s.types.all.*
import refined4s.types.networkGens

import scala.math.BigDecimal.int2bigDecimal

/** @author Kevin Lee
  * @since 2023-12-10
  */
@SuppressWarnings(Array("org.wartremover.warts.ToString"))
object autoSpec extends Properties {

  override def tests: List[Test] = List(
    property("test Eq[NegInt]", testEqNegInt),
    property("test Order[NegInt]", testOrderNegInt),
    property("test Show[NegInt]", testShowNegInt),
    //
    property("test Eq[NonNegInt]", testEqNonNegInt),
    property("test Order[NonNegInt]", testOrderNonNegInt),
    property("test Show[NonNegInt]", testShowNonNegInt),
    //
    property("test Eq[PosInt]", testEqPosInt),
    property("test Order[PosInt]", testOrderPosInt),
    property("test Show[PosInt]", testShowPosInt),
    //
    property("test Eq[NonPosInt]", testEqNonPosInt),
    property("test Order[NonPosInt]", testOrderNonPosInt),
    property("test Show[NonPosInt]", testShowNonPosInt),
    //
    property("test Eq[NegLong]", testEqNegLong),
    property("test Order[NegLong]", testOrderNegLong),
    property("test Show[NegLong]", testShowNegLong),
    //
    property("test Eq[NonNegLong]", testEqNonNegLong),
    property("test Order[NonNegLong]", testOrderNonNegLong),
    property("test Show[NonNegLong]", testShowNonNegLong),
    //
    property("test Eq[PosLong]", testEqPosLong),
    property("test Order[PosLong]", testOrderPosLong),
    property("test Show[PosLong]", testShowPosLong),
    //
    property("test Eq[NonPosLong]", testEqNonPosLong),
    property("test Order[NonPosLong]", testOrderNonPosLong),
    property("test Show[NonPosLong]", testShowNonPosLong),
    //
    property("test Eq[NegShort]", testEqNegShort),
    property("test Order[NegShort]", testOrderNegShort),
    property("test Show[NegShort]", testShowNegShort),
    //
    property("test Eq[NonNegShort]", testEqNonNegShort),
    property("test Order[NonNegShort]", testOrderNonNegShort),
    property("test Show[NonNegShort]", testShowNonNegShort),
    //
    property("test Eq[PosShort]", testEqPosShort),
    property("test Order[PosShort]", testOrderPosShort),
    property("test Show[PosShort]", testShowPosShort),
    //
    property("test Eq[NonPosShort]", testEqNonPosShort),
    property("test Order[NonPosShort]", testOrderNonPosShort),
    property("test Show[NonPosShort]", testShowNonPosShort),
    //
    property("test Eq[NegByte]", testEqNegByte),
    property("test Order[NegByte]", testOrderNegByte),
    property("test Show[NegByte]", testShowNegByte),
    //
    property("test Eq[NonNegByte]", testEqNonNegByte),
    property("test Order[NonNegByte]", testOrderNonNegByte),
    property("test Show[NonNegByte]", testShowNonNegByte),
    //
    property("test Eq[PosByte]", testEqPosByte),
    property("test Order[PosByte]", testOrderPosByte),
    property("test Show[PosByte]", testShowPosByte),
    //
    property("test Eq[NonPosByte]", testEqNonPosByte),
    property("test Order[NonPosByte]", testOrderNonPosByte),
    property("test Show[NonPosByte]", testShowNonPosByte),
    //
    property("test Eq[NegFloat]", testEqNegFloat),
    property("test Order[NegFloat]", testOrderNegFloat),
    property("test Show[NegFloat]", testShowNegFloat),
    //
    property("test Eq[NonNegFloat]", testEqNonNegFloat),
    property("test Order[NonNegFloat]", testOrderNonNegFloat),
    property("test Show[NonNegFloat]", testShowNonNegFloat),
    //
    property("test Eq[PosFloat]", testEqPosFloat),
    property("test Order[PosFloat]", testOrderPosFloat),
    property("test Show[PosFloat]", testShowPosFloat),
    //
    property("test Eq[NonPosFloat]", testEqNonPosFloat),
    property("test Order[NonPosFloat]", testOrderNonPosFloat),
    property("test Show[NonPosFloat]", testShowNonPosFloat),
    //
    property("test Eq[NegDouble]", testEqNegDouble),
    property("test Order[NegDouble]", testOrderNegDouble),
    property("test Show[NegDouble]", testShowNegDouble),
    //
    property("test Eq[NonNegDouble]", testEqNonNegDouble),
    property("test Order[NonNegDouble]", testOrderNonNegDouble),
    property("test Show[NonNegDouble]", testShowNonNegDouble),
    //
    property("test Eq[PosDouble]", testEqPosDouble),
    property("test Order[PosDouble]", testOrderPosDouble),
    property("test Show[PosDouble]", testShowPosDouble),
    //
    property("test Eq[NonPosDouble]", testEqNonPosDouble),
    property("test Order[NonPosDouble]", testOrderNonPosDouble),
    property("test Show[NonPosDouble]", testShowNonPosDouble),
    //
    property("test Eq[NegBigInt]", testEqNegBigInt),
    property("test Order[NegBigInt]", testOrderNegBigInt),
    property("test Show[NegBigInt]", testShowNegBigInt),
    //
    property("test Eq[NonNegBigInt]", testEqNonNegBigInt),
    property("test Order[NonNegBigInt]", testOrderNonNegBigInt),
    property("test Show[NonNegBigInt]", testShowNonNegBigInt),
    //
    property("test Eq[PosBigInt]", testEqPosBigInt),
    property("test Order[PosBigInt]", testOrderPosBigInt),
    property("test Show[PosBigInt]", testShowPosBigInt),
    //
    property("test Eq[NonPosBigInt]", testEqNonPosBigInt),
    property("test Order[NonPosBigInt]", testOrderNonPosBigInt),
    property("test Show[NonPosBigInt]", testShowNonPosBigInt),
    //
    property("test Eq[NegBigDecimal]", testEqNegBigDecimal),
    property("test Order[NegBigDecimal]", testOrderNegBigDecimal),
    property("test Show[NegBigDecimal]", testShowNegBigDecimal),
    //
    property("test Eq[NonNegBigDecimal]", testEqNonNegBigDecimal),
    property("test Order[NonNegBigDecimal]", testOrderNonNegBigDecimal),
    property("test Show[NonNegBigDecimal]", testShowNonNegBigDecimal),
    //
    property("test Eq[PosBigDecimal]", testEqPosBigDecimal),
    property("test Order[PosBigDecimal]", testOrderPosBigDecimal),
    property("test Show[PosBigDecimal]", testShowPosBigDecimal),
    //
    property("test Eq[NonPosBigDecimal]", testEqNonPosBigDecimal),
    property("test Order[NonPosBigDecimal]", testOrderNonPosBigDecimal),
    property("test Show[NonPosBigDecimal]", testShowNonPosBigDecimal),
    //
    property("test Eq[NonEmptyString]", testEqNonEmptyString),
    property("test Order[NonEmptyString]", testOrderNonEmptyString),
    property("test Show[NonEmptyString]", testShowNonEmptyString),
    //
    property("test Eq[NonBlankString]", testEqNonBlankString),
    property("test Order[NonBlankString]", testOrderNonBlankString),
    property("test Show[NonBlankString]", testShowNonBlankString),
    //
    property("test Eq[Uri]", testEqUri),
    property("test Show[Uri]", testShowUri),

    //
    property("test Eq[PortNumber]", testEqPortNumber),
    property("test Order[PortNumber]", testOrderPortNumber),
    property("test Show[PortNumber]", testShowPortNumber),
    //
    property("test Eq[SystemPortNumber]", testEqSystemPortNumber),
    property("test Order[SystemPortNumber]", testOrderSystemPortNumber),
    property("test Show[SystemPortNumber]", testShowSystemPortNumber),
    //
    property("test Eq[NonSystemPortNumber]", testEqNonSystemPortNumber),
    property("test Order[NonSystemPortNumber]", testOrderNonSystemPortNumber),
    property("test Show[NonSystemPortNumber]", testShowNonSystemPortNumber),
    //
    property("test Eq[UserPortNumber]", testEqUserPortNumber),
    property("test Order[UserPortNumber]", testOrderUserPortNumber),
    property("test Show[UserPortNumber]", testShowUserPortNumber),
    //
    property("test Eq[DynamicPortNumber]", testEqDynamicPortNumber),
    property("test Order[DynamicPortNumber]", testOrderDynamicPortNumber),
    property("test Show[DynamicPortNumber]", testShowDynamicPortNumber),
    //
    property("test Eq[MyNum]", testEqMyNum),
    property("test Order[MyNum]", testOrderMyNum),
    property("test Show[MyNum]", testShowMyNum),
    //
    property("test Eq[MyType] with MyType(MyTypeWithEq) where MyTypeWithEq has its own Eq", testEqMyType),
  )

  def testEqNegInt: Property =
    for {
      n <- Gen.int(Range.linear(-1, Int.MinValue)).log("n")
    } yield {
      val input = NegInt.unsafeFrom(n)

      val expected = input
      val actual   = input

      Result.diffNamed("NegInt(value) === NegInt(value)", actual, expected)(Eq[NegInt].eqv(_, _))
    }

  def testOrderNegInt: Property =
    for {
      n1 <- Gen.int(Range.linear(-1, Int.MinValue)).log("n1")
      n2 <- Gen.int(Range.linear(-1, Int.MinValue)).log("n2")
    } yield {
      val input1 = NegInt.unsafeFrom(n1)
      val input2 = NegInt.unsafeFrom(n2)

      val expected = n1.compare(n2)
      Result.diffNamed(show"Comparing $input1 and $input2", input1, input2)(Order[NegInt].compare(_, _) === expected)
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

      Result.diffNamed("NonNegInt(value) === NonNegInt(value)", actual, expected)(Eq[NonNegInt].eqv(_, _))
    }

  def testOrderNonNegInt: Property =
    for {
      n1 <- Gen.int(Range.linear(0, Int.MaxValue)).log("n1")
      n2 <- Gen.int(Range.linear(0, Int.MaxValue)).log("n2")
    } yield {
      val input1 = NonNegInt.unsafeFrom(n1)
      val input2 = NonNegInt.unsafeFrom(n2)

      val expected = n1.compare(n2)
      Result.diffNamed(show"Comparing $input1 and $input2", input1, input2)(Order[NonNegInt].compare(_, _) === expected)
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

      Result.diffNamed("PosInt(value) === PosInt(value)", actual, expected)(Eq[PosInt].eqv(_, _))
    }

  def testOrderPosInt: Property =
    for {
      n1 <- Gen.int(Range.linear(1, Int.MaxValue)).log("n1")
      n2 <- Gen.int(Range.linear(1, Int.MaxValue)).log("n2")
    } yield {
      val input1 = PosInt.unsafeFrom(n1)
      val input2 = PosInt.unsafeFrom(n2)

      val expected = n1.compare(n2)
      Result.diffNamed(show"Comparing $input1 and $input2", input1, input2)(Order[PosInt].compare(_, _) === expected)
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

      Result.diffNamed("NonPosInt(value) === NonPosInt(value)", actual, expected)(Eq[NonPosInt].eqv(_, _))
    }

  def testOrderNonPosInt: Property =
    for {
      n1 <- Gen.int(Range.linear(0, Int.MinValue)).log("n1")
      n2 <- Gen.int(Range.linear(0, Int.MinValue)).log("n2")
    } yield {
      val input1 = NonPosInt.unsafeFrom(n1)
      val input2 = NonPosInt.unsafeFrom(n2)

      val expected = n1.compare(n2)
      Result.diffNamed(show"Comparing $input1 and $input2", input1, input2)(Order[NonPosInt].compare(_, _) === expected)
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

      Result.diffNamed("NegLong(value) === NegLong(value)", actual, expected)(Eq[NegLong].eqv(_, _))
    }

  def testOrderNegLong: Property =
    for {
      n1 <- Gen.long(Range.linear(-1L, Long.MinValue)).log("n1")
      n2 <- Gen.long(Range.linear(-1L, Long.MinValue)).log("n2")
    } yield {
      val input1 = NegLong.unsafeFrom(n1)
      val input2 = NegLong.unsafeFrom(n2)

      val expected = n1.compare(n2)
      Result.diffNamed(show"Comparing $input1 and $input2", input1, input2)(Order[NegLong].compare(_, _) === expected)
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

      Result.diffNamed("NonNegLong(value) === NonNegLong(value)", actual, expected)(Eq[NonNegLong].eqv(_, _))
    }

  def testOrderNonNegLong: Property =
    for {
      n1 <- Gen.long(Range.linear(0L, Long.MaxValue)).log("n1")
      n2 <- Gen.long(Range.linear(0L, Long.MaxValue)).log("n2")
    } yield {
      val input1 = NonNegLong.unsafeFrom(n1)
      val input2 = NonNegLong.unsafeFrom(n2)

      val expected = n1.compare(n2)
      Result.diffNamed(show"Comparing $input1 and $input2", input1, input2)(Order[NonNegLong].compare(_, _) === expected)
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

      Result.diffNamed("PosLong(value) === PosLong(value)", actual, expected)(Eq[PosLong].eqv(_, _))
    }

  def testOrderPosLong: Property =
    for {
      n1 <- Gen.long(Range.linear(1L, Long.MaxValue)).log("n1")
      n2 <- Gen.long(Range.linear(1L, Long.MaxValue)).log("n2")
    } yield {
      val input1 = PosLong.unsafeFrom(n1)
      val input2 = PosLong.unsafeFrom(n2)

      val expected = n1.compare(n2)
      Result.diffNamed(show"Comparing $input1 and $input2", input1, input2)(Order[PosLong].compare(_, _) === expected)
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
      n <- Gen.long(Range.linear(0L, Long.MinValue)).log("n")
    } yield {
      val input = NonPosLong.unsafeFrom(n)

      val expected = input
      val actual   = input

      Result.diffNamed("NonPosLong(value) === NonPosLong(value)", actual, expected)(Eq[NonPosLong].eqv(_, _))
    }

  def testOrderNonPosLong: Property =
    for {
      n1 <- Gen.long(Range.linear(0L, Long.MinValue)).log("n1")
      n2 <- Gen.long(Range.linear(0L, Long.MinValue)).log("n2")
    } yield {
      val input1 = NonPosLong.unsafeFrom(n1)
      val input2 = NonPosLong.unsafeFrom(n2)

      val expected = n1.compare(n2)
      Result.diffNamed(show"Comparing $input1 and $input2", input1, input2)(Order[NonPosLong].compare(_, _) === expected)
    }

  def testShowNonPosLong: Property =
    for {
      n <- Gen.long(Range.linear(0L, Long.MinValue)).log("n")
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

      Result.diffNamed("NegShort(value) === NegShort(value)", actual, expected)(Eq[NegShort].eqv(_, _))
    }

  def testOrderNegShort: Property =
    for {
      n1 <- Gen.short(Range.linear(-1, Short.MinValue)).log("n1")
      n2 <- Gen.short(Range.linear(-1, Short.MinValue)).log("n2")
    } yield {
      val input1 = NegShort.unsafeFrom(n1)
      val input2 = NegShort.unsafeFrom(n2)

      val expected = n1.compare(n2)
      Result.diffNamed(show"Comparing $input1 and $input2", input1, input2)(Order[NegShort].compare(_, _) === expected)
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

      Result.diffNamed("NonNegShort(value) === NonNegShort(value)", actual, expected)(Eq[NonNegShort].eqv(_, _))
    }

  def testOrderNonNegShort: Property =
    for {
      n1 <- Gen.short(Range.linear(0, Short.MaxValue)).log("n1")
      n2 <- Gen.short(Range.linear(0, Short.MaxValue)).log("n2")
    } yield {
      val input1 = NonNegShort.unsafeFrom(n1)
      val input2 = NonNegShort.unsafeFrom(n2)

      val expected = n1.compare(n2)
      Result.diffNamed(show"Comparing $input1 and $input2", input1, input2)(Order[NonNegShort].compare(_, _) === expected)
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

      Result.diffNamed("PosShort(value) === PosShort(value)", actual, expected)(Eq[PosShort].eqv(_, _))
    }

  def testOrderPosShort: Property =
    for {
      n1 <- Gen.short(Range.linear(1, Short.MaxValue)).log("n1")
      n2 <- Gen.short(Range.linear(1, Short.MaxValue)).log("n2")
    } yield {
      val input1 = PosShort.unsafeFrom(n1)
      val input2 = PosShort.unsafeFrom(n2)

      val expected = n1.compare(n2)
      Result.diffNamed(show"Comparing $input1 and $input2", input1, input2)(Order[PosShort].compare(_, _) === expected)
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

      Result.diffNamed("NonPosShort(value) === NonPosShort(value)", actual, expected)(Eq[NonPosShort].eqv(_, _))
    }

  def testOrderNonPosShort: Property =
    for {
      n1 <- Gen.short(Range.linear(0, Short.MinValue)).log("n1")
      n2 <- Gen.short(Range.linear(0, Short.MinValue)).log("n2")
    } yield {
      val input1 = NonPosShort.unsafeFrom(n1)
      val input2 = NonPosShort.unsafeFrom(n2)

      val expected = n1.compare(n2)
      Result.diffNamed(show"Comparing $input1 and $input2", input1, input2)(Order[NonPosShort].compare(_, _) === expected)
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

      Result.diffNamed("NegByte(value) === NegByte(value)", actual, expected)(Eq[NegByte].eqv(_, _))
    }

  def testOrderNegByte: Property =
    for {
      n1 <- Gen.byte(Range.linear(-1, Byte.MinValue)).log("n1")
      n2 <- Gen.byte(Range.linear(-1, Byte.MinValue)).log("n2")
    } yield {
      val input1 = NegByte.unsafeFrom(n1)
      val input2 = NegByte.unsafeFrom(n2)

      val expected = n1.compare(n2)
      Result.diffNamed(show"Comparing $input1 and $input2", input1, input2)(Order[NegByte].compare(_, _) === expected)
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

      Result.diffNamed("NonNegByte(value) === NonNegByte(value)", actual, expected)(Eq[NonNegByte].eqv(_, _))
    }

  def testOrderNonNegByte: Property =
    for {
      n1 <- Gen.byte(Range.linear(0, Byte.MaxValue)).log("n1")
      n2 <- Gen.byte(Range.linear(0, Byte.MaxValue)).log("n2")
    } yield {
      val input1 = NonNegByte.unsafeFrom(n1)
      val input2 = NonNegByte.unsafeFrom(n2)

      val expected = n1.compare(n2)
      Result.diffNamed(show"Comparing $input1 and $input2", input1, input2)(Order[NonNegByte].compare(_, _) === expected)
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

      Result.diffNamed("PosByte(value) === PosByte(value)", actual, expected)(Eq[PosByte].eqv(_, _))
    }

  def testOrderPosByte: Property =
    for {
      n1 <- Gen.byte(Range.linear(1, Byte.MaxValue)).log("n1")
      n2 <- Gen.byte(Range.linear(1, Byte.MaxValue)).log("n2")
    } yield {
      val input1 = PosByte.unsafeFrom(n1)
      val input2 = PosByte.unsafeFrom(n2)

      val expected = n1.compare(n2)
      Result.diffNamed(show"Comparing $input1 and $input2", input1, input2)(Order[PosByte].compare(_, _) === expected)
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

      Result.diffNamed("NonPosByte(value) === NonPosByte(value)", actual, expected)(Eq[NonPosByte].eqv(_, _))
    }

  def testOrderNonPosByte: Property =
    for {
      n1 <- Gen.byte(Range.linear(0, Byte.MinValue)).log("n1")
      n2 <- Gen.byte(Range.linear(0, Byte.MinValue)).log("n2")
    } yield {
      val input1 = NonPosByte.unsafeFrom(n1)
      val input2 = NonPosByte.unsafeFrom(n2)

      val expected = n1.compare(n2)
      Result.diffNamed(show"Comparing $input1 and $input2", input1, input2)(Order[NonPosByte].compare(_, _) === expected)
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

      Result.diffNamed("NegFloat(value) === NegFloat(value)", actual, expected)(Eq[NegFloat].eqv(_, _))
    }

  def testOrderNegFloat: Property =
    for {
      n1 <- Gen.double(Range.linearFrac(-0.00001d, Float.MinValue)).map(_.toFloat).log("n1")
      n2 <- Gen.double(Range.linearFrac(-0.00001d, Float.MinValue)).map(_.toFloat).log("n2")
    } yield {
      val input1 = NegFloat.unsafeFrom(n1)
      val input2 = NegFloat.unsafeFrom(n2)

      val expected = n1.compare(n2)
      Result.diffNamed(show"Comparing $input1 and $input2", input1, input2)(Order[NegFloat].compare(_, _) === expected)
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

      Result.diffNamed("NonNegFloat(value) === NonNegFloat(value)", actual, expected)(Eq[NonNegFloat].eqv(_, _))
    }

  def testOrderNonNegFloat: Property =
    for {
      n1 <- Gen.double(Range.linearFrac(0d, Float.MaxValue)).map(_.toFloat).log("n1")
      n2 <- Gen.double(Range.linearFrac(0d, Float.MaxValue)).map(_.toFloat).log("n2")
    } yield {
      val input1 = NonNegFloat.unsafeFrom(n1)
      val input2 = NonNegFloat.unsafeFrom(n2)

      val expected = n1.compare(n2)
      Result.diffNamed(show"Comparing $input1 and $input2", input1, input2)(Order[NonNegFloat].compare(_, _) === expected)
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

      Result.diffNamed("PosFloat(value) === PosFloat(value)", actual, expected)(Eq[PosFloat].eqv(_, _))
    }

  def testOrderPosFloat: Property =
    for {
      n1 <- Gen.double(Range.linearFrac(0.0001d, Float.MaxValue)).map(_.toFloat).log("n1")
      n2 <- Gen.double(Range.linearFrac(0.0001d, Float.MaxValue)).map(_.toFloat).log("n2")
    } yield {
      val input1 = PosFloat.unsafeFrom(n1)
      val input2 = PosFloat.unsafeFrom(n2)

      val expected = n1.compare(n2)
      Result.diffNamed(show"Comparing $input1 and $input2", input1, input2)(Order[PosFloat].compare(_, _) === expected)
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

      Result.diffNamed("NonPosFloat(value) === NonPosFloat(value)", actual, expected)(Eq[NonPosFloat].eqv(_, _))
    }

  def testOrderNonPosFloat: Property =
    for {
      n1 <- Gen.double(Range.linearFrac(0d, Float.MinValue)).map(_.toFloat).log("n1")
      n2 <- Gen.double(Range.linearFrac(0d, Float.MinValue)).map(_.toFloat).log("n2")
    } yield {
      val input1 = NonPosFloat.unsafeFrom(n1)
      val input2 = NonPosFloat.unsafeFrom(n2)

      val expected = n1.compare(n2)
      Result.diffNamed(show"Comparing $input1 and $input2", input1, input2)(Order[NonPosFloat].compare(_, _) === expected)
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

      Result.diffNamed("NegDouble(value) === NegDouble(value)", actual, expected)(Eq[NegDouble].eqv(_, _))
    }

  def testOrderNegDouble: Property =
    for {
      n1 <- Gen.double(Range.linearFrac(-0.000001d, Double.MinValue)).log("n1")
      n2 <- Gen.double(Range.linearFrac(-0.000001d, Double.MinValue)).log("n2")
    } yield {
      val input1 = NegDouble.unsafeFrom(n1)
      val input2 = NegDouble.unsafeFrom(n2)

      val expected = n1.compare(n2)
      Result.diffNamed(show"Comparing $input1 and $input2", input1, input2)(Order[NegDouble].compare(_, _) === expected)
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

      Result.diffNamed("NonNegDouble(value) === NonNegDouble(value)", actual, expected)(Eq[NonNegDouble].eqv(_, _))
    }

  def testOrderNonNegDouble: Property =
    for {
      n1 <- Gen.double(Range.linearFrac(0d, Double.MaxValue)).log("n1")
      n2 <- Gen.double(Range.linearFrac(0d, Double.MaxValue)).log("n2")
    } yield {
      val input1 = NonNegDouble.unsafeFrom(n1)
      val input2 = NonNegDouble.unsafeFrom(n2)

      val expected = n1.compare(n2)
      Result.diffNamed(show"Comparing $input1 and $input2", input1, input2)(Order[NonNegDouble].compare(_, _) === expected)
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

      Result.diffNamed("PosDouble(value) === PosDouble(value)", actual, expected)(Eq[PosDouble].eqv(_, _))
    }

  def testOrderPosDouble: Property =
    for {
      n1 <- Gen.double(Range.linearFrac(0.000001d, Double.MaxValue)).log("n1")
      n2 <- Gen.double(Range.linearFrac(0.000001d, Double.MaxValue)).log("n2")
    } yield {
      val input1 = PosDouble.unsafeFrom(n1)
      val input2 = PosDouble.unsafeFrom(n2)

      val expected = n1.compare(n2)
      Result.diffNamed(show"Comparing $input1 and $input2", input1, input2)(Order[PosDouble].compare(_, _) === expected)
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

      Result.diffNamed("NonPosDouble(value) === NonPosDouble(value)", actual, expected)(Eq[NonPosDouble].eqv(_, _))
    }

  def testOrderNonPosDouble: Property =
    for {
      n1 <- Gen.double(Range.linearFrac(0d, Double.MinValue)).log("n1")
      n2 <- Gen.double(Range.linearFrac(0d, Double.MinValue)).log("n2")
    } yield {
      val input1 = NonPosDouble.unsafeFrom(n1)
      val input2 = NonPosDouble.unsafeFrom(n2)

      val expected = n1.compare(n2)
      Result.diffNamed(show"Comparing $input1 and $input2", input1, input2)(Order[NonPosDouble].compare(_, _) === expected)
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

      Result.diffNamed("NegBigInt(value) === NegBigInt(value)", actual, expected)(Eq[NegBigInt].eqv(_, _))
    }

  def testOrderNegBigInt: Property =
    for {
      n1 <- Gen.long(Range.linear(-1L, Long.MinValue)).map(BigInt(_)).log("n1")
      n2 <- Gen.long(Range.linear(-1L, Long.MinValue)).map(BigInt(_)).log("n2")
    } yield {
      val input1 = NegBigInt.unsafeFrom(n1)
      val input2 = NegBigInt.unsafeFrom(n2)

      val expected = n1.compare(n2)
      Result.diffNamed(show"Comparing $input1 and $input2", input1, input2)(Order[NegBigInt].compare(_, _) === expected)
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

      Result.diffNamed("NonNegBigInt(value) === NonNegBigInt(value)", actual, expected)(Eq[NonNegBigInt].eqv(_, _))
    }

  def testOrderNonNegBigInt: Property =
    for {
      n1 <- Gen.long(Range.linear(0L, Long.MaxValue)).map(BigInt(_)).log("n1")
      n2 <- Gen.long(Range.linear(0L, Long.MaxValue)).map(BigInt(_)).log("n2")
    } yield {
      val input1 = NonNegBigInt.unsafeFrom(n1)
      val input2 = NonNegBigInt.unsafeFrom(n2)

      val expected = n1.compare(n2)
      Result.diffNamed(show"Comparing $input1 and $input2", input1, input2)(Order[NonNegBigInt].compare(_, _) === expected)
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

      Result.diffNamed("PosBigInt(value) === PosBigInt(value)", actual, expected)(Eq[PosBigInt].eqv(_, _))
    }

  def testOrderPosBigInt: Property =
    for {
      n1 <- Gen.long(Range.linear(1L, Long.MaxValue)).map(BigInt(_)).log("n1")
      n2 <- Gen.long(Range.linear(1L, Long.MaxValue)).map(BigInt(_)).log("n2")
    } yield {
      val input1 = PosBigInt.unsafeFrom(n1)
      val input2 = PosBigInt.unsafeFrom(n2)

      val expected = n1.compare(n2)
      Result.diffNamed(show"Comparing $input1 and $input2", input1, input2)(Order[PosBigInt].compare(_, _) === expected)
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

      Result.diffNamed("NonPosBigInt(value) === NonPosBigInt(value)", actual, expected)(Eq[NonPosBigInt].eqv(_, _))
    }

  def testOrderNonPosBigInt: Property =
    for {
      n1 <- Gen.long(Range.linear(0L, Long.MinValue)).map(BigInt(_)).log("n1")
      n2 <- Gen.long(Range.linear(0L, Long.MinValue)).map(BigInt(_)).log("n2")
    } yield {
      val input1 = NonPosBigInt.unsafeFrom(n1)
      val input2 = NonPosBigInt.unsafeFrom(n2)

      val expected = n1.compare(n2)
      Result.diffNamed(show"Comparing $input1 and $input2", input1, input2)(Order[NonPosBigInt].compare(_, _) === expected)
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

      Result.diffNamed("NegBigDecimal(value) === NegBigDecimal(value)", actual, expected)(Eq[NegBigDecimal].eqv(_, _))
    }

  def testOrderNegBigDecimal: Property =
    for {
      n1 <- Gen.double(Range.linearFrac(-0.0000001d, Double.MinValue)).map(BigDecimal(_)).log("n1")
      n2 <- Gen.double(Range.linearFrac(-0.0000001d, Double.MinValue)).map(BigDecimal(_)).log("n2")
    } yield {
      val input1 = NegBigDecimal.unsafeFrom(n1)
      val input2 = NegBigDecimal.unsafeFrom(n2)

      val expected = n1.compare(n2)
      Result.diffNamed(show"Comparing $input1 and $input2", input1, input2)(Order[NegBigDecimal].compare(_, _) === expected)
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

      Result.diffNamed("NonNegBigDecimal(value) === NonNegBigDecimal(value)", actual, expected)(Eq[NonNegBigDecimal].eqv(_, _))
    }

  def testOrderNonNegBigDecimal: Property =
    for {
      n1 <- Gen.double(Range.linearFrac(0d, Double.MaxValue)).map(BigDecimal(_)).log("n1")
      n2 <- Gen.double(Range.linearFrac(0d, Double.MaxValue)).map(BigDecimal(_)).log("n2")
    } yield {
      val input1 = NonNegBigDecimal.unsafeFrom(n1)
      val input2 = NonNegBigDecimal.unsafeFrom(n2)

      val expected = n1.compare(n2)
      Result.diffNamed(show"Comparing $input1 and $input2", input1, input2)(Order[NonNegBigDecimal].compare(_, _) === expected)
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

      Result.diffNamed("PosBigDecimal(value) === PosBigDecimal(value)", actual, expected)(Eq[PosBigDecimal].eqv(_, _))
    }

  def testOrderPosBigDecimal: Property =
    for {
      n1 <- Gen.double(Range.linearFrac(0.0000001d, Double.MaxValue)).map(BigDecimal(_)).log("n1")
      n2 <- Gen.double(Range.linearFrac(0.0000001d, Double.MaxValue)).map(BigDecimal(_)).log("n2")
    } yield {
      val input1 = PosBigDecimal.unsafeFrom(n1)
      val input2 = PosBigDecimal.unsafeFrom(n2)

      val expected = n1.compare(n2)
      Result.diffNamed(show"Comparing $input1 and $input2", input1, input2)(Order[PosBigDecimal].compare(_, _) === expected)
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

      Result.diffNamed("NonPosBigDecimal(value) === NonPosBigDecimal(value)", actual, expected)(Eq[NonPosBigDecimal].eqv(_, _))
    }

  def testOrderNonPosBigDecimal: Property =
    for {
      n1 <- Gen.double(Range.linearFrac(0d, Double.MinValue)).map(BigDecimal(_)).log("n1")
      n2 <- Gen.double(Range.linearFrac(0d, Double.MinValue)).map(BigDecimal(_)).log("n2")
    } yield {
      val input1 = NonPosBigDecimal.unsafeFrom(n1)
      val input2 = NonPosBigDecimal.unsafeFrom(n2)

      val expected = n1.compare(n2)
      Result.diffNamed(show"Comparing $input1 and $input2", input1, input2)(Order[NonPosBigDecimal].compare(_, _) === expected)
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

  def testEqNonEmptyString: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
    } yield {
      val input = NonEmptyString.unsafeFrom(s)

      val expected = input
      val actual   = input

      Result.diffNamed("NonEmptyString(value) === NonEmptyString(value)", actual, expected)(Eq[NonEmptyString].eqv(_, _))
    }

  def testOrderNonEmptyString: Property =
    for {
      s1 <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s1")
      s2 <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s2")
    } yield {
      val input1 = NonEmptyString.unsafeFrom(s1)
      val input2 = NonEmptyString.unsafeFrom(s2)

      val expected = s1.compare(s2)

      Result.diffNamed(show"Comparing $input1 and $input2 with Order[NonEmptyString]", input1, input2)(
        Order[NonEmptyString].compare(_, _) === expected
      )
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

      Result.diffNamed("NonBlankString(value) === NonBlankString(value)", actual, expected)(Eq[NonBlankString].eqv(_, _))
    }

  def testOrderNonBlankString: Property =
    for {
      nonWhitespaceString1 <- Gen.string(hedgehog.extra.Gens.genNonWhitespaceChar, Range.linear(1, 10)).log("nonWhitespaceString1")
      whitespaceString1    <- Gen
                                .string(
                                  hedgehog.extra.Gens.genCharByRange(refined4s.types.strings.WhitespaceCharRange),
                                  Range.linear(1, 10),
                                )
                                .log("whitespaceString1")

      s1 <- Gen.constant(scala.util.Random.shuffle((nonWhitespaceString1 + whitespaceString1).toList).mkString).log("s1")

      nonWhitespaceString2 <- Gen.string(hedgehog.extra.Gens.genNonWhitespaceChar, Range.linear(1, 10)).log("nonWhitespaceString2")
      whitespaceString2    <- Gen
                                .string(
                                  hedgehog.extra.Gens.genCharByRange(refined4s.types.strings.WhitespaceCharRange),
                                  Range.linear(1, 10),
                                )
                                .log("whitespaceString2")

      s2 <- Gen.constant(scala.util.Random.shuffle((nonWhitespaceString2 + whitespaceString2).toList).mkString).log("s2")
    } yield {
      val input1 = NonBlankString.unsafeFrom(s1)
      val input2 = NonBlankString.unsafeFrom(s2)

      val expected = s1.compare(s2)

      Result.diffNamed(show"Comparing $input1 and $input2 with Order[NonBlankString]", input1, input2)(
        Order[NonBlankString].compare(_, _) === expected
      )
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

  def testEqUri: Property =
    for {
      uri <- networkGens.genUriString.log("uri")
    } yield {
      val input = Uri.unsafeFrom(uri)

      val expected = input
      val actual   = input

      Result.diffNamed("Uri(value) === Uri(value)", actual, expected)(Eq[Uri].eqv(_, _))
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
          Result.diffNamed("Comparing the same objects with ===", input1, input1)(Eq[PortNumber].eqv(_, _)),
          Result.diffNamed("Comparing the different objects with =!=", input1, input2)(Eq[PortNumber].neqv(_, _)),
        )
      )
    }

  def testOrderPortNumber: Property =
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

      val expected = portNumber1.compare(portNumber2)
      Result.diffNamed(show"Comparing $input1 and $input2", input1, input2)(Order[PortNumber].compare(_, _) === expected)

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
          Result.diffNamed("Comparing the same objects with ===", input1, input1)(Eq[SystemPortNumber].eqv(_, _)),
          Result.diffNamed("Comparing the different objects with =!=", input1, input2)(Eq[SystemPortNumber].neqv(_, _)),
        )
      )
    }

  def testOrderSystemPortNumber: Property =
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

      val expected = systemPortNumber1.compare(systemPortNumber2)
      Result.diffNamed(show"Comparing $input1 and $input2", input1, input2)(Order[SystemPortNumber].compare(_, _) === expected)

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
          Result.diffNamed("Comparing the same objects with ===", input1, input1)(Eq[NonSystemPortNumber].eqv(_, _)),
          Result.diffNamed("Comparing the different objects with =!=", input1, input2)(Eq[NonSystemPortNumber].neqv(_, _)),
        )
      )
    }

  def testOrderNonSystemPortNumber: Property =
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

      val expected = nonSystemPortNumber1.compare(nonSystemPortNumber2)
      Result.diffNamed(show"Comparing $input1 and $input2", input1, input2)(Order[NonSystemPortNumber].compare(_, _) === expected)

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
          Result.diffNamed("Comparing the same objects with ===", input1, input1)(Eq[UserPortNumber].eqv(_, _)),
          Result.diffNamed("Comparing the different objects with =!=", input1, input2)(Eq[UserPortNumber].neqv(_, _)),
        )
      )
    }

  def testOrderUserPortNumber: Property =
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

      val expected = userPortNumber1.compare(userPortNumber2)
      Result.diffNamed(show"Comparing $input1 and $input2", input1, input2)(Order[UserPortNumber].compare(_, _) === expected)

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
          Result.diffNamed("Comparing the same objects with ===", input1, input1)(Eq[DynamicPortNumber].eqv(_, _)),
          Result.diffNamed("Comparing the different objects with =!=", input1, input2)(Eq[DynamicPortNumber].neqv(_, _)),
        )
      )
    }

  def testOrderDynamicPortNumber: Property =
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

      val expected = dynamicPortNumber1.compare(dynamicPortNumber2)
      Result.diffNamed(show"Comparing $input1 and $input2", input1, input2)(Order[DynamicPortNumber].compare(_, _) === expected)

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

  ///

  def testEqMyNum: Property =
    for {
      n1 <- Gen.int(Range.linear(Int.MinValue, Int.MaxValue)).log("n1")
      n2 <- Gen.constant(if n1 === Int.MinValue then n1 + 1 else n1 - 1).log("n2")
    } yield {
      val input1 = MyNum(n1)
      val input2 = MyNum(n2)
      Result.all(
        List(
          Result.diffNamed("Comparing the same objects with ===", input1, input1)(Eq[MyNum].eqv(_, _)),
          Result.diffNamed("Comparing the different objects with =!=", input1, input2)(Eq[MyNum].neqv(_, _)),
        )
      )
    }

  def testOrderMyNum: Property =
    for {
      n1 <- Gen.int(Range.linear(Int.MinValue, Int.MaxValue)).log("n1")
      n2 <- Gen.int(Range.linear(Int.MinValue, Int.MaxValue)).log("n2")
    } yield {
      val input1 = MyNum(n1)
      val input2 = MyNum(n2)

      val expected = n1.compare(n2)
      Result.diffNamed(show"Comparing $input1 and $input2", input1, input2)(Order[MyNum].compare(_, _) === expected)
    }

  def testShowMyNum: Property =
    for {
      dynamicPortNumber <- networkGens.genDynamicPortNumberInt.log("dynamicPortNumber")
    } yield {
      val input = DynamicPortNumber.unsafeFrom(dynamicPortNumber)

      val expected = dynamicPortNumber.toString
      val actual   = input.show

      actual ==== expected
    }

  def testEqMyType: Property =
    for {
      b1 <- Gen.boolean.log("b1")
      b2 <- Gen.constant(if b1 then false else true).log("b2")
    } yield {
      val input1 = MyType(MyTypeWithEq(b1))
      val input2 = MyType(MyTypeWithEq(b2))
      Result.all(
        List(
          Result.diffNamed("Comparing the same objects with ===", input1, input1)(Eq[MyType].eqv(_, _)),
          Result.diffNamed("Comparing the different objects with =!=", input1, input2)(Eq[MyType].neqv(_, _)),
        )
      )
    }

  type MyNum = MyNum.Type
  object MyNum extends Newtype[Int]

  final case class MyTypeWithEq(value: Boolean)
  object MyTypeWithEq {
    given eqMyTypeWithEq: Eq[MyTypeWithEq] with {
      override def eqv(x: MyTypeWithEq, y: MyTypeWithEq): Boolean = {
        x.value === y.value
      }
    }
  }

  type MyType = MyType.Type
  object MyType extends Newtype[MyTypeWithEq]
}
