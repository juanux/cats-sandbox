package sandbox

import org.scalatest.{FlatSpec, Matchers}

class OptionTest extends FlatSpec with Matchers{

  "Option fold" should "Returns the result of applying $f to this $option's value if the $option is nonempty.  Otherwise, evaluates expression `ifEmpty`." in {
    Option("a").fold("Nothing")(value=> "Some value") shouldBe "Some value"
    Option(null).fold("Nothing")(value=> "Some value") shouldBe "Nothing"
  }

  "Option toLeft" should "Returns a [[scala.util.Right]] containing the given argument `right` if this is empty, or a [[scala.util.Left]] containing this $option's value if this $option is nonempty" in {
    Option("a").toLeft("Right").isLeft shouldBe true
    Option(null).toLeft("Right").isRight shouldBe true
  }

  "Option toRight" should "Returns a [[scala.util.Left]] containing the given argument `left` if this $option is empty, or a [[scala.util.Right]] containing this $option's value if this is nonempty." in {
    Option("a").toRight("Left").isRight shouldBe true
    Option(null).toRight("Left").isLeft shouldBe true
  }

  "Option collect" should "Returns a $some containing the result of applying `pf` to this $option's contained  value, '''if''' this option is  nonempty '''and''' `pf` is defined for that value. Returns $none otherwise." in {
    Option("a").collect{
      case "a" => "it's a 'a'"
    } shouldBe Option("it's a 'a'")

    Option("a").collect{
      case "b" => "it's a 'a'"
    } shouldBe None
  }

  "Option contains" should "Tests whether the option contains a given value as an element." in {
    Option("a").contains("a") shouldBe true
    Option("a").contains("b") shouldBe false
  }

  "Option exists" should "Returns true if this option is nonempty '''and''' the predicate $p returns true when applied to this $option's value. Otherwise, returns false." in {
    Option("a").exists(x=> x.toUpperCase == "A") shouldBe true
    Option.empty[String].exists(x=> x.toUpperCase == "A") shouldBe false
  }

  "Option filter" should "Returns this $option if it is nonempty '''and''' applying the predicate $p to  this $option's value returns true. Otherwise, return $none." in {
    Option("a").filter(x=> x.toUpperCase == "A") shouldBe Option("a")
  }

  "Option filterNot" should " Returns this $option if it is nonempty '''and''' applying the predicate $p to this $option's value returns false. Otherwise, return $none." in {
    Option("a").filterNot(x=> x.toUpperCase == "A") shouldBe None
  }

  "Option forall" should " Returns this $option if it is nonempty '''and''' applying the predicate $p to this $option's value returns false. Otherwise, return $none." in {
    Option("a").forall(x=> x == "a") shouldBe true
    Option.empty[String].forall(x=> x == "a") shouldBe true
    Option("a").forall(x=> x == "b") shouldBe false
  }


  "Option isDefined" should " Returns true if the option is an instance of $some, false otherwise." in {
    Option("a").isDefined shouldBe true
    Option.empty[String].isDefined shouldBe false
  }

  "Option isEmpty" should " Returns true if the option is $none, false otherwise.." in {
    Option("a").isEmpty shouldBe false
    Option.empty[String].isEmpty shouldBe true
  }

  "Option nonEmpty" should " Returns true if the option is $none, false otherwise.." in {
    Option("a").nonEmpty shouldBe true
    Option.empty[String].nonEmpty shouldBe false
  }

  "Option orElse" should "Returns this $option if it is nonempty, otherwise return the result of evaluating `alternative`" in {
    Option.empty[String].orElse(Option("other option")) shouldBe Option("other option")
    Option("a").orElse(Option("other option")) shouldBe Option("a")
  }

  "Option iterator" should "Returns a singleton iterator returning the $option's value if it is nonempty, or an empty iterator if the option is empty." in {
    Option("a").iterator.toList shouldBe Iterator("a").toList
    Option.empty[String].iterator.toList shouldBe List()
  }

  "Option toList" should "Returns a singleton list containing the $option's value  if it is nonempty, or the empty list if the $option is empty." in {
    Option("a").toList shouldBe List("a")
    Option.empty[String].iterator.toList shouldBe List()
  }

  "Option flatMap" should "Returns the result of applying $f to this $option's value if this $option is nonempty.* Returns $none if this $option is empty. Slightly different from `map` in that $f is expected to return an $option (which could be $none)." in {
    Option("a").flatMap(a=> Some(a+"b")) shouldBe Some("ab")
    Option("a").flatMap(a=> Some(a+"b")).flatMap(b=> Some(b+"c")) shouldBe Some("abc")
    Option("a").flatMap(a=> Option.empty[String]).flatMap(b=> Some(b +"c")) shouldBe None
    Option("a").flatMap(a=> Option("a")).flatMap(b=> Option.empty[String]) shouldBe None
  }

  "Option flatMap" should "(with different types) Returns the result of applying $f to this $option's value if this $option is nonempty.* Returns $none if this $option is empty. Slightly different from `map` in that $f is expected to return an $option (which could be $none)." in {
    Option(1).flatMap(a=> Some(a + 3)).flatMap(b=> Option(b.toString)).flatMap(c=> Option(c.concat("a"))) shouldBe Option("4a")

  }

  "Option flatMap" should "(composing options Returns the result of applying $f to this $option's value if this $option is nonempty.* Returns $none if this $option is empty. Slightly different from `map` in that $f is expected to return an $option (which could be $none)." in {
    val op1 = Option(1)
    val op2 = Option(2)
    val op3 = Option(3)

    op1.flatMap(x => op2.flatMap(y => Option(x + y).flatMap(z => op3.flatMap(s => Option(z + s))))) shouldBe Option(6)

  }

  "Option map" should "(with different types) Returns the result of applying $f to this $option's value if this $option is nonempty.* Returns $none if this $option is empty. Slightly different from `map` in that $f is expected to return an $option (which could be $none)." in {
    Option(1).map(x=> 1*2) shouldBe Option(2)
    Option(1).map(x=> 1*2).map(y=> (y*2).toString) shouldBe Option(4.toString)
    Option.empty[Int].map(x=> 1*2).map(y=> (y*2).toString) shouldBe None


  }

  "Option flatten" should "Returns the result of applying $f to this $option's value if this $option is nonempty. Returns $none if this $option is empty.  Slightly different from `map` in that $f is expected to return an $option (which could be $none)." in {
    Option(Option(1)).flatMap(x=> Option(x.map(_*2))).flatten shouldBe Option(2)
  }




}
