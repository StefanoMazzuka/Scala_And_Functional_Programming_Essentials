package lectures.part_1_basics

/**
 * @author Stefano Mazzuka on 28/10/2023
 */
object StringOps extends App {

  val aString: String = "Hello, I am learning Scala"

  // Java functions available on Scala
  println(aString.charAt(2))
  println(aString.substring(7, 11))
  println(aString.split(" ").toList)
  println(aString.startsWith("Hello"))
  println(aString.replace(" ", "-"))
  println(aString.toLowerCase())
  println(aString.length)

  // Scala functions
  val aNumberString: String = "2"
  val aNumber: Int = aNumberString.toInt

  // PREPENDING and APPENDING
  // Prepending the char a and appending the char z
  println('a' +: aNumberString :+ 'z')
  println(aString.reverse)
  println(aString.take(2))

  // SCALA-SPECIFIC: STRING INTERPOLATORS
  // S-interpolators
  val name = "David"
  val age = 12
  val greeting = s"Hello, my name is $name and I am $age years old"
  val anotherGreeting = s"Hello, my name is $name and I will be turning ${age + 1} years old"
  println(anotherGreeting)

  //F-interpolators
  val speed = 1.2f
  val myth = f"$name can eat $speed%10.2f burgers per minute" // $speed%10.2f means 10 chars and 2 decimals minimum -> "      1,20"
  // also can use %s to string and %3d to decimals
  println(myth)

  // RAW-interpolators
  println(raw"This is an \n new line")
  val escaped = "This is an \n new line"
  println(raw"$escaped")
}
