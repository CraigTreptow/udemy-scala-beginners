package lectures.part1basics

object DefaultArgs extends App {
  def trFact(n: Int, acc: Int = 1): Int =
    if (n <= 1) acc
    else trFact(n - 1, n * acc)

  // val fact10 = trFact(10, 1)
  val fact10 = trFact(10)
  val fact20 = trFact(n = 20)
  println(fact10)
  println(fact20)
}
