package lectures.part2oop

object Exceptions extends App {
  val x: String = null
  // crashes with null pointer exception
  // println(x.length)

  // 1. throwing and catching exceptions
  // val aWeirdValue = throw new NullPointerException
  // val aWeirdValue2: String = throw new NullPointerException

  // throwable classes extend the Throwable class
  // Exception and Error are the major Throwable subtypes

  // 2. how to catch exceptions

  def getInt(withException: Boolean): Int =
    if (withException) throw new RuntimeException("No Int for you!")
    else 42

  val potentialFail = try {
    // code that might fail
    getInt(true)
  } catch {
    case e: RuntimeException => 43 // println("caught a Runtime exception")
  } finally {
    // code that will always execute
    // optional
    // does not influence the return type of this expression
    // use finally, only for side effects (e.g. logging to a file)
    println("finally")
  }

  println(potentialFail)

  // 3. define our own exceptions
  class MyException extends Exception
  val exception = new MyException

  // throw exception

  // Exercises:
  // 1. crash with an OutOfMemoryError
  // 2. crash with a StackOverflowError
  // 3. define PocketCalculator
  //    - add(x,y)
  //    - subtract, multiply, and divide
  //    throw:
  //    - OverflowException if add exceeds Int.MAX_VALUE
  //    - UnderflowException if subtract exceeds Int.MIN_VALUE
  //    - MathCalculationException for division by 0

  // 1.
  // val array = Array.ofDim(Int.MaxValue)

  // 2.
  def doit(x: Int): Int = 1 + doit(x + 1)
  // doit(1)

  class OverflowException extends Exception
  class UnderflowException extends Exception
  class MathCalculationException extends Exception

  object PocketCalculator {
    def add(x: Int, y: Int) =
      val result = x + y
      println(result)
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result > 0) throw new UnderflowException
      else result

    def subtract(x: Int, y: Int) =
      val result = x - y
      if (x > 0 && y < 0 && result < 0) throw new OverflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result

    def multiply(x: Int, y: Int) =
      val result = x * y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result < 0) throw new OverflowException
      else if (x > 0 && y < 0 && result > 0) throw new UnderflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result

    def divide(x: Int, y: Int) =
      if (y == 0) throw new MathCalculationException
      else x / y
  }

  // println(PocketCalculator.add(Int.MaxValue, 10))
  // println(PocketCalculator.divide(2, 0))

}
