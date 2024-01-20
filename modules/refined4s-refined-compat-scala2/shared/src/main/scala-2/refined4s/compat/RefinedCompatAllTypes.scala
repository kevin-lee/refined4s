package refined4s.compat

import eu.timepit.refined.types.AllTypes
import eu.timepit.refined.types.AllTypesBinCompat1
import eu.timepit.refined.types.AllTypesBinCompat2
import eu.timepit.refined.types.AllTypesBinCompat3

/** @author Kevin Lee
  * @since 2024-01-19
  */
trait RefinedCompatAllTypes extends AllTypes with AllTypesBinCompat1 with AllTypesBinCompat2 with AllTypesBinCompat3
object RefinedCompatAllTypes extends RefinedCompatAllTypes
