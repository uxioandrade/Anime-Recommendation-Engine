package controllers

import play.api.mvc.Action
import play.api.mvc.Controller
import javax.inject._

@Singleton
class Application extends Controller {

  def index() = Action {
    Ok(views.html.home())
  }

}
