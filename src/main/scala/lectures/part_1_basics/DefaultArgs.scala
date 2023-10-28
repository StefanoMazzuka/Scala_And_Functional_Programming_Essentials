package lectures.part_1_basics

/**
 * @author Stefano Mazzuka on 27/10/2023
 */
object DefaultArgs extends App {

  def trFact(n: Int, acc: Int = 1): Int = {
    if(n <= 1) acc
    else trFact(n - 1, n * acc)
  }

  val fact_10 = trFact(10)
  println(fact_10)

  def savePicture(format: String, width: Int = 1920, height: Int = 1080): Unit = println("Saving picture")
  savePicture(width = 800, format = "png")
}
