package edu.ncsu.csc216.wolf_hire.model.command;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_hire.model.command.Command.CommandValue;

class CommandTest {

	/**
	 * tests the command constructor
	 */
	@Test
	public void testCommandConstructor() {
		Exception ce1 = assertThrows(IllegalArgumentException.class, () -> new Command(null, ""));
		assertEquals("Invalid information.", ce1.getMessage());

		Exception ce2 = assertThrows(IllegalArgumentException.class,
				() -> new Command(Command.CommandValue.ASSIGN, null));
		assertEquals("Invalid information.", ce2.getMessage());

		Exception ce3 = assertThrows(IllegalArgumentException.class,
				() -> new Command(Command.CommandValue.REJECT, null));
		assertEquals("Invalid information.", ce3.getMessage());

		Exception ce4 = assertThrows(IllegalArgumentException.class,
				() -> new Command(Command.CommandValue.TERMINATE, null));
		assertEquals("Invalid information.", ce4.getMessage());

		Exception ce5 = assertThrows(IllegalArgumentException.class,
				() -> new Command(Command.CommandValue.RESUBMIT, ""));
		assertEquals("Invalid information.", ce5.getMessage());

		Exception ce6 = assertThrows(IllegalArgumentException.class,
				() -> new Command(Command.CommandValue.RETURN, ""));
		assertEquals("Invalid information.", ce6.getMessage());

		Exception ce7 = assertThrows(IllegalArgumentException.class,
				() -> new Command(Command.CommandValue.SCHEDULE, ""));
		assertEquals("Invalid information.", ce7.getMessage());

		Exception ce8 = assertThrows(IllegalArgumentException.class,
				() -> new Command(Command.CommandValue.PROCESS, ""));
		assertEquals("Invalid information.", ce8.getMessage());

		Exception ce9 = assertThrows(IllegalArgumentException.class, () -> new Command(Command.CommandValue.HIRE, ""));
		assertEquals("Invalid information.", ce9.getMessage());
		
		Exception ce10 = assertThrows(IllegalArgumentException.class, 
				() -> new Command(Command.CommandValue.TERMINATE, ""));
		assertEquals("Invalid information.", ce10.getMessage());
		
		Exception ce11 = assertThrows(IllegalArgumentException.class, 
				() -> new Command(Command.CommandValue.REJECT, ""));
		assertEquals("Invalid information.", ce11.getMessage());
		
		Exception ce12 = assertThrows(IllegalArgumentException.class, 
				() -> new Command(Command.CommandValue.ASSIGN, ""));
		assertEquals("Invalid information.", ce12.getMessage());

	}

	/**
	 * tests get command information
	 */
	@Test
	public void testGetCommandInformation() {
		Command c = new Command(CommandValue.ASSIGN, "command information");

		assertEquals(CommandValue.ASSIGN, c.getCommand());
		assertEquals("command information", c.getCommandInformation());
	}
}
