package refined4s

/** @author Kevin Lee
  * @since 2023-12-02
  */
trait syntax {

  extension [A](a: A) {

    inline def coerce[B](using coercible: Coercible[A, B]): B = coercible(a)

    inline def refinedTo[T](using refinedCtor: RefinedCtor[T, A]): Either[String, T] =
      refinedCtor.create(a)
  }

  import internal.typeTools.*
  extension [A, T](a: A) {

    inline def refinedNewtype[N](
      using coercible: Coercible[T, N],
      refinedCtor: RefinedCtor[T, A],
    ): Either[String, N] =
      a.refinedTo[T]
        .left
        .map(err => s"Failed to create ${getTypeName[N]}: $err")
        .map(coercible(_))

  }

}
object syntax extends syntax
