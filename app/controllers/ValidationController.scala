package controllers

import play.api.mvc._
import services.{ SchemaService, ValidationService }

import javax.inject._
import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class ValidationController @Inject() (cc: ControllerComponents) extends AbstractController(cc) {

  def validate(schemaId: String): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    SchemaService.download(schemaId).map {
      case Some(schema) =>
        request.body.asJson match {
          case Some(json) =>
            ValidationService.validate(schema, json) match {
              case Right(cleanJson) => Ok(cleanJson)
              case Left(diffJson)   => BadRequest(diffJson)
            }
          case None =>
            BadRequest("Incorrect request, only JSONs are expected")
        }
      case None =>
        NotFound(s"Schema $schemaId is not uploaded")
    }
  }

}
