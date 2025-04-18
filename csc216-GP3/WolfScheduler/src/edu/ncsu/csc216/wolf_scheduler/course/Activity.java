package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * An abstract superclass of course for the purpose of allowing schedule to hold
 * both courses an events.
 * 
 * @author hchunter
 *
 */
public abstract class Activity implements Conflict {

	/** Course's title. */
	private String title;
	/** Course's meeting days */
	private String meetingDays;
	/** Course's starting time */
	private int startTime;
	/** Course's ending time */
	private int endTime;
	/** Course's hour time upper boundary is 24 */
	private static final int UPPER_HOUR = 24;
	/** Course's minute time upper boundary is 60 */
	private static final int UPPER_MINUTE = 60;

	/**
	 * Activity constructor
	 * 
	 * @param title       - The activities title
	 * @param meetingDays - The activities meeting days
	 * @param startTime   - The activities start time
	 * @param endTime     - The activities end time
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		super();
		setTitle(title);
		setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}

	/**
	 * The short display array is used to populate the rows of the course catalog
	 * and student schedule
	 * 
	 * @return A string array for either the course catalog or students schedule
	 */
	public abstract String[] getShortDisplayArray();

	/**
	 * The full display array is used to display the final schedule.
	 * 
	 * @return a 7 column full display
	 */
	public abstract String[] getLongDisplayArray();

	/**
	 * Returns the Activities title.
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the Activities title.
	 * 
	 * @param title the title to set
	 * @throws IllegalArgumentException with messaged Invalid title if title is null
	 *                                  or an empty string.
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
	 * Returns the Activities Meeting Days
	 * 
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Returns the Activities Start Time.
	 * 
	 * @return the startTime
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Returns the Activities end time.
	 * 
	 * @return the endTime
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * Initializes meeting days, start times, and end times of a given Activity and
	 * verifies that they are valid to be initialized.
	 * 
	 * @param meetingDays The days the Activities meets
	 * @param startTime   The Activities start time
	 * @param endTime     The Activities end time
	 * @throws IllegalArgumentException if meeting days is null, empty, or contains
	 *                                  invalid characters if an arranged class has
	 *                                  non-zero start or end times if start time is
	 *                                  an incorrect time if end time is an
	 *                                  incorrect time if end time is less than
	 *                                  start time
	 */
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {

		// Throws IAE if meeting days is null or the string is empty
		if ("".equals(meetingDays) || meetingDays == null) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}

		// no night classes
		if (endTime < startTime) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}

		int startTimeHours = 0;
		int startTimeMin = 0;
		int endTimeHours = 0;
		int endTimeMin = 0;
		// military time to standard time
		try {
			startTimeHours = startTime / 100;
			startTimeMin = startTime % 100;

			endTimeHours = endTime / 100;
			endTimeMin = endTime % 100;
		} catch (NullPointerException e) {
			throw new IllegalArgumentException("Invalid meeting days and times");
		}

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

	/**
	 * Converts military time to standard time, and returns the Activities meeting
	 * days, and time it meets.
	 * 
	 * @return a string consisting of a Activities meeting days and time it meets
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (endTime != other.endTime)
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
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
	 * Used to check for duplicates for either events or courses.
	 * 
	 * @param activity - activity to check
	 * @return true if it is a duplicate and false if not
	 */
	public abstract boolean isDuplicate(Activity activity);

	/**
	 * The checkConflict() method is an instance method of Activity. That means it
	 * compares the current instance (i.e., this) with the parameter
	 * possibleConflictingActivity to see if there is any conflict in their days and
	 * times. If there is a conflict, the checked ConflictException is thrown. If
	 * there is no conflict, nothing happens and the statement in the client code
	 * following the call to checkConflict() is executed.
	 */
	@Override
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
		// System.out.println("---");
		// System.out.println(possibleConflictingActivity);
		// System.out.println("---");
		// System.out.println(this);
		// System.out.println("---");
		int compareStartTime = possibleConflictingActivity.getStartTime();
		int compareEndTime = possibleConflictingActivity.getEndTime();
		String compareWeekDays = possibleConflictingActivity.getMeetingDays();

		int thisStartTime = this.getStartTime();
		int thisEndTime = this.getEndTime();
		String thisWeekDays = this.getMeetingDays();
		for (int i = 0; i < compareWeekDays.length(); i++) {
			for (int j = 0; j < thisWeekDays.length(); j++) {

				if (!("A".equals(compareWeekDays) || "A".equals(thisWeekDays))
						&& compareWeekDays.charAt(i) == thisWeekDays.charAt(j)) {
					// System.out.println("compareWeekDays " + compareWeekDays.charAt(i));
					// System.out.println("compareStartTime " + compareStartTime);
					// System.out.println("compareEndTime " + compareEndTime);
					// System.out.println("thisWeekDays " + thisWeekDays.charAt(j));
					// System.out.println("thisStartTime " + thisStartTime);
					// System.out.println("thisEndTime " + thisEndTime);
					// System.out.println("------");

					if (compareStartTime == thisStartTime || compareStartTime == thisEndTime) {
						throw new ConflictException("Schedule conflict.");
					}
					if (compareEndTime == thisEndTime || compareEndTime == thisStartTime) {
						throw new ConflictException("Schedule conflict.");
					}
					// System.out.println(compareEndTime + " > " + thisEndTime + " & " +
					// thisStartTime + " > " + compareStartTime);
					// System.out.println(thisEndTime + " < " + compareStartTime + " & " +
					// compareStartTime + " < " + thisStartTime);
					// System.out.println("---");
					if (compareEndTime > thisEndTime && thisStartTime > compareStartTime
							|| thisEndTime < compareStartTime && compareStartTime < thisStartTime) {
						throw new ConflictException();
					}
					// System.out.println(compareStartTime + " > " + thisStartTime + " & " +
					// compareEndTime + " < " + thisEndTime);
					// System.out.println(thisEndTime + " < " + compareEndTime + " & " +
					// thisStartTime + " > " + compareStartTime);
					// System.out.println("---");
					if (compareStartTime > thisStartTime && compareEndTime < thisEndTime) {
						throw new ConflictException("Schedule conflict.");
					}
				} // if not A if statement close
			} // j for loop close
		} // i for loop close
	} // method close

}
