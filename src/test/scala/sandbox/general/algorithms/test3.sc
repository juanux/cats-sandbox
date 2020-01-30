import scala.annotation.tailrec
import scala.collection.JavaConverters._

case class Village(index:Int,conections:List[Int])
object Solution {
  def solution(t: Array[Int]): Int = {


    //1 value
    //2 index
    val zippedT: Array[(Int, Int)] = t.zipWithIndex

    val map: Array[Village] = zippedT.map{ v=>

      Village(v._2,
        fillList(v._2, t: Array[Int])
      )
    }


???

}



  def fillList(index:Int, t: Array[Int]):List[Int] = {
    val value = t(index)

    index :: value :: t.zipWithIndex.collect{
      case (e,k) if  e == index => k
    }.toList

  }
}


val array = Array(2,0,2, 2, 1, 0)

array.indices.flatMap{ i =>

  array.indices.map{j=>

    (i,j)

  }
}.toList

Solution.solution(array)

//  (0, 3), (0, 4).
