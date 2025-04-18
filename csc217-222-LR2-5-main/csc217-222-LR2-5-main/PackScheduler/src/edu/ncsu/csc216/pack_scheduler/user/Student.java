package edu.ncsu.csc216.pack_scheduler.user;

import java.util.Objects;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * Creates a user interface for working with the StudentDirectory.
 * 
 * @author Sarah Heckman
 * @author Hayden Hunter
 * @author Hari Vemulapalli
 * @author Victor Hermida
 */
public class Student extends User implements Comparable<Student> {
	/** Maximum credits for a student */
	public static final int MAX_CREDITS = 18;
	/** Student's credit hours */
	private int maxCredits;
	
	/** creates a schedule object*/
	private Schedule schedule;

	/**
	 * Sets constructor for Student
	 * 
	 * @param firstName  The first name of the student
	 * @param lastName   The last name of the student
	 * @param id         The id of the student
	 * @param email      The email of the student
	 * @param hashPW     The password of the student
	 * @param maxCredits The total of credits of the student
	 */
	public Student(String firstName, String lastName, String id, String email, String hashPW, int maxCredits) {
		super(firstName, lastName, id, email, hashPW);
		setFirstName(firstName);
		setLastName(lastName);
		setId(id);
		setEmail(email);
		setPassword(hashPW);
		setMaxCredits(maxCredits);
		schedule = new Schedule();
	}

	/**
	 * Sets constructor for Student with the default credit hours, which is 18.
	 * 
	 * @param firstName The first name of the student
	 * @param lastName  The last name of the student
	 * @param id        The id of the student
	 * @param email     The email of the student
	 * @param hashPW    The password of the student
	 */
	public Student(String firstName, String lastName, String id, String email, String hashPW) {
		super(firstName, lastName, id, email, hashPW);
		setFirstName(firstName);
		setLastName(lastName);
		setId(id);
		setEmail(email);
		setPassword(hashPW);
		setMaxCredits(MAX_CREDITS);
		schedule = new Schedule();
	}

	/**
	 * Returning the max credits
	 * 
	 * @return The max credits
	 */
	public int getMaxCredits() {
		return maxCredits;
	}

	/**
	 * Setting the max credits for the student
	 * 
	 * @param maxCredits the max credits of the student
	 * @throws IllegalArgumentException if maxCredits is less than 3 or is greater
	 *                                  than 18
	 */
	public void setMaxCredits(int maxCredits) {
		if (maxCredits < 3 || maxCredits > MAX_CREDITS) {
			throw new IllegalArgumentException("Invalid max credits");
		}
		this.maxCredits = maxCredits;
	}

	/**
	 * Returns a comma separated value String of all student fields.
	 * 
	 * @return String representation of student
	 */
	@Override
	public String toString() {
		String cslist = getFirstName() + "," + getLastName() + "," + getId() + "," + getEmail() + "," + getPassword()
				+ "," + maxCredits;
		return cslist;
	}

	/**
	 * Returns a status of comparison between this Student and the other Student
	 */
	@Override
	public int compareTo(Student o) {
		String thisFirstName = this.getFirstName();
		String thisLastName = this.getLastName();
		String thisId = this.getId();
		String otherFirstName = o.getFirstName();
		String otherLastName = o.getLastName();
		String otherId = o.getId();
		if (thisLastName.compareTo(otherLastName) < 0) {
			return -1;
		} else if (thisLastName.compareTo(otherLastName) > 0) {
			return 1;
		} else {
			if (thisFirstName.compareTo(otherFirstName) < 0) {
				return -1;
			} else if (thisFirstName.compareTo(otherFirstName) > 0) {
				return 1;
			} else {
				if (thisId.compareTo(otherId) < 0) {
					return -1;
				} else if (thisId.compareTo(otherId) > 0) {
					return 1;
				} else {
					return 0;
				}
			}
		}
	}

	/**
	 * Provides the hashcode values of all fields and objects used in Student.java
	 * so the equals method works properly.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(maxCredits);
		return result;
	}

	/**
	 * Checks for any duplicates of fields or objects in Student.java
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return maxCredits == other.maxCredits;
	}

	/**
	 * Returns the schedule
	 * @return returns the schedule array
	 */
	public Schedule getSchedule() {
		return schedule;
	}
	/**
	 * Checks whether the course can be added to the schedule, and also makes sure that course credits is not above max credits.
	 * @param c Course to be added
	 * @return true if course can be added or false otherwise
	 */
	public boolean canAdd(Course c) {
		if(!schedule.canAdd(c)) {
			return false;
		}
		boolean canAdds = maxCredits >= c.getCredits() + schedule.getScheduleCredits();
		return canAdds;
	}
	
}
