/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.course.validator.CourseNameValidator;
import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;

/**
 * Course class contains the course name, section number, the number of credit
 * hours, instructor’s unity id for a course record. A Course will be listed in
 * the catalog for the student to add to their schedule if it is valid. The
 * Course will provide all of the necessary information for the student to
 * consider while creating their schedule.
 * 
 * @author Thien Nguyen
 * @author Hayden Hunter
 *
 */
public class Course extends Activity implements Comparable<Course> {

	/** Course's name. */
	private String name;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	/** instance of the CourseNameValidatorclass */
	private CourseNameValidator cnv;
	/** roll object for storing students*/
	private CourseRoll roll;
	/** Required length of Course's section */
	private static final int SECTION_LENGTH = 3;
	/** Maximum credit hours of the Course */
	private static final int MAX_CREDITS = 5;
	/** Minimum credit hours of the Course */
	private static final int MIN_CREDITS = 1;

	/**
	 * Constructs a Course object with values for all fields.
	 * 
	 * @param name         name of Course
	 * @param title        title of Course
	 * @param section      section of Course
	 * @param credits      credit hours for Course
	 * @param instructorId instructor's unity id
	 * @param enrollmentCap max number of students that can be enrolled in the course
	 * @param meetingDays  meeting days for Course as series of chars
	 * @param startTime    start time for Course
	 * @param endTime      end time for Course
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays,
			int startTime, int endTime) {
		super(title, meetingDays, startTime, endTime);
		setName(name);
		setSection(section);
		setCredits(credits);
		setInstructorId(instructorId);
		roll = new CourseRoll(this, enrollmentCap);
	}

	/**
	 * Creates a Course with the given name, title, section, credits, instructorId,
	 * and meetingDays for courses that are arranged.
	 * 
	 * @param name         name of Course
	 * @param title        title of Course
	 * @param section      section of Course
	 * @param credits      credit hours for Course
	 * @param instructorId instructor's unity id
	 * @param enrollmentCap max number of students that can be enrolled in the course
	 * @param meetingDays  meeting days for Course as series of chars
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays) {
		this(name, title, section, credits, instructorId, enrollmentCap, meetingDays, 0, 0);
	}

	/**
	 * Gets the course roll
	 * @return the roll
	 */
	public CourseRoll getCourseRoll() {
		return roll;
	}
	/**
	 * Returns the Course's name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the Course's name. If the name is null, has a length less than 5 or more
	 * than 8, does not contain a space between letter characters and number
	 * characters, has less than 1 or more than 4 letter characters, and not exactly
	 * three trailing digit characters, an IllegalArgumentException is thrown.
	 * 
	 * @param name the name to set
	 * @throws IllegalArgumentException if the name parameter is invalid
	 */
	private void setName(String name) {
		if (name == null || "".equals(name)) {
			throw new IllegalArgumentException("Invalid course name.");
		}
		
		cnv = new CourseNameValidator();
		try {
			if (cnv.isValid(name)) {
				this.name = name;
			}
			else {
				throw new IllegalArgumentException("Invalid course name.");
			}
		} catch (InvalidTransitionException e) {
			throw new IllegalArgumentException("Invalid course name.");
		}
	}

	/**
	 * Returns the Course's section.
	 * 
	 * @return the section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * Sets the Course's section.
	 * 
	 * @param section the section to set
	 * @throws IllegalArgumentException if the section parameter is invalid
	 */
	public void setSection(String section) {
		if (section == null || section.length() != SECTION_LENGTH) {
			throw new IllegalArgumentException("Invalid section.");
		}
		for (int i = 0; i < section.length(); i++) {
			if (!Character.isDigit(section.charAt(i))) {
				throw new IllegalArgumentException("Invalid section.");
			}
		}
		this.section = section;
	}

	/**
	 * Returns the Course's credits.
	 * 
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * Sets the Course's credits.
	 * 
	 * @param credits the credits to set
	 * @throws IllegalArgumentException if the credits parameter is invalid
	 */
	public void setCredits(int credits) {
		if (credits < MIN_CREDITS || credits > MAX_CREDITS) {
			throw new IllegalArgumentException("Invalid credits.");
		}
		this.credits = credits;
	}

	/**
	 * Returns the Course's Instructor ID.
	 * 
	 * @return the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * Sets the Course's Instructor ID.
	 * 
	 * @param instructorId the instructorId to set
	 * @throws IllegalArgumentException if the instructorId parameter is an empty String
	 */
	public void setInstructorId(String instructorId) {
		if ("".equals(instructorId)) {
			throw new IllegalArgumentException("Invalid instructor id.");
		}
		this.instructorId = instructorId;
	}

	/**
	 * Returns a comma separated value String of all Course fields.
	 * 
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
		if ("A".equals(getMeetingDays())) {
			return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + roll.getEnrollmentCap() + ","
					+ getMeetingDays();
		}
		return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + roll.getEnrollmentCap() + "," + getMeetingDays()
				+ "," + getStartTime() + "," + getEndTime();
	}

	/**
	 * Generates a hashCode for Course using all fields.
	 * 
	 * @return hashCode for Course
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + instructorId.hashCode();
		result = prime * result + name.hashCode();
		result = prime * result + section.hashCode();
		return result;
	}

	/**
	 * Compares a given object to this object for equality on all fields.
	 * 
	 * @param obj the Object to compare
	 * @return true if the objects are the same on all fields
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (instructorId == null && other.getInstructorId() != null) {
			return false;
		}
		else if (instructorId != null && other.getInstructorId() == null) {
			return false;
		}
		if (instructorId == null && other.getInstructorId() == null) {
			if (!name.equals(other.name))
				return false;
			else if (!section.equals(other.section))
				return false;
			return true;
		}
		else if (!instructorId.equals(other.instructorId))
			return false;
		else if (!name.equals(other.name))
			return false;
		else if (!section.equals(other.section))
			return false;
		return true;
	}

	/**
	 * Returns the array that is used to to populate the rows of the course catalog
	 * and student schedule for the courses.
	 * 
	 * @return the array of length 4 containing the Course name, section, title, and
	 *         meeting string
	 */
	@Override
	public String[] getShortDisplayArray() {
		String[] array = new String[5];
		array[0] = getName();
		array[1] = getSection();
		array[2] = getTitle();
		array[3] = getMeetingString();
		array[4] = String.valueOf(roll.getOpenSeats());
		return array;
	}

	/**
	 * Returns the array that is used to display the final schedule for the courses.
	 * 
	 * @return the array of length 7 containing the Course name, section, title,
	 *         credits, instructorId, meeting string, and empty string
	 */
	@Override
	public String[] getLongDisplayArray() {
		String[] array = new String[7];
		array[0] = getName();
		array[1] = getSection();
		array[2] = getTitle();
		array[3] = Integer.toString(getCredits());
		array[4] = getInstructorId();
		array[5] = getMeetingString();
		array[6] = "";
		return array;
	}

	/**
	 * Sets the Course's meeting days and time. If the meeting days for the Course
	 * is arranged, then startTime and endTime will be 0. If the meeting days for
	 * Course are not arranged, then there must be a valid startTime and endTime.
	 * 
	 * @param meetingDays meeting days for Course as series of chars
	 * @param startTime   start time for Course
	 * @param endTime     end time for Course
	 * @throws IllegalArgumentException if the meetingDays, startTime, or endTime
	 *                                  parameters are invalid
	 */
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if ("A".equals(meetingDays)) {
			if (startTime != 0 || endTime != 0) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			int startTimeA = 0;
			int endTimeA = 0;
			super.setMeetingDaysAndTime(meetingDays, startTimeA, endTimeA);
		} else {
			int counterMon = 0;
			int counterTue = 0;
			int counterWed = 0;
			int counterThu = 0;
			int counterFri = 0;
			for (int i = 0; i < meetingDays.length(); i++) {
				if (meetingDays.charAt(i) == 'M') {
					counterMon++;
				} else if (meetingDays.charAt(i) == 'T') {
					counterTue++;
				} else if (meetingDays.charAt(i) == 'W') {
					counterWed++;
				} else if (meetingDays.charAt(i) == 'H') {
					counterThu++;
				} else if (meetingDays.charAt(i) == 'F') {
					counterFri++;
				} else {
					throw new IllegalArgumentException("Invalid meeting days and times.");
				}
			}
			if (counterMon > 1 || counterTue > 1 || counterWed > 1 || counterThu > 1 || counterFri > 1) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
		}
	}

	/**
	 * Determines whether the given activity is a duplicate of another activity (in
	 * this case course) already in the schedule.
	 * 
	 * @param activity the Activity to compare
	 * @return true if the two Courses have the same name
	 */
	@Override
	public boolean isDuplicate(Activity activity) {
		if (activity instanceof Course) {
			Course course = (Course) activity;
			return course.getName().equals(this.getName());
		}
		return false;
	}

	/**
	 * The method compares the current instance Course with the parameter c,
	 * another Course, for the sorted order based on their name and section
	 * 
	 * @return 1 if this Course is lexicographically follows c, the Course being
	 *         compared to, -1 if this Course is lexicographically precedes c, or 0
	 *         if this Course is equal to c.
	 * @param c the Course being compared to
	 * @throws NullPointerException if the Course c is null
	 * @throws ClassCastException   if c specified object's type prevents it from
	 *                              being compared to this Course object
	 */
	@Override
	public int compareTo(Course c) {
		if (c == null) {
			throw new NullPointerException();
		}
		if (getClass() != c.getClass()) {
			throw new ClassCastException();
		}
		if (this.name.compareTo(c.getName()) > 0) {
			return 1;
		} else if (this.name.compareTo(c.getName()) < 0) {
			return -1;
		} else {
			if (this.section.compareTo(c.getSection()) > 0) {
				return 1;
			} else if (this.section.compareTo(c.getSection()) < 0) {
				return -1;
			} else {
				return 0;
			}
		}
	}

}