package refined4s.modules.pureconfig.derivation.types

import refined4s.*

/** @author Kevin Lee
  * @since 2023-12-26
  */
trait all extends numeric, strings, network
object all extends all
