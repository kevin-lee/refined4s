package refined4s.cats

import cats.data.*
import cats.syntax.all.*
import refined4s.*

/** @author Kevin Lee
  * @since 2023-12-06
  */
trait syntax {
  import refined4s.syntax.*
  import refined4s.internal.typeTools.*

  extension [A, T](a: A) {

    inline def validateAs[N](
      using coercible: Coercible[T, N],
      refinedCtor: RefinedCtor[T, A],
    ): EitherNec[String, N] =
      a.refinedTo[T]
        .leftMap(err => s"Failed to create ${getTypeName[N]}: $err")
        .toEitherNec
        .map(coercible(_))

  }

}
object syntax extends syntax
