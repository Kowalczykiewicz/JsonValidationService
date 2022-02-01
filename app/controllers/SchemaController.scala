package controllers

import play.api.mvc._

import javax.inject._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class SchemaController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def upload(schemaId: String) = Action { implicit request: Request[AnyContent] =>
    Ok("upload")
  }
  
  def download(schemaId: String) = Action { implicit request: Request[AnyContent] =>
    Ok("download")
  }

  
}
