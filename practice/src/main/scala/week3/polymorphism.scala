import week3._

object polymorphism {

  def nth[A](n: Int, list: List[A]): A = {
    if (list.isEmpty) throw new IndexOutOfBoundsException
    else if (n == 0) list.head
    else nth(n - 1, list.tail)
  }

  def main(args: Array[String]) = {
    println(nth(2, new Cons("hello", new Cons("there", new Cons("you", new Nil)))))
    println(nth(-2, new Cons("hello", new Cons("there", new Cons("you", new Nil)))))
  }
}
