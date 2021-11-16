package lectures.part2oop

object MethodNotations extends App {

  class Person(val name: String, favoriteMovie: String, val age: Int = 0) {
    def likes(movie: String):Boolean = movie == favoriteMovie
    def hangOutWith(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def +(nickname: String): Person = new Person(s"${this.name} ($nickname)", favoriteMovie)
    def unary_! :String = s"$name, what the heck!!"
    def unary_+ :Person = new Person(name, favoriteMovie, age + 1)
    def isAlive: Boolean = true
    def learnScala: String = this.learns("Scala")
    def learns(subject: String): String = s"${this.name} learns $subject"
    def apply(): String = s"Hi, my name is $name and I like $favoriteMovie"
    def apply(count: Int): String = s"${this.name} watched $favoriteMovie $count times."
  }

  val mary = new Person("Mary", "Inception")
  println(mary.likes("Inception"))
  println(mary likes "Inception")  // equivalent to the above
  // above is infix notation - operator notation (syntatic sugar example)
  // only works when method has a single parameter

  // "operators" in Scala
  val tom = new Person("Tom", "Fight Club")
  println(mary hangOutWith tom)
  println(mary + tom)

  // all operators are methods
  println(1 + 2)
  println(1.+(2))

  // prefix notation
  val x = -1
  val y = 1.unary_-
  // unary_ prefix only works with - + ~ !

  println(!mary)
  println(mary.unary_!)

  // postfix notation - function cannot take a parameter
  // postfix operator `isAlive` needs to be enabled
  // by making the implicit value scala.language.postfixOps visible.
  // println(mary.isAlive)
  // println(mary isAlive)

  // apply
  println(mary.apply())
  println(mary())

  // Exercises:
  // 1. overload the + operator which receives a string and returns a new person with a nickname
  //  mary + "the rockstar" => new person "Mary (the rockstar)"
  println((tom + "the hippie").name)
  // 2. Add an age to the Person class
  //    addd a unary + operator => new person with age incremented
  println((+tom).age)
  // 3. Add "learns" method to Person class => "Mary learns Scala"  (receives string param)
  //    Add learnScala method (postfix), calls the above method
  println(mary.learns("Scala"))
  println(mary.learnScala)
  // 4. Overload the apply method
  //  mary.apply(2) => "Mary watched Inception 2 times
  println(mary.apply(2))
}
