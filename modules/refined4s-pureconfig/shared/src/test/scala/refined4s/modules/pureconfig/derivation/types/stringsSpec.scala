package refined4s.modules.pureconfig.derivation.types

import cats.syntax.all.*
import hedgehog.*
import hedgehog.runner.*
import pureconfig.error.{ConfigReaderFailures, ConvertFailure, UserValidationFailed}
import pureconfig.generic.derivation.default.*
import pureconfig.{ConfigReader, ConfigSource, ConfigWriter}
import refined4s.internal.typeTools
import refined4s.types.{strings, UuidV7TestTools}
import refined4s.types.strings.*

import java.util.UUID

/** @author Kevin Lee
  * @since 2025-09-02
  */
trait stringsSpec {

  protected val stringsTypeClasses: refined4s.modules.pureconfig.derivation.types.strings
  import stringsTypeClasses.given

  def allTests: List[Test] = List(
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
    property("test ConfigReader[UuidV7]", testConfigReaderUuidV7),
    property("test ConfigReader[UuidV7] with invalid value", testConfigReaderUuidV7Invalid),
    property("test ConfigReader[UuidV7] with UUID v4 (invalid UUID v7)", testConfigReaderUuidV7WithUuidV4),
    property("test ConfigWriter[UuidV7]", testConfigWriterUuidV7),
  )

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

  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  def testConfigReaderNonEmptyStringInvalid: Result = {

    val confString = """name = """""

    val expected = NonEmptyString.from("").leftMap { err =>
      s"The value  cannot be created as the expected type, ${typeTools.getTypeName[NonEmptyString]}, due to the following error: $err"
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

  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
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
      s"The value $s cannot be created as the expected type, ${typeTools.getTypeName[NonBlankString]}, due to the following error: $err"
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
      whitespaceString    <-
        Gen.string(hedgehog.extra.Gens.genCharByRange(strings.WhitespaceCharRange), Range.linear(1, 10)).log("whitespaceString")

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

  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  def testConfigReaderUuidInvalid: Property =
    for {
      s <- Gen.string(Gen.alphaNum, Range.linear(0, 10)).log("s")
    } yield {

      val confString = s"""id = "$s""""

      val expected = Uuid.from(s).leftMap { err =>
        s"The value $s cannot be created as the expected type, ${typeTools.getTypeName[Uuid]}, due to the following error: $err"
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

  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  def testConfigReaderUuidV7: Property =
    for {
      uuid <- Gen.elementUnsafe(UuidV7TestTools.validUuidV7Strings).log("uuid")
    } yield {

      val confString =
        raw"""
             |id = "$uuid"
             |""".stripMargin

      val expected = UuidV7.unsafeFromString(uuid)

      ConfigSource
        .string(confString)
        .load[UuidV7Config] match {
        case Right(UuidV7Config(actual)) =>
          actual ==== expected

        case Left(err) =>
          Result.failure.log(s"parse config failed with error: ${err.toString}")
      }

    }

  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  def testConfigReaderUuidV7Invalid: Property =
    for {
      s <- Gen.string(Gen.alphaNum, Range.linear(1, 10)).log("s")
    } yield {

      val confString = s"""id = "$s""""

      val expectedType = "refined4s.types.strings.UuidV7"

      val expected = UuidV7.fromString(s).leftMap { err =>
        s"The value $s cannot be created as the expected type, ${typeTools.getTypeName[UuidV7]}, due to the following error: $err"
      }

      ConfigSource
        .string(confString)
        .load[UuidV7Config] match {
        case Right(UuidV7Config(actual)) =>
          Result.failure.log(s"It should have failed to parse the config but got $actual")

        case Left(ConfigReaderFailures(ConvertFailure(pureconfig.error.CannotConvert(value, actualType, err), Some(_), field))) =>
          Result.all(
            List(
              (value ==== s).log(s"value=$value, s=$s"),
              (actualType ==== expectedType).log(s"actualType=$actualType, expectedType=$expectedType"),
              (err.asLeft ==== expected).log(s"Left(err)=Left($err), expected=$expected"),
              (field ==== "id").log("field"),
            )
          )

        case unexpected =>
          Result.failure.log(s"Unexpected result: ${unexpected.toString}")
      }

    }

  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  def testConfigReaderUuidV7WithUuidV4: Property =
    for {
      uuid <- Gen.constant(UUID.randomUUID().toString).log("uuid")
    } yield {

      val confString = s"""id = "$uuid""""

      val expectedType = "refined4s.types.strings.UuidV7"

      val expected = UuidV7.fromString(uuid).leftMap { err =>
        s"The value $uuid cannot be created as the expected type, ${typeTools.getTypeName[UuidV7]}, due to the following error: $err"
      }

      ConfigSource
        .string(confString)
        .load[UuidV7Config] match {
        case Right(UuidV7Config(actual)) =>
          Result.failure.log(s"It should have failed to parse the config but got $actual")

        case Left(ConfigReaderFailures(ConvertFailure(pureconfig.error.CannotConvert(value, actualType, err), Some(_), field))) =>
          Result.all(
            List(
              (value ==== uuid).log(s"value=$value, s=$uuid"),
              (actualType ==== expectedType).log(s"actualType=$actualType, expectedType=$expectedType"),
              (err.asLeft ==== expected).log(s"Left(err)=Left($err), expected=$expected"),
              (field ==== "id").log("field"),
            )
          )

        case unexpected =>
          Result.failure.log(s"Unexpected result: ${unexpected.toString}")
      }

    }

  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  def testConfigWriterUuidV7: Property =
    for {
      uuid <- Gen.elementUnsafe(UuidV7TestTools.validUuidV7Strings).log("uuid")
    } yield {

      val input = UuidV7.unsafeFromString(uuid)

      val expected = ConfigWriter[String].to(uuid)
      val actual   = ConfigWriter[UuidV7].to(input)

      actual ==== expected

    }
  final case class UuidV7Config(id: UuidV7) derives ConfigReader

}
object stringsSpec extends Properties, stringsSpec {

  override protected object stringsTypeClasses extends refined4s.modules.pureconfig.derivation.types.strings

  override def tests: List[Test] = allTests

}
