---
sidebar_position: 1
id: newtype-refined
title: "Newtype + Refined"
---

## Create with `syntax`

### Import `syntax`

```scala mdoc:reset-object
import refined4s.syntax.*
```

### refinedNewtype (`Either[String, ?]`)
```scala mdoc
import refined4s.*
import refined4s.types.all.*

type Name = Name.Type
object Name extends Newtype[NonEmptyString]
```
```scala mdoc
"Carol Danvers".refinedNewtype[Name]

val name = "Kal-El"
name.refinedNewtype[Name]
```

```scala mdoc
"".refinedNewtype[Name]

val invalidName = ""
invalidName.refinedNewtype[Name]
```

## Create with `cats.syntax`

### Import `cats.syntax`

```scala mdoc
import refined4s.modules.cats.syntax.*
```

### refinedNewtypeNec (`EitherNec[String, ?]`)

```scala mdoc
"Carol Danvers".refinedNewtypeNec[Name]

val name2 = "Kal-El"
name2.refinedNewtypeNec[Name]
```

```scala mdoc
"".refinedNewtypeNec[Name]

val invalidName2 = ""
invalidName2.refinedNewtypeNec[Name]
```


### refinedNewtypeNel (`EitherNel[String, ?]`)

```scala mdoc
"Carol Danvers".refinedNewtypeNel[Name]

val name3 = "Kal-El"
name3.refinedNewtypeNel[Name]
```

```scala mdoc
"".refinedNewtypeNel[Name]

val invalidName3 = ""
invalidName3.refinedNewtypeNel[Name]
```

***


### validateAs (`Validated[String, ?]`)

```scala mdoc
"Carol Danvers".validateAs[Name]

val name4 = "Kal-El"
name4.validateAs[Name]
```

```scala mdoc
"".validateAs[Name]

val invalidName4 = ""
invalidName4.validateAs[Name]
```

### validateNecAs (`ValidatedNec[String, ?]`)

```scala mdoc
"Carol Danvers".validateNecAs[Name]

val name5 = "Kal-El"
name5.validateNecAs[Name]
```

```scala mdoc
"".validateNecAs[Name]

val invalidName5 = ""
invalidName5.validateNecAs[Name]
```


### validateNelAs (`ValidatedNel[String, ?]`)

```scala mdoc
"Carol Danvers".validateNelAs[Name]

val name6 = "Kal-El"
name6.validateNelAs[Name]
```

```scala mdoc
"".validateNelAs[Name]

val invalidName6 = ""
invalidName6.validateNelAs[Name]
```

