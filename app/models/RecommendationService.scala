package model

import org.apache.spark.mllib.recommendation._
import javax.inject._
import org.jooq.{SQLDialect, DSLContext}
import org.jooq.impl.DSL
import generated.Tables.VIEWER
import play.api.db.Database
import play.api._
import collection.JavaConverters._


@Singleton
class RecommendationService @Inject()() {
    val dbContext: DSLContext = Driver.getDbContext
/* 
    val conf = new SparkConf().setAppName("Anime-Recommender-System").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val spark = SparkSession
        .builder()
        .getOrCreate()
    val sqlContext = new SQLContext(sc)

    val model = ALS.load("model/anime-recommendation.model")

    def getRecommendationForUser(userid : Long) = {
        val topRecommendations = model.recommendProduct(userid,5)
        topRecommendations.foreach(println)
    }*/
}   