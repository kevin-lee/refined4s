package refined4s.compat

import eu.timepit.refined
import eu.timepit.refined.api.{Refined, RefinedTypeOps}
import eu.timepit.refined.types.AllTypes
import eu.timepit.refined.types.AllTypesBinCompat1
import eu.timepit.refined.types.AllTypesBinCompat2
import eu.timepit.refined.types.AllTypesBinCompat3

/** @author Kevin Lee
  * @since 2024-01-19
  */
trait RefinedCompatAllTypes extends AllTypes with AllTypesBinCompat1 with AllTypesBinCompat2 with AllTypesBinCompat3 {

  final type Uri = allTypes.Uri
  final val Uri = allTypes.Uri

  final type Url = allTypes.Url
  final val Url = allTypes.Url

  final type Uuid = allTypes.Uuid
  final val Uuid = allTypes.Uuid

}
object RefinedCompatAllTypes extends RefinedCompatAllTypes

private[compat] object allTypes {

  type Uri = String Refined refined.string.Uri
  object Uri extends RefinedTypeOps[Uri, String]

  type Url = String Refined refined.string.Url
  object Url extends RefinedTypeOps[Url, String]

  type Uuid = String Refined refined.string.Uuid
  object Uuid extends RefinedTypeOps[Uuid, String]

}
