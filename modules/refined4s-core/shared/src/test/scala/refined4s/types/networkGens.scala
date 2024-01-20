package refined4s.types

import hedgehog.*
import refined4s.types.all.*

/** @author Kevin Lee
  * @since 2023-12-12
  */
object networkGens {
  def genInvalidPortNumberInt: Gen[Int] =
    Gen.frequency1(
      20 -> Gen.int(Range.linear(-1, Int.MinValue)),
      80 -> Gen.int(Range.linear(65536, Int.MaxValue)),
    )

  def genPortNumberInt: Gen[Int] =
    Gen.int(Range.linear(0, 65535))

  def genPortNumber: Gen[PortNumber] =
    genPortNumberInt
      .map(PortNumber.unsafeFrom)

  def genInvalidSystemPortNumberInt: Gen[Int] =
    Gen.frequency1(
      30 -> Gen.int(Range.linear(Int.MinValue, -1)),
      70 -> Gen.int(Range.linear(1024, Int.MaxValue)),
    )

  def genSystemPortNumberInt: Gen[Int] =
    Gen.int(Range.linear(0, 1023))

  def genSystemPortNumber: Gen[SystemPortNumber] =
    genSystemPortNumberInt
      .map(SystemPortNumber.unsafeFrom)

  def genInvalidNonSystemPortNumberInt: Gen[Int] =
    Gen.frequency1(
      40 -> Gen.int(Range.linear(Int.MinValue, 1023)),
      60 -> Gen.int(Range.linear(65536, Int.MaxValue)),
    )

  def genNonSystemPortNumberInt: Gen[Int] =
    Gen
      .int(Range.linear(1024, 65535))

  def genNonSystemPortNumber: Gen[NonSystemPortNumber] =
    genNonSystemPortNumberInt
      .map(NonSystemPortNumber.unsafeFrom)

  def genInvalidUserPortNumberInt: Gen[Int] =
    Gen.frequency1(
      40 -> Gen.int(Range.linear(Int.MinValue, 1023)),
      60 -> Gen.int(Range.linear(49152, Int.MaxValue)),
    )

  def genUserPortNumberInt: Gen[Int] =
    Gen.int(Range.linear(1024, 49151))

  def genUserPortNumber: Gen[UserPortNumber] =
    genUserPortNumberInt
      .map(UserPortNumber.unsafeFrom)

  def genInvalidDynamicPortNumberInt: Gen[Int] =
    Gen.frequency1(
      40 -> Gen.int(Range.linear(Int.MinValue, 49151)),
      60 -> Gen.int(Range.linear(65536, Int.MaxValue)),
    )

  def genDynamicPortNumberInt: Gen[Int] =
    Gen.int(Range.linear(49152, 65535))

  def genDynamicPortNumber: Gen[DynamicPortNumber] =
    genDynamicPortNumberInt
      .map(DynamicPortNumber.unsafeFrom)

  def genUriString: Gen[String] =
    for {
      uri  <- genUriStringWithoutPath
      path <- Gen
                .frequency1(
                  40 -> Gen.constant(""),
                  60 -> Gen
                    .string(Gen.alphaNum, Range.linear(1, 10))
                    .list(Range.linear(1, 5))
                    .map(_.mkString("/")),
                )
      uriWithPath = s"$uri${if (path.isEmpty) "" else "/" + path}"
    } yield uriWithPath

  def genUriStringWithoutPath: Gen[String] =
    for {
      scheme    <- Gen
                     .frequency1(
                       30 -> Gen.element1("http", "https", "ftp", "file"),
                       70 -> Gen.string(Gen.alpha, Range.linear(3, 10)),
                     )
      authority <- Gen
                     .string(Gen.alphaNum, Range.linear(3, 10))
                     .list(Range.linear(1, 4))
                     .map(_.mkString("."))
      uri = s"$scheme://$authority"
    } yield uri

  def genUrlString: Gen[String] =
    for {
      uri  <- genUrlStringWithoutPath
      path <- Gen
                .frequency1(
                  40 -> Gen.constant(""),
                  60 -> Gen
                    .string(Gen.alphaNum, Range.linear(1, 10))
                    .list(Range.linear(1, 5))
                    .map(_.mkString("/")),
                )
      urlWithPath = s"$uri${if (path.isEmpty) "" else "/" + path}"
    } yield urlWithPath

  def genUrlStringWithoutPath: Gen[String] =
    for {
      scheme    <- Gen.element1("http", "https", "ftp", "file")
      authority <- Gen
                     .string(Gen.alphaNum, Range.linear(3, 10))
                     .list(Range.linear(1, 4))
                     .map(_.mkString("."))
      url = s"$scheme://$authority"
    } yield url

}
