package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * A program that allows a user to input courses into a schedule
 * 
 * @author hchunter
 *
 */
public class Course {

	/** Course's name. */
	private String name;
	/** Course's title. */
	private String title;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	/** Course's meeting days */
	private String meetingDays;
	/** Course's starting time */
	private int startTime;
	/** Course's ending time */
	private int endTime;
	/** Course's minimum name length is 5 */
	private static final int MIN_NAME_LENGTH = 5;
	/** Course's maximum name length is 8 */
	private static final int MAX_NAME_LENGTH = 8;
	/** Course's minimum letter count is 1 */
	private static final int MIN_LETTER_COUNT = 1;
	/** Course's maximum letter count is 4 */
	private static final int MAX_LETTER_COUNT = 4;
	/** Course's section count must be 3 */
	private static final int DIGIT_COUNT = 3;
	/** Course's section must be length 3 */
	private static final int SECTION_LENGTH = 3;
	/** Course's maximum credit amount is 5 */
	private static final int MAX_CREDITS = 5;
	/** Course's minimum credit amount is 1 */
	private static final int MIN_CREDITS = 1;
	/** Course's hour time upper boundary is 24 */
	private static final int UPPER_HOUR = 24;
	/** Course's minute time upper boundary is 60 */
	private static final int UPPER_MINUTE = 60;

	/**
	 * Constructs a Course object with values for all fields.
	 * 
	 * @param name         name of Course
	 * @param title        title of Course
	 * @param section      section of Course
	 * @param credits      credit hours for Course
	 * @param instructorId instructor's unity id
	 * @param meetingDays  meeting days for Course as series of chars
	 * @param startTime    start time for Course
	 * @param endTime      end time for Course
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays,
			int startTime, int endTime) {
		setName(name);
		setTitle(title);
		setSection(section);
		setCredits(credits);
		setInstructorId(instructorId);
		setMeetingDaysAndTime(meetingDays, startTime, endTime);
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
	 * @param meetingDays  meeting days for Course as series of chars
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays) {
		this(name, title, section, credits, instructorId, meetingDays, 0, 0);
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

		// throw exception if name is null
		if (name == null) {
			throw new IllegalArgumentException("Invalid course name.");
		}

		// Throw exception if the name is an empty string
		// Throw exception if the name contains less than 5 character or greater than 8
		// characters
		if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
			throw new IllegalArgumentException("Invalid course name.");
		}

		// Check for pattern of L[LLL] NNN
		int letters = 0;
		int digits = 0;
		boolean flag = false;

		for (int i = 0; i < name.length(); i++) {
			if (!flag) {
				if (name.charAt(i) >= 'A' && name.charAt(i) <= 'Z') {
					letters++;
				} else if (name.charAt(i) == ' ') {
					flag = true;
				} else {
					throw new IllegalArgumentException("Invalid course name.");
				}
			} else if (flag) {
				if (name.charAt(i) >= '0' && name.charAt(i) <= '9') {
					digits++;
				} else {
					throw new IllegalArgumentException("Invalid course name.");
				}
			}

		} // for close

		// Check that the number of letters is correct
		if (letters < MIN_LETTER_COUNT || letters > MAX_LETTER_COUNT) {
			throw new IllegalArgumentException("Invalid course name.");
		}

		// Check that the number of digits is correct
		if (digits != DIGIT_COUNT) {
			throw new IllegalArgumentException("Invalid course name.");
		}

		this.name = name;
	}

	/**
	 * Returns the Course's title.
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the Course's title.
	 * 
	 * @param title the title to set
	 */
	public void setTitle(String title) {

		// Checks the length of the title string. If the length is zero, the string is
		// empty.
		// Throws invalid if null
		if (title == null || title.length() == 0) {
			throw new IllegalArgumentException("Invalid title.");
		}

		this.title = title;
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
	 * @throws IllegalArgumentException with message Invalid section when section is
	 *                                  null, an empty string, doesn't contain only
	 *                                  digits, or does not have a length of three.
	 */
	public void setSection(String section) {
		// Checks the length of the section string. If the length is zero, the string is
		// empty.
		// Throws invalid if null
		if (section == null || section.length() == 0) {
			throw new IllegalArgumentException("Invalid section.");
		}

		// initialize counter for digits
		int digits = 0;

		for (int i = 0; i < section.length(); i++) {
			if (section.charAt(i) >= '0' && section.charAt(i) <= '9') {
				digits++;
			} else {
				throw new IllegalArgumentException("Invalid section.");
			}
		}

		// Throws invalid if section digits is not 3
		if (digits != SECTION_LENGTH) {
			throw new IllegalArgumentException("Invalid section.");
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
	 */
	public void setCredits(int credits) {

		// if credits is out of bounds lower 1, or upper 5
		if (credits < MIN_CREDITS || credits > MAX_CREDITS) {
			throw new IllegalArgumentException("Invalid credits.");
		}

		this.credits = credits;
	}

	/**
	 * Returns the Course's InstructorId
	 * 
	 * @return the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * Sets the Course's Instructor Id
	 * 
	 * @param instructorId the instructorId to set
	 * @throws IllegalArgumentException if instructor id is null or an empty string.
	 */
	public void setInstructorId(String instructorId) {

		// Throws exception for instructor id being null or an empty string
		if (instructorId == null || instructorId.length() == 0) {
			throw new IllegalArgumentException("Invalid instructor id.");
		}

		this.instructorId = instructorId;
	}

	/**
	 * Returns the Course's Meeting Days
	 * 
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Returns the Course's Start Time.
	 * 
	 * @return the startTime
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Returns the Course's end time.
	 * 
	 * @return the endTime
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * Initializes meeting days, start times, and end times of a given course and
	 * verifies that they are valid to be initialized.
	 * 
	 * @param meetingDays The days the course meets
	 * @param startTime   The Courses start time
	 * @param endTime     The Courses end time
	 * @throws IllegalArgumentException if meeting days is null, empty, or contains
	 *                                  invalid characters if an arranged class has
	 *                                  non-zero start or end times if start time is
	 *                                  an incorrect time if end time is an
	 *                                  incorrect time if end time is less than
	 *                                  start time
	 */
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {

		// Throws IAE if meeting days is null or the string is empty
		if (meetingDays == null || meetingDays.length() == 0) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}

		if ("A".equals(meetingDays)) { // Arranged
			if (startTime != 0 || endTime != 0) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			this.meetingDays = meetingDays;
			this.startTime = 0;
			this.endTime = 0;
		} else { // Not Arranged
			int countM = 0;
			int countT = 0;
			int countW = 0;
			int countH = 0;
			int countF = 0;

			for (int i = 0; i < meetingDays.length(); i++) {
				if (meetingDays.charAt(i) == 'M') {
					countM++;
				} else if (meetingDays.charAt(i) == 'T') {
					countT++;
				} else if (meetingDays.charAt(i) == 'W') {
					countW++;
				} else if (meetingDays.charAt(i) == 'H') {
					countH++;
				} else if (meetingDays.charAt(i) == 'F') {
					countF++;
				} else {
					throw new IllegalArgumentException("Invalid meeting days and times.");
				}

			}

			// Checks to see if any letter is a duplicate
			if (countM > 1 || countT > 1 || countW > 1 || countH > 1 || countF > 1) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}

			// no night classes
			if (endTime < startTime) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}

			// military time to standard time
			int startTimeHours = startTime / 100;
			int startTimeMin = startTime % 100;

			int endTimeHours = endTime / 100;
			int endTimeMin = endTime % 100;

			if (startTimeHours < 0 || startTimeHours >= UPPER_HOUR) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}

			if (startTimeMin < 0 || startTimeMin >= UPPER_MINUTE) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}

			if (endTimeHours < 0 || endTimeHours >= UPPER_HOUR) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}

			if (endTimeMin < 0 || endTimeMin >= UPPER_MINUTE) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}

			this.meetingDays = meetingDays;
			this.startTime = startTime;
			this.endTime = endTime;

		}
	}

	/**
	 * Converts military time to standard time, and returns the course meeting days,
	 * and time it meets.
	 * 
	 * @return a string consisting of a courses meeting days and time it meets
	 */
	public String getMeetingString() {
		int standStartHour = startTime / 100;
		int standStartMin = startTime % 100;
		int standEndHour = endTime / 100;
		int standEndMin = endTime % 100;
		String startAmPm = "AM";
		String endAmPm = "AM";

		if ("A".equals(meetingDays)) {
			return "Arranged";
		}

		if (standStartHour >= 12) {
			standStartHour = standStartHour - 12;
			startAmPm = "PM";
		}

		if (standEndHour >= 12) {
			standEndHour = standEndHour - 12;
			endAmPm = "PM";
		}

		if (standStartHour == 0) {
			standStartHour = 12;

		}

		if (standEndHour == 0) {
			standEndHour = 12;

		}

		if (standStartMin < 10 && standEndMin < 10) {
			return meetingDays + " " + standStartHour + ":" + "0" + standStartMin + startAmPm + "-" + standEndHour + ":"
					+ "0" + standEndMin + endAmPm;
		}

		if (standStartMin < 10) {
			return meetingDays + " " + standStartHour + ":" + "0" + standStartMin + startAmPm + "-" + standEndHour + ":"
					+ standEndMin + endAmPm;
		}

		if (standEndMin < 10) {
			return meetingDays + " " + standStartHour + ":" + standStartMin + startAmPm + "-" + standEndHour + ":" + "0"
					+ standEndMin + endAmPm;
		}
		return meetingDays + " " + standStartHour + ":" + standStartMin + startAmPm + "-" + standEndHour + ":"
				+ standEndMin + endAmPm;
	}

	/**
	 * Generates a hashCode for Course using all fields.
	 * 
	 * @return hashCode for Course
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + credits;
		result = prime * result + endTime;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/**
	 * Compares a given object to this object for equality on all fields.
	 * 
	 * @param obj the Object to compare
	 * @return true if the objects are the same on all fields.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (endTime != other.endTime)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		if (startTime != other.startTime)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	/**
	 * Returns a comma separated value String of all Course fields.
	 * 
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
		if ("A".equals(meetingDays)) {
			return name + "," + title + "," + section + "," + credits + "," + instructorId + "," + meetingDays;
		}
		return name + "," + title + "," + section + "," + credits + "," + instructorId + "," + meetingDays + ","
				+ startTime + "," + endTime;
	}

}
