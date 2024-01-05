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
There are the following pre-defined traits to support doobie' `Encoder` and `Decoder` (or `Codec`).
* `refined4s.modules.doobie.derivation.DoobiePut`
* `refined4s.modules.doobie.derivation.DoobieNewtypeGet`
* `refined4s.modules.doobie.derivation.DoobieRefinedGet`

:::warning NOTE
This works only when the actual type already has `Get` and/or `Put`.
:::

e.g.)
```scala mdoc:reset-object {7,10}
import cats.effect.*

import refined4s.*
import refined4s.types.all.*
import refined4s.modules.doobie.derivation.*

type NotEmptyStr = NotEmptyStr.Type
object NotEmptyStr extends Refined[String] with DoobieRefinedGetPut[String] {
  inline def invalidReason(a: String): String = "non-empty String"

  inline def predicate(a: String): Boolean = a != ""
}

type Name = Name.Type
object Name extends Newtype[NotEmptyStr] with DoobieNewtypeGetPut[NotEmptyStr]


final case class Person(name: Name)

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
insertOrUpdate[F](
  sql"""
    INSERT INTO db_tools_test.example (name) VALUES (${person.name})
  """
)(transactor)

// You don't have to use .value or .value.value like
// insertOrUpdate[F](
//   sql"""
//     INSERT INTO db_tools_test.example (name) VALUES (${person.name.value.value})
//   """
// )(transactor)

```

