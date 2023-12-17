---
sidebar_position: 3
id: custom-type
title: "Custom Type"
---

## `Import`
```scala mdoc:reset-object
import refined4s.*
```

## Define `Refined` Type

```scala
type RefinedTypeName = RefinedTypeName.Type
object RefinedTypeName extends Refined[ActualType] {
  override inline def invalidReason(a: ActualType): String =
    expectedMessage("something with blah blah")

  override inline def predicate(a: ActualType): Boolean =
    // validation logic here
}
```

e.g.)
```scala mdoc
type MyString = MyString.Type
object MyString extends Refined[String] {
  override inline def invalidReason(a: String): String =
    expectedMessage("a non-empty String")

  override inline def predicate(a: String): Boolean =
    a != ""
}
```


## Create Value
Given the following `Refined` type,
```scala mdoc
type Month = Month.Type
object Month extends Refined[Int] {
  override inline def invalidReason(a: Int): String =
    expectedMessage("Int between 1 and 12 (1 - 12)")

  override inline def predicate(a: Int): Boolean =
    a >= 1 && a <= 12
}
```

### With Compile-time Validation
If the actual value is a constant value meaning that the actual value is known in compile-time 
(e.g. number, Boolean, String literals), `Refined` provides a compile-time validation 
with its `apply` method.

**Valid cases**
```scala mdoc
Month(1)
Month(12)
```

**Invalid cases**
```scala
Month(0)
// Month(0)
// ^^^^^^^^
// Invalid value: [0]. It must be Int between 1 and 12 (1 - 12)

Month(13)
// Month(13)
// ^^^^^^^^^
// Invalid value: [13]. It must be Int between 1 and 12 (1 - 12)
```

### With Runtime Validation

**Valid cases**
```scala mdoc
val monthInput1 = 1
val monthInput2 = 12

Month.from(monthInput1)
Month.from(monthInput2)
```

**Invalid cases**
```scala mdoc
val invalidMonthInput1 = 0
val invalidMonthInput2 = 13

Month.from(invalidMonthInput1)
Month.from(invalidMonthInput2)

```

