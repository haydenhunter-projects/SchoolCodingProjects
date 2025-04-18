package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Activity class contains the course title, meeting days, start time, and end
 * time so that it can help the schedule to hold both Courses and Events
 * classes. This is the superclass of the Course and Event classes that holds
 * the general and similar information so that its subclasses can expand more on
 * their differences.
 * 
 * @author Thien Nguyen
 *
 */
public abstract class Activity implements Conflict {

	/** Activity's title. */
	private String title;
	/** Activity's meeting days */
	private String meetingDays;
	/** Activity's starting time */
	private int startTime;
	/** Activity's ending time */
	private int endTime;
	/** Maximum hours in one day */
	private static final int UPPER_HOUR = 24;
	/** Maximum minutes in one hour */
	private static final int UPPER_MINUTE = 60;

	/**
	 * Constructs an Activity object with the given title, meetingDays, startTime,
	 * and endTime of the Course.
	 * 
	 * @param title       title of Activity
	 * @param meetingDays meeting days for Activity as series of chars
	 * @param startTime   start time for Activity
	 * @param endTime     end time for Activity
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		super();
		setTitle(title);
		setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}

	/**
	 * Returns the short display array that is used to populate the rows of the
	 * course catalog and student schedule.
	 * 
	 * @return the short display array
	 */
	public abstract String[] getShortDisplayArray();

	/**
	 * Returns the full display array that is used to display the final schedule.
	 * 
	 * @return the full display array
	 */
	public abstract String[] getLongDisplayArray();

	/**
	 * Returns the Activity's title.
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the Activity's title.
	 * 
	 * @param title the title to set
	 * @throws IllegalArgumentException if the title parameter is invalid
	 */
	public void setTitle(String title) {
		if (title == null || "".equals(title)) {
			throw new IllegalArgumentException("Invalid title.");
		}
		this.title = title;
	}

	/**
	 * Returns the Activity's meeting days.
	 * 
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Returns the Activity's start time.
	 * 
	 * @return the startTime
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Returns the Activity's end time.
	 * 
	 * @return the endTime
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * Sets the Course's or Event's meeting days and time. There must be a valid
	 * meetingDays, startTime and endTime or an exception will be thrown.
	 * 
	 * @param meetingDays meeting days for Course or Event as series of chars
	 * @param startTime   start time for Course or Event
	 * @param endTime     end time for Course or Event
	 * @throws IllegalArgumentException if the meetingDays, startTime, or endTime
	 *                                  parameters are invalid
	 */
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if (meetingDays == null || "".equals(meetingDays)) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}

		int startHour = startTime / 100;
		int startMin = startTime % 100;
		int endHour = endTime / 100;
		int endMin = endTime % 100;
		if (endTime < startTime) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		if (startHour < 0 || startHour >= UPPER_HOUR) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		if (startMin < 0 || startMin >= UPPER_MINUTE) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		if (endHour < 0 || endHour >= UPPER_HOUR) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		if (endMin < 0 || endMin >= UPPER_MINUTE) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		this.meetingDays = meetingDays;
		this.startTime = startTime;
		this.endTime = endTime;

	}

	/**
	 * Provides meeting information as a string in standard time format rather than
	 * in military format.
	 * 
	 * @return the meeting information in standard time format
	 */
	public String getMeetingString() {
		if ("A".equals(getMeetingDays())) {
			return "Arranged";
		} else {
			int startHour = getStartTime() / 100;
			int startMin = getStartTime() % 100;
			int endHour = getEndTime() / 100;
			int endMin = getEndTime() % 100;
			String startMinString = "";
			String endMinString = "";
			String startString = "";
			String endString = "";

			if (startMin < 10) {
				startMinString = "0" + startMin;
			} else {
				startMinString += startMin;
			}
			if (endMin < 10) {
				endMinString = "0" + endMin;
			} else {
				endMinString += endMin;
			}
			if (startHour == 0) {
				startString = "12:" + startMinString + "AM";
			} else if (startHour < 12) {
				startString = startHour + ":" + startMinString + "AM";
			} else if (startHour >= 12) {
				startHour -= 12;
				if (startHour == 0) {
					startString = "12:" + startMinString + "PM";
				} else {
					startString = startHour + ":" + startMinString + "PM";
				}
			}
			if (endHour == 0) {
				endString = "12:" + endMinString + "AM";
			} else if (endHour < 12) {
				endString = endHour + ":" + endMinString + "AM";
			} else if (endHour >= 12) {
				endHour -= 12;
				if (endHour == 0) {
					endString = "12:" + endMinString + "PM";
				} else {
					endString = endHour + ":" + endMinString + "PM";
				}
			}
			return getMeetingDays() + " " + startString + "-" + endString;
		}
	}

	/**
	 * Generates a hashCode for Activity using all fields.
	 * 
	 * @return hashCode for Activity
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + meetingDays.hashCode();
		result = prime * result + startTime;
		result = prime * result + title.hashCode();
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
		if (obj == null) {
			return false;
		}
		Activity other = (Activity) obj;
		if (endTime != other.endTime)
			return false;
		else if (!meetingDays.equals(other.meetingDays))
			return false;
		if (startTime != other.startTime)
			return false;
		else if (!title.equals(other.title))
			return false;
		return true;
	}

	/**
	 * Determines whether the given activity is a duplicate of another activity
	 * already in the schedule.
	 * 
	 * @param activity the Activity to compare
	 * @return true if the two Activities have the same name or title
	 */
	public abstract boolean isDuplicate(Activity activity);

	/**
	 * The method compares the current instance Activity with the parameter
	 * possibleConflictingActivity to see if there is any conflict in their days and
	 * times. If there is a conflict, the checked ConflictException is thrown.
	 * 
	 * @param possibleConflictingActivity theActivity that could be in conflict
	 * @throws ConflictException if the Activity is in conflict of one another
	 */
	@Override
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
		for (int i = 0; i < this.meetingDays.length(); i++) {
			for (int j = 0; j < possibleConflictingActivity.getMeetingDays().length(); j++) {
				if (this.meetingDays.charAt(i) == 'A' && possibleConflictingActivity.getMeetingDays().charAt(j) == 'A') {
					break;
				} else if (this.meetingDays.charAt(i) == possibleConflictingActivity.getMeetingDays().charAt(j)) {
					if (this.startTime <= possibleConflictingActivity.getStartTime()
							&& possibleConflictingActivity.getStartTime() <= this.endTime) {
						throw new ConflictException();
					} else if (this.startTime <= possibleConflictingActivity.getEndTime()
							&& possibleConflictingActivity.getEndTime() <= this.endTime) {
						throw new ConflictException();
					} else if (this.startTime >= possibleConflictingActivity.getStartTime()
							&& possibleConflictingActivity.getEndTime() >= this.startTime) {
						throw new ConflictException();
					}
				}
			}
		}

	}

}