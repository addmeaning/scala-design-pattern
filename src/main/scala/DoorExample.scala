

trait Status

trait Open extends Status

trait Closed extends Status

trait Door[S <: Status]

object Door {
  def apply[S <: Status] = new Door[S] {}

  def open[S <: Closed](d: Door[S]) = Door[Open]
  def close[S<: Open](d: Door[S]) = Door[Closed]
}


object Test {
  def main(args: Array[String]): Unit = {
    val closed = Door[Closed]
    Door.open(closed)
  }
}