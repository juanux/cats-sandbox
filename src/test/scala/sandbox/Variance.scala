package sandbox

import org.scalatest.{FlatSpec, Matchers}

class Variance extends FlatSpec with Matchers{

  trait Animal{
    def getName():String
  }
  trait Mamals extends Animal
  trait Fish extends Animal
  case class Cow() extends Mamals{
    override  def getName():String = "Cow"
  }
  case class Dolphin() extends Fish{
    override  def getName():String = "Dolphin"

  }

  def getAnimalName(animal:Animal): String ={

    animal.getName

  }


  "getAnimalName" should " return Fish string " in {

    val fish = Dolphin()

    getAnimalName(fish) shouldBe "Dolphin"

  }

  "getAnimalName" should " return Cow string " in {

    val cow = Cow()

    getAnimalName(cow) shouldBe "Cow"

  }


	
}