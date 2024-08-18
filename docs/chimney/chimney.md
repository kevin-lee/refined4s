---
id: chimney
title: "chimney module"
---

## Import
```scala mdoc
import refined4s.modules.chimney.derivation.types.all.given
```
```scala mdoc
import refined4s.modules.chimney.derivation.*
```


## Use Drived Instances for Pre-defined Types
To use `Newtype`, `Refined` and `InlinedRefined` with Chimney, you need to have type-class instances of `Transformer[NewType, RawType]` and `PartialTransformer[RawType, NewType]`.

`refined4s` comes with them already.

```scala
import refined4s.modules.chimney.derivation.types.all.given
```

```scala mdoc:reset-object {7,16,19}
import refined4s.*
import refined4s.types.all.*

import io.scalaland.chimney
import io.scalaland.chimney.dsl.*

import refined4s.modules.chimney.derivation.types.all.given

final case class Person(name: NonEmptyString)

final case class User(name: NonEmptyString)

val person = Person(NonEmptyString("Wade Wilson"))

person.intoPartial[User].transform

person.transformIntoPartial[User]
```


## With Explicit Pre-defined Chimney Support
There are the following pre-defined traits to support chimney's `Transformer` and `PartialTransformer`
* `refined4s.modules.chimney.derivation.ChimneyNewtype`
* `refined4s.modules.chimney.derivation.ChimneyRefined`


```scala mdoc:reset-object {2,10,18,24,27,30,33,36}
import refined4s.*
import refined4s.modules.chimney.derivation.*

import io.scalaland.chimney.*
import io.scalaland.chimney.dsl.*

final case class Person(name: Person.Name)
object Person {
  type Name = Name.Type
  object Name extends Newtype[String], ChimneyNewtype[String]
}

final case class User(name: String)

final case class Member(name: Member.Name)
object Member {
  type Name = Name.Type
  object Name extends Newtype[String], ChimneyNewtype[String]
}

val person = Person(Person.Name("Wade Wilson"))

Person.Name("Wade Wilson").into[String].transform

val deadpool = person.transformInto[User]

deadpool.transformInto[Person]

val member = person.transformInto[Member]

member.transformInto[Person]
```
```scala mdoc {2,10,24,29,32,35}
import refined4s.*
import refined4s.modules.chimney.derivation.*

import io.scalaland.chimney.*
import io.scalaland.chimney.dsl.*

final case class XMen(name: XMen.NotEmptyStr)
object XMen {
  type NotEmptyStr = NotEmptyStr.Type
  object NotEmptyStr extends Refined[String], ChimneyRefined[String] {
    inline def invalidReason(a: String): String = "non-empty String"
  
    inline def predicate(a: String): Boolean = a != ""
  }
}

final case class MarvelCharacter(name: String)

import refined4s.types.all.*

final case class Hero(name: Hero.Name)
object Hero {
  type Name = Name.Type
  object Name extends Newtype[NonEmptyString], ChimneyNewtype[NonEmptyString]
}

val logan = XMen(XMen.NotEmptyStr("James Howlett"))
val wolverine = logan.transformInto[MarvelCharacter]

wolverine.transformIntoPartial[XMen]

val hero = logan.transformIntoPartial[Hero]
```


## With `auto` derivation
If you want to implicitly have `Transformer` and `PartialTransformer` type-class instances for your `Newtype` or `Refined` or `InlinedRefined`, you can use the `refined4s.modules.chimney.derivation.generic.auto`.

```scala mdoc:reset-object {4,28,31,34,37,42,45,48,51}
import cats.*

import refined4s.*
import refined4s.modules.chimney.derivation.generic.auto.given

import io.scalaland.chimney.*
import io.scalaland.chimney.dsl.*

type Name = Name.Type
object Name extends Newtype[String]

type NotEmptyStr = NotEmptyStr.Type
object NotEmptyStr extends Refined[String] {
  inline def invalidReason(a: String): String = "NonEmptyStr should be a non-empty String"

  inline def predicate(a: String): Boolean = a != ""
}

final case class Person(name: Name)

final case class User(name: String)

final case class AnotherUser(name: NotEmptyStr)

val person = Person(Name("Wade Wilson"))

person.into[User].transform

val user = person.transformInto[User]

user.transformIntoPartial[Person]

val anotherUser = person.transformIntoPartial[AnotherUser]

anotherUser.flatMap(_.transformIntoPartial[Person])

val personWithEmptyName = Person(Name(""))

personWithEmptyName.transformInto[User]

personWithEmptyName.transformIntoPartial[AnotherUser]
```
