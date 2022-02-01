package controllers

import play.api.libs.json.Json
import play.api.mvc._

import javax.inject._
import services.SchemaService

import scala.util.{ Failure, Success }

@Singleton
class SchemaController @Inject() (cc: ControllerComponents) extends AbstractController(cc) {

  def upload(schemaId: String): Action[_] = Action { implicit request: Request[AnyContent] =>
    request.body.asJson match {
      case Some(json) =>
        SchemaService.upload(schemaId, json) match {
          case Success(_) => Created(s"Schema $schemaId successfully uploaded")
          case Failure(exception) =>
            BadRequest(s"Upload failed $exception for schema $schemaId")
        }
      case None =>
        BadRequest("Incorrect request, only JSONs are expected")
    }
  }

  def download(schemaId: String): Action[_] = Action {
    SchemaService.download(schemaId) match {
      case Success(Some(schema)) =>
        Ok(Json.toJson(schema))
      case Success(None) =>
        NotFound(s"There is no schema $schemaId")
      case Failure(exception) =>
        BadRequest(s"Can not download schema $schemaId, exception: $exception")
    }
  }

}
