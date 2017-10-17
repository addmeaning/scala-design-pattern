package scala

final class Wrapper(val underlying: Int) extends AnyVal {
  def foo: Wrapper = new Wrapper(underlying * 19)
}

object Runner {
  def matcher(x: Any): Unit = {
    println(x.getClass)

  }

  def main(args: Array[String]): Unit = {
    println(new Wrapper(4).foo.toString)
    val i : Int = 4
    println(i.toString)
  }
}