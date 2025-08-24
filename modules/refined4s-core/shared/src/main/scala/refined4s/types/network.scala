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

object network extends OrphanCats, OrphanCatsKernel {

  final type Url = networkCompat.Url
  // scalafix:off DisableSyntax.noFinalVal
  final val Url = networkCompat.Url
  // scalafix:on

  type Uri = Uri.Type
  object Uri extends InlinedRefined[String] {

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

    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedUriEq[F[*]: CatsEq, G[*]: CatsEq](using eqActual: G[String]): F[Uri] = {
      internalDef.contraCoercible(eqActual.asInstanceOf[cats.Eq[String]])
    }.asInstanceOf[F[Uri]] // scalafix:ok DisableSyntax.asInstanceOf

    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedUriShow[F[*]: CatsShow, G[*]: CatsShow](using showActual: G[String]): F[Uri] = {
      internalDef.contraCoercible(showActual.asInstanceOf[cats.Show[String]])
    }.asInstanceOf[F[Uri]] // scalafix:ok DisableSyntax.asInstanceOf

  }

  type PortNumber = PortNumber.Type
  object PortNumber extends Refined[Int] {

    override inline def invalidReason(a: Int): String =
      "It has to be Int between 0 and 65535 (0 <= PortNumber <= 65535)"

    override inline def predicate(a: Int): Boolean =
      0 <= a && a <= 65535

    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedPortNumberEq[F[*]: CatsEq, G[*]: CatsEq](using eqActual: G[Int]): F[PortNumber] = {
      internalDef.contraCoercible(eqActual.asInstanceOf[cats.Eq[Int]])
    }.asInstanceOf[F[PortNumber]] // scalafix:ok DisableSyntax.asInstanceOf

    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedPortNumberShow[F[*]: CatsShow, G[*]: CatsShow](using showActual: G[Int]): F[PortNumber] = {
      internalDef.contraCoercible(showActual.asInstanceOf[cats.Show[Int]])
    }.asInstanceOf[F[PortNumber]] // scalafix:ok DisableSyntax.asInstanceOf

  }

  type SystemPortNumber = SystemPortNumber.Type
  object SystemPortNumber extends Refined[Int] {

    override inline def invalidReason(a: Int): String =
      "It has to be Int between 0 and 1023 (0 <= SystemPortNumber <= 1023)"

    override inline def predicate(a: Int): Boolean =
      0 <= a && a <= 1023

    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedSystemPortNumberEq[F[*]: CatsEq, G[*]: CatsEq](using eqActual: G[Int]): F[SystemPortNumber] = {
      internalDef.contraCoercible(eqActual.asInstanceOf[cats.Eq[Int]])
    }.asInstanceOf[F[SystemPortNumber]] // scalafix:ok DisableSyntax.asInstanceOf

    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedSystemPortNumberShow[F[*]: CatsShow, G[*]: CatsShow](using showActual: G[Int]): F[SystemPortNumber] = {
      internalDef.contraCoercible(showActual.asInstanceOf[cats.Show[Int]])
    }.asInstanceOf[F[SystemPortNumber]] // scalafix:ok DisableSyntax.asInstanceOf

  }

  type NonSystemPortNumber = NonSystemPortNumber.Type
  object NonSystemPortNumber extends Refined[Int] {

    override inline def invalidReason(a: Int): String =
      "It has to be Int between 1024 and 65535 (1024 <= NonSystemPortNumber <= 65535)"

    override inline def predicate(a: Int): Boolean =
      1024 <= a && a <= 65535

    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNonSystemPortNumberEq[F[*]: CatsEq, G[*]: CatsEq](using eqActual: G[Int]): F[NonSystemPortNumber] = {
      internalDef.contraCoercible(eqActual.asInstanceOf[cats.Eq[Int]])
    }.asInstanceOf[F[NonSystemPortNumber]] // scalafix:ok DisableSyntax.asInstanceOf

    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedNonSystemPortNumberShow[F[*]: CatsShow, G[*]: CatsShow](using showActual: G[Int]): F[NonSystemPortNumber] = {
      internalDef.contraCoercible(showActual.asInstanceOf[cats.Show[Int]])
    }.asInstanceOf[F[NonSystemPortNumber]] // scalafix:ok DisableSyntax.asInstanceOf

  }

  type UserPortNumber = UserPortNumber.Type
  object UserPortNumber extends Refined[Int] {

    override inline def invalidReason(a: Int): String =
      "It has to be Int between 1024 and 49151 (1024 <= UserPortNumber <= 49151)"

    override inline def predicate(a: Int): Boolean =
      1024 <= a && a <= 49151

    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedUserPortNumberEq[F[*]: CatsEq, G[*]: CatsEq](using eqActual: G[Int]): F[UserPortNumber] = {
      internalDef.contraCoercible(eqActual.asInstanceOf[cats.Eq[Int]])
    }.asInstanceOf[F[UserPortNumber]] // scalafix:ok DisableSyntax.asInstanceOf

    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedUserPortNumberShow[F[*]: CatsShow, G[*]: CatsShow](using showActual: G[Int]): F[UserPortNumber] = {
      internalDef.contraCoercible(showActual.asInstanceOf[cats.Show[Int]])
    }.asInstanceOf[F[UserPortNumber]] // scalafix:ok DisableSyntax.asInstanceOf
  }

  type DynamicPortNumber = DynamicPortNumber.Type
  object DynamicPortNumber extends Refined[Int] {

    override inline def invalidReason(a: Int): String =
      "It has to be Int between 49152 and 65535 (49152 <= DynamicPortNumber <= 65535)"

    override inline def predicate(a: Int): Boolean =
      49152 <= a && a <= 65535

    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedDynamicPortNumberEq[F[*]: CatsEq, G[*]: CatsEq](using eqActual: G[Int]): F[DynamicPortNumber] = {
      internalDef.contraCoercible(eqActual.asInstanceOf[cats.Eq[Int]])
    }.asInstanceOf[F[DynamicPortNumber]] // scalafix:ok DisableSyntax.asInstanceOf

    @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
    inline given derivedDynamicPortNumberShow[F[*]: CatsShow, G[*]: CatsShow](using showActual: G[Int]): F[DynamicPortNumber] = {
      internalDef.contraCoercible(showActual.asInstanceOf[cats.Show[Int]])
    }.asInstanceOf[F[DynamicPortNumber]] // scalafix:ok DisableSyntax.asInstanceOf

  }

}
