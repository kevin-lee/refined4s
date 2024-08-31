package refined4s.types

import refined4s.*

import hedgehog.*
import hedgehog.runner.*

/** @author Kevin Lee
  * @since 2023-04-26
  */
object numericSpec extends Properties {
  override def tests: List[Test] =
    NegIntSpec.tests ++ NonNegIntSpec.tests ++ PosIntSpec.tests ++ NonPosIntSpec.tests ++
      NegLongSpec.tests ++ NonNegLongSpec.tests ++ PosLongSpec.tests ++ NonPosLongSpec.tests ++
      NegShortSpec.tests ++ NonNegShortSpec.tests ++ PosShortSpec.tests ++ NonPosShortSpec.tests ++
      NegByteSpec.tests ++ NonNegByteSpec.tests ++ PosByteSpec.tests ++ NonPosByteSpec.tests ++
      NegFloatSpec.tests ++ NonNegFloatSpec.tests ++ PosFloatSpec.tests ++ NonPosFloatSpec.tests ++
      NegDoubleSpec.tests ++ NonNegDoubleSpec.tests ++ PosDoubleSpec.tests ++ NonPosDoubleSpec.tests ++
      NegBigIntSpec.tests ++ NonNegBigIntSpec.tests ++ PosBigIntSpec.tests ++ NonPosBigIntSpec.tests ++
      NegBigDecimalSpec.tests ++ NonNegBigDecimalSpec.tests ++ PosBigDecimalSpec.tests ++ NonPosBigDecimalSpec.tests

  object NegIntSpec {

    import all.NegInt

    def tests: List[Test] = List(
      example("test NegInt.apply", testApply),
      property("test NegInt.from(valid)", testFromValid),
      property("test NegInt.from(invalid)", testFromInvalid),
      property("test NegInt.unsafeFrom(valid)", testUnsafeFromValid),
      property("test NegInt.unsafeFrom(invalid)", testUnsafeFromInvalid),
      property("test NegInt.value", testValue),
      property("test NegInt.unapply", testUnapplyWithPatternMatching),
      property("test Ordering[NegInt]", testOrdering),
      property("test Ordered[NegInt]", testNumericOrdered),
      example("test NegInt.MinValue", testMinValue),
      example("test NegInt.MaxValue", testMaxValue),
    )

    def testApply: Result = {
      /* The actual test is whether this compiles or not actual ==== expected is meaningless here */
      val expected = NegInt(-1)
      val actual   = NegInt(-1)
      actual ==== expected
    }

    def testFromValid: Property =
      for {
        n <- Gen.int(Range.linear(Int.MinValue, -1)).log("n")
      } yield {
        val expected = NegInt.unsafeFrom(n)
        val actual   = NegInt.from(n)
        actual ==== Right(expected)
      }

    def testFromInvalid: Property =
      for {
        n <- Gen.int(Range.linear(0, Int.MaxValue)).log("n")
      } yield {
        val expected = s"Invalid value: [${n.toString}]. It must be a negative Int"
        val actual   = NegInt.from(n)
        actual ==== Left(expected)
      }

    def testUnsafeFromValid: Property =
      for {
        n <- Gen.int(Range.linear(Int.MinValue, -1)).log("n")
      } yield {
        val expected = NegInt.unsafeFrom(n)
        val actual   = NegInt.unsafeFrom(n)
        actual ==== expected
      }

    def testUnsafeFromInvalid: Property =
      for {
        n <- Gen.int(Range.linear(0, Int.MaxValue)).log("n")
      } yield {
        val expected = s"Invalid value: [$n]. It must be a negative Int"
        try {
          val _ = NegInt.unsafeFrom(n)
          Result
            .failure
            .log(
              s"""IllegalArgumentException was expected from NegInt.unsafeFrom(${n.toString}), but it was not thrown."""
            )
        } catch {
          case ex: IllegalArgumentException =>
            ex.getMessage ==== expected

        }
      }

    def testValue: Property =
      for {
        n <- Gen.int(Range.linear(Int.MinValue, -1)).log("n")
      } yield {
        val expected = n
        val actual   = NegInt.unsafeFrom(n)
        actual.value ==== expected
      }

    def testUnapplyWithPatternMatching: Property =
      for {
        n <- Gen.int(Range.linear(Int.MinValue, -1)).log("n")
      } yield {
        val expected = n
        val nes      = NegInt.unsafeFrom(n)
        nes match {
          case NegInt(actual) =>
            actual ==== expected
        }
      }

    def testOrdering: Property =
      for {
        n1 <- Gen.int(Range.linear(Int.MinValue, -1)).log("n1")
        n2 <- Gen.int(Range.linear(Int.MinValue, -1)).log("n2")
      } yield {
        val input1 = NegInt.unsafeFrom(n1)
        val input2 = NegInt.unsafeFrom(n2)
        Ordering[NegInt].compare(input1, input2) ==== Ordering[Int].compare(input1.value, input2.value)
      }

    def testNumericOrdered: Property =
      for {
        n1 <- Gen.int(Range.linear(Int.MinValue, -1)).log("n1")
        n2 <- Gen.int(Range.linear(Int.MinValue, -1)).log("n2")
      } yield {
        val input1: Ordered[NegInt] = NegInt.unsafeFrom(n1)
        val input2: NegInt          = NegInt.unsafeFrom(n2)
        input1.compare(input2) ==== n1.compare(n2)
      }

    def testMinValue: Result = {
      val expected  = NegInt(Int.MinValue)
      val actual    = NegInt.MinValue
      val actualMin = NegInt.min

      Result.all(
        List(
          (actual ==== expected).log(s"NegInt.MinValue ==== ${expected.toString} failed"),
          (actualMin ==== expected).log(s"NegInt.min ==== ${expected.toString} failed"),
        )
      )
    }

    def testMaxValue: Result = {
      val expected  = NegInt(-1)
      val actual    = NegInt.MaxValue
      val actualMax = NegInt.max

      Result.all(
        List(
          (actual ==== expected).log(s"NegInt.MaxValue ==== ${expected.toString} failed"),
          (actualMax ==== expected).log(s"NegInt.max ==== ${expected.toString} failed"),
        )
      )
    }

  }

  object NonNegIntSpec {

    import all.NonNegInt

    def tests: List[Test] = List(
      example("test NonNegInt.apply", testApply),
      property("test NonNegInt.from(valid)", testFromValid),
      property("test NonNegInt.from(invalid)", testFromInvalid),
      property("test NonNegInt.unsafeFrom(valid)", testUnsafeFromValid),
      property("test NonNegInt.unsafeFrom(invalid)", testUnsafeFromInvalid),
      property("test NonNegInt.value", testValue),
      property("test NonNegInt.unapply", testUnapplyWithPatternMatching),
      property("test Ordering[NonNegInt]", testOrdering),
      property("test Ordered[NonNegInt]", testNumericOrdered),
      example("test NonNegInt.MinValue", testMinValue),
      example("test NonNegInt.MaxValue", testMaxValue),
    )

    def testApply: Result = {
      /* The actual test is whether this compiles or not actual ==== expected is meaningless here */
      val expected = NonNegInt(0)
      val actual   = NonNegInt(0)
      actual ==== expected
    }

    def testFromValid: Property =
      for {
        n <- Gen.int(Range.linear(0, Int.MaxValue)).log("n")
      } yield {
        val expected = NonNegInt.unsafeFrom(n)
        val actual   = NonNegInt.from(n)
        actual ==== Right(expected)
      }

    def testFromInvalid: Property =
      for {
        n <- Gen.int(Range.linear(Int.MinValue, -1)).log("n")
      } yield {
        val expected = s"Invalid value: [${n.toString}]. It must be a non-negative Int"
        val actual   = NonNegInt.from(n)
        actual ==== Left(expected)
      }

    def testUnsafeFromValid: Property =
      for {
        n <- Gen.int(Range.linear(0, Int.MaxValue)).log("n")
      } yield {
        val expected = NonNegInt.unsafeFrom(n)
        val actual   = NonNegInt.unsafeFrom(n)
        actual ==== expected
      }

    def testUnsafeFromInvalid: Property =
      for {
        n <- Gen.int(Range.linear(-1, Int.MinValue)).log("n")
      } yield {
        val expected = s"Invalid value: [$n]. It must be a non-negative Int"
        try {
          val _ = NonNegInt.unsafeFrom(n)
          Result
            .failure
            .log(
              s"""IllegalArgumentException was expected from NonNegInt.unsafeFrom(${n.toString}), but it was not thrown."""
            )
        } catch {
          case ex: IllegalArgumentException =>
            ex.getMessage ==== expected

        }
      }

    def testValue: Property =
      for {
        n <- Gen.int(Range.linear(0, Int.MaxValue)).log("n")
      } yield {
        val expected = n
        val actual   = NonNegInt.unsafeFrom(n)
        actual.value ==== expected
      }

    def testUnapplyWithPatternMatching: Property =
      for {
        n <- Gen.int(Range.linear(0, Int.MaxValue)).log("n")
      } yield {
        val expected = n
        val nes      = NonNegInt.unsafeFrom(n)
        nes match {
          case NonNegInt(actual) =>
            actual ==== expected
        }
      }

    def testOrdering: Property =
      for {
        n1 <- Gen.int(Range.linear(0, Int.MaxValue)).log("n1")
        n2 <- Gen.int(Range.linear(0, Int.MaxValue)).log("n2")
      } yield {
        val input1 = NonNegInt.unsafeFrom(n1)
        val input2 = NonNegInt.unsafeFrom(n2)
        Ordering[NonNegInt].compare(input1, input2) ==== Ordering[Int].compare(input1.value, input2.value)
      }

    def testNumericOrdered: Property =
      for {
        n1 <- Gen.int(Range.linear(0, Int.MaxValue)).log("n1")
        n2 <- Gen.int(Range.linear(0, Int.MaxValue)).log("n2")
      } yield {
        val input1: Ordered[NonNegInt] = NonNegInt.unsafeFrom(n1)
        val input2: NonNegInt          = NonNegInt.unsafeFrom(n2)
        input1.compare(input2) ==== n1.compare(n2)
      }

    def testMinValue: Result = {
      val expected  = NonNegInt(0)
      val actual    = NonNegInt.MinValue
      val actualMin = NonNegInt.min

      Result.all(
        List(
          (actual ==== expected).log(s"NonNegInt.MinValue ==== ${expected.toString} failed"),
          (actualMin ==== expected).log(s"NonNegInt.min ==== ${expected.toString} failed"),
        )
      )
    }

    def testMaxValue: Result = {
      val expected  = NonNegInt(Int.MaxValue)
      val actual    = NonNegInt.MaxValue
      val actualMax = NonNegInt.max

      Result.all(
        List(
          (actual ==== expected).log(s"NonNegInt.MaxValue ==== ${expected.toString} failed"),
          (actualMax ==== expected).log(s"NonNegInt.max ==== ${expected.toString} failed"),
        )
      )
    }

  }

  object PosIntSpec {

    import all.PosInt

    def tests: List[Test] = List(
      example("test PosInt.apply", testApply),
      property("test PosInt.from(valid)", testFromValid),
      property("test PosInt.from(invalid)", testFromInvalid),
      property("test PosInt.unsafeFrom(valid)", testUnsafeFromValid),
      property("test PosInt.unsafeFrom(invalid)", testUnsafeFromInvalid),
      property("test PosInt.value", testValue),
      property("test PosInt.unapply", testUnapplyWithPatternMatching),
      property("test Ordering[PosInt]", testOrdering),
      property("test Ordered[PosInt]", testNumericOrdered),
      example("test PosInt.MinValue", testMinValue),
      example("test PosInt.MaxValue", testMaxValue),
    )

    def testApply: Result = {
      /* The actual test is whether this compiles or not actual ==== expected is meaningless here */
      val expected = PosInt(1)
      val actual   = PosInt(1)
      actual ==== expected
    }

    def testFromValid: Property =
      for {
        n <- Gen.int(Range.linear(1, Int.MaxValue)).log("n")
      } yield {
        val expected = PosInt.unsafeFrom(n)
        val actual   = PosInt.from(n)
        actual ==== Right(expected)
      }

    def testFromInvalid: Property =
      for {
        n <- Gen.int(Range.linear(Int.MinValue, 0)).log("n")
      } yield {
        val expected = s"Invalid value: [${n.toString}]. It must be a positive Int"
        val actual   = PosInt.from(n)
        actual ==== Left(expected)
      }

    def testUnsafeFromValid: Property =
      for {
        n <- Gen.int(Range.linear(1, Int.MaxValue)).log("n")
      } yield {
        val expected = PosInt.unsafeFrom(n)
        val actual   = PosInt.unsafeFrom(n)
        actual ==== expected
      }

    def testUnsafeFromInvalid: Property =
      for {
        n <- Gen.int(Range.linear(Int.MinValue, 0)).log("n")
      } yield {
        val expected = s"Invalid value: [$n]. It must be a positive Int"
        try {
          val _ = PosInt.unsafeFrom(n)
          Result
            .failure
            .log(
              s"""IllegalArgumentException was expected from PosInt.unsafeFrom(${n.toString}), but it was not thrown."""
            )
        } catch {
          case ex: IllegalArgumentException =>
            ex.getMessage ==== expected

        }
      }

    def testValue: Property =
      for {
        n <- Gen.int(Range.linear(1, Int.MaxValue)).log("n")
      } yield {
        val expected = n
        val actual   = PosInt.unsafeFrom(n)
        actual.value ==== expected
      }

    def testUnapplyWithPatternMatching: Property =
      for {
        n <- Gen.int(Range.linear(1, Int.MaxValue)).log("n")
      } yield {
        val expected = n
        val nes      = PosInt.unsafeFrom(n)
        nes match {
          case PosInt(actual) =>
            actual ==== expected
        }
      }

    def testOrdering: Property =
      for {
        n1 <- Gen.int(Range.linear(1, Int.MaxValue)).log("n1")
        n2 <- Gen.int(Range.linear(1, Int.MaxValue)).log("n2")
      } yield {
        val input1 = PosInt.unsafeFrom(n1)
        val input2 = PosInt.unsafeFrom(n2)
        Ordering[PosInt].compare(input1, input2) ==== Ordering[Int].compare(input1.value, input2.value)
      }

    def testNumericOrdered: Property =
      for {
        n1 <- Gen.int(Range.linear(1, Int.MaxValue)).log("n1")
        n2 <- Gen.int(Range.linear(1, Int.MaxValue)).log("n2")
      } yield {
        val input1: Ordered[PosInt] = PosInt.unsafeFrom(n1)
        val input2: PosInt          = PosInt.unsafeFrom(n2)
        input1.compare(input2) ==== n1.compare(n2)
      }

    def testMinValue: Result = {
      val expected  = PosInt(1)
      val actual    = PosInt.MinValue
      val actualMin = PosInt.min

      Result.all(
        List(
          (actual ==== expected).log(s"PosInt.MinValue ==== ${expected.toString} failed"),
          (actualMin ==== expected).log(s"PosInt.min ==== ${expected.toString} failed"),
        )
      )
    }

    def testMaxValue: Result = {
      val expected  = PosInt(Int.MaxValue)
      val actual    = PosInt.MaxValue
      val actualMax = PosInt.max

      Result.all(
        List(
          (actual ==== expected).log(s"PosInt.MaxValue ==== ${expected.toString} failed"),
          (actualMax ==== expected).log(s"PosInt.max ==== ${expected.toString} failed"),
        )
      )
    }
  }

  object NonPosIntSpec {

    import all.NonPosInt

    def tests: List[Test] = List(
      example("test NonPosInt.apply", testApply),
      property("test NonPosInt.from(valid)", testFromValid),
      property("test NonPosInt.from(invalid)", testFromInvalid),
      property("test NonPosInt.unsafeFrom(valid)", testUnsafeFromValid),
      property("test NonPosInt.unsafeFrom(invalid)", testUnsafeFromInvalid),
      property("test NonPosInt.value", testValue),
      property("test NonPosInt.unapply", testUnapplyWithPatternMatching),
      property("test Ordering[NonPosInt]", testOrdering),
      property("test Ordered[NonPosInt]", testNumericOrdered),
      example("test NonPosInt.MinValue", testMinValue),
      example("test NonPosInt.MaxValue", testMaxValue),
    )

    def testApply: Result = {
      /* The actual test is whether this compiles or not actual ==== expected is meaningless here */
      val expected  = NonPosInt(0)
      val actual    = NonPosInt(0)
      val expected2 = NonPosInt(Int.MinValue)
      val actual2   = NonPosInt(Int.MinValue)
      Result.all(
        List(
          actual ==== expected,
          actual2 ==== expected2,
        )
      )
    }

    def testFromValid: Property =
      for {
        n <- Gen.int(Range.linear(Int.MinValue, 0)).log("n")
      } yield {
        val expected = NonPosInt.unsafeFrom(n)
        val actual   = NonPosInt.from(n)
        actual ==== Right(expected)
      }

    def testFromInvalid: Property =
      for {
        n <- Gen.int(Range.linear(1, Int.MaxValue)).log("n")
      } yield {
        val expected = s"Invalid value: [${n.toString}]. It must be a non-positive Int"
        val actual   = NonPosInt.from(n)
        actual ==== Left(expected)
      }

    def testUnsafeFromValid: Property =
      for {
        n <- Gen.int(Range.linear(Int.MinValue, 0)).log("n")
      } yield {
        val expected = NonPosInt.unsafeFrom(n)
        val actual   = NonPosInt.unsafeFrom(n)
        actual ==== expected
      }

    def testUnsafeFromInvalid: Property =
      for {
        n <- Gen.int(Range.linear(1, Int.MaxValue)).log("n")
      } yield {
        val expected = s"Invalid value: [$n]. It must be a non-positive Int"
        try {
          val _ = NonPosInt.unsafeFrom(n)
          Result
            .failure
            .log(
              s"""IllegalArgumentException was expected from NonPosInt.unsafeFrom(${n.toString}), but it was not thrown."""
            )
        } catch {
          case ex: IllegalArgumentException =>
            ex.getMessage ==== expected

        }
      }

    def testValue: Property =
      for {
        n <- Gen.int(Range.linear(Int.MinValue, 0)).log("n")
      } yield {
        val expected = n
        val actual   = NonPosInt.unsafeFrom(n)
        actual.value ==== expected
      }

    def testUnapplyWithPatternMatching: Property =
      for {
        n <- Gen.int(Range.linear(Int.MinValue, 0)).log("n")
      } yield {
        val expected = n
        val nes      = NonPosInt.unsafeFrom(n)
        nes match {
          case NonPosInt(actual) =>
            actual ==== expected
        }
      }

    def testOrdering: Property =
      for {
        n1 <- Gen.int(Range.linear(Int.MinValue, 0)).log("n1")
        n2 <- Gen.int(Range.linear(Int.MinValue, 0)).log("n2")
      } yield {
        val input1 = NonPosInt.unsafeFrom(n1)
        val input2 = NonPosInt.unsafeFrom(n2)
        Ordering[NonPosInt].compare(input1, input2) ==== Ordering[Int].compare(input1.value, input2.value)
      }

    def testNumericOrdered: Property =
      for {
        n1 <- Gen.int(Range.linear(Int.MinValue, 0)).log("n1")
        n2 <- Gen.int(Range.linear(Int.MinValue, 0)).log("n2")
      } yield {
        val input1: Ordered[NonPosInt] = NonPosInt.unsafeFrom(n1)
        val input2: NonPosInt          = NonPosInt.unsafeFrom(n2)
        input1.compare(input2) ==== n1.compare(n2)
      }

    def testMinValue: Result = {
      val expected  = NonPosInt(Int.MinValue)
      val actual    = NonPosInt.MinValue
      val actualMin = NonPosInt.min

      Result.all(
        List(
          (actual ==== expected).log(s"NonPosInt.MinValue ==== ${expected.toString} failed"),
          (actualMin ==== expected).log(s"NonPosInt.min ==== ${expected.toString} failed"),
        )
      )
    }

    def testMaxValue: Result = {
      val expected  = NonPosInt(0)
      val actual    = NonPosInt.MaxValue
      val actualMax = NonPosInt.max

      Result.all(
        List(
          (actual ==== expected).log(s"NonPosInt.MaxValue ==== ${expected.toString} failed"),
          (actualMax ==== expected).log(s"NonPosInt.max ==== ${expected.toString} failed"),
        )
      )
    }
  }

  object NegLongSpec {

    import all.NegLong

    def tests: List[Test] = List(
      example("test NegLong.apply", testApply),
      property("test NegLong.from(valid)", testFromValid),
      property("test NegLong.from(invalid)", testFromInvalid),
      property("test NegLong.unsafeFrom(valid)", testUnsafeFromValid),
      property("test NegLong.unsafeFrom(invalid)", testUnsafeFromInvalid),
      property("test NegLong.value", testValue),
      property("test NegLong.unapply", testUnapplyWithPatternMatching),
      property("test Ordering[NegLong]", testOrdering),
      property("test Ordered[NegLong]", testNumericOrdered),
      example("test NegLong.MinValue", testMinValue),
      example("test NegLong.MaxValue", testMaxValue),
    )

    def testApply: Result = {
      /* The actual test is whether this compiles or not actual ==== expected is meaningless here */
      val expected = NegLong(-1L)
      val actual   = NegLong(-1L)
      actual ==== expected
    }

    def testFromValid: Property =
      for {
        n <- Gen.long(Range.linear(Long.MinValue, -1L)).log("n")
      } yield {
        val expected = NegLong.unsafeFrom(n)
        val actual   = NegLong.from(n)
        actual ==== Right(expected)
      }

    def testFromInvalid: Property =
      for {
        n <- Gen.long(Range.linear(0L, Long.MaxValue)).log("n")
      } yield {
        val expected = s"Invalid value: [${n.toString}]. It must be a negative Long"
        val actual   = NegLong.from(n)
        actual ==== Left(expected)
      }

    def testUnsafeFromValid: Property =
      for {
        n <- Gen.long(Range.linear(Long.MinValue, -1L)).log("n")
      } yield {
        val expected = NegLong.unsafeFrom(n)
        val actual   = NegLong.unsafeFrom(n)
        actual ==== expected
      }

    def testUnsafeFromInvalid: Property =
      for {
        n <- Gen.long(Range.linear(0L, Long.MaxValue)).log("n")
      } yield {
        val expected = s"Invalid value: [$n]. It must be a negative Long"
        try {
          val _ = NegLong.unsafeFrom(n)
          Result
            .failure
            .log(
              s"""IllegalArgumentException was expected from NegLong.unsafeFrom(${n.toString}), but it was not thrown."""
            )
        } catch {
          case ex: IllegalArgumentException =>
            ex.getMessage ==== expected

        }
      }

    def testValue: Property =
      for {
        n <- Gen.long(Range.linear(Long.MinValue, -1L)).log("n")
      } yield {
        val expected = n
        val actual   = NegLong.unsafeFrom(n)
        actual.value ==== expected
      }

    def testUnapplyWithPatternMatching: Property =
      for {
        n <- Gen.long(Range.linear(Long.MinValue, -1L)).log("n")
      } yield {
        val expected = n
        val nes      = NegLong.unsafeFrom(n)
        nes match {
          case NegLong(actual) =>
            actual ==== expected
        }
      }

    def testOrdering: Property =
      for {
        n1 <- Gen.long(Range.linear(Long.MinValue, -1L)).log("n1")
        n2 <- Gen.long(Range.linear(Long.MinValue, -1L)).log("n2")
      } yield {
        val input1 = NegLong.unsafeFrom(n1)
        val input2 = NegLong.unsafeFrom(n2)
        Ordering[NegLong].compare(input1, input2) ==== Ordering[Long].compare(input1.value, input2.value)
      }

    def testNumericOrdered: Property =
      for {
        n1 <- Gen.long(Range.linear(Long.MinValue, -1L)).log("n1")
        n2 <- Gen.long(Range.linear(Long.MinValue, -1L)).log("n2")
      } yield {
        val input1: Ordered[NegLong] = NegLong.unsafeFrom(n1)
        val input2: NegLong          = NegLong.unsafeFrom(n2)
        input1.compare(input2) ==== n1.compare(n2)
      }

    def testMinValue: Result = {
      val expected  = NegLong(Long.MinValue)
      val actual    = NegLong.MinValue
      val actualMin = NegLong.min

      Result.all(
        List(
          (actual ==== expected).log(s"NegLong.MinValue ==== ${expected.toString} failed"),
          (actualMin ==== expected).log(s"NegLong.min ==== ${expected.toString} failed"),
        )
      )
    }

    def testMaxValue: Result = {
      val expected  = NegLong(-1L)
      val actual    = NegLong.MaxValue
      val actualMax = NegLong.max

      Result.all(
        List(
          (actual ==== expected).log(s"NegLong.MaxValue ==== ${expected.toString} failed"),
          (actualMax ==== expected).log(s"NegLong.max ==== ${expected.toString} failed"),
        )
      )
    }

  }

  object NonNegLongSpec {

    import all.NonNegLong

    def tests: List[Test] = List(
      example("test NonNegLong.apply", testApply),
      property("test NonNegLong.from(valid)", testFromValid),
      property("test NonNegLong.from(invalid)", testFromInvalid),
      property("test NonNegLong.unsafeFrom(valid)", testUnsafeFromValid),
      property("test NonNegLong.unsafeFrom(invalid)", testUnsafeFromInvalid),
      property("test NonNegLong.value", testValue),
      property("test NonNegLong.unapply", testUnapplyWithPatternMatching),
      property("test Ordering[NonNegLong]", testOrdering),
      property("test Ordered[NonNegLong]", testNumericOrdered),
      example("test NonNegLong.MinValue", testMinValue),
      example("test NonNegLong.MaxValue", testMaxValue),
    )

    def testApply: Result = {
      /* The actual test is whether this compiles or not actual ==== expected is meaningless here */
      val expected  = NonNegLong(1L)
      val actual    = NonNegLong(1L)
      val expected2 = NonNegLong(0L)
      val actual2   = NonNegLong(0L)
      Result.all(
        List(
          actual ==== expected,
          actual2 ==== expected2,
        )
      )
    }

    def testFromValid: Property =
      for {
        n <- Gen.long(Range.linear(0L, Long.MaxValue)).log("n")
      } yield {
        val expected = NonNegLong.unsafeFrom(n)
        val actual   = NonNegLong.from(n)
        actual ==== Right(expected)
      }

    def testFromInvalid: Property =
      for {
        n <- Gen.long(Range.linear(Long.MinValue, -1L)).log("n")
      } yield {
        val expected = s"Invalid value: [${n.toString}]. It must be a non-negative Long"
        val actual   = NonNegLong.from(n)
        actual ==== Left(expected)
      }

    def testUnsafeFromValid: Property =
      for {
        n <- Gen.long(Range.linear(0L, Long.MaxValue)).log("n")
      } yield {
        val expected = NonNegLong.unsafeFrom(n)
        val actual   = NonNegLong.unsafeFrom(n)
        actual ==== expected
      }

    def testUnsafeFromInvalid: Property =
      for {
        n <- Gen.long(Range.linear(Long.MinValue, -1L)).log("n")
      } yield {
        val expected = s"Invalid value: [$n]. It must be a non-negative Long"
        try {
          val _ = NonNegLong.unsafeFrom(n)
          Result
            .failure
            .log(
              s"""IllegalArgumentException was expected from NonNegLong.unsafeFrom(${n.toString}), but it was not thrown."""
            )
        } catch {
          case ex: IllegalArgumentException =>
            ex.getMessage ==== expected

        }
      }

    def testValue: Property =
      for {
        n <- Gen.long(Range.linear(0L, Long.MaxValue)).log("n")
      } yield {
        val expected = n
        val actual   = NonNegLong.unsafeFrom(n)
        actual.value ==== expected
      }

    def testUnapplyWithPatternMatching: Property =
      for {
        n <- Gen.long(Range.linear(0L, Long.MaxValue)).log("n")
      } yield {
        val expected = n
        val nes      = NonNegLong.unsafeFrom(n)
        nes match {
          case NonNegLong(actual) =>
            actual ==== expected
        }
      }

    def testOrdering: Property =
      for {
        n1 <- Gen.long(Range.linear(0L, Long.MaxValue)).log("n1")
        n2 <- Gen.long(Range.linear(0L, Long.MaxValue)).log("n2")
      } yield {
        val input1 = NonNegLong.unsafeFrom(n1)
        val input2 = NonNegLong.unsafeFrom(n2)
        Ordering[NonNegLong].compare(input1, input2) ==== Ordering[Long].compare(input1.value, input2.value)
      }

    def testNumericOrdered: Property =
      for {
        n1 <- Gen.long(Range.linear(0L, Long.MaxValue)).log("n1")
        n2 <- Gen.long(Range.linear(0L, Long.MaxValue)).log("n2")
      } yield {
        val input1: Ordered[NonNegLong] = NonNegLong.unsafeFrom(n1)
        val input2: NonNegLong          = NonNegLong.unsafeFrom(n2)
        input1.compare(input2) ==== n1.compare(n2)
      }

    def testMinValue: Result = {
      val expected  = NonNegLong(0L)
      val actual    = NonNegLong.MinValue
      val actualMin = NonNegLong.min

      Result.all(
        List(
          (actual ==== expected).log(s"NonNegLong.MinValue ==== ${expected.toString} failed"),
          (actualMin ==== expected).log(s"NonNegLong.min ==== ${expected.toString} failed"),
        )
      )
    }

    def testMaxValue: Result = {
      val expected  = NonNegLong(Long.MaxValue)
      val actual    = NonNegLong.MaxValue
      val actualMax = NonNegLong.max

      Result.all(
        List(
          (actual ==== expected).log(s"NonNegLong.MaxValue ==== ${expected.toString} failed"),
          (actualMax ==== expected).log(s"NonNegLong.max ==== ${expected.toString} failed"),
        )
      )
    }

  }

  object PosLongSpec {

    import all.PosLong

    def tests: List[Test] = List(
      example("test PosLong.apply", testApply),
      property("test PosLong.from(valid)", testFromValid),
      property("test PosLong.from(invalid)", testFromInvalid),
      property("test PosLong.unsafeFrom(valid)", testUnsafeFromValid),
      property("test PosLong.unsafeFrom(invalid)", testUnsafeFromInvalid),
      property("test PosLong.value", testValue),
      property("test PosLong.unapply", testUnapplyWithPatternMatching),
      property("test Ordering[PosLong]", testOrdering),
      property("test Ordered[PosLong]", testNumericOrdered),
      example("test PosLong.MinValue", testMinValue),
      example("test PosLong.MaxValue", testMaxValue),
    )

    def testApply: Result = {
      /* The actual test is whether this compiles or not actual ==== expected is meaningless here */
      val expected = PosLong(1L)
      val actual   = PosLong(1L)
      actual ==== expected
    }

    def testFromValid: Property =
      for {
        n <- Gen.long(Range.linear(1L, Long.MaxValue)).log("n")
      } yield {
        val expected = PosLong.unsafeFrom(n)
        val actual   = PosLong.from(n)
        actual ==== Right(expected)
      }

    def testFromInvalid: Property =
      for {
        n <- Gen.long(Range.linear(Long.MinValue, 0L)).log("n")
      } yield {
        val expected = s"Invalid value: [${n.toString}]. It must be a positive Long"
        val actual   = PosLong.from(n)
        actual ==== Left(expected)
      }

    def testUnsafeFromValid: Property =
      for {
        n <- Gen.long(Range.linear(1L, Long.MaxValue)).log("n")
      } yield {
        val expected = PosLong.unsafeFrom(n)
        val actual   = PosLong.unsafeFrom(n)
        actual ==== expected
      }

    def testUnsafeFromInvalid: Property =
      for {
        n <- Gen.long(Range.linear(Long.MinValue, 0L)).log("n")
      } yield {
        val expected = s"Invalid value: [$n]. It must be a positive Long"
        try {
          val _ = PosLong.unsafeFrom(n)
          Result
            .failure
            .log(
              s"""IllegalArgumentException was expected from PosLong.unsafeFrom(${n.toString}), but it was not thrown."""
            )
        } catch {
          case ex: IllegalArgumentException =>
            ex.getMessage ==== expected

        }
      }

    def testValue: Property =
      for {
        n <- Gen.long(Range.linear(1L, Long.MaxValue)).log("n")
      } yield {
        val expected = n
        val actual   = PosLong.unsafeFrom(n)
        actual.value ==== expected
      }

    def testUnapplyWithPatternMatching: Property =
      for {
        n <- Gen.long(Range.linear(1L, Long.MaxValue)).log("n")
      } yield {
        val expected = n
        val nes      = PosLong.unsafeFrom(n)
        nes match {
          case PosLong(actual) =>
            actual ==== expected
        }
      }

    def testOrdering: Property =
      for {
        n1 <- Gen.long(Range.linear(1L, Long.MaxValue)).log("n1")
        n2 <- Gen.long(Range.linear(1L, Long.MaxValue)).log("n2")
      } yield {
        val input1 = PosLong.unsafeFrom(n1)
        val input2 = PosLong.unsafeFrom(n2)
        Ordering[PosLong].compare(input1, input2) ==== Ordering[Long].compare(input1.value, input2.value)
      }

    def testNumericOrdered: Property =
      for {
        n1 <- Gen.long(Range.linear(1L, Long.MaxValue)).log("n1")
        n2 <- Gen.long(Range.linear(1L, Long.MaxValue)).log("n2")
      } yield {
        val input1: Ordered[PosLong] = PosLong.unsafeFrom(n1)
        val input2: PosLong          = PosLong.unsafeFrom(n2)
        input1.compare(input2) ==== n1.compare(n2)
      }

    def testMinValue: Result = {
      val expected  = PosLong(1L)
      val actual    = PosLong.MinValue
      val actualMin = PosLong.min

      Result.all(
        List(
          (actual ==== expected).log(s"PosLong.MinValue ==== ${expected.toString} failed"),
          (actualMin ==== expected).log(s"PosLong.min ==== ${expected.toString} failed"),
        )
      )
    }

    def testMaxValue: Result = {
      val expected  = PosLong(Long.MaxValue)
      val actual    = PosLong.MaxValue
      val actualMax = PosLong.max

      Result.all(
        List(
          (actual ==== expected).log(s"PosLong.MaxValue ==== ${expected.toString} failed"),
          (actualMax ==== expected).log(s"PosLong.max ==== ${expected.toString} failed"),
        )
      )
    }

  }

  object NonPosLongSpec {

    import all.NonPosLong

    def tests: List[Test] = List(
      example("test NonPosLong.apply", testApply),
      property("test NonPosLong.from(valid)", testFromValid),
      property("test NonPosLong.from(invalid)", testFromInvalid),
      property("test NonPosLong.unsafeFrom(valid)", testUnsafeFromValid),
      property("test NonPosLong.unsafeFrom(invalid)", testUnsafeFromInvalid),
      property("test NonPosLong.value", testValue),
      property("test NonPosLong.unapply", testUnapplyWithPatternMatching),
      property("test Ordering[NonPosLong]", testOrdering),
      property("test Ordered[NonPosLong]", testNumericOrdered),
      example("test NonPosLong.MinValue", testMinValue),
      example("test NonPosLong.MaxValue", testMaxValue),
    )

    def testApply: Result = {
      /* The actual test is whether this compiles or not actual ==== expected is meaningless here */
      val expected  = NonPosLong(-1L)
      val actual    = NonPosLong(-1L)
      val expected2 = NonPosLong(0L)
      val actual2   = NonPosLong(0L)
      Result.all(
        List(
          actual ==== expected,
          actual2 ==== expected2,
        )
      )
    }

    def testFromValid: Property =
      for {
        n <- Gen.long(Range.linear(Long.MinValue, 0L)).log("n")
      } yield {
        val expected = NonPosLong.unsafeFrom(n)
        val actual   = NonPosLong.from(n)
        actual ==== Right(expected)
      }

    def testFromInvalid: Property =
      for {
        n <- Gen.long(Range.linear(1L, Long.MaxValue)).log("n")
      } yield {
        val expected = s"Invalid value: [${n.toString}]. It must be a non-positive Long"
        val actual   = NonPosLong.from(n)
        actual ==== Left(expected)
      }

    def testUnsafeFromValid: Property =
      for {
        n <- Gen.long(Range.linear(Long.MinValue, 0L)).log("n")
      } yield {
        val expected = NonPosLong.unsafeFrom(n)
        val actual   = NonPosLong.unsafeFrom(n)
        actual ==== expected
      }

    def testUnsafeFromInvalid: Property =
      for {
        n <- Gen.long(Range.linear(1L, Long.MaxValue)).log("n")
      } yield {
        val expected = s"Invalid value: [$n]. It must be a non-positive Long"
        try {
          val _ = NonPosLong.unsafeFrom(n)
          Result
            .failure
            .log(
              s"""IllegalArgumentException was expected from NonPosLong.unsafeFrom(${n.toString}), but it was not thrown."""
            )
        } catch {
          case ex: IllegalArgumentException =>
            ex.getMessage ==== expected

        }
      }

    def testValue: Property =
      for {
        n <- Gen.long(Range.linear(Long.MinValue, 0L)).log("n")
      } yield {
        val expected = n
        val actual   = NonPosLong.unsafeFrom(n)
        actual.value ==== expected
      }

    def testUnapplyWithPatternMatching: Property =
      for {
        n <- Gen.long(Range.linear(Long.MinValue, 0L)).log("n")
      } yield {
        val expected = n
        val nes      = NonPosLong.unsafeFrom(n)
        nes match {
          case NonPosLong(actual) =>
            actual ==== expected
        }
      }

    def testOrdering: Property =
      for {
        n1 <- Gen.long(Range.linear(Long.MinValue, 0L)).log("n1")
        n2 <- Gen.long(Range.linear(Long.MinValue, 0L)).log("n2")
      } yield {
        val input1 = NonPosLong.unsafeFrom(n1)
        val input2 = NonPosLong.unsafeFrom(n2)
        Ordering[NonPosLong].compare(input1, input2) ==== Ordering[Long].compare(input1.value, input2.value)
      }

    def testNumericOrdered: Property =
      for {
        n1 <- Gen.long(Range.linear(Long.MinValue, 0L)).log("n1")
        n2 <- Gen.long(Range.linear(Long.MinValue, 0L)).log("n2")
      } yield {
        val input1: Ordered[NonPosLong] = NonPosLong.unsafeFrom(n1)
        val input2: NonPosLong          = NonPosLong.unsafeFrom(n2)
        input1.compare(input2) ==== n1.compare(n2)
      }

    def testMinValue: Result = {
      val expected  = NonPosLong(Long.MinValue)
      val actual    = NonPosLong.MinValue
      val actualMin = NonPosLong.min

      Result.all(
        List(
          (actual ==== expected).log(s"NonPosLong.MinValue ==== ${expected.toString} failed"),
          (actualMin ==== expected).log(s"NonPosLong.min ==== ${expected.toString} failed"),
        )
      )
    }

    def testMaxValue: Result = {
      val expected  = NonPosLong(0L)
      val actual    = NonPosLong.MaxValue
      val actualMax = NonPosLong.max

      Result.all(
        List(
          (actual ==== expected).log(s"NonPosLong.MaxValue ==== ${expected.toString} failed"),
          (actualMax ==== expected).log(s"NonPosLong.max ==== ${expected.toString} failed"),
        )
      )
    }

  }

  object NegShortSpec {

    import all.NegShort

    def tests: List[Test] = List(
      example("test NegShort.apply", testApply),
      property("test NegShort.from(valid)", testFromValid),
      property("test NegShort.from(invalid)", testFromInvalid),
      property("test NegShort.unsafeFrom(valid)", testUnsafeFromValid),
      property("test NegShort.unsafeFrom(invalid)", testUnsafeFromInvalid),
      property("test NegShort.value", testValue),
      property("test NegShort.unapply", testUnapplyWithPatternMatching),
      property("test Ordering[NegShort]", testOrdering),
      property("test Ordered[NegShort]", testNumericOrdered),
      example("test NegShort.MinValue", testMinValue),
      example("test NegShort.MaxValue", testMaxValue),
    )

    def testApply: Result = {
      /* The actual test is whether this compiles or not actual ==== expected is meaningless here */
      val expected = NegShort(-1)
      val actual   = NegShort(-1)
      actual ==== expected
    }

    def testFromValid: Property =
      for {
        n <- Gen.short(Range.linear(Short.MinValue, -1)).log("n")
      } yield {
        val expected = NegShort.unsafeFrom(n)
        val actual   = NegShort.from(n)
        actual ==== Right(expected)
      }

    def testFromInvalid: Property =
      for {
        n <- Gen.short(Range.linear(0, Short.MaxValue)).log("n")
      } yield {
        val expected = s"Invalid value: [${n.toString}]. It must be a negative Short"
        val actual   = NegShort.from(n)
        actual ==== Left(expected)
      }

    def testUnsafeFromValid: Property =
      for {
        n <- Gen.short(Range.linear(Short.MinValue, -1)).log("n")
      } yield {
        val expected = NegShort.unsafeFrom(n)
        val actual   = NegShort.unsafeFrom(n)
        actual ==== expected
      }

    def testUnsafeFromInvalid: Property =
      for {
        n <- Gen.short(Range.linear(0, Short.MaxValue)).log("n")
      } yield {
        val expected = s"Invalid value: [$n]. It must be a negative Short"
        try {
          val _ = NegShort.unsafeFrom(n)
          Result
            .failure
            .log(
              s"""IllegalArgumentException was expected from NegShort.unsafeFrom(${n.toString}), but it was not thrown."""
            )
        } catch {
          case ex: IllegalArgumentException =>
            ex.getMessage ==== expected

        }
      }

    def testValue: Property =
      for {
        n <- Gen.short(Range.linear(Short.MinValue, -1)).log("n")
      } yield {
        val expected = n
        val actual   = NegShort.unsafeFrom(n)
        actual.value ==== expected
      }

    def testUnapplyWithPatternMatching: Property =
      for {
        n <- Gen.short(Range.linear(Short.MinValue, -1)).log("n")
      } yield {
        val expected = n
        val nes      = NegShort.unsafeFrom(n)
        nes match {
          case NegShort(actual) =>
            actual ==== expected
        }
      }

    def testOrdering: Property =
      for {
        n1 <- Gen.short(Range.linear(Short.MinValue, -1)).log("n1")
        n2 <- Gen.short(Range.linear(Short.MinValue, -1)).log("n2")
      } yield {
        val input1 = NegShort.unsafeFrom(n1)
        val input2 = NegShort.unsafeFrom(n2)
        Ordering[NegShort].compare(input1, input2) ==== Ordering[Short].compare(input1.value, input2.value)
      }

    def testNumericOrdered: Property =
      for {
        n1 <- Gen.short(Range.linear(Short.MinValue, -1)).log("n1")
        n2 <- Gen.short(Range.linear(Short.MinValue, -1)).log("n2")
      } yield {
        val input1: Ordered[NegShort] = NegShort.unsafeFrom(n1)
        val input2: NegShort          = NegShort.unsafeFrom(n2)
        input1.compare(input2) ==== n1.compare(n2)
      }

    def testMinValue: Result = {
      val expected  = NegShort(Short.MinValue)
      val actual    = NegShort.MinValue
      val actualMin = NegShort.min

      Result.all(
        List(
          (actual ==== expected).log(s"NegShort.MinValue ==== ${expected.toString} failed"),
          (actualMin ==== expected).log(s"NegShort.min ==== ${expected.toString} failed"),
        )
      )
    }

    def testMaxValue: Result = {
      val expected  = NegShort(-1)
      val actual    = NegShort.MaxValue
      val actualMax = NegShort.max

      Result.all(
        List(
          (actual ==== expected).log(s"NegShort.MaxValue ==== ${expected.toString} failed"),
          (actualMax ==== expected).log(s"NegShort.max ==== ${expected.toString} failed"),
        )
      )
    }

  }

  object NonNegShortSpec {

    import all.NonNegShort

    def tests: List[Test] = List(
      example("test NonNegShort.apply", testApply),
      property("test NonNegShort.from(valid)", testFromValid),
      property("test NonNegShort.from(invalid)", testFromInvalid),
      property("test NonNegShort.unsafeFrom(valid)", testUnsafeFromValid),
      property("test NonNegShort.unsafeFrom(invalid)", testUnsafeFromInvalid),
      property("test NonNegShort.value", testValue),
      property("test NonNegShort.unapply", testUnapplyWithPatternMatching),
      property("test Ordering[NonNegShort]", testOrdering),
      property("test Ordered[NonNegShort]", testNumericOrdered),
      example("test NonNegShort.MinValue", testMinValue),
      example("test NonNegShort.MaxValue", testMaxValue),
    )

    def testApply: Result = {
      /* The actual test is whether this compiles or not actual ==== expected is meaningless here */
      val expected = NonNegShort(0)
      val actual   = NonNegShort(0)
      actual ==== expected
    }

    def testFromValid: Property =
      for {
        n <- Gen.short(Range.linear(0, Short.MaxValue)).log("n")
      } yield {
        val expected = NonNegShort.unsafeFrom(n)
        val actual   = NonNegShort.from(n)
        actual ==== Right(expected)
      }

    def testFromInvalid: Property =
      for {
        n <- Gen.short(Range.linear(Short.MinValue, -1)).log("n")
      } yield {
        val expected = s"Invalid value: [${n.toString}]. It must be a non-negative Short"
        val actual   = NonNegShort.from(n)
        actual ==== Left(expected)
      }

    def testUnsafeFromValid: Property =
      for {
        n <- Gen.short(Range.linear(0, Short.MaxValue)).log("n")
      } yield {
        val expected = NonNegShort.unsafeFrom(n)
        val actual   = NonNegShort.unsafeFrom(n)
        actual ==== expected
      }

    def testUnsafeFromInvalid: Property =
      for {
        n <- Gen.short(Range.linear(-1, Short.MinValue)).log("n")
      } yield {
        val expected = s"Invalid value: [$n]. It must be a non-negative Short"
        try {
          val _ = NonNegShort.unsafeFrom(n)
          Result
            .failure
            .log(
              s"""IllegalArgumentException was expected from NonNegShort.unsafeFrom(${n.toString}), but it was not thrown."""
            )
        } catch {
          case ex: IllegalArgumentException =>
            ex.getMessage ==== expected

        }
      }

    def testValue: Property =
      for {
        n <- Gen.short(Range.linear(0, Short.MaxValue)).log("n")
      } yield {
        val expected = n
        val actual   = NonNegShort.unsafeFrom(n)
        actual.value ==== expected
      }

    def testUnapplyWithPatternMatching: Property =
      for {
        n <- Gen.short(Range.linear(0, Short.MaxValue)).log("n")
      } yield {
        val expected = n
        val nes      = NonNegShort.unsafeFrom(n)
        nes match {
          case NonNegShort(actual) =>
            actual ==== expected
        }
      }

    def testOrdering: Property =
      for {
        n1 <- Gen.short(Range.linear(0, Short.MaxValue)).log("n1")
        n2 <- Gen.short(Range.linear(0, Short.MaxValue)).log("n2")
      } yield {
        val input1 = NonNegShort.unsafeFrom(n1)
        val input2 = NonNegShort.unsafeFrom(n2)
        Ordering[NonNegShort].compare(input1, input2) ==== Ordering[Short].compare(input1.value, input2.value)
      }

    def testNumericOrdered: Property =
      for {
        n1 <- Gen.short(Range.linear(0, Short.MaxValue)).log("n1")
        n2 <- Gen.short(Range.linear(0, Short.MaxValue)).log("n2")
      } yield {
        val input1: Ordered[NonNegShort] = NonNegShort.unsafeFrom(n1)
        val input2: NonNegShort          = NonNegShort.unsafeFrom(n2)
        input1.compare(input2) ==== n1.compare(n2)
      }

    def testMinValue: Result = {
      val expected  = NonNegShort(0)
      val actual    = NonNegShort.MinValue
      val actualMin = NonNegShort.min

      Result.all(
        List(
          (actual ==== expected).log(s"NonNegShort.MinValue ==== ${expected.toString} failed"),
          (actualMin ==== expected).log(s"NonNegShort.min ==== ${expected.toString} failed"),
        )
      )
    }

    def testMaxValue: Result = {
      val expected  = NonNegShort(Short.MaxValue)
      val actual    = NonNegShort.MaxValue
      val actualMax = NonNegShort.max

      Result.all(
        List(
          (actual ==== expected).log(s"NonNegShort.MaxValue ==== ${expected.toString} failed"),
          (actualMax ==== expected).log(s"NonNegShort.max ==== ${expected.toString} failed"),
        )
      )
    }

  }

  object PosShortSpec {

    import all.PosShort

    def tests: List[Test] = List(
      example("test PosShort.apply", testApply),
      property("test PosShort.from(valid)", testFromValid),
      property("test PosShort.from(invalid)", testFromInvalid),
      property("test PosShort.unsafeFrom(valid)", testUnsafeFromValid),
      property("test PosShort.unsafeFrom(invalid)", testUnsafeFromInvalid),
      property("test PosShort.value", testValue),
      property("test PosShort.unapply", testUnapplyWithPatternMatching),
      property("test Ordering[PosShort]", testOrdering),
      property("test Ordered[PosShort]", testNumericOrdered),
      example("test PosShort.MinValue", testMinValue),
      example("test PosShort.MaxValue", testMaxValue),
    )

    def testApply: Result = {
      /* The actual test is whether this compiles or not actual ==== expected is meaningless here */
      val expected = PosShort(1)
      val actual   = PosShort(1)
      actual ==== expected
    }

    def testFromValid: Property =
      for {
        n <- Gen.short(Range.linear(1, Short.MaxValue)).log("n")
      } yield {
        val expected = PosShort.unsafeFrom(n)
        val actual   = PosShort.from(n)
        actual ==== Right(expected)
      }

    def testFromInvalid: Property =
      for {
        n <- Gen.short(Range.linear(Short.MinValue, 0)).log("n")
      } yield {
        val expected = s"Invalid value: [${n.toString}]. It must be a positive Short"
        val actual   = PosShort.from(n)
        actual ==== Left(expected)
      }

    def testUnsafeFromValid: Property =
      for {
        n <- Gen.short(Range.linear(1, Short.MaxValue)).log("n")
      } yield {
        val expected = PosShort.unsafeFrom(n)
        val actual   = PosShort.unsafeFrom(n)
        actual ==== expected
      }

    def testUnsafeFromInvalid: Property =
      for {
        n <- Gen.short(Range.linear(Short.MinValue, 0)).log("n")
      } yield {
        val expected = s"Invalid value: [$n]. It must be a positive Short"
        try {
          val _ = PosShort.unsafeFrom(n)
          Result
            .failure
            .log(
              s"""IllegalArgumentException was expected from PosShort.unsafeFrom(${n.toString}), but it was not thrown."""
            )
        } catch {
          case ex: IllegalArgumentException =>
            ex.getMessage ==== expected

        }
      }

    def testValue: Property =
      for {
        n <- Gen.short(Range.linear(1, Short.MaxValue)).log("n")
      } yield {
        val expected = n
        val actual   = PosShort.unsafeFrom(n)
        actual.value ==== expected
      }

    def testUnapplyWithPatternMatching: Property =
      for {
        n <- Gen.short(Range.linear(1, Short.MaxValue)).log("n")
      } yield {
        val expected = n
        val nes      = PosShort.unsafeFrom(n)
        nes match {
          case PosShort(actual) =>
            actual ==== expected
        }
      }

    def testOrdering: Property =
      for {
        n1 <- Gen.short(Range.linear(1, Short.MaxValue)).log("n1")
        n2 <- Gen.short(Range.linear(1, Short.MaxValue)).log("n2")
      } yield {
        val input1 = PosShort.unsafeFrom(n1)
        val input2 = PosShort.unsafeFrom(n2)
        Ordering[PosShort].compare(input1, input2) ==== Ordering[Short].compare(input1.value, input2.value)
      }

    def testNumericOrdered: Property =
      for {
        n1 <- Gen.short(Range.linear(1, Short.MaxValue)).log("n1")
        n2 <- Gen.short(Range.linear(1, Short.MaxValue)).log("n2")
      } yield {
        val input1: Ordered[PosShort] = PosShort.unsafeFrom(n1)
        val input2: PosShort          = PosShort.unsafeFrom(n2)
        input1.compare(input2) ==== n1.compare(n2)
      }

    def testMinValue: Result = {
      val expected  = PosShort(1)
      val actual    = PosShort.MinValue
      val actualMin = PosShort.min

      Result.all(
        List(
          (actual ==== expected).log(s"PosShort.MinValue ==== ${expected.toString} failed"),
          (actualMin ==== expected).log(s"PosShort.min ==== ${expected.toString} failed"),
        )
      )
    }

    def testMaxValue: Result = {
      val expected  = PosShort(Short.MaxValue)
      val actual    = PosShort.MaxValue
      val actualMax = PosShort.max

      Result.all(
        List(
          (actual ==== expected).log(s"PosShort.MaxValue ==== ${expected.toString} failed"),
          (actualMax ==== expected).log(s"PosShort.max ==== ${expected.toString} failed"),
        )
      )
    }

  }

  object NonPosShortSpec {

    import all.NonPosShort

    def tests: List[Test] = List(
      example("test NonPosShort.apply", testApply),
      property("test NonPosShort.from(valid)", testFromValid),
      property("test NonPosShort.from(invalid)", testFromInvalid),
      property("test NonPosShort.unsafeFrom(valid)", testUnsafeFromValid),
      property("test NonPosShort.unsafeFrom(invalid)", testUnsafeFromInvalid),
      property("test NonPosShort.value", testValue),
      property("test NonPosShort.unapply", testUnapplyWithPatternMatching),
      property("test Ordering[NonPosShort]", testOrdering),
      property("test Ordered[NonPosShort]", testNumericOrdered),
      example("test NonPosShort.MinValue", testMinValue),
      example("test NonPosShort.MaxValue", testMaxValue),
    )

    def testApply: Result = {
      /* The actual test is whether this compiles or not actual ==== expected is meaningless here */
      val expected  = NonPosShort(0)
      val actual    = NonPosShort(0)
      val expected2 = NonPosShort(Short.MinValue)
      val actual2   = NonPosShort(Short.MinValue)
      Result.all(
        List(
          actual ==== expected,
          actual2 ==== expected2,
        )
      )
    }

    def testFromValid: Property =
      for {
        n <- Gen.short(Range.linear(Short.MinValue, 0)).log("n")
      } yield {
        val expected = NonPosShort.unsafeFrom(n)
        val actual   = NonPosShort.from(n)
        actual ==== Right(expected)
      }

    def testFromInvalid: Property =
      for {
        n <- Gen.short(Range.linear(1, Short.MaxValue)).log("n")
      } yield {
        val expected = s"Invalid value: [${n.toString}]. It must be a non-positive Short"
        val actual   = NonPosShort.from(n)
        actual ==== Left(expected)
      }

    def testUnsafeFromValid: Property =
      for {
        n <- Gen.short(Range.linear(Short.MinValue, 0)).log("n")
      } yield {
        val expected = NonPosShort.unsafeFrom(n)
        val actual   = NonPosShort.unsafeFrom(n)
        actual ==== expected
      }

    def testUnsafeFromInvalid: Property =
      for {
        n <- Gen.short(Range.linear(1, Short.MaxValue)).log("n")
      } yield {
        val expected = s"Invalid value: [$n]. It must be a non-positive Short"
        try {
          val _ = NonPosShort.unsafeFrom(n)
          Result
            .failure
            .log(
              s"""IllegalArgumentException was expected from NonPosShort.unsafeFrom(${n.toString}), but it was not thrown."""
            )
        } catch {
          case ex: IllegalArgumentException =>
            ex.getMessage ==== expected

        }
      }

    def testValue: Property =
      for {
        n <- Gen.short(Range.linear(Short.MinValue, 0)).log("n")
      } yield {
        val expected = n
        val actual   = NonPosShort.unsafeFrom(n)
        actual.value ==== expected
      }

    def testUnapplyWithPatternMatching: Property =
      for {
        n <- Gen.short(Range.linear(Short.MinValue, 0)).log("n")
      } yield {
        val expected = n
        val nes      = NonPosShort.unsafeFrom(n)
        nes match {
          case NonPosShort(actual) =>
            actual ==== expected
        }
      }

    def testOrdering: Property =
      for {
        n1 <- Gen.short(Range.linear(Short.MinValue, 0)).log("n1")
        n2 <- Gen.short(Range.linear(Short.MinValue, 0)).log("n2")
      } yield {
        val input1 = NonPosShort.unsafeFrom(n1)
        val input2 = NonPosShort.unsafeFrom(n2)
        Ordering[NonPosShort].compare(input1, input2) ==== Ordering[Short].compare(input1.value, input2.value)
      }

    def testNumericOrdered: Property =
      for {
        n1 <- Gen.short(Range.linear(Short.MinValue, 0)).log("n1")
        n2 <- Gen.short(Range.linear(Short.MinValue, 0)).log("n2")
      } yield {
        val input1: Ordered[NonPosShort] = NonPosShort.unsafeFrom(n1)
        val input2: NonPosShort          = NonPosShort.unsafeFrom(n2)
        input1.compare(input2) ==== n1.compare(n2)
      }

    def testMinValue: Result = {
      val expected  = NonPosShort(Short.MinValue)
      val actual    = NonPosShort.MinValue
      val actualMin = NonPosShort.min

      Result.all(
        List(
          (actual ==== expected).log(s"NonPosShort.MinValue ==== ${expected.toString} failed"),
          (actualMin ==== expected).log(s"NonPosShort.min ==== ${expected.toString} failed"),
        )
      )
    }

    def testMaxValue: Result = {
      val expected  = NonPosShort(0)
      val actual    = NonPosShort.MaxValue
      val actualMax = NonPosShort.max

      Result.all(
        List(
          (actual ==== expected).log(s"NonPosShort.MaxValue ==== ${expected.toString} failed"),
          (actualMax ==== expected).log(s"NonPosShort.max ==== ${expected.toString} failed"),
        )
      )
    }

  }

  object NegByteSpec {

    import all.NegByte

    def tests: List[Test] = List(
      example("test NegByte.apply", testApply),
      property("test NegByte.from(valid)", testFromValid),
      property("test NegByte.from(invalid)", testFromInvalid),
      property("test NegByte.unsafeFrom(valid)", testUnsafeFromValid),
      property("test NegByte.unsafeFrom(invalid)", testUnsafeFromInvalid),
      property("test NegByte.value", testValue),
      property("test NegByte.unapply", testUnapplyWithPatternMatching),
      property("test Ordering[NegByte]", testOrdering),
      property("test Ordered[NegByte]", testNumericOrdered),
      example("test NegByte.MinValue", testMinValue),
      example("test NegByte.MaxValue", testMaxValue),
    )

    def testApply: Result = {
      /* The actual test is whether this compiles or not actual ==== expected is meaningless here */
      val expected = NegByte(-1)
      val actual   = NegByte(-1)
      actual ==== expected
    }

    def testFromValid: Property =
      for {
        n <- Gen.byte(Range.linear(Byte.MinValue, -1)).log("n")
      } yield {
        val expected = NegByte.unsafeFrom(n)
        val actual   = NegByte.from(n)
        actual ==== Right(expected)
      }

    def testFromInvalid: Property =
      for {
        n <- Gen.byte(Range.linear(0, Byte.MaxValue)).log("n")
      } yield {
        val expected = s"Invalid value: [${n.toString}]. It must be a negative Byte"
        val actual   = NegByte.from(n)
        actual ==== Left(expected)
      }

    def testUnsafeFromValid: Property =
      for {
        n <- Gen.byte(Range.linear(Byte.MinValue, -1)).log("n")
      } yield {
        val expected = NegByte.unsafeFrom(n)
        val actual   = NegByte.unsafeFrom(n)
        actual ==== expected
      }

    def testUnsafeFromInvalid: Property =
      for {
        n <- Gen.byte(Range.linear(0, Byte.MaxValue)).log("n")
      } yield {
        val expected = s"Invalid value: [$n]. It must be a negative Byte"
        try {
          val _ = NegByte.unsafeFrom(n)
          Result
            .failure
            .log(
              s"""IllegalArgumentException was expected from NegByte.unsafeFrom(${n.toString}), but it was not thrown."""
            )
        } catch {
          case ex: IllegalArgumentException =>
            ex.getMessage ==== expected

        }
      }

    def testValue: Property =
      for {
        n <- Gen.byte(Range.linear(Byte.MinValue, -1)).log("n")
      } yield {
        val expected = n
        val actual   = NegByte.unsafeFrom(n)
        actual.value ==== expected
      }

    def testUnapplyWithPatternMatching: Property =
      for {
        n <- Gen.byte(Range.linear(Byte.MinValue, -1)).log("n")
      } yield {
        val expected = n
        val nes      = NegByte.unsafeFrom(n)
        nes match {
          case NegByte(actual) =>
            actual ==== expected
        }
      }

    def testOrdering: Property =
      for {
        n1 <- Gen.byte(Range.linear(Byte.MinValue, -1)).log("n1")
        n2 <- Gen.byte(Range.linear(Byte.MinValue, -1)).log("n2")
      } yield {
        val input1 = NegByte.unsafeFrom(n1)
        val input2 = NegByte.unsafeFrom(n2)
        Ordering[NegByte].compare(input1, input2) ==== Ordering[Byte].compare(input1.value, input2.value)
      }

    def testNumericOrdered: Property =
      for {
        n1 <- Gen.byte(Range.linear(Byte.MinValue, -1)).log("n1")
        n2 <- Gen.byte(Range.linear(Byte.MinValue, -1)).log("n2")
      } yield {
        val input1: Ordered[NegByte] = NegByte.unsafeFrom(n1)
        val input2: NegByte          = NegByte.unsafeFrom(n2)
        input1.compare(input2) ==== n1.compare(n2)
      }

    def testMinValue: Result = {
      val expected  = NegByte(Byte.MinValue)
      val actual    = NegByte.MinValue
      val actualMin = NegByte.min

      Result.all(
        List(
          (actual ==== expected).log(s"NegByte.MinValue ==== ${expected.toString} failed"),
          (actualMin ==== expected).log(s"NegByte.min ==== ${expected.toString} failed"),
        )
      )
    }

    def testMaxValue: Result = {
      val expected  = NegByte(-1)
      val actual    = NegByte.MaxValue
      val actualMax = NegByte.max

      Result.all(
        List(
          (actual ==== expected).log(s"NegByte.MaxValue ==== ${expected.toString} failed"),
          (actualMax ==== expected).log(s"NegByte.max ==== ${expected.toString} failed"),
        )
      )
    }

  }

  object NonNegByteSpec {

    import all.NonNegByte

    def tests: List[Test] = List(
      example("test NonNegByte.apply", testApply),
      property("test NonNegByte.from(valid)", testFromValid),
      property("test NonNegByte.from(invalid)", testFromInvalid),
      property("test NonNegByte.unsafeFrom(valid)", testUnsafeFromValid),
      property("test NonNegByte.unsafeFrom(invalid)", testUnsafeFromInvalid),
      property("test NonNegByte.value", testValue),
      property("test NonNegByte.unapply", testUnapplyWithPatternMatching),
      property("test Ordering[NonNegByte]", testOrdering),
      property("test Ordered[NonNegByte]", testNumericOrdered),
      example("test NonNegByte.MinValue", testMinValue),
      example("test NonNegByte.MaxValue", testMaxValue),
    )

    def testApply: Result = {
      /* The actual test is whether this compiles or not actual ==== expected is meaningless here */
      val expected = NonNegByte(0)
      val actual   = NonNegByte(0)
      actual ==== expected
    }

    def testFromValid: Property =
      for {
        n <- Gen.byte(Range.linear(0, Byte.MaxValue)).log("n")
      } yield {
        val expected = NonNegByte.unsafeFrom(n)
        val actual   = NonNegByte.from(n)
        actual ==== Right(expected)
      }

    def testFromInvalid: Property =
      for {
        n <- Gen.byte(Range.linear(Byte.MinValue, -1)).log("n")
      } yield {
        val expected = s"Invalid value: [${n.toString}]. It must be a non-negative Byte"
        val actual   = NonNegByte.from(n)
        actual ==== Left(expected)
      }

    def testUnsafeFromValid: Property =
      for {
        n <- Gen.byte(Range.linear(0, Byte.MaxValue)).log("n")
      } yield {
        val expected = NonNegByte.unsafeFrom(n)
        val actual   = NonNegByte.unsafeFrom(n)
        actual ==== expected
      }

    def testUnsafeFromInvalid: Property =
      for {
        n <- Gen.byte(Range.linear(-1, Byte.MinValue)).log("n")
      } yield {
        val expected = s"Invalid value: [$n]. It must be a non-negative Byte"
        try {
          val _ = NonNegByte.unsafeFrom(n)
          Result
            .failure
            .log(
              s"""IllegalArgumentException was expected from NonNegByte.unsafeFrom(${n.toString}), but it was not thrown."""
            )
        } catch {
          case ex: IllegalArgumentException =>
            ex.getMessage ==== expected

        }
      }

    def testValue: Property =
      for {
        n <- Gen.byte(Range.linear(0, Byte.MaxValue)).log("n")
      } yield {
        val expected = n
        val actual   = NonNegByte.unsafeFrom(n)
        actual.value ==== expected
      }

    def testUnapplyWithPatternMatching: Property =
      for {
        n <- Gen.byte(Range.linear(0, Byte.MaxValue)).log("n")
      } yield {
        val expected = n
        val nes      = NonNegByte.unsafeFrom(n)
        nes match {
          case NonNegByte(actual) =>
            actual ==== expected
        }
      }

    def testOrdering: Property =
      for {
        n1 <- Gen.byte(Range.linear(0, Byte.MaxValue)).log("n1")
        n2 <- Gen.byte(Range.linear(0, Byte.MaxValue)).log("n2")
      } yield {
        val input1 = NonNegByte.unsafeFrom(n1)
        val input2 = NonNegByte.unsafeFrom(n2)
        Ordering[NonNegByte].compare(input1, input2) ==== Ordering[Byte].compare(input1.value, input2.value)
      }

    def testNumericOrdered: Property =
      for {
        n1 <- Gen.byte(Range.linear(0, Byte.MaxValue)).log("n1")
        n2 <- Gen.byte(Range.linear(0, Byte.MaxValue)).log("n2")
      } yield {
        val input1: Ordered[NonNegByte] = NonNegByte.unsafeFrom(n1)
        val input2: NonNegByte          = NonNegByte.unsafeFrom(n2)
        input1.compare(input2) ==== n1.compare(n2)
      }

    def testMinValue: Result = {
      val expected  = NonNegByte(0)
      val actual    = NonNegByte.MinValue
      val actualMin = NonNegByte.min

      Result.all(
        List(
          (actual ==== expected).log(s"NonNegByte.MinValue ==== ${expected.toString} failed"),
          (actualMin ==== expected).log(s"NonNegByte.min ==== ${expected.toString} failed"),
        )
      )
    }

    def testMaxValue: Result = {
      val expected  = NonNegByte(Byte.MaxValue)
      val actual    = NonNegByte.MaxValue
      val actualMax = NonNegByte.max

      Result.all(
        List(
          (actual ==== expected).log(s"NonNegByte.MaxValue ==== ${expected.toString} failed"),
          (actualMax ==== expected).log(s"NonNegByte.max ==== ${expected.toString} failed"),
        )
      )
    }

  }

  object PosByteSpec {

    import all.PosByte

    def tests: List[Test] = List(
      example("test PosByte.apply", testApply),
      property("test PosByte.from(valid)", testFromValid),
      property("test PosByte.from(invalid)", testFromInvalid),
      property("test PosByte.unsafeFrom(valid)", testUnsafeFromValid),
      property("test PosByte.unsafeFrom(invalid)", testUnsafeFromInvalid),
      property("test PosByte.value", testValue),
      property("test PosByte.unapply", testUnapplyWithPatternMatching),
      property("test Ordering[PosByte]", testOrdering),
      property("test Ordered[PosByte]", testNumericOrdered),
      example("test PosByte.MinValue", testMinValue),
      example("test PosByte.MaxValue", testMaxValue),
    )

    def testApply: Result = {
      /* The actual test is whether this compiles or not actual ==== expected is meaningless here */
      val expected = PosByte(1)
      val actual   = PosByte(1)
      actual ==== expected
    }

    def testFromValid: Property =
      for {
        n <- Gen.byte(Range.linear(1, Byte.MaxValue)).log("n")
      } yield {
        val expected = PosByte.unsafeFrom(n)
        val actual   = PosByte.from(n)
        actual ==== Right(expected)
      }

    def testFromInvalid: Property =
      for {
        n <- Gen.byte(Range.linear(Byte.MinValue, 0)).log("n")
      } yield {
        val expected = s"Invalid value: [${n.toString}]. It must be a positive Byte"
        val actual   = PosByte.from(n)
        actual ==== Left(expected)
      }

    def testUnsafeFromValid: Property =
      for {
        n <- Gen.byte(Range.linear(1, Byte.MaxValue)).log("n")
      } yield {
        val expected = PosByte.unsafeFrom(n)
        val actual   = PosByte.unsafeFrom(n)
        actual ==== expected
      }

    def testUnsafeFromInvalid: Property =
      for {
        n <- Gen.byte(Range.linear(Byte.MinValue, 0)).log("n")
      } yield {
        val expected = s"Invalid value: [$n]. It must be a positive Byte"
        try {
          val _ = PosByte.unsafeFrom(n)
          Result
            .failure
            .log(
              s"""IllegalArgumentException was expected from PosByte.unsafeFrom(${n.toString}), but it was not thrown."""
            )
        } catch {
          case ex: IllegalArgumentException =>
            ex.getMessage ==== expected

        }
      }

    def testValue: Property =
      for {
        n <- Gen.byte(Range.linear(1, Byte.MaxValue)).log("n")
      } yield {
        val expected = n
        val actual   = PosByte.unsafeFrom(n)
        actual.value ==== expected
      }

    def testUnapplyWithPatternMatching: Property =
      for {
        n <- Gen.byte(Range.linear(1, Byte.MaxValue)).log("n")
      } yield {
        val expected = n
        val nes      = PosByte.unsafeFrom(n)
        nes match {
          case PosByte(actual) =>
            actual ==== expected
        }
      }

    def testOrdering: Property =
      for {
        n1 <- Gen.byte(Range.linear(1, Byte.MaxValue)).log("n1")
        n2 <- Gen.byte(Range.linear(1, Byte.MaxValue)).log("n2")
      } yield {
        val input1 = PosByte.unsafeFrom(n1)
        val input2 = PosByte.unsafeFrom(n2)
        Ordering[PosByte].compare(input1, input2) ==== Ordering[Byte].compare(input1.value, input2.value)
      }

    def testNumericOrdered: Property =
      for {
        n1 <- Gen.byte(Range.linear(1, Byte.MaxValue)).log("n1")
        n2 <- Gen.byte(Range.linear(1, Byte.MaxValue)).log("n2")
      } yield {
        val input1: Ordered[PosByte] = PosByte.unsafeFrom(n1)
        val input2: PosByte          = PosByte.unsafeFrom(n2)
        input1.compare(input2) ==== n1.compare(n2)
      }

    def testMinValue: Result = {
      val expected  = PosByte(1)
      val actual    = PosByte.MinValue
      val actualMin = PosByte.min

      Result.all(
        List(
          (actual ==== expected).log(s"PosByte.MinValue ==== ${expected.toString} failed"),
          (actualMin ==== expected).log(s"PosByte.min ==== ${expected.toString} failed"),
        )
      )
    }

    def testMaxValue: Result = {
      val expected  = PosByte(Byte.MaxValue)
      val actual    = PosByte.MaxValue
      val actualMax = PosByte.max

      Result.all(
        List(
          (actual ==== expected).log(s"PosByte.MaxValue ==== ${expected.toString} failed"),
          (actualMax ==== expected).log(s"PosByte.max ==== ${expected.toString} failed"),
        )
      )
    }

  }

  object NonPosByteSpec {

    import all.NonPosByte

    def tests: List[Test] = List(
      example("test NonPosByte.apply", testApply),
      property("test NonPosByte.from(valid)", testFromValid),
      property("test NonPosByte.from(invalid)", testFromInvalid),
      property("test NonPosByte.unsafeFrom(valid)", testUnsafeFromValid),
      property("test NonPosByte.unsafeFrom(invalid)", testUnsafeFromInvalid),
      property("test NonPosByte.value", testValue),
      property("test NonPosByte.unapply", testUnapplyWithPatternMatching),
      property("test Ordering[NonPosByte]", testOrdering),
      property("test Ordered[NonPosByte]", testNumericOrdered),
      example("test NonPosByte.MinValue", testMinValue),
      example("test NonPosByte.MaxValue", testMaxValue),
    )

    def testApply: Result = {
      /* The actual test is whether this compiles or not actual ==== expected is meaningless here */
      val expected  = NonPosByte(0)
      val actual    = NonPosByte(0)
      val expected2 = NonPosByte(Byte.MinValue)
      val actual2   = NonPosByte(Byte.MinValue)
      Result.all(
        List(
          actual ==== expected,
          actual2 ==== expected2,
        )
      )
    }

    def testFromValid: Property =
      for {
        n <- Gen.byte(Range.linear(Byte.MinValue, 0)).log("n")
      } yield {
        val expected = NonPosByte.unsafeFrom(n)
        val actual   = NonPosByte.from(n)
        actual ==== Right(expected)
      }

    def testFromInvalid: Property =
      for {
        n <- Gen.byte(Range.linear(1, Byte.MaxValue)).log("n")
      } yield {
        val expected = s"Invalid value: [${n.toString}]. It must be a non-positive Byte"
        val actual   = NonPosByte.from(n)
        actual ==== Left(expected)
      }

    def testUnsafeFromValid: Property =
      for {
        n <- Gen.byte(Range.linear(Byte.MinValue, 0)).log("n")
      } yield {
        val expected = NonPosByte.unsafeFrom(n)
        val actual   = NonPosByte.unsafeFrom(n)
        actual ==== expected
      }

    def testUnsafeFromInvalid: Property =
      for {
        n <- Gen.byte(Range.linear(1, Byte.MaxValue)).log("n")
      } yield {
        val expected = s"Invalid value: [$n]. It must be a non-positive Byte"
        try {
          val _ = NonPosByte.unsafeFrom(n)
          Result
            .failure
            .log(
              s"""IllegalArgumentException was expected from NonPosByte.unsafeFrom(${n.toString}), but it was not thrown."""
            )
        } catch {
          case ex: IllegalArgumentException =>
            ex.getMessage ==== expected

        }
      }

    def testValue: Property =
      for {
        n <- Gen.byte(Range.linear(Byte.MinValue, 0)).log("n")
      } yield {
        val expected = n
        val actual   = NonPosByte.unsafeFrom(n)
        actual.value ==== expected
      }

    def testUnapplyWithPatternMatching: Property =
      for {
        n <- Gen.byte(Range.linear(Byte.MinValue, 0)).log("n")
      } yield {
        val expected = n
        val nes      = NonPosByte.unsafeFrom(n)
        nes match {
          case NonPosByte(actual) =>
            actual ==== expected
        }
      }

    def testOrdering: Property =
      for {
        n1 <- Gen.byte(Range.linear(Byte.MinValue, 0)).log("n1")
        n2 <- Gen.byte(Range.linear(Byte.MinValue, 0)).log("n2")
      } yield {
        val input1 = NonPosByte.unsafeFrom(n1)
        val input2 = NonPosByte.unsafeFrom(n2)
        Ordering[NonPosByte].compare(input1, input2) ==== Ordering[Byte].compare(input1.value, input2.value)
      }

    def testNumericOrdered: Property =
      for {
        n1 <- Gen.byte(Range.linear(Byte.MinValue, 0)).log("n1")
        n2 <- Gen.byte(Range.linear(Byte.MinValue, 0)).log("n2")
      } yield {
        val input1: Ordered[NonPosByte] = NonPosByte.unsafeFrom(n1)
        val input2: NonPosByte          = NonPosByte.unsafeFrom(n2)
        input1.compare(input2) ==== n1.compare(n2)
      }

    def testMinValue: Result = {
      val expected  = NonPosByte(Byte.MinValue)
      val actual    = NonPosByte.MinValue
      val actualMin = NonPosByte.min

      Result.all(
        List(
          (actual ==== expected).log(s"NonPosByte.MinValue ==== ${expected.toString} failed"),
          (actualMin ==== expected).log(s"NonPosByte.min ==== ${expected.toString} failed"),
        )
      )
    }

    def testMaxValue: Result = {
      val expected  = NonPosByte(0)
      val actual    = NonPosByte.MaxValue
      val actualMax = NonPosByte.max

      Result.all(
        List(
          (actual ==== expected).log(s"NonPosByte.MaxValue ==== ${expected.toString} failed"),
          (actualMax ==== expected).log(s"NonPosByte.max ==== ${expected.toString} failed"),
        )
      )
    }

  }

  object NegFloatSpec {

    import all.NegFloat

    def tests: List[Test] = List(
      example("test NegFloat.apply", testApply),
      property("test NegFloat.from(valid)", testFromValid),
      property("test NegFloat.from(invalid)", testFromInvalid),
      property("test NegFloat.unsafeFrom(valid)", testUnsafeFromValid),
      property("test NegFloat.unsafeFrom(invalid)", testUnsafeFromInvalid),
      property("test NegFloat.value", testValue),
      property("test NegFloat.unapply", testUnapplyWithPatternMatching),
      property("test Ordering[NegFloat]", testOrdering),
      property("test Ordered[NegFloat]", testNumericOrdered),
      example("test NegFloat.MinValue", testMinValue),
      example("test NegFloat.MaxValue", testMaxValue),
    )

    def testApply: Result = {
      /* The actual test is whether this compiles or not actual ==== expected is meaningless here */
      val expected = NegFloat(-0.0000001f)
      val actual   = NegFloat(-0.0000001f)
      actual ==== expected
    }

    def testFromValid: Property =
      for {
        n <- Gen.double(Range.linearFrac(Float.MinValue, -0.0000001f)).map(_.toFloat).log("n")
      } yield {
        val expected = NegFloat.unsafeFrom(n)
        val actual   = NegFloat.from(n)
        actual ==== Right(expected)
      }

    def testFromInvalid: Property =
      for {
        n <- Gen.double(Range.linearFrac(0f, Float.MaxValue)).map(_.toFloat).log("n")
      } yield {
        val expected = s"Invalid value: [${n.toString}]. It must be a negative Float"
        val actual   = NegFloat.from(n)
        actual ==== Left(expected)
      }

    def testUnsafeFromValid: Property =
      for {
        n <- Gen.double(Range.linearFrac(Float.MinValue, -0.0000001f)).map(_.toFloat).log("n")
      } yield {
        val expected = NegFloat.unsafeFrom(n)
        val actual   = NegFloat.unsafeFrom(n)
        actual ==== expected
      }

    def testUnsafeFromInvalid: Property =
      for {
        n <- Gen.double(Range.linearFrac(0f, Float.MaxValue)).map(_.toFloat).log("n")
      } yield {
        val expected = s"Invalid value: [$n]. It must be a negative Float"
        try {
          val _ = NegFloat.unsafeFrom(n)
          Result
            .failure
            .log(
              s"""IllegalArgumentException was expected from NegFloat.unsafeFrom(${n.toString}), but it was not thrown."""
            )
        } catch {
          case ex: IllegalArgumentException =>
            ex.getMessage ==== expected

        }
      }

    def testValue: Property =
      for {
        n <- Gen.double(Range.linearFrac(Float.MinValue, -0.0000001f)).map(_.toFloat).log("n")
      } yield {
        val expected = n
        val actual   = NegFloat.unsafeFrom(n)
        actual.value ==== expected
      }

    def testUnapplyWithPatternMatching: Property =
      for {
        n <- Gen.double(Range.linearFrac(Float.MinValue, -0.0000001f)).map(_.toFloat).log("n")
      } yield {
        val expected = n
        val nes      = NegFloat.unsafeFrom(n)
        nes match {
          case NegFloat(actual) =>
            actual ==== expected
        }
      }

    def testOrdering: Property =
      for {
        n1 <- Gen.double(Range.linearFrac(Float.MinValue, -0.0000001f)).map(_.toFloat).log("n1")
        n2 <- Gen.double(Range.linearFrac(Float.MinValue, -0.0000001f)).map(_.toFloat).log("n2")
      } yield {
        val input1 = NegFloat.unsafeFrom(n1)
        val input2 = NegFloat.unsafeFrom(n2)
        Ordering[NegFloat].compare(input1, input2) ==== Ordering[Float].compare(input1.value, input2.value)
      }

    def testNumericOrdered: Property =
      for {
        n1 <- Gen.double(Range.linearFrac(Float.MinValue, -0.0000001f)).map(_.toFloat).log("n1")
        n2 <- Gen.double(Range.linearFrac(Float.MinValue, -0.0000001f)).map(_.toFloat).log("n2")
      } yield {
        val input1: Ordered[NegFloat] = NegFloat.unsafeFrom(n1)
        val input2: NegFloat          = NegFloat.unsafeFrom(n2)
        input1.compare(input2) ==== n1.compare(n2)
      }

    def testMinValue: Result = {
      val expected  = NegFloat.unsafeFrom(Float.MinValue)
      val actual    = NegFloat.MinValue
      val actualMin = NegFloat.min

      Result.all(
        List(
          (actual ==== expected).log(s"NegFloat.MinValue ==== ${expected.toString} failed"),
          (actualMin ==== expected).log(s"NegFloat.min ==== ${expected.toString} failed"),
        )
      )
    }

    def testMaxValue: Result = {
      val expected  = NegFloat.unsafeFrom(math.nextDown(0f))
      val actual    = NegFloat.MaxValue
      val actualMax = NegFloat.max

      Result.all(
        List(
          (actual ==== expected).log(s"NegFloat.MaxValue ==== ${expected.toString} failed"),
          (actualMax ==== expected).log(s"NegFloat.max ==== ${expected.toString} failed"),
        )
      )
    }

  }

  object NonNegFloatSpec {

    import all.NonNegFloat

    def tests: List[Test] = List(
      example("test NonNegFloat.apply", testApply),
      property("test NonNegFloat.from(valid)", testFromValid),
      property("test NonNegFloat.from(invalid)", testFromInvalid),
      property("test NonNegFloat.unsafeFrom(valid)", testUnsafeFromValid),
      property("test NonNegFloat.unsafeFrom(invalid)", testUnsafeFromInvalid),
      property("test NonNegFloat.value", testValue),
      property("test NonNegFloat.unapply", testUnapplyWithPatternMatching),
      property("test Ordering[NonNegFloat]", testOrdering),
      property("test Ordered[NonNegFloat]", testNumericOrdered),
      example("test NonNegFloat.MinValue", testMinValue),
      example("test NonNegFloat.MaxValue", testMaxValue),
    )

    def testApply: Result = {
      /* The actual test is whether this compiles or not actual ==== expected is meaningless here */
      val expected = NonNegFloat(0f)
      val actual   = NonNegFloat(0f)
      actual ==== expected
    }

    def testFromValid: Property =
      for {
        n <- Gen.double(Range.linearFrac(0f, Float.MaxValue)).map(_.toFloat).log("n")
      } yield {
        val expected = NonNegFloat.unsafeFrom(n)
        val actual   = NonNegFloat.from(n)
        actual ==== Right(expected)
      }

    def testFromInvalid: Property =
      for {
        n <- Gen.double(Range.linearFrac(Float.MinValue, -0.0000001f)).map(_.toFloat).log("n")
      } yield {
        val expected = s"Invalid value: [${n.toString}]. It must be a non-negative Float"
        val actual   = NonNegFloat.from(n)
        actual ==== Left(expected)
      }

    def testUnsafeFromValid: Property =
      for {
        n <- Gen.double(Range.linearFrac(0f, Float.MaxValue)).map(_.toFloat).log("n")
      } yield {
        val expected = NonNegFloat.unsafeFrom(n)
        val actual   = NonNegFloat.unsafeFrom(n)
        actual ==== expected
      }

    def testUnsafeFromInvalid: Property =
      for {
        n <- Gen.double(Range.linearFrac(-0.0000001f, Float.MinValue)).map(_.toFloat).log("n")
      } yield {
        val expected = s"Invalid value: [$n]. It must be a non-negative Float"
        try {
          val _ = NonNegFloat.unsafeFrom(n)
          Result
            .failure
            .log(
              s"""IllegalArgumentException was expected from NonNegFloat.unsafeFrom(${n.toString}), but it was not thrown."""
            )
        } catch {
          case ex: IllegalArgumentException =>
            ex.getMessage ==== expected

        }
      }

    def testValue: Property =
      for {
        n <- Gen.double(Range.linearFrac(0, Float.MaxValue)).map(_.toFloat).log("n")
      } yield {
        val expected = n
        val actual   = NonNegFloat.unsafeFrom(n)
        actual.value ==== expected
      }

    def testUnapplyWithPatternMatching: Property =
      for {
        n <- Gen.double(Range.linearFrac(0, Float.MaxValue)).map(_.toFloat).log("n")
      } yield {
        val expected = n
        val nes      = NonNegFloat.unsafeFrom(n)
        nes match {
          case NonNegFloat(actual) =>
            actual ==== expected
        }
      }

    def testOrdering: Property =
      for {
        n1 <- Gen.double(Range.linearFrac(0, Float.MaxValue)).map(_.toFloat).log("n1")
        n2 <- Gen.double(Range.linearFrac(0, Float.MaxValue)).map(_.toFloat).log("n2")
      } yield {
        val input1 = NonNegFloat.unsafeFrom(n1)
        val input2 = NonNegFloat.unsafeFrom(n2)
        Ordering[NonNegFloat].compare(input1, input2) ==== Ordering[Float].compare(input1.value, input2.value)
      }

    def testNumericOrdered: Property =
      for {
        n1 <- Gen.double(Range.linearFrac(0, Float.MaxValue)).map(_.toFloat).log("n1")
        n2 <- Gen.double(Range.linearFrac(0, Float.MaxValue)).map(_.toFloat).log("n2")
      } yield {
        val input1: Ordered[NonNegFloat] = NonNegFloat.unsafeFrom(n1)
        val input2: NonNegFloat          = NonNegFloat.unsafeFrom(n2)
        input1.compare(input2) ==== n1.compare(n2)
      }

    def testMinValue: Result = {
      val expected  = NonNegFloat(0f)
      val actual    = NonNegFloat.MinValue
      val actualMin = NonNegFloat.min

      Result.all(
        List(
          (actual ==== expected).log(s"NonNegFloat.MinValue ==== ${expected.toString} failed"),
          (actualMin ==== expected).log(s"NonNegFloat.min ==== ${expected.toString} failed"),
        )
      )
    }

    def testMaxValue: Result = {
      val expected  = NonNegFloat(Float.MaxValue)
      val actual    = NonNegFloat.MaxValue
      val actualMax = NonNegFloat.max

      Result.all(
        List(
          (actual ==== expected).log(s"NonNegFloat.MaxValue ==== ${expected.toString} failed"),
          (actualMax ==== expected).log(s"NonNegFloat.max ==== ${expected.toString} failed"),
        )
      )
    }

  }

  object PosFloatSpec {

    import all.PosFloat

    def tests: List[Test] = List(
      example("test PosFloat.apply", testApply),
      property("test PosFloat.from(valid)", testFromValid),
      property("test PosFloat.from(invalid)", testFromInvalid),
      property("test PosFloat.unsafeFrom(valid)", testUnsafeFromValid),
      property("test PosFloat.unsafeFrom(invalid)", testUnsafeFromInvalid),
      property("test PosFloat.value", testValue),
      property("test PosFloat.unapply", testUnapplyWithPatternMatching),
      property("test Ordering[PosFloat]", testOrdering),
      property("test Ordered[PosFloat]", testNumericOrdered),
      example("test PosFloat.MinValue", testMinValue),
      example("test PosFloat.MaxValue", testMaxValue),
    )

    def testApply: Result = {
      /* The actual test is whether this compiles or not actual ==== expected is meaningless here */
      val expected = PosFloat(0.0000001f)
      val actual   = PosFloat(0.0000001f)
      actual ==== expected
    }

    def testFromValid: Property =
      for {
        n <- Gen.double(Range.linearFrac(0.0000001f, Float.MaxValue)).map(_.toFloat).log("n")
      } yield {
        val expected = PosFloat.unsafeFrom(n)
        val actual   = PosFloat.from(n)
        actual ==== Right(expected)
      }

    def testFromInvalid: Property =
      for {
        n <- Gen.double(Range.linearFrac(Float.MinValue, 0f)).map(_.toFloat).log("n")
      } yield {
        val expected = s"Invalid value: [${n.toString}]. It must be a positive Float"
        val actual   = PosFloat.from(n)
        actual ==== Left(expected)
      }

    def testUnsafeFromValid: Property =
      for {
        n <- Gen.double(Range.linearFrac(0.0000001f, Float.MaxValue)).map(_.toFloat).log("n")
      } yield {
        val expected = PosFloat.unsafeFrom(n)
        val actual   = PosFloat.unsafeFrom(n)
        actual ==== expected
      }

    def testUnsafeFromInvalid: Property =
      for {
        n <- Gen.double(Range.linearFrac(Float.MinValue, 0f)).map(_.toFloat).log("n")
      } yield {
        val expected = s"Invalid value: [$n]. It must be a positive Float"
        try {
          val _ = PosFloat.unsafeFrom(n)
          Result
            .failure
            .log(
              s"""IllegalArgumentException was expected from PosFloat.unsafeFrom(${n.toString}), but it was not thrown."""
            )
        } catch {
          case ex: IllegalArgumentException =>
            ex.getMessage ==== expected

        }
      }

    def testValue: Property =
      for {
        n <- Gen.double(Range.linearFrac(0.0000001f, Float.MaxValue)).map(_.toFloat).log("n")
      } yield {
        val expected = n
        val actual   = PosFloat.unsafeFrom(n)
        actual.value ==== expected
      }

    def testUnapplyWithPatternMatching: Property =
      for {
        n <- Gen.double(Range.linearFrac(0.0000001f, Float.MaxValue)).map(_.toFloat).log("n")
      } yield {
        val expected = n
        val nes      = PosFloat.unsafeFrom(n)
        nes match {
          case PosFloat(actual) =>
            actual ==== expected
        }
      }

    def testOrdering: Property =
      for {
        n1 <- Gen.double(Range.linearFrac(0.0000001f, Float.MaxValue)).map(_.toFloat).log("n1")
        n2 <- Gen.double(Range.linearFrac(0.0000001f, Float.MaxValue)).map(_.toFloat).log("n2")
      } yield {
        val input1 = PosFloat.unsafeFrom(n1)
        val input2 = PosFloat.unsafeFrom(n2)
        Ordering[PosFloat].compare(input1, input2) ==== Ordering[Float].compare(input1.value, input2.value)
      }

    def testNumericOrdered: Property =
      for {
        n1 <- Gen.double(Range.linearFrac(0.0000001f, Float.MaxValue)).map(_.toFloat).log("n1")
        n2 <- Gen.double(Range.linearFrac(0.0000001f, Float.MaxValue)).map(_.toFloat).log("n2")
      } yield {
        val input1: Ordered[PosFloat] = PosFloat.unsafeFrom(n1)
        val input2: PosFloat          = PosFloat.unsafeFrom(n2)
        input1.compare(input2) ==== n1.compare(n2)
      }

    def testMinValue: Result = {
      val expected  = PosFloat.unsafeFrom(math.nextUp(0f))
      val actual    = PosFloat.MinValue
      val actualMin = PosFloat.min

      Result.all(
        List(
          (actual ==== expected).log(s"PosFloat.MinValue ==== ${expected.toString} failed"),
          (actualMin ==== expected).log(s"PosFloat.min ==== ${expected.toString} failed"),
        )
      )
    }

    def testMaxValue: Result = {
      val expected  = PosFloat(Float.MaxValue)
      val actual    = PosFloat.MaxValue
      val actualMax = PosFloat.max

      Result.all(
        List(
          (actual ==== expected).log(s"PosFloat.MaxValue ==== ${expected.toString} failed"),
          (actualMax ==== expected).log(s"PosFloat.max ==== ${expected.toString} failed"),
        )
      )
    }

  }

  object NonPosFloatSpec {

    import all.NonPosFloat

    def tests: List[Test] = List(
      example("test NonPosFloat.apply", testApply),
      property("test NonPosFloat.from(valid)", testFromValid),
      property("test NonPosFloat.from(invalid)", testFromInvalid),
      property("test NonPosFloat.unsafeFrom(valid)", testUnsafeFromValid),
      property("test NonPosFloat.unsafeFrom(invalid)", testUnsafeFromInvalid),
      property("test NonPosFloat.value", testValue),
      property("test NonPosFloat.unapply", testUnapplyWithPatternMatching),
      property("test Ordering[NonPosFloat]", testOrdering),
      property("test Ordered[NonPosFloat]", testNumericOrdered),
      example("test NonPosFloat.MinValue", testMinValue),
      example("test NonPosFloat.MaxValue", testMaxValue),
    )

    def testApply: Result = {
      /* The actual test is whether this compiles or not actual ==== expected is meaningless here */
      val expected = NonPosFloat(0f)
      val actual   = NonPosFloat(0f)

      val expected2 = NonPosFloat(-3.4028234e38f)
      val actual2   = NonPosFloat(-3.4028234e38f)

      Result.all(
        List(
          actual ==== expected,
          actual2 ==== expected2,
        )
      )
    }

    def testFromValid: Property =
      for {
        n <- Gen.double(Range.linearFrac(Float.MinValue, 0f)).map(_.toFloat).log("n")
      } yield {
        val expected = NonPosFloat.unsafeFrom(n)
        val actual   = NonPosFloat.from(n)
        actual ==== Right(expected)
      }

    def testFromInvalid: Property =
      for {
        n <- Gen.double(Range.linearFrac(0.0000001f, Float.MaxValue)).map(_.toFloat).log("n")
      } yield {
        val expected = s"Invalid value: [${n.toString}]. It must be a non-positive Float"
        val actual   = NonPosFloat.from(n)
        actual ==== Left(expected)
      }

    def testUnsafeFromValid: Property =
      for {
        n <- Gen.double(Range.linearFrac(Float.MinValue, 0f)).map(_.toFloat).log("n")
      } yield {
        val expected = NonPosFloat.unsafeFrom(n)
        val actual   = NonPosFloat.unsafeFrom(n)
        actual ==== expected
      }

    def testUnsafeFromInvalid: Property =
      for {
        n <- Gen.double(Range.linearFrac(0.0000001f, Float.MaxValue)).map(_.toFloat).log("n")
      } yield {
        val expected = s"Invalid value: [$n]. It must be a non-positive Float"
        try {
          val _ = NonPosFloat.unsafeFrom(n)
          Result
            .failure
            .log(
              s"""IllegalArgumentException was expected from NonPosFloat.unsafeFrom(${n.toString}), but it was not thrown."""
            )
        } catch {
          case ex: IllegalArgumentException =>
            ex.getMessage ==== expected

        }
      }

    def testValue: Property =
      for {
        n <- Gen.double(Range.linearFrac(Float.MinValue, 0f)).map(_.toFloat).log("n")
      } yield {
        val expected = n
        val actual   = NonPosFloat.unsafeFrom(n)
        actual.value ==== expected
      }

    def testUnapplyWithPatternMatching: Property =
      for {
        n <- Gen.double(Range.linearFrac(Float.MinValue, 0f)).map(_.toFloat).log("n")
      } yield {
        val expected = n
        val nes      = NonPosFloat.unsafeFrom(n)
        nes match {
          case NonPosFloat(actual) =>
            actual ==== expected
        }
      }

    def testOrdering: Property =
      for {
        n1 <- Gen.double(Range.linearFrac(Float.MinValue, 0f)).map(_.toFloat).log("n1")
        n2 <- Gen.double(Range.linearFrac(Float.MinValue, 0f)).map(_.toFloat).log("n2")
      } yield {
        val input1 = NonPosFloat.unsafeFrom(n1)
        val input2 = NonPosFloat.unsafeFrom(n2)
        Ordering[NonPosFloat].compare(input1, input2) ==== Ordering[Float].compare(input1.value, input2.value)
      }

    def testNumericOrdered: Property =
      for {
        n1 <- Gen.double(Range.linearFrac(Float.MinValue, 0f)).map(_.toFloat).log("n1")
        n2 <- Gen.double(Range.linearFrac(Float.MinValue, 0f)).map(_.toFloat).log("n2")
      } yield {
        val input1: Ordered[NonPosFloat] = NonPosFloat.unsafeFrom(n1)
        val input2: NonPosFloat          = NonPosFloat.unsafeFrom(n2)
        input1.compare(input2) ==== n1.compare(n2)
      }

    def testMinValue: Result = {
      val expected  = NonPosFloat.unsafeFrom(Float.MinValue)
      val actual    = NonPosFloat.MinValue
      val actualMin = NonPosFloat.min

      Result.all(
        List(
          (actual ==== expected).log(s"NonPosFloat.MinValue ==== ${expected.toString} failed"),
          (actualMin ==== expected).log(s"NonPosFloat.min ==== ${expected.toString} failed"),
        )
      )
    }

    def testMaxValue: Result = {
      val expected  = NonPosFloat(0f)
      val actual    = NonPosFloat.MaxValue
      val actualMax = NonPosFloat.max

      Result.all(
        List(
          (actual ==== expected).log(s"NonPosFloat.MaxValue ==== ${expected.toString} failed"),
          (actualMax ==== expected).log(s"NonPosFloat.max ==== ${expected.toString} failed"),
        )
      )
    }

  }

  object NegDoubleSpec {

    import all.NegDouble

    def tests: List[Test] = List(
      example("test NegDouble.apply", testApply),
      property("test NegDouble.from(valid)", testFromValid),
      property("test NegDouble.from(invalid)", testFromInvalid),
      property("test NegDouble.unsafeFrom(valid)", testUnsafeFromValid),
      property("test NegDouble.unsafeFrom(invalid)", testUnsafeFromInvalid),
      property("test NegDouble.value", testValue),
      property("test NegDouble.unapply", testUnapplyWithPatternMatching),
      property("test Ordering[NegDouble]", testOrdering),
      property("test Ordered[NegDouble]", testNumericOrdered),
      example("test NegDouble.MinValue", testMinValue),
      example("test NegDouble.MaxValue", testMaxValue),
    )

    def testApply: Result = {
      /* The actual test is whether this compiles or not actual ==== expected is meaningless here */
      val expected = NegDouble(-0.0000001d)
      val actual   = NegDouble(-0.0000001d)
      actual ==== expected
    }

    def testFromValid: Property =
      for {
        n <- Gen.double(Range.linearFrac(Double.MinValue, -0.0000001d)).log("n")
      } yield {
        val expected = NegDouble.unsafeFrom(n)
        val actual   = NegDouble.from(n)
        actual ==== Right(expected)
      }

    def testFromInvalid: Property =
      for {
        n <- Gen.double(Range.linearFrac(0d, Double.MaxValue)).log("n")
      } yield {
        val expected = s"Invalid value: [${n.toString}]. It must be a negative Double"
        val actual   = NegDouble.from(n)
        actual ==== Left(expected)
      }

    def testUnsafeFromValid: Property =
      for {
        n <- Gen.double(Range.linearFrac(Double.MinValue, -0.0000001d)).log("n")
      } yield {
        val expected = NegDouble.unsafeFrom(n)
        val actual   = NegDouble.unsafeFrom(n)
        actual ==== expected
      }

    def testUnsafeFromInvalid: Property =
      for {
        n <- Gen.double(Range.linearFrac(0d, Double.MaxValue)).log("n")
      } yield {
        val expected = s"Invalid value: [$n]. It must be a negative Double"
        try {
          val _ = NegDouble.unsafeFrom(n)
          Result
            .failure
            .log(
              s"""IllegalArgumentException was expected from NegDouble.unsafeFrom(${n.toString}), but it was not thrown."""
            )
        } catch {
          case ex: IllegalArgumentException =>
            ex.getMessage ==== expected

        }
      }

    def testValue: Property =
      for {
        n <- Gen.double(Range.linearFrac(Double.MinValue, -0.0000001d)).log("n")
      } yield {
        val expected = n
        val actual   = NegDouble.unsafeFrom(n)
        actual.value ==== expected
      }

    def testUnapplyWithPatternMatching: Property =
      for {
        n <- Gen.double(Range.linearFrac(Double.MinValue, -0.0000001d)).log("n")
      } yield {
        val expected = n
        val nes      = NegDouble.unsafeFrom(n)
        nes match {
          case NegDouble(actual) =>
            actual ==== expected
        }
      }

    def testOrdering: Property =
      for {
        n1 <- Gen.double(Range.linearFrac(Double.MinValue, -0.0000001d)).log("n1")
        n2 <- Gen.double(Range.linearFrac(Double.MinValue, -0.0000001d)).log("n2")
      } yield {
        val input1 = NegDouble.unsafeFrom(n1)
        val input2 = NegDouble.unsafeFrom(n2)
        Ordering[NegDouble].compare(input1, input2) ==== Ordering[Double].compare(input1.value, input2.value)
      }

    def testNumericOrdered: Property =
      for {
        n1 <- Gen.double(Range.linearFrac(Double.MinValue, -0.0000001d)).log("n1")
        n2 <- Gen.double(Range.linearFrac(Double.MinValue, -0.0000001d)).log("n2")
      } yield {
        val input1: Ordered[NegDouble] = NegDouble.unsafeFrom(n1)
        val input2: NegDouble          = NegDouble.unsafeFrom(n2)
        input1.compare(input2) ==== n1.compare(n2)
      }

    def testMinValue: Result = {
      val expected  = NegDouble.unsafeFrom(Double.MinValue)
      val actual    = NegDouble.MinValue
      val actualMin = NegDouble.min

      Result.all(
        List(
          (actual ==== expected).log(s"NegDouble.MinValue ==== ${expected.toString} failed"),
          (actualMin ==== expected).log(s"NegDouble.min ==== ${expected.toString} failed"),
        )
      )
    }

    def testMaxValue: Result = {
      val expected  = NegDouble.unsafeFrom(math.nextDown(0d))
      val actual    = NegDouble.MaxValue
      val actualMax = NegDouble.max

      Result.all(
        List(
          (actual ==== expected).log(s"NegDouble.MaxValue ==== ${expected.toString} failed"),
          (actualMax ==== expected).log(s"NegDouble.max ==== ${expected.toString} failed"),
        )
      )
    }

  }

  object NonNegDoubleSpec {

    import all.NonNegDouble

    def tests: List[Test] = List(
      example("test NonNegDouble.apply", testApply),
      property("test NonNegDouble.from(valid)", testFromValid),
      property("test NonNegDouble.from(invalid)", testFromInvalid),
      property("test NonNegDouble.unsafeFrom(valid)", testUnsafeFromValid),
      property("test NonNegDouble.unsafeFrom(invalid)", testUnsafeFromInvalid),
      property("test NonNegDouble.value", testValue),
      property("test NonNegDouble.unapply", testUnapplyWithPatternMatching),
      property("test Ordering[NonNegDouble]", testOrdering),
      property("test Ordered[NonNegDouble]", testNumericOrdered),
      example("test NonNegDouble.MinValue", testMinValue),
      example("test NonNegDouble.MaxValue", testMaxValue),
    )

    def testApply: Result = {
      /* The actual test is whether this compiles or not actual ==== expected is meaningless here */
      val expected = NonNegDouble(0d)
      val actual   = NonNegDouble(0d)
      actual ==== expected
    }

    def testFromValid: Property =
      for {
        n <- Gen.double(Range.linearFrac(0d, Double.MaxValue)).log("n")
      } yield {
        val expected = NonNegDouble.unsafeFrom(n)
        val actual   = NonNegDouble.from(n)
        actual ==== Right(expected)
      }

    def testFromInvalid: Property =
      for {
        n <- Gen.double(Range.linearFrac(Double.MinValue, -0.0000001d)).log("n")
      } yield {
        val expected = s"Invalid value: [${n.toString}]. It must be a non-negative Double"
        val actual   = NonNegDouble.from(n)
        actual ==== Left(expected)
      }

    def testUnsafeFromValid: Property =
      for {
        n <- Gen.double(Range.linearFrac(0d, Double.MaxValue)).log("n")
      } yield {
        val expected = NonNegDouble.unsafeFrom(n)
        val actual   = NonNegDouble.unsafeFrom(n)
        actual ==== expected
      }

    def testUnsafeFromInvalid: Property =
      for {
        n <- Gen.double(Range.linearFrac(-0.0000001d, Double.MinValue)).log("n")
      } yield {
        val expected = s"Invalid value: [$n]. It must be a non-negative Double"
        try {
          val _ = NonNegDouble.unsafeFrom(n)
          Result
            .failure
            .log(
              s"""IllegalArgumentException was expected from NonNegDouble.unsafeFrom(${n.toString}), but it was not thrown."""
            )
        } catch {
          case ex: IllegalArgumentException =>
            ex.getMessage ==== expected

        }
      }

    def testValue: Property =
      for {
        n <- Gen.double(Range.linearFrac(0, Double.MaxValue)).log("n")
      } yield {
        val expected = n
        val actual   = NonNegDouble.unsafeFrom(n)
        actual.value ==== expected
      }

    def testUnapplyWithPatternMatching: Property =
      for {
        n <- Gen.double(Range.linearFrac(0, Double.MaxValue)).log("n")
      } yield {
        val expected = n
        val nes      = NonNegDouble.unsafeFrom(n)
        nes match {
          case NonNegDouble(actual) =>
            actual ==== expected
        }
      }

    def testOrdering: Property =
      for {
        n1 <- Gen.double(Range.linearFrac(0, Double.MaxValue)).log("n1")
        n2 <- Gen.double(Range.linearFrac(0, Double.MaxValue)).log("n2")
      } yield {
        val input1 = NonNegDouble.unsafeFrom(n1)
        val input2 = NonNegDouble.unsafeFrom(n2)
        Ordering[NonNegDouble].compare(input1, input2) ==== Ordering[Double].compare(input1.value, input2.value)
      }

    def testNumericOrdered: Property =
      for {
        n1 <- Gen.double(Range.linearFrac(0, Double.MaxValue)).log("n1")
        n2 <- Gen.double(Range.linearFrac(0, Double.MaxValue)).log("n2")
      } yield {
        val input1: Ordered[NonNegDouble] = NonNegDouble.unsafeFrom(n1)
        val input2: NonNegDouble          = NonNegDouble.unsafeFrom(n2)
        input1.compare(input2) ==== n1.compare(n2)
      }

    def testMinValue: Result = {
      val expected  = NonNegDouble(0d)
      val actual    = NonNegDouble.MinValue
      val actualMin = NonNegDouble.min

      Result.all(
        List(
          (actual ==== expected).log(s"NonNegDouble.MinValue ==== ${expected.toString} failed"),
          (actualMin ==== expected).log(s"NonNegDouble.min ==== ${expected.toString} failed"),
        )
      )
    }

    def testMaxValue: Result = {
      val expected  = NonNegDouble(Double.MaxValue)
      val actual    = NonNegDouble.MaxValue
      val actualMax = NonNegDouble.max

      Result.all(
        List(
          (actual ==== expected).log(s"NonNegDouble.MaxValue ==== ${expected.toString} failed"),
          (actualMax ==== expected).log(s"NonNegDouble.max ==== ${expected.toString} failed"),
        )
      )
    }

  }

  object PosDoubleSpec {

    import all.PosDouble

    def tests: List[Test] = List(
      example("test PosDouble.apply", testApply),
      property("test PosDouble.from(valid)", testFromValid),
      property("test PosDouble.from(invalid)", testFromInvalid),
      property("test PosDouble.unsafeFrom(valid)", testUnsafeFromValid),
      property("test PosDouble.unsafeFrom(invalid)", testUnsafeFromInvalid),
      property("test PosDouble.value", testValue),
      property("test PosDouble.unapply", testUnapplyWithPatternMatching),
      property("test Ordering[PosDouble]", testOrdering),
      property("test Ordered[PosDouble]", testNumericOrdered),
      example("test PosDouble.MinValue", testMinValue),
      example("test PosDouble.MaxValue", testMaxValue),
    )

    def testApply: Result = {
      /* The actual test is whether this compiles or not actual ==== expected is meaningless here */
      val expected = PosDouble(0.0000001d)
      val actual   = PosDouble(0.0000001d)
      actual ==== expected
    }

    def testFromValid: Property =
      for {
        n <- Gen.double(Range.linearFrac(0.0000001d, Double.MaxValue)).log("n")
      } yield {
        val expected = PosDouble.unsafeFrom(n)
        val actual   = PosDouble.from(n)
        actual ==== Right(expected)
      }

    def testFromInvalid: Property =
      for {
        n <- Gen.double(Range.linearFrac(Double.MinValue, 0d)).log("n")
      } yield {
        val expected = s"Invalid value: [${n.toString}]. It must be a positive Double"
        val actual   = PosDouble.from(n)
        actual ==== Left(expected)
      }

    def testUnsafeFromValid: Property =
      for {
        n <- Gen.double(Range.linearFrac(0.0000001d, Double.MaxValue)).log("n")
      } yield {
        val expected = PosDouble.unsafeFrom(n)
        val actual   = PosDouble.unsafeFrom(n)
        actual ==== expected
      }

    def testUnsafeFromInvalid: Property =
      for {
        n <- Gen.double(Range.linearFrac(Double.MinValue, 0d)).log("n")
      } yield {
        val expected = s"Invalid value: [$n]. It must be a positive Double"
        try {
          val _ = PosDouble.unsafeFrom(n)
          Result
            .failure
            .log(
              s"""IllegalArgumentException was expected from PosDouble.unsafeFrom(${n.toString}), but it was not thrown."""
            )
        } catch {
          case ex: IllegalArgumentException =>
            ex.getMessage ==== expected

        }
      }

    def testValue: Property =
      for {
        n <- Gen.double(Range.linearFrac(0.0000001d, Double.MaxValue)).log("n")
      } yield {
        val expected = n
        val actual   = PosDouble.unsafeFrom(n)
        actual.value ==== expected
      }

    def testUnapplyWithPatternMatching: Property =
      for {
        n <- Gen.double(Range.linearFrac(0.0000001d, Double.MaxValue)).log("n")
      } yield {
        val expected = n
        val nes      = PosDouble.unsafeFrom(n)
        nes match {
          case PosDouble(actual) =>
            actual ==== expected
        }
      }

    def testOrdering: Property =
      for {
        n1 <- Gen.double(Range.linearFrac(0.0000001d, Double.MaxValue)).log("n1")
        n2 <- Gen.double(Range.linearFrac(0.0000001d, Double.MaxValue)).log("n2")
      } yield {
        val input1 = PosDouble.unsafeFrom(n1)
        val input2 = PosDouble.unsafeFrom(n2)
        Ordering[PosDouble].compare(input1, input2) ==== Ordering[Double].compare(input1.value, input2.value)
      }

    def testNumericOrdered: Property =
      for {
        n1 <- Gen.double(Range.linearFrac(0.0000001d, Double.MaxValue)).log("n1")
        n2 <- Gen.double(Range.linearFrac(0.0000001d, Double.MaxValue)).log("n2")
      } yield {
        val input1: Ordered[PosDouble] = PosDouble.unsafeFrom(n1)
        val input2: PosDouble          = PosDouble.unsafeFrom(n2)
        input1.compare(input2) ==== n1.compare(n2)
      }

    def testMinValue: Result = {
      val expected  = PosDouble.unsafeFrom(math.nextUp(0d))
      val actual    = PosDouble.MinValue
      val actualMin = PosDouble.min

      Result.all(
        List(
          (actual ==== expected).log(s"PosDouble.MinValue ==== ${expected.toString} failed"),
          (actualMin ==== expected).log(s"PosDouble.min ==== ${expected.toString} failed"),
        )
      )
    }

    def testMaxValue: Result = {
      val expected  = PosDouble(Double.MaxValue)
      val actual    = PosDouble.MaxValue
      val actualMax = PosDouble.max

      Result.all(
        List(
          (actual ==== expected).log(s"PosDouble.MaxValue ==== ${expected.toString} failed"),
          (actualMax ==== expected).log(s"PosDouble.max ==== ${expected.toString} failed"),
        )
      )
    }

  }

  object NonPosDoubleSpec {

    import all.NonPosDouble

    def tests: List[Test] = List(
      example("test NonPosDouble.apply", testApply),
      property("test NonPosDouble.from(valid)", testFromValid),
      property("test NonPosDouble.from(invalid)", testFromInvalid),
      property("test NonPosDouble.unsafeFrom(valid)", testUnsafeFromValid),
      property("test NonPosDouble.unsafeFrom(invalid)", testUnsafeFromInvalid),
      property("test NonPosDouble.value", testValue),
      property("test NonPosDouble.unapply", testUnapplyWithPatternMatching),
      property("test Ordering[NonPosDouble]", testOrdering),
      property("test Ordered[NonPosDouble]", testNumericOrdered),
      example("test NonPosDouble.MinValue", testMinValue),
      example("test NonPosDouble.MaxValue", testMaxValue),
    )

    def testApply: Result = {
      /* The actual test is whether this compiles or not actual ==== expected is meaningless here */
      val expected = NonPosDouble(0d)
      val actual   = NonPosDouble(0d)

      val expected2 = NonPosDouble(-3.4028234e38d)
      val actual2   = NonPosDouble(-3.4028234e38d)

      Result.all(
        List(
          actual ==== expected,
          actual2 ==== expected2,
        )
      )
    }

    def testFromValid: Property =
      for {
        n <- Gen.double(Range.linearFrac(Double.MinValue, 0d)).log("n")
      } yield {
        val expected = NonPosDouble.unsafeFrom(n)
        val actual   = NonPosDouble.from(n)
        actual ==== Right(expected)
      }

    def testFromInvalid: Property =
      for {
        n <- Gen.double(Range.linearFrac(0.0000001d, Double.MaxValue)).log("n")
      } yield {
        val expected = s"Invalid value: [${n.toString}]. It must be a non-positive Double"
        val actual   = NonPosDouble.from(n)
        actual ==== Left(expected)
      }

    def testUnsafeFromValid: Property =
      for {
        n <- Gen.double(Range.linearFrac(Double.MinValue, 0d)).log("n")
      } yield {
        val expected = NonPosDouble.unsafeFrom(n)
        val actual   = NonPosDouble.unsafeFrom(n)
        actual ==== expected
      }

    def testUnsafeFromInvalid: Property =
      for {
        n <- Gen.double(Range.linearFrac(0.0000001d, Double.MaxValue)).log("n")
      } yield {
        val expected = s"Invalid value: [$n]. It must be a non-positive Double"
        try {
          val _ = NonPosDouble.unsafeFrom(n)
          Result
            .failure
            .log(
              s"""IllegalArgumentException was expected from NonPosDouble.unsafeFrom(${n.toString}), but it was not thrown."""
            )
        } catch {
          case ex: IllegalArgumentException =>
            ex.getMessage ==== expected

        }
      }

    def testValue: Property =
      for {
        n <- Gen.double(Range.linearFrac(Double.MinValue, 0d)).log("n")
      } yield {
        val expected = n
        val actual   = NonPosDouble.unsafeFrom(n)
        actual.value ==== expected
      }

    def testUnapplyWithPatternMatching: Property =
      for {
        n <- Gen.double(Range.linearFrac(Double.MinValue, 0d)).log("n")
      } yield {
        val expected = n
        val nes      = NonPosDouble.unsafeFrom(n)
        nes match {
          case NonPosDouble(actual) =>
            actual ==== expected
        }
      }

    def testOrdering: Property =
      for {
        n1 <- Gen.double(Range.linearFrac(Double.MinValue, 0d)).log("n1")
        n2 <- Gen.double(Range.linearFrac(Double.MinValue, 0d)).log("n2")
      } yield {
        val input1 = NonPosDouble.unsafeFrom(n1)
        val input2 = NonPosDouble.unsafeFrom(n2)
        Ordering[NonPosDouble].compare(input1, input2) ==== Ordering[Double].compare(input1.value, input2.value)
      }

    def testNumericOrdered: Property =
      for {
        n1 <- Gen.double(Range.linearFrac(Double.MinValue, 0d)).log("n1")
        n2 <- Gen.double(Range.linearFrac(Double.MinValue, 0d)).log("n2")
      } yield {
        val input1: Ordered[NonPosDouble] = NonPosDouble.unsafeFrom(n1)
        val input2: NonPosDouble          = NonPosDouble.unsafeFrom(n2)
        input1.compare(input2) ==== n1.compare(n2)
      }

    def testMinValue: Result = {
      val expected  = NonPosDouble.unsafeFrom(Double.MinValue)
      val actual    = NonPosDouble.MinValue
      val actualMin = NonPosDouble.min

      Result.all(
        List(
          (actual ==== expected).log(s"NonPosDouble.MinValue ==== ${expected.toString} failed"),
          (actualMin ==== expected).log(s"NonPosDouble.min ==== ${expected.toString} failed"),
        )
      )
    }

    def testMaxValue: Result = {
      val expected  = NonPosDouble(0d)
      val actual    = NonPosDouble.MaxValue
      val actualMax = NonPosDouble.max

      Result.all(
        List(
          (actual ==== expected).log(s"NonPosDouble.MaxValue ==== ${expected.toString} failed"),
          (actualMax ==== expected).log(s"NonPosDouble.max ==== ${expected.toString} failed"),
        )
      )
    }

  }

  object NegBigIntSpec {

    import all.NegBigInt

    def tests: List[Test] = List(
      example("test NegBigInt.apply", testApply),
      property("test NegBigInt.from(valid)", testFromValid),
      property("test NegBigInt.from(invalid)", testFromInvalid),
      property("test NegBigInt.unsafeFrom(valid)", testUnsafeFromValid),
      property("test NegBigInt.unsafeFrom(invalid)", testUnsafeFromInvalid),
      property("test NegBigInt.value", testValue),
      property("test NegBigInt.unapply", testUnapplyWithPatternMatching),
      property("test Ordering[NegBigInt]", testOrdering),
      property("test Ordered[NegBigInt]", testNumericOrdered),
    )

    def testApply: Result = {
      /* The actual test is whether this compiles or not actual ==== expected is meaningless here */
      val expected1 = NegBigInt(-1)
      val actual1   = NegBigInt(-1)

      val expected2 = NegBigInt(-1L)
      val actual2   = NegBigInt(-1L)

      val expected3 = NegBigInt("-1")
      val actual3   = NegBigInt("-1")

      val expected4 = NegBigInt(BigInt(-1))
      val actual4   = NegBigInt(BigInt(-1))

      val expected5 = NegBigInt(BigInt(-1L))
      val actual5   = NegBigInt(BigInt(-1L))

      val expected6 = NegBigInt(BigInt("-1"))
      val actual6   = NegBigInt(BigInt("-1"))

      val expected7 = NegBigInt(-2147483648)
      val actual7   = NegBigInt(-2147483648)

      val expected8 = NegBigInt(-9223372036854775808L)
      val actual8   = NegBigInt(-9223372036854775808L)

      val expected9 = NegBigInt("-9223372036854775808")
      val actual9   = NegBigInt("-9223372036854775808")

      val expected10 = NegBigInt(BigInt(-2147483648))
      val actual10   = NegBigInt(BigInt(-2147483648))

      val expected11 = NegBigInt(BigInt(-9223372036854775808L))
      val actual11   = NegBigInt(BigInt(-9223372036854775808L))

      val expected12 = NegBigInt(BigInt("-9223372036854775808"))
      val actual12   = NegBigInt(BigInt("-9223372036854775808"))

      Result.all(
        List(
          actual1 ==== expected1,
          actual2 ==== expected2,
          actual3 ==== expected3,
          actual4 ==== expected4,
          actual5 ==== expected5,
          actual6 ==== expected6,
          actual7 ==== expected7,
          actual8 ==== expected8,
          actual9 ==== expected9,
          actual10 ==== expected10,
          actual11 ==== expected11,
          actual12 ==== expected12,
        )
      )
    }

    @SuppressWarnings(Array("org.wartremover.warts.ToString"))
    def testFromValid: Property =
      for {
        n <- Gen.long(Range.linear(Long.MinValue, -1L)).map(BigInt(_)).log("n")
      } yield {
        val expected = NegBigInt.unsafeFrom(n)
        val actual   = NegBigInt.from(n)
        actual ==== Right(expected)
      }

    def testFromInvalid: Property =
      for {
        n <- Gen.long(Range.linear(0L, Long.MaxValue)).map(BigInt(_)).log("n")
      } yield {
        val expected = s"Invalid value: [${n.toString}]. It must be a negative BigInt"
        val actual   = NegBigInt.from(n)
        actual ==== Left(expected)
      }

    def testUnsafeFromValid: Property =
      for {
        n <- Gen.long(Range.linear(Long.MinValue, -1L)).map(BigInt(_)).log("n")
      } yield {
        val expected = NegBigInt.unsafeFrom(n)
        val actual   = NegBigInt.unsafeFrom(n)
        actual ==== expected
      }

    def testUnsafeFromInvalid: Property =
      for {
        n <- Gen.long(Range.linear(0L, Long.MaxValue)).map(BigInt(_)).log("n")
      } yield {
        val expected = s"Invalid value: [$n]. It must be a negative BigInt"
        try {
          val _ = NegBigInt.unsafeFrom(n)
          Result
            .failure
            .log(
              s"""IllegalArgumentException was expected from NegBigInt.unsafeFrom(${n.toString}), but it was not thrown."""
            )
        } catch {
          case ex: IllegalArgumentException =>
            ex.getMessage ==== expected

        }
      }

    def testValue: Property =
      for {
        n <- Gen.long(Range.linear(Long.MinValue, -1L)).map(BigInt(_)).log("n")
      } yield {
        val expected = n
        val actual   = NegBigInt.unsafeFrom(n)
        actual.value ==== expected
      }

    def testUnapplyWithPatternMatching: Property =
      for {
        n <- Gen.long(Range.linear(Long.MinValue, -1L)).map(BigInt(_)).log("n")
      } yield {
        val expected = n
        val nes      = NegBigInt.unsafeFrom(n)
        nes match {
          case NegBigInt(actual) =>
            actual ==== expected
        }
      }

    def testOrdering: Property =
      for {
        n1 <- Gen.long(Range.linear(Long.MinValue, -1L)).map(BigInt(_)).log("n1")
        n2 <- Gen.long(Range.linear(Long.MinValue, -1L)).map(BigInt(_)).log("n2")
      } yield {
        val input1 = NegBigInt.unsafeFrom(n1)
        val input2 = NegBigInt.unsafeFrom(n2)
        Ordering[NegBigInt].compare(input1, input2) ==== Ordering[BigInt].compare(input1.value, input2.value)
      }

    def testNumericOrdered: Property =
      for {
        n1 <- Gen.long(Range.linear(Long.MinValue, -1L)).map(BigInt(_)).log("n1")
        n2 <- Gen.long(Range.linear(Long.MinValue, -1L)).map(BigInt(_)).log("n2")
      } yield {
        val input1: Ordered[NegBigInt] = NegBigInt.unsafeFrom(n1)
        val input2: NegBigInt          = NegBigInt.unsafeFrom(n2)
        input1.compare(input2) ==== n1.compare(n2)
      }

  }

  object NonNegBigIntSpec {

    import all.NonNegBigInt

    def tests: List[Test] = List(
      example("test NonNegBigInt.apply", testApply),
      property("test NonNegBigInt.from(valid)", testFromValid),
      property("test NonNegBigInt.from(invalid)", testFromInvalid),
      property("test NonNegBigInt.unsafeFrom(valid)", testUnsafeFromValid),
      property("test NonNegBigInt.unsafeFrom(invalid)", testUnsafeFromInvalid),
      property("test NonNegBigInt.value", testValue),
      property("test NonNegBigInt.unapply", testUnapplyWithPatternMatching),
      property("test Ordering[NonNegBigInt]", testOrdering),
      property("test Ordered[NonNegBigInt]", testNumericOrdered),
    )

    def testApply: Result = {
      /* The actual test is whether this compiles or not actual ==== expected is meaningless here */
      val expected1 = NonNegBigInt(0L)
      val actual1   = NonNegBigInt(0L)

      val expected2 = NonNegBigInt(0L)
      val actual2   = NonNegBigInt(0L)

      val expected3 = NonNegBigInt("0")
      val actual3   = NonNegBigInt("0")

      val expected4 = NonNegBigInt(BigInt(0))
      val actual4   = NonNegBigInt(BigInt(0))

      val expected5 = NonNegBigInt(BigInt(0L))
      val actual5   = NonNegBigInt(BigInt(0L))

      val expected6 = NonNegBigInt(BigInt("0"))
      val actual6   = NonNegBigInt(BigInt("0"))

      val expected7 = NonNegBigInt(2147483647)
      val actual7   = NonNegBigInt(2147483647)

      val expected8 = NonNegBigInt(9223372036854775807L)
      val actual8   = NonNegBigInt(9223372036854775807L)

      val expected9 = NonNegBigInt("9223372036854775807")
      val actual9   = NonNegBigInt("9223372036854775807")

      val expected10 = NonNegBigInt(BigInt(2147483647))
      val actual10   = NonNegBigInt(BigInt(2147483647))

      val expected11 = NonNegBigInt(BigInt(9223372036854775807L))
      val actual11   = NonNegBigInt(BigInt(9223372036854775807L))

      val expected12 = NonNegBigInt(BigInt("9223372036854775807"))
      val actual12   = NonNegBigInt(BigInt("9223372036854775807"))

      Result.all(
        List(
          actual1 ==== expected1,
          actual2 ==== expected2,
          actual3 ==== expected3,
          actual4 ==== expected4,
          actual5 ==== expected5,
          actual6 ==== expected6,
          actual7 ==== expected7,
          actual8 ==== expected8,
          actual9 ==== expected9,
          actual10 ==== expected10,
          actual11 ==== expected11,
          actual12 ==== expected12,
        )
      )
    }

    def testFromValid: Property =
      for {
        n <- Gen.long(Range.linear(0L, Long.MaxValue)).map(BigInt(_)).log("n")
      } yield {
        val expected = NonNegBigInt.unsafeFrom(n)
        val actual   = NonNegBigInt.from(n)
        actual ==== Right(expected)
      }

    def testFromInvalid: Property =
      for {
        n <- Gen.long(Range.linear(Long.MinValue, -1L)).map(BigInt(_)).log("n")
      } yield {
        val expected = s"Invalid value: [${n.toString}]. It must be a non-negative BigInt"
        val actual   = NonNegBigInt.from(n)
        actual ==== Left(expected)
      }

    def testUnsafeFromValid: Property =
      for {
        n <- Gen.long(Range.linear(0L, Long.MaxValue)).map(BigInt(_)).log("n")
      } yield {
        val expected = NonNegBigInt.unsafeFrom(n)
        val actual   = NonNegBigInt.unsafeFrom(n)
        actual ==== expected
      }

    def testUnsafeFromInvalid: Property =
      for {
        n <- Gen.long(Range.linear(-1L, Long.MinValue)).map(BigInt(_)).log("n")
      } yield {
        val expected = s"Invalid value: [$n]. It must be a non-negative BigInt"
        try {
          val _ = NonNegBigInt.unsafeFrom(n)
          Result
            .failure
            .log(
              s"""IllegalArgumentException was expected from NonNegBigInt.unsafeFrom(${n.toString}), but it was not thrown."""
            )
        } catch {
          case ex: IllegalArgumentException =>
            ex.getMessage ==== expected

        }
      }

    def testValue: Property =
      for {
        n <- Gen.long(Range.linear(0L, Long.MaxValue)).map(BigInt(_)).log("n")
      } yield {
        val expected = n
        val actual   = NonNegBigInt.unsafeFrom(n)
        actual.value ==== expected
      }

    def testUnapplyWithPatternMatching: Property =
      for {
        n <- Gen.long(Range.linear(0L, Long.MaxValue)).map(BigInt(_)).log("n")
      } yield {
        val expected = n
        val nes      = NonNegBigInt.unsafeFrom(n)
        nes match {
          case NonNegBigInt(actual) =>
            actual ==== expected
        }
      }

    def testOrdering: Property =
      for {
        n1 <- Gen.long(Range.linear(0L, Long.MaxValue)).map(BigInt(_)).log("n1")
        n2 <- Gen.long(Range.linear(0L, Long.MaxValue)).map(BigInt(_)).log("n2")
      } yield {
        val input1 = NonNegBigInt.unsafeFrom(n1)
        val input2 = NonNegBigInt.unsafeFrom(n2)
        Ordering[NonNegBigInt].compare(input1, input2) ==== Ordering[BigInt].compare(input1.value, input2.value)
      }

    def testNumericOrdered: Property =
      for {
        n2 <- Gen.long(Range.linear(0L, Long.MaxValue)).map(BigInt(_)).log("n2")
        n1 <- Gen.long(Range.linear(0L, Long.MaxValue)).map(BigInt(_)).log("n1")
      } yield {
        val input1: Ordered[NonNegBigInt] = NonNegBigInt.unsafeFrom(n1)
        val input2: NonNegBigInt          = NonNegBigInt.unsafeFrom(n2)
        input1.compare(input2) ==== n1.compare(n2)
      }

  }

  object PosBigIntSpec {

    import all.PosBigInt

    def tests: List[Test] = List(
      example("test PosBigInt.apply", testApply),
      property("test PosBigInt.from(valid)", testFromValid),
      property("test PosBigInt.from(invalid)", testFromInvalid),
      property("test PosBigInt.unsafeFrom(valid)", testUnsafeFromValid),
      property("test PosBigInt.unsafeFrom(invalid)", testUnsafeFromInvalid),
      property("test PosBigInt.value", testValue),
      property("test PosBigInt.unapply", testUnapplyWithPatternMatching),
      property("test Ordering[PosBigInt]", testOrdering),
      property("test Ordered[PosBigInt]", testNumericOrdered),
    )

    def testApply: Result = {
      /* The actual test is whether this compiles or not actual ==== expected is meaningless here */
      val expected1 = PosBigInt(1L)
      val actual1   = PosBigInt(1L)

      val expected2 = PosBigInt(1L)
      val actual2   = PosBigInt(1L)

      val expected3 = PosBigInt("1")
      val actual3   = PosBigInt("1")

      val expected4 = PosBigInt(BigInt(1))
      val actual4   = PosBigInt(BigInt(1))

      val expected5 = PosBigInt(BigInt(1L))
      val actual5   = PosBigInt(BigInt(1L))

      val expected6 = PosBigInt(BigInt("1"))
      val actual6   = PosBigInt(BigInt("1"))

      val expected7 = PosBigInt(2147483647)
      val actual7   = PosBigInt(2147483647)

      val expected8 = PosBigInt(9223372036854775807L)
      val actual8   = PosBigInt(9223372036854775807L)

      val expected9 = PosBigInt("9223372036854775807")
      val actual9   = PosBigInt("9223372036854775807")

      val expected10 = PosBigInt(BigInt(2147483647))
      val actual10   = PosBigInt(BigInt(2147483647))

      val expected11 = PosBigInt(BigInt(9223372036854775807L))
      val actual11   = PosBigInt(BigInt(9223372036854775807L))

      val expected12 = PosBigInt(BigInt("9223372036854775807"))
      val actual12   = PosBigInt(BigInt("9223372036854775807"))

      Result.all(
        List(
          actual1 ==== expected1,
          actual2 ==== expected2,
          actual3 ==== expected3,
          actual4 ==== expected4,
          actual5 ==== expected5,
          actual6 ==== expected6,
          actual7 ==== expected7,
          actual8 ==== expected8,
          actual9 ==== expected9,
          actual10 ==== expected10,
          actual11 ==== expected11,
          actual12 ==== expected12,
        )
      )
    }

    def testFromValid: Property =
      for {
        n <- Gen.long(Range.linear(1L, Long.MaxValue)).map(BigInt(_)).log("n")
      } yield {
        val expected = PosBigInt.unsafeFrom(n)
        val actual   = PosBigInt.from(n)
        actual ==== Right(expected)
      }

    def testFromInvalid: Property =
      for {
        n <- Gen.long(Range.linear(Long.MinValue, 0L)).map(BigInt(_)).log("n")
      } yield {
        val expected = s"Invalid value: [${n.toString}]. It must be a positive BigInt"
        val actual   = PosBigInt.from(n)
        actual ==== Left(expected)
      }

    def testUnsafeFromValid: Property =
      for {
        n <- Gen.long(Range.linear(1L, Long.MaxValue)).map(BigInt(_)).log("n")
      } yield {
        val expected = PosBigInt.unsafeFrom(n)
        val actual   = PosBigInt.unsafeFrom(n)
        actual ==== expected
      }

    def testUnsafeFromInvalid: Property =
      for {
        n <- Gen.long(Range.linear(Long.MinValue, 0L)).map(BigInt(_)).log("n")
      } yield {
        val expected = s"Invalid value: [$n]. It must be a positive BigInt"
        try {
          val _ = PosBigInt.unsafeFrom(n)
          Result
            .failure
            .log(
              s"""IllegalArgumentException was expected from PosBigInt.unsafeFrom(${n.toString}), but it was not thrown."""
            )
        } catch {
          case ex: IllegalArgumentException =>
            ex.getMessage ==== expected

        }
      }

    def testValue: Property =
      for {
        n <- Gen.long(Range.linear(1L, Long.MaxValue)).map(BigInt(_)).log("n")
      } yield {
        val expected = n
        val actual   = PosBigInt.unsafeFrom(n)
        actual.value ==== expected
      }

    def testUnapplyWithPatternMatching: Property =
      for {
        n <- Gen.long(Range.linear(1L, Long.MaxValue)).map(BigInt(_)).log("n")
      } yield {
        val expected = n
        val nes      = PosBigInt.unsafeFrom(n)
        nes match {
          case PosBigInt(actual) =>
            actual ==== expected
        }
      }

    def testOrdering: Property =
      for {
        n1 <- Gen.long(Range.linear(1L, Long.MaxValue)).map(BigInt(_)).log("n1")
        n2 <- Gen.long(Range.linear(1L, Long.MaxValue)).map(BigInt(_)).log("n2")
      } yield {
        val input1 = PosBigInt.unsafeFrom(n1)
        val input2 = PosBigInt.unsafeFrom(n2)
        Ordering[PosBigInt].compare(input1, input2) ==== Ordering[BigInt].compare(input1.value, input2.value)
      }

    def testNumericOrdered: Property =
      for {
        n1 <- Gen.long(Range.linear(1L, Long.MaxValue)).map(BigInt(_)).log("n1")
        n2 <- Gen.long(Range.linear(1L, Long.MaxValue)).map(BigInt(_)).log("n2")
      } yield {
        val input1: Ordered[PosBigInt] = PosBigInt.unsafeFrom(n1)
        val input2: PosBigInt          = PosBigInt.unsafeFrom(n2)
        input1.compare(input2) ==== n1.compare(n2)
      }

  }

  object NonPosBigIntSpec {

    import all.NonPosBigInt

    def tests: List[Test] = List(
      example("test NonPosBigInt.apply", testApply),
      property("test NonPosBigInt.from(valid)", testFromValid),
      property("test NonPosBigInt.from(invalid)", testFromInvalid),
      property("test NonPosBigInt.unsafeFrom(valid)", testUnsafeFromValid),
      property("test NonPosBigInt.unsafeFrom(invalid)", testUnsafeFromInvalid),
      property("test NonPosBigInt.value", testValue),
      property("test NonPosBigInt.unapply", testUnapplyWithPatternMatching),
      property("test Ordering[NonPosBigInt]", testOrdering),
      property("test Ordered[NonPosBigInt]", testNumericOrdered),
    )

    def testApply: Result = {
      /* The actual test is whether this compiles or not actual ==== expected is meaningless here */
      val expected1 = NonPosBigInt(0L)
      val actual1   = NonPosBigInt(0L)

      val expected2 = NonPosBigInt(0L)
      val actual2   = NonPosBigInt(0L)

      val expected3 = NonPosBigInt("0")
      val actual3   = NonPosBigInt("0")

      val expected4 = NonPosBigInt(BigInt(0))
      val actual4   = NonPosBigInt(BigInt(0))

      val expected5 = NonPosBigInt(BigInt(0L))
      val actual5   = NonPosBigInt(BigInt(0L))

      val expected6 = NonPosBigInt(BigInt("0"))
      val actual6   = NonPosBigInt(BigInt("0"))

      val expected7 = NonPosBigInt(-2147483648)
      val actual7   = NonPosBigInt(-2147483648)

      val expected8 = NonPosBigInt(-9223372036854775808L)
      val actual8   = NonPosBigInt(-9223372036854775808L)

      val expected9 = NonPosBigInt("-9223372036854775808")
      val actual9   = NonPosBigInt("-9223372036854775808")

      val expected10 = NonPosBigInt(BigInt(-2147483648))
      val actual10   = NonPosBigInt(BigInt(-2147483648))

      val expected11 = NonPosBigInt(BigInt(-9223372036854775808L))
      val actual11   = NonPosBigInt(BigInt(-9223372036854775808L))

      val expected12 = NonPosBigInt(BigInt("-9223372036854775808"))
      val actual12   = NonPosBigInt(BigInt("-9223372036854775808"))

      Result.all(
        List(
          actual1 ==== expected1,
          actual2 ==== expected2,
          actual3 ==== expected3,
          actual4 ==== expected4,
          actual5 ==== expected5,
          actual6 ==== expected6,
          actual7 ==== expected7,
          actual8 ==== expected8,
          actual9 ==== expected9,
          actual10 ==== expected10,
          actual11 ==== expected11,
          actual12 ==== expected12,
        )
      )
    }

    def testFromValid: Property =
      for {
        n <- Gen.long(Range.linear(Long.MinValue, 0L)).map(BigInt(_)).log("n")
      } yield {
        val expected = NonPosBigInt.unsafeFrom(n)
        val actual   = NonPosBigInt.from(n)
        actual ==== Right(expected)
      }

    def testFromInvalid: Property =
      for {
        n <- Gen.long(Range.linear(1L, Long.MaxValue)).map(BigInt(_)).log("n")
      } yield {
        val expected = s"Invalid value: [${n.toString}]. It must be a non-positive BigInt"
        val actual   = NonPosBigInt.from(n)
        actual ==== Left(expected)
      }

    def testUnsafeFromValid: Property =
      for {
        n <- Gen.long(Range.linear(Long.MinValue, 0L)).map(BigInt(_)).log("n")
      } yield {
        val expected = NonPosBigInt.unsafeFrom(n)
        val actual   = NonPosBigInt.unsafeFrom(n)
        actual ==== expected
      }

    def testUnsafeFromInvalid: Property =
      for {
        n <- Gen.long(Range.linear(1L, Long.MaxValue)).map(BigInt(_)).log("n")
      } yield {
        val expected = s"Invalid value: [$n]. It must be a non-positive BigInt"
        try {
          val _ = NonPosBigInt.unsafeFrom(n)
          Result
            .failure
            .log(
              s"""IllegalArgumentException was expected from NonPosBigInt.unsafeFrom(${n.toString}), but it was not thrown."""
            )
        } catch {
          case ex: IllegalArgumentException =>
            ex.getMessage ==== expected

        }
      }

    def testValue: Property =
      for {
        n <- Gen.long(Range.linear(Long.MinValue, 0L)).map(BigInt(_)).log("n")
      } yield {
        val expected = n
        val actual   = NonPosBigInt.unsafeFrom(n)
        actual.value ==== expected
      }

    def testUnapplyWithPatternMatching: Property =
      for {
        n <- Gen.long(Range.linear(Long.MinValue, 0L)).map(BigInt(_)).log("n")
      } yield {
        val expected = n
        val nes      = NonPosBigInt.unsafeFrom(n)
        nes match {
          case NonPosBigInt(actual) =>
            actual ==== expected
        }
      }

    def testOrdering: Property =
      for {
        n1 <- Gen.long(Range.linear(Long.MinValue, 0L)).map(BigInt(_)).log("n1")
        n2 <- Gen.long(Range.linear(Long.MinValue, 0L)).map(BigInt(_)).log("n2")
      } yield {
        val input1 = NonPosBigInt.unsafeFrom(n1)
        val input2 = NonPosBigInt.unsafeFrom(n2)
        Ordering[NonPosBigInt].compare(input1, input2) ==== Ordering[BigInt].compare(input1.value, input2.value)
      }

    def testNumericOrdered: Property =
      for {
        n1 <- Gen.long(Range.linear(Long.MinValue, 0L)).map(BigInt(_)).log("n1")
        n2 <- Gen.long(Range.linear(Long.MinValue, 0L)).map(BigInt(_)).log("n2")
      } yield {
        val input1: Ordered[NonPosBigInt] = NonPosBigInt.unsafeFrom(n1)
        val input2: NonPosBigInt          = NonPosBigInt.unsafeFrom(n2)
        input1.compare(input2) ==== n1.compare(n2)
      }

  }

  object NegBigDecimalSpec {

    import all.NegBigDecimal

    def tests: List[Test] = List(
      example("test NegBigDecimal.apply", testApply),
      property("test NegBigDecimal.from(valid)", testFromValid),
      property("test NegBigDecimal.from(invalid)", testFromInvalid),
      property("test NegBigDecimal.unsafeFrom(valid)", testUnsafeFromValid),
      property("test NegBigDecimal.unsafeFrom(invalid)", testUnsafeFromInvalid),
      property("test NegBigDecimal.value", testValue),
      property("test NegBigDecimal.unapply", testUnapplyWithPatternMatching),
      property("test Ordering[NegBigDecimal]", testOrdering),
      property("test Ordered[NegBigDecimal]", testNumericOrdered),
    )

    def testApply: Result = {
      /* The actual test is whether this compiles or not actual ==== expected is meaningless here */
      val expected1 = NegBigDecimal(-1)
      val actual1   = NegBigDecimal(-1)

      val expected2 = NegBigDecimal(-1L)
      val actual2   = NegBigDecimal(-1L)

      val expected3 = NegBigDecimal("-1")
      val actual3   = NegBigDecimal("-1")

      val expected4 = NegBigDecimal(BigDecimal(-1))
      val actual4   = NegBigDecimal(BigDecimal(-1))

      val expected5 = NegBigDecimal(BigDecimal(-1L))
      val actual5   = NegBigDecimal(BigDecimal(-1L))

      val expected6 = NegBigDecimal(BigDecimal("-1"))
      val actual6   = NegBigDecimal(BigDecimal("-1"))

      val expected7 = NegBigDecimal(-2147483648)
      val actual7   = NegBigDecimal(-2147483648)

      val expected8 = NegBigDecimal(-9223372036854775808L)
      val actual8   = NegBigDecimal(-9223372036854775808L)

      val expected9 = NegBigDecimal("-9223372036854775808")
      val actual9   = NegBigDecimal("-9223372036854775808")

      val expected10 = NegBigDecimal(BigDecimal(-2147483648))
      val actual10   = NegBigDecimal(BigDecimal(-2147483648))

      val expected11 = NegBigDecimal(BigDecimal(-9223372036854775808L))
      val actual11   = NegBigDecimal(BigDecimal(-9223372036854775808L))

      val expected12 = NegBigDecimal(BigDecimal("-9223372036854775808"))
      val actual12   = NegBigDecimal(BigDecimal("-9223372036854775808"))

      val expected13 = NegBigDecimal(-0.00001f)
      val actual13   = NegBigDecimal(-0.00001f)

      val expected14 = NegBigDecimal(-0.0000001d)
      val actual14   = NegBigDecimal(-0.0000001d)

      val expected15 = NegBigDecimal("-0.0000001")
      val actual15   = NegBigDecimal("-0.0000001")

      val expected16 = NegBigDecimal(BigDecimal(-0.00001f))
      val actual16   = NegBigDecimal(BigDecimal(-0.00001f))

      val expected17 = NegBigDecimal(BigDecimal(-0.0000001d))
      val actual17   = NegBigDecimal(BigDecimal(-0.0000001d))

      val expected18 = NegBigDecimal(BigDecimal("-0.0000001"))
      val actual18   = NegBigDecimal(BigDecimal("-0.0000001"))

      Result.all(
        List(
          actual1 ==== expected1,
          actual2 ==== expected2,
          actual3 ==== expected3,
          actual4 ==== expected4,
          actual5 ==== expected5,
          actual6 ==== expected6,
          actual7 ==== expected7,
          actual8 ==== expected8,
          actual9 ==== expected9,
          actual10 ==== expected10,
          actual11 ==== expected11,
          actual12 ==== expected12,
          actual13 ==== expected13,
          actual14 ==== expected14,
          actual15 ==== expected15,
          actual16 ==== expected16,
          actual17 ==== expected17,
          actual18 ==== expected18,
        )
      )
    }

    @SuppressWarnings(Array("org.wartremover.warts.ToString"))
    def testFromValid: Property =
      for {
        n <- Gen.double(Range.linearFrac(Double.MinValue, -0.0000001d)).map(BigDecimal(_)).log("n")
      } yield {
        val expected = NegBigDecimal.unsafeFrom(n)
        val actual   = NegBigDecimal.from(n)
        actual ==== Right(expected)
      }

    def testFromInvalid: Property =
      for {
        n <- Gen.double(Range.linearFrac(0d, Double.MaxValue)).map(BigDecimal(_)).log("n")
      } yield {
        val expected = s"Invalid value: [${n.toString}]. It must be a negative BigDecimal"
        val actual   = NegBigDecimal.from(n)
        actual ==== Left(expected)
      }

    def testUnsafeFromValid: Property =
      for {
        n <- Gen.double(Range.linearFrac(Double.MinValue, -0.0000001d)).map(BigDecimal(_)).log("n")
      } yield {
        val expected = NegBigDecimal.unsafeFrom(n)
        val actual   = NegBigDecimal.unsafeFrom(n)
        actual ==== expected
      }

    def testUnsafeFromInvalid: Property =
      for {
        n <- Gen.double(Range.linearFrac(0d, Double.MaxValue)).map(BigDecimal(_)).log("n")
      } yield {
        val expected = s"Invalid value: [$n]. It must be a negative BigDecimal"
        try {
          val _ = NegBigDecimal.unsafeFrom(n)
          Result
            .failure
            .log(
              s"""IllegalArgumentException was expected from NegBigDecimal.unsafeFrom(${n.toString}), but it was not thrown."""
            )
        } catch {
          case ex: IllegalArgumentException =>
            ex.getMessage ==== expected

        }
      }

    def testValue: Property =
      for {
        n <- Gen.double(Range.linearFrac(Double.MinValue, -0.0000001d)).map(BigDecimal(_)).log("n")
      } yield {
        val expected = n
        val actual   = NegBigDecimal.unsafeFrom(n)
        actual.value ==== expected
      }

    def testUnapplyWithPatternMatching: Property =
      for {
        n <- Gen.double(Range.linearFrac(Double.MinValue, -0.0000001d)).map(BigDecimal(_)).log("n")
      } yield {
        val expected = n
        val nes      = NegBigDecimal.unsafeFrom(n)
        nes match {
          case NegBigDecimal(actual) =>
            actual ==== expected
        }
      }

    def testOrdering: Property =
      for {
        n1 <- Gen.double(Range.linearFrac(Double.MinValue, -0.0000001d)).map(BigDecimal(_)).log("n1")
        n2 <- Gen.double(Range.linearFrac(Double.MinValue, -0.0000001d)).map(BigDecimal(_)).log("n2")
      } yield {
        val input1 = NegBigDecimal.unsafeFrom(n1)
        val input2 = NegBigDecimal.unsafeFrom(n2)
        Ordering[NegBigDecimal].compare(input1, input2) ==== Ordering[BigDecimal].compare(input1.value, input2.value)
      }

    def testNumericOrdered: Property =
      for {
        n1 <- Gen.double(Range.linearFrac(Double.MinValue, -0.0000001d)).map(BigDecimal(_)).log("n1")
        n2 <- Gen.double(Range.linearFrac(Double.MinValue, -0.0000001d)).map(BigDecimal(_)).log("n2")
      } yield {
        val input1: Ordered[NegBigDecimal] = NegBigDecimal.unsafeFrom(n1)
        val input2: NegBigDecimal          = NegBigDecimal.unsafeFrom(n2)
        input1.compare(input2) ==== n1.compare(n2)
      }

  }

  object NonNegBigDecimalSpec {

    import all.NonNegBigDecimal

    def tests: List[Test] = List(
      example("test NonNegBigDecimal.apply", testApply),
      property("test NonNegBigDecimal.from(valid)", testFromValid),
      property("test NonNegBigDecimal.from(invalid)", testFromInvalid),
      property("test NonNegBigDecimal.unsafeFrom(valid)", testUnsafeFromValid),
      property("test NonNegBigDecimal.unsafeFrom(invalid)", testUnsafeFromInvalid),
      property("test NonNegBigDecimal.value", testValue),
      property("test NonNegBigDecimal.unapply", testUnapplyWithPatternMatching),
      property("test Ordering[NonNegBigDecimal]", testOrdering),
      property("test Ordered[NonNegBigDecimal]", testNumericOrdered),
    )

    def testApply: Result = {
      /* The actual test is whether this compiles or not actual ==== expected is meaningless here */
      val expected1 = NonNegBigDecimal(0L)
      val actual1   = NonNegBigDecimal(0L)

      val expected2 = NonNegBigDecimal(0L)
      val actual2   = NonNegBigDecimal(0L)

      val expected3 = NonNegBigDecimal("0")
      val actual3   = NonNegBigDecimal("0")

      val expected4 = NonNegBigDecimal(BigDecimal(0))
      val actual4   = NonNegBigDecimal(BigDecimal(0))

      val expected5 = NonNegBigDecimal(BigDecimal(0L))
      val actual5   = NonNegBigDecimal(BigDecimal(0L))

      val expected6 = NonNegBigDecimal(BigDecimal("0"))
      val actual6   = NonNegBigDecimal(BigDecimal("0"))

      val expected7 = NonNegBigDecimal(2147483647)
      val actual7   = NonNegBigDecimal(2147483647)

      val expected8 = NonNegBigDecimal(9223372036854775807L)
      val actual8   = NonNegBigDecimal(9223372036854775807L)

      val expected9 = NonNegBigDecimal("9223372036854775807")
      val actual9   = NonNegBigDecimal("9223372036854775807")

      val expected10 = NonNegBigDecimal(BigDecimal(2147483647))
      val actual10   = NonNegBigDecimal(BigDecimal(2147483647))

      val expected11 = NonNegBigDecimal(BigDecimal(9223372036854775807L))
      val actual11   = NonNegBigDecimal(BigDecimal(9223372036854775807L))

      val expected12 = NonNegBigDecimal(BigDecimal("9223372036854775807"))
      val actual12   = NonNegBigDecimal(BigDecimal("9223372036854775807"))

      val expected13 = NonNegBigDecimal(3.141592f)
      val actual13   = NonNegBigDecimal(3.141592f)

      val expected14 = NonNegBigDecimal(3.141592d)
      val actual14   = NonNegBigDecimal(3.141592d)

      val expected15 = NonNegBigDecimal("3.141592")
      val actual15   = NonNegBigDecimal("3.141592")

      val expected16 = NonNegBigDecimal(BigDecimal(3.141592f))
      val actual16   = NonNegBigDecimal(BigDecimal(3.141592f))

      val expected17 = NonNegBigDecimal(BigDecimal(3.141592d))
      val actual17   = NonNegBigDecimal(BigDecimal(3.141592d))

      val expected18 = NonNegBigDecimal(BigDecimal("3.141592"))
      val actual18   = NonNegBigDecimal(BigDecimal("3.141592"))

      Result.all(
        List(
          actual1 ==== expected1,
          actual2 ==== expected2,
          actual3 ==== expected3,
          actual4 ==== expected4,
          actual5 ==== expected5,
          actual6 ==== expected6,
          actual7 ==== expected7,
          actual8 ==== expected8,
          actual9 ==== expected9,
          actual10 ==== expected10,
          actual11 ==== expected11,
          actual12 ==== expected12,
          actual13 ==== expected13,
          actual14 ==== expected14,
          actual15 ==== expected15,
          actual16 ==== expected16,
          actual17 ==== expected17,
          actual18 ==== expected18,
        )
      )
    }

    def testFromValid: Property =
      for {
        n <- Gen.double(Range.linearFrac(0d, Double.MaxValue)).map(BigDecimal(_)).log("n")
      } yield {
        val expected = NonNegBigDecimal.unsafeFrom(n)
        val actual   = NonNegBigDecimal.from(n)
        actual ==== Right(expected)
      }

    def testFromInvalid: Property =
      for {
        n <- Gen.double(Range.linearFrac(Double.MinValue, -0.0000001d)).map(BigDecimal(_)).log("n")
      } yield {
        val expected = s"Invalid value: [${n.toString}]. It must be a non-negative BigDecimal"
        val actual   = NonNegBigDecimal.from(n)
        actual ==== Left(expected)
      }

    def testUnsafeFromValid: Property =
      for {
        n <- Gen.double(Range.linearFrac(0d, Double.MaxValue)).map(BigDecimal(_)).log("n")
      } yield {
        val expected = NonNegBigDecimal.unsafeFrom(n)
        val actual   = NonNegBigDecimal.unsafeFrom(n)
        actual ==== expected
      }

    def testUnsafeFromInvalid: Property =
      for {
        n <- Gen.double(Range.linearFrac(-0.0000001d, Double.MinValue)).map(BigDecimal(_)).log("n")
      } yield {
        val expected = s"Invalid value: [$n]. It must be a non-negative BigDecimal"
        try {
          val _ = NonNegBigDecimal.unsafeFrom(n)
          Result
            .failure
            .log(
              s"""IllegalArgumentException was expected from NonNegBigDecimal.unsafeFrom(${n.toString}), but it was not thrown."""
            )
        } catch {
          case ex: IllegalArgumentException =>
            ex.getMessage ==== expected

        }
      }

    def testValue: Property =
      for {
        n <- Gen.double(Range.linearFrac(0d, Double.MaxValue)).map(BigDecimal(_)).log("n")
      } yield {
        val expected = n
        val actual   = NonNegBigDecimal.unsafeFrom(n)
        actual.value ==== expected
      }

    def testUnapplyWithPatternMatching: Property =
      for {
        n <- Gen.double(Range.linearFrac(0d, Double.MaxValue)).map(BigDecimal(_)).log("n")
      } yield {
        val expected = n
        val nes      = NonNegBigDecimal.unsafeFrom(n)
        nes match {
          case NonNegBigDecimal(actual) =>
            actual ==== expected
        }
      }

    def testOrdering: Property =
      for {
        n1 <- Gen.double(Range.linearFrac(0d, Double.MaxValue)).map(BigDecimal(_)).log("n1")
        n2 <- Gen.double(Range.linearFrac(0d, Double.MaxValue)).map(BigDecimal(_)).log("n2")
      } yield {
        val input1 = NonNegBigDecimal.unsafeFrom(n1)
        val input2 = NonNegBigDecimal.unsafeFrom(n2)
        Ordering[NonNegBigDecimal].compare(input1, input2) ==== Ordering[BigDecimal].compare(input1.value, input2.value)
      }

    def testNumericOrdered: Property =
      for {
        n2 <- Gen.double(Range.linearFrac(0d, Double.MaxValue)).map(BigDecimal(_)).log("n2")
        n1 <- Gen.double(Range.linearFrac(0d, Double.MaxValue)).map(BigDecimal(_)).log("n1")
      } yield {
        val input1: Ordered[NonNegBigDecimal] = NonNegBigDecimal.unsafeFrom(n1)
        val input2: NonNegBigDecimal          = NonNegBigDecimal.unsafeFrom(n2)
        input1.compare(input2) ==== n1.compare(n2)
      }

  }

  object PosBigDecimalSpec {

    import all.PosBigDecimal

    def tests: List[Test] = List(
      example("test PosBigDecimal.apply", testApply),
      property("test PosBigDecimal.from(valid)", testFromValid),
      property("test PosBigDecimal.from(invalid)", testFromInvalid),
      property("test PosBigDecimal.unsafeFrom(valid)", testUnsafeFromValid),
      property("test PosBigDecimal.unsafeFrom(invalid)", testUnsafeFromInvalid),
      property("test PosBigDecimal.value", testValue),
      property("test PosBigDecimal.unapply", testUnapplyWithPatternMatching),
      property("test Ordering[PosBigDecimal]", testOrdering),
      property("test Ordered[PosBigDecimal]", testNumericOrdered),
    )

    def testApply: Result = {
      /* The actual test is whether this compiles or not actual ==== expected is meaningless here */
      val expected1 = PosBigDecimal(1L)
      val actual1   = PosBigDecimal(1L)

      val expected2 = PosBigDecimal(1L)
      val actual2   = PosBigDecimal(1L)

      val expected3 = PosBigDecimal("1")
      val actual3   = PosBigDecimal("1")

      val expected4 = PosBigDecimal(BigDecimal(1))
      val actual4   = PosBigDecimal(BigDecimal(1))

      val expected5 = PosBigDecimal(BigDecimal(1L))
      val actual5   = PosBigDecimal(BigDecimal(1L))

      val expected6 = PosBigDecimal(BigDecimal("1"))
      val actual6   = PosBigDecimal(BigDecimal("1"))

      val expected7 = PosBigDecimal(2147483647)
      val actual7   = PosBigDecimal(2147483647)

      val expected8 = PosBigDecimal(9223372036854775807L)
      val actual8   = PosBigDecimal(9223372036854775807L)

      val expected9 = PosBigDecimal("9223372036854775807")
      val actual9   = PosBigDecimal("9223372036854775807")

      val expected10 = PosBigDecimal(BigDecimal(2147483647))
      val actual10   = PosBigDecimal(BigDecimal(2147483647))

      val expected11 = PosBigDecimal(BigDecimal(9223372036854775807L))
      val actual11   = PosBigDecimal(BigDecimal(9223372036854775807L))

      val expected12 = PosBigDecimal(BigDecimal("9223372036854775807"))
      val actual12   = PosBigDecimal(BigDecimal("9223372036854775807"))

      val expected13 = PosBigDecimal(3.141592f)
      val actual13   = PosBigDecimal(3.141592f)

      val expected14 = PosBigDecimal(3.141592d)
      val actual14   = PosBigDecimal(3.141592d)

      val expected15 = PosBigDecimal("3.141592")
      val actual15   = PosBigDecimal("3.141592")

      val expected16 = PosBigDecimal(BigDecimal(3.141592f))
      val actual16   = PosBigDecimal(BigDecimal(3.141592f))

      val expected17 = PosBigDecimal(BigDecimal(3.141592d))
      val actual17   = PosBigDecimal(BigDecimal(3.141592d))

      val expected18 = PosBigDecimal(BigDecimal("3.141592"))
      val actual18   = PosBigDecimal(BigDecimal("3.141592"))

      Result.all(
        List(
          actual1 ==== expected1,
          actual2 ==== expected2,
          actual3 ==== expected3,
          actual4 ==== expected4,
          actual5 ==== expected5,
          actual6 ==== expected6,
          actual7 ==== expected7,
          actual8 ==== expected8,
          actual9 ==== expected9,
          actual10 ==== expected10,
          actual11 ==== expected11,
          actual12 ==== expected12,
          actual13 ==== expected13,
          actual14 ==== expected14,
          actual15 ==== expected15,
          actual16 ==== expected16,
          actual17 ==== expected17,
          actual18 ==== expected18,
        )
      )
    }

    def testFromValid: Property =
      for {
        n <- Gen.double(Range.linearFrac(0.0000001d, Double.MaxValue)).map(BigDecimal(_)).log("n")
      } yield {
        val expected = PosBigDecimal.unsafeFrom(n)
        val actual   = PosBigDecimal.from(n)
        actual ==== Right(expected)
      }

    def testFromInvalid: Property =
      for {
        n <- Gen.double(Range.linearFrac(Double.MinValue, 0d)).map(BigDecimal(_)).log("n")
      } yield {
        val expected = s"Invalid value: [${n.toString}]. It must be a positive BigDecimal"
        val actual   = PosBigDecimal.from(n)
        actual ==== Left(expected)
      }

    def testUnsafeFromValid: Property =
      for {
        n <- Gen.double(Range.linearFrac(0.0000001d, Double.MaxValue)).map(BigDecimal(_)).log("n")
      } yield {
        val expected = PosBigDecimal.unsafeFrom(n)
        val actual   = PosBigDecimal.unsafeFrom(n)
        actual ==== expected
      }

    def testUnsafeFromInvalid: Property =
      for {
        n <- Gen.double(Range.linearFrac(Double.MinValue, 0d)).map(BigDecimal(_)).log("n")
      } yield {
        val expected = s"Invalid value: [$n]. It must be a positive BigDecimal"
        try {
          val _ = PosBigDecimal.unsafeFrom(n)
          Result
            .failure
            .log(
              s"""IllegalArgumentException was expected from PosBigDecimal.unsafeFrom(${n.toString}), but it was not thrown."""
            )
        } catch {
          case ex: IllegalArgumentException =>
            ex.getMessage ==== expected

        }
      }

    def testValue: Property =
      for {
        n <- Gen.double(Range.linearFrac(0.0000001d, Double.MaxValue)).map(BigDecimal(_)).log("n")
      } yield {
        val expected = n
        val actual   = PosBigDecimal.unsafeFrom(n)
        actual.value ==== expected
      }

    def testUnapplyWithPatternMatching: Property =
      for {
        n <- Gen.double(Range.linearFrac(0.0000001d, Double.MaxValue)).map(BigDecimal(_)).log("n")
      } yield {
        val expected = n
        val nes      = PosBigDecimal.unsafeFrom(n)
        nes match {
          case PosBigDecimal(actual) =>
            actual ==== expected
        }
      }

    def testOrdering: Property =
      for {
        n1 <- Gen.double(Range.linearFrac(0.0000001d, Double.MaxValue)).map(BigDecimal(_)).log("n1")
        n2 <- Gen.double(Range.linearFrac(0.0000001d, Double.MaxValue)).map(BigDecimal(_)).log("n2")
      } yield {
        val input1 = PosBigDecimal.unsafeFrom(n1)
        val input2 = PosBigDecimal.unsafeFrom(n2)
        Ordering[PosBigDecimal].compare(input1, input2) ==== Ordering[BigDecimal].compare(input1.value, input2.value)
      }

    def testNumericOrdered: Property =
      for {
        n1 <- Gen.double(Range.linearFrac(0.0000001d, Double.MaxValue)).map(BigDecimal(_)).log("n1")
        n2 <- Gen.double(Range.linearFrac(0.0000001d, Double.MaxValue)).map(BigDecimal(_)).log("n2")
      } yield {
        val input1: Ordered[PosBigDecimal] = PosBigDecimal.unsafeFrom(n1)
        val input2: PosBigDecimal          = PosBigDecimal.unsafeFrom(n2)
        input1.compare(input2) ==== n1.compare(n2)
      }

  }

  object NonPosBigDecimalSpec {

    import all.NonPosBigDecimal

    def tests: List[Test] = List(
      example("test NonPosBigDecimal.apply", testApply),
      property("test NonPosBigDecimal.from(valid)", testFromValid),
      property("test NonPosBigDecimal.from(invalid)", testFromInvalid),
      property("test NonPosBigDecimal.unsafeFrom(valid)", testUnsafeFromValid),
      property("test NonPosBigDecimal.unsafeFrom(invalid)", testUnsafeFromInvalid),
      property("test NonPosBigDecimal.value", testValue),
      property("test NonPosBigDecimal.unapply", testUnapplyWithPatternMatching),
      property("test Ordering[NonPosBigDecimal]", testOrdering),
      property("test Ordered[NonPosBigDecimal]", testNumericOrdered),
    )

    def testApply: Result = {
      /* The actual test is whether this compiles or not actual ==== expected is meaningless here */
      val expected1 = NonPosBigDecimal(0L)
      val actual1   = NonPosBigDecimal(0L)

      val expected2 = NonPosBigDecimal(0L)
      val actual2   = NonPosBigDecimal(0L)

      val expected3 = NonPosBigDecimal("0")
      val actual3   = NonPosBigDecimal("0")

      val expected4 = NonPosBigDecimal(BigDecimal(0))
      val actual4   = NonPosBigDecimal(BigDecimal(0))

      val expected5 = NonPosBigDecimal(BigDecimal(0L))
      val actual5   = NonPosBigDecimal(BigDecimal(0L))

      val expected6 = NonPosBigDecimal(BigDecimal("0"))
      val actual6   = NonPosBigDecimal(BigDecimal("0"))

      val expected7 = NonPosBigDecimal(-2147483648)
      val actual7   = NonPosBigDecimal(-2147483648)

      val expected8 = NonPosBigDecimal(-9223372036854775808L)
      val actual8   = NonPosBigDecimal(-9223372036854775808L)

      val expected9 = NonPosBigDecimal("-9223372036854775808")
      val actual9   = NonPosBigDecimal("-9223372036854775808")

      val expected10 = NonPosBigDecimal(BigDecimal(-2147483648))
      val actual10   = NonPosBigDecimal(BigDecimal(-2147483648))

      val expected11 = NonPosBigDecimal(BigDecimal(-9223372036854775808L))
      val actual11   = NonPosBigDecimal(BigDecimal(-9223372036854775808L))

      val expected12 = NonPosBigDecimal(BigDecimal("-9223372036854775808"))
      val actual12   = NonPosBigDecimal(BigDecimal("-9223372036854775808"))

      val expected13 = NonPosBigDecimal(-3.141592f)
      val actual13   = NonPosBigDecimal(-3.141592f)

      val expected14 = NonPosBigDecimal(-3.141592d)
      val actual14   = NonPosBigDecimal(-3.141592d)

      val expected15 = NonPosBigDecimal("-3.141592")
      val actual15   = NonPosBigDecimal("-3.141592")

      val expected16 = NonPosBigDecimal(BigDecimal(-3.141592f))
      val actual16   = NonPosBigDecimal(BigDecimal(-3.141592f))

      val expected17 = NonPosBigDecimal(BigDecimal(-3.141592d))
      val actual17   = NonPosBigDecimal(BigDecimal(-3.141592d))

      val expected18 = NonPosBigDecimal(BigDecimal("-3.141592"))
      val actual18   = NonPosBigDecimal(BigDecimal("-3.141592"))

      Result.all(
        List(
          actual1 ==== expected1,
          actual2 ==== expected2,
          actual3 ==== expected3,
          actual4 ==== expected4,
          actual5 ==== expected5,
          actual6 ==== expected6,
          actual7 ==== expected7,
          actual8 ==== expected8,
          actual9 ==== expected9,
          actual10 ==== expected10,
          actual11 ==== expected11,
          actual12 ==== expected12,
          actual13 ==== expected13,
          actual14 ==== expected14,
          actual15 ==== expected15,
          actual16 ==== expected16,
          actual17 ==== expected17,
          actual18 ==== expected18,
        )
      )
    }

    def testFromValid: Property =
      for {
        n <- Gen.double(Range.linearFrac(Double.MinValue, 0d)).map(BigDecimal(_)).log("n")
      } yield {
        val expected = NonPosBigDecimal.unsafeFrom(n)
        val actual   = NonPosBigDecimal.from(n)
        actual ==== Right(expected)
      }

    def testFromInvalid: Property =
      for {
        n <- Gen.double(Range.linearFrac(0.0000001d, Double.MaxValue)).map(BigDecimal(_)).log("n")
      } yield {
        val expected = s"Invalid value: [${n.toString}]. It must be a non-positive BigDecimal"
        val actual   = NonPosBigDecimal.from(n)
        actual ==== Left(expected)
      }

    def testUnsafeFromValid: Property =
      for {
        n <- Gen.double(Range.linearFrac(Double.MinValue, 0d)).map(BigDecimal(_)).log("n")
      } yield {
        val expected = NonPosBigDecimal.unsafeFrom(n)
        val actual   = NonPosBigDecimal.unsafeFrom(n)
        actual ==== expected
      }

    def testUnsafeFromInvalid: Property =
      for {
        n <- Gen.double(Range.linearFrac(0.0000001d, Double.MaxValue)).map(BigDecimal(_)).log("n")
      } yield {
        val expected = s"Invalid value: [$n]. It must be a non-positive BigDecimal"
        try {
          val _ = NonPosBigDecimal.unsafeFrom(n)
          Result
            .failure
            .log(
              s"""IllegalArgumentException was expected from NonPosBigDecimal.unsafeFrom(${n.toString}), but it was not thrown."""
            )
        } catch {
          case ex: IllegalArgumentException =>
            ex.getMessage ==== expected

        }
      }

    def testValue: Property =
      for {
        n <- Gen.double(Range.linearFrac(Double.MinValue, 0d)).map(BigDecimal(_)).log("n")
      } yield {
        val expected = n
        val actual   = NonPosBigDecimal.unsafeFrom(n)
        actual.value ==== expected
      }

    def testUnapplyWithPatternMatching: Property =
      for {
        n <- Gen.double(Range.linearFrac(Double.MinValue, 0d)).map(BigDecimal(_)).log("n")
      } yield {
        val expected = n
        val nes      = NonPosBigDecimal.unsafeFrom(n)
        nes match {
          case NonPosBigDecimal(actual) =>
            actual ==== expected
        }
      }

    def testOrdering: Property =
      for {
        n1 <- Gen.double(Range.linearFrac(Double.MinValue, 0d)).map(BigDecimal(_)).log("n1")
        n2 <- Gen.double(Range.linearFrac(Double.MinValue, 0d)).map(BigDecimal(_)).log("n2")
      } yield {
        val input1 = NonPosBigDecimal.unsafeFrom(n1)
        val input2 = NonPosBigDecimal.unsafeFrom(n2)
        Ordering[NonPosBigDecimal].compare(input1, input2) ==== Ordering[BigDecimal].compare(input1.value, input2.value)
      }

    def testNumericOrdered: Property =
      for {
        n1 <- Gen.double(Range.linearFrac(Double.MinValue, 0d)).map(BigDecimal(_)).log("n1")
        n2 <- Gen.double(Range.linearFrac(Double.MinValue, 0d)).map(BigDecimal(_)).log("n2")
      } yield {
        val input1: Ordered[NonPosBigDecimal] = NonPosBigDecimal.unsafeFrom(n1)
        val input2: NonPosBigDecimal          = NonPosBigDecimal.unsafeFrom(n2)
        input1.compare(input2) ==== n1.compare(n2)
      }

  }

}
