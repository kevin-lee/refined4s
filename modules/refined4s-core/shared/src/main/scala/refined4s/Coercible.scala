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
  given unsafeWrapTC[F[*], A, B](
    using Coercible[A, B]
  ): Coercible[F[A], F[B]] = Coercible.instance

  /* For higher-kinded type */
  given unsafeWrapHKT[F[*], G[*], A, B](
    using Coercible[G[A], G[B]]
  ): Coercible[F[G[A]], F[G[B]]] = Coercible.instance
}
