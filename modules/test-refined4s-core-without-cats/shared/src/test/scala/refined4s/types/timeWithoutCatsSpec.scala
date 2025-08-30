package refined4s.types

import hedgehog.*
import hedgehog.runner.*
import refined4s.ExpectedErrorMessages

/** @author Kevin Lee
  * @since 2025-08-24
  */
object timeWithoutCatsSpec extends Properties {
  override def tests: List[Test] =
    monthSpec.tests ++ daySpec.tests ++ hourSpec.tests ++ minuteSpec.tests ++ secondSpec.tests ++ millisSpec.tests

  object monthSpec {
    def tests: List[Test] = List(
      example("test   Eq[Month]", testEq),
      example("test Hash[Month]", testHash),
      example("test Show[Month]", testShow),
    )

    def testEq: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingEq

      val actual = typeCheckErrors(
        """
         val _ = refined4s.types.time.Month.derivedMonthEq
      """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }

    def testHash: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingHash

      val actual = typeCheckErrors(
        """
         val _ = refined4s.types.time.Month.derivedMonthHash
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
         val _ = refined4s.types.time.Month.derivedMonthShow
      """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }
  }

  object daySpec {
    def tests: List[Test] = List(
      example("test   Eq[Day]", testEq),
      example("test Hash[Day]", testHash),
      example("test Show[Day]", testShow),
    )

    def testEq: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingEq

      val actual = typeCheckErrors(
        """
         val _ = refined4s.types.time.Day.derivedDayEq
      """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }

    def testHash: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingHash

      val actual = typeCheckErrors(
        """
         val _ = refined4s.types.time.Day.derivedDayHash
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
         val _ = refined4s.types.time.Day.derivedDayShow
      """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }
  }

  object hourSpec {
    def tests: List[Test] = List(
      example("test   Eq[Hour]", testEq),
      example("test Hash[Hour]", testHash),
      example("test Show[Hour]", testShow),
    )

    def testEq: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingEq

      val actual = typeCheckErrors(
        """
         val _ = refined4s.types.time.Hour.derivedHourEq
      """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }

    def testHash: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingHash

      val actual = typeCheckErrors(
        """
         val _ = refined4s.types.time.Hour.derivedHourHash
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
         val _ = refined4s.types.time.Hour.derivedHourShow
      """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }
  }

  object minuteSpec {
    def tests: List[Test] = List(
      example("test   Eq[Minute]", testEq),
      example("test Hash[Minute]", testHash),
      example("test Show[Minute]", testShow),
    )

    def testEq: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingEq

      val actual = typeCheckErrors(
        """
         val _ = refined4s.types.time.Minute.derivedMinuteEq
      """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }

    def testHash: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingHash

      val actual = typeCheckErrors(
        """
         val _ = refined4s.types.time.Minute.derivedMinuteHash
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
         val _ = refined4s.types.time.Minute.derivedMinuteShow
      """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }
  }

  object secondSpec {
    def tests: List[Test] = List(
      example("test   Eq[Second]", testEq),
      example("test Hash[Second]", testHash),
      example("test Show[Second]", testShow),
    )

    def testEq: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingEq

      val actual = typeCheckErrors(
        """
         val _ = refined4s.types.time.Second.derivedSecondEq
      """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }

    def testHash: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingHash

      val actual = typeCheckErrors(
        """
         val _ = refined4s.types.time.Second.derivedSecondHash
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
         val _ = refined4s.types.time.Second.derivedSecondShow
      """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }
  }

  object millisSpec {
    def tests: List[Test] = List(
      example("test   Eq[Millis]", testEq),
      example("test Hash[Millis]", testHash),
      example("test Show[Millis]", testShow),
    )

    def testEq: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingEq

      val actual = typeCheckErrors(
        """
         val _ = refined4s.types.time.Millis.derivedMillisEq
      """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }

    def testHash: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingHash

      val actual = typeCheckErrors(
        """
         val _ = refined4s.types.time.Millis.derivedMillisHash
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
         val _ = refined4s.types.time.Millis.derivedMillisShow
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
