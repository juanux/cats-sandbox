//This problem was asked by Uber.
//Given an array of integers, return a new array such that each element at index i of the new array is the product of all the numbers in the original array except the one at i.
//For example, if our input was [1, 2, 3, 4, 5], the expected output would be [120, 60, 40, 30, 24]. If our input was [3, 2, 1], the expected output would be [2, 3, 6].
//Follow-up: what if you can't use division?

object Solution{

  def multArray(array:List[Int]):List[Int] = {

    def process(currentList:List[Int],resultList:List[Int],next:Int):List[Int] = {


      (currentList,resultList) match {

        case (c,r) if r.size == c.size => r
        case (_::ct, r )   => {
          val prod:Int = ct.product
          process(ct:+next,r:+prod,ct.head)
        }

      }

    }
    if(array.isEmpty) Nil
    else process(array,Nil,array.head)

  }


}


Solution.multArray(List(1,2,3,4,5))
Solution.multArray(List(3,2,1))