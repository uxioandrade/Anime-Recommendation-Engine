package controllers

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import play.api.mvc.Action
import play.api.mvc.Controller
import model.UserService
import model.RecommendationService
import model.AnimeService
import generated.enums.Animetype
import util.SparkAlsUtility
import org.apache.spark.mllib.recommendation.Rating
import play.api.libs.json._
import play.api.data.Forms._
import play.api.data._
import play.api.mvc._
import javax.inject._


@Singleton
class RecommendationController  @Inject()(
  recommendendationService: RecommendationService,
  animeService: AnimeService,
  usersService: UserService
  )
extends Controller {

  def userRecommendationForm(): Form[String] = 
    Form (
      "username" -> nonEmptyText
    )

  def formatRecommendationAsJson(recommendations: Map[Long,Double],animeData: Map[Long,(String, String)]): JsArray = {
    var animeArray = new JsArray
    animeData map { 
      case (k,(v1,v2)) => {
        animeArray = animeArray :+ Json.obj(
          "id" -> k,
          "title" -> v1,
          "imageUrl" -> v2,
          "score" -> recommendations(k)
        )
      }
    }
    animeArray
  }
  
  def getRecommendationForUser(username: String) = Action { implicit req =>
    userRecommendationForm().bindFromRequest.fold(
      formWithErrors => {
        BadRequest("Error when retrieving this user's recommendations")
      },
        form => {
          val userId = usersService.getUserIdByUsername(form)
          val recommendations = SparkAlsUtility.makePrediction(userId)
          val recommendationsIds = recommendations.map(x => x.product.toLong).toList
          val recommendationsMap = recommendations.map(
            x =>
              x.product.toLong -> x.rating
          ).toMap
          Ok(
            formatRecommendationAsJson(
              recommendationsMap,
              animeService.getSeqOfAnimesWithTheirId(recommendationsIds)
            )
          )
      }
    )
  }
/*
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
*/
}