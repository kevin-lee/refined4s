package refined4s.types

import orphan.{OrphanCats, OrphanCatsKernel}
import refined4s.*

import scala.quoted.*
import scala.util.control.NonFatal
import java.net.{URI, URL}

/** @author Kevin Lee
  * @since 2023-12-09
  */
trait network {

  // scalafix:off DisableSyntax.noFinalVal

  final type Url = network.Url
  final val Url = network.Url

  final type Uri = network.Uri
  final val Uri = network.Uri

//  final type Url = network.Url
//  final val Url = network.Url

  final type PortNumber = network.PortNumber
  final val PortNumber = network.PortNumber

  final type SystemPortNumber = network.SystemPortNumber
  final val SystemPortNumber = network.SystemPortNumber

  final type NonSystemPortNumber = network.NonSystemPortNumber
  final val NonSystemPortNumber = network.NonSystemPortNumber

  final type UserPortNumber = network.UserPortNumber
  final val UserPortNumber = network.UserPortNumber

  final type DynamicPortNumber = network.DynamicPortNumber
  final val DynamicPortNumber = network.DynamicPortNumber

  // scalafix:on

}
object network {

  final type Url = networkCompat.Url
  // scalafix:off DisableSyntax.noFinalVal
  final val Url = networkCompat.Url
  // scalafix:on

  type Uri = Uri.Type
  object Uri extends InlinedRefined[String], UriTypeClassInstances {

    override def invalidReason(a: String): String =
      expectedMessage(inlinedExpectedValue)

    override def predicate(a: String): Boolean =
      try {
        new URI(a)
        true
      } catch {
        case NonFatal(_) =>
          false
      }

    override inline val inlinedExpectedValue = "a URI String"

    override inline def inlinedPredicate(inline uri: String): Boolean = ${ UriValidator.isValidateUri('uri) }

    @SuppressWarnings(Array("org.wartremover.warts.ToString", "org.wartremover.warts.Overloading"))
    def apply(a: URI): Type = unsafeFrom(a.toString)

    extension (uri: Type) {
      def toURI: URI = new URI(uri.value)

      def toUrl: networkCompat.Url = networkCompat.Url.unsafeFrom(uri.value)

      def toURL: URL = toURI.toURL
    }

  }
  private[types] trait UriTypeClassInstances extends UriTypeClassInstance1 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    given derivedUriEq[F[*]: CatsEq, G[*]: CatsEq](using eqActual: G[String]): F[Uri] = {
      internalDef.contraCoercible[cats.Eq, Uri, String, cats.Contravariant](eqActual.asInstanceOf[cats.Eq[String]])
    }.asInstanceOf[F[Uri]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait UriTypeClassInstance1 extends UriTypeClassInstance2 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    given derivedUriHash[F[*]: CatsHash, G[*]: CatsHash](using hashActual: G[String]): F[Uri] = {
      internalDef.contraCoercible[cats.Hash, Uri, String, cats.Contravariant](hashActual.asInstanceOf[cats.Hash[String]])
    }.asInstanceOf[F[Uri]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait UriTypeClassInstance2 extends OrphanCats, OrphanCatsKernel {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    given derivedUriShow[F[*]: CatsShow, G[*]: CatsShow](using showActual: G[String]): F[Uri] = {
      internalDef.contraCoercible[cats.Show, Uri, String, cats.Contravariant](showActual.asInstanceOf[cats.Show[String]])
    }.asInstanceOf[F[Uri]] // scalafix:ok DisableSyntax.asInstanceOf
  }

  type PortNumber = PortNumber.Type
  object PortNumber extends Refined[Int], PortNumberTypeClassInstances {

    override inline def invalidReason(a: Int): String =
      "It has to be Int between 0 and 65535 (0 <= PortNumber <= 65535)"

    override inline def predicate(a: Int): Boolean =
      0 <= a && a <= 65535

  }
  private[types] trait PortNumberTypeClassInstances extends PortNumberTypeClassInstance1 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    given derivedPortNumberEq[F[*]: CatsEq, G[*]: CatsEq](using eqActual: G[Int]): F[PortNumber] = {
      internalDef.contraCoercible[cats.Eq, PortNumber, Int, cats.Contravariant](eqActual.asInstanceOf[cats.Eq[Int]])
    }.asInstanceOf[F[PortNumber]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait PortNumberTypeClassInstance1 extends PortNumberTypeClassInstance2 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    given derivedPortNumberHash[F[*]: CatsHash, G[*]: CatsHash](using hashActual: G[Int]): F[PortNumber] = {
      internalDef.contraCoercible[cats.Hash, PortNumber, Int, cats.Contravariant](hashActual.asInstanceOf[cats.Hash[Int]])
    }.asInstanceOf[F[PortNumber]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait PortNumberTypeClassInstance2 extends OrphanCats, OrphanCatsKernel {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    given derivedPortNumberShow[F[*]: CatsShow, G[*]: CatsShow](using showActual: G[Int]): F[PortNumber] = {
      internalDef.contraCoercible[cats.Show, PortNumber, Int, cats.Contravariant](showActual.asInstanceOf[cats.Show[Int]])
    }.asInstanceOf[F[PortNumber]] // scalafix:ok DisableSyntax.asInstanceOf
  }

  type SystemPortNumber = SystemPortNumber.Type
  object SystemPortNumber extends Refined[Int], SystemPortNumberTypeClassInstances {

    override inline def invalidReason(a: Int): String =
      "It has to be Int between 0 and 1023 (0 <= SystemPortNumber <= 1023)"

    override inline def predicate(a: Int): Boolean =
      0 <= a && a <= 1023

  }
  private[types] trait SystemPortNumberTypeClassInstances extends SystemPortNumberTypeClassInstance1 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    given derivedSystemPortNumberEq[F[*]: CatsEq, G[*]: CatsEq](using eqActual: G[Int]): F[SystemPortNumber] = {
      internalDef.contraCoercible[cats.Eq, SystemPortNumber, Int, cats.Contravariant](eqActual.asInstanceOf[cats.Eq[Int]])
    }.asInstanceOf[F[SystemPortNumber]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait SystemPortNumberTypeClassInstance1 extends SystemPortNumberTypeClassInstance2 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    given derivedSystemPortNumberHash[F[*]: CatsHash, G[*]: CatsHash](using hashActual: G[Int]): F[SystemPortNumber] = {
      internalDef.contraCoercible[cats.Hash, SystemPortNumber, Int, cats.Contravariant](hashActual.asInstanceOf[cats.Hash[Int]])
    }.asInstanceOf[F[SystemPortNumber]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait SystemPortNumberTypeClassInstance2 extends OrphanCats, OrphanCatsKernel {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    given derivedSystemPortNumberShow[F[*]: CatsShow, G[*]: CatsShow](using showActual: G[Int]): F[SystemPortNumber] = {
      internalDef.contraCoercible[cats.Show, SystemPortNumber, Int, cats.Contravariant](showActual.asInstanceOf[cats.Show[Int]])
    }.asInstanceOf[F[SystemPortNumber]] // scalafix:ok DisableSyntax.asInstanceOf
  }

  type NonSystemPortNumber = NonSystemPortNumber.Type
  object NonSystemPortNumber extends Refined[Int], NonSystemPortNumberTypeClassInstances {

    override inline def invalidReason(a: Int): String =
      "It has to be Int between 1024 and 65535 (1024 <= NonSystemPortNumber <= 65535)"

    override inline def predicate(a: Int): Boolean =
      1024 <= a && a <= 65535

  }
  private[types] trait NonSystemPortNumberTypeClassInstances extends NonSystemPortNumberTypeClassInstance1 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    given derivedNonSystemPortNumberEq[F[*]: CatsEq, G[*]: CatsEq](using eqActual: G[Int]): F[NonSystemPortNumber] = {
      internalDef.contraCoercible[cats.Eq, NonSystemPortNumber, Int, cats.Contravariant](eqActual.asInstanceOf[cats.Eq[Int]])
    }.asInstanceOf[F[NonSystemPortNumber]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait NonSystemPortNumberTypeClassInstance1 extends NonSystemPortNumberTypeClassInstance2 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    given derivedNonSystemPortNumberHash[F[*]: CatsHash, G[*]: CatsHash](using hashActual: G[Int]): F[NonSystemPortNumber] = {
      internalDef.contraCoercible[cats.Hash, NonSystemPortNumber, Int, cats.Contravariant](hashActual.asInstanceOf[cats.Hash[Int]])
    }.asInstanceOf[F[NonSystemPortNumber]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait NonSystemPortNumberTypeClassInstance2 extends OrphanCats, OrphanCatsKernel {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    given derivedNonSystemPortNumberShow[F[*]: CatsShow, G[*]: CatsShow](using showActual: G[Int]): F[NonSystemPortNumber] = {
      internalDef.contraCoercible[cats.Show, NonSystemPortNumber, Int, cats.Contravariant](showActual.asInstanceOf[cats.Show[Int]])
    }.asInstanceOf[F[NonSystemPortNumber]] // scalafix:ok DisableSyntax.asInstanceOf
  }

  type UserPortNumber = UserPortNumber.Type
  object UserPortNumber extends Refined[Int], UserPortNumberTypeClassInstances {

    override inline def invalidReason(a: Int): String =
      "It has to be Int between 1024 and 49151 (1024 <= UserPortNumber <= 49151)"

    override inline def predicate(a: Int): Boolean =
      1024 <= a && a <= 49151

  }
  private[types] trait UserPortNumberTypeClassInstances extends UserPortNumberTypeClassInstance1 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    given derivedUserPortNumberEq[F[*]: CatsEq, G[*]: CatsEq](using eqActual: G[Int]): F[UserPortNumber] = {
      internalDef.contraCoercible[cats.Eq, UserPortNumber, Int, cats.Contravariant](eqActual.asInstanceOf[cats.Eq[Int]])
    }.asInstanceOf[F[UserPortNumber]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait UserPortNumberTypeClassInstance1 extends UserPortNumberTypeClassInstance2 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    given derivedUserPortNumberHash[F[*]: CatsHash, G[*]: CatsHash](using hashActual: G[Int]): F[UserPortNumber] = {
      internalDef.contraCoercible[cats.Hash, UserPortNumber, Int, cats.Contravariant](hashActual.asInstanceOf[cats.Hash[Int]])
    }.asInstanceOf[F[UserPortNumber]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait UserPortNumberTypeClassInstance2 extends OrphanCats, OrphanCatsKernel {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    given derivedUserPortNumberShow[F[*]: CatsShow, G[*]: CatsShow](using showActual: G[Int]): F[UserPortNumber] = {
      internalDef.contraCoercible[cats.Show, UserPortNumber, Int, cats.Contravariant](showActual.asInstanceOf[cats.Show[Int]])
    }.asInstanceOf[F[UserPortNumber]] // scalafix:ok DisableSyntax.asInstanceOf
  }

  type DynamicPortNumber = DynamicPortNumber.Type
  object DynamicPortNumber extends Refined[Int], DynamicPortNumberTypeClassInstances {

    override inline def invalidReason(a: Int): String =
      "It has to be Int between 49152 and 65535 (49152 <= DynamicPortNumber <= 65535)"

    override inline def predicate(a: Int): Boolean =
      49152 <= a && a <= 65535

  }
  private[types] trait DynamicPortNumberTypeClassInstances extends DynamicPortNumberTypeClassInstance1 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    given derivedDynamicPortNumberEq[F[*]: CatsEq, G[*]: CatsEq](using eqActual: G[Int]): F[DynamicPortNumber] = {
      internalDef.contraCoercible[cats.Eq, DynamicPortNumber, Int, cats.Contravariant](eqActual.asInstanceOf[cats.Eq[Int]])
    }.asInstanceOf[F[DynamicPortNumber]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait DynamicPortNumberTypeClassInstance1 extends DynamicPortNumberTypeClassInstance2 {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    given derivedDynamicPortNumberHash[F[*]: CatsHash, G[*]: CatsHash](using hashActual: G[Int]): F[DynamicPortNumber] = {
      internalDef.contraCoercible[cats.Hash, DynamicPortNumber, Int, cats.Contravariant](hashActual.asInstanceOf[cats.Hash[Int]])
    }.asInstanceOf[F[DynamicPortNumber]] // scalafix:ok DisableSyntax.asInstanceOf
  }
  private[types] trait DynamicPortNumberTypeClassInstance2 extends OrphanCats, OrphanCatsKernel {
    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    given derivedDynamicPortNumberShow[F[*]: CatsShow, G[*]: CatsShow](using showActual: G[Int]): F[DynamicPortNumber] = {
      internalDef.contraCoercible[cats.Show, DynamicPortNumber, Int, cats.Contravariant](showActual.asInstanceOf[cats.Show[Int]])
    }.asInstanceOf[F[DynamicPortNumber]] // scalafix:ok DisableSyntax.asInstanceOf

  }

}
