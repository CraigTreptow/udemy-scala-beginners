package lectures.part2oop

object AnonymousClasses extends App {
  trait Animal {
    def eat: Unit
  }

  // anonymous class
  val funnyAnimal = new Animal {
    override def eat: Unit = println("ha ha ha")
  }

  println(funnyAnimal.getClass)
  println(funnyAnimal.eat)

  class Person(name: String) {
    def sayHi: Unit = println(s"Hi, my name is $name")
  }

  val jim = new Person("Jim") {
    override def sayHi: Unit = println("Jim says hi!")
  }

  println(jim.sayHi)

  // must pass in required arguments
  // must implement all agstract fields/methods
  // works for traits and classes (abstract or not)

  // Exercises - expand MyList
  // 1. Generic trait MyPredicate[-T]
  //    - test(T) => Boolean
  // 2. Generic trait MyTransformer[-A, B]
  //    - transform(A) => B
  // 3. MyList functions:
  //    - map(transformer) => Mylist
  //      - [1,2,3].map(n * 2) = [2,4,6]
  //    - filter(predicate) => MyList
  //      - [1,2,3,4].filter(n % 2) = [2,4]
  //    - flatMap(transformer from A to MyList[B]) => MyList[B]
  //      - [1,2,3].flatMap(n => [n, n+1] => [1,2,2,3,3,4]

}
