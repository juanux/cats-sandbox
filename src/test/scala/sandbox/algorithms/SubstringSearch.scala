package sandbox.algorithms

import org.scalatest.{FlatSpec, Matchers}

class SubstringSearch extends FlatSpec with Matchers {

}

object SubstringSearch {

  def naiveSubstringSearch(text: String, pattern: String):Int =
    text.indices.find(i=> i + pattern.length < text.length &&
      pattern.indices.forall(j=> text( j + i) == pattern(j))).getOrElse(-1)

}
