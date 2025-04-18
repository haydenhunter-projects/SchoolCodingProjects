package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.manager.RegistrationManager;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * Reads Course records from text files. Writes a set of CourseRecords to a
 * file.
 * 
 * @author Sarah Heckman, Thien Nguyen
 * @author Hayden Hunter
 */
public class CourseRecordIO {

	/**
	 * Reads course records from a file and generates a list of valid Courses. Any
	 * invalid Courses are ignored. If the file to read cannot be found or the
	 * permissions are incorrect a File NotFoundException is thrown.
	 * 
	 * @param fileName file to read Course records from
	 * @return a list of valid Courses
	 * @throws FileNotFoundException if the file cannot be found or read
	 */
	public static SortedList<Course> readCourseRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName)); // Create a file scanner to read the file
		SortedList<Course> courses = new SortedList<Course>(); // Create an empty array of Course objects
		while (fileReader.hasNextLine()) { // While we have more lines in the file
			try { // Attempt to do the following
					// Read the line, process it in readCourse, and get the object
					// If trying to construct a Course in readCourse() results in an exception, flow
					// of control will transfer to the catch block, below
				Course course = readCourse(fileReader.nextLine());

				// Create a flag to see if the newly created Course is a duplicate of something
				// already in the list
				boolean duplicate = false;
				// Look at all the courses in our list
				for (int i = 0; i < courses.size(); i++) {
					// Get the course at index i
					Course current = courses.get(i);
					// Check if the name and section are the same
					if (course.getName().equals(current.getName())
							&& course.getSection().equals(current.getSection())) {
						// It's a duplicate!
						duplicate = true;
						break; // We can break out of the loop, no need to continue searching
					}
				}
				// If the course is NOT a duplicate
				if (!duplicate) {
					courses.add(course); // Add to the SortedList!
				} // Otherwise ignore
			} catch (IllegalArgumentException e) {
				// The line is invalid b/c we couldn't create a course, skip it!
			}
		}
		// Close the Scanner b/c we're responsible with our file handles
		fileReader.close();
		// Return the SortedList with all the courses we read!
		return courses;
	}

	/**
	 * Read course's information from a line and generates a new Course object if
	 * the information is appropriate. If there are any additional tokens after the
	 * expected last one or there are invalid values in the input string, an
	 * IllegalArgumentException is thrown.
	 * @param line a line from the input file which has the course's information
	 * @return a new Course with appropriate information
	 * @throws IllegalArgumentException if there are any additional tokens after the
	 * expected last one or if there are invalid values in the input string
	 */
	private static Course readCourse(String line) {
		Scanner s = new Scanner(line);
		s.useDelimiter(",");
		try {
			String name = s.next();
			String title = s.next();
			String section = s.next();
			int creditHours = s.nextInt();
			String instructor = s.next();
//			if ("".equals(instructor)) {
//				instructor = null;
//			}
			int enrollmentCap = s.nextInt();
			String meetingDay = s.next();
			if ("A".equals(meetingDay)) {
				if (s.hasNext()) {
					s.close();
					throw new IllegalArgumentException();
				} else {
					s.close();
					Course c = new Course(name, title, section, creditHours, null, enrollmentCap, meetingDay);
					if(RegistrationManager.getInstance().getFacultyDirectory().getFacultyById(instructor) != null) {
						
						RegistrationManager.getInstance().getFacultyDirectory().getFacultyById(instructor).getSchedule().addCourseToSchedule(c);
					} 
					return c;
				}
			} else {
				int startTime = s.nextInt();
				int endTime = s.nextInt();
				if (s.hasNext()) {
					s.close();
					throw new IllegalArgumentException();
				}
				s.close();
				Course c = new Course(name, title, section, creditHours, null, enrollmentCap, meetingDay, startTime, endTime);
				if(RegistrationManager.getInstance().getFacultyDirectory().getFacultyById(instructor) != null) {
					
					RegistrationManager.getInstance().getFacultyDirectory().getFacultyById(instructor).getSchedule().addCourseToSchedule(c);
				} 
				return c;
			}
			
		} catch (InputMismatchException e) {
			throw new IllegalArgumentException();
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Writes the given list of Courses to
	 * 
	 * @param fileName file to write schedule of Courses to
	 * @param courses  list of Courses to write
	 * @throws IOException if cannot write to file
	 */
	public static void writeCourseRecords(String fileName, SortedList<Course> courses) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));

		for (int i = 0; i < courses.size(); i++) {
			fileWriter.println(courses.get(i).toString());
		}

		fileWriter.close();

	}

}