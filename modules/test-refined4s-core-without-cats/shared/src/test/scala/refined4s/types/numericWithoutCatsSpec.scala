package refined4s.types

import hedgehog.*
import hedgehog.runner.*
import refined4s.ExpectedErrorMessages

/** @author Kevin Lee
  * @since 2025-08-24
  */
object numericWithoutCatsSpec extends Properties {
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
      example("test   Eq[NegInt]", testEq),
      example("test Hash[NegInt]", testHash),
      example("test Order[NegInt]", testOrder),
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

    def testHash: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingHash

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.NegInt.derivedNegIntHash
        """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }

    def testOrder: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingOrder

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.NegInt.derivedNegIntOrder
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
      example("test   Eq[NonNegInt]", testEq),
      example("test Hash[NonNegInt]", testHash),
      example("test Order[NonNegInt]", testOrder),
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

    def testHash: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingHash

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.NonNegInt.derivedNonNegIntHash
        """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }

    def testOrder: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingOrder

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.NonNegInt.derivedNonNegIntOrder
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
      example("test   Eq[PosInt]", testEq),
      example("test Hash[PosInt]", testHash),
      example("test Order[PosInt]", testOrder),
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

    def testHash: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingHash

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.PosInt.derivedPosIntHash
        """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }

    def testOrder: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingOrder

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.PosInt.derivedPosIntOrder
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
      example("test   Eq[NonPosInt]", testEq),
      example("test Hash[NonPosInt]", testHash),
      example("test Order[NonPosInt]", testOrder),
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

    def testHash: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingHash

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.NonPosInt.derivedNonPosIntHash
        """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }

    def testOrder: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingOrder

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.NonPosInt.derivedNonPosIntOrder
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
      example("test   Eq[NegLong]", testEq),
      example("test Hash[NegLong]", testHash),
      example("test Order[NegLong]", testOrder),
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

    def testHash: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingHash

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.NegLong.derivedNegLongHash
        """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }

    def testOrder: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingOrder

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.NegLong.derivedNegLongOrder
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
      example("test   Eq[NonNegLong]", testEq),
      example("test Hash[NonNegLong]", testHash),
      example("test Order[NonNegLong]", testOrder),
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

    def testHash: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingHash

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.NonNegLong.derivedNonNegLongHash
        """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }

    def testOrder: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingOrder

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.NonNegLong.derivedNonNegLongOrder
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
      example("test   Eq[PosLong]", testEq),
      example("test Hash[PosLong]", testHash),
      example("test Order[PosLong]", testOrder),
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

    def testHash: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingHash

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.PosLong.derivedPosLongHash
        """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }

    def testOrder: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingOrder

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.PosLong.derivedPosLongOrder
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
      example("test   Eq[NonPosLong]", testEq),
      example("test Hash[NonPosLong]", testHash),
      example("test Order[NonPosLong]", testOrder),
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

    def testHash: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingHash

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.NonPosLong.derivedNonPosLongHash
        """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }

    def testOrder: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingOrder

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.NonPosLong.derivedNonPosLongOrder
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
      example("test   Eq[NegShort]", testEq),
      example("test Hash[NegShort]", testHash),
      example("test Order[NegShort]", testOrder),
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

    def testHash: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingHash

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.NegShort.derivedNegShortHash
        """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }

    def testOrder: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingOrder

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.NegShort.derivedNegShortOrder
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
      example("test   Eq[NonNegShort]", testEq),
      example("test Hash[NonNegShort]", testHash),
      example("test Order[NonNegShort]", testOrder),
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

    def testHash: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingHash

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.NonNegShort.derivedNonNegShortHash
        """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }

    def testOrder: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingOrder

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.NonNegShort.derivedNonNegShortOrder
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
      example("test   Eq[PosShort]", testEq),
      example("test Hash[PosShort]", testHash),
      example("test Order[PosShort]", testOrder),
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

    def testHash: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingHash

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.PosShort.derivedPosShortHash
        """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }

    def testOrder: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingOrder

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.PosShort.derivedPosShortOrder
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
      example("test   Eq[NonPosShort]", testEq),
      example("test Hash[NonPosShort]", testHash),
      example("test Order[NonPosShort]", testOrder),
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

    def testHash: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingHash

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.NonPosShort.derivedNonPosShortHash
        """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }

    def testOrder: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingOrder

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.NonPosShort.derivedNonPosShortOrder
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
      example("test   Eq[NegByte]", testEq),
      example("test Hash[NegByte]", testHash),
      example("test Order[NegByte]", testOrder),
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

    def testHash: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingHash

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.NegByte.derivedNegByteHash
        """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }

    def testOrder: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingOrder

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.NegByte.derivedNegByteOrder
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
      example("test   Eq[NonNegByte]", testEq),
      example("test Hash[NonNegByte]", testHash),
      example("test Order[NonNegByte]", testOrder),
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

    def testHash: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingHash

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.NonNegByte.derivedNonNegByteHash
        """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }

    def testOrder: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingOrder

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.NonNegByte.derivedNonNegByteOrder
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
      example("test   Eq[PosByte]", testEq),
      example("test Hash[PosByte]", testHash),
      example("test Order[PosByte]", testOrder),
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

    def testHash: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingHash

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.PosByte.derivedPosByteHash
        """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }

    def testOrder: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingOrder

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.PosByte.derivedPosByteOrder
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
      example("test   Eq[NonPosByte]", testEq),
      example("test Hash[NonPosByte]", testHash),
      example("test Order[NonPosByte]", testOrder),
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

    def testHash: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingHash

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.NonPosByte.derivedNonPosByteHash
        """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }

    def testOrder: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingOrder

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.NonPosByte.derivedNonPosByteOrder
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
      example("test   Eq[NegFloat]", testEq),
      example("test Hash[NegFloat]", testHash),
      example("test Order[NegFloat]", testOrder),
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

    def testHash: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingHash

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.NegFloat.derivedNegFloatHash
        """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }

    def testOrder: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingOrder

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.NegFloat.derivedNegFloatOrder
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
      example("test   Eq[NonNegFloat]", testEq),
      example("test Hash[NonNegFloat]", testHash),
      example("test Order[NonNegFloat]", testOrder),
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

    def testHash: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingHash

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.NonNegFloat.derivedNonNegFloatHash
        """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }

    def testOrder: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingOrder

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.NonNegFloat.derivedNonNegFloatOrder
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
      example("test   Eq[PosFloat]", testEq),
      example("test Hash[PosFloat]", testHash),
      example("test Order[PosFloat]", testOrder),
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

    def testHash: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingHash

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.PosFloat.derivedPosFloatHash
        """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }

    def testOrder: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingOrder

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.PosFloat.derivedPosFloatOrder
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
      example("test   Eq[NonPosFloat]", testEq),
      example("test Hash[NonPosFloat]", testHash),
      example("test Order[NonPosFloat]", testOrder),
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

    def testHash: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingHash

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.NonPosFloat.derivedNonPosFloatHash
        """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }

    def testOrder: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingOrder

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.NonPosFloat.derivedNonPosFloatOrder
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
      example("test   Eq[NegDouble]", testEq),
      example("test Hash[NegDouble]", testHash),
      example("test Order[NegDouble]", testOrder),
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

    def testHash: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingHash

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.NegDouble.derivedNegDoubleHash
        """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }

    def testOrder: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingOrder

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.NegDouble.derivedNegDoubleOrder
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
      example("test   Eq[NonNegDouble]", testEq),
      example("test Hash[NonNegDouble]", testHash),
      example("test Order[NonNegDouble]", testOrder),
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

    def testHash: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingHash

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.NonNegDouble.derivedNonNegDoubleHash
        """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }

    def testOrder: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingOrder

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.NonNegDouble.derivedNonNegDoubleOrder
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
      example("test   Eq[PosDouble]", testEq),
      example("test Hash[PosDouble]", testHash),
      example("test Order[PosDouble]", testOrder),
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

    def testHash: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingHash

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.PosDouble.derivedPosDoubleHash
        """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }

    def testOrder: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingOrder

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.PosDouble.derivedPosDoubleOrder
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
      example("test   Eq[NonPosDouble]", testEq),
      example("test Hash[NonPosDouble]", testHash),
      example("test Order[NonPosDouble]", testOrder),
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

    def testHash: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingHash

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.NonPosDouble.derivedNonPosDoubleHash
        """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }

    def testOrder: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingOrder

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.NonPosDouble.derivedNonPosDoubleOrder
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
      example("test   Eq[NegBigInt]", testEq),
      example("test Hash[NegBigInt]", testHash),
      example("test Order[NegBigInt]", testOrder),
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

    def testHash: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingHash

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.NegBigInt.derivedNegBigIntHash
        """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }

    def testOrder: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingOrder

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.NegBigInt.derivedNegBigIntOrder
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
      example("test   Eq[NonNegBigInt]", testEq),
      example("test Hash[NonNegBigInt]", testHash),
      example("test Order[NonNegBigInt]", testOrder),
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

    def testHash: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingHash

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.NonNegBigInt.derivedNonNegBigIntHash
        """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }

    def testOrder: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingOrder

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.NonNegBigInt.derivedNonNegBigIntOrder
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
      example("test   Eq[PosBigInt]", testEq),
      example("test Hash[PosBigInt]", testHash),
      example("test Order[PosBigInt]", testOrder),
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

    def testHash: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingHash

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.PosBigInt.derivedPosBigIntHash
        """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }

    def testOrder: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingOrder

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.PosBigInt.derivedPosBigIntOrder
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
      example("test   Eq[NonPosBigInt]", testEq),
      example("test Hash[NonPosBigInt]", testHash),
      example("test Order[NonPosBigInt]", testOrder),
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

    def testHash: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingHash

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.NonPosBigInt.derivedNonPosBigIntHash
        """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }

    def testOrder: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingOrder

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.NonPosBigInt.derivedNonPosBigIntOrder
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
      example("test   Eq[NegBigDecimal]", testEq),
      example("test Hash[NegBigDecimal]", testHash),
      example("test Order[NegBigDecimal]", testOrder),
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

    def testHash: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingHash

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.NegBigDecimal.derivedNegBigDecimalHash
        """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }

    def testOrder: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingOrder

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.NegBigDecimal.derivedNegBigDecimalOrder
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
      example("test   Eq[NonNegBigDecimal]", testEq),
      example("test Hash[NonNegBigDecimal]", testHash),
      example("test Order[NonNegBigDecimal]", testOrder),
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

    def testHash: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingHash

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.NonNegBigDecimal.derivedNonNegBigDecimalHash
        """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }

    def testOrder: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingOrder

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.NonNegBigDecimal.derivedNonNegBigDecimalOrder
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
      example("test   Eq[PosBigDecimal]", testEq),
      example("test Hash[PosBigDecimal]", testHash),
      example("test Order[PosBigDecimal]", testOrder),
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

    def testHash: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingHash

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.PosBigDecimal.derivedPosBigDecimalHash
        """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }

    def testOrder: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingOrder

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.PosBigDecimal.derivedPosBigDecimalOrder
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
      example("test   Eq[NonPosBigDecimal]", testEq),
      example("test Hash[NonPosBigDecimal]", testHash),
      example("test Order[NonPosBigDecimal]", testOrder),
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

    def testHash: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingHash

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.NonPosBigDecimal.derivedNonPosBigDecimalHash
        """
      ).map(_.message).mkString

      (actual ==== expected)
        .log(
          """The actual error message doesn't start with the expected one.
            |""".stripMargin
        )
    }

    def testOrder: Result = {
      import scala.compiletime.testing.typeCheckErrors
      val expected = ExpectedErrorMessages.missingOrder

      val actual = typeCheckErrors(
        """
        val _ = refined4s.types.numeric.NonPosBigDecimal.derivedNonPosBigDecimalOrder
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
