package refined4s

import hedgehog.*
import hedgehog.runner.*

/** @author Kevin Lee
  * @since 2023-12-29
  */
object CanBeOrderedSpec extends Properties {
  override def tests: List[Test] = List(
    property("test Ordering from CanBeOrdered", testOrdering),
    property("test Ordered from CanBeOrdered", testOrdered),
  )

  def testOrdering: Property =
    for {
      n1 <- Gen.int(Range.linear(Int.MinValue, Int.MaxValue)).log("n1")
      n2 <- Gen.int(Range.linear(Int.MinValue, Int.MaxValue)).log("n2")
    } yield {
      val input1 = MyNum(n1)
      val input2 = MyNum(n2)

      val expected = n1.compare(n2)
      Result.diff(input1, input2)(Ordering[MyNum].compare(_, _) == expected)
    }

  def testOrdered: Property =
    for {
      n1 <- Gen.int(Range.linear(Int.MinValue, Int.MaxValue)).log("n1")
      n2 <- Gen.int(Range.linear(Int.MinValue, Int.MaxValue)).log("n2")
    } yield {
      val input1 = MyNum(n1)
      val input2 = MyNum(n2)

      val expected = n1.compare(n2)
      Result.diff(input1, input2: MyNum)(_.compare(_) == expected)
    }

  type MyNum = MyNum.Type
  object MyNum extends Newtype[Int], CanBeOrdered[Int]

}
