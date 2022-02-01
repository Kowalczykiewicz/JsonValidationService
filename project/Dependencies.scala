import sbt._

object Dependencies {
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.5"
  lazy val h2 = "com.h2database" % "h2" % "1.4.192"
  lazy val slick = "com.typesafe.slick" %% "slick" % "3.3.2"

  lazy val circeDeps = {
    val circeSchema = "io.circe" %% "circe-json-schema" % "0.1.0"
    val circeVersion = "0.14.1"
    Seq(
      "io.circe" %% "circe-core",
      "io.circe" %% "circe-generic",
      "io.circe" %% "circe-parser"
    ).map(_ % circeVersion) :+ circeSchema

  }
}
