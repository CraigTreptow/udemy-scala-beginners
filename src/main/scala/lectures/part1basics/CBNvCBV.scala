package lectures.part1basics

object CBNvCBV extends App {
  // value is computed before this function is evaluated
  def calledByValue(x: Long): Unit =
    println(s"by value: $x")
    println(s"by value: $x")

  // => is the magic, delays evaluation of argument until it is required (lazy)
  // value is the *expression* as-is
  // it is evaluated everytime it is referenced in the function
  def calledByName(x: => Long): Unit =
    println(s"by name: $x")
    println(s"by name: $x")

  calledByValue(System.nanoTime())
  calledByName(System.nanoTime())

  def infinite(): Int = 1 + infinite()
  def printFirst(x: Int, y: => Int) = println(x)

  // printFirst(infinite(), 34) // stack overflow error
  printFirst(34, infinite())    // no stack overflow
}
