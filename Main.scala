package sandbox

import cats.Show
import cats.syntax.semigroup._
import sandbox.domain.Cat
import sandbox.lib.PrintableSyntax._
import sandbox.lib.PrintableInstances._

import cats.instances.int._
import cats.instances.string._
import cats.instances.option._
import cats.syntax.show._


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

}

