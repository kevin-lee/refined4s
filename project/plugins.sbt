addSbtPlugin("com.github.sbt" % "sbt-ci-release" % "1.5.10")

addSbtPlugin("org.wartremover" % "sbt-wartremover" % "3.1.0")

addSbtPlugin("ch.epfl.scala" % "sbt-scalafix"  % "0.10.4")
addSbtPlugin("org.scalameta" % "sbt-scalafmt"  % "2.5.0")
addSbtPlugin("org.scoverage" % "sbt-scoverage" % "2.0.7")
addSbtPlugin("org.scalameta" % "sbt-mdoc"      % "2.3.7")
addSbtPlugin("io.kevinlee"   % "sbt-docusaur"  % "0.13.0")

addSbtPlugin("org.scala-js"       % "sbt-scalajs"              % "1.13.0")
addSbtPlugin("org.portable-scala" % "sbt-scalajs-crossproject" % "1.2.0")

val sbtDevOopsVersion = "2.24.0"
addSbtPlugin("io.kevinlee" % "sbt-devoops-scala"     % sbtDevOopsVersion)
addSbtPlugin("io.kevinlee" % "sbt-devoops-sbt-extra" % sbtDevOopsVersion)
addSbtPlugin("io.kevinlee" % "sbt-devoops-github"    % sbtDevOopsVersion)

addSbtPlugin("io.kevinlee" % "sbt-devoops-starter" % sbtDevOopsVersion)

addSbtPlugin("io.github.davidgregory084" % "sbt-tpolecat" % "0.4.2")
