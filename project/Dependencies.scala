import sbt._

object Dependencies {
  lazy val scalaTestPlay = "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0"
  lazy val h2 = "com.h2database" % "h2" % "1.4.192"
  lazy val slickPlay = "com.typesafe.play" %% "play-slick" % "4.0.0"

  lazy val circeDeps = {
    val playCirce = "com.dripower" %% "play-circe" % "2814.1"
    val circeSchema = "io.circe" %% "circe-json-schema" % "0.1.0"
    val circeVersion = "0.14.1"
    Seq(
      "io.circe" %% "circe-core",
      "io.circe" %% "circe-generic",
      "io.circe" %% "circe-parser"
    ).map(_ % circeVersion) :+ circeSchema :+ playCirce

  }
}
