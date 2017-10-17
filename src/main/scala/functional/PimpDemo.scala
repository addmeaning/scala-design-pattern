package functional

package object implicits {

implicit class StringOpss(s: String) {
  def bling = println(s"*$s*")
}

}

object AppDemo extends App {
  import functional.implicits._

  "s".bling
}

