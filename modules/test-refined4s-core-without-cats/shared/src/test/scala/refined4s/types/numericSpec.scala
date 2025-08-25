package refined4s.types

import hedgehog.*
import hedgehog.runner.*
import refined4s.ExpectedErrorMessages

/** @author Kevin Lee
  * @since 2025-08-24
  */
object numericSpec extends Properties {
  override def tests: List[Test] =
    negIntSpec.tests ++ nonNegIntSpec.tests ++ posIntSpec.tests ++ nonPosIntSpec.tests ++
      negLongSpec.tests ++ nonNegLongSpec.tests ++ posLongSpec.tests ++ nonPosLongSpec.tests ++
      negShortSpec.tests ++ nonNegShortSpec.tests ++ posShortSpec.tests ++ nonPosShortSpec.tests ++
      negByteSpec.tests ++ nonNegByteSpec.tests ++ posByteSpec.tests ++ nonPosByteSpec.tests ++
      negFloatSpec.tests ++ nonNegFloatSpec.tests ++ posFloatSpec.tests ++ nonPosFloatSpec.tests ++
      negDoubleSpec.tests ++ nonNegDoubleSpec.tests ++ posDoubleSpec.tests ++ nonPosDoubleSpec.tests ++
      negBigIntSpec.tests ++ nonNegBigIntSpec.tests ++ posBigIntSpec.tests ++ nonPosBigIntSpec.tests ++
      negBigDecimalSpec.tests ++ nonNegBigDecimalSpec.tests ++ posBigDecimalSpec.tests ++ nonPosBigDecimalSpec.tests

  object negIntSpec {
    def tests: List[Test] = List(
      example("test Eq[NegInt]", testEq),
      example("test Show[NegInt]", testShow),
    )

    def testEq: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingEq

      val actual = typeCheckErrors(
        """
         val _ = refined4s.types.numeric.NegInt.derivedNegIntEq
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
         val _ = refined4s.types.numeric.NegInt.derivedNegIntShow
      """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }
  }

  object nonNegIntSpec {
    def tests: List[Test] = List(
      example("test Eq[NonNegInt]", testEq),
      example("test Show[NonNegInt]", testShow),
    )

    def testEq: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingEq

      val actual = typeCheckErrors(
        """
         val _ = refined4s.types.numeric.NonNegInt.derivedNonNegIntEq
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
         val _ = refined4s.types.numeric.NonNegInt.derivedNonNegIntShow
      """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }
  }

  object posIntSpec {
    def tests: List[Test] = List(
      example("test Eq[PosInt]", testEq),
      example("test Show[PosInt]", testShow),
    )

    def testEq: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingEq

      val actual = typeCheckErrors(
        """
         val _ = refined4s.types.numeric.PosInt.derivedPosIntEq
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
         val _ = refined4s.types.numeric.PosInt.derivedPosIntShow
      """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }
  }

  object nonPosIntSpec {
    def tests: List[Test] = List(
      example("test Eq[NonPosInt]", testEq),
      example("test Show[NonPosInt]", testShow),
    )

    def testEq: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingEq

      val actual = typeCheckErrors(
        """
         val _ = refined4s.types.numeric.NonPosInt.derivedNonPosIntEq
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
         val _ = refined4s.types.numeric.NonPosInt.derivedNonPosIntShow
      """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }
  }

  object negLongSpec {
    def tests: List[Test] = List(
      example("test Eq[NegLong]", testEq),
      example("test Show[NegLong]", testShow),
    )

    def testEq: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingEq

      val actual = typeCheckErrors(
        """
         val _ = refined4s.types.numeric.NegLong.derivedNegLongEq
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
         val _ = refined4s.types.numeric.NegLong.derivedNegLongShow
      """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }
  }

  object nonNegLongSpec {
    def tests: List[Test] = List(
      example("test Eq[NonNegLong]", testEq),
      example("test Show[NonNegLong]", testShow),
    )

    def testEq: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingEq

      val actual = typeCheckErrors(
        """
         val _ = refined4s.types.numeric.NonNegLong.derivedNonNegLongEq
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
         val _ = refined4s.types.numeric.NonNegLong.derivedNonNegLongShow
      """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }
  }

  object posLongSpec {
    def tests: List[Test] = List(
      example("test Eq[PosLong]", testEq),
      example("test Show[PosLong]", testShow),
    )

    def testEq: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingEq

      val actual = typeCheckErrors(
        """
         val _ = refined4s.types.numeric.PosLong.derivedPosLongEq
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
         val _ = refined4s.types.numeric.PosLong.derivedPosLongShow
      """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }
  }

  object nonPosLongSpec {
    def tests: List[Test] = List(
      example("test Eq[NonPosLong]", testEq),
      example("test Show[NonPosLong]", testShow),
    )

    def testEq: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingEq

      val actual = typeCheckErrors(
        """
         val _ = refined4s.types.numeric.NonPosLong.derivedNonPosLongEq
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
         val _ = refined4s.types.numeric.NonPosLong.derivedNonPosLongShow
      """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }
  }

  object negShortSpec {
    def tests: List[Test] = List(
      example("test Eq[NegShort]", testEq),
      example("test Show[NegShort]", testShow),
    )

    def testEq: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingEq

      val actual = typeCheckErrors(
        """
         val _ = refined4s.types.numeric.NegShort.derivedNegShortEq
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
         val _ = refined4s.types.numeric.NegShort.derivedNegShortShow
      """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }
  }

  object nonNegShortSpec {
    def tests: List[Test] = List(
      example("test Eq[NonNegShort]", testEq),
      example("test Show[NonNegShort]", testShow),
    )

    def testEq: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingEq

      val actual = typeCheckErrors(
        """
         val _ = refined4s.types.numeric.NonNegShort.derivedNonNegShortEq
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
         val _ = refined4s.types.numeric.NonNegShort.derivedNonNegShortShow
      """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }
  }

  object posShortSpec {
    def tests: List[Test] = List(
      example("test Eq[PosShort]", testEq),
      example("test Show[PosShort]", testShow),
    )

    def testEq: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingEq

      val actual = typeCheckErrors(
        """
         val _ = refined4s.types.numeric.PosShort.derivedPosShortEq
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
         val _ = refined4s.types.numeric.PosShort.derivedPosShortShow
      """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }
  }

  object nonPosShortSpec {
    def tests: List[Test] = List(
      example("test Eq[NonPosShort]", testEq),
      example("test Show[NonPosShort]", testShow),
    )

    def testEq: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingEq

      val actual = typeCheckErrors(
        """
         val _ = refined4s.types.numeric.NonPosShort.derivedNonPosShortEq
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
         val _ = refined4s.types.numeric.NonPosShort.derivedNonPosShortShow
      """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }
  }

  object negByteSpec {
    def tests: List[Test] = List(
      example("test Eq[NegByte]", testEq),
      example("test Show[NegByte]", testShow),
    )

    def testEq: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingEq

      val actual = typeCheckErrors(
        """
         val _ = refined4s.types.numeric.NegByte.derivedNegByteEq
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
         val _ = refined4s.types.numeric.NegByte.derivedNegByteShow
      """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }
  }

  object nonNegByteSpec {
    def tests: List[Test] = List(
      example("test Eq[NonNegByte]", testEq),
      example("test Show[NonNegByte]", testShow),
    )

    def testEq: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingEq

      val actual = typeCheckErrors(
        """
         val _ = refined4s.types.numeric.NonNegByte.derivedNonNegByteEq
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
         val _ = refined4s.types.numeric.NonNegByte.derivedNonNegByteShow
      """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }
  }

  object posByteSpec {
    def tests: List[Test] = List(
      example("test Eq[PosByte]", testEq),
      example("test Show[PosByte]", testShow),
    )

    def testEq: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingEq

      val actual = typeCheckErrors(
        """
         val _ = refined4s.types.numeric.PosByte.derivedPosByteEq
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
         val _ = refined4s.types.numeric.PosByte.derivedPosByteShow
      """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }
  }

  object nonPosByteSpec {
    def tests: List[Test] = List(
      example("test Eq[NonPosByte]", testEq),
      example("test Show[NonPosByte]", testShow),
    )

    def testEq: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingEq

      val actual = typeCheckErrors(
        """
         val _ = refined4s.types.numeric.NonPosByte.derivedNonPosByteEq
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
         val _ = refined4s.types.numeric.NonPosByte.derivedNonPosByteShow
      """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }
  }

  object negFloatSpec {
    def tests: List[Test] = List(
      example("test Eq[NegFloat]", testEq),
      example("test Show[NegFloat]", testShow),
    )

    def testEq: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingEq

      val actual = typeCheckErrors(
        """
         val _ = refined4s.types.numeric.NegFloat.derivedNegFloatEq
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
         val _ = refined4s.types.numeric.NegFloat.derivedNegFloatShow
      """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }
  }

  object nonNegFloatSpec {
    def tests: List[Test] = List(
      example("test Eq[NonNegFloat]", testEq),
      example("test Show[NonNegFloat]", testShow),
    )

    def testEq: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingEq

      val actual = typeCheckErrors(
        """
         val _ = refined4s.types.numeric.NonNegFloat.derivedNonNegFloatEq
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
         val _ = refined4s.types.numeric.NonNegFloat.derivedNonNegFloatShow
      """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }
  }

  object posFloatSpec {
    def tests: List[Test] = List(
      example("test Eq[PosFloat]", testEq),
      example("test Show[PosFloat]", testShow),
    )

    def testEq: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingEq

      val actual = typeCheckErrors(
        """
         val _ = refined4s.types.numeric.PosFloat.derivedPosFloatEq
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
         val _ = refined4s.types.numeric.PosFloat.derivedPosFloatShow
      """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }
  }

  object nonPosFloatSpec {
    def tests: List[Test] = List(
      example("test Eq[NonPosFloat]", testEq),
      example("test Show[NonPosFloat]", testShow),
    )

    def testEq: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingEq

      val actual = typeCheckErrors(
        """
         val _ = refined4s.types.numeric.NonPosFloat.derivedNonPosFloatEq
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
         val _ = refined4s.types.numeric.NonPosFloat.derivedNonPosFloatShow
      """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }
  }

  object negDoubleSpec {
    def tests: List[Test] = List(
      example("test Eq[NegDouble]", testEq),
      example("test Show[NegDouble]", testShow),
    )

    def testEq: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingEq

      val actual = typeCheckErrors(
        """
         val _ = refined4s.types.numeric.NegDouble.derivedNegDoubleEq
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
         val _ = refined4s.types.numeric.NegDouble.derivedNegDoubleShow
      """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }
  }

  object nonNegDoubleSpec {
    def tests: List[Test] = List(
      example("test Eq[NonNegDouble]", testEq),
      example("test Show[NonNegDouble]", testShow),
    )

    def testEq: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingEq

      val actual = typeCheckErrors(
        """
         val _ = refined4s.types.numeric.NonNegDouble.derivedNonNegDoubleEq
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
         val _ = refined4s.types.numeric.NonNegDouble.derivedNonNegDoubleShow
      """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }
  }

  object posDoubleSpec {
    def tests: List[Test] = List(
      example("test Eq[PosDouble]", testEq),
      example("test Show[PosDouble]", testShow),
    )

    def testEq: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingEq

      val actual = typeCheckErrors(
        """
         val _ = refined4s.types.numeric.PosDouble.derivedPosDoubleEq
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
         val _ = refined4s.types.numeric.PosDouble.derivedPosDoubleShow
      """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }
  }

  object nonPosDoubleSpec {
    def tests: List[Test] = List(
      example("test Eq[NonPosDouble]", testEq),
      example("test Show[NonPosDouble]", testShow),
    )

    def testEq: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingEq

      val actual = typeCheckErrors(
        """
         val _ = refined4s.types.numeric.NonPosDouble.derivedNonPosDoubleEq
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
         val _ = refined4s.types.numeric.NonPosDouble.derivedNonPosDoubleShow
      """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }
  }

  object negBigIntSpec {
    def tests: List[Test] = List(
      example("test Eq[NegBigInt]", testEq),
      example("test Show[NegBigInt]", testShow),
    )

    def testEq: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingEq

      val actual = typeCheckErrors(
        """
         val _ = refined4s.types.numeric.NegBigInt.derivedNegBigIntEq
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
         val _ = refined4s.types.numeric.NegBigInt.derivedNegBigIntShow
      """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }
  }

  object nonNegBigIntSpec {
    def tests: List[Test] = List(
      example("test Eq[NonNegBigInt]", testEq),
      example("test Show[NonNegBigInt]", testShow),
    )

    def testEq: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingEq

      val actual = typeCheckErrors(
        """
         val _ = refined4s.types.numeric.NonNegBigInt.derivedNonNegBigIntEq
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
         val _ = refined4s.types.numeric.NonNegBigInt.derivedNonNegBigIntShow
      """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }
  }

  object posBigIntSpec {
    def tests: List[Test] = List(
      example("test Eq[PosBigInt]", testEq),
      example("test Show[PosBigInt]", testShow),
    )

    def testEq: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingEq

      val actual = typeCheckErrors(
        """
         val _ = refined4s.types.numeric.PosBigInt.derivedPosBigIntEq
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
         val _ = refined4s.types.numeric.PosBigInt.derivedPosBigIntShow
      """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }
  }

  object nonPosBigIntSpec {
    def tests: List[Test] = List(
      example("test Eq[NonPosBigInt]", testEq),
      example("test Show[NonPosBigInt]", testShow),
    )

    def testEq: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingEq

      val actual = typeCheckErrors(
        """
         val _ = refined4s.types.numeric.NonPosBigInt.derivedNonPosBigIntEq
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
         val _ = refined4s.types.numeric.NonPosBigInt.derivedNonPosBigIntShow
      """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }
  }

  object negBigDecimalSpec {
    def tests: List[Test] = List(
      example("test Eq[NegBigDecimal]", testEq),
      example("test Show[NegBigDecimal]", testShow),
    )

    def testEq: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingEq

      val actual = typeCheckErrors(
        """
         val _ = refined4s.types.numeric.NegBigDecimal.derivedNegBigDecimalEq
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
         val _ = refined4s.types.numeric.NegBigDecimal.derivedNegBigDecimalShow
      """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }
  }

  object nonNegBigDecimalSpec {
    def tests: List[Test] = List(
      example("test Eq[NonNegBigDecimal]", testEq),
      example("test Show[NonNegBigDecimal]", testShow),
    )

    def testEq: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingEq

      val actual = typeCheckErrors(
        """
         val _ = refined4s.types.numeric.NonNegBigDecimal.derivedNonNegBigDecimalEq
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
         val _ = refined4s.types.numeric.NonNegBigDecimal.derivedNonNegBigDecimalShow
      """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }
  }

  object posBigDecimalSpec {
    def tests: List[Test] = List(
      example("test Eq[PosBigDecimal]", testEq),
      example("test Show[PosBigDecimal]", testShow),
    )

    def testEq: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingEq

      val actual = typeCheckErrors(
        """
         val _ = refined4s.types.numeric.PosBigDecimal.derivedPosBigDecimalEq
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
         val _ = refined4s.types.numeric.PosBigDecimal.derivedPosBigDecimalShow
      """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }
  }

  object nonPosBigDecimalSpec {
    def tests: List[Test] = List(
      example("test Eq[NonPosBigDecimal]", testEq),
      example("test Show[NonPosBigDecimal]", testShow),
    )

    def testEq: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingEq

      val actual = typeCheckErrors(
        """
         val _ = refined4s.types.numeric.NonPosBigDecimal.derivedNonPosBigDecimalEq
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
         val _ = refined4s.types.numeric.NonPosBigDecimal.derivedNonPosBigDecimalShow
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
