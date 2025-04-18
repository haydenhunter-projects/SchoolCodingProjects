package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;

import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
* Input and Output method for Faculty objects. Users can read from a file
* and receive a LinkedList of
* @author smithej
*
*/
public class FacultyRecordIO {
	
	/**
	 * Reads the Faculty information from the entered file. Faculty objects are returned in
	 * a LinkedList of Faculty objects.
	 * @param fileName the file of faculty information
	 * @return linkedlist of faculty members
	 * @throws FileNotFoundException if the entered file cannot be found
	 */
	public static LinkedList<Faculty> readFacultyRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		LinkedList<Faculty> facultyDirectory = new LinkedList<Faculty>();
		while (fileReader.hasNextLine()) {
			try {
				Faculty faculty = processFaculty(fileReader.nextLine());
				boolean duplicate = false;
				for (int i = 0; i < facultyDirectory.size(); i++) {
					User current = facultyDirectory.get(i);
					if (faculty.getId().equals(current.getId())) {
						duplicate = true;
						break;
					}
				}
				if (!duplicate) {
					facultyDirectory.add(faculty);
				}
			} catch (IllegalArgumentException e) {
				// The line is invalid b/c we couldn't create a faculty, skip it!
			}
		}
		fileReader.close();
		return facultyDirectory;
	}

	/**
	 * Processes faculty information string. Receives a String of faculty information
	 * and parses it into fields for a faculty object. The faculty object is created
	 * and returned.
	 * @param line String of faculty information to parse
	 * @return Faculty Object created by the information in the String
	 */
	private static Faculty processFaculty(String line) {
		Scanner scan = new Scanner(line);
		scan.useDelimiter(",");

		try {
			String first = scan.next();
			String last = scan.next();
			String id = scan.next();
			String email = scan.next();
			String hashPass = scan.next();
			int maxCourses = scan.nextInt();
			if (scan.hasNext()) {
				scan.close();
				throw new IllegalArgumentException();
			} else {
				scan.close();
				return new Faculty(first, last, id, email, hashPass, maxCourses);
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("IAE - process student");
		}
	}
	
	
	/**
	 * Write Faculty objects to the entered file. Uses the Faculty toString
	 * to write Faculty objects to the entered file. Faculty objects are written
	 * in the order that they appear in the facultyDirectory LinkedList.
	 * @param fileName the name of the file that the faculty information should be written to
	 * @param facultyDirectory the list of Faculty object to be written to the file
	 * @throws IOException if if cannot write to the file
	 */
	public static void writeFacultyRecords(String fileName, LinkedList<Faculty> facultyDirectory) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));

		for (int i = 0; i < facultyDirectory.size(); i++) {
			fileWriter.println(facultyDirectory.get(i).toString());
		}

		fileWriter.close();

	}
}
