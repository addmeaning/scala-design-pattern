package creational.prototype

case class Cell(dna: String, proteins: List[String])
object PrototypeExample {
  def main(args: Array[String]): Unit = {
    val initialCell = Cell("abcd", List("protein1", "protein2"))
    val copy1 = initialCell.copy()
    val copy2 = initialCell.copy()
    val copy3 = initialCell.copy(dna = "1234")
    println(s"The prototype is: ${initialCell}")
    println(s"Cell 1: ${copy1}")
    println(s"Cell 2: ${copy2}")
    println(s"Cell 3: ${copy3}")
    println(s"1 and 2 are equal: ${copy1 == copy2}")
  }
}