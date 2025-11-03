package refined4s.types

import hedgehog.*
import hedgehog.runner.*
import refined4s.*
import refined4s.predef4testing.*

//import java.net.{URI, URL}
import java.net.URI

/** @author Kevin Lee
  * @since 2023-12-09
  */
@SuppressWarnings(Array("org.wartremover.warts.JavaNetURLConstructors"))
trait networkSpec extends Properties, networkCompatSpec {
  import network.*

  override def tests: List[Test] = compatTests ++ List(
    example("test Uri(valid URI String)", testUriApply),
    example("test Uri(URI)", testUriApplyURI),
    example("test Uri(invalid URI String)", testUriApplyInvalid),
    property("test Uri.predicate(valid)", testUriPredicateValid),
    property("test Uri.predicate(invalid)", testUriPredicateInvalid),
    property("test Uri.from(valid)", testUriFromValid),
    property("test Uri.from(invalid)", testUriFromInvalid),
    property("test Uri.unsafeFrom(valid)", testUriUnsafeFromValid),
    property("test Uri.unsafeFrom(invalid)", testUriUnsafeFromInvalid),
    property("test Uri.value", testUriValue),
    property("test Uri.unapply", testUriUnapply),
    property("test Uri.toURI", testUriToURI),
//    property("test Uri.toURL", testUriToURL),
    example("test network.isValidateUri(valid URI String)", testNetworkIsValidateUriValid),
    example("test network.isValidateUri(invalid URI String)", testNetworkIsValidateUriInvalid),
    example("test network.isValidateUri(non-String literal)", testNetworkIsValidateUriWithInvalidLiteral),

    //
    example("test PortNumber(valid)", testPortNumberApply),
    example("test PortNumber(invalid)", testPortNumberApplyInvalid),
    property("test PortNumber.from(valid)", testPortNumberFromValid),
    property("test PortNumber.from(invalid)", testPortNumberFromInvalid),
    property("test PortNumber.unsafeFrom(valid)", testPortNumberUnsafeFromValid),
    property("test PortNumber.unsafeFrom(invalid)", testPortNumberUnsafeFromInvalid),
    property("test PortNumber.value", testPortNumberValue),
    property("test PortNumber.unapply", testPortNumberUnapply),
    //
    example("test SystemPortNumber(valid)", testSystemPortNumberApply),
    example("test SystemPortNumber(invalid)", testSystemPortNumberApplyInvalid),
    property("test SystemPortNumber.from(valid)", testSystemPortNumberFromValid),
    property("test SystemPortNumber.from(invalid)", testSystemPortNumberFromInvalid),
    property("test SystemPortNumber.unsafeFrom(valid)", testSystemPortNumberUnsafeFromValid),
    property("test SystemPortNumber.unsafeFrom(invalid)", testSystemPortNumberUnsafeFromInvalid),
    property("test SystemPortNumber.value", testSystemPortNumberValue),
    property("test SystemPortNumber.unapply", testSystemPortNumberUnapply),
    //
    example("test NonSystemPortNumber(valid)", testNonSystemPortNumberApply),
    example("test NonSystemPortNumber(invalid)", testNonSystemPortNumberApplyInvalid),
    property("test NonSystemPortNumber.from(valid)", testNonSystemPortNumberFromValid),
    property("test NonSystemPortNumber.from(invalid)", testNonSystemPortNumberFromInvalid),
    property("test NonSystemPortNumber.unsafeFrom(valid)", testNonSystemPortNumberUnsafeFromValid),
    property("test NonSystemPortNumber.unsafeFrom(invalid)", testNonSystemPortNumberUnsafeFromInvalid),
    property("test NonSystemPortNumber.value", testNonSystemPortNumberValue),
    property("test NonSystemPortNumber.unapply", testNonSystemPortNumberUnapply),
    //
    example("test UserPortNumber(valid)", testUserPortNumberApply),
    example("test UserPortNumber(invalid)", testUserPortNumberApplyInvalid),
    property("test UserPortNumber.from(valid)", testUserPortNumberFromValid),
    property("test UserPortNumber.from(invalid)", testUserPortNumberFromInvalid),
    property("test UserPortNumber.unsafeFrom(valid)", testUserPortNumberUnsafeFromValid),
    property("test UserPortNumber.unsafeFrom(invalid)", testUserPortNumberUnsafeFromInvalid),
    property("test UserPortNumber.value", testUserPortNumberValue),
    property("test UserPortNumber.unapply", testUserPortNumberUnapply),
    //
    example("test DynamicPortNumber(valid)", testDynamicPortNumberApply),
    example("test DynamicPortNumber(invalid)", testDynamicPortNumberApplyInvalid),
    property("test DynamicPortNumber.from(valid)", testDynamicPortNumberFromValid),
    property("test DynamicPortNumber.from(invalid)", testDynamicPortNumberFromInvalid),
    property("test DynamicPortNumber.unsafeFrom(valid)", testDynamicPortNumberUnsafeFromValid),
    property("test DynamicPortNumber.unsafeFrom(invalid)", testDynamicPortNumberUnsafeFromInvalid),
    property("test DynamicPortNumber.value", testDynamicPortNumberValue),
    property("test DynamicPortNumber.unapply", testDynamicPortNumberUnapply),
  )

  def testUriApply: Result = {
    val expected = new URI("https://github.com/kevin-lee/refined4s")
    val actual   = Uri("https://github.com/kevin-lee/refined4s")
    Result.all(
      List(
        actual.value ==== expected.toString,
        actual.toURI ==== expected,
      )
    )
  }

  def testUriApplyURI: Result = {
    val uri      = new URI("https://github.com/kevin-lee/refined4s")
    val expected = uri
    val actual   = Uri(uri)
    Result.all(
      List(
        actual.value ==== expected.toString,
        actual.toURI ==== expected,
      )
    )
  }

  def testUriApplyInvalid: Result = {
    import scala.compiletime.testing.{typeChecks, typeCheckErrors}

    val shouldNotCompile = !typeChecks(
      """
          import network.*
          Uri("%^<>[]`{}")
        """
    )

    val expectedErrorMessage  = """Invalid Uri value: Malformed escape pair at index 0: %^<>[]`{}"""
    val shouldNotCompileError = typeCheckErrors(
      """
          import network.*
          Uri("%^<>[]`{}")
        """
    ).map(_.message).mkString

    Result.all(
      List(
        Result.assert(shouldNotCompile).log("""Uri("%^<>[]`{}") should have failed compilation but it succeeded."""),
        shouldNotCompileError ==== expectedErrorMessage,
      )
    )
  }

  def testUriPredicateValid: Property =
    for {
      uri <- networkGens.genUriString.log("uri")
    } yield {
      val expected = true
      val actual   = Uri.predicate(uri)

      actual ==== expected
    }

  @SuppressWarnings(Array("org.wartremover.warts.Throw"))
  def testUriPredicateInvalid: Property =
    for {
      input <- Gen.string(Gen.element1('%', '^', '<', '>', '[', ']', '`', '{', '}'), Range.linear(1, 5)).log("input")
    } yield {
      val expected = false
      val actual   = Uri.predicate(input)

      actual ==== expected
    }

  def testUriFromValid: Property =
    for {
      uri <- networkGens.genUriString.log("uri")
    } yield {
      val expected = Uri.unsafeFrom(uri).asRight
      val actual   = Uri.from(uri)

      val expectedUri = new URI(uri).asRight
      val actualUri   = actual.map(_.toURI)

      Result.all(
        List(
          actual ==== expected,
          actualUri ==== expectedUri,
        )
      )
    }

  @SuppressWarnings(Array("org.wartremover.warts.Throw"))
  def testUriFromInvalid: Property =
    for {
      input <- Gen.string(Gen.element1('%', '^', '<', '>', '[', ']', '`', '{', '}'), Range.linear(1, 5)).log("input")
    } yield {
      val invalidUriMessage =
        try {
          new URI(input)
          throw new AssertionError("new URI(input) should have failed but it succeeded.") // scalafix:ok DisableSyntax.throw
        } catch {
          case scala.util.control.NonFatal(ex) => s"Invalid Uri value: [$input]. ${ex.getMessage}"
        }
      val expected          = invalidUriMessage.asLeft
      val actual            = Uri.from(input)

      actual ==== expected
    }

  def testUriUnsafeFromValid: Property =
    for {
      uri <- networkGens.genUriString.log("uri")
    } yield {
      val expected = Uri.from(uri)
      val actual   = Uri.unsafeFrom(uri)

      val expectedUri = new URI(uri)
      val actualUri   = actual.toURI

      Result.all(
        List(
          actual.asRight ==== expected,
          actualUri ==== expectedUri,
        )
      )
    }

  @SuppressWarnings(Array("org.wartremover.warts.Throw"))
  def testUriUnsafeFromInvalid: Property =
    for {
      input <- Gen.string(Gen.element1('%', '^', '<', '>', '[', ']', '`', '{', '}'), Range.linear(1, 5)).log("input")
    } yield {
      val expected =
        try {
          new URI(input)
          throw new AssertionError("new URI(input) should have failed but it succeeded.") // scalafix:ok DisableSyntax.throw
        } catch {
          case scala.util.control.NonFatal(ex) => s"Invalid Uri value: [$input]. ${ex.getMessage}"
        }

      try {
        val _ = Uri.unsafeFrom(input)
        Result
          .failure
          .log(
            s"""IllegalArgumentException was expected from Uri.unsafeFrom($input), but it was not thrown."""
          )
      } catch {
        case ex: IllegalArgumentException =>
          ex.getMessage ==== expected
      }
    }

  def testUriValue: Property =
    for {
      uri <- networkGens.genUriString.log("uri")
    } yield {
      val expected = uri
      val actual   = Uri.unsafeFrom(uri).value

      actual ==== expected
    }

  def testUriUnapply: Property =
    for {
      uri <- networkGens.genUriString.log("uri")
    } yield {
      val expected = uri
      Uri.unsafeFrom(uri) match {
        case Uri(actual) =>
          actual ==== expected
      }
    }

  def testUriToURI: Property =
    for {
      uri <- networkGens.genUriString.log("uri")
    } yield {
      val expected = new URI(uri)
      val actual   = Uri.unsafeFrom(uri).toURI

      actual ==== expected
    }

//  def testUriToURL: Property =
//    for {
//      uri <- networkGens.genUrlString.log("uri")
//    } yield {
//      val expected = new URL(uri)
//      val actual   = Uri.unsafeFrom(uri).toURL
//
//      actual ==== expected
//    }

  inline def runNetworkIsValidateUri(inline a: String): Boolean = ${ UriValidator.isValidateUri('a) }

  def testNetworkIsValidateUriValid: Result = {
    val expected = true
    val actual   = runNetworkIsValidateUri("https://github.com/kevin-lee/refined4s")
    (actual ==== expected)
      .log("""network.isValidateUri("https://github.com/kevin-lee/refined4s") should return true but it returned false.""")
  }

  def testNetworkIsValidateUriInvalid: Result = {
    import scala.compiletime.testing.{typeChecks, typeCheckErrors}
    val expected = """Invalid Uri value: Malformed escape pair at index 0: %^<>[]`{}"""
    val actual   = typeCheckErrors("""runNetworkIsValidateUri("%^<>[]`{}")""").map(_.message).mkString
    (actual ==== expected)
      .log(s"""network.isValidateUri("%^<>[]`{}") should not compile with the expected error: $expected""")
  }

  def testNetworkIsValidateUriWithInvalidLiteral: Result = {
    import scala.compiletime.testing.{typeChecks, typeCheckErrors}
    val expectedMessage = UriValidator.UnexpectedLiteralErrorMessage

    val actual = typeCheckErrors(
      """
        val a = "blah"
        runNetworkIsValidateUri(a)
      """
    )

    val actualErrorMessage = actual.map(_.message).mkString
    (actualErrorMessage ==== expectedMessage)
  }

  //
  def testPortNumberApply: Result = {
    val expected = 0
    val actual   = PortNumber(0)

    val expected2 = 65535
    val actual2   = PortNumber(65535)

    Result.all(
      List(
        actual.value ==== expected,
        actual2.value ==== expected2,
      )
    )
  }

  def testPortNumberApplyInvalid: Result = {
    import scala.compiletime.testing.{typeChecks, typeCheckErrors}

    val shouldNotCompile  = !typeChecks(
      """
          import network.*
          PortNumber(-1)
        """
    )
    val shouldNotCompile2 = !typeChecks(
      """
          import network.*
          PortNumber(65536)
        """
    )

    val expectedErrorMessage         = """Invalid value: [-1]. It has to be Int between 0 and 65535 (0 <= PortNumber <= 65535)"""
    val shouldNotCompileErrorMessage = typeCheckErrors(
      """
          import network.*
          PortNumber(-1)
        """
    ).map(_.message).mkString

    val expectedErrorMessage2         = """Invalid value: [65536]. It has to be Int between 0 and 65535 (0 <= PortNumber <= 65535)"""
    val shouldNotCompile2ErrorMessage = typeCheckErrors(
      """
          import network.*
          PortNumber(65536)
        """
    ).map(_.message).mkString

    Result.all(
      List(
        Result.assert(shouldNotCompile).log("""PortNumber(-1) should have failed compilation but it succeeded."""),
        Result.assert(shouldNotCompile2).log("""PortNumber(65536) should have failed compilation but it succeeded."""),
        shouldNotCompileErrorMessage ==== expectedErrorMessage,
        shouldNotCompile2ErrorMessage ==== expectedErrorMessage2,
      )
    )
  }

  def testPortNumberFromValid: Property =
    for {
      p <- networkGens.genPortNumberInt.log("p")
    } yield {
      val expected = PortNumber.unsafeFrom(p).asRight
      val actual   = PortNumber.from(p)

      actual ==== expected
    }

  def testPortNumberFromInvalid: Property =
    for {
      p <- networkGens.genInvalidPortNumberInt.log("p")
    } yield {
      val expected = s"Invalid value: [$p]. It has to be Int between 0 and 65535 (0 <= PortNumber <= 65535)".asLeft
      val actual   = PortNumber.from(p)

      actual ==== expected
    }

  def testPortNumberUnsafeFromValid: Property =
    for {
      p <- networkGens.genPortNumberInt.log("p")
    } yield {
      val expected = PortNumber.from(p)
      val actual   = PortNumber.unsafeFrom(p)

      actual.asRight ==== expected
    }

  def testPortNumberUnsafeFromInvalid: Property =
    for {
      p <- networkGens.genInvalidPortNumberInt.log("p")
    } yield {
      val expected = s"Invalid value: [$p]. It has to be Int between 0 and 65535 (0 <= PortNumber <= 65535)"

      try {
        val _ = PortNumber.unsafeFrom(p)
        Result
          .failure
          .log(
            s"""IllegalArgumentException was expected from PortNumber.unsafeFrom($p), but it was not thrown."""
          )
      } catch {
        case ex: IllegalArgumentException =>
          ex.getMessage ==== expected
      }
    }

  def testPortNumberValue: Property =
    for {
      p <- networkGens.genPortNumberInt.log("p")
    } yield {
      val expected = p
      val actual   = PortNumber.unsafeFrom(p).value

      actual ==== expected
    }

  def testPortNumberUnapply: Property =
    for {
      p <- networkGens.genPortNumberInt.log("p")
    } yield {
      val expected = p
      PortNumber.unsafeFrom(p) match {
        case PortNumber(actual) =>
          actual ==== expected
      }
    }

  //
  def testSystemPortNumberApply: Result = {
    val expected = 0
    val actual   = SystemPortNumber(0)

    val expected2 = 1023
    val actual2   = SystemPortNumber(1023)

    Result.all(
      List(
        actual.value ==== expected,
        actual2.value ==== expected2,
      )
    )
  }

  def testSystemPortNumberApplyInvalid: Result = {
    import scala.compiletime.testing.{typeChecks, typeCheckErrors}

    val shouldNotCompile  = !typeChecks(
      """
          import network.*
          SystemPortNumber(-1)
        """
    )
    val shouldNotCompile2 = !typeChecks(
      """
          import network.*
          SystemPortNumber(1024)
        """
    )

    val expectedErrorMessage         = "Invalid value: [-1]. It has to be Int between 0 and 1023 (0 <= SystemPortNumber <= 1023)"
    val shouldNotCompileErrorMessage = typeCheckErrors(
      """
          import network.*
          SystemPortNumber(-1)
        """
    ).map(_.message).mkString

    val expectedErrorMessage2         = "Invalid value: [1024]. It has to be Int between 0 and 1023 (0 <= SystemPortNumber <= 1023)"
    val shouldNotCompile2ErrorMessage = typeCheckErrors(
      """
          import network.*
          SystemPortNumber(1024)
        """
    ).map(_.message).mkString

    Result.all(
      List(
        Result.assert(shouldNotCompile).log("""SystemPortNumber(-1) should have failed compilation but it succeeded."""),
        Result.assert(shouldNotCompile2).log("""SystemPortNumber(1024) should have failed compilation but it succeeded."""),
        shouldNotCompileErrorMessage ==== expectedErrorMessage,
        shouldNotCompile2ErrorMessage ==== expectedErrorMessage2,
      )
    )
  }

  def testSystemPortNumberFromValid: Property =
    for {
      p <- networkGens.genSystemPortNumberInt.log("p")
    } yield {
      val expected = SystemPortNumber.unsafeFrom(p).asRight
      val actual   = SystemPortNumber.from(p)

      actual ==== expected
    }

  def testSystemPortNumberFromInvalid: Property =
    for {
      p <- networkGens.genInvalidSystemPortNumberInt.log("p")
    } yield {
      val expected = s"Invalid value: [$p]. It has to be Int between 0 and 1023 (0 <= SystemPortNumber <= 1023)".asLeft
      val actual   = SystemPortNumber.from(p)

      actual ==== expected
    }

  def testSystemPortNumberUnsafeFromValid: Property =
    for {
      p <- networkGens.genSystemPortNumberInt.log("p")
    } yield {
      val expected = SystemPortNumber.from(p)
      val actual   = SystemPortNumber.unsafeFrom(p)

      actual.asRight ==== expected
    }

  def testSystemPortNumberUnsafeFromInvalid: Property =
    for {
      p <- networkGens.genInvalidSystemPortNumberInt.log("p")
    } yield {
      val expected = s"Invalid value: [$p]. It has to be Int between 0 and 1023 (0 <= SystemPortNumber <= 1023)"

      try {
        val _ = SystemPortNumber.unsafeFrom(p)
        Result
          .failure
          .log(
            s"""IllegalArgumentException was expected from SystemPortNumber.unsafeFrom($p), but it was not thrown."""
          )
      } catch {
        case ex: IllegalArgumentException =>
          ex.getMessage ==== expected
      }
    }

  def testSystemPortNumberValue: Property =
    for {
      p <- networkGens.genSystemPortNumberInt.log("p")
    } yield {
      val expected = p
      val actual   = SystemPortNumber.unsafeFrom(p).value

      actual ==== expected
    }

  def testSystemPortNumberUnapply: Property =
    for {
      p <- networkGens.genSystemPortNumberInt.log("p")
    } yield {
      val expected = p
      SystemPortNumber.unsafeFrom(p) match {
        case SystemPortNumber(actual) =>
          actual ==== expected
      }
    }

  //
  def testNonSystemPortNumberApply: Result = {
    val expected = 1024
    val actual   = NonSystemPortNumber(1024)

    val expected2 = 65535
    val actual2   = NonSystemPortNumber(65535)

    Result.all(
      List(
        actual.value ==== expected,
        actual2.value ==== expected2,
      )
    )

  }

  def testNonSystemPortNumberApplyInvalid: Result = {
    import scala.compiletime.testing.{typeChecks, typeCheckErrors}

    val shouldNotCompile  = !typeChecks(
      """
          import network.*
          NonSystemPortNumber(1023)
        """
    )
    val shouldNotCompile2 = !typeChecks(
      """
          import network.*
          NonSystemPortNumber(65536)
        """
    )

    val expectedErrorMessage = "Invalid value: [1023]. It has to be Int between 1024 and 65535 (1024 <= NonSystemPortNumber <= 65535)"
    val shouldNotCompileErrorMessage = typeCheckErrors(
      """
          import network.*
          NonSystemPortNumber(1023)
        """
    ).map(_.message).mkString

    val expectedErrorMessage2 = "Invalid value: [65536]. It has to be Int between 1024 and 65535 (1024 <= NonSystemPortNumber <= 65535)"
    val shouldNotCompile2ErrorMessage = typeCheckErrors(
      """
          import network.*
          NonSystemPortNumber(65536)
        """
    ).map(_.message).mkString

    Result.all(
      List(
        Result.assert(shouldNotCompile).log("""NonSystemPortNumber(1023) should have failed compilation but it succeeded."""),
        Result.assert(shouldNotCompile2).log("""NonSystemPortNumber(65536) should have failed compilation but it succeeded."""),
        shouldNotCompileErrorMessage ==== expectedErrorMessage,
        shouldNotCompile2ErrorMessage ==== expectedErrorMessage2,
      )
    )
  }

  def testNonSystemPortNumberFromValid: Property =
    for {
      p <- networkGens.genNonSystemPortNumberInt.log("p")
    } yield {
      val expected = NonSystemPortNumber.unsafeFrom(p).asRight
      val actual   = NonSystemPortNumber.from(p)

      actual ==== expected
    }

  def testNonSystemPortNumberFromInvalid: Property =
    for {
      p <- networkGens.genInvalidNonSystemPortNumberInt.log("p")
    } yield {
      val expected = s"Invalid value: [$p]. It has to be Int between 1024 and 65535 (1024 <= NonSystemPortNumber <= 65535)".asLeft
      val actual   = NonSystemPortNumber.from(p)

      actual ==== expected
    }

  def testNonSystemPortNumberUnsafeFromValid: Property =
    for {
      p <- networkGens.genNonSystemPortNumberInt.log("p")
    } yield {
      val expected = NonSystemPortNumber.from(p)
      val actual   = NonSystemPortNumber.unsafeFrom(p)

      actual.asRight ==== expected
    }

  def testNonSystemPortNumberUnsafeFromInvalid: Property =
    for {
      p <- networkGens.genInvalidNonSystemPortNumberInt.log("p")
    } yield {
      val expected = s"Invalid value: [$p]. It has to be Int between 1024 and 65535 (1024 <= NonSystemPortNumber <= 65535)"

      try {
        val _ = NonSystemPortNumber.unsafeFrom(p)
        Result
          .failure
          .log(
            s"""IllegalArgumentException was expected from NonSystemPortNumber.unsafeFrom($p), but it was not thrown."""
          )
      } catch {
        case ex: IllegalArgumentException =>
          ex.getMessage ==== expected
      }
    }

  def testNonSystemPortNumberValue: Property =
    for {
      p <- networkGens.genNonSystemPortNumberInt.log("p")
    } yield {
      val expected = p
      val actual   = NonSystemPortNumber.unsafeFrom(p).value

      actual ==== expected
    }

  def testNonSystemPortNumberUnapply: Property =
    for {
      p <- networkGens.genNonSystemPortNumberInt.log("p")
    } yield {
      val expected = p
      NonSystemPortNumber.unsafeFrom(p) match {
        case NonSystemPortNumber(actual) =>
          actual ==== expected
      }
    }

  //
  def testUserPortNumberApply: Result = {
    val expected = 1024
    val actual   = UserPortNumber(1024)

    val expected2 = 49151
    val actual2   = UserPortNumber(49151)

    Result.all(
      List(
        actual.value ==== expected,
        actual2.value ==== expected2,
      )
    )
  }

  def testUserPortNumberApplyInvalid: Result = {
    import scala.compiletime.testing.{typeChecks, typeCheckErrors}

    val shouldNotCompile  = !typeChecks(
      """
          import network.*
          UserPortNumber(1023)
        """
    )
    val shouldNotCompile2 = !typeChecks(
      """
          import network.*
          UserPortNumber(49152)
        """
    )

    val expectedErrorMessage         = "Invalid value: [1023]. It has to be Int between 1024 and 49151 (1024 <= UserPortNumber <= 49151)"
    val shouldNotCompileErrorMessage = typeCheckErrors(
      """
          import network.*
          UserPortNumber(1023)
        """
    ).map(_.message).mkString

    val expectedErrorMessage2         = "Invalid value: [49152]. It has to be Int between 1024 and 49151 (1024 <= UserPortNumber <= 49151)"
    val shouldNotCompile2ErrorMessage = typeCheckErrors(
      """
          import network.*
          UserPortNumber(49152)
        """
    ).map(_.message).mkString

    Result.all(
      List(
        Result.assert(shouldNotCompile).log("""UserPortNumber(1023) should have failed compilation but it succeeded."""),
        Result.assert(shouldNotCompile2).log("""UserPortNumber(49152) should have failed compilation but it succeeded."""),
        shouldNotCompileErrorMessage ==== expectedErrorMessage,
        shouldNotCompile2ErrorMessage ==== expectedErrorMessage2,
      )
    )
  }

  def testUserPortNumberFromValid: Property =
    for {
      p <- networkGens.genUserPortNumberInt.log("p")
    } yield {
      val expected = UserPortNumber.unsafeFrom(p).asRight
      val actual   = UserPortNumber.from(p)

      actual ==== expected
    }

  def testUserPortNumberFromInvalid: Property =
    for {
      p <- networkGens.genInvalidUserPortNumberInt.log("p")
    } yield {
      val expected = s"Invalid value: [$p]. It has to be Int between 1024 and 49151 (1024 <= UserPortNumber <= 49151)".asLeft
      val actual   = UserPortNumber.from(p)

      actual ==== expected
    }

  def testUserPortNumberUnsafeFromValid: Property =
    for {
      p <- networkGens.genUserPortNumberInt.log("p")
    } yield {
      val expected = UserPortNumber.from(p)
      val actual   = UserPortNumber.unsafeFrom(p)

      actual.asRight ==== expected
    }

  def testUserPortNumberUnsafeFromInvalid: Property =
    for {
      p <- networkGens.genInvalidUserPortNumberInt.log("p")
    } yield {
      val expected = s"Invalid value: [$p]. It has to be Int between 1024 and 49151 (1024 <= UserPortNumber <= 49151)"

      try {
        val _ = UserPortNumber.unsafeFrom(p)
        Result
          .failure
          .log(
            s"""IllegalArgumentException was expected from UserPortNumber.unsafeFrom($p), but it was not thrown."""
          )
      } catch {
        case ex: IllegalArgumentException =>
          ex.getMessage ==== expected
      }
    }

  def testUserPortNumberValue: Property =
    for {
      p <- networkGens.genUserPortNumberInt.log("p")
    } yield {
      val expected = p
      val actual   = UserPortNumber.unsafeFrom(p).value

      actual ==== expected
    }

  def testUserPortNumberUnapply: Property =
    for {
      p <- networkGens.genUserPortNumberInt.log("p")
    } yield {
      val expected = p
      UserPortNumber.unsafeFrom(p) match {
        case UserPortNumber(actual) =>
          actual ==== expected
      }
    }

  //
  def testDynamicPortNumberApply: Result = {
    val expected = 49152
    val actual   = DynamicPortNumber(49152)

    val expected2 = 65535
    val actual2   = DynamicPortNumber(65535)

    Result.all(
      List(
        actual.value ==== expected,
        actual2.value ==== expected2,
      )
    )
  }

  def testDynamicPortNumberApplyInvalid: Result = {
    import scala.compiletime.testing.{typeChecks, typeCheckErrors}

    val shouldNotCompile  = !typeChecks(
      """
          import network.*
          DynamicPortNumber(49151)
        """
    )
    val shouldNotCompile2 = !typeChecks(
      """
          import network.*
          DynamicPortNumber(65536)
        """
    )

    val expectedErrorMessage = "Invalid value: [49151]. It has to be Int between 49152 and 65535 (49152 <= DynamicPortNumber <= 65535)"
    val shouldNotCompileErrorMessage = typeCheckErrors(
      """
          import network.*
          DynamicPortNumber(49151)
        """
    ).map(_.message).mkString

    val expectedErrorMessage2 = "Invalid value: [65536]. It has to be Int between 49152 and 65535 (49152 <= DynamicPortNumber <= 65535)"
    val shouldNotCompile2ErrorMessage = typeCheckErrors(
      """
          import network.*
          DynamicPortNumber(65536)
        """
    ).map(_.message).mkString

    Result.all(
      List(
        Result.assert(shouldNotCompile).log("""DynamicPortNumber(49151) should have failed compilation but it succeeded."""),
        Result.assert(shouldNotCompile2).log("""DynamicPortNumber(65536) should have failed compilation but it succeeded."""),
        shouldNotCompileErrorMessage ==== expectedErrorMessage,
        shouldNotCompile2ErrorMessage ==== expectedErrorMessage2,
      )
    )
  }

  def testDynamicPortNumberFromValid: Property =
    for {
      p <- networkGens.genDynamicPortNumberInt.log("p")
    } yield {
      val expected = DynamicPortNumber.unsafeFrom(p).asRight
      val actual   = DynamicPortNumber.from(p)

      actual ==== expected
    }

  def testDynamicPortNumberFromInvalid: Property =
    for {
      p <- networkGens.genInvalidDynamicPortNumberInt.log("p")
    } yield {
      val expected = s"Invalid value: [$p]. It has to be Int between 49152 and 65535 (49152 <= DynamicPortNumber <= 65535)".asLeft
      val actual   = DynamicPortNumber.from(p)

      actual ==== expected
    }

  def testDynamicPortNumberUnsafeFromValid: Property =
    for {
      p <- networkGens.genDynamicPortNumberInt.log("p")
    } yield {
      val expected = DynamicPortNumber.from(p)
      val actual   = DynamicPortNumber.unsafeFrom(p)

      actual.asRight ==== expected
    }

  def testDynamicPortNumberUnsafeFromInvalid: Property =
    for {
      p <- networkGens.genInvalidDynamicPortNumberInt.log("p")
    } yield {
      val expected = s"Invalid value: [$p]. It has to be Int between 49152 and 65535 (49152 <= DynamicPortNumber <= 65535)"

      try {
        val _ = DynamicPortNumber.unsafeFrom(p)
        Result
          .failure
          .log(
            s"""IllegalArgumentException was expected from DynamicPortNumber.unsafeFrom($p), but it was not thrown."""
          )
      } catch {
        case ex: IllegalArgumentException =>
          ex.getMessage ==== expected
      }
    }

  def testDynamicPortNumberValue: Property =
    for {
      p <- networkGens.genDynamicPortNumberInt.log("p")
    } yield {
      val expected = p
      val actual   = DynamicPortNumber.unsafeFrom(p).value

      actual ==== expected
    }

  def testDynamicPortNumberUnapply: Property =
    for {
      p <- networkGens.genDynamicPortNumberInt.log("p")
    } yield {
      val expected = p
      DynamicPortNumber.unsafeFrom(p) match {
        case DynamicPortNumber(actual) =>
          actual ==== expected
      }
    }

}
object networkSpec extends networkSpec
