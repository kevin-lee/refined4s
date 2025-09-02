package refined4s.modules.doobie.derivation.types

import extras.doobie.RunWithDb
import extras.hedgehog.ce3.CatsEffectRunner
import hedgehog.runner.*

/** @author Kevin Lee
  * @since 2023-12-27
  */
object allSpec extends Properties, CatsEffectRunner, RunWithDb {

  object numericSpecWithAll extends numericSpec {
    override protected val numericTypeClasses: numeric = refined4s.modules.doobie.derivation.types.all
  }

  object stringsSpecWithAll extends stringsSpec {
    override protected val stringsTypeClasses: strings = refined4s.modules.doobie.derivation.types.all
  }

  object networkSpecWithAll extends networkSpec {
    override protected val networkTypeClasses: network = refined4s.modules.doobie.derivation.types.all
  }

  override def tests: List[Test] =
    numericSpecWithAll.allTests ++ stringsSpecWithAll.allTests ++ networkSpecWithAll.allTests

}
