package refined4s

/** The idea of Coercible is from scala-newtype
  * https://github.com/estatico/scala-newtype/blob/201dad6b0c628caa9a80141e304ca70716d601a8/shared/src/main/scala/io/estatico/newtype/Coercible.scala
  * It does exactly the same thing as scala-newtype's.
  *
  * @author Kevin Lee
  * @since 2023-12-02
  */
trait Coercible[A, B] {
  @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
  final inline def apply(a: A): B = a.asInstanceOf[B] // scalafix:ok DisableSyntax.asInstanceOf
}
object Coercible {

  def apply[A, B](using ev: Coercible[A, B]): Coercible[A, B] = ev

  // scalafix:off DisableSyntax.asInstanceOf
  @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
  def instance[A, B]: Coercible[A, B] = coercible.asInstanceOf[Coercible[A, B]]
  // scalafix:on

  private object coercible extends Coercible[Any, Any]

  /* For type constructors */
  given unsafeWrapM[M[*], A, B](
    using Coercible[A, B]
  ): Coercible[M[A], M[B]] = Coercible.instance

  /* For nested type constructors */
  given unsafeWrapMOfM[M1[*], M2[*], A, B](
    using Coercible[M2[A], M2[B]]
  ): Coercible[M1[M2[A]], M1[M2[B]]] = Coercible.instance
}
