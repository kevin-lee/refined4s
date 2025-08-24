package refined4s.types

import orphan.OrphanCats
import refined4s.Coercible

/** @author Kevin Lee
  * @since 2025-08-21
  */
private[types] object internalDef extends OrphanCats {

  inline def contraCoercible[F[*], A, B, G[*[*]]: CatsContravariant](
    inline fb: F[B]
  )(using inline contravariant: cats.Contravariant[F], coercible: Coercible[A, B]): F[A] =
    contravariant.contramap[B, A](fb)(coercible(_))

//  inline def contraCoercible[F[*], A, B, G[*[*]]: CatsContravariant](
//    inline fb: F[B]
//  )(using inline catsContravariant: G[F], coercible: Coercible[A, B]): F[A] = {
//    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
//    val contravariant = catsContravariant.asInstanceOf[cats.Contravariant[F]]
//    contravariant.contramap[B, A](fb)(coercible(_))
//  }

}
