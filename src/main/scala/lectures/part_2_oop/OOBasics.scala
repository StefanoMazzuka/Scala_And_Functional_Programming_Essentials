package lectures.part_2_oop

/**
 * @author Stefano Mazzuka on 28/10/2023
 */
object OOBasics extends App {

  val person = new Person("John", 26)
  // println(person.name) // Class parameter, this is an ERROR
  println(person.age)
  person.greet("Daniel")
  person.greet
  person.greet()

  val writer: Writer   = new Writer("Stefano", "Mazzuka", 1992)
  val impostor: Writer = new Writer("Stefano", "Mazzuka", 1992)
  val novel: Novel     = new Novel("Ala Rota", writer, 2016)

  println(novel.authorAge)
  println(novel.isWrittenBy(impostor))
  val new_novel = novel.copy(2023)
  println(new_novel.authorAge)
  println(new_novel.isWrittenBy)

  val counter = new Counter(0)
  counter.increment.increment.increment.currentCount
  counter.increment(5).increment.decrement(2).currentCount
}

// IMPORTANT: Everything that is in the body of the class, will be evaluated
// Class parameters are NOT FIELDS: name = parameter, age = field
class Person(name: String, val age: Int) {
  // body

  val x = 2

  println(1 + 3)

  // method
  def greet(name: String) = println(s"${this.name} says: Hi, $name")

  // overloading
  def greet: Unit             = println(s"Hi, I am $name")
  def greet(x: Int = 1): Unit = println(s"Hi, I am $x")

  // multiple constructors
  def this(name: String) = this(name, 0)
}

/*
  Creates two classes: Novel and Writer
  Writer: first_name: String, surname: String, birth_year: Int
    - method: fullName

  Novel: name: String, author: Writer, release_year: Int
    - method: authorAge() Author age at the Novel release
    - method: isWrittenBy(author)
    - method: copy(new_release_year) New instance of Novel
 */
class Writer(first_name: String, surname: String, val birth_year: Int) {
  def fullName: String = s"$first_name $surname"
}

class Novel(name: String, author: Writer, release_year: Int) {
  def authorAge: Int                       = release_year - author.birth_year
  def isWrittenBy(author: Writer): Boolean = author == this.author
  def copy(release_year: Int): Novel       = new Novel(name, author, release_year)
}

/*
  Creates a class Counter
  Counter: i: Int
    - method: return current count
    - method: increment/decrement => return new Counter
    - method: overload increment/decrement to receive an amount to increment => return new Counter
 */
class Counter(val i: Int) {
  def currentCount: Unit = println(i)
  def increment: Counter = {
    println("incrementing")
    new Counter(i + 1)
  } // Immutability
  def increment(i: Int): Counter = {
    if(i <= 0) this
    else increment.increment(i - 1)
  }
  def decrement: Counter = {
    println("decrementing")
    new Counter(i - 1)
  }
  def decrement(i: Int): Counter = {
    if(i <= 0) this
    else decrement.decrement(i - 1)
  }
}