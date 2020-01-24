package sandbox.general

import org.scalatest.prop.TableDrivenPropertyChecks._
import org.scalatest.{FunSuite, Matchers}
import org.scalatest.prop.Tables.Table

class ExpressionValidatorTest extends FunSuite with Matchers {

  object ExpressionValidator {
    import scala.annotation.tailrec

    private val matchingBrackets = Map('(' -> ')', '{' -> '}', '[' -> ']')
    private val openBrackets = matchingBrackets.keySet

    def validate(input: String): Boolean = {

      @tailrec
      def isValid(input: List[Char], stack: List[Char] = Nil): Boolean = (input, stack) match {
        case (nextBracket +: tail, _) if openBrackets.contains(nextBracket) => isValid(tail, nextBracket +: stack)
        case (nextBracket +: tail, lastBracket +: stackTail) if matchingBrackets(lastBracket) == nextBracket => isValid(tail, stackTail)
        case (Nil, _) => stack.isEmpty
        case _ => false
      }

      input.length % 2 == 0 && isValid(input.toCharArray.toList)
    }
  }

  test("validate") {
    val inputs = Table(
      ("expression", "expectedResult"),
      ("()", true),
      ("({})", true),
      ("({[]})", true),
      ("({[()]})", true),
      ("({[()]}())", true),
      ("()()()", true),
      ("(", false),
      (")", false),
      ("({)}", false),
      ("({)", false),
      ("(]", false),
      ("({[(])})", false),
        ("({[()]}())", true)
    )

    forAll(inputs) { case (input, expected) => ExpressionValidator.validate(input) shouldBe expected }
  }

}
