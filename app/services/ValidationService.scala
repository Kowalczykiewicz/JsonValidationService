package services

import io.circe._
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.schema.{ Schema => SchemaCirce }
import io.circe.syntax._
import services.responses.{ ResponseInvalid, ResponseValid }

object ValidationService {

  def validate(schemaId: String, schemaRaw: String, jsonRaw: String): Either[Json, Json] = {
    def valid() = ResponseValid(schemaId, "validateDocument", "success").asJson
    def invalid(errors: List[String]) = ResponseInvalid(schemaId, "validateDocument", "error", errors.mkString).asJson

    parse(jsonRaw) match {
      case Right(json) =>
        val schema = SchemaCirce.load(parse(schemaRaw).right.get)
        val validated = schema.validate(json.deepDropNullValues)
        val errors = validated.swap.map(_.toList).toList.flatten.map(_.getMessage)

        if (errors.isEmpty) Right(valid()) else Left(invalid(errors))

      case Left(_) =>
        Left(invalid(List("This is not JSON")))
    }
  }

}
