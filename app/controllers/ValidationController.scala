package controllers

import play.api.mvc._
import services.ValidationService

import javax.inject._
import scala.util.{ Failure, Success }

@Singleton
class ValidationController @Inject() (cc: ControllerComponents) extends AbstractController(cc) {

  def validate(schemaId: String): Action[_] = Action { implicit request: Request[AnyContent] =>
    request.body.asJson match {
      case Some(json) =>
        ValidationService.validate(schemaId, json) match {
          case Success(cleanJson) =>
            Ok(cleanJson)
          case Failure(exception) =>
            BadRequest(s"Upload failed $exception for schema $schemaId")
        }
      case None =>
        BadRequest("Incorrect request, only JSONs are expected")
    }
  }

}
