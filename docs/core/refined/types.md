---
sidebar_position: 2
id: types
title: "Pre-defined Types"
---

## Import

```scala mdoc
import refined4s.types.all.*
```

## Refined `Int`
### `NegInt`: negative `Int`

#### Compile-time Validation
```scala mdoc
NegInt(-1)
```
```scala mdoc:fail
NegInt(0)
```
```scala mdoc:fail
NegInt(1)
```

#### Runtime Validation
```scala mdoc
val validNegInt = -1 
NegInt.from(validNegInt)
```
```scala mdoc
val invalidNegInt1 = 0 
NegInt.from(invalidNegInt1)

val invalidNegInt2 = 1
NegInt.from(invalidNegInt2)
```

#### Comparison
```scala mdoc
val negInt1 = NegInt(-1)
val negInt2 = NegInt(-2)

negInt1 > negInt2
negInt1 >= negInt2
negInt1 == negInt2
negInt1 < negInt2
negInt1 <= negInt2
```

#### Get Value
```scala mdoc
val negInt123 = NegInt(-123)
val negInt999 = NegInt(-999)

negInt123.value

negInt999.value
```

***

### `NonNegInt`: non-negative `Int`

#### Compile-time Validation
```scala mdoc
NonNegInt(0)
NonNegInt(1)
```

```scala mdoc:fail
NonNegInt(-2)
```

#### Runtime Validation
```scala mdoc
val validNonNegInt = 1 
NonNegInt.from(validNonNegInt)
```
```scala mdoc
val invalidNonNegInt1 = -1
NonNegInt.from(invalidNonNegInt1)

val invalidNonNegInt2 = -999
NonNegInt.from(invalidNonNegInt2)
```

#### Comparison

```scala mdoc
val nonNegInt1 = NonNegInt(0)
val nonNegInt2 = NonNegInt(999)

nonNegInt1 > nonNegInt2
nonNegInt1 >= nonNegInt2
nonNegInt1 == nonNegInt2
nonNegInt1 < nonNegInt2
nonNegInt1 <= nonNegInt2
```

#### Get Value

```scala mdoc
val nonNegInt123 = NonNegInt(0)
val nonNegInt999 = NonNegInt(999)

nonNegInt123.value

nonNegInt999.value
```

***

### `PosInt`: positive `Int`

#### Compile-time Validation
```scala mdoc
PosInt(1)
PosInt(999)
```

```scala mdoc:fail
PosInt(0)
```
```scala mdoc:fail
PosInt(-2)
```

#### Runtime Validation

```scala mdoc
val validPosInt = 1
PosInt.from(validPosInt)
```

```scala mdoc
val invalidPosInt1 = 0
PosInt.from(invalidPosInt1)

val invalidPosInt2 = -999
PosInt.from(invalidPosInt2)
```

#### Comparison

```scala mdoc
val posInt1 = PosInt(1)
val posInt2 = PosInt(999)

posInt1 > posInt2
posInt1 >= posInt2
posInt1 == posInt2
posInt1 < posInt2
posInt1 <= posInt2
```

#### Get Value

```scala mdoc
val posInt123 = PosInt(123)
val posInt999 = PosInt(999)

posInt123.value

posInt999.value
```

***

### `NonPosInt`: non-positive `Int`

#### Compile-time Validation
```scala mdoc
NonPosInt(0)
NonPosInt(-999)
```

```scala mdoc:fail
NonPosInt(1)
```

#### Runtime Validation

```scala mdoc
val validNonPosInt = 0
NonPosInt.from(validNonPosInt)

val validNonPosInt2 = -999
NonPosInt.from(validNonPosInt2)
```

```scala mdoc
val invalidNonPosInt1 = 1
NonPosInt.from(invalidNonPosInt1)

val invalidNonPosInt2 = 999
NonPosInt.from(invalidNonPosInt2)
```

#### Comparison

```scala mdoc
val nonPosInt1 = NonPosInt(0)
val nonPosInt2 = NonPosInt(-999)

nonPosInt1 > nonPosInt2
nonPosInt1 >= nonPosInt2
nonPosInt1 == nonPosInt2
nonPosInt1 < nonPosInt2
nonPosInt1 <= nonPosInt2
```

#### Get Value

```scala mdoc
val nonPosInt0 = NonPosInt(0)
val nonPosIntMinus999 = NonPosInt(-999)

nonPosInt0.value

nonPosIntMinus999.value
```

***

## Refined `Long`

...TBA...

***


## Refined `Double`

...TBA...

***

## Refined `Float`

...TBA...

***

## Refined `BigInt`

...TBA...

***

## Refined `BigDecimal`

...TBA...


***

## Refined `NonEmptyString`

...TBA...


***

## Refined `Uri`

...TBA...

