/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.roll;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Test Course Roll Class
 * @author nolanhurst
 *
 */
public class CourseRollTest {

	/**
	 * Test course roll constructor and set enrollment cap
	 */
	@Test
	public void testCourseRoll() {
		CourseRoll a = new CourseRoll(150);
		assertEquals(150, a.getOpenSeats());
		
		a.setEnrollmentCap(175);
		assertEquals(175, a.getOpenSeats());
		
		assertThrows(IllegalArgumentException.class, () -> a.setEnrollmentCap(0));
		}
	
	/**
	 * Test enroll
	 */
	@Test
	public void testEnroll() {
		CourseRoll a = new CourseRoll(150);
		Student s = new Student("Nolan", "Hurst", "nhurst", "nhurst@ncsu.edu", "password", 16);
		a.enroll(s);
		assertFalse(a.canEnroll(s));
		
		assertThrows(IllegalArgumentException.class, () -> a.enroll(null));
		
		
	}
	
	/**
	 * Test drop
	 */
	@Test
	public void testDrop() {
		CourseRoll a = new CourseRoll(150);
		Student s = new Student("Nolan", "Hurst", "nhurst", "nhurst@ncsu.edu", "password", 16);
		a.enroll(s);
		a.drop(s);
		assertTrue(a.canEnroll(s));
	}
}
