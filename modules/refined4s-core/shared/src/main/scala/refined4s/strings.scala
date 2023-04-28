package refined4s

import scala.annotation.targetName

/** @author Kevin Lee
  * @since 2023-04-25
  */
trait strings {
  type NonEmptyString = NonEmptyString.Type

  @SuppressWarnings(Array("org.wartremover.warts.Equals"))
  object NonEmptyString extends Refined[String] {

    override inline def invalidReason(a: String): String =
      expectedMessage("a non-empty String")

    override inline def predicate(a: String): Boolean = a != ""

    extension (thisNes: NonEmptyString) {
      @targetName("plus")
      def ++(thatNes: NonEmptyString): NonEmptyString = NonEmptyString.unsafeFrom(thisNes.value + thatNes.value)
    }
  }
}
object strings extends strings
