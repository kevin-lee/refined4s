---
id: pureconfig
title: "pureconfig module"
---

## Import
```scala mdoc
import refined4s.modules.pureconfig.derivation.types.all.given
```
```scala mdoc
import refined4s.modules.pureconfig.derivation.*
```


## Use Drived Instances for Pre-defined Types
To make `Newtype`, `Refined` and `InlinedRefined` have `ConfigReader` and `ConfigWriter` type-class instances derived from the actual values, you can simply use
```scala
import refined4s.modules.pureconfig.derivation.types.all.given
```
:::warning NOTE
This works only when the actual type already has `ConfigReader` and `ConfigWriter`.
:::
:::info
Using `refined4s.modules.pureconfig.derivation.types.all.given` is required only when `ConfigReader` and/or `ConfigWriter` is required for the pre-defined types.<br/>
So if you want your `Newtype` or `Refined` or `InlinedRefined` to have `ConfigReader` and `ConfigWriter` instances,<br/>
you can use [pre-defined traits for pureconfig](#with-explicit-pre-defined-pureconfig-support) or the [`deriving` method](#with-deriving-method) instead.<br/>
:::
```scala mdoc:reset-object
import refined4s.*
import refined4s.types.all.*

import com.typesafe.config.*
import pureconfig.generic.derivation.default.*
import pureconfig.*

import scala.jdk.CollectionConverters.*
```

**With `derivation.types.all`,**

```scala mdoc {1}
import refined4s.modules.pureconfig.derivation.types.all.given

final case class NewtypeApiConfig(api: NewtypeApiConfig.Api) derives ConfigReader
object NewtypeApiConfig {
  
  final case class Api(
    id: PosLong,
    baseUri: Uri,
    endpointPath: NonEmptyString,
    additionalId: PosLong,
  ) derives ConfigReader

}

val confString =
  raw"""api {
       |  id = 123
       |  base-uri = "https://localhost:8080"
       |  endpoint-path = "/v1/blah/blah"
       |  additional-id = 999
       |}
       |""".stripMargin

ConfigSource.string(confString).load[NewtypeApiConfig]

```


## With Explicit Pre-defined Pureconfig Support
There are the following pre-defined traits to support pureconfig `ConfigReader` and `ConfigWriter`.
* `refined4s.modules.pureconfig.derivation.PureconfigConfigWriter`
* `refined4s.modules.pureconfig.derivation.PureconfigNewtypeConfigReader`
* `refined4s.modules.pureconfig.derivation.PureconfigRefinedConfigReader`

:::warning NOTE
This works only when the actual type already has  `ConfigReader` and `ConfigWriter`.
:::

```scala mdoc:reset-object {24,37,40,48}
import refined4s.*
import refined4s.types.all.*
import refined4s.modules.pureconfig.derivation.*
import refined4s.modules.pureconfig.derivation.types.all.given

import com.typesafe.config.*
import pureconfig.generic.derivation.default.*
import pureconfig.*

import scala.jdk.CollectionConverters.*

final case class NewtypeApiConfig(api: NewtypeApiConfig.Api) derives ConfigReader
object NewtypeApiConfig {
  
  final case class Api(
    id: Api.Id,
    baseUri: Api.NewtypeBaseUri,
    endpointPath: Api.RefinedEndpointPath,
    additionalId: Api.InlinedRefinedNewtypeId,
  ) derives ConfigReader
  object Api {

    type Id = Id.Type
    object Id extends InlinedRefined[Long], PureconfigRefinedConfigReader[Long] {

      override inline val inlinedExpectedValue = "a positive Long"

      override inline def invalidReason(a: Long): String =
        "It must be a positive Long"

      override inline def predicate(a: Long): Boolean = a > 0L

      override inline def inlinedPredicate(inline a: Long): Boolean = a > 0L
    }

    type NewtypeBaseUri = NewtypeBaseUri.Type
    object NewtypeBaseUri extends Newtype[Uri], PureconfigNewtypeConfigReader[Uri]

    type RefinedEndpointPath = RefinedEndpointPath.Type
    object RefinedEndpointPath extends Refined[String], PureconfigRefinedConfigReader[String] {
      override inline def invalidReason(a: String): String =
        "It must be a non-empty String"

      override inline def predicate(a: String): Boolean = a != ""
    }

    type InlinedRefinedNewtypeId = InlinedRefinedNewtypeId.Type
    object InlinedRefinedNewtypeId extends Newtype[PosLong], PureconfigNewtypeConfigReader[PosLong]
  }

}

val confString =
  raw"""api {
       |  id = 123
       |  base-uri = "https://localhost:8080"
       |  endpoint-path = "/v1/blah/blah"
       |  additional-id = 999
       |}
       |""".stripMargin

ConfigSource.string(confString).load[NewtypeApiConfig]


```


## With `deriving` Method
If you want to have explicit `ConfigReader` and `ConfigWriter` type-class instances in your `Newtype` or `Refined` or `InlinedRefined`, you can use the `deriving` method.

:::warning NOTE
This works only when the actual type already has `ConfigReader` and `ConfigWriter`.
:::

```scala mdoc:reset-object {35,40,50,55}
import refined4s.*
import refined4s.types.all.*
import refined4s.modules.pureconfig.derivation.*
import refined4s.modules.pureconfig.derivation.types.all.given

import com.typesafe.config.*
import pureconfig.generic.derivation.default.*
import pureconfig.*

import scala.jdk.CollectionConverters.*

final case class NewtypeApiConfig(api: NewtypeApiConfig.Api) derives ConfigReader
object NewtypeApiConfig {
  
  final case class Api(
    id: Api.Id,
    baseUri: Api.NewtypeBaseUri,
    endpointPath: Api.RefinedEndpointPath,
    additionalId: Api.InlinedRefinedNewtypeId,
  ) derives ConfigReader
  object Api {

    type Id = Id.Type
    object Id extends InlinedRefined[Long] {

      override inline val inlinedExpectedValue = "a positive Long"

      override inline def invalidReason(a: Long): String =
        "It must be a positive Long"

      override inline def predicate(a: Long): Boolean = a > 0L

      override inline def inlinedPredicate(inline a: Long): Boolean = a > 0L
      
      given configReaderId: ConfigReader[Id] = deriving[ConfigReader]
    }

    type NewtypeBaseUri = NewtypeBaseUri.Type
    object NewtypeBaseUri extends Newtype[Uri] {
      given configReaderNewtypeBaseUri: ConfigReader[NewtypeBaseUri] = deriving[ConfigReader]
    }

    type RefinedEndpointPath = RefinedEndpointPath.Type
    object RefinedEndpointPath extends Refined[String] {
      override inline def invalidReason(a: String): String =
        "It must be a non-empty String"

      override inline def predicate(a: String): Boolean = a != ""
      
      given configReaderRefinedEndpointPath: ConfigReader[RefinedEndpointPath] = deriving[ConfigReader]
    }

    type InlinedRefinedNewtypeId = InlinedRefinedNewtypeId.Type
    object InlinedRefinedNewtypeId extends Newtype[PosLong] {
      given configReaderInlinedRefinedNewtypeId: ConfigReader[InlinedRefinedNewtypeId] = deriving[ConfigReader]
    }
  }

}

val confString =
  raw"""api {
       |  id = 123
       |  base-uri = "https://localhost:8080"
       |  endpoint-path = "/v1/blah/blah"
       |  additional-id = 999
       |}
       |""".stripMargin

ConfigSource.string(confString).load[NewtypeApiConfig]

```
