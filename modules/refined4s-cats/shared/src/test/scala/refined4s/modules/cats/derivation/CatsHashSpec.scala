package refined4s.modules.cats.derivation

import cats.*
import cats.syntax.all.*
import hedgehog.*
import hedgehog.runner.*
import refined4s.*

/** @author Kevin Lee
  * @since 2023-12-07
  */
object CatsHashSpec extends Properties {
  override def tests: List[Test] = List(
    property(
      "Given Newtype with CatsHash. Newtype[A](a).hash === Newtype[A](a).hash should return true",
      testHashNewtypeSame,
    ),
    property(
      "Given Newtype with CatsHash. Newtype[A](a).hash =!= Newtype[A](not a).hash should return true",
      testHashNewtypeDifferent,
    ),
    property(
      "Given Refined with CatsHash. Refined[A](a).hash === Refined[A](a).hash should return true",
      testHashRefinedSame,
    ),
    property(
      "Given Refined with CatsHash. Refined[A](a).hash =!= Refined[A](not a).hash should return true",
      testHashRefinedDifferent,
    ),
    property(
      "Given Newtype[Refined] with CatsHash. Newtype[Refined[A]](a).hash === Newtype[Refined[A]](a).hash should return true",
      testHashNewtypeRefinedSame,
    ),
    property(
      "Given Newtype[Refined] with CatsHash. Newtype[Refined[A]](a).hash =!= Newtype[Refined[A]](not a).hash should return true",
      testHashNewtypeRefinedDifferent,
    ),
  )

  def testHashNewtypeSame: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(0, 10)).log("s")
    } yield {
      val a = MyNewtype(s)
      val b = a
      Result.diffNamed("MyNewtype(value.hash === MyNewtype(value).hash", a.hash, b.hash)(_ === _)
    }

  def testHashNewtypeDifferent: Property =
    for {
      s1 <- Gen.string(Gen.unicode, Range.linear(0, 10)).log("s1")
      s2 <- Gen.string(Gen.unicode, Range.linear(0, 10)).map(s1 + "-" + _).log("s2")
    } yield {
      val a = MyNewtype(s1)
      val b = MyNewtype(s2)
      Result.diffNamed("MyNewtype(value).hash =!= MyNewtype(value).hash", a.hash, b.hash)(_ =!= _)
    }

  def testHashRefinedSame: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
    } yield {
      val a = MyRefinedType.unsafeFrom(s)
      val b = a
      Result.diffNamed(s"MyRefinedType(${a.value}).hash === MyRefinedType(${b.value}).hash", a.hash, b.hash)(_ === _)
    }

  def testHashRefinedDifferent: Property =
    for {
      s1 <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s1")
      s2 <- Gen.string(Gen.unicode, Range.linear(1, 10)).map(s1 + "-" + _).log("s2")
    } yield {
      val a = MyRefinedType.unsafeFrom(s1)
      val b = MyRefinedType.unsafeFrom(s2)
      Result.diffNamed(s"MyRefinedType(${a.value}).hash =!= MyRefinedType(${b.value}).hash", a.hash, b.hash)(_ =!= _)
    }

  def testHashNewtypeRefinedSame: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
    } yield {
      val a = MyRefinedNewtype(MyRefinedType.unsafeFrom(s))
      val b = a
      Result.diffNamed(
        s"MyRefinedNewtype(MyRefinedType(${a.value})).hash === MyRefinedNewtype(MyRefinedType(${b.value})).hash",
        a.hash,
        b.hash,
      )(_ === _)
    }

  def testHashNewtypeRefinedDifferent: Property =
    for {
      s1 <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s1")
      s2 <- Gen.string(Gen.unicode, Range.linear(1, 10)).map(s1 + "-" + _).log("s2")
    } yield {
      val a = MyRefinedNewtype(MyRefinedType.unsafeFrom(s1))
      val b = MyRefinedNewtype(MyRefinedType.unsafeFrom(s2))
      Result.diffNamed(
        s"MyRefinedNewtype(MyRefinedType(${a.value})).hash =!= MyRefinedNewtype(MyRefinedType(${b.value})).hash",
        a.hash,
        b.hash,
      )(_ =!= _)
    }

  type MyNewtype = MyNewtype.Type
  object MyNewtype extends Newtype[String] with CatsHash[String]

  type MyRefinedType = MyRefinedType.Type
  @SuppressWarnings(Array("org.wartremover.warts.Equals"))
  object MyRefinedType extends Refined[String] with CatsHash[String] {
    override inline def invalidReason(a: String): String =
      "It has to be a non-empty String but got \"" + a + "\""

    override inline def predicate(a: String): Boolean = a != ""
  }

  type MyRefinedNewtype = MyRefinedNewtype.Type
  object MyRefinedNewtype extends Newtype[MyRefinedType] with CatsHash[MyRefinedType]

}
