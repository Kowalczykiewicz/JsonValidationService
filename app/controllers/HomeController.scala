package controllers

import play.api.mvc._

import javax.inject._

@Singleton
class HomeController @Inject() (cc: ControllerComponents) extends AbstractController(cc) {

  def index(): Action[AnyContent] = Action {
    val api =
      """
        |POST    /schema/SCHEMAID        - Upload a JSON Schema with unique `SCHEMAID`
        |GET     /schema/SCHEMAID        - Download a JSON Schema with unique `SCHEMAID`
        |POST    /validate/SCHEMAID      - Validate a JSON document against the JSON Schema identified by `SCHEMAID`
        |""".stripMargin
    Ok(api)
  }

}
