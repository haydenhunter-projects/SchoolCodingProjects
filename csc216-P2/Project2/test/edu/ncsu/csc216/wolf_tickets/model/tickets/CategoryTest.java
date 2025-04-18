package edu.ncsu.csc216.wolf_tickets.model.tickets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * tests the Category class
 *
 * @author hchunter
 *
 */
public class CategoryTest {

	/**
	 * tests Category constructor
	 */
	@Test
	public void testCategoryConstructor() {
		Category category = new Category("Desktop", 3);

		// check that category object was correctly created
		assertEquals("Desktop", category.getCategoryName());
		assertEquals(3, category.getCompletedCount());

		// check for exception with null name
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> new Category(null, 3));
		assertEquals("Invalid name.", e1.getMessage());

		// check for exception with empty string name
		Exception e2 = assertThrows(IllegalArgumentException.class, () -> new Category("", 3));
		assertEquals("Invalid name.", e2.getMessage());

		// check for exception with negative completed count
		Exception e3 = assertThrows(IllegalArgumentException.class, () -> new Category("Desktop", -1));
		assertEquals("Invalid completed count.", e3.getMessage());
	}

	/**
	 * tests Ticket AddCategory, getCategoryName, completeTicket
	 */
	@Test
	public void testTicketAddCategory() {
		AbstractCategory category = new Category("Desktop", 3);
		Ticket ticket = new Ticket("Desktop broken", "it wont turn on", true);

		// check for exception with null name
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> ticket.addCategory(new Category(null, 3)));
		assertEquals("Invalid name.", e1.getMessage());

		category.addTicket(ticket);
		ticket.addCategory(category);

		assertEquals("Desktop", ticket.getCategoryName());
		assertEquals(3, category.getCompletedCount());

		// test completing the ticket
		ticket.completeTicket();

		// completed count should increment
		assertEquals(4, category.getCompletedCount());

		// category getTicket at 0 should now throw exception because it has no
		// ticket(s)
		Exception e2 = assertThrows(IndexOutOfBoundsException.class, () -> category.getTicket(0));
		assertEquals("Invalid index.", e2.getMessage());
	}

	/**
	 * tests Ticket Compare
	 */
	@Test
	public void testCategoryCompare() {

		Category category = new Category("Desktop", 3);
		Category category1 = new Category("Home", 1);
		Category category2 = new Category("Office", 5);
		Category category3 = new Category("Living Area", 9);

		// Desktop to Home
		assertEquals(-4, category.compareTo(category1));

		// Home to Office
		assertEquals(-7, category1.compareTo(category2));

		// Office to Living Area
		assertEquals(3, category2.compareTo(category3));

		// Living Area to Desktop
		assertEquals(8, category3.compareTo(category));
	}

	/**
	 * tests Ticket get tickets as array.
	 */
	@Test
	public void testTicketStringArray() {
		Category category = new Category("Desktop", 3);
		Ticket ticket = new Ticket("Desktop broken", "it wont turn on", true);
		Ticket ticket1 = new Ticket("Monitor broken", "it wont turn on", true);

		category.addTicket(ticket);
		category.addTicket(ticket1);

		String[][] check = category.getTicketsAsArray();
		// should be ticket1, ticket2
		assertEquals("0", check[0][0]);
		assertEquals("Desktop broken", check[0][1]);
		assertEquals("1", check[1][0]);
		assertEquals("Monitor broken", check[1][1]);

	}
}
