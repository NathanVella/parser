import static helper.ParserHelper.expressionTree;

import model.ExpNode;
import exception.ParseError;
import textio.TextIO;

/**
 * This program reads standard expressions typed in by the user. The program constructs an expression tree to represent the
 * expression.  It then prints the value of the tree. It also uses the tree to print out a list of commands that could be
 * used on a stack machine to evaluate the expression. The expressions can use positive real numbers and the binary operators
 * +, -, *, and /. The unary minus operation is supported.
 *
 * The expressions are defined by the BNF rules:
 *   <expression>  ::=  [ "-" ] <term> [ [ "+" | "-" ] <term> ]...
 *   <term>  ::=  <factor> [ [ "*" | "/" ] <factor> ]...
 *   <factor>  ::=  <number>  |  "(" <expression> ")"
 *
 * A number must begin with a digit (i.e., not a decimal point). A line of input must contain exactly one such expression. If
 * extra data is found on a line after an expression has been read, it is considered an error.
 *
 * In addition to the main program class, SimpleParser, this program defines a set of four nested classes for implementing
 * expression trees.
 */

public class ParserApplication {

  public static void main(String[] args) {
    while (true) {
      System.out.println("Enter an expression, or press return to end.");
      System.out.print("?  ");
      TextIO.skipBlanks();
      if (TextIO.peek() == '\n') {
        break;
      }
      try {
        ExpNode exp = expressionTree();
        TextIO.skipBlanks();
        if (TextIO.peek() != '\n') {
          throw new ParseError("Extra data after end of expression.");
        }
        TextIO.getln();
        System.out.println("Value is " + exp.value());
        System.out.println("Order of postfix evaluation is:");
        exp.printStackCommands();
      } catch (ParseError e) {
        System.out.println("*** Error in input:   " + e.getMessage());
        System.out.println("*** Discarding input: " + TextIO.getln());
      }
    }

    System.out.println("Done.");
  }
}
