package refined4s.modules.doobie.derivation.types

import cats.Show
import doobie.{Get, Put}
import refined4s.types.network.*

/** @author Kevin Lee
  * @since 2025-09-02
  */
trait network {

  inline given derivedUriGet: Get[Uri] = network.derivedUriGet
  inline given derivedUriPut: Put[Uri] = network.derivedUriPut

  inline given derivedUrlGet: Get[Url] = network.derivedUrlGet
  inline given derivedUrlPut: Put[Url] = network.derivedUrlPut

  inline given derivedPortNumberGet: Get[PortNumber] = network.derivedPortNumberGet
  inline given derivedPortNumberPut: Put[PortNumber] = network.derivedPortNumberPut

  inline given derivedSystemPortNumberGet: Get[SystemPortNumber] = network.derivedSystemPortNumberGet
  inline given derivedSystemPortNumberPut: Put[SystemPortNumber] = network.derivedSystemPortNumberPut

  inline given derivedNonSystemPortNumberGet: Get[NonSystemPortNumber] = network.derivedNonSystemPortNumberGet
  inline given derivedNonSystemPortNumberPut: Put[NonSystemPortNumber] = network.derivedNonSystemPortNumberPut

  inline given derivedUserPortNumberGet: Get[UserPortNumber] = network.derivedUserPortNumberGet
  inline given derivedUserPortNumberPut: Put[UserPortNumber] = network.derivedUserPortNumberPut

  inline given derivedDynamicPortNumberGet: Get[DynamicPortNumber] = network.derivedDynamicPortNumberGet
  inline given derivedDynamicPortNumberPut: Put[DynamicPortNumber] = network.derivedDynamicPortNumberPut

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
