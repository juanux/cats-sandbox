package sandbox

import org.scalatest.{FlatSpec, Matchers}

class Covariance extends FlatSpec with Matchers{

  trait Car{
    def getName():String
    def startEngine():String
  }

  trait Volkswagen extends Car{
    override def startEngine():String = "Start engine Volkswagen"
  }
  trait Chevrolet extends Car{
    override def startEngine():String = "Start engine Chevrolet"
  }

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

  def startEngineVolkswagen[A <: Volkswagen](car:A): String ={
     car.startEngine()

  }

  def startEngineChevrolet[A <: Chevrolet](car:A): String ={
    car.startEngine()

  }



  "Start engine chevrolet " should "start engine only for Chevrolet cars " in{
    startEngineChevrolet(Sail()) shouldBe "Start engine Chevrolet"
    startEngineChevrolet(Sonic()) shouldBe "Start engine Chevrolet"

  }

  "Start engine volkswagen " should "start engine only for Volkswagen cars " in{
    startEngineVolkswagen(Polo()) shouldBe "Start engine Volkswagen"
    startEngineVolkswagen(Jetta()) shouldBe "Start engine Volkswagen"

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
