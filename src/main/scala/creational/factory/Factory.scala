package creational.factory

trait SimpleConnection {
  def getName(): String
  def executeQuery(query: String): Unit
}

class SimpleMySqlConnection extends SimpleConnection {
  override def getName(): String = "MySQL"
  override def executeQuery(query: String): Unit = print(s"MySQL: $query")
}

class SimplePgSqlConnection extends SimpleConnection {
  override def getName(): String = "PgSQL"
  override def executeQuery(query: String): Unit = print(s"PgSQL: $query")
}


abstract class DatabaseClient {
  def executeQuery(query: String): Unit = {
    val connection = connect()
    connection.executeQuery(query)
  }

  protected def connect(): SimpleConnection
}


class MySqlClient extends  DatabaseClient{
  override protected def connect(): SimpleConnection = new SimpleMySqlConnection
}

class PgClient extends DatabaseClient {
  override protected def connect(): SimpleConnection = new SimplePgSqlConnection
}

object Example extends App{
  val mySqlClient : DatabaseClient = new MySqlClient
  val pgClient: DatabaseClient = new PgClient
  mySqlClient.executeQuery("select * from somewhere")
  pgClient.executeQuery("select * from somewhereElse")
}