package refined4s.modules.pureconfig.derivation.types

import hedgehog.*
import hedgehog.runner.*

/** @author Kevin Lee
  * @since 2023-12-13
  */
object allSpec extends Properties {

  object numericSpecWithAll extends numericSpec {
    override protected val numericTypeClasses: numeric = refined4s.modules.pureconfig.derivation.types.all
  }

  object stringsSpecWithAll extends stringsSpec {
    override protected val stringsTypeClasses: strings = refined4s.modules.pureconfig.derivation.types.all
  }

  object networkSpecWithAll extends networkSpec {
    override protected val networkTypeClasses: network = refined4s.modules.pureconfig.derivation.types.all
  }

  object timeSpecWithAll extends timeSpec {
    override protected val timeTypeClasses: time = refined4s.modules.pureconfig.derivation.types.all
  }

  override def tests: List[Test] =
    numericSpecWithAll.allTests ++ stringsSpecWithAll.allTests ++ networkSpecWithAll.allTests ++ timeSpecWithAll.allTests

}
