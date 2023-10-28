package lectures.part_1_basics

/**
 * @author Stefano Mazzuka on 27/10/2023
 */
object CBNvsCBV extends App {

  // CALL-BY-NAME VS CALL-BY-VALUE
  def calledByValue(x: Long): Unit = {
    println(s"by value: ${x}")
    println(s"by value: ${x}")
  }

  def calledByName(x: => Long): Unit = {
    println(s"by name: ${x}")
    println(s"by name: ${x}")
  }

  calledByValue(System.nanoTime())
  calledByName(System.nanoTime())

  /*
  by value: 618126102693900
  by value: 618126102693900
  by name: 618126203205700
  by name: 618126203980500
   */

  def infinite(): Int = 1 + infinite()
  def printFirst(x: Int, y: => Int) = println(x)

  // printFirst(infinite(), 34) ERROR
  printFirst(34, infinite()) // We don't use y so it doesn't evaluated, like lazy evaluation
}
