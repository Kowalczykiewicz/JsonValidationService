package controllers

import play.api.libs.circe.Circe
import play.api.mvc._
import services.{ StorageService, ValidationService }

import javax.inject._
import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class ValidationController @Inject() (cc: ControllerComponents) extends AbstractController(cc) with Circe {

  def validate(schemaId: String): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    StorageService.download(schemaId).map {
      case Some(schema) =>
        request.body.asJson match {
          case Some(jsonRaw) =>
            ValidationService.validate(schemaId, schema.toString, jsonRaw.toString) match {
              case Right(validResponse)  => Ok(validResponse)
              case Left(invalidResponse) => BadRequest(invalidResponse)
            }
          case None =>
            BadRequest("Incorrect request, empty body")
        }
      case None =>
        NotFound(s"Schema $schemaId is not uploaded")
    }
  }

}
