package sandbox

import org.scalatest.{FlatSpec, Matchers}

class Variance extends FlatSpec with Matchers{

  trait Animals
  case class Mamals() extends Animals
  case class Fish() extends Animals
  case class Cow() extends Mamals
  case class Dolphin() extends Fish



	
}