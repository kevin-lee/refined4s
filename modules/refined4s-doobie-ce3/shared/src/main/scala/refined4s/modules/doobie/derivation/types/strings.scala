package refined4s.modules.doobie.derivation.types

import cats.Show
import doobie.{Get, Put}
import refined4s.types.all.*

/** @author Kevin Lee
  * @since 2025-09-03
  */
trait strings {

  given derivedNonEmptyStringGet: Get[NonEmptyString] = strings.derivedNonEmptyStringGet
  given derivedNonEmptyStringPut: Put[NonEmptyString] = strings.derivedNonEmptyStringPut

  given derivedNonBlankStringGet: Get[NonBlankString] = strings.derivedNonBlankStringGet
  given derivedNonBlankStringPut: Put[NonBlankString] = strings.derivedNonBlankStringPut

  given derivedUuidGet: Get[Uuid] = strings.derivedUuidGet
  given derivedUuidPut: Put[Uuid] = strings.derivedUuidPut

  given derivedUuidV7Get: Get[UuidV7] = strings.derivedUuidV7Get
  given derivedUuidV7Put: Put[UuidV7] = strings.derivedUuidV7Put

}
object strings {

  given derivedNonEmptyStringGet(using Show[String]): Get[NonEmptyString] = Get[String].temap(NonEmptyString.from)
  given derivedNonEmptyStringPut: Put[NonEmptyString]                     = Put[String].contramap(_.value)

  given derivedNonBlankStringGet(using Show[String]): Get[NonBlankString] = Get[String].temap(NonBlankString.from)
  given derivedNonBlankStringPut: Put[NonBlankString]                     = Put[String].contramap(_.value)

  given derivedUuidGet(using Show[String]): Get[Uuid] = Get[String].temap(Uuid.from)
  given derivedUuidPut: Put[Uuid]                     = Put[String].contramap(_.value)

  given derivedUuidV7Get(using Show[String]): Get[UuidV7] = Get[String].temap(UuidV7.fromString)
  given derivedUuidV7Put: Put[UuidV7]                     = Put[String].contramap(_.value.toString)

}
