package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * Record system for courses in the WolfScheduler Project. Reads Course records
 * from text files and puts them into a list of Course objects. Writes Course
 * records from a list and exports them to a text file.
 * 
 * @author Hari Vemulapalli
 * @author Hayden Hunter
 * @author Victor Hermida
 */
public class CourseRecordIO {

	/**
	 * Reads course records from a file and generates a list of valid Courses.
	 * Courses are ignored. If the file to read cannot be found or the permissions
	 * are incorrect, a File NotFoundException is thrown.
	 * 
	 * @param fileName file to read Course records form
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
					// of control will transfer to the catch block below
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
	 * Reads information about a course from a String type variable and returns a
	 * Course object based on that information.
	 * 
	 * @param line - String containing course information
	 * @return Course object
	 * @throws IllegalArgumentException if any information in line (related to
	 *                                  Course) is invalid
	 */
	private static Course readCourse(String line) {
		// Creates the Scanner object to process the information from String line
		Scanner courseProcess;
		courseProcess = new Scanner(line);
		// Uses ',' as delimiter to separate course information into designated
		// variables
		courseProcess.useDelimiter(",");
		try {
			// Puts the course information separately as tokens
			String name = courseProcess.next();
			String title = courseProcess.next();
			String section = courseProcess.next();
			int creditHours = courseProcess.nextInt();
			String instructor = courseProcess.next();
			int enrollmentCap = courseProcess.nextInt();
			String meetingDay = courseProcess.next();
			// Cannot have more tokens if meetingDay is "A"
			if ("A".equals(meetingDay)) {
				if (courseProcess.hasNext()) {
					courseProcess.close();
					throw new IllegalArgumentException("Cannot have startTime and endTime when meetingDays is 'A'");
				} else {
					// Returns without the time variables
					Course course = new Course(name, title, section, creditHours, instructor, enrollmentCap, meetingDay);
					courseProcess.close();
					return course;
				}
			}
			// If meetingDay is NOT "A", then we need to add startTime and endTime as new
			// tokens
			else {
				int startTime = courseProcess.nextInt();
				int endTime = courseProcess.nextInt();
				// Checks to see if we have more information than necessary
				if (courseProcess.hasNext()) {
					courseProcess.close();
					throw new IllegalArgumentException("Cannot have any more tokens!");
				}
				// Returns with the time variables
				Course course = new Course(name, title, section, creditHours, instructor, enrollmentCap, meetingDay, startTime,
						endTime);
				courseProcess.close();
				return course;
			}
		}
		// Throws an exception if any information put into the different variables have
		// invalid arguments
		catch (Exception e) {
			throw new IllegalArgumentException("Invalid inputs for the parameters!");
		}

	}

	/**
	 * Writes the given list of courses to a given file
	 * 
	 * @param fileName - file to save to
	 * @param courses  list of courses to save
	 * @throws IOException if the file cannot be written
	 */
	public static void writeCourseRecords(String fileName, SortedList<Course> courses) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));

		for (int i = 0; i < courses.size(); i++) {
			fileWriter.println(courses.get(i).toString());
		}

		fileWriter.close();
	}

}
