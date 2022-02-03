package services

import io.circe.Json
import io.circe.generic.auto._
import io.circe.parser.parse
import io.circe.syntax.EncoderOps
import models.tables.SchemasTable
import models.{ DB, IdSchema, Schema }
import services.responses._
import slick.jdbc.H2Profile.api._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object StorageService {

  def upload(schemaId: String, jsonRaw: String): Future[Either[Json, Json]] = {
    def valid() = ResponseValid(schemaId, "uploadSchema", "success").asJson
    def invalid() = ResponseInvalid(schemaId, "uploadSchema", "error", "Invalid JSON").asJson

    val isJsonValid = parse(jsonRaw).isRight

    if (isJsonValid) {
      val updated = SchemasTable.query.insertOrUpdate(Schema(IdSchema(schemaId), jsonRaw))
      DB.db.run(updated).map(_ => Right(valid()))
    } else {
      Future(Left(invalid()))
    }
  }

  def download(schemaId: String): Future[Option[Json]] = {
    val query = SchemasTable.query.filter(_.id === IdSchema(schemaId))
    DB.db.run(query.result.headOption).map(_.map(elem => parse(elem.json).right.get))
  }

}
