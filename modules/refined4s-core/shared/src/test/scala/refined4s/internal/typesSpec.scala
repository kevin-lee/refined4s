package refined4s.internal

import hedgehog.*
import hedgehog.runner.*
import refined4s.internal.types.*
import refined4s.internal.types.Bind.*
import refined4s.internal.types.Bind.given

object typesSpec extends Properties {
  override def tests: List[Test] = List(
    property("Bind[Identity].pure(a) returns a", testBindPure),
    property("Bind[Identity].map(a)(f) returns the mapped value", testBindMap),
    property("Bind[Identity].flatMap(a)(f) returns the flat-mapped value", testBindFlatMap),
    property("Identity extension map returns the mapped value", testBindExtensionMap),
    property("Identity extension flatMap returns the flat-mapped value", testBindExtensionFlatMap),
    example("StateRef.newAtomicLongStateRef() starts at 0L", testStateRefInitialState),
    property("StateRef.set(a) updates the stored value", testStateRefSet),
    property("StateRef.updateAndGet(f) returns and persists the updated state", testStateRefUpdateAndGet),
    property("StateRef.modify(f) returns the result and persists the updated state", testStateRefModify),
    example("RandomSource.nextLong() can be called successfully", testRandomSourceNextLong),
    property("RandomSource.nextInt(upperBound) returns a value in range", testRandomSourceNextInt),
    example("TimestampSource.timestamp() returns a bounded wall-clock value", testTimestampSourceTimestamp),
  )

  def testBindPure: Property =
    for {
      n <- Gen.int(Range.linear(-1_000, 1_000)).log("n")
    } yield {
      val expected = n
      val actual   = Bind[Identity].pure(n)
      actual ==== expected
    }

  def testBindMap: Property =
    for {
      n <- Gen.int(Range.linear(-1_000, 1_000)).log("n")
    } yield {
      val f = (x: Int) => x + 1

      val actual   = Bind[Identity].map(n)(f)
      val expected = f(n)
      actual ==== expected
    }

  def testBindFlatMap: Property =
    for {
      n <- Gen.int(Range.linear(-1_000, 1_000)).log("n")
    } yield {
      val f: Int => Identity[String] = value => s"${value}!${value + 1}"

      val actual   = Bind[Identity].flatMap(n)(f)
      val expected = f(n)
      actual ==== expected
    }

  def testBindExtensionMap: Property =
    for {
      n <- Gen.int(Range.linear(-1_000, 1_000)).log("n")
    } yield {
      val fa: Identity[Int] = n

      val f = (x: Int) => x + 1

      val actual   = fa.map(f)
      val expected = f(n)
      actual ==== expected
    }

  def testBindExtensionFlatMap: Property =
    for {
      n <- Gen.int(Range.linear(-1_000, 1_000)).log("n")
    } yield {
      val fa: Identity[Int] = n

      val f: Int => Identity[String] = value => s"${value}!${value + 1}"

      val actual   = fa.flatMap(f)
      val expected = f(n)

      actual ==== expected
    }

  def testStateRefInitialState: Result = {
    val stateRef = StateRef.newAtomicLongStateRef()
    stateRef.get ==== 0L
  }

  def testStateRefSet: Property =
    for {
      n <- Gen.long(Range.linear(-1_000_000L, 1_000_000L)).log("n")
    } yield {
      val stateRef = StateRef.newAtomicLongStateRef()
      stateRef.set(n)
      stateRef.get ==== n
    }

  def testStateRefUpdateAndGet: Property =
    for {
      initial <- Gen.long(Range.linear(-1_000_000L, 1_000_000L)).log("initial")
      delta   <- Gen.long(Range.linear(-1_000L, 1_000L)).log("delta")
    } yield {
      val stateRef = StateRef.newAtomicLongStateRef()
      stateRef.set(initial)

      val expectedState = initial + delta

      val actual = stateRef.updateAndGet(_ + delta)

      Result.all(
        List(
          actual ==== expectedState,
          stateRef.get ==== expectedState,
        )
      )
    }

  def testStateRefModify: Property =
    for {
      initial <- Gen.long(Range.linear(-1_000_000L, 1_000_000L)).log("initial")
      delta   <- Gen.long(Range.linear(-1_000L, 1_000L)).log("delta")
    } yield {
      val stateRef = StateRef.newAtomicLongStateRef()
      stateRef.set(initial)

      val expectedState  = initial + delta
      val expectedResult = s"${initial}->${expectedState}"

      val actual = stateRef.modify(current => (current + delta, s"${current}->${current + delta}"))

      Result.all(
        List(
          actual ==== expectedResult,
          stateRef.get ==== expectedState,
        )
      )
    }

  def testRandomSourceNextLong: Result = {
    val randomSource = RandomSource.newDefaultRandomSource()
    val actual       = randomSource.nextLong()

    Result.assert(actual.toString.nonEmpty).log(s"nextLong() returned an empty string representation: $actual")
  }

  def testRandomSourceNextInt: Property =
    for {
      upperBound <- Gen.int(Range.linear(1, 10_000)).log("upperBound")
    } yield {
      val randomSource = RandomSource.newDefaultRandomSource()
      val actual       = randomSource.nextInt(upperBound)

      Result.all(
        List(
          Result.assert(actual >= 0).log(s"Expected nextInt($upperBound) >= 0 but got $actual"),
          Result.assert(actual < upperBound).log(s"Expected nextInt($upperBound) < $upperBound but got $actual"),
        )
      )
    }

  def testTimestampSourceTimestamp: Result = {
    val timestampSource = TimestampSource.newDefaultTimestampSource()
    val before          = System.currentTimeMillis()
    val actual          = timestampSource.timestamp()
    val after           = System.currentTimeMillis()

    Result.all(
      List(
        Result.assert(actual >= before).log(s"Expected timestamp $actual to be >= $before"),
        Result.assert(actual <= after).log(s"Expected timestamp $actual to be <= $after"),
      )
    )
  }
}
