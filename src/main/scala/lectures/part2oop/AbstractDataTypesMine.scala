package lectures.part2oop

object AbstractDataTypesMine extends App {
  // abstract - subclases must implement the methods
  abstract class Animal {
    val creatureType: String = "wild"
    // val eat: Unit
  }

  class Dog extends Animal {
    override val creatureType: String = "Canine"
    // override is optional
    // def eat: Unit = println("cunch crunch")
  }

  // traits
  trait Carnivore {
    def eat(animal: Animal): Unit
    val preferredMeal: String = "fresh meat"
  }

  trait ColdBlooded
  class Crocodile extends Animal with Carnivore with ColdBlooded {
    override val creatureType: String = "croc"
    // def eat: Unit = println("nom nom")
    def eat(animal: Animal): Unit = println(s"I'm a croc and I'm eating ${animal.creatureType}")
  }
  val dog = new Dog
  val croc = new Crocodile
  croc.eat(dog)

  // traits vs abstract classes
  // 1. traits cannot have constructor parameters (now available in Scala 3)
  // 2. Multiple traits may be inherited by the same class
  // 3. traits are behavior, abstract class is a type of thing
}
