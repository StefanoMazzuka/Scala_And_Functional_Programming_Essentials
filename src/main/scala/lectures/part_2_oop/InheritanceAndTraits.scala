package lectures.part_2_oop

/**
 * @author Stefano Mazzuka on 30/10/2023
 */
object InheritanceAndTraits extends App {

  // Single class inheritance
  class Animal {
    val creature_type = "wild"
    protected def eat = println("nom nom...")
    def step = println("animal step...")
  }

  class Cat extends Animal {
    def crunch = {
      eat // nom nom...
      println("crunch crunch")
    }
  }

  val cat = new Cat
  cat.crunch // crunch crunch

  // Constructors
  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }
  // class Adult(name: String, age: Int, id_card: String) extends Person ERROR: needs parameters
  class Adult_1(name: String, age: Int, id_card: String) extends Person(name, age)
  class Adult_2(name: String, age: Int, id_card: String) extends Person(name)

  // Overriding
  class Dog(override val creature_type: String) extends Animal {
    // override val creature_type = "domestic"
    override def eat = println("nom nom nom...")
    override def step = {
      super.step // animal step...
      println("dog step...")
    }
  }
  val dog = new Dog("K9")
  dog.eat // nom nom nom...
  println(dog.creature_type) // K9

  // Type substitution (bread: polymorphism)
  val unknown_animal: Animal = new Dog("K9")
  unknown_animal.step // dog steps...

  // overRIDING vs overLOADING

  // super

  /*
    Preventing override:
      1. Use final on member -> final def eat()
      2. Use final on the entire class -> final class Animal{}
      3. Seal the class = extends classes in THIS FILE but prevent extension in other files -> sealed class Animal{}
   */
}
