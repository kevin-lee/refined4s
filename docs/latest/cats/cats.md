---
id: cats
title: "cats module"
---

## Import

```scala
import refined4s.modules.cats.derivation.*
```
***
:::note
The following import is required only for versions prior to `1.8.0`.
```scala
import refined4s.modules.cats.derivation.types.all.given
```
If you are using `refined4s` `1.8.0` or later, **you don't need it**.
:::

## Use Drived Instances for Pre-defined Types

:::info NOTE
Since `refined4s` `1.8.0`, you no longer need to import anything to use the predefined `Eq` and `Show` type-class instances derived from the actual values. As soon as you project includes `cats`, `refined4s` will automatically provide the predefined `Eq` and `Show` type-class instances derived from the actual values.
:::

:::warning NOTE
This works only when the actual type already has `Eq` and `Show`.
:::

:::info
`Eq` and/or `Show` type-class instances are provided only for the types from `refined4s.types.all`.
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
  if Eq[A].eqv(a1, a2) then println("The given values are equal.")
  else println("The given values are not equal.")
```

**For `refined4s.types.all.*`, `Eq` and `Show` type-class instances are already provided if the underlying type has `Eq` and `Show` instances.**

e.g.)
```scala mdoc
import refined4s.types.all.*

hello(NonEmptyString("Peter Parker"))

hello(PosInt(999))


equal(NonEmptyString("Peter Parker"), NonEmptyString("Peter Parker"))

equal(NonEmptyString("Peter Parker"), NonEmptyString("Natasha Romanoff"))

equal(NonNegInt(0), NonNegInt(0))

equal(NegInt(-123), NegInt(-999))

```

**Custom types without `Eq` and `Show` type-class instances,**

```scala mdoc:fail
hello(Name("Tony Stark"))
```
```scala mdoc:fail
hello(NotEmptyStr("Thor Odinson"))
```
```scala mdoc:fail
equal(Name("Tony Stark"), Name("Tony Stark"))
```
```scala mdoc:fail
equal(NotEmptyStr("Thor Odinson"), NotEmptyStr("Thor Odinson"))
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

