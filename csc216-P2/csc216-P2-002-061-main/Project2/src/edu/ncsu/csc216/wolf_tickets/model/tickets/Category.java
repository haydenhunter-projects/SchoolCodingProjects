package edu.ncsu.csc216.wolf_tickets.model.tickets;

import edu.ncsu.csc216.wolf_tickets.model.util.ISwapList;

/**
 * Extends AbstractCategory and implements the Comparable interface.
 *
 * @author hchunter
 */
public class Category extends AbstractCategory implements Comparable<Category> {

	/**
	 * Constructs a category
	 *
	 * @param categoryName   the name of the category
	 * @param completedCount the number of completed tickets in the category
	 */
	public Category(String categoryName, int completedCount) {
		super(categoryName, completedCount);
	}

	/**
	 * Returns a 2D String array where the first column is the priority of the
	 * Ticket, which is the index of the ticket in the list of tickets, and the name
	 * of the Ticket.
	 *
	 * @return A string array of tickets
	 */
	@Override
	public String[][] getTicketsAsArray() {

		ISwapList<Ticket> tickets = this.getTickets();
		String[][] ticketArray = new String[tickets.size()][2];

		for (int i = 0; i < tickets.size(); i++) {
			for (int j = 0; j < 2; j++) {
				ticketArray[i][j] = Integer.toString(i);
				j++;
				ticketArray[i][j] = tickets.get(i).getTicketName();
			}
		}

		return ticketArray;
	}

	/**
	 * Compares the names of the Category's. Use the case insensitive comparison.
	 *
	 * @param otherCategory the category we wish to compare with
	 * @return an int that checks if two category's are the same
	 */
	@Override
	public int compareTo(Category otherCategory) {
		return this.getCategoryName().compareTo(otherCategory.getCategoryName());
	}

}
