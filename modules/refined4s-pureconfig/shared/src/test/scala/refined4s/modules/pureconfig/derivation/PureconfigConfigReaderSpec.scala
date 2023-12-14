package refined4s.modules.pureconfig.derivation

import cats.syntax.all.*
import com.typesafe.config.ConfigOrigin
import com.typesafe.config.impl.{OriginType, SimpleConfigOrigin}
import hedgehog.*
import hedgehog.runner.*
import pureconfig.*
import pureconfig.error.{ConfigReaderFailures, ConvertFailure, UserValidationFailed}
import pureconfig.generic.derivation.default.*
import refined4s.modules.pureconfig.derivation.*
import refined4s.{InlinedRefined, Newtype, Refined}

import java.net.URL
import java.util

/** @author Kevin Lee
  * @since 2023-12-14
  */
object PureconfigConfigReaderSpec extends Properties {
  override def tests: List[Test] = List(
    property(
      "test RefinedNewtypeConfigReader with valid value in config",
      testRefinedConfigReaderValid,
    ),
    property(
      "test PureconfigRefinedConfigReader for Refined with valid value in config",
      testPureconfigRefinedConfigReaderRefinedValid,
    ),
    example(
      "test PureconfigRefinedConfigReader for Refined with invalid value in config",
      testPureconfigRefinedConfigReaderRefinedInvalid,
    ),
    property(
      "test PureconfigRefinedConfigReader for InlinedRefined with valid value in config",
      testPureconfigRefinedConfigReaderInlinedRefinedValid,
    ),
    example(
      "test PureconfigRefinedConfigReader for InlinedRefined with valid value in config",
      testPureconfigRefinedConfigReaderInlinedRefinedInvalid,
    ),
    property(
      "test PureconfigNewtypeConfigReader for Newtype(Refined) with valid value in config",
      testPureconfigRefinedConfigReaderRefinedNewtypeValid,
    ),
    example(
      "test PureconfigNewtypeConfigReader for Newtype(Refined) with valid value in config",
      testPureconfigRefinedConfigReaderRefinedNewtypeInvalid,
    ),
  )

  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  def testRefinedConfigReaderValid: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
    } yield {
      val confString =
        raw"""the-value = "$s"
             |""".stripMargin

      val expected = MyNewtype(s)

      ConfigSource
        .string(confString)
        .load[MyNewtypeConfig] match {
        case Right(MyNewtypeConfig(actual)) =>
          actual ==== expected

        case Left(err) =>
          Result.failure.log(s"parse String config failed with error: ${err.toString}")
      }

    }

  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  def testPureconfigRefinedConfigReaderRefinedValid: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
    } yield {
      val confString =
        raw"""the-value = "$s"
             |""".stripMargin

      val expected = MyRefinedType.unsafeFrom(s)

      ConfigSource
        .string(confString)
        .load[MyRefinedTypeConfig] match {
        case Right(MyRefinedTypeConfig(actual)) =>
          actual ==== expected

        case Left(err) =>
          Result.failure.log(s"parse String config failed with error: ${err.toString}")
      }

    }

  @SuppressWarnings(Array("org.wartremover.warts.ToString", "org.wartremover.warts.Null", "org.wartremover.warts.AsInstanceOf"))
  def testPureconfigRefinedConfigReaderRefinedInvalid: Result = {
    val confString =
      """the-value = ""
        |""".stripMargin

    val expected = MyRefinedType.from("").left.map { err =>
      s"The value  cannot be created as the expected type, PureconfigRefinedConfigReader.this.Type, due to the following error: $err"
    }

    ConfigSource
      .string(confString)
      .load[MyRefinedTypeConfig] match {
      case Right(MyRefinedTypeConfig(actual)) =>
        Result.failure.log(s"It should have failed to parse the config but got $actual")

      case Left(ConfigReaderFailures(ConvertFailure(UserValidationFailed(err), _, _))) =>
        err.asLeft ==== expected

      case unexpected =>
        Result.failure.log(s"Unexpected result: ${unexpected.toString}")
    }

  }

  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  def testPureconfigRefinedConfigReaderInlinedRefinedValid: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
    } yield {
      val confString =
        raw"""the-value = "$s"
             |""".stripMargin

      val expected = MyInlinedRefinedType.unsafeFrom(s)

      ConfigSource
        .string(confString)
        .load[MyInlinedRefinedTypeConfig] match {
        case Right(MyInlinedRefinedTypeConfig(actual)) =>
          actual ==== expected

        case Left(err) =>
          Result.failure.log(s"parse String config failed with error: ${err.toString}")
      }

    }

  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  def testPureconfigRefinedConfigReaderInlinedRefinedInvalid: Result = {
    val confString =
      """the-value = ""
        |""".stripMargin

    val expected = MyRefinedType.from("").left.map { err =>
      s"The value  cannot be created as the expected type, PureconfigRefinedConfigReader.this.Type, due to the following error: $err"
    }

    ConfigSource
      .string(confString)
      .load[MyInlinedRefinedTypeConfig] match {
      case Right(MyInlinedRefinedTypeConfig(actual)) =>
        Result.failure.log(s"It should have failed to parse the config but got $actual")

      case Left(ConfigReaderFailures(ConvertFailure(UserValidationFailed(err), _, _))) =>
        err.asLeft ==== expected

      case unexpected =>
        Result.failure.log(s"Unexpected result: ${unexpected.toString}")
    }

  }

  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  def testPureconfigRefinedConfigReaderRefinedNewtypeValid: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
    } yield {
      val confString =
        raw"""the-value = "$s"
             |""".stripMargin

      val expected = MyInlinedRefinedNewtype(MyRefinedType.unsafeFrom(s))

      ConfigSource
        .string(confString)
        .load[MyInlinedRefinedNewtypeConfig] match {
        case Right(MyInlinedRefinedNewtypeConfig(actual)) =>
          actual ==== expected

        case Left(err) =>
          Result.failure.log(s"parse String config failed with error: ${err.toString}")
      }

    }

  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  def testPureconfigRefinedConfigReaderRefinedNewtypeInvalid: Result = {
    val confString =
      """the-value = ""
        |""".stripMargin

    val expected = MyRefinedType.from("").left.map { err =>
      s"The value  cannot be created as the expected type, PureconfigRefinedConfigReader.this.Type, due to the following error: $err"
    }

    ConfigSource
      .string(confString)
      .load[MyInlinedRefinedNewtypeConfig] match {
      case Right(MyInlinedRefinedNewtypeConfig(actual)) =>
        Result.failure.log(s"It should have failed to parse the config but got $actual")

      case Left(ConfigReaderFailures(ConvertFailure(UserValidationFailed(err), _, _))) =>
        err.asLeft ==== expected

      case unexpected =>
        Result.failure.log(s"Unexpected result: ${unexpected.toString}")
    }

  }

  final case class MyNewtypeConfig(theValue: MyNewtype) derives ConfigReader

  final case class MyRefinedTypeConfig(theValue: MyRefinedType) derives ConfigReader

  final case class MyInlinedRefinedTypeConfig(theValue: MyInlinedRefinedType) derives ConfigReader

  final case class MyInlinedRefinedNewtypeConfig(theValue: MyInlinedRefinedNewtype) derives ConfigReader

  type MyNewtype = MyNewtype.Type
  object MyNewtype extends Newtype[String] with PureconfigNewtypeConfigReader[String]

  type MyRefinedType = MyRefinedType.Type
  @SuppressWarnings(Array("org.wartremover.warts.Equals"))
  object MyRefinedType extends Refined[String] with PureconfigRefinedConfigReader[String] {
    override inline def invalidReason(a: String): String =
      "It has to be a non-empty String but got \"" + a + "\""

    override inline def predicate(a: String): Boolean = a != ""
  }

  type MyRefinedNewtype = MyRefinedNewtype.Type
  object MyRefinedNewtype extends Newtype[MyRefinedType] with PureconfigNewtypeConfigReader[MyRefinedType]

  type MyInlinedRefinedType = MyInlinedRefinedType.Type
  @SuppressWarnings(Array("org.wartremover.warts.Equals"))
  object MyInlinedRefinedType extends InlinedRefined[String] with PureconfigRefinedConfigReader[String] {
    override inline def invalidReason(a: String): String =
      "It has to be a non-empty String but got \"" + a + "\""

    override inline def predicate(a: String): Boolean = a != ""

    override inline def inlinedPredicate(inline a: String): Boolean = a != ""
  }

  type MyInlinedRefinedNewtype = MyInlinedRefinedNewtype.Type
  object MyInlinedRefinedNewtype extends Newtype[MyRefinedType] with PureconfigNewtypeConfigReader[MyRefinedType]

}
