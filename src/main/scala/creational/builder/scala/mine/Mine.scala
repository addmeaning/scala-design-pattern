package creational.builder.scala.mine


class Person(
              val firstName: String,
              val lastName: String,
              val age: Int)

sealed trait BuildStep

sealed trait HasFirstName extends BuildStep

sealed trait HasLastName extends BuildStep

class PersonBuilder[Step <: BuildStep] private(
                                                private var firstName: String,
                                                private var lastName: String,
                                                private var age: Int
                                              ) {
  protected def this(pb: PersonBuilder[_]) = this(
    pb.firstName,
    pb.lastName,
    pb.age

  )

  def setFirstName(firstName: String): PersonBuilder[HasFirstName] = {
    this.firstName = firstName
    new PersonBuilder[HasFirstName](this)
  }

  def setLastName[S <: HasFirstName](lastName: String): PersonBuilder[HasLastName] = {
    this.lastName = lastName
    new PersonBuilder[HasLastName](this)
  }

  def build[S <: HasLastName]() = new Person(firstName, lastName, age)
}

object PersonBuilder {
  def apply() = new PersonBuilder[BuildStep]("", "", 0)

}

object Test {
  def main(args: Array[String]): Unit = {
    println(PersonBuilder().setLastName("s").build())
  }
}

