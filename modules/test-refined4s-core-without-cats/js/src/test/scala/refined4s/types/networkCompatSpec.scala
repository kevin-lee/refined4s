package refined4s.types

import refined4s.predef4testing.*
import hedgehog.*
import hedgehog.runner.*

import java.net.URI

/** @author Kevin Lee
  * @since 2024-09-05
  */
trait networkCompatSpec {

  import refined4s.types.network.*

  def compatTests: List[Test] = List(
    property("test Uri.toUrl", testUriToUrl),
    //
    example("test Url(valid URL String)", testUrlApply),
//    example("test Url(URL)", testUrlApplyURL),
    example("test Url(invalid URL String)", testUrlApplyInvalid),
    property("test Url.predicate(valid)", testUrlPredicateValid),
    property("test Url.predicate(invalid)", testUrlPredicateInvalid),
    property("test Url.from(valid)", testUrlFromValid),
    property("test Url.from(invalid)", testUrlFromInvalid),
    property("test Url.unsafeFrom(valid)", testUrlUnsafeFromValid),
    property("test Url.unsafeFrom(invalid)", testUrlUnsafeFromInvalid),
    property("test Url.value", testUrlValue),
    property("test Url.unapply", testUrlUnapply),
//    property("test Url.toURL", testUrlToURL),
    property("test Url.toUri", testUrlToUri),
    property("test Url.toURI", testUrlToURI),
    example("test network.isValidateUrl(valid URL String)", testNetworkIsValidateUrlValid),
    example("test network.isValidateUrl(invalid URL String)", testNetworkIsValidateUrlInvalid),
    example("test network.isValidateUrl(non-String literal)", testNetworkIsValidateUrlWithInvalidLiteral),
  )
  //

  def testUriToUrl: Property =
    for {
      uri <- networkGens.genUrlString.log("uri")
    } yield {
      val expected = Url.unsafeFrom(uri)
      val actual   = Uri.unsafeFrom(uri).toUrl

      actual ==== expected
    }

  //

  def testUrlApply: Result = {
    @SuppressWarnings(Array("org.wartremover.warts.JavaNetURLConstructors"))
    val expected = URI("https://github.com/kevin-lee/refined4s")
    val actual   = Url("https://github.com/kevin-lee/refined4s")
    Result.all(
      List(
        actual.value ==== expected.toString,
        actual.toURI ==== expected,
      )
    )
  }

//  def testUrlApplyURL: Result = {
//    @SuppressWarnings(Array("org.wartremover.warts.JavaNetURLConstructors"))
//    val url      = new URL("https://github.com/kevin-lee/refined4s")
//    val expected = url
//    val actual   = Url(url)
//    Result.all(
//      List(
//        actual.value ==== expected.toString,
//        actual.toURL ==== expected,
//      )
//    )
//  }

  def testUrlApplyInvalid: Result = {
    import scala.compiletime.testing.{typeChecks, typeCheckErrors}

    val expected          = false
    val shouldNotCompile1 = typeChecks(
      """
        import network.*
        Url("%^<>[]`{}")
      """
    )

    val shouldNotCompile2 = typeChecks(
      """
        import network.*
        Url("blah://www.google.com")
      """
    )

    val expectedCompilationErrorMessage1 = """Invalid Url value: [%^<>[]`{}]. Malformed escape pair at index 0: %^<>[]`{}"""
    val shouldNotCompile1ErrorMessage    = typeCheckErrors(
      """
        import network.*
        Url("%^<>[]`{}")
      """
    ).map(_.message).mkString

    val expectedCompilationErrorMessage2 = """Invalid Url value: [blah://www.google.com]. invalid schema"""
    val shouldNotCompile2ErrorMessage    = typeCheckErrors(
      """
        import network.*
        Url("blah://www.google.com")
      """
    ).map(_.message).mkString

    Result.all(
      List(
        (shouldNotCompile1 ==== expected).log("""Url("%^<>[]`{}") should have failed compilation but it succeeded."""),
        (shouldNotCompile2 ==== expected).log("""Url("blah://www.google.com") should have failed compilation but it succeeded."""),
        shouldNotCompile1ErrorMessage ==== expectedCompilationErrorMessage1,
        shouldNotCompile2ErrorMessage ==== expectedCompilationErrorMessage2,
      )
    )
  }

  def testUrlPredicateValid: Property =
    for {
      url <- networkGens.genUrlString.log("url")
    } yield {
      val expected = true
      val actual   = Url.predicate(url)

      actual ==== expected
    }

  def testUrlPredicateInvalid: Property =
    for {
      input <- Gen
                 .frequency1(
                   30 ->
                     Gen.string(Gen.element1('%', '^', '<', '>', '[', ']', '`', '{', '}'), Range.linear(1, 5)),
                   70 -> (for {
                     scheme    <- Gen.string(Gen.alpha, Range.linear(3, 10)).map {
                                    case "http" | "https" | "ftp" | "file" => "blah"
                                    case anythingElse => anythingElse
                                  }
                     authority <- Gen
                                    .string(Gen.alphaNum, Range.linear(3, 10))
                                    .list(Range.linear(1, 4))
                                    .map(_.mkString("."))
                   } yield s"$scheme/$authority"),
                 )
                 .log("input")
    } yield {

      val expected = false
      val actual   = Url.predicate(input)

      actual ==== expected

    }

  def testUrlFromValid: Property =
    for {
      url <- networkGens.genUrlString.log("url")
    } yield {
      val expected = Url.unsafeFrom(url).asRight
      val actual   = Url.from(url)

      Result.all(
        List(
          actual ==== expected
        )
      )
    }

  def testUrlFromInvalid: Property =
    for {
      input <- Gen
                 .frequency1(
                   30 ->
                     Gen.string(Gen.element1('%', '^', '<', '>', '[', ']', '`', '{', '}'), Range.linear(1, 5)),
                   70 -> (for {
                     scheme    <- Gen.string(Gen.alpha, Range.linear(3, 10)).map {
                                    case "http" | "https" | "ftp" | "file" => "blah"
                                    case anythingElse => anythingElse
                                  }
                     authority <- Gen
                                    .string(Gen.alphaNum, Range.linear(3, 10))
                                    .list(Range.linear(1, 4))
                                    .map(_.mkString("."))
                   } yield s"$scheme/$authority"),
                 )
                 .log("input")
    } yield {

      val expected = Url.validate(input)

      val expectedStartWith = s"Invalid Url value: [$input]"

      val actual = Url.from(input)

      Result.all(
        List(
          actual
            .left
            .map(err => {
              Result
                .assert(err.startsWith(expectedStartWith))
                .log(s"The error message, $err, doesn't start with '$expectedStartWith'")
            })
            .swap
            .getOrElse(Result.failure.log(s"expected Left(error message) but get $actual instead")),
          actual ==== expected,
        )
      )

    }

  def testUrlUnsafeFromValid: Property =
    for {
      url <- networkGens.genUrlString.log("url")
    } yield {
      val expected = Url.from(url)
      val actual   = Url.unsafeFrom(url)

      Result.all(
        List(
          actual.asRight ==== expected,
          expected.fold(err => Result.failure.log(s"expected Url.from(url) results in error: $err"), actual ==== _),
        )
      )
    }

  def testUrlUnsafeFromInvalid: Property =
    for {
      input <- Gen
                 .frequency1(
                   30 ->
                     Gen.string(Gen.element1('%', '^', '<', '>', '[', ']', '`', '{', '}'), Range.linear(1, 5)),
                   70 -> (for {
                     scheme    <- Gen.string(Gen.alpha, Range.linear(3, 10)).map {
                                    case "http" | "https" | "ftp" | "file" => "blah"
                                    case anythingElse => anythingElse
                                  }
                     authority <- Gen
                                    .string(Gen.alphaNum, Range.linear(3, 10))
                                    .list(Range.linear(1, 4))
                                    .map(_.mkString("."))
                   } yield s"$scheme/$authority"),
                 )
                 .log("input")
    } yield {
      val expectedMessage = Url.validate(input)

      try {
        val _ = Url.unsafeFrom(input)
        Result
          .failure
          .log(
            s"""IllegalArgumentException was expected from Url.unsafeFrom($input), but it was not thrown."""
          )
      } catch {
        case ex: IllegalArgumentException =>
          val expectedStartWith = s"Invalid Url value: [$input]"

          val actual = ex.getMessage
          Result.all(
            List(
              Result.assert(ex.getMessage.startsWith(expectedStartWith)).log(s"The error message doesn't start with '$expectedStartWith'"),
              expectedMessage
                .left
                .map(expected => actual ==== expected)
                .swap
                .getOrElse(
                  Result
                    .failure
                    .log(
                      s"""expected error message generation failed. Expected Left(error message) but get $expectedMessage instead.
                         |  NOTE: It's not the failure of Url.unsafeFrom but Url.validate.""".stripMargin
                    )
                ),
            )
          )
      }

    }

  def testUrlValue: Property =
    for {
      uri <- networkGens.genUrlString.log("uri")
    } yield {
      val expected = uri
      val actual   = Url.unsafeFrom(uri).value

      actual ==== expected
    }

  def testUrlUnapply: Property =
    for {
      uri <- networkGens.genUrlString.log("uri")
    } yield {
      val expected = uri
      Url.unsafeFrom(uri) match {
        case Url(actual) =>
          actual ==== expected
      }
    }

//  def testUrlToURL: Property =
//    for {
//      uri <- networkGens.genUrlString.log("uri")
//    } yield {
//      @SuppressWarnings(Array("org.wartremover.warts.JavaNetURLConstructors"))
//      val expected = new URL(uri)
//      val actual   = Url.unsafeFrom(uri).toURL
//
//      actual ==== expected
//    }

  def testUrlToUri: Property =
    for {
      uri <- networkGens.genUrlString.log("uri")
    } yield {
      val expected = Uri.unsafeFrom(uri)
      val actual   = Url.unsafeFrom(uri).toUri

      actual ==== expected
    }

  def testUrlToURI: Property =
    for {
      uri <- networkGens.genUrlString.log("uri")
    } yield {
      val expected = new URI(uri)
      val actual   = Url.unsafeFrom(uri).toURI

      actual ==== expected
    }

  inline def runNetworkIsValidateUrl(inline a: String): Boolean = ${ networkCompat.isValidateUrl('a) }

  def testNetworkIsValidateUrlValid: Result = {
    val expected = true
    val actual   = runNetworkIsValidateUrl("https://github.com/kevin-lee/refined4s")
    (actual ==== expected)
      .log("""network.isValidateUrl("https://github.com/kevin-lee/refined4s") should return true but it returned false.""")
  }

  def testNetworkIsValidateUrlInvalid: Result = {
    import scala.compiletime.testing.{typeChecks, typeCheckErrors}
    val expected = false

    val actual1 = typeChecks("""runNetworkIsValidateUrl("%^<>[]`{}")""")
    val actual2 = typeChecks("""runNetworkIsValidateUrl("blah://www.google.com")""")

    val expectedErrorMessage1 = """Invalid Url value: [%^<>[]`{}]. Malformed escape pair at index 0: %^<>[]`{}"""
    val expectedErrorMessage2 = """Invalid Url value: [blah://www.google.com]. invalid schema"""

    val actualErrorMessage1 = typeCheckErrors("""runNetworkIsValidateUrl("%^<>[]`{}")""").map(_.message).mkString
    val actualErrorMessage2 = typeCheckErrors("""runNetworkIsValidateUrl("blah://www.google.com")""").map(_.message).mkString
    Result.all(
      List(
        (actual1 ==== expected)
          .log("""network.isValidateUrl("%^<>[]`{}") should return false but it returned true"""),
        (actual2 ==== expected)
          .log("""network.isValidateUrl("blah://www.google.com") should return false but it returned true"""),
        (actualErrorMessage1 ==== expectedErrorMessage1),
        (actualErrorMessage2 ==== expectedErrorMessage2),
      )
    )
  }

  def testNetworkIsValidateUrlWithInvalidLiteral: Result = {
    import scala.compiletime.testing.typeCheckErrors
    val expectedMessage = UriValidator.UnexpectedLiteralErrorMessageForUrl

    val actual = typeCheckErrors(
      """
        val a = "blah"
        runNetworkIsValidateUrl(a)
      """
    )

    val actualErrorMessage = actual.map(_.message).mkString
    (actualErrorMessage ==== expectedMessage)
  }

}
