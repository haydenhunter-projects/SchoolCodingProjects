package edu.ncsu.csc216.pack_scheduler.directory;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Tests StudentDirectory.
 * 
 * @author Sarah Heckman, Thien Nguyen
 */
public class StudentDirectoryTest {

	/** Valid course records */
	private final String validTestFile = "test-files/student_records.txt";
	/** Test first name */
	private static final String FIRST_NAME = "Stu";
	/** Test last name */
	private static final String LAST_NAME = "Dent";
	/** Test id */
	private static final String ID = "sdent";
	/** Test email */
	private static final String EMAIL = "sdent@ncsu.edu";
	/** Test password */
	private static final String PASSWORD = "pw";
	/** Test max credits */
	private static final int MAX_CREDITS = 15;

	/**
	 * Resets course_records.txt for use in other tests.
	 * 
	 * @throws Exception if something fails during setup.
	 */
	@Before
	public void setUp() throws Exception {
		// Reset student_records.txt so that it's fine for other needed tests
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "expected_full_student_records.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "student_records.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}

	/**
	 * Tests StudentDirectory().
	 */
	@Test
	public void testStudentDirectory() {
		// Test that the StudentDirectory is initialized to an empty list
		StudentDirectory sd = new StudentDirectory();
		assertFalse(sd.removeStudent("sesmith5"));
		assertEquals(0, sd.getStudentDirectory().length);
	}

	/**
	 * Tests StudentDirectory.testNewStudentDirectory().
	 */
	@Test
	public void testNewStudentDirectory() {
		// Test that if there are students in the directory, they
		// are removed after calling newStudentDirectory().
		StudentDirectory sd = new StudentDirectory();

		sd.loadStudentsFromFile(validTestFile);
		assertEquals(10, sd.getStudentDirectory().length);

		sd.newStudentDirectory();
		assertEquals(0, sd.getStudentDirectory().length);
	}

	/**
	 * Tests StudentDirectory.loadStudentsFromFile().
	 */
	@Test
	public void testLoadStudentsFromFile() {
		StudentDirectory sd = new StudentDirectory();

		// Test valid file
		sd.loadStudentsFromFile(validTestFile);
		assertEquals(10, sd.getStudentDirectory().length);
	}

	/**
	 * Tests StudentDirectory.loadStudentsFromFile() but with invalid file.
	 */
	@Test
	public void testLoadInvalidStudentsFromFile() {
		StudentDirectory sd = new StudentDirectory();

		// Test invalid file
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> sd.loadStudentsFromFile("test-files/invalid_file.txt"));
		assertEquals("Unable to read file test-files/invalid_file.txt", e1.getMessage()); // Check correct exception
																							// message
	}

	/**
	 * Tests StudentDirectory.addStudent().
	 */
	@Test
	public void testAddStudent() {
		StudentDirectory sd = new StudentDirectory();

		// Test valid Students
		sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_CREDITS);
		assertTrue(sd.addStudent("Tea", "cher", "tcher", "tcher@ncsu.edu", "wolf", "wolf", 1));
		sd.addStudent("Col", "lege", "clege", "clege@ncsu.edu", "pack", "pack", 19);
		String[][] studentDirectory = sd.getStudentDirectory();
		assertEquals(3, studentDirectory.length);
		assertEquals(FIRST_NAME, studentDirectory[0][0]);
		assertEquals(LAST_NAME, studentDirectory[0][1]);
		assertEquals(ID, studentDirectory[0][2]);
		assertEquals("Tea", studentDirectory[1][0]);
		assertEquals("cher", studentDirectory[1][1]);
		assertEquals("tcher", studentDirectory[1][2]);
		assertEquals("Col", studentDirectory[2][0]);
		assertEquals("lege", studentDirectory[2][1]);
		assertEquals("clege", studentDirectory[2][2]);
	}

	/**
	 * Tests StudentDirectory.addStudent() but with duplicate Student.
	 */
	@Test
	public void testAddDuplicateStudent() {
		StudentDirectory sd = new StudentDirectory();
		sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_CREDITS);

		// Test invalid Student
		assertFalse(sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_CREDITS));
		String[][] studentDirectory = sd.getStudentDirectory();
		assertEquals(1, studentDirectory.length);
		assertEquals(FIRST_NAME, studentDirectory[0][0]);
		assertEquals(LAST_NAME, studentDirectory[0][1]);
		assertEquals(ID, studentDirectory[0][2]);
	}

	/**
	 * Tests StudentDirectory.addStudent() but with invalid parameters.
	 */
	@Test
	public void testAddInvalidStudent() {
		StudentDirectory sd = new StudentDirectory();

		// Test invalid Students
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, null, PASSWORD, MAX_CREDITS));
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, null, MAX_CREDITS));
		Exception e3 = assertThrows(IllegalArgumentException.class,
				() -> sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, "", PASSWORD, MAX_CREDITS));
		Exception e4 = assertThrows(IllegalArgumentException.class,
				() -> sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, "", MAX_CREDITS));
		Exception e5 = assertThrows(IllegalArgumentException.class,
				() -> sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, "PW", MAX_CREDITS));
		assertEquals("Invalid password", e1.getMessage()); // Check correct exception message
		assertEquals("Invalid password", e2.getMessage()); // Check correct exception message
		assertEquals("Invalid password", e3.getMessage()); // Check correct exception message
		assertEquals("Invalid password", e4.getMessage()); // Check correct exception message
		assertEquals("Passwords do not match", e5.getMessage()); // Check correct exception message

	}

	/**
	 * Tests StudentDirectory.removeStudent().
	 */
	@Test
	public void testRemoveStudent() {
		StudentDirectory sd = new StudentDirectory();

		// Add students and remove
		sd.loadStudentsFromFile(validTestFile);
		assertEquals(10, sd.getStudentDirectory().length);
		assertTrue(sd.removeStudent("daustin"));
		String[][] studentDirectory = sd.getStudentDirectory();
		assertEquals(9, studentDirectory.length);
		assertEquals("Lane", studentDirectory[0][0]);
		assertEquals("Berg", studentDirectory[0][1]);
		assertEquals("lberg", studentDirectory[0][2]);
	}

	/**
	 * Tests StudentDirectory.saveStudentDirectory().
	 */
	@Test
	public void testSaveStudentDirectory() {
		StudentDirectory sd = new StudentDirectory();

		// Add a student
		sd.addStudent("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", "pw", "pw", 15);
		assertEquals(1, sd.getStudentDirectory().length);
		sd.saveStudentDirectory("test-files/actual_student_records.txt");
		checkFiles("test-files/expected_student_records.txt", "test-files/actual_student_records.txt");
	}

	/**
	 * Tests StudentDirectory.SaveStudentDirectory() but with invalid file.
	 */
	@Test
	public void testSaveInvalidStudentDirectory() {
		StudentDirectory sd = new StudentDirectory();

		// Add a student
		sd.addStudent("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", "pw", "pw", 15);
		assertEquals(1, sd.getStudentDirectory().length);
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> sd.saveStudentDirectory("/home/sesmith5/actual_student_records.txt"));
		assertEquals("Unable to write to file /home/sesmith5/actual_student_records.txt", e1.getMessage());
	}

	/**
	 * Helper method to compare two files for the same contents
	 * 
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try {
			Scanner expScanner = new Scanner(new FileInputStream(expFile));
			Scanner actScanner = new Scanner(new FileInputStream(actFile));

			while (expScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}

			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}
	
	/**
	 * Tests getStudentById
	 */
	@Test
	public void testGetStudentById() {
		new Student("Bob", "Smith", "bsmith5", "bsmith@e.mail", "password", 5);
		new Student("Jake", "Tmith", "jtmith5", "jtmith@e.mail", "password", 5);

		StudentDirectory sd = new StudentDirectory();
		sd.addStudent("Bob", "Smith", "bsmith5", "bsmith@e.mail", "password", "password", 5);
		assertEquals(null, sd.getStudentById("bab"));
		
		assertEquals("Bob", sd.getStudentById("bsmith5").getFirstName());
		assertEquals("Smith", sd.getStudentById("bsmith5").getLastName());
		assertEquals("bsmith5", sd.getStudentById("bsmith5").getId());
		assertEquals(5, sd.getStudentById("bsmith5").getMaxCredits());
		
		
		
		
		

		
	}

}
