---
sidebar_position: 1
id: newtype
title: "Newtype"
---

## `Import`
```scala mdoc
import refined4s.*
```

## Define Newtype

```scala
type NewtypeName = NewtypeName.Type
object NewtypeName extends Newtype[ActualType]
```

e.g.)
```scala mdoc
type Name = Name.Type
object Name extends Newtype[String]
```

## Create Value

```scala
val newtypeName = NewtypeName(value)
```

e.g.)
```scala mdoc
val name = Name("Kevin")
```


## Get Actual Value

To get the actual value you can simply use the `value` method.

```scala
newtypeName.value
```

e.g.)
```scala mdoc
name.value
```

## Pattern Matching

For pattern matching, `Newtype` has built-in `unapply` so you can simply do

```scala mdoc
name match {
  case Name(value) =>
    println(s"Pattern matched value: $value")
}
```


## Example
```scala mdoc:reset-object
import refined4s.*

type Name = Name.Type
object Name extends Newtype[String]

type Email = Email.Type
object Email extends Newtype[String]

def hello(name: Name): Unit = println(s"Hello ${name.value}")

def send(email: Email): Unit = println(s"Sending email to ${email.value}")

val name = Name("Kevin")
// Name.Type = "Kevin"
name.value

hello(name)

val email = Email("kevin@blah.blah")
// Email.Type = "kevin@blah.blah"
email.value

send(email)
```
```scala mdoc:fail
hello("Kevin")
```
```scala mdoc:fail
send("kevin@blah.blah")
```
