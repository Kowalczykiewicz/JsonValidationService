package controllers

import play.api.libs.circe.Circe
import play.api.mvc._
import services.StorageService

import javax.inject._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class SchemaController @Inject() (cc: ControllerComponents) extends AbstractController(cc) with Circe {

  def upload(schemaId: String): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    request.body.asJson match {
      case Some(jsonRaw) =>
        StorageService.upload(schemaId, jsonRaw.toString).map {
          case Right(validResponse)  => Created(validResponse)
          case Left(invalidResponse) => BadRequest(invalidResponse)
        }
      case None =>
        Future(BadRequest("Incorrect request, only JSONs are expected"))
    }
  }

  def download(schemaId: String): Action[AnyContent] = Action.async {
    StorageService.download(schemaId).map {
      case Some(schemaJson) => Ok(schemaJson)
      case None             => NotFound(s"There is no schema $schemaId")
    }
  }

}
