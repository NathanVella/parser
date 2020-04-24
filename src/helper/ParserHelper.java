package helper;

import exception.ParseError;
import model.BinOpNode;
import model.ConstNode;
import model.ExpNode;
import model.UnaryMinusNode;
import textio.TextIO;

public class ParserHelper {

  /**
   * Reads an expression from the current line of input and builds an expression tree that represents the expression.
   *
   * @return an ExpNode which is a pointer to the root node of the expression tree
   * @throws ParseError if a syntax error is found in the input
   */
  public static ExpNode expressionTree() throws ParseError {
    TextIO.skipBlanks();
    boolean negative;  // True if there is a leading minus sign.
    negative = false;
    if (TextIO.peek() == '-') {
      TextIO.getAnyChar();
      negative = true;
    }
    ExpNode exp;       // The expression tree for the expression.
    exp = termTree();  // Start with the first term.
    if (negative) {
      exp = new UnaryMinusNode(exp);
    }
    TextIO.skipBlanks();
    while (TextIO.peek() == '+' || TextIO.peek() == '-') {
      // Read the next term and combine it with the
      // previous terms into a bigger expression tree.
      char op = TextIO.getAnyChar();
      ExpNode nextTerm = termTree();
      exp = new BinOpNode(op, exp, nextTerm);
      TextIO.skipBlanks();
    }
    return exp;
  }

  /**
   * Reads a term from the current line of input and builds an expression tree that represents the expression.
   *
   * @return an ExpNode which is a pointer to the root node of the expression tree
   * @throws ParseError if a syntax error is found in the input
   */
  private static ExpNode termTree() throws ParseError {
    TextIO.skipBlanks();
    ExpNode term;  // The expression tree representing the term.
    term = factorTree();
    TextIO.skipBlanks();
    while (TextIO.peek() == '*' || TextIO.peek() == '/') {
      // Read the next factor, and combine it with the
      // previous factors into a bigger expression tree.
      char op = TextIO.getAnyChar();
      ExpNode nextFactor = factorTree();
      term = new BinOpNode(op, term, nextFactor);
      TextIO.skipBlanks();
    }
    return term;
  }

  /**
   * Reads a factor from the current line of input and builds an expression tree that represents the expression.
   *
   * @return an ExpNode which is a pointer to the root node of the expression tree
   * @throws ParseError if a syntax error is found in the input
   */
  private static ExpNode factorTree() throws ParseError {
    TextIO.skipBlanks();
    char ch = TextIO.peek();
    if (Character.isDigit(ch)) {
      // The factor is a number.  Return a ConstNode.
      double num = TextIO.getDouble();
      return new ConstNode(num);
    } else if (ch == '(') {
      // The factor is an expression in parentheses.
      // Return a tree representing that expression.
      TextIO.getAnyChar();  // Read the "("
      ExpNode exp = expressionTree();
      TextIO.skipBlanks();
      if (TextIO.peek() != ')') {
        throw new ParseError("Missing right parenthesis.");
      }
      TextIO.getAnyChar();  // Read the ")"
      return exp;
    } else if (ch == '\n') {
      throw new ParseError("End-of-line encountered in the middle of an expression.");
    } else if (ch == ')') {
      throw new ParseError("Extra right parenthesis.");
    } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
      throw new ParseError("Misplaced operator.");
    } else {
      throw new ParseError("Unexpected character \"" + ch + "\" encountered.");
    }
  }
}
