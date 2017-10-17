/**
  * description: 
  * link to test/main class:
  */
trait Ping{
  def ping(): Unit = println("ping")
}

trait Pong{
  def ping(): Unit = println("just to annoy you")
  def pong(): Unit = println("pong")
}

object MixinRunner extends Ping with Pong {

  override def ping(): Unit = super.ping()

  def main(args: Array[String]): Unit = {
    ping()
    pong()
  }
}


