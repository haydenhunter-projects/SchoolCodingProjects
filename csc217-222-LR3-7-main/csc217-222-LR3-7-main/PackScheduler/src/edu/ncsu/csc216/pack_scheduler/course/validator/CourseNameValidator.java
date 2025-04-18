package edu.ncsu.csc216.pack_scheduler.course.validator;
/**
 * Validates that course name meets requirements
 * Validates that course name contains 1 to 4 letter characters 
 * followed immediately by 3 digit and optionally followed by one letter.
 * No spaces and special characters allowed
 * @author jerol
 *
 */
public class CourseNameValidator {
	/** If a valid string is found, this changes to true */
	private boolean validEndState = false;
	/** Number of letter in course name */
	private int letterCount;
	/** Number of digits in course name */
	private int digitCount;
	/** Current State of course name */
	private State currentState;
	/**
	 * Creates a CourseNameValidator object
	 */
	public CourseNameValidator() {
		//This should be undocumented and empty
	}
	/**
	 * Checks is course name is valid
	 * @param courseName Name of the course 
	 * @return boolean True if valid, False if invalid
	 * @throws InvalidTransitionException if the called method throws an exception
	 */
	public boolean isValid(String courseName) throws InvalidTransitionException {
		this.currentState = new InitialState();
		letterCount = 0;
		digitCount = 0;
		for (int i = 0; i < courseName.length(); i++) {
			if (Character.isDigit(courseName.charAt(i))) {
				currentState.onDigit();
			}
			if (Character.isLetter(courseName.charAt(i))) {
				currentState.onLetter();
			}
			if (!(Character.isDigit(courseName.charAt(i))) && !(Character.isLetter(courseName.charAt(i)))) {
				currentState.onOther();
			}
		}
		if (digitCount == 3 && letterCount > 0 && letterCount < 5){
			validEndState = true;
		}
		return validEndState;
	}
	
	private abstract class State {
		public State() {
			//This should be undocumented and empty
		}
			
		public abstract void onLetter() throws InvalidTransitionException;	
		public abstract void onDigit() throws InvalidTransitionException;
		public void onOther() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only contain letters and digits.");
		}
	}
	
	private class LetterState extends State {
		/** Maximum letters in course name */
		private static final int MAX_PREFIX_LETTERS = 4;
		
		private LetterState() {
			//implement
		}
		@Override
		public void onLetter() throws InvalidTransitionException {
			if (letterCount > 0 && letterCount < 4) {
				letterCount++;
			}
			else if (letterCount == MAX_PREFIX_LETTERS) {
				throw new InvalidTransitionException("Course name cannot start with more than 4 letters.");
			}
			
		}

		@Override
		public void onDigit() throws InvalidTransitionException {
			if (letterCount > 0 && letterCount < 5) {
				digitCount++;
				currentState = new NumberState();
			}
			else {
				throw new InvalidTransitionException("Course name must start with a letter.");
			}
		}
		
	}
	
	private class SuffixState extends State {

		private SuffixState() {
			
		}
		@Override
		public void onLetter() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only have a 1 letter suffix.");
		}

		@Override
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name cannot contain digits after the suffix.");
		}
		
	}
	
	private class InitialState extends State {

		private InitialState() {
			//implement
		}
		@Override
		public void onLetter() {
			letterCount++;
			currentState = new LetterState();
		}

		@Override
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name must start with a letter.");
		}
		
	}
	
	private class NumberState extends State {
		/** Maximum digits in course name */
		private static final int COURSE_NUMBER_LENGTH = 3;
		
		private NumberState() {
			//implement
		}
		

		@Override
		public void onLetter() throws InvalidTransitionException {
			if (digitCount == COURSE_NUMBER_LENGTH) {
				currentState = new SuffixState();
			}
			else {
				throw new InvalidTransitionException("Course name must have 3 digits.");
			}
		}

		@Override
		public void onDigit() throws InvalidTransitionException {
			if (digitCount < COURSE_NUMBER_LENGTH) {
				digitCount++;
			}
			else {
				throw new InvalidTransitionException("Course name can only have 3 digits.");
			}
			
		}
		
	}
	
	

}
