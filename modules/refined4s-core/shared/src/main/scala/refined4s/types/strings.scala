package refined4s.types

import orphan.OrphanCats
import orphan.OrphanCatsKernel
import refined4s.*

import java.util.UUID
import scala.annotation.targetName
import scala.quoted.*
import scala.util.control.NonFatal

/** @author Kevin Lee
  * @since 2023-04-25
  */
trait strings {

  // scalafix:off DisableSyntax.noFinalVal

  final type NonEmptyString = strings.NonEmptyString
  final val NonEmptyString = strings.NonEmptyString

  final type NonBlankString = strings.NonBlankString
  final val NonBlankString = strings.NonBlankString

  final type Uuid = strings.Uuid
  final val Uuid = strings.Uuid

  final type UuidV7 = strings.UuidV7
  final val UuidV7 = strings.UuidV7

  // scalafix:on

}
object strings {

  type NonEmptyString = NonEmptyString.Type
  @SuppressWarnings(Array("org.wartremover.warts.Equals"))
  object NonEmptyString extends Refined[String], CanBeOrdered[String], NonEmptyStringTypeClassInstances {

    override inline def invalidReason(a: String): String =
      expectedMessage("a non-empty String")

    override inline def predicate(a: String): Boolean = a != ""

    extension (thisNes: NonEmptyString) {
      @targetName("plus")
      def ++(thatNes: NonEmptyString): NonEmptyString = NonEmptyString.unsafeFrom(thisNes.value + thatNes.value)
    }

  }
  private[types] trait NonEmptyStringTypeClassInstances extends NonEmptyStringTypeClassInstance2 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    given derivedNonEmptyStringEq[F[*]: CatsEq, G[*]: CatsEq](using eqActual: G[String]): F[NonEmptyString] = {
      internalDef.contraCoercible[cats.Eq, NonEmptyString, String, cats.Contravariant](eqActual.asInstanceOf[cats.Eq[String]])
    }.asInstanceOf[F[NonEmptyString]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait NonEmptyStringTypeClassInstance2 extends NonEmptyStringTypeClassInstance1 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    given derivedNonEmptyStringHash[F[*]: CatsHash, G[*]: CatsHash](using hashActual: G[String]): F[NonEmptyString] = {
      internalDef.contraCoercible[cats.Hash, NonEmptyString, String, cats.Contravariant](hashActual.asInstanceOf[cats.Hash[String]])
    }.asInstanceOf[F[NonEmptyString]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait NonEmptyStringTypeClassInstance1 extends OrphanCats, OrphanCatsKernel {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    given derivedNonEmptyStringShow[F[*]: CatsShow, G[*]: CatsShow](using showActual: G[String]): F[NonEmptyString] = {
      internalDef.contraCoercible[cats.Show, NonEmptyString, String, cats.Contravariant](showActual.asInstanceOf[cats.Show[String]])
    }.asInstanceOf[F[NonEmptyString]] // scalafix:ok DisableSyntax.asInstanceOf

  }

  val WhitespaceCharRange: List[(Int, Int)] =
    List(
      9     -> 13,
      28    -> 32,
      5760  -> 5760,
      8192  -> 8198,
      8200  -> 8202,
      8232  -> 8233,
      8287  -> 8287,
      12288 -> 12288,
    )

  type NonBlankString = NonBlankString.Type
  @SuppressWarnings(Array("org.wartremover.warts.Equals"))
  object NonBlankString extends InlinedRefined[String], CanBeOrdered[String], NonBlankStringInstances {

    override inline val inlinedExpectedValue = "not all whitespace non-empty String"

    override inline def inlinedPredicate(inline a: String): Boolean = ${ isValidNotAllWhitespaceNonEmptyString('a) }

    override def invalidReason(a: String): String =
      expectedMessage("not all whitespace non-empty String")

    private val stringToType: Coercible[String, Type] = Coercible.instance[String, Type]

    override def from(a: String): Either[String, Type] = {
      Either.cond(
        predicate(a),
        stringToType(a),
        "Invalid value: [" + a + s"], unicode=[${a.map(c => "\\u%04x".format(c.toInt)).mkString}]. " + invalidReason(a),
      )
    }

    override def predicate(a: String): Boolean = a != "" && !a.forall(c => WhitespaceCharRange.exists((min, max) => c >= min && c <= max))

    extension (inline thisNbs: Type) {
      @targetName("plusPlus")
      inline def ++(thatNbs: Type): Type = NonBlankString.unsafeFrom(thisNbs.value + thatNbs.value)

      inline def prependString(that: String): Type = NonBlankString.unsafeFrom(that + thisNbs.value)

      inline def appendString(that: String): Type = NonBlankString.unsafeFrom(thisNbs.value + that)
    }

  }
  private[types] trait NonBlankStringInstances extends NonBlankStringInstance2 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    given derivedNonBlankStringEq[F[*]: CatsEq, G[*]: CatsEq](using eqActual: G[String]): F[NonBlankString] = {
      internalDef.contraCoercible[cats.Eq, NonBlankString, String, cats.Contravariant](eqActual.asInstanceOf[cats.Eq[String]])
    }.asInstanceOf[F[NonBlankString]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait NonBlankStringInstance2 extends NonBlankStringInstance1 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    given derivedNonBlankStringHash[F[*]: CatsHash, G[*]: CatsHash](using hashActual: G[String]): F[NonBlankString] = {
      internalDef.contraCoercible[cats.Hash, NonBlankString, String, cats.Contravariant](hashActual.asInstanceOf[cats.Hash[String]])
    }.asInstanceOf[F[NonBlankString]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait NonBlankStringInstance1 extends OrphanCats, OrphanCatsKernel {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    given derivedNonBlankStringShow[F[*]: CatsShow, G[*]: CatsShow](using showActual: G[String]): F[NonBlankString] = {
      internalDef.contraCoercible[cats.Show, NonBlankString, String, cats.Contravariant](showActual.asInstanceOf[cats.Show[String]])
    }.asInstanceOf[F[NonBlankString]] // scalafix:ok DisableSyntax.asInstanceOf
  }

  type Uuid = Uuid.Type
  object Uuid extends InlinedRefined[String], CanBeOrdered[String], UuidInstances {
    override inline val inlinedExpectedValue = "UUID"

    override inline def inlinedPredicate(inline a: String): Boolean = ${ isValidateUuid('a) }

    override def invalidReason(a: String): String = expectedMessage(inlinedExpectedValue)

    override def predicate(a: String): Boolean =
      try {
        UUID.fromString(a)
        true
      } catch {
        case NonFatal(_) =>
          false
      }

    @SuppressWarnings(Array("org.wartremover.warts.ToString", "org.wartremover.warts.Overloading"))
    def apply(a: UUID): Type = unsafeFrom(a.toString)

    extension (uuid: Uuid) {
      def toUUID: UUID = UUID.fromString(uuid.value)
    }
  }
  private[types] trait UuidInstances extends UuidInstance2 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    given derivedUuidEq[F[*]: CatsEq, G[*]: CatsEq](using eqActual: G[String]): F[Uuid] = {
      internalDef.contraCoercible[cats.Eq, Uuid, String, cats.Contravariant](eqActual.asInstanceOf[cats.Eq[String]])
    }.asInstanceOf[F[Uuid]] // scalafix:ok DisableSyntax.asInstanceOf
  }

  private[types] trait UuidInstance2 extends UuidInstance1 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    given derivedUuidHash[F[*]: CatsHash, G[*]: CatsHash](using hashActual: G[String]): F[Uuid] = {
      internalDef.contraCoercible[cats.Hash, Uuid, String, cats.Contravariant](hashActual.asInstanceOf[cats.Hash[String]])
    }.asInstanceOf[F[Uuid]] // scalafix:ok DisableSyntax.asInstanceOf
  }

  private[types] trait UuidInstance1 extends OrphanCats, OrphanCatsKernel {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    given derivedUuidShow[F[*]: CatsShow, G[*]: CatsShow](using showActual: G[String]): F[Uuid] = {
      internalDef.contraCoercible[cats.Show, Uuid, String, cats.Contravariant](showActual.asInstanceOf[cats.Show[String]])
    }.asInstanceOf[F[Uuid]] // scalafix:ok DisableSyntax.asInstanceOf
  }

  type UuidV7 = UuidV7.Type
  object UuidV7 extends InlinedRefined[UUID], CanBeOrdered[UUID], UuidV7Instances {
    override inline val inlinedExpectedValue =
      "UUID v7 (RFC 9562) - For more info, see https://www.rfc-editor.org/rfc/rfc9562#name-uuid-version-7"

    private inline def inlinedPredicateForString(inline a: String): Boolean = ${ refined4s.internal.UuidV7Macros.isValidateUuidV7('a) }

    override inline def inlinedPredicate(inline a: UUID): Boolean =
      ${ refined4s.internal.UuidV7Macros.isValidateJavaUuidV7('a, "UuidV7") }

    override def invalidReason(a: UUID): String = expectedMessage(inlinedExpectedValue)

    override def predicate(a: UUID): Boolean = a.version() == 7 && a.variant() == 2

    @SuppressWarnings(Array("org.wartremover.warts.ToString", "org.wartremover.warts.Overloading", "org.wartremover.warts.AsInstanceOf"))
    inline def apply(inline a: String): Type = {
      inline if inlinedPredicateForString(a) then UUID.fromString(a).asInstanceOf[Type] // scalafix:ok DisableSyntax.asInstanceOf
      else refined4s.internal.RefinedMacros.internalError(a, inlinedExpectedValue)
    }

    def fromString(uuidString: String): Either[String, Type] = {
      scala
        .util
        .Try(UUID.fromString(uuidString))
        .toEither
        .left
        .map(err => "Invalid value: [" + uuidString + "]. " + expectedMessage(inlinedExpectedValue))
        .flatMap(from)
    }

    @SuppressWarnings(Array("org.wartremover.warts.Throw"))
    def unsafeFromString(uuidString: String): Type =
      fromString(uuidString).fold(err => throw new IllegalArgumentException(err), identity) // scalafix:ok DisableSyntax.throw

    extension (uuid: UuidV7) {
      def toUuid: Uuid = Uuid(uuid.value)
      def toUUID: UUID = uuid.value
    }

    private val timestampAndSequence = new java.util.concurrent.atomic.AtomicLong()
    private lazy val entropy         = new java.security.SecureRandom()
    private val Version              = 7L
    private val Variant              = 2L // RFC 9562 uses 10x for the variant, which is 2 in UUID API

    @scala.annotation.tailrec
    private def updateAndGetState(): (Long, Long) = {
      val state         = timestampAndSequence.get()
      val lastTimestamp = state >>> 16
      val lastSequence  = state & 0xffffL

      val currentTimestamp = System.currentTimeMillis()

      val (newTimestamp, newSequence) =
        if (currentTimestamp > lastTimestamp) {
          (currentTimestamp, 0L)
        } else if (currentTimestamp == lastTimestamp) {
          val nextSequence = lastSequence + 1
          if (nextSequence > 0xfffL) { // Exceeded 12 bits allocated for rand_a
            (lastTimestamp + 1, 0L)
          } else {
            (lastTimestamp, nextSequence)
          }
        } else {
          /* Clock moved backwards. To maintain monotonicity, we use the last timestamp and increment sequence */
          val nextSequence = lastSequence + 1
          if (nextSequence > 0xfffL) {
            (lastTimestamp + 1, 0L)
          } else {
            (lastTimestamp, nextSequence)
          }
        }

      val newState = (newTimestamp << 16) | newSequence
      if (timestampAndSequence.compareAndSet(state, newState)) {
        (newTimestamp, newSequence)
      } else {
        updateAndGetState()
      }
    }

    def generate(): Type = {
      val (newTimestamp, newSequence) = updateAndGetState()

      val randA = newSequence & 0xfffL

      /* mostSigBits:
       * 48 bits: timestamp
       * 4 bits: version (7)
       * 12 bits: rand_a (sequence)
       */
      val mostSigBits = (newTimestamp << 16) | (Version << 12) | randA

      /*
       * leastSigBits:
       * 2 bits: variant (10)
       * 62 bits: rand_b (random)
       */
      val randB        = entropy.nextLong() & 0x3fffffffffffffffL // 62 bits
      val leastSigBits = (Variant << 62) | randB

      val uuid = new UUID(mostSigBits, leastSigBits)
      unsafeFrom(uuid)
    }
  }

  private[types] trait UuidV7Instances extends UuidV7Instance2 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    given derivedUuidV7Eq[F[*]: CatsEq, G[*]: CatsEq](using eqActual: G[UUID]): F[UuidV7] = {
      internalDef.contraCoercible[cats.Eq, UuidV7, UUID, cats.Contravariant](eqActual.asInstanceOf[cats.Eq[UUID]])
    }.asInstanceOf[F[UuidV7]] // scalafix:ok DisableSyntax.asInstanceOf
  }

  private[types] trait UuidV7Instance2 extends UuidV7Instance1 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    given derivedUuidV7Hash[F[*]: CatsHash, G[*]: CatsHash](using hashActual: G[UUID]): F[UuidV7] = {
      internalDef.contraCoercible[cats.Hash, UuidV7, UUID, cats.Contravariant](hashActual.asInstanceOf[cats.Hash[UUID]])
    }.asInstanceOf[F[UuidV7]] // scalafix:ok DisableSyntax.asInstanceOf
  }

  private[types] trait UuidV7Instance1 extends OrphanCats, OrphanCatsKernel {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    given derivedUuidV7Show[F[*]: CatsShow, G[*]: CatsShow](using showActual: G[UUID]): F[UuidV7] = {
      internalDef.contraCoercible[cats.Show, UuidV7, UUID, cats.Contravariant](showActual.asInstanceOf[cats.Show[UUID]])
    }.asInstanceOf[F[UuidV7]] // scalafix:ok DisableSyntax.asInstanceOf
  }

  @SuppressWarnings(Array("org.wartremover.warts.Equals"))
  def isValidNotAllWhitespaceNonEmptyString(stringExpr: Expr[String])(using Quotes): Expr[Boolean] = {
    import quotes.reflect.*
    stringExpr.asTerm match {
      case Inlined(_, _, Literal(StringConstant(str))) =>
        Expr(NonBlankString.predicate(str))

      case _ =>
        report.error(
          "The argument passed to NotAllWhitespaceNonEmptyString.apply must be a string literal.",
          stringExpr,
        )
        Expr(false)
    }
  }

  val UnexpectedLiteralErrorMessage: String =
    """Uri must be a string literal.
      |If it's unknown in compile-time, use `Uuid.from` or `Uuid.unsafeFrom` instead.
      |(unsafeFrom is not recommended)""".stripMargin

  def isValidateUuid(uuidExpr: Expr[String])(using Quotes): Expr[Boolean] = {
    import quotes.reflect.*
    uuidExpr.asTerm match {
      case Inlined(_, _, Literal(StringConstant(uuidStr))) =>
        try {
          java.util.UUID.fromString(uuidStr)
          Expr(true)
        } catch {
          case _: Throwable => Expr(false)
        }
      case _ =>
        report.error(
          UnexpectedLiteralErrorMessage,
          uuidExpr,
        )
        Expr(false)
    }
  }

}
