/**
 * The manager package contains the two classes that manage the Positions and their Applications
 */
package edu.ncsu.csc216.wolf_hire.model.manager;

import java.util.ArrayList;
import edu.ncsu.csc216.wolf_hire.model.application.Application;
import edu.ncsu.csc216.wolf_hire.model.command.Command;
import edu.ncsu.csc216.wolf_hire.model.io.PositionReader;
import edu.ncsu.csc216.wolf_hire.model.io.PositionWriter;

/**
 * Position represents a single position and its list of Applications and
 * WolfHire controls the creation and modification of many Positions.
 * 
 * @author hchunter
 *
 */
public class WolfHire {

	/**
	 * Value to store a single instance of wolfhire in
	 */
	private static WolfHire singleton;

	/**
	 * create a list of positions
	 */
	private ArrayList<Position> positions;

	/**
	 * initialize activePosition
	 */
	private Position activePosition;

	/**
	 * used to create a single instance of wolfhire at any given time
	 * 
	 * @return a single instance of wolfhire
	 */
	public static WolfHire getInstance() {
		if (singleton == null) {
			singleton = new WolfHire();
		}
		return singleton;
	}

	private WolfHire() {
		positions = new ArrayList<>();

	}

	/**
	 * Find the Position with the given name in the list, makes it active or
	 * activePosition, and sets the application id for that position so that any new
	 * Applications added to the position are created with the next correct id
	 * 
	 * @param fileName - file name to load positions from
	 * @throws IllegalArgumentException if there is no position with the given name.
	 *                                  The message is Position not available.
	 */
	public void loadPositionsFromFile(String fileName) {

		positions = PositionReader.readPositionFile(fileName);

//		System.out.println(positions.toString());
//		System.out.println(positions.get(0).getApplications());

		activePosition = positions.get(0);
	}

	/**
	 * Writes the list of Positions to the file using the PositionWriter class.
	 * 
	 * @param fileName - the file wished to save positions to
	 * @throws IllegalArgumentException if the activePosition is null with a message
	 *                                  Unable to save file.
	 */
	public void savePositionsToFile(String fileName) {
		if(activePosition == null) {
			throw new IllegalArgumentException("cannto save file");
		}
		PositionWriter.writePositionsToFile(fileName, positions);
	}

	/**
	 * Creates a new Position with the given info and adds it to the end of the
	 * positions list. The position is then loaded as the activePosition by calling
	 * the loadPosition(String positionName) method.
	 * 
	 * @param positionName - the name of the position
	 * @param hoursPerWeek - the hours per week of the position
	 * @param payRate      - the pay rate of the position
	 * @throws IllegalArgumentException is thrown if the positionName parameter is
	 *                                  null, empty string, or a duplicate of an
	 *                                  existing position name (case-insensitive).
	 *                                  The message is “Position cannot be
	 *                                  created.
	 */
	public void addNewPosition(String positionName, int hoursPerWeek, int payRate) {
		
		for (int i = 0; i < positions.size(); i++) {
			if (positions.get(i).getPositionName().equals(positionName)) {
				throw new IllegalArgumentException("Position cannot be created.");
			}
		}
		
		if ("".equals(positionName) || positionName == null) {
			throw new IllegalArgumentException("Position cannot be created.");
		}

		Position newPosition = new Position(positionName, payRate, payRate); // create new position
		newPosition.setApplicationId();
		
		positions.add(newPosition); // add it to the end of the list

		loadPosition(newPosition.getPositionName()); // load it as active position
	}

	/**
	 * Find the Position with the given name in the list, makes it active or
	 * activePosition, and sets the application id for that position so that any new
	 * Applications added to the position are created with the next correct id. An
	 * IllegalArgumentException is thrown if there is no position with the given
	 * name. The message is Position not available.
	 * 
	 * @param positionName name of position to find from the list.
	 */
	public void loadPosition(String positionName) {

		for (int i = 0; i < positions.size(); i++) {
//			System.out.println(positions.get(i).getPositionName());
//			System.out.println(positionName);
			if (positions.get(i).getPositionName().equals(positionName)) {
				activePosition = positions.get(i);
				// something about id

			}

		}
		if (activePosition == null) {
			throw new IllegalArgumentException("Position not available.");
		}
	}

	/**
	 * returns the active position name
	 * 
	 * @return the activePosition or null if there is no activePosition.
	 */
	public String getActivePositionName() {
		if (activePosition == null) {
			return null;
		} else {
			return activePosition.getPositionName();
		}
	}

	/**
	 * gets the active position
	 * 
	 * @return the active position - which is an index 0
	 */
	public Position getActivePosition() {

		return this.activePosition;
	}

	/**
	 * returns a string array of position names
	 * 
	 * @return a String array of position names in the order they are listed in the
	 *         positions list. This is used by the GUI to populate the position drop
	 *         down. If there are no positions, the list is empty.
	 * 
	 */
	public String[] getPositionList() {

		String[] positionList = new String[positions.size()];
		for (int i = 0; i < positions.size(); i++) {
			positionList[i] = positions.get(i).getPositionName();
		}
		return positionList;
	}

	/**
	 * Adds an application to a position
	 * 
	 * @param firstName of an applicant
	 * @param surname   of an applicant
	 * @param unityId   of an applicant
	 */
	public void addApplicationToPosition(String firstName, String surname, String unityId) {
		
		if(activePosition != null) {
		activePosition.addApplication(firstName, surname, unityId);
		}
		
	}

	/**
	 * Executes a command on an application
	 * 
	 * @param id of an application
	 * @param c  - command to execute on a specific application
	 */
	public void executeCommand(int id, Command c) {
		if(getActivePosition() != null) {
			for (int i = 0; i < getActivePosition().getApplications().size(); i++) {
				if (getActivePosition().getApplications().get(i).getId() == id) {
					try {
					getActivePosition().getApplications().get(i).update(c);
					}
					catch(Exception e) {
						throw new UnsupportedOperationException("Invalid command.");
					}
				}
			}
		}
		else {
			return;
		}
		
	}

	/**
	 * Deletes a specific application by an ID and provides information to the GUI
	 * 
	 * @param id - the selected application to delete
	 */
	public void deleteApplicationById(int id) {
		if(activePosition != null) {
			
			activePosition.getApplicationById(id);
			positions.remove(id);
			
		}
		
	}

	/**
	 * Selects a filtered list of applications by a filter and provides information
	 * to the GUI
	 * 
	 * @param filter - used to filter how many applications are shown
	 * @return a 2D Object array that is used to populate the ApplicationTableModel
	 */
	public String[][] getApplicationsAsArray(String filter) {

		String[][] newList = null;
		Application a = null;

		for (int i = 0; i < getPositionList().length; i++) {
			
			//doesnt work with multiple applications
			ArrayList<Application> applications = positions.get(i).getApplications();
			
			newList = new String[applications.size()][4];
//			int counter = 0;
			for (int j = 0; j < applications.size(); j++) {
				
				a = applications.get(j);
				
				if("All".equals(filter)) {
//					System.out.println("app a " + a + "\n");
					
					newList[j][0] = Integer.toString(a.getId());
					newList[j][1] = a.getState();
					newList[j][2] = a.getUnityId();
					newList[j][3] = a.getReviewer();
				}
					
				if(a.getState().equals(filter)) {
//					System.out.println("app a " + a + "\n");
						
					newList[j][0] = Integer.toString(a.getId());
					newList[j][1] = a.getState();
					newList[j][2] = a.getUnityId();
					newList[j][3] = a.getReviewer();
	
					
	//				System.out.println(temp[0]);
	//				System.out.println(temp[1]);
	//				System.out.println(temp[2]);
	//				System.out.println(temp[3]);
				}
			}
		}

		return newList;
	}

	/**
	 * Selects a specific application by an ID and provides information to the GUI
	 * 
	 * @param id - the id of the given application
	 * @return the given application or null if there is no application with the
	 *         given id in the activePosition.
	 */
	public Application getApplicationById(int id) {
		if(getActivePosition() != null) {
		return getActivePosition().getApplicationById(id);
		} else {
			return null;
		}
	}

	/**
	 * Used to reset the manager i.e setting singleton to null
	 */
	protected void resetManager() {
		singleton = null;
	}
}
