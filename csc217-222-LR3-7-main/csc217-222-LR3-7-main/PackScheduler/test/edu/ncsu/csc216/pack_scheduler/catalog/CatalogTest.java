package edu.ncsu.csc216.pack_scheduler.catalog;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.manager.RegistrationManager;

/**
 * Tests the CourseCatalog class.
 * 
 * @author Eric Smith
 */
class CatalogTest {
	
	/** Valid course records */
	private final String validTestFile = "test-files/course_records.txt";

	/** Course name */
	private static final String NAME = "CSC216";
	/** Course title */
	private static final String TITLE = "Software Development Fundamentals";
	/** Course section */
	private static final String SECTION = "001";
	/** Course credits */
	private static final int CREDITS = 3;
	/** Course instructor id */
	private static final String INSTRUCTOR_ID = "sesmith5";
	/** Max enrollment for the course */
	private static final int ENROLLMENT_CAP = 150;
	/** Course meeting days */
	private static final String MEETING_DAYS = "TH";
	/** Course start time */
	private static final int START_TIME = 1330;
	/** Course end time */
	private static final int END_TIME = 1445;

	/**
	 * Test CourseCatalog.getCourseFromCatalog()
	 */
	@Test
	public void testGetCourseFromCatalog() {
		RegistrationManager registrar = RegistrationManager.getInstance();
		registrar.getFacultyDirectory().loadFacultyFromFile("test-files/faculty_records_test.txt");
		CourseCatalog cat = new CourseCatalog();
		cat.loadCoursesFromFile(validTestFile);

		// Attempt to get a course that doesn't exist
		assertNull(cat.getCourseFromCatalog("CSC492", "001"));
		assertNull(cat.getCourseFromCatalog("CSC216", "003"));

		// Attempt to get a course that does exist
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		assertEquals(c, cat.getCourseFromCatalog("CSC216", "001"));
	}

	/**
	 * Test CourseCatalog.addCourseToCatalog()
	 */
	@Test
	public void testAddCourseToCatalog() {
		CourseCatalog cat = new CourseCatalog();
		assertTrue(cat.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME,
				END_TIME));
		assertFalse(cat.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME,
				END_TIME));
	}

	/**
	 * Test CourseCatalog.RemoveCourseFromCatalog()
	 */
	@Test
	public void testRemoveCourseFromCatalog() {
		RegistrationManager registrar = RegistrationManager.getInstance();
		registrar.getFacultyDirectory().loadFacultyFromFile("test-files/faculty_records_test.txt");
		CourseCatalog cat = new CourseCatalog();
		cat.loadCoursesFromFile(validTestFile);
		assertTrue(cat.removeCourseFromCatalog("CSC216", "001"));
		assertFalse(cat.removeCourseFromCatalog("CSC216", "003"));
		assertFalse(cat.removeCourseFromCatalog("CSC492", "001"));
	}

	/**
	 * Test CourseCatalog.RemoveCourseFromCatalog()
	 */
	@Test
	public void testGetCourseCatalog() {
		CourseCatalog cat = new CourseCatalog();
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		cat.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		String[][] courseStrArr = new String[1][5];
		courseStrArr[0][0] = NAME;
		courseStrArr[0][1] = SECTION;
		courseStrArr[0][2] = TITLE;
		courseStrArr[0][3] = c.getMeetingString();
		courseStrArr[0][4] = String.valueOf(ENROLLMENT_CAP);
		assertEquals(courseStrArr[0][0], cat.getCourseCatalog()[0][0]);
		assertEquals(courseStrArr[0][1], cat.getCourseCatalog()[0][1]);
		assertEquals(courseStrArr[0][2], cat.getCourseCatalog()[0][2]);
		assertEquals(courseStrArr[0][3], cat.getCourseCatalog()[0][3]);
		assertEquals(courseStrArr[0][4], cat.getCourseCatalog()[0][4]);
	}

	/**
	 * Test CourseCatalog.saveCourseCatalog()
	 */
	@Test
	public void testSaveCourseCatalog() {
		// Test that empty schedule exports correctly
		CourseCatalog cat = new CourseCatalog();
		cat.saveCourseCatalog("test-files/actual_empty_export.txt");
		checkFiles("test-files/expected_empty_export.txt", "test-files/actual_empty_export.txt");

		// Add courses and test that exports correctly
		assertTrue(cat.addCourseToCatalog("CSC116", "Intro to Programming - Java", "003", 3, "spbalik", 10, "MW", 1250, 1440));
		assertTrue(cat.addCourseToCatalog("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "MW", 1330, 1445));
		assertTrue(cat.addCourseToCatalog("CSC216", "Software Development Fundamentals", "601", 3, "jctetter", 10, "A", 0, 0));
		cat.saveCourseCatalog("test-files/actual_course_records.txt");
		checkFiles("test-files/expected_course_records.txt", "test-files/actual_course_records.txt");
	}

	/**
	 * Helper method to compare two files for the same contents
	 * 
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try (Scanner expScanner = new Scanner(new File(expFile));
				Scanner actScanner = new Scanner(new File(actFile));) {

			while (actScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			if (expScanner.hasNextLine()) {
				fail();
			}

			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}

}
