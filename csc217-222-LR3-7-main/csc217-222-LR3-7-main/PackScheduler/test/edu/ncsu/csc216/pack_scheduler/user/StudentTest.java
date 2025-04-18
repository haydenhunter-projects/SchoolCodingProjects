package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the Student object.
 * @author Thien Nguyen, Jerolyn ClementRaj, Eric Smith
 */
public class StudentTest {

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
	private static final int MAXCREDITS = 15;

	/**
	 * Tests constructing a Student with maximum number of credits.
	 */
	@Test
	void testStudentWithMaxCredits() {
		// Test a valid construction
		Student s = assertDoesNotThrow(() -> new Student(FIRSTNAME, LASTNAME, ID, EMAIL, PASSWORD, MAXCREDITS),
				"Should not throw exception");
		assertAll("Student", () -> assertEquals(FIRSTNAME, s.getFirstName(), "incorrect first name"),
				() -> assertEquals(LASTNAME, s.getLastName(), "incorrect last name"),
				() -> assertEquals(ID, s.getId(), "incorrect id"),
				() -> assertEquals(EMAIL, s.getEmail(), "incorrect email"),
				() -> assertEquals(PASSWORD, s.getPassword(), "incorrect password"),
				() -> assertEquals(MAXCREDITS, s.getMaxCredits(), "incorrect max credits"));
	}

	/**
	 * Tests constructing a Course with no number of credits.
	 */
	@Test
	void testStudentWithNoMaxCredits() {
		// Test a valid construction
		User s = assertDoesNotThrow(() -> new Student(FIRSTNAME, LASTNAME, ID, EMAIL, PASSWORD),
				"Should not throw exception");
		assertAll("Student", () -> assertEquals(FIRSTNAME, s.getFirstName(), "incorrect first name"),
				() -> assertEquals(LASTNAME, s.getLastName(), "incorrect last name"),
				() -> assertEquals(ID, s.getId(), "incorrect id"),
				() -> assertEquals(EMAIL, s.getEmail(), "incorrect email"),
				() -> assertEquals(PASSWORD, s.getPassword(), "incorrect password"));
	}
	
	/**
	 * Tests constructing a Student invalid
	 */
	@Test
	void testInvalidContructor() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student("first", "last", "", "email@ncsu.edu", "hashedpassword"));
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> new Student("first", "", "id", "email@ncsu.edu", "hashedpassword"));
		Exception e3 = assertThrows(IllegalArgumentException.class,
				() -> new Student("", "last", "id", "email@ncsu.edu", "hashedpassword"));
		Exception e4 = assertThrows(IllegalArgumentException.class,
				() -> new Student("first", "last", "id", "", "hashedpassword"));
		Exception e5 = assertThrows(IllegalArgumentException.class,
				() -> new Student("first", "last", "id", "email@ncsu.edu", ""));
		Exception e6 = assertThrows(IllegalArgumentException.class,
				() -> new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 19));
		Exception e7 = assertThrows(IllegalArgumentException.class,
				() -> new Student("first", "last", null, "email@ncsu.edu", "hashedpassword"));
		assertEquals("Invalid id", e1.getMessage());
		assertEquals("Invalid id", e7.getMessage());
		assertEquals("Invalid last name", e2.getMessage());
		assertEquals("Invalid first name", e3.getMessage());
		assertEquals("Invalid email", e4.getMessage());
		assertEquals("Invalid password", e5.getMessage());
		assertEquals("Invalid max credits", e6.getMessage());
	
	}

	/**
	 * Tests setting first name that is invalid
	 */
	@Test
	void testSetFirstNameInvalid() {
		//Construct a valid Student
		User s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
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
				User s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
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
		User s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
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

	/**
	 * Tests setting credits that is invalid
	 */
	@Test
	void testSetMaxCreditsInvalid() {
		Student s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		Exception e1 = assertThrows(IllegalArgumentException.class,
						() -> s.setMaxCredits(2));
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> s.setMaxCredits(19));
		Exception e3 = assertThrows(IllegalArgumentException.class,
				() -> s.setMaxCredits(-1));
		assertEquals("Invalid max credits", e1.getMessage()); //Check correct exception message
		assertEquals("Invalid max credits", e2.getMessage()); //Check correct exception message
		assertEquals("Invalid max credits", e3.getMessage()); //Check correct exception message
		assertEquals(18, s.getMaxCredits()); //Check that default did not change
	}
	
	/**
	 * Tests setting maxCredits
	 */
	@Test
	void testSetMaxCredits() {
		Student s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		assertEquals(18, s.getMaxCredits()); // checks default
		s.setMaxCredits(15);
		assertEquals(15, s.getMaxCredits()); // checks setter
		s.setMaxCredits(3);
		assertEquals(3, s.getMaxCredits()); // checks border setter
		s.setMaxCredits(18);
		assertEquals(18, s.getMaxCredits()); // checks border setter
	}

	/**
	 * Tests setting password that is invalid
	 */
	@Test
	void testSetPasswordInvalid() {
		User s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
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
		User s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		assertEquals("hashedpassword", s.getPassword()); // checks pass from constructor
		s.setPassword("abcde");
		assertEquals("abcde", s.getPassword()); // checks setter
	}


	/**
	 * Test toString() method.
	 */
	@Test
	public void testToString() {
		User s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		assertEquals("first,last,id,email@ncsu.edu,hashedpassword,18", s.toString());
	}

	/**
	 * Tests equals object
	 */
	@Test
	void testEqualsObject() {
		User s1 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		User s2 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		User s3 = new Student("first2", "last", "id", "email@ncsu.edu", "hashedpassword");
		User s4 = new Student("first", "last2", "id", "email@ncsu.edu", "hashedpassword");
		User s5 = new Student("first", "last", "id2", "email@ncsu.edu", "hashedpassword");
		User s6 = new Student("first", "last", "id", "email2@ncsu.edu", "hashedpassword");
		User s7 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword2");
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
		User s1 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		User s2 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		User s3 = new Student("first2", "last", "id", "email@ncsu.edu", "hashedpassword");
		User s4 = new Student("first", "last2", "id", "email@ncsu.edu", "hashedpassword");
		User s5 = new Student("first", "last", "id2", "email@ncsu.edu", "hashedpassword");
		User s6 = new Student("first", "last", "id", "email2@ncsu.edu", "hashedpassword");
		User s7 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword2");
		assertEquals(s1.hashCode(), s2.hashCode());
		assertNotEquals(s2.hashCode(), s3.hashCode());
		assertNotEquals(s2.hashCode(), s4.hashCode());
		assertNotEquals(s2.hashCode(), s5.hashCode());
		assertNotEquals(s2.hashCode(), s6.hashCode());
		assertNotEquals(s2.hashCode(), s7.hashCode());
	}

	/**
	 * Tests compareTo() method.
	 */
	@Test
	public void testCompareTo() {
		Student s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		Student s1 = new Student(FIRSTNAME, LASTNAME, ID, EMAIL, PASSWORD);
		Student s2 = new Student("Michael", "Jordan", "mjord1", "mjord1@ncsu.edu", "123");
		Student s3 = new Student("Michael", "Jordan", "mjord2", "mjord2@ncsu.edu", "456");
		assertEquals(1, s.compareTo(s1));
		assertEquals(-1, s1.compareTo(s));
		assertEquals(-1, s1.compareTo(s2));
		assertEquals(1, s2.compareTo(s1));
		assertEquals(-1, s2.compareTo(s3));
		assertEquals(1, s3.compareTo(s2));
		assertEquals(0, s1.compareTo(s1));
		
		assertThrows(NullPointerException .class,
				() -> s.compareTo(null));
		
	}
}
