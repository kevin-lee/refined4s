package refined4s.modules.tapir.derivation

import refined4s.NewtypeBase
import sttp.tapir.Schema

/** @author Kevin Lee
  * @since 2024-04-03
  */
trait TapirNewtypeSchema[A: Schema] {
  self: NewtypeBase[A] =>

  given derivedSchema: Schema[Type] = deriving[Schema]
}
