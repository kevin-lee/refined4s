package refined4s.modules.doobie.derivation

import cats.*
import refined4s.*
import refined4s.modules.cats.derivation.*
import refined4s.modules.cats.derivation.instances.given
import refined4s.modules.doobie.derivation.instances.given
import refined4s.types.all.*

final case class ExampleWithDoobieGetPut(
  id: ExampleWithDoobieGetPut.Id,
  name: ExampleWithDoobieGetPut.Name,
  note: ExampleWithDoobieGetPut.Note,
  count: ExampleWithDoobieGetPut.Count,
)
object ExampleWithDoobieGetPut {

  given eqExampleWithDoobieGetPut: Eq[ExampleWithDoobieGetPut]     = Eq.fromUniversalEquals
  given showExampleWithDoobieGetPut: Show[ExampleWithDoobieGetPut] = Show.fromToString

  type Id = Id.Type
  object Id extends Newtype[Int] with CatsEqShow[Int] with DoobieNewtypeGetPut[Int]

  type Name = Name.Type
  object Name extends Newtype[NonEmptyString] with CatsEqShow[NonEmptyString] with DoobieNewtypeGetPut[NonEmptyString]

  type MyString = MyString.Type
  @SuppressWarnings(Array("org.wartremover.warts.Equals"))
  object MyString extends InlinedRefined[String] with DoobieRefinedGetPut[String] {
    override inline def inlinedPredicate(inline a: String): Boolean = a != ""

    override inline def invalidReason(a: String): String = "It must be a non-empty String"

    override inline def predicate(a: String): Boolean = a != ""
  }

  type Note = Note.Type
  object Note extends Newtype[MyString] with CatsEqShow[MyString] with DoobieNewtypeGetPut[MyString]

  type Count = Count.Type
  object Count extends Refined[Int] with CatsEqShow[Int] with DoobieRefinedGetPut[Int] {

    override inline def invalidReason(a: Int): String = "It must be a non-negative Int"

    override inline def predicate(a: Int): Boolean = a >= 0
  }
}
