package lectures.part4pm

object PatternsEverywhere extends App {
  // big idea #1
  try {
    // code
  } catch {
    case e: RuntimeException       => "exception"
    case npe: NullPointerException => "npe"
    case _                         => "something else"
  }

  // catches are actually MATCHES

  // big idea #2
  val list = List(1, 2, 3, 4)
  val evenOnes = for {
    x <- list if x % 2 == 0 // ?!
  } yield 10 * x

  // generators are also based on pattern matching!
  val tuples = List((1, 2), (3, 4))
  val filterTuples = for {
    (first, second) <- tuples
  } yield first * second
  // case classes, :: operators, ...

  // big idea #3
  val tuple     = (1, 2, 3)
  val (a, b, c) = tuple // values are assigned
  println(b)

  val head :: tail = list
  println(head)
  println(tail)

  // big idea #4
  // partial function based on pattern matching

  // partial function literal
  val mappedList = list.map {
    case v if v % 2 == 0 => v + " is even"
    case 1 => "the one"
    case _ => "something else"
  }

  // EQUIVALENT
  val mappedList2 = list.map { x =>
    x match {
      case v if v % 2 == 0 => v + " is even"
      case 1 => "the one"
      case _ => "something else"
    }
  }

  println(mappedList)

}
