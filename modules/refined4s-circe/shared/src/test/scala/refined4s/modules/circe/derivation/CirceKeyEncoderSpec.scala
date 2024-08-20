package refined4s.modules.circe.derivation

import hedgehog.*
import hedgehog.runner.*
import io.circe.*
import io.circe.syntax.*
import refined4s.*

/** @author Kevin Lee
  * @since 2023-12-11
  */
object CirceKeyEncoderSpec extends Properties {
  override def tests: List[Test] = List(
    property("test CirceKeyEncoder for Newtype", testNewtypeEncoder),
    property("test CirceKeyEncoder for Refined", testRefinedEncoder),
    property("test CirceKeyEncoder for Newtype(Refined)", testRefinedNewtypeEncoder),
    property("test CirceKeyEncoder for InlinedRefined", testInlinedRefinedEncoder),
    property("test CirceKeyEncoder for Newtype(InlinedRefined)", testInlinedRefinedNewtypeEncoder),
  )

  def testNewtypeEncoder: Property =
    for {
      ss    <- Gen
                 .string(Gen.unicode, Range.linear(1, 10))
                 .list(Range.linear(1, 10))
                 .log("ss")
      ns    <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(ss.length)).log("ns")
      map   <- Gen.constant(ss.zip(ns).toMap).log("map")
      input <- Gen.constant(map.map { case (key, value) => MyNewtype(key) -> value }).log("input")
    } yield {
      val expected = Json.fromFields(map.map { case (key, value) => key -> value.asJson })
      val actual   = input.asJson
      Result.all(
        List(
          actual ==== expected,
          actual.noSpaces ==== expected.noSpaces,
        )
      )
    }

  def testRefinedEncoder: Property =
    for {
      ss    <- Gen
                 .string(Gen.unicode, Range.linear(1, 10))
                 .list(Range.linear(1, 10))
                 .log("ss")
      ns    <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(ss.length)).log("ns")
      map   <- Gen.constant(ss.zip(ns).toMap).log("map")
      input <- Gen.constant(map.map { case (key, value) => MyRefinedType.unsafeFrom(key) -> value }).log("input")
    } yield {
      val expected = Json.fromFields(map.map { case (key, value) => key -> value.asJson })
      val actual   = input.asJson
      Result.all(
        List(
          actual ==== expected,
          actual.noSpaces ==== expected.noSpaces,
        )
      )
    }

  def testRefinedNewtypeEncoder: Property =
    for {
      ss    <- Gen
                 .string(Gen.unicode, Range.linear(1, 10))
                 .list(Range.linear(1, 10))
                 .log("ss")
      ns    <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(ss.length)).log("ns")
      map   <- Gen.constant(ss.zip(ns).toMap).log("map")
      input <- Gen.constant(map.map { case (key, value) => MyRefinedNewtype(MyRefinedType.unsafeFrom(key)) -> value }).log("input")
    } yield {
      val expected = Json.fromFields(map.map { case (key, value) => key -> value.asJson })
      val actual   = input.asJson
      Result.all(
        List(
          actual ==== expected,
          actual.noSpaces ==== expected.noSpaces,
        )
      )
    }

  def testInlinedRefinedEncoder: Property =
    for {
      ss    <- Gen
                 .string(Gen.unicode, Range.linear(1, 10))
                 .list(Range.linear(1, 10))
                 .log("ss")
      ns    <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(ss.length)).log("ns")
      map   <- Gen.constant(ss.zip(ns).toMap).log("map")
      input <- Gen.constant(map.map { case (key, value) => MyInlinedRefinedType.unsafeFrom(key) -> value }).log("input")
    } yield {
      val expected = Json.fromFields(map.map { case (key, value) => key -> value.asJson })
      val actual   = input.asJson
      Result.all(
        List(
          actual ==== expected,
          actual.noSpaces ==== expected.noSpaces,
        )
      )
    }

  def testInlinedRefinedNewtypeEncoder: Property =
    for {
      ss    <- Gen
                 .string(Gen.unicode, Range.linear(1, 10))
                 .list(Range.linear(1, 10))
                 .log("ss")
      ns    <- Gen.int(Range.linear(0, Int.MaxValue)).list(Range.singleton(ss.length)).log("ns")
      map   <- Gen.constant(ss.zip(ns).toMap).log("map")
      input <-
        Gen.constant(map.map { case (key, value) => MyInlinedRefinedNewtype(MyInlinedRefinedType.unsafeFrom(key)) -> value }).log("input")
    } yield {
      val expected = Json.fromFields(map.map { case (key, value) => key -> value.asJson })
      val actual   = input.asJson
      Result.all(
        List(
          actual ==== expected,
          actual.noSpaces ==== expected.noSpaces,
        )
      )
    }

  type MyNewtype = MyNewtype.Type
  object MyNewtype extends Newtype[String], CirceKeyEncoder[String]

  type MyRefinedType = MyRefinedType.Type
  @SuppressWarnings(Array("org.wartremover.warts.Equals"))
  object MyRefinedType extends Refined[String], CirceKeyEncoder[String], CirceEncoder[String] {
    override inline def invalidReason(a: String): String =
      "It has to be a non-empty String but got \"" + a + "\""

    override inline def predicate(a: String): Boolean = a != ""
  }

  type MyRefinedNewtype = MyRefinedNewtype.Type
  object MyRefinedNewtype extends Newtype[MyRefinedType], CirceKeyEncoder[MyRefinedType], CirceEncoder[MyRefinedType]

  type MyInlinedRefinedType = MyInlinedRefinedType.Type
  @SuppressWarnings(Array("org.wartremover.warts.Equals"))
  object MyInlinedRefinedType extends InlinedRefined[String], CirceKeyEncoder[String], CirceEncoder[String] {

    override inline val inlinedExpectedValue = "a non-empty String"

    override inline def invalidReason(a: String): String =
      "It has to be a non-empty String but got \"" + a + "\""

    override inline def predicate(a: String): Boolean = a != ""

    override inline def inlinedPredicate(inline a: String): Boolean = a != ""
  }

  type MyInlinedRefinedNewtype = MyInlinedRefinedNewtype.Type
  object MyInlinedRefinedNewtype
      extends Newtype[MyInlinedRefinedType],
        CirceKeyEncoder[MyInlinedRefinedType],
        CirceEncoder[MyInlinedRefinedType]

}
