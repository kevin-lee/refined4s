package refined4s.internal

/** @author Kevin Lee
  * @since 2026-03-07
  */
object types {

  type Identity[A] = A

  trait Bind[F[*]] {
    def pure[A](a: A): F[A]
    def map[A, B](fa: F[A])(f: A => B): F[B]
    def flatMap[A, B](fa: F[A])(f: A => F[B]): F[B]
  }
  object Bind {
    def apply[F[*]](using B: Bind[F]): Bind[F] = B

    given bindIdentity: Bind[Identity] with {
      def pure[A](a: A): A                   = a
      def map[A, B](fa: A)(f: A => B): B     = f(fa)
      def flatMap[A, B](fa: A)(f: A => B): B = f(fa)
    }

    extension [F[*], A](fa: F[A]) {
      def map[B](f: A => B)(using bind: Bind[F]): F[B]        = bind.map(fa)(f)
      def flatMap[B](f: A => F[B])(using bind: Bind[F]): F[B] = bind.flatMap(fa)(f)
    }
  }

  trait StateRef[F[*], A] {
    def get: F[A]

    def set(a: A): F[Unit]

    def updateAndGet(f: A => A): F[A]

    def modify[B](f: A => (A, B)): F[B]
  }
  object StateRef {
    def newAtomicLongStateRef(): StateRef[Identity, Long] = new AtomicLongStateRef()

    final private class AtomicLongStateRef extends StateRef[Identity, Long] {
      private val ref = new java.util.concurrent.atomic.AtomicLong()

      def get: Long = ref.get()

      def set(a: Long): Unit = ref.set(a)

      @scala.annotation.tailrec
      def updateAndGet(f: Long => Long): Long = {
        val current  = ref.get()
        val newValue = f(current)
        if (ref.compareAndSet(current, newValue)) newValue else updateAndGet(f)
      }

      @scala.annotation.tailrec
      def modify[B](f: Long => (Long, B)): B = {
        val current            = ref.get()
        val (newState, result) = f(current)
        if (ref.compareAndSet(current, newState)) result else modify(f)
      }
    }
  }

  trait RandomSource[F[*]] {
    def nextLong(): F[Long]

    def nextInt(upperBound: Int): F[Int]
  }
  object RandomSource {
    def newDefaultRandomSource(): RandomSource[Identity] = new DefaultRandomSource

    final private class DefaultRandomSource extends RandomSource[Identity] {
      private lazy val secureRandom = new java.security.SecureRandom()

      def nextLong(): Long              = secureRandom.nextLong()
      def nextInt(upperBound: Int): Int = secureRandom.nextInt(upperBound)
    }
  }

  trait TimestampSource[F[*]] {
    def timestamp(): F[Long]
  }
  object TimestampSource {
    def newDefaultTimestampSource(): TimestampSource[Identity] = new DefaultTimestampSource

    final private class DefaultTimestampSource extends TimestampSource[Identity] {
      def timestamp(): Long = System.currentTimeMillis()
    }
  }

}
