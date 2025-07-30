---
sidebar_position: 1
id: 'intro'
title: 'Refined4s'
slug: '/'
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

# Refined4s

[![Build Status](https://github.com/kevin-lee/refined4s/workflows/Build-All/badge.svg)](https://github.com/kevin-lee/refined4s/actions?workflow=Build-All)
[![Release Status](https://github.com/kevin-lee/refined4s/workflows/Release/badge.svg)](https://github.com/kevin-lee/refined4s/actions?workflow=Release)
![GitHub Release Date](https://img.shields.io/github/release-date/kevin-lee/refined4s?logo=github)

![Maven Central Version](https://img.shields.io/maven-central/v/io.kevinlee/refined4s-core_3)
[![Latest version](https://index.scala-lang.org/kevin-lee/refined4s/latest.svg)](https://index.scala-lang.org/kevin-lee/refined4s)

[![Hits](https://hits.sh/github.com/kevin-lee/refined4s.svg)](https://hits.sh/github.com/kevin-lee/refined4s/)
[![codecov](https://codecov.io/gh/kevin-lee/refined4s/graph/badge.svg?token=eRXmN9YMzk)](https://codecov.io/gh/kevin-lee/refined4s)

![Refined4s Logo](/img/refined4s-320x320.png)

Newtypes and Refinement types for Scala 3

|                 Project | Maven Central                                                                                          |
|------------------------:|--------------------------------------------------------------------------------------------------------|
|          refined4s-core | ![Maven Central Version](https://img.shields.io/maven-central/v/io.kevinlee/refined4s-core_3)          |
|          refined4s-cats | ![Maven Central Version](https://img.shields.io/maven-central/v/io.kevinlee/refined4s-cats_3)          |
|       refined4s-chimney | ![Maven Central Version](https://img.shields.io/maven-central/v/io.kevinlee/refined4s-chimney_3)       |
|         refined4s-circe | ![Maven Central Version](https://img.shields.io/maven-central/v/io.kevinlee/refined4s-circe_3)         |
|    refined4s-pureconfig | ![Maven Central Version](https://img.shields.io/maven-central/v/io.kevinlee/refined4s-pureconfig_3)    |
|    refined4s-doobie-ce2 | ![Maven Central Version](https://img.shields.io/maven-central/v/io.kevinlee/refined4s-doobie-ce2_3)    |
|    refined4s-doobie-ce3 | ![Maven Central Version](https://img.shields.io/maven-central/v/io.kevinlee/refined4s-doobie-ce3_3)    |
| refined4s-extras-render | ![Maven Central Version](https://img.shields.io/maven-central/v/io.kevinlee/refined4s-extras-render_3) |
|         refined4s-tapir | ![Maven Central Version](https://img.shields.io/maven-central/v/io.kevinlee/refined4s-tapir_3)         |
|       refined4s-chimney | ![Maven Central Version](https://img.shields.io/maven-central/v/io.kevinlee/refined4s-chimney_3)       |

:::info
[![Scala.js](https://www.scala-js.org/assets/badges/scalajs-1.16.0.svg)](https://www.scala-js.org)

Refined4s supports Scala.js.
:::

## Getting Started

To get `refined4s` for your project,

### refined4s-core

<Tabs
groupId="refined4s"
defaultValue="refined4s-sbt"
values={[
{label: 'sbt', value: 'refined4s-sbt'},
{label: 'sbt (with libraryDependencies)', value: 'refined4s-sbt-lib'},
{label: 'scala-cli', value: 'refined4s-scala-cli'},
]}>
<TabItem value="refined4s-sbt">

In `build.sbt`,

```scala
"io.kevinlee" %% "refined4s-core" % "@VERSION@"
```

or for Scala.js

```scala
"io.kevinlee" %%% "refined4s-core" % "@VERSION@"
```

  </TabItem>

  <TabItem value="refined4s-sbt-lib">

In `build.sbt`,

```scala
libraryDependencies += "io.kevinlee" %% "refined4s-core" % "@VERSION@"
```

or for Scala.js

```scala
libraryDependencies += "io.kevinlee" %%% "refined4s-core" % "@VERSION@"
```

  </TabItem>

  <TabItem value="refined4s-scala-cli">

```scala
//> using dep "io.kevinlee::refined4s-core:@VERSION@"
```

  </TabItem>
</Tabs>

### refined4s-cats

<Tabs
groupId="refined4s"
defaultValue="refined4s-sbt"
values={[
{label: 'sbt', value: 'refined4s-sbt'},
{label: 'sbt (with libraryDependencies)', value: 'refined4s-sbt-lib'},
{label: 'scala-cli', value: 'refined4s-scala-cli'},
]}>
<TabItem value="refined4s-sbt">

In `build.sbt`,

```scala
"io.kevinlee" %% "refined4s-cats" % "@VERSION@"
```

or for Scala.js

```scala
"io.kevinlee" %%% "refined4s-cats" % "@VERSION@"
```

  </TabItem>

  <TabItem value="refined4s-sbt-lib">

In `build.sbt`,

```scala
libraryDependencies += "io.kevinlee" %% "refined4s-cats" % "@VERSION@"
```

or for Scala.js

```scala
libraryDependencies += "io.kevinlee" %%% "refined4s-cats" % "@VERSION@"
```

  </TabItem>

  <TabItem value="refined4s-scala-cli">

```scala
//> using dep "io.kevinlee::refined4s-cats:@VERSION@"
```

  </TabItem>
</Tabs>

### refined4s-chimney

<Tabs
groupId="refined4s"
defaultValue="refined4s-sbt"
values={[
{label: 'sbt', value: 'refined4s-sbt'},
{label: 'sbt (with libraryDependencies)', value: 'refined4s-sbt-lib'},
{label: 'scala-cli', value: 'refined4s-scala-cli'},
]}>
<TabItem value="refined4s-sbt">

In `build.sbt`,

```scala
"io.kevinlee" %% "refined4s-chimney" % "@VERSION@"
```

or for Scala.js

```scala
"io.kevinlee" %%% "refined4s-chimney" % "@VERSION@"
```

  </TabItem>

  <TabItem value="refined4s-sbt-lib">

In `build.sbt`,

```scala
libraryDependencies += "io.kevinlee" %% "refined4s-chimney" % "@VERSION@"
```

or for Scala.js

```scala
libraryDependencies += "io.kevinlee" %%% "refined4s-chimney" % "@VERSION@"
```

  </TabItem>

  <TabItem value="refined4s-scala-cli">

```scala
//> using dep "io.kevinlee::refined4s-chimney:@VERSION@"
```

  </TabItem>
</Tabs>

### refined4s-circe

<Tabs
groupId="refined4s"
defaultValue="refined4s-sbt"
values={[
{label: 'sbt', value: 'refined4s-sbt'},
{label: 'sbt (with libraryDependencies)', value: 'refined4s-sbt-lib'},
{label: 'scala-cli', value: 'refined4s-scala-cli'},
]}>
<TabItem value="refined4s-sbt">

In `build.sbt`,

```scala
"io.kevinlee" %% "refined4s-circe" % "@VERSION@"
```

or for Scala.js

```scala
"io.kevinlee" %%% "refined4s-circe" % "@VERSION@"
```

  </TabItem>

  <TabItem value="refined4s-sbt-lib">

In `build.sbt`,

```scala
libraryDependencies += "io.kevinlee" %% "refined4s-circe" % "@VERSION@"
```

or for Scala.js

```scala
libraryDependencies += "io.kevinlee" %%% "refined4s-circe" % "@VERSION@"
```

  </TabItem>

  <TabItem value="refined4s-scala-cli">

```scala
//> using dep "io.kevinlee::refined4s-circe:@VERSION@"
```

  </TabItem>
</Tabs>

### refined4s-pureconfig

<Tabs
groupId="refined4s"
defaultValue="refined4s-sbt"
values={[
{label: 'sbt', value: 'refined4s-sbt'},
{label: 'sbt (with libraryDependencies)', value: 'refined4s-sbt-lib'},
{label: 'scala-cli', value: 'refined4s-scala-cli'},
]}>
<TabItem value="refined4s-sbt">

In `build.sbt`,

```scala
"io.kevinlee" %% "refined4s-pureconfig" % "@VERSION@"
```

:::note
Pureconfig doesn't support Scala.js. So, `refined4s-pureconfig` is not available
for Scala.js.
:::

  </TabItem>

  <TabItem value="refined4s-sbt-lib">

In `build.sbt`,

```scala
libraryDependencies += "io.kevinlee" %% "refined4s-pureconfig" % "@VERSION@"
```

:::note
Pureconfig doesn't support Scala.js. So, `refined4s-pureconfig` is not available
for Scala.js.
:::

  </TabItem>

  <TabItem value="refined4s-scala-cli">

```scala
//> using dep "io.kevinlee::refined4s-pureconfig:@VERSION@"
```

  </TabItem>
</Tabs>

### refined4s-doobie-ce2

<Tabs
groupId="refined4s"
defaultValue="refined4s-sbt"
values={[
{label: 'sbt', value: 'refined4s-sbt'},
{label: 'sbt (with libraryDependencies)', value: 'refined4s-sbt-lib'},
{label: 'scala-cli', value: 'refined4s-scala-cli'},
]}>
<TabItem value="refined4s-sbt">

In `build.sbt`,

```scala
"io.kevinlee" %% "refined4s-doobie-ce2" % "@VERSION@"
```

:::note
Doobie doesn't support Scala.js. So, `refined4s-doobie-ce2` is not available for
Scala.js.
:::

  </TabItem>

  <TabItem value="refined4s-sbt-lib">

In `build.sbt`,

```scala
libraryDependencies += "io.kevinlee" %% "refined4s-doobie-ce2" % "@VERSION@"
```

:::note
Doobie doesn't support Scala.js. So, `refined4s-doobie-ce2` is not available for
Scala.js.
:::

  </TabItem>

  <TabItem value="refined4s-scala-cli">

```scala
//> using dep "io.kevinlee::refined4s-doobie-ce2:@VERSION@"
```

  </TabItem>
</Tabs>

### refined4s-doobie-ce3

<Tabs
groupId="refined4s"
defaultValue="refined4s-sbt"
values={[
{label: 'sbt', value: 'refined4s-sbt'},
{label: 'sbt (with libraryDependencies)', value: 'refined4s-sbt-lib'},
{label: 'scala-cli', value: 'refined4s-scala-cli'},
]}>
<TabItem value="refined4s-sbt">

In `build.sbt`,

```scala
"io.kevinlee" %% "refined4s-doobie-ce3" % "@VERSION@"
```

:::note
Doobie doesn't support Scala.js. So, `refined4s-doobie-ce3` is not available for
Scala.js.
:::

  </TabItem>

  <TabItem value="refined4s-sbt-lib">

In `build.sbt`,

```scala
libraryDependencies += "io.kevinlee" %% "refined4s-doobie-ce3" % "@VERSION@"
```

:::note
Doobie doesn't support Scala.js. So, `refined4s-doobie-ce3` is not available for
Scala.js.
:::

  </TabItem>

  <TabItem value="refined4s-scala-cli">

```scala
//> using dep "io.kevinlee::refined4s-doobie-ce3:@VERSION@"
```

  </TabItem>
</Tabs>

***

### refined4s-extras-render

<Tabs
groupId="refined4s"
defaultValue="refined4s-sbt"
values={[
{label: 'sbt', value: 'refined4s-sbt'},
{label: 'sbt (with libraryDependencies)', value: 'refined4s-sbt-lib'},
{label: 'scala-cli', value: 'refined4s-scala-cli'},
]}>
<TabItem value="refined4s-sbt">

In `build.sbt`,

```scala
"io.kevinlee" %% "refined4s-extras-render" % "@VERSION@"
```

or for Scala.js

```scala
"io.kevinlee" %%% "refined4s-extras-render" % "@VERSION@"
```

  </TabItem>

  <TabItem value="refined4s-sbt-lib">

In `build.sbt`,

```scala
libraryDependencies += "io.kevinlee" %% "refined4s-extras-render" % "@VERSION@"
```

or for Scala.js

```scala
libraryDependencies += "io.kevinlee" %%% "refined4s-extras-render" % "@VERSION@"
```

  </TabItem>

  <TabItem value="refined4s-scala-cli">

```scala
//> using dep "io.kevinlee::refined4s-extras-render:@VERSION@"
```

  </TabItem>
</Tabs>

***

### refined4s-tapir

<Tabs
groupId="refined4s"
defaultValue="refined4s-sbt"
values={[
{label: 'sbt', value: 'refined4s-sbt'},
{label: 'sbt (with libraryDependencies)', value: 'refined4s-sbt-lib'},
{label: 'scala-cli', value: 'refined4s-scala-cli'},
]}>
<TabItem value="refined4s-sbt">

In `build.sbt`,

```scala
"io.kevinlee" %% "refined4s-tapir" % "@VERSION@"
```

or for Scala.js

```scala
"io.kevinlee" %%% "refined4s-tapir" % "@VERSION@"
```

  </TabItem>

  <TabItem value="refined4s-sbt-lib">

In `build.sbt`,

```scala
libraryDependencies += "io.kevinlee" %% "refined4s-tapir" % "@VERSION@"
```

or for Scala.js

```scala
libraryDependencies += "io.kevinlee" %%% "refined4s-tapir" % "@VERSION@"
```

  </TabItem>

  <TabItem value="refined4s-scala-cli">

```scala
//> using dep "io.kevinlee::refined4s-tapir:@VERSION@"
```

  </TabItem>
</Tabs>

***

### refined4s-chimney

<Tabs
groupId="refined4s"
defaultValue="refined4s-sbt"
values={[
{label: 'sbt', value: 'refined4s-sbt'},
{label: 'sbt (with libraryDependencies)', value: 'refined4s-sbt-lib'},
{label: 'scala-cli', value: 'refined4s-scala-cli'},
]}>
<TabItem value="refined4s-sbt">

In `build.sbt`,

```scala
"io.kevinlee" %% "refined4s-chimney" % "@VERSION@"
```

or for Scala.js

```scala
"io.kevinlee" %%% "refined4s-chimney" % "@VERSION@"
```

  </TabItem>

  <TabItem value="refined4s-sbt-lib">

In `build.sbt`,

```scala
libraryDependencies += "io.kevinlee" %% "refined4s-chimney" % "@VERSION@"
```

or for Scala.js

```scala
libraryDependencies += "io.kevinlee" %%% "refined4s-chimney" % "@VERSION@"
```

  </TabItem>

  <TabItem value="refined4s-scala-cli">

```scala
//> using dep "io.kevinlee::refined4s-chimney:@VERSION@"
```

  </TabItem>
</Tabs>

***

### All refined4s modules

<Tabs
groupId="refined4s"
defaultValue="refined4s-sbt"
values={[
{label: 'sbt', value: 'refined4s-sbt'},
{label: 'sbt (with libraryDependencies)', value: 'refined4s-sbt-lib'},
{label: 'scala-cli', value: 'refined4s-scala-cli'},
]}>
<TabItem value="refined4s-sbt">

In `build.sbt`,

```scala
"io.kevinlee" %% "refined4s-core" % "@VERSION@"
,
"io.kevinlee" %% "refined4s-cats" % "@VERSION@"
,
"io.kevinlee" %% "refined4s-chimney" % "@VERSION@"
,
"io.kevinlee" %% "refined4s-circe" % "@VERSION@"
,
"io.kevinlee" %% "refined4s-pureconfig" % "@VERSION@"
,
"io.kevinlee" %% "refined4s-doobie-ce2" % "@VERSION@"
, // Use either refined4s-doobie-ce2
"io.kevinlee" %% "refined4s-doobie-ce3" % "@VERSION@"
, // OR refined4s-doobie-ce3
"io.kevinlee" %% "refined4s-extras-render" % "@VERSION@"
,
"io.kevinlee" %% "refined4s-tapir" % "@VERSION@"
,
"io.kevinlee" %% "refined4s-chimney" % "@VERSION@"
,
```

or for Scala.js

```scala
"io.kevinlee" %%% "refined4s-core" % "@VERSION@"
,
"io.kevinlee" %%% "refined4s-cats" % "@VERSION@"
,
"io.kevinlee" %%% "refined4s-chimney" % "@VERSION@"
,
"io.kevinlee" %%% "refined4s-circe" % "@VERSION@"
,
"io.kevinlee" %%% "refined4s-extras-render" % "@VERSION@"
,
"io.kevinlee" %%% "refined4s-tapir" % "@VERSION@"
,
"io.kevinlee" %%% "refined4s-chimney" % "@VERSION@"
,
```

  </TabItem>

  <TabItem value="refined4s-sbt-lib">

In `build.sbt`,

```scala
libraryDependencies ++= Seq(
  "io.kevinlee" %% "refined4s-core" % "@VERSION@",
  "io.kevinlee" %% "refined4s-cats" % "@VERSION@",
  "io.kevinlee" %% "refined4s-chimney" % "@VERSION@",
  "io.kevinlee" %% "refined4s-circe" % "@VERSION@",
  "io.kevinlee" %% "refined4s-pureconfig" % "@VERSION@",
  "io.kevinlee" %% "refined4s-doobie-ce2" % "@VERSION@", // Use either refined4s-doobie-ce2
  "io.kevinlee" %% "refined4s-doobie-ce3" % "@VERSION@", // OR refined4s-doobie-ce3
  "io.kevinlee" %% "refined4s-extras-render" % "@VERSION@",
  "io.kevinlee" %% "refined4s-tapir" % "@VERSION@",
  "io.kevinlee" %% "refined4s-chimney" % "@VERSION@",
)
```

or for Scala.js

```scala
libraryDependencies ++= Seq(
  "io.kevinlee" %%% "refined4s-core" % "@VERSION@",
  "io.kevinlee" %%% "refined4s-cats" % "@VERSION@",
  "io.kevinlee" %%% "refined4s-chimney" % "@VERSION@",
  "io.kevinlee" %%% "refined4s-circe" % "@VERSION@",
  "io.kevinlee" %%% "refined4s-extras-render" % "@VERSION@",
  "io.kevinlee" %%% "refined4s-tapir" % "@VERSION@",
  "io.kevinlee" %%% "refined4s-chimney" % "@VERSION@",
)
```

  </TabItem>

  <TabItem value="refined4s-scala-cli">

```scala
//> using dep "io.kevinlee::refined4s-core:@VERSION@"
//> using dep "io.kevinlee::refined4s-cats:@VERSION@"
//> using dep "io.kevinlee::refined4s-chimney:@VERSION@"
//> using dep "io.kevinlee::refined4s-circe:@VERSION@"
//> using dep "io.kevinlee::refined4s-pureconfig:@VERSION@"
//> using dep "io.kevinlee::refined4s-doobie-ce2:@VERSION@" // Use either refined4s-doobie-ce2
//> using dep "io.kevinlee::refined4s-doobie-ce3:@VERSION@" // OR refined4s-doobie-ce3
//> using dep "io.kevinlee::refined4s-extras-render:@VERSION@"
//> using dep "io.kevinlee::refined4s-tapir:@VERSION@"
//> using dep "io.kevinlee::refined4s-chimney:@VERSION@"
```

  </TabItem>
</Tabs>

## Why `refined4s`?

Given the following methods

```scala mdoc:reset-object
def hello(name: String): Unit = println(s"Hello $name")
def sendEmail(email: String): Unit = {
  println(s"Sending email to [email address: $email]")
  // ... send email
}
```

You can easily mess up method parameters like this.

```scala mdoc
val name = "Kevin"
val email = "blah@blah.blah"

hello(email)
sendEmail(name)
```

If you use `refined4s`, you don't need to worry about that anymore.

```scala mdoc:reset-object
import refined4s.*

type Name = Name.Type

object Name extends Newtype[String]

type Email = Email.Type

object Email extends Newtype[String]

def hello(name: Name): Unit = println(s"Hello ${name.value}")
def sendEmail(email: Email): Unit = {
  println(s"Sending email to [email address: ${email.value}]")
  // ... send email
}
```

You can easily mess up method parameters like this.

If you pass the right types, it works.

```scala mdoc
val name = Name("Kevin")
val email = Email("blah@blah.blah")

hello(name)
sendEmail(email)
```

If you don't, it does not compile.

```scala mdoc:fail
hello(email)
sendEmail(name)
```

