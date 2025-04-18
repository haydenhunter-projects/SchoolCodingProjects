package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CourseNameValidatorTest {

	@Test
	void testIsValid() {
		CourseNameValidator cnv = new CourseNameValidator();
		assertDoesNotThrow(() -> cnv.isValid("CSC216"));
		assertDoesNotThrow(() -> cnv.isValid("CS216"));
		assertDoesNotThrow(() -> cnv.isValid("C216"));
		assertDoesNotThrow(() -> cnv.isValid("CSCS216"));
		assertDoesNotThrow(() -> cnv.isValid("CSC216C"));
		assertDoesNotThrow(() -> cnv.isValid("CS216C"));
		assertDoesNotThrow(() -> cnv.isValid("C216C"));
		assertDoesNotThrow(() -> cnv.isValid("ESCS216C"));
		
		assertThrows(InvalidTransitionException.class, () -> cnv.isValid("216"));
		assertThrows(InvalidTransitionException.class, () -> cnv.isValid("AAAAA216"));
		assertThrows(InvalidTransitionException.class, () -> cnv.isValid("CSC 216"));
		assertThrows(InvalidTransitionException.class, () -> cnv.isValid("CSC 1256"));
		assertThrows(InvalidTransitionException.class, () -> cnv.isValid("CSC1C2"));
		assertThrows(InvalidTransitionException.class, () -> cnv.isValid("CSC12C"));
		assertThrows(InvalidTransitionException.class, () -> cnv.isValid("CSC1122"));
		assertThrows(InvalidTransitionException.class, () -> cnv.isValid("CSC122CC"));
		assertThrows(InvalidTransitionException.class, () -> cnv.isValid("CSC122C2"));
		
	}

}
