package util

import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.mllib.recommendation._
import org.apache.spark.ml.PipelineModel

object SparkAlsUtility {

    def makePrediction(userId: Long): Seq[Rating] = {

        val conf = new SparkConf(false)
            .setMaster("local[4]")
            .setAppName("animeRecommendation")
            .set("spark.logConf","true")
            .set("spark.driver.allowMultipleContexts", "true")
            .set("spark.driver.host","localhost")

        val sc = new SparkContext(conf)

        val model = MatrixFactorizationModel.load(sc,"machineLearningModels/als-model")

        val recommendations = model.recommendProducts(userId.toInt, 5)
        recommendations
    }

}