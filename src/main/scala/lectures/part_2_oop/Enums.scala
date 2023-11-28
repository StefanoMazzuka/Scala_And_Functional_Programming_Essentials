package lectures.part_2_oop

/**
 * @author Stefano Mazzuka on 28/11/2023
 */
object Enums {

  enum Permissions {
    case READ, WRITE, EXECUTE, NONE

    // Add fields/methods
    def openDocument(): Unit = {
      if(this == READ) println("Opening documents...")
      else println("reading not allowed.")
    }
  }

  val some_permissions: Permissions = Permissions.NONE

  // Constructor args
  enum PermissionsWithBits(bits: Int) {
    case READ extends PermissionsWithBits(4) // 100
    case WRITE extends PermissionsWithBits(2) // 010
    case EXECUTE extends PermissionsWithBits(1) // 001
    case NONE extends PermissionsWithBits(0) // 000
  }

  object PermissionsWithBits {
    def fromBits(bits: Int): PermissionsWithBits = // Whatever
    PermissionsWithBits.NONE
  }

  // Standard API
  val some_permissions_ordinal = some_permissions.ordinal
  val all_permissions = PermissionsWithBits.values // array of all possible values of the enum
  val read_permission: Permissions = Permissions.valueOf("READ") // Permissions.READ

  def main(args: Array[String]): Unit = {
    some_permissions.openDocument()
    println(some_permissions_ordinal)
    all_permissions.foreach(println)
    val test = PermissionsWithBits.EXECUTE
    println(test)
  }
}
