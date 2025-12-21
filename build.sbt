import just.semver.SemVer
import sbtcrossproject.CrossProject
import extras.scala.io.syntax.color._

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
    coreNative,
    testCoreWithoutCatsJvm,
    testCoreWithoutCatsJs,
    testCoreWithoutCatsNative,
    catsJvm,
    catsJs,
    catsNative,
    circeJvm,
    circeJs,
    circeNative,
    pureconfigJvm,
    doobieCe2Jvm,
    doobieCe3Jvm,
    extrasRenderJvm,
    extrasRenderJs,
    extrasRenderNative,
    refinedCompatScala2Jvm,
    refinedCompatScala2Js,
    refinedCompatScala2Native,
    refinedCompatScala3Jvm,
    refinedCompatScala3Js,
    refinedCompatScala3Native,
    tapirJvm,
    tapirJs,
//    tapirNative,
    chimneyJvm,
    chimneyJs,
    chimneyNative,
  )

lazy val core       = module("core", crossProject(JVMPlatform, JSPlatform, NativePlatform))
  .settings(
    scalacOptions ++= List("-Xprint-suspension"),
    libraryDependencies ++= List(
      libs.orphanCats.value,
      libs.cats.value       % Optional,
      libs.extrasCore.value % Test,
    ),
  )
lazy val coreJvm    = core
  .jvm
  .settings(
    libraryDependencies ++= List(libs.tests.hedgehogExtraCore.value)
  )
lazy val coreJs     = core
  .js
  .settings(jsSettingsForFuture)
  .settings(
    libraryDependencies ++= List(
      libs.scalajsJavaSecurerandom.value,
      libs.tests.scalaJavaTime.value,
    )
  )
  .settings(
    libraryDependencies ++= List(
      libs.tests.hedgehogExtraCore.value
    )
  )
lazy val coreNative = core
  .native
  .settings(nativeSettings)
  .settings(
    libraryDependencies ++= List(
      libs.tests.scalaNativeCrypto.value
    )
  )

lazy val testCoreWithoutCats       = testModule("core-without-cats", crossProject(JVMPlatform, JSPlatform, NativePlatform))
  .settings(noPublish)
  .settings(
    scalacOptions ++= List("-Xprint-suspension"),
    libraryDependencies ++= List(
      libs.tests.hedgehogExtraCore.value
    ),
    libraryDependencies ~= (libs => libs.filterNot(_.name.startsWith("cats"))),
  )
  .dependsOn(core)
lazy val testCoreWithoutCatsJvm    = testCoreWithoutCats.jvm
lazy val testCoreWithoutCatsJs     = testCoreWithoutCats.js.settings(jsSettingsForFuture)
lazy val testCoreWithoutCatsNative = testCoreWithoutCats
  .native
  .settings(nativeSettings)
  .settings(
    libraryDependencies ++= List(
      libs.tests.scalaNativeCrypto.value
    )
  )

lazy val cats       = module("cats", crossProject(JVMPlatform, JSPlatform, NativePlatform))
  .settings(
    libraryDependencies ++= List(
      libs.cats.value,
      libs.extrasCore.value % Test,
    )
  )
  .dependsOn(
    core                % props.IncludeTest,
    testCoreWithoutCats % "test->test",
  )
lazy val catsJvm    = cats.jvm
lazy val catsJs     = cats.js.settings(jsSettingsForFuture)
lazy val catsNative = cats
  .native
  .settings(nativeSettings)
  .settings(
    libraryDependencies ++= List(
      libs.tests.scalaJavaTime.value
    )
  )

lazy val circe       = module("circe", crossProject(JVMPlatform, JSPlatform, NativePlatform))
  .settings(
    libraryDependencies ++= List(
      libs.circeCore.value,
      libs.circeParser.value         % Test,
      libs.circeLiteral.value        % Test,
      libs.extrasCore.value          % Test,
      libs.extrasHedgehogCirce.value % Test,
    )
  )
  .dependsOn(
    core                % props.IncludeTest,
    testCoreWithoutCats % "test->test",
    cats,
  )
lazy val circeJvm    = circe.jvm
lazy val circeJs     = circe.js.settings(jsSettingsForFuture)
lazy val circeNative = circe
  .native
  .settings(nativeSettings)
  .settings(
    libraryDependencies ++= List(
      libs.tests.scalaJavaTime.value
    )
  )

lazy val pureconfig    = module("pureconfig", crossProject(JVMPlatform))
  .settings(
    libraryDependencies ++= List(
      libs.pureconfigCore,
      libs.cats.value       % Test,
      libs.extrasCore.value % Test,
    )
  )
  .dependsOn(
    core                % props.IncludeTest,
    testCoreWithoutCats % "test->test",
  )
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
    core                % props.IncludeTest,
    testCoreWithoutCats % "test->test",
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
    core                % props.IncludeTest,
    testCoreWithoutCats % "test->test",
    cats,
  )
lazy val doobieCe3Jvm = doobieCe3.jvm

lazy val extrasRender       = module("extras-render", crossProject(JVMPlatform, JSPlatform, NativePlatform))
  .settings(
    libraryDependencies ++= List(
      libs.extrasRender.value,
      libs.cats.value % Test,
    )
  )
  .dependsOn(
    core                % props.IncludeTest,
    testCoreWithoutCats % "test->test",
  )
lazy val extrasRenderJvm    = extrasRender.jvm
lazy val extrasRenderJs     = extrasRender.js.settings(jsSettingsForFuture)
lazy val extrasRenderNative = extrasRender.native.settings(nativeSettings)

lazy val chimney       = module("chimney", crossProject(JVMPlatform, JSPlatform, NativePlatform))
  .settings(
    libraryDependencies ++= List(
      libs.chimney.value,
      libs.cats.value % Test,
      libs.tests.hedgehogExtraCore.value,
      libs.tests.hedgehogExtraRefined4s.value,
    )
  )
  .dependsOn(
    core                % props.IncludeTest,
    testCoreWithoutCats % "test->test",
  )
lazy val chimneyJvm    = chimney.jvm
lazy val chimneyJs     = chimney.js.settings(jsSettingsForFuture)
lazy val chimneyNative = chimney.native.settings(nativeSettings)

lazy val refinedCompatScala2       = module("refined-compat-scala2", crossProject(JVMPlatform, JSPlatform, NativePlatform))
  .settings(
    crossScalaVersions := List("2.12.18", "2.13.16")
  )
lazy val refinedCompatScala2Jvm    = refinedCompatScala2
  .jvm
  .settings(
    libraryDependencies ++=
      (
        if (isScala3(scalaVersion.value))
          List.empty
        else
          List(
            ("eu.timepit" %%% "refined" % "0.9.29")
              .excludeAll("org.scala-lang.modules" %% "scala-xml")
          )
      )
  )
lazy val refinedCompatScala2Js     = refinedCompatScala2
  .js
  .settings(jsSettingsForFuture)
  .settings(
    libraryDependencies ++=
      (
        if (isScala3(scalaVersion.value))
          List.empty
        else
          List(
            ("eu.timepit" %%% "refined" % "0.9.29")
              .excludeAll("org.scala-lang.modules" %% "scala-xml")
          )
      )
  )
lazy val refinedCompatScala2Native = refinedCompatScala2
  .native
  .settings(nativeSettings)
  .settings(
    libraryDependencies ++=
      (
        if (isScala3(scalaVersion.value))
          List.empty
        else
          List("eu.timepit" %%% "refined" % "0.11.3")
      )
  )

lazy val refinedCompatScala3       = module("refined-compat-scala3", crossProject(JVMPlatform, JSPlatform, NativePlatform))
  .settings(
    libraryDependencies ++= List(
      libs.cats.value % Test
    )
  )
  .dependsOn(
    core % props.IncludeTest
  )
lazy val refinedCompatScala3Jvm    = refinedCompatScala3.jvm
lazy val refinedCompatScala3Js     = refinedCompatScala3.js.settings(jsSettingsForFuture)
lazy val refinedCompatScala3Native = refinedCompatScala3.native.settings(nativeSettings)

lazy val tapir    = module("tapir", crossProject(JVMPlatform, JSPlatform)) // , NativePlatform))
  .settings(
    libraryDependencies ++= List(
      libs.tapirCore.value,
      libs.cats.value % Test,
    )
  )
  .dependsOn(
    core                % props.IncludeTest,
    testCoreWithoutCats % "test->test",
  )
lazy val tapirJvm = tapir.jvm
lazy val tapirJs  = tapir.js.settings(jsSettingsForFuture)
//lazy val tapirNative = tapir.native.settings(nativeSettings)

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
        "io.kevinlee"                              %% "refined4s-doobie-ce2" % latestVersion,
        "io.kevinlee" %%% "refined4s-extras-render" % latestVersion,
        "io.kevinlee" %%% "refined4s-tapir"         % latestVersion,
        libs.circeCore.value,
        libs.circeLiteral.value,
        libs.circeParser.value,
      )
    },
    mdocVariables := {
      implicit val logger: Logger = sLog.value

      val latestVersion = getTheLatestTaggedVersion(version.value)
      createMdocVariables(latestVersion)
    },
    docusaurDir := (ThisBuild / baseDirectory).value / "website",
    docusaurBuildDir := docusaurDir.value / "build",
    mdoc := {
      implicit val logger: Logger = sLog.value

      val latestVersion = getTheLatestTaggedVersion(version.value)

      val envVarCi = sys.env.get("CI")
      val ciResult = s"""sys.env.get("CI")=${envVarCi}"""
      envVarCi match {
        case Some("true") =>
          logger.info(
            s">> ${ciResult.yellow} so ${"run".green} `${"writeLatestVersion".blue}` and `${"writeVersionsArchived".blue}`."
          )
          val websiteDir = docusaurDir.value
          writeLatestVersion(websiteDir, latestVersion)
          writeVersionsArchived(websiteDir, latestVersion)
        case Some(_) | None =>
          logger.info(
            s">> ${ciResult.yellow} so it will ${"not run".red} `${"writeLatestVersion".cyan}` and `${"writeVersionsArchived".cyan}`."
          )
      }
      mdoc.evaluated
    },
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
    mdocVariables := createMdocVariables("0.19.0"),
  )
  .settings(noPublish)

def getTheLatestTaggedVersion(version: String)(implicit logger: Logger): String = {
  import sys.process._
  val envVarCi = sys.env.get("CI")
  val ciResult = s"""sys.env.get("CI")=${envVarCi}"""
  envVarCi match {
    case Some("true") =>
      val gitFetchTagsCmd = "git fetch --tags"
      logger.info(s">> ${ciResult.yellow} so ${"run".green} `${gitFetchTagsCmd.blue}`")
      gitFetchTagsCmd.!
    case Some(_) | None =>
      logger.info(s">> ${ciResult.yellow} so ${"skip fetching tags".red}")
  }

  val tag = "git rev-list --tags --max-count=1".!!.trim
  if (tag.nonEmpty)
    s"git describe --tags $tag".!!.trim.stripPrefix("v")
  else
    version
}

def writeLatestVersion(websiteDir: File, latestVersion: String)(implicit logger: Logger): Unit = {
  val latestVersionFile = websiteDir / "latestVersion.json"
  val latestVersionJson = raw"""{"version":"$latestVersion"}"""

  val websiteDirRelativePath =
    s"${latestVersionFile.getParentFile.getParentFile.getName.cyan}/${latestVersionFile.getParentFile.getName.yellow}"
  logger.info(
    s""">> Writing ${"the latest version".blue} to $websiteDirRelativePath/${latestVersionFile.getName.green}.
       |>> Content: ${latestVersionJson.blue}
       |""".stripMargin
  )
  IO.write(latestVersionFile, latestVersionJson)
}

def writeVersionsArchived(websiteDir: File, latestVersion: String): Unit = {
  import sys.process._
  "git fetch --tags".!
  val tags = "git tag".!!.trim

  val versions = tags
    .split("\n")
    .map(_.stripPrefix("v"))
    .map(SemVer.parse)
    .collect { case Right(v) => v }
    .sorted(Ordering[SemVer].reverse)
    .map(_.render)
    .filter(_ != latestVersion)

  val versionsArchivedFile = websiteDir / "src" / "pages" / "versionsArchived.json"

  val versionsInJson = versions
    .map { v =>
      raw"""  {
           |    "name": "$v",
           |    "label": "$v"
           |  }""".stripMargin
    }
    .mkString("[\n", ",\n", "\n]")

  IO.write(versionsArchivedFile, versionsInJson)
}

def createMdocVariables(version: String): Map[String, String] = {
  val versionForDoc = version

  Map(
    "VERSION" -> versionForDoc
  )
}

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

    val HedgehogVersion      = "0.13.0"
    val HedgehogExtraVersion = "0.15.0"

    val ExtrasVersion = "0.50.0"

    val CatsVersion = "2.12.0"

    val CirceVersion = "0.14.12"

    val PureconfigVersion = "0.17.1"

    val DoobieCe2Version = "0.13.4"
    val DoobieCe3Version = "1.0.0-RC10"

    val EmbeddedPostgresVersion = "2.0.7"

    val EffectieVersion = "2.0.0"

    val LogbackVersion = "1.5.6"

    val OrphanVersion = "0.5.0"

    val KittensVersion = "3.0.0"

    val TapirVersion = "1.11.28"

    val ChimneyVersion = "1.3.0"

    val ScalajsJavaSecurerandomVersion = "1.0.0"

    val ScalaJavaTimeVersion = "2.6.0"

    val ScalaNativeCryptoVersion = "0.2.1"

  }

lazy val libs = new {

  lazy val orphanCats = Def.setting("io.kevinlee" %%% "orphan-cats" % props.OrphanVersion)

  lazy val extrasCore           = Def.setting("io.kevinlee" %%% "extras-core" % props.ExtrasVersion)
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

    lazy val scalaNativeCrypto =
      Def.setting("com.github.lolgab" %%% "scala-native-crypto" % props.ScalaNativeCryptoVersion % Test)

    lazy val scalaJavaTime = Def.setting("io.github.cquiroz" %%% "scala-java-time" % props.ScalaJavaTimeVersion % Test)

  }
}

// scalafmt: off
def prefixedProjectName(name: String) = s"${props.RepoName}${if (name.isEmpty) "" else s"-$name"}"
// scalafmt: on

def isScala3(scalaVersion: String): Boolean = scalaVersion.startsWith("3")

def module(projectName: String, crossProject: CrossProject.Builder): CrossProject = {
  val prefixedName = prefixedProjectName(projectName)
  commonModule(prefixedName, crossProject)
}

def testModule(projectName: String, crossProject: CrossProject.Builder): CrossProject = {
  val prefixedName = s"test-${prefixedProjectName(projectName)}"
  commonModule(prefixedName, crossProject)
}

def commonModule(prefixedName: String, crossProject: CrossProject.Builder): CrossProject = {
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
      scalacOptions ++= (if (isScala3(scalaVersion.value)) List("-no-indent", "-explain") else List("-Xsource:3")),
//      scalacOptions ~= (ops => ops.filter(_ != "UTF-8")),
      libraryDependencies ++= libs.tests.hedgehog.value,
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

lazy val nativeSettings: SettingsDefinition = List(
  Test / fork := false,
  coverageEnabled := false,
)
