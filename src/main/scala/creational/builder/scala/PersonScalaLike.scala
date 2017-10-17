package creational.builder.scala

sealed trait BuildStep

sealed trait HasFirstName extends BuildStep

sealed trait HasLastName extends BuildStep

class Person(
              val firstName: String,
              val lastName: String,
              val age: Int)

class PersonBuilder[PassedStep <: BuildStep] private (
                                                       var firstName: String,
                                                       var lastName: String,
                                                       var age: Int
                                                     ) {
  protected def this() = this("","",0)
  protected def this(pb: PersonBuilder[_]) = this(
    pb.firstName,
    pb.lastName,
    pb.age
  )
  def setFirstName(firstName: String): PersonBuilder[HasFirstName] = {
    this.firstName = firstName
    new PersonBuilder[HasFirstName](this)
  }

  def setLastName(lastName: String)(implicit ev: PassedStep =:= HasFirstName): PersonBuilder[HasLastName] = {
    this.lastName = lastName
    new PersonBuilder[HasLastName](this)
  }

  def setAge(age: Int): PersonBuilder[PassedStep] = {
    this.age = age
    this
  }

  def build()(implicit ev: PassedStep =:= HasLastName): Person = new Person(
    firstName,
    lastName,
    age
  )
}

object PersonBuilder {
  def apply() = new PersonBuilder[BuildStep]()
}
