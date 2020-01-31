
trait UpperInverse[A]{

  def toUpperInverse(a:A):A

}

object UpperInverseInstances{

  implicit val stringToUpperInverse = new UpperInverse[String]{

    def toUpperInverse(a:String):String = a.reverse.toUpperCase()

  }


  implicit val intToUpperInverse = new UpperInverse[Int]{

    def toUpperInverse(a:Int):Int = 4

  }

}

object UpperInverseSyntax {
  implicit class UpperInverseOps[A](a:A){

    def toUpperInverse(implicit instance:UpperInverse[A] ) = instance.toUpperInverse(a)

  }

}


import UpperInverseInstances._
import UpperInverseSyntax._

"aaabbbccc".toUpperInverse

9.toUpperInverse