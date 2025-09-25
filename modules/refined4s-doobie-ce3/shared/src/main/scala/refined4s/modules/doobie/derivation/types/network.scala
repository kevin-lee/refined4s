package refined4s.modules.doobie.derivation.types

import cats.Show
import doobie.{Get, Put}
import refined4s.types.network.*

/** @author Kevin Lee
  * @since 2025-09-03
  */
trait network {

  given derivedUriGet: Get[Uri] = network.derivedUriGet
  given derivedUriPut: Put[Uri] = network.derivedUriPut

  given derivedUrlGet: Get[Url] = network.derivedUrlGet
  given derivedUrlPut: Put[Url] = network.derivedUrlPut

  given derivedPortNumberGet: Get[PortNumber] = network.derivedPortNumberGet
  given derivedPortNumberPut: Put[PortNumber] = network.derivedPortNumberPut

  given derivedSystemPortNumberGet: Get[SystemPortNumber] = network.derivedSystemPortNumberGet
  given derivedSystemPortNumberPut: Put[SystemPortNumber] = network.derivedSystemPortNumberPut

  given derivedNonSystemPortNumberGet: Get[NonSystemPortNumber] = network.derivedNonSystemPortNumberGet
  given derivedNonSystemPortNumberPut: Put[NonSystemPortNumber] = network.derivedNonSystemPortNumberPut

  given derivedUserPortNumberGet: Get[UserPortNumber] = network.derivedUserPortNumberGet
  given derivedUserPortNumberPut: Put[UserPortNumber] = network.derivedUserPortNumberPut

  given derivedDynamicPortNumberGet: Get[DynamicPortNumber] = network.derivedDynamicPortNumberGet
  given derivedDynamicPortNumberPut: Put[DynamicPortNumber] = network.derivedDynamicPortNumberPut

}
object network {

  given derivedUriGet(using Show[String]): Get[Uri] = Get[String].temap(Uri.from)
  given derivedUriPut: Put[Uri]                     = Put[String].contramap(_.value)

  given derivedUrlGet(using Show[String]): Get[Url] = Get[String].temap(Url.from)
  given derivedUrlPut: Put[Url]                     = Put[String].contramap(_.value)

  given derivedPortNumberGet(using Show[Int]): Get[PortNumber] = Get[Int].temap(PortNumber.from)
  given derivedPortNumberPut: Put[PortNumber]                  = Put[Int].contramap(_.value)

  given derivedSystemPortNumberGet(using Show[Int]): Get[SystemPortNumber] = Get[Int].temap(SystemPortNumber.from)
  given derivedSystemPortNumberPut: Put[SystemPortNumber]                  = Put[Int].contramap(_.value)

  given derivedNonSystemPortNumberGet(using Show[Int]): Get[NonSystemPortNumber] = Get[Int].temap(NonSystemPortNumber.from)
  given derivedNonSystemPortNumberPut: Put[NonSystemPortNumber]                  = Put[Int].contramap(_.value)

  given derivedUserPortNumberGet(using Show[Int]): Get[UserPortNumber] = Get[Int].temap(UserPortNumber.from)
  given derivedUserPortNumberPut: Put[UserPortNumber]                  = Put[Int].contramap(_.value)

  given derivedDynamicPortNumberGet(using Show[Int]): Get[DynamicPortNumber] = Get[Int].temap(DynamicPortNumber.from)
  given derivedDynamicPortNumberPut: Put[DynamicPortNumber]                  = Put[Int].contramap(_.value)

}
