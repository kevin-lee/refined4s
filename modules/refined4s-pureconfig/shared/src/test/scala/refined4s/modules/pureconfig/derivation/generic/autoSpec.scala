package refined4s.modules.pureconfig.derivation.generic

import cats.syntax.all.*
import com.typesafe.config.{ConfigRenderOptions, ConfigValue, ConfigValueFactory}
import hedgehog.*
import hedgehog.runner.*
import pureconfig.error.{ConfigReaderFailures, ConvertFailure, UserValidationFailed}
import pureconfig.generic.derivation.default.*
import pureconfig.{ConfigReader, ConfigSource, ConfigWriter}
import refined4s.*
import refined4s.internal.typeTools
import refined4s.modules.pureconfig.derivation.generic.auto.given
import refined4s.types.networkGens
import refined4s.types.all.*

import java.util.UUID

/** @author Kevin Lee
  * @since 2023-12-13
  */
object autoSpec extends Properties {
  override def tests: List[Test] = PredefinedRefinedTypesSpec.tests ++ CustomTypeSpec.tests

  object PredefinedRefinedTypesSpec {
    def tests: List[Test] = List(
      property("test ConfigReader[NegInt]", testConfigReaderNegInt),
      property("test ConfigReader[NegInt] with invalid value", testConfigReaderNegIntInvalid),
      property("test ConfigWriter[NegInt]", testConfigWriterNegInt),
      //
      property("test ConfigReader[NonNegInt]", testConfigReaderNonNegInt),
      property("test ConfigReader[NonNegInt] with invalid value", testConfigReaderNonNegIntInvalid),
      property("test ConfigWriter[NonNegInt]", testConfigWriterNonNegInt),
      //
      property("test ConfigReader[PosInt]", testConfigReaderPosInt),
      property("test ConfigReader[PosInt] with invalid value", testConfigReaderPosIntInvalid),
      property("test ConfigWriter[PosInt]", testConfigWriterPosInt),
      //
      property("test ConfigReader[NonPosInt]", testConfigReaderNonPosInt),
      property("test ConfigReader[NonPosInt] with invalid value", testConfigReaderNonPosIntInvalid),
      property("test ConfigWriter[NonPosInt]", testConfigWriterNonPosInt),
      //
      property("test ConfigReader[NegLong]", testConfigReaderNegLong),
      property("test ConfigReader[NegLong] with invalid value", testConfigReaderNegLongInvalid),
      property("test ConfigWriter[NegLong]", testConfigWriterNegLong),
      //
      property("test ConfigReader[NonNegLong]", testConfigReaderNonNegLong),
      property("test ConfigReader[NonNegLong] with invalid value", testConfigReaderNonNegLongInvalid),
      property("test ConfigWriter[NonNegLong]", testConfigWriterNonNegLong),
      //
      property("test ConfigReader[PosLong]", testConfigReaderPosLong),
      property("test ConfigReader[PosLong] with invalid value", testConfigReaderPosLongInvalid),
      property("test ConfigWriter[PosLong]", testConfigWriterPosLong),
      //
      property("test ConfigReader[NonPosLong]", testConfigReaderNonPosLong),
      property("test ConfigReader[NonPosLong] with invalid value", testConfigReaderNonPosLongInvalid),
      property("test ConfigWriter[NonPosLong]", testConfigWriterNonPosLong),
      //
      property("test ConfigReader[NegShort]", testConfigReaderNegShort),
      property("test ConfigReader[NegShort] with invalid value", testConfigReaderNegShortInvalid),
      property("test ConfigWriter[NegShort]", testConfigWriterNegShort),
      //
      property("test ConfigReader[NonNegShort]", testConfigReaderNonNegShort),
      property("test ConfigReader[NonNegShort] with invalid value", testConfigReaderNonNegShortInvalid),
      property("test ConfigWriter[NonNegShort]", testConfigWriterNonNegShort),
      //
      property("test ConfigReader[PosShort]", testConfigReaderPosShort),
      property("test ConfigReader[PosShort] with invalid value", testConfigReaderPosShortInvalid),
      property("test ConfigWriter[PosShort]", testConfigWriterPosShort),
      //
      property("test ConfigReader[NonPosShort]", testConfigReaderNonPosShort),
      property("test ConfigReader[NonPosShort] with invalid value", testConfigReaderNonPosShortInvalid),
      property("test ConfigWriter[NonPosShort]", testConfigWriterNonPosShort),
      //
      property("test ConfigReader[NegByte]", testConfigReaderNegByte),
      property("test ConfigReader[NegByte] with invalid value", testConfigReaderNegByteInvalid),
      property("test ConfigWriter[NegByte]", testConfigWriterNegByte),
      //
      property("test ConfigReader[NonNegByte]", testConfigReaderNonNegByte),
      property("test ConfigReader[NonNegByte] with invalid value", testConfigReaderNonNegByteInvalid),
      property("test ConfigWriter[NonNegByte]", testConfigWriterNonNegByte),
      //
      property("test ConfigReader[PosByte]", testConfigReaderPosByte),
      property("test ConfigReader[PosByte] with invalid value", testConfigReaderPosByteInvalid),
      property("test ConfigWriter[PosByte]", testConfigWriterPosByte),
      //
      property("test ConfigReader[NonPosByte]", testConfigReaderNonPosByte),
      property("test ConfigReader[NonPosByte] with invalid value", testConfigReaderNonPosByteInvalid),
      property("test ConfigWriter[NonPosByte]", testConfigWriterNonPosByte),
      //
      property("test ConfigReader[NegFloat]", testConfigReaderNegFloat),
      property("test ConfigReader[NegFloat] with invalid value", testConfigReaderNegFloatInvalid),
      property("test ConfigWriter[NegFloat]", testConfigWriterNegFloat),
      //
      property("test ConfigReader[NonNegFloat]", testConfigReaderNonNegFloat),
      property("test ConfigReader[NonNegFloat] with invalid value", testConfigReaderNonNegFloatInvalid),
      property("test ConfigWriter[NonNegFloat]", testConfigWriterNonNegFloat),
      //
      property("test ConfigReader[PosFloat]", testConfigReaderPosFloat),
      property("test ConfigReader[PosFloat] with invalid value", testConfigReaderPosFloatInvalid),
      property("test ConfigWriter[PosFloat]", testConfigWriterPosFloat),
      //
      property("test ConfigReader[NonPosFloat]", testConfigReaderNonPosFloat),
      property("test ConfigReader[NonPosFloat] with invalid value", testConfigReaderNonPosFloatInvalid),
      property("test ConfigWriter[NonPosFloat]", testConfigWriterNonPosFloat),
      //
      property("test ConfigReader[NegDouble]", testConfigReaderNegDouble),
      property("test ConfigReader[NegDouble] with invalid value", testConfigReaderNegDoubleInvalid),
      property("test ConfigWriter[NegDouble]", testConfigWriterNegDouble),
      //
      property("test ConfigReader[NonNegDouble]", testConfigReaderNonNegDouble),
      property("test ConfigReader[NonNegDouble] with invalid value", testConfigReaderNonNegDoubleInvalid),
      property("test ConfigWriter[NonNegDouble]", testConfigWriterNonNegDouble),
      //
      property("test ConfigReader[PosDouble]", testConfigReaderPosDouble),
      property("test ConfigReader[PosDouble] with invalid value", testConfigReaderPosDoubleInvalid),
      property("test ConfigWriter[PosDouble]", testConfigWriterPosDouble),
      //
      property("test ConfigReader[NonPosDouble]", testConfigReaderNonPosDouble),
      property("test ConfigReader[NonPosDouble] with invalid value", testConfigReaderNonPosDoubleInvalid),
      property("test ConfigWriter[NonPosDouble]", testConfigWriterNonPosDouble),
      //
      property("test ConfigReader[NegBigInt]", testConfigReaderNegBigInt),
      property("test ConfigReader[NegBigInt] with invalid value", testConfigReaderNegBigIntInvalid),
      property("test ConfigWriter[NegBigInt]", testConfigWriterNegBigInt),
      //
      property("test ConfigReader[NonNegBigInt]", testConfigReaderNonNegBigInt),
      property("test ConfigReader[NonNegBigInt] with invalid value", testConfigReaderNonNegBigIntInvalid),
      property("test ConfigWriter[NonNegBigInt]", testConfigWriterNonNegBigInt),
      //
      property("test ConfigReader[PosBigInt]", testConfigReaderPosBigInt),
      property("test ConfigReader[PosBigInt] with invalid value", testConfigReaderPosBigIntInvalid),
      property("test ConfigWriter[PosBigInt]", testConfigWriterPosBigInt),
      //
      property("test ConfigReader[NonPosBigInt]", testConfigReaderNonPosBigInt),
      property("test ConfigReader[NonPosBigInt] with invalid value", testConfigReaderNonPosBigIntInvalid),
      property("test ConfigWriter[NonPosBigInt]", testConfigWriterNonPosBigInt),
      //
      property("test ConfigReader[NegBigDecimal]", testConfigReaderNegBigDecimal),
      property("test ConfigReader[NegBigDecimal] with invalid value", testConfigReaderNegBigDecimalInvalid),
      property("test ConfigWriter[NegBigDecimal]", testConfigWriterNegBigDecimal),
      //
      property("test ConfigReader[NonNegBigDecimal]", testConfigReaderNonNegBigDecimal),
      property("test ConfigReader[NonNegBigDecimal] with invalid value", testConfigReaderNonNegBigDecimalInvalid),
      property("test ConfigWriter[NonNegBigDecimal]", testConfigWriterNonNegBigDecimal),
      //
      property("test ConfigReader[PosBigDecimal]", testConfigReaderPosBigDecimal),
      property("test ConfigReader[PosBigDecimal] with invalid value", testConfigReaderPosBigDecimalInvalid),
      property("test ConfigWriter[PosBigDecimal]", testConfigWriterPosBigDecimal),
      //
      property("test ConfigReader[NonPosBigDecimal]", testConfigReaderNonPosBigDecimal),
      property("test ConfigReader[NonPosBigDecimal] with invalid value", testConfigReaderNonPosBigDecimalInvalid),
      property("test ConfigWriter[NonPosBigDecimal]", testConfigWriterNonPosBigDecimal),
      //
      property("test ConfigReader[NonEmptyString]", testConfigReaderNonEmptyString),
      example("test ConfigReader[NonEmptyString] with invalid value", testConfigReaderNonEmptyStringInvalid),
      property("test ConfigWriter[NonEmptyString]", testConfigWriterNonEmptyString),
      //
      property("test ConfigReader[NonBlankString]", testConfigReaderNonBlankString),
      property("test ConfigReader[NonBlankString] with invalid value", testConfigReaderNonBlankStringInvalid),
      property("test ConfigWriter[NonBlankString]", testConfigWriterNonBlankString),
      //
      property("test ConfigReader[Uuid]", testConfigReaderUuid),
      property("test ConfigReader[Uuid] with invalid value", testConfigReaderUuidInvalid),
      property("test ConfigWriter[Uuid]", testConfigWriterUuid),
      //
      property("test ConfigReader[Uri]", testConfigReaderUri),
      property("test ConfigReader[Uri] with invalid value", testConfigReaderUriInvalid),
      property("test ConfigWriter[Uri]", testConfigWriterUri),

      //
      property("test ConfigReader[Url]", testConfigReaderUrl),
      property("test ConfigReader[Url] with invalid value", testConfigReaderUrlInvalid),
      property("test ConfigWriter[Url]", testConfigWriterUrl),

      //
      property("test ConfigReader[PortNumber]", testConfigReaderPortNumber),
      property("test ConfigReader[PortNumber] with invalid value", testConfigReaderPortNumberInvalid),
      property("test ConfigWriter[PortNumber]", testConfigWriterPortNumber),
      //
      property("test ConfigReader[SystemPortNumber]", testConfigReaderSystemPortNumber),
      property("test ConfigReader[SystemPortNumber] with invalid value", testConfigReaderSystemPortNumberInvalid),
      property("test ConfigWriter[SystemPortNumber]", testConfigWriterSystemPortNumber),
      //
      property("test ConfigReader[NonSystemPortNumber]", testConfigReaderNonSystemPortNumber),
      property("test ConfigReader[NonSystemPortNumber] with invalid value", testConfigReaderNonSystemPortNumberInvalid),
      property("test ConfigWriter[NonSystemPortNumber]", testConfigWriterNonSystemPortNumber),
      //
      property("test ConfigReader[UserPortNumber]", testConfigReaderUserPortNumber),
      property("test ConfigReader[UserPortNumber] with invalid value", testConfigReaderUserPortNumberInvalid),
      property("test ConfigWriter[UserPortNumber]", testConfigWriterUserPortNumber),
      //
      property("test ConfigReader[DynamicPortNumber]", testConfigReaderDynamicPortNumber),
      property("test ConfigReader[DynamicPortNumber] with invalid value", testConfigReaderDynamicPortNumberInvalid),
      property("test ConfigWriter[DynamicPortNumber]", testConfigWriterDynamicPortNumber),
    )

    def testConfigReaderNegInt: Property =
      for {
        n <- Gen.int(Range.linear(-1, Int.MinValue)).log("n")
      } yield {

        val confString =
          raw"""
               |number = ${n.toString}
               |""".stripMargin

        val expected = NegInt.unsafeFrom(n)

        ConfigSource
          .string(confString)
          .load[NegIntConfig] match {
          case Right(NegIntConfig(actual)) =>
            actual ==== expected

          case Left(err) =>
            Result.failure.log(s"parse config failed with error: ${err.toString}")
        }

      }

    def testConfigReaderNegIntInvalid: Property =
      for {
        n <- Gen.int(Range.linear(0, Int.MaxValue)).log("n")
      } yield {

        val confString =
          raw"""number = ${n.toString}"""

        val expected = NegInt.from(n).leftMap { err =>
          s"The value ${n.toString} cannot be created as the expected type, ${typeTools.getTypeName[refined4s.types.numeric.NegInt]}.Type, due to the following error: $err"
        }

        ConfigSource
          .string(confString)
          .load[NegIntConfig] match {
          case Right(NegIntConfig(actual)) =>
            Result.failure.log(s"It should have failed to parse the config but got $actual")

          case Left(ConfigReaderFailures(ConvertFailure(UserValidationFailed(err), _, _))) =>
            err.asLeft ==== expected

          case unexpected =>
            Result.failure.log(s"Unexpected result: ${unexpected.toString}")
        }

      }

    def testConfigWriterNegInt: Property =
      for {
        n <- Gen.int(Range.linear(-1, Int.MinValue)).log("n")
      } yield {

        val input = NegInt.unsafeFrom(n)

        val expected = ConfigWriter[Int].to(n)
        val actual   = ConfigWriter[NegInt].to(input)

        actual ==== expected

      }

    final case class NegIntConfig(number: NegInt) derives ConfigReader

    ///

    def testConfigReaderNonNegInt: Property =
      for {
        n <- Gen.int(Range.linear(0, Int.MaxValue)).log("n")
      } yield {

        val confString =
          raw"""
               |number = ${n.toString}
               |""".stripMargin

        val expected = NonNegInt.unsafeFrom(n)

        ConfigSource
          .string(confString)
          .load[NonNegIntConfig] match {
          case Right(NonNegIntConfig(actual)) =>
            actual ==== expected

          case Left(err) =>
            Result.failure.log(s"parse config failed with error: ${err.toString}")
        }

      }

    def testConfigReaderNonNegIntInvalid: Property =
      for {
        n <- Gen.int(Range.linear(Int.MinValue, -1)).log("n")
      } yield {

        val confString =
          raw"""number = ${n.toString}"""

        val expected = NonNegInt.from(n).leftMap { err =>
          s"The value ${n.toString} cannot be created as the expected type, ${typeTools.getTypeName[refined4s.types.numeric.NonNegInt]}.Type, due to the following error: $err"
        }

        ConfigSource
          .string(confString)
          .load[NonNegIntConfig] match {
          case Right(NonNegIntConfig(actual)) =>
            Result.failure.log(s"It should have failed to parse the config but got $actual")

          case Left(ConfigReaderFailures(ConvertFailure(UserValidationFailed(err), _, _))) =>
            err.asLeft ==== expected

          case unexpected =>
            Result.failure.log(s"Unexpected result: ${unexpected.toString}")
        }

      }

    def testConfigWriterNonNegInt: Property =
      for {
        n <- Gen.int(Range.linear(0, Int.MaxValue)).log("n")
      } yield {

        val input = NonNegInt.unsafeFrom(n)

        val expected = ConfigWriter[Int].to(n)
        val actual   = ConfigWriter[NonNegInt].to(input)

        actual ==== expected

      }

    final case class NonNegIntConfig(number: NonNegInt) derives ConfigReader

    ///

    def testConfigReaderPosInt: Property =
      for {
        n <- Gen.int(Range.linear(1, Int.MaxValue)).log("n")
      } yield {

        val confString =
          raw"""
               |number = ${n.toString}
               |""".stripMargin

        val expected = PosInt.unsafeFrom(n)

        ConfigSource
          .string(confString)
          .load[PosIntConfig] match {
          case Right(PosIntConfig(actual)) =>
            actual ==== expected

          case Left(err) =>
            Result.failure.log(s"parse config failed with error: ${err.toString}")
        }

      }

    def testConfigReaderPosIntInvalid: Property =
      for {
        n <- Gen.int(Range.linear(Int.MinValue, 0)).log("n")
      } yield {

        val confString =
          raw"""number = ${n.toString}"""

        val expected = PosInt.from(n).leftMap { err =>
          s"The value ${n.toString} cannot be created as the expected type, ${typeTools.getTypeName[refined4s.types.numeric.PosInt]}.Type, due to the following error: $err"
        }

        ConfigSource
          .string(confString)
          .load[PosIntConfig] match {
          case Right(PosIntConfig(actual)) =>
            Result.failure.log(s"It should have failed to parse the config but got $actual")

          case Left(ConfigReaderFailures(ConvertFailure(UserValidationFailed(err), _, _))) =>
            err.asLeft ==== expected

          case unexpected =>
            Result.failure.log(s"Unexpected result: ${unexpected.toString}")
        }

      }

    def testConfigWriterPosInt: Property =
      for {
        n <- Gen.int(Range.linear(1, Int.MaxValue)).log("n")
      } yield {

        val input = PosInt.unsafeFrom(n)

        val expected = ConfigWriter[Int].to(n)
        val actual   = ConfigWriter[PosInt].to(input)

        actual ==== expected

      }

    final case class PosIntConfig(number: PosInt) derives ConfigReader

    ///
    def testConfigReaderNonPosInt: Property =
      for {
        n <- Gen.int(Range.linear(0, Int.MinValue)).log("n")
      } yield {

        val confString =
          raw"""
               |number = ${n.toString}
               |""".stripMargin

        val expected = NonPosInt.unsafeFrom(n)

        ConfigSource
          .string(confString)
          .load[NonPosIntConfig] match {
          case Right(NonPosIntConfig(actual)) =>
            actual ==== expected

          case Left(err) =>
            Result.failure.log(s"parse config failed with error: ${err.toString}")
        }

      }

    def testConfigReaderNonPosIntInvalid: Property =
      for {
        n <- Gen.int(Range.linear(1, Int.MaxValue)).log("n")
      } yield {

        val confString =
          raw"""number = ${n.toString}"""

        val expected = NonPosInt.from(n).leftMap { err =>
          s"The value ${n.toString} cannot be created as the expected type, ${typeTools.getTypeName[refined4s.types.numeric.NonPosInt]}.Type, due to the following error: $err"
        }

        ConfigSource
          .string(confString)
          .load[NonPosIntConfig] match {
          case Right(NonPosIntConfig(actual)) =>
            Result.failure.log(s"It should have failed to parse the config but got $actual")

          case Left(ConfigReaderFailures(ConvertFailure(UserValidationFailed(err), _, _))) =>
            err.asLeft ==== expected

          case unexpected =>
            Result.failure.log(s"Unexpected result: ${unexpected.toString}")
        }

      }

    def testConfigWriterNonPosInt: Property =
      for {
        n <- Gen.int(Range.linear(0, Int.MinValue)).log("n")
      } yield {

        val input = NonPosInt.unsafeFrom(n)

        val expected = ConfigWriter[Int].to(n)
        val actual   = ConfigWriter[NonPosInt].to(input)

        actual ==== expected

      }

    final case class NonPosIntConfig(number: NonPosInt) derives ConfigReader

    ///

    def testConfigReaderNegLong: Property =
      for {
        n <- Gen.long(Range.linear(-1L, Long.MinValue)).log("n")
      } yield {

        val confString =
          raw"""
               |number = ${n.toString}
               |""".stripMargin

        val expected = NegLong.unsafeFrom(n)

        ConfigSource
          .string(confString)
          .load[NegLongConfig] match {
          case Right(NegLongConfig(actual)) =>
            actual ==== expected

          case Left(err) =>
            Result.failure.log(s"parse config failed with error: ${err.toString}")
        }

      }

    def testConfigReaderNegLongInvalid: Property =
      for {
        n <- Gen.long(Range.linear(0, Long.MaxValue)).log("n")
      } yield {

        val confString =
          raw"""number = ${n.toString}"""

        val expected = NegLong.from(n).leftMap { err =>
          s"The value ${n.toString} cannot be created as the expected type, ${typeTools.getTypeName[refined4s.types.numeric.NegLong]}.Type, due to the following error: $err"
        }

        ConfigSource
          .string(confString)
          .load[NegLongConfig] match {
          case Right(NegLongConfig(actual)) =>
            Result.failure.log(s"It should have failed to parse the config but got $actual")

          case Left(ConfigReaderFailures(ConvertFailure(UserValidationFailed(err), _, _))) =>
            err.asLeft ==== expected

          case unexpected =>
            Result.failure.log(s"Unexpected result: ${unexpected.toString}")
        }

      }

    def testConfigWriterNegLong: Property =
      for {
        n <- Gen.long(Range.linear(-1L, Long.MinValue)).log("n")
      } yield {

        val input = NegLong.unsafeFrom(n)

        val expected = ConfigWriter[Long].to(n)
        val actual   = ConfigWriter[NegLong].to(input)

        actual ==== expected

      }

    final case class NegLongConfig(number: NegLong) derives ConfigReader

    ///

    def testConfigReaderNonNegLong: Property =
      for {
        n <- Gen.long(Range.linear(0L, Long.MaxValue)).log("n")
      } yield {

        val confString =
          raw"""
               |number = ${n.toString}
               |""".stripMargin

        val expected = NonNegLong.unsafeFrom(n)

        ConfigSource
          .string(confString)
          .load[NonNegLongConfig] match {
          case Right(NonNegLongConfig(actual)) =>
            actual ==== expected

          case Left(err) =>
            Result.failure.log(s"parse config failed with error: ${err.toString}")
        }

      }

    def testConfigReaderNonNegLongInvalid: Property =
      for {
        n <- Gen.long(Range.linear(Long.MinValue, -1L)).log("n")
      } yield {

        val confString =
          raw"""number = ${n.toString}"""

        val expected = NonNegLong.from(n).leftMap { err =>
          s"The value ${n.toString} cannot be created as the expected type, ${typeTools.getTypeName[refined4s.types.numeric.NonNegLong]}.Type, due to the following error: $err"
        }

        ConfigSource
          .string(confString)
          .load[NonNegLongConfig] match {
          case Right(NonNegLongConfig(actual)) =>
            Result.failure.log(s"It should have failed to parse the config but got $actual")

          case Left(ConfigReaderFailures(ConvertFailure(UserValidationFailed(err), _, _))) =>
            err.asLeft ==== expected

          case unexpected =>
            Result.failure.log(s"Unexpected result: ${unexpected.toString}")
        }

      }

    def testConfigWriterNonNegLong: Property =
      for {
        n <- Gen.long(Range.linear(0L, Long.MaxValue)).log("n")
      } yield {

        val input = NonNegLong.unsafeFrom(n)

        val expected = ConfigWriter[Long].to(n)
        val actual   = ConfigWriter[NonNegLong].to(input)

        actual ==== expected

      }

    final case class NonNegLongConfig(number: NonNegLong) derives ConfigReader

    ///

    def testConfigReaderPosLong: Property =
      for {
        n <- Gen.long(Range.linear(1L, Long.MaxValue)).log("n")
      } yield {

        val confString =
          raw"""
               |number = ${n.toString}
               |""".stripMargin

        val expected = PosLong.unsafeFrom(n)

        ConfigSource
          .string(confString)
          .load[PosLongConfig] match {
          case Right(PosLongConfig(actual)) =>
            actual ==== expected

          case Left(err) =>
            Result.failure.log(s"parse config failed with error: ${err.toString}")
        }

      }

    def testConfigReaderPosLongInvalid: Property =
      for {
        n <- Gen.long(Range.linear(Long.MinValue, 0L)).log("n")
      } yield {

        val confString =
          raw"""number = ${n.toString}"""

        val expected = PosLong.from(n).leftMap { err =>
          s"The value ${n.toString} cannot be created as the expected type, ${typeTools.getTypeName[refined4s.types.numeric.PosLong]}.Type, due to the following error: $err"
        }

        ConfigSource
          .string(confString)
          .load[PosLongConfig] match {
          case Right(PosLongConfig(actual)) =>
            Result.failure.log(s"It should have failed to parse the config but got $actual")

          case Left(ConfigReaderFailures(ConvertFailure(UserValidationFailed(err), _, _))) =>
            err.asLeft ==== expected

          case unexpected =>
            Result.failure.log(s"Unexpected result: ${unexpected.toString}")
        }

      }

    def testConfigWriterPosLong: Property =
      for {
        n <- Gen.long(Range.linear(1L, Long.MaxValue)).log("n")
      } yield {

        val input = PosLong.unsafeFrom(n)

        val expected = ConfigWriter[Long].to(n)
        val actual   = ConfigWriter[PosLong].to(input)

        actual ==== expected

      }

    final case class PosLongConfig(number: PosLong) derives ConfigReader

    ///

    def testConfigReaderNonPosLong: Property =
      for {
        n <- Gen.long(Range.linear(0, Long.MinValue)).log("n")
      } yield {

        val confString =
          raw"""
               |number = ${n.toString}
               |""".stripMargin

        val expected = NonPosLong.unsafeFrom(n)

        ConfigSource
          .string(confString)
          .load[NonPosLongConfig] match {
          case Right(NonPosLongConfig(actual)) =>
            actual ==== expected

          case Left(err) =>
            Result.failure.log(s"parse config failed with error: ${err.toString}")
        }

      }

    def testConfigReaderNonPosLongInvalid: Property =
      for {
        n <- Gen.long(Range.linear(1, Long.MaxValue)).log("n")
      } yield {

        val confString =
          raw"""number = ${n.toString}"""

        val expected = NonPosLong.from(n).leftMap { err =>
          s"The value ${n.toString} cannot be created as the expected type, ${typeTools.getTypeName[refined4s.types.numeric.NonPosLong]}.Type, due to the following error: $err"
        }

        ConfigSource
          .string(confString)
          .load[NonPosLongConfig] match {
          case Right(NonPosLongConfig(actual)) =>
            Result.failure.log(s"It should have failed to parse the config but got $actual")

          case Left(ConfigReaderFailures(ConvertFailure(UserValidationFailed(err), _, _))) =>
            err.asLeft ==== expected

          case unexpected =>
            Result.failure.log(s"Unexpected result: ${unexpected.toString}")
        }

      }

    def testConfigWriterNonPosLong: Property =
      for {
        n <- Gen.long(Range.linear(0, Long.MinValue)).log("n")
      } yield {

        val input = NonPosLong.unsafeFrom(n)

        val expected = ConfigWriter[Long].to(n)
        val actual   = ConfigWriter[NonPosLong].to(input)

        actual ==== expected

      }

    final case class NonPosLongConfig(number: NonPosLong) derives ConfigReader

    ///

    def testConfigReaderNegShort: Property =
      for {
        n <- Gen.short(Range.linear(-1, Short.MinValue)).log("n")
      } yield {

        val confString =
          raw"""
               |number = ${n.toString}
               |""".stripMargin

        val expected = NegShort.unsafeFrom(n)

        ConfigSource
          .string(confString)
          .load[NegShortConfig] match {
          case Right(NegShortConfig(actual)) =>
            actual ==== expected

          case Left(err) =>
            Result.failure.log(s"parse config failed with error: ${err.toString}")
        }

      }

    def testConfigReaderNegShortInvalid: Property =
      for {
        n <- Gen.short(Range.linear(0, Short.MaxValue)).log("n")
      } yield {

        val confString =
          raw"""number = ${n.toString}"""

        val expected = NegShort.from(n).leftMap { err =>
          s"The value ${n.toString} cannot be created as the expected type, ${typeTools.getTypeName[refined4s.types.numeric.NegShort]}.Type, due to the following error: $err"
        }

        ConfigSource
          .string(confString)
          .load[NegShortConfig] match {
          case Right(NegShortConfig(actual)) =>
            Result.failure.log(s"It should have failed to parse the config but got $actual")

          case Left(ConfigReaderFailures(ConvertFailure(UserValidationFailed(err), _, _))) =>
            err.asLeft ==== expected

          case unexpected =>
            Result.failure.log(s"Unexpected result: ${unexpected.toString}")
        }

      }

    def testConfigWriterNegShort: Property =
      for {
        n <- Gen.short(Range.linear(-1, Short.MinValue)).log("n")
      } yield {

        val input = NegShort.unsafeFrom(n)

        val expected = ConfigWriter[Short].to(n)
        val actual   = ConfigWriter[NegShort].to(input)

        actual ==== expected

      }

    final case class NegShortConfig(number: NegShort) derives ConfigReader

    ///

    def testConfigReaderNonNegShort: Property =
      for {
        n <- Gen.short(Range.linear(0, Short.MaxValue)).log("n")
      } yield {

        val confString =
          raw"""
               |number = ${n.toString}
               |""".stripMargin

        val expected = NonNegShort.unsafeFrom(n)

        ConfigSource
          .string(confString)
          .load[NonNegShortConfig] match {
          case Right(NonNegShortConfig(actual)) =>
            actual ==== expected

          case Left(err) =>
            Result.failure.log(s"parse config failed with error: ${err.toString}")
        }

      }

    def testConfigReaderNonNegShortInvalid: Property =
      for {
        n <- Gen.short(Range.linear(Short.MinValue, -1)).log("n")
      } yield {

        val confString =
          raw"""number = ${n.toString}"""

        val expected = NonNegShort.from(n).leftMap { err =>
          s"The value ${n.toString} cannot be created as the expected type, ${typeTools.getTypeName[refined4s.types.numeric.NonNegShort]}.Type, due to the following error: $err"
        }

        ConfigSource
          .string(confString)
          .load[NonNegShortConfig] match {
          case Right(NonNegShortConfig(actual)) =>
            Result.failure.log(s"It should have failed to parse the config but got $actual")

          case Left(ConfigReaderFailures(ConvertFailure(UserValidationFailed(err), _, _))) =>
            err.asLeft ==== expected

          case unexpected =>
            Result.failure.log(s"Unexpected result: ${unexpected.toString}")
        }

      }

    def testConfigWriterNonNegShort: Property =
      for {
        n <- Gen.short(Range.linear(0, Short.MaxValue)).log("n")
      } yield {

        val input = NonNegShort.unsafeFrom(n)

        val expected = ConfigWriter[Short].to(n)
        val actual   = ConfigWriter[NonNegShort].to(input)

        actual ==== expected

      }

    final case class NonNegShortConfig(number: NonNegShort) derives ConfigReader

    ///

    def testConfigReaderPosShort: Property =
      for {
        n <- Gen.short(Range.linear(1, Short.MaxValue)).log("n")
      } yield {

        val confString =
          raw"""
               |number = ${n.toString}
               |""".stripMargin

        val expected = PosShort.unsafeFrom(n)

        ConfigSource
          .string(confString)
          .load[PosShortConfig] match {
          case Right(PosShortConfig(actual)) =>
            actual ==== expected

          case Left(err) =>
            Result.failure.log(s"parse config failed with error: ${err.toString}")
        }

      }

    def testConfigReaderPosShortInvalid: Property =
      for {
        n <- Gen.short(Range.linear(Short.MinValue, 0)).log("n")
      } yield {

        val confString =
          raw"""number = ${n.toString}"""

        val expected = PosShort.from(n).leftMap { err =>
          s"The value ${n.toString} cannot be created as the expected type, ${typeTools.getTypeName[refined4s.types.numeric.PosShort]}.Type, due to the following error: $err"
        }

        ConfigSource
          .string(confString)
          .load[PosShortConfig] match {
          case Right(PosShortConfig(actual)) =>
            Result.failure.log(s"It should have failed to parse the config but got $actual")

          case Left(ConfigReaderFailures(ConvertFailure(UserValidationFailed(err), _, _))) =>
            err.asLeft ==== expected

          case unexpected =>
            Result.failure.log(s"Unexpected result: ${unexpected.toString}")
        }

      }

    def testConfigWriterPosShort: Property =
      for {
        n <- Gen.short(Range.linear(1, Short.MaxValue)).log("n")
      } yield {

        val input = PosShort.unsafeFrom(n)

        val expected = ConfigWriter[Short].to(n)
        val actual   = ConfigWriter[PosShort].to(input)

        actual ==== expected

      }

    final case class PosShortConfig(number: PosShort) derives ConfigReader

    ///

    def testConfigReaderNonPosShort: Property =
      for {
        n <- Gen.short(Range.linear(0, Short.MinValue)).log("n")
      } yield {

        val confString =
          raw"""
               |number = ${n.toString}
               |""".stripMargin

        val expected = NonPosShort.unsafeFrom(n)

        ConfigSource
          .string(confString)
          .load[NonPosShortConfig] match {
          case Right(NonPosShortConfig(actual)) =>
            actual ==== expected

          case Left(err) =>
            Result.failure.log(s"parse config failed with error: ${err.toString}")
        }

      }

    def testConfigReaderNonPosShortInvalid: Property =
      for {
        n <- Gen.short(Range.linear(1, Short.MaxValue)).log("n")
      } yield {

        val confString =
          raw"""number = ${n.toString}"""

        val expected = NonPosShort.from(n).leftMap { err =>
          s"The value ${n.toString} cannot be created as the expected type, ${typeTools.getTypeName[refined4s.types.numeric.NonPosShort]}.Type, due to the following error: $err"
        }

        ConfigSource
          .string(confString)
          .load[NonPosShortConfig] match {
          case Right(NonPosShortConfig(actual)) =>
            Result.failure.log(s"It should have failed to parse the config but got $actual")

          case Left(ConfigReaderFailures(ConvertFailure(UserValidationFailed(err), _, _))) =>
            err.asLeft ==== expected

          case unexpected =>
            Result.failure.log(s"Unexpected result: ${unexpected.toString}")
        }

      }

    def testConfigWriterNonPosShort: Property =
      for {
        n <- Gen.short(Range.linear(0, Short.MinValue)).log("n")
      } yield {

        val input = NonPosShort.unsafeFrom(n)

        val expected = ConfigWriter[Short].to(n)
        val actual   = ConfigWriter[NonPosShort].to(input)

        actual ==== expected

      }

    final case class NonPosShortConfig(number: NonPosShort) derives ConfigReader

    ///

    def testConfigReaderNegByte: Property =
      for {
        n <- Gen.byte(Range.linear(-1, Byte.MinValue)).log("n")
      } yield {

        val confString =
          raw"""
               |number = ${n.toString}
               |""".stripMargin

        val expected = NegByte.unsafeFrom(n)

        ConfigSource
          .string(confString)
          .load[NegByteConfig] match {
          case Right(NegByteConfig(actual)) =>
            actual ==== expected

          case Left(err) =>
            Result.failure.log(s"parse config failed with error: ${err.toString}")
        }

      }

    def testConfigReaderNegByteInvalid: Property =
      for {
        n <- Gen.byte(Range.linear(0, Byte.MaxValue)).log("n")
      } yield {

        val confString =
          raw"""number = ${n.toString}"""

        val expected = NegByte.from(n).leftMap { err =>
          s"The value ${n.toString} cannot be created as the expected type, ${typeTools.getTypeName[refined4s.types.numeric.NegByte]}.Type, due to the following error: $err"
        }

        ConfigSource
          .string(confString)
          .load[NegByteConfig] match {
          case Right(NegByteConfig(actual)) =>
            Result.failure.log(s"It should have failed to parse the config but got $actual")

          case Left(ConfigReaderFailures(ConvertFailure(UserValidationFailed(err), _, _))) =>
            err.asLeft ==== expected

          case unexpected =>
            Result.failure.log(s"Unexpected result: ${unexpected.toString}")
        }

      }

    def testConfigWriterNegByte: Property =
      for {
        n <- Gen.byte(Range.linear(-1, Byte.MinValue)).log("n")
      } yield {

        val input = NegByte.unsafeFrom(n)

        val expected = ConfigWriter[Byte].to(n)
        val actual   = ConfigWriter[NegByte].to(input)

        actual ==== expected

      }

    final case class NegByteConfig(number: NegByte) derives ConfigReader

    ///

    def testConfigReaderNonNegByte: Property =
      for {
        n <- Gen.byte(Range.linear(0, Byte.MaxValue)).log("n")
      } yield {

        val confString =
          raw"""
               |number = ${n.toString}
               |""".stripMargin

        val expected = NonNegByte.unsafeFrom(n)

        ConfigSource
          .string(confString)
          .load[NonNegByteConfig] match {
          case Right(NonNegByteConfig(actual)) =>
            actual ==== expected

          case Left(err) =>
            Result.failure.log(s"parse config failed with error: ${err.toString}")
        }

      }

    def testConfigReaderNonNegByteInvalid: Property =
      for {
        n <- Gen.byte(Range.linear(Byte.MinValue, -1)).log("n")
      } yield {

        val confString =
          raw"""number = ${n.toString}"""

        val expected = NonNegByte.from(n).leftMap { err =>
          s"The value ${n.toString} cannot be created as the expected type, ${typeTools.getTypeName[refined4s.types.numeric.NonNegByte]}.Type, due to the following error: $err"
        }

        ConfigSource
          .string(confString)
          .load[NonNegByteConfig] match {
          case Right(NonNegByteConfig(actual)) =>
            Result.failure.log(s"It should have failed to parse the config but got $actual")

          case Left(ConfigReaderFailures(ConvertFailure(UserValidationFailed(err), _, _))) =>
            err.asLeft ==== expected

          case unexpected =>
            Result.failure.log(s"Unexpected result: ${unexpected.toString}")
        }

      }

    def testConfigWriterNonNegByte: Property =
      for {
        n <- Gen.byte(Range.linear(0, Byte.MaxValue)).log("n")
      } yield {

        val input = NonNegByte.unsafeFrom(n)

        val expected = ConfigWriter[Byte].to(n)
        val actual   = ConfigWriter[NonNegByte].to(input)

        actual ==== expected

      }

    final case class NonNegByteConfig(number: NonNegByte) derives ConfigReader

    ///

    def testConfigReaderPosByte: Property =
      for {
        n <- Gen.byte(Range.linear(1, Byte.MaxValue)).log("n")
      } yield {

        val confString =
          raw"""
               |number = ${n.toString}
               |""".stripMargin

        val expected = PosByte.unsafeFrom(n)

        ConfigSource
          .string(confString)
          .load[PosByteConfig] match {
          case Right(PosByteConfig(actual)) =>
            actual ==== expected

          case Left(err) =>
            Result.failure.log(s"parse config failed with error: ${err.toString}")
        }

      }

    def testConfigReaderPosByteInvalid: Property =
      for {
        n <- Gen.byte(Range.linear(Byte.MinValue, 0)).log("n")
      } yield {

        val confString =
          raw"""number = ${n.toString}"""

        val expected = PosByte.from(n).leftMap { err =>
          s"The value ${n.toString} cannot be created as the expected type, ${typeTools.getTypeName[refined4s.types.numeric.PosByte]}.Type, due to the following error: $err"
        }

        ConfigSource
          .string(confString)
          .load[PosByteConfig] match {
          case Right(PosByteConfig(actual)) =>
            Result.failure.log(s"It should have failed to parse the config but got $actual")

          case Left(ConfigReaderFailures(ConvertFailure(UserValidationFailed(err), _, _))) =>
            err.asLeft ==== expected

          case unexpected =>
            Result.failure.log(s"Unexpected result: ${unexpected.toString}")
        }

      }

    def testConfigWriterPosByte: Property =
      for {
        n <- Gen.byte(Range.linear(1, Byte.MaxValue)).log("n")
      } yield {

        val input = PosByte.unsafeFrom(n)

        val expected = ConfigWriter[Byte].to(n)
        val actual   = ConfigWriter[PosByte].to(input)

        actual ==== expected

      }

    final case class PosByteConfig(number: PosByte) derives ConfigReader

    ///

    def testConfigReaderNonPosByte: Property =
      for {
        n <- Gen.byte(Range.linear(0, Byte.MinValue)).log("n")
      } yield {

        val confString =
          raw"""
               |number = ${n.toString}
               |""".stripMargin

        val expected = NonPosByte.unsafeFrom(n)

        ConfigSource
          .string(confString)
          .load[NonPosByteConfig] match {
          case Right(NonPosByteConfig(actual)) =>
            actual ==== expected

          case Left(err) =>
            Result.failure.log(s"parse config failed with error: ${err.toString}")
        }

      }

    def testConfigReaderNonPosByteInvalid: Property =
      for {
        n <- Gen.byte(Range.linear(1, Byte.MaxValue)).log("n")
      } yield {

        val confString =
          raw"""number = ${n.toString}"""

        val expected = NonPosByte.from(n).leftMap { err =>
          s"The value ${n.toString} cannot be created as the expected type, ${typeTools.getTypeName[refined4s.types.numeric.NonPosByte]}.Type, due to the following error: $err"
        }

        ConfigSource
          .string(confString)
          .load[NonPosByteConfig] match {
          case Right(NonPosByteConfig(actual)) =>
            Result.failure.log(s"It should have failed to parse the config but got $actual")

          case Left(ConfigReaderFailures(ConvertFailure(UserValidationFailed(err), _, _))) =>
            err.asLeft ==== expected

          case unexpected =>
            Result.failure.log(s"Unexpected result: ${unexpected.toString}")
        }

      }

    def testConfigWriterNonPosByte: Property =
      for {
        n <- Gen.byte(Range.linear(0, Byte.MinValue)).log("n")
      } yield {

        val input = NonPosByte.unsafeFrom(n)

        val expected = ConfigWriter[Byte].to(n)
        val actual   = ConfigWriter[NonPosByte].to(input)

        actual ==== expected

      }

    final case class NonPosByteConfig(number: NonPosByte) derives ConfigReader

    ///

    def testConfigReaderNegFloat: Property =
      for {
        n <- Gen.double(Range.linearFrac(-0.00001d, Float.MinValue)).map(_.toFloat).log("n")
      } yield {

        val confString =
          raw"""
               |number = ${n.toString}
               |""".stripMargin

        val expected = NegFloat.unsafeFrom(n)

        ConfigSource
          .string(confString)
          .load[NegFloatConfig] match {
          case Right(NegFloatConfig(actual)) =>
            actual ==== expected

          case Left(err) =>
            Result.failure.log(s"parse config failed with error: ${err.toString}")
        }

      }

    def testConfigReaderNegFloatInvalid: Property =
      for {
        n <- Gen.double(Range.linearFrac(0d, Float.MaxValue)).map(_.toFloat).log("n")
      } yield {

        val confString =
          raw"""number = ${n.toString}"""

        val expected = NegFloat.from(n).leftMap { err =>
          s"The value ${n.toString} cannot be created as the expected type, ${typeTools.getTypeName[refined4s.types.numeric.NegFloat]}.Type, due to the following error: $err"
        }

        ConfigSource
          .string(confString)
          .load[NegFloatConfig] match {
          case Right(NegFloatConfig(actual)) =>
            Result.failure.log(s"It should have failed to parse the config but got $actual")

          case Left(ConfigReaderFailures(ConvertFailure(UserValidationFailed(err), _, _))) =>
            err.asLeft ==== expected

          case unexpected =>
            Result.failure.log(s"Unexpected result: ${unexpected.toString}")
        }

      }

    def testConfigWriterNegFloat: Property =
      for {
        n <- Gen.double(Range.linearFrac(-0.00001d, Float.MinValue)).map(_.toFloat).log("n")
      } yield {

        val input = NegFloat.unsafeFrom(n)

        val expected = ConfigWriter[Float].to(n)
        val actual   = ConfigWriter[NegFloat].to(input)

        actual ==== expected

      }

    final case class NegFloatConfig(number: NegFloat) derives ConfigReader

    ///

    def testConfigReaderNonNegFloat: Property =
      for {
        n <- Gen.double(Range.linearFrac(0d, Float.MaxValue)).map(_.toFloat).log("n")
      } yield {

        val confString =
          raw"""
               |number = ${n.toString}
               |""".stripMargin

        val expected = NonNegFloat.unsafeFrom(n)

        ConfigSource
          .string(confString)
          .load[NonNegFloatConfig] match {
          case Right(NonNegFloatConfig(actual)) =>
            actual ==== expected

          case Left(err) =>
            Result.failure.log(s"parse config failed with error: ${err.toString}")
        }

      }

    def testConfigReaderNonNegFloatInvalid: Property =
      for {
        n <- Gen.double(Range.linearFrac(Float.MinValue, -0.00001d)).map(_.toFloat).log("n")
      } yield {

        val confString =
          raw"""number = ${n.toString}"""

        val expected = NonNegFloat.from(n).leftMap { err =>
          s"The value ${n.toString} cannot be created as the expected type, ${typeTools.getTypeName[refined4s.types.numeric.NonNegFloat]}.Type, due to the following error: $err"
        }

        ConfigSource
          .string(confString)
          .load[NonNegFloatConfig] match {
          case Right(NonNegFloatConfig(actual)) =>
            Result.failure.log(s"It should have failed to parse the config but got $actual")

          case Left(ConfigReaderFailures(ConvertFailure(UserValidationFailed(err), _, _))) =>
            err.asLeft ==== expected

          case unexpected =>
            Result.failure.log(s"Unexpected result: ${unexpected.toString}")
        }

      }

    def testConfigWriterNonNegFloat: Property =
      for {
        n <- Gen.double(Range.linearFrac(0d, Float.MaxValue)).map(_.toFloat).log("n")
      } yield {

        val input = NonNegFloat.unsafeFrom(n)

        val expected = ConfigWriter[Float].to(n)
        val actual   = ConfigWriter[NonNegFloat].to(input)

        actual ==== expected

      }

    final case class NonNegFloatConfig(number: NonNegFloat) derives ConfigReader

    ///

    def testConfigReaderPosFloat: Property =
      for {
        n <- Gen.double(Range.linearFrac(0.0001d, Float.MaxValue)).map(_.toFloat).log("n")
      } yield {

        val confString =
          raw"""
               |number = ${n.toString}
               |""".stripMargin

        val expected = PosFloat.unsafeFrom(n)

        ConfigSource
          .string(confString)
          .load[PosFloatConfig] match {
          case Right(PosFloatConfig(actual)) =>
            actual ==== expected

          case Left(err) =>
            Result.failure.log(s"parse config failed with error: ${err.toString}")
        }

      }

    def testConfigReaderPosFloatInvalid: Property =
      for {
        n <- Gen.double(Range.linearFrac(Float.MinValue, 0d)).map(_.toFloat).log("n")
      } yield {

        val confString =
          raw"""number = ${n.toString}"""

        val expected = PosFloat.from(n).leftMap { err =>
          s"The value ${n.toString} cannot be created as the expected type, ${typeTools.getTypeName[refined4s.types.numeric.PosFloat]}.Type, due to the following error: $err"
        }

        ConfigSource
          .string(confString)
          .load[PosFloatConfig] match {
          case Right(PosFloatConfig(actual)) =>
            Result.failure.log(s"It should have failed to parse the config but got $actual")

          case Left(ConfigReaderFailures(ConvertFailure(UserValidationFailed(err), _, _))) =>
            err.asLeft ==== expected

          case unexpected =>
            Result.failure.log(s"Unexpected result: ${unexpected.toString}")
        }

      }

    def testConfigWriterPosFloat: Property =
      for {
        n <- Gen.double(Range.linearFrac(0.0001d, Float.MaxValue)).map(_.toFloat).log("n")
      } yield {

        val input = PosFloat.unsafeFrom(n)

        val expected = ConfigWriter[Float].to(n)
        val actual   = ConfigWriter[PosFloat].to(input)

        actual ==== expected

      }

    final case class PosFloatConfig(number: PosFloat) derives ConfigReader

    ///

    def testConfigReaderNonPosFloat: Property =
      for {
        n <- Gen.double(Range.linearFrac(0d, Float.MinValue)).map(_.toFloat).log("n")
      } yield {

        val confString =
          raw"""
               |number = ${n.toString}
               |""".stripMargin

        val expected = NonPosFloat.unsafeFrom(n)

        ConfigSource
          .string(confString)
          .load[NonPosFloatConfig] match {
          case Right(NonPosFloatConfig(actual)) =>
            actual ==== expected

          case Left(err) =>
            Result.failure.log(s"parse config failed with error: ${err.toString}")
        }

      }

    def testConfigReaderNonPosFloatInvalid: Property =
      for {
        n <- Gen.double(Range.linearFrac(0.0001d, Float.MaxValue)).map(_.toFloat).log("n")
      } yield {

        val confString =
          raw"""number = ${n.toString}"""

        val expected = NonPosFloat.from(n).leftMap { err =>
          s"The value ${n.toString} cannot be created as the expected type, ${typeTools.getTypeName[refined4s.types.numeric.NonPosFloat]}.Type, due to the following error: $err"
        }

        ConfigSource
          .string(confString)
          .load[NonPosFloatConfig] match {
          case Right(NonPosFloatConfig(actual)) =>
            Result.failure.log(s"It should have failed to parse the config but got $actual")

          case Left(ConfigReaderFailures(ConvertFailure(UserValidationFailed(err), _, _))) =>
            err.asLeft ==== expected

          case unexpected =>
            Result.failure.log(s"Unexpected result: ${unexpected.toString}")
        }

      }

    def testConfigWriterNonPosFloat: Property =
      for {
        n <- Gen.double(Range.linearFrac(0d, Float.MinValue)).map(_.toFloat).log("n")
      } yield {

        val input = NonPosFloat.unsafeFrom(n)

        val expected = ConfigWriter[Float].to(n)
        val actual   = ConfigWriter[NonPosFloat].to(input)

        actual ==== expected

      }

    final case class NonPosFloatConfig(number: NonPosFloat) derives ConfigReader

    ///

    def testConfigReaderNegDouble: Property =
      for {
        n <- Gen.double(Range.linearFrac(-0.000001d, Double.MinValue)).log("n")
      } yield {

        val confString =
          raw"""
               |number = ${n.toString}
               |""".stripMargin

        val expected = NegDouble.unsafeFrom(n)

        ConfigSource
          .string(confString)
          .load[NegDoubleConfig] match {
          case Right(NegDoubleConfig(actual)) =>
            actual ==== expected

          case Left(err) =>
            Result.failure.log(s"parse config failed with error: ${err.toString}")
        }

      }

    def testConfigReaderNegDoubleInvalid: Property =
      for {
        n <- Gen.double(Range.linearFrac(0d, Double.MaxValue)).log("n")
      } yield {

        val confString =
          raw"""number = ${n.toString}"""

        val expected = NegDouble.from(n).leftMap { err =>
          s"The value ${n.toString} cannot be created as the expected type, ${typeTools.getTypeName[refined4s.types.numeric.NegDouble]}.Type, due to the following error: $err"
        }

        ConfigSource
          .string(confString)
          .load[NegDoubleConfig] match {
          case Right(NegDoubleConfig(actual)) =>
            Result.failure.log(s"It should have failed to parse the config but got $actual")

          case Left(ConfigReaderFailures(ConvertFailure(UserValidationFailed(err), _, _))) =>
            err.asLeft ==== expected

          case unexpected =>
            Result.failure.log(s"Unexpected result: ${unexpected.toString}")
        }

      }

    def testConfigWriterNegDouble: Property =
      for {
        n <- Gen.double(Range.linearFrac(-0.000001d, Double.MinValue)).log("n")
      } yield {

        val input = NegDouble.unsafeFrom(n)

        val expected = ConfigWriter[Double].to(n)
        val actual   = ConfigWriter[NegDouble].to(input)

        actual ==== expected

      }

    final case class NegDoubleConfig(number: NegDouble) derives ConfigReader

    ///

    def testConfigReaderNonNegDouble: Property =
      for {
        n <- Gen.double(Range.linearFrac(0d, Double.MaxValue)).log("n")
      } yield {

        val confString =
          raw"""
               |number = ${n.toString}
               |""".stripMargin

        val expected = NonNegDouble.unsafeFrom(n)

        ConfigSource
          .string(confString)
          .load[NonNegDoubleConfig] match {
          case Right(NonNegDoubleConfig(actual)) =>
            actual ==== expected

          case Left(err) =>
            Result.failure.log(s"parse config failed with error: ${err.toString}")
        }

      }

    def testConfigReaderNonNegDoubleInvalid: Property =
      for {
        n <- Gen.double(Range.linearFrac(Double.MinValue, -0.000001d)).log("n")
      } yield {

        val confString =
          raw"""number = ${n.toString}"""

        val expected = NonNegDouble.from(n).leftMap { err =>
          s"The value ${n.toString} cannot be created as the expected type, ${typeTools.getTypeName[refined4s.types.numeric.NonNegDouble]}.Type, due to the following error: $err"
        }

        ConfigSource
          .string(confString)
          .load[NonNegDoubleConfig] match {
          case Right(NonNegDoubleConfig(actual)) =>
            Result.failure.log(s"It should have failed to parse the config but got $actual")

          case Left(ConfigReaderFailures(ConvertFailure(UserValidationFailed(err), _, _))) =>
            err.asLeft ==== expected

          case unexpected =>
            Result.failure.log(s"Unexpected result: ${unexpected.toString}")
        }

      }

    def testConfigWriterNonNegDouble: Property =
      for {
        n <- Gen.double(Range.linearFrac(0d, Double.MaxValue)).log("n")
      } yield {

        val input = NonNegDouble.unsafeFrom(n)

        val expected = ConfigWriter[Double].to(n)
        val actual   = ConfigWriter[NonNegDouble].to(input)

        actual ==== expected

      }

    final case class NonNegDoubleConfig(number: NonNegDouble) derives ConfigReader

    ///

    def testConfigReaderPosDouble: Property =
      for {
        n <- Gen.double(Range.linearFrac(0.000001d, Double.MaxValue)).log("n")
      } yield {

        val confString =
          raw"""
               |number = ${n.toString}
               |""".stripMargin

        val expected = PosDouble.unsafeFrom(n)

        ConfigSource
          .string(confString)
          .load[PosDoubleConfig] match {
          case Right(PosDoubleConfig(actual)) =>
            actual ==== expected

          case Left(err) =>
            Result.failure.log(s"parse config failed with error: ${err.toString}")
        }

      }

    def testConfigReaderPosDoubleInvalid: Property =
      for {
        n <- Gen.double(Range.linearFrac(Double.MinValue, 0d)).log("n")
      } yield {

        val confString =
          raw"""number = ${n.toString}"""

        val expected = PosDouble.from(n).leftMap { err =>
          s"The value ${n.toString} cannot be created as the expected type, ${typeTools.getTypeName[refined4s.types.numeric.PosDouble]}.Type, due to the following error: $err"
        }

        ConfigSource
          .string(confString)
          .load[PosDoubleConfig] match {
          case Right(PosDoubleConfig(actual)) =>
            Result.failure.log(s"It should have failed to parse the config but got $actual")

          case Left(ConfigReaderFailures(ConvertFailure(UserValidationFailed(err), _, _))) =>
            err.asLeft ==== expected

          case unexpected =>
            Result.failure.log(s"Unexpected result: ${unexpected.toString}")
        }

      }

    def testConfigWriterPosDouble: Property =
      for {
        n <- Gen.double(Range.linearFrac(0.000001d, Double.MaxValue)).log("n")
      } yield {

        val input = PosDouble.unsafeFrom(n)

        val expected = ConfigWriter[Double].to(n)
        val actual   = ConfigWriter[PosDouble].to(input)

        actual ==== expected

      }

    final case class PosDoubleConfig(number: PosDouble) derives ConfigReader

    ///

    def testConfigReaderNonPosDouble: Property =
      for {
        n <- Gen.double(Range.linearFrac(0d, Double.MinValue)).log("n")
      } yield {

        val confString =
          raw"""
               |number = ${n.toString}
               |""".stripMargin

        val expected = NonPosDouble.unsafeFrom(n)

        ConfigSource
          .string(confString)
          .load[NonPosDoubleConfig] match {
          case Right(NonPosDoubleConfig(actual)) =>
            actual ==== expected

          case Left(err) =>
            Result.failure.log(s"parse config failed with error: ${err.toString}")
        }

      }

    def testConfigReaderNonPosDoubleInvalid: Property =
      for {
        n <- Gen.double(Range.linearFrac(0.000001d, Double.MaxValue)).log("n")
      } yield {

        val confString =
          raw"""number = ${n.toString}"""

        val expected = NonPosDouble.from(n).leftMap { err =>
          s"The value ${n.toString} cannot be created as the expected type, ${typeTools.getTypeName[refined4s.types.numeric.NonPosDouble]}.Type, due to the following error: $err"
        }

        ConfigSource
          .string(confString)
          .load[NonPosDoubleConfig] match {
          case Right(NonPosDoubleConfig(actual)) =>
            Result.failure.log(s"It should have failed to parse the config but got $actual")

          case Left(ConfigReaderFailures(ConvertFailure(UserValidationFailed(err), _, _))) =>
            err.asLeft ==== expected

          case unexpected =>
            Result.failure.log(s"Unexpected result: ${unexpected.toString}")
        }

      }

    def testConfigWriterNonPosDouble: Property =
      for {
        n <- Gen.double(Range.linearFrac(0d, Double.MinValue)).log("n")
      } yield {

        val input = NonPosDouble.unsafeFrom(n)

        val expected = ConfigWriter[Double].to(n)
        val actual   = ConfigWriter[NonPosDouble].to(input)

        actual ==== expected

      }

    final case class NonPosDoubleConfig(number: NonPosDouble) derives ConfigReader

    ///

    def testConfigReaderNegBigInt: Property =
      for {
        n <- Gen.long(Range.linear(-1L, Long.MinValue)).map(BigInt(_)).log("n")
      } yield {

        val confString =
          raw"""
               |number = ${n.toString}
               |""".stripMargin

        val expected = NegBigInt.unsafeFrom(n)

        ConfigSource
          .string(confString)
          .load[NegBigIntConfig] match {
          case Right(NegBigIntConfig(actual)) =>
            actual ==== expected

          case Left(err) =>
            Result.failure.log(s"parse config failed with error: ${err.toString}")
        }

      }

    def testConfigReaderNegBigIntInvalid: Property =
      for {
        n <- Gen.long(Range.linear(0L, Long.MaxValue)).map(BigInt(_)).log("n")
      } yield {

        val confString =
          raw"""number = ${n.toString}"""

        val expected = NegBigInt.from(n).leftMap { err =>
          s"The value ${n.toString} cannot be created as the expected type, ${typeTools.getTypeName[refined4s.types.numeric.NegBigInt]}.Type, due to the following error: $err"
        }

        ConfigSource
          .string(confString)
          .load[NegBigIntConfig] match {
          case Right(NegBigIntConfig(actual)) =>
            Result.failure.log(s"It should have failed to parse the config but got $actual")

          case Left(ConfigReaderFailures(ConvertFailure(UserValidationFailed(err), _, _))) =>
            err.asLeft ==== expected

          case unexpected =>
            Result.failure.log(s"Unexpected result: ${unexpected.toString}")
        }

      }

    def testConfigWriterNegBigInt: Property =
      for {
        n <- Gen.long(Range.linear(-1L, Long.MinValue)).map(BigInt(_)).log("n")
      } yield {

        val input = NegBigInt.unsafeFrom(n)

        val expected = ConfigWriter[BigInt].to(n)
        val actual   = ConfigWriter[NegBigInt].to(input)

        actual ==== expected

      }

    final case class NegBigIntConfig(number: NegBigInt) derives ConfigReader

    ///

    def testConfigReaderNonNegBigInt: Property =
      for {
        n <- Gen.long(Range.linear(0L, Long.MaxValue)).map(BigInt(_)).log("n")
      } yield {

        val confString =
          raw"""
               |number = ${n.toString}
               |""".stripMargin

        val expected = NonNegBigInt.unsafeFrom(n)

        ConfigSource
          .string(confString)
          .load[NonNegBigIntConfig] match {
          case Right(NonNegBigIntConfig(actual)) =>
            actual ==== expected

          case Left(err) =>
            Result.failure.log(s"parse config failed with error: ${err.toString}")
        }

      }

    def testConfigReaderNonNegBigIntInvalid: Property =
      for {
        n <- Gen.long(Range.linear(Long.MinValue, -1L)).map(BigInt(_)).log("n")
      } yield {

        val confString =
          raw"""number = ${n.toString}"""

        val expected = NonNegBigInt.from(n).leftMap { err =>
          s"The value ${n.toString} cannot be created as the expected type, ${typeTools.getTypeName[refined4s.types.numeric.NonNegBigInt]}.Type, due to the following error: $err"
        }

        ConfigSource
          .string(confString)
          .load[NonNegBigIntConfig] match {
          case Right(NonNegBigIntConfig(actual)) =>
            Result.failure.log(s"It should have failed to parse the config but got $actual")

          case Left(ConfigReaderFailures(ConvertFailure(UserValidationFailed(err), _, _))) =>
            err.asLeft ==== expected

          case unexpected =>
            Result.failure.log(s"Unexpected result: ${unexpected.toString}")
        }

      }

    def testConfigWriterNonNegBigInt: Property =
      for {
        n <- Gen.long(Range.linear(0L, Long.MaxValue)).map(BigInt(_)).log("n")
      } yield {

        val input = NonNegBigInt.unsafeFrom(n)

        val expected = ConfigWriter[BigInt].to(n)
        val actual   = ConfigWriter[NonNegBigInt].to(input)

        actual ==== expected

      }

    final case class NonNegBigIntConfig(number: NonNegBigInt) derives ConfigReader

    ///

    def testConfigReaderPosBigInt: Property =
      for {
        n <- Gen.long(Range.linear(1L, Long.MaxValue)).map(BigInt(_)).log("n")
      } yield {

        val confString =
          raw"""
               |number = ${n.toString}
               |""".stripMargin

        val expected = PosBigInt.unsafeFrom(n)

        ConfigSource
          .string(confString)
          .load[PosBigIntConfig] match {
          case Right(PosBigIntConfig(actual)) =>
            actual ==== expected

          case Left(err) =>
            Result.failure.log(s"parse config failed with error: ${err.toString}")
        }

      }

    def testConfigReaderPosBigIntInvalid: Property =
      for {
        n <- Gen.long(Range.linear(Long.MinValue, 0L)).map(BigInt(_)).log("n")
      } yield {

        val confString =
          raw"""number = ${n.toString}"""

        val expected = PosBigInt.from(n).leftMap { err =>
          s"The value ${n.toString} cannot be created as the expected type, ${typeTools.getTypeName[refined4s.types.numeric.PosBigInt]}.Type, due to the following error: $err"
        }

        ConfigSource
          .string(confString)
          .load[PosBigIntConfig] match {
          case Right(PosBigIntConfig(actual)) =>
            Result.failure.log(s"It should have failed to parse the config but got $actual")

          case Left(ConfigReaderFailures(ConvertFailure(UserValidationFailed(err), _, _))) =>
            err.asLeft ==== expected

          case unexpected =>
            Result.failure.log(s"Unexpected result: ${unexpected.toString}")
        }

      }

    def testConfigWriterPosBigInt: Property =
      for {
        n <- Gen.long(Range.linear(1L, Long.MaxValue)).map(BigInt(_)).log("n")
      } yield {

        val input = PosBigInt.unsafeFrom(n)

        val expected = ConfigWriter[BigInt].to(n)
        val actual   = ConfigWriter[PosBigInt].to(input)

        actual ==== expected

      }

    final case class PosBigIntConfig(number: PosBigInt) derives ConfigReader

    ///

    def testConfigReaderNonPosBigInt: Property =
      for {
        n <- Gen.long(Range.linear(0L, Long.MinValue)).map(BigInt(_)).log("n")
      } yield {

        val confString =
          raw"""
               |number = ${n.toString}
               |""".stripMargin

        val expected = NonPosBigInt.unsafeFrom(n)

        ConfigSource
          .string(confString)
          .load[NonPosBigIntConfig] match {
          case Right(NonPosBigIntConfig(actual)) =>
            actual ==== expected

          case Left(err) =>
            Result.failure.log(s"parse config failed with error: ${err.toString}")
        }

      }

    def testConfigReaderNonPosBigIntInvalid: Property =
      for {
        n <- Gen.long(Range.linear(1L, Long.MaxValue)).map(BigInt(_)).log("n")
      } yield {

        val confString =
          raw"""number = ${n.toString}"""

        val expected = NonPosBigInt.from(n).leftMap { err =>
          s"The value ${n.toString} cannot be created as the expected type, ${typeTools.getTypeName[refined4s.types.numeric.NonPosBigInt]}.Type, due to the following error: $err"
        }

        ConfigSource
          .string(confString)
          .load[NonPosBigIntConfig] match {
          case Right(NonPosBigIntConfig(actual)) =>
            Result.failure.log(s"It should have failed to parse the config but got $actual")

          case Left(ConfigReaderFailures(ConvertFailure(UserValidationFailed(err), _, _))) =>
            err.asLeft ==== expected

          case unexpected =>
            Result.failure.log(s"Unexpected result: ${unexpected.toString}")
        }

      }

    def testConfigWriterNonPosBigInt: Property =
      for {
        n <- Gen.long(Range.linear(0L, Long.MinValue)).map(BigInt(_)).log("n")
      } yield {

        val input = NonPosBigInt.unsafeFrom(n)

        val expected = ConfigWriter[BigInt].to(n)
        val actual   = ConfigWriter[NonPosBigInt].to(input)

        actual ==== expected

      }

    final case class NonPosBigIntConfig(number: NonPosBigInt) derives ConfigReader

    ///

    def testConfigReaderNegBigDecimal: Property =
      for {
        n <- Gen.double(Range.linearFrac(-0.0000001d, Double.MinValue)).map(BigDecimal(_)).log("n")
      } yield {

        val confString =
          raw"""
               |number = ${n.toString}
               |""".stripMargin

        val expected = NegBigDecimal.unsafeFrom(n)

        ConfigSource
          .string(confString)
          .load[NegBigDecimalConfig] match {
          case Right(NegBigDecimalConfig(actual)) =>
            actual ==== expected

          case Left(err) =>
            Result.failure.log(s"parse config failed with error: ${err.toString}")
        }

      }

    def testConfigReaderNegBigDecimalInvalid: Property =
      for {
        n <- Gen.double(Range.linearFrac(0, Double.MaxValue)).map(BigDecimal(_)).log("n")
      } yield {

        val confString =
          raw"""number = ${n.toString}"""

        val expected = NegBigDecimal.from(n).leftMap { err =>
          s"The value ${n.toString} cannot be created as the expected type, ${typeTools.getTypeName[refined4s.types.numeric.NegBigDecimal]}.Type, due to the following error: $err"
        }

        ConfigSource
          .string(confString)
          .load[NegBigDecimalConfig] match {
          case Right(NegBigDecimalConfig(actual)) =>
            Result.failure.log(s"It should have failed to parse the config but got $actual")

          case Left(ConfigReaderFailures(ConvertFailure(UserValidationFailed(err), _, _))) =>
            err.asLeft ==== expected

          case unexpected =>
            Result.failure.log(s"Unexpected result: ${unexpected.toString}")
        }

      }

    def testConfigWriterNegBigDecimal: Property =
      for {
        n <- Gen.double(Range.linearFrac(-0.0000001d, Double.MinValue)).map(BigDecimal(_)).log("n")
      } yield {

        val input = NegBigDecimal.unsafeFrom(n)

        val expected = ConfigWriter[BigDecimal].to(n)
        val actual   = ConfigWriter[NegBigDecimal].to(input)

        actual ==== expected

      }

    final case class NegBigDecimalConfig(number: NegBigDecimal) derives ConfigReader

    ///

    def testConfigReaderNonNegBigDecimal: Property =
      for {
        n <- Gen.double(Range.linearFrac(0d, Double.MaxValue)).map(BigDecimal(_)).log("n")
      } yield {

        val confString =
          raw"""
               |number = ${n.toString}
               |""".stripMargin

        val expected = NonNegBigDecimal.unsafeFrom(n)

        ConfigSource
          .string(confString)
          .load[NonNegBigDecimalConfig] match {
          case Right(NonNegBigDecimalConfig(actual)) =>
            actual ==== expected

          case Left(err) =>
            Result.failure.log(s"parse config failed with error: ${err.toString}")
        }

      }

    def testConfigReaderNonNegBigDecimalInvalid: Property =
      for {
        n <- Gen.double(Range.linearFrac(Double.MinValue, -0.0000001d)).map(BigDecimal(_)).log("n")
      } yield {

        val confString =
          raw"""number = ${n.toString}"""

        val expected = NonNegBigDecimal.from(n).leftMap { err =>
          s"The value ${n.toString} cannot be created as the expected type, ${typeTools
              .getTypeName[refined4s.types.numeric.NonNegBigDecimal]}.Type, due to the following error: $err"
        }

        ConfigSource
          .string(confString)
          .load[NonNegBigDecimalConfig] match {
          case Right(NonNegBigDecimalConfig(actual)) =>
            Result.failure.log(s"It should have failed to parse the config but got $actual")

          case Left(ConfigReaderFailures(ConvertFailure(UserValidationFailed(err), _, _))) =>
            err.asLeft ==== expected

          case unexpected =>
            Result.failure.log(s"Unexpected result: ${unexpected.toString}")
        }

      }

    def testConfigWriterNonNegBigDecimal: Property =
      for {
        n <- Gen.double(Range.linearFrac(0d, Double.MaxValue)).map(BigDecimal(_)).log("n")
      } yield {

        val input = NonNegBigDecimal.unsafeFrom(n)

        val expected = ConfigWriter[BigDecimal].to(n)
        val actual   = ConfigWriter[NonNegBigDecimal].to(input)

        actual ==== expected

      }

    final case class NonNegBigDecimalConfig(number: NonNegBigDecimal) derives ConfigReader

    ///

    def testConfigReaderPosBigDecimal: Property =
      for {
        n <- Gen.double(Range.linearFrac(0.0000001d, Double.MaxValue)).map(BigDecimal(_)).log("n")
      } yield {

        val confString =
          raw"""
               |number = ${n.toString}
               |""".stripMargin

        val expected = PosBigDecimal.unsafeFrom(n)

        ConfigSource
          .string(confString)
          .load[PosBigDecimalConfig] match {
          case Right(PosBigDecimalConfig(actual)) =>
            actual ==== expected

          case Left(err) =>
            Result.failure.log(s"parse config failed with error: ${err.toString}")
        }

      }

    def testConfigReaderPosBigDecimalInvalid: Property =
      for {
        n <- Gen.double(Range.linearFrac(Double.MinValue, 0d)).map(BigDecimal(_)).log("n")
      } yield {

        val confString =
          raw"""number = ${n.toString}"""

        val expected = PosBigDecimal.from(n).leftMap { err =>
          s"The value ${n.toString} cannot be created as the expected type, ${typeTools.getTypeName[refined4s.types.numeric.PosBigDecimal]}.Type, due to the following error: $err"
        }

        ConfigSource
          .string(confString)
          .load[PosBigDecimalConfig] match {
          case Right(PosBigDecimalConfig(actual)) =>
            Result.failure.log(s"It should have failed to parse the config but got $actual")

          case Left(ConfigReaderFailures(ConvertFailure(UserValidationFailed(err), _, _))) =>
            err.asLeft ==== expected

          case unexpected =>
            Result.failure.log(s"Unexpected result: ${unexpected.toString}")
        }

      }

    def testConfigWriterPosBigDecimal: Property =
      for {
        n <- Gen.double(Range.linearFrac(0.0000001d, Double.MaxValue)).map(BigDecimal(_)).log("n")
      } yield {

        val input = PosBigDecimal.unsafeFrom(n)

        val expected = ConfigWriter[BigDecimal].to(n)
        val actual   = ConfigWriter[PosBigDecimal].to(input)

        actual ==== expected

      }

    final case class PosBigDecimalConfig(number: PosBigDecimal) derives ConfigReader

    ///

    def testConfigReaderNonPosBigDecimal: Property =
      for {
        n <- Gen.double(Range.linearFrac(0d, Double.MinValue)).map(BigDecimal(_)).log("n")
      } yield {

        val confString =
          raw"""
               |number = ${n.toString}
               |""".stripMargin

        val expected = NonPosBigDecimal.unsafeFrom(n)

        ConfigSource
          .string(confString)
          .load[NonPosBigDecimalConfig] match {
          case Right(NonPosBigDecimalConfig(actual)) =>
            actual ==== expected

          case Left(err) =>
            Result.failure.log(s"parse config failed with error: ${err.toString}")
        }

      }

    def testConfigReaderNonPosBigDecimalInvalid: Property =
      for {
        n <- Gen.double(Range.linearFrac(0.0000001d, Double.MaxValue)).map(BigDecimal(_)).log("n")
      } yield {

        val confString =
          raw"""number = ${n.toString}"""

        val expected = NonPosBigDecimal.from(n).leftMap { err =>
          s"The value ${n.toString} cannot be created as the expected type, ${typeTools
              .getTypeName[refined4s.types.numeric.NonPosBigDecimal]}.Type, due to the following error: $err"
        }

        ConfigSource
          .string(confString)
          .load[NonPosBigDecimalConfig] match {
          case Right(NonPosBigDecimalConfig(actual)) =>
            Result.failure.log(s"It should have failed to parse the config but got $actual")

          case Left(ConfigReaderFailures(ConvertFailure(UserValidationFailed(err), _, _))) =>
            err.asLeft ==== expected

          case unexpected =>
            Result.failure.log(s"Unexpected result: ${unexpected.toString}")
        }

      }

    def testConfigWriterNonPosBigDecimal: Property =
      for {
        n <- Gen.double(Range.linearFrac(0d, Double.MinValue)).map(BigDecimal(_)).log("n")
      } yield {

        val input = NonPosBigDecimal.unsafeFrom(n)

        val expected = ConfigWriter[BigDecimal].to(n)
        val actual   = ConfigWriter[NonPosBigDecimal].to(input)

        actual ==== expected

      }

    final case class NonPosBigDecimalConfig(number: NonPosBigDecimal) derives ConfigReader

    ///

    def testConfigReaderNonEmptyString: Property =
      for {
        s <- Gen.string(Gen.alphaNum, Range.linear(1, 10)).log("s")
      } yield {

        val confString =
          raw"""
               |name = "$s"
               |""".stripMargin

        val expected = NonEmptyString.unsafeFrom(s)

        ConfigSource
          .string(confString)
          .load[NonEmptyStringConfig] match {
          case Right(NonEmptyStringConfig(actual)) =>
            actual ==== expected

          case Left(err) =>
            Result.failure.log(s"parse config failed with error: ${err.toString}")
        }

      }

    def testConfigReaderNonEmptyStringInvalid: Result = {

      val confString = """name = """""

      val expected = NonEmptyString.from("").leftMap { err =>
        s"The value  cannot be created as the expected type, ${typeTools.getTypeName[refined4s.types.strings.NonEmptyString]}.Type, due to the following error: $err"
      }

      ConfigSource
        .string(confString)
        .load[NonEmptyStringConfig] match {
        case Right(NonEmptyStringConfig(actual)) =>
          Result.failure.log(s"It should have failed to parse the config but got $actual")

        case Left(ConfigReaderFailures(ConvertFailure(UserValidationFailed(err), _, _))) =>
          err.asLeft ==== expected

        case unexpected =>
          Result.failure.log(s"Unexpected result: ${unexpected.toString}")
      }

    }

    def testConfigWriterNonEmptyString: Property =
      for {
        s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
      } yield {

        val input = NonEmptyString.unsafeFrom(s)

        val expected = ConfigWriter[String].to(s)
        val actual   = ConfigWriter[NonEmptyString].to(input)

        actual ==== expected

      }

    final case class NonEmptyStringConfig(name: NonEmptyString) derives ConfigReader

    ///

    def testConfigReaderNonBlankString: Property =
      for {
        nonWhitespaceString <- Gen
                                 .string(
                                   hedgehog.extra.Gens.genCharByRange(hedgehog.extra.common.NonWhitespaceCharRange.drop(2)),
                                   Range.linear(1, 10),
                                 )
                                 .log("nonWhitespaceString")
        whitespaceString    <- Gen
                                 .string(
                                   /*
                                     strings.WhitespaceCharRange can be used here because of
                                     ```
                                     ConfigReaderFailures(CannotParse(Expecting a value but got wrong token: 'tab'
                                     (JSON does not allow unescaped tab in quoted strings, use a backslash escape)
                                     (if you intended 'tab' (JSON does not allow unescaped tab in quoted strings, use a backslash escape)
                                     to be part of a key or string value, try enclosing the key or value in double quotes,
                                     or you may be able to rename the file .properties rather than .conf),Some(ConfigOrigin(String))))
                                     ```
                                     It happens to '\t' (tab), '\n' (newline), '\r' (control character 0xd), '\b' (control character 0x8), and possibly more.
                                 */
                                   Gen.choice1(
                                     Gen.char(32, 32),
                                     Gen.char(8192, 8198),
                                     Gen.char(8200, 8202),
                                     Gen.char(8232, 8233),
                                     Gen.char(8287, 8287),
                                     Gen.char(12288, 12288),
                                   ),
                                   Range.linear(1, 10),
                                 )
                                 .log("whitespaceString")

        s <- Gen.constant(scala.util.Random.shuffle((nonWhitespaceString + whitespaceString).toList).mkString).log("s")
      } yield {

        val confString =
          raw"""
               |name = "$s"
               |""".stripMargin

        val expected = NonBlankString.unsafeFrom(s)

        ConfigSource
          .string(confString)
          .load[NonBlankStringConfig] match {
          case Right(NonBlankStringConfig(actual)) =>
            actual ==== expected

          case Left(err) =>
            Result.failure.log(s"parse config failed with error: ${err.toString}")
        }

      }

    def testConfigReaderNonBlankStringInvalid: Property = for {
      s <-
        Gen
          .frequency1(
            5  -> Gen.constant(""),
            /* strings.WhitespaceCharRange can be used here because of
               ```
               ConfigReaderFailures(CannotParse(Expecting a value but got wrong token: 'tab'
               (JSON does not allow unescaped tab in quoted strings, use a backslash escape)
               (if you intended 'tab' (JSON does not allow unescaped tab in quoted strings, use a backslash escape)
               to be part of a key or string value, try enclosing the key or value in double quotes,
               or you may be able to rename the file .properties rather than .conf),Some(ConfigOrigin(String))))
               ```
               It happens to '\t' (tab), '\n' (newline), '\r' (control character 0xd), '\b' (control character 0x8), and possibly more.
             */
            95 -> Gen.string(
              Gen.choice1(
                Gen.char(32, 32),
                Gen.char(8192, 8198),
                Gen.char(8200, 8202),
                Gen.char(8232, 8233),
                Gen.char(8287, 8287),
                Gen.char(12288, 12288),
              ),
              Range.linear(1, 10),
            ),
          )
          .log("s")
    } yield {

      val confString = s"""name = "$s""""

      val expected = NonBlankString.from(s).leftMap { err =>
        s"The value $s cannot be created as the expected type, ${typeTools.getTypeName[refined4s.types.strings.NonBlankString]}.Type, due to the following error: $err"
      }

      ConfigSource
        .string(confString)
        .load[NonBlankStringConfig] match {
        case Right(NonBlankStringConfig(actual)) =>
          Result.failure.log(s"It should have failed to parse the config but got $actual")

        case Left(ConfigReaderFailures(ConvertFailure(UserValidationFailed(err), _, _))) =>
          err.asLeft ==== expected

        case unexpected =>
          Result.failure.log(s"Unexpected result: ${unexpected.toString}")
      }

    }

    def testConfigWriterNonBlankString: Property =
      for {
        nonWhitespaceString <- Gen
                                 .string(
                                   hedgehog.extra.Gens.genCharByRange(hedgehog.extra.common.NonWhitespaceCharRange.drop(2)),
                                   Range.linear(1, 10),
                                 )
                                 .log("nonWhitespaceString")
        whitespaceString    <- Gen
                                 .string(
                                   hedgehog.extra.Gens.genCharByRange(refined4s.types.strings.WhitespaceCharRange),
                                   Range.linear(1, 10),
                                 )
                                 .log("whitespaceString")

        s <- Gen.constant(scala.util.Random.shuffle((nonWhitespaceString + whitespaceString).toList).mkString).log("s")
      } yield {

        val input = NonBlankString.unsafeFrom(s)

        val expected = ConfigWriter[String].to(s)
        val actual   = ConfigWriter[NonBlankString].to(input)

        actual ==== expected

      }

    final case class NonBlankStringConfig(name: NonBlankString) derives ConfigReader

    ///

    @SuppressWarnings(Array("org.wartremover.warts.ToString"))
    def testConfigReaderUuid: Property =
      for {
        uuid <- Gen.constant(UUID.randomUUID()).log("uuid")
      } yield {

        val s = uuid.toString

        val confString =
          raw"""
               |id = "$s"
               |""".stripMargin

        val expected = Uuid(uuid)

        ConfigSource
          .string(confString)
          .load[UuidConfig] match {
          case Right(UuidConfig(actual)) =>
            actual ==== expected

          case Left(err) =>
            Result.failure.log(s"parse config failed with error: ${err.toString}")
        }

      }

    def testConfigReaderUuidInvalid: Property =
      for {
        s <- Gen.string(Gen.alphaNum, Range.linear(0, 10)).log("s")
      } yield {

        val confString = s"""id = "$s""""

        val expected = Uuid.from(s).leftMap { err =>
          s"The value $s cannot be created as the expected type, ${typeTools.getTypeName[refined4s.types.strings.Uuid]}.Type, due to the following error: $err"
        }

        ConfigSource
          .string(confString)
          .load[UuidConfig] match {
          case Right(UuidConfig(actual)) =>
            Result.failure.log(s"It should have failed to parse the config but got $actual")

          case Left(ConfigReaderFailures(ConvertFailure(UserValidationFailed(err), _, _))) =>
            err.asLeft ==== expected

          case unexpected =>
            Result.failure.log(s"Unexpected result: ${unexpected.toString}")
        }

      }

    @SuppressWarnings(Array("org.wartremover.warts.ToString"))
    def testConfigWriterUuid: Property =
      for {
        uuid <- Gen.constant(UUID.randomUUID()).log("uuid")
      } yield {

        val input = Uuid(uuid)

        val expected = ConfigWriter[String].to(uuid.toString)
        val actual   = ConfigWriter[Uuid].to(input)

        actual ==== expected

      }

    final case class UuidConfig(id: Uuid) derives ConfigReader

    ///

    @SuppressWarnings(Array("org.wartremover.warts.ToString"))
    def testConfigReaderUri: Property =
      for {
        uri <- networkGens.genUriString.log("uri")
      } yield {

        val confString =
          raw"""
               |uri = "$uri"
               |""".stripMargin

        val expected = Uri.unsafeFrom(uri)

        ConfigSource
          .string(confString)
          .load[UriConfig] match {
          case Right(UriConfig(actual)) =>
            actual ==== expected

          case Left(err) =>
            Result.failure.log(s"parse String config failed with error: ${err.toString}")
        }

      }

    @SuppressWarnings(Array("org.wartremover.warts.ToString"))
    def testConfigReaderUriInvalid: Property =
      for {
        uri <- Gen.string(Gen.element1('%', '^', '<', '>', '[', ']', '`', '{', '}'), Range.linear(1, 5)).log("uri")
      } yield {

        val confString = raw"""uri = "$uri""""

        val expected = Uri.from(uri).leftMap { err =>
          s"The value $uri cannot be created as the expected type, ${typeTools.getTypeName[refined4s.types.network.Uri]}.Type, due to the following error: $err"
        }

        ConfigSource
          .string(confString)
          .load[UriConfig] match {
          case Right(UriConfig(actual)) =>
            Result.failure.log(s"It should have failed to parse the config but got $actual")

          case Left(ConfigReaderFailures(ConvertFailure(UserValidationFailed(err), _, _))) =>
            err.asLeft ==== expected

          case unexpected =>
            Result.failure.log(s"Unexpected result: ${unexpected.toString}")
        }

      }

    @SuppressWarnings(Array("org.wartremover.warts.ToString"))
    def testConfigWriterUri: Property =
      for {
        uri <- networkGens.genUriString.log("uri")
      } yield {

        val input  = Uri.unsafeFrom(uri)
        val actual = ConfigWriter[Uri].to(input)

        val expected = ConfigWriter[String].to(uri)

        actual ==== expected

      }

    final case class UriConfig(uri: Uri) derives ConfigReader

    //

    @SuppressWarnings(Array("org.wartremover.warts.ToString"))
    def testConfigReaderUrl: Property =
      for {
        url <- networkGens.genUrlString.log("url")
      } yield {

        val confString =
          raw"""
               |url = "$url"
               |""".stripMargin

        val expected = Url.unsafeFrom(url)

        ConfigSource
          .string(confString)
          .load[UrlConfig] match {
          case Right(UrlConfig(actual)) =>
            actual ==== expected

          case Left(err) =>
            Result.failure.log(s"parse String config failed with error: ${err.toString}")
        }

      }

    @SuppressWarnings(Array("org.wartremover.warts.ToString"))
    def testConfigReaderUrlInvalid: Property =
      for {
        url <- Gen.string(Gen.element1('%', '^', '<', '>', '[', ']', '`', '{', '}'), Range.linear(1, 5)).log("url")
      } yield {

        val confString = raw"""url = "$url""""

        val expected = Url.from(url).leftMap { err =>
          s"The value $url cannot be created as the expected type, ${typeTools.getTypeName[refined4s.types.network.Url]}.Type, due to the following error: $err"
        }

        ConfigSource
          .string(confString)
          .load[UrlConfig] match {
          case Right(UrlConfig(actual)) =>
            Result.failure.log(s"It should have failed to parse the config but got $actual")

          case Left(ConfigReaderFailures(ConvertFailure(UserValidationFailed(err), _, _))) =>
            err.asLeft ==== expected

          case unexpected =>
            Result.failure.log(s"Unexpected result: ${unexpected.toString}")
        }

      }

    @SuppressWarnings(Array("org.wartremover.warts.ToString"))
    def testConfigWriterUrl: Property =
      for {
        url <- networkGens.genUrlString.log("url")
      } yield {

        val input  = Url.unsafeFrom(url)
        val actual = ConfigWriter[Url].to(input)

        val expected = ConfigWriter[String].to(url)

        actual ==== expected

      }

    final case class UrlConfig(url: Url) derives ConfigReader

    /* network ports */

    def testConfigReaderPortNumber: Property =
      for {
        portNumber <- networkGens.genPortNumberInt.log("portNumber")
      } yield {

        val confString =
          raw"""
               |port = $portNumber
               |""".stripMargin

        val expected = PortNumber.unsafeFrom(portNumber)

        ConfigSource
          .string(confString)
          .load[PortNumberConfig] match {
          case Right(PortNumberConfig(actual)) =>
            actual ==== expected

          case Left(err) =>
            Result.failure.log(s"parse config failed with error: ${err.toString}")
        }

      }

    def testConfigReaderPortNumberInvalid: Property =
      for {
        portNumber <- networkGens.genInvalidPortNumberInt.log("portNumber")
      } yield {

        val confString = raw"""port = $portNumber"""

        val expected = PortNumber.from(portNumber).leftMap { err =>
          s"The value $portNumber cannot be created as the expected type, ${typeTools.getTypeName[refined4s.types.network.PortNumber]}.Type, due to the following error: $err"
        }

        ConfigSource
          .string(confString)
          .load[PortNumberConfig] match {
          case Right(PortNumberConfig(actual)) =>
            Result.failure.log(s"It should have failed to parse the config but got $actual")

          case Left(ConfigReaderFailures(ConvertFailure(UserValidationFailed(err), _, _))) =>
            err.asLeft ==== expected

          case unexpected =>
            Result.failure.log(s"Unexpected result: ${unexpected.toString}")
        }

      }

    def testConfigWriterPortNumber: Property =
      for {
        portNumber <- networkGens.genPortNumberInt.log("portNumber")
      } yield {

        val input = PortNumber.unsafeFrom(portNumber)

        val expected = ConfigWriter[Int].to(portNumber)
        val actual   = ConfigWriter[PortNumber].to(input)

        actual ==== expected

      }

    final case class PortNumberConfig(port: PortNumber) derives ConfigReader

    ///

    def testConfigReaderSystemPortNumber: Property =
      for {
        systemPortNumber <- networkGens.genSystemPortNumberInt.log("systemPortNumber")
      } yield {

        val confString =
          raw"""
               |port = $systemPortNumber
               |""".stripMargin

        val expected = SystemPortNumber.unsafeFrom(systemPortNumber)

        ConfigSource
          .string(confString)
          .load[SystemPortNumberConfig] match {
          case Right(SystemPortNumberConfig(actual)) =>
            actual ==== expected

          case Left(err) =>
            Result.failure.log(s"parse config failed with error: ${err.toString}")
        }

      }

    def testConfigReaderSystemPortNumberInvalid: Property =
      for {
        systemPortNumber <- networkGens.genInvalidSystemPortNumberInt.log("systemPortNumber")
      } yield {

        val confString = raw"""port = $systemPortNumber"""

        val expected = SystemPortNumber.from(systemPortNumber).leftMap { err =>
          s"The value $systemPortNumber cannot be created as the expected type, ${typeTools
              .getTypeName[refined4s.types.network.SystemPortNumber]}.Type, due to the following error: $err"
        }

        ConfigSource
          .string(confString)
          .load[SystemPortNumberConfig] match {
          case Right(SystemPortNumberConfig(actual)) =>
            Result.failure.log(s"It should have failed to parse the config but got $actual")

          case Left(ConfigReaderFailures(ConvertFailure(UserValidationFailed(err), _, _))) =>
            err.asLeft ==== expected

          case unexpected =>
            Result.failure.log(s"Unexpected result: ${unexpected.toString}")
        }

      }

    def testConfigWriterSystemPortNumber: Property =
      for {
        systemPortNumber <- networkGens.genSystemPortNumberInt.log("systemPortNumber")
      } yield {

        val input = SystemPortNumber.unsafeFrom(systemPortNumber)

        val expected = ConfigWriter[Int].to(systemPortNumber)
        val actual   = ConfigWriter[SystemPortNumber].to(input)

        actual ==== expected

      }

    final case class SystemPortNumberConfig(port: SystemPortNumber) derives ConfigReader

    ///

    def testConfigReaderNonSystemPortNumber: Property =
      for {
        nonSystemPortNumber <- networkGens.genNonSystemPortNumberInt.log("nonSystemPortNumber")
      } yield {

        val confString =
          raw"""
               |port = $nonSystemPortNumber
               |""".stripMargin

        val expected = NonSystemPortNumber.unsafeFrom(nonSystemPortNumber)

        ConfigSource
          .string(confString)
          .load[NonSystemPortNumberConfig] match {
          case Right(NonSystemPortNumberConfig(actual)) =>
            actual ==== expected

          case Left(err) =>
            Result.failure.log(s"parse config failed with error: ${err.toString}")
        }

      }

    def testConfigReaderNonSystemPortNumberInvalid: Property =
      for {
        nonSystemPortNumber <- networkGens.genInvalidNonSystemPortNumberInt.log("nonSystemPortNumber")
      } yield {

        val confString = raw"""port = $nonSystemPortNumber"""

        val expected = NonSystemPortNumber.from(nonSystemPortNumber).leftMap { err =>
          s"The value $nonSystemPortNumber cannot be created as the expected type, ${typeTools
              .getTypeName[refined4s.types.network.NonSystemPortNumber]}.Type, due to the following error: $err"
        }

        ConfigSource
          .string(confString)
          .load[NonSystemPortNumberConfig] match {
          case Right(NonSystemPortNumberConfig(actual)) =>
            Result.failure.log(s"It should have failed to parse the config but got $actual")

          case Left(ConfigReaderFailures(ConvertFailure(UserValidationFailed(err), _, _))) =>
            err.asLeft ==== expected

          case unexpected =>
            Result.failure.log(s"Unexpected result: ${unexpected.toString}")
        }

      }

    def testConfigWriterNonSystemPortNumber: Property =
      for {
        nonSystemPortNumber <- networkGens.genNonSystemPortNumberInt.log("nonSystemPortNumber")
      } yield {

        val input = NonSystemPortNumber.unsafeFrom(nonSystemPortNumber)

        val expected = ConfigWriter[Int].to(nonSystemPortNumber)
        val actual   = ConfigWriter[NonSystemPortNumber].to(input)

        actual ==== expected

      }

    final case class NonSystemPortNumberConfig(port: NonSystemPortNumber) derives ConfigReader

    ///

    def testConfigReaderUserPortNumber: Property =
      for {
        userPortNumber <- networkGens.genUserPortNumberInt.log("userPortNumber")
      } yield {

        val confString =
          raw"""
               |port = $userPortNumber
               |""".stripMargin

        val expected = UserPortNumber.unsafeFrom(userPortNumber)

        ConfigSource
          .string(confString)
          .load[UserPortNumberConfig] match {
          case Right(UserPortNumberConfig(actual)) =>
            actual ==== expected

          case Left(err) =>
            Result.failure.log(s"parse config failed with error: ${err.toString}")
        }

      }

    def testConfigReaderUserPortNumberInvalid: Property =
      for {
        userPortNumber <- networkGens.genInvalidUserPortNumberInt.log("userPortNumber")
      } yield {

        val confString = raw"""port = $userPortNumber"""

        val expected = UserPortNumber.from(userPortNumber).leftMap { err =>
          s"The value $userPortNumber cannot be created as the expected type, ${typeTools
              .getTypeName[refined4s.types.network.UserPortNumber]}.Type, due to the following error: $err"
        }

        ConfigSource
          .string(confString)
          .load[UserPortNumberConfig] match {
          case Right(UserPortNumberConfig(actual)) =>
            Result.failure.log(s"It should have failed to parse the config but got $actual")

          case Left(ConfigReaderFailures(ConvertFailure(UserValidationFailed(err), _, _))) =>
            err.asLeft ==== expected

          case unexpected =>
            Result.failure.log(s"Unexpected result: ${unexpected.toString}")
        }

      }

    def testConfigWriterUserPortNumber: Property =
      for {
        userPortNumber <- networkGens.genUserPortNumberInt.log("userPortNumber")
      } yield {

        val input = UserPortNumber.unsafeFrom(userPortNumber)

        val expected = ConfigWriter[Int].to(userPortNumber)
        val actual   = ConfigWriter[UserPortNumber].to(input)

        actual ==== expected

      }

    final case class UserPortNumberConfig(port: UserPortNumber) derives ConfigReader

    ///

    def testConfigReaderDynamicPortNumber: Property =
      for {
        dynamicPortNumber <- networkGens.genDynamicPortNumberInt.log("dynamicPortNumber")
      } yield {

        val confString =
          raw"""
               |port = $dynamicPortNumber
               |""".stripMargin

        val expected = DynamicPortNumber.unsafeFrom(dynamicPortNumber)

        ConfigSource
          .string(confString)
          .load[DynamicPortNumberConfig] match {
          case Right(DynamicPortNumberConfig(actual)) =>
            actual ==== expected

          case Left(err) =>
            Result.failure.log(s"parse config failed with error: ${err.toString}")
        }

      }

    def testConfigReaderDynamicPortNumberInvalid: Property =
      for {
        dynamicPortNumber <- networkGens.genInvalidDynamicPortNumberInt.log("dynamicPortNumber")
      } yield {

        val confString = raw"""port = $dynamicPortNumber"""

        val expected = DynamicPortNumber.from(dynamicPortNumber).leftMap { err =>
          s"The value $dynamicPortNumber cannot be created as the expected type, ${typeTools
              .getTypeName[refined4s.types.network.DynamicPortNumber]}.Type, due to the following error: $err"
        }

        ConfigSource
          .string(confString)
          .load[DynamicPortNumberConfig] match {
          case Right(DynamicPortNumberConfig(actual)) =>
            Result.failure.log(s"It should have failed to parse the config but got $actual")

          case Left(ConfigReaderFailures(ConvertFailure(UserValidationFailed(err), _, _))) =>
            err.asLeft ==== expected

          case unexpected =>
            Result.failure.log(s"Unexpected result: ${unexpected.toString}")
        }

      }

    def testConfigWriterDynamicPortNumber: Property =
      for {
        dynamicPortNumber <- networkGens.genDynamicPortNumberInt.log("dynamicPortNumber")
      } yield {

        val input = DynamicPortNumber.unsafeFrom(dynamicPortNumber)

        val expected = ConfigWriter[Int].to(dynamicPortNumber)
        val actual   = ConfigWriter[DynamicPortNumber].to(input)

        actual ==== expected

      }

    final case class DynamicPortNumberConfig(port: DynamicPortNumber) derives ConfigReader

  }

  object CustomTypeSpec {
    def tests: List[Test] = List(
      property("test ConfigReader[Newtype], ConfigReader[Refined] and ConfigReader[InlinedRefined] all together", testConfigReaderAll),
      property("test ConfigReader[Refined] with invalid value", testConfigReaderInvalid),
      property("test ConfigWriter[Newtype], ConfigWriter[Refined] and ConfigWriter[InlinedRefined] all together", testConfigWriterAll),
    )

    @SuppressWarnings(Array("org.wartremover.warts.ToString"))
    def testConfigReaderAll: Property =
      for {
        id           <- Gen.long(Range.linear(1L, Long.MaxValue)).log("id")
        uri          <- networkGens.genUriStringWithoutPath.log("uri")
        endpointPath <- Gen.string(Gen.alphaNum, Range.linear(1, 7)).list(Range.linear(1, 5)).map(_.mkString("/")).log("endpointPath")
        additionalId <- Gen.long(Range.linear(1L, Long.MaxValue)).log("additionalId")
      } yield {

        val confString =
          raw"""api {
               |  id = ${id.toString}
               |  base-uri = "$uri"
               |  endpoint-path = "$endpointPath"
               |  additional-id = $additionalId
               |}
               |""".stripMargin

        val expected = NewtypeApiConfig(
          NewtypeApiConfig.Api(
            Id.unsafeFrom(id),
            NewtypeBaseUri(Uri.unsafeFrom(uri)),
            RefinedEndpointPath.unsafeFrom(endpointPath),
            InlinedRefinedNewtypeId(Id.unsafeFrom(additionalId)),
          )
        )

        ConfigSource
          .string(confString)
          .load[NewtypeApiConfig] match {
          case Right(actual) =>
            actual ==== expected

          case Left(err) =>
            Result.failure.log(s"parse String config failed with error: ${err.toString}")
        }

      }

    @SuppressWarnings(Array("org.wartremover.warts.ToString"))
    def testConfigReaderInvalid: Property =
      for {
        id           <- Gen.long(Range.linear(Long.MinValue, 0)).log("id")
        uri          <- networkGens.genUriStringWithoutPath.log("uri")
        endpointPath <- Gen.string(Gen.alphaNum, Range.linear(1, 7)).list(Range.linear(1, 5)).map(_.mkString("/")).log("endpointPath")
        additionalId <- Gen.long(Range.linear(1L, Long.MaxValue)).log("additionalId")
      } yield {

        val confString =
          raw"""api {
               |  id = $id
               |  base-uri = "$uri"
               |  endpoint-path = "$endpointPath"
               |  additional-id = $additionalId
               |}
               |""".stripMargin

        val expected =
          s"The value ${id.toString} cannot be created as the expected type, ${Id.getClass.getName.replace("$", ".")}Type, due to the following error: " +
            s"Invalid value: [${id.toString}]. It must be a positive Long"

        ConfigSource
          .string(confString)
          .load[NewtypeApiConfig] match {
          case Right(actual) =>
            Result.failure.log(s"It should have failed to parse the config but got $actual")

          case Left(ConfigReaderFailures(ConvertFailure(UserValidationFailed(err), _, _))) =>
            err ==== expected

          case unexpected =>
            Result.failure.log(s"Unexpected result: ${unexpected.toString}")
        }

      }

    import scala.jdk.CollectionConverters.*

    @SuppressWarnings(Array("org.wartremover.warts.ToString"))
    def testConfigWriterAll: Property =
      for {
        id           <- Gen.long(Range.linear(1L, Long.MaxValue)).log("id")
        uri          <- networkGens.genUriStringWithoutPath.log("uri")
        endpointPath <- Gen.string(Gen.alphaNum, Range.linear(1, 7)).list(Range.linear(1, 5)).map(_.mkString("/")).log("endpointPath")
        additionalId <- Gen.long(Range.linear(1L, Long.MaxValue)).log("additionalId")
      } yield {

        val expected = ConfigValueFactory.fromMap(
          Map(
            "api" -> Map(
              "id"            -> id,
              "base-uri"      -> uri,
              "endpoint-path" -> endpointPath,
              "additional-id" -> additionalId,
            ).asJava
          ).asJava
        )

        val input = NewtypeApiConfig(
          NewtypeApiConfig.Api(
            Id.unsafeFrom(id),
            NewtypeBaseUri(Uri.unsafeFrom(uri)),
            RefinedEndpointPath.unsafeFrom(endpointPath),
            InlinedRefinedNewtypeId(Id.unsafeFrom(additionalId)),
          )
        )

        val actual = ConfigWriter[NewtypeApiConfig].to(input)

        (actual ==== expected).log(
          s"""
             | >>   actual: ${actual.render(ConfigRenderOptions.concise())}
             | >> ---------
             | >> expected: ${expected.render(ConfigRenderOptions.concise())}
             |""".stripMargin
        )

      }

    final case class NewtypeApiConfig(api: NewtypeApiConfig.Api) derives ConfigReader

    object NewtypeApiConfig {

      given configWriterNewtypeApiConfig: ConfigWriter[NewtypeApiConfig] = a =>
        ConfigValueFactory.fromMap(
          Map("api" -> ConfigWriter[NewtypeApiConfig.Api].to(a.api)).asJava
        )

      final case class Api(
        id: Id,
        baseUri: NewtypeBaseUri,
        endpointPath: RefinedEndpointPath,
        additionalId: InlinedRefinedNewtypeId,
      ) derives ConfigReader

      object Api {
        given configWriterApi: ConfigWriter[Api] = a =>
          ConfigValueFactory.fromMap(
            Map(
              "id"            -> ConfigWriter[Id].to(a.id),
              "base-uri"      -> ConfigWriter[NewtypeBaseUri].to(a.baseUri),
              "endpoint-path" -> ConfigWriter[RefinedEndpointPath].to(a.endpointPath),
              "additional-id" -> ConfigWriter[InlinedRefinedNewtypeId].to(a.additionalId),
            ).asJava
          )
      }
    }

    type Id = Id.Type

    object Id extends InlinedRefined[Long] {

      override inline val inlinedExpectedValue = "a positive Long"

      override inline def invalidReason(a: Long): String =
        "It must be a positive Long"

      override inline def predicate(a: Long): Boolean = a > 0L

      override inline def inlinedPredicate(inline a: Long): Boolean = a > 0L
    }

    type NewtypeBaseUri = NewtypeBaseUri.Type

    object NewtypeBaseUri extends Newtype[Uri]

    type RefinedEndpointPath = RefinedEndpointPath.Type

    @SuppressWarnings(Array("org.wartremover.warts.Equals"))
    object RefinedEndpointPath extends Refined[String] {
      override inline def invalidReason(a: String): String =
        "It must be a non-empty String"

      override inline def predicate(a: String): Boolean = a != ""
    }

    type InlinedRefinedNewtypeId = InlinedRefinedNewtypeId.Type

    object InlinedRefinedNewtypeId extends Newtype[Id]
  }
}
