package refined4s.modules.cats.derivation.generic

import cats.*
import refined4s.*

/** @author Kevin Lee
  * @since 2023-12-07
  */
trait auto {
  given derivedEq[A, B](using coercible: Coercible[A, B], eqB: Eq[B]): Eq[A] =
    refined4s.modules.cats.syntax.contraCoercible(eqB)

  given derivedOrder[A, B](using coercible: Coercible[A, B], orderB: Order[B]): Order[A] =
    refined4s.modules.cats.syntax.contraCoercible(orderB)

  given derivedShow[A, B](using coercible: Coercible[A, B], showB: Show[B]): Show[A] =
    refined4s.modules.cats.syntax.contraCoercible(showB)
}
object auto extends auto
