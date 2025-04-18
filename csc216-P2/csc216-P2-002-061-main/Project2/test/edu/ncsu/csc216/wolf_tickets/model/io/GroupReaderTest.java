package edu.ncsu.csc216.wolf_tickets.model.io;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_tickets.model.group.Group;

class GroupReaderTest {

	/** valid file with 1 group */
	private File validTestFile1 = new File("test-files/group0.txt");

	/** valid file with 1 group, 3 categories */
	private File validTestFile2 = new File("test-files/group1.txt");

	/** valid file with 1 group, 1 categories */
	private File invalidTestFile1 = new File("test-files/group7.txt");

	/**
	 * Resets course_records.txt for use in other tests.
	 *
	 * @throws IOException if unable to reset files
	 */
	@Before
	public void setUp() throws Exception {
		Path sourcePath = FileSystems.getDefault().getPath("test-files");
		Path destinationPath = FileSystems.getDefault().getPath("test-files");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}

	/**
	 * Tests groupReader group0.txt
	 */
	@Test
	public void testReadValidGroup() throws FileNotFoundException {
		Group g = GroupReader.readGroupFile(validTestFile1);

		assertEquals("CSC IT", g.getGroupName());
	}

	/**
	 * Tests groupReader categories and tickets group1.txt
	 */
	@Test
	public void testReadValidGroupCategories() throws FileNotFoundException {
		Group g = GroupReader.readGroupFile(validTestFile2);
		assertEquals("CSC IT", g.getGroupName());

		// check to make sure it read in all 3 categories, in sorted order
		String[] display = new String[g.getCategoriesNames().length];
		display = g.getCategoriesNames();

		// classroom, desktop, web
		assertEquals("Active Tickets", display[0]);
		assertEquals("Classroom Tech", display[1]);
		assertEquals("Desktop", display[2]);
		assertEquals("Web", display[3]);

		// check to make sure classroom tech has 4 valid tickets
		g.setCurrentCategory("Classroom Tech");

		String[][] displayClassroomTech = new String[4][2];
		displayClassroomTech = g.getCurrentCategory().getTicketsAsArray();

		// ticket at prio 0
		assertEquals("0", displayClassroomTech[0][0]);
		assertEquals("EBII 1025 Laptop display won't work", displayClassroomTech[0][1]);

		// ticket at prio 1
		assertEquals("1", displayClassroomTech[1][0]);
		assertEquals("EBII 1010 Podium monitor won't turn on.", displayClassroomTech[1][1]);

		// ticket at prio 2
		assertEquals("2", displayClassroomTech[2][0]);
		assertEquals("EBII 1025 Replace lights", displayClassroomTech[2][1]);

		// ticket at prio 3
		assertEquals("3", displayClassroomTech[3][0]);
		assertEquals("LMP 200 update Firefox", displayClassroomTech[3][1]);

		// repeat for desktop two tickets
		g.setCurrentCategory("Desktop");

		String[][] displayDesktop = new String[1][2];
		displayDesktop = g.getCurrentCategory().getTicketsAsArray();

		// ticket at prio 0
		assertEquals("0", displayDesktop[0][0]);
		assertEquals("Dr. McLeod's computer won't charge.", displayDesktop[0][1]);

		assertEquals("1", displayDesktop[1][0]);
		assertEquals("Microphone not detected through docking station.", displayDesktop[1][1]);

		// repeat for Web one ticket
		g.setCurrentCategory("Web");

		String[][] displayWeb = new String[1][2];
		displayWeb = g.getCurrentCategory().getTicketsAsArray();

		// ticket at prio 0
		assertEquals("0", displayWeb[0][0]);
		assertEquals("Dr. McLeod website pages won't update.", displayWeb[0][1]);
	}

	/**
	 * Tests groupReader group7.txt
	 */
	@Test
	public void testReadInvalidGroup() throws FileNotFoundException {
		Group g = GroupReader.readGroupFile(invalidTestFile1);

		assertEquals("OIT", g.getGroupName());
	}

}
