package edu.ncsu.csc216.wolf_tickets.model.tickets;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * tests the ticket class
 *
 * @author hchunter
 *
 */
public class TicketTest {

	/**
	 * tests the ticket constructor
	 */
	@Test
	public void testTicketConstructor() {
		Ticket ticket1 = new Ticket("broken monitor", "i punched a hole in my monitor", true);

		// correct ticket name and description
		assertEquals("broken monitor", ticket1.getTicketName());
		assertEquals("i punched a hole in my monitor", ticket1.getTicketDescription());

		Exception e1 = assertThrows(IllegalArgumentException.class, () -> new Ticket(null, "ticket description", true));
		assertEquals("Incomplete ticket information.", e1.getMessage());

		Exception e2 = assertThrows(IllegalArgumentException.class, () -> new Ticket("", "ticket description", true));
		assertEquals("Incomplete ticket information.", e2.getMessage());

		Exception e3 = assertThrows(IllegalArgumentException.class, () -> new Ticket("ticket name", null, true));
		assertEquals("Incomplete ticket information.", e3.getMessage());

	}

	/**
	 * tests Ticket to string
	 */
	@Test
	public void testTicketToString() {
		Ticket ticket1 = new Ticket("broken monitor", "i punched a hole in my monitor", true);

		assertEquals("* broken monitor,active\n" + "i punched a hole in my monitor\n", ticket1.toString());
	}

	/**
	 * tests Ticket active
	 */
	@Test
	public void testTicketIsActive() {
		Ticket ticket1 = new Ticket("broken monitor", "i punched a hole in my monitor", true);

		assertTrue(ticket1.isActive());
		assertTrue(new Ticket("Desktop broken", "wont turn on", true).isActive());
		assertFalse(new Ticket("cable broken", "wont power", false).isActive());
		assertFalse(new Ticket("keyboard broken", "keyboard wont let me type", false).isActive());
	}

	/**
	 * tests Ticket observer pattern
	 */
	@Test
	public void testTicketObserver() {
		Ticket ticket = new Ticket("broken monitor", "i punched a hole in my monitor", true);
		Ticket ticket1 = new Ticket("broken tv", "i punched a hole in my tv", true);
		Ticket ticket2 = new Ticket("broken cable", "i cut my monitor cable", true);
		Category category = new Category("Office", 2);

		category.addTicket(ticket);
		category.addTicket(ticket1);
		category.addTicket(ticket2);
		assertEquals("Office", ticket.getCategoryName());
		assertEquals("Office", ticket1.getCategoryName());
		assertEquals("Office", ticket2.getCategoryName());

		ticket.completeTicket();
		ticket1.completeTicket();
		assertEquals(4, category.getCompletedCount());
		assertEquals(ticket2, category.getTicket(0));

	}

}
