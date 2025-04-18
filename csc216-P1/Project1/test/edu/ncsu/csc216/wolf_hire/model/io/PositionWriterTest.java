package edu.ncsu.csc216.wolf_hire.model.io;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_hire.model.manager.Position;

/**
 * Tests PositionWriter.java
 * 
 * @author hchunter
 *
 */
public class PositionWriterTest {

	/**
	 * Tests writeActivityRecords()
	 */
	@Test
	public void testWriteActivityRecords() {
		ArrayList<Position> positions = new ArrayList<Position>();
		positions.add(new Position("CSC 116 PTF", 15, 15));
		positions.add(new Position("CSC 216 PTF", 10, 12));
		positions.add(new Position("CSC 316 PTF", 12, 20));

		try {
			PositionWriter.writePositionsToFile("test-files/testWrite.txt", positions);
		} catch (IllegalArgumentException e) {
			fail("Unable to save file.");
		}
	}

}
