package controllers

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import play.api.mvc._
//import util.Recommender
import play.Logger
import reactivemongo.api.Cursor
import javax.inject._
import play.api.libs.json._

import model.AnimeService


@Singleton
class AnimeController @Inject()(
  
  animeService: AnimeService
  )
extends Controller {

  def list() = Action {
    val animes = animeService.getAllAnimes()
    Ok(new JsArray(animes))
  }

  def getTopRatedAnimes() = Action {
    val animes = animeService.getTopRatedAnimes()
    Ok(new JsArray(animes))
  }

  def getAnime(id: String) = Action {
    val anime = animeService.getAnimeById(id.toLong)
    Ok(anime)
  }

  def get(number: Int) = Action {
//    val itemsF = ReactiveDB.getOneCustomerByNumber(number,recommender)
    Ok
  }
}
