package refined4s

import hedgehog.*
import hedgehog.runner.*

/** @author Kevin Lee
  * @since 2023-04-26
  */
object numericSpec extends Properties {
  override def tests: List[Test] =
    NegIntSpec.tests ++ NonNegIntSpec.tests ++ PosIntSpec.tests ++ NonPosIntSpec.tests ++
      NegLongSpec.tests ++ NonNegLongSpec.tests ++ PosLongSpec.tests ++ NonPosLongSpec.tests

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
        n1 <- Gen.int(Range.linear(0, Int.MaxValue)).log("n1")
        n2 <- Gen.int(Range.linear(0, Int.MaxValue)).log("n2")
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
        n1 <- Gen.long(Range.linear(0L, Long.MaxValue)).log("n1")
        n2 <- Gen.long(Range.linear(0L, Long.MaxValue)).log("n2")
      } yield {
        val input1 = PosLong.unsafeFrom(n1)
        val input2 = PosLong.unsafeFrom(n2)
        Ordering[PosLong].compare(input1, input2) ==== Ordering[Long].compare(input1.value, input2.value)
      }

    def testNumericOrdered: Property =
      for {
        n1 <- Gen.long(Range.linear(0L, Long.MaxValue)).log("n1")
        n2 <- Gen.long(Range.linear(0L, Long.MaxValue)).log("n2")
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

}
