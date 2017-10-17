package simulacrum



import simulacrum._

@typeclass trait Semigroup[A]{
  @op("|+|") def append(x: A, y: A): A
}

@typeclass trait Mutator[F[_]]{
  @op("-+>") def get(x: F, y: F): F
}


object Demo extends App{
  implicit val semigroupInt : Semigroup[Int] = (x: Int, y: Int) => x + y
  implicit val semigroupString : Semigroup[String] = (x: String, y: String) => x + " elo " + y
  implicit def mutateToString[F]: Mutator[Semigroup[F]] = new Mutator[Semigroup[F]] {
    override def get(x: Semigroup[F], y: Semigroup[F]): Semigroup[F] = (x: F, y: F) => x
  }
  import Semigroup.ops._
  import Mutator.ops._
  println(1 |+| 2)
  println("h" |+| "!")
}