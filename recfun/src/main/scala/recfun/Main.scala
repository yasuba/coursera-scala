package recfun

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    def factorial(acc: Int, n:Int): Int = {
      if (r == 0 || c == 0) 1
      else if (n == 0) acc
      else factorial(acc * n, n - 1)
    }
    factorial(1, r) / (factorial(1,c) * factorial(1, r-c))
  }
  
  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
    val parentheses = chars.filter(c => c == '(' || c == ')')
    def oppositeBracket(head: Char) = if (head == '(') ')' else '('
    def removePair(closingBracket: Char, list: List[Char]) = list diff List(closingBracket)

    def areParenthesesBalanced(parentheses: List[Char]): Boolean = {
      if (parentheses.isEmpty) true
      else if (parentheses.head == ')' || parentheses.last == '(') false
      else areParenthesesBalanced(removePair(oppositeBracket(parentheses.head), parentheses.tail))
    }
    areParenthesesBalanced(parentheses)
  }
  
  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    if (money < 0 || coins.isEmpty) 0
    else if(money == 0) 1
    else countChange(money - coins.head, coins) + countChange(money, coins.tail)
  }
}
