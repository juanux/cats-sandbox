package sandbox.algorithms

import org.scalatest.{FlatSpec, Matchers}

class SubstringSearch extends FlatSpec with Matchers {

  it should "find a pattern in a specific text " in {

    val text = "Hola como estas, yo muy bien y tu que tal"
    val pattern = "yo muy bien"

    SubstringSearch.naiveSubstringSearch("Hola como estas, yo muy bien y tu que tal", "yo muy bien")
    text.indexOf(pattern)
  }

}

object SubstringSearch {

  def naiveSubstringSearch(text: String, pattern: String):Int =
    text.indices.find(i=> i + pattern.length < text.length &&
      pattern.indices.forall(j=> text( j + i) == pattern(j))).getOrElse(-1)

}
