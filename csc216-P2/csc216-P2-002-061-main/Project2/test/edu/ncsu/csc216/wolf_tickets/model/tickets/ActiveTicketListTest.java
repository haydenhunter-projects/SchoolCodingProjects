package edu.ncsu.csc216.wolf_tickets.model.tickets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * tests the Active Ticket List class
 *
 * @author hchunter
 *
 */
public class ActiveTicketListTest {

	/**
	 * tests ActiveTicket list constructor
	 */
	@Test
	public void testActiveTicketListConstructor() {
		AbstractCategory activeTicketList = new ActiveTicketList();

		activeTicketList.setCategoryName("Active Tickets");

		assertEquals("Active Tickets", activeTicketList.getCategoryName());
		assertEquals(0, activeTicketList.getCompletedCount());
	}

	/**
	 * tests ActiveTicket list add Ticket, clear ticket, get ticket as array
	 */
	@Test
	public void testActiveTicketListAddTicket() {
		ActiveTicketList activeTicketList = new ActiveTicketList();
		Ticket ticket = new Ticket("Desktop broken", "it wont turn on", true);
		Ticket ticket1 = new Ticket("Monitor broken", "it wont turn on", true);
		Ticket ticket2 = new Ticket("Cable frayed", "it was chopped off", false);
		Category category = new Category("Office", 2);
		category.addTicket(ticket);
		category.addTicket(ticket1);
		category.addTicket(ticket2);

		// throws exception because not an active ticket
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> activeTicketList.addTicket(ticket2));
		assertEquals("Cannot add ticket to Active Tickets.", e1.getMessage());

		activeTicketList.addTicket(ticket);
		activeTicketList.addTicket(ticket1);
		String[][] check = activeTicketList.getTicketsAsArray();

		// should be ticket1, ticket2
		assertEquals("Office", check[0][0]);
		assertEquals("Desktop broken", check[0][1]);
		assertEquals("Office", check[1][0]);
		assertEquals("Monitor broken", check[1][1]);

		activeTicketList.clearTickets();
		check = activeTicketList.getTicketsAsArray();

		// should be empty
		assertEquals(0, check.length);
	}

	/**
	 * tests active ticket list
	 */
	@Test
	public void testActiveTicketListCategory() {
		ActiveTicketList activeTicketList = new ActiveTicketList();
		Ticket ticket = new Ticket("Desktop broken", "it wont turn on", false);
		Ticket ticket1 = new Ticket("Monitor broken", "it wont turn on", false);
		Ticket ticket2 = new Ticket("Cable frayed", "it was chopped off", true);
		Ticket ticket4 = new Ticket("Desktop broken", "it wont turn on", true);
		Ticket ticket5 = new Ticket("Monitor broken", "it wont turn on", true);

		// throws exception because not an active ticket
		Exception e = assertThrows(IllegalArgumentException.class, () -> activeTicketList.addTicket(ticket));
		assertEquals("Cannot add ticket to Active Tickets.", e.getMessage());
		// throws exception because not an active ticket
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> activeTicketList.addTicket(ticket1));
		assertEquals("Cannot add ticket to Active Tickets.", e1.getMessage());

		activeTicketList.addTicket(ticket2);
		activeTicketList.getCategoryName();

		String[][] displayTest = new String[4][5];
		displayTest = activeTicketList.getTicketsAsArray();
		assertEquals("Active Tickets", displayTest[0][0]);

		// activeTicketList should now have 3 tickets
		activeTicketList.addTicket(ticket4);
		activeTicketList.addTicket(ticket5);

		// complete a ticket in the list and check size to 2
		activeTicketList.completeTicket(ticket5);
		assertEquals(2, activeTicketList.getTickets().size());

		activeTicketList.clearTickets();
		assertEquals(0, activeTicketList.getTickets().size());
	}

}
