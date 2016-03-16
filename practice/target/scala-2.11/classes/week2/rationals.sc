package week2

object rationals {
  val x = new Rational(1, 3)
  x.numer

  val y = new Rational(5, 7)
  x + y
  val z = new Rational(3, 2)

  -z

  z - x

  x - y - z

  y + y

  x < y

  x.max(y)
}

class Rational(x: Int, y: Int) {

  require(y != 0, "y must not be zero")

  private def greatestCommonDivisor(a: Int, b: Int): Int = if (b == 0) a else greatestCommonDivisor(b, a % b)

  def numer = x
  def denom = y

  def + (that: Rational) =
    new Rational(
      numer * that.denom + that.numer * denom,
      denom * that.denom)

  def unary_- : Rational = new Rational(-x, y)

  def - (that: Rational) =
    this + -that

  def < (that: Rational) =
    this.numer * that.denom < that.numer * this.denom

  def max(that: Rational) =
    if(this < that) that else this

  override def toString = {
    val g = greatestCommonDivisor(x,y)
    numer/g + "/" + denom/g
  }
}
