package refined4s

/** @author Kevin Lee
  * @since 2023-12-03
  */
trait NewtypeBase[@specialized A] {
  type Type

  given newRefinedCanEqual: CanEqual[Type, Type] = CanEqual.derived

  given unwrap: Coercible[Type, A] = Coercible.instance

  given unwrapTC[F[*]]: Coercible[F[Type], F[A]] = Coercible.instance

  extension (typ: Type) {
    def value: A
  }

  extension [B](typ: Type) {
    def toValue(using coercibleA2B: Coercible[A, B]): B =
      coercibleA2B(typ.value)
  }

  def deriving[F[*]](using fa: F[A]): F[Type]

}
