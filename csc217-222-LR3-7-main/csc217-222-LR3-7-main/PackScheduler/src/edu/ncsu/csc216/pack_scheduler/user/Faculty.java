package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.user.schedule.FacultySchedule;

/**
 * Faculty class that extends User. Faculty mostly relies on User
 * for its implementation. Howeve, it does have one unique field called
 * maxCourses. This is the max number of courses the faculty can teach
 * in a given semester. Faculty has a getter and setter for this field, as
 * well as the typical toString, hashCode, and equals methods.
 * @author jpkenlin
 *
 */
public class Faculty extends User {
	/** The max number of courses that a faculty can teach in a semester */
	private int maxCourses;
	/** Constant for the minimum that macCourses can be set to */
	private static final int MIN_COURSES = 1;
	/** Constant for the maximum that macCourses can be set to */
	public static final int MAX_COURSES = 3;
	/** The Faculty's schedule for the semester */
	private FacultySchedule facultySchedule;
	
	
	/**
	 * Faculty constructor. Creates Faculty object by calling the User constructor
	 * with all entered parameters except for maxCourses. The maxCourses are then set
	 * locally. The facultySchedule is created and passed the Faculty's id.
	 * @param firstName the Faculty's first name
	 * @param lastName the Faculty's last name
	 * @param id the Faculty's unityId
	 * @param email the Facutly's email
	 * @param hashPW the Faculty's password
	 * @param maxCourses the max number of courses that the Faculty can teach.
	 */
	public Faculty(String firstName, String lastName, String id, String email, String hashPW, int maxCourses) {
		super(firstName, lastName, id, email, hashPW);
		setMaxCourses(maxCourses);
		facultySchedule = new FacultySchedule(id);
	}
	
	/**
	 * Sets the max number of courses that the faculty can teach.
	 * @param maxCourses the max amount of courses that the Faculty can teach this semester
	 * @throws IllegalArgumentException if the entered amount is above 3 or less than 1
	 */
	public void setMaxCourses(int maxCourses) throws IllegalArgumentException {
		if(maxCourses < MIN_COURSES || maxCourses > MAX_COURSES) {
			throw new IllegalArgumentException("Invalid max courses");
		}
		this.maxCourses = maxCourses;
	}
	
	/**
	 * Returns the max number of courses a faculty can teach.
	 * @return the max number of courses a faculty can teach
	 */
	public int getMaxCourses() {
		return maxCourses;
	}
	
	/**
	 * Returns the Faculty's schedule
	 * @return the facultySchedule
	 */
	public FacultySchedule getSchedule() {
		return facultySchedule;
	}
	
	/**
	 * Returns true if the number of scheduled courses for this Faculty is greater than this Faculty's
	 * maxCourses.
	 * @return false if the number of scheduled courses for this Faculty is less than or equal to 
	 * this Faculty's maxCourses
	 */
	public boolean isOverloaded() {
		return facultySchedule.getNumScheduledCourses() > getMaxCourses();
	}

	/**
	 * HashCode method.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCourses;
		return result;
	}

	/**
	 * Equals method. Calls User's equals method and if that 
	 * returns true then the maxCourses are compared. If they are the 
	 * same the objects are the same and true is returned.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Faculty other = (Faculty) obj;
//		if (maxCourses != other.maxCourses)
		return maxCourses == other.maxCourses;
//		return true;
	}
	
	/**
	 * toString method for Faculty. Returns the elements separated by commas. The 
	 * elements are in this order: firstName, lastName, id, email, password, maxCredits
	 * @return String the string of elements separated by commas
	 */
	public String toString() {
		return this.getFirstName() + "," + this.getLastName() + "," + this.getId() + "," + this.getEmail() + "," + this.getPassword() + "," + maxCourses;
	}
	
}
