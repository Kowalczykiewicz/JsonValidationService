package services

import models.tables.Schemas
import models.{ DB, IdSchema, Schema }
import play.api.libs.json.{ JsNull, JsValue }
import slick.jdbc.H2Profile.api._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object SchemaService {
  def upload(schemaId: String, json: JsValue): Future[Either[JsValue, JsValue]] = {
    def isValid(json: JsValue) = true

    if (isValid(json)) {
      val updated = Schemas.query.insertOrUpdate(Schema(IdSchema(schemaId), json.toString))
      DB.db.run(updated).map(_ => Right(JsNull))
    } else {
      Future(Left(JsNull))
    }

  }

  def download(schemaId: String): Future[Option[Schema]] = {
    val query = Schemas.query.filter(_.id === IdSchema(schemaId))
    DB.db.run(query.result.headOption)
  }
}
