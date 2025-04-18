package edu.ncsu.csc216.pack_scheduler.user.schedule;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;
/**
 * Tests the Student object.
 * @author Jerolyn ClementRaj
 */
class ScheduleTest {

	/** Max enrollment for the course */
	private static final int ENROLLMENT_CAP = 150;

	@Test
	void testSchedule() {
		Schedule sh1 = new Schedule();
		assertEquals(0, sh1.getScheduledCourses().length);
		assertEquals("My Schedule", sh1.getTitle());
	}

	@Test
	void testAddCourseToSchedule() {
		Schedule sh1 = new Schedule();
		Course c1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", ENROLLMENT_CAP, "MW", 1330, 1445);
		Course c2 = new Course("CSC116", "Intro to Java", "003", 3, "sesmith7", ENROLLMENT_CAP, "M", 1330, 1445);
		Course c3 = new Course("CSC216", "Software Development Fundamentals", "002", 3, "sesmith6", ENROLLMENT_CAP, "T", 1445, 1500);
		sh1.addCourseToSchedule(c1);
		assertEquals(1, sh1.getScheduledCourses().length);
		assertEquals("CSC216", sh1.getScheduledCourses()[0][0]);
		assertEquals("001", sh1.getScheduledCourses()[0][1]);
		assertEquals("Software Development Fundamentals", sh1.getScheduledCourses()[0][2]);
		assertEquals(c1.getMeetingString(), sh1.getScheduledCourses()[0][3]);
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> sh1.addCourseToSchedule(c2));
		assertEquals("The course cannot be added due to a conflict.", e1.getMessage());
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> sh1.addCourseToSchedule(c3));
		assertEquals("You are already enrolled in " + c3.getName(), e2.getMessage());
	}

	@Test
	void testRemoveCourseFromSchedule() {
		Schedule sh1 = new Schedule();
		Course c1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", ENROLLMENT_CAP, "MW", 1330, 1445);
		Course c2 = new Course("CSC226", "Discreate	Math", "002", 3, "sesmith6", ENROLLMENT_CAP, "T", 1330, 1445);
		Course c3 = new Course("CSC116", "Intro to Java", "003", 3, "sesmith7", ENROLLMENT_CAP, "F", 1330, 1445);
		sh1.addCourseToSchedule(c1);
		sh1.addCourseToSchedule(c2);
		assertEquals(2, sh1.getScheduledCourses().length);
		assertTrue(sh1.removeCourseFromSchedule(c1));
		assertEquals(1, sh1.getScheduledCourses().length);
		assertTrue(sh1.removeCourseFromSchedule(c2));
		assertEquals(0, sh1.getScheduledCourses().length);
		assertFalse(sh1.removeCourseFromSchedule(c3));
	}

	@Test
	void testResetSchedule() {
		Schedule sh1 = new Schedule();
		Course c1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", ENROLLMENT_CAP, "MW", 1330, 1445);
		Course c2 = new Course("CSC226", "Discreate	Math", "002", 3, "sesmith6", ENROLLMENT_CAP, "T", 1330, 1445);
		sh1.addCourseToSchedule(c1);
		sh1.addCourseToSchedule(c2);
		sh1.setTitle("Best Schedule");
		assertEquals("Best Schedule", sh1.getTitle());
		assertEquals(2, sh1.getScheduledCourses().length);
		sh1.resetSchedule();
		assertEquals(0, sh1.getScheduledCourses().length);
		assertEquals("My Schedule", sh1.getTitle());
	}

	@Test
	void testGetScheduledCourses() {
		Schedule sh1 = new Schedule();
		Course c1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", ENROLLMENT_CAP, "MW", 1330, 1445);
		Course c2 = new Course("CSC226", "Discreate	Math", "002", 3, "sesmith6", ENROLLMENT_CAP, "T", 1330, 1445);
		sh1.addCourseToSchedule(c1);
		sh1.addCourseToSchedule(c2);
		assertEquals(2, sh1.getScheduledCourses().length);
	}

	@Test
	void testSetTitle() {
		Schedule sh1 = new Schedule();
		sh1.setTitle("Best Schedule");
		assertEquals("Best Schedule", sh1.getTitle());
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> sh1.setTitle(null));
		assertEquals("Title cannot be null.", e1.getMessage());
	}

	@Test
	void testGetTitle() {
		Schedule sh1 = new Schedule();
		sh1.setTitle("Best Schedule");
		assertEquals("Best Schedule", sh1.getTitle());
	}
	
//	@Test 
//	void testSchedule2() {
//		Schedule schedule = new Schedule();
//		CourseCatalog cc = new CourseCatalog();
//		cc.loadCoursesFromFile("test-files/course_records.txt");
//		assertTrue(schedule.canAdd(cc.getCourseFromCatalog("CSC216", "001")));
//		Course a = new Course("CSC216", "Title", "001", 3, null, 20, "TH", 1330, 1445);
//		assertEquals(a, cc.getCourseFromCatalog("CSC216", "001"));
//	}

}
