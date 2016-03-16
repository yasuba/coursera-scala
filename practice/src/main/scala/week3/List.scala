package week3

trait List[T] {
  def isEmpty: Boolean
  def head: T
  def tail: List[T]
}

class Cons[T](val head: T, val tail: List[T]) extends List[T] {
  def isEmpty: Boolean = false
  override def toString = "(" + head + tail + ")"
}

class Nil[T] extends List[T] {
  def isEmpty: Boolean = true
  def head: Nothing = throw new NoSuchElementException("Nil.head")
  def tail: Nothing = throw new NoSuchElementException("Nil.tail")
}

object types {
  def singleton[T](elem: T) = new Cons[T](elem, new Nil[T])

  def main(args: Array[String]) = {
    println(singleton[Int](1))
    println(singleton(true))
  }
}
