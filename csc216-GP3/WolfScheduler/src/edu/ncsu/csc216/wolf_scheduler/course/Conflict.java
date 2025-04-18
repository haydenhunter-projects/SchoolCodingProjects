/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * The Conflict interface is used to make sure there are no conflicts in a
 * schedule.
 * 
 * @author hchunter
 *
 */
public interface Conflict {

	/**
	 * The check conflict method is used to check an object for a conflict in the
	 * schedule.
	 * 
	 * @param possibleConflictingActivity an activity that is passed through to
	 *                                    check for a conflict
	 * @throws ConflictException an exception with the message The course/activity
	 *                           cannot be added due to a conflict
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;
}
