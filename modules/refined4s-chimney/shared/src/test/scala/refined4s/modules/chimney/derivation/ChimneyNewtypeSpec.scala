package refined4s.modules.chimney.derivation

import cats.syntax.all.*
import hedgehog.*
import hedgehog.extra.refined4s.gens.{NumGens, StringGens}
import hedgehog.runner.*
import io.scalaland.chimney
import refined4s.*
import refined4s.types.all.PosInt

/** @author Kevin Lee
  * @since 2024-08-04
  */
object ChimneyNewtypeSpec extends Properties {

  override def tests: List[Test] = List(
    property("test Newtype to Newtype with Transformer", testNewtypeToNewtype),
    property("test Newtype to non-Newtype with Transformer", testNewtypeToNonNewtype),
    property("test Newtype[Refined]", testRefinedNewtype),
    property("test case class", testCaseClass),
    property("test case class with Newtype to non-Newtype", testCaseClassWithNewtypeToNonNewtype),
    property("test case class with Refined[A] to Newtype[Refined[A]]", testCaseClassWithRefinedToNewtypeRefined),
  )

  def testNewtypeToNewtype: Property = {
    import TestType1.*

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
  }

  def testNewtypeToNonNewtype: Property = {
    import TestType1.*

    for {
      nameString <- Gen
                      .string(Gen.alpha, Range.linear(1, 10))
                      .list(Range.singleton(2))
                      .map(_.mkString(" "))
                      .log("nameString")
      name = Foo.Name(nameString)
    } yield {
      import io.scalaland.chimney.dsl.*
      val expected = nameString

      val actual = name.into[String].transform
      expected ==== actual
    }
  }

  def testRefinedNewtype: Property = {
    import TestType1.*

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

  def testCaseClass: Property = {
    import TestType1.*

    for {
      idNum      <- NumGens.genPosIntMaxTo(PosInt(Int.MaxValue)).log("idNum")
      nameString <- Gen
                      .string(Gen.alpha, Range.linear(1, 10))
                      .list(Range.singleton(2))
                      .map(_.mkString(" "))
                      .log("nameString")

      id   = Foo.Id(idNum)
      name = Foo.Name(nameString)
    } yield {
      import io.scalaland.chimney.dsl.*

      val expected = Bar(Bar.Code(idNum), Bar.Baz(Bar.Label(nameString)))

      val actual = Foo(id, Foo.Baz(name)).into[Bar].transform
      expected ==== actual
    }
  }

  def testCaseClassWithNewtypeToNonNewtype: Property = {
    import TestTypes2.*
    for {
      nameString <- Gen
                      .string(Gen.alpha, Range.linear(1, 10))
                      .list(Range.singleton(2))
                      .map(_.mkString(" "))
                      .log("nameString")

      name = Person.Name(nameString)
    } yield {
      import io.scalaland.chimney.dsl.*

      val expected = User(nameString)

      val actual = Person(name).into[User].transform
      expected ==== actual
    }
  }

  def testCaseClassWithRefinedToNewtypeRefined: Property = {
    import TestTypes3.*

    for {
      nonEmptyString <- StringGens.genNonEmptyString(Gen.alpha, PosInt(10)).log("nonEmptyString")

      name <- Gen.constant(XMen.Name(nonEmptyString.value)).log("name")
      xMen <- Gen.constant(XMen(name)).log("xMen")
    } yield {
      import io.scalaland.chimney.dsl.*

      val expected = chimney.partial.Result.fromValue(MarvelCharacter(MarvelCharacter.Name(nonEmptyString)))
      val actual   = xMen.intoPartial[MarvelCharacter].transform

      expected ==== actual
    }
  }

  object TestType1 {
    final case class Foo(id: Foo.Id, baz: Foo.Baz)

    object Foo {
      type Id = Id.Type
      object Id extends Newtype[PosInt], ChimneyNewtype[PosInt]

      type Name = Name.Type
      object Name extends Newtype[String], ChimneyNewtype[String]

      final case class Baz(name: Name)
    }

    final case class Bar(id: Bar.Code, baz: Bar.Baz)
    object Bar {
      type Code = Code.Type
      object Code extends Newtype[PosInt], ChimneyNewtype[PosInt]

      type Label = Label.Type
      object Label extends Newtype[String], ChimneyNewtype[String]

      final case class Baz(name: Label)
    }
  }

  object TestTypes2 {

    final case class Person(name: Person.Name)
    object Person {
      type Name = Name.Type

      object Name extends Newtype[String], ChimneyNewtype[String]
    }

    final case class User(name: String)
  }

  object TestTypes3 {

    final case class XMen(name: XMen.Name)
    object XMen {
      type Name = Name.Type
      object Name extends Newtype[String], ChimneyNewtype[String]
    }

    import refined4s.types.all.*

    final case class MarvelCharacter(name: MarvelCharacter.Name)
    object MarvelCharacter {
      type Name = Name.Type

      object Name extends Newtype[NonEmptyString], ChimneyNewtype[NonEmptyString]
    }

  }

}
