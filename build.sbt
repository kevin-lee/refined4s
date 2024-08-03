import just.semver.SemVer
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

ThisBuild / resolvers += props.SonatypeSnapshots

ThisBuild / scalafixDependencies += "com.github.xuwei-k" %% "scalafix-rules" % "0.3.0"

ThisBuild / scalafixConfig := (
  if (scalaVersion.value.startsWith("3"))
    ((ThisBuild / baseDirectory).value / ".scalafix-scala3.conf").some
  else
    ((ThisBuild / baseDirectory).value / ".scalafix-scala2.conf").some
)

ThisBuild / scalafixScalaBinaryVersion := {
  val log        = sLog.value
  val newVersion = if (scalaVersion.value.startsWith("3")) {
    (ThisBuild / scalafixScalaBinaryVersion).value
  } else {
    CrossVersion.binaryScalaVersion(scalaVersion.value)
  }

  log.info(
    s">> Change ThisBuild / scalafixScalaBinaryVersion from ${(ThisBuild / scalafixScalaBinaryVersion).value} to $newVersion"
  )
  newVersion
}

lazy val refined4s = (project in file("."))
  .enablePlugins(DevOopsGitHubReleasePlugin)
  .settings(mavenCentralPublishSettings)
  .settings(noPublish)
  .aggregate(
    coreJvm,
    coreJs,
    catsJvm,
    catsJs,
    circeJvm,
    circeJs,
    pureconfigJvm,
    pureconfigJs,
    doobieCe2Jvm,
    doobieCe2Js,
    doobieCe3Jvm,
    doobieCe3Js,
    extrasRenderJvm,
    extrasRenderJs,
    refinedCompatScala2Jvm,
    refinedCompatScala2Js,
    refinedCompatScala3Jvm,
    refinedCompatScala3Js,
    tapirJvm,
    tapirJs,
  )

lazy val core    = module("core", crossProject(JVMPlatform, JSPlatform))
  .settings(
    libraryDependencies ++= List(
      libs.extrasTypeInfo % Test,
      libs.cats           % Test,
    )
  )
lazy val coreJvm = core.jvm
lazy val coreJs  = core.js.settings(jsSettingsForFuture)

lazy val cats    = module("cats", crossProject(JVMPlatform, JSPlatform))
  .settings(
    libraryDependencies ++= List(
      libs.cats,
      libs.extrasTypeInfo % Test,
    )
  )
  .dependsOn(core % props.IncludeTest)
lazy val catsJvm = cats.jvm
lazy val catsJs  = cats.js.settings(jsSettingsForFuture)

lazy val circe    = module("circe", crossProject(JVMPlatform, JSPlatform))
  .settings(
    libraryDependencies ++= List(
      libs.circeCore,
      libs.circeParser         % Test,
      libs.circeLiteral        % Test,
      libs.extrasTypeInfo      % Test,
      libs.extrasHedgehogCirce % Test,
    )
  )
  .dependsOn(
    core % props.IncludeTest,
    cats,
  )
lazy val circeJvm = circe.jvm
lazy val circeJs  = circe.js.settings(jsSettingsForFuture)

lazy val pureconfig    = module("pureconfig", crossProject(JVMPlatform, JSPlatform))
  .settings(
    libraryDependencies ++= List(
      libs.pureconfigCore,
      libs.extrasTypeInfo % Test,
    )
  )
  .dependsOn(core % props.IncludeTest)
lazy val pureconfigJvm = pureconfig.jvm
lazy val pureconfigJs  = pureconfig.js.settings(jsSettingsForFuture)

lazy val doobieCe2    = module("doobie-ce2", crossProject(JVMPlatform, JSPlatform))
  .settings(
    libraryDependencies ++= List(
      libs.doobieCoreCe2,
      libs.embeddedPostgres     % Test,
      libs.effectieCe2          % Test,
      libs.extrasDoobieToolsCe2 % Test,
      libs.logback              % Test,
//      libs.kittens              % Test,
    )
  )
  .dependsOn(
    core % props.IncludeTest,
    cats,
  )
lazy val doobieCe2Jvm = doobieCe2.jvm
lazy val doobieCe2Js  = doobieCe2.js.settings(jsSettingsForFuture)

lazy val doobieCe3    = module("doobie-ce3", crossProject(JVMPlatform, JSPlatform))
  .settings(
    libraryDependencies ++= List(
      libs.doobieCoreCe3,
      libs.embeddedPostgres     % Test,
      libs.effectieCe3          % Test,
      libs.extrasDoobieToolsCe3 % Test,
      libs.logback              % Test,
//      libs.kittens              % Test,
    )
  )
  .dependsOn(
    core % props.IncludeTest,
    cats,
  )
lazy val doobieCe3Jvm = doobieCe3.jvm
lazy val doobieCe3Js  = doobieCe3.js.settings(jsSettingsForFuture)

lazy val extrasRender    = module("extras-render", crossProject(JVMPlatform, JSPlatform))
  .settings(
    libraryDependencies ++= List(
      libs.extrasRender
    )
  )
  .dependsOn(
    core % props.IncludeTest
  )
lazy val extrasRenderJvm = extrasRender.jvm
lazy val extrasRenderJs  = extrasRender.js.settings(jsSettingsForFuture)

lazy val refinedCompatScala2    = module("refined-compat-scala2", crossProject(JVMPlatform, JSPlatform))
  .settings(
    crossScalaVersions := List("2.12.15", "2.13.13"),
    libraryDependencies ++=
      (
        if (isScala3(scalaVersion.value))
          List.empty
        else
          List("eu.timepit" %% "refined" % "0.9.29")
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
      libs.tapirCore
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
    scalacOptions ~= (ops => ops.filter(_ != "-Wunused:imports")),
    mdocIn := file("docs"),
    mdocOut := file("generated-docs/docs"),
    cleanFiles += file("generated-docs/docs"),
    libraryDependencies ++= {
      import sys.process.*
      "git fetch --tags".!
      val tag           = "git rev-list --tags --max-count=1".!!.trim
      val latestVersion = s"git describe --tags $tag".!!.trim.stripPrefix("v")

      List(
        "io.kevinlee" %% "refined4s-core"          % latestVersion,
        "io.kevinlee" %% "refined4s-cats"          % latestVersion,
        "io.kevinlee" %% "refined4s-circe"         % latestVersion,
        "io.kevinlee" %% "refined4s-pureconfig"    % latestVersion,
        "io.kevinlee" %% "refined4s-doobie-ce2"    % latestVersion,
        "io.kevinlee" %% "refined4s-extras-render" % latestVersion,
        "io.kevinlee" %% "refined4s-tapir"         % latestVersion,
        libs.circeCore,
        libs.circeLiteral,
        libs.circeParser,
      )
    },
    mdocVariables := Map(
      "VERSION" -> {
        import sys.process.*
        "git fetch --tags".!
        val tag = "git rev-list --tags --max-count=1".!!.trim
        s"git describe --tags $tag".!!.trim.stripPrefix("v")
      }
    ),
    docusaurDir := (ThisBuild / baseDirectory).value / "website",
    docusaurBuildDir := docusaurDir.value / "build",
  )
  .settings(noPublish)

lazy val props =
  new {

    private val GitHubRepo = findRepoOrgAndName

    val Org = "io.kevinlee"

    val GitHubUsername = GitHubRepo.fold("kevin-lee")(_.orgToString)
    val RepoName       = GitHubRepo.fold("refined4s")(_.nameToString)

//    val Scala3Version = "3.1.3"
    val Scala3Version = "3.3.3"

    val ProjectScalaVersion = Scala3Version

    lazy val licenses = List("MIT" -> url("http://opensource.org/licenses/MIT"))

    val SonatypeCredentialHost = "s01.oss.sonatype.org"
    val SonatypeRepository     = s"https://$SonatypeCredentialHost/service/local"

    val SonatypeSnapshots = "sonatype-snapshots".at(s"https://$SonatypeCredentialHost/content/repositories/snapshots")

    val removeDottyIncompatible: ModuleID => Boolean =
      m =>
        m.name == "ammonite" ||
          m.name == "kind-projector" ||
          m.name == "better-monadic-for" ||
          m.name == "mdoc"

    val IncludeTest = "compile->compile;test->test"

    val HedgehogVersion      = "0.10.1"
    val HedgehogExtraVersion = "0.8.0"

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
  }

lazy val libs = new {

  lazy val extrasTypeInfo       = "io.kevinlee" %% "extras-type-info"        % props.ExtrasVersion
  lazy val extrasHedgehogCirce  = "io.kevinlee" %% "extras-hedgehog-circe"   % props.ExtrasVersion
  lazy val extrasDoobieToolsCe2 = "io.kevinlee" %% "extras-doobie-tools-ce2" % props.ExtrasVersion
  lazy val extrasDoobieToolsCe3 = "io.kevinlee" %% "extras-doobie-tools-ce3" % props.ExtrasVersion
  lazy val extrasRender         = "io.kevinlee" %% "extras-render"           % props.ExtrasVersion

  lazy val cats = "org.typelevel" %% "cats-core" % props.CatsVersion

  lazy val kittens = "org.typelevel" %% "kittens" % props.KittensVersion

  lazy val circeCore    = "io.circe" %% "circe-core"    % props.CirceVersion
  lazy val circeParser  = "io.circe" %% "circe-parser"  % props.CirceVersion
  lazy val circeLiteral = "io.circe" %% "circe-literal" % props.CirceVersion

  lazy val pureconfigCore    = "com.github.pureconfig" %% "pureconfig-core"    % props.PureconfigVersion
  lazy val pureconfigGeneric = "com.github.pureconfig" %% "pureconfig-generic" % props.PureconfigVersion

  lazy val doobieCoreCe2 = "org.tpolecat" %% "doobie-core" % props.DoobieCe2Version
  lazy val doobieCoreCe3 = "org.tpolecat" %% "doobie-core" % props.DoobieCe3Version

  lazy val embeddedPostgres = "io.zonky.test" % "embedded-postgres" % props.EmbeddedPostgresVersion

  lazy val effectieCore   = "io.kevinlee" %% "effectie-core"         % props.EffectieVersion
  lazy val effectieSyntax = "io.kevinlee" %% "effectie-syntax"       % props.EffectieVersion
  lazy val effectieCe2    = "io.kevinlee" %% "effectie-cats-effect2" % props.EffectieVersion
  lazy val effectieCe3    = "io.kevinlee" %% "effectie-cats-effect3" % props.EffectieVersion

  lazy val logback = "ch.qos.logback" % "logback-classic" % props.LogbackVersion

  lazy val hedgehogCore   = "qa.hedgehog" %% "hedgehog-core"   % props.HedgehogVersion
  lazy val hedgehogRunner = "qa.hedgehog" %% "hedgehog-runner" % props.HedgehogVersion
  lazy val hedgehogSbt    = "qa.hedgehog" %% "hedgehog-sbt"    % props.HedgehogVersion

  lazy val tapirCore = "com.softwaremill.sttp.tapir" %% "tapir-core" % props.TapirVersion

  lazy val tests = new {

    lazy val hedgehog: List[ModuleID] =
      List(
        hedgehogCore,
        hedgehogRunner,
        hedgehogSbt,
      ).map(_ % Test)

    lazy val hedgehogExtraCore = "io.kevinlee" %% "hedgehog-extra-core" % props.HedgehogExtraVersion % Test
  }
}

// scalafmt: off
def prefixedProjectName(name: String) = s"${props.RepoName}${if (name.isEmpty) "" else s"-$name"}"
// scalafmt: on

lazy val mavenCentralPublishSettings: SettingsDefinition = List(
  /* Publish to Maven Central { */
  sonatypeCredentialHost := props.SonatypeCredentialHost,
  sonatypeRepository := props.SonatypeRepository,
  /* } Publish to Maven Central */
)

def isScala3(scalaVersion: String): Boolean = scalaVersion.startsWith("3")

def module(projectName: String, crossProject: CrossProject.Builder): CrossProject = {
  val prefixedName = prefixedProjectName(projectName)
  crossProject
    .in(file(s"modules/$prefixedName"))
    .settings(
      name := prefixedName,
      fork := true,
      semanticdbEnabled := true,
      semanticdbVersion := scalafixSemanticdb.revision,
      scalafixConfig := (
        if (scalaVersion.value.startsWith("3"))
          ((ThisBuild / baseDirectory).value / ".scalafix-scala3.conf").some
        else
          ((ThisBuild / baseDirectory).value / ".scalafix-scala2.conf").some
      ),
      scalacOptions ++= (if (isScala3(scalaVersion.value)) List("-no-indent") else List("-Xsource:3")),
//      scalacOptions ~= (ops => ops.filter(_ != "UTF-8")),
      libraryDependencies ++= libs.tests.hedgehog ++ List(libs.tests.hedgehogExtraCore),
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
    .settings(mavenCentralPublishSettings)
}

lazy val jsSettingsForFuture: SettingsDefinition = List(
  Test / fork := false,
  Test / scalacOptions ++= (if (scalaVersion.value.startsWith("3")) List.empty
                            else List("-P:scalajs:nowarnGlobalExecutionContext")),
  Test / compile / scalacOptions ++= (if (scalaVersion.value.startsWith("3")) List.empty
                                      else List("-P:scalajs:nowarnGlobalExecutionContext")),
)
