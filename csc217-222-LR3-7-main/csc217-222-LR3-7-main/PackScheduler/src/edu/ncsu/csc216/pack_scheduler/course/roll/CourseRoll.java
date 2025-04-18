/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.roll;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.ArrayQueue;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;

/**
 * CourseRoll manages a list of Students enrolled in the course. CourseRoll establishes the 
 * max number of students that can enroll in the course. The max number of enrolled students can be 
 * changed at any point given that the new cap is larger than the number of enrolled students. 
 * CourseRoll contains methods that return the max number of students allowed to enroll and the number 
 * of "seats" that remain in the course. CourseRoll can also enroll(add) and drop(remove) students from 
 * the list. CourseRoll can also check if an entered student can enroll in the selected course. 
 * @author jpkenlin
 *
 */
public class CourseRoll {
	/** The LinkedAbstractList that holds the Student objects */
	private LinkedAbstractList<Student> roll = null;
	/** The max number of students that can enroll in the class */
	private int enrollmentCap;
	/** The minimum amount of students that should be allowed to enroll in a given course */
	private static final int MIN_ENROLLMENT = 10;
	/** The maximum amount of students that should be allowed to enroll in a given course */
	private static final int MAX_ENROLLMENT = 250;
	/** waitlist */
	private ArrayQueue<Student> waitlist;
	/** course */
	private Course c;
	
	/**
	 * Constructor for CourseRoll objects. This takes in an enrollmentCap and creates a 
	 * LinkedAbstractList of students with the enrollmentCap. 
	 * 
	 * @param c Course
	 * @param enrollmentCap max number of students that can enroll in the course
	 * @throws IllegalArgumentException if the enrollmentCap is less than 0 or smaller than
	 * the size of students in the course
	 */
	public CourseRoll(Course c, int enrollmentCap) throws IllegalArgumentException {
		if(c == null) {
			throw new IllegalArgumentException("Course null");
		}
		this.c = c;
		setEnrollmentCap(enrollmentCap);
		roll = new LinkedAbstractList<Student>(enrollmentCap);
		waitlist = new ArrayQueue<Student>(10);
	}
	
	/**
	 * Gets the number of students that can still enroll in the course.
	 * @return the number of remaining open seats
	 */
	public int getOpenSeats() {
		return enrollmentCap - roll.size();
	}

	/**
	 * Returns the max number of students that the course can hold.
	 * @return the enrollmentCap
	 */
	public int getEnrollmentCap() {
		return enrollmentCap;
	}

	/**
	 * Sets the enrollmentCap. 
	 * @param enrollmentCap the enrollmentCap to set
	 * @throws IllegalArgumentException if the enrollmentCap is less than 0 or smaller than
	 * the size of students in the course
	 */
	public void setEnrollmentCap(int enrollmentCap) throws IllegalArgumentException {
		if (enrollmentCap < MIN_ENROLLMENT || enrollmentCap > MAX_ENROLLMENT) {
			throw new IllegalArgumentException();
		}
		
		if (roll != null) {
//			LinkedAbstractList<Student> temp = roll;
//			if (temp.size() > enrollmentCap) {
//				throw new IllegalArgumentException();
//			}
//			roll = new LinkedAbstractList<Student>(enrollmentCap);
//			for (int i = 0; i < temp.size(); i++) {
//				roll.add(i, temp.get(i));
				
			//}
			roll.setCapacity(enrollmentCap);
		}
		this.enrollmentCap = enrollmentCap;
		
	}
	
	/**
	 * Adds the entered student to the roll. 
	 * @param s student object to add to the roll.
	 * @throws IllegalArgumentException if the student object is null, if the student is 
	 * already enrolled in this course, or if the enrollmentCap has been reached. 
	 */
	public void enroll(Student s) throws IllegalArgumentException {
		if (s == null) {
			throw new IllegalArgumentException();
		}
		if (roll.size() == enrollmentCap) {
			waitlist.enqueue(s);
//			//might work
//			if(waitlist.size() < 10) {
//				waitlist.enqueue(s);
//			} else {
//				throw new IllegalArgumentException();
//			}
		} else {
		for (int i = 0; i < roll.size(); i++) {
			if (s.equals(roll.get(i))) {
				throw new IllegalArgumentException();
			}
		}
		try  {
			roll.add(s);
		} catch (NullPointerException | IllegalArgumentException | IndexOutOfBoundsException e) {
			throw new IllegalArgumentException();
		}
		}
	}
	
	/**
	 * Removes a student from the roll.
	 * @param s the student object to remove from the roll.
	 * @throws IllegalArgumentException if the student object is null. 
	 */
	public void drop(Student s) throws IllegalArgumentException {
		if (s == null) {
			throw new IllegalArgumentException();
		}
		boolean onRoll = false;
		for(int i = 0; i < roll.size(); i++) {
			if(s.equals(roll.get(i))) {
				onRoll = true;
			}
		}
		
		if(onRoll) {
			try {
				
				//for (int i = 0; i < roll.size(); i++) {
					//if (s.equals(roll.get(i))) {
						roll.remove(s);
						if(waitlist.size() > 0) {
							Student x = waitlist.dequeue();
							roll.add(x);
							x.getSchedule().addCourseToSchedule(c);
						}
					//}
				//}
			} catch (IndexOutOfBoundsException e) {
				throw new IllegalArgumentException();
			}
		} else {
			ArrayQueue<Student> waitlist2 = new ArrayQueue<Student>(10);
			int size = waitlist.size();
			if(waitlist.size() != 0) {
				for(int i = 0; i < size; i++) {
					Student x = waitlist.dequeue();
					if(!x.equals(s)) {
						waitlist2.enqueue(x);
					}
				}
				waitlist = waitlist2;
			}
		}
	}
	
	/**
	 * Checks if the entered student object can enroll in the course.
	 * If the max number of students the can enroll has been reached, or 
	 * if the student is already enrolled in the course. 
	 * @param s the student object trying to enroll
	 * @return true if the student can enroll, false otherwise
	 */
	public boolean canEnroll(Student s) {
		boolean canEnroll = true;
		if (roll.size() == enrollmentCap && waitlist.size() == 10) {
			canEnroll = false;
		}
		for (int i = 0; i < roll.size(); i++) {
			if (s.equals(roll.get(i))) {
				canEnroll = false;
			}
		}
		for (int i = 0; i < waitlist.size(); i++) {
			Student x = waitlist.dequeue();
			waitlist.enqueue(x);
			if(x.equals(s)) {
				canEnroll = false;
			}
		}
		return canEnroll;
	}
	
	/**
	 * returns number of Students on the waitlist
	 * 
	 * @return waitlist size
	 */
	public int getNumberOnWaitlist() {
		return waitlist.size();
	}

}
