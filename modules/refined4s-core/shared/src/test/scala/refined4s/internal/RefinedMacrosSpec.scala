package refined4s.internal

import hedgehog.*
import hedgehog.runner.*

/** @author Kevin Lee
  * @since 2026-02-06
  */
object RefinedMacrosSpec extends Properties {
  override def tests: List[Test] =
    internalErrorTests ++
      exprToStringTests

  inline def callExprToString[A](inline a: A): String =
    ${ RefinedMacros.exprToStringImpl('a) }

  private def internalErrorTests: List[Test] =
    List(
      example(
        "internalError should fail compilation with the expected error message",
        testInternalError,
      )
    )

  private def exprToStringTests: List[Test] =
    List(
      example(
        "callExprToString(String) should return the String value",
        callExprToString("hello") ==== "hello",
      ),
      example(
        "callExprToString(Long) should return the Long value String + L",
        callExprToString(123L) ==== "123L",
      ),
      example(
        "callExprToString(Int) should return the Int value String",
        callExprToString(123) ==== "123",
      ),
      example(
        "callExprToString(Float) should return the Float value String + f",
        callExprToString(123.45f) ==== "123.45f",
      ),
      example(
        "callExprToString(Double) should return the Double value String + d",
        callExprToString(123.45d) ==== "123.45d",
      ),
      example(
        "callExprToString(Short) should return the Short value String",
        callExprToString(123: Short) ==== "123",
      ),
      example(
        "callExprToString(Byte) should return the Byte value String",
        callExprToString(123: Byte) ==== "123",
      ),
      example(
        "callExprToString(Char) should return the Char value String",
        callExprToString('a') ==== "a",
      ),
      example(
        "callExprToString(Boolean) should return the Boolean value String",
        callExprToString(true) ==== "true",
      ),
      example(
        "callExprToString(Unit) should return ()",
        callExprToString(()) ==== "()",
      ),
      example(
        "callExprToString(Null) should return null", {
          @SuppressWarnings(Array("org.wartremover.warts.Null"))
          val result = callExprToString(null) ==== "null" // scalafix:ok DisableSyntax.null
          result
        },
      ),
      example(
        "callExprToString(BigInt(1)) should return ()",
        testExprToStringWithUnsupported,
      ),
    )

  def testInternalError: Result = {
    import scala.compiletime.testing.typeCheckErrors

    val expected = """Invalid value: ["Something wrong"]. It must be expected message."""

    val errors = typeCheckErrors(
      """
        refined4s.internal.RefinedMacros.internalError("Something wrong", "expected message")
      """
    )

    val actual = errors.map(_.message).mkString

    actual ==== expected
  }

  def testExprToStringWithUnsupported: Result = {
    import scala.compiletime.testing.typeCheckErrors

    val expected = """Expected a constant string for error message but got: scala.BigInt.apply(1)
                     |Term: Inlined(EmptyTree,List(),Apply(Select(Ident(BigInt),apply),List(Literal(Constant(1)))))
                     |Unwrapped: Apply(Select(Ident(BigInt),apply),List(Literal(Constant(1))))""".stripMargin

    val errors = typeCheckErrors(
      """
        callExprToString(BigInt(1))
      """
    )

    val actual = errors.map(_.message).mkString
    actual ==== expected
  }

}
