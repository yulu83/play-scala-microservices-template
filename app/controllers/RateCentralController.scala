package controllers

import controllers.security.{SecuredAction, SecuredRequest}
import javax.inject._
import play.api.mvc._
import services.SecurityService

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class RateCentralController @Inject()(securityService: SecurityService, cc: ControllerComponents) extends AbstractController(cc) {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def main = SecuredAction(parse.default,securityService).async { implicit request: SecuredRequest[AnyContent] =>
    Future.successful(Ok(s"Hello User " +  request.user.token))
  }
}
