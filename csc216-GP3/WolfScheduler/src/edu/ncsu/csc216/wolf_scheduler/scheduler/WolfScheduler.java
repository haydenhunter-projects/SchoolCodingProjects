package edu.ncsu.csc216.wolf_scheduler.scheduler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import edu.ncsu.csc216.wolf_scheduler.course.Activity;
import edu.ncsu.csc216.wolf_scheduler.course.ConflictException;
import edu.ncsu.csc216.wolf_scheduler.course.Course;
import edu.ncsu.csc216.wolf_scheduler.course.Event;
import edu.ncsu.csc216.wolf_scheduler.io.ActivityRecordIO;
import edu.ncsu.csc216.wolf_scheduler.io.CourseRecordIO;

/**
 * The WolfScheduler class file reads in and stores as a list all of the Course
 * records stored in a file. Additionally, WolfScheduler creates a schedule for
 * the current user (a student) and provides functionality for naming the
 * schedule, adding a Course to the schedule, removing a Course from the
 * schedule, resetting the schedule
 * 
 * @author hchunter
 *
 */
public class WolfScheduler {

	/** A private field containing an ArrayList of Course Objects named catalog */
	private ArrayList<Course> catalog;

	/** A private field containing an ArrayList of Course Objects named schedule */
	private ArrayList<Activity> schedule;

	/** A private field containing a string named Title */
	private String title;

	/**
	 * The WolfScheduler constructor
	 * 
	 * @param fileName - the file that is passed through for input and output
	 */
	public WolfScheduler(String fileName) {
		schedule = new ArrayList<>();

		title = "My Schedule";

		try {
			catalog = new ArrayList<>(CourseRecordIO.readCourseRecords(fileName));
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Cannot find file.");
		}
	}

	/**
	 * Will return the course catalog.
	 * 
	 * If there are no Courses in the catalog, an empty 2D String array is returned.
	 * 
	 * @return a 2d String array of the catalog
	 */
	public String[][] getCourseCatalog() {
		String[][] catalogArray = new String[catalog.size()][3];
		for (int i = 0; i < catalog.size(); i++) {
			Course c = catalog.get(i);
			catalogArray[i] = c.getShortDisplayArray();
		}
		return catalogArray;
	}

	/**
	 * Will return the course schedule with limited information.
	 * 
	 * If there are no courses and empty 2d string array is returned
	 * 
	 * @return a 2D String array of the schedule
	 */
	public String[][] getScheduledActivities() {
		String[][] courseSchedule = new String[schedule.size()][4];
		for (int i = 0; i < schedule.size(); i++) {
			Activity c = schedule.get(i);
			courseSchedule[i] = c.getShortDisplayArray();
		}

		return courseSchedule;
	}

	/**
	 * Returns the Full Course Schedule with all relevant information
	 * 
	 * @return a 2D string array of the schedule with all the information for each
	 *         course
	 */
	public String[][] getFullScheduledActivities() {
		String[][] fullSchedule = new String[schedule.size()][7];
		for (int i = 0; i < schedule.size(); i++) {
			Activity c = schedule.get(i);
			fullSchedule[i] = c.getLongDisplayArray();
		}

		return fullSchedule;
	}

	/**
	 * Will add a course to the schedule array list Method will check to verify the
	 * course exists in the catalog, the student is not currently enrolled in the
	 * class, and will then attempt to add the course to the schedule.
	 * 
	 * @param name    - the courses name
	 * @param section - the courses section
	 * @return a boolean for if the course was added to the schedule or not
	 * @throws IllegalArgumentException with the messaged You are already enrolled
	 *                                  in [course name] if a duplicate of the you
	 *                                  are trying to enroll in is on your schedule
	 */
	public boolean addCourseToSchedule(String name, String section) {

		Course g = getCourseFromCatalog(name, section);
		for (int i = 0; i < schedule.size(); i++) {

			if (schedule.get(i).isDuplicate(getCourseFromCatalog(name, section))) {
				throw new IllegalArgumentException("You are already enrolled in " + name);
			}
			try {
				schedule.get(i).checkConflict(g);

			} catch (ConflictException e) {
				throw new IllegalArgumentException("The course cannot be added due to a conflict.");
			}
		}
		if (getCourseFromCatalog(name, section) == null) {
			return false;
		} else {
			schedule.add(getCourseFromCatalog(name, section));
			return true;
		}

	}

	/**
	 * Will remove a course from the schedule array list Method will check to verify
	 * the course exists on the schedule, and will then attempt to remove the course
	 * from the schedule.
	 * 
	 * @param idx -the index of the activity you want to remove
	 * 
	 * @return -true or false stating rather or not the course was removed from the
	 *         schedule
	 * @throws IndexOutOfBoundsException - will throw exception if schedule does not
	 *                                   have an element at the index passed through
	 */
	public boolean removeActivityFromSchedule(int idx) {

		try {
			schedule.remove(idx);
		} catch (IndexOutOfBoundsException e) {
			return false;
		}

		return true;
	}

	/**
	 * Uses a course name, and a course section to search through the catalog for a
	 * specific course.
	 * 
	 * @param name    the courses name
	 * @param section the courses section
	 * @return the searched for course from the catalog
	 */
	public Course getCourseFromCatalog(String name, String section) {

		for (int i = 0; i < catalog.size(); i++) {

			if (catalog.get(i).getName().equals(name) && catalog.get(i).getSection().equals(section)) {
				return catalog.get(i);
			}
		}
		return null;
	}

	/**
	 * Returns the schedule title
	 * 
	 * @return title
	 */
	public String getScheduleTitle() {
		return title;
	}

	/**
	 * Exports the schedule to a file
	 * 
	 * @param fileName - the file passed through for input/output
	 * @throws IllegalArgumentException with message The file cannot be saved if the
	 *                                  file cannot be found.
	 */
	public void exportSchedule(String fileName) {
		try {
			ActivityRecordIO.writeActivityRecords(fileName, schedule);
		} catch (IOException e) {
			throw new IllegalArgumentException("The file cannot be saved");
		}
	}

	/**
	 * Will set the title of the schedule
	 * 
	 * @param title - the schedules title
	 * @throws IllegalArgumentException with message Title cannot be null if title
	 *                                  is null or an empty string
	 */
	public void setScheduleTitle(String title) {
		if ("".equals(title) || title == null) {
			throw new IllegalArgumentException("Title cannot be null");
		}
		this.title = title;
	}

	/**
	 * Will reset the ArrayList schedule back to blank
	 */
	public void resetSchedule() {
		schedule.removeAll(schedule);
	}

	/**
	 * Add event is used to add an event onto your schedule.
	 * 
	 * @param eventTitle       -the title of your event
	 * @param eventMeetingDays -the meeting days of your event
	 * @param eventStartTime   -the start time of your event
	 * @param eventEndTime     -the end time of your event
	 * @param eventDetails     -the details of your event
	 * @throws IllegalArgumentException - with the message You have already created
	 *                                  an event called [event title] if the event
	 *                                  has the same title as another event you have
	 *                                  created
	 */
	public void addEventToSchedule(String eventTitle, String eventMeetingDays, int eventStartTime, int eventEndTime,
			String eventDetails) {
		Event g = new Event(eventTitle, eventMeetingDays, eventStartTime, eventEndTime, eventDetails);
		for (int i = 0; i < schedule.size(); i++) {
			if (schedule.get(i).isDuplicate(g)) {
				throw new IllegalArgumentException("You have already created an event called " + eventTitle);
			}
			try {
				schedule.get(i).checkConflict(g);
			} catch (ConflictException e) {
				throw new IllegalArgumentException("The event cannot be added due to a conflict.");
			}
		}
		schedule.add(g);
	}
}
