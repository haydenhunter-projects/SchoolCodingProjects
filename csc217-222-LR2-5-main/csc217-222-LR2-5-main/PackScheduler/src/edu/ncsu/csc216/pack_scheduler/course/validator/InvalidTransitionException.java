/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * A custom exception used to represent an invalid transition in the FSM.
 * 
 * @author saket
 * @author hchunter
 */
public class InvalidTransitionException extends Exception {
	/**
	 * a unique identifier that allows the custom exception to be thrown.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * a constructor for the Invalid Transition Exception
	 * 
	 * @param message - the message associated with the custom exception
	 */
	public InvalidTransitionException(String message) {
		super(message);
	}

	/**
	 * the default constructor of the Invalid Transition Exception with default
	 * message "Invalid FSM Transition."
	 */
	public InvalidTransitionException() {
		super("Invalid FSM Transition.");
	}

}
