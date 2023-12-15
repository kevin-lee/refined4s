package refined4s.modules.pureconfig.derivation

import pureconfig.ConfigWriter
import refined4s.NewtypeBase

/** @author Kevin Lee
  * @since 2023-12-15
  */
trait PureconfigConfigWriter[A: ConfigWriter] {
  self: NewtypeBase[A] =>

  given derivedConfigWriter: ConfigWriter[Type] = deriving[ConfigWriter]
}
