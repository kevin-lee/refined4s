package refined4s.modules.cats.derivation

import cats.*
import refined4s.*
import refined4s.types.all.*

/** @author Kevin Lee
  * @since 2023-12-07
  */

trait instances {

  given eqDerived[A, B](using coercible: Coercible[A, B], eqB: Eq[B]): Eq[A] = (a1, a2) => eqB.eqv(coercible(a1), coercible(a2))

  given showDerived[A, B](using coercible: Coercible[A, B], showB: Show[B]): Show[A] = showB.show.compose(coercible(_))(_)

}
object instances extends instances
