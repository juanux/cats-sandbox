import scala.annotation.tailrec

object Solution {
  def solution(a: Array[Int], b: Array[Int]): Int = {
    // write your code in Scala 2.12

    @tailrec
    def validate(currentIndex: Int): Int = {

      if (currentIndex == a.length -1) 0
      else if(sumSplit(currentIndex,a,b)) currentIndex
      else validate(currentIndex + 1 )

      }

    def sumSplit(index:Int,array1: Array[Int],array2: Array[Int]): Boolean ={

      val (a1,a2) = array1.splitAt(index)
      val (b1,b2) = array2.splitAt(index)

      val s1Sum = a1.sum
      if((a1.nonEmpty && a2.nonEmpty && b1.nonEmpty && b2.nonEmpty) && ((s1Sum == a2.sum) && (s1Sum == b1.sum) && (s1Sum == b2.sum) )) true
      else false

    }

    validate(0)

  }
}

//Solution.solution(Array(4,-1,0,3),Array(-2,5,0,3))
Solution.solution(Array(2,-2,-3,3),Array(0,0,4,-4))