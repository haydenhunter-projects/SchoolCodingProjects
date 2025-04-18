/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Activity test was made to check the newly implemented checkConflict method in
 * activity.
 * 
 * @author hchunter
 *
 */
class ActivityTest {

	/**
	 * tests checkConflict with no conflicts.
	 */
	@Test
	void testCheckConflict() {
		Activity a1 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1330,
				1445);
		Activity a2 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "TH", 1330,
				1445);
		Activity a3 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1450,
				1600);
		Activity a4 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1650,
				1700);
		Activity a5 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "TH", 1000,
				1445);
		Activity a6 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "TH", 800, 900);

		assertDoesNotThrow(() -> a1.checkConflict(a2));
		assertDoesNotThrow(() -> a2.checkConflict(a1));
		assertDoesNotThrow(() -> a3.checkConflict(a4));
		assertDoesNotThrow(() -> a4.checkConflict(a3));
		assertDoesNotThrow(() -> a5.checkConflict(a6));
		assertDoesNotThrow(() -> a6.checkConflict(a5));
	}

	/**
	 * tests checkConflict with conflicts.
	 */
	@Test
	public void testCheckConflictWithConflict() {
		Activity a1 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1330,
				1445);
		Activity a2 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "M", 1330, 1445);
		Activity a3 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1450,
				1600);
		Activity a4 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1600,
				1700);
		Activity a5 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "WH", 1000,
				1445);
		Activity a6 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "TH", 800, 1000);
		Activity a7 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "WH", 900, 1445);
		Activity a8 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "WT", 800, 1445);
		Activity a9 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MT", 900, 1445);
		Activity a10 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "WT", 900,
				1445);
		Activity a11 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "WH", 900,
				1000);

		Activity b1 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "W", 1400, 1430);
		Activity b2 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1330,
				1445);

		Exception e1 = assertThrows(ConflictException.class, () -> a1.checkConflict(a2));
		assertEquals("Schedule conflict.", e1.getMessage());

		Exception e2 = assertThrows(ConflictException.class, () -> a2.checkConflict(a1));
		assertEquals("Schedule conflict.", e2.getMessage());

		Exception e3 = assertThrows(ConflictException.class, () -> a3.checkConflict(a4));
		assertEquals("Schedule conflict.", e3.getMessage());

		Exception e4 = assertThrows(ConflictException.class, () -> a4.checkConflict(a3));
		assertEquals("Schedule conflict.", e4.getMessage());

		Exception e5 = assertThrows(ConflictException.class, () -> a5.checkConflict(a6));
		assertEquals("Schedule conflict.", e5.getMessage());

		Exception e6 = assertThrows(ConflictException.class, () -> a6.checkConflict(a5));
		assertEquals("Schedule conflict.", e6.getMessage());

		Exception e7 = assertThrows(ConflictException.class, () -> a7.checkConflict(a8));
		assertEquals("Schedule conflict.", e7.getMessage());

		Exception e8 = assertThrows(ConflictException.class, () -> a8.checkConflict(a7));
		assertEquals("Schedule conflict.", e8.getMessage());

		Exception e9 = assertThrows(ConflictException.class, () -> a9.checkConflict(a10));
		assertEquals("Schedule conflict.", e9.getMessage());

		Exception e10 = assertThrows(ConflictException.class, () -> a10.checkConflict(a9));
		assertEquals("Schedule conflict.", e10.getMessage());

		Exception e11 = assertThrows(ConflictException.class, () -> a6.checkConflict(a11));
		assertEquals("Schedule conflict.", e11.getMessage());

		Exception staff1 = assertThrows(ConflictException.class, () -> b1.checkConflict(b2));
		assertEquals("Schedule conflict.", staff1.getMessage());

		Exception staff2 = assertThrows(ConflictException.class, () -> b2.checkConflict(b1));
		assertEquals("Schedule conflict.", staff2.getMessage());
	}
}
