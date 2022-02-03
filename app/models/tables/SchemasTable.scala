package models.tables

import models.{ IdSchema, Schema }
import slick.jdbc.H2Profile.api._

class SchemasTable(tag: Tag) extends Table[Schema](tag, "schemas") {
  def id = column[IdSchema]("id", O.PrimaryKey)
  def json = column[String]("json")
  def * = (id, json) <> (Schema.tupled, Schema.unapply)
}

object SchemasTable {
  lazy val query = TableQuery[SchemasTable]
}
