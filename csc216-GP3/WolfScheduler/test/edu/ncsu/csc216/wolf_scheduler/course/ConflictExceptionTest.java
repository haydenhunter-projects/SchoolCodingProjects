/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the custom exception ConflictException to check if it works as intended
 * Uses standard two constructors for custom exceptions
 * 
 * @author hchunter
 *
 */
class ConflictExceptionTest {

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.wolf_scheduler.course.ConflictException#ConflictException(java.lang.String)}.
	 */
	@Test
	public void testConflictExceptionString() {
		ConflictException ce = new ConflictException("Custom exception message");
		assertEquals("Custom exception message", ce.getMessage());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.wolf_scheduler.course.ConflictException#ConflictException()}.
	 */
	@Test
	void testConflictException() {
		ConflictException ce = new ConflictException("Schedule conflict.");
		assertEquals("Schedule conflict.", ce.getMessage());
	}

}
