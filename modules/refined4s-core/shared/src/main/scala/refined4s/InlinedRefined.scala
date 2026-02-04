package refined4s

import scala.compiletime.*

/** @author Kevin Lee
  * @since 2023-08-12
  */
trait InlinedRefined[@specialized A] extends RefinedBase[A] {

  inline def inlinedExpectedValue: String

  inline def inlinedPredicate(inline a: A): Boolean

  @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
  inline def apply(inline a: A): Type =
    inline if inlinedPredicate(a) then a.asInstanceOf[Type] // scalafix:ok DisableSyntax.asInstanceOf
    else error("Invalid value: [" + codeOf(a) + "]. It must be " + inlinedExpectedValue)

}
