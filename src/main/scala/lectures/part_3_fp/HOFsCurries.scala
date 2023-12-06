package lectures.part_3_fp

import scala.annotation.tailrec

/**
 * @author Stefano Mazzuka on 06/12/2023
 */
object HOFsCurries extends App {

  // Higher order function (HOF)
  // map, flatMap, filter in MyList
   val superFunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = null

  // Function that applies a function n times over a value x
  // Parameters: nTimes(f, n, x)
  // nTimes(f, 3, x) = f(f(f(x)))
  @tailrec
  def nTimes(f: Int => Int, n: Int, x: Int): Int = {
    if(n <= 0) x
    else nTimes(f, n - 1, f(x))
  }

  val plusOneVal = (x: Int) => x + 1
  def plusOneFunc(x: Int): Int = x + 1

  println(nTimes(plusOneVal, 3, 1)) // 4
  println(nTimes(plusOneFunc, 3, 1)) // 4

  // nTimesBetter(f, n) => f(f(...f(x)))
  // increment10 = nTimesBetter(plusOne, 10) = x => plusOne(plusOne(...plusOne(x)))
  // val y = increment10(1)
  def nTimesBetter(f: Int => Int, n: Int): Int => Int = {
    if(n <= 0) (x: Int) => x
    else (x: Int) => nTimesBetter(f, n - 1)(f(x))
  }

  // Same
  def nTimesBetterCopy(f: Function[Int, Int], n: Int): Function[Int, Int] = {
    if (n <= 0) new Function[Int, Int] {
      override def apply(x: Int) = x
    }
  else new Function[Int, Int] {
      override def apply(x: Int) = nTimesBetterCopy(f, n - 1)(f(x))
    }
  }

  val plusTen = nTimesBetter(plusOneVal, 10)
  val plusTenCopy = nTimesBetterCopy(plusOneVal, 10)

  println(plusTen(10))
  println(plusTenCopy(10))

  // Curried functions
  val superAdder: Int => (Int => Int) = (x: Int) => (y: Int) => x + y
  val addThree = superAdder(3)
  println(addThree(10))
  println(superAdder(3)(10))

  // Functions with multiple parameter lists
  def curriedFormatter(c: String)(x: Double): String = c.format(x)

  val standardFormat: (Double => String) = curriedFormatter("%4.2f")
  val preciseFormat: (Double => String) = curriedFormatter("%10.8f")

  println(standardFormat(Math.PI))
  println(preciseFormat(Math.PI))
}
