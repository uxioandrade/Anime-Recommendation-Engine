package util

import org.apache.spark.mllib.recommendation._
import scala.Tuple2
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.SQLContext
import org.apache.spark.ml.evaluation.RegressionEvaluator

import com.mongodb.spark.sql._

import org.apache.spark.rdd.RDD

//username,anime_id,my_watched_episodes,my_start_date,my_finish_date,my_score,my_status,my_rewatching,my_rewatching_ep,my_last_updated,my_tags

/*case class Rating(
  username: String,
  animeId: Long,
  score: Double,
  watchedEpisodes: Long//  
   )*/

object RecommendationModel {

    def main(args: Array[String]): Unit = {
        val ratingsFile = "datasets/ratings_clean.csv"

        val conf = new SparkConf().setAppName("Anime-Recommender-System").setMaster("local[2]")
        val sc = new SparkContext(conf)
        val spark = SparkSession
            .builder()
            .getOrCreate()
        val sqlContext = new SQLContext(sc)


        val df = spark.read
        .format("com.databricks.spark.csv")
        .option("header",true)
        .load(ratingsFile)

        print(df.first)

        val ratingsDF = df.select(df.col("user_id"),
        df.col("anime_id"),
        df.col("my_score")
        )

        print(ratingsDF.first)

        val filteredDF = ratingsDF.filter(row => !row.anyNull)
        val splits = filteredDF.randomSplit(Array(0.8,0.2),12345L)
        val (trainingData, testData) = (splits(0),splits(1))
        val numTrainig = trainingData.count()
        val numTest = testData.count()
        println("Training: " + numTrainig + " Test: " + numTest)
        trainingData.head(10).foreach(println)
        val ratingsRDD = trainingData.rdd.map(row => {
            val user = row.getString(0)
            val animeId = row.getString(1)
            val score = row.getString(2)
            val userId = if (user.forall(_.isDigit)) user.toInt else 0
            Rating(userId,animeId.toInt,score.toInt.toDouble)
        })

        val testRDD = testData.rdd.map(row => {
            val user = row.getString(0)
            val animeId = row.getString(1)
            val score = row.getString(2)
            val userId = if(user.forall(_.isDigit)) user.toInt else 0
            Rating(userId,animeId.toInt,score.toInt.toDouble)
        })

        val rank = 20
        val numIterations = 15
        val lambda = 0.10
        val alpha = 1.00
        val block = -1
        val seed = 12345L
        val implicitPrefs = false
        val model = 
            new ALS()
            .setIterations(numIterations)
            .setBlocks(block)
            .setAlpha(alpha)
            .setLambda(lambda)
            .setRank(rank)
            .setSeed(seed)
            .setImplicitPrefs(implicitPrefs)
            .run(ratingsRDD)

        println("Recommendations: (MovieId => Rating)") 
        println("----------------------------------") 
        val recommendationsUser = model.recommendProducts(0, 6) 
        recommendationsUser.map(rating => (rating.product, rating.rating)).foreach(println) 
        println("----------------------------------")

        /*
        val evaluator = new RegressionEvaluator()
            .setMetricName("rmse")
            .setLabelCol("rating")
            .setPredictionCol("prediction")

        val predictions = model.transform(testRDD)

        val rmse = evaluator.evaluate(predictions)
        println("Test RMSE: = " + rmse)*/

        model.save(sc,"machineLearningModels/als-model")
    }
}