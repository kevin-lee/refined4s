package refined4s

import compiletime.*

/** Copied from https://gist.github.com/Kevin-Lee/158d3d5c3e036560f8962087a34c95c5 and modified.
  * @author Kevin Lee
  * @since 2022-03-23
  */
trait Refined[A] extends RefinedBase[A] {

  @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
  inline def apply(a: A): Type =
    inline if predicate(a) then a.asInstanceOf[Type] // scalafix:ok DisableSyntax.asInstanceOf
    else error("Invalid value: [" + codeOf(a) + "]. " + invalidReason(a))

}
