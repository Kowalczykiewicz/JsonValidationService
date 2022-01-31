import Dependencies.scalaTest
import sbt.Keys.resolvers

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := "Json Validation Service",
    organization := "com.example",
    version := "1.0-SNAPSHOT",
    scalaVersion := "2.12.12",
    libraryDependencies ++= Seq(scalaTest % "test"),
    scalacOptions ++= Seq(
      "-feature",
      "-deprecation",
      "-unchecked",
      "-Xfatal-warnings",
      "-Yno-adapted-args",
      "-Ywarn-dead-code",
      "-Ywarn-numeric-widen",
      "-Ywarn-value-discard",
      "-Ywarn-unused",
      "-Xlint:unsound-match"
    ),
    resolvers ++= Seq(
      "Typesafe" at "https://repo.typesafe.com/typesafe/releases/",
      "Typesafe-ivy-releases" at "https://repo.typesafe.com/typesafe/ivy-releases"
    )
  )
