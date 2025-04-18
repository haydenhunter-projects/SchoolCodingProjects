/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.roll;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;


/**
 * Test class for CourseRoll
 * @author jpkenlin
 *
 */
class CourseRollTest {

	/**
	 * Test for CourseRoll constructor
	 */
	@Test
	void testCourseRoll() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll cr = c.getCourseRoll();
		assertEquals(10, cr.getEnrollmentCap());
		assertEquals(10, cr.getOpenSeats());
		CourseRoll cr2 = new CourseRoll(c, 250);
		assertEquals(250, cr2.getEnrollmentCap());
		assertEquals(250, cr2.getOpenSeats());
		CourseRoll cr3 = new CourseRoll(c, 150);
		assertEquals(150, cr3.getEnrollmentCap());
		assertEquals(150, cr3.getOpenSeats());
		assertThrows(IllegalArgumentException.class, () -> new CourseRoll(c, 9));
		assertThrows(IllegalArgumentException.class, () -> new CourseRoll(c, 251));
	}

	/**
	 * Test for setEnrollmentCap method
	 */
	@Test
	void testSetEnrollmentCap() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll cr = c.getCourseRoll();
		cr.setEnrollmentCap(12);
		assertEquals(12, cr.getEnrollmentCap());
		assertEquals(12, cr.getOpenSeats());
		Student s1 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		Student s2 = new Student("test", "lsast", "idd", "emadeil@ncsu.edu", "hasheddpassword");
		Student s3 = new Student("first2", "last", "ifd", "emaail@ncsu.edu", "hasheddpassword");
		Student s4 = new Student("firwerst", "last2", "ifd", "ema3il@ncsu.edu", "hashedapassword");
		Student s5 = new Student("firagst", "laafhst", "idad2", "emairl@ncsu.edu", "hashesdpassword");
		Student s6 = new Student("fiahrst", "la53wyst", "ifd", "emaeil2@ncsu.edu", "hashedapassword");
		Student s7 = new Student("firrest", "larest", "idad", "emadil@ncsu.edu", "hashedpfassword2");
		Student s8 = new Student("firafhst", "larst", "iddaf", "emafil@ncsu.edu", "hashedpasssword2");
		Student s9 = new Student("firndst", "laregst", "idadf", "emaial@ncsu.edu", "hasheddpassword2");
		Student s10 = new Student("firehhqrst", "laabfst", "infad", "emdaail@ncsu.edu", "hashefdpassword2");
		Student s11 = new Student("firafgagst", "lartewrst", "itrd", "emdail@ncsu.edu", "hashedapassword2");

		cr.enroll(s1);
		assertEquals(11, cr.getOpenSeats());
		cr.enroll(s2);
		assertEquals(10, cr.getOpenSeats());
		cr.enroll(s3);
		assertEquals(9, cr.getOpenSeats());
		cr.enroll(s4);
		assertEquals(8, cr.getOpenSeats());
		cr.enroll(s5);
		assertEquals(7, cr.getOpenSeats());
		cr.enroll(s6);
		assertEquals(6, cr.getOpenSeats());
		cr.enroll(s7);
		assertEquals(5, cr.getOpenSeats());
		cr.enroll(s8);
		assertEquals(4, cr.getOpenSeats());
		cr.enroll(s9);
		assertEquals(3, cr.getOpenSeats());
		cr.enroll(s10);
		assertEquals(2, cr.getOpenSeats());
		cr.enroll(s11);
		assertEquals(1, cr.getOpenSeats());

		cr.setEnrollmentCap(11);
		assertEquals(0, cr.getOpenSeats());

		assertThrows(IllegalArgumentException.class, () -> cr.setEnrollmentCap(10));
	}

	/**
	 * Test for Enroll method
	 */
	@Test
	void testEnroll() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll cr = c.getCourseRoll();
		assertEquals(10, cr.getEnrollmentCap());
		assertEquals(10, cr.getOpenSeats());
		Student s1 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		Student s2 = new Student("test", "lsast", "idd", "emadeil@ncsu.edu", "hasheddpassword");
		Student s3 = new Student("first2", "last", "ifd", "emaail@ncsu.edu", "hasheddpassword");
		Student s4 = new Student("firwerst", "last2", "ifd", "ema3il@ncsu.edu", "hashedapassword");
		Student s5 = new Student("firagst", "laafhst", "idad2", "emairl@ncsu.edu", "hashesdpassword");
		Student s6 = new Student("fiahrst", "la53wyst", "ifd", "emaeil2@ncsu.edu", "hashedapassword");
		Student s7 = new Student("firrest", "larest", "idad", "emadil@ncsu.edu", "hashedpfassword2");
		Student s8 = new Student("firafhst", "larst", "iddaf", "emafil@ncsu.edu", "hashedpasssword2");
		Student s9 = new Student("firndst", "laregst", "idadf", "emaial@ncsu.edu", "hasheddpassword2");
		Student s10 = new Student("firehhqrst", "laabfst", "infad", "emdaail@ncsu.edu", "hashefdpassword2");
		Student s11 = new Student("firafgagst", "lartewrst", "itrd", "emdail@ncsu.edu", "hashedapassword2");
		Student s12 = new Student("first12", "last12", "id12", "email12@ncsu.edu", "hashedpassword12");
		Student s13 = new Student("first13", "last13", "id13", "email13@ncsu.edu", "hashedpassword13");
		Student s14 = new Student("first14", "last14", "id14", "email14@ncsu.edu", "hashedpassword14");
		Student s15 = new Student("first15", "last15", "id15", "email15@ncsu.edu", "hashedpassword13");
		Student s16 = new Student("first16", "last16", "id16", "email16@ncsu.edu", "hashedpassword13");
		Student s17 = new Student("first17", "last17", "id17", "email17@ncsu.edu", "hashedpassword13");
		Student s18 = new Student("first18", "last18", "id18", "email18@ncsu.edu", "hashedpassword13");
		Student s19 = new Student("first19", "last19", "id19", "email19@ncsu.edu", "hashedpassword13");
		Student s20 = new Student("first20", "last20", "id20", "email20@ncsu.edu", "hashedpassword13");


		cr.enroll(s1);
		assertEquals(9, cr.getOpenSeats());
		cr.enroll(s2);
		assertEquals(8, cr.getOpenSeats());
		cr.enroll(s3);
		assertEquals(7, cr.getOpenSeats());
		cr.enroll(s4);
		assertEquals(6, cr.getOpenSeats());
		cr.enroll(s5);
		assertEquals(5, cr.getOpenSeats());
		cr.enroll(s6);
		assertEquals(4, cr.getOpenSeats());
		cr.enroll(s7);
		assertEquals(3, cr.getOpenSeats());
		cr.enroll(s8);
		assertEquals(2, cr.getOpenSeats());
		cr.enroll(s9);
		assertEquals(1, cr.getOpenSeats());
		cr.enroll(s10);
		assertEquals(0, cr.getOpenSeats());

		cr.enroll(s11);
		assertEquals(1, cr.getNumberOnWaitlist());
		
		
		
		
		cr.drop(s9);
		assertEquals(0, cr.getNumberOnWaitlist());
		cr.enroll(s9);
		cr.enroll(s12);
		assertEquals(2, cr.getNumberOnWaitlist());
		cr.enroll(s13);
		assertEquals(3, cr.getNumberOnWaitlist());
		cr.enroll(s14);
		assertEquals(4, cr.getNumberOnWaitlist());
		cr.enroll(s15);
		assertEquals(5, cr.getNumberOnWaitlist());
		cr.enroll(s16);
		assertEquals(6, cr.getNumberOnWaitlist());
		cr.enroll(s17);
		assertEquals(7, cr.getNumberOnWaitlist());
		cr.enroll(s18);
		assertEquals(8, cr.getNumberOnWaitlist());
		cr.enroll(s19);
		assertEquals(9, cr.getNumberOnWaitlist());
		cr.enroll(s20);
		assertEquals(10, cr.getNumberOnWaitlist());
		assertThrows(IllegalArgumentException.class, () -> cr.enroll(null));
		
		Course c2 = new Course("CSC217", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll cr2 = c2.getCourseRoll();
		
		cr2.enroll(s1);
		cr2.enroll(s2);
		cr2.enroll(s3);
		cr2.enroll(s4);
		cr2.enroll(s5);
		cr2.enroll(s6);
		cr2.enroll(s7);
		cr2.enroll(s8);
		cr2.enroll(s9);
		cr2.enroll(s10);
		cr2.enroll(s11);
		cr2.enroll(s12);
		cr2.enroll(s13);
		cr2.enroll(s14);
		cr2.enroll(s15);
		cr2.enroll(s16);
		cr2.enroll(s17);
		cr2.enroll(s18);
		cr2.enroll(s19);
		cr2.enroll(s20);

	}

	/**
	 * Test for drop method
	 */
	@Test
	void testDrop() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll cr = c.getCourseRoll();
		assertEquals(10, cr.getEnrollmentCap());
		assertEquals(10, cr.getOpenSeats());
		Student s1 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		Student s2 = new Student("test", "lsast", "idd", "emadeil@ncsu.edu", "hasheddpassword");
		Student s3 = new Student("first2", "last", "ifd", "emaail@ncsu.edu", "hasheddpassword");
		Student s4 = new Student("firwerst", "last2", "ifd", "ema3il@ncsu.edu", "hashedapassword");
		Student s5 = new Student("firagst", "laafhst", "idad2", "emairl@ncsu.edu", "hashesdpassword");
		Student s6 = new Student("fiahrst", "la53wyst", "ifd", "emaeil2@ncsu.edu", "hashedapassword");
		Student s7 = new Student("firrest", "larest", "idad", "emadil@ncsu.edu", "hashedpfassword2");
		Student s8 = new Student("firafhst", "larst", "iddaf", "emafil@ncsu.edu", "hashedpasssword2");
		Student s9 = new Student("firndst", "laregst", "idadf", "emaial@ncsu.edu", "hasheddpassword2");
		Student s10 = new Student("firehhqrst", "laabfst", "infad", "emdaail@ncsu.edu", "hashefdpassword2");
		Student s11 = new Student("firafgagst", "lartewrst", "itrd", "emdail@ncsu.edu", "hashedapassword2");
		Student s12 = new Student("first12", "last12", "id12", "email12@ncsu.edu", "hashedpassword12");
		Student s13 = new Student("first13", "last13", "id13", "email13@ncsu.edu", "hashedpassword13");
		Student s14 = new Student("first14", "last14", "id14", "email14@ncsu.edu", "hashedpassword14");
		Student s15 = new Student("first15", "last15", "id15", "email15@ncsu.edu", "hashedpassword13");
		Student s16 = new Student("first16", "last16", "id16", "email16@ncsu.edu", "hashedpassword13");
		Student s17 = new Student("first17", "last17", "id17", "email17@ncsu.edu", "hashedpassword13");
		Student s18 = new Student("first18", "last18", "id18", "email18@ncsu.edu", "hashedpassword13");
		Student s19 = new Student("first19", "last19", "id19", "email19@ncsu.edu", "hashedpassword13");
		Student s20 = new Student("first20", "last20", "id20", "email20@ncsu.edu", "hashedpassword13");

		assertThrows(IllegalArgumentException.class, 
				() -> cr.drop(null));
		cr.enroll(s1);
		assertEquals(9, cr.getOpenSeats());
		cr.drop(s1);
		assertEquals(10, cr.getOpenSeats());
		cr.enroll(s1);
		assertEquals(9, cr.getOpenSeats());
		cr.enroll(s2);
		assertEquals(8, cr.getOpenSeats());
		cr.enroll(s3);
		assertEquals(7, cr.getOpenSeats());
		cr.enroll(s4);
		assertEquals(6, cr.getOpenSeats());
		cr.drop(s4);
		assertEquals(7, cr.getOpenSeats());
		cr.enroll(s4);
		assertEquals(6, cr.getOpenSeats());
		cr.enroll(s5);
		assertEquals(5, cr.getOpenSeats());
		cr.enroll(s6);
		assertEquals(4, cr.getOpenSeats());
		cr.enroll(s7);
		assertEquals(3, cr.getOpenSeats());
		cr.enroll(s8);
		assertEquals(2, cr.getOpenSeats());
		cr.enroll(s9);
		assertEquals(1, cr.getOpenSeats());
		cr.enroll(s10);
		assertEquals(0, cr.getOpenSeats());
		cr.drop(s10);
		assertEquals(1, cr.getOpenSeats());
		cr.enroll(s10);
		cr.enroll(s11);
		assertEquals(1, cr.getNumberOnWaitlist());
		cr.drop(s11);
		assertEquals(0, cr.getNumberOnWaitlist());
		cr.enroll(s11);
		
		cr.enroll(s12);
		cr.enroll(s13);
		cr.enroll(s14);
		cr.enroll(s15);
		cr.enroll(s16);
		cr.enroll(s17);
		cr.enroll(s18);
		cr.enroll(s19);
		cr.enroll(s20);
		
		cr.drop(s9);
		assertEquals(0, cr.getOpenSeats());
		assertEquals(9, cr.getNumberOnWaitlist());

	}
	
	/**
	 * Test for canEnroll method
	 */
	@Test
	void testCanEnroll() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll cr = c.getCourseRoll();
		assertEquals(10, cr.getEnrollmentCap());
		assertEquals(10, cr.getOpenSeats());
		Student s1 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		Student s2 = new Student("test", "lsast", "idd", "emadeil@ncsu.edu", "hasheddpassword");
		Student s3 = new Student("first2", "last", "ifd", "emaail@ncsu.edu", "hasheddpassword");
		Student s4 = new Student("firwerst", "last2", "ifd", "ema3il@ncsu.edu", "hashedapassword");
		Student s5 = new Student("firagst", "laafhst", "idad2", "emairl@ncsu.edu", "hashesdpassword");
		Student s6 = new Student("fiahrst", "la53wyst", "ifd", "emaeil2@ncsu.edu", "hashedapassword");
		Student s7 = new Student("firrest", "larest", "idad", "emadil@ncsu.edu", "hashedpfassword2");
		Student s8 = new Student("firafhst", "larst", "iddaf", "emafil@ncsu.edu", "hashedpasssword2");
		Student s9 = new Student("firndst", "laregst", "idadf", "emaial@ncsu.edu", "hasheddpassword2");
		Student s10 = new Student("firehhqrst", "laabfst", "infad", "emdaail@ncsu.edu", "hashefdpassword2");
		Student s11 = new Student("firafgagst", "lartewrst", "itrd", "emdail@ncsu.edu", "hashedapassword2");
		
		
		assertTrue(cr.canEnroll(s10));
		cr.enroll(s1);
		assertEquals(9, cr.getOpenSeats());
		assertTrue(cr.canEnroll(s10));
		cr.enroll(s2);
		assertEquals(8, cr.getOpenSeats());
		assertTrue(cr.canEnroll(s10));
		cr.enroll(s3);
		assertEquals(7, cr.getOpenSeats());
		assertTrue(cr.canEnroll(s10));
		cr.enroll(s4);
		assertEquals(6, cr.getOpenSeats());
		assertTrue(cr.canEnroll(s10));
		assertFalse(cr.canEnroll(s1));
		cr.enroll(s5);
		assertEquals(5, cr.getOpenSeats());
		assertTrue(cr.canEnroll(s10));
		cr.enroll(s6);
		assertEquals(4, cr.getOpenSeats());
		assertTrue(cr.canEnroll(s10));
		cr.enroll(s7);
		assertEquals(3, cr.getOpenSeats());
		assertTrue(cr.canEnroll(s10));
		cr.enroll(s8);
		assertEquals(2, cr.getOpenSeats());
		assertTrue(cr.canEnroll(s10));
		cr.enroll(s9);
		assertEquals(1, cr.getOpenSeats());
		assertTrue(cr.canEnroll(s10));
		cr.enroll(s10);
		assertEquals(0, cr.getOpenSeats());
		assertTrue(cr.canEnroll(s11));
		
		
	}

}
