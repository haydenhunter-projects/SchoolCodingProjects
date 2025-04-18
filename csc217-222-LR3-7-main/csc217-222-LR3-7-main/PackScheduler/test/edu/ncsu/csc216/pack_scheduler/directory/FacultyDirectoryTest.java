package edu.ncsu.csc216.pack_scheduler.directory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.io.FacultyRecordIO;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * Faculty Directory Test
 * 
 * @author smithej
 */
public class FacultyDirectoryTest {
	/** Valid course records */
	private final String validTestFile = "test-files/faculty_records.txt";
	/** Test first name */
	private static final String FIRST_NAME = "Fac";
	/** Test last name */
	private static final String LAST_NAME = "Ulty";
	/** Test id */
	private static final String ID = "fulty";
	/** Test email */
	private static final String EMAIL = "fulty@ncsu.edu";
	/** Test password */
	private static final String PASSWORD = "pw";
	/** Test max credits */
	private static final int MAX_COURSES = 3;

	/**
	 * Resets course_records.txt for use in other tests.
	 * 
	 * @throws Exception if something fails during setup.
	 */
	@Before
	public void setUp() throws Exception {
		// Reset faculty_records.txt so that it's fine for other needed tests
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "expected_full_faculty_records.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "faculty_records.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}

	/**
	 * Tests FacultyDirectory().
	 */
	@Test
	public void testFacultyDirectory() {
		// Test that the FacultyDirectory is initialized to an empty list
		FacultyDirectory sd = new FacultyDirectory();
		assertFalse(sd.removeFaculty("sesmith5"));
		assertEquals(0, sd.getFacultyDirectory().length);
	}

	/**
	 * Tests FacultyDirectory.testNewFacultyDirectory().
	 */
	@Test
	public void testNewFacultyDirectory() {
		// Test that if there are Faculty in the directory, they
		// are removed after calling newFacultyDirectory().
		FacultyDirectory sd = new FacultyDirectory();

		sd.loadFacultyFromFile(validTestFile);
		assertEquals(8, sd.getFacultyDirectory().length);

		sd.newFacultyDirectory();
		assertEquals(0, sd.getFacultyDirectory().length);
	}

	/**
	 * Tests FacultyDirectory.loadFacultyFromFile().
	 */
	@Test
	public void testLoadFacultyFromFile() {
		FacultyDirectory sd = new FacultyDirectory();

		// Test valid file
		sd.loadFacultyFromFile(validTestFile);
		assertEquals(8, sd.getFacultyDirectory().length);
	}

//	/**
//	 * Tests FacultyDirectory.loadFacultyFromFile() but with invalid file.
//	 */
//	@Test
//	public void testLoadInvalidFacultyFromFile() {
//		FacultyDirectory sd = new FacultyDirectory();
//
//		// Test invalid file
//		Exception e1 = assertThrows(IllegalArgumentException.class,
//				() -> sd.loadFacultyFromFile("test-files/invalid_file.txt"));
//		assertEquals("Unable to read file test-files/invalid_file.txt", e1.getMessage()); // Check correct exception
//																							// message
//	}

	/**
	 * Tests FacultyDirectory.addFaculty().
	 */
	@Test
	public void testAddFaculty() {
		FacultyDirectory sd = new FacultyDirectory();

		// Test valid Faculty
		sd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_COURSES);
		assertTrue(sd.addFaculty("Tea", "cher", "tcher", "tcher@ncsu.edu", "wolf", "wolf", 1));
		sd.addFaculty("Col", "lege", "clege", "clege@ncsu.edu", "pack", "pack", 3);
		String[][] facultyDirectory = sd.getFacultyDirectory();
		assertEquals(3, facultyDirectory.length);
		assertEquals(FIRST_NAME, facultyDirectory[0][0]);
		assertEquals(LAST_NAME, facultyDirectory[0][1]);
		assertEquals(ID, facultyDirectory[0][2]);
		assertEquals("Tea", facultyDirectory[1][0]);
		assertEquals("cher", facultyDirectory[1][1]);
		assertEquals("tcher", facultyDirectory[1][2]);
		assertEquals("Col", facultyDirectory[2][0]);
		assertEquals("lege", facultyDirectory[2][1]);
		assertEquals("clege", facultyDirectory[2][2]);
		
		
		
	}

	/**
	 * Tests FacultyDirectory.addFaculty() but with duplicate Faculty.
	 */
	@Test
	public void testAddDuplicateFaculty() {
		FacultyDirectory sd = new FacultyDirectory();
		sd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_COURSES);

		// Test invalid Faculty
		assertFalse(sd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_COURSES));
		String[][] facultyDirectory = sd.getFacultyDirectory();
		assertEquals(1, facultyDirectory.length);
		assertEquals(FIRST_NAME, facultyDirectory[0][0]);
		assertEquals(LAST_NAME, facultyDirectory[0][1]);
		assertEquals(ID, facultyDirectory[0][2]);
	}

	/**
	 * Tests FacultyDirectory.addFaculty() but with invalid parameters.
	 */
	@Test
	public void testAddInvalidFaculty() {
		FacultyDirectory sd = new FacultyDirectory();

		// Test invalid Faculty
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> sd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, null, PASSWORD, MAX_COURSES));
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> sd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, null, MAX_COURSES));
		Exception e3 = assertThrows(IllegalArgumentException.class,
				() -> sd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, "", PASSWORD, MAX_COURSES));
		Exception e4 = assertThrows(IllegalArgumentException.class,
				() -> sd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, "", MAX_COURSES));
		Exception e5 = assertThrows(IllegalArgumentException.class,
				() -> sd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, "PW", MAX_COURSES));
		assertEquals("Invalid password", e1.getMessage()); // Check correct exception message
		assertEquals("Invalid password", e2.getMessage()); // Check correct exception message
		assertEquals("Invalid password", e3.getMessage()); // Check correct exception message
		assertEquals("Invalid password", e4.getMessage()); // Check correct exception message
		assertEquals("Passwords do not match", e5.getMessage()); // Check correct exception message

	}

	/**
	 * Tests FacultyDirectory.removeFaculty().
	 */
	@Test
	public void testRemoveFaculty() {
		FacultyDirectory sd = new FacultyDirectory();

		// Add Faculty and remove
		sd.loadFacultyFromFile(validTestFile);
		assertEquals(8, sd.getFacultyDirectory().length);
		assertTrue(sd.removeFaculty("awitt"));
		String[][] facultyDirectory = sd.getFacultyDirectory();
		assertEquals(7, facultyDirectory.length);
		assertEquals("Fiona", facultyDirectory[0][0]);
		assertEquals("Meadows", facultyDirectory[0][1]);
		assertEquals("fmeadow", facultyDirectory[0][2]);
	}

	/**
	 * Tests FacultyDirectory.saveFacultyDirectory().
	 */
	@Test
	public void testSaveFacultyDirectory() {
		FacultyDirectory sd = new FacultyDirectory();

		// Add a Faculty
		sd.addFaculty("Ashely", "Witt", "awitt", "mollis@Fuscealiquetmagna.net", "pw", "pw", 2);
		assertEquals(1, sd.getFacultyDirectory().length);
		sd.saveFacultyDirectory("test-files/actual_faculty_records.txt");
		checkFiles("test-files/expected_faculty_records.txt", "test-files/actual_faculty_records.txt");
	}

//	/**
//	 * Tests FacultyDirectory.SaveFacultyDirectory() but with invalid file.
//	 */
//	@Test
//	public void testSaveInvalidFacultyDirectory() {
//		FacultyDirectory sd = new FacultyDirectory();
//
//		// Add a Faculty
//		sd.addFaculty("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", "pw", "pw", 15);
//		assertEquals(1, sd.getFacultyDirectory().length);
//		Exception e1 = assertThrows(IllegalArgumentException.class,
//				() -> sd.saveFacultyDirectory("/home/sesmith5/actual_faculty_records.txt"));
//		assertEquals("Unable to write to file /home/sesmith5/actual_faculty_records.txt", e1.getMessage());
//	}

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
	 * Tests getFacultyById
	 */
	@Test
	public void testGetFacultyById() {
		new Faculty("Bob", "Smith", "bsmith5", "bsmith@e.mail", "password", 2);
		new Faculty("Jake", "Tmith", "jtmith5", "jtmith@e.mail", "password", 2);

		FacultyDirectory sd = new FacultyDirectory();
		sd.addFaculty("Bob", "Smith", "bsmith5", "bsmith@e.mail", "password", "password", 2);
		assertEquals(null, sd.getFacultyById("bab"));
		
		assertEquals("Bob", sd.getFacultyById("bsmith5").getFirstName());
		assertEquals("Smith", sd.getFacultyById("bsmith5").getLastName());
		assertEquals("bsmith5", sd.getFacultyById("bsmith5").getId());
		assertEquals(2, sd.getFacultyById("bsmith5").getMaxCourses());
		
	}
	
	
	/**
	 * tests Faculty IO
	 */
	@Test
	public void testIO() {
		try {
			LinkedList<Faculty> x = FacultyRecordIO.readFacultyRecords("test-files/invalid_faculty_records.txt");
			assertEquals(0, x.size());
		} catch (FileNotFoundException e) {
			fail();
		}
		
	}
}
