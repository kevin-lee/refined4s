package refined4s.types

import hedgehog.*
import hedgehog.runner.*
import refined4s.ExpectedErrorMessages

/** @author Kevin Lee
  * @since 2025-08-24
  */
object networkWithoutCatsSpec extends Properties {
  override def tests: List[Test] =
    uriSpec.tests ++ urlSpec.tests ++ portNumberSpec.tests ++ systemPortNumberSpec.tests ++ nonSystemPortNumberSpec.tests ++ userPortNumberSpec.tests

  object uriSpec {
    def tests: List[Test] = List(
      example("test Eq[Uri]", testEq),
      example("test Show[Uri]", testShow),
    )

    def testEq: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingEq

      val actual = typeCheckErrors(
        """
         val _ = refined4s.types.network.Uri.derivedUriEq
      """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }

    def testShow: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingShow

      val actual = typeCheckErrors(
        """
         val _ = refined4s.types.network.Uri.derivedUriShow
      """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }
  }

  object urlSpec {
    def tests: List[Test] = List(
      example("test Eq[Url]", testEq),
      example("test Show[Url]", testShow),
    )

    def testEq: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingEq

      val actual = typeCheckErrors(
        """
         val _ = refined4s.types.network.Url.derivedUrlEq
      """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }

    def testShow: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingShow

      val actual = typeCheckErrors(
        """
         val _ = refined4s.types.network.Url.derivedUrlShow
      """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }
  }

  object portNumberSpec {
    def tests: List[Test] = List(
      example("test Eq[PortNumber]", testEq),
      example("test Show[PortNumber]", testShow),
    )

    def testEq: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingEq

      val actual = typeCheckErrors(
        """
         val _ = refined4s.types.network.PortNumber.derivedPortNumberEq
      """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }

    def testShow: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingShow

      val actual = typeCheckErrors(
        """
         val _ = refined4s.types.network.PortNumber.derivedPortNumberShow
      """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }
  }

  object systemPortNumberSpec {
    def tests: List[Test] = List(
      example("test Eq[SystemPortNumber]", testEq),
      example("test Show[SystemPortNumber]", testShow),
    )

    def testEq: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingEq

      val actual = typeCheckErrors(
        """
         val _ = refined4s.types.network.SystemPortNumber.derivedSystemPortNumberEq
      """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }

    def testShow: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingShow

      val actual = typeCheckErrors(
        """
         val _ = refined4s.types.network.SystemPortNumber.derivedSystemPortNumberShow
      """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }
  }

  object nonSystemPortNumberSpec {
    def tests: List[Test] = List(
      example("test Eq[NonSystemPortNumber]", testEq),
      example("test Show[NonSystemPortNumber]", testShow),
    )

    def testEq: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingEq

      val actual = typeCheckErrors(
        """
         val _ = refined4s.types.network.NonSystemPortNumber.derivedNonSystemPortNumberEq
      """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }

    def testShow: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingShow

      val actual = typeCheckErrors(
        """
         val _ = refined4s.types.network.NonSystemPortNumber.derivedNonSystemPortNumberShow
      """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }
  }

  object userPortNumberSpec {
    def tests: List[Test] = List(
      example("test Eq[UserPortNumber]", testEq),
      example("test Show[UserPortNumber]", testShow),
    )

    def testEq: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingEq

      val actual = typeCheckErrors(
        """
         val _ = refined4s.types.network.UserPortNumber.derivedUserPortNumberEq
      """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }

    def testShow: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingShow

      val actual = typeCheckErrors(
        """
         val _ = refined4s.types.network.UserPortNumber.derivedUserPortNumberShow
      """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }
  }

}
