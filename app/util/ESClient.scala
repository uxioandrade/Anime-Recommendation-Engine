package util

import com.sksamuel.elastic4s.ElasticClient
import com.sksamuel.elastic4s.ElasticDsl._
import configuration.AppConfig
import scala.concurrent.Future
import org.elasticsearch.action.search.SearchResponse


//Not used right now, but it will probably be used in the near future
//to implement the anime search

object ESClient {
  val ESConfig = AppConfig.ES

  /**
   * Search for an item in the index
   * @param q
   * @return
   */
  
  /*
  def searchItem(q: String): Future[SearchResponse] = {
    
    val client = ElasticClient.remote(ESConfig.server, ESConfig.port)
    val productsIndex = ESConfig.productsIndex
    val rs = client.execute { search in productsIndex / "items" query q }
    rs
  }*/

}
