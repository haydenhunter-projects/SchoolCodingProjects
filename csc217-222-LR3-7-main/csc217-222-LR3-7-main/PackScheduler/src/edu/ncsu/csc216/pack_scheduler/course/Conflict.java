/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

/**
 * The interface Conflict will throw a ConflictException, which is a custom
 * checked exception, whenever the Activity is in conflict of one another. It
 * will handle in the Activity hierarchy by comparing the current instance to a
 * parameter instance, and handle in the list of Activities.
 * 
 * @author Thien Nguyen
 *
 */
public interface Conflict {
	/**
	 * The method will compare the current Activity to the Activity in the parameter
	 * to check whether the parameter is already in conflict.
	 * 
	 * @param possibleConflictingActivity the Activity that could be in conflict
	 * @throws ConflictException if the Activity is in conflict of one another
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;

}
