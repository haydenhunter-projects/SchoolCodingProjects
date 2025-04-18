package edu.ncsu.csc216.pack_scheduler.catalog;

import java.io.FileNotFoundException;
import java.io.IOException;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.io.CourseRecordIO;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * This class maintains a catalog of all courses at NC State. It reads in and
 * stores as a list all of the Course records stored in a file. Additionally,
 * CourseCatalog provides functionality for adding a Course to the catalog,
 * removing a Course from the catalog, and getting a Course from the catalog.
 * 
 * @author Jerolyn ClementRaj
 */
public class CourseCatalog {

	/** List of courses in the catalog */
	private SortedList<Course> catalog;

	/**
	 * Creates an empty course catalog.
	 */
	public CourseCatalog() {
		newCourseCatalog();
	}

	/**
	 * Creates an empty course catalog.
	 */
	public void newCourseCatalog() {
		catalog = new SortedList<Course>();
	}

	/**
	 * Constructs the course catalog by reading in course information from the given file. 
	 * Throws an IllegalArgumentException if the file cannot be found.
	 * 
	 * @param fileName file containing list of courses
	 * @throws IllegalArgumentException if the file cannot be found
	 */
	public void loadCoursesFromFile(String fileName) {
		try {
			catalog = CourseRecordIO.readCourseRecords(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file " + fileName);
		}
	}

	/**
	 * Returns true if Course created by the parameters is added to Catalog.
	 * 
	 * @param name         name of the Course
	 * @param title        title of the Course
	 * @param section      section of the Course
	 * @param credits      credits of the Course
	 * @param instructorId instructor of the Course
	 * @param enrollmentCap the max number of students that can enroll in the course
	 * @param meetingDays  meeting days of the Course
	 * @param startTime    start time of the Course
	 * @param endTime      end time of the Course
	 * @return true if Course can be added to the Catalog or false if the Course
	 *         already exists in the Catalog
	 */
	public boolean addCourseToCatalog(String name, String title, String section, int credits, String instructorId, int enrollmentCap,
			String meetingDays, int startTime, int endTime) {
		Course course = new Course(name, title, section, credits, instructorId, enrollmentCap, meetingDays, startTime, endTime);
		for (int i = 0; i < this.catalog.size(); i++) {
			if (name.equals(this.catalog.get(i).getName()) && section.equals(this.catalog.get(i).getSection())) {
				return false;
			}
		}
		return this.catalog.add(course);
	}

	/**
	 * Returns true if Course created by the parameters is removed from the Catalog
	 * 
	 * @param name    name of the Course
	 * @param section section of the Course
	 * @return true if the Course can be removed from the Catalog or false if the
	 *         Course isn't in the Catalog
	 */
	public boolean removeCourseFromCatalog(String name, String section) {
		for (int i = 0; i < this.catalog.size(); i++) {
			if (this.catalog.get(i).getName().equals(name) && this.catalog.get(i).getSection().equals(section)) {
				catalog.remove(i);
				return true;
			}
		}
		return false;

	}

	/**
	 * Returns a Course created by the parameters name and section from the Catalog.
	 * 
	 * @param name    name of the Course
	 * @param section section of the Course
	 * @return the Course with appropriate name and section from the Catalog or null
	 *         if the Course isn't in the Catalog
	 */
	public Course getCourseFromCatalog(String name, String section) {

		// Iterates through catalog to check if Course is in catalog
		for (int i = 0; i < this.catalog.size(); i++) {
			if (this.catalog.get(i).getName().equals(name) && this.catalog.get(i).getSection().equals(section)) {
				return this.catalog.get(i);
			}
		}
		return null;
	}

	/**
	 * Returns the name, section, title, and meeting information for Courses in the
	 * Catalog.
	 * 
	 * @return String array with name, section, title, and meeting information for
	 *         Courses in the Catalog
	 */
	public String[][] getCourseCatalog() {
		String[][] cat = new String[this.catalog.size()][5];
		for (int i = 0; i < this.catalog.size(); i++) {
			cat[i] = this.catalog.get(i).getShortDisplayArray();
//			cat[i][0] = this.catalog.get(i).getName();
//			cat[i][1] = this.catalog.get(i).getSection();
//			cat[i][2] = this.catalog.get(i).getTitle();
//			cat[i][3] = this.catalog.get(i).getMeetingString();
		}
		return cat;
	}

	/**
	 * Receives a String parameter that is the filename where the Course Catalog
	 * will be saved to. All of the Courses in the Catalog will be saved to this
	 * file.
	 * 
	 * @param fileName the filename to be saved to
	 * @throws IllegalArgumentException if the file cannot be saved
	 */
	public void saveCourseCatalog(String fileName) {
		try {
			CourseRecordIO.writeCourseRecords(fileName, catalog);
		} catch (IOException e) {
			throw new IllegalArgumentException("The file cannot be saved");
		}
	}

}
