package lectures.part4pm

import scala.util.Random

object PatternMatching extends App {
  // swith on steroids
  val random = new Random
  val x      = random.nextInt(10)

  val description = x match
    case 1 => "the ONE"
    case 2 => "double or nothing"
    case 3 => "third time is a charm"
    case _ => "wild card"

  println(x)
  println(description)

  //  1. decompose values
  case class Person(name: String, age: Int)
  val bob = Person("bob", 20)
  val greeting = bob match
    case Person(n, a) if a < 21 => s"Hey, my name is $n and I am youngin'" // guard
    case Person(n, a)           => s"Hey, my name is $n and I am $a years old"
    case _                      => "I have no idea who I am"

  println(greeting)

  // cases are matched in order and the first match is used
  // always have a wild card at the end
  // what is the type?
  // if all the same type, then that is it, if not, the compiler uses the lowest common type (might be Any)
  // PM works well with case classes (not normal classes)

  // PM on sealed hierarchies
  sealed class Animal
  case class Dog(breed: String)       extends Animal
  case class Parrot(greeting: String) extends Animal

  val animal: Animal = Dog("malamute")
  animal match {
    case Dog(someBreed) => println(s"Match a dog of $someBreed")
  }

  // match everything (this is overkill, don't do this)
  val isEven = x match
    case n if n % 2 == 0 => true
    case _ => false

  val isEvenCond   = if (x % 2 == 0) true else false // NO!!!
  val isEvenNormal = x % 2 == 0 // YES

  // Exercise
  // simple funtions uses PM
  // takes Expr => human readable form
  // Sum(Number(2), Number(3)) => 2 + 3
  // Sum(Number(2), Number(3), Number(4)) => 2 + 3 + 4
  // Prod(Sum(Number(2), Number(1)), Number(3)) = (2 + 1) * 3
  // Sum(Prod(Number(2), Number(1)), Number(3)) = 2 * 1 + 3

  trait Expr
  case class Number(n: Int)           extends Expr
  case class Sum(e1: Expr, e2: Expr)  extends Expr
  case class Prod(e1: Expr, e2: Expr) extends Expr

  def toHuman(e: Expr): String =
    e match {
      case Number(n)   => s"$n"
      case Sum(e1, e2) => toHuman(e1) + " + " + toHuman(e2)
      case Prod(e1, e2) => {
        def maybeShowParentheses(e: Expr) = e match {
          case Prod(_, _) => toHuman(e)
          case Number(_)  => toHuman(e)
          case _          => "(" + toHuman(e) + ")"
        }

        maybeShowParentheses(e1) + " * " + maybeShowParentheses(e2)
      }
    }

  println(toHuman(Sum(Number(2), Number(3))))
  println(toHuman(Sum(Sum(Number(2), Number(3)), Number(4))))
  println(toHuman(Prod(Sum(Number(2), Number(1)), Number(3))))
  println(toHuman(Prod(Sum(Number(2), Number(1)), Number(3))))
  println(toHuman(Sum(Prod(Number(2), Number(1)), Number(3))))

}
