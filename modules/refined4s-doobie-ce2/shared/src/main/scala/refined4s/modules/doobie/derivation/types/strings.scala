package refined4s.modules.doobie.derivation.types

import cats.Show
import doobie.{Get, Put}
import refined4s.types.strings.*

/** @author Kevin Lee
  * @since 2025-09-02
  */
trait strings {

  given derivedNonEmptyStringGet: Get[NonEmptyString] = strings.derivedNonEmptyStringGet
  given derivedNonEmptyStringPut: Put[NonEmptyString] = strings.derivedNonEmptyStringPut

  given derivedNonBlankStringGet: Get[NonBlankString] = strings.derivedNonBlankStringGet
  given derivedNonBlankStringPut: Put[NonBlankString] = strings.derivedNonBlankStringPut

  given derivedUuidGet: Get[Uuid] = strings.derivedUuidGet
  given derivedUuidPut: Put[Uuid] = strings.derivedUuidPut

}
object strings {

  given derivedNonEmptyStringGet(using Show[String]): Get[NonEmptyString] = Get[String].temap(NonEmptyString.from)
  given derivedNonEmptyStringPut: Put[NonEmptyString]                     = Put[String].contramap(_.value)

  given derivedNonBlankStringGet(using Show[String]): Get[NonBlankString] = Get[String].temap(NonBlankString.from)
  given derivedNonBlankStringPut: Put[NonBlankString]                     = Put[String].contramap(_.value)

  given derivedUuidGet(using Show[String]): Get[Uuid] = Get[String].temap(Uuid.from)
  given derivedUuidPut: Put[Uuid]                     = Put[String].contramap(_.value)

}
