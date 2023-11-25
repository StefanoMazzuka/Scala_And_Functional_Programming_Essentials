package lectures.part_1_basics

import javax.xml.transform.Result
import scala.annotation.tailrec

/**
 * @author Stefano Mazzuka on 23/10/2023
 */
object Recursion extends App {

  def aFactorialFunction(n: Int): Int = {
    if(n <= 1) 1
    else {
      println(s"Computing factorial of $n - I first need a factorial of ${n - 1}")
      val result = n * aFactorialFunction(n - 1)
      println(s"Computing factorial of $n")

      result
    }
  }

  println(aFactorialFunction(10))
  //  println(aFactorialFunction(5000)) ERROR: StackOverflowError

  // TAIL RECURSION
  @tailrec // We put this tag to prove that is Tail Recursion. If it's not, it would be an error
    def factorialHelper(x: Int, accumulator: BigInt): BigInt = {
      if(x <= 1) accumulator
      else factorialHelper(x - 1, x * accumulator) // Tail Recursion = Use recursive call as the LAST expression
    }

    factorialHelper(5000, 1)

  /*
    anotherFactorial(10) = factHelper(10, 1)
    = factHelper(9, 10 * 1)
    = factHelper(8, 9 * 10 * 1)
    = factHelper(7, 8 * 9 * 10 * 1)
    ...
    = factHelper(2, 3 * 4 * ... * 10 * 1)
    = factHelper(1, 1 * 2 * 3 * 4 * ... * 10)
    = 1 * 2 * 3 * 4 * ... * 10
  */

  println(factorialHelper(5000, 1))

  // WHEN YOU NEED LOOPS, USE TAIL RECURSION

  // 1. Concatenate a string n times
  @tailrec
  def concatenateStrings(aString: String, nTimes: Int, result: String = ""): String = {
    if(nTimes == 0) result
    else concatenateStrings(aString, nTimes - 1, result + aString)
  }
  println(concatenateStrings("hello ", 3))

  // 2. isPrime function with tail recursion
  def isPrime(n: Int): Boolean = {
    @tailrec
    def recursiveDivision(dividend: Int, divisior: Int, result: Boolean = false): Boolean = {
      if(divisior <= 1) true
      else if((dividend % divisior) == 0) false
      else recursiveDivision(dividend, divisior - 1, true)
    }

    recursiveDivision(n, n - 1)
  }
  println(isPrime(97))

  // 3. Fibonacci function with tail recursion
  def aFibonacciFunction(n: Int): Int = {
    @tailrec
    def aFibonacciTailRecursion(count: Int, last: Int, nextToLast: Int): Int = {
      if(count == n) last
      else aFibonacciTailRecursion(count + 1, last + nextToLast, last)
    }

    if(n <= 2) 1
    else aFibonacciTailRecursion(2, 1, 1)
  }

  println(aFibonacciFunction(8))
}
