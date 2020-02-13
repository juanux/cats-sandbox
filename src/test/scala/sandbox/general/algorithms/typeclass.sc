trait Power[A]{

  def power(a:A):A

}

object PowerInstance{

  implicit val powerInt: Power[Int] = new Power[Int]{
    def power(a:Int):Int = a*a
  }


  implicit val powerDouble: Power[Double] = new Power[Double]{
    def power(a:Double):Double = a*a
  }

  implicit val powerLong: Power[Long] = new Power[Long]{
    def power(a:Long):Long = a*a
  }



}

object PowerOps{

  implicit class PowerSyntax[A](a:A){
    def power(implicit power:Power[A]):A = power.power(a)
  }
}


import PowerInstance._
import PowerOps._

4.power
4l.power