package lectures.part4pm

import exercises._

object AllThePatterns extends App {
  // 1. constants
  val x: Any = "Scala"
  val constants = x match {
    case 1              => "a number"
    case "Scala"        => "THE Scala"
    case true           => "the truth"
    case AllThePatterns => "A singleton object"
  }

  // 2. match anything
  // 2.1 wildcard
  val matchAnything = x match {
    case _ => ""
  }

  // 2.2 variable
  val matchAVariable = x match {
    case something => s"I've found $something"
  }

  // 3. tuples
  val aTuple = (1, 2)
  val matchATuple = aTuple match {
    case (1, 1)         => ""
    case (something, 2) => s"$something"
  }

  val nestedTuple = (1, (2, 3))
  val matchANestedTuple = nestedTuple match {
    case (_, (2, v)) => ""
  }

  // 4. case classes - constructor pattern
  val aList: MyList[Int] = Cons(1, Cons(2, Empty))
  val matchAList = aList match {
    case Empty                              => ""
    case Cons(head, tail)                   => ""
    case Cons(head, Cons(subhead, subtail)) => ""
  }

  // 5. list patterns
  val aStandardList = List(1, 2, 3, 42)
  val standardListMatching = aStandardList match {
    case List(1, _, _, _)    => "extractor"
    case List(1, _*)         => "vararg pattern, list of arbitrary length"
    case 1 :: List(_)        => "infix pattern"
    case List(1, 2, 3) :+ 42 => "also infix pattern"
  }

  // 6. type specifiers
  val unknown: Any = 2
  val unknownMatch = unknown match {
    case list: List[Int] => "explicit type specifier"
    case _               => ""
  }

  // 7. name binding
  val nameBindingMatch = aList match {
    case nonEmptyList @ Cons(_, _)  => "name binding => use the name later in the return expression"
    case Cons(1, rest @ Cons(2, _)) => "name binding inside nested patterns"
  }

  // 8 multi-patterns
  val multiPattern = aList match {
    case Empty | Cons(0, _) => "compound, or multi pattern - chain as many as you want"
    case _                  => ""
  }

  // 9 if guards
  val secondElementSpecial = aList match {
    case Cons(_, Cons(specialElement, _)) if specialElement % 2 == 0 => "use a predicate to filter"
  }

  // Question
  val numbers = List(1, 2, 3)
  val numbersMatch = numbers match {
    case listOfStrings: List[String] => "a list of strings"
    case listOfNumbers: List[Int]    => "a list of numbers"
    case _                           => ""
  }

  println(numbersMatch) // my answer is it should be "a list of numbers" since it is
  // actually get "a list of strings"
  // JVM was designed for Java, which is designed for backwards compatibility
  // JVM is oblivious to generic types, after type checking, the types are removed
  // so the first match is matched, which returns the strings answer

}
