package refined4s.types

import hedgehog.*
import hedgehog.runner.*

/** @author Kevin Lee
  * @since 2025-08-26
  */
object allSpec extends Properties {
  override def tests: List[Test] = networkSpec.tests ++ stringsSpec.tests ++ numericSpec.tests ++ timeSpec.tests
}
