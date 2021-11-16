package lectures.part3fp

object MapFlatMapFilterFor extends App {
  val list = List(1, 2, 3)
  println(list)
  println(list.head)
  println(list.tail)

  println(list.map(_ + 1))
  println(list.map(_ + " is a number"))

  println(list.filter(_ % 2 == 0))

  val toPair = (x: Int) => List(x, x + 1)
  println(list.flatMap(toPair))

  // Exercise: print out all combinations between two lists
  // List("a1", "a2", ..., "d4")
  val numbers    = List(1, 2, 3, 4)
  val characters = List("a", "b", "c")
  val colors     = List("white", "black")

  // two loops => map and flatMap
  val combinations = numbers.flatMap(n => characters.map(c => "" + c + n))
  println(combinations)

  // three way loop "iterations"
  val combinations2 = numbers.flatMap(n => characters.flatMap(c => colors.map(color => "" + c + n + "-" + color)))
  println(combinations2)

  list.foreach(println)

  // for-comprehensions, written to the above by the compiler
  val forCombinations = for {
    n <- numbers if n % 2 == 0 // with a guard
    c     <- characters
    color <- colors
  } yield "" + c + n + "-" + color
  println(forCombinations)

  for {
    n <- numbers
  } println(n)

  // syntax overload
  list.map { x => x * 2 }

  // Exercises:
  // 1. Does MyList support for comprehensions? = YES
  //    Must these three to be compatible with for comprehensions
  //    map(f: A => B) => MyList[B]
  //    filter(p: A => Boolean) => MyList[A]
  //    flatMap(f: A => MyList[B] => MyList[B]
  // 2. Implement a small collection of at most ONE element - Maybe[+T]
  //    - map, flatMap, filter

}
