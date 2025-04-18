package edu.ncsu.csc216.wolf_hire.model.manager;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_hire.model.application.Application;

/**
 * Tests WolfHire.java
 * 
 * @author hchunter
 *
 */
public class WolfHireTest {

//	/** Valid course records */
//	private final String validTestFile = "test-files/positions1.txt";
//	/** Invalid course records */
//	private final String invalidTestFile = "test-files/positions17.txt";
	/**
	 * Value to store a single instance of wolfhire in
	 */
	private static WolfHire singleton;

	/**
	 * Resets course_records.txt for use in other tests.
	 * 
	 * @throws Exception if unable to reset files
	 */
	@Before
	public void setUp() throws Exception {
		// Reset course_records.txt so that it's fine for other needed tests
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "positions1.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "positions1.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}

//	/**
//	 * Tests WolfScheduler().
//	 */
//	@Test
//	public void testWolfHire() {
//		
//		new WolfHire();
//		wh1.loadPositionsFromFile(validTestFile);
//		assertEquals(0, wh1.getPositionList().length);
//		assertEquals(null, wh1.getActivePosition());
//		
//		WolfHire wh2 = new WolfHire();
//		assertEquals(13, wh2.getPositionList().length);
//		assertEquals("", wh2.getActivePosition());
//	}

	/**
	 * tests get Instance in wolf hire
	 */
	@Test
	public void testGetInstance() {
		WolfHire wh = singleton.getInstance();
		assertEquals(wh, wh.getInstance());
	}

//	@Test
//	public void testGetApplications() { 
//		WolfHire wh2 = new WolfHire();
//		assertEquals(, wh2.getApplicationsAsArray(validTestFile));
//	}
//	
	/**
	 * tests adding a new position in wolf hire
	 */
	@Test
	public void testAddNewPosition() {

		WolfHire wh = singleton.getInstance();
		Application.setCounter(0);
		
		wh.loadPositionsFromFile("test-files/positions2.txt");
		assertEquals(4, wh.getPositionList().length);
		
		wh.savePositionsToFile("test-files/testWrite.txt");
	
		assertEquals("CSC 116 Grader", wh.getActivePositionName());

		Exception wh1e1 = assertThrows(IllegalArgumentException.class, () -> wh.addNewPosition("", 12, 12));
		assertEquals("Position cannot be created.", wh1e1.getMessage());

		Exception wh1e2 = assertThrows(IllegalArgumentException.class, () -> wh.addNewPosition(null, 12, 12));
		assertEquals("Position cannot be created.", wh1e2.getMessage());

		wh.addNewPosition("Testing", 14, 13);
//		wh.loadPosition("CSC 116 PTF");
		assertEquals("Testing", wh.getActivePositionName());
		
		assertEquals(5, wh.getPositionList().length);
		
		wh.addApplicationToPosition("hayden", "hunter", "hchunter");
		
		assertEquals("* 1,Submitted,hayden,hunter,hchunter,,", wh.getApplicationById(1).toString());
		
	}

	/**
	 * tests wolfhire
	 */
	@Test
	public void testAddApplicationToPosition() { 
	WolfHire w = singleton.getInstance();
	
	assertEquals(null, w.getActivePosition());
		
	w.loadPositionsFromFile("test-files/positions1.txt");
	
//	System.out.println("number of pos " + w.getPositionList().length);
//	System.out.println("number of apps " + w.getActivePosition().getApplications());
			
			
	w.addNewPosition("Admin", 15, 20);
	w.addApplicationToPosition("hayden", "hunter", "hchunter");
//	w.getApplicationsAsArray("All");
	
	assertEquals(1, w.getApplicationsAsArray("All").length);
	assertEquals(1, w.getApplicationsAsArray("Rejected").length);
	assertEquals(1, w.getApplicationsAsArray("Reviewing").length);
	assertEquals(1, w.getApplicationsAsArray("Submitted").length);
	
	}
//	
//	@Test
//	public void testGetInstance() { 
//		WolfHire wh = singleton.getInstance();
//		assertEquals(wh, wh.getInstance());
//	}
}
