package lectures.part2oop

object Objects extends App {
  // Scala does not have class level functionality ("static")
  object Person {  // type + its only instance
    // "static"/"class" level functionality
    val N_EYES = 2
    def canFly: Boolean = false

    // factory method
    def apply(mother: Person, father: Person): Person = new Person("Bobby")
  }
  class Person(val name: String) {
    // instance level functionality
  }
  // companions - same names and same scope

  println(Person.N_EYES)
  println(Person.canFly)

  // Scala object = singleton instance
  val mary = Person
  val john = Person
  println(mary == john)

  val maxie = new Person("maxie")
  val jamie = new Person("jamie")
  println(maxie == jamie)

  // val bobbie = Person.apply(mary, john)
  // val bobbie = Person(mary, john)

  // Scala applications = Scala object with
  // def main(args: Array[String]): Unit
  // this is required since it is turned into JVM apps whose entry point is public static void main with array of string
  // this is what "extends App" gives us



}
