package structural.flyweight


import scala.collection.mutable
import scala.collection.mutable.ListBuffer


object FlyweightDemo {
  def main(args: Array[String]): Unit = {
    val graphic = new Graphics
    graphic.addCircle(1, 1, 1.0, CircleFactory.makeCircle(Green))
    graphic.addCircle(1, 2, 1.0, CircleFactory.makeCircle(Red))
    graphic.addCircle(2, 1, 1.0, CircleFactory.makeCircle(Blue))
    graphic.addCircle(2, 2, 1.0, CircleFactory.makeCircle(Green))
    graphic.addCircle(2, 3, 1.0, CircleFactory.makeCircle(Yellow))
    graphic.addCircle(3, 2, 1.0, CircleFactory.makeCircle(Magenta))
    graphic.addCircle(3, 3, 1.0, CircleFactory.makeCircle(Blue))
    graphic.addCircle(4, 3, 1.0, CircleFactory.makeCircle(Blue))
    graphic.addCircle(3, 4, 1.0, CircleFactory.makeCircle(Yellow))
    graphic.addCircle(4, 4, 1.0, CircleFactory.makeCircle(Red))

    graphic.draw()

    println(s"Total number of circle objects created: ${CircleFactory.circlesCreated()}")
  }
}

class Graphics {
  protected val items: ListBuffer[(Int, Int, Double, Circle)] = ListBuffer.empty

  def addCircle(x: Int, y: Int, radius: Double, circle: Circle): Unit = {
    items += ((x, y, radius, circle))
  }

  def draw(): Unit = {
    items.foreach {
      case (x, y, radius, circle) =>
        println(s"Drawing a circle at ($x, $y) with radius $radius: $circle")
    }
  }
}


object CircleFactory {
  private val cache: mutable.Map[Color, Circle] = mutable.Map.empty

  def makeCircle(color: Color): Circle = cache.getOrElseUpdate(color, Circle(color))

  def circlesCreated(): Int = cache.size
}

case class Circle(color: Color) {
  {
    println(s"creating a circle with $color color")
  }
}


sealed abstract class Color

case object Red extends Color

case object Green extends Color

case object Blue extends Color

case object Magenta extends Color

case object Yellow extends Color