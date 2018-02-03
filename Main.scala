package sandbox

import cats.instances.string._
import cats.syntax.semigroup._
import sandbox.domain.Cat
import sandbox.lib.PrintableSyntax._
import sandbox.lib.PrintableInstances._


object Main extends App {
  println("Hello " |+| "Cats!")
  val lola = Cat("Lola",5,"White")
  lola.print
}
