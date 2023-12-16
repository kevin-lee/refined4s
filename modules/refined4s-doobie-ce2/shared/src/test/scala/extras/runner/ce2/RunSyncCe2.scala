package extras.runner.ce2

import cats.effect.IO
import extras.runner.RunSync

/** Copied from [extras](https://github.com/kevin-lee/extras/tree/d7d64a7276fc2041463323109ee7dcd3c2bd2b69/modules/extras-doobie-tools-ce2/shared/src/test/scala/extras)
  * @author Kevin Lee
  * @since 2022-11-27
  */
trait RunSyncCe2 {

  type F[A] = IO[A]
  val F: IO.type = IO

  given RunSyncIo: RunSync[IO] with {
    override def runSync[A](fa: IO[A]): A = fa.unsafeRunSync()
  }
}
