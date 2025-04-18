package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test the methods in the Student class
 * 
 * @author Hari Vemulapalli
 * @author Hayden Hunter
 * @author Victor Hermida
 *
 */
public class StudentTest {

	/** Course name */
	private static final String FIRSTNAME = "John";
	/** Course title */
	private static final String LASTNAME = "Booth";
	/** Course section */
	private static final String ID = "jwbooth";
	/** Course credits */
	private static final String EMAIL = "jbooth@ncsu.edu";
	/** Course password */
	private static final String PASSWORD = "password";
	/** Course instructor id */
	private static final int MAXCREDITS = 17;

	/** First Student Object */
	Student stu1 = new Student("John", "Booth", "jwbooth", "jbooth@ncsu.edu", "password", 17);
	/** Second Student Object */
	Student stu2 = new Student("Robert", "Phone", "rphone", "rphone@ncsu.edu", "Clever");
	/** Third Student Object */
	User stu3 = new Student("Ben", "Dover", "bdover", "bdover@ncsu.edu", "Gamer65", 7);
	/** Fourth Student Object */
	User stu4 = new Student("Ben", "Dover", "bdover", "bdover@ncsu.edu", "Gamer65", 7);
	/** Fifth Student Object */
	User stu5 = new Student("Joe", "Mama", "jmama", "jmama@ncsu.edu", "Mymom23");
	/** Sixth student Object */
	User stu6 = new Student("Itachi", "Uchiha", "iuchiha", "iuchiha@ncsu.edu", "Clanslayer01", 6);
	/** Seventh student Object */
	User stu7 = new Student("Rick", "Grimes", "rgrimes", "rgrimes@ncsu.edu", "Carollll1");
	/** Eighth student Object */
	User stu8 = new Student("Joe", "Goldberg", "jgoldberg", "jgoldberg@ncsu.edu", "You44", 10);
	/** Ninth student Object */
	User stu9 = new Student("Joe", "Goldberg", "jgoldberg", "jgoldberg@ncsu.edu", "You44", 10);

	/**
	 * Tests Student.hashCode()
	 */
	@Test
	public void testHashCode() {

		assertEquals(stu3.hashCode(), stu4.hashCode());
	}

	/**
	 * Test Student constructor with given credit hours
	 */
	@Test
	public void testStudentConstructorWithCredits() {

		assertEquals("John", stu1.getFirstName());
		assertEquals("Booth", stu1.getLastName());
		assertEquals("jwbooth", stu1.getId());
		assertEquals("jbooth@ncsu.edu", stu1.getEmail());
		assertEquals("password", stu1.getPassword());
		assertEquals(17, stu1.getMaxCredits());
	}

	/**
	 * Test Student constructor without putting credit hours (uses default credit
	 * hours value!)
	 */
	@Test
	public void testStudentConstructorWithoutCredits() {
		// might come back
		assertEquals("Robert", stu2.getFirstName());
		assertEquals("Phone", stu2.getLastName());
		assertEquals("rphone", stu2.getId());
		assertEquals("rphone@ncsu.edu", stu2.getEmail());
		assertEquals("Clever", stu2.getPassword());
		assertEquals(18, stu2.getMaxCredits());
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> new Student("Robert", "Daniels", "", "rdaniel@ncsu.edu", "Arpensas32@"));
		assertEquals("Invalid id", e.getMessage());

	}

	/**
	 * Test Student.setFirstName()
	 */
	@Test
	public void testSetFirstName() {

		Exception e1 = assertThrows(IllegalArgumentException.class, () -> stu1.setFirstName(null));

		assertEquals("Invalid first name", e1.getMessage());
		assertEquals("John", stu1.getFirstName());

		Exception e2 = assertThrows(IllegalArgumentException.class, () -> stu2.setFirstName(null));

		assertEquals("Invalid first name", e2.getMessage());
		assertEquals("Robert", stu2.getFirstName());

		Exception e3 = assertThrows(IllegalArgumentException.class, () -> stu3.setFirstName(null));

		assertEquals("Invalid first name", e3.getMessage());
		assertEquals("Ben", stu3.getFirstName());

		Exception e4 = assertThrows(IllegalArgumentException.class, () -> stu5.setFirstName(null));

		assertEquals("Invalid first name", e4.getMessage());
		assertEquals("Joe", stu5.getFirstName());

		Exception e5 = assertThrows(IllegalArgumentException.class, () -> stu6.setFirstName(null));

		assertEquals("Invalid first name", e5.getMessage());
		assertEquals("Itachi", stu6.getFirstName());

		Exception e6 = assertThrows(IllegalArgumentException.class, () -> stu7.setFirstName(null));

		assertEquals("Invalid first name", e6.getMessage());
		assertEquals("Rick", stu7.getFirstName());

		Exception e7 = assertThrows(IllegalArgumentException.class, () -> stu8.setFirstName(null));

		assertEquals("Invalid first name", e7.getMessage());
		assertEquals("Joe", stu8.getFirstName());

	}

	/**
	 * Test Student.setLastName()
	 */
	@Test
	public void testSetLastName() {

		Exception e1 = assertThrows(IllegalArgumentException.class, () -> stu1.setLastName(null));

		assertEquals("Invalid last name", e1.getMessage());
		assertEquals("Booth", stu1.getLastName());

		Exception e2 = assertThrows(IllegalArgumentException.class, () -> stu2.setLastName(null));

		assertEquals("Invalid last name", e2.getMessage());
		assertEquals("Phone", stu2.getLastName());

		Exception e3 = assertThrows(IllegalArgumentException.class, () -> stu3.setLastName(null));

		assertEquals("Invalid last name", e3.getMessage());
		assertEquals("Dover", stu3.getLastName());

		Exception e4 = assertThrows(IllegalArgumentException.class, () -> stu5.setLastName(null));

		assertEquals("Invalid last name", e4.getMessage());
		assertEquals("Mama", stu5.getLastName());

		Exception e5 = assertThrows(IllegalArgumentException.class, () -> stu6.setLastName(null));

		assertEquals("Invalid last name", e5.getMessage());
		assertEquals("Uchiha", stu6.getLastName());

		Exception e6 = assertThrows(IllegalArgumentException.class, () -> stu7.setLastName(null));

		assertEquals("Invalid last name", e6.getMessage());
		assertEquals("Grimes", stu7.getLastName());

		Exception e7 = assertThrows(IllegalArgumentException.class, () -> stu8.setLastName(null));

		assertEquals("Invalid last name", e7.getMessage());
		assertEquals("Goldberg", stu8.getLastName());

	}

	/**
	 * Test Student.setEmail()
	 */
	@Test
	public void testSetEmail() {

		Exception e1 = assertThrows(IllegalArgumentException.class, () -> stu1.setEmail(null));

		assertEquals("Invalid email", e1.getMessage());
		assertEquals("jbooth@ncsu.edu", stu1.getEmail());

		Exception e2 = assertThrows(IllegalArgumentException.class, () -> stu2.setEmail(null));

		assertEquals("Invalid email", e2.getMessage());
		assertEquals("rphone@ncsu.edu", stu2.getEmail());

		Exception e3 = assertThrows(IllegalArgumentException.class, () -> stu4.setEmail(null));

		assertEquals("Invalid email", e3.getMessage());
		assertEquals("bdover@ncsu.edu", stu4.getEmail());

		Exception e4 = assertThrows(IllegalArgumentException.class, () -> stu5.setEmail(null));

		assertEquals("Invalid email", e4.getMessage());
		assertEquals("jmama@ncsu.edu", stu5.getEmail());

		Exception e5 = assertThrows(IllegalArgumentException.class, () -> stu6.setEmail(null));

		assertEquals("Invalid email", e5.getMessage());
		assertEquals("iuchiha@ncsu.edu", stu6.getEmail());

		Exception e6 = assertThrows(IllegalArgumentException.class, () -> stu7.setEmail(null));

		assertEquals("Invalid email", e6.getMessage());
		assertEquals("rgrimes@ncsu.edu", stu7.getEmail());

		Exception e7 = assertThrows(IllegalArgumentException.class, () -> stu8.setEmail(null));

		assertEquals("Invalid email", e7.getMessage());
		assertEquals("jgoldberg@ncsu.edu", stu8.getEmail());

	}

	/**
	 * Test Student.setPassword()
	 */
	@Test
	public void testSetPassword() {
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> stu1.setPassword(null));

		assertEquals("Invalid password", e1.getMessage());
		assertEquals("password", stu1.getPassword());

		Exception e2 = assertThrows(IllegalArgumentException.class, () -> stu2.setPassword(null));

		assertEquals("Invalid password", e2.getMessage());
		assertEquals("Clever", stu2.getPassword());

		Exception e3 = assertThrows(IllegalArgumentException.class, () -> stu3.setPassword(null));

		assertEquals("Invalid password", e3.getMessage());
		assertEquals("Gamer65", stu3.getPassword());

		Exception e4 = assertThrows(IllegalArgumentException.class, () -> stu5.setPassword(null));

		assertEquals("Invalid password", e4.getMessage());
		assertEquals("Mymom23", stu5.getPassword());

		Exception e5 = assertThrows(IllegalArgumentException.class, () -> stu6.setPassword(null));

		assertEquals("Invalid password", e5.getMessage());
		assertEquals("Clanslayer01", stu6.getPassword());

		Exception e6 = assertThrows(IllegalArgumentException.class, () -> stu7.setPassword(null));

		assertEquals("Invalid password", e6.getMessage());
		assertEquals("Carollll1", stu7.getPassword());

		Exception e7 = assertThrows(IllegalArgumentException.class, () -> stu8.setPassword(null));

		assertEquals("Invalid password", e7.getMessage());
		assertEquals("You44", stu8.getPassword());
	}

	/**
	 * Test Student.setMaxCredits()
	 */
	@Test
	public void testSetMaxCredits() {
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> stu1.setMaxCredits(2));

		assertEquals("Invalid max credits", e1.getMessage());
		assertEquals(17, stu1.getMaxCredits());
	}

	/**
	 * Test Student.equals()
	 */
	@Test
	public void testEqualsObject() {
		assertTrue(stu3.equals(stu4));
		assertFalse(stu1.equals(stu2));
		assertTrue(stu3.equals(stu3));
		assertTrue(stu8.equals(stu9));

	}

	/**
	 * Test Student.toString()
	 */
	@Test
	public void testToString() {
		User s1 = new Student(FIRSTNAME, LASTNAME, ID, EMAIL, PASSWORD, MAXCREDITS);
		assertEquals("John,Booth,jwbooth,jbooth@ncsu.edu,password,17", s1.toString());
		User s2 = new Student(FIRSTNAME, LASTNAME, ID, EMAIL, PASSWORD);
		assertEquals("John,Booth,jwbooth,jbooth@ncsu.edu,password,18", s2.toString());
	}

	/**
	 * Test Student.compareTo(), based on first name, then last name, and then unity
	 * id
	 */
	@Test
	public void testCompareTo() {
		Student s1 = new Student(FIRSTNAME, LASTNAME, "jrbooth", EMAIL, PASSWORD, MAXCREDITS);
		Student s2 = new Student("Harry", LASTNAME, "jrbooth", EMAIL, PASSWORD, MAXCREDITS);
		Student s3 = new Student("Harry", "Carter", "jrbooth", EMAIL, PASSWORD, MAXCREDITS);
		Student s4 = new Student("Joson", "Botson", "jtbotso", EMAIL, PASSWORD, MAXCREDITS);
		assertEquals(-1, s1.compareTo(s3));
		assertEquals(-1, s4.compareTo(s3));
		assertEquals(1, s3.compareTo(s1));
		assertEquals(1, s4.compareTo(s2));
		assertEquals(-1, s2.compareTo(s1));
		assertEquals(1, s1.compareTo(s2));
	}

}