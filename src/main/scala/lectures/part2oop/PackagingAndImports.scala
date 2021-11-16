package lectures.part2oop

import playground.{PrinceCharming, Cinderella as Princess}

import java.sql
import java.util.Date
import java.sql.Date

object PackagingAndImports extends App {

  // package members are accessible by their simple name
  val writer = new Writer("Craig", "Treptow", 2021)

  // import the package
  val princess = new Princess
  // val principal2 = new playground.Cinderella // fully qualified name

  // package hierarchy
  // matching folder structure (generally)

  // package object - Scala specific
  sayHello
  println(SPEED_OF_LIGHT)

  val prince = new PrinceCharming

  // val d = new sql.Date()

  // default imports - exaamples
  // java.lang - String, Object, Exception
  // scala - Int, Nothing, Function
  // scala.Predef - println, ???

}
