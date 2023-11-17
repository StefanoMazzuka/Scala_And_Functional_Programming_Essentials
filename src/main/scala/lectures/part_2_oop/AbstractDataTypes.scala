package lectures.part_2_oop

/**
 * @author Stefano Mazzuka on 02/11/2023
 */
object AbstractDataTypes extends App {

  // Abstract
  abstract class Animal {
    val creatureType: String // Abstract member
    val preferred_meal: String = "fresh meat" // Non abstract member
    def eat: Unit
  }

  // You can't create an instance of an abstract class like val animal = new Animal()

  class Dog extends Animal {
    override val creatureType: String = "Canis Lupus Familiaris"
    override def eat: Unit = println("Crunch crunch...")
  }

  trait Carnivore {
    def eat(animal: Animal): Unit
    val preferred_meal: String = "fresh meat"
  }

  trait ColdBlood

  class Crocodile extends Animal with Carnivore with ColdBlood {
    override val creatureType: String = "Crocodile"
    override val preferred_meal: String = "dead meat"
    def eat: Unit = println("Nom nom...")
    def eat(animal: Animal): Unit = println(s"I'm a Crocodile and I'm eating ${animal.creatureType}")
  }

  val dog = new Dog
  val crocodile = new Crocodile
  crocodile.eat(dog)

  /*
    Differences between trait and abstract
    1. Traits do not have constructor parameters
    2. Multiple traits may be inherited by the same class
    3. Traits = behavior, Abstract = thing
   */

}
