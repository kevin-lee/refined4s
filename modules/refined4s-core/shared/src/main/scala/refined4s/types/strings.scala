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

  // scalafix:on

}
object strings extends OrphanCats, OrphanCatsKernel {

  type NonEmptyString = NonEmptyString.Type
  @SuppressWarnings(Array("org.wartremover.warts.Equals"))
  object NonEmptyString extends Refined[String], CanBeOrdered[String] {

    override inline def invalidReason(a: String): String =
      expectedMessage("a non-empty String")

    override inline def predicate(a: String): Boolean = a != ""

    extension (thisNes: NonEmptyString) {
      @targetName("plus")
      def ++(thatNes: NonEmptyString): NonEmptyString = NonEmptyString.unsafeFrom(thisNes.value + thatNes.value)
    }

    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    given derivedNonEmptyStringEq[F[*]: CatsEq, G[*]: CatsEq](using eqActual: G[String]): F[NonEmptyString] = {
      internalDef.contraCoercible(eqActual.asInstanceOf[cats.Eq[String]])
    }.asInstanceOf[F[NonEmptyString]] // scalafix:ok DisableSyntax.asInstanceOf

    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    given derivedNonEmptyStringShow[F[*]: CatsShow, G[*]: CatsShow](using showActual: G[String]): F[NonEmptyString] = {
      internalDef.contraCoercible(showActual.asInstanceOf[cats.Show[String]])
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
  object NonBlankString extends InlinedRefined[String], CanBeOrdered[String] {

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

    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNonBlankStringEq[F[*]: CatsEq, G[*]: CatsEq](using eqActual: G[String]): F[NonBlankString] = {
      internalDef.contraCoercible(eqActual.asInstanceOf[cats.Eq[String]])
    }.asInstanceOf[F[NonBlankString]] // scalafix:ok DisableSyntax.asInstanceOf

    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNonBlankStringShow[F[*]: CatsShow, G[*]: CatsShow](using showActual: G[String]): F[NonBlankString] = {
      internalDef.contraCoercible(showActual.asInstanceOf[cats.Show[String]])
    }.asInstanceOf[F[NonBlankString]] // scalafix:ok DisableSyntax.asInstanceOf

  }

  type Uuid = Uuid.Type
  object Uuid extends InlinedRefined[String], CanBeOrdered[String] {
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

    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedUuidEq[F[*]: CatsEq, G[*]: CatsEq](using eqActual: G[String]): F[Uuid] = {
      internalDef.contraCoercible(eqActual.asInstanceOf[cats.Eq[String]])
    }.asInstanceOf[F[Uuid]] // scalafix:ok DisableSyntax.asInstanceOf

    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedUuidShow[F[*]: CatsShow, G[*]: CatsShow](using showActual: G[String]): F[Uuid] = {
      internalDef.contraCoercible(showActual.asInstanceOf[cats.Show[String]])
    }.asInstanceOf[F[Uuid]] // scalafix:ok DisableSyntax.asInstanceOf

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
