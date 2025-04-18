package edu.ncsu.csc216.wolf_tickets.model.io;

import java.io.File;
import java.io.PrintStream;

import edu.ncsu.csc216.wolf_tickets.model.tickets.Category;
import edu.ncsu.csc216.wolf_tickets.model.util.ISortedList;

/**
 * GroupWriter has one public method writeGroupToFile that receives a File with
 * the file name to write to, the name of the group, and a ISortedList of
 * Category's to write to file. GroupWriter should use Ticket’s toString()
 * method to create the properly formatted output for a Ticket. You’ll need to
 * call the appropriate methods in Group and Category to build up the output as
 * defined in the Data Format. If there are any errors or exceptions, an
 * IllegalArgumentException is thrown with the message “Unable to save file.”
 *
 * @author hchunter
 */
public class GroupWriter {

	/**
	 * writeGroupFile writes a group object containing categories to a file
	 *
	 * @param groupFile  the file we wish to write to
	 * @param groupName  the name of the group
	 * @param categories the categories in the group
	 * @throws IllegalArgumentException any problems occur during save, with message
	 *                                  unable to save file
	 */
	public static void writeGroupFile(File groupFile, String groupName, ISortedList<Category> categories) {

		try {
			PrintStream fileWriter;
			fileWriter = new PrintStream(groupFile);

			String output = "! " + groupName + "\n";

			for (int i = 0; i < categories.size(); i++) {
				output += "# " + categories.get(i).getCategoryName() + "," + categories.get(i).getCompletedCount()
						+ "\n";
				for (int j = 0; j < categories.get(i).getTickets().size(); j++) {
					output += categories.get(i).getTicket(j).toString();
				}
			}
			fileWriter.print(output);

			fileWriter.close();
		} catch (Exception e) {
			throw new IllegalArgumentException("Unable to save file");
		}
	}
}
