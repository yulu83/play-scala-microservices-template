package controllers.security

import domain.security.SecurityToken
import javax.inject.Inject
import play.api.mvc._
import services.SecurityService

import scala.concurrent.{ExecutionContext, Future}

case class SecuredRequest[A](val user: SecurityToken, request: Request[A]) extends WrappedRequest[A](request)

case class SecuredAction[B] @Inject()(val parser: BodyParser[B], scs: SecurityService)
                             (implicit val executionContext: ExecutionContext)
  extends ActionBuilder[SecuredRequest, B] {

  override def invokeBlock[A](request: Request[A], block: SecuredRequest[A] => Future[Result]): Future[Result] = {
    val user: Option[String] = request.headers.get("username")
    val token: Option[String] = request.headers.get("token")

    val isValid = scs.isTokenValid(user.fold("")(u => u), token.fold("")(t => t))

    if (isValid) {
      block(SecuredRequest(SecurityToken("ylu3", "token"), request))
    } else {
      Future.successful(Results.Unauthorized("Unauthorized"))
    }
  }
}
