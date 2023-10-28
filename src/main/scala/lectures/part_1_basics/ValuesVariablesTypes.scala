package lectures.part_1_basics

/**
 * @author Stefano Mazzuka on 22/10/2023
 */
object ValuesVariablesTypes extends App {
  
  // VALUES

  // Vals are immutable
  // Compiler can infer types

  val a_string: String = "hello"
  val another_string   = "goodbye"
  val a_boolean: Boolean = false
  val a_char: Char = 'a'
  val a_int: Int = 42
  val a_short: Short = 32767 // 2 Bytes -> -32,768 to 32,767
  val a_long: Long = 2147483647L // 4 Bytes -> -2,147,483,648 to 2,147,483,647
  val a_float: Float = 2.0F
  val a_double: Double = 3.14

  // VARIABLES

  // Vars are mutable

  var x: Int = 1
  x = x + 1
  x += 1
}
