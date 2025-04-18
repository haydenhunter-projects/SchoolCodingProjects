package edu.ncsu.csc216.wolf_tickets.model.io;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

import edu.ncsu.csc216.wolf_tickets.model.group.Group;
import edu.ncsu.csc216.wolf_tickets.model.tickets.ActiveTicketList;
import edu.ncsu.csc216.wolf_tickets.model.tickets.Category;
import edu.ncsu.csc216.wolf_tickets.model.tickets.Ticket;

/**
 * GroupReader has one public method readGroupFile that receives a File with the
 * file name to read from. If the file cannot be loaded because it doesn’t
 * exist, the method will throw an IllegalArgumentException with the message
 * “Unable to load file.” Any invalid categories or tickets (i.e., they cannot
 * be constructed or information is missing) are ignored.
 *
 * @author hchunter
 */
public class GroupReader {

	/**
	 * receives a File with the file name to read from. If the file cannot be loaded
	 * because it doesn’t exist, the method will throw an IllegalArgumentException
	 * with the message “Unable to load file.” Any invalid categories or tickets
	 * (i.e., they cannot be constructed or information is missing) are ignored.
	 *
	 * @param file the file we wish to read contents from
	 * @throws IllegalArgumentException if unable to load file
	 * @return a group object
	 */
	public static Group readGroupFile(File file) {
		Scanner fileReader;
		try {
			// will try to find file to read from
			fileReader = new Scanner(new FileInputStream(file));
		} catch (Exception e) {
			// throws IAE if cannot load file
			throw new IllegalArgumentException("Unable to load " + file);
		}

		String oneBigLine = "";

		while (fileReader.hasNextLine()) {
			oneBigLine += fileReader.nextLine() + "\n";
		}

		Scanner readBigLine = new Scanner(oneBigLine);

		Group g = null;
		Category c = null;

		String groupLine = readBigLine.nextLine();

		// this functions correctly
		if (!groupLine.startsWith("!")) {
			readBigLine.close();
			throw new IllegalArgumentException("Unable to load file.");
		} else {
			groupLine = groupLine.substring(1).trim();
			g = new Group(groupLine);
		}

		while (readBigLine.hasNext()) {
			// removes #
			readBigLine.useDelimiter("\\r?\\n?[#]");

			// stores every token leading to the next #
			String categoryLine = readBigLine.next();

			categoryLine = categoryLine.substring(1).trim();

			c = processCategory(categoryLine);
			if (c != null) {
				g.addCategory(c);
			}
		}

		g.setCurrentCategory(ActiveTicketList.ACTIVE_TASKS_NAME);
		g.setChanged(false);

		readBigLine.close();
		return g;
	}

	/**
	 * process category objects
	 *
	 * @param categoryText the lines of text in the file that contains a category
	 *                     object
	 * @return a category object or null if cant create category
	 */
	private static Category processCategory(String categoryText) {

		// scanner for category and ticket
		Scanner scan = new Scanner(categoryText);

		// store the category line as a string
		String categoryLine = scan.nextLine();

		// scanner for category line
		Scanner scanCategory = new Scanner(categoryLine);

		// separate tokens by commas
		scanCategory.useDelimiter(",");

		// initialize variables in try catch
		String categoryName = null;
		int completedTickets = 0;

		// create the category
		try {
			categoryName = scanCategory.next();
			completedTickets = Integer.parseInt(scanCategory.next());
			Category category = new Category(categoryName, completedTickets);

			// create the ticket
			scan.useDelimiter("\\r?\\n?[*]");

			// add all the following tickets to the category
			while (scan.hasNext()) {
				String ticketLine = scan.next();

				// only adds not null tickets
				Ticket t = processTicket(ticketLine);
				if (t != null) {
					category.addTicket(t);
				}

			}
			scanCategory.close();
			scan.close();
			return category;
		} catch (Exception e) {
			// do not create the object
		}
		scanCategory.close();
		scan.close();
		return null;

	}

	/**
	 * process ticket objects
	 *
	 * @param ticketText the lines of text in the file that contains a ticket object
	 * @return a ticket object or null if cant create ticket
	 */
	private static Ticket processTicket(String ticketText) {

		// scanner for ticket
		Scanner scan = new Scanner(ticketText);

		String ticketTitle = scan.nextLine();

		ticketTitle = ticketTitle.substring(1);

		// scanner for title
		Scanner scanTicket = new Scanner(ticketTitle);

		// make sure it has a name
		if (ticketTitle.startsWith(",")) {
			scan.close();
			scanTicket.close();
			return null;
		}

		// separate tokens by commas
		scanTicket.useDelimiter(",");

		String ticketName = null;
		String ticketDescription = null;
		boolean active = false;

		try {
			ticketName = scanTicket.next();
			active = false;

			if (scanTicket.hasNext() && "active".equals(scanTicket.next())) {
				// if string = active set boolean to true otherwise it will be false
				active = true;
			}

			if (scanTicket.hasNext()) {
				ticketDescription = scan.nextLine();
			} else {
				ticketDescription = "";
			}

			Ticket ticket = new Ticket(ticketName, ticketDescription, active);

			scan.close();
			scanTicket.close();
			return ticket;
		} catch (Exception e) {
			// do not create the object
			return null;
		}
	}

}
