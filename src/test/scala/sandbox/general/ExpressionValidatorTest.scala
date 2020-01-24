package sandbox.general

import org.scalatest.prop.TableDrivenPropertyChecks._
import org.scalatest.{FunSuite, Matchers}
import org.scalatest.prop.Tables.Table

class ExpressionValidatorTest extends FunSuite with Matchers {

  object ExpressionValidator {
    import scala.annotation.tailrec

    private val matchingBrackets: Map[Char, Char] = Map('(' -> ')','{' -> '}','[' -> ']')
    private val openingBrackets: Set[Char] = matchingBrackets.keySet

    def validate(exp:String):Boolean = {
      @tailrec
      def isValid(exp: List[Char], stack: List[Char] = Nil): Boolean = (exp,stack) match{
        case (headExp:: tailExp, _) if openingBrackets.contains(headExp) =>isValid(tailExp, headExp :: stack)
        case (headExp:: tailExp, headStack:: tailStack) if matchingBrackets(headStack) == headExp => isValid(tailExp, tailStack)
        case (Nil, _) => stack.isEmpty
        case _ => false
      }

      exp.toCharArray.length % 2 == 0 && isValid(exp.toCharArray.toList)
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
        ("([()])", true)
    )

    forAll(inputs) { case (input, expected) => ExpressionValidator.validate(input) shouldBe expected }
  }

}
