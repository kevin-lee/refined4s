package refined4s.types

import cats.syntax.all.*
import hedgehog.*
import hedgehog.runner.*

import java.net.{URI, URL}

/** @author Kevin Lee
  * @since 2024-09-05
  */
trait networkCompatSpec {

  import refined4s.types.network.*

  def compatTests: List[Test] = List(
    property("test Uri.toUrl", testUriToUrl),
    property("test Uri.toURL", testUriToURL),
    //
    example("test Url(valid URL String)", testUrlApply),
    example("test Url(URL)", testUrlApplyURL),
    example("test Url(invalid URL String)", testUrlApplyInvalid),
    property("test Url.from(valid)", testUrlFromValid),
    property("test Url.from(invalid)", testUrlFromInvalid),
    property("test Url.unsafeFrom(valid)", testUrlUnsafeFromValid),
    property("test Url.unsafeFrom(invalid)", testUrlUnsafeFromInvalid),
    property("test Url.value", testUrlValue),
    property("test Url.unapply", testUrlUnapply),
    property("test Url.toURL", testUrlToURL),
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

  def testUriToURL: Property =
    for {
      uri <- networkGens.genUrlString.log("uri")
    } yield {
      @SuppressWarnings(Array("org.wartremover.warts.JavaNetURLConstructors"))
      val expected = new URL(uri)
      val actual   = Uri.unsafeFrom(uri).toURL

      actual ==== expected
    }

  //

  def testUrlApply: Result = {
    @SuppressWarnings(Array("org.wartremover.warts.JavaNetURLConstructors"))
    val expected = new URL("https://github.com/kevin-lee/refined4s")
    val actual   = Url("https://github.com/kevin-lee/refined4s")
    Result.all(
      List(
        actual.value ==== expected.toString,
        actual.toURL ==== expected,
      )
    )
  }

  def testUrlApplyURL: Result = {
    @SuppressWarnings(Array("org.wartremover.warts.JavaNetURLConstructors"))
    val url      = new URL("https://github.com/kevin-lee/refined4s")
    val expected = url
    val actual   = Url(url)
    Result.all(
      List(
        actual.value ==== expected.toString,
        actual.toURL ==== expected,
      )
    )
  }

  def testUrlApplyInvalid: Result = {
    import scala.compiletime.testing.typeChecks

    val shouldNotCompile1 = !typeChecks(
      """
        import network.*
        Url("%^<>[]`{}")
      """
    )

    val shouldNotCompile2 = !typeChecks(
      """
        import network.*
        Url("blah://www.google.com")
      """
    )

    Result.all(
      List(
        Result.assert(shouldNotCompile1).log("""Url("%^<>[]`{}") should have failed compilation but it succeeded."""),
        Result.assert(shouldNotCompile2).log("""Url("blah://www.google.com") should have failed compilation but it succeeded."""),
      )
    )
  }

  def testUrlFromValid: Property =
    for {
      uri <- networkGens.genUrlString.log("uri")
    } yield {
      val expected = Url.unsafeFrom(uri).asRight
      val actual   = Url.from(uri)

      @SuppressWarnings(Array("org.wartremover.warts.JavaNetURLConstructors"))
      val expectedUrl = new URL(uri).asRight
      val actualUrl   = actual.map(_.toURL)

      Result.all(
        List(
          actual ==== expected,
          actualUrl ==== expectedUrl,
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
      val expected = s"Invalid value: [$input]. It must be a URL String".asLeft
      val actual   = Url.from(input)

      actual ==== expected
    }

  def testUrlUnsafeFromValid: Property =
    for {
      uri <- networkGens.genUrlString.log("uri")
    } yield {
      val expected = Url.from(uri)
      val actual   = Url.unsafeFrom(uri)

      @SuppressWarnings(Array("org.wartremover.warts.JavaNetURLConstructors"))
      val expectedUrl = new URL(uri)
      val actualUrl   = actual.toURL

      Result.all(
        List(
          actual.asRight ==== expected,
          actualUrl ==== expectedUrl,
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
      val expected = s"Invalid value: [$input]. It must be a URL String"

      try {
        val _ = Url.unsafeFrom(input)
        Result
          .failure
          .log(
            s"""IllegalArgumentException was expected from Url.unsafeFrom($input), but it was not thrown."""
          )
      } catch {
        case ex: IllegalArgumentException =>
          ex.getMessage ==== expected
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

  def testUrlToURL: Property =
    for {
      uri <- networkGens.genUrlString.log("uri")
    } yield {
      @SuppressWarnings(Array("org.wartremover.warts.JavaNetURLConstructors"))
      val expected = new URL(uri)
      val actual   = Url.unsafeFrom(uri).toURL

      actual ==== expected
    }

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
    val expected = false
    val actual1  = runNetworkIsValidateUrl("%^<>[]`{}")
    val actual2  = runNetworkIsValidateUrl("blah://www.google.com")
    Result.all(
      List(
        (actual1 ==== expected)
          .log("""network.isValidateUrl("%^<>[]`{}") should return false but it returned true"""),
        (actual2 ==== expected)
          .log("""network.isValidateUrl("blah://www.google.com") should return false but it returned true"""),
      )
    )
  }

  def testNetworkIsValidateUrlWithInvalidLiteral: Result = {
    import scala.compiletime.testing.typeCheckErrors
    val expectedMessage = network.UnexpectedLiteralErrorMessage

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
