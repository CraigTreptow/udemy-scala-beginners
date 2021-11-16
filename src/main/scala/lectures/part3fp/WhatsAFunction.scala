package lectures.part3fp

object WhatsAFunction extends App {
  // DREAM: use functions as first class elements
  // problem: OOP

  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  println(doubler(2))

  // function types = Function1 .. Function22  up to 22 parameters
  // Function[A, B]
  val stringToIntConverter = new Function[String, Int] {
    override def apply(string: String): Int = string.toInt
  }

  println(stringToIntConverter("3") + 4)

  // val adder: ((Int, Int) => Int)
  val adder = new Function2[Int, Int, Int] {
    override def apply(a: Int, b: Int): Int = a + b
  }

  // Function types Function2[A, B, R] === (A,B) => R
  // ALL SCALA FUNCTIONS ARE OBJECTS

  // Exercises:
  // 1. a function which takes two strings and concatenates them
  // 2. in MyList, transform MyPredicate and MyTransformer into function types
  // 3. define a function which takes an Int and returns another function which takes an int and returns an int
  //    - what's the type?
  //    - how to do it?

  def ccer: (String, String) => String = new Function2[String, String, String] {
    override def apply(x: String, y: String): String = x + y
  }

  println(ccer("Hello", "Scala"))

  // Function1[Int, Function1[Int, Int]]
  def superAdder: Function1[Int, Function1[Int, Int]] = new Function1[Int, Function1[Int, Int]] {
    override def apply(x: Int): Function1[Int, Int] = new Function1[Int, Int] {
      override def apply(y: Int): Int = x + y
    }
  }

  def superAdder2: Int => Int => Int = new Function1[Int, Function1[Int, Int]] {
    override def apply(x: Int): Int => Int = new Function1[Int, Int] {
      override def apply(y: Int): Int = x + y
    }
  }

  val adder3 = superAdder(3)
  println(adder3(4))
  println(superAdder(3)(4))  // curried function

  val adder4 = superAdder2(4)
  println(adder4(4))
  println(superAdder2(4)(4))  // curried function

}

trait MyFunction[A, B] {
  def apply(element: A): B
}
