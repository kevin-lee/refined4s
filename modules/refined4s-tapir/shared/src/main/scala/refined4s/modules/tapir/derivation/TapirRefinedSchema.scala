package refined4s.modules.tapir.derivation

import refined4s.RefinedBase
import sttp.tapir.Schema

/** @author Kevin Lee
  * @since 2024-04-03
  */
trait TapirRefinedSchema[A: Schema] {
  self: RefinedBase[A] =>

  given derivedSchema: Schema[Type] = summon[Schema[A]].map[Type](from(_).toOption)(_.value)
}
