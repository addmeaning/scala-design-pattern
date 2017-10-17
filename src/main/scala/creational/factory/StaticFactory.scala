package creational.factory

trait Animal
class Bird extends Animal
class Mammal extends Animal
class Fish extends Animal

object Animal{
  def apply(animal: String): Animal = {
    val lowerCaseName = animal.toLowerCase
    lowerCaseName match {
      case "bird" => new Bird
      case "mammal" => new Mammal
      case "fish" => new Fish
      case x: String => throw new RuntimeException(s"unknown animal $x")
    }
  }
}