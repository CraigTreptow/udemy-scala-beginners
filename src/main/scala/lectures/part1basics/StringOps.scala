package lectures.part1basics

object StringOps extends App {
  val str: String = "Hello, I am learning Scala"

  println(str.charAt(2))
  println(str.substring(7, 11))
  println(str.split(" ").toList)
  println(str.startsWith("Hello"))
  println(str.replace(" ", "-" ))
  println(str.toUpperCase())
  println(str.length)

  // Scala specific, I think
  val aNumberString = "45"
  val aNumber = aNumberString.toInt
  println('a' +: aNumberString :+ 'z')  //prepend 'a', append 'z'
  println(str.reverse)
  println(str.take(2))

  // Scala specific: String interpolators
  // S-interpotators
  val name = "David"
  val age = 12
  val greeting = s"Hello, my name is $name and I am $age years old"
  val greeting2 = s"Hello, my name is $name and I am ${age + 1} years old"
  println(greeting)
  println(greeting2)

  // F-interprolators - formatted strings in a printf like fashion
  val speed = 1.2f
  // can also check for type correctness
  // name should be a string, and speed a Float
  val myth = f"$name%s can eat $speed%2.2f burgers per minute"
  println(myth)

  // raw-interpolators
  println(raw"This is a \n newline")
  val escaped = "This is a \n newline"
  println(raw"$escaped")
}
