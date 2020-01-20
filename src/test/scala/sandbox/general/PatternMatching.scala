package sandbox.general

import org.scalatest.{FlatSpec, Matchers}
import cats.implicits._

class PatternMatching extends FlatSpec with Matchers{

it should "unapply method for Employee return name" in {

  val employee:Employee = Employee("name",23,"lastName", "position")
  Employee.unapply(employee) shouldBe "name".some
}

  it should "unapply method for Customer return email" in {

    val customer:Customer = Customer("name",23,"lastName", "email")
    Customer.unapply(customer) shouldBe "email".some
  }

  it should "apply pattern matching for get the name of given employee" in {
    val employee:Person = Employee("name",23,"lastName", "position")

   val employeeName:String = employee match {
      case Employee(name) => name
      case Customer(email) => "Customer"
    }

    employeeName shouldBe "name"

  }

  it should "apply pattern matching for get the email of given customer" in {
    val customer:Person = Customer("name",23,"lastName", "email")

    val customerEmail:String = customer match {
      case Employee(name) => name
      case Customer(customerEmail) => customerEmail
    }

    customerEmail shouldBe "email"

  }

  it should "validate the name of a user" in {
    val user1:Person = User("name",24,"lastName", "dfdsfs")
    val user2:Person = User("name",18,"lastName", "dfdsfs")

    def userAgeValid(user:Person):String = user match {
      case User(age) if age > 23 =>"Valid"
      case _ => "Invalid"
    }

    userAgeValid(user1) shouldBe "Valid"
    userAgeValid(user2) shouldBe "Invalid"

  }

  it should "Return the sum of the 2 first numbers of a list" in {
    val list: List[Int] = 1:: 2:: 3:: 4:: Nil

    val result: Int = list match {
      case List(a,b,_*) => a + b
      case _ => 0
    }

    result shouldBe 3

  }

  it should "return the first name using pattern matching" in {

    val names:String = "Juan Jose"


    def getFirstName(names:String):String ={
      names match{
        case GivenNames(firstName,_*) => firstName
        case _ => ""
      }

    }

    getFirstName(names)  shouldBe "Juan"

  }

  it should "given a tuple of names and scores return the names of the player with score higher than 10000" in {

    def gameResults(): Seq[(String, Int)] = ("Daniel", 3500) :: ("Melissa", 13000) :: ("John", 7000) :: Nil

    val result:Seq[String] = for{
      (name,score) <-gameResults()
    if(score > 10000)

    }yield name

    result shouldBe ("Melissa") :: Nil

  }

  it should "filter using anonymous functions to remove words between 3 and 25" in {

    val wordFrequencies: List[(String, Int)] = List(
         ("habitual", 6),
         ("and", 56),
         ("consuetudinary", 2),
         ("additionally", 27),
         ("homely", 5),
         ("society", 13))


    val result: List[String] = wordFrequencies.filter{
      case (_,f) => f > 3 && f < 25
    }.map{
      case (w,_) => w
    }

    result shouldBe List("habitual", "homely", "society")

  }

  it should "filter using partial functions to remove words between 3 and 25" in {

    val wordFrequencies: List[(String, Int)] = List(
      ("habitual", 6),
      ("and", 56),
      ("consuetudinary", 2),
      ("additionally", 27),
      ("homely", 5),
      ("society", 13))


    val result: List[String] = wordFrequencies.collect{
      case (word,freq) if freq > 3 && freq < 25 => word
    }

    result shouldBe List("habitual", "homely", "society")

  }

}

trait Person{
  def name:String
  def age: Int

}
class Employee(val name:String, val age:Int,val lastName:String, val position:String) extends Person
class Customer(val name:String, val age:Int,val lastName:String, val email:String) extends Person
class User(val name:String, val age:Int,val lastName:String,val accountId:String) extends Person

object Employee{
  def apply(name: String, age: Int, lastName: String, position: String): Employee = new Employee(name, age, lastName, position)

  def unapply(arg: Employee): Option[String] = arg.name.some

}

object Customer{

  def apply(name: String, age: Int, lastName: String, email: String): Customer = new Customer(name, age, lastName, email)

  def unapply(arg: Customer): Option[String] = arg.email.some
}

object User {
  def apply(name: String, age: Int, lastName: String, accountId: String): User = new User(name, age, lastName, accountId)

  def unapply(arg: User): Option[Int] = arg.age.some
}

object GivenNames{
  def unapplySeq(name:String):Option[Seq[String]] ={
    val names: Seq[String] = name.trim.split(" ")

    if(names.forall(_.isEmpty)) None else names.some

  }
}


