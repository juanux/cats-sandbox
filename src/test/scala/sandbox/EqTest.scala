package sandbox

import org.scalatest.{FlatSpec, Matchers}
import cats.instances.int._
import cats.Eq
import sandbox.domain.Cat


class EqTest extends  FlatSpec with Matchers{


  "Eq " should "compare 2 Ints with the same value" in {

    val eqInt = Eq[Int]

    eqInt.eqv(1,1) shouldBe true

  }

  "Eq " should "compare 2 Ints with different values" in {

    val eqInt = Eq[Int]

    eqInt.eqv(1,2) shouldBe false

  }
  //TODO Conflict with scalactics library. Here we are not using cats for === =!=
  "Eq " should "compare 2 Ints with the same value using syntax" in {

    1 === 1 shouldBe true

  }

  "Eq " should "compare 2 Ints with different values using syntax" in {

    1 === 2 shouldBe false
  }


  ignore  should "compare 2 Ints with different values for inequality using syntax" in {

    1 !== 2 shouldBe true

  }


  ignore should "compare 2 Ints with same values for inequality using syntax" in {

    1 !== 1 shouldBe false

  }

  "Eq " should "compare integer optionals with same value " in{

    (Some(1):Option[Int]) === (Some(1):Option[Int]) shouldBe true
  }

  "Eq " should "compare integer optionals with different value " in{

    (Some(1):Option[Int]) === (Some(2):Option[Int]) shouldBe false
  }

  "Eq " should "compare integer optionals with a None value" in{

    (Some(1):Option[Int]) === (None:Option[Int]) shouldBe false
  }

  //TODO : Dont work because library conflict with scalactics
   "Eq" should "compare custom type with same value" in{

//    implicit val catEq:Eq[Cat] = Eq.instance[Cat]{
//      (cat1,cat2) => cat1.age === cat2.age && cat1.color === cat2.color && cat1.name === cat2.name

//    }

    val lola1 = Cat("Lola",5,"White")
    val lola2= Cat("Lola",5,"White")

    lola1 === lola2 shouldBe true


  }

  //TODO : Dont work because library conflict with scalactics
  "Eq" should "compare custom type with different value" in{

    //    implicit val catEq:Eq[Cat] = Eq.instance[Cat]{
    //      (cat1,cat2) => cat1.age === cat2.age && cat1.color === cat2.color && cat1.name === cat2.name

    //    }

    val lola1 = Cat("Lola",5,"White")
    val lola2= Cat("Lola2",5,"White")

    lola1 === lola2 shouldBe false


  }

}
