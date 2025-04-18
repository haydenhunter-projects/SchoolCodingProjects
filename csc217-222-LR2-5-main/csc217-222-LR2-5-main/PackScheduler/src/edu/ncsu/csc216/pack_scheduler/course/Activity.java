package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Includes details about a particular activity, like it's title, meeting days,
 * the start time, and the end time
 * 
 * @author Hari Vemulapalli
 *
 */
public abstract class Activity implements Conflict {

	/** Activity title. */
	private String title;
	/** Activity's meeting days */
	private String meetingDays;
	/** Activity start time */
	private int startTime;
	/** Activity end time */
	private int endTime;

	/**
	 * Sets the required information about an activity.
	 * 
	 * @param title       - title of activity
	 * @param meetingDays - days when the activity will happen
	 * @param startTime   - time when the activity will start
	 * @param endTime     - time when the activity will end
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		setTitle(title);
		setMeetingDaysAndTime(meetingDays, startTime, endTime);
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
	 * Returns the Activity title.
	 * 
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the Activity title. If the title is null or an empty string, an
	 * IllegalArgumentException will be thrown.
	 * 
	 * @param title the title to set
	 * @throws IllegalArgumentException if title parameter is invalid
	 */
	public void setTitle(String title) {
		// Conditional 1: Checks for equality of the title string with the empty string
		if (title == null || "".equals(title)) {
			throw new IllegalArgumentException("Invalid title.");
		}
		// Conditional 2: Checks the length of title string.
		if (title == null || title.length() == 0) {
			throw new IllegalArgumentException("Invalid title.");
		}
		this.title = title;
	}

	/**
	 * Returns the Activity's meeting days.
	 * 
	 * @return meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Returns the Activity start time.
	 * 
	 * @return startTime
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Returns the Activity end time.
	 * 
	 * @return endTime
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * Sets the Activity's meeting days, start time, and end time. If meeting days
	 * are characters other than 'M', 'T', 'W', 'H', 'F', or 'A', If there are
	 * duplicates of these characters, If start time and/or end time is not between
	 * 0000 and 2359, If end time is less than start time, and if a start time
	 * and/or end time is listed when meeting days is "A", an
	 * IllegalArgumentException will be thrown.
	 * 
	 * @param meetingDays - days when the activity will meet
	 * @param startTime   - time when the activity starts
	 * @param endTime     - time when the activity ends
	 * @throws IllegalArgumentException when the parameters meetingDays, startTime,
	 *                                  and/or endTime is invalid
	 */
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		// Checks if parameter meetingDays is null or empty
		if (meetingDays == null || "".equals(meetingDays)) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		// Checks if meetingDays is "A"
		if ("A".equals(meetingDays)) {
			// Checks that if meetingDays is "A", then whether the startTime and endTime are
			// 0
			if (startTime != 0 || endTime != 0) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			} else {
				// If startTime and endTime are 0, then the parameters are valid
				this.meetingDays = meetingDays;
				this.startTime = 0;
				this.endTime = 0;
			}
		}
		// Checks if meetingDays are "M", "T", "W", "H", "F", "U", or "S" and counts for
		// each
		else {
			int numM = 0;
			int numT = 0;
			int numW = 0;
			int numH = 0;
			int numF = 0;
			int numU = 0;
			int numS = 0;
			// Counter for each of the meeting days
			for (int i = 0; i < meetingDays.length(); i++) {
				String c = meetingDays.substring(i, i + 1);
				if ("M".equals(c)) {
					numM++;
				} else if ("T".equals(c)) {
					numT++;
				} else if ("W".equals(c)) {
					numW++;
				} else if ("H".equals(c)) {
					numH++;
				} else if ("F".equals(c)) {
					numF++;
				} else if ("U".equals(c)) {
					numU++;
				} else if ("S".equals(c)) {
					numS++;
				}
				// If there are values other than "M", "T", "W", "H", "F", "U", or "S", then an
				// exception is thrown
				// "A" cannot be a value if Activity is an instance of Event
				else {
					throw new IllegalArgumentException("Invalid meeting days and times.");
				}
			}
			// If any of the meeting days are counted more than 1, then an exception is
			// thrown
			if (numM > 1 || numT > 1 || numW > 1 || numH > 1 || numF > 1 || numU > 1 || numS > 1) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			// Converts military time to standard time by separating military time into
			// different variables
			if (endTime < startTime) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			int startHour = startTime / 100;
			int startMin = startTime % 100;
			int endHour = endTime / 100;
			int endMin = endTime % 100;

			if (startHour < 0 || startHour > 23) { // Invalid if not between 0 and 23, inclusive
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			if (startMin < 0 || startMin > 59) { // Invalid if not between 0 and 59, inclusive
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			if (endHour < 0 || endHour > 23) { // Invalid if not between 0 and 23, inclusive
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			if (endMin < 0 || endMin > 59) { // Invalid if not between 0 and 59, inclusive
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			// Sets the values of parameters if valid
			this.meetingDays = meetingDays;
			this.startTime = startTime;
			this.endTime = endTime;
		}
	}

	/**
	 * This method is intended to get the standard time, given the military time.
	 * 
	 * @param time - military time, which converts to standard time
	 * @return timeString
	 */
	public String getTimeString(int time) {
		// Separates military time into hours and minutes as part of standard time
		int hour = time / 100;
		int minute = time % 100;
		// "AM" by default
		String aORp = "AM";
		String standardTime = "";
		// It is "PM" if hour is >=12
		if (hour >= 12) {
			if (hour > 12) {
				hour = hour - 12;
			}
			aORp = "PM";
		}
		// 12 AM if hour is 0
		if (hour == 0) {
			hour = 12;
		}
		// Need a format if minute is <10 and if minute is >=10
		if (minute < 10) {
			standardTime = hour + ":0" + minute + aORp;
		} else if (minute >= 10) {
			standardTime = hour + ":" + minute + aORp;
		}
		// Returns the standardTime as the time string
		return standardTime;
	}

	/**
	 * Checks if there are any conflicts between activities No conflicts if the
	 * activities are arranged.
	 * 
	 * @param possibleConflictingActivity - Checks whether an activity may have a
	 *                                    conflict with another activity.
	 * @throws ConflictException if a conflict is found
	 */
	@Override
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
		// There would be no conflict if the Activity objects are Arranged
		if (this.meetingDays.contains("A") && possibleConflictingActivity.getMeetingDays().contains("A")) {
			return;
		}
		// Checks with meeting days to see the conflicts
		else {
			String shorterStringMeetingDays;
			if (this.meetingDays.length() > possibleConflictingActivity.getMeetingDays().length()) {
				shorterStringMeetingDays = possibleConflictingActivity.getMeetingDays();
			} else {
				shorterStringMeetingDays = this.meetingDays;
			}
			// Checks if there is at least 1 meeting day that is the same between both
			// Activity objects
			for (int i = 0; i < shorterStringMeetingDays.length(); i++) {
				String c = shorterStringMeetingDays.substring(i, i + 1);
				if (this.meetingDays.contains(c) && possibleConflictingActivity.getMeetingDays().contains(c)) {
					// Start Time and End Time are same for both!
					if (this.startTime == possibleConflictingActivity.getStartTime()
							&& this.endTime == possibleConflictingActivity.getEndTime()) {
						throw new ConflictException();
					}
					// Where one activity overlaps the timings of another activity (this.Activity!)
					else if (this.startTime < possibleConflictingActivity.getStartTime()
							&& this.endTime > possibleConflictingActivity.getEndTime()) {
						throw new ConflictException();
					}
					// Where one activity overlaps the timings of another activity (other Activity!)
					else if (this.startTime > possibleConflictingActivity.getStartTime()
							&& this.endTime < possibleConflictingActivity.getEndTime()) {
						throw new ConflictException();
					}
					// When this.Activity starts and ends after the next Activity, but takes over
					// the timings of the next Activity
					else if (this.startTime > possibleConflictingActivity.getStartTime()
							&& this.endTime > possibleConflictingActivity.getEndTime()
							&& this.startTime < possibleConflictingActivity.getEndTime()) {
						throw new ConflictException();
					}
					// When this.Activity starts and ends before the next Activity, but takes over
					// the timings of the next Activity
					else if (this.startTime < possibleConflictingActivity.getStartTime()
							&& this.endTime < possibleConflictingActivity.getEndTime()
							&& this.endTime > possibleConflictingActivity.getStartTime()) {
						throw new ConflictException();
					}
					// When this.Activity end time equals the other Activity start time
					else if (this.endTime == possibleConflictingActivity.getStartTime()) {
						throw new ConflictException();
					}
					// When this.Activity start time equals the other Activity end time
					else if (this.startTime == possibleConflictingActivity.getEndTime()) {
						throw new ConflictException();
					}
					// When this.Activity start time or end time equals the other Activity start
					// time or end time
					else if (this.startTime == possibleConflictingActivity.getStartTime()
							|| this.endTime == possibleConflictingActivity.getEndTime()) {
						throw new ConflictException();
					} else {
						return;
					}
				}
			}
		}
	}

	/**
	 * Returns information related to meeting details for an activity, the days,
	 * start and end time.
	 * 
	 * @return meetingInfo
	 */
	public String getMeetingString() {
		// Gets the meeting days
		String days = getMeetingDays();
		// Sets up where information for the meeting goes
		String meetingInfo = " ";
		// In case days is "A"
		if ("A".equals(days)) {
			meetingInfo = "Arranged";
			return meetingInfo;
		}
		// Gets the time information for the meeting
		String start = getTimeString(getStartTime());
		String end = getTimeString(getEndTime());
		// Concatenates information for the meeting
		meetingInfo = days + " " + start + "-" + end;
		// Returns the variable with the meeting information
		return meetingInfo;

	}

	/**
	 * Method used to get part of the information for Activity objects
	 * 
	 * @return shortDisplayArray
	 */
	public abstract String[] getShortDisplayArray();

	/**
	 * Method used to get all of the information for Activity objects
	 * 
	 * @return longDisplayArray
	 */
	public abstract String[] getLongDisplayArray();

	/**
	 * Checks for any Activity objects that are the exact same
	 * 
	 * @param activity - the activity object
	 * @return duplicate
	 */
	public abstract boolean isDuplicate(Activity activity);
}