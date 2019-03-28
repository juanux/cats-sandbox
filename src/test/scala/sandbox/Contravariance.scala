package sandbox

import org.scalatest.{FlatSpec, Matchers}

class Contravariance extends FlatSpec with Matchers{

  class Person {}
  class User extends Person{}
  class Admin extends User{}
  class SystemUser[-T]

  def setUser(box:SystemUser[Admin]):Option[String] = {
    None
  }

  it should " create a UserSystem receiving a User for a parametrized Admin" in {

    setUser(new SystemUser[Admin]) shouldBe None
    setUser(new SystemUser[User])   shouldBe None

  }

}
