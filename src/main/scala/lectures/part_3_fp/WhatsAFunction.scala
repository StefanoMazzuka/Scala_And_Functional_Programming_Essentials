package lectures.part_3_fp

/**
 * @author Stefano Mazzuka on 03/12/2023
 */
object WhatsAFunction extends App {

  // Use functions as first class elements
  // Problem: oop

  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  println(doubler(2))

  // Function types = Function1[A, B]
  // new Function[String, Int] where String is input and Int is output
  val stringToIntConverter = new Function[String, Int] {
    override def apply(string: String): Int = string.toInt
  }

  println(stringToIntConverter("3") + 4)

  // new Function2[Int, Int, Int] takes the first two Ints as input, and another Int as output
  val adder: Function2[Int, Int, Int] = new Function2[Int, Int, Int] {
    override def apply(a: Int, b: Int): Int = a + b
  }
  // Same as:
  val adder2: (Int, Int) => Int = new Function2[Int, Int, Int] {
    override def apply(a: Int, b: Int): Int = a + b
  }
  // Same as:
  val adder3: (Int, Int) => Int = (a: Int, b: Int) => {a + b}
  // Same as:
  val adder4 = (a: Int, b: Int) => {a + b}

  // Function types Function2[A, B, R] === (A, B) => R

  // ALL SCALA FUNCTIONS ARE OBJECTS

  /*
    1. Create a function which takes 2 strings and concatenates them
  */
  def concatenator(a: String, b: String): String = a + b

  /*
    2. Transform the MyPredicate and MyTransformer into a function types
  */
  // Done in MyListFP
  /*
    3. Define a function which takes an Int and returns another function which takes an Int and returns an Int
      - Define what's the type of this function
      - How to do it
   */
  // Function1[Int, Function[Int, Int]]
  val superAdder: Function1[Int, Function1[Int, Int]] = new Function1[Int, Function1[Int, Int]] {
    override def apply(x: Int): Function1[Int, Int] = new Function1[Int, Int] {
      override def apply(y: Int): Int = x + y
    }
  }

  val adder5 = superAdder(3)
  println(adder5(4))
  println(superAdder(3)(4)) // Curried function
}

trait MyFunction[A, B] {
  def apply(element: A): B
}
