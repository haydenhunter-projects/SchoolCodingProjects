/**
 * holds the classes that handle file input and output.
 */
package edu.ncsu.csc216.wolf_hire.model.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.List;

import edu.ncsu.csc216.wolf_hire.model.manager.Position;

/**
 * Handles writing positions to a file
 * 
 * @author hchunter
 *
 */
public class PositionWriter {

	/**
	 * Method receives a String with the file name to write to and a List of
	 * Positions to write to file.
	 * 
	 * @param fileName  - name of file to write to
	 * @param positions - positions to write to file
	 * @throws FileNotFoundException    - when you cannot find file
	 * @throws IllegalArgumentException is thrown with the message Unable to save
	 *                                  file.
	 */
	public static void writePositionsToFile(String fileName, List<Position> positions) {
		PrintStream fileWriter;
		try {
			fileWriter = new PrintStream(new File(fileName));
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("File not found");
		}

		for (Position a : positions) {
			fileWriter.println(a.toString());
		}

		fileWriter.close();

	}
}
