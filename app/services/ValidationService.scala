package services

import models.Schema
import play.api.libs.json.{ JsNull, JsValue }

object ValidationService {
  def validate(schema: Schema, json: JsValue): Either[JsValue, JsValue] = {
    def validateJson(schema: Schema, json: JsValue): Boolean = true

    if (validateJson(schema, json)) Right(JsNull) else Left(JsNull)
  }
}
