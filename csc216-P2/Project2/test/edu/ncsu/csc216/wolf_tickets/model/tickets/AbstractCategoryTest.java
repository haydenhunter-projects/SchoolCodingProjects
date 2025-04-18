package edu.ncsu.csc216.wolf_tickets.model.tickets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class AbstractCategoryTest {

	/**
	 * tests AbstractCategory add, remove and complete ticket
	 */
	@Test
	void testAbstractCategoryAddRemoveComplete() {
		AbstractCategory category = new Category("Desktop", 1);
		Ticket ticket = new Ticket("Desktop broken", "it wont turn on", true);
		Ticket ticket1 = new Ticket("Monitor broken", "it wont turn on", true);
		Ticket ticket2 = new Ticket("Cable frayed", "it was chopped off", false);

		category.addTicket(ticket);
		category.addTicket(ticket1);
		category.addTicket(ticket2);

		assertEquals("Desktop", category.getCategoryName());
		assertEquals(1, category.getCompletedCount());
		assertEquals(3, category.getTicketsAsArray().length);

		// remove ticket
		category.removeTicket(0);

		// try to complete a null ticket
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> category.completeTicket(new Ticket(null, null, false)));
		assertEquals("Incomplete ticket information.", e.getMessage());

		// check to make sure we are still at 2 tickets in the list
		assertEquals(2, category.getTicketsAsArray().length);

		category.completeTicket(ticket2);

		// removes ticket 2 and increments count
		assertEquals(1, category.getTicketsAsArray().length);

		// count should be at 2
		assertEquals(2, category.getCompletedCount());

	}

}
