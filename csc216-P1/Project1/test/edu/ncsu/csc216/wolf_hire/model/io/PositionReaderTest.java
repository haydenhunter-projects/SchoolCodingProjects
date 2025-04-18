package edu.ncsu.csc216.wolf_hire.model.io;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_hire.model.manager.Position;

import org.junit.Before;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Tests PositionReader.java
 * 
 * @author hchunter
 *
 */
public class PositionReaderTest {

	/** Valid positions file */
	private final String validTestFile1 = "test-files/positions1.txt";

	/** Valid positions file */
	private final String validTestFile2 = "test-files/positions2.txt";

	/** Invalid positions file */
	private final String invalidTestFile = "test-files/doesntexist.txt";

	/** Expected results for valid position in positions1.txt - line 1 */
	private final String validPosition1 = "# CSC 216 PTF,12,15";
	
	/** Expected results for valid position in positions1.txt - line 1 */
	private final String validPosition2 = "# CSC 116 Grader,10,11";
	
	/** Expected results for valid position in positions1.txt - line 1 */
	private final String validPosition3 = "# CSC 216 PTF,12,15";
	
	/** Expected results for valid position in positions1.txt - line 1 */
	private final String validPosition4 = "# CSC 226 Grader,8,15";
	
	/** Expected results for valid position in positions1.txt - line 1 */
	private final String validPosition5 = "# Research Assistant,15,16";

	/** Expected results for valid applications in positions1.txt - line 2 */
	private final String validApplication1 = "* 2,Submitted,Carol,Schmidt,cschmid,,";
	/** Expected results for valid applications in positions1.txt - line 3 */
	private final String validApplication2 = "* 3,Rejected,Kathleen,Gillespie,kgilles,Incomplete,";
	/** Expected results for valid applications in positions1.txt - line 4 */
	private final String validApplication3 = "* 4,Hired,Fiona,Rosario,frosari,sesmith5,";
	/** Expected results for valid applications in positions1.txt - line 5 */
	private final String validApplication4 = "* 7,Inactive,Deanna,Sanders,dsander,sesmith5,Completed";
	/** Expected results for valid applications in positions1.txt - line 6 */
	private final String validApplication5 = "* 8,Interviewing,Benjamin,Nieves,bmnieves,sesmith5,";
	/** Expected results for valid applications in positions1.txt - line 7 */
	private final String validApplication6 = "* 11,Processing,Quemby,Mullen,qmullen,sesmith5,";

	/** Expected results for valid applications in positions2.txt - line 2 */
	private final String validApplication7 = "* 1,Reviewing,Cailin,Roach,cvroach,tnmacnei,";
	/** Expected results for valid applications in positions2.txt - line 3 */
	private final String validApplication8 = "* 2,Rejected,Clinton,Armstrong,carmstr,,Qualifications";
	/** Expected results for valid applications in positions2.txt - line 4 */
	private final String validApplication9 = "* 5,Hired,Craig,Armstrong,carmstr,tnmacnei,";
	/** Expected results for valid applications in positions2.txt - line 5 */
	private final String validApplication10 = "* 7,Inactive,Audrey,Kemp,akemp,tnmacnei,Fired";
	/** Expected results for valid applications in positions2.txt - line 6 */
	private final String validApplication11 = "* 25,Interviewing,Harper,Holden,hholden,tnmacnei,";
	/** Expected results for valid applications in positions2.txt - line 7 */
	private final String validApplication12 = "* 3,Processing,Eagan,Cardenas,ecarden,tnmacnei,";
	/** Expected results for valid applications in positions2.txt - line 7 */
	private final String validApplication13 = "* 4,Processing,Ferdinand,Acevedo,faceved,tnmacnei,";
	/** Expected results for valid applications in positions2.txt - line 2 */
	private final String validApplication15 = "* 2,Submitted,Carol,Schmidt,cschmid,,";
	/** Expected results for valid applications in positions2.txt - line 3 */
	private final String validApplication16 = "* 3,Rejected,Kathleen,Gillespie,kgilles,,Incomplete";
	/** Expected results for valid applications in positions2.txt - line 4 */
	private final String validApplication17 = "* 4,Hired,Fiona,Rosario,frosari,sesmith5,";
	/** Expected results for valid applications in positions2.txt - line 5 */
	private final String validApplication18 = "* 7,Inactive,Deanna,Sanders,dsander,sesmith5,Completed";
	/** Expected results for valid applications in positions2.txt - line 6 */
	private final String validApplication19 = "* 8,Interviewing,Benjamin,Nieves,bmnieves,sesmith5,";
	/** Expected results for valid applications in positions2.txt - line 7 */
	private final String validApplication20 = "* 11,Processing,Quemby,Mullen,qmullen,sesmith5,";
	
	
	/** Expected results for valid applications in positions2.txt - line 7 */
	private final String validApplication22 = "* 5,Hired,Karen,Mcguire,kamcguir,jdyoung2,";
	/** Expected results for valid applications in positions2.txt - line 7 */
	private final String validApplication23 = "* 7,Interviewing,Dean,Wilder,dwilder,jdyoung2,";
	/** Expected results for valid applications in positions2.txt - line 7 */
	private final String validApplication24 = "* 8,Inactive,Nehru,Contreras,ncontre,jdyoung2,Completed";
	
	/** Expected results for valid applications in positions2.txt - line 7 */
	private final String validApplication25 = "* 7,Reviewing,Kerry,Tucker,kbtucker,tmbarnes,";
	/** Expected results for valid applications in positions2.txt - line 7 */
	private final String validApplication26 = "* 3,Submitted,Inez,Koch,ikoch,,";
	
	
	/** Array to hold expected results */
	private final String[] validApplications = { validApplication1, validApplication2, validApplication3,
			validApplication4, validApplication5, validApplication6, };
	
	/** Array to hold expected results */
	private final String[] validApplications2 = { 
			
			validApplication7, validApplication8, validApplication12, validApplication13, validApplication9, validApplication10, validApplication11,  
		    validApplication15, validApplication16, validApplication17, validApplication18, validApplication19,
			validApplication20, validApplication22, validApplication23, validApplication24,
			validApplication25, validApplication26};
	
	
	/** Array to hold expected results */
	private final String[] validPositions = { validPosition1 };
	
	/** Array to hold expected results */
	private final String[] validPositions2 = { validPosition2, validPosition3, validPosition4, validPosition5 };

	
	
	
	
	
	/**
	 * Resets course_records.txt for use in other tests.
	 * 
	 * @throws IOException if unable to reset files
	 */
	@Before
	public void setUp() throws Exception {
		// Reset positions1.txt so that it's fine for other needed tests
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "positions2.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "positions3.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}

	/**
	 * Tests readValidCourseRecords().
	 */
	@Test
	public void testReadValidApplications1() throws FileNotFoundException {
		ArrayList<Position> positions = PositionReader.readPositionFile(validTestFile1);
		
			for (int j = 0; j < validPositions.length; j++) {
				assertEquals(6, positions.get(j).getApplications().size());
		}
	}
	
	/**
	 * Tests readValidCourseRecords().
	 */
	@Test
	public void testReadValidPositions1() throws FileNotFoundException {
		ArrayList<Position> positions = PositionReader.readPositionFile(validTestFile1);
		
			for (int j = 0; j < validApplications.length; j++) {
				assertEquals(validPositions.length, positions.size());
		}
	}
	
	/**
	 * Tests readValidCourseRecords().
	 */
	@Test
	public void testReadValidPositions2() throws FileNotFoundException {
		ArrayList<Position> positions = PositionReader.readPositionFile(validTestFile2);
		
			for (int j = 0; j < validApplications2.length; j++) {
				assertEquals(validPositions2.length, positions.size());
		}
	}

	/**
	 * Tests readValidCourseRecords().
	 */
	@Test
	public void testReadValidApplications() throws FileNotFoundException {
		ArrayList<Position> positions = PositionReader.readPositionFile(validTestFile2);

				assertEquals(7, positions.get(0).getApplications().size());
				assertEquals(6, positions.get(1).getApplications().size());
				assertEquals(4, positions.get(2).getApplications().size());
				assertEquals(2, positions.get(3).getApplications().size());
	
	}

	/**
	 * Tests readInvalidCourseRecords().
	 */
	@Test
	public void testReadInvalidPositions() throws FileNotFoundException {

		Exception p1e1 = assertThrows(Exception.class,
				() -> PositionReader.readPositionFile(invalidTestFile));
		assertEquals("Unable to load test-files/doesntexist.txt", p1e1.getMessage());
	}

}
