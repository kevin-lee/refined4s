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
    override inline def invalidReason(a: Int): String = expectedMessage("a negative Int")

    override inline def predicate(a: Int): Boolean = a < 0

    override given numericOrdering: Ordering[NegInt] =
      (x, y) => scala.math.Numeric.IntIsIntegral.compare(x.value, y.value)

  }

  type NonNegInt = NonNegInt.Type
  object NonNegInt extends Numeric[Int] {
    override inline def invalidReason(a: Int): String = expectedMessage("a non-negative Int")

    override inline def predicate(a: Int): Boolean = a >= 0

    override given numericOrdering: Ordering[NonNegInt] =
      (x, y) => scala.math.Numeric.IntIsIntegral.compare(x.value, y.value)

  }

  type PosInt = PosInt.Type
  object PosInt extends Numeric[Int] {
    override inline def invalidReason(a: Int): String = expectedMessage("a positive Int")

    override inline def predicate(a: Int): Boolean = a > 0

    override given numericOrdering: Ordering[PosInt] =
      (x, y) => scala.math.Numeric.IntIsIntegral.compare(x.value, y.value)

  }

  type NonPosInt = NonPosInt.Type
  object NonPosInt extends Numeric[Int] {
    override inline def invalidReason(a: Int): String = expectedMessage("a non-positive Int")

    override inline def predicate(a: Int): Boolean = a <= 0

    override given numericOrdering: Ordering[NonPosInt] =
      (x, y) => scala.math.Numeric.IntIsIntegral.compare(x.value, y.value)

  }

  type NegLong = NegLong.Type
  object NegLong extends Numeric[Long] {
    override inline def invalidReason(a: Long): String = expectedMessage("a negative Long")

    override inline def predicate(a: Long): Boolean = a < 0L

    override given numericOrdering: Ordering[NegLong] =
      (x, y) => scala.math.Numeric.LongIsIntegral.compare(x.value, y.value)

  }

  type NonNegLong = NonNegLong.Type
  object NonNegLong extends Numeric[Long] {
    override inline def invalidReason(a: Long): String = expectedMessage("a non-negative Long")

    override inline def predicate(a: Long): Boolean = a >= 0L

    override given numericOrdering: Ordering[NonNegLong] =
      (x, y) => scala.math.Numeric.LongIsIntegral.compare(x.value, y.value)

  }

  type PosLong = PosLong.Type
  object PosLong extends Numeric[Long] {
    override inline def invalidReason(a: Long): String = expectedMessage("a positive Long")

    override inline def predicate(a: Long): Boolean = a > 0L

    override given numericOrdering: Ordering[PosLong] =
      (x, y) => scala.math.Numeric.LongIsIntegral.compare(x.value, y.value)

  }

}
object numeric extends numeric
