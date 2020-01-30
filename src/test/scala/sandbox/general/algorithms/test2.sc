
import scala.annotation.tailrec


/*
This is a demo task.
Write a function:
	object Solution { def solution(a: Array[Int]): Int }
that, given an array A of N integers, returns the smallest positive integer (greater than 0) that does not occur in A.
For example, given A = [1, 3, 6, 4, 1, 2], the function should return 5.
Given A = [1, 2, 3], the function should return 4.
Given A = [−1, −3], the function should return 1.
Write an efficient algorithm for the following assumptions:
	• N is an integer within the range [1..100,000];
each element of array A is an integer within the range [−1,000,000..1,000,000].
 */

object Solution {
  def solution(a: Array[Int]): Int = {
    // write your code in Scala 2.12
    val min:Int = a.min
    @tailrec
    def validate(current:Int):Int ={
      if(min > 1) 1
      else if(current <= 0 || a.contains(current)) validate(current +1)
      else current
    }
    validate(min)
  }
}
Solution.solution(Array(0))
Solution.solution(Array(-1,0,9))
Solution.solution(Array(1, 3, 6, 4, 1, 2))
Solution.solution(Array(1,2,3))
Solution.solution(Array(-1,-3))