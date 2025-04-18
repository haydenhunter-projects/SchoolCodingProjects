/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the CourseNameValidator class
 * @author nhurst
 * @author skpoolla
 * @author hchunter
 */
class CourseNameValidatorFSMTest {

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.course.validator.CourseNameValidatorFSM#isValid(java.lang.String)}.
	 * 
	 * @throws InvalidTransitionException with message Invalid FSM Transition if
	 *                                    invalid transition is entered.
	 */
	@Test
	void testIsValid() throws InvalidTransitionException {

		CourseNameValidatorFSM c = new CourseNameValidatorFSM();
		assertTrue(c.isValid("CSCA116"));
		assertTrue(c.isValid("E115"));
		assertTrue(c.isValid("E105"));
		assertTrue(c.isValid("E115"));
		assertTrue(c.isValid("HESF115"));
		assertTrue(c.isValid("PY208"));
		assertTrue(c.isValid("CSC216A"));
		Exception e1 = assertThrows(InvalidTransitionException.class, () -> c.isValid("CSC 216"));
		assertEquals("Course name can only contain letters and digits.", e1.getMessage());

		Exception e2 = assertThrows(InvalidTransitionException.class, () -> c.isValid("203"));
		assertEquals("Course name must start with a letter.", e2.getMessage());

		assertFalse(c.isValid("EER"));
		assertFalse(c.isValid("HEFS01"));

		Exception e4 = assertThrows(InvalidTransitionException.class, () -> c.isValid("HESFG115"));
		assertEquals("Course name cannot start with more than 4 letters.", e4.getMessage());

		Exception e5 = assertThrows(InvalidTransitionException.class, () -> c.isValid("HESFG11"));
		assertEquals("Course name cannot start with more than 4 letters.", e5.getMessage());

		Exception e6 = assertThrows(InvalidTransitionException.class, () -> c.isValid("HESFG1"));
		assertEquals("Course name cannot start with more than 4 letters.", e6.getMessage());

		Exception e7 = assertThrows(InvalidTransitionException.class, () -> c.isValid("Csc 216"));
		assertEquals("Course name can only contain letters and digits.", e7.getMessage());

		Exception e8 = assertThrows(InvalidTransitionException.class, () -> c.isValid("CSC216AA"));
		assertEquals("Course name can only have a 1 letter suffix.", e8.getMessage());

		Exception e9 = assertThrows(InvalidTransitionException.class, () -> c.isValid("CSC216A1"));
		assertEquals("Course name cannot contain digits after the suffix.", e9.getMessage());

		Exception e10 = assertThrows(InvalidTransitionException.class, () -> c.isValid("CSC2166"));
		assertEquals("Course name can only have 3 digits.", e10.getMessage());

		Exception e11 = assertThrows(InvalidTransitionException.class, () -> c.isValid("CSC21A"));
		assertEquals("Course name must have 3 digits.", e11.getMessage());

	}

}
