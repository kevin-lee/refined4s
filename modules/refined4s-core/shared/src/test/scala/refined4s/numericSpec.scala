package refined4s

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
      NegByteSpec.tests ++ NonNegByteSpec.tests ++ PosByteSpec.tests ++ NonPosByteSpec.tests
    NegFloatSpec.tests ++ NonNegFloatSpec.tests ++ PosFloatSpec.tests ++ NonPosFloatSpec.tests

  object NegIntSpec {

    import numeric.NegInt

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
          NegInt.unsafeFrom(n)
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

  }

  object NonNegIntSpec {

    import numeric.NonNegInt

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
          NonNegInt.unsafeFrom(n)
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

  }

  object PosIntSpec {

    import numeric.PosInt

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
          PosInt.unsafeFrom(n)
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

  }

  object NonPosIntSpec {

    import numeric.NonPosInt

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
          NonPosInt.unsafeFrom(n)
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

  }

  object NegLongSpec {

    import numeric.NegLong

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
          NegLong.unsafeFrom(n)
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

  }

  object NonNegLongSpec {

    import numeric.NonNegLong

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
          NonNegLong.unsafeFrom(n)
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

  }

  object PosLongSpec {

    import numeric.PosLong

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
          PosLong.unsafeFrom(n)
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

  }

  object NonPosLongSpec {

    import numeric.NonPosLong

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
          NonPosLong.unsafeFrom(n)
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

  }

  object NegShortSpec {

    import numeric.NegShort

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
          NegShort.unsafeFrom(n)
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

  }

  object NonNegShortSpec {

    import numeric.NonNegShort

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
          NonNegShort.unsafeFrom(n)
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

  }

  object PosShortSpec {

    import numeric.PosShort

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
          PosShort.unsafeFrom(n)
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

  }

  object NonPosShortSpec {

    import numeric.NonPosShort

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
          NonPosShort.unsafeFrom(n)
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

  }

  object NegByteSpec {

    import numeric.NegByte

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
          NegByte.unsafeFrom(n)
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

  }

  object NonNegByteSpec {

    import numeric.NonNegByte

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
          NonNegByte.unsafeFrom(n)
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

  }

  object PosByteSpec {

    import numeric.PosByte

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
          PosByte.unsafeFrom(n)
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

  }

  object NonPosByteSpec {

    import numeric.NonPosByte

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
          NonPosByte.unsafeFrom(n)
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

  }

  object NegFloatSpec {

    import numeric.NegFloat

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
          NegFloat.unsafeFrom(n)
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

  }

  object NonNegFloatSpec {

    import numeric.NonNegFloat

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
          NonNegFloat.unsafeFrom(n)
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

  }

  object PosFloatSpec {

    import numeric.PosFloat

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
          PosFloat.unsafeFrom(n)
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

  }

  object NonPosFloatSpec {

    import numeric.NonPosFloat

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
          NonPosFloat.unsafeFrom(n)
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

  }

}
