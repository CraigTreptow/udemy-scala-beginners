package lectures.part2oop

object Generics extends App {
  class MyList[A] {
    // use the type A
    // traits can also be type parameterized
  }

  class MyList2[+A] {
    // Covariant
    def add[B >: A](element: B): MyList2[B] = this
    // A = Cat
    // B = Dog = Animal
  }

  // two generic types
  class MyMap[Key, Value]

  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]

  // generic method
  object MyList {
    def empty[A]: MyList[A] = MyList[A]
  }
  val emptyListOfIntegers = MyList.empty[Int]

  // variance problem
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // Does a list of cats also extend Animal?
  // 1. yes List[Cat] extends List[Animal] = Covariance
  class CovariantList[+A]
  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
  // animalList.add(new Dog) ?? this would pollute the list of Cats.  HARD QUESTION
  // above would turn it into a list of Animals  => we return a list of Animals

  // 2. no = Invariance
  class InvariantList[A]
  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal]

  // 3. Hell, no - Contravariance (opposite to Covariance)
  class ContravariantList[-A]
  // doesn't really make sense
  val contravariantList: ContravariantList[Cat] = new ContravariantList[Animal]

  class Trainer[-A]
  // this works fine
  val trainer: Trainer[Cat] = new Trainer[Animal]

  // bounded types
  // Cage only accepts subtypes of Animal
  // Upper bounded type
  // class Cage[A <: Animal](animal: A)
  // val cage = new Cage(new Dog)

  // Lower bounded type
  // Cage only accepts something that is a super type of Animal
  class Cage[A >: Animal](animal: A)

  class Car
  val newCage = new Cage(new Car)

  // Exercis: expand MyList to be generic

}
