/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the Activity class.
 * 
 * @author Thien Nguyen
 *
 */
public class ActivityTest {
	
	/** Max enrollment for the course */
	private static final int ENROLLMENT_CAP = 150;

	/**
	 * Test that the Activities happening in different days or time do not conflict.
	 */
	@Test
	public void testCheckConflict() {
		Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", ENROLLMENT_CAP, "MW", 1330,
				1445);
		Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", ENROLLMENT_CAP, "TH", 1330,
				1445);
		Activity a3 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", ENROLLMENT_CAP, "MW", 1200,
				1315);
		Activity a4 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", ENROLLMENT_CAP, "A");
		Activity a5 = new Course("CSC217", "Software Development Fundamentals", "001", 3, "sesmith5", ENROLLMENT_CAP, "A");

		assertDoesNotThrow(() -> a1.checkConflict(a2));
		assertDoesNotThrow(() -> a2.checkConflict(a1));
		assertDoesNotThrow(() -> a1.checkConflict(a3));
		assertDoesNotThrow(() -> a3.checkConflict(a1));
		assertDoesNotThrow(() -> a2.checkConflict(a3));
		assertDoesNotThrow(() -> a3.checkConflict(a2));
		assertDoesNotThrow(() -> a4.checkConflict(a5));
		assertDoesNotThrow(() -> a5.checkConflict(a4));
		assertDoesNotThrow(() -> a1.checkConflict(a4));
		assertDoesNotThrow(() -> a4.checkConflict(a1));
	}

	/**
	 * Test that the Activities happening in one day with an overlapping time
	 * conflict.
	 */
	@Test
	public void testCheckConflictWithConflict() {
		Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", ENROLLMENT_CAP, "MW", 1330,
				1445);
		Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", ENROLLMENT_CAP, "M", 1330, 1445);
		Activity a3 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", ENROLLMENT_CAP, "MW", 1445,
				1600);
		Activity a4 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", ENROLLMENT_CAP, "MW", 1330,
				1600);
		Activity a5 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", ENROLLMENT_CAP, "MW", 1400,
				1430);
		Activity a6 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", ENROLLMENT_CAP, "MW", 1200,
				1400);
		
		Exception e1 = assertThrows(ConflictException.class, () -> a1.checkConflict(a2));
		assertEquals("Schedule conflict.", e1.getMessage());

		Exception e2 = assertThrows(ConflictException.class, () -> a2.checkConflict(a1));
		assertEquals("Schedule conflict.", e2.getMessage());
		
		Exception e3 = assertThrows(ConflictException.class, () -> a1.checkConflict(a3));
		assertEquals("Schedule conflict.", e3.getMessage());
		
		Exception e4 = assertThrows(ConflictException.class, () -> a3.checkConflict(a1));
		assertEquals("Schedule conflict.", e4.getMessage());
		
		Exception e5 = assertThrows(ConflictException.class, () -> a2.checkConflict(a3));
		assertEquals("Schedule conflict.", e5.getMessage());
		
		Exception e6 = assertThrows(ConflictException.class, () -> a3.checkConflict(a2));
		assertEquals("Schedule conflict.", e6.getMessage());
		
		Exception e7 = assertThrows(ConflictException.class, () -> a3.checkConflict(a4));
		assertEquals("Schedule conflict.", e7.getMessage());
		
		Exception e8 = assertThrows(ConflictException.class, () -> a4.checkConflict(a3));
		assertEquals("Schedule conflict.", e8.getMessage());
		
		Exception e9 = assertThrows(ConflictException.class, () -> a1.checkConflict(a4));
		assertEquals("Schedule conflict.", e9.getMessage());
		
		Exception e10 = assertThrows(ConflictException.class, () -> a4.checkConflict(a1));
		assertEquals("Schedule conflict.", e10.getMessage());
		
		Exception e11 = assertThrows(ConflictException.class, () -> a1.checkConflict(a5));
		assertEquals("Schedule conflict.", e11.getMessage());
		
		Exception e12 = assertThrows(ConflictException.class, () -> a5.checkConflict(a1));
		assertEquals("Schedule conflict.", e12.getMessage());
		
		Exception e13 = assertThrows(ConflictException.class, () -> a1.checkConflict(a6));
		assertEquals("Schedule conflict.", e13.getMessage());
		
		Exception e14 = assertThrows(ConflictException.class, () -> a6.checkConflict(a1));
		assertEquals("Schedule conflict.", e14.getMessage());
	}

}
