package util

import com.mongodb.casbah.{ MongoCollection, MongoClient }
import configuration.AppConfig
import model.{ AmazonMeta, Customer }
import reactivemongo.api.MongoDriver
//import reactivemongo.bson.{ BSONDateTime, BSONDocument, BSONDouble }
import reactivemongo.bson._
import reactivemongo.bson.Producer._
import scala.concurrent.ExecutionContext.Implicits.global
import model.CustomerMapping
import scala.concurrent.Future
import play.Logger

import scala.concurrent._
import scala.concurrent.duration._

object ReactiveDB {

  val DBConfig = AppConfig.DBConfig

  def getCollection(collectionName: String) = {
    val mongoClient = MongoClient(DBConfig.dbHost, DBConfig.dbPort)
    val db = mongoClient(DBConfig.dbName)
    db(collectionName)
  }

  def productCollection() = {
    val driver = new MongoDriver
    val connection = driver.connection(List(DBConfig.dbHost))
    val db = Await.result(connection.database(DBConfig.dbName),10.seconds)
    db.collection("products")
  }

  def customerCollection() = {
    val driver = new MongoDriver
    val connection = driver.connection(List(DBConfig.dbHost))
    val db = Await.result(connection.database(DBConfig.dbName),10.seconds)
    db.collection("customers")
  }

  def customerMappingCollection() = {
    val driver = new MongoDriver
    val connection = driver.connection(List(DBConfig.dbHost))
    val db = Await.result(connection.database(DBConfig.dbName),10.seconds)
    db.collection("customer_mapping")
  }

  def reviewsCollection() = {
    val driver = new MongoDriver
    val connection = driver.connection(List(DBConfig.dbHost))
    val db = Await.result(connection.database(DBConfig.dbName),10.seconds)
    db.collection("reviews")
  }

  def getByASIN(asin: String) = {
    val collection = productCollection()
    val query = BSONDocument("asin" -> asin)
    val rs = collection.find(query).cursor[AmazonMeta]
    rs
  }

  def getByGroup(group: String) = {
    val collection = productCollection()
    val query = BSONDocument("group" -> group)
    val rs = collection.find(query).cursor[AmazonMeta]
    rs
  }

  def getAll(asin: String) = {
    val collection = productCollection()
    val query = BSONDocument.empty
    val rs = collection.find(query).cursor[AmazonMeta]
    rs
  }

  def allProducts() = {
    val query = BSONDocument.empty
    val collection = productCollection()
    val cursor = collection.find(query).cursor[AmazonMeta]
    cursor
  }

  def topRatedProducts() = {
    val query = BSONDocument.empty
    val sortCriteria = BSONDocument("overallReview.averageRating" -> -1)
    val collection = productCollection()
    val cursor = collection.find(query).sort(sortCriteria).cursor[AmazonMeta]
    cursor
  }

  def mostPopularProducts() = {
    val query = BSONDocument.empty
    val sortCriteria = BSONDocument("overallReview.total" -> -1)
    val collection = productCollection()
    val cursor = collection.find(query).sort(sortCriteria).cursor[AmazonMeta]
    cursor
  }

  def allCustomers() = {
    val query = BSONDocument.empty
    val collection = customerCollection()
    val cursor = collection.find(query).cursor[Customer]()
    cursor
  }

  def getCustomerByNumber(number: Int) = {
    val collection = customerCollection()
    val query = BSONDocument("Number" -> number)
    val rs = collection.find(query).cursor[Customer]()
    rs
  }

  def getOneCustomerByNumber(number: Int): Future[Option[(Customer, Option[String])]] = {
    val itemCursor = getCustomerByNumber(number)
    val optF = itemCursor.headOption

    val p = for {
      customerOpt <- optF
      t <- customerOpt match {
        case Some(customer) =>
          val f1 = Recommender.findOneAmazonCustomerForNumber(customer.Number)
            .map { x =>
              val y = Some(customer, x)
              y
            }
          f1.onFailure {
            case t: Throwable => Logger.info(s"onFailure: $t")
          }
          f1.fallbackTo(Future {
            val y = Some(customer, None)
            y
          })
        case None =>
          Future(None)
      }
    } yield t
    p.fallbackTo(Future(None))
  }

  def getCustomerByID(id: String) = {
    val collection = customerCollection()
    val query = BSONDocument("id" -> id)
    val rs = collection.find(query).cursor[Customer]
    rs
  }

  def trendingCustomers(year: Int, month: Int) = {
    val query = BSONDocument.empty
    val sortCriteria = BSONDocument("overallReview.total" -> -1)
    val collection = reviewsCollection()
    val cursor = collection.find(query).sort(sortCriteria).cursor[AmazonMeta]
    cursor
  }

  def oneMonthReviewsFrom(year: Int, month: Int) = {

    val start = DateUtils.dateFor(year, month, 1)
    val end = DateUtils.dateFor(year, month + 1, 1)
    println("Start Date: %s, End Date: %s".format(
      DateUtils.sdfFull.format(start),
      DateUtils.sdfFull.format(end)))

    println(start.getTime(), end.getTime())
    val query = BSONDocument(
      "date" -> BSONDocument(
        "$gte" -> BSONDateTime(start.getTime()),
        "$lt" -> BSONDateTime(end.getTime()) //
        ) //
        )

    val sortCriteria = BSONDocument("votes" -> -1)
    val collection = reviewsCollection()
    val cursor = collection
      .find(query)
      //.sort(sortCriteria)
      .cursor[BSONDocument]

    cursor
  }

  def getAmazonCustomersForCustomerNumber(number: Int) = {
    val collection = customerMappingCollection()
    val query = BSONDocument("customer_number" -> number)
    val rs = collection.find(query).cursor[CustomerMapping]
    rs
  }

  def getCustomerNumber(custID: String): Int = {
    val collection = customerMappingCollection()
    val query = BSONDocument("_id" -> custID)
    val cursor = collection.find(query).cursor[CustomerMapping]
    val result = cursor.headOption.map(_.map(_.customerNumber.toInt).getOrElse(0))
    Await.result(result, 10 seconds)
  }

  def main(args: Array[String]) {
    val c = oneMonthReviewsFrom(2005, 1)
    val seqF = c.collect[Seq](10, false)
    seqF.onFailure {
      case _ =>
        println("Failed")
    }

    seqF.onSuccess {
      case x @ _ =>
        println(s"Succeeded with $x")
    }

    val resF = seqF.map { seq =>
      seq foreach { x =>
        val productId = x.get("productId").map { v =>
          v.asInstanceOf[BSONDouble].value.toLong
        }.get
        println(productId)
      }
    }

    Await.result(resF, 1 minute)
  }

}
