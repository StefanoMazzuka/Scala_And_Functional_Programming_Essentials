package execises

/**
 * @author Stefano Mazzuka on 06/11/2023
 */
abstract class MyListFP[+A] {
  /*
    methods:
    - head(): return first element of the list
    - tail(): return remainder of the list
    - isEmpty(): return is this list empty
    - add(int): return new list with this element added
    - toString(): return a string representation of the list
   */

  def head: A
  def tail: MyListFP[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyListFP[B]
  def printElements: String
  // Polymorphic call
  override def toString: String = s"[$printElements]"

  // Higher-order functions
  def map[B](transformer: A => B): MyListFP[B]
  def flatMap[B](transformer: A => MyListFP[B]): MyListFP[B]
  def filter[B](predicate: A => Boolean): MyListFP[A]
  def ++[B >: A](list: MyListFP[B]): MyListFP[B]
}

case object EmptyFP extends MyListFP[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyListFP[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyListFP[B] = new ConsFP(element, EmptyFP)
  override def printElements: String = ""
  def map[B](transformer: Nothing => B): MyListFP[B] = EmptyFP
  def flatMap[B](transformer: Nothing => MyListFP[B]): MyListFP[B] = EmptyFP
  def filter[B](predicate: Nothing => Boolean): MyListFP[Nothing] = EmptyFP
  def ++[B >: Nothing](list: MyListFP[B]): MyListFP[B] = list
}

case class ConsFP[+A](h: A, t: MyListFP[A]) extends MyListFP[A] {
  def head: A = h
  def tail: MyListFP[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](element: B): MyListFP[B] = new ConsFP(element, this)
  override def printElements: String = if(t.isEmpty) "" + h else "" + h + " " + t.printElements
  /*
    [1,2,3].map(n * 2)
      = new ConsFP(2, [2,3].map(n * 2))
      = new ConsFP(2, new ConsFP(4, [3].map(n * 2)))
      = new ConsFP(2, new ConsFP(4, new ConsFP(6, Empty.map(n * 2))))
      = new ConsFP(2, new ConsFP(4, new ConsFP(6, Empty))))
   */
  def map[B](transformer: A => B): MyListFP[B] =
    new ConsFP(transformer(h), t.map(transformer))
  /*
    [1,2].flatMap(n => [n, n+1])
      = [1,2] ++ [2].flatMap(n => [n, n+1])
      = [1,2] ++ [2,3] ++ Empty.flatMap(n => [n, n+1])
      = [1,2] ++ [2,3] ++ Empty
      = [1,2,2,3]
   */
  def flatMap[B](transformer: A => MyListFP[B]): MyListFP[B] =
    transformer(h) ++ t.flatMap(transformer)
  /*
    [1,2,3].filter(n % 2 = 0) =
      [2,3].filter(n % 2 == 0) =
      = new ConsFP(2, [3].filter(n % 2 == 0))
      = new ConsFP(2, Empty.filter(n % 2 == 0))
      = new ConsFP(2, Empty)
  */
  def filter[B](predicate: A => Boolean): MyListFP[A] = {
    if(predicate(h)) new ConsFP(h, t.filter(predicate))
    else t.filter(predicate)
  }
  /*
    [1,2] ++ [3,4,5]
      = new ConsFP(1, [2] ++ [3,4,5])
      = new ConsFP(1, new ConsFP(2, Empty ++ [3,4,5])
      = new ConsFP(1, new ConsFP(2, new ConsFP(3, new ConsFP(4, new ConsFP(5)))))
   */
  def ++[B >: A](list: MyListFP[B]): MyListFP[B] = new ConsFP(h, t ++ list)
}

object ListTestFP extends App {
//  val list_0 = new ConsFP(1, new ConsFP(2, new ConsFP(3, Empty)))
//  println(list_0.head)
//  println(list_0.tail.head)
//  println(list_0.add(4).head)
//  val list_1 = list_0.add(4).add(5).add(6)
//  println(list_1.printElements)

  val list_of_integers: MyListFP[Int] = new ConsFP[Int](1, new ConsFP[Int](2, new ConsFP[Int](3, EmptyFP)))
  val clone_list_of_integers: MyListFP[Int] = new ConsFP[Int](1, new ConsFP[Int](2, new ConsFP[Int](3, EmptyFP)))
  val another_list_of_integers: MyListFP[Int] = new ConsFP[Int](4, new ConsFP[Int](5, EmptyFP))
  val list_of_strings: MyListFP[String] = new ConsFP[String]("Hello", new ConsFP[String]("Scala", EmptyFP))

  println(list_of_integers.toString)
  println(list_of_strings.toString)

  val map_transformer = new Function[Int, Int] {
    override def apply(elem: Int): Int = elem * 2
  }
  println(list_of_integers.map(map_transformer).toString)

  val filter_predicate = new Function[Int, Boolean] {
    override def apply(elem: Int): Boolean = elem % 2 == 0
  }
  println(list_of_integers.filter(filter_predicate).toString)

  println((list_of_integers ++ another_list_of_integers).toString)

  val flatmap_transformer = new Function[Int, MyListFP[Int]] {
    override def apply(elem: Int): MyListFP[Int] = new ConsFP(elem, new ConsFP(elem + 1, EmptyFP))
  }
  println(list_of_integers.flatMap(flatmap_transformer).toString)
  
  println(clone_list_of_integers == list_of_integers)
}