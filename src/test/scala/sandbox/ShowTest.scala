package sandbox

import cats.Show
import org.scalatest.{FlatSpec, Matchers}
import cats.instances.int._
import cats.instances.string._
import cats.instances.option._
import cats.syntax.show._
import sandbox.domain.Cat

class ShowTest extends  FlatSpec with Matchers{

  val intShow:Show[Int] = Show.apply[Int]
  val stringShow:Show[String] = Show.apply[String]
  val optionShow:Show[Option[String]] = Show.apply[Option[String]]
  val lola = Cat("Lola",5,"White")


  "Show instance for int " should " show string values " in {

    intShow.show(12233) shouldBe "12233"

  }

  "Show instance for int " should " show string values using syntax" in {

    12233.show shouldBe "12233"

  }

  "Show instance for string " should " show string values " in {

    stringShow.show("Hellooo") shouldBe "Hellooo"

  }

  "Show instance for string " should " show string values using syntax" in {

    "Hellooo".show shouldBe "Hellooo"

  }

  "Show instance for optional string " should " show string values " in {

    optionShow.show(Some("I am an optional value")) shouldBe "Some(I am an optional value)"

  }

  "Show instance for optional string " should " show string values using syntax" in {
   //TODO : How to implement show to a option
   // Some("I am an optional value").show shouldBe "Some(I am an optional value)"

  }

  "Show instance for a ADT " should " show string value for specific ADT using syntax" in{

    implicit val catShow:Show[Cat] = Show.show(cat => s"${cat.name.show} is ${cat.age.show} year old and  ${cat.color.show} cat.!")

    lola.show shouldBe s"${lola.name} is ${lola.age} year old and  ${lola.color} cat.!"

  }

}
