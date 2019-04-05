package services

import com.google.inject.ImplementedBy
import domain.security.{Login, SecurityToken}

@ImplementedBy(classOf[SecurityServiceImpl])
trait SecurityService {

  def authenticate(login: Login): Option[SecurityToken]
  def isTokenValid(user: String, token: String): Boolean

}

class SecurityServiceImpl extends SecurityService {

  override def authenticate(login: Login): Option[SecurityToken] = {
    if (login.user == "yuan" && login.password == "password")
      Some(SecurityToken("yuan", "token"))
    else None
  }

  override def isTokenValid(username: String, token: String) = {
    // place holder to check if user token is valid
    if (username == "ylu3") true else true
  }
}
