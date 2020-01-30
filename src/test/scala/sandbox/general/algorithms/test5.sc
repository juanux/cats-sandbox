import scala.annotation.tailrec

object Solution {
  def solution(n: Int): Int = {
    // write your code in Scala 2.12

    @tailrec
    def validate(next:Int):Int = {
      if(getSum(next) == getSum(n)) next
      else validate(next + 1)
    }

    def getSum(num:Int):Int ={
      val digits = num.toString.split("")

      if(digits.length > 1) digits.map(_.toInt).sum
      else num
    }

    validate(n + 1)

  }
}


Solution.solution(28)
Solution.solution(734)
Solution.solution(1990)
Solution.solution(1000)
Solution.solution(50000)