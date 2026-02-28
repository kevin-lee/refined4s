package refined4s.modules.chimney.derivation.generic

import hedgehog.*
import hedgehog.extra.refined4s.gens.NumGens
import hedgehog.runner.*
import io.scalaland.chimney
import refined4s.modules.chimney.derivation.generic.auto.given
import refined4s.types.UuidV7TestTools
import refined4s.types.all.*
import refined4s.{Newtype, Refined}

import java.util.UUID

/** @author Kevin Lee
  * @since 2024-08-04
  */
object autoSpec extends Properties {
  import TestTypes.*

  override def tests: List[Test] = List(
    property("test Newtype", testNewtype),
    property("test Newtype[Refined]", testRefinedNewtype),
    property("test Refined (partial into)", testRefined),
    property("test case class", testCaseClass),
    property("test case class (partial into)", testCaseClassPartial),
  ) ++ uuidV7Spec.tests

  def testNewtype: Property =
    for {
      nameString <- Gen
                      .string(Gen.alpha, Range.linear(1, 10))
                      .list(Range.singleton(2))
                      .map(_.mkString(" "))
                      .log("nameString")
    } yield {
      import io.scalaland.chimney.dsl.*
      val expected = Bar.Label(nameString)

      val name   = Foo.Name(nameString)
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

      id    = Foo.Id(idNum)
      name  = Foo.Name(nameString)
      email = Foo.Email.unsafeFrom(emailString)
    } yield {
      import io.scalaland.chimney.dsl.*

      val expected =
        chimney.partial.Result.fromValue(Bar(Bar.Code(idNum), Bar.Baz(Bar.Label(nameString), Bar.Email.unsafeFrom(emailString))))

      val actual = Foo(id, Foo.Baz(name, email)).intoPartial[Bar].transform
      expected ==== actual
    }

  object uuidV7Spec {
    import io.scalaland.chimney.dsl.*

    def tests: List[Test] = List(
      property("test from UuidV7 to UUID", testFromUuidV7),
      property("test from UUID to UuidV7", testToUuidV7),
    )

    def testFromUuidV7: Property =
      for {
        uuid <- Gen.elementUnsafe(UuidV7TestTools.validUuidV7Strings).log("uuid")
      } yield {
        val input = UuidV7.unsafeFromString(uuid)

        val expected = UUID.fromString(uuid)
        val actual   = input.into[UUID].transform

        actual ==== expected
      }

    def testToUuidV7: Property =
      for {
        uuid <- Gen.elementUnsafe(UuidV7TestTools.validUuidV7Strings).log("uuid")
      } yield {
        val input = UUID.fromString(uuid)

        val expected = chimney.partial.Result.fromEitherString(UuidV7.fromString(uuid))
        val actual   = input.intoPartial[UuidV7].transform

        actual ==== expected
      }

  }

}

object TestTypes {
  val emailRegEx =
    """([a-zA-Z0-9]+([-_\.\+]+[a-zA-Z0-9]+)*@[a-zA-Z0-9]+([-_]+[a-zA-Z0-9]+)*(?:[.][a-zA-Z0-9]+([-_]+[a-zA-Z0-9]+)*)+)""".r

  final case class Foo(id: Foo.Id, baz: Foo.Baz)
  object Foo {
    type Id = Id.Type
    object Id extends Newtype[PosInt]

    type Name = Name.Type
    object Name extends Newtype[String]

    type Email = Email.Type
    object Email extends Refined[String] {

      override def invalidReason(a: String): String = s"Invalid email: $a"

      override def predicate(a: String): Boolean = emailRegEx.findFirstMatchIn(a).isDefined
    }

    final case class Baz(name: Name, email: Email)
  }

  final case class Bar(id: Bar.Code, baz: Bar.Baz)
  object Bar {
    type Code = Code.Type
    object Code extends Newtype[PosInt]

    type Label = Label.Type
    object Label extends Newtype[String]

    type Email = Email.Type
    object Email extends Refined[String] {

      override def invalidReason(a: String): String = s"Invalid email: $a"

      override def predicate(a: String): Boolean = emailRegEx.findFirstMatchIn(a).isDefined
    }

    final case class Baz(name: Label, email: Email)
  }

  final case class Something(id: Something.Id, innerThing: Something.InnerThing)
  object Something {
    type Id = Id.Type
    object Id extends Newtype[PosInt]

    type Name = Name.Type
    object Name extends Newtype[String]

    final case class InnerThing(name: Name)
  }

  final case class SomethingElse(id: SomethingElse.Code, innerThing: SomethingElse.InnerThing)
  object SomethingElse {
    type Code = Code.Type
    object Code extends Newtype[PosInt]

    type Label = Label.Type
    object Label extends Newtype[String]

    final case class InnerThing(name: Label)
  }

}
