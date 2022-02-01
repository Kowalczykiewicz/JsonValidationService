package models

import slick.jdbc.JdbcBackend.Database

object DB {
  lazy val db = Database.forConfig("h2mem")
}
