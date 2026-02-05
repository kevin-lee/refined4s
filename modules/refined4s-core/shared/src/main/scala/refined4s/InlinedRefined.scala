package refined4s

import refined4s.internal.RefinedMacros

/** @author Kevin Lee
  * @since 2023-08-12
  */
trait InlinedRefined[@specialized A] extends RefinedBase[A] {

  inline def inlinedExpectedValue: String

  inline def inlinedPredicate(inline a: A): Boolean

  @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
  inline def apply(inline a: A): Type =
    inline if inlinedPredicate(a) then a.asInstanceOf[Type] // scalafix:ok DisableSyntax.asInstanceOf
    else RefinedMacros.internalError(a, inlinedExpectedValue)

}
