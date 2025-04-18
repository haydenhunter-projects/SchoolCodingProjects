/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user.schedule;

import edu.ncsu.csc216.pack_scheduler.course.ConflictException;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.util.ArrayList;

/**
 * Schedule object that hold an ArrayList of Courses. The Schedule object
 * can add and remove Courses from the ArrayList. To add a Course, it must not be 
 * a duplicate or a conflict, and to remove a Course it must be in the schedule.
 * Schedule also has a title whose default value is "My Schedule." Schedule can 
 * return the Courses in an array with their names, sections, titles, and meeting
 * times for the GUI. If the schedule needs to be restarted, reset schedule sets
 * everything back to the default values. 
 * @author jpkenlin
 *
 */
public class Schedule {
	
	/** the title of this schedule object */
	private String title;
	/** the ArrayList of Course objects in this schedule */
	private ArrayList<Course> schedule;

	/**
	 * Constructor for the Schedule object. Sets an empty ArrayList 
	 * of courses called schedule and sets the title to the default 
	 * value of "My Schedule."
	 */
	public Schedule() {
		schedule = new ArrayList<Course>();
		setTitle("My Schedule");
	}
	
	/**
	 * Checks the schedule to see if the new course
	 * is a duplicate or a conflict. If the course is neither of these, 
	 * the course is added to the schedule and true is returned. 
	 * @param course the course object to be added to the schedule
	 * @return true if the course is successfully added to the schedule 
	 * @throws IllegalArgumentException if the course is a duplicate or a schedule conflict
	 */
	public boolean addCourseToSchedule(Course course) throws IllegalArgumentException {
		for (int i = 0; i < schedule.size(); i++) {
			if (schedule.get(i).isDuplicate(course)) {
				throw new IllegalArgumentException("You are already enrolled in " + course.getName());
			}
			try {
				schedule.get(i).checkConflict(course);
			} catch (ConflictException e) {
				throw new IllegalArgumentException("The course cannot be added due to a conflict.");
			}
		}
		schedule.add(course);
		return true;
	}
	
	/**
	 * Checks the schedule for a course that is equal 
	 * to the given course object. If a match is found, 
	 * it is removed from the schedule and true is returned.
	 * If no match is found, false is returned.
	 * @param course the course to be removed
	 * @return true if the course is removed, false if there was no matching course an nothing was removed.
	 */
	public boolean removeCourseFromSchedule(Course course) {
		for (int i = 0; i < schedule.size(); i++) {
			if (schedule.get(i).equals(course)) {
				schedule.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Clears the schedule and resets the title to the default value. 
	 */
	public void resetSchedule() {
		setTitle("My Schedule");
		schedule = new ArrayList<Course>();
	}
	
	/**
	 * Gets a 2D array of Strings for each Course in the schedule.
	 * Each row is a different Course with columns of name, section,
	 * title, and the meeting string
	 * @return 2D array of information for each Course in the schedule
	 */
	public String[][] getScheduledCourses() {
		String[][] courses = new String[schedule.size()][5];
		for (int i = 0; i < schedule.size(); i++) {
			courses[i] = schedule.get(i).getShortDisplayArray();
		}
		return courses;
	}
	
	/**
	 * Sets the title of the schedule
	 * @param title the desired new title
	 * @throws IllegalArgumentException if the given title is null
	 */
	public void setTitle(String title) throws IllegalArgumentException {
		if (title == null) {
			throw new IllegalArgumentException("Title cannot be null.");
		}
		this.title = title;
	}
	
	/**
	 * Gets the title of the schedule
	 * @return the title of the Schedule
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * Gets the total credits in the schedule
	 * @return the total amount of credits in the schedule
	 */
	public int getScheduleCredits() {
		int credits = 0; 
		for (int i = 0; i < schedule.size(); i++) {
			credits += schedule.get(i).getCredits();
		}
		return credits;
	}
	
	/**
	 * Checks if course can be added to the schedule
	 * @param course potential course to be added 
	 * @return true/false if course can be added
	 */
	public boolean canAdd(Course course) {
		if (course == null) {
			return false;
		}
		for (int i = 0; i < schedule.size(); i++) {
			if (schedule.get(i).isDuplicate(course)) {
				return false;
			}
			try {
				schedule.get(i).checkConflict(course);
			} catch (ConflictException e) {
				return false;
			}
		}
		return true;
//		for (int i = 0; i < schedule.size(); i++) {
//			if (course.equals(schedule.get(i))) {
//				return false;
//			}
////			if (course.getName().equals(schedule.get(i).getName()) && course.getSection().equals(schedule.get(i).getSection())) {
////				return false;
////			}
//			try{ 
//				schedule.get(i).checkConflict(course);
//				//course.checkConflict(schedule.get(i));
//
//			} catch(IllegalArgumentException e) {
//				return false;
//			}
//		}
		 
	}
	
}
