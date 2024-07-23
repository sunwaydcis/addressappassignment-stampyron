package ch.makery.address.util

import ch.makery.address.model.Person
import scalikejdbc.{AutoSession, ConnectionPool, DB}

trait Database{
  val derbyDriverClassname = "org.apache.derby.jdbc.EmbeddedDriver"

  val dbURL = "jdbc:derby:myDB;create=true"
  //intilaize JDBC driver and the connection pool
  Class.forName(derbyDriverClassname)
  ConnectionPool.singleton(dbURL, "me", "mine")

  //adhoc session provider
  implicit val session = AutoSession
}

object Database extends Database{
  def hasDBInitialize: Boolean ={
    DB getTable("Person") match {
      case Some(x) => true
      case None => false
    }
  }

  def setupDB(): Unit = {
    if(!hasDBInitialize){
      Person.initializeTable()

    }
  }
}
