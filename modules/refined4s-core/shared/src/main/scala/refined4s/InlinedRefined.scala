package refined4s

import scala.compiletime.*

/** @author Kevin Lee
  * @since 2023-08-12
  */
trait InlinedRefined[A] extends RefinedBase[A] {

  inline def inlinedInvalidReason(inline a: A): String

  inline def inlinedPredicate(inline a: A): Boolean

  inline def apply(inline a: A): Type =
    inline if inlinedPredicate(a) then a.asInstanceOf[Type] // scalafix:ok DisableSyntax.asInstanceOf
    else error("Invalid value: [" + codeOf(a) + "]. " + inlinedInvalidReason(a))

}
