package model;

/**
 * Represents an expression node that holds a number.
 */
public class ConstNode extends ExpNode {

  double number;  // The number.

  public ConstNode(double val) {
    // Construct a ConstNode containing the specified number.
    number = val;
  }

  public double value() {
    // The value of the node is the number that it contains.
    return number;
  }

  public void printStackCommands() {
    // On a stack machine, just push the number onto the stack.
    System.out.println("\tPush " + number);
  }
}
