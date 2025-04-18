/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Custom exception used for the Conflict interface
 * 
 * @author Hari Vemulapalli
 */
public class ConflictException extends Exception {
	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;
	/** String used to print out custom exception message */
	private String custom;

	/**
	 * Creates constructor for a custom message to call the exception.
	 * 
	 * @param customMessage - message set by user input
	 */
	public ConflictException(String customMessage) {
		custom = customMessage;
	}

	/**
	 * Creates constructor for the default message to call the exception.
	 */
	public ConflictException() {
		custom = "Schedule conflict.";
	}

	/**
	 * Returns the message when the exception is called.
	 * 
	 * @return custom
	 */
	public String getMessage() {
		return custom;
	}
}
