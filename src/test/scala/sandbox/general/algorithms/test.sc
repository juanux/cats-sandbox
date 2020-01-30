val listSequency:List[Int] = List(1,6,10,4,7,9,5)


listSequency.foldLeft(0)(_ - _)

listSequency.foldRight(0)(_ - _)

//5-0 = 5
//9-5 = 4
//7-4 = 3
//4 -3 = 1
//10 -1 = 9
// 6 -9 = -3
// 1 - (-3) = 4

def balancedBrackets(s: String): Boolean =
  s.substring(0,s.length/2)  == s.substring(Math.floorDiv(s.length(),2)).map {
    x => if(x == ')') '('
    else if(x == ']') '['
    else if(x == '}') '{'
  }.mkString.reverse

balancedBrackets("({({})})")

balancedBrackets("(]}){{)")

balancedBrackets("({[()]}())")



