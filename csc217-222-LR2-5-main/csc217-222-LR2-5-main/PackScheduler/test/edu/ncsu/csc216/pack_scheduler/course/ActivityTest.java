/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * 
 * Tests the methods used in the Activity class
 * 
 * @author Hari Vemulapalli
 */
public class ActivityTest {

	/**
	 * Checks if there is a conflict with objects that don't actually have a
	 * conflict
	 */
	@Test
	public void testCheckConflict() {
		Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "MW", 1330, 1445);
		Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "TH", 1330, 1445);
		Activity a3 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "MW", 1440, 1555);
		Activity a4 = new Course("CSC216", "Software Development Fundamentals", "003", 3, "sesmith5", 10, "MW", 1145, 1300);
		Activity a5 = new Course("CSC217", "Software Development Fundamentals Lab", "002", 3, "sesmith5", 10, "TH", 1130,
				1320);
		Activity a6 = new Course("CSC217", "Software Development Fundamentals Lab", "003", 3, "sesmith5", 10, "TH", 1140,
				1330);
		Activity a7 = new Course("CSC217", "Software Development Fundamentals Lab", "001", 3, "sesmith5", 10, "A");
		Activity a8 = new Course("CSC216", "Software Development Fundamentals", "004", 3, "sesmith5", 10, "A");

		// Both are same time but different days
		assertDoesNotThrow(() -> a1.checkConflict(a2));
		assertDoesNotThrow(() -> a2.checkConflict(a1));
		// this.endTime equals the start time of inputed Activity object, but with
		// different days
		assertDoesNotThrow(() -> a2.checkConflict(a3));
		// Checks a conflict with another event that would be at a different time and
		// different days
		assertDoesNotThrow(() -> a4.checkConflict(a2));
		// Checks a conflict with another event that is within the given event, but
		// different days
		assertDoesNotThrow(() -> a5.checkConflict(a4));
		// Checks a conflict with given event that is within another event, but
		// different days
		assertDoesNotThrow(() -> a4.checkConflict(a5));
		// this.startTime equals the end time of inputed Activity object, but with
		// different days
		assertDoesNotThrow(() -> a1.checkConflict(a6));
		// Using the "A" for meeting days to make sure there is no conflict
		assertDoesNotThrow(() -> a7.checkConflict(a1));
		assertDoesNotThrow(() -> a1.checkConflict(a7));
		assertDoesNotThrow(() -> a7.checkConflict(a8));
		assertDoesNotThrow(() -> a8.checkConflict(a7));
	}

	/**
	 * Checks if there is a conflict with an actual conflict
	 */
	@Test
	public void testCheckConflictWithConflict() {
		Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "MW", 1330, 1445);
		Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "M", 1330, 1445);
		Activity a3 = new Course("CSC216", "Software Development Fundamentals", "002", 3, "sesmith5", 10, "MW", 1445, 1555);
		Activity a4 = new Course("CSC216", "Software Development Fundamentals", "003", 3, "sesmith5", 10, "MW", 1145, 1300);
		Activity a5 = new Course("CSC217", "Software Development Fundamentals Lab", "002", 3, "sesmith5", 10, "MW", 1130,
				1320);
		Activity a6 = new Course("CSC216", "Software Development Fundamentals", "004", 3, "sesmith5", 10, "MW", 1115, 1230);
		Activity a7 = new Course("CSC216", "Software Development Fundamentals", "002", 3, "sesmith5", 10, "MW", 1445, 1445);
		Activity a8 = new Course("CSC216", "Software Development Fundamentals", "002", 3, "sesmith5", 10, "MW", 1340, 1340);
		Activity a9 = new Course("CSC216", "Software Development Fundamentals", "005", 3, "sesmith5", 10, "W", 1330, 1340);
		Activity a10 = new Course("CSC216", "Software Development Fundamentals", "005", 3, "sesmith5", 10, "W", 1340, 1340);

		Exception e1 = assertThrows(ConflictException.class, () -> a1.checkConflict(a2));
		assertEquals("Schedule conflict.", e1.getMessage());
		Exception e2 = assertThrows(ConflictException.class, () -> a2.checkConflict(a7));
		assertEquals("Schedule conflict.", e2.getMessage());
		Exception e3 = assertThrows(ConflictException.class, () -> a7.checkConflict(a2));
		assertEquals("Schedule conflict.", e3.getMessage());
		Exception e4 = assertThrows(ConflictException.class, () -> a4.checkConflict(a5));
		assertEquals("Schedule conflict.", e4.getMessage());
		Exception e5 = assertThrows(ConflictException.class, () -> a5.checkConflict(a4));
		assertEquals("Schedule conflict.", e5.getMessage());
		Exception e6 = assertThrows(ConflictException.class, () -> a2.checkConflict(a3));
		assertEquals("Schedule conflict.", e6.getMessage());
		Exception e7 = assertThrows(ConflictException.class, () -> a5.checkConflict(a6));
		assertEquals("Schedule conflict.", e7.getMessage());
		Exception e8 = assertThrows(ConflictException.class, () -> a2.checkConflict(a8));
		assertEquals("Schedule conflict.", e8.getMessage());
		Exception e9 = assertThrows(ConflictException.class, () -> a8.checkConflict(a2));
		assertEquals("Schedule conflict.", e9.getMessage());
		Exception e10 = assertThrows(ConflictException.class, () -> a9.checkConflict(a10));
		assertEquals("Schedule conflict.", e10.getMessage());
		Exception e11 = assertThrows(ConflictException.class, () -> a10.checkConflict(a9));
		assertEquals("Schedule conflict.", e11.getMessage());

	}

}
