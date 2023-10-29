package lectures.part_2_oop

import scala.language.postfixOps

/**
 * @author Stefano Mazzuka on 29/10/2023
 */
object MethodNotations extends App {

  class Person(val name: String, favoriteMovie: String, val age: Int) {
    def likes(movie: String): Boolean       = movie == favoriteMovie
    def hangOutWith(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def +(person: Person): String           = s"${this.name} is hanging out with ${person.name}"
    def +(title: String): Person            = new Person(s"$name ($title)", favoriteMovie, age)
    def unary_! : String                    = s"$name what the heck?!" // IMPORTANT: Put a space between method name and :
    def unary_+ : Person                    = new Person(name, favoriteMovie, age + 1)
    def isAlive: Boolean                    = true
    def apply(): String                     = s"Hi, my name is $name and I like $favoriteMovie"
    def apply(times: Int): String           = s"$name watched $favoriteMovie $times times"
    def learns(text: String): String        = s"$name learns $text"
    def learnsScala                         = this learns "Scala"
  }

  val mary = new Person("Mary", "Inception", 30)
  println(mary.likes("Inception")) // Object.method.parameter
  println(mary likes "Inception") // Object method parameter (Equivalent, can be use only with one parameter methods)
  // Infix notation = operator notation = syntactic sugar

  // "Operators in Scala"
  val tom = new Person("Tom", "Fight Club", 30)
  println(mary hangOutWith tom)
  println(mary + tom)
  println(mary.+(tom))

  // ALL OPERATORS ARE METHODS
  val (x, y) = (1, 2)
  println(x + y)
  println(x.+(y))

  // Prefix notation
  val a = -1 // Equivalent with 1.unary_-
  val b = 1.unary_- // Only works with - + ~ !

  println(!mary) // Equivalent with 1.unary_!
  println(mary.unary_!)

  // Postfix notation
  println(mary.isAlive)
  println(mary isAlive) // IMPORTANT: needs scala.language.postfixOps

  // apply
  println(mary.apply())
  println(mary()) // Equivalent, and object call like method = .apply()

  /*
    1. Overload the + operator
      - mary + "the rockstar" returns new Person "Mary (the rockstar)"
  */
  println((mary + "the rockstar").name)

  /*
    2. Add and age to the Person class
      - Add a unary + operator returns new Person with the age + 1
      - +mary returns mary with the age incrementer
  */
  println((+mary).age)

  /*
    3. Add a "learns" method in the Person class
      - learns(text: String) returns "Mary learns text"
      - learnsScala() calls learns method with "Scala"
      - Use it in postfix notation
   */
  println(mary learnsScala)

  /*
    4. Overload the apply method
      - mary.apply(2) returns "Mary watched Inception 2 times"
   */
  println(mary(2))
}
