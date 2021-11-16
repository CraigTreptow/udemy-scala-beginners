package lectures.part2oop

object Inheritance extends App {
  class Animal {
    private def nom = println("nom nom") // this class only
    protected def eat = println("eat eat")  // this class and sub-classes only
    val creatureType = "wild"
  }

  // single class inheritance
  class Cat extends Animal {
    def crunch = {
      eat
      println("crunch crunch")
    }
  }

  val scar = new Cat
  // scar.eat
  // scar.nom
  scar.crunch

  // constructors
  class Person(name: String, age: Int)
  // the JVM calls the parent constructor first, but won't find one with
  // a signature like this, so we need to pass in the arguments to satisfy
  // the Person constructor
  class Adult(name: String, age: Int, idCard: String) extends Person(name, age)

  // overriding
  class Dog(override val creatureType: String) extends Animal {
    override def eat: Unit = println("doggy crunch")
    // override val creatureType: String = "domesticated"
  }
  val d = new Dog("spot")
  d.eat
  println(d.creatureType)  // doggy crunch, not wild

  // type substitution (broad: polymorphism)
  val unknownAnimal: Animal = new Dog("K9")
  // unknownAnimal.eat

  // overriding => a different implementation in derived classes
  // overloading => multiple methods with different signatures and same name in same class

  // super

  // preventing overrides
  // 1. use final on member => prevents derived classes from overriding a method
  // 2. use final on class => prevents class from being extended
  // 3. seal the class (sealed) => can extend class in the same file ONLY, prevents extension in other files
}
