package lectures.part_2_oop

/**
 * @author Stefano Mazzuka on 28/11/2023
 */
object CaseClasees extends App {

  case class Person(name: String, age: Int)

  // 1. Class parameters are fields
  val jim = Person("Jim", 34)
  println(jim.name)

  // 2. Sensible toString
  // println(instance) = println(instance.toString) // Syntactic sugar
  println(jim.toString)
  println(jim)

  // 3. Equals and hashCode implemented OOTB (Out of the box)
  val jim_2 = Person("Jim", 34)
  println(jim == jim_2)

  // 4. CCs have handy copy method
  val jim_3 = jim.copy(age = 45)
  println(jim_3)

  // 5. CCs have companion objects
  val the_person = Person
  val mary = the_person("Mary", 34)
  println(mary)

  // 6. CCs are serializable
  // Akka

  // 7. CCs have extractor patterns = CCs can be udes in PATTERN MATCHING

  case object UnitedKingdom {
    def name: String = "The UK of GB and NI"
  }

  /*
    Expand MyList -> Use case classes and case objects
   */
}
