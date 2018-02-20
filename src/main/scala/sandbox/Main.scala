package sandbox

import cats.Show
import cats.Eq
import cats.syntax.semigroup._
import sandbox.domain.Cat
import sandbox.lib.PrintableSyntax._
import sandbox.lib.PrintableInstances._

import cats.instances.int._
import cats.instances.string._
import cats.instances.option._
import cats.syntax.show._
import cats.syntax.eq._


object Main extends App {
  println("Hello " |+| "Cats!")

  //Example using my own type class
  val lola = Cat("Lola",5,"White")
  lola.print

  //example using Show

  val intShow:Show[Int] = Show.apply[Int]
  val stringShow:Show[String] = Show.apply[String]
  val optionShow:Show[Option[String]] = Show.apply[Option[String]]


  intShow.show(12233) //using instance int
  stringShow.show("Hellooo") //using instance string
  optionShow.show(Some("I am an optional value"))

  println(13123231.show)  //using show syntax
  println("Holi  !! ".show)

  //Add new features to an existing type class

  implicit val catShow:Show[Cat] = Show.show(cat => s"${cat.name.show} is ${cat.age.show} year old and  ${cat.color.show} cat.!")

  println(lola.show)

  //Eq examples
  val eqInt = Eq[Int]

  println(eqInt.eqv(123,321)) //Comparing Ints false
  println(eqInt.eqv(123,123)) //Compare Ints true

 //Eq using syntax
  println(123 === 123)
  println(323 === 444)

 //Comparing options
  println((Some(1):Option[Int]) === (None:Option[Int])) //false
  println((Some(1):Option[Int]) === (Some(1):Option[Int])) //true

  //Comparing custom types

  implicit val catEq:Eq[Cat] = Eq.instance[Cat]{
    (cat1,cat2) => cat1.age === cat2.age && cat1.color === cat2.color && cat1.name === cat2.name

  }

  val lola2 = Cat("Lola",5,"White")

  println(lola === lola2)
  println(lola === Cat("Lola",5,"Black"))


}
