package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Event is a superclass extension of Activity that allows a user to add events
 * into a schedule within the WolfScheduluer program.
 * 
 * @author hchunter
 *
 */
public class Event extends Activity {

	/**
	 * A description of the event.
	 */
	private String eventDetails;

	/**
	 * Event Constructor
	 * 
	 * @param title        - the title of the event
	 * @param meetingDays  - the meeting days of the event
	 * @param startTime    - the starting time of the event
	 * @param endTime      - the ending time of the event
	 * @param eventDetails - the details of the event
	 */
	public Event(String title, String meetingDays, int startTime, int endTime, String eventDetails) {
		super(title, meetingDays, startTime, endTime);
		setEventDetails(eventDetails);
	}

	@Override
	public String[] getShortDisplayArray() {
		String[] tempArray = new String[4];
		tempArray[0] = "";
		tempArray[1] = "";
		tempArray[2] = getTitle();
		tempArray[3] = getMeetingString();
		return tempArray;
	}

	@Override
	public String[] getLongDisplayArray() {
		String[] tempArray = new String[7];
		tempArray[0] = "";
		tempArray[1] = "";
		tempArray[2] = getTitle();
		tempArray[3] = "";
		tempArray[4] = "";
		tempArray[5] = getMeetingString();
		tempArray[6] = eventDetails;

		return tempArray;
	}

	/**
	 * Returns the event details
	 * 
	 * @return eventDetails
	 */
	public String getEventDetails() {
		return eventDetails;
	}

	/**
	 * Sets the event details
	 * 
	 * @param eventDetails - the details of the event being set
	 */
	public void setEventDetails(String eventDetails) {

		if (eventDetails == null) {
			throw new IllegalArgumentException("Invalid event details.");
		}
		this.eventDetails = eventDetails;
	}

	/**
	 * An override of toString for the Event object.
	 * 
	 * @return A comma separated string of events title, meeting days, start time,
	 *         end time, and details.
	 */
	@Override
	public String toString() {
		return getTitle() + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime() + "," + eventDetails;
	}

	/**
	 * This is an extended version of this method in Course. It uses the activity
	 * superclass to allow it to work with Course.java. Checks for arranged or
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
	 *                                  than A, M, T, W, H, F, S, U if any of those
	 *                                  characters appear more than once in the
	 *                                  string meetingDays if the start and end time
	 *                                  are the same
	 * 
	 */
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {

		if ("A".equals(meetingDays)) { // Arranged
			if (startTime != 0 || endTime != 0) {
				throw new IllegalArgumentException("Invalid meeting days and times");
			}
		} else {

			int countM = 0;
			int countT = 0;
			int countW = 0;
			int countH = 0;
			int countF = 0;
			int countS = 0;
			int countU = 0;

			if (meetingDays != null) {
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
					} else if (meetingDays.charAt(i) == 'S') {
						countS++;
					} else if (meetingDays.charAt(i) == 'U') {
						countU++;
					} else {
						throw new IllegalArgumentException("Invalid meeting days and times.");
					}
				}
			}

			// Checks to see if any day of the week is a duplicate
			if (countM > 1 || countT > 1 || countW > 1 || countH > 1 || countF > 1 || countS > 1 || countU > 1) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
		}

		if (endTime == 0 || startTime == 0) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}

	/**
	 * Checks for duplicates of Event(s) In this a duplicate is considered two
	 * events with the same title.
	 * 
	 * @param activity - Event you want to check for a duplicate of
	 * @return true - when there is a duplicate, and false - when there is no
	 *         duplicate
	 * 
	 */
	@Override
	public boolean isDuplicate(Activity activity) {
		return activity instanceof Event && ((Event) activity).getTitle().equals(this.getTitle());
	}

}
