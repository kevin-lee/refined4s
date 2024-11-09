---
id: 'extras-render'
title: "extras-render module"
---

## Import
```scala mdoc
import refined4s.modules.extras.derivation.types.all.given
```
```scala mdoc
import refined4s.modules.extras.derivation.*
```


## Use Drived Instances for Pre-defined Types
To make `Newtype`, `Refined` and `InlinedRefined` have `Render` type-class instances derived from the actual values, you can simply use
```scala
import refined4s.modules.extras.derivation.types.all.given
```
:::warning NOTE
This works only when the actual type already has `Render`.
:::
:::info
Using `refined4s.modules.extras.derivation.types.all.given` is required only when `Render` is required for the pre-defined types.<br/>
So if you want your `Newtype` or `Refined` or `InlinedRefined` to have `Render` instances,<br/>
you can use [pre-defined traits for extras](#with-explicit-pre-defined-extras-render-support) or the [`deriving` method](#with-deriving-method) instead.<br/>
:::
```scala mdoc:reset-object
import refined4s.*
import refined4s.types.all.*

import extras.render.*
import extras.render.syntax.*

def renderA[A: Render](a: A): Unit = println(a.render)

```

**With `derivation.types.all`,**

```scala mdoc {1}
import refined4s.modules.extras.derivation.types.all.given

final case class Person(name: NonEmptyString)
object Person {
  given renderPerson: Render[Person] = person => render"Person(name=${person.name})"
}

renderA(NonEmptyString("Tony Stark"))

val thor = Person(NonEmptyString("Thor Odinson"))
renderA(thor)

val captainAmerica = Person(NonEmptyString("Steve Rogers"))
renderA(captainAmerica)
```


## With Explicit Pre-defined extras-render Support
There are the following pre-defined traits to support extras' `Render`.
* `refined4s.modules.extras.derivation.ExtrasRender`

:::warning NOTE
This works only when the actual type already has `Render`.
:::

```scala mdoc:reset-object {5,8}
import refined4s.*
import refined4s.modules.extras.derivation.*

type Name = Name.Type
object Name extends Newtype[String] with ExtrasRender[String]

type NotEmptyStr = NotEmptyStr.Type
object NotEmptyStr extends Refined[String] with ExtrasRender[String] {
  inline def invalidReason(a: String): String = "non-empty String"

  inline def predicate(a: String): Boolean = a != ""
}

import extras.render.*
import extras.render.syntax.*

final case class Person(name: Name)

final case class Item(id: NotEmptyStr)

import extras.render.syntax.*

def renderA[A: Render](a: A): Unit = println(a.render)

```

```scala mdoc
renderA(Name("Tony Stark"))

renderA(NotEmptyStr("Thor Odinson"))

renderA(Person(Name("Steve Rogers")).name)

renderA(Item(NotEmptyStr("abc-999")).id)
```


## With `deriving` Method
If you want to have explicit `Render` type-class instances in your `Newtype` or `Refined` or `InlinedRefined`, you can use the `deriving` method.

:::warning NOTE
This works only when the actual type already has `Render`.
:::

```scala mdoc:reset-object {10,19}
import cats.*

import refined4s.*
import refined4s.modules.extras.derivation.*

import extras.render.*

type Name = Name.Type
object Name extends Newtype[String] {
  given renderName: Render[Name] = deriving[Render]
}

type NotEmptyStr = NotEmptyStr.Type
object NotEmptyStr extends Refined[String] {
  inline def invalidReason(a: String): String = "non-empty String"

  inline def predicate(a: String): Boolean = a != ""

  given renderNotEmptyStr: Render[NotEmptyStr] = deriving[Render]
}

import extras.render.syntax.*

final case class Person(name: Name)

final case class Item(id: NotEmptyStr)

import extras.render.syntax.*

def renderA[A: Render](a: A): Unit = println(a.render)

```
```scala mdoc
renderA(Name("Tony Stark"))

renderA(NotEmptyStr("Thor Odinson"))

renderA(Person(Name("Steve Rogers")).name)

renderA(Item(NotEmptyStr("abc-999")).id)
```
