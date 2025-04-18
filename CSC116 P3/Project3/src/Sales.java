import java.util.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.*;
import java.io.*;

/**
 * Sales is a program to help a small store manage their inventory.
 * The program will read in the inventory from a file, and allow the user to
 * list items,
 * search for items, and make purchases, which will decrease the item
 * quantities.
 * When the user decides to quit, the updated inventory will be output to a
 * file.
 * 
 * @author Hayden Hunter
 */
public class Sales {
    
    /**
     * allows the program to check if the ITEM ID is breaking the boundary of 6
     * characters
     */
    static final int BOUNDARY_ITEMID = 6;

    /**
     * Returns the number of lines in the file
     * 
     * @param in "scanner"
     * @Throws an IllegalArgumentException with the message
     *         "Null file" if in is null
     * @return countLine,
     */
    public static int getNumberOfLines(Scanner in) {
        int countLine = 0;
        if (in == null) {
            throw new IllegalArgumentException("Null file");
        }
        try {
            while (in.hasNextLine()) {
                in.nextLine();
                countLine++;
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Null file");
        }

        return countLine;

    }

    /**
     * Reads each line of the input file, and stores the item
     * number, name, quantity
     * in the appropriate array parameter
     * 
     * @param in             "input scanner"
     * 
     * @param itemIds        String array that stores itemIds as an input from a csv
     *                       file
     * @param itemNames      String array that stores itemNames as an input from a
     *                       csv file
     * @param itemQuantities Int array that stores itemQuantities as an input from a
     *                       csv file
     * 
     * @return inputSuccess if successful, and
     *         false, if a line of the input file
     *         (a) does not contain exactly three values as described above,
     *         (b) the quantity for an item is not an integer that is greater than
     *         or equal
     *         to 0,
     *         (c) or two or more items have the same id
     *
     * @throws IllegalArgumentException with the message
     *                                  "Null file" if in is null
     *
     * @throws IllegalArgumentException with the message
     *                                  "Null array" if itemIds, itemNames, and/or
     *                                  itemQuantities are/is null
     *
     * @throws IllegalArgumentException with the message "Invalid array length"
     *                                  if
     *                                  (a) the lengths of itemIds, itemNames,
     *                                  and/or itemQuantities are not
     *                                  the same
     *                                  OR
     *                                  (b) if the number of lines in the file is
     *                                  not the same as the length
     *                                  of the
     *                                  arrays,
     *                                  which may be detected when reading the lines
     *                                  of the file
     * 
     */
    public static boolean inputInventory(Scanner in, String[] itemIds, String[] itemNames,
            int[] itemQuantities) {

        Boolean inputSuccess = false;

        int tryInt = 0;

        if (in == null) {
            throw new IllegalArgumentException("Null file");
        }

        if ((itemNames == null) || (itemIds == null) || (itemQuantities == null)) {
            throw new IllegalArgumentException("Null array");
        }

        if ((itemIds.length != itemNames.length) || (itemIds.length != itemQuantities.length)) {
            throw new IllegalArgumentException("Invalid array length");
        }
        int numLines = itemIds.length;
        if ((itemIds.length != numLines) || (itemNames.length != numLines) ||
                (itemQuantities.length != numLines)) {
            throw new IllegalArgumentException("Invalid array length");
        }

        String[] splitInput = null;
        String input = null;

        for (int i = 0; i < numLines; i++) {
            input = in.nextLine();
            splitInput = input.split(",");

            if (splitInput.length != 3) {
                return false;
            }

            itemIds[i] = splitInput[0];
            itemNames[i] = splitInput[1];

            try {
                Integer.parseInt(splitInput[2]);
            } catch (NumberFormatException e) {
                return false;
            }
            tryInt = Integer.parseInt(splitInput[2]);
            if (tryInt < 0) {
                return false;
            }
            itemQuantities[i] = tryInt;
        }

        for (int i = 0; i < numLines; i++) {
            for (int j = 0; j < numLines; j++) {
                if (i != j) {
                    if (itemIds[i].equals(itemIds[j])) {
                        return false;
                    } else {
                        inputSuccess = true;
                    }
                }
            }
        }

        return inputSuccess;

    }

    /**
     * getLit prints out a list of every current element stored in each of the 3
     * arrays
     * 
     * @param itemIds        String array that stores itemIds as an input from a csv
     *                       file
     * @param itemNames      String array that stores itemNames as an input from a
     *                       csv file
     * @param itemQuantities Int array that stores itemQuantities as an input from a
     *                       csv file
     * 
     * @return a String with each item id, name, and quantity formatted as shown
     *         above
     *         with a newline character after the String for each item.
     * 
     * @throws IllegalArgumentException with the message
     *                                  "Null array" if itemIds, itemNames, and/or
     *                                  itemQuantities are/is null
     *
     * @throws IllegalArgumentException with the message
     *                                  "Invalid array length" if the lengths of
     *                                  itemIds, itemNames, and
     *                                  itemQuantities arrays
     *                                  are not the same
     */
    public static String getList(String[] itemIds, String[] itemNames,
            int[] itemQuantities) {

        String temp = "";
        if ((itemIds == null) || (itemNames == null) || (itemQuantities == null)) {
            throw new IllegalArgumentException("Null array");
        }

        if ((itemIds.length != itemNames.length) || (itemIds.length != itemQuantities.length)) {
            throw new IllegalArgumentException("Invalid array length");
        }
        System.out.println("  ID" + "            Name" + "          Quantity");

        for (int i = 0; i < itemIds.length; i++) {
            String itemId = itemIds[i];
            String itemName = itemNames[i];
            int quantity = itemQuantities[i];
            temp = temp + toString(itemId, itemName, quantity) + "\n";
        }

        return temp;
    }

    /**
     * The searchByName method takes the input of an item name and
     * searches the itemNames array for matches
     * 
     * @param itemIds        String array that stores itemIds as an input from a csv
     *                       file
     * @param itemNames      String array that stores itemNames as an input from a
     *                       csv file
     * @param itemQuantities Int array that stores itemQuantities as an input from a
     *                       csv file
     * 
     * @param itemName       a string passed through too the method that contains
     *                       what the user is searching for in the array.
     * 
     * @return a String with each item whose name is/contains itemName, disregarding
     *         case,
     *         with the item id, name, and quantity formatted as shown above
     *         with a newline character after the String for each item.
     *
     *         If none of the items have a name that is or contains itemName,
     *         the empty String ("") is returned.
     *
     * @throws IllegalArgumentException with the message
     *                                  "Null array" if itemIds, itemNames, and/or
     *                                  itemQuantities are/is null
     *
     * @throws IllegalArgumentException with the message
     *                                  "Invalid array length" if the lengths of
     *                                  itemIds, itemNames, and
     *                                  itemQuantities arrays
     *                                  are not the same
     */
    public static String searchByName(String[] itemIds, String[] itemNames,
            int[] itemQuantities, String itemName) {

        if ((itemIds == null) || (itemNames == null) || (itemQuantities == null)) {
            throw new IllegalArgumentException("Null array");
        }

        if ((itemIds.length != itemNames.length) || (itemIds.length != itemQuantities.length)) {
            throw new IllegalArgumentException("Invalid array length");
        }

        int numLines = itemIds.length;
        String temp = "";
        String nameItem = "";
        String compare = "";
        String itemId = "";
        int quantity = 0;
        itemName = itemName.toLowerCase();
        for (int i = 0; i < numLines; i++) {
            itemId = itemIds[i];
            nameItem = itemNames[i];
            compare = itemNames[i].toLowerCase();
            quantity = itemQuantities[i];

            if (compare.contains(itemName)) {
                String list = toString(itemId, nameItem, quantity);
                temp = temp + list + "\n";
            }
        }
        return temp;

    }

    /**
     * Returns formatted string for item
     * DO NOT CHANGE METHOD HEADER OR METHOD CONTENTS!
     * 
     * @param itemId       String array that stores itemIds as an input from a csv
     *                     file in this case just one itemId
     * @param itemName     String array that stores itemNames as an input from a
     *                     csv file in this case just one itemName
     * @param itemQuantity Int array that stores itemQuantities as an input from a
     *                     csv file in this case just one itemQuantity
     * 
     * @return a formatted string with proper spacing of each line parsed from the
     *         csv file
     */
    public static String toString(String itemId, String itemName, int itemQuantity) {
        return String.format("%6s  %-25s %4d", itemId, itemName, itemQuantity);
    }

    /**
     * The method makePurchase allows the user to "purchase" an item from the
     * imported file
     * which allows them to choose a specific itemId and quantity and remove that
     * quantity from
     * the file, and output a new file with the updated numbers.
     * 
     * @param itemIds        String array that stores itemIds as an input from a csv
     *                       file
     * 
     * @param itemId         A string that is passed through allowing the user to
     *                       match an itemId in the csv file to the one they would
     *                       like to purchase
     * 
     * @param itemQuantities Int array that stores itemQuantities as an input from a
     *                       csv file
     * 
     * @param itemQuantity   a int passed through too the method that contains
     *                       how much the user would like to purchase of an itemId
     * 
     * @return If the item's quantity is < itemQuantity, "Insufficient quantity" is
     *         returned.
     *         else the item's quantity is decreased by itemQuantity and "Successful
     *         purchase" is returned.
     *
     * @throws IllegalArgumentException with the message
     *                                  "Null array" if itemIds or itemQuantities is
     *                                  null
     *
     * @throws IllegalArgumentException with the message
     *                                  "Invalid array length" if the lengths of
     *                                  itemIds and itemQuantities arrays
     *                                  are not the same
     *
     * @throws IllegalArgumentException with the message
     *                                  "Invalid item" if the itemId does not exist
     *
     * @throws IllegalArgumentException with the message
     *                                  "Invalid quantity" if itemQuantity is < 0
     *
     */
    public static String makePurchase(String[] itemIds, int[] itemQuantities, String itemId,
            int itemQuantity) {
        if ((itemIds == null) || itemQuantities == null) {
            throw new IllegalArgumentException("Null array");
        }
        if ((itemIds.length != itemQuantities.length)) {
            throw new IllegalArgumentException("Invalid array length");
        }
        if (itemId == null) {
            throw new IllegalArgumentException("Invalid item");
        }
        if (itemQuantity < 0) {
            throw new IllegalArgumentException("Invalid quantity");
        }

        int numLines = itemIds.length;
        String purchaseMessage = "Insufficient quantity";
        int buffer = 0;
        boolean purchaseSuccesful = false;
        for (int i = 0; i < numLines; i++) {
            if (itemIds[i].equals(itemId)) {
                if (itemQuantities[i] >= itemQuantity) {
                    buffer = itemQuantities[i];
                    itemQuantities[i] = buffer - itemQuantity;
                    purchaseMessage = "Successful purchase";
                }
                purchaseSuccesful = true;
            }

        }
        if (purchaseSuccesful == false) {
            throw new IllegalArgumentException("Invalid item");
        }

        return purchaseMessage;

    }

    /**
     * Outputs the inventory data to the file in CSV format as described above.
     *
     * @param out            Printwriter variable that allows to output an updated
     *                       file
     * @param itemIds        String array that stores csv files inputted itemIds
     * 
     * @param itemNames      String array that stores csv files inputted itemNames
     * 
     * @param itemQuantities int array that stores csv files inputted itemQuantities
     * 
     * @throws IllegalArgumentException with the message
     *                                  "Null file" if out is null
     *
     * @throws IllegalArgumentException with the message
     *                                  "Null array" if itemIds, itemNames, and/or
     *                                  itemQuantities are/is null
     *
     * @throws IllegalArgumentException with the message
     *                                  "Invalid array length" if the lengths of
     *                                  itemIds, itemNames, and
     *                                  itemQuantities arrays
     *                                  are not the same
     */
    public static void outputInventory(PrintWriter out, String[] itemIds, String[] itemNames,
            int[] itemQuantities) {

        if (out == null) {
            throw new IllegalArgumentException("Null file");
        }
        if ((itemIds == null) || (itemNames == null) || (itemQuantities == null)) {
            throw new IllegalArgumentException("Null array");
        }
        if ((itemIds.length != itemNames.length) || (itemIds.length != itemQuantities.length)) {
            throw new IllegalArgumentException("Invalid array length");
        }

        int numLines = itemIds.length;
        for (int i = 0; i < numLines; i++) {
            out.print(itemIds[i] + "," + itemNames[i] + "," + itemQuantities[i] + "\n");
        }

    }

    /**
     * This initialzes the UI of the program
     * giving the user options for how they would like
     * to navigate the program
     */
    public static void displayMenu() {
        System.out.println("Sales Program - Please choose an option." + "\n");
        System.out.println("L - List items");
        System.out.println("S - Search by item name");
        System.out.println("P - Purchase item");
        System.out.println("Q - Quit");
        System.out.print("\n");
        System.out.print("\n");
        System.out.print("Option: ");

    }

    /**
     * the main method runs the software
     * and checks args to make sure both an
     * infile and outfile were passed through
     * to allow the program to run
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String option = "";
        String filename = "";
        Boolean inputInventory = false;
        PrintWriter output = null;

        if (args.length != 2) {
            System.out.println("Usage: java -cp bin Sales infile outfile");
            return;
        }

        FileOutputStream fileOutput = null;
        try {
            File outputFile = new File(args[1]);

            if (outputFile.exists() == true) {

                System.out.print(args[1] + " exists - OK to overwrite (y,n): ");
                String overwrite = in.next();

                if (overwrite.equalsIgnoreCase("y") || (overwrite.equalsIgnoreCase("Yes"))) {
                    fileOutput = new FileOutputStream(outputFile);
                    output = new PrintWriter(fileOutput);
                } else {
                    System.exit(1);
                }
            } else {
                fileOutput = new FileOutputStream(outputFile);
                output = new PrintWriter(fileOutput);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Cannot create output file" + "\n");
            System.exit(1);
        }

        FileInputStream file = null;
        try {
            file = new FileInputStream(args[0]);
            in = new Scanner(file);

        } catch (FileNotFoundException e) {
            System.out.println("Unable to access input file: " + "\n" + args[0]);
            System.exit(1);
        }

        int numLines = getNumberOfLines(in);
        String[] itemIds = new String[numLines];
        String[] itemNames = new String[numLines];
        int[] itemQuantities = new int[numLines];
        int lineOutput = 0;
        int tryInt = 0;

        try {
            file = new FileInputStream(args[0]);
            in = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to access input file: " + "\n" + args[0]);
            System.exit(1);
        }

        while (in.hasNextLine()) {
            String input = in.nextLine();
            String[] splitInput = input.split(",");
            if ((splitInput.length > 3) || (splitInput.length < 3)) {
                System.out.println("Invalid input file");
                System.exit(1);
            }
            itemIds[lineOutput] = splitInput[0];
            itemNames[lineOutput] = splitInput[1];
            try {
                tryInt = Integer.parseInt(splitInput[2]);
                if (tryInt < 0) {
                    System.out.println("Invalid input file");
                    System.exit(1);
                }
                itemQuantities[lineOutput] = tryInt;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input file");
                System.exit(1);
            }
            lineOutput += 1;
        }
        for (int i = 0; i < numLines; i++) {
            for (int j = 0; j < numLines; j++) {
                if (i != j) {
                    if (itemIds[i].equals(itemIds[j])) {
                        System.out.println("Invalid input file");
                        System.exit(1);
                    }
                }
            }
        }

        boolean inputSuccess = false;
        in.close();
        in = new Scanner(System.in);
        int itemQuantity = 0;
        String[] acceptedChar = { "q", "l", "s", "p" };
        while (!option.equalsIgnoreCase("Q")) {

            displayMenu();
            option = in.next();
            System.out.print("\n");

            if (option.equalsIgnoreCase("L")) {

                System.out.println(getList(itemIds, itemNames, itemQuantities));

            } else if (option.equalsIgnoreCase("S")) {
                System.out.print("Item name (is/contains): ");
                String search = in.next();
                System.out.print("\n");
                System.out.println(searchByName(itemIds, itemNames, itemQuantities, search));

            } else if (option.equalsIgnoreCase("P")) {
                System.out.print("Item id: ");
                String itemId = in.next();
                if (itemId.length() > BOUNDARY_ITEMID) {
                    System.out.println("Invalid item");
                    System.exit(1);
                }
                System.out.print("Quantity: ");
                try {
                    itemQuantity = in.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid quantity");
                    System.exit(1);
                }
                System.out.println(makePurchase(itemIds, itemQuantities, itemId, itemQuantity));
                System.out.print("\n");

            } else if (!option.equalsIgnoreCase("Q")) {
                System.exit(1);
            }
        }
        outputInventory(output, itemIds, itemNames, itemQuantities);
        output.close();
    }

}
