package services

import play.api.libs.json.JsValue

import scala.util.Try

object ValidationService {
  def validate(schemaId: String, json: JsValue): Try[JsValue] = ???
}
