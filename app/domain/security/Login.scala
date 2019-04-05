package domain.security

case class Login(user: String, password: String)
case class SecurityToken(user: String, token: String)
