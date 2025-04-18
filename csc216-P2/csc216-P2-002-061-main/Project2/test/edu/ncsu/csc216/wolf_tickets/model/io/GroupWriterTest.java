package edu.ncsu.csc216.wolf_tickets.model.io;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_tickets.model.group.Group;
import edu.ncsu.csc216.wolf_tickets.model.tickets.Category;
import edu.ncsu.csc216.wolf_tickets.model.tickets.Ticket;
import edu.ncsu.csc216.wolf_tickets.model.util.ISortedList;
import edu.ncsu.csc216.wolf_tickets.model.util.SortedList;

/**
 * tests GroupWriter class
 *
 * @author hchunter
 *
 */
public class GroupWriterTest {

	/** file used for testing write */
	File testFile = new File("test-files/testfile.txt");

	/** file used for testing write */
	File testFile1 = new File("test-files/testfile1.txt");

	/**
	 * Tests writeGroup()
	 */
	@Test
	public void testWriteGroupCategories() {
		Group group = new Group("cool bro");

		ISortedList<Category> categories = new SortedList<>();
		categories.add(new Category("desktop", 3));
		categories.add(new Category("classroom tech", 4));
		categories.add(new Category("web", 6));

		try {
			GroupWriter.writeGroupFile(testFile, group.getGroupName(), categories);
		} catch (IllegalArgumentException e) {
			fail("Unable to save file.");
		}
	}

	/**
	 * Tests writeGroup custom file
	 */
	@Test
	public void testWriteGroupCategoriesAndTickets() {
		Group group = new Group("Group1");

		ISortedList<Category> categories = new SortedList<>();
		Category category = new Category("desktop", 3);
		Ticket ticket = new Ticket("Ticket0", "Ticket0Description", true);
		category.addTicket(ticket);

		Category category1 = new Category("classroom tech", 4);
		Ticket ticket1 = new Ticket("Ticket1", "Ticket1Description", true);
		Ticket ticket2 = new Ticket("Ticket2", "Ticket2Description", false);
		category1.addTicket(ticket1);
		category1.addTicket(ticket2);

		Category category2 = new Category("web", 6);
		Ticket ticket3 = new Ticket("Ticket3", "Ticket3Description", true);
		Ticket ticket4 = new Ticket("Ticket4", "Ticket4Description", false);
		Ticket ticket5 = new Ticket("Ticket5", "Ticket5Description", true);

		category2.addTicket(ticket3);
		category2.addTicket(ticket4);
		category2.addTicket(ticket5);

		categories.add(category);
		categories.add(category1);
		categories.add(category2);

		try {
			GroupWriter.writeGroupFile(testFile1, group.getGroupName(), categories);
		} catch (IllegalArgumentException e) {
			fail("Unable to save file.");
		}
	}

}
