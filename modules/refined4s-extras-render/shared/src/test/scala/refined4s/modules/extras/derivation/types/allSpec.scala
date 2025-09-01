package refined4s.modules.extras.derivation.types

import hedgehog.*
import hedgehog.runner.*

/** @author Kevin Lee
  * @since 2024-01-01
  */
@SuppressWarnings(Array("org.wartremover.warts.ToString"))
object allSpec extends Properties {

  object numericSpecWithAll extends numericSpec {
    override protected val numericTypeClasses: numeric = refined4s.modules.extras.derivation.types.all
  }

  object stringsSpecWithAll extends stringsSpec {
    override protected val stringsTypeClasses: strings = refined4s.modules.extras.derivation.types.all
  }

  object networkSpecWithAll extends networkSpec {
    override protected val networkTypeClasses: network = refined4s.modules.extras.derivation.types.all
  }

  object timeSpecWithAll extends timeSpec {
    override protected val timeTypeClasses: time = refined4s.modules.extras.derivation.types.all
  }

  override def tests: List[Test] =
    numericSpecWithAll.allTests ++ stringsSpecWithAll.allTests ++ networkSpecWithAll.allTests ++ timeSpecWithAll.allTests

}
