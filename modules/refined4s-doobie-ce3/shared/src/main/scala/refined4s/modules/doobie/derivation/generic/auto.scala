package refined4s.modules.doobie.derivation.generic

import cats.*
import doobie.{Get, Put}
import refined4s.{Coercible, RefinedCtor}

/** @author Kevin Lee
  * @since 2023-12-16
  */
trait auto {

  given derivedPut[A, B](using coercible: Coercible[A, B], put: Put[B]): Put[A] =
    put.contramap(coercible(_))

  given derivedRefinedGet[A, B](using refinedCtor: RefinedCtor[B, A], getA: Get[A], showA: Show[A]): Get[B] =
    getA.temap(refinedCtor.create)

  given derivedNewtypeGet[A, B](using coercible: Coercible[A, B], getA: Get[A]): Get[B] =
    getA.map(coercible(_))

}
object auto extends auto
