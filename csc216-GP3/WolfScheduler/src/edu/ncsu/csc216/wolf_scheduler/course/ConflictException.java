/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * The ConflictException is an exception with the message, The course/activity
 * cannot be added due to a conflict. This method is used as a custom checked
 * exception that will force a user to deal with the conflict before moving
 * forward in the program.
 * 
 * @author hchunter
 *
 */
public class ConflictException extends Exception {

	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;

	/**
	 * A constructor for ConflictException with no parameters
	 * 
	 * 
	 */
	public ConflictException() {
		super("Schedule conflict.");
	}

	/**
	 * A constructor for ConflictException with a string parameter
	 * 
	 * @param message The default message should be Schedule conflict.
	 * 
	 */
	public ConflictException(String message) {
		super(message);
	}

}
