package controllers

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import play.api.mvc.Action
import play.api.mvc.Controller
import play.api.mvc.{ Action, Controller }
import util.ReactiveDB
import util.Recommender
import play.Logger

object CustomerController extends Controller {
  def list() = Action.async {

    val itemsF = ReactiveDB.allCustomers().collect[Seq](100, true)
    itemsF map { items =>
      Ok(views.html.customers(items))
    }
  }

  def get(number: Int) = Action.async {
    val itemF = ReactiveDB.getOneCustomerByNumber(number)
    itemF.map { itemOpt =>
      Ok(views.html.customer(itemOpt))
    }
  }
}
