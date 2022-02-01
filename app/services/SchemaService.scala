package services

import play.api.libs.json.JsValue

import scala.util.Try

object SchemaService {
  def upload(schemaId:   String, json: JsValue): Try[_] = ???
  def download(schemaId: String): Try[Option[JsValue]] = ???
}
