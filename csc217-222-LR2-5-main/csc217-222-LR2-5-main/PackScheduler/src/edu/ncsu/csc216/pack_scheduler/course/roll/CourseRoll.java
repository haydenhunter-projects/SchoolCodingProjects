/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.roll;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;

/**
 * The course roll class adds a roll for a course that has a list of students enrolled in it and a capacity
 * @author nolanhurst
 *
 */
public class CourseRoll {
	/** The students enrolled in a class */
	private LinkedAbstractList<Student> roll;
	
	/** the roll’s enrollment capacity */
	private int enrollmentCap;
	
	/** Minimum number of students that can enroll in a class */
	private static final int MIN_ENROLLMENT = 10;
	
	/** Maximum number of students that can enroll in a class */
	private static final int MAX_ENROLLMENT = 250;
	
	/**
	 * Course Roll constructor that sets the enrollment cap and creates a new LinkedAbstractList using the
	 * enrollment cap parameter
	 * @param enrollmentCap the max number of students that can enroll in a course
	 */
	public CourseRoll(int enrollmentCap) {
		setEnrollmentCap(enrollmentCap);
		roll = new LinkedAbstractList<Student>(enrollmentCap);
	}
	
	/**
	 * getter method for the enrollment cap and it just returns the enrollment cap
	 * @return the enrollment cap field
	 */
	public int getEnrollmentCap() {
		return enrollmentCap;
	}
	
	/**
	 * setter method for enrollment cap and checks if the enrollment cap is within the max and min enrollment
	 * possible and if so sets the instance variable to the parameter. 
	 * @param enrollmentCap the cap of students that can enroll in a class
	 * @throws IllegalArgumentException if the enrollment cap paramenter is not within 10 and 250 (max and
	 * min enrollment).
	 */
	public void setEnrollmentCap(int enrollmentCap) {
		if (enrollmentCap < MIN_ENROLLMENT || enrollmentCap > MAX_ENROLLMENT) {
			throw new IllegalArgumentException();
		}
		if (roll != null && enrollmentCap < roll.size()) {
			throw new IllegalArgumentException();
			
		}
		this.enrollmentCap = enrollmentCap;
	}
	
	/**
	 * This methods returns the enrollment cap subtracted by the size of roll (number of students already 
	 * enrolled) and returns the number
	 * @return the number of open seats for a class
	 */
	public int getOpenSeats() {
		return enrollmentCap - roll.size();
	}
	
	/**
	 * Trys to add a student to a course and if the add method throws and exception it catches it and 
	 * throws and IllegalArgumentExcpetion
	 * @param s a student to add to a course
	 * @throws IllegalArgumentException if the add method throws an exception, if the student is null, 
	 * if the student is already enrolled, and if the enrollment if full
	 */
	public void enroll(Student s) {
		if (s == null) {
			throw new IllegalArgumentException();
		}
		
		if (!canEnroll(s)) {
			throw new IllegalArgumentException();
		}
		
		try {
			roll.add(s);
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * This method is to remove student from the enrollment, drop checks for a null student then 
	 * finds the student that is supposed to drop the class and removes them.
	 * @param s student to drop from class
	 * @throws IllegalArgumentException if the student to remove is null or if remove throws an exception
	 */
	public void drop(Student s) {
		if (s == null) {
			throw new IllegalArgumentException();
		}
		
		for (int i = 0; i < roll.size(); i++) {
			if (roll.get(i).equals(s)) {
				roll.remove(i);
				
			}
		}
	}
	
	/**
	 * Checks if the enrollment is full and returns false if so, also checks that s isn't already enrolled
	 * and if they are returns false, else returns true
	 * @param s student to check of they can be enrolled
	 * @return true if the student can be enrolled and false if they cannot
	 */
	public boolean canEnroll(Student s) {
		if (roll.size() == enrollmentCap) {
			return false;
		}
		
		if (roll.size() == 0) {
			return true;
		}
		
		for (int i = 0; i < roll.size(); i++) {
			if (roll.get(i).equals(s)) {
				return false;
			}
		}
		return true;
	}
	
	
	
}
