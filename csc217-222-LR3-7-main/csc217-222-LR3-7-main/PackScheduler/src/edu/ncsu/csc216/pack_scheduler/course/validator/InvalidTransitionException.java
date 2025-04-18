/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * Exception for invalid transitions. If the current state cannot transition to the desired 
 * state, this exception is thrown. Default message is "Invalid FSM Transition" but there 
 * is a method that allows users to make their own message. 
 * @author jpkenlin
 *
 */
public class InvalidTransitionException extends Exception {

	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * ConflictException constructor with no received error message.
	 * Creates default exception message of "Schedule conflict".
	 */
	public InvalidTransitionException() {
		this("Invalid FSM Transition.");
	}
	
	/**
	 * ConflictException constructor that receives custom exception from caller.
	 * @param message - received exception message from caller 
	 */
	public InvalidTransitionException(String message) {
		super(message);
	}

}
