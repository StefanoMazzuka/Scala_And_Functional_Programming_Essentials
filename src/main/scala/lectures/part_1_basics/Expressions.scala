package lectures.part_1_basics

/**
 * @author Stefano Mazzuka on 22/10/2023
 */
object Expressions extends App {

  // EXPRESSIONS
  val x = 1 + 2
  println(2 + 3 * 4) // + - * / & | ^ << >> >>> (right shift with zero extension)
  println(1 << 2) // 1 (decimal) = 0001 (binary) shift left with 2 zeros 0100 (binary) = 4 (decimal)
  println(1 == x) // == != > >= < <=
  println(!(1 == x)) // ! && ||
  var a_variable = 2
  a_variable += 3 // -= *= /= ... side effects
  println(a_variable)

  // Instructions (do) vs Expressions (value)

  // IF Expression
  val a_condition = true
  val a_condition_value = if(a_condition) 1 else 2 // IF Expression
  println(a_condition_value)
  println(if(a_condition) 1 else 2)
  println(1 + 3)

  var i = 0
  val a_while = while(i < 10) { // NEVER WRITE THIS AGAIN!
    println(1)
    i += 1
  }

  // EVERYTHING in Scala is an Expression!
  val a_weird_value = (a_variable = 3) // Unit === void
  println(a_weird_value) // Side effects: println(), whiles, reassigning. Side effect = Expression that returns Unit

  // CODE BLOCK (are expressions)
  val a_code_block = {
    val y = 2
    val z = y + 1

    if(z > 2) "hello" else "goodbye"
  }

  // 1. Difference between "hello world!" vs println("hello world!")?
  // "hello world!" = String
  // println("hello world!") = It's a side effect, Unit

  // 2. What's the value of:
  val some_value = {
    2 < 3
  }
  println(some_value)
  // some_value true
  val some_other_value = {
    if(some_value) 239 else 986
    42
  }
  println(some_other_value)
  // some_other_value 42
}
