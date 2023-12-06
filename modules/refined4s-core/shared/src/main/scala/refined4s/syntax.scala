package refined4s

/** @author Kevin Lee
  * @since 2023-12-02
  */
object syntax {

  extension [A](a: A) {

    inline def coerce[B](using coercible: Coercible[A, B]): B = coercible(a)

    inline def refinedTo[T](using refinedCtor: RefinedCtor[T, A]): Either[String, T] =
      refinedCtor.create(a)
  }

}
