package lectures.part2oop

object OOBasics extends App {
  val person = new Person("John", 26)
  println(person.age)
  println(person.x)
  person.greet("Daniel")
  person.greet()
  val person2 = new Person("Buddy")
  person2.greet()

  val author = new Writer("Charles", "Dickens", 1812)
  val imposter = new Writer("Charles", "Dickens", 1812)
  val novel = new Novel("Great Expectations", 1861, author)
  println(novel.authorAge)
  println(novel.isWrittenBy(author))
  println(novel.isWrittenBy(imposter))

  val counter = new Counter
  counter.inc.inc.inc.print

}

// class parameters are NOT FIELDS, must add val
class Person(name: String, val age: Int) {
  // body
  val x = 2
  // evaluated every time the class is instantiated
  println(1 + 4)

  def greet(name: String): Unit = println(s"${this.name} says: Hi, $name!")

  // overloading
  def greet(): Unit = println(s"Hi, I am $name")

  // multiple constructors (overloading contstuctors)
  def this(name: String) = this(name, 0)
}

// Exercises - Novel and Writer classes
class Writer(val firstName: String, val surName: String, val birthYear: Int) {
  def fullname = s"$firstName $surName"
}

class Novel(val name: String, val yearOfRelease: Int, val author: Writer) {
  def authorAge = yearOfRelease - author.birthYear

  def isWrittenBy(author: Writer) = this.author == author

  def copy(newReleaseYear: Int) = new Novel(this.name, newReleaseYear, this.author)
}
// Writer: first name, surname, year
//         - method fullname
// Novel - name, year of release, author
//         - method authorAge (at time of release)
//         - isWrittenBy(author)
//         - copy (new year of release) = new instance of Novel

class Counter(val currentCount: Int = 0) {
  // immutability - A GOOD THING
  def inc = {
    println("incrementing")
    new Counter(currentCount + 1)
  }
  def dec = new Counter(currentCount - 1)

  // def inc(amount: Int) = new Counter(currentCount + amount)
  def inc(amount: Int): Counter = {
    println("incrementing")
    if (amount <= 0) this
    else inc.inc(amount - 1)
  }

  def dec(amount: Int) = new Counter(currentCount - amount)

  def print = println(currentCount)
}

// Counter class
// - receives an int value
// - method current count
// - method to increment/decrement => new Counter
// - overload inc/dec to receive an amount

