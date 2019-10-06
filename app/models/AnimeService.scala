package model

import javax.inject._
import org.jooq.{SQLDialect, DSLContext}
import org.jooq.impl.DSL
import generated.Tables.ANIME
import generated.enums.Animetype
import play.api.db.Database
import play.api._
import play.api.libs.json._
import collection.JavaConverters._


@Singleton
class AnimeService @Inject()() {
    val dbContext: DSLContext = Driver.getDbContext

    def getAllAnimes(): IndexedSeq[JsObject] = {
        val ret = dbContext.selectFrom(ANIME).limit(100).fetch()
        val animes = ret.asScala.map(anime => new Anime(anime))
        animes.map(anime => anime.toJson).toIndexedSeq
    }       

    def getTopRatedAnimes(): IndexedSeq[JsObject] = {
        val ret = dbContext.selectFrom(ANIME).orderBy(ANIME.SCORE.desc()).limit(10).fetch()
        val animes = ret.asScala.map(anime => new Anime(anime))
        animes.map(anime => anime.toJson).toIndexedSeq
    }

    def getAnimeById(id: Long): JsObject = {
        val ret = dbContext
            .selectFrom(ANIME)
            .where(ANIME.ID equal id)
            .fetch
        val anime = ret.map(anime => new Anime(anime))
        anime.get(0).toJson
    }
 
    //TODO: this should be done with SQL's IN clause
    def getSeqOfAnimesWithTheirId(animeIds: Seq[Long]): Map[Long,(String,String)] = {
        animeIds.map(x => {
            val anime = dbContext
                .select(ANIME.ID, ANIME.TITLE, ANIME.IMAGEURL)
                .from(ANIME)
                .where(ANIME.ID equal x)
                .fetchOne
            x -> (Option(anime.getValue(ANIME.TITLE)).getOrElse(""),Option(anime.getValue(ANIME.IMAGEURL)).getOrElse(""))
        }).toMap
    }

}   