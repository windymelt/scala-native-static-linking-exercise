val scala3Version = "3.3.0"
val circeVersion = "0.14.5"

enablePlugins(ScalaNativePlugin)

lazy val root = project
  .in(file("."))
  .settings(
    name := "scala-native-static-linking-exercise",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,
    libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test,
    libraryDependencies ++= Seq(
      "org.typelevel" %%% "cats-core" % "2.9.0"
    ),
    libraryDependencies ++= Seq(
      "io.circe" %%% "circe-core",
      "io.circe" %%% "circe-generic",
      "io.circe" %%% "circe-parser"
    ).map(_ % circeVersion)
  )

import scala.scalanative.build._

// defaults set with common options shown
nativeConfig ~= { c =>
  c.withLTO(LTO.none) // thin
    .withMode(Mode.debug) // releaseFast
    .withGC(GC.immix) // commix
    .withLinkingOptions(Seq("-static"))
}
