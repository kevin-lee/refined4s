package refined4s.modules.cats

import cats.Contravariant
import cats.data.*
import cats.syntax.all.*
import refined4s.*

/** @author Kevin Lee
  * @since 2023-12-06
  */
trait syntax {

  inline def contraCoercible[F[*], A, B](inline fb: F[B])(using contravariant: Contravariant[F], coercible: Coercible[A, B]): F[A] =
    contravariant.contramap[B, A](fb)(coercible(_))

  import refined4s.syntax.*
  import refined4s.internal.typeTools.*

  extension [A, T](a: A) {

    inline def refinedNewtypeNec[N](
      using coercible: Coercible[T, N],
      refinedCtor: RefinedCtor[T, A],
    ): EitherNec[String, N] =
      a.refinedTo[T]
        .leftMap(err => s"Failed to create ${getTypeName[N]}: $err")
        .toEitherNec
        .map(coercible(_))

    inline def refinedNewtypeNel[N](
      using coercible: Coercible[T, N],
      refinedCtor: RefinedCtor[T, A],
    ): EitherNel[String, N] =
      a.refinedTo[T]
        .leftMap(err => s"Failed to create ${getTypeName[N]}: $err")
        .toEitherNel
        .map(coercible(_))

  }

}
object syntax extends syntax
