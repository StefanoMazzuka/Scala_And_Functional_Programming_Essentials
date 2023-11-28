package lectures.part_2_oop

/**
 * @author Stefano Mazzuka on 28/11/2023
 */
object Exceptions extends App {

  val x: String = null
//  println(x.length) ERROR

  // 1. Throwing exception
//  val a_weird_value: String = throw new NullPointerException()

  // Throwable classes extend the Throwable class
  // Exception and Error are the major Throwable subtypes

  // 2. How to catch exceptions
  def getInt(with_exceptions: Boolean): Int = {
    if(with_exceptions) throw new RuntimeException("No int for you!")
    else 42
  }

  try {
    // Code that MIGHT THROW
    getInt(false)
  } catch {
    case e: RuntimeException => println("Caught a Runtime exception")
//    case e: NullPointerException => println("Caught a Null pointer exception")
  } finally {
    // Code that will get executed NO MATTER WHAT
    println("Finally")
  }

  val potential_fail = try {
    // Code that MIGHT THROW
    getInt(false)
  } catch {
    case e: RuntimeException => 43
    //    case e: NullPointerException => println("Caught a Null pointer exception")
  } finally {
    // Code that will get executed NO MATTER WHAT
    // Optional
    // Does not influence the return type of this expression
    // Use finally only for side effects
    println("Finally")
  }

  println(potential_fail) // 43

  // 3. How to define your own exceptions
  class MyException extends Exception

  val exception = new Exception

//  throw exception

  /*
  EXERCISES:
    1. Crash your program with an OutOfMemoryError (OOM)
  */
//  val array = Array.ofDim[Int](Int.MaxValue) -> java.lang.OutOfMemoryError: Requested array size exceeds VM limit

  /*
    2. Crash your program with an SOError (SO)
  */
//  def infinite: Int = 1 + infinite -> java.lang.StackOverflowError
//  val no_limit = infinite

  /*
    3. PocketCalculator
      - add(x,y)
      - subtract(x,y)
      - multiply(x,y)
      - divide(x,y)

      Throw:
        - OverflowException if add(x,y) exceeds Int.MAX_VALUE
        - UnderflowException if subtract(x,y) exceeds Int.MIN_VALUE
        - MathCalculationException for division by 0
   */

  class OverflowException extends Exception
  class UnderflowException extends Exception
  class MathCalculationException extends Exception("Division by 0")

  object PocketCalculator {
    def add(x: Int, y: Int) = {
      val result = x + y
      if(x > 0 && y > 0 && result < 0) throw new OverflowException
      else if(x < 0 && y < 0 && result > 0) throw new UnderflowException
      else result
    }
    def subtract(x: Int, y: Int) = {
      val result = x - y
      if (x > 0 && y < 0 && result < 0) throw new OverflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }
    def multiply(x: Int, y: Int) = {
      val result = x * y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result < 0) throw new OverflowException
      else if (x > 0 && y < 0 && result > 0) throw new UnderflowException
      else if (x > 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }
    def divide(x: Int, y: Int) = {
      if (y == 0) throw new MathCalculationException
      else x / y
    }
  }

//  println(PocketCalculator.add(Int.MaxValue, 10)) -> lectures.part_2_oop.Exceptions$OverflowException
//  println(PocketCalculator.subtract(Int.MinValue, 10)) -> lectures.part_2_oop.Exceptions$UnderflowException
//  println(PocketCalculator.divide(2, 0)) -> lectures.part_2_oop.Exceptions$MathCalculationException: Division by 0
}
