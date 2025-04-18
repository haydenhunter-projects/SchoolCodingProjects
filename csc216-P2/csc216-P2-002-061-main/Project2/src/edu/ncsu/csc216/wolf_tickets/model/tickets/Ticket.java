package edu.ncsu.csc216.wolf_tickets.model.tickets;

import edu.ncsu.csc216.wolf_tickets.model.util.ISwapList;
import edu.ncsu.csc216.wolf_tickets.model.util.SwapList;

/**
 * The Ticket class contains the information about each individual ticket
 * including the ticketName, ticketDescription, and if the ticket is active. The
 * Ticket additionally contains an ISwapList of AbstractCategorys called
 * categories.
 *
 * @author hchunter
 */
public class Ticket {

	/**
	 * stores the name of a ticket
	 */
	private String ticketName;

	/**
	 * stores the description of a ticket
	 */
	private String ticketDescription;

	/**
	 * stores true if the ticket is active, false if the ticket is not active
	 */
	private boolean active;

	/**
	 * The categories field means that the Ticket knows which AbstractCategorys that
	 * the Ticket belongs to.
	 */
	private ISwapList<AbstractCategory> categories;

	/**
	 * Constructs the Ticket with the given parameters. The categories field is
	 * constructed to an empty SwapList of AbstractCategorys.
	 *
	 * @param ticketName        the name of a ticket
	 * @param ticketDescription the description of a ticket
	 * @param active            true or false if the ticket is active or not
	 */
	public Ticket(String ticketName, String ticketDescription, boolean active) {
		setTicketName(ticketName);
		setTicketDescription(ticketDescription);
		setActive(active);
		categories = new SwapList<>();
	}

	/**
	 * returns the name of a ticket
	 *
	 * @return the ticket name
	 */
	public String getTicketName() {
		return ticketName;
	}

	/**
	 * Public helper method that checks for a valid ticket name and throws an IAE if
	 * null or empty string. The exception message is Incomplete ticket information.
	 *
	 * @param ticketName the name of a ticket
	 * @throws IllegalArgumentException if ticketName is null or an empty string,
	 *                                  with message incomplete ticket information
	 */
	public void setTicketName(String ticketName) {
		if (ticketName == null || "".equals(ticketName)) {
			throw new IllegalArgumentException("Incomplete ticket information.");
		}
		this.ticketName = ticketName;
	}

	/**
	 * returns the description of a ticket
	 *
	 * @return the ticket description
	 */
	public String getTicketDescription() {
		return ticketDescription;
	}

	/**
	 * Public helper method that checks for a valid ticket description and throws an
	 * IAE if null. The exception message is Incomplete ticket information.
	 *
	 * @param ticketDescription the ticket description
	 * @throws IllegalArgumentException if the ticket description is null, with
	 *                                  message incomplete ticket information
	 */
	public void setTicketDescription(String ticketDescription) {
		if (ticketDescription == null) {
			throw new IllegalArgumentException("Incomplete ticket information.");
		}

		this.ticketDescription = ticketDescription;
	}

	/**
	 * Public helper method that sets active
	 *
	 * @param active if a ticket is active or not
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * returns true if the ticket is active, false if the ticket is not active
	 *
	 * @return active true or false
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * Returns the name of the AbstractCategory at index 0. If the categories field
	 * is null or empty, an empty string is returned. There could be multiple
	 * AbstractCategorys in the categories field; if so, the name of the first
	 * AbstractCategory is returned.
	 *
	 * @return the category name the ticket belongs too
	 */
	public String getCategoryName() {
		if (categories == null || categories.size() == 0) {
			return "";
		} else {
			return categories.get(0).getCategoryName();
		}
	}

	/**
	 * If the AbstractCategory is NOT already registered with the Ticket the
	 * AbstractCategory is added to the end of the categories field. If the
	 * parameter is null an IAE is thrown with message “Incomplete ticket
	 * information.”
	 *
	 * @param category the category we want to register our ticket with
	 * @throws IllegalArgumentException if category is null, with message Incomplete
	 *                                  ticket information.
	 */
	public void addCategory(AbstractCategory category) {

		if (category == null) {
			throw new IllegalArgumentException("Incomplete ticket information.");
		}

		if (this.getCategoryName() != category.getCategoryName()) {
			categories.add(category);
		}

	}

	/**
	 * This method will complete the Ticket and notify the categories by sharing the
	 * current Ticket instance via the Category.completeTicket(Ticket) method.
	 */
	public void completeTicket() {
		setActive(false);
		int size = categories.size();
		for (int i = 0; i < size; i++) {
			categories.get(i).completeTicket(this);
		}
	}

	/**
	 * Returns a string representation of the Ticket for printing to a file. See the
	 * requirements for expectations on what the output should look like.
	 *
	 * @return a ticket object in string representation
	 */
	@Override
	public String toString() {
		String activePrint = "";
		if (isActive()) {
			activePrint = "active";
			return "* " + getTicketName() + "," + activePrint + "\n" + getTicketDescription() + "\n";
		} else {
			return "* " + getTicketName() + "\n" + getTicketDescription() + "\n";
		}
	}

}
