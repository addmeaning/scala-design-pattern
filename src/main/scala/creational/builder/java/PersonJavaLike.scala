package creational.builder.java

//note: case classes are better

case class  Person(
                   firstName: String = "",
                   lastName: String = "",
                   age: Int = 0
                 )

object PersonCaseClassExample {
  def main(args: Array[String]): Unit = {
    val person1 = Person(
      firstName = "Ivan",
      lastName = "Nikolov",
      age = 26
    )

    val person2 = Person(
      firstName = "John"
    )

    System.out.println(s"Person 1: ${person1}")
    System.out.println(s"Person 2: ${person2}")
  }
}

//java-like implementation
//with lots of boilerplate
class PersonJavaLike(builder: PersonBuilder) {
  val firstName = builder.firstName
  val lastName = builder.lastName
  val age = builder.age
}

class PersonBuilder {
  var firstName = ""
  var lastName = ""
  var age = 0
  def setFirstName(firstName: String): PersonBuilder = {
    this.firstName = firstName
    this
  }

  def setLastName(lastName: String): PersonBuilder = {
    this.lastName = lastName
    this
  }

  def setAge(age: Int): PersonBuilder = {
    this.age = age
    this
  }
  def build(): PersonJavaLike = new PersonJavaLike(this)
}