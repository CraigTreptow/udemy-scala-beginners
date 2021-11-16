package lectures.part3fp

import scala.util.{Failure, Random, Success, Try}

object HandlingFailure extends App {
  // create success and failure
  val aSuccess = Success(3)
  val aFailure = Failure(new RuntimeException("Super bad failure"))

  println(aSuccess)
  println(aFailure)

  def unsafeMethod(): String = throw new RuntimeException("no string for you")

  // Try objects via the apply method
  val potentialFailure = Try(unsafeMethod())
  println(potentialFailure)

  // sugar
  val anotherPotentialFailure = Try {
    // code that might throw
    unsafeMethod()
  }

  // utilities
  println(potentialFailure.isSuccess)

  def backupMethod(): String = "A valid result"
  val fallbackTry            = Try(unsafeMethod()).orElse((Try(backupMethod())))
  println(fallbackTry)

  // if you design the API, wrap it in a Try
  def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException)
  def betterBackupMethod(): Try[String] = Success("got the reslt")
  val betterFallback                    = betterUnsafeMethod().orElse(betterBackupMethod())
  println(betterFallback)

  // map, flatMap, filter
  println(aSuccess.map(_ * 2))
  println(aSuccess.flatMap((x => Success(x * 10))))
  println(aSuccess.filter(_ > 10))

  // Exercise
  val hostname                 = "localhost"
  val port                     = "8080"
  def renderHTML(page: String) = println(page)

  class Connection {
    def get(url: String): String = {
      val random = new Random(System.nanoTime())
      if (random.nextBoolean()) "<html>...</html>"
      else throw new RuntimeException("Connection interrupted")
    }

    def getSafe(url: String): Try[String] = Try(get(url))
  }

  object HttpService {
    val random = new Random(System.nanoTime())
    def getConnection(host: String, port: String): Connection = {
      if (random.nextBoolean()) new Connection
      else throw new RuntimeException("port taken")
    }

    def getSafeConnection(host: String, port: String): Try[Connection] = Try(getConnection(host, port))
  }

  // if you get the html page from the connection, print it to the console.  e.g. call renderHTML

  val possibleConnection = HttpService.getSafeConnection(hostname, port)
  val possibleHTML       = possibleConnection.flatMap(connection => connection.getSafe("/home"))
  possibleHTML.foreach(renderHTML)

  // shorthand
  HttpService
    .getSafeConnection(hostname, port)
    .flatMap(connection => connection.getSafe("/users"))
    .foreach(renderHTML)

  // for-comprehension
  for {
    connection <- HttpService.getSafeConnection(hostname, port)
    html       <- connection.getSafe("/admin")
  } renderHTML(html)

  // imperitive
  /*
  try {
    connection = ....
    try {
      page = ....
      renderHTML(page)
    } catch (some other exception)
  } catch exception {
    // handle
  }
   */

  // Try is a functional way of dealing with failure
  // map, flatMap, filter
  // orElse
  // others: fold, collect, toList, conversion to Options
  // if you design a method to return some thype, but may throw an exception,
  // return a Try[that type] instead
}
