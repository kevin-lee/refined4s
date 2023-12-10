package refined4s.modules.cats.derivation

import cats.*
import cats.syntax.all.*
import hedgehog.*
import hedgehog.runner.*
import refined4s.*
import refined4s.modules.cats.derivation.CatsShowSpec.{MyRefinedNewtype, MyRefinedType}

/** @author Kevin Lee
  * @since 2023-12-07
  */
object CatsEqShowSpec extends Properties {
  override def tests: List[Test] = List(
    property("Given Newtype with CatsEq. Newtype[A](a) === Newtype[A](a) should return true", testEqNewtypeSame),
    property(
      "Given Newtype with CatsEq. Newtype[A](a) =!= Newtype[A](not a) should return true",
      testEqNewtypeDifferent,
    ),
    property(
      "Given Refined with CatsEq. Refined[A](a) === Refined[A](a) should return true",
      testEqRefinedSame,
    ),
    property(
      "Given Refined with CatsEq. Refined[A](a) =!= Refined[A](not a) should return true",
      testEqRefinedDifferent,
    ),
    property(
      "Given Newtype[Refined] with CatsEq. Newtype[Refined[A]](a) === Newtype[Refined[A]](a) should return true",
      testEqNewtypeRefinedSame,
    ),
    property(
      "Given Newtype[Refined] with CatsEq. Newtype[Refined[A]](a) =!= Newtype[Refined[A]](not a) should return true",
      testEqNewtypeRefinedDifferent,
    ),
    property(
      "Given Newtype with CatsShow. Newtype[A].show(a) should return the same String as Show[A].show(a)",
      testShowNewtype,
    ),
    property(
      "Given Refined with CatsShow. Refined[A].show(a) should return the same String as Show[A].show(a)",
      testShowRefined,
    ),
    property(
      "Given Newtype[Refined] with CatsShow. Newtype[Refined[A]].show(a) should return the same String as Show[A].show(a)",
      testShowNewtypeRefined,
    ),
  )

  def testEqNewtypeSame: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(0, 10)).log("s")
    } yield {
      val a = MyNewtype(s)
      val b = a
      Result.diffNamed("MyNewtype(value) === MyNewtype(value)", a, b)(_ === _)
    }

  def testEqNewtypeDifferent: Property =
    for {
      s1 <- Gen.string(Gen.unicode, Range.linear(0, 10)).log("s1")
      s2 <- Gen.string(Gen.unicode, Range.linear(0, 10)).map(s1 + "-" + _).log("s2")
    } yield {
      val a = MyNewtype(s1)
      val b = MyNewtype(s2)
      Result.diffNamed("MyNewtype(value) =!= MyNewtype(value)", a, b)(_ =!= _)
    }

  def testEqRefinedSame: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
    } yield {
      val a = MyRefinedType.unsafeFrom(s)
      val b = a
      Result.diffNamed(s"MyRefinedType(${a.value}) === MyRefinedType(${b.value})", a, b)(_ === _)
    }

  def testEqRefinedDifferent: Property =
    for {
      s1 <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s1")
      s2 <- Gen.string(Gen.unicode, Range.linear(1, 10)).map(s1 + "-" + _).log("s2")
    } yield {
      val a = MyRefinedType.unsafeFrom(s1)
      val b = MyRefinedType.unsafeFrom(s2)
      Result.diffNamed(s"MyRefinedType(${a.value}) =!= MyRefinedType(${b.value})", a, b)(_ =!= _)
    }

  def testEqNewtypeRefinedSame: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
    } yield {
      val a = MyRefinedNewtype(MyRefinedType.unsafeFrom(s))
      val b = a
      Result.diffNamed(
        s"MyRefinedNewtype(MyRefinedType(${a.value})) === MyRefinedNewtype(MyRefinedType(${b.value}))",
        a,
        b,
      )(_ === _)
    }

  def testEqNewtypeRefinedDifferent: Property =
    for {
      s1 <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s1")
      s2 <- Gen.string(Gen.unicode, Range.linear(1, 10)).map(s1 + "-" + _).log("s2")
    } yield {
      val a = MyRefinedNewtype(MyRefinedType.unsafeFrom(s1))
      val b = MyRefinedNewtype(MyRefinedType.unsafeFrom(s2))
      Result.diffNamed(
        s"MyRefinedNewtype(MyRefinedType(${a.value})) =!= MyRefinedNewtype(MyRefinedType(${b.value}))",
        a,
        b,
      )(_ =!= _)
    }

  def testShowNewtype: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(0, 10)).log("s")
    } yield {
      val a = MyNewtype(s)
      a.show ==== Show[String].show(s)
    }

  def testShowRefined: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
    } yield {
      val a = MyRefinedType.unsafeFrom(s)
      a.show ==== Show[String].show(s)
    }

  def testShowNewtypeRefined: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
    } yield {
      val a = MyRefinedNewtype(MyRefinedType.unsafeFrom(s))
      a.show ==== Show[String].show(s)
    }

  type MyNewtype = MyNewtype.Type
  object MyNewtype extends Newtype[String] with CatsEqShow[String]

  type MyRefinedType = MyRefinedType.Type
  @SuppressWarnings(Array("org.wartremover.warts.Equals"))
  object MyRefinedType extends Refined[String] with CatsEqShow[String] {
    override inline def invalidReason(a: String): String =
      "It has to be a non-empty String but got \"" + a + "\""

    override inline def predicate(a: String): Boolean = a != ""
  }

  type MyRefinedNewtype = MyRefinedNewtype.Type
  object MyRefinedNewtype extends Newtype[MyRefinedType] with CatsEqShow[MyRefinedType]

}
