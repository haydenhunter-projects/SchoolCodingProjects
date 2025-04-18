package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * Tests StudentRecordIO
 * 
 * @author Hari Vemulapalli
 * @author Hayden Hunter
 * @author Victor Hermida
 *
 */
public class StudentRecordIOTest {
	/** The test file of valid student records. */
	private final String validTestFile = "test-files/student_records.txt";
	/** The test file of invalid student records. */
	private final String invalidTestFile = "test-files/invalid_student_records.txt";
	/** The first valid student */
	private String validStudent0 = "Zahir,King,zking,orci.Donec@ametmassaQuisque.com,pw,15";
	/** The second valid student */
	private String validStudent1 = "Cassandra,Schwartz,cschwartz,semper@imperdietornare.co.uk,pw,4";
	/** The third valid student */
	private String validStudent2 = "Shannon,Hansen,shansen,convallis.est.vitae@arcu.ca,pw,14";
	/** The fourth valid student */
	private String validStudent3 = "Demetrius,Austin,daustin,Curabitur.egestas.nunc@placeratorcilacus.co.uk,pw,18";
	/** The fifth valid student */
	private String validStudent4 = "Raymond,Brennan,rbrennan,litora.torquent@pellentesquemassalobortis.ca,pw,12";
	/** The sixth valid student */
	private String validStudent5 = "Emerald,Frost,efrost,adipiscing@acipsumPhasellus.edu,pw,3";
	/** The seventh valid student */
	private String validStudent6 = "Lane,Berg,lberg,sociis@non.org,pw,14";
	/** The eighth valid student */
	private String validStudent7 = "Griffith,Stone,gstone,porta@magnamalesuadavel.net,pw,17";
	/** The ninth valid student */
	private String validStudent8 = "Althea,Hicks,ahicks,Phasellus.dapibus@luctusfelis.com,pw,11";
	/** The tenth valid student */
	private String validStudent9 = "Dylan,Nolan,dnolan,placerat.Cras.dictum@dictum.net,pw,5";
	/**
	 * String List used to store the String variables used for the helper method
	 * setUp()
	 */
	private String[] validStudents = { validStudent3, validStudent6, validStudent4, validStudent5, validStudent2,
			validStudent8, validStudent0, validStudent9, validStudent1, validStudent7 };
	/** String variable for a hash used for helper method setUp() */
	private String hashPW;
	/** String variable for hash algorithm used for helper method setUp() */
	private static final String HASH_ALGORITHM = "SHA-256";

	/**
	 * Helper method used to set up files before calling each method
	 */
	@BeforeEach
	public void setUp() {
		try {
			String password = "pw";
			MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
			digest.update(password.getBytes());
			hashPW = Base64.getEncoder().encodeToString(digest.digest());

			for (int i = 0; i < validStudents.length; i++) {
				validStudents[i] = validStudents[i].replace(",pw,", "," + hashPW + ",");
			}
		} catch (NoSuchAlgorithmException e) {
			fail("Unable to create hash during setup");
		}
	}

	/**
	 * Test StudentRecordIO.readStudentRecords()
	 * 
	 * @throws FileNotFoundException if the file is not readable or doesn't exist
	 */
	@Test
	void testReadStudentRecords() {
		SortedList<Student> students;
		SortedList<Student> students2;
		try {
			students = StudentRecordIO.readStudentRecords(validTestFile);
			assertEquals(10, students.size());

			for (int i = 0; i < validStudents.length; i++) {
				assertEquals(validStudents[i], students.get(i).toString());
			}

			students2 = StudentRecordIO.readStudentRecords(invalidTestFile);
			assertEquals(0, students2.size());

		} catch (FileNotFoundException e) {
			fail("Unexpected error reading " + validTestFile);
		}

	}

	/**
	 * Test StudentRecordIO.writeStudentRecords()
	 * 
	 * @throws IOException if specified file or directory does not exist
	 */
	@Test
	public void testWriteStudentRecordsNoPermissions() throws IOException {
		// Creates a Student SortedList to use to test the writing of student records
		SortedList<Student> students = new SortedList<Student>();
		students.add(new Student("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", hashPW, 15));
		StudentRecordIO.writeStudentRecords("test-files/new_student_records.txt", students);
		SortedList<Student> expectingStudents = StudentRecordIO
				.readStudentRecords("test-files/new_student_records.txt");
		// Checks if the output is as expected!
		for (int i = 0; i < students.size(); i++) {
			if (!students.get(i).equals(expectingStudents.get(i))) {
				fail("Not expected output!");
			}
		}
		// Checking for an invalid file or directory
		assertThrows(IOException.class,
				() -> StudentRecordIO.writeStudentRecords("/home/sesmith5/actual_student_records.txt", students));
	}

}