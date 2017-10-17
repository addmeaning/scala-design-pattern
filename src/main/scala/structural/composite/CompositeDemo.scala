package structural.composite

import scala.collection.mutable.ListBuffer

class Tree extends Node{
  private val children: ListBuffer[Node] = ListBuffer.empty[Node]

  override def print(prefix: String):Unit = {
    println(s"$prefix(")
    children.foreach(_.print(s"$prefix$prefix"))
    println(s"$prefix)")
  }
  def add(child: Node):Unit = children += child
  def remove():Unit = if (children.nonEmpty) children remove 0
}

class Leaf(val data:String) extends Node{


  override def print(prefix: String): Unit = println(s"$prefix$data")
}

trait Node{
  def print(prefix: String):Unit
}