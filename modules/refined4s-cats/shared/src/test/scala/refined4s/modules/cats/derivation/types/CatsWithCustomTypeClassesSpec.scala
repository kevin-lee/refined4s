package refined4s.modules.cats.derivation.types

import cats.syntax.all.*
import cats.{Eq, Show}
import hedgehog.*
import hedgehog.runner.*
import refined4s.*
import refined4s.modules.cats.derivation.types.all.given
import refined4s.types.all.*

import java.time.Instant

/** @author Kevin Lee
  * @since 2023-12-26
  */
object CatsWithCustomTypeClassesSpec extends Properties {
  override def tests: List[Test] = List(
    property("test Eq with custom type-classes object === object should return true", testEqTrueCase),
    property("test Eq with custom type-classes object =!= differentObject should return true", testEqFalseCase),
    property("test Show with custom type-classes", testShow),
  )

  def testEqTrueCase: Property =
    for {
      id       <- Gen
                    .int(Range.linear(1, Int.MaxValue))
                    .map(PosInt.unsafeFrom)
                    .map(Id(_))
                    .log("id")
      name     <- Gen
                    .string(Gen.alpha, Range.linear(1, 10))
                    .list(Range.singleton(2))
                    .map(_.mkString(" "))
                    .map(NonEmptyString.unsafeFrom)
                    .map(Name(_))
                    .log("name")
      created  <- Gen
                    .long(Range.linear(0, Long.MaxValue))
                    .map(Instant.ofEpochMilli)
                    .map(Created(_))
                    .log("created")
      testData <- Gen.constant(TestData(id, name, created)).log("expected")
    } yield {
      Result.diffNamed("Comparing the same objects with === should return true", testData, testData)(_ === _)
    }

  def testEqFalseCase: Property =
    for {
      id1       <- Gen
                     .int(Range.linear(1, Int.MaxValue))
                     .map(PosInt.unsafeFrom)
                     .map(Id(_))
                     .log("id")
      name1     <- Gen
                     .string(Gen.alpha, Range.linear(1, 10))
                     .list(Range.singleton(2))
                     .map(_.mkString(" "))
                     .map(NonEmptyString.unsafeFrom)
                     .map(Name(_))
                     .log("name")
      created1  <- Gen
                     .long(Range.linear(0, Long.MaxValue))
                     .map(Instant.ofEpochMilli)
                     .map(Created(_))
                     .log("created")
      testData1 <- Gen.constant(TestData(id1, name1, created1)).log("expected")
      id2       <- Gen
                     .constant(id1.toValue)
                     .map(n => (n >> 1) + 123)
                     .map(PosInt.unsafeFrom)
                     .map(Id(_))
                     .log("id")
      name2     <- Gen
                     .constant(name1)
                     .map { name1 =>
                       val splitNames = name1.toValue.split("\\s+")
                       splitNames.map(_ + "2").mkString(" ")
                     }
                     .map(NonEmptyString.unsafeFrom)
                     .map(Name(_))
                     .log("name")
      created2  <- Gen
                     .constant(created1.value.toEpochMilli)
                     .map(n => (n >> 1) + 123)
                     .map(Instant.ofEpochMilli)
                     .map(Created(_))
                     .log("created")
      testData2 <- Gen.constant(TestData(id2, name2, created2)).log("expected")
    } yield {
      Result.diffNamed("Comparing the different objects with =!= should return true", testData1, testData2)(_ =!= _)
    }

  def testShow: Property =
    for {
      id       <- Gen
                    .int(Range.linear(1, Int.MaxValue))
                    .map(PosInt.unsafeFrom)
                    .map(Id(_))
                    .log("id")
      name     <- Gen
                    .string(Gen.alpha, Range.linear(1, 10))
                    .list(Range.singleton(2))
                    .map(_.mkString(" "))
                    .map(NonEmptyString.unsafeFrom)
                    .map(Name(_))
                    .log("name")
      created  <- Gen
                    .long(Range.linear(0, Long.MaxValue))
                    .map(Instant.ofEpochMilli)
                    .map(Created(_))
                    .log("created")
      testData <- Gen.constant(TestData(id, name, created)).log("expected")
    } yield {
      val names = name.toValue.split("\\s+")

      @SuppressWarnings(Array("org.wartremover.warts.ToString"))
      val expected = s"TestData(${id.toString}, ${names(1)}, ${names(0)}, ${created.value.toEpochMilli.toString})"
      val actual   = testData.show
      actual ==== expected
    }

  final case class TestData(id: Id, name: Name, created: Created)
  object TestData {
    given testDataEq: Eq[TestData] = {
      case (TestData(id1, name1, created1), TestData(id2, name2, created2)) =>
        Eq[Id].eqv(id1, id2) && Eq[Name].eqv(name1, name2) && Eq[Created].eqv(created1, created2)
    }

    given testDataShow: Show[TestData] = {
      case TestData(id, name, created) =>
        show"${classOf[TestData].getSimpleName}($id, $name, $created)"
    }
  }

  type Id = Id.Type
  object Id extends Newtype[PosInt] {
    given idEq: Eq[Id]     = deriving[Eq]
    given idShow: Show[Id] = deriving[Show]
  }

  type Name = Name.Type
  object Name extends Newtype[NonEmptyString] {
    given nameEq: Eq[Name] =
      _.value.value.split("\\s+").toList === _.value.value.split("\\s+").toList

    given nameShow: Show[Name] = { name =>
      val nameString = name.value.value

      nameString.split("\\s+").toList match {
        case firstName :: lastName :: Nil => s"$lastName, $firstName"
        case _ => nameString
      }
    }
  }

  type Created = Created.Type
  object Created extends Newtype[Instant] {

    given createdEq: Eq[Created]     = _.value.toEpochMilli === _.value.toEpochMilli
    given createdShow: Show[Created] = _.value.toEpochMilli.toString

  }
}
