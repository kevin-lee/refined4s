package refined4s.modules.circe.derivation.types

/** @author Kevin Lee
  * @since 2024-10-28
  */
trait NumericTestValues {

  /** From JavaScript's Number.MIN_SAFE_INTEGER
    */
  val MinLongValue: Long = -9007199254740991L

  /** From JavaScript's Number.MAX_SAFE_INTEGER
    */
  val MaxLongValue: Long = 9007199254740991L
}
