package hedgehog.extra

import hedgehog._

/** @author Kevin Lee
  * @since 2021-04-06
  */
trait Gens {

  def genCharByRange(range: List[(Int, Int)]): Gen[Char] =
    Gen.frequencyUnsafe(
      range.map {
        case (from, to) =>
          (to + 1 - from) -> Gen.char(from.toChar, to.toChar)
      }
    )

  def genNonWhitespaceChar: Gen[Char] =
    genCharByRange(common.NonWhitespaceCharRange)

  @SuppressWarnings(Array("org.wartremover.warts.Throw"))
  def genUnsafeNonWhitespaceString(maxLength: Int): Gen[String] =
    if (maxLength > 0)
      Gen.string(
        genNonWhitespaceChar,
        Range.linear(1, maxLength),
      )
    else
      throw new IllegalArgumentException( // scalafix:ok DisableSyntax.throw
        s"maxLength for genUnsafeNonWhitespaceString should be a positive Int (> 0). [maxLength: ${maxLength.toString}]"
      )

  @SuppressWarnings(Array("org.wartremover.warts.Throw"))
  def genUnsafeNonWhitespaceStringMinMax(minLength: Int, maxLength: Int): Gen[String] =
    if (minLength > 0) {
      if (minLength <= maxLength)
        Gen.string(
          genNonWhitespaceChar,
          Range.linear(minLength, maxLength),
        )
      else
        throw new IllegalArgumentException( // scalafix:ok DisableSyntax.throw
          "maxLength for genUnsafeNonWhitespaceStringMinMax is less than minLength. " +
            "maxLength for genUnsafeNonWhitespaceStringMinMax should be greater than or equal to minLength (minLength <= maxLength). " +
            s"[minLength: ${minLength.toString}, maxLength: ${maxLength.toString}]"
        )
    } else
      throw new IllegalArgumentException( // scalafix:ok DisableSyntax.throw
        s"minLength for genUnsafeNonWhitespaceStringMinMax should be a positive Int (> 0). [minLength: ${minLength.toString}]"
      )

}
object Gens extends Gens
