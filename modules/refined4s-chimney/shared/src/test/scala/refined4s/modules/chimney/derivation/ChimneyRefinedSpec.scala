package refined4s.modules.chimney.derivation

import hedgehog.*
import hedgehog.extra.refined4s.gens.{NumGens, StringGens}
import hedgehog.runner.*
import io.scalaland.chimney
import refined4s.types.all.*
import refined4s.{Newtype, Refined}

/** @author Kevin Lee
  * @since 2024-08-04
  */
object ChimneyRefinedSpec extends Properties {

  override def tests: List[Test] = List(
    property("test Newtype[Refined]", testRefinedNewtype),
    property("test Refined (partial into)", testRefined),
    property("test case class (partial into)", testCaseClassPartial),
    property("test case class with Refined to non-Refined", testCaseClassWithRefinedToNonRefined),
    property("test case class with Refined[A] to Newtype[Refined[A]]", testCaseClassWithRefinedToNewtypeRefined),
  )

  def testRefinedNewtype: Property = {
    import TestTypes1.*
    for {
      idNum <- NumGens.genPosIntMaxTo(PosInt(Int.MaxValue)).log("idNum")
      id = Foo.Id(idNum)
    } yield {
      import io.scalaland.chimney.dsl.*
      val expected = Bar.Code(idNum)

      val actual = id.into[Bar.Code].transform
      expected ==== actual
    }
  }

  def testRefined: Property = {
    import TestTypes1.*

    for {
      usernameString <- Gen.string(Gen.alpha, Range.linear(3, 10)).log("usernameString")
      domainString   <- Gen.string(Gen.alpha, Range.linear(2, 3)).list(Range.linear(2, 3)).map(_.mkString(".")).log("domainString")
      emailString = s"$usernameString@$domainString"

    } yield {
      import io.scalaland.chimney.dsl.*
      val expected = chimney.partial.Result.fromValue(Bar.Email.unsafeFrom(emailString))

      val email: Foo.Email = Foo.Email.unsafeFrom(emailString)

      val actual = email.intoPartial[Bar.Email].transform
      expected ==== actual
    }
  }

  def testRefinedRefined: Property = {
    import TestTypes1.*

    for {
      noteString <- StringGens.genNonEmptyStringMinMax(Gen.alphaNum, PosInt(3), PosInt(20)).log("noteString")
      note = Foo.Note.unsafeFrom(noteString)
    } yield {
      import io.scalaland.chimney.dsl.*
      val expected = chimney.partial.Result.fromValue(Bar.Note.unsafeFrom(noteString))

      val actual = note.intoPartial[Bar.Note].transform
      expected ==== actual
    }
  }

  def testCaseClassPartial: Property = {
    import TestTypes1.*

    for {
      idNum <- NumGens.genPosIntMaxTo(PosInt(Int.MaxValue)).log("idNum")

      usernameString <- Gen.string(Gen.alpha, Range.linear(3, 10)).log("usernameString")
      domainString   <- Gen.string(Gen.alpha, Range.linear(2, 3)).list(Range.linear(2, 3)).map(_.mkString(".")).log("domainString")
      emailString = s"$usernameString@$domainString"

      noteString <- StringGens.genNonEmptyStringMinMax(Gen.alphaNum, PosInt(3), PosInt(20)).log("noteString")

      id    = Foo.Id(idNum)
      email = Foo.Email.unsafeFrom(emailString)
      note  = Foo.Note.unsafeFrom(noteString)
    } yield {
      import io.scalaland.chimney.dsl.*

      val expected =
        chimney.partial.Result.fromValue(Bar(Bar.Code(idNum), Bar.Baz(Bar.Email.unsafeFrom(emailString)), Bar.Note.unsafeFrom(noteString)))

      val actual = Foo(id, Foo.Baz(email), note).intoPartial[Bar].transform
      expected ==== actual
    }
  }

  def testCaseClassWithRefinedToNonRefined: Property = {
    import TestTypes2.*

    for {
      nonEmptyString <- StringGens.genNonEmptyString(Gen.alpha, PosInt(10)).log("nonEmptyString")
      name           <- Gen.constant(Person.NotEmptyStr.unsafeFrom(nonEmptyString.value)).log("name")

      person <- Gen.constant(Person(name)).log("person")

    } yield {
      import io.scalaland.chimney.dsl.*

      val expected = User(nonEmptyString.value)
      val actual   = person.into[User].transform

      expected ==== actual
    }
  }

  def testCaseClassWithRefinedToNewtypeRefined: Property = {
    import TestTypes3.*

    for {
      nonEmptyString <- StringGens.genNonEmptyString(Gen.alpha, PosInt(10)).log("nonEmptyString")

      name <- Gen.constant(XMen.NotEmptyStr.unsafeFrom(nonEmptyString.value)).log("name")
      xMen <- Gen.constant(XMen(name)).log("xMen")
    } yield {
      import io.scalaland.chimney.dsl.*

      val expected = chimney.partial.Result.fromValue(MarvelCharacter(MarvelCharacter.Name(nonEmptyString)))
      val actual   = xMen.intoPartial[MarvelCharacter].transform

      expected ==== actual
    }
  }

  object TestTypes1 {
    val emailRegEx =
      """([a-zA-Z0-9]+([-_\.\+]+[a-zA-Z0-9]+)*@[a-zA-Z0-9]+([-_]+[a-zA-Z0-9]+)*(?:[.][a-zA-Z0-9]+([-_]+[a-zA-Z0-9]+)*)+)""".r

    final case class Foo(id: Foo.Id, baz: Foo.Baz, note: Foo.Note)
    object Foo {

      type Id = Id.Type
      object Id extends Newtype[PosInt], ChimneyNewtype[PosInt]

      type Email = Email.Type
      object Email extends Refined[String], ChimneyRefined[String] {

        override def invalidReason(a: String): String = s"Invalid email: $a"

        override def predicate(a: String): Boolean = emailRegEx.findFirstMatchIn(a).isDefined
      }

      type Note = Note.Type
      object Note extends Refined[NonEmptyString], ChimneyRefined[NonEmptyString] {
        override def invalidReason(a: NonEmptyString): String =
          s"It should be more than 2 chars. The length of the given String=${a.value.length}"

        override def predicate(a: NonEmptyString): Boolean = a.value.length > 2
      }

      final case class Baz(email: Email)
    }

    final case class Bar(id: Bar.Code, baz: Bar.Baz, note: Bar.Note)
    object Bar {

      type Code = Code.Type
      object Code extends Newtype[PosInt], ChimneyNewtype[PosInt]

      type Email = Email.Type
      object Email extends Refined[String], ChimneyRefined[String] {

        override def invalidReason(a: String): String = s"Invalid email: $a"

        override def predicate(a: String): Boolean = emailRegEx.findFirstMatchIn(a).isDefined
      }

      type Note = Note.Type
      object Note extends Refined[NonEmptyString], ChimneyRefined[NonEmptyString] {
        override def invalidReason(a: NonEmptyString): String =
          s"It should be more than 2 chars. The length of the given String=${a.value.length}"

        override def predicate(a: NonEmptyString): Boolean = a.value.length > 2
      }

      final case class Baz(email: Email)
    }
  }

  object TestTypes2 {
    final case class Person(name: Person.NotEmptyStr)

    object Person {
      type NotEmptyStr = NotEmptyStr.Type
      @SuppressWarnings(Array("org.wartremover.warts.Equals"))
      object NotEmptyStr extends Refined[String], ChimneyRefined[String] {
        inline def invalidReason(a: String): String = "non-empty String"

        inline def predicate(a: String): Boolean = a != ""
      }
    }

    final case class User(name: String)
  }

  object TestTypes3 {

    final case class XMen(name: XMen.NotEmptyStr)
    object XMen {
      type NotEmptyStr = NotEmptyStr.Type
      @SuppressWarnings(Array("org.wartremover.warts.Equals"))
      object NotEmptyStr extends Refined[String], ChimneyRefined[String] {
        inline def invalidReason(a: String): String = "non-empty String"

        inline def predicate(a: String): Boolean = a != ""
      }
    }

    import refined4s.types.all.*

    final case class MarvelCharacter(name: MarvelCharacter.Name)
    object MarvelCharacter {
      type Name = Name.Type

      object Name extends Newtype[NonEmptyString], ChimneyNewtype[NonEmptyString]
    }

  }

}
