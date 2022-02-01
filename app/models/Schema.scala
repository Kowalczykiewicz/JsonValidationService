package models

import slick.lifted.MappedTo

case class IdSchema(id: String) extends MappedTo[String] {
  override def value: String = id
}

case class Schema(id: IdSchema, json: String)
