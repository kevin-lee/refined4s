package refined4s

/** @author Kevin Lee
  * @since 2023-12-29
  */
trait CanBeOrdered[@specialized(Int, Long, Short, Byte, Float, Double) A: Ordering] {
  self: NewtypeBase[A] =>

  given derivedOrdering: Ordering[Type] = deriving[Ordering]

  given derivedToOrdered: Conversion[Type, Ordered[Type]] with {
    def apply(a: Type): Ordered[Type] =
      Ordered.orderingToOrdered[Type](a)(using derivedOrdering)
  }

}
