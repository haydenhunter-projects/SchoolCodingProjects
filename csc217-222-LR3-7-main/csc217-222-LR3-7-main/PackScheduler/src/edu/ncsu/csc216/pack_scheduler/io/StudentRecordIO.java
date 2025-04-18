package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * Reads Student records from a text file in order to list the students to the
 * Student Directory table in sorted order. In addition, writes a set of
 * StudentRecords to a file when the student choose to save the Student
 * Directory.
 * 
 * @author Jerolyn ClementRaj
 * @author Eric Smith
 * @author Thien Nguyen
 *
 */
public class StudentRecordIO {

	/**
	 * Reads Student records from a file and generates a list of valid Students. Any
	 * invalid Students are ignored. If the file to read cannot be found or the
	 * permissions are incorrect a FileNotFoundException is thrown.
	 * 
	 * @param fileName file to read Student records from
	 * @return a list of valid Students
	 * @throws FileNotFoundException if the file cannot be found or read
	 */
	public static SortedList<Student> readStudentRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		SortedList<Student> students = new SortedList<Student>();
		while (fileReader.hasNextLine()) {
			try {
				Student student = processStudent(fileReader.nextLine());
				boolean duplicate = false;
				for (int i = 0; i < students.size(); i++) {
					User current = students.get(i);
					if (student.getId().equals(current.getId())) {
						duplicate = true;
						break;
					}
				}
				if (!duplicate) {
					students.add(student);
				}
			} catch (IllegalArgumentException e) {
				// The line is invalid b/c we couldn't create a course, skip it!
			}
		}
		fileReader.close();
		return students;
	}

	/**
	 * Read student's information from a line and generates a new Student object if
	 * the information is appropriate. If there are any additional tokens after the
	 * expected last one or there are invalid values in the input string, an
	 * IllegalArgumentException is thrown.
	 * 
	 * @param line a line from the input file which has the student's information
	 * @return a new Student with appropriate information
	 * @throws IllegalArgumentException if there are any additional tokens after the
	 *                                  expected last one or if there are invalid
	 *                                  values in the input string
	 */
	private static Student processStudent(String line) {
		Scanner scan = new Scanner(line);
		scan.useDelimiter(",");

		try {
			String first = scan.next();
			String last = scan.next();
			String id = scan.next();
			String email = scan.next();
			String hashPass = scan.next();
			int maxCredits = scan.nextInt();
			if (scan.hasNext()) {
				scan.close();
				throw new IllegalArgumentException();
			} else {
				scan.close();
				return new Student(first, last, id, email, hashPass, maxCredits);
			}
		} catch (InputMismatchException e) {
			throw new IllegalArgumentException("IAE - process student");
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException("IAE - process student");
		}
	}

	/**
	 * Writes the given list of Students to a file.
	 * 
	 * @param fileName         file to write record of Students to
	 * @param studentDirectory list of Students to write
	 * @throws IOException if cannot write to file
	 */
	public static void writeStudentRecords(String fileName, SortedList<Student> studentDirectory) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));

		for (int i = 0; i < studentDirectory.size(); i++) {
			fileWriter.println(studentDirectory.get(i).toString());
		}

		fileWriter.close();

	}

}