package refined4s.modules.chimney.derivation

import cats.syntax.all.*
import hedgehog.*
import hedgehog.extra.refined4s.gens.NumGens
import hedgehog.runner.*
import io.scalaland.chimney
import refined4s.Newtype
import refined4s.types.all.PosInt

/** @author Kevin Lee
  * @since 2024-08-04
  */
object ChimneyNewtypeSpec extends Properties {

  override def tests: List[Test] = List(
    property("test Newtype", testNewtype),
    property("test Newtype[Refined]", testRefinedNewtype),
    property("test case class", testCaseClass),
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

  def testCaseClass: Property =
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
