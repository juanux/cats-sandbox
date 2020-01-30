package sandbox.general.algorithms

import org.scalatest.{FlatSpec, Matchers}


class MergeSortSpec extends FlatSpec with Matchers{

    it should " order a List" in {

        MergeSort.mergeSort(List(8,5,1,9,-1,4,7).toIndexedSeq) shouldBe List(-1,1,4,5,7,8,9)

    }
}

object MergeSort{

    def mergeSort(list: IndexedSeq[Int]):List[Int] = {
        if(list.length == 1){
            List(list.head)
        }else{
            val (left,right) = list.splitAt(list.size / 2)
            val sortedLeft = mergeSort(left)
            val sortedRight = mergeSort(right)
            merge(sortedLeft,sortedRight)
        }

    }

    def merge(left: List[Int], right: List[Int]):List[Int] = {

      def auxFunction(merged:List[Int],leftRemaining: List[Int], rightRemaining: List[Int]):List[Int] ={
          (leftRemaining,rightRemaining) match {
              case (Nil, hr :: tr) => auxFunction(hr :: merged, Nil, tr)
              case (hl :: tl, Nil) => auxFunction(hl :: merged, tl, Nil)
              case (hl :: tl, hr :: tr) if hl < hr => auxFunction(hl :: merged, tl, rightRemaining)
              case (hl :: tl, hr :: tr)  => auxFunction(hr :: merged, leftRemaining, tr)
              case (Nil,Nil) => merged.reverse
          }

      }
        auxFunction(List(),left,right)

    }

}