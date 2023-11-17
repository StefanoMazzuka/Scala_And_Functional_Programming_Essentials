package lectures.part_2_oop

/**
 * @author Stefano Mazzuka on 30/10/2023
 */
object Objects extends App {

  // SCALA DOES NOT HAVE CLASS-LEVEL FUNCTIONALITY ("static")
  object Person {
    // "static"/"class" - level functionality
    val N_EYES = 2
    def canFly: Boolean = false

    // Factory method
    def apply(mother: Person, father: Person): Person = new Person("Boobie")
  }

  // SCALA COMPANION
  class Person(val name: String) {
    // "instance" - level functionality
  }

  println(Person.N_EYES)
  println(Person.canFly)

  // Scala Objects are SINGLETON INSTANCES
  val mary_1 = Person
  val john_1 = Person
  println(mary_1 == john_1) // true

  val mary_2 = new Person("mary_2")
  val john_2 = new Person("john_2")
  println(mary_2 == john_2) // false

  val boobie = Person(mary_2, john_2)

  // Scala Applications = Scala object with
  // def main(args: Array[String]): Unit
}
