package edu.ncsu.csc216.wolf_tickets.model.group;

import java.io.File;

import edu.ncsu.csc216.wolf_tickets.model.io.GroupWriter;
import edu.ncsu.csc216.wolf_tickets.model.tickets.AbstractCategory;
import edu.ncsu.csc216.wolf_tickets.model.tickets.ActiveTicketList;
import edu.ncsu.csc216.wolf_tickets.model.tickets.Category;
import edu.ncsu.csc216.wolf_tickets.model.tickets.Ticket;
import edu.ncsu.csc216.wolf_tickets.model.util.ISortedList;
import edu.ncsu.csc216.wolf_tickets.model.util.ISwapList;
import edu.ncsu.csc216.wolf_tickets.model.util.SortedList;

/**
 * A Group has an ISortedList of Categorys, one ActiveTicketList, an
 * AbstractCategory for the currentCategory, a groupName, and boolean flag that
 * keeps track of if the Group has been changed since the last save.
 *
 * @author hchunter
 */
public class Group {

	/**
	 * stores the name of the group
	 */
	private String groupName;

	/**
	 * stores true if the group has been edited, stores false if the group has not
	 * been edited.
	 */
	private boolean isChanged;

	/**
	 * stores the current category selected
	 */
	private AbstractCategory currentCategory;

	/**
	 * stores a swap list of activeTickets
	 */
	private ActiveTicketList activeTicketList;

	/**
	 * stores a Sorted list of categories
	 */
	private ISortedList<Category> categories;

	/**
	 * Constructs a Group with the given name. The categories field is constructed
	 * as a SortedList and the activeTicketList is constructed as an empty
	 * ActiveTicketList. The currentCategory is set to the activeTicketList.
	 * isChanged is initialized to true. An IAE is thrown if the groupName is null,
	 * empty, or matches ACTIVE_TASKS_NAME.
	 *
	 * @param groupName the name of the group
	 * @throws IllegalArgumentException if groupName is null, an empty string, or
	 *                                  equal to Active Tickets.
	 */
	public Group(String groupName) {
		setGroupName(groupName);
		this.categories = new SortedList<>();
		this.activeTicketList = new ActiveTicketList();
		this.currentCategory = activeTicketList;
		setChanged(isChanged);
	}

	/**
	 * Saves the current Group to the given file. isChanged is updated to false.
	 *
	 * @param groupFile the file we wish to save the group to
	 */
	public void saveGroup(File groupFile) {
		GroupWriter.writeGroupFile(groupFile, this.getGroupName(), categories);
		isChanged = false;

	}

	/**
	 * gets the name of the group
	 *
	 * @return groupName the name of the group
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * sets the name of the group
	 *
	 * @param groupName the name of the group
	 * @throws IllegalArgumentException if groupName parameter is null, an empty
	 *                                  string, or equal to Active Tickets, with
	 *                                  message invalid name.
	 */
	private void setGroupName(String groupName) {
		if ("Active Tickets".equals(groupName) || "".equals(groupName) || groupName == null) {
			throw new IllegalArgumentException("Invalid name.");
		}
		this.groupName = groupName;
	}

	/**
	 * returns true if the group has been edited, stores false if the group has not
	 * been edited.
	 *
	 * @return true or false if the group has been changed or not
	 */
	public boolean isChanged() {
		return isChanged;
	}

	/**
	 * sets true if the group has been edited, stores false if the group has not
	 * been edited.
	 *
	 * @param isChanged a boolean stating true or false for if the group has been
	 *                  changed or not
	 */
	public void setChanged(boolean isChanged) {
		this.isChanged = true;
	}

	/**
	 * If the new Category’s name is ACTIVE_TASKS_NAME or a duplicate of an existing
	 * Category (both case insensitive), then an IAE is thrown with message “Invalid
	 * name.”. Otherwise, the Category is added to the list of categories (the
	 * SortedList will ensure sorted order), the current category is updated to the
	 * new category and isChanged is updated to true.
	 *
	 * @param category the category we wish to add
	 * @throws IAE if the passed through category has the name Active Tasks. if the
	 *             passed through category is a duplicate of an existing category in
	 *             the list, with message invalid name.
	 */
	public void addCategory(Category category) {
		if ("Active Tickets".equals(category.getCategoryName())) {
			throw new IllegalArgumentException("Invalid name.");
		}
		for (int i = 0; i < categories.size(); i++) {
			if (categories.get(i).getCategoryName().equals(category.getCategoryName())) {
				throw new IllegalArgumentException("Invalid name.");
			}
		}

		categories.add(category);
		currentCategory = category;
		isChanged = true;

	}

	/**
	 * returns a string array of category names for the GUI
	 *
	 * @return A string array of category names
	 */
	public String[] getCategoriesNames() {
		String[] display = new String[categories.size() + 1];
		display[0] = activeTicketList.getCategoryName();

		for (int i = 1; i < categories.size() + 1; i++) {
			display[i] = categories.get(i - 1).getCategoryName();
		}

		return display;
	}

	/**
	 * A private helper method that is useful for working with the ActiveTicketList.
	 * The order that Tickets are stored in the ActiveTicket list is related to the
	 * order of the Categorys and then the order of the active Tickets in those
	 * Categorys. Maintaining that order via the ActiveTicketList.add() method can
	 * be a bit tricky. Instead, build the ActiveTicketList each time there’s a
	 * change by iterating through all the Categories (in sorted order) and add each
	 * active Ticket (in order of priority). Make sure you clear the
	 * ActiveTicketList first!
	 */
	private void getActiveTicketList() {
		activeTicketList.clearTickets();

		for (int i = 0; i < categories.size(); i++) {
			ISwapList<Ticket> tickets = categories.get(i).getTickets();

			for (int j = 0; j < tickets.size(); j++) {
				if (tickets.get(j).isActive()) {
					activeTicketList.addTicket(tickets.get(j));
				}
			}
		}

	}

	/**
	 * Sets the currentCategory to the AbstractCategory with the given name. If a
	 * Category with that name is not found, then the currentCategory is set to the
	 * activeTicketList. If you set the currentCategory to the activeTicketList,
	 * make sure to call getActiveTicketList() first to ensure that everything is up
	 * to date!
	 *
	 * @param categoryName the name of the current category
	 */
	public void setCurrentCategory(String categoryName) {

		for (int i = 0; i < categories.size(); i++) {

			if (categories.get(i).getCategoryName().equals(categoryName)) {
				currentCategory = categories.get(i);
				break;
			} else {
				getActiveTicketList();
				currentCategory = activeTicketList;
			}
		}

	}

	/**
	 * public helper method to return the current category selected
	 *
	 * @return currentCategory the currently selected category in the GUI
	 */
	public AbstractCategory getCurrentCategory() {
		return currentCategory;
	}

	/**
	 * An IAE is thrown if the currentCategory is an ActiveTicketList, if the new
	 * name matches “Active Tickets” (case insensitive), or is a duplicate of the
	 * name of another Category (case insensitive and including if the name is the
	 * same as the list that will be renamed). Note that when editing, you are
	 * editing the currentCategory and you cannot edit the Category in place in the
	 * ISortedList. You must maintain sorted order. This means that you should
	 * remove the currentCategory, edit it, and then add it back to the categories
	 * field. isChanged is updated to true.
	 *
	 * @param categoryName the name of the category we wish to edit
	 * @throws IllegalArgumentException if currentCategory is the active ticket
	 *                                  list, with message the active tickets list
	 *                                  may not be edited. if Active Tickets is the
	 *                                  category name passed through, with message
	 *                                  invalid name. if the categoryName already
	 *                                  exists in the list of categories, with
	 *                                  message invalid name.
	 *
	 */
	public void editCategory(String categoryName) {

		if (currentCategory == activeTicketList) {
			throw new IllegalArgumentException("The Active Tickets list may not be edited.");
		}

		if ("Active Tickets".equals(categoryName)) {
			throw new IllegalArgumentException("Invalid name.");
		}

		for (int i = 0; i < categories.size(); i++) {
			if (!categories.get(i).getCategoryName().equals(categoryName)) {
				if (currentCategory == categories.get(i)) {
					int completedCount = currentCategory.getCompletedCount();
					categories.remove(i);
					categories.add(new Category(categoryName, completedCount));
					setCurrentCategory(categoryName);
					isChanged = true;
				}
			} else {
				throw new IllegalArgumentException("Invalid name.");
			}
		}

	}

	/**
	 * An IAE is thrown if the currentCategory is an ActiveTicketList with the
	 * message “The Active Tickets list may not be deleted.”. Otherwise, the
	 * currentCategory is removed and then set to the activeTicketList. isChanged is
	 * updated to true.
	 */
	public void removeCategory() {

		if (currentCategory == activeTicketList) {
			throw new IllegalArgumentException("The Active Tickets list may not be deleted.");
		} else {
			for (int i = 0; i < categories.size(); i++) {
				if (currentCategory.getCategoryName() == categories.get(i).getCategoryName()) {
					categories.remove(i);
				}
			}
			currentCategory = null;
			currentCategory = activeTicketList;
			isChanged = true;
		}
	}

	/**
	 * A Ticket can only be added directly to a Category. If the currentCategory is
	 * not a Category do nothing with the Ticket. If the currentCategory is a
	 * Category, then add the ticket and check if the Ticket is active. If so, then
	 * you can update the activeTicketList. This is a place where the helper method
	 * getActiveTicketList() can be helpful. isChanged is updated to true.
	 *
	 * @param t the ticket we wish to add
	 */
	public void addTicket(Ticket t) {

		if (currentCategory != null && currentCategory != activeTicketList) {
			currentCategory.addTicket(t);
			isChanged = true;
		} else if (t.isActive()) {
			activeTicketList.addTicket(t);
			getActiveTicketList();
			isChanged = true;
		}
	}

	/**
	 * A Ticket can only be edited if the currentCategory is a Category; otherwise,
	 * do nothing. If the Ticket can be edited, update the fields of the Ticket at
	 * the specified index. Check if the Ticket is active. If so, then you can
	 * update the activeTicketList. This is a place where the helper method
	 * getActiveTicketList() can be helpful. isChanged is updated to true.
	 *
	 * @param idx               the index of the ticket we wish to edit
	 * @param ticketName        the name of the ticket
	 * @param ticketDescription the description of the ticket
	 * @param active            a boolean stating if the ticket is active or not
	 */
	public void editTicket(int idx, String ticketName, String ticketDescription, boolean active) {

		if (currentCategory instanceof Category && currentCategory != activeTicketList) {
			currentCategory.getTicket(idx).setTicketName(ticketName);
			currentCategory.getTicket(idx).setTicketDescription(ticketDescription);
			currentCategory.getTicket(idx).setActive(active);
		}
		getActiveTicketList();

		isChanged = true;

	}

}
