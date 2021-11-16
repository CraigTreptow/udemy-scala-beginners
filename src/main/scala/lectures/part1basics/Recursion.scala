package lectures.part1basics

import lectures.part1basics.Functions.greet

object Recursion extends App {
  def factorial(n: Int): Int =
    if (n <= 1) 1
    else {
      println(s"Computing factorial of $n - I first need factorial of ${n - 1}")
      val result = n * factorial(n - 1)
      println(s"Computed factorial of $n")

      result
    }

  // tail recursion
  def anotherFactorial(n: Int): BigInt = {
    // @tailrec
    def factHelper(x: Int, accumulator: BigInt): BigInt =
      if (x <= 1) accumulator
      else factHelper(x - 1, x * accumulator)

    factHelper(n, 1)
  }

  println(factorial(10))
  println(anotherFactorial(20000))

  // using tail recursion
  // 1. Concatenate a string n times
  // 2. IsPrime
  // 3. Fibonacci

  def concatTailRec(s: String, n: Int, acc: String): String =
    if (n < 1) acc
    else concatTailRec(s, n - 1, acc + s)

  println(concatTailRec("A", 5, ""))

  def isPrimeTailRec(n: Int): Boolean = {
    def isPrimeTailRec(t: Int, isStillPrime: Boolean): Boolean =
      if (!isStillPrime) then false
      else if (t <= 1) true
      else isPrimeTailRec(t - 1,  n % t != 0 && isStillPrime)

    isPrimeTailRec((n / 2), true)
  }

  println(isPrimeTailRec(2003))

  def fibTailRec(n: Int): Int = {
    def fiboTailRec(i: Int, last: Int, nextToLast: Int): Int =
      if (i >= n) last
      else fiboTailRec(i + 1, last + nextToLast, last)

    n match
      case 1 => 1
      case 2 => 1
      case _ => fiboTailRec(2, 1, 1)

    // if (n <= 2) 1
    // else fiboTailRec(2, 1, 1)
  }

  println("Fib: " + fibTailRec(8))
}
