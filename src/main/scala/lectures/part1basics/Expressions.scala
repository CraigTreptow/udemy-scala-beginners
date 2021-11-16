package lectures.part1basics

object Expressions extends App {
  val x = 1 + 2 // expression
  println(x)
  println(1 == x)
  println(!(1 == x))
  var aVariable = 1

  // Instructions (DO) vs Expressions (VALUE)
  // IF expression
  val aCondition = 1 == 1
  val aConditionValue = if aCondition then 5 else 3 // if expression
  println(aConditionValue)
  println(if aCondition then 5 else 3)

  // DO NOT DO THIS!
  var i = 0
  while (i < 10) {
    println(i)
    i+= 1
  }

  val aWeirdValue = (aVariable = 3) // Unit == void
  println(aWeirdValue)

  val aCodeBlock = {
    val y = 2
    val z = y + 1 // only visible inside code block

    if (z > 2) "hello" else "goodbye"
  }
  println(aCodeBlock)

  // Questions:
  // 1. difference between "hello world" and println("hello world")
  //    A: The first is the actual value, the second is a side affect
  // 2.
  val someValue = { 2 < 3}
  println(someValue == true) // true

  // 3.
  val someOtherValue = { if someValue then 239 else 986; 42}
  println(someOtherValue == 42)
}
