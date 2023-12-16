package refined4s.modules.doobie.derivation

import hedgehog.*
import refined4s.modules.doobie.derivation.Example.MyString
import refined4s.types.all.*

/** @author Kevin Lee
  * @since 2023-12-16
  */
object Gens {
  def genExample: Gen[Example] = for {
    id    <- Gen.int(Range.linear(1, Int.MaxValue)).map(Example.Id(_))
    name  <- Gen.string(Gen.alphaNum, Range.linear(5, 20)).map(s => Example.Name(NonEmptyString.unsafeFrom(s)))
    note  <- Gen.string(Gen.alphaNum, Range.linear(5, 20)).map(s => Example.Note(MyString.unsafeFrom(s)))
    count <- Gen.int(Range.linear(0, Int.MaxValue)).map(Example.Count.unsafeFrom)
  } yield Example(id, name, note, count)
}
