package refined4s

/** @author Kevin Lee
  * @since 2023-12-05
  */
trait RefinedCtor[T, A] {
  def create(a: A): Either[String, T]
}
object RefinedCtor {
  def apply[T, A](using refinedCtor: RefinedCtor[T, A]): RefinedCtor[T, A] = refinedCtor
}
