package controllers

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import play.api.mvc.Action
import play.api.mvc.Controller
import util.Database
import util.ReactiveDB
import play.api.libs.json.Json
import play.api.libs.json.JsValue
import util.Recommender
import javax.inject._


@Singleton
class RecommendationController  @Inject()(recommender: Recommender)
extends Controller {

  def topRated() = Action.async {
    val itemsF = ReactiveDB.topRatedProducts.collect[Seq](100, ReactiveDB.handleProductsError())
    itemsF map { items =>
      Ok(views.html.top_rated_products(items))
    }
  }

  def topRatedAnimes() = Action.async {
    val itemsF = ReactiveDB.topRatedAnimes.collect[Seq](100, ReactiveDB.handleAnimeError())
    itemsF map { items =>
      Ok(views.html.top_rated_animes(items))
    }
  }

  def mostPopular() = Action.async {
    val itemsF = ReactiveDB.mostPopularProducts.collect[Seq](100, ReactiveDB.handleProductsError())
    itemsF map { items =>
      Ok(views.html.most_popular_products(items))
    }
  }

  def trends() = Action {
    Ok(views.html.trending())
  }

  def trendingProduct(year: Int, month: Int) = Action.async {
    val tpF = recommender.findTrendingProductsFor(year, month - 1)
    tpF.map { items =>
      Ok(Json.toJson(items))
    }
  }

  def trendingCustomer(year: Int, month: Int) = Action.async {
    val tpF = recommender.findTrendingCustomersFor(year, month - 1)
    tpF.map { items =>
      Ok(Json.toJson(items))
    }
  }

  def recommendationsForCustomer(customerId: String) = Action.async {
    val tpF = recommender.findForCustomer(customerId)
    tpF.map { items =>
      Ok(Json.toJson(items))
    }
  }

}