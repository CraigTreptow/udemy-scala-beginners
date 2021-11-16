package lectures.part3fp

object Options extends App {
  val myFirstOption: Option[Int] = Some(4)
  val noOption: Option[Int]      = None
  println(myFirstOption)
  println(noOption)

  // unsafe APIs
  def unsafeMethod(): String = null
  // val result = Some(unsafeMethod()) // WRONG - might get null
  val result = Option(unsafeMethod()) // Some or None
  println(result)

  // chained methods
  def backupMethod(): String = "a valid result"
  val chainedResult          = Option(unsafeMethod()).orElse(Option(backupMethod()))

  // DESIGN unsafe APIs
  def betterUnsafeMethod(): Option[String] = None
  def betterBackupMethod(): Option[String] = Some("a valid result")
  val betterChainedResult                  = betterUnsafeMethod() orElse betterBackupMethod()

  // functions on Options
  println(myFirstOption.isEmpty)
  println(myFirstOption.get) // unsafe - do not use this

  // map, flatMap, filter
  println(myFirstOption.map(_ * 2))
  println(myFirstOption.filter(x => x > 10))
  println(myFirstOption.flatMap(x => Option(x * 10)))

  // for-comprehensions
  // Exercise
  val config: Map[String, String] = Map(
    // fetched from elsewhere
    "host" -> "176.167.42.42",
    "port" -> "80"
  )

  class Connection {
    def connect = "Connect" // connect to some server
  }
  object Connection {
    val random = scala.util.Random(System.nanoTime())
    def apply(host: String, port: String): Option[Connection] =
      if (random.nextBoolean()) Some(new Connection)
      else None
  }

  // try to establish a connection, if so - print the connect method
  val host = config.get("host")
  val port = config.get("port")

  /*
    if (h != null)
      if (p != null)
        return Connection.apply(h, p)
    return null
   */
  val connection = host.flatMap(h => port.flatMap(p => Connection.apply(h, p)))

  /*
    if (c != null)
      return c.connect
    return null
   */
  val connectionStatus = connection.map(c => c.connect)

  // if (connectionStatus == null println(None) else print (Some(connectionStatus.get))
  println("Connections")
  println(connectionStatus)

  /*
    if (status != null)
      println(status)
   */
  connectionStatus.foreach(println)

  // chained solution
  config
    .get("host")
    .flatMap(host =>
      config
        .get("port")
        .flatMap(port => Connection(host, port))
        .map(connection => connection.connect)
    )
    .foreach(println)

  // for-comprehensions (most readable)
  // if any fail, you get None, otherwise Some()
  val forConnectionStatus = for {
    host       <- config.get("host")
    port       <- config.get("port")
    connection <- Connection(host, port)
  } yield connection.connect

  forConnectionStatus.foreach(println)
}
