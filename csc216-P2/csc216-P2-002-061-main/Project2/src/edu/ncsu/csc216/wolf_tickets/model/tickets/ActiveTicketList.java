package edu.ncsu.csc216.wolf_tickets.model.tickets;

import edu.ncsu.csc216.wolf_tickets.model.util.ISwapList;

/**
 * Extends AbstractCategory.
 *
 * @author hchunter
 */
public class ActiveTicketList extends AbstractCategory {

	/**
	 * Constant holding the name of the “Active Tickets” list.
	 */
	public static final String ACTIVE_TASKS_NAME = "Active Tickets";

	/**
	 * Constructs the ActiveTicketList with the expected name and no completed
	 * tickets.
	 */
	public ActiveTicketList() {
		super(ACTIVE_TASKS_NAME, 0);
	}

	/**
	 * Overrides the method to check that the Ticket is active before adding to the
	 * end of the ISwapList. If the Ticket is not active, an IAE is thrown with the
	 * message “Cannot add ticket to Active Tickets.”
	 *
	 * @param t the ticket we wish to add
	 * @throws IllegalArgumentException if the ticket being added is not active,
	 *                                  with message cannot add ticket to active
	 *                                  tickets.
	 *
	 */
	@Override
	public void addTicket(Ticket t) {

		if (t.isActive()) {
			ISwapList<Ticket> tickets = this.getTickets();
			tickets.add(t);
			t.addCategory(this);
		} else {
			throw new IllegalArgumentException("Cannot add ticket to Active Tickets.");
		}
	}

	/**
	 * Overrides the method to ensure that the parameter value matches the expected
	 * name of “Active Tickets”. If so, the name is set. If not, an IAE is thrown
	 * with the message “The Active Tickets list may not be edited.”
	 *
	 * @param categoryName the name of the category
	 * @throws IllegalArgumentException if category name is not active tickets, with
	 *                                  message the active tickets list may not be
	 *                                  edited.
	 */
	@Override
	public void setCategoryName(String categoryName) {
		if (categoryName.equals(ACTIVE_TASKS_NAME)) {
			super.setCategoryName(categoryName);
		} else {
			throw new IllegalArgumentException("The Active Tickets list may not be edited.");
		}
	}

	/**
	 * Returns a 2D String array where the first column is the name of the Category
	 * that the Ticket belongs to (or at least the Category at index 0) and the name
	 * of the Ticket.
	 *
	 * @return a string array of tickets
	 */
	@Override
	public String[][] getTicketsAsArray() {

		ISwapList<Ticket> tickets = this.getTickets();

		String[][] ticketArray = new String[tickets.size()][2];

		for (int i = 0; i < tickets.size(); i++) {
			for (int j = 0; j < 2; j++) {
				ticketArray[i][j] = tickets.get(i).getCategoryName();
				j++;
				ticketArray[i][j] = tickets.get(i).getTicketName();
			}
		}

		return ticketArray;
	}

	/**
	 * Clears the ActiveTicketList of all Tickets. You’ll need to do this using a
	 * for loop. You don’t have direct access to the AbstractCategory field.
	 */
	public void clearTickets() {
		ISwapList<Ticket> tickets = this.getTickets();

		for (int i = tickets.size() - 1; i > -1; i--) {
			tickets.remove(i);
		}
	}

}
