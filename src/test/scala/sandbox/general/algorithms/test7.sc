//This problem was recently asked by Google.
//Given a list of numbers and a number k, return whether any two numbers from the list add up to k.
//For example, given [10, 15, 3, 7] and k of 17, return true since 10 + 7 is 17.
//Bonus: Can you do this in one pass?

object Solution{

  def addUp(list:List[Int],k:Int):Boolean = {

    def validate(currentList:List[Int],l:List[Int]): Boolean ={

      currentList match {
        case head::tail if l.contains(k - head) => true
        case head::tail => validate(tail,l:+head)
        case Nil => false
      }
    }

    validate(list,Nil)

  }

}

Solution.addUp(List(10, 15, 3, 7),17)
Solution.addUp(List(16, 5, 0, 1,6),17)
Solution.addUp(List(4,90,0,-1,8,87,23,2,2,4,7),11)
Solution.addUp(List(4,90,0,-1,8,87,23,2,2,4,7),33)
Solution.addUp(List(),33)
Solution.addUp(List(4,90,0,-1,8,87,23,2,2,4,7),-3)



