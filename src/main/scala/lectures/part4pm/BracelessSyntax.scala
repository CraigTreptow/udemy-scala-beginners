package lectures.part4pm

object BracelessSyntax extends App {
  // if expressions
  val onIfExpression = if (2 > 3) "bigger" else "smaller"

  // java style
  val onIfExpression2 =
    if (2 > 3) {
      "bigger"
    } else {
      "smaller"
    }

  // compact
  val onIfExpression3 =
    if (2 > 3) "bigger"
    else "smaller"

  // Scala 3 allows (exclusive)
  val onIfExpression4 =
    if 2 > 3 then "bigger"
    else "smaller"

  // can be a bit more complex
  val onIfExpression5 =
    if 2 > 3 then
      val result = "bigger"
      result
    else
      val result = "smaller"
      result

  println(onIfExpression5)

  // val onIfExpression6 = if 2 > 3 "bigger" else "smaller"

  // for comprehensions
  val aForComprehension = for {
    n <- List(1, 2, 3)
    s <- List("black", "white")
  } yield s"$n$s"

  // Scala 3
  val aForComprehension2 =
    for
      n <- List(1, 2, 3)
      s <- List("black", "white")
    yield s"$n$s"

  // pattern matching
  val meaningOfLife = 42
  val aPatternMatch = meaningOfLife match {
    case 1 => "the one"
    case 2 => "double"
    case _ => "something else"
  }

  // Scala 3
  val aPatternMatch2 = meaningOfLife match
    case 1 => "the one"
    case 2 => "double"
    case _ => "something else"

  // methods without braces
  def conputeMeaninfOfLife(arg: Int): Int = {
    val partialResult = 40

    partialResult + 2
  }

  // Scala 3
  def conputeMeaninfOfLife2(arg: Int): Int =
    val partialResult = 40

    partialResult + 2

  // class, traits, objects, Enums, etc.
  class Animal {
    def eat(): Unit = println("I'm eating")
  }

  class Animal2: // compiler expects body of Animal2 now
    def eat(): Unit = println("I'm eating")

    def grow(): Unit =
      println("I'm growing")
    // many more lines
    end grow

  // many more lines

  end Animal2 // works with for , if, match, methods, classes, traits, enums, objects

  // anonymous classes
  val aSpecialAnimal = new Animal:
    override def eat(): Unit = println("I'm special")

  // indentation = strictly larger indentation
  // 3 spaces + 2 tabs > 2 spaces + 2 tabs
  // 3 spaces + 2 tabs > 3 spaces + 1 tab
  // 3 tabs + 2 spaces ??? 2 tabs + 3 spaces
  // DO NOT MIX SPACES AND TABS
  // USE SPACES!! LOL

}
