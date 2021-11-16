package lectures.part3fp

import scala.util.Random

object Sequences extends App {
  val aSequence = Seq(1, 3, 2, 4)
  println(aSequence)
  println(aSequence.reverse)
  println(aSequence(2)) // 0 indexed
  println(aSequence ++ Seq(7, 5, 6))
  println(aSequence.sorted)

  // ranges
  val aRange: Seq[Int] = 1 to 3
  aRange.foreach(println)

  (1 to 4).foreach(x => println("hello"))

  // lists
  val aList      = List(1, 2, 3)
  val prepended  = 42 :: aList       // or +:
  val prepended2 = 42 +: aList :+ 89 // the : is always on the side of the list
  println(prepended)
  println(prepended2)

  val apples5 = List.fill(5)("apple")
  println(apples5)
  println(aList.mkString("-|-"))

  // arrays
  val numbers       = Array(1, 2, 3, 4)
  val threeElements = Array.ofDim[Int](3)
  println(threeElements)
  threeElements.foreach(println)

  // mutation
  numbers(2) = 0 // sugar for numbers.update(2, 0)
  println(numbers.mkString(" "))

  // arrays and seq
  val numbersSeq: Seq[Int] = numbers // implicit conversions
  println(numbersSeq)

  // vectors
  val vector: Vector[Int] = Vector(1, 2, 3)
  println(vector)

  // vectors vs lists

  val maxRuns     = 1000
  val maxCapacity = 1000000
  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      collection.updated(r.nextInt(maxCapacity), r.nextInt())
      System.nanoTime() - currentTime
    }

    // average time to update at random index with random value
    times.sum * 1.0 / maxRuns
  }

  val numbersList   = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector

  // adv: efficient for updating head
  // adv: keeps reference to tail
  // dis: updating element in the middle takes a long time
  println(getWriteTime(numbersList) + " nanoseconds")

  // adv: depth of tree is small
  // dis: needs to replace an entire 32-element chunk
  println(getWriteTime(numbersVector) + " nanoseconds")

}
