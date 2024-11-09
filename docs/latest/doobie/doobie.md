---
id: doobie
title: "doobie module"
---

## Import
```scala mdoc
import refined4s.modules.doobie.derivation.types.all.given
```
```scala mdoc
import refined4s.modules.doobie.derivation.*
```


## Use Drived Instances for Pre-defined Types
To use `Newtype`, `Refined` and `InlinedRefined` with Doobie by having `Get` and `Put` (or `Read` and `Write` from `Get` and `Put`) type-class instances derived from the actual values, you can simply use
```scala
import refined4s.modules.doobie.derivation.types.all.given
```
:::warning NOTE
This works only when the actual type already has `Get` and `Put`.
:::
:::info
Using `refined4s.modules.doobie.derivation.types.all.given` is required only when `Get` and/or `Put` is required for the pre-defined types.<br/>
So if you want your `Newtype` or `Refined` or `InlinedRefined` to have `Get` and `Put` instances,<br/>
you can use [pre-defined traits for doobie](#with-explicit-pre-defined-doobie-support) or the [`deriving` method](#with-deriving-method) instead.<br/>
:::
```scala mdoc:reset-object
import cats.effect.*

import refined4s.*
import refined4s.types.all.*

import doobie.*
import doobie.syntax.all.*

def insertOrUpdate[F[*]](fragment: Fragment)(transactor: Transactor[F])(
  using bracket: Bracket[F, Throwable]
): F[Int] =
  fragment
    .update
    .run
    .transact(transactor)

```

**With `derivation.types.all`,**

```scala {1}
import refined4s.modules.doobie.derivation.types.all.given

final case class Person(name: NonEmptyString)

val name = NonEmptyString("Tony Stark")

insertOrUpdate[F](
  sql"""
    INSERT INTO db_tools_test.example (value) VALUES ($name)
  """
)(transactor)

// You don't have to do use .value like
// insertOrUpdate[F](
// sql"""
//     INSERT INTO db_tools_test.example (value) VALUES ($name.value)
//   """
// )(transactor)
```



## With Explicit Pre-defined Doobie Support
There are the following pre-defined traits to support doobie's `Get` and `Put` (or `Codec`).
* `refined4s.modules.doobie.derivation.DoobiePut`
* `refined4s.modules.doobie.derivation.DoobieNewtypeGet`
* `refined4s.modules.doobie.derivation.DoobieRefinedGet`

:::warning NOTE
This works only when the actual type already has `Get` and/or `Put`.
:::

e.g.)
```scala mdoc:reset-object {8,15,18}
import cats.effect.*

import refined4s.*
import refined4s.types.all.*
import refined4s.modules.doobie.derivation.*

type NotEmptyStr = NotEmptyStr.Type
object NotEmptyStr extends Refined[String], DoobieRefinedGetPut[String] {
  inline def invalidReason(a: String): String = "non-empty String"

  inline def predicate(a: String): Boolean = a != ""
}

type Name = Name.Type
object Name extends Newtype[NotEmptyStr], DoobieNewtypeGetPut[NotEmptyStr]

type Id = Id.Type
object Id extends Newtype[Long], DoobieNewtypeGetPut[Long]

final case class Person(id: Id, name: Name)

import doobie.*
import doobie.syntax.all.*

def insertOrUpdate[F[*]](fragment: Fragment)(transactor: Transactor[F])(
  using bracket: Bracket[F, Throwable]
): F[Int] =
  fragment
    .update
    .run
    .transact(transactor)

```

```scala
val person = Person(Id(1), Name(NotEmptyStr("Kevin")))

insertOrUpdate[F](
  sql"""
    INSERT INTO db_tools_test.example (id, name) VALUES (${person.id}, ${person.name})
  """
)(transactor)

// You don't have to use .value or .value.value like
// insertOrUpdate[F](
//   sql"""
//     INSERT INTO db_tools_test.example (id, name) VALUES (${person.id.value}, ${person.name.value.value})
//   """
// )(transactor)

```


## With `deriving` Method
If you want to have explicit `Get` and `Put` type-class instances in your `Newtype` or `Refined` or `InlinedRefined`, you can use the `deriving` method.

:::warning NOTE
This works only when the actual type already has `Get` and `Put`.
:::

```scala mdoc:reset-object {9-10,19-20,25-26}
import cats.effect.*

import refined4s.*

import doobie.*

type Name = Name.Type
object Name extends Newtype[NotEmptyStr] {
  given getName: Get[Name] = deriving[Get]
  given putName: Put[Name] = deriving[Put]
}

type NotEmptyStr = NotEmptyStr.Type
object NotEmptyStr extends Refined[String] {
  inline def invalidReason(a: String): String = "non-empty String"

  inline def predicate(a: String): Boolean = a != ""

  given getNotEmptyStr: Get[NotEmptyStr] = Get[String].temap(NotEmptyStr.from)
  given putNotEmptyStr: Put[NotEmptyStr] = deriving[Put]
}

type Id = Id.Type
object Id extends Newtype[Long] {
  given getId: Get[Id] = deriving[Get]
  given putId: Put[Id] = deriving[Put]
}

final case class Person(id: Id, name: Name)

import doobie.syntax.all.*

def insertOrUpdate[F[*]](fragment: Fragment)(transactor: Transactor[F])(
  using bracket: Bracket[F, Throwable]
): F[Int] =
  fragment
    .update
    .run
    .transact(transactor)


```
```scala
val person = Person(Id(1), Name(NotEmptyStr("Kevin")))

insertOrUpdate[F](
  sql"""
    INSERT INTO db_tools_test.example (id, name) VALUES (${person.id}, ${person.name})
  """
)(transactor)

// You don't have to use .value or .value.value like
// insertOrUpdate[F](
//   sql"""
//     INSERT INTO db_tools_test.example (id, name) VALUES (${person.id.value}, ${person.name.value.value})
//   """
// )(transactor)

```
