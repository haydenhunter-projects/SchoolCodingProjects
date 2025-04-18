package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * CourseNameValidator implements the State pattern for the FSM focusing on
 * course name validation.
 * 
 * @author nolanhurst
 * @author hchunter
 */
public class CourseNameValidator {

	/** Count of letters in course name */
	private static int letterCount;

	/** Count of digits in course name */
	private static int digitCount;

	/** Count of suffix's in course name */
	private static int suffixCount;

	/** Current state of the course name */
	private static State currentState;

	/** Max letters allowed the course name */
	private static int maxLetters = 4;

	/** Minimum number of letters allowed in course name */
	private static int minLetters = 1;

	/** Amount of digits required for valid course name */
	private static int correctDigits = 3;

	/** Max amount of suffix's allowed in course name */
	private static int maxSuffix = 1;

	/** Letter State of State class */
	private State letterState = new LetterState();

	/** Digit State of State class */
	private State digitState = new DigitState();

	/** Initial State of State Class */
	private State initialState = new InitialState();

	/** Suffix State of State Class */
	private State suffixState = new SuffixState();

	/**
	 * This method checks if a course name is in the correct format (1-4) letters
	 * followed by 3 digit followed by 1 optional suffix
	 * 
	 * @param courseName the name of course
	 * 
	 * @return true if the course name is valid and false if not
	 * @throws InvalidTransitionException with message Invalid FSM Transition if
	 *                                    invalid transition is entered.
	 */
	public boolean isValid(String courseName) throws InvalidTransitionException {

		currentState = initialState;
		letterCount = 0;
		digitCount = 0;
		suffixCount = 0;

		for (int i = 0; i < courseName.length(); i++) {
			if (Character.isLetter(courseName.charAt(i)) && currentState != digitState) {
				currentState.onLetter();
			} else if (Character.isDigit(courseName.charAt(i))) {
				currentState.onDigit();
			} else if (Character.isLetter(courseName.charAt(i))) {
				currentState.onLetter();
			} else {
				State.onOther();
			}
		}

		return digitCount == correctDigits;
	}

	/**
	 * The State class will allow transitions for the FSM. This abstract class holds
	 * the three transitions through our FSM, onLetter, onOther, and onDigit.
	 * 
	 * @author nolanhurst
	 * @author hchunter
	 *
	 */
	private abstract class State {

		/**
		 * Abstract method that passes onLetter to correct State class
		 * 
		 * @throws InvalidTransitionException from onLetter in individual State classes
		 */
		abstract void onLetter() throws InvalidTransitionException;

		/**
		 * Concrete method called if there is a character that is not a digit or a
		 * letter in the course name string
		 * 
		 * @throws InvalidTransitionException if there is a character in the course name
		 *                                    string that is not a digit or letter
		 */
		public static void onOther() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only contain letters and digits.");
		}

		/**
		 * Abstract method that passes onDigit to correct State class
		 * 
		 * @throws InvalidTransitionException from onDigit in individual State classes
		 */
		abstract void onDigit() throws InvalidTransitionException;

	}

	/**
	 * This is the Letter State for a course name, if the course name is on a letter
	 * not including a suffix then it will be in this state
	 * 
	 * @author nolanhurst
	 *
	 */
	public class LetterState extends State {

		/**
		 * If a letter is next in the course name this method is called and add 1 to the
		 * letter Count and if the letterCound goes over the maximum letters it throw
		 * and InvalidTransitionException
		 * 
		 * @throws InvalidTransitionException if the number of letters is greater than 4
		 */
		public void onLetter() throws InvalidTransitionException {
			letterCount++;
			if (letterCount > maxLetters) {
				throw new InvalidTransitionException("Course name cannot start with more than 4 letters.");
			}
		}

		/**
		 * If a digit is next in the course name and previously there was a letter this
		 * method is called and adds 1 to the digit count and changes current state to
		 * the digit state
		 */
		public void onDigit() {
			digitCount++;
			currentState = digitState;
		}

	}

	/**
	 * This is the digit State, if the course name character is a digit then it will
	 * be put into this state
	 * 
	 * @author nolanhurst
	 *
	 */
	public class DigitState extends State {

		/**
		 * If a letter is found after the digits in course name this method will be
		 * called and will check for the correct amount of digits (3) and change the
		 * state to a suffixState and add 1 to the suffix count, but if the amount of
		 * digits is not 3 then it will throw and InvalidTransitionException
		 * 
		 * @throws InvalidTransitionException if the digit count is not equal to 3
		 */
		public void onLetter() throws InvalidTransitionException {
			if (digitCount == correctDigits) {
				suffixCount++;
				currentState = suffixState;
			} else {
				throw new InvalidTransitionException("Course name must have 3 digits.");
			}
		}

		/**
		 * If there is a digit character in the course name followed by another digit
		 * this method will be called and will check for the correct amount of letters
		 * before the digit (1) and add 1 to the digit count, if the digit count is
		 * greater than 3 or if the letter count is less than 1 it will throw in
		 * InvalidTransitionExcpeition
		 * 
		 * @throws InvalidTransitionException if letter count is less than 1 or is digit
		 *                                    Count is greater than 3
		 * 
		 */
		public void onDigit() throws InvalidTransitionException {
			if (letterCount < minLetters) {
				throw new InvalidTransitionException("Course name must start with a letter.");
			}
			digitCount++;
			if (digitCount > correctDigits) {
				throw new InvalidTransitionException("Course name can only have 3 digits.");
			}

		}

	}

	/**
	 * This is the initial state of the course name and it will always start in this
	 * state
	 * 
	 * @author nolanhurst
	 */
	public class InitialState extends State {

		/**
		 * If the first character in the course name string is a letter this method will
		 * be called and will add 1 to the letter count and change the current state to
		 * the letter state.
		 */
		public void onLetter() {
			letterCount++;
			currentState = letterState;

		}

		/**
		 * If the first character in the course name string is a digit then this method
		 * will be called and will add 1 to the digit count and change the current state
		 * to the digit state.
		 */
		public void onDigit() {
			digitCount++;
			currentState = digitState;

		}

	}

	/**
	 * The suffix state is for when there is a letter suffix following the digits in
	 * the course name string.
	 * 
	 * @author nolanhurst
	 *
	 */
	public class SuffixState extends State {

		/**
		 * This method will be called when there is a letter following the digits in the
		 * course name string and it will add 1 to the suffix count, then if the suffix
		 * count is greater than 1 it will throw an InvalidTransitionException
		 * 
		 * @throws InvalidTransitionException if the course name has more than 1 letter
		 *                                    suffix
		 */
		public void onLetter() throws InvalidTransitionException {
			suffixCount++;
			if (suffixCount > maxSuffix) {
				throw new InvalidTransitionException("Course name can only have a 1 letter suffix.");
			}
		}

		/**
		 * This method will be called if there is a digit after the suffix of the course
		 * name string and will throw in InvalidTransitionException if it is called
		 * 
		 * @throws InvalidTransitionException if there is a digit following the suffix
		 * 
		 */
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name cannot contain digits after the suffix.");

		}

	}

}