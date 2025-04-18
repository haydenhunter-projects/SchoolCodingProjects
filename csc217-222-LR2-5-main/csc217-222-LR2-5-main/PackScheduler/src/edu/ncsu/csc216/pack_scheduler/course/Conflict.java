/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Checks for a conflict with activities in the schedule
 * 
 * @author Hari Vemulapalli
 */
public interface Conflict {
	/**
	 * Checks if there are any conflicts between activities
	 * 
	 * @param possibleConflictingActivity - Activity object that may have a conflict
	 *                                    with another Activity object
	 * @throws ConflictException if a conflict is found
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;
}
