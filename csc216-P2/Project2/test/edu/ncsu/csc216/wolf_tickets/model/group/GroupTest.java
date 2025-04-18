package edu.ncsu.csc216.wolf_tickets.model.group;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_tickets.model.tickets.Category;
import edu.ncsu.csc216.wolf_tickets.model.tickets.Ticket;

/**
 * tests Group Class
 *
 * @author hchunter
 *
 */
public class GroupTest {

	/**
	 * tests Group Constructor
	 */
	@Test
	public void testGroupConstructor() {

		Group group = new Group("Cool Group Name");
		assertEquals("Cool Group Name", group.getGroupName());
		assertEquals(1, group.getCategoriesNames().length);
		assertEquals("Active Tickets", group.getCurrentCategory().getCategoryName());
		Exception e = assertThrows(IllegalArgumentException.class, () -> new Group(null));
		assertEquals("Invalid name.", e.getMessage());

		Exception e1 = assertThrows(IllegalArgumentException.class, () -> new Group(""));
		assertEquals("Invalid name.", e1.getMessage());

		Exception e2 = assertThrows(IllegalArgumentException.class, () -> new Group("Active Tickets"));
		assertEquals("Invalid name.", e2.getMessage());
	}

	/**
	 * tests Group add / display
	 */
	@Test
	public void testGroupAdd() {
		Group group = new Group("Cool Group Name");

		Category category = new Category("Desktop", 1);

		group.addCategory(category);

		// The only category name to display should be desktop
		String[] displayTest = group.getCategoriesNames();
		assertEquals("Active Tickets", displayTest[0]);
		assertEquals("Desktop", displayTest[1]);

		// current category is Desktop
		assertEquals("Desktop", group.getCurrentCategory().getCategoryName());

		// adding a category to a group marks it as changed
		assertTrue(group.isChanged());
	}

	/**
	 * tests Group functionality remove
	 */
	@Test
	public void testGroupRemove() {
		Group group = new Group("Cool Group Name");
		Category category = new Category("Desktop", 1);
		Category category1 = new Category("Monitor", 1);
		group.addCategory(category);

		// current category is Desktop
		assertEquals("Desktop", group.getCurrentCategory().getCategoryName());

		// adding a category to a group marks it as changed
		assertTrue(group.isChanged());

		// will remove the current category from the list
		group.removeCategory();

		// current category should now be Active Tickets since only category is removed
		assertEquals("Active Tickets", group.getCurrentCategory().getCategoryName());

		// test adding multiple categories
		group.addCategory(category);
		group.addCategory(category1);

		// Categories list is now Desktop, Monitor
		String[] displayTest = group.getCategoriesNames();
		assertEquals("Active Tickets", displayTest[0]);
		assertEquals("Desktop", displayTest[1]);
		assertEquals("Monitor", displayTest[2]);

		// current category should be category 1
		assertEquals("Monitor", group.getCurrentCategory().getCategoryName());

		// lets set the current category to category and remove it
		group.setCurrentCategory("Desktop");
		assertEquals("Desktop", group.getCurrentCategory().getCategoryName());
		group.removeCategory();

		// only category in list now is category1, should be ActiveTickets after removal
		assertEquals("Active Tickets", group.getCurrentCategory().getCategoryName());

		// remove the other category
		group.setCurrentCategory("Monitor");
		assertEquals("Monitor", group.getCurrentCategory().getCategoryName());
		group.removeCategory();

		// current category should be active tickets
		assertEquals("Active Tickets", group.getCurrentCategory().getCategoryName());

		// attempt to set current category to a category that does not exist
		group.addCategory(category1);
		group.setCurrentCategory("Apple");

		// current category is active tickets still
		assertEquals("Active Tickets", group.getCurrentCategory().getCategoryName());

		// Exception when trying to remove Active Tickets
		Exception e = assertThrows(IllegalArgumentException.class, () -> group.removeCategory());
		assertEquals("The Active Tickets list may not be deleted.", e.getMessage());

	}

	/**
	 * tests Group functionality
	 */
	@Test
	public void testGroupGetCategoryNames() {

		Group group = new Group("Group Title");
		group.addCategory(new Category("Category1", 0));
		String[] cateNames = new String[4];
		cateNames = group.getCategoriesNames();
		assertEquals("Active Tickets", cateNames[0]);
		assertEquals("Category1", cateNames[1]);

		group.getCurrentCategory().addTicket(new Ticket("Ticket1", "TicketDescription1", false));

		String[][] ticketNames = new String[4][4];
		ticketNames = group.getCurrentCategory().getTicketsAsArray();
		assertEquals("Ticket1", ticketNames[0][1]);
	}

	/**
	 * tests Group functionality edit ticket
	 */
	@Test
	public void testGroupEditTicket() {
		Group group = new Group("Group Title");
		Category category = new Category("Category1", 0);
		Ticket ticket = new Ticket("Desktop broken", "it wont turn on", false);
		Ticket ticket1 = new Ticket("Monitor broken", "it wont turn on", false);
		Ticket ticket2 = new Ticket("Cable frayed", "it was chopped off", true);
		Ticket ticket3 = new Ticket("Desktop broken", "it wont turn on", true);
		Ticket ticket4 = new Ticket("Monitor broken", "it wont turn on", true);

		category.addTicket(ticket);
		category.addTicket(ticket1);
		category.addTicket(ticket2);
		category.addTicket(ticket3);
		category.addTicket(ticket4);
		group.addCategory(category);

		// should be active ticket list
		assertEquals("Category1", group.getCurrentCategory().getCategoryName());
		assertEquals(5, group.getCurrentCategory().getTickets().size());

		// test edit
		group.editTicket(0, "Monitor broken sheeesh", "sheesh", true);

		assertEquals("Monitor broken sheeesh", group.getCurrentCategory().getTicket(0).getTicketName());
		assertEquals("sheesh", group.getCurrentCategory().getTicket(0).getTicketDescription());

		group.setCurrentCategory("Active Tickets");
		assertEquals(4, group.getCurrentCategory().getTickets().size());

		group.setCurrentCategory("Category1");
		group.editTicket(2, "Ticket1", "Ticket1Description", false);

		// active ticket list should now have 3 active
		group.setCurrentCategory("Active Tickets");
		assertEquals(3, group.getCurrentCategory().getTickets().size());
	}

	/**
	 * tests Group functionality edit category
	 */
	@Test
	public void testGroupEditCategory() {
		Group group = new Group("Group Title");
		Category category = new Category("Category1", 0);
		Ticket ticket2 = new Ticket("Cable frayed", "it was chopped off", true);
		Ticket ticket3 = new Ticket("Desktop broken", "it wont turn on", true);
		Ticket ticket4 = new Ticket("Monitor broken", "it wont turn on", true);

		category.addTicket(ticket2);
		category.addTicket(ticket3);
		category.addTicket(ticket4);
		group.addCategory(category);

		// Exception when trying to add duplicate category
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> group.addCategory(new Category("Category1", 0)));
		assertEquals("Invalid name.", e.getMessage());

		group.editCategory("sick category");

		assertEquals("sick category", group.getCurrentCategory().getCategoryName());
		assertEquals(0, group.getCurrentCategory().getCompletedCount());

		Exception e1 = assertThrows(IllegalArgumentException.class, () -> group.editCategory("Active Tickets"));
		assertEquals("Invalid name.", e1.getMessage());

		Exception e2 = assertThrows(IllegalArgumentException.class, () -> group.editCategory(null));
		assertEquals("Invalid name.", e2.getMessage());
	}

}
