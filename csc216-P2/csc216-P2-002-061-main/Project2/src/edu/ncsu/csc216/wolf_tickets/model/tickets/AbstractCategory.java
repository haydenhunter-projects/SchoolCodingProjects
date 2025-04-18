package edu.ncsu.csc216.wolf_tickets.model.tickets;

import edu.ncsu.csc216.wolf_tickets.model.util.ISwapList;
import edu.ncsu.csc216.wolf_tickets.model.util.SwapList;

/**
 * The AbstractCategory class is an abstract class at the top of the hierarchy
 * for categories. The AbstractCategory knows its categoryName, the ISwapList of
 * Tickets, and the number of completed tickets.
 *
 * @author hchunter
 */
public abstract class AbstractCategory {

	/**
	 * stores the name of a category
	 */
	private String categoryName;

	/**
	 * stores the number of completed tickets in a category
	 */
	private int completedCount;

	/**
	 * stores a list of tickets in a category
	 */
	private ISwapList<Ticket> tickets;

	/**
	 * Sets the fields from the parameters and constructs a SwapList for the
	 * Tickets. An IAE is thrown with the message “Invalid name.” if the
	 * categoryName is null or empty string. An IAE is thrown with the message
	 * “Invalid completed count.” if the completedCount is less than zero.
	 *
	 * @param categoryName   the name of a category
	 * @param completedCount the number of tickets completed in the category
	 * @throws IllegalArgumentException if completed count is less than 0 with
	 *                                  message invalid completed count.
	 */
	public AbstractCategory(String categoryName, int completedCount) {
		setCategoryName(categoryName);
		if (completedCount < 0) {
			throw new IllegalArgumentException("Invalid completed count.");
		}
		this.completedCount = completedCount;

		tickets = new SwapList<>();
	}

	/**
	 * returns the name of a category
	 *
	 * @return the category name
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * sets the name of the category
	 *
	 * @param categoryName the name of the category
	 * @throws IllegalArgumentException if category name is null or an empty string
	 *                                  with message invalid name.
	 */
	public void setCategoryName(String categoryName) {
		if (categoryName == null || "".equals(categoryName)) {
			throw new IllegalArgumentException("Invalid name.");
		}

		this.categoryName = categoryName;
	}

	/**
	 * Returns a list of tickets in a category sorted by priority.
	 *
	 * @return a SwapList of tickets of type Ticket
	 */
	public ISwapList<Ticket> getTickets() {
		return tickets;
	}

	/**
	 * returns the number of completed tickets in a category
	 *
	 * @return the number of completed tickets
	 */
	public int getCompletedCount() {
		return completedCount;
	}

	/**
	 * Adds the Ticket to the end of the list. The current instance of the Category
	 * adds itself to the Ticket.
	 *
	 * @param t the ticket we wish to add to a category
	 */
	public void addTicket(Ticket t) {

		tickets.add(t);
		t.addCategory(this);
	}

	/**
	 * Removes the Ticket from the list of tickets and returns the removed ticket.
	 *
	 * @param idx the index of the ticket we wish to remove
	 * @return the removed ticket
	 */
	public Ticket removeTicket(int idx) {
		return tickets.remove(idx);
	}

	/**
	 * Returns the Ticket at the given index.
	 *
	 * @param idx the index of the ticket we wish to retrieve
	 * @return the ticket at the index
	 */
	public Ticket getTicket(int idx) {
		return tickets.get(idx);
	}

	/**
	 * Finds the given Ticket in the list and removes it. The completedCount is
	 * incremented. To compare Tickets use ==! In this case, we want to compare that
	 * the Tickets are the same object.
	 *
	 * @param t the ticket we wish to set as completed
	 * @throws IllegalArgumentException if the ticket is null, with message
	 *                                  incomplete ticket information
	 */
	public void completeTicket(Ticket t) {
		if (t == null) {
			throw new IllegalArgumentException("Incomplete ticket information.");
		}

		for (int i = 0; i < tickets.size(); i++) {
			if (tickets.get(i).getTicketName() == t.getTicketName()
					&& tickets.get(i).getTicketDescription() == t.getTicketDescription()) {
				tickets.remove(i);
				completedCount++;
			}
		}

	}

	/**
	 * An abstract method that returns a 2D String array. The contents of the array
	 * are left for the child classes to define.
	 *
	 * @return String array of tickets
	 */
	public abstract String[][] getTicketsAsArray();

}
