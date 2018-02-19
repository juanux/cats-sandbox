package sandbox

import org.scalatest.{FlatSpec, Matchers}
import sandbox.domain.Cat
import sandbox.lib.Printable

class TypeClassTest extends FlatSpec with Matchers{

  val lola = Cat("Lola",5,"White")


  "A printable type class " should " format using printable object" in{
    import sandbox.lib.PrintableInstances._

    Printable.format(lola) shouldBe s"${lola.name} is ${lola.age} year old and  ${lola.color} cat.!"

  }

  "A printable type class " should " format using syntax for Cat type" in{
    import sandbox.lib.PrintableInstances._
    import sandbox.lib.PrintableSyntax._

    Cat("Lola",5,"White").format shouldBe s"${lola.name} is ${lola.age} year old and  ${lola.color} cat.!"

  }


}
