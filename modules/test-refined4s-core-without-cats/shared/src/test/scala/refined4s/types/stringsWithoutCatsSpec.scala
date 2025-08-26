package refined4s.types

import hedgehog.*
import hedgehog.runner.*
import refined4s.*

/** @author Kevin Lee
  * @since 2023-04-25
  */
object stringsWithoutCatsSpec extends Properties {
  override def tests: List[Test] = NonEmptyStringSpec.tests ++ NonBlankStringSpec.tests ++ UuidSpec.tests

  object NonEmptyStringSpec {

    def tests: List[Test] = List(
      example("test Eq[NonEmptyString]", testEq),
      example("test Show[NonEmptyString]", testShow),
    )

    def testEq: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expectedMessage = ExpectedErrorMessages.missingEq

      val actual = typeCheckErrors(
        """
          val _ = refined4s.types.strings.NonEmptyString.derivedNonEmptyStringEq
        """
      )

      val actualErrorMessage = actual.map(_.message).mkString
      (actualErrorMessage ==== expectedMessage)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }

    def testShow: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expectedMessage = ExpectedErrorMessages.missingShow

      val actual = typeCheckErrors(
        """
          val _ = refined4s.types.strings.NonEmptyString.derivedNonEmptyStringShow
        """
      )

      val actualErrorMessage = actual.map(_.message).mkString
      (actualErrorMessage ==== expectedMessage)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }

  }

  object NonBlankStringSpec {

    def tests: List[Test] = List(
      example("test Eq[NonBlankString]", testEq),
      example("test Show[NonBlankString]", testShow),
    )

    def testEq: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expectedMessage = ExpectedErrorMessages.missingEq

      val actual = typeCheckErrors(
        """
          val _ = refined4s.types.strings.NonBlankString.derivedNonBlankStringEq
        """
      )

      val actualErrorMessage = actual.map(_.message).mkString
      (actualErrorMessage ==== expectedMessage)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }

    def testShow: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expectedMessage = ExpectedErrorMessages.missingShow

      val actual = typeCheckErrors(
        """
          val _ = refined4s.types.strings.NonBlankString.derivedNonBlankStringShow
        """
      )

      val actualErrorMessage = actual.map(_.message).mkString
      (actualErrorMessage ==== expectedMessage)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }

  }

  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  object UuidSpec {

    def tests: List[Test] = List(
      example("test Eq[Uuid]", testEq),
      example("test Show[Uuid]", testShow),
    )

    def testEq: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expectedMessage = ExpectedErrorMessages.missingEq

      val actual = typeCheckErrors(
        """
          val _ = refined4s.types.strings.Uuid.derivedUuidEq
        """
      )

      val actualErrorMessage = actual.map(_.message).mkString
      (actualErrorMessage ==== expectedMessage)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }

    def testShow: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expectedMessage = ExpectedErrorMessages.missingShow

      val actual = typeCheckErrors(
        """
          val _ = refined4s.types.strings.Uuid.derivedUuidShow
        """
      )

      val actualErrorMessage = actual.map(_.message).mkString
      (actualErrorMessage ==== expectedMessage)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }

  }
}
