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
  .settings(noPublish)
  .aggregate(coreJvm, coreJs)

lazy val core = module("core", crossProject(JVMPlatform, JSPlatform))
lazy val coreJvm = core.jvm
lazy val coreJs = core.js.settings(jsSettingsForFuture)

lazy val props =
  new {

    val Org = "io.kevinlee"

    val GitHubUsername = "Kevin-Lee"
    val RepoName       = "refined4s"

    val Scala3Version = "3.1.3"

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

    val HedgehogVersion = "0.10.1"

  }

lazy val libs = new {
  lazy val hedgehogCore   = "qa.hedgehog" %% "hedgehog-core"   % props.HedgehogVersion
  lazy val hedgehogRunner = "qa.hedgehog" %% "hedgehog-runner" % props.HedgehogVersion
  lazy val hedgehogSbt    = "qa.hedgehog" %% "hedgehog-sbt"    % props.HedgehogVersion

  lazy val hedgehog: List[ModuleID] =
    List(
      hedgehogCore,
      hedgehogRunner,
      hedgehogSbt,
    ).map(_ % Test)
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
      scalacOptions ++= (if (isScala3(scalaVersion.value)) List.empty else List("-Xsource:3")),
      scalacOptions ~= (ops => ops.filter(_ != "UTF-8")),
      libraryDependencies ++= libs.hedgehog,
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
      testFrameworks ++= (testFrameworks.value ++ Seq(TestFramework("hedgehog.sbt.Framework"))).distinct,
      licenses := props.licenses,
      /* coverage { */
      coverageHighlighting := (CrossVersion.partialVersion(scalaVersion.value) match {
        case Some((2, 10)) | Some((2, 11)) =>
          false
        case _ =>
          true
      }),
      /* } coverage */

      scalacOptions ~= (_.filterNot(_.startsWith("-language"))),
      scalacOptions ++= List(
        "-language:dynamics",
        "-language:existentials",
        "-language:higherKinds",
        "-language:reflectiveCalls",
        "-language:experimental.macros",
        "-language:implicitConversions",
      ),
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
