package refined4s.modules.pureconfig.derivation

import pureconfig.ConfigReader
import refined4s.NewtypeBase

/** @author Kevin Lee
  * @since 2023-12-14
  */
trait PureconfigNewtypeConfigReader[A: ConfigReader] {
  self: NewtypeBase[A] =>

  given derivedConfigReader: ConfigReader[Type] = deriving[ConfigReader]
}
