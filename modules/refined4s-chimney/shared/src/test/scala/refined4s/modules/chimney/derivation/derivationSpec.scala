package refined4s.modules.chimney.derivation

import hedgehog.*
import hedgehog.extra.refined4s.gens.{NumGens, StringGens}
import hedgehog.runner.*
import io.scalaland.chimney
import refined4s.types.all
import refined4s.types.all.*
import refined4s.{Newtype, Refined}

/** @author Kevin Lee
  * @since 2024-08-04
  */
object derivationSpec extends Properties {

  override def tests: List[Test] = List(
    property("test Newtype", testNewtype),
    property("test Newtype[Refined]", testRefinedNewtype),
    property("test Refined (partial into)", testRefined),
    property("test Refined[Refined] (partial into)", testRefinedRefined),
    property("test case class", testCaseClass),
    property("test case class (partial into)", testCaseClassPartial),
  )

  def testNewtype: Property =
    for {
      nameString <- Gen
                      .string(Gen.alpha, Range.linear(1, 10))
                      .list(Range.singleton(2))
                      .map(_.mkString(" "))
                      .log("nameString")
      name = Foo.Name(nameString)
    } yield {
      import io.scalaland.chimney.dsl.*
      val expected = Bar.Label(nameString)

      val actual = name.into[Bar.Label].transform
      expected ==== actual
    }

  def testRefinedNewtype: Property =
    for {
      idNum <- NumGens.genPosIntMaxTo(PosInt(Int.MaxValue)).log("idNum")
      id = Foo.Id(idNum)
    } yield {
      import io.scalaland.chimney.dsl.*
      val expected = Bar.Code(idNum)

      val actual = id.into[Bar.Code].transform
      expected ==== actual
    }

  def testRefined: Property =
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

  def testRefinedRefined: Property =
    for {
      noteString <- StringGens.genNonEmptyStringMinMax(Gen.alphaNum, PosInt(3), PosInt(20)).log("noteString")
      note = Foo.Note.unsafeFrom(noteString)
    } yield {
      import io.scalaland.chimney.dsl.*
      val expected = chimney.partial.Result.fromValue(Bar.Note.unsafeFrom(noteString))

      val actual = note.intoPartial[Bar.Note].transform
      expected ==== actual
    }

  def testCaseClass: Property =
    for {
      idNum      <- NumGens.genPosIntMaxTo(PosInt(Int.MaxValue)).log("idNum")
      nameString <- Gen
                      .string(Gen.alpha, Range.linear(1, 10))
                      .list(Range.singleton(2))
                      .map(_.mkString(" "))
                      .log("nameString")

      id   = Something.Id(idNum)
      name = Something.Name(nameString)
    } yield {
      import io.scalaland.chimney.dsl.*

      val expected = SomethingElse(SomethingElse.Code(idNum), SomethingElse.InnerThing(SomethingElse.Label(nameString)))

      val actual = Something(id, Something.InnerThing(name)).into[SomethingElse].transform
      expected ==== actual
    }

  def testCaseClassPartial: Property =
    for {
      idNum      <- NumGens.genPosIntMaxTo(PosInt(Int.MaxValue)).log("idNum")
      nameString <- Gen
                      .string(Gen.alpha, Range.linear(1, 10))
                      .list(Range.singleton(2))
                      .map(_.mkString(" "))
                      .log("nameString")

      usernameString <- Gen.string(Gen.alpha, Range.linear(3, 10)).log("usernameString")
      domainString   <- Gen.string(Gen.alpha, Range.linear(2, 3)).list(Range.linear(2, 3)).map(_.mkString(".")).log("domainString")
      emailString = s"$usernameString@$domainString"

      noteString <- StringGens.genNonEmptyStringMinMax(Gen.alphaNum, PosInt(3), PosInt(20)).log("noteString")

      id    = Foo.Id(idNum)
      name  = Foo.Name(nameString)
      email = Foo.Email.unsafeFrom(emailString)
      note  = Foo.Note.unsafeFrom(noteString)
    } yield {
      import io.scalaland.chimney.dsl.*

      val expected =
        chimney
          .partial
          .Result
          .fromValue(
            Bar(Bar.Code(idNum), Bar.Baz(Bar.Label(nameString), Bar.Email.unsafeFrom(emailString)), Bar.Note.unsafeFrom(noteString))
          )

      val actual = Foo(id, Foo.Baz(name, email), note).intoPartial[Bar].transform
      expected ==== actual
    }

  val emailRegEx =
    """([a-zA-Z0-9]+([-_\.\+]+[a-zA-Z0-9]+)*@[a-zA-Z0-9]+([-_]+[a-zA-Z0-9]+)*(?:[.][a-zA-Z0-9]+([-_]+[a-zA-Z0-9]+)*)+)""".r

  final case class Foo(id: Foo.Id, baz: Foo.Baz, note: Foo.Note)
  object Foo {
    type Id = Id.Type
    object Id extends Newtype[PosInt], ChimneyNewtype[PosInt]

    type Name = Name.Type
    object Name extends Newtype[String], ChimneyNewtype[String]

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

    final case class Baz(name: Name, email: Email)
  }

  final case class Bar(id: Bar.Code, baz: Bar.Baz, note: Bar.Note)
  object Bar {
    type Code = Code.Type
    object Code extends Newtype[PosInt], ChimneyNewtype[PosInt]

    type Label = Label.Type
    object Label extends Newtype[String], ChimneyNewtype[String]

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

    final case class Baz(name: Label, email: Email)
  }

  final case class Something(id: Something.Id, innerThing: Something.InnerThing)

  object Something {
    type Id = Id.Type
    object Id extends Newtype[PosInt], ChimneyNewtype[PosInt]

    type Name = Name.Type
    object Name extends Newtype[String], ChimneyNewtype[String]

    final case class InnerThing(name: Name)
  }

  final case class SomethingElse(id: SomethingElse.Code, innerThing: SomethingElse.InnerThing)

  object SomethingElse {
    type Code = Code.Type
    object Code extends Newtype[PosInt], ChimneyNewtype[PosInt]

    type Label = Label.Type
    object Label extends Newtype[String], ChimneyNewtype[String]

    final case class InnerThing(name: Label)
  }
}
