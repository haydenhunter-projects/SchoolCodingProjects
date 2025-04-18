package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * A program that allows a user to input courses into a schedule in
 * WolfScheduluer
 * 
 * @author hchunter
 *
 */
public class Course extends Activity {

	/** Course's name. */
	private String name;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
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
		super(title, meetingDays, startTime, endTime);
		setName(name);
		setSection(section);
		setCredits(credits);
		setInstructorId(instructorId);

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
	 * @throws IllegalArgumentException with the message Invalid credits if credits
	 *                                  is less than 1 or greater than 5
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		return result;
	}

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
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
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
		return true;
	}

	/**
	 * Returns a comma separated value String of all Course fields.
	 * 
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
		if ("A".equals(getMeetingDays())) {
			return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + ","
					+ getMeetingDays();
		}
		return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays()
				+ "," + getStartTime() + "," + getEndTime();
	}

	/**
	 * The short display array is used to populate the rows of the course catalog
	 * and student schedule.
	 * 
	 * @return null
	 */
	public String[] getShortDisplayArray() {
		String[] arrTemp = new String[4];
		arrTemp[0] = name;
		arrTemp[1] = section;
		arrTemp[2] = getTitle();
		arrTemp[3] = getMeetingString();
		return arrTemp;
	}

	/**
	 * The full display array is used to display the final schedule.
	 * 
	 * @return null
	 */
	public String[] getLongDisplayArray() {
		String[] arrTemp = new String[7];
		arrTemp[0] = name;
		arrTemp[1] = section;
		arrTemp[2] = getTitle();
		arrTemp[3] = Integer.toString(credits);
		arrTemp[4] = instructorId;
		arrTemp[5] = getMeetingString();
		arrTemp[6] = "";
		return arrTemp;
	}

	/**
	 * This is an updated version of this method. Now it uses the activity
	 * superclass to allow it to work with Event.java. Checks for arranged or
	 * regular meeting days. Used to verify valid meetingDays, startTimes, and
	 * endTimes.
	 * 
	 * @param meetingDays - the days the activity meets
	 * @param startTime   - the time the activity starts
	 * @param endTime     - the time the activity ends
	 * @throws IllegalArgumentException with the message Invalid meeting days and
	 *                                  times if the class has arranged style
	 *                                  meetingDays but start and end times that are
	 *                                  not 0. if meeting days is any letter other
	 *                                  than A, M, T, W, H , F if any of those
	 *                                  characters appear more than once in the
	 *                                  string meetingDays
	 * 
	 */
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {

		if ("A".equals(meetingDays)) { // Arranged

			if (startTime != 0 || endTime != 0) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
		} else {
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

			if (countM > 1 || countT > 1 || countW > 1 || countH > 1 || countF > 1) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
		}
		super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}

	/**
	 * Checks for duplicates of Course(s)
	 * 
	 * @param activity - course you want to check for a duplicate of
	 * @return true - when there is a duplicate, and false - when there is no
	 *         duplicate
	 * 
	 */
	@Override
	public boolean isDuplicate(Activity activity) {
		return activity instanceof Course && ((Course) activity).getName().equals(this.getName());
	}

}
