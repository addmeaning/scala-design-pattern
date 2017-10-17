package functional

import scala.collection.{GenTraversable, GenTraversableOnce}

trait Monad[T] extends Functor[T] {
  def flatMap[Y](f: T => Monad[Y]): Monad[Y]

  def unit[Y](value: Y): Monad[Y]

  override def map[Y](f: T => Y): Monad[Y] = flatMap(x => unit(f(x)))
}


case class IntList(list: List[Int]) {
  def map[B](f: Int => B): List[B] = list.map(f)

  def flatMap[B](f: Int => GenTraversableOnce[B]): List[B] = list.flatMap(f)
}

//case class IntList[B](list: List[Int]) extends Monad[B] {
//  override def flatMap[Y](f: (Y) => Monad[B]): Monad[B] = ???
//
//  override def unit(value: B): Monad[B] = ???
//}

sealed trait Option[A] extends Monad[A]

case class Some[A](a: A) extends Option[A] {
  override def unit[Y](value: Y): Monad[Y] = Some(value)

  override def flatMap[Y](f: (A) => Monad[Y]): Monad[Y] = f(a)
}

case class None[A]() extends Option[A] {
  override def unit[Y](value: Y): Monad[Y] = None()

  override def flatMap[Y](f: (A) => Monad[Y]): Monad[Y] = None()
}

case class Doer_v2() {
  def getAlgorithm(isFail: Boolean): Option[Algorithm_v2] =
    if (isFail) None() else Some(Algorithm_v2())
}

case class Implementation(left: Int, right: Int) {
  def compute: Int = left + right
}

case class Algorithm_v2() {
  def getImplementation(isFail: Boolean, left: Int, right: Int): Option[Implementation] =
    if (isFail) None()
    else Some(Implementation(left, right))
}