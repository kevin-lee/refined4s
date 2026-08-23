package refined4s.modules.cats.derivation.types

import cats.syntax.all.*
import hedgehog.*
import hedgehog.runner.*
import refined4s.types.numeric.*
import refined4s.types.network.*
import refined4s.types.strings.*
import refined4s.types.time.*
import refined4s.types.networkGens
//import refined4s.types.UuidV7TestTools

import java.util.UUID

/** @author Kevin Lee
  * @since 2023-12-10
  */
@SuppressWarnings(Array("org.wartremover.warts.ToString"))
object allSpec extends Properties {

  override def tests: List[Test] =
    negIntSpec.tests ++ nonNegIntSpec.tests ++ posIntSpec.tests ++ nonPosIntSpec.tests ++
      negLongSpec.tests ++ nonNegLongSpec.tests ++ posLongSpec.tests ++ nonPosLongSpec.tests ++
      negShortSpec.tests ++ nonNegShortSpec.tests ++ posShortSpec.tests ++ nonPosShortSpec.tests ++
      negByteSpec.tests ++ nonNegByteSpec.tests ++ posByteSpec.tests ++ nonPosByteSpec.tests ++
      negFloatSpec.tests ++ nonNegFloatSpec.tests ++ posFloatSpec.tests ++ nonPosFloatSpec.tests ++
      negDoubleSpec.tests ++ nonNegDoubleSpec.tests ++ posDoubleSpec.tests ++ nonPosDoubleSpec.tests ++
      negBigIntSpec.tests ++ nonNegBigIntSpec.tests ++ posBigIntSpec.tests ++ nonPosBigIntSpec.tests ++
      negBigDecimalSpec.tests ++ nonNegBigDecimalSpec.tests ++ posBigDecimalSpec.tests ++ nonPosBigDecimalSpec.tests ++
      nonEmptyStringSpec.tests ++
      nonBlankStringSpec.tests ++
      uuidSpec.tests ++
      /* TODO: #597 - Temporarily hide UuidV7 until it's ready for use.
      uuidV7Spec.tests ++
       */
      uriSpec.tests ++
      urlSpec.tests ++
      portNumberSpec.tests ++ systemPortNumberSpec.tests ++ nonSystemPortNumberSpec.tests ++ userPortNumberSpec.tests ++ dynamicPortNumberSpec.tests ++
      timeSpec.tests

  object negIntSpec {

    def tests: List[Test] = List(
      property("test   Eq[NegInt] === case", testEq),
      property("test   Eq[NegInt] =!= case", testEqNotEqual),
      property("test Hash[NegInt]", testHash),
      property("test Order[NegInt]", testOrder),
      property("test Order[NegInt] == case", testOrderEqualCase).withTests(25),
      property("test Show[NegInt]", testShow),
    )

    def testEq: Property =
      for {
        n <- Gen.int(Range.linear(-1, Int.MinValue)).log("n")
      } yield {
        val input = NegInt.unsafeFrom(n)

        val expected = input
        val actual   = input

        Result.diffNamed("NegInt(value) === NegInt(value)", actual, expected)(_ === _)
      }

    def testEqNotEqual: Property =
      for {
        n1 <- Gen.int(Range.linear(-1, Int.MinValue)).log("n1")
        n2 <- Gen
                .int(Range.linear(-1, Int.MinValue))
                .map {
                  case `n1` =>
                    if n1 === Int.MinValue then n1 + 1
                    else if n1 === -1 then n1 - 1
                    else n1 + 1

                  case n2 =>
                    n2
                }
                .log("n2")
      } yield {
        val input1 = NegInt.unsafeFrom(n1)
        val input2 = NegInt.unsafeFrom(n2)

        Result.diffNamed("NegInt(value) =!= NegInt(value)", input1, input2)(_ =!= _)
      }

    def testHash: Property =
      for {
        n <- Gen.int(Range.linear(-1, Int.MinValue)).log("n")
      } yield {
        val input = NegInt.unsafeFrom(n)

        val expected       = n.hashCode
        val actual         = input.hash
        val actualHashCode = input.##

        Result.all(
          List(
            Result.diffNamed("NegInt(n).hash === n.hashCode", actual, expected)(_ === _),
            Result.diffNamed("NegInt(n).## === n.hashCode", actualHashCode, expected)(_ === _),
          )
        )
      }

    def testOrder: Property =
      for {
        n1 <- Gen
                .int(
                  Range.linear(
                    Int.MinValue - (Int.MinValue / 3), // -1431655766
                    Int.MinValue - ((Int.MinValue / 3) << 1), // -715827884
                  )
                )
                .log("n1")
        n2 <- Gen
                .frequency1(
                  25 -> Gen.int(Range.linear(Int.MinValue, n1 - 1)), // <
                  25 -> Gen.int(Range.linear(Int.MinValue, n1)), // <=
                  25 -> Gen.int(Range.linear(n1 + 1, -1)), // >
                  25 -> Gen.int(Range.linear(n1, -1)), // >=
                )
                .log("n2")
                .cover(20, "<", n2Val => n2Val < n1)
                .cover(20, "<=", n2Val => n2Val <= n1)
                .cover(20, ">", n2Val => n2Val > n1)
                .cover(20, ">=", n2Val => n2Val >= n1)
      } yield {
        val input1 = NegInt.unsafeFrom(n1)
        val input2 = NegInt.unsafeFrom(n2)

        val expected = cats.Order[Int].compare(n1, n2)
        val actual   = cats.Order[NegInt].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[NegInt].compare(input1, input2) === cats.Order[Int].compare(n1, n2)",
          actual,
          expected,
        )(_ === _)

      }

    def testOrderEqualCase: Property =
      for {
        n1 <- Gen.int(Range.linear(Int.MinValue, -1)).log("n1")
        n2 <- Gen.constant(n1).log("n2")
      } yield {
        val input1 = NegInt.unsafeFrom(n1)
        val input2 = NegInt.unsafeFrom(n2)

        val expected = 0
        val actual   = cats.Order[NegInt].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[NegInt].compare(NegInt(n1), NegInt(n2)) where n1 === n2 should return 0",
          actual,
          expected,
        )(_ === _)
      }

    def testShow: Property =
      for {
        n <- Gen.int(Range.linear(-1, Int.MinValue)).log("n")
      } yield {
        val input = NegInt.unsafeFrom(n)

        val expected = n.toString
        val actual   = input.show

        actual ==== expected
      }
  }

  object nonNegIntSpec {

    def tests: List[Test] = List(
      property("test   Eq[NonNegInt] === case", testEq),
      property("test   Eq[NonNegInt] =!= case", testEqNotEqual),
      property("test Hash[NonNegInt]", testHash),
      property("test Order[NonNegInt]", testOrder),
      property("test Order[NonNegInt] == case", testOrderEqualCase).withTests(25),
      property("test Show[NonNegInt]", testShow),
    )

    def testEq: Property =
      for {
        n <- Gen.int(Range.linear(0, Int.MaxValue)).log("n")
      } yield {
        val input = NonNegInt.unsafeFrom(n)

        val expected = input
        val actual   = input

        Result.diffNamed("NegInt(value) === NegInt(value)", actual, expected)(_ === _)
      }

    def testEqNotEqual: Property =
      for {
        n1 <- Gen.int(Range.linear(0, Int.MaxValue)).log("n1")
        n2 <- Gen
                .int(Range.linear(0, Int.MaxValue))
                .map {
                  case `n1` =>
                    if n1 === Int.MaxValue then n1 - 1
                    else if n1 === 0 then n1 + 1
                    else n1 + 1

                  case n2 =>
                    n2
                }
                .log("n2")
      } yield {
        val input1 = NonNegInt.unsafeFrom(n1)
        val input2 = NonNegInt.unsafeFrom(n2)

        Result.diffNamed("NonNegInt(value) =!= NonNegInt(value)", input1, input2)(_ =!= _)
      }

    def testHash: Property =
      for {
        n <- Gen.int(Range.linear(0, Int.MaxValue)).log("n")
      } yield {
        val input = NonNegInt.unsafeFrom(n)

        val expected       = n.hashCode
        val actual         = input.hash
        val actualHashCode = input.##

        Result.all(
          List(
            Result.diffNamed("NonNegInt(n).hash === n.hashCode", actual, expected)(_ === _),
            Result.diffNamed("NonNegInt(n).## === n.hashCode", actualHashCode, expected)(_ === _),
          )
        )
      }

    def testOrder: Property =
      for {
        n1 <- Gen.int(Range.linear(Int.MaxValue / 3, (Int.MaxValue / 3) << 1)).log("n1")
        n2 <- Gen
                .frequency1(
                  25 -> Gen.int(Range.linear(0, n1 - 1)), // <
                  25 -> Gen.int(Range.linear(0, n1)), // <=
                  25 -> Gen.int(Range.linear(n1 + 1, Int.MaxValue)), // >
                  25 -> Gen.int(Range.linear(n1, Int.MaxValue)), // >=
                )
                .log("n2")
                .cover(20, "<", n2Val => n2Val < n1)
                .cover(20, "<=", n2Val => n2Val <= n1)
                .cover(20, ">", n2Val => n2Val > n1)
                .cover(20, ">=", n2Val => n2Val >= n1)
      } yield {
        val input1 = NonNegInt.unsafeFrom(n1)
        val input2 = NonNegInt.unsafeFrom(n2)

        val expected = cats.Order[Int].compare(n1, n2)
        val actual   = cats.Order[NonNegInt].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[NonNegInt].compare(input1, input2) === cats.Order[Int].compare(n1, n2)",
          actual,
          expected,
        )(_ === _)

      }

    def testOrderEqualCase: Property =
      for {
        n1 <- Gen.int(Range.linear(0, Int.MaxValue)).log("n1")
        n2 <- Gen.constant(n1).log("n2")
      } yield {
        val input1 = NonNegInt.unsafeFrom(n1)
        val input2 = NonNegInt.unsafeFrom(n2)

        val expected = 0
        val actual   = cats.Order[NonNegInt].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[NonNegInt].compare(NonNegInt(n1), NonNegInt(n2)) where n1 === n2 should return 0",
          actual,
          expected,
        )(_ === _)
      }

    def testShow: Property =
      for {
        n <- Gen.int(Range.linear(0, Int.MaxValue)).log("n")
      } yield {
        val input = NonNegInt.unsafeFrom(n)

        val expected = n.toString
        val actual   = input.show

        actual ==== expected
      }
  }

  object posIntSpec {

    def tests: List[Test] = List(
      property("test   Eq[PosInt] === case", testEq),
      property("test   Eq[PosInt] =!= case", testEqNotEqual),
      property("test Hash[PosInt]", testHash),
      property("test Order[PosInt]", testOrder),
      property("test Order[PosInt] == case", testOrderEqualCase).withTests(25),
      property("test Show[PosInt]", testShow),
    )

    def testEq: Property =
      for {
        n <- Gen.int(Range.linear(1, Int.MaxValue)).log("n")
      } yield {
        val input = PosInt.unsafeFrom(n)

        val expected = input
        val actual   = input

        Result.diffNamed("PosInt(value) === PosInt(value)", actual, expected)(_ === _)
      }

    def testEqNotEqual: Property =
      for {
        n1 <- Gen.int(Range.linear(1, Int.MaxValue)).log("n1")
        n2 <- Gen
                .int(Range.linear(1, Int.MaxValue))
                .map {
                  case `n1` =>
                    if n1 === Int.MaxValue then n1 - 1
                    else if n1 === 1 then n1 + 1
                    else n1 + 1

                  case n2 =>
                    n2
                }
                .log("n2")
      } yield {
        val input1 = PosInt.unsafeFrom(n1)
        val input2 = PosInt.unsafeFrom(n2)

        Result.diffNamed("PosInt(value) =!= PosInt(value)", input1, input2)(_ =!= _)
      }

    def testHash: Property =
      for {
        n <- Gen.int(Range.linear(1, Int.MaxValue)).log("n")
      } yield {
        val input = PosInt.unsafeFrom(n)

        val expected       = n.hashCode
        val actual         = input.hash
        val actualHashCode = input.##

        Result.all(
          List(
            Result.diffNamed("PosInt(n).hash === n.hashCode", actual, expected)(_ === _),
            Result.diffNamed("PosInt(n).## === n.hashCode", actualHashCode, expected)(_ === _),
          )
        )
      }

    def testOrder: Property =
      for {
        n1 <- Gen.int(Range.linear(Int.MaxValue / 3, (Int.MaxValue / 3) << 1)).log("n1")
        n2 <- Gen
                .frequency1(
                  25 -> Gen.int(Range.linear(1, n1 - 1)), // <
                  25 -> Gen.int(Range.linear(1, n1)), // <=
                  25 -> Gen.int(Range.linear(n1 + 1, Int.MaxValue)), // >
                  25 -> Gen.int(Range.linear(n1, Int.MaxValue)), // >=
                )
                .log("n2")
                .cover(20, "<", n2Val => n2Val < n1)
                .cover(20, "<=", n2Val => n2Val <= n1)
                .cover(20, ">", n2Val => n2Val > n1)
                .cover(20, ">=", n2Val => n2Val >= n1)
      } yield {
        val input1 = PosInt.unsafeFrom(n1)
        val input2 = PosInt.unsafeFrom(n2)

        val expected = cats.Order[Int].compare(n1, n2)
        val actual   = cats.Order[PosInt].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[PosInt].compare(input1, input2) === cats.Order[Int].compare(n1, n2)",
          actual,
          expected,
        )(_ === _)

      }

    def testOrderEqualCase: Property =
      for {
        n1 <- Gen.int(Range.linear(1, Int.MaxValue)).log("n1")
        n2 <- Gen.constant(n1).log("n2")
      } yield {
        val input1 = PosInt.unsafeFrom(n1)
        val input2 = PosInt.unsafeFrom(n2)

        val expected = 0
        val actual   = cats.Order[PosInt].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[PosInt].compare(PosInt(n1), PosInt(n2)) where n1 === n2 should return 0",
          actual,
          expected,
        )(_ === _)
      }

    def testShow: Property =
      for {
        n <- Gen.int(Range.linear(1, Int.MaxValue)).log("n")
      } yield {
        val input = PosInt.unsafeFrom(n)

        val expected = n.toString
        val actual   = input.show

        actual ==== expected
      }
  }

  object nonPosIntSpec {
    def tests: List[Test] = List(
      property("test   Eq[NonPosInt] === case", testEq),
      property("test   Eq[NonPosInt] =!= case", testEqNotEqual),
      property("test Hash[NonPosInt]", testHash),
      property("test Order[NonPosInt]", testOrder),
      property("test Order[NonPosInt] == case", testOrderEqualCase).withTests(25),
      property("test Show[NonPosInt]", testShow),
    )

    def testEq: Property =
      for {
        n <- Gen.int(Range.linear(0, Int.MinValue)).log("n")
      } yield {
        val input = NonPosInt.unsafeFrom(n)

        val expected = input
        val actual   = input

        Result.diffNamed("NonPosInt(value) === NonPosInt(value)", actual, expected)(_ === _)
      }

    def testEqNotEqual: Property =
      for {
        n1 <- Gen.int(Range.linear(0, Int.MinValue)).log("n1")
        n2 <- Gen
                .int(Range.linear(0, Int.MinValue))
                .map {
                  case `n1` =>
                    if n1 === Int.MinValue then n1 + 1
                    else if n1 === 0 then n1 - 1
                    else n1 + 1

                  case n2 =>
                    n2
                }
                .log("n2")
      } yield {
        val input1 = NonPosInt.unsafeFrom(n1)
        val input2 = NonPosInt.unsafeFrom(n2)

        Result.diffNamed("NonPosInt(value) =!= NonPosInt(value)", input1, input2)(_ =!= _)
      }

    def testHash: Property =
      for {
        n <- Gen.int(Range.linear(0, Int.MinValue)).log("n")
      } yield {
        val input = NonPosInt.unsafeFrom(n)

        val expected       = n.hashCode
        val actual         = input.hash
        val actualHashCode = input.##

        Result.all(
          List(
            Result.diffNamed("NonPosInt(n).hash === n.hashCode", actual, expected)(_ === _),
            Result.diffNamed("NonPosInt(n).## === n.hashCode", actualHashCode, expected)(_ === _),
          )
        )
      }

    def testOrder: Property =
      for {
        n1 <- Gen.int(Range.linear(Int.MinValue - (Int.MinValue / 3), Int.MinValue - ((Int.MinValue / 3) << 1))).log("n1")
        n2 <- Gen
                .frequency1(
                  25 -> Gen.int(Range.linear(Int.MinValue, n1 - 1)), // <
                  25 -> Gen.int(Range.linear(Int.MinValue, n1)), // <=
                  25 -> Gen.int(Range.linear(n1 + 1, 0)), // >
                  25 -> Gen.int(Range.linear(n1, 0)), // >=
                )
                .log("n2")
                .cover(20, "<", n2Val => n2Val < n1)
                .cover(20, "<=", n2Val => n2Val <= n1)
                .cover(20, ">", n2Val => n2Val > n1)
                .cover(20, ">=", n2Val => n2Val >= n1)
      } yield {
        val input1 = NonPosInt.unsafeFrom(n1)
        val input2 = NonPosInt.unsafeFrom(n2)

        val expected = cats.Order[Int].compare(n1, n2)
        val actual   = cats.Order[NonPosInt].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[NonPosInt].compare(input1, input2) === cats.Order[Int].compare(n1, n2)",
          actual,
          expected,
        )(_ === _)

      }

    def testOrderEqualCase: Property =
      for {
        n1 <- Gen.int(Range.linear(Int.MinValue, 0)).log("n1")
        n2 <- Gen.constant(n1).log("n2")
      } yield {
        val input1 = NonPosInt.unsafeFrom(n1)
        val input2 = NonPosInt.unsafeFrom(n2)

        val expected = 0
        val actual   = cats.Order[NonPosInt].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[NonPosInt].compare(NonPosInt(n1), NonPosInt(n2)) where n1 === n2 should return 0",
          actual,
          expected,
        )(_ === _)
      }

    def testShow: Property =
      for {
        n <- Gen.int(Range.linear(0, Int.MinValue)).log("n")
      } yield {
        val input = NonPosInt.unsafeFrom(n)

        val expected = n.toString
        val actual   = input.show

        actual ==== expected
      }
  }

  object negLongSpec {

    def tests: List[Test] = List(
      property("test   Eq[NegLong] === case", testEq),
      property("test   Eq[NegLong] =!= case", testEqNotEqual),
      property("test Hash[NegLong]", testHash),
      property("test Order[NegLong]", testOrder),
      property("test Order[NegLong] == case", testOrderEqualCase).withTests(25),
      property("test Show[NegLong]", testShow),
    )

    def testEq: Property =
      for {
        n <- Gen.long(Range.linear(-1L, Long.MinValue)).log("n")
      } yield {
        val input = NegLong.unsafeFrom(n)

        val expected = input
        val actual   = input

        Result.diffNamed("NegLong(value) === NegLong(value)", actual, expected)(_ === _)
      }

    def testEqNotEqual: Property =
      for {
        n1 <- Gen.long(Range.linear(-1L, Long.MinValue)).log("n1")
        n2 <- Gen
                .long(Range.linear(-1L, Long.MinValue))
                .map {
                  case `n1` =>
                    if n1 === Long.MinValue then n1 + 1
                    else if n1 === -1 then n1 - 1
                    else n1 + 1

                  case n2 =>
                    n2
                }
                .log("n2")
      } yield {
        val input1 = NegLong.unsafeFrom(n1)
        val input2 = NegLong.unsafeFrom(n2)

        Result.diffNamed("NegLong(value) =!= NegLong(value)", input1, input2)(_ =!= _)
      }

    def testHash: Property =
      for {
        n <- Gen.long(Range.linear(-1L, Long.MinValue)).log("n")
      } yield {
        val input = NegLong.unsafeFrom(n)

        val expected       = n.hashCode
        val actual         = input.hash
        val actualHashCode = input.##

        Result.all(
          List(
            Result.diffNamed("NegLong(n).hash === n.hashCode", actual, expected)(_ === _),
            Result.diffNamed("NegLong(n).## === n.hashCode", actualHashCode, expected)(_ === _),
          )
        )
      }

    def testOrder: Property =
      for {
        n1 <- Gen.long(Range.linear(Long.MinValue - (Long.MinValue / 3), Long.MinValue - ((Long.MinValue / 3) << 1))).log("n1")
        n2 <- Gen
                .frequency1(
                  25 -> Gen.long(Range.linear(Long.MinValue, n1 - 1)), // <
                  25 -> Gen.long(Range.linear(Long.MinValue, n1)), // <=
                  25 -> Gen.long(Range.linear(n1 + 1, -1L)), // >
                  25 -> Gen.long(Range.linear(n1, -1L)), // >=
                )
                .log("n2")
                .cover(20, "<", n2Val => n2Val < n1)
                .cover(20, "<=", n2Val => n2Val <= n1)
                .cover(20, ">", n2Val => n2Val > n1)
                .cover(20, ">=", n2Val => n2Val >= n1)
      } yield {
        val input1 = NegLong.unsafeFrom(n1)
        val input2 = NegLong.unsafeFrom(n2)

        val expected = cats.Order[Long].compare(n1, n2)
        val actual   = cats.Order[NegLong].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[NegLong].compare(input1, input2) === cats.Order[Long].compare(n1, n2)",
          actual,
          expected,
        )(_ === _)

      }

    def testOrderEqualCase: Property =
      for {
        n1 <- Gen.long(Range.linear(Long.MinValue, -1L)).log("n1")
        n2 <- Gen.constant(n1).log("n2")
      } yield {
        val input1 = NegLong.unsafeFrom(n1)
        val input2 = NegLong.unsafeFrom(n2)

        val expected = 0
        val actual   = cats.Order[NegLong].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[NegLong].compare(NegLong(n1), NegLong(n2)) where n1 === n2 should return 0",
          actual,
          expected,
        )(_ === _)
      }

    def testShow: Property =
      for {
        n <- Gen.long(Range.linear(-1L, Long.MinValue)).log("n")
      } yield {
        val input = NegLong.unsafeFrom(n)

        val expected = n.toString
        val actual   = input.show

        actual ==== expected
      }

  }

  object nonNegLongSpec {

    def tests: List[Test] = List(
      property("test   Eq[NonNegLong] === case", testEq),
      property("test   Eq[NonNegLong] =!= case", testEqNotEqual),
      property("test Hash[NonNegLong]", testHash),
      property("test Order[NonNegLong]", testOrder),
      property("test Order[NonNegLong] == case", testOrderEqualCase).withTests(25),
      property("test Show[NonNegLong]", testShow),
    )

    def testEq: Property =
      for {
        n <- Gen.long(Range.linear(0L, Long.MaxValue)).log("n")
      } yield {
        val input = NonNegLong.unsafeFrom(n)

        val expected = input
        val actual   = input

        Result.diffNamed("NonNegLong(value) === NonNegLong(value)", actual, expected)(_ === _)
      }

    def testEqNotEqual: Property =
      for {
        n1 <- Gen.long(Range.linear(0L, Long.MaxValue)).log("n1")
        n2 <- Gen
                .long(Range.linear(0L, Long.MaxValue))
                .map {
                  case `n1` =>
                    if n1 === Long.MaxValue then n1 - 1
                    else if n1 === 0 then n1 + 1
                    else n1 + 1

                  case n2 =>
                    n2
                }
                .log("n2")
      } yield {
        val input1 = NonNegLong.unsafeFrom(n1)
        val input2 = NonNegLong.unsafeFrom(n2)

        Result.diffNamed("NonNegLong(value) =!= NonNegLong(value)", input1, input2)(_ =!= _)
      }

    def testHash: Property =
      for {
        n <- Gen.long(Range.linear(0L, Long.MaxValue)).log("n")
      } yield {
        val input = NonNegLong.unsafeFrom(n)

        val expected       = n.hashCode
        val actual         = input.hash
        val actualHashCode = input.##

        Result.all(
          List(
            Result.diffNamed("NonNegLong(n).hash === n.hashCode", actual, expected)(_ === _),
            Result.diffNamed("NonNegLong(n).## === n.hashCode", actualHashCode, expected)(_ === _),
          )
        )
      }

    def testOrder: Property =
      for {
        n1 <- Gen.long(Range.linear(Long.MaxValue / 3, (Long.MaxValue / 3) << 1)).log("n1")
        n2 <- Gen
                .frequency1(
                  25 -> Gen.long(Range.linear(0L, n1 - 1)), // <
                  25 -> Gen.long(Range.linear(0L, n1)), // <=
                  25 -> Gen.long(Range.linear(n1 + 1, Long.MaxValue)), // >
                  25 -> Gen.long(Range.linear(n1, Long.MaxValue)), // >=
                )
                .log("n2")
                .cover(20, "<", n2Val => n2Val < n1)
                .cover(20, "<=", n2Val => n2Val <= n1)
                .cover(20, ">", n2Val => n2Val > n1)
                .cover(20, ">=", n2Val => n2Val >= n1)
      } yield {
        val input1 = NonNegLong.unsafeFrom(n1)
        val input2 = NonNegLong.unsafeFrom(n2)

        val expected = cats.Order[Long].compare(n1, n2)
        val actual   = cats.Order[NonNegLong].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[NonNegLong].compare(input1, input2) === cats.Order[Long].compare(n1, n2)",
          actual,
          expected,
        )(_ === _)

      }

    def testOrderEqualCase: Property =
      for {
        n1 <- Gen.long(Range.linear(0L, Long.MaxValue)).log("n1")
        n2 <- Gen.constant(n1).log("n2")
      } yield {
        val input1 = NonNegLong.unsafeFrom(n1)
        val input2 = NonNegLong.unsafeFrom(n2)

        val expected = 0
        val actual   = cats.Order[NonNegLong].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[NonNegLong].compare(NonNegLong(n1), NonNegLong(n2)) where n1 === n2 should return 0",
          actual,
          expected,
        )(_ === _)
      }

    def testShow: Property =
      for {
        n <- Gen.long(Range.linear(0L, Long.MaxValue)).log("n")
      } yield {
        val input = NonNegLong.unsafeFrom(n)

        val expected = n.toString
        val actual   = input.show

        actual ==== expected
      }
  }

  object posLongSpec {

    def tests: List[Test] = List(
      property("test   Eq[PosLong] === case", testEq),
      property("test   Eq[PosLong] =!= case", testEqNotEqual),
      property("test Hash[PosLong]", testHash),
      property("test Order[PosLong]", testOrder),
      property("test Order[PosLong] == case", testOrderEqualCase).withTests(25),
      property("test Show[PosLong]", testShow),
    )

    def testEq: Property =
      for {
        n <- Gen.long(Range.linear(1L, Long.MaxValue)).log("n")
      } yield {
        val input = PosLong.unsafeFrom(n)

        val expected = input
        val actual   = input

        Result.diffNamed("PosLong(value) === PosLong(value)", actual, expected)(_ === _)
      }

    def testEqNotEqual: Property =
      for {
        n1 <- Gen.long(Range.linear(1L, Long.MaxValue)).log("n1")
        n2 <- Gen
                .long(Range.linear(1L, Long.MaxValue))
                .map {
                  case `n1` =>
                    if n1 === Long.MaxValue then n1 - 1
                    else if n1 === 1 then n1 + 1
                    else n1 + 1

                  case n2 =>
                    n2
                }
                .log("n2")
      } yield {
        val input1 = PosLong.unsafeFrom(n1)
        val input2 = PosLong.unsafeFrom(n2)

        Result.diffNamed("PosLong(value) =!= PosLong(value)", input1, input2)(_ =!= _)
      }

    def testHash: Property =
      for {
        n <- Gen.long(Range.linear(1L, Long.MaxValue)).log("n")
      } yield {
        val input = PosLong.unsafeFrom(n)

        val expected       = n.hashCode
        val actual         = input.hash
        val actualHashCode = input.##

        Result.all(
          List(
            Result.diffNamed("PosLong(n).hash === n.hashCode", actual, expected)(_ === _),
            Result.diffNamed("PosLong(n).## === n.hashCode", actualHashCode, expected)(_ === _),
          )
        )
      }

    def testOrder: Property =
      for {
        n1 <- Gen.long(Range.linear(Long.MaxValue / 3, (Long.MaxValue / 3) << 1)).log("n1")
        n2 <- Gen
                .frequency1(
                  25 -> Gen.long(Range.linear(1L, n1 - 1)), // <
                  25 -> Gen.long(Range.linear(1L, n1)), // <=
                  25 -> Gen.long(Range.linear(n1 + 1, Long.MaxValue)), // >
                  25 -> Gen.long(Range.linear(n1, Long.MaxValue)), // >=
                )
                .log("n2")
                .cover(20, "<", n2Val => n2Val < n1)
                .cover(20, "<=", n2Val => n2Val <= n1)
                .cover(20, ">", n2Val => n2Val > n1)
                .cover(20, ">=", n2Val => n2Val >= n1)
      } yield {
        val input1 = PosLong.unsafeFrom(n1)
        val input2 = PosLong.unsafeFrom(n2)

        val expected = cats.Order[Long].compare(n1, n2)
        val actual   = cats.Order[PosLong].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[PosLong].compare(input1, input2) === cats.Order[Long].compare(n1, n2)",
          actual,
          expected,
        )(_ === _)

      }

    def testOrderEqualCase: Property =
      for {
        n1 <- Gen.long(Range.linear(1L, Long.MaxValue)).log("n1")
        n2 <- Gen.constant(n1).log("n2")
      } yield {
        val input1 = PosLong.unsafeFrom(n1)
        val input2 = PosLong.unsafeFrom(n2)

        val expected = 0
        val actual   = cats.Order[PosLong].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[PosLong].compare(PosLong(n1), PosLong(n2)) where n1 === n2 should return 0",
          actual,
          expected,
        )(_ === _)
      }

    def testShow: Property =
      for {
        n <- Gen.long(Range.linear(1L, Long.MaxValue)).log("n")
      } yield {
        val input = PosLong.unsafeFrom(n)

        val expected = n.toString
        val actual   = input.show

        actual ==== expected
      }

  }

  object nonPosLongSpec {

    def tests: List[Test] = List(
      property("test   Eq[NonPosLong] === case", testEq),
      property("test   Eq[NonPosLong] =!= case", testEqNotEqual),
      property("test Hash[NonPosLong]", testHash),
      property("test Order[NonPosLong]", testOrder),
      property("test Order[NonPosLong] == case", testOrderEqualCase).withTests(25),
      property("test Show[NonPosLong]", testShow),
    )

    def testEq: Property =
      for {
        n <- Gen.long(Range.linear(0, Long.MinValue)).log("n")
      } yield {
        val input = NonPosLong.unsafeFrom(n)

        val expected = input
        val actual   = input

        Result.diffNamed("NonPosLong(value) === NonPosLong(value)", actual, expected)(_ === _)
      }

    def testEqNotEqual: Property =
      for {
        n1 <- Gen.long(Range.linear(0, Long.MinValue)).log("n1")
        n2 <- Gen
                .long(Range.linear(0, Long.MinValue))
                .map {
                  case `n1` =>
                    if n1 === Long.MinValue then n1 + 1
                    else if n1 === 0 then n1 - 1
                    else n1 + 1

                  case n2 =>
                    n2
                }
                .log("n2")
      } yield {
        val input1 = NonPosLong.unsafeFrom(n1)
        val input2 = NonPosLong.unsafeFrom(n2)

        Result.diffNamed("NonPosLong(value) =!= NonPosLong(value)", input1, input2)(_ =!= _)
      }

    def testHash: Property =
      for {
        n <- Gen.long(Range.linear(0, Long.MinValue)).log("n")
      } yield {
        val input = NonPosLong.unsafeFrom(n)

        val expected       = n.hashCode
        val actual         = input.hash
        val actualHashCode = input.##

        Result.all(
          List(
            Result.diffNamed("NonPosLong(n).hash === n.hashCode", actual, expected)(_ === _),
            Result.diffNamed("NonPosLong(n).## === n.hashCode", actualHashCode, expected)(_ === _),
          )
        )
      }

    def testOrder: Property =
      for {
        n1 <- Gen.long(Range.linear(Long.MinValue - (Long.MinValue / 3), Long.MinValue - ((Long.MinValue / 3) << 1))).log("n1")
        n2 <- Gen
                .frequency1(
                  25 -> Gen.long(Range.linear(Long.MinValue, n1 - 1)), // <
                  25 -> Gen.long(Range.linear(Long.MinValue, n1)), // <=
                  25 -> Gen.long(Range.linear(n1 + 1, 0L)), // >
                  25 -> Gen.long(Range.linear(n1, 0L)), // >=
                )
                .log("n2")
                .cover(20, "<", n2Val => n2Val < n1)
                .cover(20, "<=", n2Val => n2Val <= n1)
                .cover(20, ">", n2Val => n2Val > n1)
                .cover(20, ">=", n2Val => n2Val >= n1)
      } yield {
        val input1 = NonPosLong.unsafeFrom(n1)
        val input2 = NonPosLong.unsafeFrom(n2)

        val expected = cats.Order[Long].compare(n1, n2)
        val actual   = cats.Order[NonPosLong].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[NonPosLong].compare(input1, input2) === cats.Order[Long].compare(n1, n2)",
          actual,
          expected,
        )(_ === _)

      }

    def testOrderEqualCase: Property =
      for {
        n1 <- Gen.long(Range.linear(Long.MinValue, 0L)).log("n1")
        n2 <- Gen.constant(n1).log("n2")
      } yield {
        val input1 = NonPosLong.unsafeFrom(n1)
        val input2 = NonPosLong.unsafeFrom(n2)

        val expected = 0
        val actual   = cats.Order[NonPosLong].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[NonPosLong].compare(NonPosLong(n1), NonPosLong(n2)) where n1 === n2 should return 0",
          actual,
          expected,
        )(_ === _)
      }

    def testShow: Property =
      for {
        n <- Gen.long(Range.linear(0, Long.MinValue)).log("n")
      } yield {
        val input = NonPosLong.unsafeFrom(n)

        val expected = n.toString
        val actual   = input.show

        actual ==== expected
      }

  }

  object negShortSpec {

    def tests: List[Test] = List(
      property("test   Eq[NegShort] === case", testEq),
      property("test   Eq[NegShort] =!= case", testEqNotEqual),
      property("test Hash[NegShort]", testHash),
      property("test Order[NegShort]", testOrder),
      property("test Order[NegShort] == case", testOrderEqualCase).withTests(25),
      property("test Show[NegShort]", testShow),
    )

    def testEq: Property =
      for {
        n <- Gen.short(Range.linear(-1, Short.MinValue)).log("n")
      } yield {
        val input = NegShort.unsafeFrom(n)

        val expected = input
        val actual   = input

        Result.diffNamed("NegShort(value) === NegShort(value)", actual, expected)(_ === _)
      }

    def testEqNotEqual: Property =
      for {
        n1 <- Gen.short(Range.linear(-1, Short.MinValue)).log("n1")
        n2 <- Gen
                .short(Range.linear(-1, Short.MinValue))
                .map {
                  case `n1` =>
                    val newN2 =
                      if n1 === Short.MinValue then n1 + 1
                      else if n1 === -1 then n1 - 1
                      else n1 + 1
                    newN2.toShort

                  case n2 =>
                    n2
                }
                .log("n2")
      } yield {
        val input1 = NegShort.unsafeFrom(n1)
        val input2 = NegShort.unsafeFrom(n2)

        Result.diffNamed("NegShort(value) =!= NegShort(value)", input1, input2)(_ =!= _)
      }

    def testHash: Property =
      for {
        n <- Gen.short(Range.linear(-1, Short.MinValue)).log("n")
      } yield {
        val input = NegShort.unsafeFrom(n)

        val expected       = n.hashCode
        val actual         = input.hash
        val actualHashCode = input.##

        Result.all(
          List(
            Result.diffNamed("NegShort(n).hash === n.hashCode", actual, expected)(_ === _),
            Result.diffNamed("NegShort(n).## === n.hashCode", actualHashCode, expected)(_ === _),
          )
        )
      }

    def testOrder: Property =
      for {
        n1 <- Gen.short(Range.linear(-21845, -10923)).log("n1")
        n2 <- Gen
                .frequency1(
                  25 -> Gen.short(Range.linear(Short.MinValue, (n1 - 1).toShort)), // <
                  25 -> Gen.short(Range.linear(Short.MinValue, n1)), // <=
                  25 -> Gen.short(Range.linear((n1 + 1).toShort, -1)), // >
                  25 -> Gen.short(Range.linear(n1, -1)), // >=
                )
                .log("n2")
                .cover(20, "<", n2Val => n2Val < n1)
                .cover(20, "<=", n2Val => n2Val <= n1)
                .cover(20, ">", n2Val => n2Val > n1)
                .cover(20, ">=", n2Val => n2Val >= n1)
      } yield {
        val input1 = NegShort.unsafeFrom(n1)
        val input2 = NegShort.unsafeFrom(n2)

        val expected = cats.Order[Short].compare(n1, n2)
        val actual   = cats.Order[NegShort].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[NegShort].compare(input1, input2) === cats.Order[Short].compare(n1, n2)",
          actual,
          expected,
        )(_ === _)

      }

    def testOrderEqualCase: Property =
      for {
        n1 <- Gen.short(Range.linear(Short.MinValue, -1)).log("n1")
        n2 <- Gen.constant(n1).log("n2")
      } yield {
        val input1 = NegShort.unsafeFrom(n1)
        val input2 = NegShort.unsafeFrom(n2)

        val expected = 0
        val actual   = cats.Order[NegShort].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[NegShort].compare(NegShort(n1), NegShort(n2)) where n1 === n2 should return 0",
          actual,
          expected,
        )(_ === _)
      }

    def testShow: Property =
      for {
        n <- Gen.short(Range.linear(-1, Short.MinValue)).log("n")
      } yield {
        val input = NegShort.unsafeFrom(n)

        val expected = n.toString
        val actual   = input.show

        actual ==== expected
      }

  }

  object nonNegShortSpec {

    def tests: List[Test] = List(
      property("test   Eq[NonNegShort] === case", testEq),
      property("test   Eq[NonNegShort] =!= case", testEqNotEqual),
      property("test Hash[NonNegShort]", testHash),
      property("test Order[NonNegShort]", testOrder),
      property("test Order[NonNegShort] == case", testOrderEqualCase).withTests(25),
      property("test Show[NonNegShort]", testShow),
    )

    def testEq: Property =
      for {
        n <- Gen.short(Range.linear(0, Short.MaxValue)).log("n")
      } yield {
        val input = NonNegShort.unsafeFrom(n)

        val expected = input
        val actual   = input

        Result.diffNamed("NonNegShort(value) === NonNegShort(value)", actual, expected)(_ === _)
      }

    def testEqNotEqual: Property =
      for {
        n1 <- Gen.short(Range.linear(0, Short.MaxValue)).log("n1")
        n2 <- Gen
                .short(Range.linear(0, Short.MaxValue))
                .map {
                  case `n1` =>
                    val newN2 =
                      if n1 === Short.MaxValue then n1 - 1
                      else if n1 === 0 then n1 + 1
                      else n1 + 1
                    newN2.toShort

                  case n2 =>
                    n2
                }
                .log("n2")
      } yield {
        val input1 = NonNegShort.unsafeFrom(n1)
        val input2 = NonNegShort.unsafeFrom(n2)

        Result.diffNamed("NonNegShort(value) =!= NonNegShort(value)", input1, input2)(_ =!= _)
      }

    def testHash: Property =
      for {
        n <- Gen.short(Range.linear(0, Short.MaxValue)).log("n")
      } yield {
        val input = NonNegShort.unsafeFrom(n)

        val expected       = n.hashCode
        val actual         = input.hash
        val actualHashCode = input.##

        Result.all(
          List(
            Result.diffNamed("NonNegShort(n).hash === n.hashCode", actual, expected)(_ === _),
            Result.diffNamed("NonNegShort(n).## === n.hashCode", actualHashCode, expected)(_ === _),
          )
        )
      }

    def testOrder: Property =
      for {
        n1 <- Gen.short(Range.linear(10923, 21845)).log("n1")
        n2 <- Gen
                .frequency1(
                  25 -> Gen.short(Range.linear(0, (n1 - 1).toShort)), // <
                  25 -> Gen.short(Range.linear(0, n1)), // <=
                  25 -> Gen.short(Range.linear((n1 + 1).toShort, Short.MaxValue)), // >
                  25 -> Gen.short(Range.linear(n1, Short.MaxValue)), // >=
                )
                .log("n2")
                .cover(20, "<", n2Val => n2Val < n1)
                .cover(20, "<=", n2Val => n2Val <= n1)
                .cover(20, ">", n2Val => n2Val > n1)
                .cover(20, ">=", n2Val => n2Val >= n1)
      } yield {
        val input1 = NonNegShort.unsafeFrom(n1)
        val input2 = NonNegShort.unsafeFrom(n2)

        val expected = cats.Order[Short].compare(n1, n2)
        val actual   = cats.Order[NonNegShort].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[NonNegShort].compare(input1, input2) === cats.Order[Short].compare(n1, n2)",
          actual,
          expected,
        )(_ === _)

      }

    def testOrderEqualCase: Property =
      for {
        n1 <- Gen.short(Range.linear(0, Short.MaxValue)).log("n1")
        n2 <- Gen.constant(n1).log("n2")
      } yield {
        val input1 = NonNegShort.unsafeFrom(n1)
        val input2 = NonNegShort.unsafeFrom(n2)

        val expected = 0
        val actual   = cats.Order[NonNegShort].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[NonNegShort].compare(NonNegShort(n1), NonNegShort(n2)) where n1 === n2 should return 0",
          actual,
          expected,
        )(_ === _)
      }

    def testShow: Property =
      for {
        n <- Gen.short(Range.linear(0, Short.MaxValue)).log("n")
      } yield {
        val input = NonNegShort.unsafeFrom(n)

        val expected = n.toString
        val actual   = input.show

        actual ==== expected
      }

  }

  object posShortSpec {

    def tests: List[Test] = List(
      property("test   Eq[PosShort] === case", testEq),
      property("test   Eq[PosShort] =!= case", testEqNotEqual),
      property("test Hash[PosShort]", testHash),
      property("test Order[PosShort]", testOrder),
      property("test Order[PosShort] == case", testOrderEqualCase).withTests(25),
      property("test Show[PosShort]", testShow),
    )

    def testEq: Property =
      for {
        n <- Gen.short(Range.linear(1, Short.MaxValue)).log("n")
      } yield {
        val input = PosShort.unsafeFrom(n)

        val expected = input
        val actual   = input

        Result.diffNamed("PosShort(value) === PosShort(value)", actual, expected)(_ === _)
      }

    def testEqNotEqual: Property =
      for {
        n1 <- Gen.short(Range.linear(1, Short.MaxValue)).log("n1")
        n2 <- Gen
                .short(Range.linear(1, Short.MaxValue))
                .map {
                  case `n1` =>
                    val newN2 =
                      if n1 === Short.MaxValue then n1 - 1
                      else if n1 === 1 then n1 + 1
                      else n1 + 1
                    newN2.toShort

                  case n2 =>
                    n2
                }
                .log("n2")
      } yield {
        val input1 = PosShort.unsafeFrom(n1)
        val input2 = PosShort.unsafeFrom(n2)

        Result.diffNamed("PosShort(value) =!= PosShort(value)", input1, input2)(_ =!= _)
      }

    def testHash: Property =
      for {
        n <- Gen.short(Range.linear(1, Short.MaxValue)).log("n")
      } yield {
        val input = PosShort.unsafeFrom(n)

        val expected       = n.hashCode
        val actual         = input.hash
        val actualHashCode = input.##

        Result.all(
          List(
            Result.diffNamed("PosShort(n).hash === n.hashCode", actual, expected)(_ === _),
            Result.diffNamed("PosShort(n).## === n.hashCode", actualHashCode, expected)(_ === _),
          )
        )
      }

    def testOrder: Property =
      for {
        n1 <- Gen.short(Range.linear(10923, 21845)).log("n1")
        n2 <- Gen
                .frequency1(
                  25 -> Gen.short(Range.linear(1, (n1 - 1).toShort)), // <
                  25 -> Gen.short(Range.linear(1, n1)), // <=
                  25 -> Gen.short(Range.linear((n1 + 1).toShort, Short.MaxValue)), // >
                  25 -> Gen.short(Range.linear(n1, Short.MaxValue)), // >=
                )
                .log("n2")
                .cover(20, "<", n2Val => n2Val < n1)
                .cover(20, "<=", n2Val => n2Val <= n1)
                .cover(20, ">", n2Val => n2Val > n1)
                .cover(20, ">=", n2Val => n2Val >= n1)
      } yield {
        val input1 = PosShort.unsafeFrom(n1)
        val input2 = PosShort.unsafeFrom(n2)

        val expected = cats.Order[Short].compare(n1, n2)
        val actual   = cats.Order[PosShort].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[PosShort].compare(input1, input2) === cats.Order[Short].compare(n1, n2)",
          actual,
          expected,
        )(_ === _)

      }

    def testOrderEqualCase: Property =
      for {
        n1 <- Gen.short(Range.linear(1, Short.MaxValue)).log("n1")
        n2 <- Gen.constant(n1).log("n2")
      } yield {
        val input1 = PosShort.unsafeFrom(n1)
        val input2 = PosShort.unsafeFrom(n2)

        val expected = 0
        val actual   = cats.Order[PosShort].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[PosShort].compare(PosShort(n1), PosShort(n2)) where n1 === n2 should return 0",
          actual,
          expected,
        )(_ === _)
      }

    def testShow: Property =
      for {
        n <- Gen.short(Range.linear(1, Short.MaxValue)).log("n")
      } yield {
        val input = PosShort.unsafeFrom(n)

        val expected = n.toString
        val actual   = input.show

        actual ==== expected
      }
  }

  object nonPosShortSpec {

    def tests: List[Test] = List(
      property("test   Eq[NonPosShort] === case", testEq),
      property("test   Eq[NonPosShort] =!= case", testEqNotEqual),
      property("test Hash[NonPosShort]", testHash),
      property("test Order[NonPosShort]", testOrder),
      property("test Order[NonPosShort] == case", testOrderEqualCase).withTests(25),
      property("test Show[NonPosShort]", testShow),
    )

    def testEq: Property =
      for {
        n <- Gen.short(Range.linear(0, Short.MinValue)).log("n")
      } yield {
        val input = NonPosShort.unsafeFrom(n)

        val expected = input
        val actual   = input

        Result.diffNamed("NonPosShort(value) === NonPosShort(value)", actual, expected)(_ === _)
      }

    def testEqNotEqual: Property =
      for {
        n1 <- Gen.short(Range.linear(0, Short.MinValue)).log("n1")
        n2 <- Gen
                .short(Range.linear(0, Short.MinValue))
                .map {
                  case `n1` =>
                    val newN2 =
                      if n1 === Short.MinValue then n1 + 1
                      else if n1 === 0 then n1 - 1
                      else n1 + 1
                    newN2.toShort

                  case n2 =>
                    n2
                }
                .log("n2")
      } yield {
        val input1 = NonPosShort.unsafeFrom(n1)
        val input2 = NonPosShort.unsafeFrom(n2)

        Result.diffNamed("NonPosShort(value) =!= NonPosShort(value)", input1, input2)(_ =!= _)
      }

    def testHash: Property =
      for {
        n <- Gen.short(Range.linear(0, Short.MinValue)).log("n")
      } yield {
        val input = NonPosShort.unsafeFrom(n)

        val expected       = n.hashCode
        val actual         = input.hash
        val actualHashCode = input.##

        Result.all(
          List(
            Result.diffNamed("NonPosShort(n).hash === n.hashCode", actual, expected)(_ === _),
            Result.diffNamed("NonPosShort(n).## === n.hashCode", actualHashCode, expected)(_ === _),
          )
        )
      }

    def testOrder: Property =
      for {
        n1 <- Gen.short(Range.linear(-21845, -10923)).log("n1")
        n2 <- Gen
                .frequency1(
                  25 -> Gen.short(Range.linear(Short.MinValue, (n1 - 1).toShort)), // <
                  25 -> Gen.short(Range.linear(Short.MinValue, n1)), // <=
                  25 -> Gen.short(Range.linear((n1 + 1).toShort, 0)), // >
                  25 -> Gen.short(Range.linear(n1, 0)), // >=
                )
                .log("n2")
                .cover(20, "<", n2Val => n2Val < n1)
                .cover(20, "<=", n2Val => n2Val <= n1)
                .cover(20, ">", n2Val => n2Val > n1)
                .cover(20, ">=", n2Val => n2Val >= n1)
      } yield {
        val input1 = NonPosShort.unsafeFrom(n1)
        val input2 = NonPosShort.unsafeFrom(n2)

        val expected = cats.Order[Short].compare(n1, n2)
        val actual   = cats.Order[NonPosShort].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[NonPosShort].compare(input1, input2) === cats.Order[Short].compare(n1, n2)",
          actual,
          expected,
        )(_ === _)

      }

    def testOrderEqualCase: Property =
      for {
        n1 <- Gen.short(Range.linear(Short.MinValue, 0)).log("n1")
        n2 <- Gen.constant(n1).log("n2")
      } yield {
        val input1 = NonPosShort.unsafeFrom(n1)
        val input2 = NonPosShort.unsafeFrom(n2)

        val expected = 0
        val actual   = cats.Order[NonPosShort].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[NonPosShort].compare(NonPosShort(n1), NonPosShort(n2)) where n1 === n2 should return 0",
          actual,
          expected,
        )(_ === _)
      }

    def testShow: Property =
      for {
        n <- Gen.short(Range.linear(0, Short.MinValue)).log("n")
      } yield {
        val input = NonPosShort.unsafeFrom(n)

        val expected = n.toString
        val actual   = input.show

        actual ==== expected
      }

  }

  object negByteSpec {

    def tests: List[Test] = List(
      property("test   Eq[NegByte] === case", testEq),
      property("test   Eq[NegByte] =!= case", testEqNotEqual),
      property("test Hash[NegByte]", testHash),
      property("test Order[NegByte]", testOrder),
      property("test Order[NegByte] == case", testOrderEqualCase).withTests(25),
      property("test Show[NegByte]", testShow),
    )

    def testEq: Property =
      for {
        n <- Gen.byte(Range.linear(-1, Byte.MinValue)).log("n")
      } yield {
        val input = NegByte.unsafeFrom(n)

        val expected = input
        val actual   = input

        Result.diffNamed("NegByte(value) === NegByte(value)", actual, expected)(_ === _)
      }

    def testEqNotEqual: Property =
      for {
        n1 <- Gen.byte(Range.linear(-1, Byte.MinValue)).log("n1")
        n2 <- Gen
                .byte(Range.linear(-1, Byte.MinValue))
                .map {
                  case `n1` =>
                    val newN2 =
                      if n1 === Byte.MinValue then n1 + 1
                      else if n1 === -1 then n1 - 1
                      else n1 - 1
                    newN2.toByte
                  case n2 =>
                    n2
                }
                .log("n2")
      } yield {
        val input1 = NegByte.unsafeFrom(n1)
        val input2 = NegByte.unsafeFrom(n2)

        Result.diffNamed("NegByte(value) =!= NegByte(value)", input1, input2)(_ =!= _)
      }

    def testHash: Property =
      for {
        n <- Gen.byte(Range.linear(-1, Byte.MinValue)).log("n")
      } yield {
        val input = NegByte.unsafeFrom(n)

        val expected       = n.hashCode
        val actual         = input.hash
        val actualHashCode = input.##

        Result.all(
          List(
            Result.diffNamed("NegByte(n).hash === n.hashCode", actual, expected)(_ === _),
            Result.diffNamed("NegByte(n).## === n.hashCode", actualHashCode, expected)(_ === _),
          )
        )
      }

    def testOrder: Property =
      for {
        n1 <- Gen.byte(Range.linear(-85, -43)).log("n1")
        n2 <- Gen
                .frequency1(
                  25 -> Gen.byte(Range.linear(Byte.MinValue, (n1 - 1).toByte)), // <
                  25 -> Gen.byte(Range.linear(Byte.MinValue, n1)), // <=
                  25 -> Gen.byte(Range.linear((n1 + 1).toByte, -1)), // >
                  25 -> Gen.byte(Range.linear(n1, -1)), // >=
                )
                .log("n2")
                .cover(20, "<", n2Val => n2Val < n1)
                .cover(20, "<=", n2Val => n2Val <= n1)
                .cover(20, ">", n2Val => n2Val > n1)
                .cover(20, ">=", n2Val => n2Val >= n1)
      } yield {
        val input1 = NegByte.unsafeFrom(n1)
        val input2 = NegByte.unsafeFrom(n2)

        val expected = cats.Order[Byte].compare(n1, n2)
        val actual   = cats.Order[NegByte].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[NegByte].compare(input1, input2) === cats.Order[Byte].compare(n1, n2)",
          actual,
          expected,
        )(_ === _)

      }

    def testOrderEqualCase: Property =
      for {
        n1 <- Gen.byte(Range.linear(Byte.MinValue, -1)).log("n1")
        n2 <- Gen.constant(n1).log("n2")
      } yield {
        val input1 = NegByte.unsafeFrom(n1)
        val input2 = NegByte.unsafeFrom(n2)

        val expected = 0
        val actual   = cats.Order[NegByte].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[NegByte].compare(NegByte(n1), NegByte(n2)) where n1 === n2 should return 0",
          actual,
          expected,
        )(_ === _)
      }

    def testShow: Property =
      for {
        n <- Gen.byte(Range.linear(-1, Byte.MinValue)).log("n")
      } yield {
        val input = NegByte.unsafeFrom(n)

        val expected = n.toString
        val actual   = input.show

        actual ==== expected
      }

  }

  object nonNegByteSpec {

    def tests: List[Test] = List(
      property("test   Eq[NonNegByte] === case", testEq),
      property("test   Eq[NonNegByte] =!= case", testEqNotEqual),
      property("test Hash[NonNegByte]", testHash),
      property("test Order[NonNegByte]", testOrder),
      property("test Order[NonNegByte] == case", testOrderEqualCase).withTests(25),
      property("test Show[NonNegByte]", testShow),
    )

    def testEq: Property =
      for {
        n <- Gen.byte(Range.linear(0, Byte.MaxValue)).log("n")
      } yield {
        val input = NonNegByte.unsafeFrom(n)

        val expected = input
        val actual   = input

        Result.diffNamed("NonNegByte(value) === NonNegByte(value)", actual, expected)(_ === _)
      }

    def testEqNotEqual: Property =
      for {
        n1 <- Gen.byte(Range.linear(0, Byte.MaxValue)).log("n1")
        n2 <- Gen
                .byte(Range.linear(0, Byte.MaxValue))
                .map {
                  case `n1` =>
                    val newN2 =
                      if n1 === Byte.MaxValue then n1 - 1
                      else if n1 === 0 then n1 + 1
                      else n1 + 1
                    newN2.toByte

                  case n2 =>
                    n2
                }
                .log("n2")
      } yield {
        val input1 = NonNegByte.unsafeFrom(n1)
        val input2 = NonNegByte.unsafeFrom(n2)

        Result.diffNamed("NonNegByte(value) =!= NonNegByte(value)", input1, input2)(_ =!= _)
      }

    def testHash: Property =
      for {
        n <- Gen.byte(Range.linear(0, Byte.MaxValue)).log("n")
      } yield {
        val input = NonNegByte.unsafeFrom(n)

        val expected       = n.hashCode
        val actual         = input.hash
        val actualHashCode = input.##

        Result.all(
          List(
            Result.diffNamed("NonNegByte(n).hash === n.hashCode", actual, expected)(_ === _),
            Result.diffNamed("NonNegByte(n).## === n.hashCode", actualHashCode, expected)(_ === _),
          )
        )
      }

    def testOrder: Property =
      for {
        n1 <- Gen.byte(Range.linear(43, 85)).log("n1")
        n2 <- Gen
                .frequency1(
                  25 -> Gen.byte(Range.linear(0, (n1 - 1).toByte)), // <
                  25 -> Gen.byte(Range.linear(0, n1)), // <=
                  25 -> Gen.byte(Range.linear((n1 + 1).toByte, Byte.MaxValue)), // >
                  25 -> Gen.byte(Range.linear(n1, Byte.MaxValue)), // >=
                )
                .log("n2")
                .cover(20, "<", n2Val => n2Val < n1)
                .cover(20, "<=", n2Val => n2Val <= n1)
                .cover(20, ">", n2Val => n2Val > n1)
                .cover(20, ">=", n2Val => n2Val >= n1)
      } yield {
        val input1 = NonNegByte.unsafeFrom(n1)
        val input2 = NonNegByte.unsafeFrom(n2)

        val expected = cats.Order[Byte].compare(n1, n2)
        val actual   = cats.Order[NonNegByte].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[NonNegByte].compare(input1, input2) === cats.Order[Byte].compare(n1, n2)",
          actual,
          expected,
        )(_ === _)

      }

    def testOrderEqualCase: Property =
      for {
        n1 <- Gen.byte(Range.linear(0, Byte.MaxValue)).log("n1")
        n2 <- Gen.constant(n1).log("n2")
      } yield {
        val input1 = NonNegByte.unsafeFrom(n1)
        val input2 = NonNegByte.unsafeFrom(n2)

        val expected = 0
        val actual   = cats.Order[NonNegByte].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[NonNegByte].compare(NonNegByte(n1), NonNegByte(n2)) where n1 === n2 should return 0",
          actual,
          expected,
        )(_ === _)
      }

    def testShow: Property =
      for {
        n <- Gen.byte(Range.linear(0, Byte.MaxValue)).log("n")
      } yield {
        val input = NonNegByte.unsafeFrom(n)

        val expected = n.toString
        val actual   = input.show

        actual ==== expected
      }

  }

  object posByteSpec {

    def tests: List[Test] = List(
      property("test   Eq[PosByte] === case", testEq),
      property("test   Eq[PosByte] =!= case", testEqNotEqual),
      property("test Hash[PosByte]", testHash),
      property("test Order[PosByte]", testOrder),
      property("test Order[PosByte] == case", testOrderEqualCase).withTests(25),
      property("test Show[PosByte]", testShow),
    )

    def testEq: Property =
      for {
        n <- Gen.byte(Range.linear(1, Byte.MaxValue)).log("n")
      } yield {
        val input = PosByte.unsafeFrom(n)

        val expected = input
        val actual   = input

        Result.diffNamed("PosByte(value) === PosByte(value)", actual, expected)(_ === _)
      }

    def testEqNotEqual: Property =
      for {
        n1 <- Gen.byte(Range.linear(1, Byte.MaxValue)).log("n1")
        n2 <- Gen
                .byte(Range.linear(1, Byte.MaxValue))
                .map {
                  case `n1` =>
                    val newN2 =
                      if n1 === Byte.MaxValue then n1 - 1
                      else if n1 === 1 then n1 + 1
                      else n1 + 1
                    newN2.toByte

                  case n2 =>
                    n2
                }
                .log("n2")
      } yield {
        val input1 = PosByte.unsafeFrom(n1)
        val input2 = PosByte.unsafeFrom(n2)

        Result.diffNamed("PosByte(value) =!= PosByte(value)", input1, input2)(_ =!= _)
      }

    def testHash: Property =
      for {
        n <- Gen.byte(Range.linear(1, Byte.MaxValue)).log("n")
      } yield {
        val input = PosByte.unsafeFrom(n)

        val expected       = n.hashCode
        val actual         = input.hash
        val actualHashCode = input.##

        Result.all(
          List(
            Result.diffNamed("PosByte(n).hash === n.hashCode", actual, expected)(_ === _),
            Result.diffNamed("PosByte(n).## === n.hashCode", actualHashCode, expected)(_ === _),
          )
        )
      }

    def testOrder: Property =
      for {
        n1 <- Gen.byte(Range.linear(43, 85)).log("n1")
        n2 <- Gen
                .frequency1(
                  25 -> Gen.byte(Range.linear(1, (n1 - 1).toByte)), // <
                  25 -> Gen.byte(Range.linear(1, n1)), // <=
                  25 -> Gen.byte(Range.linear((n1 + 1).toByte, Byte.MaxValue)), // >
                  25 -> Gen.byte(Range.linear(n1, Byte.MaxValue)), // >=
                )
                .log("n2")
                .cover(20, "<", n2Val => n2Val < n1)
                .cover(20, "<=", n2Val => n2Val <= n1)
                .cover(20, ">", n2Val => n2Val > n1)
                .cover(20, ">=", n2Val => n2Val >= n1)
      } yield {
        val input1 = PosByte.unsafeFrom(n1)
        val input2 = PosByte.unsafeFrom(n2)

        val expected = cats.Order[Byte].compare(n1, n2)
        val actual   = cats.Order[PosByte].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[PosByte].compare(input1, input2) === cats.Order[Byte].compare(n1, n2)",
          actual,
          expected,
        )(_ === _)

      }

    def testOrderEqualCase: Property =
      for {
        n1 <- Gen.byte(Range.linear(1, Byte.MaxValue)).log("n1")
        n2 <- Gen.constant(n1).log("n2")
      } yield {
        val input1 = PosByte.unsafeFrom(n1)
        val input2 = PosByte.unsafeFrom(n2)

        val expected = 0
        val actual   = cats.Order[PosByte].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[PosByte].compare(PosByte(n1), PosByte(n2)) where n1 === n2 should return 0",
          actual,
          expected,
        )(_ === _)
      }

    def testShow: Property =
      for {
        n <- Gen.byte(Range.linear(1, Byte.MaxValue)).log("n")
      } yield {
        val input = PosByte.unsafeFrom(n)

        val expected = n.toString
        val actual   = input.show

        actual ==== expected
      }

  }

  object nonPosByteSpec {

    def tests: List[Test] = List(
      property("test   Eq[NonPosByte] === case", testEq),
      property("test   Eq[NonPosByte] =!= case", testEqNotEqual),
      property("test Hash[NonPosByte]", testHash),
      property("test Order[NonPosByte]", testOrder),
      property("test Order[NonPosByte] == case", testOrderEqualCase).withTests(25),
      property("test Show[NonPosByte]", testShow),
    )

    def testEq: Property =
      for {
        n <- Gen.byte(Range.linear(0, Byte.MinValue)).log("n")
      } yield {
        val input = NonPosByte.unsafeFrom(n)

        val expected = input
        val actual   = input

        Result.diffNamed("NonPosByte(value) === NonPosByte(value)", actual, expected)(_ === _)
      }

    def testEqNotEqual: Property =
      for {
        n1 <- Gen.byte(Range.linear(0, Byte.MinValue)).log("n1")
        n2 <- Gen
                .byte(Range.linear(0, Byte.MinValue))
                .map {
                  case `n1` =>
                    val newN2 =
                      if n1 === Byte.MinValue then n1 + 1
                      else if n1 === 0 then n1 - 1
                      else n1 + 1
                    newN2.toByte

                  case n2 =>
                    n2
                }
                .log("n2")
      } yield {
        val input1 = NonPosByte.unsafeFrom(n1)
        val input2 = NonPosByte.unsafeFrom(n2)

        Result.diffNamed("NonPosByte(value) =!= NonPosByte(value)", input1, input2)(_ =!= _)
      }

    def testHash: Property =
      for {
        n <- Gen.byte(Range.linear(0, Byte.MinValue)).log("n")
      } yield {
        val input = NonPosByte.unsafeFrom(n)

        val expected       = n.hashCode
        val actual         = input.hash
        val actualHashCode = input.##

        Result.all(
          List(
            Result.diffNamed("NonPosByte(n).hash === n.hashCode", actual, expected)(_ === _),
            Result.diffNamed("NonPosByte(n).## === n.hashCode", actualHashCode, expected)(_ === _),
          )
        )
      }

    def testOrder: Property =
      for {
        n1 <- Gen.byte(Range.linear(-85, -43)).log("n1")
        n2 <- Gen
                .frequency1(
                  25 -> Gen.byte(Range.linear(Byte.MinValue, (n1 - 1).toByte)), // <
                  25 -> Gen.byte(Range.linear(Byte.MinValue, n1)), // <=
                  25 -> Gen.byte(Range.linear((n1 + 1).toByte, 0)), // >
                  25 -> Gen.byte(Range.linear(n1, 0)), // >=
                )
                .log("n2")
                .cover(20, "<", n2Val => n2Val < n1)
                .cover(20, "<=", n2Val => n2Val <= n1)
                .cover(20, ">", n2Val => n2Val > n1)
                .cover(20, ">=", n2Val => n2Val >= n1)
      } yield {
        val input1 = NonPosByte.unsafeFrom(n1)
        val input2 = NonPosByte.unsafeFrom(n2)

        val expected = cats.Order[Byte].compare(n1, n2)
        val actual   = cats.Order[NonPosByte].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[NonPosByte].compare(input1, input2) === cats.Order[Byte].compare(n1, n2)",
          actual,
          expected,
        )(_ === _)

      }

    def testOrderEqualCase: Property =
      for {
        n1 <- Gen.byte(Range.linear(Byte.MinValue, 0)).log("n1")
        n2 <- Gen.constant(n1).log("n2")
      } yield {
        val input1 = NonPosByte.unsafeFrom(n1)
        val input2 = NonPosByte.unsafeFrom(n2)

        val expected = 0
        val actual   = cats.Order[NonPosByte].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[NonPosByte].compare(NonPosByte(n1), NonPosByte(n2)) where n1 === n2 should return 0",
          actual,
          expected,
        )(_ === _)
      }

    def testShow: Property =
      for {
        n <- Gen.byte(Range.linear(0, Byte.MinValue)).log("n")
      } yield {
        val input = NonPosByte.unsafeFrom(n)

        val expected = n.toString
        val actual   = input.show

        actual ==== expected
      }

  }

  object negFloatSpec {

    def tests: List[Test] = List(
      property("test   Eq[NegFloat] === case", testEq),
      property("test   Eq[NegFloat] =!= case", testEqNotEqual),
      property("test Hash[NegFloat]", testHash),
      property("test Order[NegFloat]", testOrder),
      property("test Order[NegFloat] == case", testOrderEqualCase).withTests(25),
      property("test Show[NegFloat]", testShow),
    )

    def testEq: Property =
      for {
        n <- Gen.double(Range.linearFrac(-0.00001d, Float.MinValue)).map(_.toFloat).log("n")
      } yield {
        val input = NegFloat.unsafeFrom(n)

        val expected = input
        val actual   = input

        Result.diffNamed("NegFloat(value) === NegFloat(value)", actual, expected)(_ === _)
      }

    def testEqNotEqual: Property =
      for {
        n1 <- Gen.double(Range.linearFrac(-0.00001d, Float.MinValue)).map(_.toFloat).log("n1")
        n2 <- Gen
                .double(Range.linearFrac(-0.00001d, Float.MinValue))
                .map(_.toFloat)
                .map {
                  case `n1` =>
                    val newN2 =
                      if n1 === Float.MinValue then n1 + 1
                      else if n1 > -1f then n1 - 1
                      else n1 - 1
                    newN2

                  case n2 =>
                    n2
                }
                .log("n2")
      } yield {
        val input1 = NegFloat.unsafeFrom(n1)
        val input2 = NegFloat.unsafeFrom(n2)

        Result.diffNamed("NegFloat(value) =!= NegFloat(value)", input1, input2)(_ =!= _)
      }

    def testHash: Property =
      for {
        n <- Gen.double(Range.linearFrac(-0.00001d, Float.MinValue)).map(_.toFloat).log("n")
      } yield {
        val input = NegFloat.unsafeFrom(n)

        val expected       = n.hashCode
        val actual         = input.hash
        val actualHashCode = input.##

        Result.all(
          List(
            Result.diffNamed("NegFloat(n).hash === n.hashCode", actual, expected)(_ === _),
            Result.diffNamed("NegFloat(n).## === n.hashCode", actualHashCode, expected)(_ === _),
          )
        )
      }

    def testOrder: Property =
      for {
        n1 <- Gen.double(Range.linearFrac(-1000000d, -1000d)).log("n1")
        n2 <- Gen
                .frequency1(
                  25 -> Gen.double(Range.linearFrac(Float.MinValue, n1 - 1d)), // <
                  25 -> Gen.double(Range.linearFrac(Float.MinValue, n1)), // <=
                  25 -> Gen.double(Range.linearFrac(n1 + 1d, -0.00001d)), // >
                  25 -> Gen.double(Range.linearFrac(n1, -0.00001d)), // >=
                )
                .log("n2")
                .cover(20, "<", n2Val => n2Val < n1)
                .cover(20, "<=", n2Val => n2Val <= n1)
                .cover(20, ">", n2Val => n2Val > n1)
                .cover(20, ">=", n2Val => n2Val >= n1)
      } yield {
        val input1 = NegFloat.unsafeFrom(n1.toFloat)
        val input2 = NegFloat.unsafeFrom(n2.toFloat)

        val expected = cats.Order[Float].compare(n1.toFloat, n2.toFloat)
        val actual   = cats.Order[NegFloat].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[NegFloat].compare(input1, input2) === cats.Order[Float].compare(n1, n2)",
          actual,
          expected,
        )(_ === _)

      }

    def testOrderEqualCase: Property =
      for {
        n1 <- Gen.double(Range.linearFrac(Float.MinValue, -0.00001d)).log("n1")
        n2 <- Gen.constant(n1).log("n2")
      } yield {
        val input1 = NegFloat.unsafeFrom(n1.toFloat)
        val input2 = NegFloat.unsafeFrom(n2.toFloat)

        val expected = 0
        val actual   = cats.Order[NegFloat].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[NegFloat].compare(NegFloat(n1), NegFloat(n2)) where n1 === n2 should return 0",
          actual,
          expected,
        )(_ === _)
      }

    def testShow: Property =
      for {
        n <- Gen.double(Range.linearFrac(-0.00001d, Float.MinValue)).map(_.toFloat).log("n")
      } yield {
        val input = NegFloat.unsafeFrom(n)

        val expected = n.toString
        val actual   = input.show

        actual ==== expected
      }

  }

  object nonNegFloatSpec {

    def tests: List[Test] = List(
      property("test   Eq[NonNegFloat] === case", testEq),
      property("test   Eq[NonNegFloat] =!= case", testEqNotEqual),
      property("test Hash[NonNegFloat]", testHash),
      property("test Order[NonNegFloat]", testOrder),
      property("test Order[NonNegFloat] == case", testOrderEqualCase).withTests(25),
      property("test Show[NonNegFloat]", testShow),
    )

    def testEq: Property =
      for {
        n <- Gen.double(Range.linearFrac(0d, Float.MaxValue)).map(_.toFloat).log("n")
      } yield {
        val input = NonNegFloat.unsafeFrom(n)

        val expected = input
        val actual   = input

        Result.diffNamed("NonNegFloat(value) === NonNegFloat(value)", actual, expected)(_ === _)
      }

    def testEqNotEqual: Property =
      for {
        n1 <- Gen.double(Range.linearFrac(0d, Float.MaxValue)).map(_.toFloat).log("n1")
        n2 <- Gen
                .double(Range.linearFrac(0d, Float.MaxValue))
                .map(_.toFloat)
                .map {
                  case `n1` =>
                    val newN2 =
                      if n1 === Float.MaxValue then n1 - 1
                      else if n1 === 0f then n1 + 1
                      else n1 + 1
                    newN2

                  case n2 =>
                    n2
                }
                .log("n2")
      } yield {
        val input1 = NonNegFloat.unsafeFrom(n1)
        val input2 = NonNegFloat.unsafeFrom(n2)

        Result.diffNamed("NonNegFloat(value) =!= NonNegFloat(value)", input1, input2)(_ =!= _)
      }

    def testHash: Property =
      for {
        n <- Gen.double(Range.linearFrac(0d, Float.MaxValue)).map(_.toFloat).log("n")
      } yield {
        val input = NonNegFloat.unsafeFrom(n)

        val expected       = n.hashCode
        val actual         = input.hash
        val actualHashCode = input.##

        Result.all(
          List(
            Result.diffNamed("NonNegFloat(n).hash === n.hashCode", actual, expected)(_ === _),
            Result.diffNamed("NonNegFloat(n).## === n.hashCode", actualHashCode, expected)(_ === _),
          )
        )
      }

    def testOrder: Property =
      for {
        n1 <- Gen.double(Range.linearFrac(1000d, 1000000d)).log("n1")
        n2 <- Gen
                .frequency1(
                  25 -> Gen.double(Range.linearFrac(0d, n1 - 1d)), // <
                  25 -> Gen.double(Range.linearFrac(0d, n1)), // <=
                  25 -> Gen.double(Range.linearFrac(n1 + 1d, Float.MaxValue)), // >
                  25 -> Gen.double(Range.linearFrac(n1, Float.MaxValue)), // >=
                )
                .log("n2")
                .cover(20, "<", n2Val => n2Val < n1)
                .cover(20, "<=", n2Val => n2Val <= n1)
                .cover(20, ">", n2Val => n2Val > n1)
                .cover(20, ">=", n2Val => n2Val >= n1)
      } yield {
        val input1 = NonNegFloat.unsafeFrom(n1.toFloat)
        val input2 = NonNegFloat.unsafeFrom(n2.toFloat)

        val expected = cats.Order[Float].compare(n1.toFloat, n2.toFloat)
        val actual   = cats.Order[NonNegFloat].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[NonNegFloat].compare(input1, input2) === cats.Order[Float].compare(n1, n2)",
          actual,
          expected,
        )(_ === _)

      }

    def testOrderEqualCase: Property =
      for {
        n1 <- Gen.double(Range.linearFrac(0d, Float.MaxValue)).log("n1")
        n2 <- Gen.constant(n1).log("n2")
      } yield {
        val input1 = NonNegFloat.unsafeFrom(n1.toFloat)
        val input2 = NonNegFloat.unsafeFrom(n2.toFloat)

        val expected = 0
        val actual   = cats.Order[NonNegFloat].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[NonNegFloat].compare(NonNegFloat(n1), NonNegFloat(n2)) where n1 === n2 should return 0",
          actual,
          expected,
        )(_ === _)
      }

    def testShow: Property =
      for {
        n <- Gen.double(Range.linearFrac(0d, Float.MaxValue)).map(_.toFloat).log("n")
      } yield {
        val input = NonNegFloat.unsafeFrom(n)

        val expected = n.toString
        val actual   = input.show

        actual ==== expected
      }

  }

  object posFloatSpec {

    def tests: List[Test] = List(
      property("test   Eq[PosFloat] === case", testEq),
      property("test   Eq[PosFloat] =!= case", testEqNotEqual),
      property("test Hash[PosFloat]", testHash),
      property("test Order[PosFloat]", testOrder),
      property("test Order[PosFloat] == case", testOrderEqualCase).withTests(25),
      property("test Show[PosFloat]", testShow),
    )

    def testEq: Property =
      for {
        n <- Gen.double(Range.linearFrac(0.0001d, Float.MaxValue)).map(_.toFloat).log("n")
      } yield {
        val input = PosFloat.unsafeFrom(n)

        val expected = input
        val actual   = input

        Result.diffNamed("PosFloat(value) === PosFloat(value)", actual, expected)(_ === _)
      }

    def testEqNotEqual: Property =
      for {
        n1 <- Gen.double(Range.linearFrac(0.0001d, Float.MaxValue)).map(_.toFloat).log("n1")
        n2 <- Gen
                .double(Range.linearFrac(0.0001d, Float.MaxValue))
                .map(_.toFloat)
                .map {
                  case `n1` =>
                    val newN2 =
                      if n1 === Float.MaxValue then n1 - 1
                      else if n1 < 1f then n1 + 1
                      else n1 + 1
                    newN2

                  case n2 =>
                    n2
                }
                .log("n2")
      } yield {
        val input1 = PosFloat.unsafeFrom(n1)
        val input2 = PosFloat.unsafeFrom(n2)

        Result.diffNamed("PosFloat(value) =!= PosFloat(value)", input1, input2)(_ =!= _)
      }

    def testHash: Property =
      for {
        n <- Gen.double(Range.linearFrac(0.0001d, Float.MaxValue)).map(_.toFloat).log("n")
      } yield {
        val input = PosFloat.unsafeFrom(n)

        val expected       = n.hashCode
        val actual         = input.hash
        val actualHashCode = input.##

        Result.all(
          List(
            Result.diffNamed("PosFloat(n).hash === n.hashCode", actual, expected)(_ === _),
            Result.diffNamed("PosFloat(n).## === n.hashCode", actualHashCode, expected)(_ === _),
          )
        )
      }

    def testOrder: Property =
      for {
        n1 <- Gen.double(Range.linearFrac(1000d, 1000000d)).log("n1")
        n2 <- Gen
                .frequency1(
                  25 -> Gen.double(Range.linearFrac(0.0001d, n1 - 1d)), // <
                  25 -> Gen.double(Range.linearFrac(0.0001d, n1)), // <=
                  25 -> Gen.double(Range.linearFrac(n1 + 1d, Float.MaxValue)), // >
                  25 -> Gen.double(Range.linearFrac(n1, Float.MaxValue)), // >=
                )
                .log("n2")
                .cover(20, "<", n2Val => n2Val < n1)
                .cover(20, "<=", n2Val => n2Val <= n1)
                .cover(20, ">", n2Val => n2Val > n1)
                .cover(20, ">=", n2Val => n2Val >= n1)
      } yield {
        val input1 = PosFloat.unsafeFrom(n1.toFloat)
        val input2 = PosFloat.unsafeFrom(n2.toFloat)

        val expected = cats.Order[Float].compare(n1.toFloat, n2.toFloat)
        val actual   = cats.Order[PosFloat].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[PosFloat].compare(input1, input2) === cats.Order[Float].compare(n1, n2)",
          actual,
          expected,
        )(_ === _)

      }

    def testOrderEqualCase: Property =
      for {
        n1 <- Gen.double(Range.linearFrac(0.0001d, Float.MaxValue)).log("n1")
        n2 <- Gen.constant(n1).log("n2")
      } yield {
        val input1 = PosFloat.unsafeFrom(n1.toFloat)
        val input2 = PosFloat.unsafeFrom(n2.toFloat)

        val expected = 0
        val actual   = cats.Order[PosFloat].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[PosFloat].compare(PosFloat(n1), PosFloat(n2)) where n1 === n2 should return 0",
          actual,
          expected,
        )(_ === _)
      }

    def testShow: Property =
      for {
        n <- Gen.double(Range.linearFrac(0.0001d, Float.MaxValue)).map(_.toFloat).log("n")
      } yield {
        val input = PosFloat.unsafeFrom(n)

        val expected = n.toString
        val actual   = input.show

        actual ==== expected
      }

  }

  object nonPosFloatSpec {

    def tests: List[Test] = List(
      property("test   Eq[NonPosFloat] === case", testEq),
      property("test   Eq[NonPosFloat] =!= case", testEqNotEqual),
      property("test Hash[NonPosFloat]", testHash),
      property("test Order[NonPosFloat]", testOrder),
      property("test Order[NonPosFloat] == case", testOrderEqualCase).withTests(25),
      property("test Show[NonPosFloat]", testShow),
    )

    def testEq: Property =
      for {
        n <- Gen.double(Range.linearFrac(0d, Float.MinValue)).map(_.toFloat).log("n")
      } yield {
        val input = NonPosFloat.unsafeFrom(n)

        val expected = input
        val actual   = input

        Result.diffNamed("NonPosFloat(value) === NonPosFloat(value)", actual, expected)(_ === _)
      }

    def testEqNotEqual: Property =
      for {
        n1 <- Gen.double(Range.linearFrac(0d, Float.MinValue)).map(_.toFloat).log("n1")
        n2 <- Gen
                .double(Range.linearFrac(0d, Float.MinValue))
                .map(_.toFloat)
                .map {
                  case `n1` =>
                    val newN2 =
                      if n1 === Float.MinValue then n1 + 1
                      else if n1 === 0f then n1 - 1
                      else n1 - 1
                    newN2

                  case n2 =>
                    n2
                }
                .log("n2")
      } yield {
        val input1 = NonPosFloat.unsafeFrom(n1)
        val input2 = NonPosFloat.unsafeFrom(n2)

        Result.diffNamed("NonPosFloat(value) =!= NonPosFloat(value)", input1, input2)(_ =!= _)
      }

    def testHash: Property =
      for {
        n <- Gen.double(Range.linearFrac(0d, Float.MinValue)).map(_.toFloat).log("n")
      } yield {
        val input = NonPosFloat.unsafeFrom(n)

        val expected       = n.hashCode
        val actual         = input.hash
        val actualHashCode = input.##

        Result.all(
          List(
            Result.diffNamed("NonPosFloat(n).hash === n.hashCode", actual, expected)(_ === _),
            Result.diffNamed("NonPosFloat(n).## === n.hashCode", actualHashCode, expected)(_ === _),
          )
        )
      }

    def testOrder: Property =
      for {
        n1 <- Gen.double(Range.linearFrac(-1000000d, -1000d)).log("n1")
        n2 <- Gen
                .frequency1(
                  25 -> Gen.double(Range.linearFrac(Float.MinValue, n1 - 1d)), // <
                  25 -> Gen.double(Range.linearFrac(Float.MinValue, n1)), // <=
                  25 -> Gen.double(Range.linearFrac(n1 + 1d, 0d)), // >
                  25 -> Gen.double(Range.linearFrac(n1, 0d)), // >=
                )
                .log("n2")
                .cover(20, "<", n2Val => n2Val < n1)
                .cover(20, "<=", n2Val => n2Val <= n1)
                .cover(20, ">", n2Val => n2Val > n1)
                .cover(20, ">=", n2Val => n2Val >= n1)
      } yield {
        val input1 = NonPosFloat.unsafeFrom(n1.toFloat)
        val input2 = NonPosFloat.unsafeFrom(n2.toFloat)

        val expected = cats.Order[Float].compare(n1.toFloat, n2.toFloat)
        val actual   = cats.Order[NonPosFloat].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[NonPosFloat].compare(input1, input2) === cats.Order[Float].compare(n1, n2)",
          actual,
          expected,
        )(_ === _)

      }

    def testOrderEqualCase: Property =
      for {
        n1 <- Gen.double(Range.linearFrac(Float.MinValue, 0d)).log("n1")
        n2 <- Gen.constant(n1).log("n2")
      } yield {
        val input1 = NonPosFloat.unsafeFrom(n1.toFloat)
        val input2 = NonPosFloat.unsafeFrom(n2.toFloat)

        val expected = 0
        val actual   = cats.Order[NonPosFloat].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[NonPosFloat].compare(NonPosFloat(n1), NonPosFloat(n2)) where n1 === n2 should return 0",
          actual,
          expected,
        )(_ === _)
      }

    def testShow: Property =
      for {
        n <- Gen.double(Range.linearFrac(0d, Float.MinValue)).map(_.toFloat).log("n")
      } yield {
        val input = NonPosFloat.unsafeFrom(n)

        val expected = n.toString
        val actual   = input.show

        actual ==== expected
      }

  }

  object negDoubleSpec {

    def tests: List[Test] = List(
      property("test   Eq[NegDouble] === case", testEq),
      property("test   Eq[NegDouble] =!= case", testEqNotEqual),
      property("test Hash[NegDouble]", testHash),
      property("test Order[NegDouble]", testOrder),
      property("test Order[NegDouble] == case", testOrderEqualCase).withTests(25),
      property("test Show[NegDouble]", testShow),
    )

    def testEq: Property =
      for {
        n <- Gen.double(Range.linearFrac(-0.000001d, Double.MinValue)).log("n")
      } yield {
        val input = NegDouble.unsafeFrom(n)

        val expected = input
        val actual   = input

        Result.diffNamed("NegDouble(value) === NegDouble(value)", actual, expected)(_ === _)
      }

    def testEqNotEqual: Property =
      for {
        n1 <- Gen.double(Range.linearFrac(-0.000001d, Double.MinValue)).log("n1")
        n2 <- Gen
                .double(Range.linearFrac(-0.000001d, Double.MinValue))
                .map {
                  case `n1` =>
                    val newN2 =
                      if n1 === Double.MinValue then n1 + 1
                      else if n1 > -1d then n1 - 1
                      else n1 - 1
                    newN2

                  case n2 =>
                    n2
                }
                .log("n2")
      } yield {
        val input1 = NegDouble.unsafeFrom(n1)
        val input2 = NegDouble.unsafeFrom(n2)

        Result.diffNamed("NegDouble(value) =!= NegDouble(value)", input1, input2)(_ =!= _)
      }

    def testHash: Property =
      for {
        n <- Gen.double(Range.linearFrac(-0.000001d, Double.MinValue)).log("n")
      } yield {
        val input = NegDouble.unsafeFrom(n)

        val expected       = n.hashCode
        val actual         = input.hash
        val actualHashCode = input.##

        Result.all(
          List(
            Result.diffNamed("NegDouble(n).hash === n.hashCode", actual, expected)(_ === _),
            Result.diffNamed("NegDouble(n).## === n.hashCode", actualHashCode, expected)(_ === _),
          )
        )
      }

    def testOrder: Property =
      for {
        n1 <- Gen.double(Range.linearFrac(-1000000d, -1000d)).log("n1")
        n2 <- Gen
                .frequency1(
                  25 -> Gen.double(Range.linearFrac(Double.MinValue, n1 - 1d)), // <
                  25 -> Gen.double(Range.linearFrac(Double.MinValue, n1)), // <=
                  25 -> Gen.double(Range.linearFrac(n1 + 1d, -0.000001d)), // >
                  25 -> Gen.double(Range.linearFrac(n1, -0.000001d)), // >=
                )
                .log("n2")
                .cover(20, "<", n2Val => n2Val < n1)
                .cover(20, "<=", n2Val => n2Val <= n1)
                .cover(20, ">", n2Val => n2Val > n1)
                .cover(20, ">=", n2Val => n2Val >= n1)
      } yield {
        val input1 = NegDouble.unsafeFrom(n1)
        val input2 = NegDouble.unsafeFrom(n2)

        val expected = cats.Order[Double].compare(n1, n2)
        val actual   = cats.Order[NegDouble].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[NegDouble].compare(input1, input2) === cats.Order[Double].compare(n1, n2)",
          actual,
          expected,
        )(_ === _)

      }

    def testOrderEqualCase: Property =
      for {
        n1 <- Gen.double(Range.linearFrac(Double.MinValue, -0.000001d)).log("n1")
        n2 <- Gen.constant(n1).log("n2")
      } yield {
        val input1 = NegDouble.unsafeFrom(n1)
        val input2 = NegDouble.unsafeFrom(n2)

        val expected = 0
        val actual   = cats.Order[NegDouble].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[NegDouble].compare(NegDouble(n1), NegDouble(n2)) where n1 === n2 should return 0",
          actual,
          expected,
        )(_ === _)
      }

    def testShow: Property =
      for {
        n <- Gen.double(Range.linearFrac(-0.000001d, Double.MinValue)).log("n")
      } yield {
        val input = NegDouble.unsafeFrom(n)

        val expected = n.toString
        val actual   = input.show

        actual ==== expected
      }

  }

  object nonNegDoubleSpec {

    def tests: List[Test] = List(
      property("test   Eq[NonNegDouble] === case", testEq),
      property("test   Eq[NonNegDouble] =!= case", testEqNotEqual),
      property("test Hash[NonNegDouble]", testHash),
      property("test Order[NonNegDouble]", testOrder),
      property("test Order[NonNegDouble] == case", testOrderEqualCase).withTests(25),
      property("test Show[NonNegDouble]", testShow),
    )

    def testEq: Property =
      for {
        n <- Gen.double(Range.linearFrac(0d, Double.MaxValue)).log("n")
      } yield {
        val input = NonNegDouble.unsafeFrom(n)

        val expected = input
        val actual   = input

        Result.diffNamed("NonNegDouble(value) === NonNegDouble(value)", actual, expected)(_ === _)
      }

    def testEqNotEqual: Property =
      for {
        n1 <- Gen.double(Range.linearFrac(0d, Double.MaxValue)).log("n1")
        n2 <- Gen
                .double(Range.linearFrac(0d, Double.MaxValue))
                .map {
                  case `n1` =>
                    val newN2 =
                      if n1 === Double.MaxValue then n1 - 1
                      else if n1 === 0d then n1 + 1
                      else n1 + 1
                    newN2

                  case n2 =>
                    n2
                }
                .log("n2")
      } yield {
        val input1 = NonNegDouble.unsafeFrom(n1)
        val input2 = NonNegDouble.unsafeFrom(n2)

        Result.diffNamed("NonNegDouble(value) =!= NonNegDouble(value)", input1, input2)(_ =!= _)
      }

    def testHash: Property =
      for {
        n <- Gen.double(Range.linearFrac(0d, Double.MaxValue)).log("n")
      } yield {
        val input = NonNegDouble.unsafeFrom(n)

        val expected       = n.hashCode
        val actual         = input.hash
        val actualHashCode = input.##

        Result.all(
          List(
            Result.diffNamed("NonNegDouble(n).hash === n.hashCode", actual, expected)(_ === _),
            Result.diffNamed("NonNegDouble(n).## === n.hashCode", actualHashCode, expected)(_ === _),
          )
        )
      }

    def testOrder: Property =
      for {
        n1 <- Gen.double(Range.linearFrac(1000d, 1000000d)).log("n1")
        n2 <- Gen
                .frequency1(
                  25 -> Gen.double(Range.linearFrac(0d, n1 - 1d)), // <
                  25 -> Gen.double(Range.linearFrac(0d, n1)), // <=
                  25 -> Gen.double(Range.linearFrac(n1 + 1d, Double.MaxValue)), // >
                  25 -> Gen.double(Range.linearFrac(n1, Double.MaxValue)), // >=
                )
                .log("n2")
                .cover(20, "<", n2Val => n2Val < n1)
                .cover(20, "<=", n2Val => n2Val <= n1)
                .cover(20, ">", n2Val => n2Val > n1)
                .cover(20, ">=", n2Val => n2Val >= n1)
      } yield {
        val input1 = NonNegDouble.unsafeFrom(n1)
        val input2 = NonNegDouble.unsafeFrom(n2)

        val expected = cats.Order[Double].compare(n1, n2)
        val actual   = cats.Order[NonNegDouble].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[NonNegDouble].compare(input1, input2) === cats.Order[Double].compare(n1, n2)",
          actual,
          expected,
        )(_ === _)

      }

    def testOrderEqualCase: Property =
      for {
        n1 <- Gen.double(Range.linearFrac(0d, Double.MaxValue)).log("n1")
        n2 <- Gen.constant(n1).log("n2")
      } yield {
        val input1 = NonNegDouble.unsafeFrom(n1)
        val input2 = NonNegDouble.unsafeFrom(n2)

        val expected = 0
        val actual   = cats.Order[NonNegDouble].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[NonNegDouble].compare(NonNegDouble(n1), NonNegDouble(n2)) where n1 === n2 should return 0",
          actual,
          expected,
        )(_ === _)
      }

    def testShow: Property =
      for {
        n <- Gen.double(Range.linearFrac(0d, Double.MaxValue)).log("n")
      } yield {
        val input = NonNegDouble.unsafeFrom(n)

        val expected = n.toString
        val actual   = input.show

        actual ==== expected
      }

  }

  object posDoubleSpec {

    def tests: List[Test] = List(
      property("test   Eq[PosDouble] === case", testEq),
      property("test   Eq[PosDouble] =!= case", testEqNotEqual),
      property("test Hash[PosDouble]", testHash),
      property("test Order[PosDouble]", testOrder),
      property("test Order[PosDouble] == case", testOrderEqualCase).withTests(25),
      property("test Show[PosDouble]", testShow),
    )

    def testEq: Property =
      for {
        n <- Gen.double(Range.linearFrac(0.000001d, Double.MaxValue)).log("n")
      } yield {
        val input = PosDouble.unsafeFrom(n)

        val expected = input
        val actual   = input

        Result.diffNamed("PosDouble(value) === PosDouble(value)", actual, expected)(_ === _)
      }

    def testEqNotEqual: Property =
      for {
        n1 <- Gen.double(Range.linearFrac(0.000001d, Double.MaxValue)).log("n1")
        n2 <- Gen
                .double(Range.linearFrac(0.000001d, Double.MaxValue))
                .map {
                  case `n1` =>
                    val newN2 =
                      if n1 === Double.MaxValue then n1 - 1
                      else if n1 < 1d then n1 + 1
                      else n1 + 1
                    newN2

                  case n2 =>
                    n2
                }
                .log("n2")
      } yield {
        val input1 = PosDouble.unsafeFrom(n1)
        val input2 = PosDouble.unsafeFrom(n2)

        Result.diffNamed("PosDouble(value) =!= PosDouble(value)", input1, input2)(_ =!= _)
      }

    def testHash: Property =
      for {
        n <- Gen.double(Range.linearFrac(0.000001d, Double.MaxValue)).log("n")
      } yield {
        val input = PosDouble.unsafeFrom(n)

        val expected       = n.hashCode
        val actual         = input.hash
        val actualHashCode = input.##

        Result.all(
          List(
            Result.diffNamed("PosDouble(n).hash === n.hashCode", actual, expected)(_ === _),
            Result.diffNamed("PosDouble(n).## === n.hashCode", actualHashCode, expected)(_ === _),
          )
        )
      }

    def testOrder: Property =
      for {
        n1 <- Gen.double(Range.linearFrac(1000d, 1000000d)).log("n1")
        n2 <- Gen
                .frequency1(
                  25 -> Gen.double(Range.linearFrac(0.000001d, n1 - 1d)), // <
                  25 -> Gen.double(Range.linearFrac(0.000001d, n1)), // <=
                  25 -> Gen.double(Range.linearFrac(n1 + 1d, Double.MaxValue)), // >
                  25 -> Gen.double(Range.linearFrac(n1, Double.MaxValue)), // >=
                )
                .log("n2")
                .cover(20, "<", n2Val => n2Val < n1)
                .cover(20, "<=", n2Val => n2Val <= n1)
                .cover(20, ">", n2Val => n2Val > n1)
                .cover(20, ">=", n2Val => n2Val >= n1)
      } yield {
        val input1 = PosDouble.unsafeFrom(n1)
        val input2 = PosDouble.unsafeFrom(n2)

        val expected = cats.Order[Double].compare(n1, n2)
        val actual   = cats.Order[PosDouble].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[PosDouble].compare(input1, input2) === cats.Order[Double].compare(n1, n2)",
          actual,
          expected,
        )(_ === _)

      }

    def testOrderEqualCase: Property =
      for {
        n1 <- Gen.double(Range.linearFrac(0.000001d, Double.MaxValue)).log("n1")
        n2 <- Gen.constant(n1).log("n2")
      } yield {
        val input1 = PosDouble.unsafeFrom(n1)
        val input2 = PosDouble.unsafeFrom(n2)

        val expected = 0
        val actual   = cats.Order[PosDouble].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[PosDouble].compare(PosDouble(n1), PosDouble(n2)) where n1 === n2 should return 0",
          actual,
          expected,
        )(_ === _)
      }

    def testShow: Property =
      for {
        n <- Gen.double(Range.linearFrac(0.000001d, Double.MaxValue)).log("n")
      } yield {
        val input = PosDouble.unsafeFrom(n)

        val expected = n.toString
        val actual   = input.show

        actual ==== expected
      }

  }

  object nonPosDoubleSpec {

    def tests: List[Test] = List(
      property("test   Eq[NonPosDouble] === case", testEq),
      property("test   Eq[NonPosDouble] =!= case", testEqNotEqual),
      property("test Hash[NonPosDouble]", testHash),
      property("test Order[NonPosDouble]", testOrder),
      property("test Order[NonPosDouble] == case", testOrderEqualCase).withTests(25),
      property("test Show[NonPosDouble]", testShow),
    )

    def testEq: Property = for {
      n <- Gen.double(Range.linearFrac(0d, Double.MinValue)).log("n")
    } yield {
      val input = NonPosDouble.unsafeFrom(n)

      val expected = input
      val actual   = input

      Result.diffNamed("NonPosDouble(value) === NonPosDouble(value)", actual, expected)(_ === _)
    }

    def testEqNotEqual: Property =
      for {
        n1 <- Gen.double(Range.linearFrac(0d, Double.MinValue)).log("n1")
        n2 <- Gen
                .double(Range.linearFrac(0d, Double.MinValue))
                .map {
                  case `n1` =>
                    val newN2 =
                      if n1 === Double.MinValue then n1 + 1
                      else if n1 === 0d then n1 - 1
                      else n1 - 1
                    newN2

                  case n2 =>
                    n2
                }
                .log("n2")
      } yield {
        val input1 = NonPosDouble.unsafeFrom(n1)
        val input2 = NonPosDouble.unsafeFrom(n2)

        Result.diffNamed("NonPosDouble(value) =!= NonPosDouble(value)", input1, input2)(_ =!= _)
      }

    def testHash: Property =
      for {
        n <- Gen.double(Range.linearFrac(0d, Double.MinValue)).log("n")
      } yield {
        val input = NonPosDouble.unsafeFrom(n)

        val expected       = n.hashCode
        val actual         = input.hash
        val actualHashCode = input.##

        Result.all(
          List(
            Result.diffNamed("NonPosDouble(n).hash === n.hashCode", actual, expected)(_ === _),
            Result.diffNamed("NonPosDouble(n).## === n.hashCode", actualHashCode, expected)(_ === _),
          )
        )
      }

    def testOrder: Property =
      for {
        n1 <- Gen.double(Range.linearFrac(-1000000d, -1000d)).log("n1")
        n2 <- Gen
                .frequency1(
                  25 -> Gen.double(Range.linearFrac(Double.MinValue, n1 - 1d)), // <
                  25 -> Gen.double(Range.linearFrac(Double.MinValue, n1)), // <=
                  25 -> Gen.double(Range.linearFrac(n1 + 1d, 0d)), // >
                  25 -> Gen.double(Range.linearFrac(n1, 0d)), // >=
                )
                .log("n2")
                .cover(20, "<", n2Val => n2Val < n1)
                .cover(20, "<=", n2Val => n2Val <= n1)
                .cover(20, ">", n2Val => n2Val > n1)
                .cover(20, ">=", n2Val => n2Val >= n1)
      } yield {
        val input1 = NonPosDouble.unsafeFrom(n1)
        val input2 = NonPosDouble.unsafeFrom(n2)

        val expected = cats.Order[Double].compare(n1, n2)
        val actual   = cats.Order[NonPosDouble].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[NonPosDouble].compare(input1, input2) === cats.Order[Double].compare(n1, n2)",
          actual,
          expected,
        )(_ === _)

      }

    def testOrderEqualCase: Property =
      for {
        n1 <- Gen.double(Range.linearFrac(Double.MinValue, 0d)).log("n1")
        n2 <- Gen.constant(n1).log("n2")
      } yield {
        val input1 = NonPosDouble.unsafeFrom(n1)
        val input2 = NonPosDouble.unsafeFrom(n2)

        val expected = 0
        val actual   = cats.Order[NonPosDouble].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[NonPosDouble].compare(NonPosDouble(n1), NonPosDouble(n2)) where n1 === n2 should return 0",
          actual,
          expected,
        )(_ === _)
      }

    def testShow: Property =
      for {
        n <- Gen.double(Range.linearFrac(0d, Double.MinValue)).log("n")
      } yield {
        val input = NonPosDouble.unsafeFrom(n)

        val expected = n.toString
        val actual   = input.show

        actual ==== expected
      }

  }

  object negBigIntSpec {

    def tests: List[Test] = List(
      property("test   Eq[NegBigInt] === case", testEq),
      property("test   Eq[NegBigInt] =!= case", testEqNotEqual),
      property("test Hash[NegBigInt]", testHash),
      property("test Order[NegBigInt]", testOrder),
      property("test Order[NegBigInt] == case", testOrderEqualCase).withTests(25),
      property("test Show[NegBigInt]", testShow),
    )

    def testEq: Property =
      for {
        n <- Gen.long(Range.linear(-1L, Long.MinValue)).map(BigInt(_)).log("n")
      } yield {
        val input = NegBigInt.unsafeFrom(n)

        val expected = input
        val actual   = input

        Result.diffNamed("NegBigInt(value) === NegBigInt(value)", actual, expected)(_ === _)
      }

    def testEqNotEqual: Property =
      for {
        n1 <- Gen.long(Range.linear(-1L, Long.MinValue)).map(BigInt(_)).log("n1")
        n2 <- Gen
                .long(Range.linear(-1L, Long.MinValue))
                .map(BigInt(_))
                .map {
                  case `n1` =>
                    val newN2 =
                      if n1.toLong === Long.MinValue then n1 + 1
                      else if n1.toLong === -1L then n1 - 1
                      else n1 - 1
                    newN2

                  case n2 =>
                    n2
                }
                .log("n2")
      } yield {
        val input1 = NegBigInt.unsafeFrom(n1)
        val input2 = NegBigInt.unsafeFrom(n2)

        Result.diffNamed("NegBigInt(value) =!= NegBigInt(value)", input1, input2)(_ =!= _)
      }

    def testHash: Property =
      for {
        n <- Gen.long(Range.linear(-1L, Long.MinValue)).map(BigInt(_)).log("n")
      } yield {
        val input = NegBigInt.unsafeFrom(n)

        val expected       = n.hashCode
        val actual         = input.hash
        val actualHashCode = input.##

        Result.all(
          List(
            Result.diffNamed("NegBigInt(n).hash === n.hashCode", actual, expected)(_ === _),
            Result.diffNamed("NegBigInt(n).## === n.hashCode", actualHashCode, expected)(_ === _),
          )
        )
      }

    def testOrder: Property =
      for {
        n1 <- Gen.long(Range.linear(Long.MinValue - (Long.MinValue / 3), Long.MinValue - ((Long.MinValue / 3) << 1))).log("n1")
        n2 <- Gen
                .frequency1(
                  25 -> Gen.long(Range.linear(Long.MinValue, n1 - 1)), // <
                  25 -> Gen.long(Range.linear(Long.MinValue, n1)), // <=
                  25 -> Gen.long(Range.linear(n1 + 1, -1L)), // >
                  25 -> Gen.long(Range.linear(n1, -1L)), // >=
                )
                .log("n2")
                .cover(20, "<", n2Val => n2Val < n1)
                .cover(20, "<=", n2Val => n2Val <= n1)
                .cover(20, ">", n2Val => n2Val > n1)
                .cover(20, ">=", n2Val => n2Val >= n1)
      } yield {
        val input1 = NegBigInt.unsafeFrom(BigInt(n1))
        val input2 = NegBigInt.unsafeFrom(BigInt(n2))

        val expected = cats.Order[BigInt].compare(BigInt(n1), BigInt(n2))
        val actual   = cats.Order[NegBigInt].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[NegBigInt].compare(input1, input2) === cats.Order[BigInt].compare(n1, n2)",
          actual,
          expected,
        )(_ === _)

      }

    def testOrderEqualCase: Property =
      for {
        n1 <- Gen.long(Range.linear(Long.MinValue, -1L)).log("n1")
        n2 <- Gen.constant(n1).log("n2")
      } yield {
        val input1 = NegBigInt.unsafeFrom(BigInt(n1))
        val input2 = NegBigInt.unsafeFrom(BigInt(n2))

        val expected = 0
        val actual   = cats.Order[NegBigInt].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[NegBigInt].compare(NegBigInt(n1), NegBigInt(n2)) where n1 === n2 should return 0",
          actual,
          expected,
        )(_ === _)
      }

    def testShow: Property =
      for {
        n <- Gen.long(Range.linear(-1L, Long.MinValue)).map(BigInt(_)).log("n")
      } yield {
        val input = NegBigInt.unsafeFrom(n)

        val expected = n.toString
        val actual   = input.show

        actual ==== expected
      }

  }

  object nonNegBigIntSpec {
    def tests: List[Test] = List(
      property("test   Eq[NonNegBigInt] === case", testEq),
      property("test   Eq[NonNegBigInt] =!= case", testEqNotEqual),
      property("test Hash[NonNegBigInt]", testHash),
      property("test Order[NonNegBigInt]", testOrder),
      property("test Order[NonNegBigInt] == case", testOrderEqualCase).withTests(25),
      property("test Show[NonNegBigInt]", testShow),
    )

    def testEq: Property = for {
      n <- Gen.long(Range.linear(0L, Long.MaxValue)).map(BigInt(_)).log("n")
    } yield {
      val input = NonNegBigInt.unsafeFrom(n)

      val expected = input
      val actual   = input

      Result.diffNamed("NonNegBigInt(value) === NonNegBigInt(value)", actual, expected)(_ === _)
    }

    def testEqNotEqual: Property =
      for {
        n1 <- Gen.long(Range.linear(0L, Long.MaxValue)).map(BigInt(_)).log("n1")
        n2 <- Gen
                .long(Range.linear(0L, Long.MaxValue))
                .map(BigInt(_))
                .map {
                  case `n1` =>
                    val newN2 =
                      if n1.toLong === Long.MaxValue then n1 - 1
                      else if n1.toLong === 0L then n1 + 1
                      else n1 + 1
                    newN2

                  case n2 =>
                    n2
                }
                .log("n2")
      } yield {
        val input1 = NonNegBigInt.unsafeFrom(n1)
        val input2 = NonNegBigInt.unsafeFrom(n2)

        Result.diffNamed("NonNegBigInt(value) =!= NonNegBigInt(value)", input1, input2)(_ =!= _)
      }

    def testHash: Property =
      for {
        n <- Gen.long(Range.linear(0L, Long.MaxValue)).map(BigInt(_)).log("n")
      } yield {
        val input = NonNegBigInt.unsafeFrom(n)

        val expected       = n.hashCode
        val actual         = input.hash
        val actualHashCode = input.##

        Result.all(
          List(
            Result.diffNamed("NonNegBigInt(n).hash === n.hashCode", actual, expected)(_ === _),
            Result.diffNamed("NonNegBigInt(n).## === n.hashCode", actualHashCode, expected)(_ === _),
          )
        )
      }

    def testOrder: Property =
      for {
        n1 <- Gen.long(Range.linear(Long.MaxValue / 3, (Long.MaxValue / 3) << 1)).log("n1")
        n2 <- Gen
                .frequency1(
                  25 -> Gen.long(Range.linear(0L, n1 - 1)), // <
                  25 -> Gen.long(Range.linear(0L, n1)), // <=
                  25 -> Gen.long(Range.linear(n1 + 1, Long.MaxValue)), // >
                  25 -> Gen.long(Range.linear(n1, Long.MaxValue)), // >=
                )
                .log("n2")
                .cover(20, "<", n2Val => n2Val < n1)
                .cover(20, "<=", n2Val => n2Val <= n1)
                .cover(20, ">", n2Val => n2Val > n1)
                .cover(20, ">=", n2Val => n2Val >= n1)
      } yield {
        val input1 = NonNegBigInt.unsafeFrom(BigInt(n1))
        val input2 = NonNegBigInt.unsafeFrom(BigInt(n2))

        val expected = cats.Order[BigInt].compare(BigInt(n1), BigInt(n2))
        val actual   = cats.Order[NonNegBigInt].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[NonNegBigInt].compare(input1, input2) === cats.Order[BigInt].compare(n1, n2)",
          actual,
          expected,
        )(_ === _)

      }

    def testOrderEqualCase: Property =
      for {
        n1 <- Gen.long(Range.linear(0L, Long.MaxValue)).log("n1")
        n2 <- Gen.constant(n1).log("n2")
      } yield {
        val input1 = NonNegBigInt.unsafeFrom(BigInt(n1))
        val input2 = NonNegBigInt.unsafeFrom(BigInt(n2))

        val expected = 0
        val actual   = cats.Order[NonNegBigInt].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[NonNegBigInt].compare(NonNegBigInt(n1), NonNegBigInt(n2)) where n1 === n2 should return 0",
          actual,
          expected,
        )(_ === _)
      }

    def testShow: Property =
      for {
        n <- Gen.long(Range.linear(0L, Long.MaxValue)).map(BigInt(_)).log("n")
      } yield {
        val input = NonNegBigInt.unsafeFrom(n)

        val expected = n.toString
        val actual   = input.show

        actual ==== expected
      }

  }

  object posBigIntSpec {

    def tests: List[Test] = List(
      property("test   Eq[PosBigInt] === case", testEq),
      property("test   Eq[PosBigInt] =!= case", testEqNotEqual),
      property("test Hash[PosBigInt]", testHash),
      property("test Order[PosBigInt]", testOrder),
      property("test Order[PosBigInt] == case", testOrderEqualCase).withTests(25),
      property("test Show[PosBigInt]", testShow),
    )

    def testEq: Property =
      for {
        n <- Gen.long(Range.linear(1L, Long.MaxValue)).map(BigInt(_)).log("n")
      } yield {
        val input = PosBigInt.unsafeFrom(n)

        val expected = input
        val actual   = input

        Result.diffNamed("PosBigInt(value) === PosBigInt(value)", actual, expected)(_ === _)
      }

    def testEqNotEqual: Property =
      for {
        n1 <- Gen.long(Range.linear(1L, Long.MaxValue)).map(BigInt(_)).log("n1")
        n2 <- Gen
                .long(Range.linear(1L, Long.MaxValue))
                .map(BigInt(_))
                .map {
                  case `n1` =>
                    val newN2 =
                      if n1.toLong === Long.MaxValue then n1 - 1
                      else if n1.toLong === 1L then n1 + 1
                      else n1 + 1
                    newN2

                  case n2 =>
                    n2
                }
                .log("n2")
      } yield {
        val input1 = PosBigInt.unsafeFrom(n1)
        val input2 = PosBigInt.unsafeFrom(n2)

        Result.diffNamed("PosBigInt(value) =!= PosBigInt(value)", input1, input2)(_ =!= _)
      }

    def testHash: Property =
      for {
        n <- Gen.long(Range.linear(1L, Long.MaxValue)).map(BigInt(_)).log("n")
      } yield {
        val input = PosBigInt.unsafeFrom(n)

        val expected       = n.hashCode
        val actual         = input.hash
        val actualHashCode = input.##

        Result.all(
          List(
            Result.diffNamed("PosBigInt(n).hash === n.hashCode", actual, expected)(_ === _),
            Result.diffNamed("PosBigInt(n).## === n.hashCode", actualHashCode, expected)(_ === _),
          )
        )
      }

    def testOrder: Property =
      for {
        n1 <- Gen.long(Range.linear(Long.MaxValue / 3, (Long.MaxValue / 3) << 1)).log("n1")
        n2 <- Gen
                .frequency1(
                  25 -> Gen.long(Range.linear(1L, n1 - 1)), // <
                  25 -> Gen.long(Range.linear(1L, n1)), // <=
                  25 -> Gen.long(Range.linear(n1 + 1, Long.MaxValue)), // >
                  25 -> Gen.long(Range.linear(n1, Long.MaxValue)), // >=
                )
                .log("n2")
                .cover(20, "<", n2Val => n2Val < n1)
                .cover(20, "<=", n2Val => n2Val <= n1)
                .cover(20, ">", n2Val => n2Val > n1)
                .cover(20, ">=", n2Val => n2Val >= n1)
      } yield {
        val input1 = PosBigInt.unsafeFrom(BigInt(n1))
        val input2 = PosBigInt.unsafeFrom(BigInt(n2))

        val expected = cats.Order[BigInt].compare(BigInt(n1), BigInt(n2))
        val actual   = cats.Order[PosBigInt].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[PosBigInt].compare(input1, input2) === cats.Order[BigInt].compare(n1, n2)",
          actual,
          expected,
        )(_ === _)

      }

    def testOrderEqualCase: Property =
      for {
        n1 <- Gen.long(Range.linear(1L, Long.MaxValue)).log("n1")
        n2 <- Gen.constant(n1).log("n2")
      } yield {
        val input1 = PosBigInt.unsafeFrom(BigInt(n1))
        val input2 = PosBigInt.unsafeFrom(BigInt(n2))

        val expected = 0
        val actual   = cats.Order[PosBigInt].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[PosBigInt].compare(PosBigInt(n1), PosBigInt(n2)) where n1 === n2 should return 0",
          actual,
          expected,
        )(_ === _)
      }

    def testShow: Property =
      for {
        n <- Gen.long(Range.linear(1L, Long.MaxValue)).map(BigInt(_)).log("n")
      } yield {
        val input = PosBigInt.unsafeFrom(n)

        val expected = n.toString
        val actual   = input.show

        actual ==== expected
      }

  }

  object nonPosBigIntSpec {

    def tests: List[Test] = List(
      property("test   Eq[NonPosBigInt] === case", testEq),
      property("test   Eq[NonPosBigInt] =!= case", testEqNotEqual),
      property("test Hash[NonPosBigInt]", testHash),
      property("test Order[NonPosBigInt]", testOrder),
      property("test Order[NonPosBigInt] == case", testOrderEqualCase).withTests(25),
      property("test Show[NonPosBigInt]", testShow),
    )

    def testEq: Property =
      for {
        n <- Gen.long(Range.linear(0L, Long.MinValue)).map(BigInt(_)).log("n")
      } yield {
        val input = NonPosBigInt.unsafeFrom(n)

        val expected = input
        val actual   = input

        Result.diffNamed("NonPosBigInt(value) === NonPosBigInt(value)", actual, expected)(_ === _)
      }

    def testEqNotEqual: Property =
      for {
        n1 <- Gen.long(Range.linear(0L, Long.MinValue)).map(BigInt(_)).log("n1")
        n2 <- Gen
                .long(Range.linear(0L, Long.MinValue))
                .map(BigInt(_))
                .map {
                  case `n1` =>
                    val newN2 =
                      if n1.toLong === Long.MinValue then n1 + 1
                      else if n1.toLong === 0L then n1 - 1
                      else n1 - 1
                    newN2

                  case n2 =>
                    n2
                }
                .log("n2")
      } yield {
        val input1 = NonPosBigInt.unsafeFrom(n1)
        val input2 = NonPosBigInt.unsafeFrom(n2)

        Result.diffNamed("NonPosBigInt(value) =!= NonPosBigInt(value)", input1, input2)(_ =!= _)
      }

    def testHash: Property =
      for {
        n <- Gen.long(Range.linear(0L, Long.MinValue)).map(BigInt(_)).log("n")
      } yield {
        val input = NonPosBigInt.unsafeFrom(n)

        val expected       = n.hashCode
        val actual         = input.hash
        val actualHashCode = input.##

        Result.all(
          List(
            Result.diffNamed("NonPosBigInt(n).hash === n.hashCode", actual, expected)(_ === _),
            Result.diffNamed("NonPosBigInt(n).## === n.hashCode", actualHashCode, expected)(_ === _),
          )
        )
      }

    def testOrder: Property =
      for {
        n1 <- Gen.long(Range.linear(Long.MinValue - (Long.MinValue / 3), Long.MinValue - ((Long.MinValue / 3) << 1))).log("n1")
        n2 <- Gen
                .frequency1(
                  25 -> Gen.long(Range.linear(Long.MinValue, n1 - 1)), // <
                  25 -> Gen.long(Range.linear(Long.MinValue, n1)), // <=
                  25 -> Gen.long(Range.linear(n1 + 1, 0L)), // >
                  25 -> Gen.long(Range.linear(n1, 0L)), // >=
                )
                .log("n2")
                .cover(20, "<", n2Val => n2Val < n1)
                .cover(20, "<=", n2Val => n2Val <= n1)
                .cover(20, ">", n2Val => n2Val > n1)
                .cover(20, ">=", n2Val => n2Val >= n1)
      } yield {
        val input1 = NonPosBigInt.unsafeFrom(BigInt(n1))
        val input2 = NonPosBigInt.unsafeFrom(BigInt(n2))

        val expected = cats.Order[BigInt].compare(BigInt(n1), BigInt(n2))
        val actual   = cats.Order[NonPosBigInt].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[NonPosBigInt].compare(input1, input2) === cats.Order[BigInt].compare(n1, n2)",
          actual,
          expected,
        )(_ === _)

      }

    def testOrderEqualCase: Property =
      for {
        n1 <- Gen.long(Range.linear(Long.MinValue, 0L)).log("n1")
        n2 <- Gen.constant(n1).log("n2")
      } yield {
        val input1 = NonPosBigInt.unsafeFrom(BigInt(n1))
        val input2 = NonPosBigInt.unsafeFrom(BigInt(n2))

        val expected = 0
        val actual   = cats.Order[NonPosBigInt].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[NonPosBigInt].compare(NonPosBigInt(n1), NonPosBigInt(n2)) where n1 === n2 should return 0",
          actual,
          expected,
        )(_ === _)
      }

    def testShow: Property =
      for {
        n <- Gen.long(Range.linear(0L, Long.MinValue)).map(BigInt(_)).log("n")
      } yield {
        val input = NonPosBigInt.unsafeFrom(n)

        val expected = n.toString
        val actual   = input.show

        actual ==== expected
      }

  }

  val BigDecimalDoubleMinValue = BigDecimal(Double.MinValue)
  val BigDecimalDoubleMaxValue = BigDecimal(Double.MaxValue)

  val BigDecimalZero   = BigDecimal(0)
  val BigDecimalNegOne = BigDecimal(-1)
  val BigDecimalPosOne = BigDecimal(1)

  object negBigDecimalSpec {

    def tests: List[Test] = List(
      property("test   Eq[NegBigDecimal] === case", testEq),
      property("test   Eq[NegBigDecimal] =!= case", testEqNotEqual),
      property("test Hash[NegBigDecimal]", testHash),
      property("test Order[NegBigDecimal]", testOrder),
      property("test Order[NegBigDecimal] == case", testOrderEqualCase).withTests(25),
      property("test Show[NegBigDecimal]", testShow),
    )

    def testEq: Property = for {
      n <- Gen.double(Range.linearFrac(-0.0000001d, Double.MinValue)).map(BigDecimal(_)).log("n")
    } yield {
      val input = NegBigDecimal.unsafeFrom(n)

      val expected = input
      val actual   = input

      Result.diffNamed("NegBigDecimal(value) === NegBigDecimal(value)", actual, expected)(_ === _)
    }

    def testEqNotEqual: Property =
      for {
        n1 <- Gen.double(Range.linearFrac(-0.0000001d, Double.MinValue)).map(BigDecimal(_)).log("n1")
        n2 <- Gen
                .double(Range.linearFrac(-0.0000001d, Double.MinValue))
                .map(BigDecimal(_))
                .map {
                  case `n1` =>
                    val newN2 =
                      if n1 === BigDecimalDoubleMinValue then n1 + 1
                      else if n1 > BigDecimalNegOne then n1 - 1
                      else n1 - 1
                    newN2

                  case n2 =>
                    n2
                }
                .log("n2")
      } yield {
        val input1 = NegBigDecimal.unsafeFrom(n1)
        val input2 = NegBigDecimal.unsafeFrom(n2)

        Result.diffNamed("NegBigDecimal(value) =!= NegBigDecimal(value)", input1, input2)(_ =!= _)
      }

    def testHash: Property =
      for {
        n <- Gen.double(Range.linearFrac(-0.0000001d, Double.MinValue)).map(BigDecimal(_)).log("n")
      } yield {
        val input = NegBigDecimal.unsafeFrom(n)

        val expected       = n.hashCode
        val actual         = input.hash
        val actualHashCode = input.##

        Result.all(
          List(
            Result.diffNamed("NegBigDecimal(n).hash === n.hashCode", actual, expected)(_ === _),
            Result.diffNamed("NegBigDecimal(n).## === n.hashCode", actualHashCode, expected)(_ === _),
          )
        )
      }

    def testOrder: Property =
      for {
        n1 <- Gen.double(Range.linearFrac(-1000000d, -1000d)).log("n1")
        n2 <- Gen
                .frequency1(
                  25 -> Gen.double(Range.linearFrac(Double.MinValue, n1 - 1d)), // <
                  25 -> Gen.double(Range.linearFrac(Double.MinValue, n1)), // <=
                  25 -> Gen.double(Range.linearFrac(n1 + 1d, -0.0000001d)), // >
                  25 -> Gen.double(Range.linearFrac(n1, -0.0000001d)), // >=
                )
                .log("n2")
                .cover(20, "<", n2Val => n2Val < n1)
                .cover(20, "<=", n2Val => n2Val <= n1)
                .cover(20, ">", n2Val => n2Val > n1)
                .cover(20, ">=", n2Val => n2Val >= n1)
      } yield {
        val input1 = NegBigDecimal.unsafeFrom(BigDecimal(n1))
        val input2 = NegBigDecimal.unsafeFrom(BigDecimal(n2))

        val expected = cats.Order[BigDecimal].compare(BigDecimal(n1), BigDecimal(n2))
        val actual   = cats.Order[NegBigDecimal].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[NegBigDecimal].compare(input1, input2) === cats.Order[BigDecimal].compare(n1, n2)",
          actual,
          expected,
        )(_ === _)

      }

    def testOrderEqualCase: Property =
      for {
        n1 <- Gen.double(Range.linearFrac(Double.MinValue, -0.0000001d)).log("n1")
        n2 <- Gen.constant(n1).log("n2")
      } yield {
        val input1 = NegBigDecimal.unsafeFrom(BigDecimal(n1))
        val input2 = NegBigDecimal.unsafeFrom(BigDecimal(n2))

        val expected = 0
        val actual   = cats.Order[NegBigDecimal].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[NegBigDecimal].compare(NegBigDecimal(n1), NegBigDecimal(n2)) where n1 === n2 should return 0",
          actual,
          expected,
        )(_ === _)
      }

    def testShow: Property =
      for {
        n <- Gen.double(Range.linearFrac(-0.0000001d, Double.MinValue)).map(BigDecimal(_)).log("n")
      } yield {
        val input = NegBigDecimal.unsafeFrom(n)

        val expected = n.toString
        val actual   = input.show

        actual ==== expected
      }

  }

  object nonNegBigDecimalSpec {
    def tests: List[Test] = List(
      property("test   Eq[NonNegBigDecimal] === case", testEq),
      property("test   Eq[NonNegBigDecimal] =!= case", testEqNotEqual),
      property("test Hash[NonNegBigDecimal]", testHash),
      property("test Order[NonNegBigDecimal]", testOrder),
      property("test Order[NonNegBigDecimal] == case", testOrderEqualCase).withTests(25),
      property("test Show[NonNegBigDecimal]", testShow),
    )

    def testEq: Property =
      for {
        n <- Gen.double(Range.linearFrac(0d, Double.MaxValue)).map(BigDecimal(_)).log("n")
      } yield {
        val input = NonNegBigDecimal.unsafeFrom(n)

        val expected = input
        val actual   = input

        Result.diffNamed("NonNegBigDecimal(value) === NonNegBigDecimal(value)", actual, expected)(_ === _)
      }

    def testEqNotEqual: Property =
      for {
        n1 <- Gen.double(Range.linearFrac(0d, Double.MaxValue)).map(BigDecimal(_)).log("n1")
        n2 <- Gen
                .double(Range.linearFrac(0d, Double.MaxValue))
                .map(BigDecimal(_))
                .map {
                  case `n1` =>
                    val newN2 =
                      if n1 === BigDecimalDoubleMaxValue then n1 - 1
                      else if n1 === BigDecimalZero then n1 + 1
                      else n1 + 1
                    newN2

                  case n2 =>
                    n2
                }
                .log("n2")
      } yield {
        val input1 = NonNegBigDecimal.unsafeFrom(n1)
        val input2 = NonNegBigDecimal.unsafeFrom(n2)

        Result.diffNamed("NonNegBigDecimal(value) =!= NonNegBigDecimal(value)", input1, input2)(_ =!= _)
      }

    def testHash: Property =
      for {
        n <- Gen.double(Range.linearFrac(0d, Double.MaxValue)).map(BigDecimal(_)).log("n")
      } yield {
        val input = NonNegBigDecimal.unsafeFrom(n)

        val expected       = n.hashCode
        val actual         = input.hash
        val actualHashCode = input.##

        Result.all(
          List(
            Result.diffNamed("NonNegBigDecimal(n).hash === n.hashCode", actual, expected)(_ === _),
            Result.diffNamed("NonNegBigDecimal(n).## === n.hashCode", actualHashCode, expected)(_ === _),
          )
        )
      }

    def testOrder: Property =
      for {
        n1 <- Gen.double(Range.linearFrac(1000d, 1000000d)).log("n1")
        n2 <- Gen
                .frequency1(
                  25 -> Gen.double(Range.linearFrac(0d, n1 - 1d)), // <
                  25 -> Gen.double(Range.linearFrac(0d, n1)), // <=
                  25 -> Gen.double(Range.linearFrac(n1 + 1d, Double.MaxValue)), // >
                  25 -> Gen.double(Range.linearFrac(n1, Double.MaxValue)), // >=
                )
                .log("n2")
                .cover(20, "<", n2Val => n2Val < n1)
                .cover(20, "<=", n2Val => n2Val <= n1)
                .cover(20, ">", n2Val => n2Val > n1)
                .cover(20, ">=", n2Val => n2Val >= n1)
      } yield {
        val input1 = NonNegBigDecimal.unsafeFrom(BigDecimal(n1))
        val input2 = NonNegBigDecimal.unsafeFrom(BigDecimal(n2))

        val expected = cats.Order[BigDecimal].compare(BigDecimal(n1), BigDecimal(n2))
        val actual   = cats.Order[NonNegBigDecimal].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[NonNegBigDecimal].compare(input1, input2) === cats.Order[BigDecimal].compare(n1, n2)",
          actual,
          expected,
        )(_ === _)

      }

    def testOrderEqualCase: Property =
      for {
        n1 <- Gen.double(Range.linearFrac(0d, Double.MaxValue)).log("n1")
        n2 <- Gen.constant(n1).log("n2")
      } yield {
        val input1 = NonNegBigDecimal.unsafeFrom(BigDecimal(n1))
        val input2 = NonNegBigDecimal.unsafeFrom(BigDecimal(n2))

        val expected = 0
        val actual   = cats.Order[NonNegBigDecimal].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[NonNegBigDecimal].compare(NonNegBigDecimal(n1), NonNegBigDecimal(n2)) where n1 === n2 should return 0",
          actual,
          expected,
        )(_ === _)
      }

    def testShow: Property =
      for {
        n <- Gen.double(Range.linearFrac(0d, Double.MaxValue)).map(BigDecimal(_)).log("n")
      } yield {
        val input = NonNegBigDecimal.unsafeFrom(n)

        val expected = n.toString
        val actual   = input.show

        actual ==== expected
      }

  }

  object posBigDecimalSpec {

    def tests: List[Test] = List(
      property("test   Eq[PosBigDecimal] === case", testEq),
      property("test   Eq[PosBigDecimal] =!= case", testEqNotEqual),
      property("test Hash[PosBigDecimal]", testHash),
      property("test Order[PosBigDecimal]", testOrder),
      property("test Order[PosBigDecimal] == case", testOrderEqualCase).withTests(25),
      property("test Show[PosBigDecimal]", testShow),
    )

    def testEq: Property =
      for {
        n <- Gen.double(Range.linearFrac(0.0000001d, Double.MaxValue)).map(BigDecimal(_)).log("n")
      } yield {
        val input = PosBigDecimal.unsafeFrom(n)

        val expected = input
        val actual   = input

        Result.diffNamed("PosBigDecimal(value) === PosBigDecimal(value)", actual, expected)(_ === _)
      }

    def testEqNotEqual: Property =
      for {
        n1 <- Gen.double(Range.linearFrac(0.0000001d, Double.MaxValue)).map(BigDecimal(_)).log("n1")
        n2 <- Gen
                .double(Range.linearFrac(0.0000001d, Double.MaxValue))
                .map(BigDecimal(_))
                .map {
                  case `n1` =>
                    val newN2 =
                      if n1 === BigDecimalDoubleMaxValue then n1 - 1
                      else if n1 < BigDecimalPosOne then n1 + 1
                      else n1 + 1
                    newN2

                  case n2 =>
                    n2
                }
                .log("n2")
      } yield {
        val input1 = PosBigDecimal.unsafeFrom(n1)
        val input2 = PosBigDecimal.unsafeFrom(n2)

        Result.diffNamed("PosBigDecimal(value) =!= PosBigDecimal(value)", input1, input2)(_ =!= _)
      }

    def testHash: Property =
      for {
        n <- Gen.double(Range.linearFrac(0.0000001d, Double.MaxValue)).map(BigDecimal(_)).log("n")
      } yield {
        val input = PosBigDecimal.unsafeFrom(n)

        val expected       = n.hashCode
        val actual         = input.hash
        val actualHashCode = input.##

        Result.all(
          List(
            Result.diffNamed("PosBigDecimal(n).hash === n.hashCode", actual, expected)(_ === _),
            Result.diffNamed("PosBigDecimal(n).## === n.hashCode", actualHashCode, expected)(_ === _),
          )
        )
      }

    def testOrder: Property =
      for {
        n1 <- Gen.double(Range.linearFrac(1000d, 1000000d)).log("n1")
        n2 <- Gen
                .frequency1(
                  25 -> Gen.double(Range.linearFrac(0.0000001d, n1 - 1d)), // <
                  25 -> Gen.double(Range.linearFrac(0.0000001d, n1)), // <=
                  25 -> Gen.double(Range.linearFrac(n1 + 1d, Double.MaxValue)), // >
                  25 -> Gen.double(Range.linearFrac(n1, Double.MaxValue)), // >=
                )
                .log("n2")
                .cover(20, "<", n2Val => n2Val < n1)
                .cover(20, "<=", n2Val => n2Val <= n1)
                .cover(20, ">", n2Val => n2Val > n1)
                .cover(20, ">=", n2Val => n2Val >= n1)
      } yield {
        val input1 = PosBigDecimal.unsafeFrom(BigDecimal(n1))
        val input2 = PosBigDecimal.unsafeFrom(BigDecimal(n2))

        val expected = cats.Order[BigDecimal].compare(BigDecimal(n1), BigDecimal(n2))
        val actual   = cats.Order[PosBigDecimal].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[PosBigDecimal].compare(input1, input2) === cats.Order[BigDecimal].compare(n1, n2)",
          actual,
          expected,
        )(_ === _)

      }

    def testOrderEqualCase: Property =
      for {
        n1 <- Gen.double(Range.linearFrac(0.0000001d, Double.MaxValue)).log("n1")
        n2 <- Gen.constant(n1).log("n2")
      } yield {
        val input1 = PosBigDecimal.unsafeFrom(BigDecimal(n1))
        val input2 = PosBigDecimal.unsafeFrom(BigDecimal(n2))

        val expected = 0
        val actual   = cats.Order[PosBigDecimal].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[PosBigDecimal].compare(PosBigDecimal(n1), PosBigDecimal(n2)) where n1 === n2 should return 0",
          actual,
          expected,
        )(_ === _)
      }

    def testShow: Property =
      for {
        n <- Gen.double(Range.linearFrac(0.0000001d, Double.MaxValue)).map(BigDecimal(_)).log("n")
      } yield {
        val input = PosBigDecimal.unsafeFrom(n)

        val expected = n.toString
        val actual   = input.show

        actual ==== expected
      }

  }

  object nonPosBigDecimalSpec {

    def tests: List[Test] = List(
      property("test   Eq[NonPosBigDecimal] === case", testEq),
      property("test   Eq[NonPosBigDecimal] =!= case", testEqNotEqual),
      property("test Hash[NonPosBigDecimal]", testHash),
      property("test Order[NonPosBigDecimal]", testOrder),
      property("test Order[NonPosBigDecimal] == case", testOrderEqualCase).withTests(25),
      property("test Show[NonPosBigDecimal]", testShow),
    )

    def testEq: Property =
      for {
        n <- Gen.double(Range.linearFrac(0d, Double.MinValue)).map(BigDecimal(_)).log("n")
      } yield {
        val input = NonPosBigDecimal.unsafeFrom(n)

        val expected = input
        val actual   = input

        Result.diffNamed("NonPosBigDecimal(value) === NonPosBigDecimal(value)", actual, expected)(_ === _)
      }

    def testEqNotEqual: Property =
      for {
        n1 <- Gen.double(Range.linearFrac(0d, Double.MinValue)).map(BigDecimal(_)).log("n1")
        n2 <- Gen
                .double(Range.linearFrac(0d, Double.MinValue))
                .map(BigDecimal(_))
                .map {
                  case `n1` =>
                    val newN2 =
                      if n1 === BigDecimalDoubleMinValue then n1 + 1
                      else if n1 === BigDecimalZero then n1 - 1
                      else n1 - 1
                    newN2

                  case n2 =>
                    n2
                }
                .log("n2")
      } yield {
        val input1 = NonPosBigDecimal.unsafeFrom(n1)
        val input2 = NonPosBigDecimal.unsafeFrom(n2)

        Result.diffNamed("NonPosBigDecimal(value) =!= NonPosBigDecimal(value)", input1, input2)(_ =!= _)
      }

    def testHash: Property =
      for {
        n <- Gen.double(Range.linearFrac(0d, Double.MinValue)).map(BigDecimal(_)).log("n")
      } yield {
        val input = NonPosBigDecimal.unsafeFrom(n)

        val expected       = n.hashCode
        val actual         = input.hash
        val actualHashCode = input.##

        Result.all(
          List(
            Result.diffNamed("NonPosBigDecimal(n).hash === n.hashCode", actual, expected)(_ === _),
            Result.diffNamed("NonPosBigDecimal(n).## === n.hashCode", actualHashCode, expected)(_ === _),
          )
        )
      }

    def testOrder: Property =
      for {
        n1 <- Gen.double(Range.linearFrac(-1000000d, -1000d)).log("n1")
        n2 <- Gen
                .frequency1(
                  25 -> Gen.double(Range.linearFrac(Double.MinValue, n1 - 1d)), // <
                  25 -> Gen.double(Range.linearFrac(Double.MinValue, n1)), // <=
                  25 -> Gen.double(Range.linearFrac(n1 + 1d, 0d)), // >
                  25 -> Gen.double(Range.linearFrac(n1, 0d)), // >=
                )
                .log("n2")
                .cover(20, "<", n2Val => n2Val < n1)
                .cover(20, "<=", n2Val => n2Val <= n1)
                .cover(20, ">", n2Val => n2Val > n1)
                .cover(20, ">=", n2Val => n2Val >= n1)
      } yield {
        val input1 = NonPosBigDecimal.unsafeFrom(BigDecimal(n1))
        val input2 = NonPosBigDecimal.unsafeFrom(BigDecimal(n2))

        val expected = cats.Order[BigDecimal].compare(BigDecimal(n1), BigDecimal(n2))
        val actual   = cats.Order[NonPosBigDecimal].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[NonPosBigDecimal].compare(input1, input2) === cats.Order[BigDecimal].compare(n1, n2)",
          actual,
          expected,
        )(_ === _)

      }

    def testOrderEqualCase: Property =
      for {
        n1 <- Gen.double(Range.linearFrac(Double.MinValue, 0d)).log("n1")
        n2 <- Gen.constant(n1).log("n2")
      } yield {
        val input1 = NonPosBigDecimal.unsafeFrom(BigDecimal(n1))
        val input2 = NonPosBigDecimal.unsafeFrom(BigDecimal(n2))

        val expected = 0
        val actual   = cats.Order[NonPosBigDecimal].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[NonPosBigDecimal].compare(NonPosBigDecimal(n1), NonPosBigDecimal(n2)) where n1 === n2 should return 0",
          actual,
          expected,
        )(_ === _)
      }

    def testShow: Property =
      for {
        n <- Gen.double(Range.linearFrac(0d, Double.MinValue)).map(BigDecimal(_)).log("n")
      } yield {
        val input = NonPosBigDecimal.unsafeFrom(n)

        val expected = n.toString
        val actual   = input.show

        actual ==== expected
      }

  }

  ///

  object nonEmptyStringSpec {

    def tests: List[Test] = List(
      property("test   Eq[NonEmptyString] === case", testEq),
      property("test   Eq[NonEmptyString] =!= case", testEqNotEqual),
      property("test Hash[NonEmptyString]", testHash),
      property("test Order[NonEmptyString]", testOrder),
      property("test Order[NonEmptyString] == case", testOrderEqualCase).withTests(25),
      property("test Show[NonEmptyString]", testShow),
    )

    def testEq: Property =
      for {
        s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
      } yield {
        val input = NonEmptyString.unsafeFrom(s)

        val expected = input
        val actual   = input

        Result.diffNamed("NonEmptyString(value) === NonEmptyString(value)", actual, expected)(_ === _)
      }

    def testEqNotEqual: Property =
      for {
        s1 <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s1")
        s2 <- Gen.string(Gen.unicode, Range.linear(1, 10)).map(s1 + "-" + _).log("s2")
      } yield {
        val input1 = NonEmptyString.unsafeFrom(s1)
        val input2 = NonEmptyString.unsafeFrom(s2)

        Result.diffNamed("NonEmptyString(value) =!= NonEmptyString(value)", input1, input2)(_ =!= _)
      }

    def testHash: Property =
      for {
        s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
      } yield {
        val input = NonEmptyString.unsafeFrom(s)

        val expected       = s.hashCode
        val actual         = input.hash
        val actualHashCode = input.##

        Result.all(
          List(
            Result.diffNamed("NonEmptyString(value).hash === s.hashCode", actual, expected)(_ === _),
            Result.diffNamed("NonEmptyString(value).## === s.hashCode", actualHashCode, expected)(_ === _),
          )
        )
      }

    def testOrder: Property =
      for {
        s1 <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s1")
        s2 <- Gen
                .string(Gen.unicode, Range.linear(1, 10))
                .log("s2")
                .cover(20, "<", s2Val => s2Val.compareTo(s1) < 0)
                .cover(20, ">", s2Val => s2Val.compareTo(s1) > 0)
      } yield {
        val input1 = NonEmptyString.unsafeFrom(s1)
        val input2 = NonEmptyString.unsafeFrom(s2)

        val expected = cats.Order[String].compare(s1, s2)
        val actual   = cats.Order[NonEmptyString].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[NonEmptyString].compare(input1, input2) === cats.Order[String].compare(s1, s2)",
          actual,
          expected,
        )(_ === _)

      }

    def testOrderEqualCase: Property =
      for {
        s1 <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s1")
        s2 <- Gen.constant(s1).log("s2")
      } yield {
        val input1 = NonEmptyString.unsafeFrom(s1)
        val input2 = NonEmptyString.unsafeFrom(s2)

        val expected = 0
        val actual   = cats.Order[NonEmptyString].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[NonEmptyString].compare(NonEmptyString(s1), NonEmptyString(s2)) where s1 === s2 should return 0",
          actual,
          expected,
        )(_ === _)
      }

    def testShow: Property =
      for {
        s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
      } yield {
        val input = NonEmptyString.unsafeFrom(s)

        val expected = s
        val actual   = input.show

        actual ==== expected
      }

  }

  ///

  object nonBlankStringSpec {

    def tests: List[Test] = List(
      property("test   Eq[NonBlankString] === case", testEq),
      property("test   Eq[NonBlankString] =!= case", testEqNotEqual),
      property("test Hash[NonBlankString]", testHash),
      property("test Order[NonBlankString]", testOrder),
      property("test Order[NonBlankString] == case", testOrderEqualCase).withTests(25),
      property("test Show[NonBlankString]", testShow),
    )

    def testEq: Property =
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

    def testEqNotEqual: Property =
      for {
        nonWhitespaceString <- Gen.string(hedgehog.extra.Gens.genNonWhitespaceChar, Range.linear(1, 10)).log("nonWhitespaceString")
        whitespaceString    <- Gen
                                 .string(
                                   hedgehog.extra.Gens.genCharByRange(refined4s.types.strings.WhitespaceCharRange),
                                   Range.linear(1, 10),
                                 )
                                 .log("whitespaceString")

        nonWhitespaceString2 <- Gen.string(hedgehog.extra.Gens.genNonWhitespaceChar, Range.linear(1, 10)).log("nonWhitespaceString2")
        s                    <- Gen.constant(scala.util.Random.shuffle((nonWhitespaceString + whitespaceString).toList).mkString).log("s")
        s2                   <-
          Gen.constant(scala.util.Random.shuffle((nonWhitespaceString + whitespaceString + nonWhitespaceString2).toList).mkString).log("s2")
      } yield {
        val input1 = NonBlankString.unsafeFrom(s)
        val input2 = NonBlankString.unsafeFrom(s2)

        Result.diffNamed("NonBlankString(value) =!= NonBlankString(value)", input1, input2)(_ =!= _)
      }

    def testHash: Property =
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

        val expected       = s.hashCode
        val actual         = input.hash
        val actualHashCode = input.##

        Result.all(
          List(
            Result.diffNamed("NonBlankString(value).hash === s.hashCode", actual, expected)(_ === _),
            Result.diffNamed("NonBlankString(value).## === s.hashCode", actualHashCode, expected)(_ === _),
          )
        )
      }

    def testOrder: Property =
      for {
        s1 <- Gen.string(hedgehog.extra.Gens.genNonWhitespaceChar, Range.linear(1, 10)).log("s1")
        s2 <- Gen
                .string(hedgehog.extra.Gens.genNonWhitespaceChar, Range.linear(1, 10))
                .log("s2")
                .cover(20, "<", s2Val => s2Val.compareTo(s1) < 0)
                .cover(20, ">", s2Val => s2Val.compareTo(s1) > 0)
      } yield {
        val input1 = NonBlankString.unsafeFrom(s1)
        val input2 = NonBlankString.unsafeFrom(s2)

        val expected = cats.Order[String].compare(s1, s2)
        val actual   = cats.Order[NonBlankString].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[NonBlankString].compare(input1, input2) === cats.Order[String].compare(s1, s2)",
          actual,
          expected,
        )(_ === _)

      }

    def testOrderEqualCase: Property =
      for {
        s1 <- Gen.string(hedgehog.extra.Gens.genNonWhitespaceChar, Range.linear(1, 10)).log("s1")
        s2 <- Gen.constant(s1).log("s2")
      } yield {
        val input1 = NonBlankString.unsafeFrom(s1)
        val input2 = NonBlankString.unsafeFrom(s2)

        val expected = 0
        val actual   = cats.Order[NonBlankString].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[NonBlankString].compare(NonBlankString(s1), NonBlankString(s2)) where s1 === s2 should return 0",
          actual,
          expected,
        )(_ === _)
      }

    def testShow: Property =
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
  }

  ///

  object uuidSpec {

    def tests: List[Test] = List(
      property("test   Eq[Uuid] === case", testEq),
      property("test   Eq[Uuid] =!= case", testEqNotEqual),
      property("test Hash[Uuid]", testHash),
      property("test Order[Uuid]", testOrder),
      property("test Order[Uuid] == case", testOrderEqualCase).withTests(25),
      property("test Show[Uuid]", testShow),
    )

    def testEq: Property =
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

    def testEqNotEqual: Property =
      for {
        uuid1 <- Gen.constant(UUID.randomUUID()).log("uuid1")
        uuid2 <- Gen.constant(UUID.randomUUID()).log("uuid2")
      } yield {
        val input1 = Uuid(uuid1)
        val input2 = Uuid(uuid2)

        Result.diffNamed("Uuid(value) =!= Uuid(different value)", input1, input2)(_ =!= _)
      }

    def testHash: Property =
      for {
        uuid <- Gen.constant(UUID.randomUUID()).log("uuid")
      } yield {
        val input = Uuid(uuid)

        val expected       = uuid.toString.hashCode
        val actual         = input.hash
        val actualHashCode = input.##

        Result.all(
          List(
            Result.diffNamed("Uuid(value).hash === UUID.toString.hashCode", actual, expected)(_ === _),
            Result.diffNamed("Uuid(value).## === UUID.toString.hashCode", actualHashCode, expected)(_ === _),
          )
        )
      }

    def testOrder: Property =
      for {
        uuid1 <- Gen.constant(UUID.randomUUID()).log("uuid1")
        uuid2 <- Gen
                   .constant(UUID.randomUUID())
                   .log("uuid2")
                   .cover(20, "<", uuid2Val => uuid2Val.toString.compareTo(uuid1.toString) < 0)
                   .cover(20, ">", uuid2Val => uuid2Val.toString.compareTo(uuid1.toString) > 0)
      } yield {
        val input1 = Uuid(uuid1)
        val input2 = Uuid(uuid2)

        val expected = cats.Order[String].compare(uuid1.toString, uuid2.toString)
        val actual   = cats.Order[Uuid].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[Uuid].compare(input1, input2) === cats.Order[String].compare(uuid1, uuid2)",
          actual,
          expected,
        )(_ === _)

      }

    def testOrderEqualCase: Property =
      for {
        uuid1 <- Gen.constant(UUID.randomUUID()).log("uuid1")
        uuid2 <- Gen.constant(uuid1).log("uuid2")
      } yield {
        val input1 = Uuid(uuid1)
        val input2 = Uuid(uuid2)

        val expected = 0
        val actual   = cats.Order[Uuid].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[Uuid].compare(Uuid(uuid1), Uuid(uuid2)) where uuid1 === uuid2 should return 0",
          actual,
          expected,
        )(_ === _)
      }

    def testShow: Property =
      for {
        uuid <- Gen.constant(UUID.randomUUID()).log("uuid")
      } yield {
        val input = Uuid(uuid)

        val expected = uuid.show
        val actual   = input.show

        actual ==== expected
      }

  }

  /* strings.UuidV7 { */

  /* TODO: #597 - Temporarily hide UuidV7 until it's ready for use.
  object uuidV7Spec {
    def tests: List[Test] = List(
      property("test   Eq[UuidV7] === case", testEq),
      property("test   Eq[UuidV7] =!= case", testEqNotEqual),
      property("test Hash[UuidV7]", testHash),
      property("test Order[UuidV7]", testOrder),
      property("test Order[UuidV7] == case", testOrderEqualCase).withTests(25),
      property("test Show[UuidV7]", testShow),
    )

    def testEq: Property =
      for {
        uuid1 <- Gen.constant(UuidV7TestTools.ValidUuidV7).log("uuid1")
        uuid2 <- Gen.constant("018f2f2c-e160-7000-8000-000000000001").log("uuid2")
      } yield {
        val input1 = UuidV7.unsafeFromString(uuid1)
        val input2 = UuidV7.unsafeFromString(uuid2)

        val expected1 = input1
        val actual1   = input1

        Result.all(
          List(
            Result.diffNamed("UuidV7(value) === UuidV7(value)", actual1, expected1)(_ === _),
            Result.diffNamed("UuidV7(value) =!= UuidV7(different value)", input1, input2)(_ =!= _),
          )
        )
      }

    def testEqNotEqual: Property =
      for {
        uuid1 <- Gen.constant(UuidV7TestTools.ValidUuidV7).log("uuid1")
        uuid2 <- Gen.constant("018f2f2c-e160-7000-8000-000000000001").log("uuid2")
      } yield {
        val input1 = UuidV7.unsafeFromString(uuid1)
        val input2 = UuidV7.unsafeFromString(uuid2)

        Result.diffNamed("UuidV7(value) =!= UuidV7(different value)", input1, input2)(_ =!= _)
      }

    def testHash: Property =
      for {
        uuid <- Gen.elementUnsafe(UuidV7TestTools.validUuidV7Strings).log("uuid")
      } yield {
        val input = UuidV7.unsafeFromString(uuid)

        val expected       = UUID.fromString(uuid).hashCode
        val actual         = input.hash
        val actualHashCode = input.##

        Result.all(
          List(
            Result.diffNamed("UuidV7(value).hash === UUID(value).hashCode", actual, expected)(_ === _),
            Result.diffNamed("UuidV7(value).## === UUID(value).hashCode", actualHashCode, expected)(_ === _),
          )
        )
      }

    def testOrder: Property =
      for {
        uuid1 <- Gen.elementUnsafe(UuidV7TestTools.validUuidV7Strings).log("uuid1")
        uuid2 <- Gen.elementUnsafe(UuidV7TestTools.validUuidV7Strings)
                .log("uuid2")
                .cover(10, "<", uuid2Val => UUID.fromString(uuid2Val).compareTo(UUID.fromString(uuid1)) < 0)
                .cover(10, ">", uuid2Val => UUID.fromString(uuid2Val).compareTo(UUID.fromString(uuid1)) > 0)
      } yield {
        val input1 = UuidV7.unsafeFromString(uuid1)
        val input2 = UuidV7.unsafeFromString(uuid2)

        val expected = cats.Order[UUID].compare(UUID.fromString(uuid1), UUID.fromString(uuid2))
        val actual   = cats.Order[UuidV7].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[UuidV7].compare(input1, input2) === cats.Order[UUID].compare(uuid1, uuid2)",
          actual,
          expected,
        )(_ === _)

      }

    def testOrderEqualCase: Property =
      for {
        uuid1 <- Gen.elementUnsafe(UuidV7TestTools.validUuidV7Strings).log("uuid1")
        uuid2 <- Gen.constant(uuid1).log("uuid2")
      } yield {
        val input1 = UuidV7.unsafeFromString(uuid1)
        val input2 = UuidV7.unsafeFromString(uuid2)

        val expected = 0
        val actual   = cats.Order[UuidV7].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[UuidV7].compare(UuidV7(uuid1), UuidV7(uuid2)) where uuid1 === uuid2 should return 0",
          actual,
          expected,
        )(_ === _)
      }

    def testShow: Property =
      for {
        uuid <- Gen.elementUnsafe(UuidV7TestTools.validUuidV7Strings).log("uuid")
      } yield {
        val input = UuidV7.unsafeFromString(uuid)

        val expected = uuid
        val actual   = input.show

        Result.diffNamed("UuidV7(value).show === value: String", actual, expected)(_ === _)
      }
  }
   */

  /* strings.UuidV7 } */

  /* network.Uri */

  object uriSpec {
    def tests: List[Test] = List(
      property("test   Eq[Uri] === case", testEq),
      property("test   Eq[Uri] =!= case", testEqNotEqual),
      property("test Hash[Uri]", testHash),
      property("test Order[Uri]", testOrder),
      property("test Order[Uri] == case", testOrderEqualCase).withTests(25),
      property("test Show[Uri]", testShow),
    )

    def testEq: Property =
      for {
        uri <- networkGens.genUriString.log("uri")
      } yield {
        val input = Uri.unsafeFrom(uri)

        val expected = input
        val actual   = input

        Result.diffNamed("Uri(value) === Uri(value)", actual, expected)(_ === _)
      }

    def testEqNotEqual: Property =
      for {
        uri  <- networkGens.genUriString.log("uri")
        uri2 <- networkGens.genUriString.map(_ + ".com.au").log("uri2")
      } yield {
        val input1 = Uri.unsafeFrom(uri)
        val input2 = Uri.unsafeFrom(uri2)

        Result.diffNamed("Uri(value) =!= Uri(different value)", input1, input2)(_ =!= _)
      }

    def testHash: Property =
      for {
        uri <- networkGens.genUriString.log("uri")
      } yield {
        val input = Uri.unsafeFrom(uri)

        val expected       = uri.hashCode
        val actual         = input.hash
        val actualHashCode = input.##

        Result.all(
          List(
            Result.diffNamed("Uri(value).hash === uri.hashCode", actual, expected)(_ === _),
            Result.diffNamed("Uri(value).## === uri.hashCode", actualHashCode, expected)(_ === _),
          )
        )
      }

    def testOrder: Property =
      for {
        s1 <- networkGens.genUriString.log("s1")
        s2 <- networkGens
                .genUriString
                .log("s2")
                .cover(20, "<", s2Val => s2Val.compareTo(s1) < 0)
                .cover(20, ">", s2Val => s2Val.compareTo(s1) > 0)
      } yield {
        val input1 = Uri.unsafeFrom(s1)
        val input2 = Uri.unsafeFrom(s2)

        val expected = cats.Order[String].compare(s1, s2)
        val actual   = cats.Order[Uri].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[Uri].compare(input1, input2) === cats.Order[String].compare(s1, s2)",
          actual,
          expected,
        )(_ === _)

      }

    def testOrderEqualCase: Property =
      for {
        s1 <- networkGens.genUriString.log("s1")
        s2 <- Gen.constant(s1).log("s2")
      } yield {
        val input1 = Uri.unsafeFrom(s1)
        val input2 = Uri.unsafeFrom(s2)

        val expected = 0
        val actual   = cats.Order[Uri].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[Uri].compare(Uri(s1), Uri(s2)) where s1 === s2 should return 0",
          actual,
          expected,
        )(_ === _)
      }

    def testShow: Property =
      for {
        uri <- networkGens.genUriString.log("uri")
      } yield {
        val input = Uri.unsafeFrom(uri)

        val expected = uri
        val actual   = input.show

        actual ==== expected
      }

  }

  /* network.Url */

  object urlSpec {
    def tests: List[Test] = List(
      property("test   Eq[Url] === case", testEq),
      property("test   Eq[Url] =!= case", testEqNotEqual),
      property("test Hash[Url]", testHash),
      property("test Order[Url]", testOrder),
      property("test Order[Url] == case", testOrderEqualCase).withTests(25),
      property("test Show[Url]", testShow),
    )

    def testEq: Property =
      for {
        url <- networkGens.genUrlString.log("url")
      } yield {
        val input = Url.unsafeFrom(url)

        val expected = input
        val actual   = input

        Result.diffNamed("Uri(value) === Uri(value)", actual, expected)(_ === _)
      }

    def testEqNotEqual: Property =
      for {
        url  <- networkGens.genUrlString.log("url")
        url2 <- networkGens.genUrlString.map(_ + ".com.au").log("url2")
      } yield {
        val input1 = Url.unsafeFrom(url)
        val input2 = Url.unsafeFrom(url2)

        Result.diffNamed("Uri(value) =!= Uri(different value)", input1, input2)(_ =!= _)
      }

    def testHash: Property =
      for {
        url <- networkGens.genUrlString.log("url")
      } yield {
        val input = Url.unsafeFrom(url)

        val expected       = url.hashCode
        val actual         = input.hash
        val actualHashCode = input.##

        Result.all(
          List(
            Result.diffNamed("Uri(value).hash === url.hashCode", actual, expected)(_ === _),
            Result.diffNamed("Uri(value).## === url.hashCode", actualHashCode, expected)(_ === _),
          )
        )
      }

    def testOrder: Property =
      for {
        s1 <- networkGens.genUrlString.log("s1")
        s2 <- networkGens
                .genUrlString
                .log("s2")
                .cover(20, "<", s2Val => s2Val.compareTo(s1) < 0)
                .cover(20, ">", s2Val => s2Val.compareTo(s1) > 0)
      } yield {
        val input1 = Url.unsafeFrom(s1)
        val input2 = Url.unsafeFrom(s2)

        val expected = cats.Order[String].compare(s1, s2)
        val actual   = cats.Order[Url].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[Url].compare(input1, input2) === cats.Order[String].compare(s1, s2)",
          actual,
          expected,
        )(_ === _)

      }

    def testOrderEqualCase: Property =
      for {
        s1 <- networkGens.genUrlString.log("s1")
        s2 <- Gen.constant(s1).log("s2")
      } yield {
        val input1 = Url.unsafeFrom(s1)
        val input2 = Url.unsafeFrom(s2)

        val expected = 0
        val actual   = cats.Order[Url].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[Url].compare(Url(s1), Url(s2)) where s1 === s2 should return 0",
          actual,
          expected,
        )(_ === _)
      }

    def testShow: Property =
      for {
        url <- networkGens.genUrlString.log("url")
      } yield {
        val input = Url.unsafeFrom(url)

        val expected = url
        val actual   = input.show

        actual ==== expected
      }

  }

  /* network ports */

  object portNumberSpec {

    def tests: List[Test] = List(
      property("test   Eq[PortNumber] === case", testEq),
      property("test   Eq[PortNumber] =!= case", testEqNotEqual),
      property("test Hash[PortNumber]", testHash),
      property("test Order[PortNumber]", testOrder),
      property("test Order[PortNumber] == case", testOrderEqualCase).withTests(25),
      property("test Show[PortNumber]", testShow),
    )

    def testEq: Property = for {
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

    def testEqNotEqual: Property = for {
      portNumber1 <- networkGens.genPortNumberInt.log("portNumber1")
      portNumber2 <- networkGens
                       .genPortNumberInt
                       .map {
                         case `portNumber1` =>
                           if portNumber1 === 0 then portNumber1 + 1 else portNumber1 - 1

                         case portNumber2 => portNumber2
                       }
                       .log("portNumber2")
    } yield {
      val input1 = PortNumber.unsafeFrom(portNumber1)
      val input2 = PortNumber.unsafeFrom(portNumber2)
      Result.diffNamed("PortNumber(value) =!= PortNumber(different value)", input1, input2)(_ =!= _)
    }

    def testHash: Property =
      for {
        portNumber <- networkGens.genPortNumberInt.log("portNumber")
      } yield {
        val input = PortNumber.unsafeFrom(portNumber)

        val expected       = portNumber.hashCode
        val actual         = input.hash
        val actualHashCode = input.##

        Result.all(
          List(
            Result.diffNamed("PortNumber(value).hash === portNumber.hashCode", actual, expected)(_ === _),
            Result.diffNamed("PortNumber(value).## === portNumber.hashCode", actualHashCode, expected)(_ === _),
          )
        )
      }

    def testOrder: Property =
      for {
        n1 <- Gen.int(Range.linear(21845, 43690)).log("n1")
        n2 <- Gen
                .frequency1(
                  25 -> Gen.int(Range.linear(0, n1 - 1)), // <
                  25 -> Gen.int(Range.linear(0, n1)), // <=
                  25 -> Gen.int(Range.linear(n1 + 1, 65535)), // >
                  25 -> Gen.int(Range.linear(n1, 65535)), // >=
                )
                .log("n2")
                .cover(20, "<", n2Val => n2Val < n1)
                .cover(20, "<=", n2Val => n2Val <= n1)
                .cover(20, ">", n2Val => n2Val > n1)
                .cover(20, ">=", n2Val => n2Val >= n1)
      } yield {
        val input1 = PortNumber.unsafeFrom(n1)
        val input2 = PortNumber.unsafeFrom(n2)

        val expected = cats.Order[Int].compare(n1, n2)
        val actual   = cats.Order[PortNumber].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[PortNumber].compare(input1, input2) === cats.Order[Int].compare(n1, n2)",
          actual,
          expected,
        )(_ === _)

      }

    def testOrderEqualCase: Property =
      for {
        n1 <- Gen.int(Range.linear(0, 65535)).log("n1")
        n2 <- Gen.constant(n1).log("n2")
      } yield {
        val input1 = PortNumber.unsafeFrom(n1)
        val input2 = PortNumber.unsafeFrom(n2)

        val expected = 0
        val actual   = cats.Order[PortNumber].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[PortNumber].compare(PortNumber(n1), PortNumber(n2)) where n1 === n2 should return 0",
          actual,
          expected,
        )(_ === _)
      }

    def testShow: Property =
      for {
        portNumber <- networkGens.genPortNumberInt.log("portNumber")
      } yield {

        val input = PortNumber.unsafeFrom(portNumber)

        val expected = portNumber.toString
        val actual   = input.show

        actual ==== expected
      }

  }

  object systemPortNumberSpec {

    def tests: List[Test] = List(
      property("test   Eq[SystemPortNumber] === case", testEq),
      property("test   Eq[SystemPortNumber] =!= case", testEqNotEqual),
      property("test Hash[SystemPortNumber]", testHash),
      property("test Order[SystemPortNumber]", testOrder),
      property("test Order[SystemPortNumber] == case", testOrderEqualCase).withTests(25),
      property("test Show[SystemPortNumber]", testShow),
    )

    def testEq: Property = for {
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

    def testEqNotEqual: Property = for {
      systemPortNumber1 <- networkGens.genSystemPortNumberInt.log("systemPortNumber1")
      systemPortNumber2 <- networkGens
                             .genSystemPortNumberInt
                             .map {
                               case `systemPortNumber1` =>
                                 if systemPortNumber1 === 0 then systemPortNumber1 + 1 else systemPortNumber1 - 1

                               case systemPortNumber2 =>
                                 systemPortNumber2
                             }
                             .log("systemPortNumber2")
    } yield {
      val input1 = SystemPortNumber.unsafeFrom(systemPortNumber1)
      val input2 = SystemPortNumber.unsafeFrom(systemPortNumber2)
      Result.diffNamed("SystemPortNumber(value) =!= SystemPortNumber(different value)", input1, input2)(_ =!= _)
    }

    def testHash: Property =
      for {
        systemPortNumber <- networkGens.genSystemPortNumberInt.log("systemPortNumber")
      } yield {
        val input = SystemPortNumber.unsafeFrom(systemPortNumber)

        val expected       = systemPortNumber.hashCode
        val actual         = input.hash
        val actualHashCode = input.##

        Result.all(
          List(
            Result.diffNamed("SystemPortNumber(value).hash === systemPortNumber.hashCode", actual, expected)(_ === _),
            Result.diffNamed("SystemPortNumber(value).## === systemPortNumber.hashCode", actualHashCode, expected)(_ === _),
          )
        )
      }

    def testOrder: Property =
      for {
        n1 <- Gen.int(Range.linear(341, 682)).log("n1")
        n2 <- Gen
                .frequency1(
                  25 -> Gen.int(Range.linear(0, n1 - 1)), // <
                  25 -> Gen.int(Range.linear(0, n1)), // <=
                  25 -> Gen.int(Range.linear(n1 + 1, 1023)), // >
                  25 -> Gen.int(Range.linear(n1, 1023)), // >=
                )
                .log("n2")
                .cover(20, "<", n2Val => n2Val < n1)
                .cover(20, "<=", n2Val => n2Val <= n1)
                .cover(20, ">", n2Val => n2Val > n1)
                .cover(20, ">=", n2Val => n2Val >= n1)
      } yield {
        val input1 = SystemPortNumber.unsafeFrom(n1)
        val input2 = SystemPortNumber.unsafeFrom(n2)

        val expected = cats.Order[Int].compare(n1, n2)
        val actual   = cats.Order[SystemPortNumber].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[SystemPortNumber].compare(input1, input2) === cats.Order[Int].compare(n1, n2)",
          actual,
          expected,
        )(_ === _)

      }

    def testOrderEqualCase: Property =
      for {
        n1 <- Gen.int(Range.linear(0, 1023)).log("n1")
        n2 <- Gen.constant(n1).log("n2")
      } yield {
        val input1 = SystemPortNumber.unsafeFrom(n1)
        val input2 = SystemPortNumber.unsafeFrom(n2)

        val expected = 0
        val actual   = cats.Order[SystemPortNumber].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[SystemPortNumber].compare(SystemPortNumber(n1), SystemPortNumber(n2)) where n1 === n2 should return 0",
          actual,
          expected,
        )(_ === _)
      }

    def testShow: Property = for {
      systemPortNumber <- networkGens.genSystemPortNumberInt.log("systemPortNumber")
    } yield {

      val input = SystemPortNumber.unsafeFrom(systemPortNumber)

      val expected = systemPortNumber.toString
      val actual   = input.show

      actual ==== expected
    }

  }

  object nonSystemPortNumberSpec {

    def tests: List[Test] = List(
      property("test   Eq[NonSystemPortNumber] === case", testEq),
      property("test   Eq[NonSystemPortNumber] =!= case", testEqNotEqual),
      property("test Hash[NonSystemPortNumber]", testHash),
      property("test Order[NonSystemPortNumber]", testOrder),
      property("test Order[NonSystemPortNumber] == case", testOrderEqualCase).withTests(25),
      property("test Show[NonSystemPortNumber]", testShow),
    )

    def testEq: Property = for {
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

    def testEqNotEqual: Property = for {
      nonSystemPortNumber1 <- networkGens.genNonSystemPortNumberInt.log("nonSystemPortNumber1")
      nonSystemPortNumber2 <- networkGens
                                .genNonSystemPortNumberInt
                                .map {
                                  case `nonSystemPortNumber1` =>
                                    if nonSystemPortNumber1 === 1024 then nonSystemPortNumber1 + 1 else nonSystemPortNumber1 - 1

                                  case nonSystemPortNumber2 => nonSystemPortNumber2
                                }
                                .log("nonSystemPortNumber2")
    } yield {
      val input1 = NonSystemPortNumber.unsafeFrom(nonSystemPortNumber1)
      val input2 = NonSystemPortNumber.unsafeFrom(nonSystemPortNumber2)
      Result.diffNamed("NonSystemPortNumber(value) =!= NonSystemPortNumber(different value)", input1, input2)(_ =!= _)
    }

    def testHash: Property =
      for {
        nonSystemPortNumber <- networkGens.genNonSystemPortNumberInt.log("nonSystemPortNumber")
      } yield {
        val input = NonSystemPortNumber.unsafeFrom(nonSystemPortNumber)

        val expected       = nonSystemPortNumber.hashCode
        val actual         = input.hash
        val actualHashCode = input.##

        Result.all(
          List(
            Result.diffNamed("NonSystemPortNumber(value).hash === nonSystemPortNumber.hashCode", actual, expected)(_ === _),
            Result.diffNamed("NonSystemPortNumber(value).## === nonSystemPortNumber.hashCode", actualHashCode, expected)(_ === _),
          )
        )
      }

    def testOrder: Property =
      for {
        n1 <- Gen.int(Range.linear(22528, 44031)).log("n1")
        n2 <- Gen
                .frequency1(
                  25 -> Gen.int(Range.linear(1024, n1 - 1)), // <
                  25 -> Gen.int(Range.linear(1024, n1)), // <=
                  25 -> Gen.int(Range.linear(n1 + 1, 65535)), // >
                  25 -> Gen.int(Range.linear(n1, 65535)), // >=
                )
                .log("n2")
                .cover(20, "<", n2Val => n2Val < n1)
                .cover(20, "<=", n2Val => n2Val <= n1)
                .cover(20, ">", n2Val => n2Val > n1)
                .cover(20, ">=", n2Val => n2Val >= n1)
      } yield {
        val input1 = NonSystemPortNumber.unsafeFrom(n1)
        val input2 = NonSystemPortNumber.unsafeFrom(n2)

        val expected = cats.Order[Int].compare(n1, n2)
        val actual   = cats.Order[NonSystemPortNumber].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[NonSystemPortNumber].compare(input1, input2) === cats.Order[Int].compare(n1, n2)",
          actual,
          expected,
        )(_ === _)

      }

    def testOrderEqualCase: Property =
      for {
        n1 <- Gen.int(Range.linear(1024, 65535)).log("n1")
        n2 <- Gen.constant(n1).log("n2")
      } yield {
        val input1 = NonSystemPortNumber.unsafeFrom(n1)
        val input2 = NonSystemPortNumber.unsafeFrom(n2)

        val expected = 0
        val actual   = cats.Order[NonSystemPortNumber].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[NonSystemPortNumber].compare(NonSystemPortNumber(n1), NonSystemPortNumber(n2)) where n1 === n2 should return 0",
          actual,
          expected,
        )(_ === _)
      }

    def testShow: Property = for {
      nonSystemPortNumber <- networkGens.genNonSystemPortNumberInt.log("nonSystemPortNumber")
    } yield {

      val input = NonSystemPortNumber.unsafeFrom(nonSystemPortNumber)

      val expected = nonSystemPortNumber.toString
      val actual   = input.show

      actual ==== expected
    }

  }

  object userPortNumberSpec {

    def tests: List[Test] = List(
      property("test   Eq[UserPortNumber] === case", testEq),
      property("test   Eq[UserPortNumber] =!= case", testEqNotEqual),
      property("test Hash[UserPortNumber]", testHash),
      property("test Order[UserPortNumber]", testOrder),
      property("test Order[UserPortNumber] == case", testOrderEqualCase).withTests(25),
      property("test Show[UserPortNumber]", testShow),
    )

    def testEq: Property = for {
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

    def testEqNotEqual: Property = for {
      userPortNumber1 <- networkGens.genUserPortNumberInt.log("userPortNumber1")
      userPortNumber2 <- networkGens
                           .genUserPortNumberInt
                           .map {
                             case `userPortNumber1` =>
                               if userPortNumber1 === 1024 then userPortNumber1 + 1 else userPortNumber1 - 1

                             case userPortNumber2 =>
                               userPortNumber2
                           }
                           .log("userPortNumber2")
    } yield {
      val input1 = UserPortNumber.unsafeFrom(userPortNumber1)
      val input2 = UserPortNumber.unsafeFrom(userPortNumber2)
      Result.diffNamed("UserPortNumber(value) =!= UserPortNumber(different value)", input1, input2)(_ =!= _)
    }

    def testHash: Property =
      for {
        userPortNumber <- networkGens.genUserPortNumberInt.log("userPortNumber")
      } yield {
        val input = UserPortNumber.unsafeFrom(userPortNumber)

        val expected       = userPortNumber.hashCode
        val actual         = input.hash
        val actualHashCode = input.##

        Result.all(
          List(
            Result.diffNamed("UserPortNumber(value).hash === userPortNumber.hashCode", actual, expected)(_ === _),
            Result.diffNamed("UserPortNumber(value).## === userPortNumber.hashCode", actualHashCode, expected)(_ === _),
          )
        )
      }

    def testOrder: Property =
      for {
        n1 <- Gen.int(Range.linear(17066, 33108)).log("n1")
        n2 <- Gen
                .frequency1(
                  25 -> Gen.int(Range.linear(1024, n1 - 1)), // <
                  25 -> Gen.int(Range.linear(1024, n1)), // <=
                  25 -> Gen.int(Range.linear(n1 + 1, 49151)), // >
                  25 -> Gen.int(Range.linear(n1, 49151)), // >=
                )
                .log("n2")
                .cover(20, "<", n2Val => n2Val < n1)
                .cover(20, "<=", n2Val => n2Val <= n1)
                .cover(20, ">", n2Val => n2Val > n1)
                .cover(20, ">=", n2Val => n2Val >= n1)
      } yield {
        val input1 = UserPortNumber.unsafeFrom(n1)
        val input2 = UserPortNumber.unsafeFrom(n2)

        val expected = cats.Order[Int].compare(n1, n2)
        val actual   = cats.Order[UserPortNumber].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[UserPortNumber].compare(input1, input2) === cats.Order[Int].compare(n1, n2)",
          actual,
          expected,
        )(_ === _)

      }

    def testOrderEqualCase: Property =
      for {
        n1 <- Gen.int(Range.linear(1024, 49151)).log("n1")
        n2 <- Gen.constant(n1).log("n2")
      } yield {
        val input1 = UserPortNumber.unsafeFrom(n1)
        val input2 = UserPortNumber.unsafeFrom(n2)

        val expected = 0
        val actual   = cats.Order[UserPortNumber].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[UserPortNumber].compare(UserPortNumber(n1), UserPortNumber(n2)) where n1 === n2 should return 0",
          actual,
          expected,
        )(_ === _)
      }

    def testShow: Property = for {
      userPortNumber <- networkGens.genUserPortNumberInt.log("userPortNumber")
    } yield {

      val input = UserPortNumber.unsafeFrom(userPortNumber)

      val expected = userPortNumber.toString
      val actual   = input.show

      actual ==== expected
    }

  }

  object dynamicPortNumberSpec {

    def tests: List[Test] = List(
      property("test   Eq[DynamicPortNumber] === case", testEq),
      property("test   Eq[DynamicPortNumber] =!= case", testEqNotEqual),
      property("test Hash[DynamicPortNumber]", testHash),
      property("test Order[DynamicPortNumber]", testOrder),
      property("test Order[DynamicPortNumber] == case", testOrderEqualCase).withTests(25),
      property("test Show[DynamicPortNumber]", testShow),
    )

    def testEq: Property = for {
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

    def testEqNotEqual: Property = for {
      dynamicPortNumber1 <- networkGens.genDynamicPortNumberInt.log("dynamicPortNumber1")
      dynamicPortNumber2 <- networkGens
                              .genDynamicPortNumberInt
                              .map {
                                case `dynamicPortNumber1` =>
                                  if dynamicPortNumber1 === 49152 then dynamicPortNumber1 + 1 else dynamicPortNumber1 - 1

                                case dynamicPortNumber2 =>
                                  dynamicPortNumber2
                              }
                              .log("dynamicPortNumber2")
    } yield {
      val input1 = DynamicPortNumber.unsafeFrom(dynamicPortNumber1)
      val input2 = DynamicPortNumber.unsafeFrom(dynamicPortNumber2)
      Result.diffNamed("DynamicPortNumber(value) =!= DynamicPortNumber(different value)", input1, input2)(_ =!= _)
    }

    def testHash: Property =
      for {
        dynamicPortNumber <- networkGens.genDynamicPortNumberInt.log("dynamicPortNumber")
      } yield {
        val input = DynamicPortNumber.unsafeFrom(dynamicPortNumber)

        val expected       = dynamicPortNumber.hashCode
        val actual         = input.hash
        val actualHashCode = input.##

        Result.all(
          List(
            Result.diffNamed("DynamicPortNumber(value).hash === dynamicPortNumber.hashCode", actual, expected)(_ === _),
            Result.diffNamed("DynamicPortNumber(value).## === dynamicPortNumber.hashCode", actualHashCode, expected)(_ === _),
          )
        )
      }

    def testOrder: Property =
      for {
        n1 <- Gen.int(Range.linear(54613, 60074)).log("n1")
        n2 <- Gen
                .frequency1(
                  25 -> Gen.int(Range.linear(49152, n1 - 1)), // <
                  25 -> Gen.int(Range.linear(49152, n1)), // <=
                  25 -> Gen.int(Range.linear(n1 + 1, 65535)), // >
                  25 -> Gen.int(Range.linear(n1, 65535)), // >=
                )
                .log("n2")
                .cover(20, "<", n2Val => n2Val < n1)
                .cover(20, "<=", n2Val => n2Val <= n1)
                .cover(20, ">", n2Val => n2Val > n1)
                .cover(20, ">=", n2Val => n2Val >= n1)
      } yield {
        val input1 = DynamicPortNumber.unsafeFrom(n1)
        val input2 = DynamicPortNumber.unsafeFrom(n2)

        val expected = cats.Order[Int].compare(n1, n2)
        val actual   = cats.Order[DynamicPortNumber].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[DynamicPortNumber].compare(input1, input2) === cats.Order[Int].compare(n1, n2)",
          actual,
          expected,
        )(_ === _)

      }

    def testOrderEqualCase: Property =
      for {
        n1 <- Gen.int(Range.linear(49152, 65535)).log("n1")
        n2 <- Gen.constant(n1).log("n2")
      } yield {
        val input1 = DynamicPortNumber.unsafeFrom(n1)
        val input2 = DynamicPortNumber.unsafeFrom(n2)

        val expected = 0
        val actual   = cats.Order[DynamicPortNumber].compare(input1, input2)

        Result.diffNamed(
          "cats.Order[DynamicPortNumber].compare(DynamicPortNumber(n1), DynamicPortNumber(n2)) where n1 === n2 should return 0",
          actual,
          expected,
        )(_ === _)
      }

    def testShow: Property =
      for {
        dynamicPortNumber <- networkGens.genDynamicPortNumberInt.log("dynamicPortNumber")
      } yield {

        val input = DynamicPortNumber.unsafeFrom(dynamicPortNumber)

        val expected = dynamicPortNumber.toString
        val actual   = input.show

        actual ==== expected
      }

  }

  object timeSpec {
    def tests: List[Test] =
      monthSpec.tests ++ daySpec.tests ++ hourSpec.tests ++ minuteSpec.tests ++ secondSpec.tests ++ millisSpec.tests

    object monthSpec {
      def tests: List[Test] = List(
        property("test   Eq[Month] === case", testEq),
        property("test   Eq[Month] =!= case", testEqNotEqual),
        property("test Hash[Month]", testHash),
        property("test Order[Month]", testOrder),
        property("test Order[Month] == case", testOrderEqualCase).withTests(25),
        property("test Show[Month]", testShow),
      )

      def testEq: Property =
        for {
          month1 <- Gen.int(Range.linear(1, 12)).log("month1")
          month2 <- Gen
                      .int(Range.linear(1, 12))
                      .map {
                        case `month1` => if month1 === 12 then month1 - 1 else month1 + 1
                        case n => n
                      }
                      .log("month2")
        } yield {
          val input1 = Month.unsafeFrom(month1)
          val input2 = Month.unsafeFrom(month2)
          Result.all(
            List(
              Result.diffNamed("Comparing the same objects with ===", input1, input1)(_ === _),
              Result.diffNamed("Comparing the different objects with =!=", input2, input2)(_ === _),
            )
          )
        }

      def testEqNotEqual: Property =
        for {
          month1 <- Gen.int(Range.linear(1, 12)).log("month1")
          month2 <- Gen
                      .int(Range.linear(1, 12))
                      .map {
                        case `month1` => if month1 === 12 then month1 - 1 else month1 + 1
                        case n => n
                      }
                      .log("month2")
        } yield {
          val input1 = Month.unsafeFrom(month1)
          val input2 = Month.unsafeFrom(month2)
          Result.diffNamed("Month(value) =!= Month(different value)", input1, input2)(_ =!= _)
        }

      def testHash: Property =
        for {
          month <- Gen.int(Range.linear(1, 12)).log("month")
        } yield {
          val input = Month.unsafeFrom(month)

          val expected       = month.hashCode
          val actual         = input.hash
          val actualHashCode = input.##

          Result.all(
            List(
              Result.diffNamed("Month(value).hash === month.hashCode", actual, expected)(_ === _),
              Result.diffNamed("Month(value).## === month.hashCode", actualHashCode, expected)(_ === _),
            )
          )
        }

      def testOrder: Property =
        for {
          n1 <- Gen.int(Range.linear(4, 8)).log("n1")
          n2 <- Gen
                  .frequency1(
                    25 -> Gen.int(Range.linear(1, n1 - 1)), // <
                    25 -> Gen.int(Range.linear(1, n1)), // <=
                    25 -> Gen.int(Range.linear(n1 + 1, 12)), // >
                    25 -> Gen.int(Range.linear(n1, 12)), // >=
                  )
                  .log("n2")
                  .cover(20, "<", n2Val => n2Val < n1)
                  .cover(20, "<=", n2Val => n2Val <= n1)
                  .cover(20, ">", n2Val => n2Val > n1)
                  .cover(20, ">=", n2Val => n2Val >= n1)
        } yield {
          val input1 = Month.unsafeFrom(n1)
          val input2 = Month.unsafeFrom(n2)

          val expected = cats.Order[Int].compare(n1, n2)
          val actual   = cats.Order[Month].compare(input1, input2)

          Result.diffNamed(
            "cats.Order[Month].compare(input1, input2) === cats.Order[Int].compare(n1, n2)",
            actual,
            expected,
          )(_ === _)

        }

      def testOrderEqualCase: Property =
        for {
          n1 <- Gen.int(Range.linear(1, 12)).log("n1")
          n2 <- Gen.constant(n1).log("n2")
        } yield {
          val input1 = Month.unsafeFrom(n1)
          val input2 = Month.unsafeFrom(n2)

          val expected = 0
          val actual   = cats.Order[Month].compare(input1, input2)

          Result.diffNamed(
            "cats.Order[Month].compare(Month(n1), Month(n2)) where n1 === n2 should return 0",
            actual,
            expected,
          )(_ === _)
        }

      def testShow: Property =
        for {
          month <- Gen.int(Range.linear(1, 12)).log("month")
        } yield {
          val input = Month.unsafeFrom(month)

          val expected = month.toString
          val actual   = input.show

          actual ==== expected
        }
    }

    object daySpec {
      def tests: List[Test] = List(
        property("test   Eq[Day] === case", testEq),
        property("test   Eq[Day] =!= case", testEqNotEqual),
        property("test Hash[Day]", testHash),
        property("test Order[Day]", testOrder),
        property("test Order[Day] == case", testOrderEqualCase).withTests(25),
        property("test Show[Day]", testShow),
      )

      def testEq: Property =
        for {
          day1 <- Gen.int(Range.linear(1, 31)).log("day1")
          day2 <- Gen
                    .int(Range.linear(1, 31))
                    .map {
                      case `day1` => if day1 === 31 then day1 - 1 else day1 + 1
                      case n => n
                    }
                    .log("day2")
        } yield {
          val input1 = Day.unsafeFrom(day1)
          val input2 = Day.unsafeFrom(day2)
          Result.all(
            List(
              Result.diffNamed("Comparing the same objects with ===", input1, input1)(_ === _),
              Result.diffNamed("Comparing the different objects with =!=", input2, input2)(_ === _),
            )
          )
        }

      def testEqNotEqual: Property =
        for {
          day1 <- Gen.int(Range.linear(1, 31)).log("day1")
          day2 <- Gen
                    .int(Range.linear(1, 31))
                    .map {
                      case `day1` => if day1 === 31 then day1 - 1 else day1 + 1
                      case n => n
                    }
                    .log("day2")
        } yield {
          val input1 = Day.unsafeFrom(day1)
          val input2 = Day.unsafeFrom(day2)
          Result.diffNamed("Day(value) =!= Day(different value)", input1, input2)(_ =!= _)
        }

      def testHash: Property =
        for {
          day <- Gen.int(Range.linear(1, 31)).log("day")
        } yield {
          val input = Day.unsafeFrom(day)

          val expected       = day.hashCode
          val actual         = input.hash
          val actualHashCode = input.##

          Result.all(
            List(
              Result.diffNamed("Day(value).hash === day.hashCode", actual, expected)(_ === _),
              Result.diffNamed("Day(value).## === day.hashCode", actualHashCode, expected)(_ === _),
            )
          )
        }

      def testOrder: Property =
        for {
          n1 <- Gen.int(Range.linear(11, 21)).log("n1")
          n2 <- Gen
                  .frequency1(
                    25 -> Gen.int(Range.linear(1, n1 - 1)), // <
                    25 -> Gen.int(Range.linear(1, n1)), // <=
                    25 -> Gen.int(Range.linear(n1 + 1, 31)), // >
                    25 -> Gen.int(Range.linear(n1, 31)), // >=
                  )
                  .log("n2")
                  .cover(20, "<", n2Val => n2Val < n1)
                  .cover(20, "<=", n2Val => n2Val <= n1)
                  .cover(20, ">", n2Val => n2Val > n1)
                  .cover(20, ">=", n2Val => n2Val >= n1)
        } yield {
          val input1 = Day.unsafeFrom(n1)
          val input2 = Day.unsafeFrom(n2)

          val expected = cats.Order[Int].compare(n1, n2)
          val actual   = cats.Order[Day].compare(input1, input2)

          Result.diffNamed(
            "cats.Order[Day].compare(input1, input2) === cats.Order[Int].compare(n1, n2)",
            actual,
            expected,
          )(_ === _)

        }

      def testOrderEqualCase: Property =
        for {
          n1 <- Gen.int(Range.linear(1, 31)).log("n1")
          n2 <- Gen.constant(n1).log("n2")
        } yield {
          val input1 = Day.unsafeFrom(n1)
          val input2 = Day.unsafeFrom(n2)

          val expected = 0
          val actual   = cats.Order[Day].compare(input1, input2)

          Result.diffNamed(
            "cats.Order[Day].compare(Day(n1), Day(n2)) where n1 === n2 should return 0",
            actual,
            expected,
          )(_ === _)
        }

      def testShow: Property =
        for {
          day <- Gen.int(Range.linear(1, 31)).log("day")
        } yield {
          val input = Day.unsafeFrom(day)

          val expected = day.toString
          val actual   = input.show

          actual ==== expected
        }
    }

    object hourSpec {
      def tests: List[Test] = List(
        property("test   Eq[Hour] === case", testEq),
        property("test   Eq[Hour] =!= case", testEqNotEqual),
        property("test Hash[Hour]", testHash),
        property("test Order[Hour]", testOrder),
        property("test Order[Hour] == case", testOrderEqualCase).withTests(25),
        property("test Show[Hour]", testShow),
      )

      def testEq: Property =
        for {
          hour1 <- Gen.int(Range.linear(0, 23)).log("hour1")
          hour2 <- Gen
                     .int(Range.linear(0, 23))
                     .map {
                       case `hour1` => if hour1 === 23 then hour1 - 1 else hour1 + 1
                       case n => n
                     }
                     .log("hour2")
        } yield {
          val input1 = Hour.unsafeFrom(hour1)
          val input2 = Hour.unsafeFrom(hour2)
          Result.all(
            List(
              Result.diffNamed("Comparing the same objects with ===", input1, input1)(_ === _),
              Result.diffNamed("Comparing the different objects with =!=", input2, input2)(_ === _),
            )
          )
        }

      def testEqNotEqual: Property =
        for {
          hour1 <- Gen.int(Range.linear(0, 23)).log("hour1")
          hour2 <- Gen
                     .int(Range.linear(0, 23))
                     .map {
                       case `hour1` => if hour1 === 23 then hour1 - 1 else hour1 + 1
                       case n => n
                     }
                     .log("hour2")
        } yield {
          val input1 = Hour.unsafeFrom(hour1)
          val input2 = Hour.unsafeFrom(hour2)
          Result.diffNamed("Hour(value) =!= Hour(different value)", input1, input2)(_ =!= _)
        }

      def testHash: Property =
        for {
          hour <- Gen.int(Range.linear(0, 23)).log("hour")
        } yield {
          val input = Hour.unsafeFrom(hour)

          val expected       = hour.hashCode
          val actual         = input.hash
          val actualHashCode = input.##

          Result.all(
            List(
              Result.diffNamed("Hour(value).hash === hour.hashCode", actual, expected)(_ === _),
              Result.diffNamed("Hour(value).## === hour.hashCode", actualHashCode, expected)(_ === _),
            )
          )
        }

      def testOrder: Property =
        for {
          n1 <- Gen.int(Range.linear(8, 15)).log("n1")
          n2 <- Gen
                  .frequency1(
                    25 -> Gen.int(Range.linear(0, n1 - 1)), // <
                    25 -> Gen.int(Range.linear(0, n1)), // <=
                    25 -> Gen.int(Range.linear(n1 + 1, 23)), // >
                    25 -> Gen.int(Range.linear(n1, 23)), // >=
                  )
                  .log("n2")
                  .cover(20, "<", n2Val => n2Val < n1)
                  .cover(20, "<=", n2Val => n2Val <= n1)
                  .cover(20, ">", n2Val => n2Val > n1)
                  .cover(20, ">=", n2Val => n2Val >= n1)
        } yield {
          val input1 = Hour.unsafeFrom(n1)
          val input2 = Hour.unsafeFrom(n2)

          val expected = cats.Order[Int].compare(n1, n2)
          val actual   = cats.Order[Hour].compare(input1, input2)

          Result.diffNamed(
            "cats.Order[Hour].compare(input1, input2) === cats.Order[Int].compare(n1, n2)",
            actual,
            expected,
          )(_ === _)

        }

      def testOrderEqualCase: Property =
        for {
          n1 <- Gen.int(Range.linear(0, 23)).log("n1")
          n2 <- Gen.constant(n1).log("n2")
        } yield {
          val input1 = Hour.unsafeFrom(n1)
          val input2 = Hour.unsafeFrom(n2)

          val expected = 0
          val actual   = cats.Order[Hour].compare(input1, input2)

          Result.diffNamed(
            "cats.Order[Hour].compare(Hour(n1), Hour(n2)) where n1 === n2 should return 0",
            actual,
            expected,
          )(_ === _)
        }

      def testShow: Property =
        for {
          hour <- Gen.int(Range.linear(0, 23)).log("hour")
        } yield {
          val input = Hour.unsafeFrom(hour)

          val expected = hour.toString
          val actual   = input.show

          actual ==== expected
        }
    }

    object minuteSpec {
      def tests: List[Test] = List(
        property("test   Eq[Minute] === case", testEq),
        property("test   Eq[Minute] =!= case", testEqNotEqual),
        property("test Hash[Minute]", testHash),
        property("test Order[Minute]", testOrder),
        property("test Order[Minute] == case", testOrderEqualCase).withTests(25),
        property("test Show[Minute]", testShow),
      )

      def testEq: Property =
        for {
          minute1 <- Gen.int(Range.linear(0, 59)).log("minute1")
          minute2 <- Gen
                       .int(Range.linear(0, 59))
                       .map {
                         case `minute1` => if minute1 === 59 then minute1 - 1 else minute1 + 1
                         case n => n
                       }
                       .log("minute2")
        } yield {
          val input1 = Minute.unsafeFrom(minute1)
          val input2 = Minute.unsafeFrom(minute2)
          Result.all(
            List(
              Result.diffNamed("Comparing the same objects with ===", input1, input1)(_ === _),
              Result.diffNamed("Comparing the different objects with =!=", input2, input2)(_ === _),
            )
          )
        }

      def testEqNotEqual: Property =
        for {
          minute1 <- Gen.int(Range.linear(0, 59)).log("minute1")
          minute2 <- Gen
                       .int(Range.linear(0, 59))
                       .map {
                         case `minute1` => if minute1 === 59 then minute1 - 1 else minute1 + 1
                         case n => n
                       }
                       .log("minute2")
        } yield {
          val input1 = Minute.unsafeFrom(minute1)
          val input2 = Minute.unsafeFrom(minute2)
          Result.diffNamed("Minute(value) =!= Minute(different value)", input1, input2)(_ =!= _)
        }

      def testHash: Property =
        for {
          minute <- Gen.int(Range.linear(0, 59)).log("minute")
        } yield {
          val input = Minute.unsafeFrom(minute)

          val expected       = minute.hashCode
          val actual         = input.hash
          val actualHashCode = input.##

          Result.all(
            List(
              Result.diffNamed("Minute(value).hash === minute.hashCode", actual, expected)(_ === _),
              Result.diffNamed("Minute(value).## === minute.hashCode", actualHashCode, expected)(_ === _),
            )
          )
        }

      def testOrder: Property =
        for {
          n1 <- Gen.int(Range.linear(20, 39)).log("n1")
          n2 <- Gen
                  .frequency1(
                    25 -> Gen.int(Range.linear(0, n1 - 1)), // <
                    25 -> Gen.int(Range.linear(0, n1)), // <=
                    25 -> Gen.int(Range.linear(n1 + 1, 59)), // >
                    25 -> Gen.int(Range.linear(n1, 59)), // >=
                  )
                  .log("n2")
                  .cover(20, "<", n2Val => n2Val < n1)
                  .cover(20, "<=", n2Val => n2Val <= n1)
                  .cover(20, ">", n2Val => n2Val > n1)
                  .cover(20, ">=", n2Val => n2Val >= n1)
        } yield {
          val input1 = Minute.unsafeFrom(n1)
          val input2 = Minute.unsafeFrom(n2)

          val expected = cats.Order[Int].compare(n1, n2)
          val actual   = cats.Order[Minute].compare(input1, input2)

          Result.diffNamed(
            "cats.Order[Minute].compare(input1, input2) === cats.Order[Int].compare(n1, n2)",
            actual,
            expected,
          )(_ === _)

        }

      def testOrderEqualCase: Property =
        for {
          n1 <- Gen.int(Range.linear(0, 59)).log("n1")
          n2 <- Gen.constant(n1).log("n2")
        } yield {
          val input1 = Minute.unsafeFrom(n1)
          val input2 = Minute.unsafeFrom(n2)

          val expected = 0
          val actual   = cats.Order[Minute].compare(input1, input2)

          Result.diffNamed(
            "cats.Order[Minute].compare(Minute(n1), Minute(n2)) where n1 === n2 should return 0",
            actual,
            expected,
          )(_ === _)
        }

      def testShow: Property =
        for {
          minute <- Gen.int(Range.linear(0, 59)).log("minute")
        } yield {
          val input = Minute.unsafeFrom(minute)

          val expected = minute.toString
          val actual   = input.show

          actual ==== expected
        }
    }

    object secondSpec {
      def tests: List[Test] = List(
        property("test   Eq[Second] === case", testEq),
        property("test   Eq[Second] =!= case", testEqNotEqual),
        property("test Hash[Second]", testHash),
        property("test Order[Second]", testOrder),
        property("test Order[Second] == case", testOrderEqualCase).withTests(25),
        property("test Show[Second]", testShow),
      )

      def testEq: Property =
        for {
          second1 <- Gen.int(Range.linear(0, 59)).log("second1")
          second2 <- Gen
                       .int(Range.linear(0, 59))
                       .map {
                         case `second1` => if second1 === 59 then second1 - 1 else second1 + 1
                         case n => n
                       }
                       .log("second2")
        } yield {
          val input1 = Second.unsafeFrom(second1)
          val input2 = Second.unsafeFrom(second2)
          Result.all(
            List(
              Result.diffNamed("Comparing the same objects with ===", input1, input1)(_ === _),
              Result.diffNamed("Comparing the different objects with =!=", input2, input2)(_ === _),
            )
          )
        }

      def testEqNotEqual: Property =
        for {
          second1 <- Gen.int(Range.linear(0, 59)).log("second1")
          second2 <- Gen
                       .int(Range.linear(0, 59))
                       .map {
                         case `second1` => if second1 === 59 then second1 - 1 else second1 + 1
                         case n => n
                       }
                       .log("second2")
        } yield {
          val input1 = Second.unsafeFrom(second1)
          val input2 = Second.unsafeFrom(second2)
          Result.diffNamed("Second(value) =!= Second(different value)", input1, input2)(_ =!= _)
        }

      def testHash: Property =
        for {
          second <- Gen.int(Range.linear(0, 59)).log("second")
        } yield {
          val input = Second.unsafeFrom(second)

          val expected       = second.hashCode
          val actual         = input.hash
          val actualHashCode = input.##

          Result.all(
            List(
              Result.diffNamed("Second(value).hash === second.hashCode", actual, expected)(_ === _),
              Result.diffNamed("Second(value).## === second.hashCode", actualHashCode, expected)(_ === _),
            )
          )
        }

      def testOrder: Property =
        for {
          n1 <- Gen.int(Range.linear(20, 39)).log("n1")
          n2 <- Gen
                  .frequency1(
                    25 -> Gen.int(Range.linear(0, n1 - 1)), // <
                    25 -> Gen.int(Range.linear(0, n1)), // <=
                    25 -> Gen.int(Range.linear(n1 + 1, 59)), // >
                    25 -> Gen.int(Range.linear(n1, 59)), // >=
                  )
                  .log("n2")
                  .cover(20, "<", n2Val => n2Val < n1)
                  .cover(20, "<=", n2Val => n2Val <= n1)
                  .cover(20, ">", n2Val => n2Val > n1)
                  .cover(20, ">=", n2Val => n2Val >= n1)
        } yield {
          val input1 = Second.unsafeFrom(n1)
          val input2 = Second.unsafeFrom(n2)

          val expected = cats.Order[Int].compare(n1, n2)
          val actual   = cats.Order[Second].compare(input1, input2)

          Result.diffNamed(
            "cats.Order[Second].compare(input1, input2) === cats.Order[Int].compare(n1, n2)",
            actual,
            expected,
          )(_ === _)

        }

      def testOrderEqualCase: Property =
        for {
          n1 <- Gen.int(Range.linear(0, 59)).log("n1")
          n2 <- Gen.constant(n1).log("n2")
        } yield {
          val input1 = Second.unsafeFrom(n1)
          val input2 = Second.unsafeFrom(n2)

          val expected = 0
          val actual   = cats.Order[Second].compare(input1, input2)

          Result.diffNamed(
            "cats.Order[Second].compare(Second(n1), Second(n2)) where n1 === n2 should return 0",
            actual,
            expected,
          )(_ === _)
        }

      def testShow: Property =
        for {
          second <- Gen.int(Range.linear(0, 59)).log("second")
        } yield {
          val input = Second.unsafeFrom(second)

          val expected = second.toString
          val actual   = input.show

          actual ==== expected
        }
    }

    object millisSpec {
      def tests: List[Test] = List(
        property("test   Eq[Millis] === case", testEq),
        property("test   Eq[Millis] =!= case", testEqNotEqual),
        property("test Hash[Millis]", testHash),
        property("test Order[Millis]", testOrder),
        property("test Order[Millis] == case", testOrderEqualCase).withTests(25),
        property("test Show[Millis]", testShow),
      )

      def testEq: Property =
        for {
          millis1 <- Gen.int(Range.linear(0, 999)).log("millis1")
          millis2 <- Gen
                       .int(Range.linear(0, 999))
                       .map {
                         case `millis1` => if millis1 === 59 then millis1 - 1 else millis1 + 1
                         case n => n
                       }
                       .log("millis2")
        } yield {
          val input1 = Millis.unsafeFrom(millis1)
          val input2 = Millis.unsafeFrom(millis2)
          Result.all(
            List(
              Result.diffNamed("Comparing the same objects with ===", input1, input1)(_ === _),
              Result.diffNamed("Comparing the different objects with =!=", input2, input2)(_ === _),
            )
          )
        }

      def testEqNotEqual: Property =
        for {
          millis1 <- Gen.int(Range.linear(0, 999)).log("millis1")
          millis2 <- Gen
                       .int(Range.linear(0, 999))
                       .map {
                         case `millis1` => if millis1 === 999 then millis1 - 1 else millis1 + 1
                         case n => n
                       }
                       .log("millis2")
        } yield {
          val input1 = Millis.unsafeFrom(millis1)
          val input2 = Millis.unsafeFrom(millis2)
          Result.diffNamed("Millis(value) =!= Millis(different value)", input1, input2)(_ =!= _)
        }

      def testHash: Property =
        for {
          millis <- Gen.int(Range.linear(0, 999)).log("millis")
        } yield {
          val input = Millis.unsafeFrom(millis)

          val expected       = millis.hashCode
          val actual         = input.hash
          val actualHashCode = input.##

          Result.all(
            List(
              Result.diffNamed("Millis(value).hash === millis.hashCode", actual, expected)(_ === _),
              Result.diffNamed("Millis(value).## === millis.hashCode", actualHashCode, expected)(_ === _),
            )
          )
        }

      def testOrder: Property =
        for {
          n1 <- Gen.int(Range.linear(333, 666)).log("n1")
          n2 <- Gen
                  .frequency1(
                    25 -> Gen.int(Range.linear(0, n1 - 1)), // <
                    25 -> Gen.int(Range.linear(0, n1)), // <=
                    25 -> Gen.int(Range.linear(n1 + 1, 999)), // >
                    25 -> Gen.int(Range.linear(n1, 999)), // >=
                  )
                  .log("n2")
                  .cover(20, "<", n2Val => n2Val < n1)
                  .cover(20, "<=", n2Val => n2Val <= n1)
                  .cover(20, ">", n2Val => n2Val > n1)
                  .cover(20, ">=", n2Val => n2Val >= n1)
        } yield {
          val input1 = Millis.unsafeFrom(n1)
          val input2 = Millis.unsafeFrom(n2)

          val expected = cats.Order[Int].compare(n1, n2)
          val actual   = cats.Order[Millis].compare(input1, input2)

          Result.diffNamed(
            "cats.Order[Millis].compare(input1, input2) === cats.Order[Int].compare(n1, n2)",
            actual,
            expected,
          )(_ === _)

        }

      def testOrderEqualCase: Property =
        for {
          n1 <- Gen.int(Range.linear(0, 999)).log("n1")
          n2 <- Gen.constant(n1).log("n2")
        } yield {
          val input1 = Millis.unsafeFrom(n1)
          val input2 = Millis.unsafeFrom(n2)

          val expected = 0
          val actual   = cats.Order[Millis].compare(input1, input2)

          Result.diffNamed(
            "cats.Order[Millis].compare(Millis(n1), Millis(n2)) where n1 === n2 should return 0",
            actual,
            expected,
          )(_ === _)
        }

      def testShow: Property =
        for {
          millis <- Gen.int(Range.linear(0, 999)).log("millis")
        } yield {
          val input = Millis.unsafeFrom(millis)

          val expected = millis.toString
          val actual   = input.show

          actual ==== expected
        }
    }

  }
}
