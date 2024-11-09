---
id: tapir
title: "tapir module"
---

## Import
```scala mdoc
import refined4s.modules.tapir.derivation.types.all.given
```
```scala mdoc
import refined4s.modules.tapir.derivation.*
```


## Use Drived Instances for Pre-defined Types
To make `Newtype`, `Refined` and `InlinedRefined` have `sttp.tapir.Schema` type-class instances derived from the actual values, you can simply use
```scala
import refined4s.modules.tapir.derivation.types.all.given
```
:::warning NOTE
This works only when the actual type already has `Schema`.
:::
:::info
Using `refined4s.modules.tapir.derivation.types.all.given` is required only when `sttp.tapir.Schema` is required for the pre-defined types.<br/>
So if you want your `Newtype` or `Refined` or `InlinedRefined` to have `Schema` instances,<br/>
you can use [pre-defined traits for tapir](#with-explicit-pre-defined-tapir-support) or the [`deriving` method](#with-deriving-method) instead.<br/>
:::
```scala mdoc:reset-object
import refined4s.*
import refined4s.types.all.*

import sttp.tapir.*

def validate[A: Schema](a: A): List[ValidationError[?]] = summon[Schema[A]].applyValidation(a)
// NOTE: You don't need code like this in your project as `Schema` is required by Tapir to generate API docs so just having the Schema type-class instances is good enough.
```

**With `derivation.types.all`,**

```scala mdoc {1}
import refined4s.modules.tapir.derivation.types.all.given
import sttp.tapir.generic.auto.* // It's only for case classes.

final case class Person(name: NonEmptyString)

validate(NonEmptyString("Tony Stark"))

val thor = Person(NonEmptyString("Thor Odinson"))
validate(thor)
```


## With Explicit Pre-defined Tapir Support
There are the following pre-defined traits to support tapir's `Schema`.
* `refined4s.modules.tapir.derivation.TapirNewtypeSchema`
* `refined4s.modules.tapir.derivation.TapirRefinedSchema`

:::warning NOTE
This works only when the actual type already has `Schema`.
:::

```scala mdoc:reset-object {6,9}
import refined4s.*
import refined4s.modules.tapir.derivation.*
import sttp.tapir.generic.auto.* // It's only for case classes.

type Name = Name.Type
object Name extends Newtype[String] with TapirNewtypeSchema[String]

type NotEmptyStr = NotEmptyStr.Type
object NotEmptyStr extends Refined[String] with TapirRefinedSchema[String] {
  inline def invalidReason(a: String): String = "non-empty String"

  inline def predicate(a: String): Boolean = a != ""
}

import sttp.tapir.*

final case class Person(name: Name)

final case class Item(id: NotEmptyStr)

def validate[A: Schema](a: A): List[ValidationError[?]] = summon[Schema[A]].applyValidation(a)
// NOTE: You don't need code like this in your project as `Schema` is required by Tapir to generate API docs so just having the Schema type-class instances is good enough.
```

```scala mdoc
validate(Name("Tony Stark"))

validate(NotEmptyStr("Thor Odinson"))

validate(Person(Name("Steve Rogers")))

validate(Item(NotEmptyStr("abc-999")))
```


## With `deriving` Method
If you want to have explicit `sttp.tapir.Schema` type-class instances in your `Newtype` or `Refined` or `InlinedRefined`, you can use the `deriving` method.

:::warning NOTE
This works only when the actual type already has the `Schema` instance.
:::

```scala mdoc:reset-object {10,19}
import cats.*

import refined4s.*
import sttp.tapir.generic.auto.* // It's only for case classes.

import sttp.tapir.*

type Name = Name.Type
object Name extends Newtype[String] {
  given schemaName: Schema[Name] = deriving[Schema]
}

type NotEmptyStr = NotEmptyStr.Type
object NotEmptyStr extends Refined[String] {
  inline def invalidReason(a: String): String = "non-empty String"

  inline def predicate(a: String): Boolean = a != ""

  given schemaNotEmptyStr: Schema[NotEmptyStr] = deriving[Schema]
}

import sttp.tapir.*

final case class Person(name: Name)

final case class Item(id: NotEmptyStr)

def validate[A: Schema](a: A): List[ValidationError[?]] = summon[Schema[A]].applyValidation(a)
// NOTE: You don't need code like this in your project as `Schema` is required by Tapir to generate API docs so just having the Schema type-class instances is good enough.
```
```scala mdoc
validate(Name("Tony Stark"))

validate(NotEmptyStr("Thor Odinson"))

validate(Person(Name("Steve Rogers")))

validate(Item(NotEmptyStr("abc-999")))
```
