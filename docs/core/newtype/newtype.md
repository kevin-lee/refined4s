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
```scala
type Name = Name.Type
object Name extends Newtype[String]
```

## Create Value

```scala
val newtypeName = NewtypeName(value)
```

## Get Actual Value

```scala
newtypeName.value
```


## Example
```scala mdoc
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
