package refined4s.internal

import hedgehog.*
import hedgehog.runner.*

/** @author Kevin Lee
  * @since 2026-02-25
  */
object UuidV7MacrosSpec extends Properties {
  override def tests: List[Test] =
    List(
      example("isValidateUuidV7 should return true for valid UUID v7", testIsValidateUuidV7Valid),
      example("isValidateUuidV7 should return false for valid UUID v4 (invalid v7)", testIsValidateUuidV7ValidV4),
      example("isValidateUuidV7 should return false for invalid UUID String", testIsValidateUuidV7WithInvalidString),
      example("test isValidateUuidV7 with invalid UUID string", testIsValidateUuidV7InvalidString),
      example("test isValidateUuidV7 with valid UUID v4 string (invalid v7)", testIsValidateUuidV7ValidUuidV4String),
      example("test isValidateUuidV7 with non-literal string", testIsValidateUuidV7NonLiteral),
      example("isValidateJavaUuidV7 should return true for valid UUID v7", testIsValidateJavaUuidV7Valid),
      example("isValidateJavaUuidV7 should return false for valid java.util.UUID v4 (invalid v7)", testIsValidateJavaUuidV7ValidV4),
      example("test isValidateJavaUuidV7 with valid java.util.UUID v4 (invalid v7)", testIsValidateJavaUuidV7ValidUuidV4),
      example("test isValidateJavaUuidV7 with non-literal UUID", testIsValidateJavaUuidV7NonLiteral),
    )

  inline def callIsValidateUuidV7(inline a: String): Boolean =
    ${ UuidV7Macros.isValidateUuidV7('a) }

  inline def callIsValidateJavaUuidV7(inline a: java.util.UUID): Boolean =
    ${ UuidV7Macros.isValidateJavaUuidV7('a, "callIsValidateJavaUuidV7") }

  def testIsValidateUuidV7Valid: Result = {
    import scala.compiletime.testing.typeChecks
    val result = typeChecks(
      """
        callIsValidateUuidV7("018e6c7a-36db-79b8-a15d-852438885cb1")
      """
    )
    result ==== true
  }

  def testIsValidateUuidV7ValidV4: Result = {
    import scala.compiletime.testing.typeChecks
    val result = typeChecks(
      """ 
        callIsValidateUuidV7("f47ac10b-58cc-4372-a567-0e02b2c3d479")
      """
    )
    result ==== false
  }

  def testIsValidateUuidV7WithInvalidString: Result = {
    import scala.compiletime.testing.typeChecks
    val result = typeChecks(
      """
        callIsValidateUuidV7("invalid-uuid")
      """
    )
    result ==== false
  }

  def testIsValidateUuidV7InvalidString: Result = {
    import scala.compiletime.testing.typeCheckErrors

    val expected = "Invalid UuidV7 value: Invalid UUID string: invalid-uuid"

    val errors = typeCheckErrors(
      """
        callIsValidateUuidV7("invalid-uuid")
      """
    )

    val actual = errors.map(_.message).mkString
    actual ==== expected
  }

  def testIsValidateUuidV7ValidUuidV4String: Result = {
    import scala.compiletime.testing.typeCheckErrors

    val expected =
      """Invalid UuidV7 value: "f47ac10b-58cc-4372-a567-0e02b2c3d479"
        |  It is a valid UUID string, but it is not a valid UUID v7 value.
        |    - Expected version=7 and variant=2
        |    -   Actual version=4 and variant=2
        |""".stripMargin

    val errors = typeCheckErrors(
      """
        callIsValidateUuidV7("f47ac10b-58cc-4372-a567-0e02b2c3d479")
      """
    )

    val actual = errors.map(_.message).mkString
    actual ==== expected
  }

  def testIsValidateUuidV7NonLiteral: Result = {
    import scala.compiletime.testing.typeCheckErrors

    val expected = UuidV7Macros.UnexpectedLiteralErrorMessage

    val errors = typeCheckErrors(
      """
        val id = "018e6c7a-36db-79b8-a15d-852438885cb1"
        callIsValidateUuidV7(id)
      """
    )

    val actual = errors.map(_.message).mkString
    actual ==== expected
  }

  def testIsValidateJavaUuidV7Valid: Result = {
    import scala.compiletime.testing.typeChecks
    val result1 = typeChecks(
      """
        callIsValidateJavaUuidV7(java.util.UUID.fromString("018e6c7a-36db-79b8-a15d-852438885cb1"))
      """
    )

    val result2 = typeChecks(
      """
        import java.util.UUID

        callIsValidateJavaUuidV7(UUID.fromString("018e6c7a-36db-79b8-a15d-852438885cb1"))
      """
    )
    Result.all(
      List(
        result1 ==== true,
        result2 ==== true,
      )
    )
  }

  def testIsValidateJavaUuidV7ValidV4: Result = {
    import scala.compiletime.testing.typeChecks
    val result1 = typeChecks(
      """
        callIsValidateJavaUuidV7(java.util.UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d479"))
      """
    )
    val result2 = typeChecks(
      """
        import java.util.UUID

        callIsValidateJavaUuidV7(UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d479"))
      """
    )

    Result.all(
      List(
        result1 ==== false,
        result2 ==== false,
      )
    )
  }

  def testIsValidateJavaUuidV7ValidUuidV4: Result = {
    import scala.compiletime.testing.typeCheckErrors

    val expected1 =
      """Invalid UuidV7 value: java.util.UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d479")
        |  It is a valid UUID string, but it is not a valid UUID v7 value.
        |    - Expected version=7 and variant=2
        |    -   Actual version=4 and variant=2
        |""".stripMargin

    val actual1 = typeCheckErrors(
      """
        callIsValidateJavaUuidV7(java.util.UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d479"))
      """
    ).map(_.message).mkString

    val expected2 =
      """Invalid UuidV7 value: java.util.UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d479")
        |  It is a valid UUID string, but it is not a valid UUID v7 value.
        |    - Expected version=7 and variant=2
        |    -   Actual version=4 and variant=2
        |""".stripMargin

    val actual2 = typeCheckErrors(
      """
        import java.util.UUID

        callIsValidateJavaUuidV7(UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d479"))
      """
    ).map(_.message).mkString

    Result.all(
      List(
        (actual1 ==== expected1).log("case 1"),
        (actual2 ==== expected2).log("case 2"),
      )
    )
  }

  def testIsValidateJavaUuidV7NonLiteral: Result = {
    import scala.compiletime.testing.typeCheckErrors

    val expected1 =
      s"""${UuidV7Macros.UnexpectedUuidWithLiteralErrorMessage}
         |
         |If you did something like this
         |  ```
         |  val uuid = UUID.fromString("018e6c7a-36db-79b8-a15d-852438885cb1")
         |  callIsValidateJavaUuidV7(uuid)
         |  ```
         |  NOTE: "018e6c7a-36db-79b8-a15d-852438885cb1" is just an example value, not yours.
         |
         |please do this instead.
         |  ```
         |  callIsValidateJavaUuidV7(UUID.fromString("018e6c7a-36db-79b8-a15d-852438885cb1"))
         |  ```
         |
         |NOTE: callIsValidateJavaUuidV7(java.util.UUID) only works with String literal.
         |      So the following will not work.
         |      ```
         |      val uuid = "018e6c7a-36db-79b8-a15d-852438885cb1"
         |      callIsValidateJavaUuidV7(UUID.fromString(uuid))
         |      ```
         |""".stripMargin

    val actual1 = typeCheckErrors(
      """
        val id = java.util.UUID.fromString("018e6c7a-36db-79b8-a15d-852438885cb1")
        callIsValidateJavaUuidV7(id)
      """
    ).map(_.message).mkString

    val expected2 =
      s"""${UuidV7Macros.UnexpectedUuidWithLiteralErrorMessage}
         |
         |If you did something like this
         |  ```
         |  val uuid = UUID.fromString("018e6c7a-36db-79b8-a15d-852438885cb1")
         |  callIsValidateJavaUuidV7(uuid)
         |  ```
         |  NOTE: "018e6c7a-36db-79b8-a15d-852438885cb1" is just an example value, not yours.
         |
         |please do this instead.
         |  ```
         |  callIsValidateJavaUuidV7(UUID.fromString("018e6c7a-36db-79b8-a15d-852438885cb1"))
         |  ```
         |
         |NOTE: callIsValidateJavaUuidV7(java.util.UUID) only works with String literal.
         |      So the following will not work.
         |      ```
         |      val uuid = "018e6c7a-36db-79b8-a15d-852438885cb1"
         |      callIsValidateJavaUuidV7(UUID.fromString(uuid))
         |      ```
         |""".stripMargin

    val actual2 = typeCheckErrors(
      """
            import java.util.UUID
    
            val uuid = UUID.fromString("018e6c7a-36db-79b8-a15d-852438885cb1")
    
            callIsValidateJavaUuidV7(uuid)
          """
    ).map(_.message).mkString

    Result.all(
      List(
        (actual1 ==== expected1).log("case 1"),
        (actual2 ==== expected2).log("case 2"),
      )
    )
  }

}
