package refined4s

import hedgehog.*
import hedgehog.runner.*

/** @author Kevin Lee
  * @since 2023-04-24
  */
object RefinedSpec extends Properties {
  override def tests: List[Test] = List(
    example("test Refined.apply", testApply),
    property("test Refined.from(valid)", testFromValid),
    example("test Refined.from(invalid)", testFromInvalid),
    property("test Refined.unsafeFrom(valid)", testUnsafeFromValid),
    example("test Refined.unsafeFrom(invalid)", testUnsafeFromInvalid),
    property("test Refined.value", testValue),
    property("test Refined.unapply", testUnapplyWithPatternMatching),
  )

  def testApply: Result = {
    /* The actual test is whether this compiles or not actual ==== expected is meaningless here */
    val expected = MyType("blah")
    val actual   = MyType("blah")
    actual ==== expected
  }

  def testFromValid: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
    } yield {
      val expected = MyType.unsafeFrom(s)
      val actual   = MyType.from(s)
      actual ==== Right(expected)
    }

  def testFromInvalid: Result = {
    val expected = "Invalid value: []. It has to be nonEmptyString but got []"
    val actual   = MyType.from("")
    actual ==== Left(expected)
  }

  def testUnsafeFromValid: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
    } yield {
      val expected = MyType.unsafeFrom(s)
      val actual   = MyType.unsafeFrom(s)
      actual ==== expected
    }

  def testUnsafeFromInvalid: Result = {
    val expected = "Invalid value: []. It has to be nonEmptyString but got []"
    try {
      MyType.unsafeFrom("")
      Result.failure.log("""IllegalArgumentException was expected from MyType.unsafeFrom(""), but it was not thrown.""")
    } catch {
      case ex: IllegalArgumentException =>
        ex.getMessage ==== expected

    }
  }

  def testValue: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
    } yield {
      val expected = s
      val actual   = MyType.unsafeFrom(s)
      actual.value ==== expected
    }

  def testUnapplyWithPatternMatching: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
    } yield {
      val expected = s
      val myType   = MyType.unsafeFrom(s)
      myType match {
        case MyType(actual) =>
          actual ==== expected
      }
    }

  type MyType = MyType.Type
  @SuppressWarnings(Array("org.wartremover.warts.Equals"))
  object MyType extends Refined[String] {

    override transparent inline def invalidReason(a: String): String =
      "It has to be nonEmptyString but got [" + a + "]"

    override inline def predicate(a: String): Boolean = a != ""
  }
}
