package execises

/**
 * @author Stefano Mazzuka on 06/11/2023
 */
abstract class MyListAF[+A] {
  /*
    methods:
    - head(): return first element of the list
    - tail(): return remainder of the list
    - isEmpty(): return is this list empty
    - add(int): return new list with this element added
    - toString(): return a string representation of the list
   */

  def head: A
  def tail: MyListAF[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyListAF[B]
  def printElements: String
  // Polymorphic call
  override def toString: String = s"[$printElements]"

  // Higher-order functions
  def map[B](transformer: A => B): MyListAF[B]
  def flatMap[B](transformer: A => MyListAF[B]): MyListAF[B]
  def filter[B](predicate: A => Boolean): MyListAF[A]
  def ++[B >: A](list: MyListAF[B]): MyListAF[B]
}

case object EmptyAF extends MyListAF[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyListAF[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyListAF[B] = new ConsAF(element, EmptyAF)
  override def printElements: String = ""
  def map[B](transformer: Nothing => B): MyListAF[B] = EmptyAF
  def flatMap[B](transformer: Nothing => MyListAF[B]): MyListAF[B] = EmptyAF
  def filter[B](predicate: Nothing => Boolean): MyListAF[Nothing] = EmptyAF
  def ++[B >: Nothing](list: MyListAF[B]): MyListAF[B] = list
}

case class ConsAF[+A](h: A, t: MyListAF[A]) extends MyListAF[A] {
  def head: A = h
  def tail: MyListAF[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](element: B): MyListAF[B] = new ConsAF(element, this)
  override def printElements: String = if(t.isEmpty) "" + h else "" + h + " " + t.printElements
  /*
    [1,2,3].map(n * 2)
      = new ConsAF(2, [2,3].map(n * 2))
      = new ConsAF(2, new ConsAF(4, [3].map(n * 2)))
      = new ConsAF(2, new ConsAF(4, new ConsAF(6, Empty.map(n * 2))))
      = new ConsAF(2, new ConsAF(4, new ConsAF(6, Empty))))
   */
  def map[B](transformer: A => B): MyListAF[B] =
    new ConsAF(transformer(h), t.map(transformer))
  /*
    [1,2].flatMap(n => [n, n+1])
      = [1,2] ++ [2].flatMap(n => [n, n+1])
      = [1,2] ++ [2,3] ++ Empty.flatMap(n => [n, n+1])
      = [1,2] ++ [2,3] ++ Empty
      = [1,2,2,3]
   */
  def flatMap[B](transformer: A => MyListAF[B]): MyListAF[B] =
    transformer(h) ++ t.flatMap(transformer)
  /*
    [1,2,3].filter(n % 2 = 0) =
      [2,3].filter(n % 2 == 0) =
      = new ConsAF(2, [3].filter(n % 2 == 0))
      = new ConsAF(2, Empty.filter(n % 2 == 0))
      = new ConsAF(2, Empty)
  */
  def filter[B](predicate: A => Boolean): MyListAF[A] = {
    if(predicate(h)) new ConsAF(h, t.filter(predicate))
    else t.filter(predicate)
  }
  /*
    [1,2] ++ [3,4,5]
      = new ConsAF(1, [2] ++ [3,4,5])
      = new ConsAF(1, new ConsAF(2, Empty ++ [3,4,5])
      = new ConsAF(1, new ConsAF(2, new ConsAF(3, new ConsAF(4, new ConsAF(5)))))
   */
  def ++[B >: A](list: MyListAF[B]): MyListAF[B] = new ConsAF(h, t ++ list)
}

object ListTestAF extends App {
//  val list_0 = new ConsAF(1, new ConsAF(2, new ConsAF(3, Empty)))
//  println(list_0.head)
//  println(list_0.tail.head)
//  println(list_0.add(4).head)
//  val list_1 = list_0.add(4).add(5).add(6)
//  println(list_1.printElements)

  val list_of_integers: MyListAF[Int] = new ConsAF[Int](1, new ConsAF[Int](2, new ConsAF[Int](3, EmptyAF)))
  val clone_list_of_integers: MyListAF[Int] = new ConsAF[Int](1, new ConsAF[Int](2, new ConsAF[Int](3, EmptyAF)))
  val another_list_of_integers: MyListAF[Int] = new ConsAF[Int](4, new ConsAF[Int](5, EmptyAF))
  val list_of_strings: MyListAF[String] = new ConsAF[String]("Hello", new ConsAF[String]("Scala", EmptyAF))

  println(list_of_integers.toString)
  println(list_of_strings.toString)

  println(list_of_integers.map(e => e * 2).toString)
  println(list_of_integers.map(_ * 2).toString)
  println(list_of_integers.filter(e => e % 2 == 0).toString)
  println(list_of_integers.filter(_ % 2 == 0).toString)
  println((list_of_integers ++ another_list_of_integers).toString)
  println(list_of_integers.flatMap(e => ConsAF(e, ConsAF(e + 1, EmptyAF))).toString)

  println(clone_list_of_integers == list_of_integers)
}