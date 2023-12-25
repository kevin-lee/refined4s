package refined4s.modules.cats.derivation

import cats.*
import refined4s.*

/** @author Kevin Lee
  * @since 2023-12-07
  */

trait instances {

  inline def contraCoercible[F[*], A, B](fb: F[B])(using contravariant: Contravariant[F], coercible: Coercible[A, B]): F[A] =
    contravariant.contramap[B, A](fb)(coercible(_))

  inline given derivedEq[A, B](using coercible: Coercible[A, B], eqB: Eq[B]): Eq[A] = contraCoercible(eqB)

  inline given derivedShow[A, B](using coercible: Coercible[A, B], showB: Show[B]): Show[A] = contraCoercible(showB)

}
object instances extends instances
