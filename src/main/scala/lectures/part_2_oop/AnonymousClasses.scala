package lectures.part_2_oop

/**
 * @author Stefano Mazzuka on 25/11/2023
 */
object AnonymousClasses extends App {

  abstract class Animal {
    def eat: Unit
  }

  // Anonymous class
  val funny_animal_0: Animal = new Animal {
    override def eat: Unit = println("nom nom")
  }

  println(funny_animal_0.getClass) // class lectures.part_2_oop.AnonymousClasses$$anon$1

  // Equivalent to:
  // START
  class AnonymousClasses$$anon$1 extends Animal {
    override def eat: Unit = println("nom nom")
  }

  val funny_animal_1 = new AnonymousClasses$$anon$1
  println(funny_animal_1.getClass)
  // END

  class Person(name: String) {
    def sayHi(): Unit = println(s"Hi, my name is $name")
  }

  val jim = new Person("Jim") {
    override def sayHi(): Unit = println("Hi, my name is Jim, how can I be of service?")
  }

  /*
    1. Generic trait MyPredicate[-T] with a little method test (T) => Boolean
    2. Generic trait MyTransformer[-A, B] with a method transform(A) => B
    3. MyList:
        - map(transformer) => MyList
        - filter(predicate) => MyList
        - flatMap(transformer from A to MyList[B]) => MyList[B]

    class EvenPredicate extends MyPredicate[Int]
    class StringToIntTransformer extends MyTransformer|String, Int]

    [1,2,3].map(n * 2)             = [2,4,6]
    [1,2,3,4].filter(n % 2)        = [2,4]
    [1,2,3].flatMap(n => [n, n+1]) = [1,2,2,3,3,4]
  */


}
