package lectures.part_3_fp

/**
 * @author Stefano Mazzuka on 06/12/2023
 */
object AnonymousFunctions extends App {

  val doublerOld = new Function1[Int, Int] {
    override def apply(x: Int): Int = x * 2
  }

  // Anonymous function (LAMBDA)
  val doublerNew1= (x: Int) => x * 2
  val doublerNew2: Int => Int = x => x * 2

  // Multiple params in a lambda
  val adder1 = (a: Int, b: Int) => a + b
  val adder2: (Int, Int) => Int = (a, b) => a + b

  // No params
  val justDoSomething: () => Int = () => 3
  // Careful
  println(justDoSomething) // lectures.part_3_fp.AnonymousFunctions$$$Lambda$5/515132998@33a1078
  println(justDoSomething()) // 3

  // Curly braces with lambdas
  val stringToInt = {(str: String) =>
    str.toInt
  }

  // MOAR syntactic sugar
  val niceIncrementer: Int => Int = _ + 1 // Equivalent to x => x + 1
  val niceAdder: (Int, Int, Int) => Int = _ + _ + _ // Equivalent to (a, b, c) = a + b + c

  /*
    1. MyList: replace all FunctionX calls with lambdas
    2. Rewrite the "special" adder as an anonymous function
   */

  val superAdd = (x: Int) => (y: Int) => x + y
  println(superAdd(3)(4)) // 7
}
