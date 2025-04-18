package edu.ncsu.csc216.pack_scheduler.user.schedule;

import edu.ncsu.csc216.pack_scheduler.course.ConflictException;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.util.ArrayList;

/**
 * Schedule provides information for the GUI
 * A schedule has a title and contains a list of courses.
 * @author hchunter
 *
 */
public class Schedule {

	/**
	 * A custom array list of Course(s)
	 */
	private ArrayList<Course> schedule;
	
	/**
	 * the schedules title
	 */
	private String title;

	/**
	 * the constructor for Schedule
	 * creates an empty arraylist of courses
	 * sets the default title to My Schedule
	 */
	public Schedule() {
		this.schedule = new ArrayList<Course>();
		this.title = "My Schedule";
	}
	
	/**
	 * Will add a course to the schedule array list. Method will verify that the course
	 * is not a duplicate and does not have a conflict before adding to the schedule.
	 * 
	 * @param e the course we want to add to the schedule
	 * @return true if the course was added, false if the course was not added
	 * 
	 * @throws IllegalArgumentException if the student is already enrolled in the course
	 * 									or if there is a conflict with the course in the schedule.
	 */
	public boolean addCourseToSchedule(Course e) {
		boolean result = false;

		for (int i = 0; i < schedule.size(); i++) {
				
			//if duplicate throw correct IAE
			if (schedule.get(i).isDuplicate(e)) {
				throw new IllegalArgumentException("You are already enrolled in " + e.getName());
			}
			
			try {
				//uses currentCourse to check for a conflict 
				schedule.get(i).checkConflict(e);
				
			} catch (ConflictException ex1) {
				throw new IllegalArgumentException("The course could not be added due to a conflict. ");
			}
		}
			//if not a duplicate add to the end of the list
			//this could potentially throw null pointer but 
			//we can check that in custom array list versus here
			schedule.add(e);
			result = true;
		
			
		//if added returns true if not returns false
		return result;
	}
	
	/**
	 * method will search through the schedule array list and find the given course.
	 * If that course exists, the course will be removed from the list.
	 * 
	 * @param e The course we wish to be removed.
	 * @return true if the course has been removed, false if it has not.
	 */
	public boolean removeCourseFromSchedule(Course e) {
		
		for (int i = 0; i < schedule.size(); i++) {
			Course s = schedule.get(i);
			
			//checks for duplicate course
			//needs remove functionality
			if (s.equals(e)) {
				schedule.remove(i);
				return true;
			}
			
		}
		return false;
	}
	
	/**
	 * method will reset the schedule array list back to empty 
	 * and the title field back to its default value.
	 */
	public void resetSchedule() {
		schedule = new ArrayList<Course>();
		title = "My Schedule";
	}
	
	/** returns a 2d list of schedule where each row is a course and each columns holds the courses info
	 * 
	 * @return a 2d string array of the schedule with all relevant information.
	 * */
	public String[][] getScheduledCourses(){
		
		String[][] scheduledCourses = new String[schedule.size()][4];
		
		for(int i = 0; i < schedule.size(); i++) {
			Course c = schedule.get(i);
			scheduledCourses[i] = c.getShortDisplayArray();
		}
		
		return scheduledCourses;
		
	}
	
	/** 
	 * sets a new schedule title
	 * @param newTitle new title of the schedule
	 */
	public void setTitle(String newTitle) {
		if(newTitle == null) {
			throw new IllegalArgumentException("Title cannot be null.");
		}
		this.title = newTitle;
	}
	
	/**
	 * returns the title of the schedule
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Returns the number of credits of each course.
	 * 
	 * @return number of credits of each course in the schedule
	 */
	public int getScheduleCredits() {
		int creditss = 0;
		for(int i = 0; i < schedule.size(); i++) {
			creditss += schedule.get(i).getCredits();
		}
		return creditss;
		
		
	}
	
	/**
	 * Checks whether a course can be added to a schedule or not
	 * @param c New Course created
	 * @return true if the none of the checks hinder the course being added to the schedule, false otherwise.
	 */
	public boolean canAdd(Course c) {
		if(c == null) {
			return false;
		}
		for(int i = 0; i < schedule.size(); i++) {
			if(c.isDuplicate(schedule.get(i))) {
				return false;
			}
			
			
		
		try {
			c.checkConflict(schedule.get(i));
		} catch(ConflictException e) {
			return false;
		}
		
	}
		return true;
}
}
