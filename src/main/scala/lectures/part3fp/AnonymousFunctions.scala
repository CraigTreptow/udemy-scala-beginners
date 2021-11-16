package lectures.part3fp

import lectures.part3fp.WhatsAFunction.superAdder

object AnonymousFunctions extends App {
  // OOP way
  // val oopDoubler = Function1[Int, Int] {
  //   override def apply(x: Int) = x * 2
  // }

  // FP - anonymous function (Lambda)
  val doubler = (x: Int) => x * 2
  val doubler2: Int => Int = x => x * 2

  // multiple params with a lambda
  val addr = (a: Int, b: Int) => a + b
  val addr2: (Int, Int) => Int = (a, b) => a + b

  // no params
  val justDoSomething: () => Int = ()  => 3

  println(justDoSomething)   // function itself
  println(justDoSomething()) // actual result of calling function

  // curly braces with lambdas
  val stringToInt = { (str: String) =>
    str.toInt
  }

  // MOAR sugar
  val niceIncrementer: Int => Int = _ + 1  // equivalent to x => x + 1
  val niceAdder: (Int, Int) => Int = _ + _ // equivalent to (a,b) => a + b

  // Exercises
  // 1. MyList: replace all FunctionX calls with lambdas
  // 2. Rewrite the "special" adder (curried) as anonymous function

  val superSpecialAdder = (x: Int) => (y: Int) => x + y

  val adder5 = superSpecialAdder(5)
  println(adder5(4))
  println(superSpecialAdder(5)(5))  // curried function
}
