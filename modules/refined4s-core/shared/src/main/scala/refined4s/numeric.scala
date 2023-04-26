package refined4s

/** @author Kevin Lee
  * @since 2023-04-26
  */
trait numeric {

  trait Numeric[A: math.Ordering] extends Refined[A] {
    given numericOrdering: Ordering[Type]

    given numericOrdered: Conversion[Type, Ordered[Type]] with {
      def apply(a: Type): Ordered[Type] =
        Ordered.orderingToOrdered[Type](a)(using numericOrdering)
    }
  }

  type NegInt = NegInt.Type

  object NegInt extends Numeric[Int] {
    override inline def invalidReason(a: Int): String =
      "It should be a negative Int value but got " + a + " instead"

    override inline def predicate(a: Int): Boolean = a < 0

    override given numericOrdering: Ordering[NegInt] =
      (x, y) => scala.math.Numeric.IntIsIntegral.compare(x.value, y.value)

  }

}
object numeric extends numeric
