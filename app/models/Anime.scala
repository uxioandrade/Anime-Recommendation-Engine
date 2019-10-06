package model

import generated.tables.records.AnimeRecord
import play.api.libs.json._

case class Anime(id: Long,
  title: String,
  imageUrl: String,
  format: generated.enums.Animetype,
  source: String,
  episodes: Int,
  status: generated.enums.Animestatus,
  airing: Boolean,
  duration: String,
  rating: String,
  score: Float,
  scoredBy: Long,
  rank: Float,
  popularity: Long,
  members: Long,
  genre: String) {
    def this(ar: AnimeRecord) = this(
      ar.getId,
      ar.getTitle,
      ar.getImageurl,
      ar.getFormat,
      ar.getSource,
      ar.getEpisodes,
      ar.getStatus,
      ar.getAiring,
      ar.getDuration,
      ar.getRating,
      ar.getScore,
      ar.getScoredby,
      ar.getRank,
      ar.getPopularity,
      ar.getMembers,
      ar.getGenre
    )

    def toJson() : JsObject = {
      Json.obj(
        "id" -> this.id,
        "title" -> this.title,
        "imageUrl" -> this.imageUrl,
        "format" -> this.format.toString,
        "source" -> this.source,
        "episodes" -> this.episodes,
        "status" -> this.status.toString,
        "airing" -> this.airing,
        "duration" -> this.duration,
        "rating" -> this.rating,
        "score" -> this.score,
        "scoredBy" -> this.scoredBy,
        "rank" -> this.rank,
        "popularity" -> this.popularity,
        "members" -> this.members,
        "genre" -> this.genre
      )
    }
  }