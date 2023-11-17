package execises

/**
 * @author Stefano Mazzuka on 06/11/2023
 */
abstract class MyList[+A] {
  /*
    methods:
    - head(): return first element of the list
    - tail(): return remainder of the list
    - isEmpty(): return is this list empty
    - add(int): return new list with this element added
    - toString(): return a string representation of the list
   */

  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyList[B]
  def printElements: String
  // Polymorphic call
  override def toString: String = s"[$printElements]"
}

object Empty extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)
  override def printElements: String = ""
}

class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h
  def tail: MyList[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](element: B): MyList[B] = new Cons(element, this)
  override def printElements: String =
    if (t.isEmpty) "" + h else "" + h + " " + t.printElements
}

object ListTest extends App {
//  val list_0 = new Cons(1, new Cons(2, new Cons(3, Empty)))
//  println(list_0.head)
//  println(list_0.tail.head)
//  println(list_0.add(4).head)
//  val list_1 = list_0.add(4).add(5).add(6)
//  println(list_1.printElements)

  val listOfIntegers: MyList[Int] = new Cons[Int](1, new Cons[Int](2, Empty))
  val listOfString: MyList[String] = new Cons[String]("Hello", new Cons[String]("Scala", Empty))

  println(listOfIntegers.toString)
  println(listOfString.toString)
}