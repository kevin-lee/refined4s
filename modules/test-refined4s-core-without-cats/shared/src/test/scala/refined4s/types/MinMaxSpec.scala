package refined4s.types

import hedgehog.*
import hedgehog.runner.*
import refined4s.*
import refined4s.types.numeric.*

/** @author Kevin Lee
  * @since 2026-02-02
  */
object MinMaxSpec extends Properties {
  import TestTypes.*

  override def tests: List[Test] =
    testMinInt ++
      testMaxInt ++
      testMinMaxInt ++
      testMinIntWithVal ++
      testMaxIntWithVal ++
      testMinMaxIntWithVal ++
      testMinLong ++
      testMaxLong ++
      testMinMaxLong ++
      testMinLongWithVal ++
      testMaxLongWithVal ++
      testMinMaxLongWithVal

  def testMinInt: List[Test] = checkMin(
    "TestMinInt",
    TestMinInt,
    Gen.int(Range.linear(10, 1000)).map(TestMinInt.unsafeFrom),
    _.value,
    10,
  )

  def testMaxInt: List[Test] = checkMax(
    "TestMaxInt",
    TestMaxInt,
    Gen.int(Range.linear(-1000, 100)).map(TestMaxInt.unsafeFrom),
    _.value,
    100,
  )

  def testMinMaxInt: List[Test] = checkMinMax(
    "TestMinMaxInt",
    TestMinMaxInt,
    Gen.int(Range.linear(10, 100)).map(TestMinMaxInt.unsafeFrom),
    _.value,
    10,
    100,
  )

  ///
  def testMinIntWithVal: List[Test] = checkMin(
    "TestMinIntWithVal",
    TestMinIntWithVal,
    Gen.int(Range.linear(10, 1000)).map(TestMinIntWithVal.unsafeFrom),
    _.value,
    10,
  )

  def testMaxIntWithVal: List[Test] = checkMax(
    "TestMaxIntWithVal",
    TestMaxIntWithVal,
    Gen.int(Range.linear(-1000, 100)).map(TestMaxIntWithVal.unsafeFrom),
    _.value,
    100,
  )

  def testMinMaxIntWithVal: List[Test] = checkMinMax(
    "TestMinMaxIntWithVal",
    TestMinMaxIntWithVal,
    Gen.int(Range.linear(10, 100)).map(TestMinMaxIntWithVal.unsafeFrom),
    _.value,
    10,
    100,
  )

  ///
  def testMinLong: List[Test] = checkMin(
    "TestMinLong",
    TestMinLong,
    Gen.long(Range.linear(10L, 1000L)).map(TestMinLong.unsafeFrom),
    _.value,
    10L,
  )

  def testMaxLong: List[Test] = checkMax(
    "TestMaxLong",
    TestMaxLong,
    Gen.long(Range.linear(-1000L, 100L)).map(TestMaxLong.unsafeFrom),
    _.value,
    100L,
  )

  def testMinMaxLong: List[Test] = checkMinMax(
    "TestMinMaxLong",
    TestMinMaxLong,
    Gen.long(Range.linear(10L, 100L)).map(TestMinMaxLong.unsafeFrom),
    _.value,
    10L,
    100L,
  )

  ///
  def testMinLongWithVal: List[Test] = checkMin(
    "TestMinLongWithVal",
    TestMinLongWithVal,
    Gen.long(Range.linear(10L, 1000L)).map(TestMinLongWithVal.unsafeFrom),
    _.value,
    10L,
  )

  def testMaxLongWithVal: List[Test] = checkMax(
    "TestMaxLongWithVal",
    TestMaxLongWithVal,
    Gen.long(Range.linear(-1000L, 100L)).map(TestMaxLongWithVal.unsafeFrom),
    _.value,
    100L,
  )

  def testMinMaxLongWithVal: List[Test] = checkMinMax(
    "TestMinMaxLongWithVal",
    TestMinMaxLongWithVal,
    Gen.long(Range.linear(10L, 100L)).map(TestMinMaxLongWithVal.unsafeFrom),
    _.value,
    10L,
    100L,
  )

  /* Test helpers */

  private def checkMin[A: Ordering, T](
    name: String,
    min: Min[A] { type Type = T },
    gen: Gen[T],
    toValue: T => A,
    expectedMin: A,
  ): List[Test] = {
    List(
      example(s"test $name.min", Result.diff(toValue(min.min), expectedMin)(implicitly[Ordering[A]].equiv)),
      example(s"test $name.MinValue", Result.diff(toValue(min.MinValue), expectedMin)(implicitly[Ordering[A]].equiv)),
      property(
        s"test $name min property",
        gen.log("n").map { n =>
          Result
            .diff(toValue(n), toValue(min.min))(implicitly[Ordering[A]].gteq)
            .log(s"value: ${toValue(n)}, min: ${toValue(min.min)}")
        },
      ),
    )
  }

  private def checkMax[A: Ordering, T](
    name: String,
    max: Max[A] { type Type = T },
    gen: Gen[T],
    toValue: T => A,
    expectedMax: A,
  ): List[Test] = {
    List(
      example(s"test $name.max", Result.diff(toValue(max.max), expectedMax)(implicitly[Ordering[A]].equiv)),
      example(s"test $name.MaxValue", Result.diff(toValue(max.MaxValue), expectedMax)(implicitly[Ordering[A]].equiv)),
      property(
        s"test $name max property",
        gen.log("n").map { n =>
          Result
            .diff(toValue(n), toValue(max.max))(implicitly[Ordering[A]].lteq)
            .log(s"value: ${toValue(n)}, max: ${toValue(max.max)}")
        },
      ),
    )
  }

  private def checkMinMax[A: Ordering, T](
    name: String,
    minMax: MinMax[A] { type Type = T },
    gen: Gen[T],
    toValue: T => A,
    expectedMin: A,
    expectedMax: A,
  ): List[Test] =
    checkMin(name, minMax, gen, toValue, expectedMin) ++
      checkMax(name, minMax, gen, toValue, expectedMax)

  object TestTypes {

    // Custom types for testing
    type TestMinInt = TestMinInt.Type
    object TestMinInt extends Refined[Int] with Min[Int] {
      override inline def invalidReason(a: Int): String = "It must be >= 10"

      override inline def predicate(a: Int): Boolean = a >= 10

      override def min: Type = apply(10)
    }

    type TestMaxInt = TestMaxInt.Type
    object TestMaxInt extends Refined[Int] with Max[Int] {
      override inline def invalidReason(a: Int): String = "It must be <= 100"

      override inline def predicate(a: Int): Boolean = a <= 100

      override def max: Type = apply(100)
    }

    type TestMinMaxInt = TestMinMaxInt.Type
    object TestMinMaxInt extends Refined[Int] with MinMax[Int] {
      override inline def invalidReason(a: Int): String = "It must be >= 10 && <= 100"

      override inline def predicate(a: Int): Boolean = a >= 10 && a <= 100

      override def min: Type = apply(10)

      override def max: Type = apply(100)
    }

    ///

    type TestMinIntWithVal = TestMinIntWithVal.Type
    object TestMinIntWithVal extends Refined[Int] with Min[Int] {
      override inline def invalidReason(a: Int): String = "It must be >= 10"

      override inline def predicate(a: Int): Boolean = a >= 10

      override val min: Type = apply(10)
    }

    type TestMaxIntWithVal = TestMaxIntWithVal.Type
    object TestMaxIntWithVal extends Refined[Int] with Max[Int] {
      override inline def invalidReason(a: Int): String = "It must be <= 100"

      override inline def predicate(a: Int): Boolean = a <= 100

      override val max: Type = apply(100)
    }

    type TestMinMaxIntWithVal = TestMinMaxIntWithVal.Type
    object TestMinMaxIntWithVal extends Refined[Int] with MinMax[Int] {
      override inline def invalidReason(a: Int): String = "It must be >= 10 && <= 100"

      override inline def predicate(a: Int): Boolean = a >= 10 && a <= 100

      override val min: Type = apply(10)
      override val max: Type = apply(100)
    }

    ///

    type TestMinLong = TestMinLong.Type

    object TestMinLong extends Refined[Long] with Min[Long] {
      override inline def invalidReason(a: Long): String = "It must be >= 10"

      override inline def predicate(a: Long): Boolean = a >= 10L

      override def min: Type = apply(10L)
    }

    type TestMaxLong = TestMaxLong.Type

    object TestMaxLong extends Refined[Long] with Max[Long] {
      override inline def invalidReason(a: Long): String = "It must be <= 100"

      override inline def predicate(a: Long): Boolean = a <= 100L

      override def max: Type = apply(100L)
    }

    type TestMinMaxLong = TestMinMaxLong.Type

    object TestMinMaxLong extends Refined[Long] with MinMax[Long] {
      override inline def invalidReason(a: Long): String = "It must be >= 10 && <= 100"

      override inline def predicate(a: Long): Boolean = a >= 10L && a <= 100L

      override def min: Type = apply(10L)
      override def max: Type = apply(100L)
    }

    ///

    type TestMinLongWithVal = TestMinLongWithVal.Type

    object TestMinLongWithVal extends Refined[Long] with Min[Long] {
      override inline def invalidReason(a: Long): String = "It must be >= 10"

      override inline def predicate(a: Long): Boolean = a >= 10L

      override val min: Type = apply(10L)
    }

    type TestMaxLongWithVal = TestMaxLongWithVal.Type

    object TestMaxLongWithVal extends Refined[Long] with Max[Long] {
      override inline def invalidReason(a: Long): String = "It must be <= 100"

      override inline def predicate(a: Long): Boolean = a <= 100L

      override val max: Type = apply(100L)
    }

    type TestMinMaxLongWithVal = TestMinMaxLongWithVal.Type

    object TestMinMaxLongWithVal extends Refined[Long] with MinMax[Long] {
      override inline def invalidReason(a: Long): String = "It must be >= 10 && <= 100"

      override inline def predicate(a: Long): Boolean = a >= 10L && a <= 100L

      override val min: Type = apply(10L)
      override val max: Type = apply(100L)
    }

  }
}
