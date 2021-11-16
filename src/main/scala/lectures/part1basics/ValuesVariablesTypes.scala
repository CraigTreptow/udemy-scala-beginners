package lectures.part1basics

object ValuesVariablesTypes extends App {
  val x:Int = 42
  println(x)

  val aBool: Boolean = false
  val aChar: Char = 'c'
  val aShort: Short = 1234               // 2 bytes, rather than 4
  val aInt: Int = 543321                 // 4 bytes
  val aLong: Long = 1234567894533353994L // 8 bytes, rather than 4
  val aFloat: Float = 2.0f
  val aDouble: Double = 3.14

  // variables
  var aVariable: Int = 4
  aVariable = 5           // side effects
}
