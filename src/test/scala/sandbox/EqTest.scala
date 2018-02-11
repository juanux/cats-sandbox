package sandbox

import org.scalatest.FlatSpec

class EqTest extends FlatSpec{

  "An empty Set" should "have size 0" in {

    assert(Set.empty.size == 0)

  }

}
