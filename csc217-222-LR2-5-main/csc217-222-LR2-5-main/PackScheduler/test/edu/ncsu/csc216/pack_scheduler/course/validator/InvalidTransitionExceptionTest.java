/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the custom exception invalid transition exception.
 * @author skpoolla
 * @author hchunter
 * @author nhurst
 */
class InvalidTransitionExceptionTest {

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException#InvalidTransitionException(java.lang.String)}.
	 */
	@Test
	void testInvalidTransitionExceptionString() {
		InvalidTransitionException ce = new InvalidTransitionException("Invalid FSM.");
		assertEquals("Invalid FSM.", ce.getMessage());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException#InvalidTransitionException()}.
	 */
	@Test
	void testInvalidTransitionException() {
		InvalidTransitionException ceDefault = new InvalidTransitionException();
		assertEquals("Invalid FSM Transition.", ceDefault.getMessage());
	}

}
