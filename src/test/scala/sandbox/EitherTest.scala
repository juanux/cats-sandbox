package sandbox

import org.scalatest.{FlatSpec, Matchers}

class EitherTest extends FlatSpec with Matchers{

  "Either.left" should "allows for-comprehensions over the left side of `Either` instances,reversing `Either`'s usual right-bias." in {

    val either1:Either[String,String] = Left("1")
    val either2:Either[String,String] = Left("2")

   val result = for{
    a<-either1.left
    b<-either2.left
    }yield a.concat(b)

    result shouldBe Left("12")

  }

  "Either.fold" should "Applies `fa` if this is a `Left` or `fb` if this is a `Right`" in {

    val either1:Either[String,String] = Left("1")
    val either2:Either[String,String] = Right("1")

    either1.fold(a=>"a",b=>"b") shouldBe "a"
    either2.fold(a=>"a",b=>"b") shouldBe "b"

  }

  "Either.swap" should "If this is a `Left`, then return the left value in `Right` or vice versa." in {

    val either1:Either[String,String] = Left("1")
    val either2:Either[String,String] = Right("1")

    either1.swap.fold(a=>"a",b=>"b") shouldBe "b"
    either2.swap.fold(a=>"a",b=>"b") shouldBe "a"

  }

  "Either.joinRight" should "Joins an `Either` through `Right`" in {

    val either1:Either[String,Either[String,Int]] = Right(Right(1))
    val either2:Either[String,Either[String,Int]] = Right(Left("1"))

    either1.joinRight shouldBe Right(1)
    either2.joinRight shouldBe Left("1")

  }

  "Either.joinLeft" should "Joins an `Either` through `Left`" in {

    val either1:Either[Either[String,Int],Int] = Left(Left("1"))
    val either2:Either[Either[String,Int],Int] = Left(Right(1))

    either1.joinLeft shouldBe Left("1")
    either2.joinLeft shouldBe Right(1)

  }

  "Either.filterOrElse" should " Returns `Right` with the existing value of `Right` if this is a `Right` and the given predicate `p` holds for the right value, or `Left(zero)` if this is a `Right` and the given predicate `p` does not hold for the right value, or `Left` with the existing value of " +
    "`Left` if this is a `Left`." in {

    Right(1).filterOrElse(_>2,0) shouldBe Left(0)
    Right(6).filterOrElse(_>2,0) shouldBe Right(6)

  }

}
