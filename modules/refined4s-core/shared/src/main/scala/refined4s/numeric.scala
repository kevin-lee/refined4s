package refined4s

/** @author Kevin Lee
  * @since 2023-04-26
  */
trait numeric {

  trait Numeric[A: math.Ordering] extends Refined[A] {
    given numericOrdering: Ordering[Type] = deriving[Ordering]

    given numericToOrdered: Conversion[Type, Ordered[Type]] with {
      def apply(a: Type): Ordered[Type] =
        Ordered.orderingToOrdered[Type](a)(using numericOrdering)
    }
  }

  type NegInt = NegInt.Type
  object NegInt extends Numeric[Int] {
    override inline def invalidReason(a: Int): String = expectedMessage("a negative Int")

    override inline def predicate(a: Int): Boolean = a < 0

  }

  type NonNegInt = NonNegInt.Type
  object NonNegInt extends Numeric[Int] {
    override inline def invalidReason(a: Int): String = expectedMessage("a non-negative Int")

    override inline def predicate(a: Int): Boolean = a >= 0

  }

  type PosInt = PosInt.Type
  object PosInt extends Numeric[Int] {
    override inline def invalidReason(a: Int): String = expectedMessage("a positive Int")

    override inline def predicate(a: Int): Boolean = a > 0

  }

  type NonPosInt = NonPosInt.Type
  object NonPosInt extends Numeric[Int] {
    override inline def invalidReason(a: Int): String = expectedMessage("a non-positive Int")

    override inline def predicate(a: Int): Boolean = a <= 0

  }

  type NegLong = NegLong.Type
  object NegLong extends Numeric[Long] {
    override inline def invalidReason(a: Long): String = expectedMessage("a negative Long")

    override inline def predicate(a: Long): Boolean = a < 0L

  }

  type NonNegLong = NonNegLong.Type
  object NonNegLong extends Numeric[Long] {
    override inline def invalidReason(a: Long): String = expectedMessage("a non-negative Long")

    override inline def predicate(a: Long): Boolean = a >= 0L

  }

  type PosLong = PosLong.Type
  object PosLong extends Numeric[Long] {
    override inline def invalidReason(a: Long): String = expectedMessage("a positive Long")

    override inline def predicate(a: Long): Boolean = a > 0L

  }

  type NonPosLong = NonPosLong.Type
  object NonPosLong extends Numeric[Long] {
    override inline def invalidReason(a: Long): String = expectedMessage("a non-positive Long")

    override inline def predicate(a: Long): Boolean = a <= 0L

  }

  type NegShort = NegShort.Type
  object NegShort extends Numeric[Short] {
    override inline def invalidReason(a: Short): String = expectedMessage("a negative Short")

    override inline def predicate(a: Short): Boolean = a < 0

  }

  type NonNegShort = NonNegShort.Type
  object NonNegShort extends Numeric[Short] {
    override inline def invalidReason(a: Short): String = expectedMessage("a non-negative Short")

    override inline def predicate(a: Short): Boolean = a >= 0

  }

  type PosShort = PosShort.Type
  object PosShort extends Numeric[Short] {
    override inline def invalidReason(a: Short): String = expectedMessage("a positive Short")

    override inline def predicate(a: Short): Boolean = a > 0

  }

  type NonPosShort = NonPosShort.Type
  object NonPosShort extends Numeric[Short] {
    override inline def invalidReason(a: Short): String = expectedMessage("a non-positive Short")

    override inline def predicate(a: Short): Boolean = a <= 0

  }

}
object numeric extends numeric
