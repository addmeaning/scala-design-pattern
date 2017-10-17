package functional

trait Monoid[T] {
  def zero: T

  def op(l: T, r: T): T
}

object MonoidOps {
  def balancedFold[K, Y](seq: IndexedSeq[K], m: Monoid[Y])(f: K => Y): Y = {
    if (seq.isEmpty) m.zero
    else if (seq.length == 1) f(seq.head)
    else {
      val (left, right) = seq.splitAt(seq.length / 2)
      m.op(balancedFold(left, m)(f), balancedFold(right, m)(f))
    }
  }

  def mapMerge[K, V](a: Monoid[V]): Monoid[Map[K, V]] = {
    new Monoid[Map[K, V]] {
      override def op(l: Map[K, V], r: Map[K, V]): Map[K, V] =
        (l.keySet ++ r.keySet).foldLeft(zero) { (result, it) => result.updated(it, a.op(l.getOrElse(it, a.zero), r.getOrElse(it, a.zero))) }

      override def zero: Map[K, V] = Map()
    }
  }
}

object FeatureCounting {
  def main(args: Array[String]): Unit = {
    val features = Array("hello", "features", "for", "ml", "hello", "for", "features")
    val intAddition: Monoid[Int] = new Monoid[Int] {
      override def op(l: Int, r: Int): Int = r + l

      override def zero: Int = 0
    }
    val counterMonoid: Monoid[Map[String, Int]] = MonoidOps.mapMerge(intAddition)

    System.out.println(s"The features are: ${MonoidOps.balancedFold(features, counterMonoid)(i => Map(i -> 1))}")
  }
}