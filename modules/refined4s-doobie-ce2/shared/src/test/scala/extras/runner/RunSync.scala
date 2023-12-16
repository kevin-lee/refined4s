package extras.runner

/**  Copied from [extras](https://github.com/kevin-lee/extras/tree/d7d64a7276fc2041463323109ee7dcd3c2bd2b69/modules/extras-doobie-tools-ce2/shared/src/test/scala/extras)
  * @author Kevin Lee
  * @since 2022-11-27
  */
trait RunSync[F[*]] {
  def runSync[A](fa: F[A]): A
}

object RunSync {
  def apply[F[*]: RunSync]: RunSync[F] = summon[RunSync[F]]
}
