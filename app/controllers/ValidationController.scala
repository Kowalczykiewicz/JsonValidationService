package controllers

import play.api.mvc._
import services.ValidationService

import javax.inject._
import scala.util.{Failure, Success}

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class ValidationController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
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
