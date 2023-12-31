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
[![Latest version](https://index.scala-lang.org/kevin-lee/refined4s/latest.svg)](https://index.scala-lang.org/kevin-lee/refined4s)

![Refined4s Logo](/img/refined4s-320x320.png)

Newtypes and Refinement types for Scala 3


|              Project | Maven Central                                                                                                                                                                           |
|---------------------:|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
|       refined4s-core | [![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.kevinlee/refined4s-core_3/badge.svg)](https://search.maven.org/artifact/io.kevinlee/refined4s-core_3)             |
|       refined4s-cats | [![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.kevinlee/refined4s-cats_3/badge.svg)](https://search.maven.org/artifact/io.kevinlee/refined4s-cats_3)             |
|      refined4s-circe | [![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.kevinlee/refined4s-circe_3/badge.svg)](https://search.maven.org/artifact/io.kevinlee/refined4s-circe_3)           |
| refined4s-pureconfig | [![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.kevinlee/refined4s-pureconfig_3/badge.svg)](https://search.maven.org/artifact/io.kevinlee/refined4s-pureconfig_3) |
| refined4s-doobie-ce2 | [![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.kevinlee/refined4s-doobie-ce2_3/badge.svg)](https://search.maven.org/artifact/io.kevinlee/refined4s-doobie-ce2_3) |
| refined4s-doobie-ce3 | [![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.kevinlee/refined4s-doobie-ce3_3/badge.svg)](https://search.maven.org/artifact/io.kevinlee/refined4s-doobie-ce3_3) |

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

  </TabItem>

  <TabItem value="refined4s-sbt-lib">

In `build.sbt`,

```scala
libraryDependencies += "io.kevinlee" %% "refined4s-core" % "@VERSION@"
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

  </TabItem>

  <TabItem value="refined4s-sbt-lib">

In `build.sbt`,

```scala
libraryDependencies += "io.kevinlee" %% "refined4s-cats" % "@VERSION@"
```

  </TabItem>

  <TabItem value="refined4s-scala-cli">

```scala
//> using dep "io.kevinlee::refined4s-cats:@VERSION@"
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

  </TabItem>

  <TabItem value="refined4s-sbt-lib">

In `build.sbt`,

```scala
libraryDependencies += "io.kevinlee" %% "refined4s-circe" % "@VERSION@"
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

  </TabItem>

  <TabItem value="refined4s-sbt-lib">

In `build.sbt`,

```scala
libraryDependencies += "io.kevinlee" %% "refined4s-pureconfig" % "@VERSION@"
```

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

  </TabItem>

  <TabItem value="refined4s-sbt-lib">

In `build.sbt`,

```scala
libraryDependencies += "io.kevinlee" %% "refined4s-doobie-ce2" % "@VERSION@"
```

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

  </TabItem>

  <TabItem value="refined4s-sbt-lib">

In `build.sbt`,

```scala
libraryDependencies += "io.kevinlee" %% "refined4s-doobie-ce3" % "@VERSION@"
```

  </TabItem>

  <TabItem value="refined4s-scala-cli">

```scala
//> using dep "io.kevinlee::refined4s-doobie-ce3:@VERSION@"
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
"io.kevinlee" %% "refined4s-core" % "@VERSION@",
"io.kevinlee" %% "refined4s-cats" % "@VERSION@",
"io.kevinlee" %% "refined4s-circe" % "@VERSION@",
"io.kevinlee" %% "refined4s-pureconfig" % "@VERSION@",
"io.kevinlee" %% "refined4s-doobie-ce2" % "@VERSION@", // Use either refined4s-doobie-ce2
"io.kevinlee" %% "refined4s-doobie-ce3" % "@VERSION@", // OR refined4s-doobie-ce3
```

  </TabItem>

  <TabItem value="refined4s-sbt-lib">

In `build.sbt`,

```scala
libraryDependencies ++= Seq(
  "io.kevinlee" %% "refined4s-core" % "@VERSION@",
  "io.kevinlee" %% "refined4s-cats" % "@VERSION@",
  "io.kevinlee" %% "refined4s-circe" % "@VERSION@",
  "io.kevinlee" %% "refined4s-pureconfig" % "@VERSION@",
  "io.kevinlee" %% "refined4s-doobie-ce2" % "@VERSION@", // Use either refined4s-doobie-ce2
  "io.kevinlee" %% "refined4s-doobie-ce3" % "@VERSION@", // OR refined4s-doobie-ce3
)
```

  </TabItem>

  <TabItem value="refined4s-scala-cli">

```scala
//> using dep "io.kevinlee::refined4s-core:@VERSION@"
//> using dep "io.kevinlee::refined4s-cats:@VERSION@"
//> using dep "io.kevinlee::refined4s-circe:@VERSION@"
//> using dep "io.kevinlee::refined4s-pureconfig:@VERSION@"
//> using dep "io.kevinlee::refined4s-doobie-ce2:@VERSION@" // Use either refined4s-doobie-ce2
//> using dep "io.kevinlee::refined4s-doobie-ce3:@VERSION@" // OR refined4s-doobie-ce3
```

  </TabItem>
</Tabs>

