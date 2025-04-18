/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

/**
 * ConflictException is a custom checked exception that will be thrown whenever
 * the Activity is in conflict of another Activity. By using this checked
 * exception, clients of the Conflict interface must handle the exceptions in
 * their code.
 * 
 * @author Thien Nguyen
 *
 */
public class ConflictException extends Exception {
	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a custom Exception with a String specifying a message for the
	 * Exception object.
	 * 
	 * @param message message for the Exception object
	 */
	public ConflictException(String message) {
		super(message);
	}

	/**
	 * Constructs a custom Exception with an author specified default message.
	 * 
	 */
	public ConflictException() {
		super("Schedule conflict.");
	}
}
