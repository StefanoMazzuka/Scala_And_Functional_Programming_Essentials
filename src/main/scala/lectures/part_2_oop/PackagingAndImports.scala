package lectures.part_2_oop

import playground.{Cinderella, PrinceCharming => Prince}

/**
 * @author Stefano Mazzuka on 28/11/2023
 */
object PackagingAndImports extends App {

  // Package members are accessible by their simple name
  val writer = new Writer("Stefano", "Cool", 2018)

  // Import the package
  val princess = new Cinderella // new playground.Cinderella = Fully qualified name

  // Packages are in hierarchy
  // Matching folder structure

  // Package object
  sayHello
  println(SPEED_OF_LIGHT)

  // Imports
  // 1. Use aliasing
  val prince = new Prince
  // 2. Default imports
  // java.lang    -> String, Object, Exception
  // scala        -> Int, Nothing, Function
  // scala.Predef -> println, ???
}
