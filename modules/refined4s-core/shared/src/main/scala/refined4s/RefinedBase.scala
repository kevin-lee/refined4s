package refined4s

/** Copied from https://gist.github.com/Kevin-Lee/158d3d5c3e036560f8962087a34c95c5 and modified.
  * @author Kevin Lee
  * @since 2022-03-23
  */
trait RefinedBase[@specialized A] extends NewtypeBase[A] {

  override opaque type Type = A

  given RefinedCtor[Type, A] with {
    override def create(a: A): Either[String, Type] = from(a)
  }

  /* The reason to have `Some[A]` as a return type here is
   * https://github.com/scala/bug/issues/12232
   * So sad :(
   */
  def unapply(typ: Type): Some[A] = Some(typ)

  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  def from(a: A): Either[String, Type] =
    if predicate(a) then Right(a) else Left("Invalid value: [" + a.toString + "]. " + invalidReason(a))

  @SuppressWarnings(Array("org.wartremover.warts.Throw"))
  def unsafeFrom(a: A): Type =
    from(a).fold(err => throw new IllegalArgumentException(err), identity) // scalafix:ok DisableSyntax.throw

  inline def expectedMessage(expected: String): String =
    "It must be " + expected + "."

  def invalidReason(a: A): String

  def predicate(a: A): Boolean

  extension (typ: Type) {
    override inline def value: A = typ
  }

  override def deriving[F[*]](using fa: F[A]): F[Type] = fa

}
