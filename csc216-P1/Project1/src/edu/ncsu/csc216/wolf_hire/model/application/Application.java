/**
 *  holds the context (Application), parent of the state hierarchy (the interface ApplicationState), and the concrete states (all the *State classes) of the State design pattern for the application FSM as part of the WolfHire system.
 */
package edu.ncsu.csc216.wolf_hire.model.application;

import edu.ncsu.csc216.wolf_hire.model.command.Command;
import edu.ncsu.csc216.wolf_hire.model.command.Command.CommandValue;

/**
 * Represents an application managed by the WolfHire system
 * 
 * @author hchunter
 *
 */
public class Application {

	/**
	 * Unique id for an application.
	 */
	private int applicationID;

	/**
	 * First name of the applicant.
	 */
	private String firstName;

	/**
	 * Surname of the applicant.
	 */
	private String surname;

	/**
	 * Unity id of the applicant.
	 */
	private String unityId;

	/**
	 * Reviewer assigned to review the application. Can only take on the values of
	 * null (e.g., not assigned reviewer) or a non-empty string.
	 */
	private String reviewer;

	/**
	 * Contains the rejection reason OR termination reason for the application, if
	 * appropriate. Can only take on the value of null (e.g., no current rejection
	 * or termination reason) or one of the rejection or termination reason
	 * constants.
	 */
	private String note;

	/**
	 * The counter of applications
	 */
	private static int counter;

	/** the initialization of the default state */
	private ApplicationState currentState;

	/** the initialization of the submitted State */
	private final ApplicationState submittedState = new SubmittedState();
	/** the initialization of the rejected State */
	private final ApplicationState rejectedState = new RejectedState();
	/** the initialization of the reviewing State */
	private final ApplicationState reviewingState = new ReviewingState();
	/** the initialization of the interviewing State */
	private final ApplicationState interviewingState = new InterviewingState();
	/** the initialization of the processing State */
	private final ApplicationState processingState = new ProcessingState();
	/** the initialization of the hired State */
	private final ApplicationState hiredState = new HiredState();
	/** the initialization of the inactive State */
	private final ApplicationState inactiveState = new InactiveState();

	/**
	 * A constant string for the submitted state’s name with the value Submitted.
	 */
	public static final String SUBMITTED_NAME = "Submitted";

	/**
	 * A constant string for the rejected state’s name with the value Rejected.
	 */
	public static final String REJECTED_NAME = "Rejected";

	/**
	 * A constant string for the reviewing state’s name with the value Reviewing.
	 */
	public static final String REVIEWING_NAME = "Reviewing";

	/**
	 * A constant string for the interviewing state’s name with the value
	 * Interviewing.
	 */
	public static final String INTERVIEWING_NAME = "Interviewing";

	/**
	 * A constant string for the processing state’s name with the value
	 * Processing.
	 */
	public static final String PROCESSING_NAME = "Processing";

	/**
	 * A constant string for the hired state’s name with the value Hired.
	 */
	public static final String HIRED_NAME = "Hired";

	/**
	 * A constant string for the inactive state’s name with the value Inactive.
	 */
	public static final String INACTIVE_NAME = "Inactive";

	/**
	 * A constant string for the rejection reason of Qualifications.
	 */
	public static final String QUALIFICATIONS_REJECTION = "Qualifications";

	/**
	 * A constant string for the rejection reason of Incomplete
	 */
	public static final String INCOMPLETE_REJECTION = "Incomplete";

	/**
	 * A constant string for the rejection reason of Positions.
	 */
	public static final String POSITIONS_REJECTION = "Positions";

	/**
	 * A constant string for the rejection reason of Duplicate.
	 */
	public static final String DUPLICATE_REJECTION = "Duplicate";

	/**
	 * A constant string for the priority of Completed.
	 */
	public static final String COMPLETED_TERMINATION = "Completed";

	/**
	 * A constant string for the priority of Resigned.
	 */
	public static final String RESIGNED_TERMINATION = "Resigned";

	/**
	 * A constant string for the priority of Fired.
	 */
	public static final String FIRED_TERMINATION = "Fired";

	/**
	 * To create a new Application with information that is provided via the user
	 * interface (e.g., first name, surname, and unity id).
	 * 
	 * @param firstName of the applicant
	 * @param surname   of the applicant
	 * @param unityId   of the applicant
	 * @throws IllegalArgumentException when any of the parameters are null or empty
	 *                                  strings.
	 */
	public Application(String firstName, String surname, String unityId) {
		if ("".equals(firstName) || firstName == null || "".equals(surname) || surname == null || "".equals(unityId)
				|| unityId == null) {
			throw new IllegalArgumentException("Application cannot be created.");
		}
		setFirstName(firstName);
		setSurname(surname);
		setUnityId(unityId);
		setId(counter);
		currentState = submittedState;

		setReviewer(null);
		setNote(null);

		incrementCounter();
	}

	/**
	 * To create an Application from information that would come from a file.
	 * 
	 * @param id        of the applicant
	 * @param state     of the applicant
	 * @param firstName of the applicant
	 * @param surname   of the applicant
	 * @param unityId   of the applicant
	 * @param reviewer  of the applicant
	 * @param note      - contains rejection reason or termination reason
	 * @throws IllegalArgumentException when params are null or empty strings.
	 */
	public Application(int id, String state, String firstName, String surname, String unityId, String reviewer,
			String note) {

		if (id < 1) {
			throw new IllegalArgumentException("Application cannot be created.");
		}

		if (id >= counter) {
			setCounter(id + 1);
		} 

		if ("".equals(state) || state == null) {
			throw new IllegalArgumentException("Application cannot be created.");
		}

		if ("".equals(firstName) || firstName == null || "".equals(surname) || surname == null || "".equals(unityId)
				|| unityId == null) {
			throw new IllegalArgumentException("Application cannot be created.");
		}

		if ("Submitted".equals(state)) {
				if(reviewer != null && note == null) {
			throw new IllegalArgumentException("Application cannot be created.");
				}	
			if(reviewer == null && note != null) {
				throw new IllegalArgumentException("Application cannot be created.");
					}	
		}

		if ("Rejected".equals(state) && reviewer == null && note == null) {
			throw new IllegalArgumentException("Application cannot be created.");
		}

		// if its a duplicate in the rejected state this is invalid
		if ("Rejected".equals(state) && reviewer != null && "Duplicate".equals(note)) {
			throw new IllegalArgumentException("Application cannot be created.");
		}

		// termination reason not a rejection reason
		if ("Rejected".equals(state) && "Completed".equals(note)) {
			throw new IllegalArgumentException("Application cannot be created.");
		}

		// termination reason not a rejection reason
		if ("Rejected".equals(state) && "Resigned".equals(note)) {
			throw new IllegalArgumentException("Application cannot be created.");
		}

		// termination reason not a rejection reason
		if ("Rejected".equals(state) && "Fired".equals(note)) {
			throw new IllegalArgumentException("Application cannot be created.");
		}

		if ("Reviewing".equals(state) && reviewer == null && note == null) {
			throw new IllegalArgumentException("Application cannot be created.");
		}

		if ("Reviewing".equals(state) && "".equals(reviewer) && note == null) {
			throw new IllegalArgumentException("Application cannot be created.");
		}

		if ("Reviewing".equals(state) && "Completed".equals(note)) {
			throw new IllegalArgumentException("Application cannot be created.");
		}

		if ("Reviewing".equals(state) && "Resigned".equals(note)) {
			throw new IllegalArgumentException("Application cannot be created.");
		}

		if ("Reviewing".equals(state) && "Fired".equals(note)) {
			throw new IllegalArgumentException("Application cannot be created.");
		}

		if ("Interviewing".equals(state) && reviewer == null && note == null) {
			throw new IllegalArgumentException("Application cannot be created.");
		}

		if ("Interviewing".equals(state) && "".equals(reviewer) && note == null) {
			throw new IllegalArgumentException("Application cannot be created.");
		}

		if ("Interviewing".equals(state) && "Completed".equals(note)) {
			throw new IllegalArgumentException("Application cannot be created.");
		}

		if ("Interviewing".equals(state) && "Resigned".equals(note)) {
			throw new IllegalArgumentException("Application cannot be created.");
		}

		if ("Interviewing".equals(state) && "Fired".equals(note)) {
			throw new IllegalArgumentException("Application cannot be created.");
		}

		if ("Processing".equals(state) && reviewer == null && note == null) {
			throw new IllegalArgumentException("Application cannot be created.");
		}

		if ("Processing".equals(state) && "".equals(reviewer) && note == null) {
			throw new IllegalArgumentException("Application cannot be created.");
		}

		if ("Processing".equals(state) && "Completed".equals(note)) {
			throw new IllegalArgumentException("Application cannot be created.");
		}

		if ("Processing".equals(state) && "Resigned".equals(note)) {
			throw new IllegalArgumentException("Application cannot be created.");
		}

		if ("Processing".equals(state) && "Fired".equals(note)) {
			throw new IllegalArgumentException("Application cannot be created.");
		}

		if ("Hired".equals(state) && reviewer == null && note == null) {
			throw new IllegalArgumentException("Application cannot be created.");
		}

		if ("Hired".equals(state) && "".equals(reviewer) && note == null) {
			throw new IllegalArgumentException("Application cannot be created.");
		}

		if ("Hired".equals(state) && "Completed".equals(note)) {
			throw new IllegalArgumentException("Application cannot be created.");
		}

		if ("Hired".equals(state) && "Resigned".equals(note)) {
			throw new IllegalArgumentException("Application cannot be created.");
		}

		if ("Hired".equals(state) && "Fired".equals(note)) {
			throw new IllegalArgumentException("Application cannot be created.");
		}

		if ("Inactive".equals(state)) {
			if (reviewer == null || note == null) {
				throw new IllegalArgumentException("Application cannot be created.");
			}

			if (!COMPLETED_TERMINATION.equals(note) && !RESIGNED_TERMINATION.equals(note)
					&& !FIRED_TERMINATION.equals(note)) {
				throw new IllegalArgumentException("Application cannot be created.");
			}
		}

		setReviewer(reviewer);
		setState(state.toString());
		setNote(note);

		setFirstName(firstName);
		setSurname(surname);
		setUnityId(unityId);

		setId(id);
//		incrementCounter();
	}

	/**
	 * sets application id
	 * 
	 * @param idValue the applicationID to set
	 */
	private void setId(int idValue) {
//		if(idValue < 0) {
//			throw new IllegalArgumentException("Error: negative id value");
//		}
		this.applicationID = idValue;
	}

	/**
	 * returns application id
	 * 
	 * @return the applicationID of the application
	 */
	public int getId() {
		return applicationID;
	}

	/**
	 * returns current state of application
	 * 
	 * @return the state of the application
	 */
	public String getState() {
		return currentState.getStateName();
	}

	/**
	 * sets the state of an application
	 * 
	 * @param stateValue the state to set
	 */
	private void setState(String stateValue) {

		if (stateValue.equals(submittedState.getStateName())) {
			currentState = submittedState;
		}
		if (stateValue.equals(rejectedState.getStateName())) {
			currentState = rejectedState;
		}
		if (stateValue.equals(reviewingState.getStateName())) {
			currentState = reviewingState;
		}
		if (stateValue.equals(interviewingState.getStateName())) {
			currentState = interviewingState;
		}
		if (stateValue.equals(processingState.getStateName())) {
			currentState = processingState;
		}
		if (stateValue.equals(hiredState.getStateName())) {
			currentState = hiredState;
		}
		if (stateValue.equals(inactiveState.getStateName())) {
			currentState = inactiveState;
		}

	}

	/**
	 * returns first name of applicant
	 * 
	 * @return the firstName of the application
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * sets first name of applicant
	 * 
	 * @param firstName the firstName to set
	 */
	private void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * returns surname of applicant
	 * 
	 * @return the surname of the application
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * gets surname of applicant
	 * 
	 * @param surname the surname to set
	 */
	private void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * returns unity id of applicant
	 * 
	 * @return the unityId of the application
	 */
	public String getUnityId() {
		return unityId;
	}

	/**
	 * sets unity id of applicant
	 * 
	 * @param unityId the unityId to set
	 */
	private void setUnityId(String unityId) {
		this.unityId = unityId;
	}

	/**
	 * returns reviewer id
	 * 
	 * @return the reviewer of the application
	 */
	public String getReviewer() {
		return reviewer;
	}

	/**
	 * sets the reviewer id
	 * 
	 * @param reviewer the reviewer to set
	 */
	private void setReviewer(String reviewer) {
		if(reviewer == null) {
			this.reviewer = "";
		}
		else {
			this.reviewer = reviewer;
		}
			
	
	}

	/**
	 * returns the note associated with the application
	 * 
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * sets the note associated with the application
	 * 
	 * @param note the note to set
	 */
	private void setNote(String note) {
		if(note == null) {
			this.note = "";
		}
		else {
			this.note = note;
		}
	}

	/**
	 * Implement counter updates the counter for applications
	 */
	public static void incrementCounter() {
		counter = counter + 1;
	}

	/**
	 * Set counter will set up the counter for the next ID, useful for if an
	 * application has been read in from a file. This sets the counter to the
	 * largest ID number +1 from the read in file.
	 * 
	 * @param newCount - the ID number we wish to set the counter to.
	 */
	public static void setCounter(int newCount) {
		counter = newCount;
	}

	/**
	 * An override of the toString method that will new output a full application in
	 * string form.
	 * 
	 * @return Application in string form
	 */
	@Override
	public String toString() {
		
		return "* " + getId() + "," + getState() + "," + getFirstName() + "," + getSurname() + ","
				+ getUnityId() + "," + getReviewer() + "," + getNote();
	}

	/**
	 * The update(Command) method drives the finite state machine by delegating to
	 * the currentState’s updateState(Command) method.
	 * 
	 * @param c - the command associated with updating the states.
	 * @throws UnsupportedOperationException if the current state determines that
	 *                                       the transition, as encapsulated by the
	 *                                       Command, is not appropriate for the
	 *                                       FSM.
	 */
	public void update(Command c) {
		currentState.updateState(c);
	}

	/**
	 * Interface for states in the Application State Pattern. All concrete
	 * Application states must implement the ApplicationState interface. The
	 * ApplicationState interface should be a private interface of the Application
	 * class.
	 * 
	 * @author Dr. Sarah Heckman (sarah_heckman@ncsu.edu)
	 */
	private interface ApplicationState {

		/**
		 * Update the Application from the given Command. An
		 * UnsupportedOperationException is thrown if the Command is not a valid action
		 * for the given state.
		 * 
		 * @param command Command describing the action that will update the
		 *                Application's state.
		 * @throws UnsupportedOperationException if the Command is not a valid action
		 *                                       for the given state.
		 */
		void updateState(Command command);

		/**
		 * Returns the name of the current state as a String.
		 * 
		 * @return the name of the current state as a String.
		 */
		String getStateName();
	}

	/**
	 * updates the state of a application to submitted
	 * 
	 * @author hchunter
	 *
	 */
	public class SubmittedState implements ApplicationState {

		/**
		 * Method updates the state of an application
		 * 
		 * @param command - the command associated with transitioning between each state
		 *                of an application
		 * 
		 * @throws UnsupportedOperationException if the current state determines that
		 *                                       the transition, as encapsulated by the
		 *                                       Command, is not appropriate for the
		 *                                       FSM.
		 */
		public void updateState(Command command) {
			try {

				// SubmittedA. The application is assigned to a reviewer and moves to the
				// Reviewing state.
				if (command.getCommand().equals(CommandValue.ASSIGN)) {
					currentState = reviewingState;
					setReviewer(command.getCommandInformation());
					setNote(note);
				}
				// SubmittedB. The application is Rejected due to one of the rejection reasons.
				if (command.getCommand().equals(CommandValue.REJECT)) {
					if (command.getCommandInformation() != QUALIFICATIONS_REJECTION
							&& command.getCommandInformation() != INCOMPLETE_REJECTION
							&& command.getCommandInformation() != POSITIONS_REJECTION
							&& command.getCommandInformation() != DUPLICATE_REJECTION) {

						setNote("");
						setReviewer("");
						throw new UnsupportedOperationException("Invalid command");
					}

					else {
						currentState = rejectedState;
						setNote(command.getCommandInformation());
						setReviewer(null);
					}
				}

				if (command.getCommand() != CommandValue.ASSIGN && command.getCommand() != CommandValue.REJECT) {
					throw new UnsupportedOperationException("Invalid command");
				}

			} catch (Exception UnsupportedOperationException) {
				throw new UnsupportedOperationException("Invalid command");
			}
		}

		/**
		 * returns submitted
		 * 
		 * @return the state name
		 */
		public String getStateName() {
			return SUBMITTED_NAME;
		}
	}

	/**
	 * updates the state of a application to rejected
	 * 
	 * @author hchunter
	 *
	 */
	public class RejectedState implements ApplicationState {

		/**
		 * Method updates the state of an application
		 * 
		 * @param command - the command associated with transitioning between each state
		 *                of an application
		 * 
		 * @throws UnsupportedOperationException if the current state determines that
		 *                                       the transition, as encapsulated by the
		 *                                       Command, is not appropriate for the
		 *                                       FSM.
		 */
		public void updateState(Command command) {

			try {
				// RejectedA. Something has changed about the application moving it back to the
				// Submitted state.
				if (command.getCommand().equals(CommandValue.RESUBMIT)) {
					currentState = submittedState;
					setNote(null);
				}

				if (command.getCommand() != CommandValue.RESUBMIT) {
					throw new UnsupportedOperationException("Invalid command");
				}

			} catch (Exception UnsupportedOperationException) {
				throw new UnsupportedOperationException("Invalid command");
			}
		}

		/**
		 * returns rejected
		 * 
		 * @return the state name
		 */
		public String getStateName() {
			return REJECTED_NAME;
		}
	}

	/**
	 * updates the state of a application to reviewing
	 * 
	 * @author hchunter
	 *
	 */
	public class ReviewingState implements ApplicationState {

		/**
		 * Method updates the state of an application
		 * 
		 * @param command - the command associated with transitioning between each state
		 *                of an application
		 * 
		 * @throws UnsupportedOperationException if the current state determines that
		 *                                       the transition, as encapsulated by the
		 *                                       Command, is not appropriate for the
		 *                                       FSM.
		 */
		public void updateState(Command command) {
			try {

				// ReviewingA. The applicant is contacted for an interview (outside of WolfHire)
				// and the application moves to the Interviewing state.
				if (command.getCommand().equals(CommandValue.SCHEDULE)) {
					currentState = interviewingState;

				}

				// ReviewingB. The reviewer cannot review the application and moves it back to
				// the Submitted state to be assigned to another reviewer.
				if (command.getCommand().equals(CommandValue.RETURN)) {
					currentState = submittedState;
					setReviewer(null);
				}

				// ReviewingC. The application is Rejected due to one of the rejection reasons.
				if (command.getCommand().equals(CommandValue.REJECT)) {
					if (command.getCommandInformation() != QUALIFICATIONS_REJECTION
							&& command.getCommandInformation() != INCOMPLETE_REJECTION
							&& command.getCommandInformation() != POSITIONS_REJECTION
							&& command.getCommandInformation() != DUPLICATE_REJECTION) {

						setNote("");
//								setReviewer("");
						throw new UnsupportedOperationException("Invalid command");
					} else {
						currentState = rejectedState;
						setNote(command.getCommandInformation());
						setReviewer(null);
					}
				}

				if (command.getCommand() != CommandValue.SCHEDULE && command.getCommand() != CommandValue.RETURN
						&& command.getCommand() != CommandValue.REJECT) {

					throw new UnsupportedOperationException("Invalid command");
				}

			} catch (Exception UnsupportedOperationException) {
				throw new UnsupportedOperationException("Invalid command");
			}
		}

		/**
		 * rejected reviewing
		 * 
		 * @return the state name
		 */
		public String getStateName() {
			return REVIEWING_NAME;

		}
	}

	/**
	 * updates the state of a application to interviewing
	 * 
	 * @author hchunter
	 *
	 */
	public class InterviewingState implements ApplicationState {

		/**
		 * Method updates the state of an application
		 * 
		 * @param command - the command associated with transitioning between each state
		 *                of an application
		 * 
		 * @throws UnsupportedOperationException if the current state determines that
		 *                                       the transition, as encapsulated by the
		 *                                       Command, is not appropriate for the
		 *                                       FSM.
		 */
		public void updateState(Command command) {
			try {
				// InterviewingA. The faculty/staff wants to hire the applicant and move the
				// application on for Processing paperwork.
				if (command.getCommand().equals(CommandValue.PROCESS)) {
					currentState = processingState;
				}

				// InterviewingB. The application is assigned to a new reviewer and is moved to
				// Reviewing.
				if (command.getCommand().equals(CommandValue.ASSIGN)) {
					currentState = reviewingState;
					setReviewer(command.getCommandInformation());
				}

				// InterviewingC. The interview is rescheduled.
				if (command.getCommand().equals(CommandValue.SCHEDULE)) {
					currentState = interviewingState;
					setNote(command.getCommandInformation());
				}

				// InterviewingD. The application is Rejected due to one of the rejection
				// reasons.
				if (command.getCommand().equals(CommandValue.REJECT)) {
					if (command.getCommandInformation() != QUALIFICATIONS_REJECTION
							&& command.getCommandInformation() != INCOMPLETE_REJECTION
							&& command.getCommandInformation() != POSITIONS_REJECTION
							&& command.getCommandInformation() != DUPLICATE_REJECTION) {

						setNote("");
//								setReviewer("");
						throw new UnsupportedOperationException("Invalid command");
					} else {
						currentState = rejectedState;
						setNote(command.getCommandInformation());
						setReviewer(null);
					}
				}

				if (command.getCommand() != CommandValue.PROCESS && command.getCommand() != CommandValue.ASSIGN
						&& command.getCommand() != CommandValue.SCHEDULE
						&& command.getCommand() != CommandValue.REJECT) {

					throw new UnsupportedOperationException("Invalid command");
				}

			} catch (Exception UnsupportedOperationException) {
				throw new UnsupportedOperationException("Invalid command");
			}
		}

		/**
		 * returns interviewing
		 * 
		 * @return the state name
		 */
		public String getStateName() {
			return INTERVIEWING_NAME;

		}
	}

	/**
	 * updates the state of a application to Processing
	 * 
	 * @author hchunter
	 *
	 */
	public class ProcessingState implements ApplicationState {

		/**
		 * Method updates the state of an application
		 * 
		 * @param command - the command associated with transitioning between each state
		 *                of an application
		 * 
		 * @throws UnsupportedOperationException if the current state determines that
		 *                                       the transition, as encapsulated by the
		 *                                       Command, is not appropriate for the
		 *                                       FSM.
		 */
		public void updateState(Command command) {

			try {

				// ProcessingA. The paperwork is completed and the applicant is Hired.
				if (command.getCommand().equals(CommandValue.HIRE)) {
					currentState = hiredState;
					setNote(null);
				}

				// ProcessingB. The application is Rejected due to one of the rejection reasons.
				if (command.getCommand().equals(CommandValue.REJECT)) {
					if (command.getCommandInformation() != QUALIFICATIONS_REJECTION
							&& command.getCommandInformation() != INCOMPLETE_REJECTION
							&& command.getCommandInformation() != POSITIONS_REJECTION
							&& command.getCommandInformation() != DUPLICATE_REJECTION) {

						setNote("");
//								setReviewer("");
						throw new UnsupportedOperationException("Invalid command");
					} else {
						currentState = rejectedState;
						setNote(command.getCommandInformation());
						setReviewer(null);
					}
				}

				if (command.getCommand() != CommandValue.HIRE && command.getCommand() != CommandValue.REJECT) {

					throw new UnsupportedOperationException("Invalid command");
				}

			} catch (Exception UnsupportedOperationException) {
				throw new UnsupportedOperationException("Invalid command");
			}

		}

		/**
		 * returns processing
		 * 
		 * @return the state name
		 */
		public String getStateName() {
			return PROCESSING_NAME;

		}
	}

	/**
	 * updates the state of a application to Hired
	 * 
	 * @author hchunter
	 *
	 */
	public class HiredState implements ApplicationState {

		/**
		 * Method updates the state of an application
		 * 
		 * @param command - the command associated with transitioning between each state
		 *                of an application
		 * 
		 * @throws UnsupportedOperationException if the current state determines that
		 *                                       the transition, as encapsulated by the
		 *                                       Command, is not appropriate for the
		 *                                       FSM.
		 */
		public void updateState(Command command) {
			try {
				if (command.getCommand() != CommandValue.TERMINATE) {
					throw new UnsupportedOperationException("Invalid command");
				}

				// HiredA. The applicant is terminated for one of the termination reasons.
				if (command.getCommand().equals(CommandValue.TERMINATE)) {
					if (command.getCommandInformation() != COMPLETED_TERMINATION
							&& command.getCommandInformation() != RESIGNED_TERMINATION
							&& command.getCommandInformation() != FIRED_TERMINATION) {

						throw new UnsupportedOperationException("Invalid command");
					} else {
						currentState = inactiveState;
						setNote(command.getCommandInformation());
					}
				}

			} catch (Exception UnsupportedOperationException) {
				throw new UnsupportedOperationException("Invalid command");
			}
		}

		/**
		 * returns hired
		 * 
		 * @return the state name
		 */
		public String getStateName() {
			return HIRED_NAME;

		}
	}

	/**
	 * updates the state of a application to Inactive
	 * 
	 * @author hchunter
	 *
	 */
	public class InactiveState implements ApplicationState {

		/**
		 * Method updates the state of an application
		 * 
		 * @param command - the command associated with transitioning between each state
		 *                of an application
		 * 
		 * @throws UnsupportedOperationException if the current state determines that
		 *                                       the transition, as encapsulated by the
		 *                                       Command, is not appropriate for the
		 *                                       FSM.
		 */
		public void updateState(Command command) {
			try {

				// Inactive a if any command is passed through throw an exception.
				if (command.getCommand() != null) {

					throw new UnsupportedOperationException("Invalid command");
				}

			} catch (Exception UnsupportedOperationException) {
				throw new UnsupportedOperationException("Invalid command");
			}
		}

		/**
		 * returns inactive
		 * 
		 * @return the state name
		 */
		public String getStateName() {
			return INACTIVE_NAME;

		}

	}

}
