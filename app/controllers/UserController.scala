package controllers

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import play.api.mvc._

import play.Logger
import javax.inject._
import play.api.libs.json._

import model.UserService

@Singleton
class UserController @Inject()(
    userService: UserService,
    recommender: Recommender)
extends Controller {


def getUser(user: String) = Action {
      userService.getOneUser()
      Ok
  }

}