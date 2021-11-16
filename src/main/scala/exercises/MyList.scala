package exercises

abstract class MyList[+A] {
  // singly linked list holding integers
  //
  // head - first element of the list
  // tail - remainder of the list
  // isEmpty - is this list empty
  // add(int) - new list with this element added
  // toString - a string representation
  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyList[B]
  def printElements: String
  // override because toString exists, so we need to override it
  override def toString: String = "[" + printElements + "]"

  def map[B](transformer: A => B): MyList[B]
  def flatMap[B](transformer: A => MyList[B]): MyList[B]
  def filter(predicate: A => Boolean): MyList[A]

  def ++[B >: A](list: MyList[B]): MyList[B]

  def foreach(f: A => Unit): Unit
  def sort(comparator: (A, A) => Int): MyList[A]
  def zipWith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C]
  def fold[B](start: B)(operator: (B, A) => B): B
}

case object Empty extends MyList[Nothing] {
  def head: Nothing                            = throw new NoSuchElementException
  def tail: MyList[Nothing]                    = throw new NoSuchElementException
  def isEmpty: Boolean                         = true
  def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)
  // polymorphic call
  def printElements: String = ""

  def map[B](transformer: Nothing => B): MyList[Nothing]           = Empty
  def flatMap[B](transformer: Nothing => MyList[B]): MyList[B]     = Empty
  def filter(predicate: Nothing => Boolean): MyList[Nothing]       = Empty
  def ++[B >: Nothing](list: MyList[B]): MyList[B]                 = list
  def foreach(f: Nothing => Unit): Unit                            = ()
  def sort(comparator: (Nothing, Nothing) => Int): MyList[Nothing] = Empty
  def zipWith[B, C](list: MyList[B], zip: (Nothing, B) => C): MyList[C] =
    if (!list.isEmpty) throw new RuntimeException("Lists do not have the same length")
    else Empty
  def fold[B](start: B)(operator: (B, Nothing) => B): B = start
}

case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A                            = h
  def tail: MyList[A]                    = t
  def isEmpty: Boolean                   = false
  def add[B >: A](element: B): MyList[B] = new Cons(element, this)
  def printElements: String =
    if (t.isEmpty) "" + h.toString
    else h.toString + " " + t.printElements

  // higher-order functions
  // either receive functions as params, or return other functions as the result
  // functions are first-class values

  def map[B](transformer: A => B): MyList[B] =
    new Cons(transformer(h), t.map(transformer))

  def flatMap[B](transformer: A => MyList[B]): MyList[B] =
    transformer(h) ++ t.flatMap(transformer)

  def filter(predicate: A => Boolean): MyList[A] =
    if (predicate(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)

  def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list)

  def foreach(f: A => Unit): Unit = {
    f(h)
    t.foreach(f)
  }
  def sort(compare: (A, A) => Int): MyList[A] = {
    // insertion sort
    def insert(x: A, sortedList: MyList[A]): MyList[A] =
      if (sortedList.isEmpty) new Cons(x, Empty)
      else if (compare(x, sortedList.head) <= 0) new Cons(x, sortedList)
      else new Cons(sortedList.head, insert(x, sortedList.tail))

    val sortedTail = t.sort(compare)
    insert(h, sortedTail)
  }
  def zipWith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C] =
    if (list.isEmpty) throw new RuntimeException("Lists do not have the same length")
    else new Cons(zip(h, list.head), t.zipWith(list.tail, zip))

  def fold[B](start: B)(operator: (B, A) => B): B = {
    t.fold(operator(start, h))(operator)
  }

}

// Not needed using function types, A => B
// trait MyPredicate[-T] {
//   def test(elem: T): Boolean
// }
//
// trait MyTransformer[-A, B] {
//   def transform(elem: A): B
// }

object ListTest extends App {
  val emptyListOfIntegers: MyList[Int]   = Empty
  val emptyListOfStrings: MyList[String] = Empty
  val listOfIntegers: MyList[Int]        = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val anotherListOfIntegers: MyList[Int] = new Cons(4, new Cons(5, new Cons(6, Empty)))
  val shorterListOfIntegers: MyList[Int] = new Cons(4, new Cons(5, Empty))
  val listOfStrings: MyList[String]      = new Cons("hello", new Cons("Scala", Empty))

  println(emptyListOfIntegers)
  println(emptyListOfStrings)
  println(listOfIntegers)
  println(listOfStrings)

  val doubler = (x: Int) => x * 2

  println(listOfIntegers.map(elem => elem * 2).toString)

  println(listOfIntegers.filter(elem => elem % 2 == 0).toString)

  println(listOfIntegers ++ anotherListOfIntegers)
  println(listOfIntegers.flatMap(elem => new Cons(elem, new Cons(elem + 1, Empty))).toString)

  println("HOFs")
  listOfIntegers.foreach(println)
  println(listOfIntegers.sort((x, y) => y - x))
  println(anotherListOfIntegers.zipWith(listOfIntegers, _ + "-" + _))
  // fold is also one of the forms that reduce can have
  println(listOfIntegers.fold(0)(_ + _))

  // for comprehensions
  val combos = for {
    n      <- listOfIntegers
    string <- listOfStrings
  } yield n + "-" + string
  println(combos)
}
