/**
 * 
 */
package edu.ncsu.csc216.wolf_hire.model.command;

/**
 * Creates objects that encapsulate user actions (or transitions) that cause the
 * state of a Application to update.
 * 
 * @author hchunter
 *
 */
public class Command {

	/**
	 * Stores the values of ASSIGN, REJECT, RESUBMIT, RETURN, SCHEDULE, PROCESS,
	 * HIRE, TERMINATE
	 */
	private CommandValue command;

	/**
	 * The information associated with each of our command values
	 */
	private String commandInformation;

	/** the possible command values that can cause transitions in our FSM */
	public enum CommandValue {
		/**
		 * the possible command values that can cause transitions in our FSM
		 */
		ASSIGN, REJECT, RESUBMIT, RETURN, SCHEDULE, PROCESS, HIRE, TERMINATE
	}

	/**
	 * Creates a command with a command value and information about the command
	 * 
	 * @param commandInformation -
	 * @param command            the values of ASSIGN, REJECT, RESUBMIT, RETURN,
	 *                           SCHEDULE, PROCESS, HIRE, TERMINATE
	 * @throws IllegalArgumentException /IF/ A Command with a null CommandValue
	 *                                  parameter. A Command MUST have a
	 *                                  CommandValue. /IF/ A Command with a
	 *                                  CommandValue of ASSIGN, REJECT, or TERMINATE
	 *                                  with a null or empty string
	 *                                  commandInformation. These commands require
	 *                                  an additional piece of information. Any
	 *                                  non-null, non-empty string is okay. /IF/ A
	 *                                  Command with a CommandValue of RESUBMIT,
	 *                                  RETURN, SCHEDULE, PROCESS, or HIRE and a
	 *                                  non-null commandInformation. These commands
	 *                                  do NOT require an additional piece of
	 *                                  information.
	 * 
	 */
	public Command(CommandValue command, String commandInformation) {
		if (command == null) {
			throw new IllegalArgumentException("Invalid information.");
		}
		if (command.equals(CommandValue.ASSIGN) && commandInformation == null) {
			throw new IllegalArgumentException("Invalid information.");
		}
		if (command.equals(CommandValue.ASSIGN) && "".equals(commandInformation)) {
			throw new IllegalArgumentException("Invalid information.");
		}
		if (command.equals(CommandValue.REJECT) && commandInformation == null) {
			throw new IllegalArgumentException("Invalid information.");
		}
		if (command.equals(CommandValue.REJECT) && "".equals(commandInformation)) {
			throw new IllegalArgumentException("Invalid information.");
		}
		if (command.equals(CommandValue.TERMINATE) && commandInformation == null) {
			throw new IllegalArgumentException("Invalid information.");
		}
		if (command.equals(CommandValue.TERMINATE) && "".equals(commandInformation)) {
			throw new IllegalArgumentException("Invalid information.");
		}
		if (command.equals(CommandValue.RESUBMIT) && commandInformation != null) {
			throw new IllegalArgumentException("Invalid information.");
		}
		if (command.equals(CommandValue.RETURN) && commandInformation != null) {
			throw new IllegalArgumentException("Invalid information.");
		}
		if (command.equals(CommandValue.SCHEDULE) && commandInformation != null) {
			throw new IllegalArgumentException("Invalid information.");
		}
		if (command.equals(CommandValue.PROCESS) && commandInformation != null) {
			throw new IllegalArgumentException("Invalid information.");
		}
		if (command.equals(CommandValue.HIRE) && commandInformation != null) {
			throw new IllegalArgumentException("Invalid information.");
		}
		this.command = command;
		this.commandInformation = commandInformation;
	}

	/**
	 * returns the current command
	 * 
	 * @return the current command
	 */
	public CommandValue getCommand() {
		return command;
	}

	/**
	 * returns the current commands information
	 * 
	 * @return the current command information
	 */
	public String getCommandInformation() {
		return commandInformation;
	}
}
