package lectures.part3fp

import scala.annotation.tailrec

object TuplesAndMaps extends App {
  // tuples - finite ordered "lists"
  // max elements are 22
  val aTuple  = new Tuple2(2, "hello scala") // Tuple2[Int, String] = (Int, String)
  val aTuple2 = (2, "hello scala")           // new is not required, nor is Tuple2
  println(aTuple._1)
  println(aTuple.copy(_2 = "goodbye Java"))
  println(aTuple.swap)

  // Maps - associate keys to values
  val aMap: Map[String, Int] = Map()

  val phonebook = Map(("Jim", 555), "Danial" -> 789).withDefaultValue(-1)
  // a -> b is sugar for (a, b)
  println(phonebook)

  // map operations
  println(phonebook.contains("Jim"))
  println(phonebook("Jim"))
  println(phonebook("Sally")) // throws NoSuchElement if key is missing, add the default like above

  // add a pairing
  val newPairing   = "Mary" -> 678
  val newPhoneBook = phonebook + newPairing
  println(newPhoneBook)

  // functions on maps
  println(phonebook.map(pair => pair._1.toLowerCase -> pair._2))

  // filterKeys
  println(phonebook.view.filterKeys(x => x.startsWith("J")).toMap)

  // mapValues
  println(phonebook.view.mapValues(number => number * 10).toMap)

  // conversions to other collections
  println(phonebook.toList)
  println(List(("Danny", 555)).toMap)

  // groupBy used with Spark and data scientists
  val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
  println(names.groupBy(name => name.charAt(0)))

  // Exercises:
  // 1. What would happen if I had two original entries "Jim" -> 555 and "JIM" -> 999? and ran the following
  //    println(phonebook.map(pair => pair._1.toLowerCase -> pair._2))
  // 2. Social network
  //    Person = String
  //    - add a person to the network
  //    - remove
  //    - friends (mutual)
  //    - unfriend
  //    - number of friends of a person
  //    - person with most friends
  //    - how many people have NO friends
  //    - if there is a social conenction between two people (direct or not)

  // 1. you lose one of them
  val t1 = "Jim" -> 111
  val t2 = "JIM" -> 222
  val pb = Map(t1, t2)
  println(pb.map(pair => pair._1.toLowerCase -> pair._2))

  // 2.
  def add(network: Map[String, Set[String]], person: String): Map[String, Set[String]] =
    network + (person -> Set())

  def friend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] =
    val friendsA = network(a)
    val friendsB = network(b)

    network + (a -> (friendsA + b)) + (b -> (friendsB + a))

  def unfriend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] =
    val friendsA = network(a)
    val friendsB = network(b)

    network + (a -> (friendsA - b)) + (b -> (friendsB - a))

  def remove(network: Map[String, Set[String]], person: String): Map[String, Set[String]] =
    def removeAux(friends: Set[String], networkAcc: Map[String, Set[String]]): Map[String, Set[String]] =
      if (friends.isEmpty) networkAcc
      else removeAux(friends.tail, unfriend(networkAcc, person, friends.head))

    val unfriended = removeAux(network(person), network)
    unfriended - person

  val empty: Map[String, Set[String]] = Map()
  val network                         = add(add(empty, "Bob"), "Mary")
  println(network)
  println(friend(network, "Bob", "Mary"))
  println(unfriend(friend(network, "Bob", "Mary"), "Bob", "Mary"))
  println(remove(friend(network, "Bob", "Mary"), "Bob"))

  // Jim, Bob, Mary
  val people  = add(add(add(empty, "Bob"), "Mary"), "Jim")
  val jimBob  = friend(people, "Bob", "Jim")
  val testNet = friend(jimBob, "Bob", "Mary")
  println(testNet)

  def nFriends(network: Map[String, Set[String]], person: String): Int =
    if (!network.contains(person)) 0
    else network(person).size

  println(nFriends(testNet, "Bob"))

  def mostFriends(network: Map[String, Set[String]]): String =
    network.maxBy(pair => pair._2.size)._1

  println(mostFriends((testNet)))

  def nPeopleWithNoFriends(network: Map[String, Set[String]]): Int =
    // network.filterKeys(k => network(k).isEmpty).size
    network.count(_._2.isEmpty)

  println(nPeopleWithNoFriends((testNet)))

  def socialConnection(network: Map[String, Set[String]], a: String, b: String): Boolean =
    // bread first search of graph
    @tailrec
    def bfs(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean =
      if (discoveredPeople.isEmpty) false
      else {
        val person = discoveredPeople.head
        if (person == target) true
        else if (consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
        else bfs(target, consideredPeople + person, discoveredPeople.tail ++ network(person))
      }

    bfs(b, Set(), network(a) + a)

  println(socialConnection(testNet, "Mary", "Jim"))
  println(socialConnection(network, "Mary", "Jim"))
}
