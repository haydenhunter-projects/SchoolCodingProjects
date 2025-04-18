package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * We will be looking in the files for each student to see if their firstName,
 * lastName, id, email, password, maxCredits is correctly ordered
 * 
 * @author Victor Hermida
 * @author Hari Vemulapalli
 * @author Hayden Hunter
 *
 */

public class StudentRecordIO {
	/**
	 * Reads a file that includes information about students and processes into an
	 * SortedList of Student objects
	 * 
	 * @param fileName - file where student information is kept
	 * @return students
	 * @throws FileNotFoundException if a file cannot be read or found
	 */
	public static SortedList<Student> readStudentRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName)); // Create a file scanner to read the file
		SortedList<Student> students = new SortedList<Student>(); // Create an empty array of Student objects
		while (fileReader.hasNextLine()) { // While we have more lines in the file
			try {
				// Read the line, process it in the processStudent() method, and get the object
				Student student = processStudent(fileReader.nextLine());
				students.add(student);
			} catch (Exception e) {
				// This line is invalid where the Student object cannot be created, mainly due
				// to invalid parameters
			}
		}
		// Close the Scanner b/c we're responsible with our file handles
		fileReader.close();
		// Return the SortedList with all the students we read!
		return students;
	}

	/**
	 * Process information from String line into a Student object
	 * 
	 * @param line The line we will be using to scan through each line in the file
	 * @return studentObject
	 * @throws IllegalArgumentException if any parameters for the Student object are
	 *                                  invalid
	 */
	private static Student processStudent(String line) {
		// We created a scanner to scan through the files
		Scanner studentProcess = new Scanner(line);
		// Using the Delimiter will break apart each string to see if their correct
		studentProcess.useDelimiter(",");
		try {
			// We will be scanning each line to see if the Strings and integers are in the
			// file
			String firstName = studentProcess.next();
			String lastName = studentProcess.next();
			String id = studentProcess.next();
			String email = studentProcess.next();
			String password = studentProcess.next();
			int maxCredits = studentProcess.nextInt();
			// This is the format that the file should be in in order to return
			Student studentObject = new Student(firstName, lastName, id, email, password, maxCredits);
			studentProcess.close();
			return studentObject;

		} catch (Exception e) {
			studentProcess.close();
			// throw if the inputs are Invalid to the parameters
			throw new IllegalArgumentException("Invalid inputs for the parameters!");
		}
	}

	/**
	 * Writes the Student objects from studentDirectory into a given file
	 * 
	 * @param fileName         - name of file to save Student objects in
	 * @param studentDirectory - SortedList where Student objects are from
	 * @throws IOException if unable to read the file
	 */
	public static void writeStudentRecords(String fileName, SortedList<Student> studentDirectory) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));
		// this for loop will allow us to print out the file for studentDirectory
		for (int i = 0; i < studentDirectory.size(); i++) {
			fileWriter.println(studentDirectory.get(i).toString());
		}
		// Closing the fileWriter is important
		fileWriter.close();

	}

}
