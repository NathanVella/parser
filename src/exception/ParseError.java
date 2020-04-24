package exception;

/**
 * An object of type ParseError represents a syntax error found in the user's input.
 */
public class ParseError extends Exception {
  public ParseError(String message) {
    super(message);
  }
}