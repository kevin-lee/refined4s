package refined4s

/** @author Kevin Lee
  * @since 2023-12-03
  */
trait NewtypeBase[A] {
  type Type

  given newRefinedCanEqual: CanEqual[Type, Type] = CanEqual.derived

  inline given unwrap: Coercible[Type, A]              = Coercible.instance
  inline given unwrapM[M[*]]: Coercible[M[Type], M[A]] = Coercible.instance

  extension (typ: Type) {
    def value: A
  }

  def deriving[M[*]](using fa: M[A]): M[Type]

}
