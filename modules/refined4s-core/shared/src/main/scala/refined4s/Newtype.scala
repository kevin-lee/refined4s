package refined4s

/** @author Kevin Lee
  * @since 2023-12-03
  */
trait Newtype[A] extends NewtypeBase[A] {
  override opaque type Type = A

  def apply(a: A): Type = a

  def unapply(typ: Type): Option[A] = Some(typ)

  inline given wrap: Coercible[A, Type] = Coercible.instance

  inline given wrapTC[F[*]]: Coercible[F[A], F[Type]] = Coercible.instance

  extension (typ: Type) {
    override inline def value: A = typ
  }

  override def deriving[F[*]](using fa: F[A]): F[Type] = fa
}
