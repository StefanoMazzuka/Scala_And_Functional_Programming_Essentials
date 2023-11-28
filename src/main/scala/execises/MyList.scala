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
  def map[B](transformer: MyTransformer[A, B]): MyList[B]
  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B]
  def filter[B](predicate: MyPredicate[A]): MyList[A]
  def ++[B >: A](list: MyList[B]): MyList[B]
}

case object Empty extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)
  override def printElements: String = ""
  def map[B](transformer: MyTransformer[Nothing, B]): MyList[B] = Empty
  def flatMap[B](transformer: MyTransformer[Nothing, MyList[B]]): MyList[B] = Empty
  def filter[B](predicate: MyPredicate[Nothing]): MyList[Nothing] = Empty
  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
}

case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h
  def tail: MyList[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](element: B): MyList[B] = new Cons(element, this)
  override def printElements: String = if(t.isEmpty) "" + h else "" + h + " " + t.printElements
  /*
    [1,2,3].map(n * 2)
      = new Cons(2, [2,3].map(n * 2))
      = new Cons(2, new Cons(4, [3].map(n * 2)))
      = new Cons(2, new Cons(4, new Cons(6, Empty.map(n * 2))))
      = new Cons(2, new Cons(4, new Cons(6, Empty))))
   */
  def map[B](transformer: MyTransformer[A, B]): MyList[B] =
    new Cons(transformer.transform(h), t.map(transformer))
  /*
    [1,2].flatMap(n => [n, n+1])
      = [1,2] ++ [2].flatMap(n => [n, n+1])
      = [1,2] ++ [2,3] ++ Empty.flatMap(n => [n, n+1])
      = [1,2] ++ [2,3] ++ Empty
      = [1,2,2,3]
   */
  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B] =
    transformer.transform(h) ++ t.flatMap(transformer)
  /*
    [1,2,3].filter(n % 2 = 0) =
      [2,3].filter(n % 2 == 0) =
      = new Cons(2, [3].filter(n % 2 == 0))
      = new Cons(2, Empty.filter(n % 2 == 0))
      = new Cons(2, Empty)
  */
  def filter[B](predicate: MyPredicate[A]): MyList[A] = {
    if(predicate.test(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)
  }
  /*
    [1,2] ++ [3,4,5]
      = new Cons(1, [2] ++ [3,4,5])
      = new Cons(1, new Cons(2, Empty ++ [3,4,5])
      = new Cons(1, new Cons(2, new Cons(3, new Cons(4, new Cons(5)))))
   */
  def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list)
}

trait MyPredicate[-T] {
  def test(elem: T): Boolean
}

trait MyTransformer[-A, B] {
  def transform(elem: A): B
}

object ListTest extends App {
//  val list_0 = new Cons(1, new Cons(2, new Cons(3, Empty)))
//  println(list_0.head)
//  println(list_0.tail.head)
//  println(list_0.add(4).head)
//  val list_1 = list_0.add(4).add(5).add(6)
//  println(list_1.printElements)

  val list_of_integers: MyList[Int] = new Cons[Int](1, new Cons[Int](2, new Cons[Int](3, Empty)))
  val clone_list_of_integers: MyList[Int] = new Cons[Int](1, new Cons[Int](2, new Cons[Int](3, Empty)))
  val another_list_of_integers: MyList[Int] = new Cons[Int](4, new Cons[Int](5, Empty))
  val list_of_strings: MyList[String] = new Cons[String]("Hello", new Cons[String]("Scala", Empty))

  println(list_of_integers.toString)
  println(list_of_strings.toString)

  val map_transformer = new MyTransformer[Int, Int] {
    override def transform(elem: Int): Int = elem * 2
  }
  println(list_of_integers.map(map_transformer).toString)

  val filter_predicate = new MyPredicate[Int] {
    override def test(elem: Int): Boolean = elem % 2 == 0
  }
  println(list_of_integers.filter(filter_predicate).toString)

  println((list_of_integers ++ another_list_of_integers).toString)

  val flatmap_transformer = new MyTransformer[Int, MyList[Int]] {
    override def transform(elem: Int): MyList[Int] = new Cons(elem, new Cons(elem + 1, Empty))
  }
  println(list_of_integers.flatMap(flatmap_transformer).toString)
  
  println(clone_list_of_integers == list_of_integers)
}