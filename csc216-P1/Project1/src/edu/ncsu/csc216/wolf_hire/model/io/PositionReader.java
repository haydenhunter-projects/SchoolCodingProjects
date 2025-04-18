/**
 * holds the classes that handle file input and output.
 */
package edu.ncsu.csc216.wolf_hire.model.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import edu.ncsu.csc216.wolf_hire.model.application.Application;
import edu.ncsu.csc216.wolf_hire.model.manager.Position;

/**
 * Handles reading and processing positions and applications from a file.
 * 
 * @author hchunter
 *
 */
public class PositionReader {

	/**
	 * Method that receives a String with the file name to read from
	 * 
	 * @param fileName - the file to read from
	 * @return the positions in the file
	 * @throws FileNotFoundException    if file cannot be found
	 * @throws IllegalArgumentException with the message “Unable to load file ."
	 */
	public static ArrayList<Position> readPositionFile(String fileName) {
		Scanner fileReader;
		
		try {
			fileReader = new Scanner(new FileInputStream(fileName));
		} catch (Exception e1) {
			throw new IllegalArgumentException("Unable to load " + fileName);
		}
		ArrayList<Position> positions = new ArrayList<Position>();

		String oneBigLine = "";

		while (fileReader.hasNextLine()) {
			oneBigLine += fileReader.nextLine() + "\n";
		}
//		System.out.println("one big line: " + oneBigLine);

		Scanner readBigLine = new Scanner(oneBigLine);

		Position p = null;
		Application a = null;
		int i = -1;

		while (readBigLine.hasNextLine()) {
			
			String temp = readBigLine.nextLine();
			
			readBigLine.useDelimiter("\\r?\\n?[#]");
//				System.out.println(readBigLine.next());

			if (temp.startsWith("# ")) {
//				System.out.println("readbig " + readBigLine.nextLine());
				p = processPosition(temp);
				
				if (p == null) {
//					throw new IllegalArgumentException("invalid");
					continue;
				}
				
				positions.add(p);
				System.out.println("position added: " + p);
				i++;
			}

			if (temp.startsWith("* ")) {
				
				a = processApplication(temp);
				
				try {
					positions.get(i).addApplication(a);
				}
				catch(Exception e) {
//					p.setApplicationId();
					System.out.println("did not add application: " + a);
//					readBigLine.nextLine();
					continue;
				}
//					p.setApplicationId();
					System.out.println("application added: " + a);
					
//					System.out.println("pos reader failure");
					
			}

		}
//		System.out.println("positions: " + positions);
		fileReader.close();
		readBigLine.close();
		// Return the ArrayList with all the courses we read!
//		System.out.println("FINAL: " + positions);
		return positions;
	}

	/**
	 * Method used for processing positions in the file
	 * 
	 * @param positionText - the text containing positions in the file
	 * @return the position name
	 */
	private static Position processPosition(String positionText) {
		Position p = null;
		Scanner scan = new Scanner(positionText);

		p = processPositionLine(positionText);
		p.setApplicationId();
//		System.out.println("pos added: " + p + "\n");

		scan.close();
		return p;
	}

	/**
	 * Method used for processing positions contents in the file
	 * 
	 * @param positionLine the line under the position name in the file
	 * @return a string of position contents
	 */
	private static Position processPositionLine(String positionLine) {
		Scanner scan = new Scanner(positionLine);
//		System.out.println("\npos line: " + positionLine);

		scan.useDelimiter(",");
		
		String positionName = scan.next().substring(2);
//			System.out.println("Position name: " + positionName);
		
		int hoursPerWeek = scan.nextInt();
		int payRate = scan.nextInt();
//				System.out.println("position added: " + positionName + "," + hoursPerWeek + "," + payRate);
		
		try {
			Position position = new Position(positionName, hoursPerWeek, payRate);
			scan.close();
			return position;

		} catch (Exception e) {
			scan.close();
			return null;
		}

	}

	/**
	 * Method used for processing positions contents in the file
	 * 
	 * @param applicationLine the line under the position name in the file
	 * @return a string of position contents
	 */
	private static Application processApplication(String applicationLine) {
		Scanner scan = new Scanner(applicationLine);
		System.out.println("app line: " + applicationLine);
		String reviewer = "";
		String note = "";

		scan.useDelimiter(",");
		
		String appId = scan.next().substring(2);
//			System.out.println(appId);
		int applicationId = Integer.parseInt(appId);
//			System.out.println(applicationId);
		String state = scan.next();

		String firstName = scan.next();
		String surname = scan.next();
		String unityId = scan.next();

		
//		System.out.println("compile " + firstName + surname + unityId);
		
		if(scan.hasNext()) {
		reviewer = scan.next();
		}
		else {
			reviewer = "";
		}
		if(scan.hasNext()) {
		note = scan.next();

		}
		else {
			note = "";
		}

		try {
			Application application = new Application(applicationId, state, firstName, surname, unityId, reviewer,
					note);
//			System.out.println(application);
			scan.close();
			return application;
		} catch (Exception e) {
			scan.close();
			System.out.println(note);
			return null;
		}
	}
}
