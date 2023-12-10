package refined4s.types

import refined4s.*

import cats.syntax.all.*
import hedgehog.*
import hedgehog.runner.*

import java.net.URI

/** @author Kevin Lee
  * @since 2023-12-09
  */
object networkSpec extends Properties {
  import network.*

  override def tests: List[Test] = List(
    example("test Uri(valid URI String)", testUriApply),
    example("test Uri(invalid URI String)", testUriApplyInvalid),
    property("test Uri.from(valid)", testUriFromValid),
    property("test Uri.from(invalid)", testUriFromInvalid),
    property("test Uri.unsafeFrom(valid)", testUriUnsafeFromValid),
    property("test Uri.unsafeFrom(invalid)", testUriUnsafeFromInvalid),
    property("test Uri.value", testUriValue),
    property("test Uri.unapply", testUriUnapply),
    property("test Uri.toURI", testUriToURI),
    example("test network.isValidateUri(valid URI String)", testNetworkIsValidateUriValid),
    example("test network.isValidateUri(invalid URI String)", testNetworkIsValidateUriInvalid),
    example("test network.isValidateUri(non-String literal)", testNetworkIsValidateUriWithInvalidLiteral),
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

  def testUriApplyInvalid: Result = {
    import scala.compiletime.testing.*

    val shouldNotCompile = !typeChecks(
      """
          import network.*
          Uri("%^<>[]`{}")
        """
    )
    Result.assert(shouldNotCompile).log("""Uri("%^<>[]`{}") should have failed compilation but it succeeded.""")
  }

  def testUriFromValid: Property =
    for {
      scheme    <- Gen
                     .frequency1(
                       30 -> Gen.element1("http", "https", "ftp", "file"),
                       70 -> Gen.string(Gen.alpha, Range.linear(3, 10)),
                     )
                     .log("scheme")
      authority <- Gen
                     .string(Gen.alphaNum, Range.linear(3, 10))
                     .list(Range.linear(1, 4))
                     .map(_.mkString("."))
                     .log("authority")
      path      <- Gen
                     .frequency1(
                       40 -> Gen.constant(""),
                       60 -> Gen
                         .string(Gen.alphaNum, Range.linear(1, 10))
                         .list(Range.linear(1, 5))
                         .map(_.mkString("/")),
                     )
                     .log("path")
      uri       <- Gen.constant(s"$scheme://$authority${if (path.isEmpty) "" else "/" + path}").log("uri")
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

  def testUriFromInvalid: Property =
    for {
      input <- Gen.string(Gen.element1('%', '^', '<', '>', '[', ']', '`', '{', '}'), Range.linear(1, 5)).log("input")
    } yield {
      val expected = s"Invalid value: [$input]. It has to be a URI but got [$input]".asLeft
      val actual   = Uri.from(input)

      actual ==== expected
    }

  def testUriUnsafeFromValid: Property =
    for {
      scheme    <- Gen
                     .frequency1(
                       30 -> Gen.element1("http", "https", "ftp", "file"),
                       70 -> Gen.string(Gen.alpha, Range.linear(3, 10)),
                     )
                     .log("scheme")
      authority <- Gen
                     .string(Gen.alphaNum, Range.linear(3, 10))
                     .list(Range.linear(1, 4))
                     .map(_.mkString("."))
                     .log("authority")
      path      <- Gen
                     .frequency1(
                       40 -> Gen.constant(""),
                       60 -> Gen
                         .string(Gen.alphaNum, Range.linear(1, 10))
                         .list(Range.linear(1, 5))
                         .map(_.mkString("/")),
                     )
                     .log("path")
      uri       <- Gen.constant(s"$scheme://$authority${if (path.isEmpty) "" else "/" + path}").log("uri")
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

  def testUriUnsafeFromInvalid: Property =
    for {
      input <- Gen.string(Gen.element1('%', '^', '<', '>', '[', ']', '`', '{', '}'), Range.linear(1, 5)).log("input")
    } yield {
      val expected = s"Invalid value: [$input]. It has to be a URI but got [$input]"

      try {
        Uri.unsafeFrom(input)
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
      scheme    <- Gen
                     .frequency1(
                       30 -> Gen.element1("http", "https", "ftp", "file"),
                       70 -> Gen.string(Gen.alpha, Range.linear(3, 10)),
                     )
                     .log("scheme")
      authority <- Gen
                     .string(Gen.alphaNum, Range.linear(3, 10))
                     .list(Range.linear(1, 4))
                     .map(_.mkString("."))
                     .log("authority")
      path      <- Gen
                     .frequency1(
                       40 -> Gen.constant(""),
                       60 -> Gen
                         .string(Gen.alphaNum, Range.linear(1, 10))
                         .list(Range.linear(1, 5))
                         .map(_.mkString("/")),
                     )
                     .log("path")
      uri       <- Gen.constant(s"$scheme://$authority${if (path.isEmpty) "" else "/" + path}").log("uri")
    } yield {
      val expected = uri
      val actual   = Uri.unsafeFrom(uri).value

      actual ==== expected
    }

  def testUriUnapply: Property =
    for {
      scheme    <- Gen
                     .frequency1(
                       30 -> Gen.element1("http", "https", "ftp", "file"),
                       70 -> Gen.string(Gen.alpha, Range.linear(3, 10)),
                     )
                     .log("scheme")
      authority <- Gen
                     .string(Gen.alphaNum, Range.linear(3, 10))
                     .list(Range.linear(1, 4))
                     .map(_.mkString("."))
                     .log("authority")
      path      <- Gen
                     .frequency1(
                       40 -> Gen.constant(""),
                       60 -> Gen
                         .string(Gen.alphaNum, Range.linear(1, 10))
                         .list(Range.linear(1, 5))
                         .map(_.mkString("/")),
                     )
                     .log("path")
      uri       <- Gen.constant(s"$scheme://$authority${if (path.isEmpty) "" else "/" + path}").log("uri")
    } yield {
      val expected = uri
      Uri.unsafeFrom(uri) match {
        case Uri(actual) =>
          actual ==== expected
      }
    }

  def testUriToURI: Property =
    for {
      scheme    <- Gen
                     .frequency1(
                       30 -> Gen.element1("http", "https", "ftp", "file"),
                       70 -> Gen.string(Gen.alpha, Range.linear(3, 10)),
                     )
                     .log("scheme")
      authority <- Gen
                     .string(Gen.alphaNum, Range.linear(3, 10))
                     .list(Range.linear(1, 4))
                     .map(_.mkString("."))
                     .log("authority")
      path      <- Gen
                     .frequency1(
                       40 -> Gen.constant(""),
                       60 -> Gen
                         .string(Gen.alphaNum, Range.linear(1, 10))
                         .list(Range.linear(1, 5))
                         .map(_.mkString("/")),
                     )
                     .log("path")
      uri       <- Gen.constant(s"$scheme://$authority${if (path.isEmpty) "" else "/" + path}").log("uri")
    } yield {
      val expected = new URI(uri)
      val actual   = Uri.unsafeFrom(uri).toURI

      actual ==== expected
    }

  inline def runNetworkIsValidateUri(inline a: String): Boolean = ${ network.isValidateUri('a) }

  def testNetworkIsValidateUriValid: Result = {
    val expected = true
    val actual   = runNetworkIsValidateUri("https://github.com/kevin-lee/refined4s")
    (actual ==== expected)
      .log("""network.isValidateUri("https://github.com/kevin-lee/refined4s") should return true but it returned false.""")
  }

  def testNetworkIsValidateUriInvalid: Result = {
    val expected = false
    val actual   = runNetworkIsValidateUri("%^<>[]`{}")
    (actual ==== expected)
      .log("""network.isValidateUri("%^<>[]`{}") should return false but it returned true""")
  }

  def testNetworkIsValidateUriWithInvalidLiteral: Result = {
    import scala.compiletime.testing.*
    val expectedMessage = network.UnexpectedLiteralErrorMessage

    val actual = typeCheckErrors(
      """
        val a = "blah"
        runNetworkIsValidateUri(a)
      """
    )

    val actualErrorMessage = actual.map(_.message).mkString
    (actualErrorMessage ==== expectedMessage)
  }

}
