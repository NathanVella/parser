package model;

/**
 * An expression node to represent a unary minus operator.
 */
public class UnaryMinusNode extends ExpNode {

  ExpNode operand;  // The operand to which the unary minus applies.

  public UnaryMinusNode(ExpNode operand) {
    // Construct a UnaryMinusNode with the specified operand.
    assert operand != null;
    this.operand = operand;
  }

  public double value() {
    // The value is the negative of the value of the operand.
    double neg = operand.value();
    return -neg;
  }

  public void printStackCommands() {
    // To evaluate this expression on a stack machine, first do
    // whatever is necessary to evaluate the operand, leaving the
    // operand on the stack.  Then apply the unary minus (which means
    // popping the operand, negating it, and pushing the result).
    operand.printStackCommands();
    System.out.println("\tUnary minus");
  }
}
