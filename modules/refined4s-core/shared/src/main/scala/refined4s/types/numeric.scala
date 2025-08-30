package refined4s.types

import orphan.{OrphanCats, OrphanCatsKernel}
import refined4s.*
import refined4s.internal.numericTools

/** @author Kevin Lee
  * @since 2023-04-26
  */
trait numeric {

  // scalafix:off DisableSyntax.noFinalVal

  final type NegInt = numeric.NegInt
  final val NegInt = numeric.NegInt

  final type NonNegInt = numeric.NonNegInt
  final val NonNegInt = numeric.NonNegInt

  final type PosInt = numeric.PosInt
  final val PosInt = numeric.PosInt

  final type NonPosInt = numeric.NonPosInt
  final val NonPosInt = numeric.NonPosInt

  final type NegLong = numeric.NegLong
  final val NegLong = numeric.NegLong

  final type NonNegLong = numeric.NonNegLong
  final val NonNegLong = numeric.NonNegLong

  final type PosLong = numeric.PosLong
  final val PosLong = numeric.PosLong

  final type NonPosLong = numeric.NonPosLong
  final val NonPosLong = numeric.NonPosLong

  final type NegShort = numeric.NegShort
  final val NegShort = numeric.NegShort

  final type NonNegShort = numeric.NonNegShort
  final val NonNegShort = numeric.NonNegShort

  final type PosShort = numeric.PosShort
  final val PosShort = numeric.PosShort

  final type NonPosShort = numeric.NonPosShort
  final val NonPosShort = numeric.NonPosShort

  final type NegByte = numeric.NegByte
  final val NegByte = numeric.NegByte

  final type NonNegByte = numeric.NonNegByte
  final val NonNegByte = numeric.NonNegByte

  final type PosByte = numeric.PosByte
  final val PosByte = numeric.PosByte

  final type NonPosByte = numeric.NonPosByte
  final val NonPosByte = numeric.NonPosByte

  final type NegFloat = numeric.NegFloat
  final val NegFloat = numeric.NegFloat

  final type NonNegFloat = numeric.NonNegFloat
  final val NonNegFloat = numeric.NonNegFloat

  final type PosFloat = numeric.PosFloat
  final val PosFloat = numeric.PosFloat

  final type NonPosFloat = numeric.NonPosFloat
  final val NonPosFloat = numeric.NonPosFloat

  final type NegDouble = numeric.NegDouble
  final val NegDouble = numeric.NegDouble

  final type NonNegDouble = numeric.NonNegDouble
  final val NonNegDouble = numeric.NonNegDouble

  final type PosDouble = numeric.PosDouble
  final val PosDouble = numeric.PosDouble

  final type NonPosDouble = numeric.NonPosDouble
  final val NonPosDouble = numeric.NonPosDouble

  final type NegBigInt = numeric.NegBigInt
  final val NegBigInt = numeric.NegBigInt

  final type NonNegBigInt = numeric.NonNegBigInt
  final val NonNegBigInt = numeric.NonNegBigInt

  final type PosBigInt = numeric.PosBigInt
  final val PosBigInt = numeric.PosBigInt

  final type NonPosBigInt = numeric.NonPosBigInt
  final val NonPosBigInt = numeric.NonPosBigInt

  final type NegBigDecimal = numeric.NegBigDecimal
  final val NegBigDecimal = numeric.NegBigDecimal

  final type NonNegBigDecimal = numeric.NonNegBigDecimal
  final val NonNegBigDecimal = numeric.NonNegBigDecimal

  final type PosBigDecimal = numeric.PosBigDecimal
  final val PosBigDecimal = numeric.PosBigDecimal

  final type NonPosBigDecimal = numeric.NonPosBigDecimal
  final val NonPosBigDecimal = numeric.NonPosBigDecimal

  // scalafix:on
}
object numeric {

  trait Numeric[@specialized(Int, Long, Short, Byte, Float, Double) A: math.Ordering] extends Refined[A], CanBeOrdered[A]

  trait InlinedNumeric[@specialized(Int, Long, Short, Byte, Float, Double) A: math.Ordering] extends InlinedRefined[A], CanBeOrdered[A]

  trait Min[@specialized(Int, Long, Short, Byte, Float, Double) A] {
    self: NewtypeBase[A] =>
    def min: Type

    val MinValue: Type = min
  }

  trait Max[@specialized(Int, Long, Short, Byte, Float, Double) A] {
    self: NewtypeBase[A] =>
    def max: Type

    val MaxValue: Type = max
  }

  trait MinMax[@specialized(Int, Long, Short, Byte, Float, Double) A] extends Min[A], Max[A] {
    self: NewtypeBase[A] =>
  }

  type NegInt = NegInt.Type
  object NegInt extends Numeric[Int], MinMax[Int], NegIntTypeClassInstances {
    override def min: Type = apply(Int.MinValue)
    override def max: Type = apply(-1)

    override inline def invalidReason(a: Int): String = expectedMessage("a negative Int")

    override inline def predicate(a: Int): Boolean = a < 0

  }
  private[types] trait NegIntTypeClassInstances extends NegIntTypeClassInstance1 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNegIntEq[F[*]: CatsEq, G[*]: CatsEq](using eqActual: G[Int]): F[NegInt] = {
      internalDef.contraCoercible[cats.Eq, NegInt, Int, cats.Contravariant](eqActual.asInstanceOf[cats.Eq[Int]])
    }.asInstanceOf[F[NegInt]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait NegIntTypeClassInstance1 extends NegIntTypeClassInstance2 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNegIntHash[F[*]: CatsHash, G[*]: CatsHash](using hashActual: G[Int]): F[NegInt] = {
      internalDef.contraCoercible[cats.Hash, NegInt, Int, cats.Contravariant](hashActual.asInstanceOf[cats.Hash[Int]])
    }.asInstanceOf[F[NegInt]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait NegIntTypeClassInstance2 extends OrphanCats, OrphanCatsKernel {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNegIntShow[F[*]: CatsShow, G[*]: CatsShow](using showActual: G[Int]): F[NegInt] = {
      internalDef.contraCoercible[cats.Show, NegInt, Int, cats.Contravariant](showActual.asInstanceOf[cats.Show[Int]])
    }.asInstanceOf[F[NegInt]] // scalafix:ok DisableSyntax.asInstanceOf
  }

  type NonNegInt = NonNegInt.Type
  object NonNegInt extends Numeric[Int], MinMax[Int], NonNegIntTypeClassInstances {
    override def min: Type = apply(0)
    override def max: Type = apply(Int.MaxValue)

    override inline def invalidReason(a: Int): String = expectedMessage("a non-negative Int")

    override inline def predicate(a: Int): Boolean = a >= 0

  }
  private[types] trait NonNegIntTypeClassInstances extends NonNegIntTypeClassInstance1 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNonNegIntEq[F[*]: CatsEq, G[*]: CatsEq](using eqActual: G[Int]): F[NonNegInt] = {
      internalDef.contraCoercible[cats.Eq, NonNegInt, Int, cats.Contravariant](eqActual.asInstanceOf[cats.Eq[Int]])
    }.asInstanceOf[F[NonNegInt]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait NonNegIntTypeClassInstance1 extends NonNegIntTypeClassInstance2 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNonNegIntHash[F[*]: CatsHash, G[*]: CatsHash](using hashActual: G[Int]): F[NonNegInt] = {
      internalDef.contraCoercible[cats.Hash, NonNegInt, Int, cats.Contravariant](hashActual.asInstanceOf[cats.Hash[Int]])
    }.asInstanceOf[F[NonNegInt]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait NonNegIntTypeClassInstance2 extends OrphanCats, OrphanCatsKernel {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNonNegIntShow[F[*]: CatsShow, G[*]: CatsShow](using showActual: G[Int]): F[NonNegInt] = {
      internalDef.contraCoercible[cats.Show, NonNegInt, Int, cats.Contravariant](showActual.asInstanceOf[cats.Show[Int]])
    }.asInstanceOf[F[NonNegInt]] // scalafix:ok DisableSyntax.asInstanceOf
  }

  type PosInt = PosInt.Type
  object PosInt extends Numeric[Int], MinMax[Int], PosIntTypeClassInstances {
    override def min: Type = apply(1)
    override def max: Type = apply(Int.MaxValue)

    override inline def invalidReason(a: Int): String = expectedMessage("a positive Int")

    override inline def predicate(a: Int): Boolean = a > 0

  }
  private[types] trait PosIntTypeClassInstances extends PosIntTypeClassInstance1 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedPosIntEq[F[*]: CatsEq, G[*]: CatsEq](using eqActual: G[Int]): F[PosInt] = {
      internalDef.contraCoercible[cats.Eq, PosInt, Int, cats.Contravariant](eqActual.asInstanceOf[cats.Eq[Int]])
    }.asInstanceOf[F[PosInt]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait PosIntTypeClassInstance1 extends PosIntTypeClassInstance2 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedPosIntHash[F[*]: CatsHash, G[*]: CatsHash](using hashActual: G[Int]): F[PosInt] = {
      internalDef.contraCoercible[cats.Hash, PosInt, Int, cats.Contravariant](hashActual.asInstanceOf[cats.Hash[Int]])
    }.asInstanceOf[F[PosInt]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait PosIntTypeClassInstance2 extends OrphanCats, OrphanCatsKernel {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedPosIntShow[F[*]: CatsShow, G[*]: CatsShow](using showActual: G[Int]): F[PosInt] = {
      internalDef.contraCoercible[cats.Show, PosInt, Int, cats.Contravariant](showActual.asInstanceOf[cats.Show[Int]])
    }.asInstanceOf[F[PosInt]] // scalafix:ok DisableSyntax.asInstanceOf
  }

  type NonPosInt = NonPosInt.Type
  object NonPosInt extends Numeric[Int], MinMax[Int], NonPosIntTypeClassInstances {
    override def min: Type = apply(Int.MinValue)
    override def max: Type = apply(0)

    override inline def invalidReason(a: Int): String = expectedMessage("a non-positive Int")

    override inline def predicate(a: Int): Boolean = a <= 0

  }
  private[types] trait NonPosIntTypeClassInstances extends NonPosIntTypeClassInstance1 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNonPosIntEq[F[*]: CatsEq, G[*]: CatsEq](using eqActual: G[Int]): F[NonPosInt] = {
      internalDef.contraCoercible[cats.Eq, NonPosInt, Int, cats.Contravariant](eqActual.asInstanceOf[cats.Eq[Int]])
    }.asInstanceOf[F[NonPosInt]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait NonPosIntTypeClassInstance1 extends NonPosIntTypeClassInstance2 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNonPosIntHash[F[*]: CatsHash, G[*]: CatsHash](using hashActual: G[Int]): F[NonPosInt] = {
      internalDef.contraCoercible[cats.Hash, NonPosInt, Int, cats.Contravariant](hashActual.asInstanceOf[cats.Hash[Int]])
    }.asInstanceOf[F[NonPosInt]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait NonPosIntTypeClassInstance2 extends OrphanCats, OrphanCatsKernel {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNonPosIntShow[F[*]: CatsShow, G[*]: CatsShow](using showActual: G[Int]): F[NonPosInt] = {
      internalDef.contraCoercible[cats.Show, NonPosInt, Int, cats.Contravariant](showActual.asInstanceOf[cats.Show[Int]])
    }.asInstanceOf[F[NonPosInt]] // scalafix:ok DisableSyntax.asInstanceOf
  }

  type NegLong = NegLong.Type
  object NegLong extends Numeric[Long], MinMax[Long], NegLongTypeClassInstances {
    override def min: Type = apply(Long.MinValue)
    override def max: Type = apply(-1L)

    override inline def invalidReason(a: Long): String = expectedMessage("a negative Long")

    override inline def predicate(a: Long): Boolean = a < 0L

  }
  private[types] trait NegLongTypeClassInstances extends NegLongTypeClassInstance1 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNegLongEq[F[*]: CatsEq, G[*]: CatsEq](using eqActual: G[Long]): F[NegLong] = {
      internalDef.contraCoercible[cats.Eq, NegLong, Long, cats.Contravariant](eqActual.asInstanceOf[cats.Eq[Long]])
    }.asInstanceOf[F[NegLong]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait NegLongTypeClassInstance1 extends NegLongTypeClassInstance2 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNegLongHash[F[*]: CatsHash, G[*]: CatsHash](using hashActual: G[Long]): F[NegLong] = {
      internalDef.contraCoercible[cats.Hash, NegLong, Long, cats.Contravariant](hashActual.asInstanceOf[cats.Hash[Long]])
    }.asInstanceOf[F[NegLong]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait NegLongTypeClassInstance2 extends OrphanCats, OrphanCatsKernel {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNegLongShow[F[*]: CatsShow, G[*]: CatsShow](using showActual: G[Long]): F[NegLong] = {
      internalDef.contraCoercible[cats.Show, NegLong, Long, cats.Contravariant](showActual.asInstanceOf[cats.Show[Long]])
    }.asInstanceOf[F[NegLong]] // scalafix:ok DisableSyntax.asInstanceOf
  }

  type NonNegLong = NonNegLong.Type
  object NonNegLong extends Numeric[Long], MinMax[Long], NonNegLongTypeClassInstances {
    override def min: Type = apply(0L)
    override def max: Type = apply(Long.MaxValue)

    override inline def invalidReason(a: Long): String = expectedMessage("a non-negative Long")

    override inline def predicate(a: Long): Boolean = a >= 0L

  }
  private[types] trait NonNegLongTypeClassInstances extends NonNegLongTypeClassInstance1 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNonNegLongEq[F[*]: CatsEq, G[*]: CatsEq](using eqActual: G[Long]): F[NonNegLong] = {
      internalDef.contraCoercible[cats.Eq, NonNegLong, Long, cats.Contravariant](eqActual.asInstanceOf[cats.Eq[Long]])
    }.asInstanceOf[F[NonNegLong]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait NonNegLongTypeClassInstance1 extends NonNegLongTypeClassInstance2 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNonNegLongHash[F[*]: CatsHash, G[*]: CatsHash](using hashActual: G[Long]): F[NonNegLong] = {
      internalDef.contraCoercible[cats.Hash, NonNegLong, Long, cats.Contravariant](hashActual.asInstanceOf[cats.Hash[Long]])
    }.asInstanceOf[F[NonNegLong]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait NonNegLongTypeClassInstance2 extends OrphanCats, OrphanCatsKernel {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNonNegLongShow[F[*]: CatsShow, G[*]: CatsShow](using showActual: G[Long]): F[NonNegLong] = {
      internalDef.contraCoercible[cats.Show, NonNegLong, Long, cats.Contravariant](showActual.asInstanceOf[cats.Show[Long]])
    }.asInstanceOf[F[NonNegLong]] // scalafix:ok DisableSyntax.asInstanceOf
  }

  type PosLong = PosLong.Type
  object PosLong extends Numeric[Long], MinMax[Long], PosLongTypeClassInstances {
    override def min: Type = apply(1L)
    override def max: Type = apply(Long.MaxValue)

    override inline def invalidReason(a: Long): String = expectedMessage("a positive Long")

    override inline def predicate(a: Long): Boolean = a > 0L

  }
  private[types] trait PosLongTypeClassInstances extends PosLongTypeClassInstance1 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedPosLongEq[F[*]: CatsEq, G[*]: CatsEq](using eqActual: G[Long]): F[PosLong] = {
      internalDef.contraCoercible[cats.Eq, PosLong, Long, cats.Contravariant](eqActual.asInstanceOf[cats.Eq[Long]])
    }.asInstanceOf[F[PosLong]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait PosLongTypeClassInstance1 extends PosLongTypeClassInstance2 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedPosLongHash[F[*]: CatsHash, G[*]: CatsHash](using hashActual: G[Long]): F[PosLong] = {
      internalDef.contraCoercible[cats.Hash, PosLong, Long, cats.Contravariant](hashActual.asInstanceOf[cats.Hash[Long]])
    }.asInstanceOf[F[PosLong]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait PosLongTypeClassInstance2 extends OrphanCats, OrphanCatsKernel {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedPosLongShow[F[*]: CatsShow, G[*]: CatsShow](using showActual: G[Long]): F[PosLong] = {
      internalDef.contraCoercible[cats.Show, PosLong, Long, cats.Contravariant](showActual.asInstanceOf[cats.Show[Long]])
    }.asInstanceOf[F[PosLong]] // scalafix:ok DisableSyntax.asInstanceOf
  }

  type NonPosLong = NonPosLong.Type
  object NonPosLong extends Numeric[Long], MinMax[Long], NonPosLongTypeClassInstances {
    override def min: Type = apply(Long.MinValue)
    override def max: Type = apply(0L)

    override inline def invalidReason(a: Long): String = expectedMessage("a non-positive Long")

    override inline def predicate(a: Long): Boolean = a <= 0L

  }
  private[types] trait NonPosLongTypeClassInstances extends NonPosLongTypeClassInstance1 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNonPosLongEq[F[*]: CatsEq, G[*]: CatsEq](using eqActual: G[Long]): F[NonPosLong] = {
      internalDef.contraCoercible[cats.Eq, NonPosLong, Long, cats.Contravariant](eqActual.asInstanceOf[cats.Eq[Long]])
    }.asInstanceOf[F[NonPosLong]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait NonPosLongTypeClassInstance1 extends NonPosLongTypeClassInstance2 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNonPosLongHash[F[*]: CatsHash, G[*]: CatsHash](using hashActual: G[Long]): F[NonPosLong] = {
      internalDef.contraCoercible[cats.Hash, NonPosLong, Long, cats.Contravariant](hashActual.asInstanceOf[cats.Hash[Long]])
    }.asInstanceOf[F[NonPosLong]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait NonPosLongTypeClassInstance2 extends OrphanCats, OrphanCatsKernel {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNonPosLongShow[F[*]: CatsShow, G[*]: CatsShow](using showActual: G[Long]): F[NonPosLong] = {
      internalDef.contraCoercible[cats.Show, NonPosLong, Long, cats.Contravariant](showActual.asInstanceOf[cats.Show[Long]])
    }.asInstanceOf[F[NonPosLong]] // scalafix:ok DisableSyntax.asInstanceOf
  }

  type NegShort = NegShort.Type
  object NegShort extends Numeric[Short], MinMax[Short], NegShortTypeClassInstances {
    override def min: Type = apply(Short.MinValue)
    override def max: Type = apply(-1)

    override inline def invalidReason(a: Short): String = expectedMessage("a negative Short")

    override inline def predicate(a: Short): Boolean = a < 0

  }
  private[types] trait NegShortTypeClassInstances extends NegShortTypeClassInstance1 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNegShortEq[F[*]: CatsEq, G[*]: CatsEq](using eqActual: G[Short]): F[NegShort] = {
      internalDef.contraCoercible[cats.Eq, NegShort, Short, cats.Contravariant](eqActual.asInstanceOf[cats.Eq[Short]])
    }.asInstanceOf[F[NegShort]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait NegShortTypeClassInstance1 extends NegShortTypeClassInstance2 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNegShortHash[F[*]: CatsHash, G[*]: CatsHash](using hashActual: G[Short]): F[NegShort] = {
      internalDef.contraCoercible[cats.Hash, NegShort, Short, cats.Contravariant](hashActual.asInstanceOf[cats.Hash[Short]])
    }.asInstanceOf[F[NegShort]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait NegShortTypeClassInstance2 extends OrphanCats, OrphanCatsKernel {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNegShortShow[F[*]: CatsShow, G[*]: CatsShow](using showActual: G[Short]): F[NegShort] = {
      internalDef.contraCoercible[cats.Show, NegShort, Short, cats.Contravariant](showActual.asInstanceOf[cats.Show[Short]])
    }.asInstanceOf[F[NegShort]] // scalafix:ok DisableSyntax.asInstanceOf
  }

  type NonNegShort = NonNegShort.Type
  object NonNegShort extends Numeric[Short], MinMax[Short], NonNegShortTypeClassInstances {
    override def min: Type = apply(0)
    override def max: Type = apply(Short.MaxValue)

    override inline def invalidReason(a: Short): String = expectedMessage("a non-negative Short")

    override inline def predicate(a: Short): Boolean = a >= 0

  }
  private[types] trait NonNegShortTypeClassInstances extends NonNegShortTypeClassInstance1 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNonNegShortEq[F[*]: CatsEq, G[*]: CatsEq](using eqActual: G[Short]): F[NonNegShort] = {
      internalDef.contraCoercible[cats.Eq, NonNegShort, Short, cats.Contravariant](eqActual.asInstanceOf[cats.Eq[Short]])
    }.asInstanceOf[F[NonNegShort]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait NonNegShortTypeClassInstance1 extends NonNegShortTypeClassInstance2 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNonNegShortHash[F[*]: CatsHash, G[*]: CatsHash](using hashActual: G[Short]): F[NonNegShort] = {
      internalDef.contraCoercible[cats.Hash, NonNegShort, Short, cats.Contravariant](hashActual.asInstanceOf[cats.Hash[Short]])
    }.asInstanceOf[F[NonNegShort]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait NonNegShortTypeClassInstance2 extends OrphanCats, OrphanCatsKernel {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNonNegShortShow[F[*]: CatsShow, G[*]: CatsShow](using showActual: G[Short]): F[NonNegShort] = {
      internalDef.contraCoercible[cats.Show, NonNegShort, Short, cats.Contravariant](showActual.asInstanceOf[cats.Show[Short]])
    }.asInstanceOf[F[NonNegShort]] // scalafix:ok DisableSyntax.asInstanceOf
  }

  type PosShort = PosShort.Type
  object PosShort extends Numeric[Short], MinMax[Short], PosShortTypeClassInstances {
    override def min: Type = apply(1)
    override def max: Type = apply(Short.MaxValue)

    override inline def invalidReason(a: Short): String = expectedMessage("a positive Short")

    override inline def predicate(a: Short): Boolean = a > 0

  }
  private[types] trait PosShortTypeClassInstances extends PosShortTypeClassInstance1 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedPosShortEq[F[*]: CatsEq, G[*]: CatsEq](using eqActual: G[Short]): F[PosShort] = {
      internalDef.contraCoercible[cats.Eq, PosShort, Short, cats.Contravariant](eqActual.asInstanceOf[cats.Eq[Short]])
    }.asInstanceOf[F[PosShort]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait PosShortTypeClassInstance1 extends PosShortTypeClassInstance2 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedPosShortHash[F[*]: CatsHash, G[*]: CatsHash](using hashActual: G[Short]): F[PosShort] = {
      internalDef.contraCoercible[cats.Hash, PosShort, Short, cats.Contravariant](hashActual.asInstanceOf[cats.Hash[Short]])
    }.asInstanceOf[F[PosShort]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait PosShortTypeClassInstance2 extends OrphanCats, OrphanCatsKernel {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedPosShortShow[F[*]: CatsShow, G[*]: CatsShow](using showActual: G[Short]): F[PosShort] = {
      internalDef.contraCoercible[cats.Show, PosShort, Short, cats.Contravariant](showActual.asInstanceOf[cats.Show[Short]])
    }.asInstanceOf[F[PosShort]] // scalafix:ok DisableSyntax.asInstanceOf
  }

  type NonPosShort = NonPosShort.Type
  object NonPosShort extends Numeric[Short], MinMax[Short], NonPosShortTypeClassInstances {
    override def min: Type = apply(Short.MinValue)
    override def max: Type = apply(0)

    override inline def invalidReason(a: Short): String = expectedMessage("a non-positive Short")

    override inline def predicate(a: Short): Boolean = a <= 0

  }
  private[types] trait NonPosShortTypeClassInstances extends NonPosShortTypeClassInstance1 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNonPosShortEq[F[*]: CatsEq, G[*]: CatsEq](using eqActual: G[Short]): F[NonPosShort] = {
      internalDef.contraCoercible[cats.Eq, NonPosShort, Short, cats.Contravariant](eqActual.asInstanceOf[cats.Eq[Short]])
    }.asInstanceOf[F[NonPosShort]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait NonPosShortTypeClassInstance1 extends NonPosShortTypeClassInstance2 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNonPosShortHash[F[*]: CatsHash, G[*]: CatsHash](using hashActual: G[Short]): F[NonPosShort] = {
      internalDef.contraCoercible[cats.Hash, NonPosShort, Short, cats.Contravariant](hashActual.asInstanceOf[cats.Hash[Short]])
    }.asInstanceOf[F[NonPosShort]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait NonPosShortTypeClassInstance2 extends OrphanCats, OrphanCatsKernel {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNonPosShortShow[F[*]: CatsShow, G[*]: CatsShow](using showActual: G[Short]): F[NonPosShort] = {
      internalDef.contraCoercible[cats.Show, NonPosShort, Short, cats.Contravariant](showActual.asInstanceOf[cats.Show[Short]])
    }.asInstanceOf[F[NonPosShort]] // scalafix:ok DisableSyntax.asInstanceOf
  }

  type NegByte = NegByte.Type
  object NegByte extends Numeric[Byte], MinMax[Byte], NegByteTypeClassInstances {
    override def min: Type = apply(Byte.MinValue)
    override def max: Type = apply(-1)

    override inline def invalidReason(a: Byte): String = expectedMessage("a negative Byte")

    override inline def predicate(a: Byte): Boolean = a < 0

  }
  private[types] trait NegByteTypeClassInstances extends NegByteTypeClassInstance1 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNegByteEq[F[*]: CatsEq, G[*]: CatsEq](using eqActual: G[Byte]): F[NegByte] = {
      internalDef.contraCoercible[cats.Eq, NegByte, Byte, cats.Contravariant](eqActual.asInstanceOf[cats.Eq[Byte]])
    }.asInstanceOf[F[NegByte]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait NegByteTypeClassInstance1 extends NegByteTypeClassInstance2 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNegByteHash[F[*]: CatsHash, G[*]: CatsHash](using hashActual: G[Byte]): F[NegByte] = {
      internalDef.contraCoercible[cats.Hash, NegByte, Byte, cats.Contravariant](hashActual.asInstanceOf[cats.Hash[Byte]])
    }.asInstanceOf[F[NegByte]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait NegByteTypeClassInstance2 extends OrphanCats, OrphanCatsKernel {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNegByteShow[F[*]: CatsShow, G[*]: CatsShow](using showActual: G[Byte]): F[NegByte] = {
      internalDef.contraCoercible[cats.Show, NegByte, Byte, cats.Contravariant](showActual.asInstanceOf[cats.Show[Byte]])
    }.asInstanceOf[F[NegByte]] // scalafix:ok DisableSyntax.asInstanceOf
  }

  type NonNegByte = NonNegByte.Type
  object NonNegByte extends Numeric[Byte], MinMax[Byte], NonNegByteTypeClassInstances {
    override def min: Type = apply(0)
    override def max: Type = apply(Byte.MaxValue)

    override inline def invalidReason(a: Byte): String = expectedMessage("a non-negative Byte")

    override inline def predicate(a: Byte): Boolean = a >= 0

  }
  private[types] trait NonNegByteTypeClassInstances extends NonNegByteTypeClassInstance1 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNonNegByteEq[F[*]: CatsEq, G[*]: CatsEq](using eqActual: G[Byte]): F[NonNegByte] = {
      internalDef.contraCoercible[cats.Eq, NonNegByte, Byte, cats.Contravariant](eqActual.asInstanceOf[cats.Eq[Byte]])
    }.asInstanceOf[F[NonNegByte]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait NonNegByteTypeClassInstance1 extends NonNegByteTypeClassInstance2 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNonNegByteHash[F[*]: CatsHash, G[*]: CatsHash](using hashActual: G[Byte]): F[NonNegByte] = {
      internalDef.contraCoercible[cats.Hash, NonNegByte, Byte, cats.Contravariant](hashActual.asInstanceOf[cats.Hash[Byte]])
    }.asInstanceOf[F[NonNegByte]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait NonNegByteTypeClassInstance2 extends OrphanCats, OrphanCatsKernel {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNonNegByteShow[F[*]: CatsShow, G[*]: CatsShow](using showActual: G[Byte]): F[NonNegByte] = {
      internalDef.contraCoercible[cats.Show, NonNegByte, Byte, cats.Contravariant](showActual.asInstanceOf[cats.Show[Byte]])
    }.asInstanceOf[F[NonNegByte]] // scalafix:ok DisableSyntax.asInstanceOf
  }

  type PosByte = PosByte.Type
  object PosByte extends Numeric[Byte], MinMax[Byte], PosByteTypeClassInstances {
    override def min: Type = apply(1)
    override def max: Type = apply(Byte.MaxValue)

    override inline def invalidReason(a: Byte): String = expectedMessage("a positive Byte")

    override inline def predicate(a: Byte): Boolean = a > 0

  }
  private[types] trait PosByteTypeClassInstances extends PosByteTypeClassInstance1 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedPosByteEq[F[*]: CatsEq, G[*]: CatsEq](using eqActual: G[Byte]): F[PosByte] = {
      internalDef.contraCoercible[cats.Eq, PosByte, Byte, cats.Contravariant](eqActual.asInstanceOf[cats.Eq[Byte]])
    }.asInstanceOf[F[PosByte]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait PosByteTypeClassInstance1 extends PosByteTypeClassInstance2 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedPosByteHash[F[*]: CatsHash, G[*]: CatsHash](using hashActual: G[Byte]): F[PosByte] = {
      internalDef.contraCoercible[cats.Hash, PosByte, Byte, cats.Contravariant](hashActual.asInstanceOf[cats.Hash[Byte]])
    }.asInstanceOf[F[PosByte]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait PosByteTypeClassInstance2 extends OrphanCats, OrphanCatsKernel {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedPosByteShow[F[*]: CatsShow, G[*]: CatsShow](using showActual: G[Byte]): F[PosByte] = {
      internalDef.contraCoercible[cats.Show, PosByte, Byte, cats.Contravariant](showActual.asInstanceOf[cats.Show[Byte]])
    }.asInstanceOf[F[PosByte]] // scalafix:ok DisableSyntax.asInstanceOf
  }

  type NonPosByte = NonPosByte.Type
  object NonPosByte extends Numeric[Byte], MinMax[Byte], NonPosByteTypeClassInstances {
    override def min: Type = apply(Byte.MinValue)
    override def max: Type = apply(0)

    override inline def invalidReason(a: Byte): String = expectedMessage("a non-positive Byte")

    override inline def predicate(a: Byte): Boolean = a <= 0

  }
  private[types] trait NonPosByteTypeClassInstances extends NonPosByteTypeClassInstance1 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNonPosByteEq[F[*]: CatsEq, G[*]: CatsEq](using eqActual: G[Byte]): F[NonPosByte] = {
      internalDef.contraCoercible[cats.Eq, NonPosByte, Byte, cats.Contravariant](eqActual.asInstanceOf[cats.Eq[Byte]])
    }.asInstanceOf[F[NonPosByte]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait NonPosByteTypeClassInstance1 extends NonPosByteTypeClassInstance2 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNonPosByteHash[F[*]: CatsHash, G[*]: CatsHash](using hashActual: G[Byte]): F[NonPosByte] = {
      internalDef.contraCoercible[cats.Hash, NonPosByte, Byte, cats.Contravariant](hashActual.asInstanceOf[cats.Hash[Byte]])
    }.asInstanceOf[F[NonPosByte]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait NonPosByteTypeClassInstance2 extends OrphanCats, OrphanCatsKernel {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNonPosByteShow[F[*]: CatsShow, G[*]: CatsShow](using showActual: G[Byte]): F[NonPosByte] = {
      internalDef.contraCoercible[cats.Show, NonPosByte, Byte, cats.Contravariant](showActual.asInstanceOf[cats.Show[Byte]])
    }.asInstanceOf[F[NonPosByte]] // scalafix:ok DisableSyntax.asInstanceOf
  }

  type NegFloat = NegFloat.Type
  object NegFloat extends Numeric[Float], MinMax[Float], NegFloatTypeClassInstances {
    override def min: Type = apply(-java.lang.Float.MAX_VALUE) // Float.MinValue
    override def max: Type = apply(-1.4e-45f) // math.nextDown(0f)

    override inline def invalidReason(a: Float): String = expectedMessage("a negative Float")

    override inline def predicate(a: Float): Boolean = a < 0f

  }
  private[types] trait NegFloatTypeClassInstances extends NegFloatTypeClassInstance1 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNegFloatEq[F[*]: CatsEq, G[*]: CatsEq](using eqActual: G[Float]): F[NegFloat] = {
      internalDef.contraCoercible[cats.Eq, NegFloat, Float, cats.Contravariant](eqActual.asInstanceOf[cats.Eq[Float]])
    }.asInstanceOf[F[NegFloat]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait NegFloatTypeClassInstance1 extends NegFloatTypeClassInstance2 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNegFloatHash[F[*]: CatsHash, G[*]: CatsHash](using hashActual: G[Float]): F[NegFloat] = {
      internalDef.contraCoercible[cats.Hash, NegFloat, Float, cats.Contravariant](hashActual.asInstanceOf[cats.Hash[Float]])
    }.asInstanceOf[F[NegFloat]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait NegFloatTypeClassInstance2 extends OrphanCats, OrphanCatsKernel {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNegFloatShow[F[*]: CatsShow, G[*]: CatsShow](using showActual: G[Float]): F[NegFloat] = {
      internalDef.contraCoercible[cats.Show, NegFloat, Float, cats.Contravariant](showActual.asInstanceOf[cats.Show[Float]])
    }.asInstanceOf[F[NegFloat]] // scalafix:ok DisableSyntax.asInstanceOf
  }

  type NonNegFloat = NonNegFloat.Type
  object NonNegFloat extends Numeric[Float], MinMax[Float], NonNegFloatTypeClassInstances {
    override def min: Type = apply(0f)
    override def max: Type = apply(Float.MaxValue)

    override inline def invalidReason(a: Float): String = expectedMessage("a non-negative Float")

    override inline def predicate(a: Float): Boolean = a >= 0f

  }
  private[types] trait NonNegFloatTypeClassInstances extends NonNegFloatTypeClassInstance1 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNonNegFloatEq[F[*]: CatsEq, G[*]: CatsEq](using eqActual: G[Float]): F[NonNegFloat] = {
      internalDef.contraCoercible[cats.Eq, NonNegFloat, Float, cats.Contravariant](eqActual.asInstanceOf[cats.Eq[Float]])
    }.asInstanceOf[F[NonNegFloat]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait NonNegFloatTypeClassInstance1 extends NonNegFloatTypeClassInstance2 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNonNegFloatHash[F[*]: CatsHash, G[*]: CatsHash](using hashActual: G[Float]): F[NonNegFloat] = {
      internalDef.contraCoercible[cats.Hash, NonNegFloat, Float, cats.Contravariant](hashActual.asInstanceOf[cats.Hash[Float]])
    }.asInstanceOf[F[NonNegFloat]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait NonNegFloatTypeClassInstance2 extends OrphanCats, OrphanCatsKernel {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNonNegFloatShow[F[*]: CatsShow, G[*]: CatsShow](using showActual: G[Float]): F[NonNegFloat] = {
      internalDef.contraCoercible[cats.Show, NonNegFloat, Float, cats.Contravariant](showActual.asInstanceOf[cats.Show[Float]])
    }.asInstanceOf[F[NonNegFloat]] // scalafix:ok DisableSyntax.asInstanceOf
  }

  type PosFloat = PosFloat.Type
  object PosFloat extends Numeric[Float], MinMax[Float], PosFloatTypeClassInstances {
    override def min: Type = apply(1.4e-45f) // math.nextUp(0f)
    override def max: Type = apply(Float.MaxValue)

    override inline def invalidReason(a: Float): String = expectedMessage("a positive Float")

    override inline def predicate(a: Float): Boolean = a > 0f

  }
  private[types] trait PosFloatTypeClassInstances extends PosFloatTypeClassInstance1 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedPosFloatEq[F[*]: CatsEq, G[*]: CatsEq](using eqActual: G[Float]): F[PosFloat] = {
      internalDef.contraCoercible[cats.Eq, PosFloat, Float, cats.Contravariant](eqActual.asInstanceOf[cats.Eq[Float]])
    }.asInstanceOf[F[PosFloat]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait PosFloatTypeClassInstance1 extends PosFloatTypeClassInstance2 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedPosFloatHash[F[*]: CatsHash, G[*]: CatsHash](using hashActual: G[Float]): F[PosFloat] = {
      internalDef.contraCoercible[cats.Hash, PosFloat, Float, cats.Contravariant](hashActual.asInstanceOf[cats.Hash[Float]])
    }.asInstanceOf[F[PosFloat]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait PosFloatTypeClassInstance2 extends OrphanCats, OrphanCatsKernel {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedPosFloatShow[F[*]: CatsShow, G[*]: CatsShow](using showActual: G[Float]): F[PosFloat] = {
      internalDef.contraCoercible[cats.Show, PosFloat, Float, cats.Contravariant](showActual.asInstanceOf[cats.Show[Float]])
    }.asInstanceOf[F[PosFloat]] // scalafix:ok DisableSyntax.asInstanceOf
  }

  type NonPosFloat = NonPosFloat.Type
  object NonPosFloat extends Numeric[Float], MinMax[Float], NonPosFloatTypeClassInstances {
    override def min: Type = apply(-java.lang.Float.MAX_VALUE)
    override def max: Type = apply(0f)

    override inline def invalidReason(a: Float): String = expectedMessage("a non-positive Float")

    override inline def predicate(a: Float): Boolean = a <= 0f

  }
  private[types] trait NonPosFloatTypeClassInstances extends NonPosFloatTypeClassInstance1 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNonPosFloatEq[F[*]: CatsEq, G[*]: CatsEq](using eqActual: G[Float]): F[NonPosFloat] = {
      internalDef.contraCoercible[cats.Eq, NonPosFloat, Float, cats.Contravariant](eqActual.asInstanceOf[cats.Eq[Float]])
    }.asInstanceOf[F[NonPosFloat]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait NonPosFloatTypeClassInstance1 extends NonPosFloatTypeClassInstance2 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNonPosFloatHash[F[*]: CatsHash, G[*]: CatsHash](using hashActual: G[Float]): F[NonPosFloat] = {
      internalDef.contraCoercible[cats.Hash, NonPosFloat, Float, cats.Contravariant](hashActual.asInstanceOf[cats.Hash[Float]])
    }.asInstanceOf[F[NonPosFloat]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait NonPosFloatTypeClassInstance2 extends OrphanCats, OrphanCatsKernel {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNonPosFloatShow[F[*]: CatsShow, G[*]: CatsShow](using showActual: G[Float]): F[NonPosFloat] = {
      internalDef.contraCoercible[cats.Show, NonPosFloat, Float, cats.Contravariant](showActual.asInstanceOf[cats.Show[Float]])
    }.asInstanceOf[F[NonPosFloat]] // scalafix:ok DisableSyntax.asInstanceOf
  }

  type NegDouble = NegDouble.Type
  object NegDouble extends Numeric[Double], MinMax[Double], NegDoubleTypeClassInstances {
    override def min: Type = apply(-java.lang.Double.MAX_VALUE) // Double.MinValue
    override def max: Type = apply(-4.9e-324d) // math.nextDown(0d)

    override inline def invalidReason(a: Double): String = expectedMessage("a negative Double")

    override inline def predicate(a: Double): Boolean = a < 0d

  }
  private[types] trait NegDoubleTypeClassInstances extends NegDoubleTypeClassInstance1 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNegDoubleEq[F[*]: CatsEq, G[*]: CatsEq](using eqActual: G[Double]): F[NegDouble] = {
      internalDef.contraCoercible[cats.Eq, NegDouble, Double, cats.Contravariant](eqActual.asInstanceOf[cats.Eq[Double]])
    }.asInstanceOf[F[NegDouble]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait NegDoubleTypeClassInstance1 extends NegDoubleTypeClassInstance2 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNegDoubleHash[F[*]: CatsHash, G[*]: CatsHash](using hashActual: G[Double]): F[NegDouble] = {
      internalDef.contraCoercible[cats.Hash, NegDouble, Double, cats.Contravariant](hashActual.asInstanceOf[cats.Hash[Double]])
    }.asInstanceOf[F[NegDouble]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait NegDoubleTypeClassInstance2 extends OrphanCats, OrphanCatsKernel {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNegDoubleShow[F[*]: CatsShow, G[*]: CatsShow](using showActual: G[Double]): F[NegDouble] = {
      internalDef.contraCoercible[cats.Show, NegDouble, Double, cats.Contravariant](showActual.asInstanceOf[cats.Show[Double]])
    }.asInstanceOf[F[NegDouble]] // scalafix:ok DisableSyntax.asInstanceOf
  }

  type NonNegDouble = NonNegDouble.Type
  object NonNegDouble extends Numeric[Double], MinMax[Double], NonNegDoubleTypeClassInstances {
    override def min: Type = apply(0d)
    override def max: Type = apply(Double.MaxValue)

    override inline def invalidReason(a: Double): String = expectedMessage("a non-negative Double")

    override inline def predicate(a: Double): Boolean = a >= 0d

  }
  private[types] trait NonNegDoubleTypeClassInstances extends NonNegDoubleTypeClassInstance1 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNonNegDoubleEq[F[*]: CatsEq, G[*]: CatsEq](using eqActual: G[Double]): F[NonNegDouble] = {
      internalDef.contraCoercible[cats.Eq, NonNegDouble, Double, cats.Contravariant](eqActual.asInstanceOf[cats.Eq[Double]])
    }.asInstanceOf[F[NonNegDouble]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait NonNegDoubleTypeClassInstance1 extends NonNegDoubleTypeClassInstance2 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNonNegDoubleHash[F[*]: CatsHash, G[*]: CatsHash](using hashActual: G[Double]): F[NonNegDouble] = {
      internalDef.contraCoercible[cats.Hash, NonNegDouble, Double, cats.Contravariant](hashActual.asInstanceOf[cats.Hash[Double]])
    }.asInstanceOf[F[NonNegDouble]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait NonNegDoubleTypeClassInstance2 extends OrphanCats, OrphanCatsKernel {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNonNegDoubleShow[F[*]: CatsShow, G[*]: CatsShow](using showActual: G[Double]): F[NonNegDouble] = {
      internalDef.contraCoercible[cats.Show, NonNegDouble, Double, cats.Contravariant](showActual.asInstanceOf[cats.Show[Double]])
    }.asInstanceOf[F[NonNegDouble]] // scalafix:ok DisableSyntax.asInstanceOf
  }

  type PosDouble = PosDouble.Type
  object PosDouble extends Numeric[Double], MinMax[Double], PosDoubleTypeClassInstances {
    override def min: Type = apply(4.9e-324d) // math.nextUp(0d)
    override def max: Type = apply(Double.MaxValue)

    override inline def invalidReason(a: Double): String = expectedMessage("a positive Double")

    override inline def predicate(a: Double): Boolean = a > 0d

  }
  private[types] trait PosDoubleTypeClassInstances extends PosDoubleTypeClassInstance1 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedPosDoubleEq[F[*]: CatsEq, G[*]: CatsEq](using eqActual: G[Double]): F[PosDouble] = {
      internalDef.contraCoercible[cats.Eq, PosDouble, Double, cats.Contravariant](eqActual.asInstanceOf[cats.Eq[Double]])
    }.asInstanceOf[F[PosDouble]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait PosDoubleTypeClassInstance1 extends PosDoubleTypeClassInstance2 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedPosDoubleHash[F[*]: CatsHash, G[*]: CatsHash](using hashActual: G[Double]): F[PosDouble] = {
      internalDef.contraCoercible[cats.Hash, PosDouble, Double, cats.Contravariant](hashActual.asInstanceOf[cats.Hash[Double]])
    }.asInstanceOf[F[PosDouble]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait PosDoubleTypeClassInstance2 extends OrphanCats, OrphanCatsKernel {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedPosDoubleShow[F[*]: CatsShow, G[*]: CatsShow](using showActual: G[Double]): F[PosDouble] = {
      internalDef.contraCoercible[cats.Show, PosDouble, Double, cats.Contravariant](showActual.asInstanceOf[cats.Show[Double]])
    }.asInstanceOf[F[PosDouble]] // scalafix:ok DisableSyntax.asInstanceOf
  }

  type NonPosDouble = NonPosDouble.Type
  object NonPosDouble extends Numeric[Double], MinMax[Double], NonPosDoubleTypeClassInstances {
    override def min: Type = apply(-java.lang.Double.MAX_VALUE) // Double.MinValue
    override def max: Type = apply(0d)

    override inline def invalidReason(a: Double): String = expectedMessage("a non-positive Double")

    override inline def predicate(a: Double): Boolean = a <= 0d

  }
  private[types] trait NonPosDoubleTypeClassInstances extends NonPosDoubleTypeClassInstance1 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNonPosDoubleEq[F[*]: CatsEq, G[*]: CatsEq](using eqActual: G[Double]): F[NonPosDouble] = {
      internalDef.contraCoercible[cats.Eq, NonPosDouble, Double, cats.Contravariant](eqActual.asInstanceOf[cats.Eq[Double]])
    }.asInstanceOf[F[NonPosDouble]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait NonPosDoubleTypeClassInstance1 extends NonPosDoubleTypeClassInstance2 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNonPosDoubleHash[F[*]: CatsHash, G[*]: CatsHash](using hashActual: G[Double]): F[NonPosDouble] = {
      internalDef.contraCoercible[cats.Hash, NonPosDouble, Double, cats.Contravariant](hashActual.asInstanceOf[cats.Hash[Double]])
    }.asInstanceOf[F[NonPosDouble]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait NonPosDoubleTypeClassInstance2 extends OrphanCats, OrphanCatsKernel {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNonPosDoubleShow[F[*]: CatsShow, G[*]: CatsShow](using showActual: G[Double]): F[NonPosDouble] = {
      internalDef.contraCoercible[cats.Show, NonPosDouble, Double, cats.Contravariant](showActual.asInstanceOf[cats.Show[Double]])
    }.asInstanceOf[F[NonPosDouble]] // scalafix:ok DisableSyntax.asInstanceOf
  }

  val BigInt0: BigInt = numericTools.BigInt0

  val BigDecimal0: BigDecimal = numericTools.BigDecimal0

  type NegBigInt = NegBigInt.Type
  @SuppressWarnings(Array("org.wartremover.warts.Overloading"))
  object NegBigInt extends InlinedNumeric[BigInt], NegBigIntTypeClassInstances {
    override inline def invalidReason(a: BigInt): String = expectedMessage("a negative BigInt")

    override def predicate(a: BigInt): Boolean = a < BigInt0

    override inline val inlinedExpectedValue = "a negative BigInt"

    override inline def inlinedPredicate(inline a: BigInt): Boolean = ${ numericTools.isNegativeBigInt('a) }

    inline def apply(inline a: Int): Type = apply(BigInt(a))

    inline def apply(inline a: Long): Type = apply(BigInt(a))

    inline def apply(inline a: BigDecimal): Type = apply(a.toBigInt)

    inline def apply(inline a: String): Type = apply(BigInt(a))

  }
  private[types] trait NegBigIntTypeClassInstances extends NegBigIntTypeClassInstance1 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNegBigIntEq[F[*]: CatsEq, G[*]: CatsEq](using eqActual: G[BigInt]): F[NegBigInt] = {
      internalDef.contraCoercible[cats.Eq, NegBigInt, BigInt, cats.Contravariant](eqActual.asInstanceOf[cats.Eq[BigInt]])
    }.asInstanceOf[F[NegBigInt]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait NegBigIntTypeClassInstance1 extends NegBigIntTypeClassInstance2 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNegBigIntHash[F[*]: CatsHash, G[*]: CatsHash](using hashActual: G[BigInt]): F[NegBigInt] = {
      internalDef.contraCoercible[cats.Hash, NegBigInt, BigInt, cats.Contravariant](hashActual.asInstanceOf[cats.Hash[BigInt]])
    }.asInstanceOf[F[NegBigInt]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait NegBigIntTypeClassInstance2 extends OrphanCats, OrphanCatsKernel {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNegBigIntShow[F[*]: CatsShow, G[*]: CatsShow](using showActual: G[BigInt]): F[NegBigInt] = {
      internalDef.contraCoercible[cats.Show, NegBigInt, BigInt, cats.Contravariant](showActual.asInstanceOf[cats.Show[BigInt]])
    }.asInstanceOf[F[NegBigInt]] // scalafix:ok DisableSyntax.asInstanceOf
  }

  type NonNegBigInt = NonNegBigInt.Type
  @SuppressWarnings(Array("org.wartremover.warts.Overloading"))
  object NonNegBigInt extends InlinedNumeric[BigInt], NonNegBigIntTypeClassInstances {
    override inline def invalidReason(a: BigInt): String = expectedMessage("a non-negative BigInt")

    override def predicate(a: BigInt): Boolean = a >= BigInt0

    override inline val inlinedExpectedValue = "a non-negative BigInt"

    override inline def inlinedPredicate(inline a: BigInt): Boolean = ${ numericTools.isNonNegativeBigInt('a) }

    inline def apply(inline a: Int): Type = apply(BigInt(a))

    inline def apply(inline a: Long): Type = apply(BigInt(a))

    inline def apply(inline a: String): Type = apply(BigInt(a))

  }
  private[types] trait NonNegBigIntTypeClassInstances extends NonNegBigIntTypeClassInstance1 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNonNegBigIntEq[F[*]: CatsEq, G[*]: CatsEq](using eqActual: G[BigInt]): F[NonNegBigInt] = {
      internalDef.contraCoercible[cats.Eq, NonNegBigInt, BigInt, cats.Contravariant](eqActual.asInstanceOf[cats.Eq[BigInt]])
    }.asInstanceOf[F[NonNegBigInt]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait NonNegBigIntTypeClassInstance1 extends NonNegBigIntTypeClassInstance2 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNonNegBigIntHash[F[*]: CatsHash, G[*]: CatsHash](using hashActual: G[BigInt]): F[NonNegBigInt] = {
      internalDef.contraCoercible[cats.Hash, NonNegBigInt, BigInt, cats.Contravariant](hashActual.asInstanceOf[cats.Hash[BigInt]])
    }.asInstanceOf[F[NonNegBigInt]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait NonNegBigIntTypeClassInstance2 extends OrphanCats, OrphanCatsKernel {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNonNegBigIntShow[F[*]: CatsShow, G[*]: CatsShow](using showActual: G[BigInt]): F[NonNegBigInt] = {
      internalDef.contraCoercible[cats.Show, NonNegBigInt, BigInt, cats.Contravariant](showActual.asInstanceOf[cats.Show[BigInt]])
    }.asInstanceOf[F[NonNegBigInt]] // scalafix:ok DisableSyntax.asInstanceOf
  }

  type PosBigInt = PosBigInt.Type
  @SuppressWarnings(Array("org.wartremover.warts.Overloading"))
  object PosBigInt extends InlinedNumeric[BigInt], PosBigIntTypeClassInstances {
    override inline def invalidReason(a: BigInt): String = expectedMessage("a positive BigInt")

    override def predicate(a: BigInt): Boolean = a > BigInt0

    override inline val inlinedExpectedValue = "a positive BigInt"

    override inline def inlinedPredicate(inline a: BigInt): Boolean = ${ numericTools.isPositiveBigInt('a) }

    inline def apply(inline a: Int): Type = apply(BigInt(a))

    inline def apply(inline a: Long): Type = apply(BigInt(a))

    inline def apply(inline a: String): Type = apply(BigInt(a))

  }
  private[types] trait PosBigIntTypeClassInstances extends PosBigIntTypeClassInstance1 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedPosBigIntEq[F[*]: CatsEq, G[*]: CatsEq](using eqActual: G[BigInt]): F[PosBigInt] = {
      internalDef.contraCoercible[cats.Eq, PosBigInt, BigInt, cats.Contravariant](eqActual.asInstanceOf[cats.Eq[BigInt]])
    }.asInstanceOf[F[PosBigInt]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait PosBigIntTypeClassInstance1 extends PosBigIntTypeClassInstance2 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedPosBigIntHash[F[*]: CatsHash, G[*]: CatsHash](using hashActual: G[BigInt]): F[PosBigInt] = {
      internalDef.contraCoercible[cats.Hash, PosBigInt, BigInt, cats.Contravariant](hashActual.asInstanceOf[cats.Hash[BigInt]])
    }.asInstanceOf[F[PosBigInt]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait PosBigIntTypeClassInstance2 extends OrphanCats, OrphanCatsKernel {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedPosBigIntShow[F[*]: CatsShow, G[*]: CatsShow](using showActual: G[BigInt]): F[PosBigInt] = {
      internalDef.contraCoercible[cats.Show, PosBigInt, BigInt, cats.Contravariant](showActual.asInstanceOf[cats.Show[BigInt]])
    }.asInstanceOf[F[PosBigInt]] // scalafix:ok DisableSyntax.asInstanceOf
  }

  type NonPosBigInt = NonPosBigInt.Type
  @SuppressWarnings(Array("org.wartremover.warts.Overloading"))
  object NonPosBigInt extends InlinedNumeric[BigInt], NonPosBigIntTypeClassInstances {
    override inline def invalidReason(a: BigInt): String = expectedMessage("a non-positive BigInt")

    override def predicate(a: BigInt): Boolean = a <= BigInt0

    override inline val inlinedExpectedValue = "a non-positive BigInt"

    override inline def inlinedPredicate(inline a: BigInt): Boolean = ${ numericTools.isNonPositiveBigInt('a) }

    inline def apply(inline a: Int): Type = apply(BigInt(a))

    inline def apply(inline a: Long): Type = apply(BigInt(a))

    inline def apply(inline a: String): Type = apply(BigInt(a))

  }
  private[types] trait NonPosBigIntTypeClassInstances extends NonPosBigIntTypeClassInstance1 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNonPosBigIntEq[F[*]: CatsEq, G[*]: CatsEq](using eqActual: G[BigInt]): F[NonPosBigInt] = {
      internalDef.contraCoercible[cats.Eq, NonPosBigInt, BigInt, cats.Contravariant](eqActual.asInstanceOf[cats.Eq[BigInt]])
    }.asInstanceOf[F[NonPosBigInt]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait NonPosBigIntTypeClassInstance1 extends NonPosBigIntTypeClassInstance2 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNonPosBigIntHash[F[*]: CatsHash, G[*]: CatsHash](using hashActual: G[BigInt]): F[NonPosBigInt] = {
      internalDef.contraCoercible[cats.Hash, NonPosBigInt, BigInt, cats.Contravariant](hashActual.asInstanceOf[cats.Hash[BigInt]])
    }.asInstanceOf[F[NonPosBigInt]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait NonPosBigIntTypeClassInstance2 extends OrphanCats, OrphanCatsKernel {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNonPosBigIntShow[F[*]: CatsShow, G[*]: CatsShow](using showActual: G[BigInt]): F[NonPosBigInt] = {
      internalDef.contraCoercible[cats.Show, NonPosBigInt, BigInt, cats.Contravariant](showActual.asInstanceOf[cats.Show[BigInt]])
    }.asInstanceOf[F[NonPosBigInt]] // scalafix:ok DisableSyntax.asInstanceOf
  }

  type NegBigDecimal = NegBigDecimal.Type
  @SuppressWarnings(Array("org.wartremover.warts.Overloading"))
  object NegBigDecimal extends InlinedNumeric[BigDecimal], NegBigDecimalTypeClassInstances {
    override inline def invalidReason(a: BigDecimal): String = expectedMessage("a negative BigDecimal")

    override def predicate(a: BigDecimal): Boolean = a < BigDecimal0

    override inline val inlinedExpectedValue = "a negative BigDecimal"

    override inline def inlinedPredicate(inline a: BigDecimal): Boolean = ${ numericTools.isNegativeBigDecimal('a) }

    inline def apply(inline a: Int): Type = apply(BigDecimal(a))

    inline def apply(inline a: Long): Type = apply(BigDecimal(a))

    inline def apply(inline a: Float): Type = apply(BigDecimal(a))

    inline def apply(inline a: Double): Type = apply(BigDecimal(a))

    inline def apply(inline a: String): Type = apply(BigDecimal(a))

  }
  private[types] trait NegBigDecimalTypeClassInstances extends NegBigDecimalTypeClassInstance1 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNegBigDecimalEq[F[*]: CatsEq, G[*]: CatsEq](using eqActual: G[BigDecimal]): F[NegBigDecimal] = {
      internalDef.contraCoercible[cats.Eq, NegBigDecimal, BigDecimal, cats.Contravariant](eqActual.asInstanceOf[cats.Eq[BigDecimal]])
    }.asInstanceOf[F[NegBigDecimal]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait NegBigDecimalTypeClassInstance1 extends NegBigDecimalTypeClassInstance2 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNegBigDecimalHash[F[*]: CatsHash, G[*]: CatsHash](using hashActual: G[BigDecimal]): F[NegBigDecimal] = {
      internalDef.contraCoercible[cats.Hash, NegBigDecimal, BigDecimal, cats.Contravariant](hashActual.asInstanceOf[cats.Hash[BigDecimal]])
    }.asInstanceOf[F[NegBigDecimal]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait NegBigDecimalTypeClassInstance2 extends OrphanCats, OrphanCatsKernel {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNegBigDecimalShow[F[*]: CatsShow, G[*]: CatsShow](using showActual: G[BigDecimal]): F[NegBigDecimal] = {
      internalDef.contraCoercible[cats.Show, NegBigDecimal, BigDecimal, cats.Contravariant](showActual.asInstanceOf[cats.Show[BigDecimal]])
    }.asInstanceOf[F[NegBigDecimal]] // scalafix:ok DisableSyntax.asInstanceOf
  }

  type NonNegBigDecimal = NonNegBigDecimal.Type
  @SuppressWarnings(Array("org.wartremover.warts.Overloading"))
  object NonNegBigDecimal extends InlinedNumeric[BigDecimal], NonNegBigDecimalTypeClassInstances {
    override inline def invalidReason(a: BigDecimal): String = expectedMessage("a non-negative BigDecimal")

    override def predicate(a: BigDecimal): Boolean = a >= BigDecimal0

    override inline val inlinedExpectedValue = "a non-negative BigDecimal"

    override inline def inlinedPredicate(inline a: BigDecimal): Boolean = ${ numericTools.isNonNegativeBigDecimal('a) }

    inline def apply(inline a: Int): Type = apply(BigDecimal(a))

    inline def apply(inline a: Long): Type = apply(BigDecimal(a))

    inline def apply(inline a: Float): Type = apply(BigDecimal(a))

    inline def apply(inline a: Double): Type = apply(BigDecimal(a))

    inline def apply(inline a: String): Type = apply(BigDecimal(a))

  }
  private[types] trait NonNegBigDecimalTypeClassInstances extends NonNegBigDecimalTypeClassInstance1 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNonNegBigDecimalEq[F[*]: CatsEq, G[*]: CatsEq](using eqActual: G[BigDecimal]): F[NonNegBigDecimal] = {
      internalDef.contraCoercible[cats.Eq, NonNegBigDecimal, BigDecimal, cats.Contravariant](eqActual.asInstanceOf[cats.Eq[BigDecimal]])
    }.asInstanceOf[F[NonNegBigDecimal]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait NonNegBigDecimalTypeClassInstance1 extends NonNegBigDecimalTypeClassInstance2 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNonNegBigDecimalHash[F[*]: CatsHash, G[*]: CatsHash](using hashActual: G[BigDecimal]): F[NonNegBigDecimal] = {
      internalDef.contraCoercible[cats.Hash, NonNegBigDecimal, BigDecimal, cats.Contravariant](
        hashActual.asInstanceOf[cats.Hash[BigDecimal]]
      )
    }.asInstanceOf[F[NonNegBigDecimal]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait NonNegBigDecimalTypeClassInstance2 extends OrphanCats, OrphanCatsKernel {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNonNegBigDecimalShow[F[*]: CatsShow, G[*]: CatsShow](using showActual: G[BigDecimal]): F[NonNegBigDecimal] = {
      internalDef.contraCoercible[cats.Show, NonNegBigDecimal, BigDecimal, cats.Contravariant](
        showActual.asInstanceOf[cats.Show[BigDecimal]]
      )
    }.asInstanceOf[F[NonNegBigDecimal]] // scalafix:ok DisableSyntax.asInstanceOf
  }

  type PosBigDecimal = PosBigDecimal.Type
  @SuppressWarnings(Array("org.wartremover.warts.Overloading"))
  object PosBigDecimal extends InlinedNumeric[BigDecimal], PosBigDecimalTypeClassInstances {
    override inline def invalidReason(a: BigDecimal): String = expectedMessage("a positive BigDecimal")

    override def predicate(a: BigDecimal): Boolean = a > BigDecimal0

    override inline val inlinedExpectedValue = "a positive BigDecimal"

    override inline def inlinedPredicate(inline a: BigDecimal): Boolean = ${ numericTools.isPositiveBigDecimal('a) }

    inline def apply(inline a: Int): Type = apply(BigDecimal(a))

    inline def apply(inline a: Long): Type = apply(BigDecimal(a))

    inline def apply(inline a: Float): Type = apply(BigDecimal(a))

    inline def apply(inline a: Double): Type = apply(BigDecimal(a))

    inline def apply(inline a: String): Type = apply(BigDecimal(a))

  }
  private[types] trait PosBigDecimalTypeClassInstances extends PosBigDecimalTypeClassInstance1 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedPosBigDecimalEq[F[*]: CatsEq, G[*]: CatsEq](using eqActual: G[BigDecimal]): F[PosBigDecimal] = {
      internalDef.contraCoercible[cats.Eq, PosBigDecimal, BigDecimal, cats.Contravariant](eqActual.asInstanceOf[cats.Eq[BigDecimal]])
    }.asInstanceOf[F[PosBigDecimal]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait PosBigDecimalTypeClassInstance1 extends PosBigDecimalTypeClassInstance2 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedPosBigDecimalHash[F[*]: CatsHash, G[*]: CatsHash](using hashActual: G[BigDecimal]): F[PosBigDecimal] = {
      internalDef.contraCoercible[cats.Hash, PosBigDecimal, BigDecimal, cats.Contravariant](hashActual.asInstanceOf[cats.Hash[BigDecimal]])
    }.asInstanceOf[F[PosBigDecimal]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait PosBigDecimalTypeClassInstance2 extends OrphanCats, OrphanCatsKernel {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedPosBigDecimalShow[F[*]: CatsShow, G[*]: CatsShow](using showActual: G[BigDecimal]): F[PosBigDecimal] = {
      internalDef.contraCoercible[cats.Show, PosBigDecimal, BigDecimal, cats.Contravariant](showActual.asInstanceOf[cats.Show[BigDecimal]])
    }.asInstanceOf[F[PosBigDecimal]] // scalafix:ok DisableSyntax.asInstanceOf
  }

  type NonPosBigDecimal = NonPosBigDecimal.Type
  @SuppressWarnings(Array("org.wartremover.warts.Overloading"))
  object NonPosBigDecimal extends InlinedNumeric[BigDecimal], NonPosBigDecimalTypeClassInstances {
    override inline def invalidReason(a: BigDecimal): String = expectedMessage("a non-positive BigDecimal")

    override def predicate(a: BigDecimal): Boolean = a <= BigDecimal0

    override inline val inlinedExpectedValue = "a non-positive BigDecimal"

    override inline def inlinedPredicate(inline a: BigDecimal): Boolean = ${ numericTools.isNonPositiveBigDecimal('a) }

    inline def apply(inline a: Int): Type = apply(BigDecimal(a))

    inline def apply(inline a: Long): Type = apply(BigDecimal(a))

    inline def apply(inline a: Float): Type = apply(BigDecimal(a))

    inline def apply(inline a: Double): Type = apply(BigDecimal(a))

    inline def apply(inline a: String): Type = apply(BigDecimal(a))

  }
  private[types] trait NonPosBigDecimalTypeClassInstances extends NonPosBigDecimalTypeClassInstance1 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNonPosBigDecimalEq[F[*]: CatsEq, G[*]: CatsEq](using eqActual: G[BigDecimal]): F[NonPosBigDecimal] = {
      internalDef.contraCoercible[cats.Eq, NonPosBigDecimal, BigDecimal, cats.Contravariant](eqActual.asInstanceOf[cats.Eq[BigDecimal]])
    }.asInstanceOf[F[NonPosBigDecimal]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait NonPosBigDecimalTypeClassInstance1 extends NonPosBigDecimalTypeClassInstance2 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNonPosBigDecimalHash[F[*]: CatsHash, G[*]: CatsHash](using hashActual: G[BigDecimal]): F[NonPosBigDecimal] = {
      internalDef.contraCoercible[cats.Hash, NonPosBigDecimal, BigDecimal, cats.Contravariant](
        hashActual.asInstanceOf[cats.Hash[BigDecimal]]
      )
    }.asInstanceOf[F[NonPosBigDecimal]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait NonPosBigDecimalTypeClassInstance2 extends OrphanCats, OrphanCatsKernel {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNonPosBigDecimalShow[F[*]: CatsShow, G[*]: CatsShow](using showActual: G[BigDecimal]): F[NonPosBigDecimal] = {
      internalDef.contraCoercible[cats.Show, NonPosBigDecimal, BigDecimal, cats.Contravariant](
        showActual.asInstanceOf[cats.Show[BigDecimal]]
      )
    }.asInstanceOf[F[NonPosBigDecimal]] // scalafix:ok DisableSyntax.asInstanceOf
  }

}
