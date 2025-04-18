package edu.ncsu.csc216.wolf_scheduler.scheduler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import edu.ncsu.csc216.wolf_scheduler.course.Course;
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
	private ArrayList<Course> schedule;

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

		String[][] courseCatalog = new String[catalog.size()][3];
		for (int i = 0; i < catalog.size(); i++) {
			for (int j = 0; j < 3; j++) {
				String name = catalog.get(i).getName();
				courseCatalog[i][j] = name;
				j++;
				String section = catalog.get(i).getSection();
				courseCatalog[i][j] = section;
				j++;
				title = catalog.get(i).getTitle();
				courseCatalog[i][j] = title;
				j++;
			}
		}

		return courseCatalog;
	}

	/**
	 * Will return the course schedule with limited information.
	 * 
	 * If there are no courses and empty 2d string array is returned
	 * 
	 * @return a 2D String array of the schedule
	 */
	public String[][] getScheduledCourses() {
		String[][] courseSchedule = new String[schedule.size()][3];
		for (int i = 0; i < schedule.size(); i++) {
			for (int j = 0; j < 3; j++) {
				String name = schedule.get(i).getName();
				courseSchedule[i][j] = name;
				j++;
				String section = schedule.get(i).getSection();
				courseSchedule[i][j] = section;
				j++;
				title = schedule.get(i).getTitle();
				courseSchedule[i][j] = title;
				j++;
			}
		}

		return courseSchedule;
	}

	/**
	 * Returns the Full Course Schedule with all relevant information
	 * 
	 * @return a 2D string array of the schedule with all the information for each
	 *         course
	 */
	public String[][] getFullScheduledCourses() {
		String[][] fullSchedule = new String[schedule.size()][catalog.size()];
		for (int i = 0; i < schedule.size(); i++) {
			for (int j = 0; j < 6; j++) {
				String name = schedule.get(i).getName();
				fullSchedule[i][j] = name;
				j++;

				String section = schedule.get(i).getSection();
				fullSchedule[i][j] = section;
				j++;

				title = schedule.get(i).getTitle();
				fullSchedule[i][j] = title;
				j++;

				int credits = schedule.get(i).getCredits();
				fullSchedule[i][j] = Integer.toString(credits);
				j++;

				String instructorId = schedule.get(i).getInstructorId();
				fullSchedule[i][j] = instructorId;
				j++;

				String meetingDays = schedule.get(i).getMeetingString();
				fullSchedule[i][j] = meetingDays;
				j++;
			}
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
	 */
	public boolean addCourseToSchedule(String name, String section) {

		for (int i = 0; i < schedule.size(); i++) {

			if (schedule.get(i).getName().equals(name)) {
				throw new IllegalArgumentException("You are already enrolled in " + name);
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
	 * @param name    - the courses name
	 * @param section - the courses section
	 * @return a boolean stating rather or not the course was removed from the
	 *         schedule
	 */
	public boolean removeCourseFromSchedule(String name, String section) {

		for (int i = 0; i < schedule.size(); i++) {
			if (schedule.get(i).getName().equals(name) && schedule.get(i).getSection().equals(section)) {
				schedule.remove(i);
				return true;
			}
		}
		return false;
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
			CourseRecordIO.writeCourseRecords(fileName, schedule);
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
		schedule = new ArrayList<>();
	}

}
