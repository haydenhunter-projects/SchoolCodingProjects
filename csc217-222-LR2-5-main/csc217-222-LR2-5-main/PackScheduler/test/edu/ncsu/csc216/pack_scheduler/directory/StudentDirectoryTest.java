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
 * @author Hari Vemulapalli
 * @author Hayden Hunter
 * @author Victor Hermidas
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
		// Test invalid file
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> sd.loadStudentsFromFile("test-files/noFile.txt"));
		assertEquals("Unable to read file " + "test-files/noFile.txt", e.getMessage());

	}

	/**
	 * Tests StudentDirectory.addStudent().
	 */
	@Test
	public void testAddStudent() {
		StudentDirectory sd = new StudentDirectory();

		// Test Student with invalid password - null
		Exception x = assertThrows(IllegalArgumentException.class,
				() -> sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, null, null, MAX_CREDITS));
		assertEquals("Invalid password", x.getMessage());

		// Test Student with invalid password - empty string
		Exception xx = assertThrows(IllegalArgumentException.class,
				() -> sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, "", "", MAX_CREDITS));
		assertEquals("Invalid password", xx.getMessage());

		// Test Student with invalid first name - null.
		Exception x1 = assertThrows(IllegalArgumentException.class,
				() -> sd.addStudent(null, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_CREDITS));
		assertEquals("Invalid first name", x1.getMessage());

		// Test Student with invalid first name -empty string.
		Exception x11 = assertThrows(IllegalArgumentException.class,
				() -> sd.addStudent("", LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_CREDITS));
		assertEquals("Invalid first name", x11.getMessage());

		// Test Student with invalid last name - null.
		Exception x2 = assertThrows(IllegalArgumentException.class,
				() -> sd.addStudent(FIRST_NAME, null, ID, EMAIL, PASSWORD, PASSWORD, MAX_CREDITS));
		assertEquals("Invalid last name", x2.getMessage());

		// Test Student with invalid last name - empty string.
		Exception x22 = assertThrows(IllegalArgumentException.class,
				() -> sd.addStudent(FIRST_NAME, "", ID, EMAIL, PASSWORD, PASSWORD, MAX_CREDITS));
		assertEquals("Invalid last name", x22.getMessage());

		// Test Student with invalid id - null.
		Exception x3 = assertThrows(IllegalArgumentException.class,
				() -> sd.addStudent(FIRST_NAME, LAST_NAME, null, EMAIL, PASSWORD, PASSWORD, MAX_CREDITS));
		assertEquals("Invalid id", x3.getMessage());

		// Test Student with invalid id - empty string.
		Exception x33 = assertThrows(IllegalArgumentException.class,
				() -> sd.addStudent(FIRST_NAME, LAST_NAME, "", EMAIL, PASSWORD, PASSWORD, MAX_CREDITS));
		assertEquals("Invalid id", x33.getMessage());

		// Test Student with invalid email - null.
		Exception x4 = assertThrows(IllegalArgumentException.class,
				() -> sd.addStudent(FIRST_NAME, LAST_NAME, ID, null, PASSWORD, PASSWORD, MAX_CREDITS));
		assertEquals("Invalid email", x4.getMessage());

		// Test Student with invalid email - null.
		Exception x44 = assertThrows(IllegalArgumentException.class,
				() -> sd.addStudent(FIRST_NAME, LAST_NAME, ID, "", PASSWORD, PASSWORD, MAX_CREDITS));
		assertEquals("Invalid email", x44.getMessage());

		// Test valid Student
		sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_CREDITS);
		String[][] studentDirectory = sd.getStudentDirectory();
		assertEquals(1, studentDirectory.length);
		assertEquals(FIRST_NAME, studentDirectory[0][0]);
		assertEquals(LAST_NAME, studentDirectory[0][1]);
		assertEquals(ID, studentDirectory[0][2]);

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
		assertTrue(sd.removeStudent("efrost"));
		String[][] studentDirectory = sd.getStudentDirectory();
		assertEquals(9, studentDirectory.length);
		assertEquals("Zahir", studentDirectory[5][0]);
		assertEquals("King", studentDirectory[5][1]);
		assertEquals("zking", studentDirectory[5][2]);
	}

	/**
	 * Tests StudentDirectory.saveStudentDirectory().
	 */
	@Test
	public void testSaveStudentDirectory() {
		StudentDirectory sd = new StudentDirectory();

		// Add a student
		sd.addStudent("Zahir", "King", "zking", "orciDonec@ametmassaQuisque.com", "pw", "pw", 15);
		assertEquals(1, sd.getStudentDirectory().length);
		sd.saveStudentDirectory("test-files/actual_student_records.txt");
		checkFiles("test-files/expected_student_records.txt", "test-files/actual_student_records.txt");
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
	 * Tests StudentDirectory.getStudentbyId
	 */
	@Test
	public void testGetStudentById() {
		StudentDirectory sd = new StudentDirectory();

		// Add a student
		sd.addStudent("Zahir", "King", "zking", "orciDonec@ametmassaQuisque.com", "pw", "pw", 15);
		assertEquals(1, sd.getStudentDirectory().length);
		Student s = new Student("Zahir", "King", "zking", "orciDonec@ametmassaQuisque.com",
				"MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=", 15);
		assertEquals(s, sd.getStudentById("zking"));
	}

}
