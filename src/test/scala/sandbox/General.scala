package sandbox

import org.scalatest.{FlatSpec, Matchers}

import scala.annotation.tailrec

class General  extends FlatSpec with Matchers{

  def fib(n:Int):Int={

    @tailrec
    def go(n:Int,acc0:Int, acc1:Int, acc2:Int):Int ={
        if(acc0 > n) acc2
        else go(n,acc0 + 1 ,acc2,acc1 + acc2)
    }

    if(n == 0) 0
    else if(n == 1) 1
    else go(n,2,0,1)

  }

  "fib function " should "return a the sum of the 2 previous numbers" in {

    fib(0) shouldBe 0
    fib(1) shouldBe 1
    fib(2) shouldBe 1
    fib(3) shouldBe 2
    fib(4) shouldBe 3
    fib(5) shouldBe 5
    fib(6) shouldBe 8
    fib(7) shouldBe 13
    fib(8) shouldBe 21
    fib(9) shouldBe 34
    fib(10) shouldBe 55
    fib(11) shouldBe 89
    fib(12) shouldBe 144
  }

  def isSorted[A](as: Array[A],ordered:(A,A) => Boolean) : Boolean ={

     
      @tailrec
      def go(n:Int): Boolean = {
        if(n == 0) true
        else if(ordered(as(n),as(n-1))) go(n-1)
        else false
      }

      if(as.size == 1) true
      else go(as.size -1)

  }

  def curry[A,B,C](f: (A,B) => C): A => (B => C) = (a:A) => (b:B) => f(a,b)

  def uncurry[A,B,C](f:A => B => C): (A,B) =>  C =  (a:A,b:B) => f(a)(b)

  def compose[A,B,C](f:B=> C, g: A => B): A => C = (a:A) => f(g(a))

  def modN(n: Int)=(x: Int) => ((x % n) == 0) //This is the same that ´def modN(n: Int)(x: Int) = ((x % n) == 0)´ 

  def max(n:Int) = (m:Int) => Math.max(n,m) 
  def max2(n:Int)(m:Int) = Math.max(n,m)
     
  "isSorted function " should "return true for sorted list a > b" in {

    val list:Array[Int] = Array(1,2,3,4,5,6,7)

    isSorted[Int](list,(a:Int,b:Int)=> a>b) shouldBe true

  }

  "isSorted function " should "return false for unsorted list a > b" in {

    val list:Array[Int] = Array(1,3,2,4,5,6,7)

    isSorted[Int](list,(a:Int,b:Int)=> a>b) shouldBe false

  }

   "isSorted function " should "return true for sorted list of strings according the lenght" in {

    val list:Array[String] = Array("1","32","222","2224","22225","222226","72222222")

    isSorted[String](list,(a:String,b:String)=> a.size>=b.size) shouldBe true

  }

  "isSorted function " should "return false for unsorted list of strings according the lenght" in {

    val list:Array[String] = Array("1","32","222","2224","225","222226","7222222")

    isSorted[String](list,(a:String,b:String)=> a.size>=b.size) shouldBe false

  }

}
