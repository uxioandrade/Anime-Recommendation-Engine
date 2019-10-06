package model


import javax.inject._
import org.jooq.{SQLDialect, DSLContext}
import org.jooq.impl.DSL
import generated.Tables.VIEWER
import play.api.db.Database
import play.api._
import collection.JavaConverters._


@Singleton
class UserService @Inject()() {
    val dbContext: DSLContext = Driver.getDbContext

    def getOneUser() = {
        val ret = dbContext.fetch(VIEWER)
        val user = ret.asScala.map(new User(_))
        user
    }
    
    def getUserIdByUsername(username: String): Long = {
        val ret = dbContext
        .select(VIEWER.ID)
        .from(VIEWER)
        .where(VIEWER.USERNAME equal username)
        .fetchOne()

        Option(ret.getValue(0)).map(_.asInstanceOf[Long]).getOrElse(0)
    }
}   