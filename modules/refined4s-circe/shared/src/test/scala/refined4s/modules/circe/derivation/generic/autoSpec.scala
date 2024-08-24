package refined4s.modules.circe.derivation.generic

import hedgehog.*
import hedgehog.runner.*
import refined4s.*

/** @author Kevin Lee
  * @since 2023-12-11
  */
object autoSpec extends Properties {

  override def tests: List[Test] =
    numericSpec.allTests ++ stringsSpec.allTests ++ networkSpec.allTests ++ CustomTypeSpec.allTests

}
