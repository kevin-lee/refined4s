import sbtcrossproject.CrossProject

ThisBuild / scalaVersion := props.ProjectScalaVersion
ThisBuild / organization := props.Org
ThisBuild / organizationName := "Kevin's Code"

ThisBuild / testFrameworks ~=
  (frameworks => (TestFramework("hedgehog.sbt.Framework") +: frameworks).distinct)

ThisBuild / developers := List(
  Developer(
    props.GitHubUsername,
    "Kevin Lee",
    "kevin.code@kevinlee.io",
    url(s"https://github.com/${props.GitHubUsername}"),
  )
)

ThisBuild / homepage := Some(url(s"https://github.com/${props.GitHubUsername}/${props.RepoName}"))
ThisBuild / scmInfo :=
  Some(
    ScmInfo(
      url(s"https://github.com/${props.GitHubUsername}/${props.RepoName}"),
      s"git@github.com:${props.GitHubUsername}/${props.RepoName}.git",
    )
  )
ThisBuild / licenses := props.licenses

ThisBuild / scalafixDependencies += "com.github.xuwei-k" %% "scalafix-rules" % "0.6.11"

ThisBuild / scalafixConfig := (
  if (scalaVersion.value.startsWith("3"))
    ((ThisBuild / baseDirectory).value / ".scalafix-scala3.conf").some
  else
    ((ThisBuild / baseDirectory).value / ".scalafix-scala2.conf").some
)

lazy val refined4s = (project in file("."))
  .enablePlugins(DevOopsGitHubReleasePlugin)
  .settings(noPublish)
  .aggregate(
    coreJvm,
    coreJs,
    catsJvm,
    catsJs,
    circeJvm,
    circeJs,
    pureconfigJvm,
    doobieCe2Jvm,
    doobieCe3Jvm,
    extrasRenderJvm,
    extrasRenderJs,
    refinedCompatScala2Jvm,
    refinedCompatScala2Js,
    refinedCompatScala3Jvm,
    refinedCompatScala3Js,
    tapirJvm,
    tapirJs,
    chimneyJvm,
    chimneyJs,
  )

lazy val core    = module("core", crossProject(JVMPlatform, JSPlatform))
  .settings(
    scalacOptions ++= List("-Xprint-suspension"),
    libraryDependencies ++= List(
      libs.extrasTypeInfo.value % Test,
      libs.cats.value           % Test,
    ),
  )
lazy val coreJvm = core.jvm
lazy val coreJs  = core
  .js
  .settings(jsSettingsForFuture)
  .settings(
    libraryDependencies ++= List(
      libs.scalajsJavaSecurerandom.value,
      libs.scalaJavaTime.value,
    )
  )

lazy val cats    = module("cats", crossProject(JVMPlatform, JSPlatform))
  .settings(
    libraryDependencies ++= List(
      libs.cats.value,
      libs.extrasTypeInfo.value % Test,
    )
  )
  .dependsOn(core % props.IncludeTest)
lazy val catsJvm = cats.jvm
lazy val catsJs  = cats.js.settings(jsSettingsForFuture)

lazy val circe    = module("circe", crossProject(JVMPlatform, JSPlatform))
  .settings(
    libraryDependencies ++= List(
      libs.circeCore.value,
      libs.circeParser.value         % Test,
      libs.circeLiteral.value        % Test,
      libs.extrasTypeInfo.value      % Test,
      libs.extrasHedgehogCirce.value % Test,
    )
  )
  .dependsOn(
    core % props.IncludeTest,
    cats,
  )
lazy val circeJvm = circe.jvm
lazy val circeJs  = circe.js.settings(jsSettingsForFuture)

lazy val pureconfig    = module("pureconfig", crossProject(JVMPlatform))
  .settings(
    libraryDependencies ++= List(
      libs.pureconfigCore,
      libs.extrasTypeInfo.value % Test,
    )
  )
  .dependsOn(core % props.IncludeTest)
lazy val pureconfigJvm = pureconfig.jvm

lazy val doobieCe2    = module("doobie-ce2", crossProject(JVMPlatform))
  .settings(
    libraryDependencies ++= List(
      libs.doobieCoreCe2,
      libs.embeddedPostgres           % Test,
      libs.effectieCe2.value          % Test,
      libs.extrasDoobieToolsCe2.value % Test,
      libs.logback                    % Test,
//      libs.kittens              % Test,
    )
  )
  .dependsOn(
    core % props.IncludeTest,
    cats,
  )
lazy val doobieCe2Jvm = doobieCe2.jvm

lazy val doobieCe3    = module("doobie-ce3", crossProject(JVMPlatform))
  .settings(
    libraryDependencies ++= List(
      libs.doobieCoreCe3,
      libs.embeddedPostgres           % Test,
      libs.effectieCe3.value          % Test,
      libs.extrasDoobieToolsCe3.value % Test,
      libs.logback                    % Test,
//      libs.kittens              % Test,
    )
  )
  .dependsOn(
    core % props.IncludeTest,
    cats,
  )
lazy val doobieCe3Jvm = doobieCe3.jvm

lazy val extrasRender    = module("extras-render", crossProject(JVMPlatform, JSPlatform))
  .settings(
    libraryDependencies ++= List(
      libs.extrasRender.value
    )
  )
  .dependsOn(
    core % props.IncludeTest
  )
lazy val extrasRenderJvm = extrasRender.jvm
lazy val extrasRenderJs  = extrasRender.js.settings(jsSettingsForFuture)

lazy val chimney    = module("chimney", crossProject(JVMPlatform, JSPlatform))
  .settings(
    libraryDependencies ++= List(
      libs.chimney.value,
      libs.tests.hedgehogExtraCore.value,
      libs.tests.hedgehogExtraRefined4s.value,
    )
  )
  .dependsOn(
    core % props.IncludeTest
  )
lazy val chimneyJvm = chimney.jvm
lazy val chimneyJs  = chimney.js.settings(jsSettingsForFuture)

lazy val refinedCompatScala2    = module("refined-compat-scala2", crossProject(JVMPlatform, JSPlatform))
  .settings(
    crossScalaVersions := List("2.12.18", "2.13.15"),
    libraryDependencies ++=
      (
        if (isScala3(scalaVersion.value))
          List.empty
        else
          List(
            ("eu.timepit" %%% "refined" % "0.9.29")
              .excludeAll("org.scala-lang.modules" %% "scala-xml")
          )
      ),
  )
lazy val refinedCompatScala2Jvm = refinedCompatScala2.jvm
lazy val refinedCompatScala2Js  = refinedCompatScala2.js.settings(jsSettingsForFuture)

lazy val refinedCompatScala3    = module("refined-compat-scala3", crossProject(JVMPlatform, JSPlatform))
  .dependsOn(
    core % props.IncludeTest
  )
lazy val refinedCompatScala3Jvm = refinedCompatScala3.jvm
lazy val refinedCompatScala3Js  = refinedCompatScala3.js.settings(jsSettingsForFuture)

lazy val tapir    = module("tapir", crossProject(JVMPlatform, JSPlatform))
  .settings(
    libraryDependencies ++= List(
      libs.tapirCore.value
    )
  )
  .dependsOn(
    core % props.IncludeTest
  )
lazy val tapirJvm = tapir.jvm
lazy val tapirJs  = tapir.js.settings(jsSettingsForFuture)

lazy val docs = (project in file("docs-gen-tmp/docs"))
  .enablePlugins(MdocPlugin, DocusaurPlugin)
  .settings(
    scalaVersion := props.Scala3Version,
    name := prefixedProjectName("docs"),
    mdocIn := file("docs/latest"),
    mdocOut := file("generated-docs/docs"),
    cleanFiles += ((ThisBuild / baseDirectory).value / "generated-docs" / "docs"),
    scalacOptions ~= (ops => ops.filter(op => !op.startsWith("-Wunused:imports") && op != "-Wnonunit-statement")),
    libraryDependencies ++= {
      import sys.process.*
      "git fetch --tags".!
      val tag           = "git rev-list --tags --max-count=1".!!.trim
      val latestVersion = s"git describe --tags $tag".!!.trim.stripPrefix("v")

      List(
        "io.kevinlee" %%% "refined4s-core"          % latestVersion,
        "io.kevinlee" %%% "refined4s-cats"          % latestVersion,
        "io.kevinlee" %%% "refined4s-chimney"       % latestVersion,
        "io.kevinlee" %%% "refined4s-circe"         % latestVersion,
        "io.kevinlee" %%% "refined4s-pureconfig"    % latestVersion,
        "io.kevinlee"  %% "refined4s-doobie-ce2"    % latestVersion,
        "io.kevinlee" %%% "refined4s-extras-render" % latestVersion,
        "io.kevinlee" %%% "refined4s-tapir"         % latestVersion,
        libs.circeCore.value,
        libs.circeLiteral.value,
        libs.circeParser.value,
      )
    },
    mdocVariables := {
      val latestVersion = {
        import sys.process.*
        "git fetch --tags".!
        val tag = "git rev-list --tags --max-count=1".!!.trim
        s"git describe --tags $tag".!!.trim.stripPrefix("v")
      }
      val websiteDir    = docusaurDir.value

      val latestVersionFile = websiteDir / "latestVersion.json"
      val latestVersionJson = s"""{"version":"$latestVersion"}"""
      IO.write(latestVersionFile, latestVersionJson)
      Map(
        "VERSION" -> latestVersion
      )
    },
    docusaurDir := (ThisBuild / baseDirectory).value / "website",
    docusaurBuildDir := docusaurDir.value / "build",
  )
  .settings(noPublish)

lazy val docsV0 = (project in file("docs-gen-tmp/docs-v0"))
  .enablePlugins(MdocPlugin)
  .settings(
    scalaVersion := props.Scala3Version,
    name := prefixedProjectName("docsV0"),
    mdocIn := file("docs/v0"),
    mdocOut := file("website/versioned_docs/version-v0/docs"),
    cleanFiles += ((ThisBuild / baseDirectory).value / "website" / "versioned_docs" / "version-v0"),
    scalacOptions ~= (ops => ops.filter(op => !op.startsWith("-Wunused:imports") && op != "-Wnonunit-statement")),
    libraryDependencies ++= {
      val theVersion          = "0.19.0"
      List(
        "io.kevinlee" %%% "refined4s-core"          % theVersion,
        "io.kevinlee" %%% "refined4s-cats"          % theVersion,
        "io.kevinlee" %%% "refined4s-chimney"       % theVersion,
        "io.kevinlee" %%% "refined4s-circe"         % theVersion,
        "io.kevinlee" %%% "refined4s-pureconfig"    % theVersion,
        "io.kevinlee"                              %% "refined4s-doobie-ce2" % theVersion,
        "io.kevinlee" %%% "refined4s-extras-render" % theVersion,
        "io.kevinlee" %%% "refined4s-tapir"         % theVersion,
        libs.circeCore.value,
        libs.circeLiteral.value,
        libs.circeParser.value,
      )
    },
    mdocVariables := Map(
      "VERSION" -> "0.19.0"
    ),
  )
  .settings(noPublish)

lazy val props =
  new {

    private val GitHubRepo = findRepoOrgAndName

    val Org = "io.kevinlee"

    val GitHubUsername = GitHubRepo.fold("kevin-lee")(_.orgToString)
    val RepoName       = GitHubRepo.fold("refined4s")(_.nameToString)

    val Scala3Version = "3.3.5"

    val ProjectScalaVersion = Scala3Version

    lazy val licenses = List("MIT" -> url("http://opensource.org/licenses/MIT"))

    val removeDottyIncompatible: ModuleID => Boolean =
      m =>
        m.name == "ammonite" ||
          m.name == "kind-projector" ||
          m.name == "better-monadic-for" ||
          m.name == "mdoc"

    val IncludeTest = "compile->compile;test->test"

    val HedgehogVersion      = "0.10.1"
    val HedgehogExtraVersion = "0.11.0"

    val ExtrasVersion = "0.44.0"

    val CatsVersion = "2.8.0"

    val CirceVersion = "0.14.3"

    val PureconfigVersion = "0.17.1"

    val DoobieCe2Version = "0.13.4"
    val DoobieCe3Version = "1.0.0-RC2"

    val EmbeddedPostgresVersion = "2.0.7"

    val EffectieVersion = "2.0.0-beta14"

    val LogbackVersion = "1.5.6"

    val KittensVersion = "3.0.0"

    val TapirVersion = "1.0.6"

    val ChimneyVersion = "1.3.0"

    val ScalajsJavaSecurerandomVersion = "1.0.0"

    val ScalaJavaTimeVersion = "2.6.0"
  }

lazy val libs = new {

  lazy val extrasTypeInfo       = Def.setting("io.kevinlee" %%% "extras-type-info" % props.ExtrasVersion)
  lazy val extrasHedgehogCirce  = Def.setting("io.kevinlee" %%% "extras-hedgehog-circe" % props.ExtrasVersion)
  lazy val extrasDoobieToolsCe2 = Def.setting("io.kevinlee" %%% "extras-doobie-tools-ce2" % props.ExtrasVersion)
  lazy val extrasDoobieToolsCe3 = Def.setting("io.kevinlee" %%% "extras-doobie-tools-ce3" % props.ExtrasVersion)
  lazy val extrasRender         = Def.setting("io.kevinlee" %%% "extras-render" % props.ExtrasVersion)

  lazy val cats = Def.setting("org.typelevel" %%% "cats-core" % props.CatsVersion)

  lazy val kittens = Def.setting("org.typelevel" %%% "kittens" % props.KittensVersion)

  lazy val circeCore    = Def.setting("io.circe" %%% "circe-core" % props.CirceVersion)
  lazy val circeParser  = Def.setting("io.circe" %%% "circe-parser" % props.CirceVersion)
  lazy val circeLiteral = Def.setting("io.circe" %%% "circe-literal" % props.CirceVersion)

  lazy val pureconfigCore    = "com.github.pureconfig" %% "pureconfig-core"    % props.PureconfigVersion
  lazy val pureconfigGeneric = "com.github.pureconfig" %% "pureconfig-generic" % props.PureconfigVersion

  lazy val doobieCoreCe2 = "org.tpolecat" %% "doobie-core" % props.DoobieCe2Version
  lazy val doobieCoreCe3 = "org.tpolecat" %% "doobie-core" % props.DoobieCe3Version

  lazy val embeddedPostgres = "io.zonky.test" % "embedded-postgres" % props.EmbeddedPostgresVersion

  lazy val effectieCore   = Def.setting("io.kevinlee" %%% "effectie-core" % props.EffectieVersion)
  lazy val effectieSyntax = Def.setting("io.kevinlee" %%% "effectie-syntax" % props.EffectieVersion)
  lazy val effectieCe2    = Def.setting("io.kevinlee" %%% "effectie-cats-effect2" % props.EffectieVersion)
  lazy val effectieCe3    = Def.setting("io.kevinlee" %%% "effectie-cats-effect3" % props.EffectieVersion)

  lazy val logback = "ch.qos.logback" % "logback-classic" % props.LogbackVersion

  lazy val tapirCore = Def.setting("com.softwaremill.sttp.tapir" %%% "tapir-core" % props.TapirVersion)

  lazy val chimney = Def.setting("io.scalaland" %%% "chimney" % props.ChimneyVersion)

  lazy val scalajsJavaSecurerandom =
    Def.setting(("org.scala-js" %%% "scalajs-java-securerandom" % props.ScalajsJavaSecurerandomVersion).cross(CrossVersion.for3Use2_13))

  lazy val scalaJavaTime = Def.setting("io.github.cquiroz" %%% "scala-java-time" % props.ScalaJavaTimeVersion)

  lazy val tests = new {

    lazy val hedgehog = Def.setting {
      List(
        "qa.hedgehog" %%% "hedgehog-core"   % props.HedgehogVersion,
        "qa.hedgehog" %%% "hedgehog-runner" % props.HedgehogVersion,
        "qa.hedgehog" %%% "hedgehog-sbt"    % props.HedgehogVersion,
      ).map(_ % Test)
    }

    lazy val hedgehogExtraCore = Def.setting("io.kevinlee" %%% "hedgehog-extra-core" % props.HedgehogExtraVersion % Test)

    lazy val hedgehogExtraRefined4s = Def.setting("io.kevinlee" %%% "hedgehog-extra-refined4s" % props.HedgehogExtraVersion % Test)
  }
}

// scalafmt: off
def prefixedProjectName(name: String) = s"${props.RepoName}${if (name.isEmpty) "" else s"-$name"}"
// scalafmt: on

def isScala3(scalaVersion: String): Boolean = scalaVersion.startsWith("3")

def module(projectName: String, crossProject: CrossProject.Builder): CrossProject = {
  val prefixedName = prefixedProjectName(projectName)
  crossProject
    .in(file(s"modules/$prefixedName"))
    .settings(
      name := prefixedName,
      fork := true,
      semanticdbEnabled := true,
      scalafixConfig := (
        if (scalaVersion.value.startsWith("3"))
          ((ThisBuild / baseDirectory).value / ".scalafix-scala3.conf").some
        else
          ((ThisBuild / baseDirectory).value / ".scalafix-scala2.conf").some
      ),
      scalacOptions ++= (if (isScala3(scalaVersion.value)) List("-no-indent") else List("-Xsource:3")),
//      scalacOptions ~= (ops => ops.filter(_ != "UTF-8")),
      libraryDependencies ++= libs.tests.hedgehog.value ++ List(libs.tests.hedgehogExtraCore.value),
      wartremoverErrors ++= Warts.allBut(Wart.Any, Wart.Nothing, Wart.ImplicitConversion, Wart.ImplicitParameter),
      Compile / console / scalacOptions :=
        (console / scalacOptions)
          .value
          .filterNot(option => option.contains("wartremover") || option.contains("import")),
      Test / console / scalacOptions :=
        (console / scalacOptions)
          .value
          .filterNot(option => option.contains("wartremover") || option.contains("import")),
      /* } WartRemover and scalacOptions */
      licenses := props.licenses,
      /* coverage { */
      coverageHighlighting := (CrossVersion.partialVersion(scalaVersion.value) match {
        case Some((2, 10)) | Some((2, 11)) =>
          false
        case _ =>
          true
      }),
      /* } coverage */

//      scalacOptions ~= (_.filterNot(_.startsWith("-language"))),
//      scalacOptions ++= List(
//        "-language:dynamics",
//        "-language:existentials",
//        "-language:higherKinds",
//        "-language:reflectiveCalls",
//        "-language:experimental.macros",
//        "-language:implicitConversions",
//      ),
    )
}

lazy val jsSettingsForFuture: SettingsDefinition = List(
  Test / fork := false,
  Test / scalacOptions ++= (if (scalaVersion.value.startsWith("3")) List.empty
                            else List("-P:scalajs:nowarnGlobalExecutionContext")),
  Test / compile / scalacOptions ++= (if (scalaVersion.value.startsWith("3")) List.empty
                                      else List("-P:scalajs:nowarnGlobalExecutionContext")),
  coverageEnabled := false,
)
