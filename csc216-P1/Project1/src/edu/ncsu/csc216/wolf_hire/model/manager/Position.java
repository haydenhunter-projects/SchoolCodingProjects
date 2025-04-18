/**
 * The manager package contains the two classes that manage the Positions and their Applications
 */
package edu.ncsu.csc216.wolf_hire.model.manager;

import java.util.ArrayList;
import edu.ncsu.csc216.wolf_hire.model.application.Application;
import edu.ncsu.csc216.wolf_hire.model.command.Command;

/**
 * Represents a position managed by the WolfHire system
 * 
 * @author hchunter
 *
 */
public class Position {

	/**
	 * The name of the position
	 */
	private String positionName;

	/**
	 * The hours per week of the position
	 */
	private int hoursPerWeek;

	/**
	 * The pay rate of the position
	 */
	private int payRate;

	/**
	 * create a list of applications
	 */
	private ArrayList<Application> applications;

	/**
	 * To create a new position with information that is provided via the user
	 * interface (e.g., positionName, hoursPerWeek, payRate).
	 * 
	 * @param positionName - the name of the position
	 * @param hoursPerWeek - the hours per week of the position
	 * @param payRate      - the pay rate of the position
	 * @throws IllegalArgumentException with the message Position cannot be
	 *                                  created.
	 */
	public Position(String positionName, int hoursPerWeek, int payRate) {
		if ("".equals(positionName) || positionName == null) {
			throw new IllegalArgumentException("Position cannot be created.");
		}
		if (hoursPerWeek < 5 || 20 < hoursPerWeek) {
			throw new IllegalArgumentException("Position cannot be created.");
		}
		if (payRate < 7 || 35 < payRate) {
			throw new IllegalArgumentException("Position cannot be created.");
		}

		setPositionName(positionName);
		setHoursPerWeek(hoursPerWeek);
		setPayRate(payRate);
		applications = new ArrayList<>();

	}

	/**
	 * sets the counter for the Application instances to the value of the maximum id
	 * in the list of Applications for the position + 1.
	 */
	public void setApplicationId() {
		
		int highestID = 0;
		for(int i = 0; i < applications.size(); i++) {
			if(highestID < applications.get(i).getId()) {
				highestID++;
			}
		}
		Application.setCounter(highestID + 1);
	}

	/**
	 * returns position name
	 * 
	 * @return returns the position name.
	 */
	public String getPositionName() {
		return positionName;
	}

	/**
	 * returns the hours per week
	 * 
	 * @return the hoursPerWeek
	 */
	public int getHoursPerWeek() {
		return hoursPerWeek;
	}

	/**
	 * returns the payrate
	 * 
	 * @return the payRate
	 */
	public int getPayRate() {
		return payRate;
	}

	/**
	 * sets the position name
	 * 
	 * @param positionName the positionName to set
	 */
	private void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	/**
	 * sets the hours per week
	 * 
	 * @param hoursPerWeek the hoursPerWeek to set
	 */
	private void setHoursPerWeek(int hoursPerWeek) {
		this.hoursPerWeek = hoursPerWeek;
	}

	/**
	 * sets the pay rate
	 * 
	 * @param payRate the payRate to set
	 */
	private void setPayRate(int payRate) {
		this.payRate = payRate;
	}

	/**
	 * Creates a new Application in the submitted state, adds it to the list in
	 * sorted order, and returns the id..
	 * 
	 * @param firstName - the name of the application
	 * @param surname   - the surname of the application
	 * @param unityId   - the unityid of the application
	 * @throws IllegalArgumentException with the message application cannot be
	 *                                  created.
	 * @return an application id
	 */
	public int addApplication(String firstName, String surname, String unityId) {
		
		setApplicationId();
		
		Application application = new Application(firstName, surname, unityId);

		applications.add(application);
		
		return application.getId();

	}

	/**
	 * Adds the application to the list in sorted order by id. The list will be
	 * maintained in sorted order, so you will be able to add a new story in order.
	 * 
	 * @param application - the application being added to the list
	 * @return an application id
	 * @throws IllegalArgumentException will be thrown with the message Application
	 *                                  cannot be created.
	 */
	public int addApplication(Application application) {
		
		setApplicationId();
		int noInfiniteLoop = applications.size();
		
		if(applications.size() == 0) {
			applications.add(application);
		}
		else {
			for(int k = 0; k < noInfiniteLoop; k++) {
				
					if (applications.get(k).getId() == application.getId()) {

//						System.out.println("check app at k " + applications.get(k));
//						System.out.println("app at k " + k + " id " + applications.get(k).getId());
//						System.out.println("passed through " + " id " + application.getId());
						
						throw new IllegalArgumentException("Application cannot be created.");
					}
					else if(application.getId() < applications.get(k).getId()) {
						applications.add(k, application);
						
						break;
					}
					else if(k + 1 == noInfiniteLoop) {
						applications.add(noInfiniteLoop, application);
						
						break;
					}
//					System.out.println(applications);
//					System.out.println("compare " + applications.get(k).getId());
//					System.out.println("compare " + application.getId());
//					System.out.println("apps " + applications);

				}
			
			}

		return application.getId();

	}

	/**
	 * Returns the List of Applications.
	 * 
	 * @return a list of applications
	 */
	public ArrayList<Application> getApplications() {

		return applications;
	}

	/**
	 * Returns the Application in the list with the given id. If there is no
	 * Application with that id, the method returns null.
	 * 
	 * @param id of the application you wish to find
	 * @return the selected application
	 */
	public Application getApplicationById(int id) {

		for (int i = 0; i < applications.size(); i++) {
			
//			System.out.println("check1: " + applications.get(i).getId());
//			System.out.println("check2: " + id);
			
			if (applications.get(i).getId() == id) {
				return applications.get(i);
			}
		}

		return null;
	}

	/**
	 * Will find the Application with the given id and update it by passing in the
	 * given Command. If there is no Application with that id, the list doesn’t
	 * change.
	 * 
	 * @param id of the application you wish to find
	 * @param c  is the command to pass to the application
	 */
	public void executeCommand(int id, Command c) {

		for (int i = 0; i < applications.size(); i++) {
			if (applications.get(i).getId() == id) {
				try {
				
				applications.get(i).update(c);
				}
				catch(Exception e) {
					throw new UnsupportedOperationException("Invalid command.");
				}
			}
		}
	
		
	}

	/**
	 * Removes the Application with the given id from the list. If there is no
	 * Application to remove, the list doesn’t change.
	 * 
	 * @param id of the application you wish to remove
	 */
	public void deleteApplicationById(int id) {
		
		
		for (int i = 0; i < applications.size(); i++) {
			if (applications.get(i).getId() == id) {
				applications.remove(i);
			}
		}
		
		
	}

	/**
	 * Method returns the string representation of the Position that is printed
	 * during file save operations
	 * 
	 * @return the string representation of position
	 */
	@Override
	public String toString() {
		return "# " + positionName + "," + hoursPerWeek + "," + payRate;
	}

}
