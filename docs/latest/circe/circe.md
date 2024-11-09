---
id: circe
title: "circe module"
---

## Import
```scala mdoc
import refined4s.modules.circe.derivation.types.all.given
```
```scala mdoc
import refined4s.modules.circe.derivation.*
```


## Use Drived Instances for Pre-defined Types
To make `Newtype`, `Refined` and `InlinedRefined` have `Encoder` and `Decoder` (or `Codec`) type-class instances derived from the actual values, you can simply use
```scala
import refined4s.modules.circe.derivation.types.all.given
```
:::warning NOTE
This works only when the actual type already has `Encoder` and `Decoder` (or `Codec`).
:::
:::info
Using `refined4s.modules.circe.derivation.types.all.given` is required only when `Encoder` and/or `Decoder` (or `Codec`) is required for the pre-defined types.<br/>
So if you want your `Newtype` or `Refined` or `InlinedRefined` to have `Encoder` and `Decoder` (or `Codec`) instances,<br/>
you can use [pre-defined traits for circe](#with-explicit-pre-defined-circe-support) or the [`deriving` method](#with-deriving-method) instead.<br/>
:::
```scala mdoc:reset-object
import refined4s.*
import refined4s.types.all.*

import io.circe.*
import io.circe.syntax.*

def printJson[A: Encoder](a: A): Unit = println(a.asJson.spaces2)

```

**With `derivation.types.all`,**

```scala mdoc {1}
import refined4s.modules.circe.derivation.types.all.given

final case class Person(name: NonEmptyString) derives Codec.AsObject

printJson(NonEmptyString("Tony Stark"))

val thor = Person(NonEmptyString("Thor Odinson"))
printJson(thor)

val captainAmerica = Person(NonEmptyString("Steve Rogers"))
printJson(captainAmerica)

import io.circe.parser.*

println(decode[Person]("""{ "name": "Kevin" }"""))

println(decode[Person]("""{ "name": "" }"""))

```


## With Explicit Pre-defined Circe Support
There are the following pre-defined traits to support circe' `Encoder` and `Decoder` (or `Codec`).
* `refined4s.modules.circe.derivation.CirceEncoder`
* `refined4s.modules.circe.derivation.CirceNewtypeDecoder`
* `refined4s.modules.circe.derivation.CirceRefinedDecoder`
* `refined4s.modules.circe.derivation.CirceNewtypeCodec`
* `refined4s.modules.circe.derivation.CirceRefinedCodec`

:::warning NOTE
This works only when the actual type already has `Encoder` and `Decoder` (or `Codec`).
:::

```scala mdoc:reset-object {5,8}
import refined4s.*
import refined4s.modules.circe.derivation.*

type Name = Name.Type
object Name extends Newtype[String] with CirceNewtypeCodec[String]

type NotEmptyStr = NotEmptyStr.Type
object NotEmptyStr extends Refined[String] with CirceRefinedCodec[String] {
  inline def invalidReason(a: String): String = "non-empty String"

  inline def predicate(a: String): Boolean = a != ""
}

import io.circe.*

final case class Person(name: Name) derives Codec.AsObject

final case class Item(id: NotEmptyStr) derives Codec.AsObject

import io.circe.syntax.*

def printJson[A: Encoder](a: A): Unit = println(a.asJson.spaces2)

```

```scala mdoc
printJson(Name("Tony Stark"))

printJson(NotEmptyStr("Thor Odinson"))

printJson(Person(Name("Steve Rogers")))

printJson(Item(NotEmptyStr("abc-999")))

import io.circe.parser.*

println(decode[Person]("""{ "name": "Kevin" }"""))

println(decode[Item]("""{ "id": "a-1234" }"""))

println(decode[Item]("""{ "id": "" }"""))

```


## With `deriving` Method
If you want to have explicit `Encoder` and `Decoder` (or `Codec`) type-class instances in your `Newtype` or `Refined` or `InlinedRefined`, you can use the `deriving` method.

:::warning NOTE
This works only when the actual type already has `Encoder` and `Decoder` (or `Codec`).
:::

```scala mdoc:reset-object {10-11,20-21}
import cats.*

import refined4s.*
import refined4s.modules.circe.derivation.*

import io.circe.*

type Name = Name.Type
object Name extends Newtype[String] {
  given encoderName: Encoder[Name] = deriving[Encoder]
  given decoderName: Decoder[Name] = deriving[Decoder]
}

type NotEmptyStr = NotEmptyStr.Type
object NotEmptyStr extends Refined[String] {
  inline def invalidReason(a: String): String = "non-empty String"

  inline def predicate(a: String): Boolean = a != ""

  given encoderNotEmptyStr: Encoder[NotEmptyStr] = deriving[Encoder]
  given decoderNotEmptyStr: Decoder[NotEmptyStr] = Decoder[String].emap(NotEmptyStr.from)
}

import io.circe.*

final case class Person(name: Name) derives Codec.AsObject

final case class Item(id: NotEmptyStr) derives Codec.AsObject

import io.circe.syntax.*

def printJson[A: Encoder](a: A): Unit = println(a.asJson.spaces2)

```
```scala mdoc
printJson(Name("Tony Stark"))

printJson(NotEmptyStr("Thor Odinson"))

printJson(Person(Name("Steve Rogers")))

printJson(Item(NotEmptyStr("abc-999")))

import io.circe.parser.*

println(decode[Person]("""{ "name": "Kevin" }"""))

println(decode[Item]("""{ "id": "a-1234" }"""))

println(decode[Item]("""{ "id": "" }"""))

```
