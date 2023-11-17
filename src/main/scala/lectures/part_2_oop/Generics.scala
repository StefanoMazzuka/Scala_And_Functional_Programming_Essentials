package lectures.part_2_oop

/**
 * @author Stefano Mazzuka on 17/11/2023
 */
object Generics extends App {

  class MyList[A] {
    // Use the type A
  }

  class MyMap[Key, Value]

  val listOfIntegers = new MyList[Int]
  val listOfStrings  = new MyList[String]

  // Generic methods
  object MyList {
    def empty[A]: MyList[A] = ???
  }
  val emptyListOfIntegers = MyList.empty[Int]

  // Variance problem
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // 1. YES List[Cat] extends List[Animal] = COVARIANCE
  class CovarianceList[+A]
  val animal: Animal = new Cat
  val animalList: CovarianceList[Animal] = new CovarianceList[Cat]
  // animalList.add(new Dog) ??? HARD QUESTION

  // 2. NO List[Cat] do not extends List[Animal] = INVARIANCE
  class InvariantList[A]
  // val invariantAnimalList: InvariantList[Animal] = new InvariantList[Cat] ERROR

  // 3. HELL, NO! CONTRAVARIANCE
  class ContravariantList[-A]
  val contravariantList: ContravariantList[Cat] = new ContravariantList[Animal]
  class Trainer[-A]
  val trainerList: Trainer[Cat] = new Trainer[Animal]

  // Bounded types
  class Cage[A <: Animal](animal: A)
  val cage = new Cage(new Dog)

  class Car
  // val car_cage = new Cage(new Car) ERROR

  class MyList2[+A] {
    def add[B >: A](element: B): MyList2[B] = ???
    /*
      A = Cat
      B = Animal
     */
  }
}