package refined4s.modules.doobie.derivation

import cats.*

import refined4s.*
import refined4s.modules.cats.derivation.*
import refined4s.types.all.*

final case class Example(id: Example.Id, name: Example.Name, note: Example.Note, count: Example.Count)
object Example {

  given eqExample: Eq[Example]     = Eq.fromUniversalEquals
  given showExample: Show[Example] = Show.fromToString

  type Id = Id.Type
  object Id extends Newtype[Int] with CatsEqShow[Int]

  type Name = Name.Type
  object Name extends Newtype[NonEmptyString] with CatsEqShow[NonEmptyString]

  type MyString = MyString.Type
  @SuppressWarnings(Array("org.wartremover.warts.Equals"))
  object MyString extends InlinedRefined[String] with CatsEqShow[String] {

    override inline val inlinedExpectedValue = "a non-empty String"

    override inline def inlinedPredicate(inline a: String): Boolean = a != ""

    override inline def invalidReason(a: String): String = "It must be a non-empty String"

    override inline def predicate(a: String): Boolean = a != ""
  }

  type Note = Note.Type
  object Note extends Newtype[MyString] with CatsEqShow[MyString]

  type Count = Count.Type
  object Count extends Refined[Int] with CatsEqShow[Int] {

    override inline def invalidReason(a: Int): String = "It must be a non-negative Int"

    override inline def predicate(a: Int): Boolean = a >= 0
  }
}
