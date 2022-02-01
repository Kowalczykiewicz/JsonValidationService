package controllers

import play.api.mvc._
import services.SchemaService

import javax.inject._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class SchemaController @Inject() (cc: ControllerComponents) extends AbstractController(cc) {

  def upload(schemaId: String): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    request.body.asJson match {
      case Some(json) =>
        SchemaService.upload(schemaId, json).map {
          case Right(x) => Created(s"Schema $schemaId successfully uploaded")
          case Left(x)  => BadRequest(s"Upload failed $x for schema $schemaId")
        }
      case None =>
        Future(BadRequest("Incorrect request, only JSONs are expected"))
    }
  }

  def download(schemaId: String): Action[AnyContent] = Action.async {
    SchemaService.download(schemaId).map {
      case Some(schema) => Ok("schema")
      case None         => NotFound(s"There is no schema $schemaId")
    }
  }

}
