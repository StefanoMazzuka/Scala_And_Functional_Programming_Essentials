package lectures.part_1_basics

/**
 * @author Stefano Mazzuka on 23/10/2023
 */
object Functions extends App {

  def aFunction(a: String, b: Int): String = { // Non recursive functions do not need return types (def aFunction(a: String, b: Int): = {})
    a + " " + b
  }

  println(aFunction("hello", 3))

  def aParameterlessFunction1(): Int = 42
  def aParameterlessFunction2: Int = 42
  println(aParameterlessFunction1())
  println(aParameterlessFunction2)

  def aRepeatedFunction(aString: String, n: Int): String = { // Recursive functions needs return types
    if(n == 1) aString
    else aString + aRepeatedFunction(aString, n - 1)
  }

  println(aRepeatedFunction("hello", 3))

  // WHEN YOU NEED LOOPS, USE RECURSION!

  def aFunctionWithSideEffects(aString: String): Unit = println(aString)

  def aBigFunction(n: Int): Int = {
    def aSmallFunction(a: Int,  b: Int): Int = a + b

    aSmallFunction(n, n - 1)
  }

  // 1. A greeting function (name, age) => "Hi, my name is $name and I am $age years old."
  def aGreetingFunction(name: String, age: Int): String = s"Hi, my name is ${name} and I am ${age} years old."
  println(aGreetingFunction("Stefano", 31))

  // 2. A factorial function 1 * 2 * 3 * ... * n
  def aFactorialFunction(n: Int): Int = {
    if(n <= 1) 1
    else n * aFactorialFunction(n - 1)
  }
  println(aFactorialFunction(5))

  // 3. A fibonacci function
  // f(1) = 1
  // f(2) = 1
  // f(n) = f(n - 1) + f(n - 2)
  def aFibonacciFunction(n: Int): Int = {
    if(n <= 2) 1
    else aFibonacciFunction(n - 1) + aFibonacciFunction(n - 2)
  }
  println(aFibonacciFunction(4))

  // 4. Test if a number is prime
  def isPrime(n: Int): Boolean = {
    def recursiveDivision(dividend: Int, divisior: Int): Int = {
      if(divisior == 1) 1
      else if((dividend % divisior) != 0) recursiveDivision(dividend, divisior - 1)
      else recursiveDivision(dividend, divisior - 1) + 1
    }

    if(n < 1) return false
    if(recursiveDivision(n, n) <= 2) true else false
  }

  println(isPrime(11))
}
