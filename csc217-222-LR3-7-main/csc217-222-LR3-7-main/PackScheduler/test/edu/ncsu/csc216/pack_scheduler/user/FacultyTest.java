package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FacultyTest {

	/** Course name */
	private static final String FIRSTNAME = "Brian";
	/** Course title */
	private static final String LASTNAME = "Jordan";
	/** Course section */
	private static final String ID = "brjor1";
	/** Course credits */
	private static final String EMAIL = "brjor1@ncsu.edu";
	/** Course instructor id */
	private static final String PASSWORD = "12345";
	/** Course meeting days */
	private static final int MAXCOURSES = 3;

	/**
	 * Tests constructing a Student with maximum number of credits.
	 */
	@Test
	void testStudentWithMaxCredits() {
		// Test a valid construction
		Faculty s = assertDoesNotThrow(() -> new Faculty(FIRSTNAME, LASTNAME, ID, EMAIL, PASSWORD, MAXCOURSES),
				"Should not throw exception");
		assertAll("Faculty", () -> assertEquals(FIRSTNAME, s.getFirstName(), "incorrect first name"),
				() -> assertEquals(LASTNAME, s.getLastName(), "incorrect last name"),
				() -> assertEquals(ID, s.getId(), "incorrect id"),
				() -> assertEquals(EMAIL, s.getEmail(), "incorrect email"),
				() -> assertEquals(PASSWORD, s.getPassword(), "incorrect password"),
				() -> assertEquals(MAXCOURSES, s.getMaxCourses(), "incorrect max courses"));
	}

	
	
	/**
	 * Tests constructing a Student invalid
	 */
	@Test
	void testInvalidContructor() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Faculty("first", "last", "", "email@ncsu.edu", "hashedpassword", 3));
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> new Faculty("first", "", "id", "email@ncsu.edu", "hashedpassword", 3));
		Exception e3 = assertThrows(IllegalArgumentException.class,
				() -> new Faculty("", "last", "id", "email@ncsu.edu", "hashedpassword", 3));
		Exception e4 = assertThrows(IllegalArgumentException.class,
				() -> new Faculty("first", "last", "id", "", "hashedpassword", 3));
		Exception e5 = assertThrows(IllegalArgumentException.class,
				() -> new Faculty("first", "last", "id", "email@ncsu.edu", "", 3));
		Exception e6 = assertThrows(IllegalArgumentException.class,
				() -> new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword", -1));
		Exception e7 = assertThrows(IllegalArgumentException.class,
				() -> new Faculty("first", "last", null, "email@ncsu.edu", "hashedpassword", 3));
		assertEquals("Invalid id", e1.getMessage());
		assertEquals("Invalid id", e7.getMessage());
		assertEquals("Invalid last name", e2.getMessage());
		assertEquals("Invalid first name", e3.getMessage());
		assertEquals("Invalid email", e4.getMessage());
		assertEquals("Invalid password", e5.getMessage());
		assertEquals("Invalid max courses", e6.getMessage());
	
	}

	/**
	 * Tests setting first name that is invalid
	 */
	@Test
	void testSetFirstNameInvalid() {
		//Construct a valid Student
		User s = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword", 3);
		Exception e1 = assertThrows(IllegalArgumentException.class,
						() -> s.setFirstName(null));
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> s.setFirstName(""));
		assertEquals("Invalid first name", e2.getMessage());
		assertEquals("Invalid first name", e1.getMessage()); //Check correct exception message
		assertEquals("first", s.getFirstName()); //Check that first name didn't change
	}

	/**
	 * Tests setting last name that is invalid
	 */
	@Test
	void testSetLastNameInvalid() {
		//Construct a valid Student
				User s = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword", 3);
				Exception e1 = assertThrows(IllegalArgumentException.class,
								() -> s.setLastName(null));
				Exception e2 = assertThrows(IllegalArgumentException.class,
						() -> s.setLastName(""));
				assertEquals("Invalid last name", e1.getMessage()); //Check correct exception message
				assertEquals("Invalid last name", e2.getMessage()); //Check correct exception message
				assertEquals("last", s.getLastName()); //Check that first name didn't change
	}

	/**
	 * Tests setting email that is invalid
	 */
	@Test
	void testSetEmailInvalid() {
		//Construct a valid Student
		User s = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword", 3);
		Exception e1 = assertThrows(IllegalArgumentException.class,
						() -> s.setEmail(null));
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> s.setEmail(""));
		Exception e3 = assertThrows(IllegalArgumentException.class,
				() -> s.setEmail("emailncsu.edu"));
		Exception e4 = assertThrows(IllegalArgumentException.class,
				() -> s.setEmail("email@ncsuedu"));
		Exception e5 = assertThrows(IllegalArgumentException.class,
				() -> s.setEmail("email.ncsu@edu"));
		assertEquals("Invalid email", e1.getMessage()); //Check correct exception message
		assertEquals("Invalid email", e2.getMessage()); //Check correct exception message
		assertEquals("Invalid email", e3.getMessage()); //Check correct exception message
		assertEquals("Invalid email", e4.getMessage()); //Check correct exception message
		assertEquals("Invalid email", e5.getMessage()); //Check correct exception message
		assertEquals("email@ncsu.edu", s.getEmail()); //Check that first name didn't change
	}

//	/**
//	 * Tests setting credits that is invalid
//	 */
//	@Test
//	void testSetMaxCoursesInvalid() {
//		Faculty s = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword", 3);
//		Exception e1 = assertThrows(IllegalArgumentException.class,
//						() -> s.setMaxCourses(3));
//		Exception e2 = assertThrows(IllegalArgumentException.class,
//				() -> s.setMaxCourses(3));
//		Exception e3 = assertThrows(IllegalArgumentException.class,
//				() -> s.setMaxCourses(-1));
//		assertEquals("Invalid max credits", e1.getMessage()); //Check correct exception message
//		assertEquals("Invalid max credits", e2.getMessage()); //Check correct exception message
//		assertEquals("Invalid max credits", e3.getMessage()); //Check correct exception message
//		assertEquals(3, s.getMaxCourses()); //Check that default did not change
//	}
	
	/**
	 * Tests setting maxCredits
	 */
	@Test
	void testSetMaxCourses() {
		Faculty s = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword", 3);
		s.setMaxCourses(3);
		assertEquals(3, s.getMaxCourses()); // checks setter
	}

	/**
	 * Tests setting password that is invalid
	 */
	@Test
	void testSetPasswordInvalid() {
		User s = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword", 3);
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> s.setPassword(null));
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> s.setPassword(""));
		Exception e3 = assertThrows(IllegalArgumentException.class,
				() -> s.setPassword(null));
		assertEquals("Invalid password", e1.getMessage()); //Check correct exception message
		assertEquals("Invalid password", e2.getMessage()); //Check correct exception message
		assertEquals("Invalid password", e3.getMessage()); //Check correct exception message
	}
	
	/**
	 * Tests setting password
	 */
	@Test
	void testSetPassword() {
		User s = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword", 3);
		assertEquals("hashedpassword", s.getPassword()); // checks pass from constructor
		s.setPassword("abcde");
		assertEquals("abcde", s.getPassword()); // checks setter
	}


	/**
	 * Test toString() method.
	 */
	@Test
	public void testToString() {
		User s = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword", 3);
		assertEquals("first,last,id,email@ncsu.edu,hashedpassword,3", s.toString());
	}

	/**
	 * Tests equals object
	 */
	@Test
	void testEqualsObject() {
		User s1 = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword", 3);
		User s2 = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword", 3);
		User s3 = new Faculty("first2", "last", "id", "email@ncsu.edu", "hashedpassword", 3);
		User s4 = new Faculty("first", "last2", "id", "email@ncsu.edu", "hashedpassword", 3);
		User s5 = new Faculty("first", "last", "id2", "email@ncsu.edu", "hashedpassword", 3);
		User s6 = new Faculty("first", "last", "id", "email2@ncsu.edu", "hashedpassword", 3);
		User s7 = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword2", 3);
		assertTrue(s1.equals(s2));
		assertFalse(s2.equals(s3));
		assertFalse(s2.equals(s4));
		assertFalse(s2.equals(s5));
		assertFalse(s2.equals(s6));
		assertFalse(s2.equals(s7));
		
	}
	
	/**
	 * Tests hashcode
	 */
	@Test
	void testHashCode() {
		User s1 = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword", 3);
		User s2 = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword", 3);
		User s3 = new Faculty("first2", "last", "id", "email@ncsu.edu", "hashedpassword", 3);
		User s4 = new Faculty("first", "last2", "id", "email@ncsu.edu", "hashedpassword", 3);
		User s5 = new Faculty("first", "last", "id2", "email@ncsu.edu", "hashedpassword", 3);
		User s6 = new Faculty("first", "last", "id", "email2@ncsu.edu", "hashedpassword", 3);
		User s7 = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword2", 3);
		assertEquals(s1.hashCode(), s2.hashCode());
		assertNotEquals(s2.hashCode(), s3.hashCode());
		assertNotEquals(s2.hashCode(), s4.hashCode());
		assertNotEquals(s2.hashCode(), s5.hashCode());
		assertNotEquals(s2.hashCode(), s6.hashCode());
		assertNotEquals(s2.hashCode(), s7.hashCode());
	}

	
	
	
	

}
