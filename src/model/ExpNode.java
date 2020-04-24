package model;

/**
 * An abstract class representing any node in an expression tree. The three concrete node classes are concrete subclasses.
 * Two instance methods are specified, so that they can be used with any ExpNode. The value() method returns the value of the
 * expression. The printStackCommands() method prints a list of commands that could be used to evaluate the expression on a
 * stack machine (assuming that the value of the expression is to be left on the stack).
 */
public abstract class ExpNode {

  public abstract double value();

  public abstract void printStackCommands();
}
