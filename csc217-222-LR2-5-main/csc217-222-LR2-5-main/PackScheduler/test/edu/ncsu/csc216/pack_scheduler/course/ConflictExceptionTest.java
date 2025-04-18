/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * 
 * Test class for the ConflictException class.
 * 
 * @author Hari Vemulapalli
 */
class ConflictExceptionTest {

	/**
	 * Tests the method for the custom exception message when throwing
	 * ConflictException
	 */
	@Test
	public void testConflictExceptionString() {
		ConflictException ce = new ConflictException("Custom exception message");
		assertEquals("Custom exception message", ce.getMessage());
	}

	/**
	 * Tests the method for the default exception message when throwing
	 * ConflictException
	 */
	@Test
	public void testConflictException() {
		ConflictException ceDefault = new ConflictException();
		assertEquals("Schedule conflict.", ceDefault.getMessage());
	}

}
