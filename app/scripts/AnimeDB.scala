package scripts

import scala.Array.canBuildFrom
import scala.Option.option2Iterable
import scala.collection.mutable.ArrayBuffer
import scala.concurrent.ExecutionContext.Implicits.global
import scala.io.Source

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.SQLContext

import com.mongodb.spark.sql._

//import sqlContext.implicits._

import reactivemongo.api.MongoDriver
//import reactivemongo.api.collections.default.BSONCollection


@deprecated("Not used anymore")
object LoadAnimeDataset {

  /*def addToEntries(entries: ArrayBuffer[AmazonMeta], currentItem: AmazonMeta) = {
    // entries += currentItem
    println(currentItem)
  }

  def addAnimeT(collection: BSONCollection, currentItem: AmazonMeta) = {
    // entries += currentItem
    println(currentItem)
    collection.save(currentItem)
  }*/

  def main(args: Array[String]) {
    
    val conf = new SparkConf().setAppName("Anime-Recommender-System").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val spark = SparkSession
      .builder()
      .getOrCreate()
    val sqlContext = new SQLContext(sc)

    val animeFile = "datasets/anime.csv"

    val animeDF = sqlContext.read.format("com.databricks.spark.csv")
      .option("header", "true")
      .option("inferSchema", "true")
      .load(animeFile)    

    //val animeDF = session.read.format("com.databricks.spark.csv").option("inferSchema", "true").option("header", "true").load(animeFile)
    //val moviesDF = animeDF.select(df2.col("movieId"), df2.col("title"), df2.col("genres"))

    print(animeDF.limit(10))

    //MongoSpark.save(animeDF.write.option("collection", "hundredClub").mode("overwrite"))

    //println("Reading from the 'hundredClub' collection:")
    //MongoSpark.load[Character](sqlContext, ReadConfig(Map("collection" -> "hundredClub"), Some(ReadConfig(sqlContext)))).show()

    }
}