---
id: cats
title: "cats module"
---

## Import
```scala
import refined4s.modules.cats.derivation.types.all.given
```
```scala
import refined4s.modules.cats.derivation.*
```

## Use Drived Instances for Pre-defined Types
To make `Newtype`, `Refined` and `InlinedRefined` have `Eq` and `Show` type-class instances derived from the actual values, you can simply use
```scala
import refined4s.modules.cats.derivation.types.all.given
```
:::warning NOTE
This works only when the actual type already has `Eq` and `Show`.
:::
:::info
Using `refined4s.modules.cats.derivation.types.all.given` is required only when `Eq` and/or `Show` is used.<br/>
So if you want your `Newtype` or `Refined` or `InlinedRefined` to have `Eq` and `Show` instances,<br/>
you can use [pre-defined traits for cats](#with-explicit-pre-defined-cats-support) or the [`deriving` method](#with-deriving-method) instead.<br/>
:::
```scala mdoc
import refined4s.*

type Name = Name.Type
object Name extends Newtype[String]

type NotEmptyStr = NotEmptyStr.Type
object NotEmptyStr extends Refined[String] {
  inline def invalidReason(a: String): String = "non-empty String"

  inline def predicate(a: String): Boolean = a != ""
}

import cats.*
import cats.syntax.all.*

def hello[A: Show](a: A): Unit = println(show"Hello $a")

def equal[A: Eq](a1: A, a2: A): Unit =
  if Eq[A].eqv(a1, a1) then println("The given values are equal.")
  else println("The given values are not equal.")
```

**With `derivation.types.all`,**

```scala {1}
import refined4s.modules.cats.derivation.types.all.given

hello(NonEmptyString("Peter Parker"))
// Hello Peter Parker

hello(Name("Tony Stark"))
// Hello Tony Stark

hello(NotEmptyStr("Thor Odinson"))
// Hello Thor Odinson

equal(NonEmptyString("Peter Parker"), NonEmptyString("Peter Parker"))
// The given values are equal.
equal(NonEmptyString("Peter Parker"), NonEmptyString("Natasha Romanoff"))
// The given values are not equal.

equal(Name("Tony Stark"), Name("Tony Stark"))
// The given values are equal.
equal(Name("Tony Stark"), Name("Steve Rogers"))
// The given values are not equal.

equal(NotEmptyStr("Thor Odinson"), NotEmptyStr("Thor Odinson"))
// The given values are equal.
equal(NotEmptyStr("Thor Odinson"), NotEmptyStr("Bruce Banner"))
// The given values are not equal.
```

**Without `derivation.types.all`,**

```scala mdoc:fail
hello(NonEmptyString("Kevin"))
```
```scala mdoc:fail
hello(Name("Kevin"))
```
```scala mdoc:fail
hello(NotEmptyStr("Kevin"))
```
```scala mdoc:fail
equal(NonEmptyString("Kevin"), NonEmptyString("Kevin"))
```
```scala mdoc:fail
equal(Name("Kevin"), Name("Kevin"))
```
```scala mdoc:fail
equal(NotEmptyStr("Kevin"), NotEmptyStr("Kevin"))
```


## With Explicit Pre-defined Cats Support
There are the following pre-defined traits to support cats' `Eq` and `Show`.
* `refined4s.modules.cats.derivation.CatsEq`
* `refined4s.modules.cats.derivation.CatsShow`
* `refined4s.modules.cats.derivation.CatsEqShow`

:::warning NOTE
This works only when the actual type already has `Eq` and `Show`.
:::

```scala mdoc:reset {5,8}
import refined4s.*
import refined4s.modules.cats.derivation.*

type Name = Name.Type
object Name extends Newtype[String] with CatsEqShow[String]

type NotEmptyStr = NotEmptyStr.Type
object NotEmptyStr extends Refined[String] with CatsEqShow[String] {
  inline def invalidReason(a: String): String = "non-empty String"

  inline def predicate(a: String): Boolean = a != ""
}

import cats.*
import cats.syntax.all.*

def hello[A: Show](a: A): Unit = println(show"Hello $a")

def equal[A: Eq](a1: A, a2: A): Unit =
  if Eq[A].eqv(a1, a1) then println("The given values are equal.")
  else println("The given values are not equal.")
```

```scala
hello(Name("Tony Stark"))
// Hello Tony Stark

hello(NotEmptyStr("Thor Odinson"))
// Hello Thor Odinson

equal(Name("Tony Stark"), Name("Tony Stark"))
// The given values are equal.
equal(Name("Tony Stark"), Name("Steve Rogers"))
// The given values are not equal.

equal(NotEmptyStr("Thor Odinson"), NotEmptyStr("Thor Odinson"))
// The given values are equal.
equal(NotEmptyStr("Thor Odinson"), NotEmptyStr("Bruce Banner"))
// The given values are not equal.
```


## With `deriving` Method
If you want to have explicit `Eq` and `Show` type-class instances in your `Newtype` or `Refined` or `InlinedRefined`, you can use the `deriving` method.

:::warning NOTE
This works only when the actual type already has `Eq` and `Show`.
:::

```scala mdoc:reset {8-9,18-19}
import cats.*

import refined4s.*
import refined4s.modules.cats.derivation.*

type Name = Name.Type
object Name extends Newtype[String] {
  given eqName: Eq[Name] = deriving[Eq]
  given showName: Show[Name] = deriving[Show]
}

type NotEmptyStr = NotEmptyStr.Type
object NotEmptyStr extends Refined[String] with CatsEqShow[String] {
  inline def invalidReason(a: String): String = "non-empty String"

  inline def predicate(a: String): Boolean = a != ""

  given eqNotEmptyStr: Eq[NotEmptyStr] = deriving[Eq]
  given showNotEmptyStr: Show[NotEmptyStr] = deriving[Show]
}

import cats.*
import cats.syntax.all.*

def hello[A: Show](a: A): Unit = println(show"Hello $a")

def equal[A: Eq](a1: A, a2: A): Unit =
  if Eq[A].eqv(a1, a1) then println("The given values are equal.")
  else println("The given values are not equal.")
```

```scala
hello(Name("Tony Stark"))
// Hello Tony Stark

hello(NotEmptyStr("Thor Odinson"))
// Hello Thor Odinson

equal(Name("Tony Stark"), Name("Tony Stark"))
// The given values are equal.
equal(Name("Tony Stark"), Name("Steve Rogers"))
// The given values are not equal.

equal(NotEmptyStr("Thor Odinson"), NotEmptyStr("Thor Odinson"))
// The given values are equal.
equal(NotEmptyStr("Thor Odinson"), NotEmptyStr("Bruce Banner"))
// The given values are not equal.
```

