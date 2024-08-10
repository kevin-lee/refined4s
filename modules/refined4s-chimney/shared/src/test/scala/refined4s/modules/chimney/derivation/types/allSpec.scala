package refined4s.modules.chimney.derivation.types

import hedgehog.*
import hedgehog.runner.*

/** @author Kevin Lee
  * @since 2024-08-10
  */
object allSpec extends Properties {

  object numericSpecWithAll extends numericSpec {
    override protected val numericTypeClasses: numeric = refined4s.modules.chimney.derivation.types.all
  }
  object stringsSpecWithAll extends stringsSpec {
    override protected val stringsTypeClasses: strings = refined4s.modules.chimney.derivation.types.all
  }
  object networkSpecWithAll extends networkSpec {
    override protected val networkTypeClasses: network = refined4s.modules.chimney.derivation.types.all
  }

  override def tests: List[Test] = numericSpec.allTests ++ stringsSpec.allTests ++ networkSpec.allTests

}
