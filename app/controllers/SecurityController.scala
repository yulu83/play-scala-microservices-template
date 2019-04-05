package controllers

import domain.security.{Login, SecurityToken}
import javax.inject._
import play.api.Logger
import play.api.libs.json.Json
import play.api.mvc._
import services.SecurityService

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class SecurityController @Inject()(cc: ControllerComponents, securityService: SecurityService) extends AbstractController(cc) {

  private val logger: Logger = Logger(this.getClass)

  implicit val securityTokenWrite = Json.writes[SecurityToken]
  //private val securityService: SecurityService = new SecurityServiceImpl;
  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def login() = Action { implicit request: Request[AnyContent] =>
    val opToken = securityService.authenticate(Login("yuan", "password"))
    logger.info(opToken.toString)
    Ok(Json.toJson(SecurityToken("yuan", "token")))
  }
}
