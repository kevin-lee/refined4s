---
id: cats
title: "cats module"
---

## Import
```scala
import refined4s.modules.cats.derivation.instances.given
```
```scala
import refined4s.modules.cats.derivation.*
```

## Use Automatically Drived Instances
To make `Newtype`, `Refined` and `InlinedRefined` have `Eq` and `Show` type-class instances derived from the actual values, you can simply use
```scala
import refined4s.modules.cats.derivation.instances.given
```

```scala mdoc
import refined4s.*
import refined4s.types.all.*

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

**With `derivation.instances`,**

```scala
import refined4s.modules.cats.derivation.instances.given

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

**Without `derivation.instances`,**

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

```scala mdoc:reset
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

