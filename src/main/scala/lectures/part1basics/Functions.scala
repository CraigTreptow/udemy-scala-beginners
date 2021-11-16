package lectures.part1basics

object Functions extends App {
  def aFunction(a: String, b: Int): String = a + " " + b

  println(aFunction("1", 5))

  def aRepeatedFunction(aString: String, n: Int): String =
    if (n == 1) aString
    else aString + aRepeatedFunction(aString, n - 1)

  println(aRepeatedFunction("hello", 5))

  def aBigFunction(n: Int): Int = {
    def aSmallFunction(a: Int, b: Int): Int = a + b

    aSmallFunction(n, n - 1) // returned expression of aBigFunction
  }

  // Exercises
  // 1. greeting
  def greet(name: String, age: Int): String = s"Hi, my name is $name, and I am $age years old."
  println(greet("Craig", 51))

  // 2. Factorial => 1 * 2 * 3 * ... * n
  def fac(n: Int): Int = if n == 0 then 1 else n * fac(n - 1)

  println("fac: " + fac(5))

  // 3. Fibonacci
  def fib(n: Int): Int =
    n match
      case 1 => 1
      case 2 => 1
      case _ => fib(n - 1) + fib(n - 2)

  println("Fib: " + fib(8))

  // 4. Tests if number is prime.
  def isPrime(n: Int):Boolean = {
    def isPrimeUntil(t: Int): Boolean =
      if (t <= 1) then true
      else n % t != 0 && isPrimeUntil(t - 1)

    isPrimeUntil((n / 2))
  }

  println(isPrime(16))
}
