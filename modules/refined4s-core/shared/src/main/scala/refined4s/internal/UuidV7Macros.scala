package refined4s.internal

import scala.quoted.*

/** @author Kevin Lee
  * @since 2026-02-25
  */
object UuidV7Macros {

  val UnexpectedLiteralErrorMessage: String =
    """UuidV7(String) must be a String literal.
      |If it's unknown in compile-time, use `UuidV7.fromString(String)` or `UuidV7.unsafeFromString(String)` instead.
      |(unsafeFrom is not recommended)""".stripMargin

  val UnexpectedUuidWithLiteralErrorMessage: String =
    """UuidV7(java.util.UUID) must be `java.util.UUID.fromString(a String literal)`.
      |If it's unknown in compile-time, use `UuidV7.from(UUID)` or `UuidV7.unsafeFrom(UUID)` instead.
      |(unsafeFrom is not recommended)""".stripMargin

  def isValidateUuidV7(uuidExpr: Expr[String])(using Quotes): Expr[Boolean] = {
    import quotes.reflect.*
    uuidExpr.asTerm match {
      case Inlined(_, _, Literal(StringConstant(uuidStr))) =>
        try {
          val uuid          = java.util.UUID.fromString(uuidStr)
          val isValidUuidV7 = uuid.version() == 7 && uuid.variant() == 2
          if isValidUuidV7 then ()
          else
            report.error(
              s"""Invalid UuidV7 value: ${uuidExpr.show}
                 |  It is a valid UUID string, but it is not a valid UUID v7 value.
                 |    - Expected version=7 and variant=2
                 |    -   Actual version=${uuid.version()} and variant=${uuid.variant()}
                 |""".stripMargin
            )
          Expr(isValidUuidV7)
        } catch {
          case ex: Throwable =>
            report.error(
              "Invalid UuidV7 value: " + ex.getMessage,
              uuidExpr,
            )
            Expr(false)
        }
      case _ =>
        report.error(
          UnexpectedLiteralErrorMessage,
          uuidExpr,
        )
        Expr(false)
    }
  }

  def isValidateJavaUuidV7(uuidExpr: Expr[java.util.UUID], callerName: String)(using Quotes): Expr[Boolean] = {
    import quotes.reflect.*

    @SuppressWarnings(Array("org.wartremover.warts.Recursion"))
    def unwrapSelect(term: Term, targetStringValue: String): Boolean = term match {
      case Select(Ident(`targetStringValue`), _) =>
        true
      case Select(t, `targetStringValue`) =>
        true
      case Select(t, _) =>
        unwrapSelect(t, targetStringValue)
      case _ =>
        false
    }

    @SuppressWarnings(Array("org.wartremover.warts.Recursion", "org.wartremover.warts.Equals"))
    def unwrapStringLiteral(term: Term): Term = term match {
      case Inlined(_, _, t) =>
//        println(s"Inlined: $t")
        unwrapStringLiteral(t)
      case Block(_, t) =>
//        println(s"Block: $t")
        unwrapStringLiteral(t)
      case Apply(sTerm @ Select(t, "fromString"), List(literal)) =>
        if unwrapSelect(sTerm, "UUID") then {
//          println(s">> ✅ Apply(Select): $sTerm, literal=$literal")
          unwrapStringLiteral(literal)
        } else {
//          println(s">> ❌ Apply(Select): $sTerm, literal=$literal")
          unwrapStringLiteral(sTerm)
        }
      case Apply(t, _) =>
//        println(s">> Apply: $t")
        unwrapStringLiteral(t)
      case TypeApply(t, _) =>
//        println(s"TypeApply: $t")
        unwrapStringLiteral(t)
      case Select(t, _) =>
//        println(s"Select: $t")
        unwrapStringLiteral(t)
      case Typed(t, _) =>
//        println(s"Typed: $t")
        unwrapStringLiteral(t)
      case _ => term
    }

    unwrapStringLiteral(uuidExpr.asTerm) match {
//      case Inlined(_, _, Apply(Select(_, "fromString"), List(Literal(StringConstant(uuidStr))))) =>
      case Literal(StringConstant(uuidStr)) =>
        try {
          val uuid          = java.util.UUID.fromString(uuidStr)
          val isValidUuidV7 = uuid.version() == 7 && uuid.variant() == 2
          if isValidUuidV7 then ()
          else
            report.error(
              s"""Invalid UuidV7 value: ${uuidExpr.show}
                 |  It is a valid UUID string, but it is not a valid UUID v7 value.
                 |    - Expected version=7 and variant=2
                 |    -   Actual version=${uuid.version()} and variant=${uuid.variant()}
                 |""".stripMargin
            )
          Expr(isValidUuidV7)
        } catch {
          case ex: Throwable =>
            report.error(
              "Invalid UuidV7 value: " + ex.getMessage,
              uuidExpr,
            )
            Expr(false)
        }
      case _ =>
        report.error(
          UnexpectedUuidWithLiteralErrorMessage +
            s"""
               |
               |If you did something like this
               |  ```
               |  val uuid = UUID.fromString("018e6c7a-36db-79b8-a15d-852438885cb1")
               |  $callerName(uuid)
               |  ```
               |  NOTE: "018e6c7a-36db-79b8-a15d-852438885cb1" is just an example value, not yours.
               |
               |please do this instead.
               |  ```
               |  $callerName(UUID.fromString("018e6c7a-36db-79b8-a15d-852438885cb1"))
               |  ```
               |
               |NOTE: $callerName(java.util.UUID) only works with String literal.
               |      So the following will not work.
               |      ```
               |      val uuid = "018e6c7a-36db-79b8-a15d-852438885cb1"
               |      $callerName(UUID.fromString(uuid))
               |      ```
               |""".stripMargin,
          uuidExpr,
        )
        Expr(false)
    }
  }

}
