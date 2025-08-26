package refined4s

/** @author Kevin Lee
  * @since 2025-08-26
  */
object predef4testing {
  extension [A](a: A) {
    @SuppressWarnings(Array("org.wartremover.warts.Equals"))
    def ===(b: A): Boolean = a == b
    @SuppressWarnings(Array("org.wartremover.warts.Equals"))
    def =!=(b: A): Boolean = a != b

    def some: Option[A] = Some(a)

    def asRight[B]: Either[B, A] = Right(a)
    def asLeft[B]: Either[A, B]  = Left(a)
  }

  def none[A]: Option[A] = None
}
