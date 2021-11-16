package lectures.part2oop

object CaseClasses extends App {
 // avoid reimplementing equals, hashCode, toString, copy, etc.
 // perfect for lightweight data-holding classes

 case class Person(name: String, age: Int)
   // Case classes give you:
   // 1. class parameters are promoted to fields
   // 2. sensible toString
   // 2a. println an instance is actually println instance.toString
   // 3. equals and hashCode are implemented out of the box
   // 4. have handy copy method
   // 5. have companion objects
   // companion objects have apply methods
   // val mary = Person("Mary", 23) - uses apply method
   // 6. are serializable (useful for Akka)
   // 7. have extractor patterns
   // can be used in pattern matching

  case object UnitedKingdom {
    // they are their own companion object
    def name: String = "The UK of GB and NI"
  }

  // Exercise - use case classes and case objects where you see fit in MyList.

}
