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

### `NegLong`: negative `Long`

#### Compile-time Validation
```scala mdoc
NegLong(-1L)
```
```scala mdoc:fail
NegLong(0L)
```
```scala mdoc:fail
NegLong(1L)
```

#### Runtime Validation
```scala mdoc
val validNegLong = -1L 
NegLong.from(validNegLong)
```
```scala mdoc
val invalidNegLong1 = 0L 
NegLong.from(invalidNegLong1)

val invalidNegLong2 = 1L
NegLong.from(invalidNegLong2)
```

#### Comparison

```scala mdoc
val negLong1 = NegLong(-1L)
val negLong2 = NegLong(-2L)

negLong1 > negLong2
negLong1 >= negLong2
negLong1 == negLong2
negLong1 < negLong2
negLong1 <= negLong2
```

#### Get Value

```scala mdoc
val negLong123 = NegLong(-123L)
val negLong999 = NegLong(-999L)

negLong123.value

negLong999.value
```

***

### `NonNegLong`: non-negative `Long`

#### Compile-time Validation
```scala mdoc
NonNegLong(0L)
NonNegLong(1L)
```

```scala mdoc:fail
NonNegLong(-2L)
```

#### Runtime Validation

```scala mdoc
val validNonNegLong = 1L
NonNegLong.from(validNonNegLong)
```

```scala mdoc
val invalidNonNegLong1 = -1L
NonNegLong.from(invalidNonNegLong1)

val invalidNonNegLong2 = -999L
NonNegLong.from(invalidNonNegLong2)
```

#### Comparison

```scala mdoc
val nonNegLong1 = NonNegLong(0L)
val nonNegLong2 = NonNegLong(999L)

nonNegLong1 > nonNegLong2
nonNegLong1 >= nonNegLong2
nonNegLong1 == nonNegLong2
nonNegLong1 < nonNegLong2
nonNegLong1 <= nonNegLong2
```

#### Get Value

```scala mdoc
val nonNegLong123 = NonNegLong(0L)
val nonNegLong999 = NonNegLong(999L)

nonNegLong123.value

nonNegLong999.value
```

***

### `PosLong`: positive `Long`

#### Compile-time Validation
```scala mdoc
PosLong(1L)
PosLong(999L)
```

```scala mdoc:fail
PosLong(0L)
```
```scala mdoc:fail
PosLong(-2L)
```

#### Runtime Validation

```scala mdoc
val validPosLong = 1L
PosLong.from(validPosLong)
```

```scala mdoc
val invalidPosLong1 = 0L
PosLong.from(invalidPosLong1)

val invalidPosLong2 = -999L
PosLong.from(invalidPosLong2)
```

#### Comparison

```scala mdoc
val posLong1 = PosLong(1L)
val posLong2 = PosLong(999L)

posLong1 > posLong2
posLong1 >= posLong2
posLong1 == posLong2
posLong1 < posLong2
posLong1 <= posLong2
```

#### Get Value

```scala mdoc
val posLong123 = PosLong(123L)
val posLong999 = PosLong(999L)

posLong123.value

posLong999.value
```

***

### `NonPosLong`: non-positive `Long`

#### Compile-time Validation
```scala mdoc
NonPosLong(0L)
NonPosLong(-999L)
```

```scala mdoc:fail
NonPosLong(1L)
```

#### Runtime Validation

```scala mdoc
val validNonPosLong = 0L
NonPosLong.from(validNonPosLong)

val validNonPosLong2 = -999L
NonPosLong.from(validNonPosLong2)
```

```scala mdoc
val invalidNonPosLong1 = 1L
NonPosLong.from(invalidNonPosLong1)

val invalidNonPosLong2 = 999L
NonPosLong.from(invalidNonPosLong2)
```

#### Comparison

```scala mdoc
val nonPosLong1 = NonPosLong(0L)
val nonPosLong2 = NonPosLong(-999L)

nonPosLong1 > nonPosLong2
nonPosLong1 >= nonPosLong2
nonPosLong1 == nonPosLong2
nonPosLong1 < nonPosLong2
nonPosLong1 <= nonPosLong2
```

#### Get Value

```scala mdoc
val nonPosLong0 = NonPosLong(0L)
val nonPosLongMinus999 = NonPosLong(-999L)

nonPosLong0.value

nonPosLongMinus999.value
```


***


## Refined `Double`

### `NegDouble`: negative `Double`

#### Compile-time Validation
```scala mdoc
NegDouble(-0.00001d)
NegDouble(-999.999d)
```
```scala mdoc:fail
NegDouble(0d)
```
```scala mdoc:fail
NegDouble(999.999d)
```

#### Runtime Validation
```scala mdoc
val validNegDouble = -0.00001d 
NegDouble.from(validNegDouble)
```
```scala mdoc
val invalidNegDouble1 = 0d 
NegDouble.from(invalidNegDouble1)

val invalidNegDouble2 = 999.999d
NegDouble.from(invalidNegDouble2)
```

#### Comparison

```scala mdoc
val negDouble1 = NegDouble(-0.1d)
val negDouble2 = NegDouble(-0.2d)

negDouble1 > negDouble2
negDouble1 >= negDouble2
negDouble1 == negDouble2
negDouble1 < negDouble2
negDouble1 <= negDouble2
```

#### Get Value

```scala mdoc
val negDouble123 = NegDouble(-123.123d)
val negDouble999 = NegDouble(-999.999d)

negDouble123.value

negDouble999.value
```

***

### `NonNegDouble`: non-negative `Double`

#### Compile-time Validation
```scala mdoc
NonNegDouble(0d)
NonNegDouble(999.999d)
```

```scala mdoc:fail
NonNegDouble(-0.00001d)
```
```
```scala mdoc:fail
NonNegDouble(-999.999d)
```

#### Runtime Validation

```scala mdoc
val validNonNegDouble1 = 0d
NonNegDouble.from(validNonNegDouble1)

val validNonNegDouble2 = 999.999d
NonNegDouble.from(validNonNegDouble2)
```

```scala mdoc
val invalidNonNegDouble1 = -0.00001d
NonNegDouble.from(invalidNonNegDouble1)

val invalidNonNegDouble2 = -999.999d
NonNegDouble.from(invalidNonNegDouble2)
```

#### Comparison

```scala mdoc
val nonNegDouble1 = NonNegDouble(0d)
val nonNegDouble2 = NonNegDouble(0.999d)

nonNegDouble1 > nonNegDouble2
nonNegDouble1 >= nonNegDouble2
nonNegDouble1 == nonNegDouble2
nonNegDouble1 < nonNegDouble2
nonNegDouble1 <= nonNegDouble2
```

#### Get Value

```scala mdoc
val nonNegDouble123 = NonNegDouble(0d)
val nonNegDouble999 = NonNegDouble(999.999d)

nonNegDouble123.value

nonNegDouble999.value
```

***

### `PosDouble`: positive `Double`

#### Compile-time Validation
```scala mdoc
PosDouble(0.00001d)
PosDouble(999.999d)
```

```scala mdoc:fail
PosDouble(0d)
```
```scala mdoc:fail
PosDouble(-999.999d)
```

#### Runtime Validation

```scala mdoc
val validPosDouble1 = 0.00001d
PosDouble.from(validPosDouble1)

val validPosDouble2 = 999.999d
PosDouble.from(validPosDouble2)
```

```scala mdoc
val invalidPosDouble1 = 0d
PosDouble.from(invalidPosDouble1)

val invalidPosDouble2 = -999.999d
PosDouble.from(invalidPosDouble2)
```

#### Comparison

```scala mdoc
val posDouble1 = PosDouble(0.00001d)
val posDouble2 = PosDouble(0.999d)

posDouble1 > posDouble2
posDouble1 >= posDouble2
posDouble1 == posDouble2
posDouble1 < posDouble2
posDouble1 <= posDouble2
```

#### Get Value

```scala mdoc
val posDouble123 = PosDouble(123.123d)
val posDouble999 = PosDouble(999.999d)

posDouble123.value

posDouble999.value
```

***

### `NonPosDouble`: non-positive `Double`

#### Compile-time Validation
```scala mdoc
NonPosDouble(0d)
NonPosDouble(-999.999d)
```

```scala mdoc:fail
NonPosDouble(0.00001d)
```
```scala mdoc:fail
NonPosDouble(999.999d)
```

#### Runtime Validation

```scala mdoc
val validNonPosDouble = 0d
NonPosDouble.from(validNonPosDouble)

val validNonPosDouble2 = -999.999d
NonPosDouble.from(validNonPosDouble2)
```

```scala mdoc
val invalidNonPosDouble1 = 0.00001d
NonPosDouble.from(invalidNonPosDouble1)

val invalidNonPosDouble2 = 999.999d
NonPosDouble.from(invalidNonPosDouble2)
```

#### Comparison

```scala mdoc
val nonPosDouble1 = NonPosDouble(0d)
val nonPosDouble2 = NonPosDouble(-0.999d)

nonPosDouble1 > nonPosDouble2
nonPosDouble1 >= nonPosDouble2
nonPosDouble1 == nonPosDouble2
nonPosDouble1 < nonPosDouble2
nonPosDouble1 <= nonPosDouble2
```

#### Get Value

```scala mdoc
val nonPosDouble0 = NonPosDouble(0d)
val nonPosDoubleMinus999 = NonPosDouble(-999.999d)

nonPosDouble0.value

nonPosDoubleMinus999.value
```


***

## Refined `Float`


### `NegFloat`: negative `Float`

#### Compile-time Validation
```scala mdoc
NegFloat(-0.00001f)
NegFloat(-999.999f)
```
```scala mdoc:fail
NegFloat(0f)
```
```scala mdoc:fail
NegFloat(999.999f)
```

#### Runtime Validation
```scala mdoc
val validNegFloat = -0.00001f 
NegFloat.from(validNegFloat)
```
```scala mdoc
val invalidNegFloat1 = 0f 
NegFloat.from(invalidNegFloat1)

val invalidNegFloat2 = 999.999f
NegFloat.from(invalidNegFloat2)
```

#### Comparison

```scala mdoc
val negFloat1 = NegFloat(-0.1f)
val negFloat2 = NegFloat(-0.2f)

negFloat1 > negFloat2
negFloat1 >= negFloat2
negFloat1 == negFloat2
negFloat1 < negFloat2
negFloat1 <= negFloat2
```

#### Get Value

```scala mdoc
val negFloat123 = NegFloat(-123.123f)
val negFloat999 = NegFloat(-999.999f)

negFloat123.value

negFloat999.value
```

***

### `NonNegFloat`: non-negative `Float`

#### Compile-time Validation
```scala mdoc
NonNegFloat(0f)
NonNegFloat(999.999f)
```

```scala mdoc:fail
NonNegFloat(-0.00001f)
```
```
```scala mdoc:fail
NonNegFloat(-999.999f)
```

#### Runtime Validation

```scala mdoc
val validNonNegFloat1 = 0f
NonNegFloat.from(validNonNegFloat1)

val validNonNegFloat2 = 999.999f
NonNegFloat.from(validNonNegFloat2)
```

```scala mdoc
val invalidNonNegFloat1 = -0.00001f
NonNegFloat.from(invalidNonNegFloat1)

val invalidNonNegFloat2 = -999.999f
NonNegFloat.from(invalidNonNegFloat2)
```

#### Comparison

```scala mdoc
val nonNegFloat1 = NonNegFloat(0f)
val nonNegFloat2 = NonNegFloat(0.999f)

nonNegFloat1 > nonNegFloat2
nonNegFloat1 >= nonNegFloat2
nonNegFloat1 == nonNegFloat2
nonNegFloat1 < nonNegFloat2
nonNegFloat1 <= nonNegFloat2
```

#### Get Value

```scala mdoc
val nonNegFloat123 = NonNegFloat(0f)
val nonNegFloat999 = NonNegFloat(999.999f)

nonNegFloat123.value

nonNegFloat999.value
```

***

### `PosFloat`: positive `Float`

#### Compile-time Validation
```scala mdoc
PosFloat(0.00001f)
PosFloat(999.999f)
```

```scala mdoc:fail
PosFloat(0f)
```
```scala mdoc:fail
PosFloat(-999.999f)
```

#### Runtime Validation

```scala mdoc
val validPosFloat1 = 0.00001f
PosFloat.from(validPosFloat1)

val validPosFloat2 = 999.999f
PosFloat.from(validPosFloat2)
```

```scala mdoc
val invalidPosFloat1 = 0f
PosFloat.from(invalidPosFloat1)

val invalidPosFloat2 = -999.999f
PosFloat.from(invalidPosFloat2)
```

#### Comparison

```scala mdoc
val posFloat1 = PosFloat(0.00001f)
val posFloat2 = PosFloat(0.999f)

posFloat1 > posFloat2
posFloat1 >= posFloat2
posFloat1 == posFloat2
posFloat1 < posFloat2
posFloat1 <= posFloat2
```

#### Get Value

```scala mdoc
val posFloat123 = PosFloat(123.123f)
val posFloat999 = PosFloat(999.999f)

posFloat123.value

posFloat999.value
```

***

### `NonPosFloat`: non-positive `Float`

#### Compile-time Validation
```scala mdoc
NonPosFloat(0f)
NonPosFloat(-999.999f)
```

```scala mdoc:fail
NonPosFloat(0.00001f)
```
```scala mdoc:fail
NonPosFloat(999.999f)
```

#### Runtime Validation

```scala mdoc
val validNonPosFloat = 0f
NonPosFloat.from(validNonPosFloat)

val validNonPosFloat2 = -999.999f
NonPosFloat.from(validNonPosFloat2)
```

```scala mdoc
val invalidNonPosFloat1 = 0.00001f
NonPosFloat.from(invalidNonPosFloat1)

val invalidNonPosFloat2 = 999.999f
NonPosFloat.from(invalidNonPosFloat2)
```

#### Comparison

```scala mdoc
val nonPosFloat1 = NonPosFloat(0f)
val nonPosFloat2 = NonPosFloat(-0.999f)

nonPosFloat1 > nonPosFloat2
nonPosFloat1 >= nonPosFloat2
nonPosFloat1 == nonPosFloat2
nonPosFloat1 < nonPosFloat2
nonPosFloat1 <= nonPosFloat2
```

#### Get Value

```scala mdoc
val nonPosFloat0 = NonPosFloat(0f)
val nonPosFloatMinus999 = NonPosFloat(-999.999f)

nonPosFloat0.value

nonPosFloatMinus999.value
```


***

## Refined `BigInt`

### `NegBigInt`: negative `BigInt`

#### Compile-time Validation
```scala mdoc
NegBigInt(-1)
```
```scala mdoc:fail
NegBigInt(0)
```
```scala mdoc:fail
NegBigInt(1)
```

#### Runtime Validation
```scala mdoc
val validNegBigInt = -1 
NegBigInt.from(validNegBigInt)
```
```scala mdoc
val invalidNegBigInt1 = 0 
NegBigInt.from(invalidNegBigInt1)

val invalidNegBigInt2 = 1
NegBigInt.from(invalidNegBigInt2)
```

#### Comparison
```scala mdoc
val negBigInt1 = NegBigInt(-1)
val negBigInt2 = NegBigInt(-2)

negBigInt1 > negBigInt2
negBigInt1 >= negBigInt2
negBigInt1 == negBigInt2
negBigInt1 < negBigInt2
negBigInt1 <= negBigInt2
```

#### Get Value
```scala mdoc
val negBigInt123 = NegBigInt(-123)
val negBigInt999 = NegBigInt(-999)

negBigInt123.value

negBigInt999.value
```

***

### `NonNegBigInt`: non-negative `BigInt`

#### Compile-time Validation
```scala mdoc
NonNegBigInt(0)
NonNegBigInt(1)
```

```scala mdoc:fail
NonNegBigInt(-2)
```

#### Runtime Validation
```scala mdoc
val validNonNegBigInt = 1 
NonNegBigInt.from(validNonNegBigInt)
```
```scala mdoc
val invalidNonNegBigInt1 = -1
NonNegBigInt.from(invalidNonNegBigInt1)

val invalidNonNegBigInt2 = -999
NonNegBigInt.from(invalidNonNegBigInt2)
```

#### Comparison

```scala mdoc
val nonNegBigInt1 = NonNegBigInt(0)
val nonNegBigInt2 = NonNegBigInt(999)

nonNegBigInt1 > nonNegBigInt2
nonNegBigInt1 >= nonNegBigInt2
nonNegBigInt1 == nonNegBigInt2
nonNegBigInt1 < nonNegBigInt2
nonNegBigInt1 <= nonNegBigInt2
```

#### Get Value

```scala mdoc
val nonNegBigInt123 = NonNegBigInt(0)
val nonNegBigInt999 = NonNegBigInt(999)

nonNegBigInt123.value

nonNegBigInt999.value
```

***

### `PosBigInt`: positive `BigInt`

#### Compile-time Validation
```scala mdoc
PosBigInt(1)
PosBigInt(999)
```

```scala mdoc:fail
PosBigInt(0)
```
```scala mdoc:fail
PosBigInt(-2)
```

#### Runtime Validation

```scala mdoc
val validPosBigInt = 1
PosBigInt.from(validPosBigInt)
```

```scala mdoc
val invalidPosBigInt1 = 0
PosBigInt.from(invalidPosBigInt1)

val invalidPosBigInt2 = -999
PosBigInt.from(invalidPosBigInt2)
```

#### Comparison

```scala mdoc
val posBigInt1 = PosBigInt(1)
val posBigInt2 = PosBigInt(999)

posBigInt1 > posBigInt2
posBigInt1 >= posBigInt2
posBigInt1 == posBigInt2
posBigInt1 < posBigInt2
posBigInt1 <= posBigInt2
```

#### Get Value

```scala mdoc
val posBigInt123 = PosBigInt(123)
val posBigInt999 = PosBigInt(999)

posBigInt123.value

posBigInt999.value
```

***

### `NonPosBigInt`: non-positive `BigInt`

#### Compile-time Validation
```scala mdoc
NonPosBigInt(0)
NonPosBigInt(-999)
```

```scala mdoc:fail
NonPosBigInt(1)
```

#### Runtime Validation

```scala mdoc
val validNonPosBigInt = 0
NonPosBigInt.from(validNonPosBigInt)

val validNonPosBigInt2 = -999
NonPosBigInt.from(validNonPosBigInt2)
```

```scala mdoc
val invalidNonPosBigInt1 = 1
NonPosBigInt.from(invalidNonPosBigInt1)

val invalidNonPosBigInt2 = 999
NonPosBigInt.from(invalidNonPosBigInt2)
```

#### Comparison

```scala mdoc
val nonPosBigInt1 = NonPosBigInt(0)
val nonPosBigInt2 = NonPosBigInt(-999)

nonPosBigInt1 > nonPosBigInt2
nonPosBigInt1 >= nonPosBigInt2
nonPosBigInt1 == nonPosBigInt2
nonPosBigInt1 < nonPosBigInt2
nonPosBigInt1 <= nonPosBigInt2
```

#### Get Value

```scala mdoc
val nonPosBigInt0 = NonPosBigInt(0)
val nonPosBigIntMinus999 = NonPosBigInt(-999)

nonPosBigInt0.value

nonPosBigIntMinus999.value
```


***

## Refined `BigDecimal`


### `NegBigDecimal`: negative `BigDecimal`

#### Compile-time Validation
```scala mdoc
NegBigDecimal(-0.00001d)
NegBigDecimal(-999.999d)
```
```scala mdoc:fail
NegBigDecimal(0d)
```
```scala mdoc:fail
NegBigDecimal(999.999d)
```

#### Runtime Validation
```scala mdoc
val validNegBigDecimal = -0.00001d 
NegBigDecimal.from(validNegBigDecimal)
```
```scala mdoc
val invalidNegBigDecimal1 = 0d 
NegBigDecimal.from(invalidNegBigDecimal1)

val invalidNegBigDecimal2 = 999.999d
NegBigDecimal.from(invalidNegBigDecimal2)
```

#### Comparison

```scala mdoc
val negBigDecimal1 = NegBigDecimal(-0.1d)
val negBigDecimal2 = NegBigDecimal(-0.2d)

negBigDecimal1 > negBigDecimal2
negBigDecimal1 >= negBigDecimal2
negBigDecimal1 == negBigDecimal2
negBigDecimal1 < negBigDecimal2
negBigDecimal1 <= negBigDecimal2
```

#### Get Value

```scala mdoc
val negBigDecimal123 = NegBigDecimal(-123.123d)
val negBigDecimal999 = NegBigDecimal(-999.999d)

negBigDecimal123.value

negBigDecimal999.value
```

***

### `NonNegBigDecimal`: non-negative `BigDecimal`

#### Compile-time Validation
```scala mdoc
NonNegBigDecimal(0d)
NonNegBigDecimal(999.999d)
```

```scala mdoc:fail
NonNegBigDecimal(-0.00001d)
```
```
```scala mdoc:fail
NonNegBigDecimal(-999.999d)
```

#### Runtime Validation

```scala mdoc
val validNonNegBigDecimal1 = 0d
NonNegBigDecimal.from(validNonNegBigDecimal1)

val validNonNegBigDecimal2 = 999.999d
NonNegBigDecimal.from(validNonNegBigDecimal2)
```

```scala mdoc
val invalidNonNegBigDecimal1 = -0.00001d
NonNegBigDecimal.from(invalidNonNegBigDecimal1)

val invalidNonNegBigDecimal2 = -999.999d
NonNegBigDecimal.from(invalidNonNegBigDecimal2)
```

#### Comparison

```scala mdoc
val nonNegBigDecimal1 = NonNegBigDecimal(0d)
val nonNegBigDecimal2 = NonNegBigDecimal(0.999d)

nonNegBigDecimal1 > nonNegBigDecimal2
nonNegBigDecimal1 >= nonNegBigDecimal2
nonNegBigDecimal1 == nonNegBigDecimal2
nonNegBigDecimal1 < nonNegBigDecimal2
nonNegBigDecimal1 <= nonNegBigDecimal2
```

#### Get Value

```scala mdoc
val nonNegBigDecimal123 = NonNegBigDecimal(0d)
val nonNegBigDecimal999 = NonNegBigDecimal(999.999d)

nonNegBigDecimal123.value

nonNegBigDecimal999.value
```

***

### `PosBigDecimal`: positive `BigDecimal`

#### Compile-time Validation
```scala mdoc
PosBigDecimal(0.00001d)
PosBigDecimal(999.999d)
```

```scala mdoc:fail
PosBigDecimal(0d)
```
```scala mdoc:fail
PosBigDecimal(-999.999d)
```

#### Runtime Validation

```scala mdoc
val validPosBigDecimal1 = 0.00001d
PosBigDecimal.from(validPosBigDecimal1)

val validPosBigDecimal2 = 999.999d
PosBigDecimal.from(validPosBigDecimal2)
```

```scala mdoc
val invalidPosBigDecimal1 = 0d
PosBigDecimal.from(invalidPosBigDecimal1)

val invalidPosBigDecimal2 = -999.999d
PosBigDecimal.from(invalidPosBigDecimal2)
```

#### Comparison

```scala mdoc
val posBigDecimal1 = PosBigDecimal(0.00001d)
val posBigDecimal2 = PosBigDecimal(0.999d)

posBigDecimal1 > posBigDecimal2
posBigDecimal1 >= posBigDecimal2
posBigDecimal1 == posBigDecimal2
posBigDecimal1 < posBigDecimal2
posBigDecimal1 <= posBigDecimal2
```

#### Get Value

```scala mdoc
val posBigDecimal123 = PosBigDecimal(123.123d)
val posBigDecimal999 = PosBigDecimal(999.999d)

posBigDecimal123.value

posBigDecimal999.value
```

***

### `NonPosBigDecimal`: non-positive `BigDecimal`

#### Compile-time Validation
```scala mdoc
NonPosBigDecimal(0d)
NonPosBigDecimal(-999.999d)
```

```scala mdoc:fail
NonPosBigDecimal(0.00001d)
```
```scala mdoc:fail
NonPosBigDecimal(999.999d)
```

#### Runtime Validation

```scala mdoc
val validNonPosBigDecimal = 0d
NonPosBigDecimal.from(validNonPosBigDecimal)

val validNonPosBigDecimal2 = -999.999d
NonPosBigDecimal.from(validNonPosBigDecimal2)
```

```scala mdoc
val invalidNonPosBigDecimal1 = 0.00001d
NonPosBigDecimal.from(invalidNonPosBigDecimal1)

val invalidNonPosBigDecimal2 = 999.999d
NonPosBigDecimal.from(invalidNonPosBigDecimal2)
```

#### Comparison

```scala mdoc
val nonPosBigDecimal1 = NonPosBigDecimal(0d)
val nonPosBigDecimal2 = NonPosBigDecimal(-0.999d)

nonPosBigDecimal1 > nonPosBigDecimal2
nonPosBigDecimal1 >= nonPosBigDecimal2
nonPosBigDecimal1 == nonPosBigDecimal2
nonPosBigDecimal1 < nonPosBigDecimal2
nonPosBigDecimal1 <= nonPosBigDecimal2
```

#### Get Value

```scala mdoc
val nonPosBigDecimal0 = NonPosBigDecimal(0d)
val nonPosBigDecimalMinus999 = NonPosBigDecimal(-999.999d)

nonPosBigDecimal0.value

nonPosBigDecimalMinus999.value
```


***

## Refined `NonEmptyString`

...TBA...


***

## Refined `Uri`

...TBA...

