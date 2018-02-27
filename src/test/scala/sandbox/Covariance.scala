package sandbox

import org.scalatest.{FlatSpec, Matchers}

class Covariance extends FlatSpec with Matchers{

  trait Car{
    def getName():String
  }

  trait Volkswagen extends Car
  trait Chevrolet extends Car

  case class Sail() extends Chevrolet{
    override def getName():String = "Sail"
  }

  case class Sonic() extends Chevrolet{
    override def getName():String = "Sonic"
  }

  case class Jetta() extends Volkswagen {
    override def getName():String = "Jetta"
  }

  case class Polo() extends Volkswagen {
    override def getName():String = "Polo"
  }

  class Vehicle[+A](t: A)  {

    def vehicleType = t

  }




  "Jetta vehicle type " should " should have Jetta name for the co - varience type Vehicle parametrized with Volkswagen" in {

   val vehicleJetta = new Vehicle[Volkswagen](Jetta())

    vehicleJetta.vehicleType.getName() shouldBe "Jetta"


  }

  "Polo vehicle type " should " should have Polo name for the co - varience type Vehicle parametrized with Volkswagen" in {

    val vehicleJetta = new Vehicle[Volkswagen](Polo())

    vehicleJetta.vehicleType.getName() shouldBe "Polo"


  }

  "Sail vehicle type " should " should have Sail name for the co - varience type Vehicle parametrized with Chevrolet" in {

    val vehicleJetta = new Vehicle[Chevrolet](Sail())

    vehicleJetta.vehicleType.getName() shouldBe "Sail"


  }

  "Sonic vehicle type " should " should have Sonic name for the co - varience type Vehicle parametrized with Chevrolet" in {

    val vehicleJetta = new Vehicle[Chevrolet](Sail())

    vehicleJetta.vehicleType.getName() shouldBe "Sail"


  }



}
