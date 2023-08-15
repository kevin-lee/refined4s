package refined4s

import hedgehog._
import hedgehog.runner._

/** @author Kevin Lee
  * @since 2023-08-14
  */
object InlinedRefinedSpec extends Properties {
  override def tests: List[Test] = List(
    example("test InlinedRefined with valid value", testInlinedRefined)
  )

  def testInlinedRefined: Result = {
    import InlinedRefinedType.*

    Something(1)
    Result.success
  }

//  def testInlinedRefined2: Result = {
//    import InlinedRefinedType.*
//
//    val a = 1
//    // This should not compile.
//    Something(a)
//    Result.failure
//  }

}
