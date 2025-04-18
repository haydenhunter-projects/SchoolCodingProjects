import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Program to test Sales methods
 *
 * @author Suzanne Balik
 * @author Michelle Glatz
 * @author Hayden Hunter
 */
public class SalesTest {

    /**
     * initializes test String[] ITEM_IDS
     */
    public static final String[] ITEM_IDS = { "PE0195", "NP1800", "P19076", "PM1889", "DE8079" };

    /**
     * initializes test String[] ITEM_NAMES
     */
    public static final String[] ITEM_NAMES = { "pencil", "notepad", "black pen",
        "black permanent marker", "red dry erase marker" };

    /**
     * initializes test int[] ITEM_QUANTITIES
     */
    public static final int[] ITEM_QUANTITIES = { 34, 4, 35, 12, 13 };

    /**
     * initializes test String[] UPDATED_ITEM_IDS
     */
    public static final String[] UPDATED_ITEM_IDS = { "PE0195", "MP8917", "DP8768", "NP1800",
        "P19076", "P90557", "PM1889", "DE6785",
        "DE8079", "BS3045", "PC8866", "PC9876",
        "T91180", "MT4987" };

    /**
     * initializes test String[] UPDATED_ITEM_NAMES
     */
    public static final String[] UPDATED_ITEM_NAMES = { "pencil", "mechanical pencil",
        "drawing pencils", "notepad", "black pen",
        "blue pen", "black permanent marker",
        "black dry erase marker",
        "red dry erase marker", "box of staples",
        "small paper clips", "large paper clips",
        "tape", "masking tape" };

    /**
     * initializes test int[] UPDATED_ITEM_QUANTITIES
     */
    public static final int[] UPDATED_ITEM_QUANTITIES = { 34, 8, 5, 4, 35, 16, 12, 8,
        13, 15, 23, 12, 20, 45 };

    /**
     * initializes test String[] ONE_ITEMID_ARRAY
     */
    public static final String[] ONE_ITEMID_ARRAY = { "BOWL23" };

    /**
     * initializes test String[] ONE_ITEMNAME_ARRAY
     */
    public static final String[] ONE_ITEMNAME_ARRAY = { "bowling ball blue" };

    /**
     * initializes test int[] ONE_QUANTITY_ARRAY
     */
    public static final int[] ONE_QUANTITY_ARRAY = { 69 };

    /**
     * Test getList
     */
    @Test
    public void testGetList1() {
        String expected = "PE0195  pencil                      34\n"
                + "NP1800  notepad                      4\n"
                + "P19076  black pen                   35\n"
                + "PM1889  black permanent marker      12\n"
                + "DE8079  red dry erase marker        13\n";

        String actual = Sales.getList(ITEM_IDS, ITEM_NAMES, ITEM_QUANTITIES);
        assertEquals(expected, actual, "Sales.getList(ITEM_IDS, ITEM_NAMES, ITEM_QUANTITIES)");

        // Check that Sales.getList does not modify arrays
        String[] ids = { "PE0195", "NP1800", "P19076", "PM1889", "DE8079" };
        String[] names = { "pencil", "notepad", "black pen", "black permanent marker",
            "red dry erase marker" };
        int[] quantities = { 34, 4, 35, 12, 13 };
        assertArrayEquals(ids, ITEM_IDS,
            "ITEM_IDS modified in Sales.getList when it shouldn't be");
        assertArrayEquals(names, ITEM_NAMES,
            "ITEM_NAMES modified in Sales.getList when it shouldn't be");
        assertArrayEquals(quantities, ITEM_QUANTITIES,
            "ITEM_QUANTITIES modified in Sales.getList when it shouldn't be");
    }

    /**
     * Test getList no items
     */
    @Test
    public void testGetList2() {
        String expected = "";

        int[] emptyInt = new int[0];
        String[] empty = new String[0];
        String actual = Sales.getList(empty, empty, emptyInt);
        assertEquals(expected, actual, "Sales.getList(empty, empty, emptyInt)");

        // Check that Sales.getList does not modify arrays
        int[] emptyIntCOPY = new int[0];
        String[] emptyCOPY = new String[0];
        assertArrayEquals(emptyIntCOPY, emptyInt,
            "emptyInt modified in Sales.getList when it shouldn't be");
        assertArrayEquals(emptyCOPY, empty,
            "empty modified in Sales.getList when it shouldn't be");
    }

    /**
     * Test getList updated items
     */
    @Test
    public void testGetList3() {
        String expected = 
                "PE0195  pencil                      34\n" +
                "MP8917  mechanical pencil            8\n" +
                "DP8768  drawing pencils              5\n" +
                "NP1800  notepad                      4\n" +
                "P19076  black pen                   35\n" +
                "P90557  blue pen                    16\n" +
                "PM1889  black permanent marker      12\n" +
                "DE6785  black dry erase marker       8\n" +
                "DE8079  red dry erase marker        13\n" +
                "BS3045  box of staples              15\n" +
                "PC8866  small paper clips           23\n" +
                "PC9876  large paper clips           12\n" +
                "T91180  tape                        20\n" +
                "MT4987  masking tape                45\n";

        String actual = Sales.getList(UPDATED_ITEM_IDS, 
            UPDATED_ITEM_NAMES, UPDATED_ITEM_QUANTITIES);
        assertEquals(expected, 
            actual, "Sales.getList(UPDATED_ITEM_IDS, UPDATED_ITEM_NAMES, UPDATED_ITEM_QUANTITIES)");

        // Check that Sales.getList does not modify arrays
        String[] ids = { "PE0195", "MP8917", "DP8768", "NP1800",
            "P19076", "P90557", "PM1889", "DE6785",
            "DE8079", "BS3045", "PC8866", "PC9876",
            "T91180", "MT4987" };
        String[] names = { "pencil", "mechanical pencil",
            "drawing pencils", "notepad", "black pen",
            "blue pen", "black permanent marker",
            "black dry erase marker",
            "red dry erase marker", "box of staples",
            "small paper clips", "large paper clips",
            "tape", "masking tape" };
        int[] quantities = { 34, 8, 5, 4, 35, 16, 12, 8,
            13, 15, 23, 12, 20, 45 };
        assertArrayEquals(ids, UPDATED_ITEM_IDS,
            "UPDATED_ITEM_IDS modified in Sales.getList when it shouldn't be");
        assertArrayEquals(names, UPDATED_ITEM_NAMES,
            "UPDATED_ITEM_NAMES modified in Sales.getList when it shouldn't be");
        assertArrayEquals(quantities, UPDATED_ITEM_QUANTITIES,
            "UPDATED_ITEM_QUANTITIES modified in Sales.getList when it shouldn't be");
    }
    
    /**
     * Test getList one item
     */
    @Test
    public void testGetList4() {
        String expected = "BOWL23  bowling ball blue           69\n";

        String actual = Sales.getList(ONE_ITEMID_ARRAY, ONE_ITEMNAME_ARRAY, ONE_QUANTITY_ARRAY);

        assertEquals(expected, actual, 
            "Sales.getList(ONE_ITEMID_ARRAY, ONE_ITEMNAME_ARRAY, ONE_QUANTITY_ARRAY)");

        // Check that Sales.getList does not modify arrays
        String[] ids = { "BOWL23" };
        String[] names = { "bowling ball blue" };
        int[] quantities = { 69 };
        assertArrayEquals(ids, ONE_ITEMID_ARRAY,
                "ONE_ITEMID_ARRAY modified in Sales.getList when it shouldn't be");
        assertArrayEquals(names, ONE_ITEMNAME_ARRAY,
                "ONE_ITEMNAME_ARRAY modified in Sales.getList when it shouldn't be");
        assertArrayEquals(quantities, ONE_QUANTITY_ARRAY,
                "ONE_QUANTITY_ARRAY modified in Sales.getList when it shouldn't be");

    }

    /**
     * Test searchByName multiple matching items
     */

    @Test
    public void testSearchByName1() {
        String expected = "PM1889  black permanent marker      12\n"
                + "DE8079  red dry erase marker        13\n";

        String actual = Sales.searchByName(ITEM_IDS, ITEM_NAMES, ITEM_QUANTITIES, "marker");
        assertEquals(expected, actual,
                "Sales.searchByName(ITEM_IDS, ITEM_NAMES, ITEM_QUANTITIES, \"marker\")");
        // Check that Sales.searchByName does not modify arrays
        String[] ids = { "PE0195", "NP1800", "P19076", "PM1889", "DE8079" };
        String[] names = { "pencil", "notepad", "black pen", "black permanent marker",
            "red dry erase marker" };
        int[] quantities = { 34, 4, 35, 12, 13 };
        assertArrayEquals(ids, ITEM_IDS,
            "ITEM_IDS modified in Sales.searchByName when it shouldn't be");
        assertArrayEquals(names, ITEM_NAMES,
            "ITEM_NAMES modified in Sales.searchByName when it shouldn't be");
        assertArrayEquals(quantities, ITEM_QUANTITIES,
            "ITEM_QUANTITIES modified in Sales.searchByName when it shouldn't be");
    }

    /**
     * Test searchByName no items match
     */

    @Test
    public void testSearchByName2() {
        String expected = "";
        String actual = Sales.searchByName(ITEM_IDS, ITEM_NAMES, ITEM_QUANTITIES, "paper");
        assertEquals(expected, actual,
            "Sales.searchByName(ITEM_IDS, ITEM_NAMES, ITEM_QUANTITIES, \"paper\")");

        // Check that Sales.searchByName does not modify arrays
        String[] ids = { "PE0195", "NP1800", "P19076", "PM1889", "DE8079" };
        String[] names = { "pencil", "notepad", "black pen", "black permanent marker",
            "red dry erase marker" };
        int[] quantities = { 34, 4, 35, 12, 13 };
        assertArrayEquals(ids, ITEM_IDS,
            "ITEM_IDS modified in Sales.searchByName when it shouldn't be");
        assertArrayEquals(names, ITEM_NAMES,
            "ITEM_NAMES modified in Sales.searchByName when it shouldn't be");
        assertArrayEquals(quantities, ITEM_QUANTITIES,
            "ITEM_QUANTITIES modified in Sales.searchByName when it shouldn't be");
    }

    /**
     * Test searchByName two token search string
     */
    @Test
    public void testSearchByName3() {
        String expected = "P19076  black pen                   35\n"
                + "PM1889  black permanent marker      12\n";
        String actual = Sales.searchByName(ITEM_IDS, ITEM_NAMES, ITEM_QUANTITIES, "ck pe");
        assertEquals(expected, actual,
                "Sales.searchByName(ITEM_IDS, ITEM_NAMES, ITEM_QUANTITIES, \"black pen\")");

        // Check that Sales.searchByName does not modify arrays
        String[] ids = { "PE0195", "NP1800", "P19076", "PM1889", "DE8079" };
        String[] names = { "pencil", "notepad", "black pen", "black permanent marker",
            "red dry erase marker" };
        int[] quantities = { 34, 4, 35, 12, 13 };
        assertArrayEquals(ids, ITEM_IDS,
            "ITEM_IDS modified in Sales.searchByName when it shouldn't be");
        assertArrayEquals(names, ITEM_NAMES,
            "ITEM_NAMES modified in Sales.searchByName when it shouldn't be");
        assertArrayEquals(quantities, ITEM_QUANTITIES,
            "ITEM_QUANTITIES modified in Sales.searchByName when it shouldn't be");
    }

    /**
    * Test searchByName to find one matching item 
    */
    @Test
    public void testSearchByName4() {
        String expected = "BOWL23  bowling ball blue           69\n";
        String actual = Sales.searchByName(ONE_ITEMID_ARRAY, 
            ONE_ITEMNAME_ARRAY, ONE_QUANTITY_ARRAY, "BOWL");
        assertEquals(expected, actual,
                "Sales.searchByName(ITEMID,ITEMNAME,QUANTITY, \"bowling ball blue\")");

        // Check that Sales.searchByName does not modify arrays
        String[] ids = { "BOWL23"};
        String[] names = { "bowling ball blue" };
        int[] quantities = {69};
        assertArrayEquals(ids, ONE_ITEMID_ARRAY,
            "ONE_ITEMID_ARRAY modified in Sales.searchByName when it shouldn't be");
        assertArrayEquals(names, ONE_ITEMNAME_ARRAY,
            "ONE_ITEMNAME_ARRAY modified in Sales.searchByName when it shouldn't be");
        assertArrayEquals(quantities, ONE_QUANTITY_ARRAY,
            "ONE_QUANTITY_ARRAY modified in Sales.searchByName when it shouldn't be");
    }

    /**
    * Test searchByName to find two matching item 
    */
    @Test
    public void testSearchByName5() {
        String expected = 
                "T91180  tape                        20\n" +
                "MT4987  masking tape                45\n";

        String actual = Sales.searchByName(UPDATED_ITEM_IDS, 
            UPDATED_ITEM_NAMES, UPDATED_ITEM_QUANTITIES, "tape");
        assertEquals(expected, actual,
                "Sales.searchByName(ITEMID,ITEMNAME,QUANTITY, \"tape\", \"masking tape\")");

        // Check that Sales.searchByName does not modify arrays
        String[] ids = { "PE0195", "MP8917", "DP8768", "NP1800",
            "P19076", "P90557", "PM1889", "DE6785",
            "DE8079", "BS3045", "PC8866", "PC9876",
            "T91180", "MT4987" };
        String[] names = { "pencil", "mechanical pencil",
            "drawing pencils", "notepad", "black pen",
            "blue pen", "black permanent marker",
            "black dry erase marker",
            "red dry erase marker", "box of staples",
            "small paper clips", "large paper clips",
            "tape", "masking tape" };
        int[] quantities = { 34, 8, 5, 4, 35, 16, 12, 8,
            13, 15, 23, 12, 20, 45 };
        assertArrayEquals(ids, UPDATED_ITEM_IDS,
            "UPDATED_ITEM_IDS modified in Sales.searchByName when it shouldn't be");
        assertArrayEquals(names, UPDATED_ITEM_NAMES,
            "UPDATED_ITEM_NAMES modified in Sales.searchByName when it shouldn't be");
        assertArrayEquals(quantities, UPDATED_ITEM_QUANTITIES,
            "UPDATED_ITEM_QUANTITIES modified in Sales.searchByName when it shouldn't be");
    }

    /**
     * Test makePurchase of in stock item
     */

    @Test
    public void testMakePurchase1() {

        String[] itemIDs = { "PE0195", "NP1800", "P19076", "PM1889", "DE8079" };
        int[] itemQuantities = { 34, 4, 35, 12, 13 };
        int[] expectedQuantities = { 22, 4, 35, 12, 13 };
        assertEquals("Successful purchase",
            Sales.makePurchase(itemIDs, itemQuantities, "PE0195", 12),
            "Purchase 12 of the 34 pencils");
        assertArrayEquals(expectedQuantities, itemQuantities,
            "Sales.makePurchase(itemIDs, itemQuantities, \"PE0195\", 12))");

        // Check that Sales.makePurchase does not modify array
        String[] ids = { "PE0195", "NP1800", "P19076", "PM1889", "DE8079" };
        assertArrayEquals(ids, itemIDs,
            "itemIDs modified in Sales.searchByName when it shouldn't be");
    }

    /**
     * Test makePurchase of item with insufficient quantity in stock
     */
    @Test
    public void testMakePurchase2() {

        String[] itemIDs = { "PE0195", "NP1800", "P19076", "PM1889", "DE8079" };
        int[] itemQuantities = { 34, 4, 35, 12, 13 };
        int[] expectedQuantities = { 34, 4, 35, 12, 13 };
        assertEquals("Insufficient quantity",
            Sales.makePurchase(itemIDs, itemQuantities, "NP1800", 5),
            "Purchase item with insufficient quantity in stock");
        assertArrayEquals(expectedQuantities, itemQuantities,
            "Sales.makePurchase(itemIDs, itemQuantities, \"NP1800\", 5))");

        // Check that Sales.makePurchase does not modify array
        String[] ids = { "PE0195", "NP1800", "P19076", "PM1889", "DE8079" };
        assertArrayEquals(ids, itemIDs,
            "itemIDs modified in Sales.searchByName when it shouldn't be");
    }

    /**
     * Test makePurchase of item with 0 quantity while fully in stock
     */
    @Test
    public void testMakePurchase3() {

        String[] itemIDs = { "BOWL23" };
        int[] itemQuantities = { 69 };
        int[] expectedQuantities = { 69 };
        assertEquals("Successful purchase",
            Sales.makePurchase(itemIDs, itemQuantities, "BOWL23", 0),
            "Purchase no item with sufficient quantity in stock");
        assertArrayEquals(expectedQuantities, itemQuantities,
            "Sales.makePurchase(itemIDs, itemQuantities, \"BOWL23\", 0))");

        // Check that Sales.makePurchase does not modify array
        String[] ids = { "BOWL23" };
        assertArrayEquals(ids, itemIDs,
            "itemIDs modified in Sales.searchByName when it shouldn't be");
    }

    /**
     * Test makePurchase of item with all quantity in stock
     */
    @Test
    public void testMakePurchase4() {

        String[] itemIDs = { "PE0195", "MP8917", "DP8768", "NP1800",
            "P19076", "P90557", "PM1889", "DE6785",
            "DE8079", "BS3045", "PC8866", "PC9876",
            "T91180", "MT4987" };
        int[] itemQuantities = { 34, 8, 5, 4, 35, 16, 12, 8,
            13, 15, 23, 12, 20, 45 };
        int[] expectedQuantities = { 34, 8, 5, 4, 35, 16, 12, 8,
            13, 15, 23, 12, 20, 0 };
        assertEquals("Successful purchase",
            Sales.makePurchase(itemIDs, itemQuantities, "MT4987", 45),
            "Purchase item with all quantity in stock");
        assertArrayEquals(expectedQuantities, itemQuantities,
            "Sales.makePurchase(itemIDs, itemQuantities, \"MT4987\", 45))");

        // Check that Sales.makePurchase does not modify array
        String[] ids = { "PE0195", "MP8917", "DP8768", "NP1800",
            "P19076", "P90557", "PM1889", "DE6785",
            "DE8079", "BS3045", "PC8866", "PC9876",
            "T91180", "MT4987" };
        assertArrayEquals(ids, itemIDs,
            "itemIDs modified in Sales.searchByName when it shouldn't be");
    }

    /**
     * Test the Sales methods with invalid values
     */

    @Test
    public void testInvalidMethods() {

        // Invalid test cases are provided for you below - You do NOT
        // need to add additional invalid tests. Just make sure these
        // pass!

        String[] itemIDs = { "PE0195", "NP1800", "P19076", "PM1889", "DE8079" };
        int[] itemQuantities = { 34, 4, 35, 12, 13 };
        String[] itemIDsLengthBad = { "", "", "", "" };
        String[] itemNamesLengthBad = { "", "", "", "", "", "" };
        int[] itemQuantitiesLengthBad = new int[2];

        String[] itemIDsCopy = { "PE0195", "NP1800", "P19076", "PM1889", "DE8079" };
        int[] itemQuantitiesCopy = { 34, 4, 35, 12, 13 };
        String[] itemIDsLengthBadCopy = { "", "", "", "" };
        String[] itemNamesLengthBadCopy = { "", "", "", "", "", "" };
        int[] itemQuantitiesLengthBadCopy = new int[2];
        String[] namesCopy = { "pencil", "notepad", 
            "black pen", "black permanent marker",
            "red dry erase marker" };

        // Testing null IDs
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> Sales.makePurchase(null, itemQuantities, "NP1800", 4),
            "Sales.makePurchase(null, itemQuantities, \"NP1800\", 4)");
        assertEquals("Null array", exception.getMessage(),
            "Testing Sales.makePurchase(null, itemQuantities, "
            + "\"NP1800\", 4) - exception message");
        assertArrayEquals(itemQuantitiesCopy, itemQuantities,
            "Array modified in Sales.makePurchase when it shouldn't be");

        // Testing null quantities
        exception = assertThrows(IllegalArgumentException.class,
            () -> Sales.makePurchase(itemIDs, null, "NP1800", 4),
            "Sales.makePurchase(itemIDs, null, \"NP1800\", 4)");
        assertEquals("Null array", exception.getMessage(),
            "Testing Sales.makePurchase(itemIDs, null,"
            + "\"NP1800,\", 4) - exception message");
        assertArrayEquals(itemIDsCopy, itemIDs,
            "Array modified in Sales.makePurchase when it shouldn't be");

        // Testing invalid length
        exception = assertThrows(IllegalArgumentException.class,
            () -> Sales.makePurchase(itemIDsLengthBad, itemQuantities, "NP1800", 4),
            "Sales.makePurchase(itemIDsLengthBad, itemQuantities, \"NP1800\", 4)");
        assertEquals("Invalid array length", exception.getMessage(),
            "Testing Sales.makePurchase(itemIDsLengthBad, itemQuantities,"
            + "\"NP1800\", 4) - exception message");
        assertArrayEquals(itemQuantitiesCopy, itemQuantities,
            "Array modified in Sales.makePurchase when it shouldn't be");
        assertArrayEquals(itemIDsLengthBadCopy, itemIDsLengthBad,
            "Array modified in Sales.makePurchase when it shouldn't be");

        exception = assertThrows(IllegalArgumentException.class,
            () -> Sales.makePurchase(itemIDs, itemQuantitiesLengthBad, "NP1800", 4),
            "Sales.makePurchase(itemIDs, itemQuantitiesLengthBad, \"NP1800\", 4)");
        assertEquals("Invalid array length", exception.getMessage(),
            "Testing Sales.makePurchase(itemIDs, itemQuantitiesLengthBad,"
            + "\"NP1800\", 4) - exception message");
        assertArrayEquals(itemIDsCopy, itemIDs,
            "Array modified in Sales.makePurchase when it shouldn't be");
        assertArrayEquals(itemQuantitiesLengthBadCopy, itemQuantitiesLengthBad,
            "Array modified in Sales.makePurchase when it shouldn't be");

        // Testing invalid quantity
        exception = assertThrows(IllegalArgumentException.class,
            () -> Sales.makePurchase(itemIDs, itemQuantities, "NP1800", -4),
            "Sales.makePurchase(itemIDs, itemQuantities, \"NP1800\", -4)");
        assertEquals("Invalid quantity", exception.getMessage(),
            "Testing Sales.makePurchase(itemIDs, itemQuantities,"
            + "\"NP1800\", -4) - exception message");
        assertArrayEquals(itemIDsCopy, itemIDs,
            "Array modified in Sales.makePurchase when it shouldn't be");
        assertArrayEquals(itemQuantitiesCopy, itemQuantities,
            "Array modified in Sales.makePurchase when it shouldn't be");

        // Testing invalid item
        exception = assertThrows(IllegalArgumentException.class,
            () -> Sales.makePurchase(itemIDs, itemQuantities, "MT4987", 2),
            "Sales.makePurchase(itemIDs, itemQuantities, \"MT4987\", 2)");
        assertEquals("Invalid item", exception.getMessage(),
            "Testing Sales.makePurchase(itemIDs, itemQuantities,"
            + "\"MT4987\", 2) - exception message");
        assertArrayEquals(itemIDsCopy, itemIDs,
            "Array modified in Sales.makePurchase when it shouldn't be");
        assertArrayEquals(itemQuantitiesCopy, itemQuantities,
            "Array modified in Sales.makePurchase when it shouldn't be");

        // Testing null id array
        exception = assertThrows(IllegalArgumentException.class,
            () -> Sales.getList(null, ITEM_NAMES, ITEM_QUANTITIES),
            "Sales.getList(null, ITEM_NAMES, ITEM_QUANTITIES)");
        assertEquals("Null array", exception.getMessage(),
            "Testing Sales.getList(null,ITEM_NAMES," + "ITEM_QUANTITIES) - exception message");
        assertArrayEquals(namesCopy, ITEM_NAMES,
            "Array modified in Sales.getList when it shouldn't be");
        assertArrayEquals(itemQuantitiesCopy, ITEM_QUANTITIES,
            "Array modified in Sales.getList when it shouldn't be");

        // Test null array
        exception = assertThrows(IllegalArgumentException.class,
            () -> Sales.searchByName(null, ITEM_NAMES, ITEM_QUANTITIES, "black pen"),
            "Sales.searchByName(null,ITEM_NAMES, ITEM_QUANTITIES, \"black pen\")");
        assertEquals("Null array", exception.getMessage(),
            "Testing Sales.searchByName(null,ITEM_NAMES, ITEM_QUANTITIES, "
            + "\"black pen\") - exception message");
        assertArrayEquals(namesCopy, ITEM_NAMES,
            "Array modified in Sales.searchByName when it shouldn't be");
        assertArrayEquals(itemQuantitiesCopy, ITEM_QUANTITIES,
             "Array modified in Sales.searchByName when it shouldn't be");

        // Test null array
        exception = assertThrows(IllegalArgumentException.class,
            () -> Sales.getList(ITEM_IDS, null, ITEM_QUANTITIES),
            "Sales.getList(ITEM_IDS, null, ITEM_QUANTITIES)");
        assertEquals("Null array", exception.getMessage(),
            "Testing Sales.getList(ITEM_IDS, null, ITEM_QUANTITIES) - exception message");
        assertArrayEquals(itemIDsCopy, ITEM_IDS,
            "Array modified in Sales.getList when it shouldn't be");
        assertArrayEquals(itemQuantitiesCopy, ITEM_QUANTITIES,
            "Array modified in Sales.getList when it shouldn't be");

        // Test null array
        exception = assertThrows(IllegalArgumentException.class,
            () -> Sales.searchByName(ITEM_IDS, null, ITEM_QUANTITIES, "black pen"),
            "Sales.searchByName(ITEM_IDS, null, ITEM_QUANTITIES, \"black pen\")");
        assertEquals("Null array", exception.getMessage(),
            "Testing Sales.searchByName(ITEM_IDS, null, ITEM_QUANTITIES, "
            + "\"black pen\") - exception message");
        assertArrayEquals(itemIDsCopy, ITEM_IDS,
            "Array modified in Sales.searchByName when it shouldn't be");
        assertArrayEquals(itemQuantitiesCopy, ITEM_QUANTITIES,
            "Array modified in Sales.searchByName when it shouldn't be");

        // Test null array
        exception = assertThrows(IllegalArgumentException.class,
            () -> Sales.getList(ITEM_IDS, ITEM_NAMES, null),
            "Sales.getList(ITEM_IDS, ITEM_NAMES, null)");
        assertEquals("Null array", exception.getMessage(),
            "Testing Sales.getList(ITEM_IDS, ITEM_NAMES, null) - exception message");
        assertArrayEquals(itemIDsCopy, ITEM_IDS,
            "Array modified in Sales.getList when it shouldn't be");
        assertArrayEquals(namesCopy, ITEM_NAMES,
            "Array modified in Sales.getList when it shouldn't be");

        // Test null array
        exception = assertThrows(IllegalArgumentException.class,
            () -> Sales.searchByName(ITEM_IDS, ITEM_NAMES, null, "black pen"),
            "Sales.searchByName(ITEM_IDS, ITEM_NAMES, null, \"black pen\")");
        assertEquals("Null array", exception.getMessage(),
            "Testing Sales.searchByName(ITEM_IDS, ITEM_NAMES, null, "
            + "\"black pen\") - exception message");
        assertArrayEquals(itemIDsCopy, ITEM_IDS,
            "Array modified in Sales.searchByName when it shouldn't be");
        assertArrayEquals(namesCopy, ITEM_NAMES,
            "Array modified in Sales.searchByName when it shouldn't be");

        // Test invalid length
        exception = assertThrows(IllegalArgumentException.class,
            () -> Sales.getList(itemIDsLengthBad, ITEM_NAMES, ITEM_QUANTITIES),
            "Sales.getList(itemIDsLengthBad, ITEM_NAMES, ITEM_QUANTITIES)");
        assertEquals("Invalid array length", exception.getMessage(),
            "Testing Sales.getList(itemIDsLengthBad, ITEM_NAMES, "
            + "ITEM_QUANTITIES) - exception message");
        assertArrayEquals(namesCopy, ITEM_NAMES,
            "Array modified in Sales.getList when it shouldn't be");
        assertArrayEquals(itemQuantitiesCopy, ITEM_QUANTITIES,
            "Array modified in Sales.getList when it shouldn't be");

        // Test invalid length
        exception = assertThrows(IllegalArgumentException.class,
            () -> Sales.searchByName(itemIDsLengthBad, ITEM_NAMES, ITEM_QUANTITIES,
            "black pen"),
            "Sales.searchByName(itemIDsLengthBad, ITEM_NAMES, ITEM_QUANTITIES, \"black pen\")");
        assertEquals("Invalid array length", exception.getMessage(),
            "Testing Sales.searchByName(itemIDsLengthBad, ITEM_NAMES, ITEM_QUANTITIES, "
            + "\"black pen\") - exception message");
        assertArrayEquals(namesCopy, ITEM_NAMES,
            "Array modified in Sales.searchByName when it shouldn't be");
        assertArrayEquals(itemQuantitiesCopy, ITEM_QUANTITIES,
            "Array modified in Sales.searchByName when it shouldn't be");

        // Test invalid length
        exception = assertThrows(IllegalArgumentException.class,
            () -> Sales.getList(ITEM_IDS, itemNamesLengthBad, ITEM_QUANTITIES),
            "Sales.getList(ITEM_IDS, itemNamesLengthBad, ITEM_QUANTITIES)");
        assertEquals("Invalid array length", exception.getMessage(),
            "Testing Sales.getList(ITEM_IDS, itemNamesLengthBad, "
            + "ITEM_QUANTITIES) - exception message");
        assertArrayEquals(itemIDsCopy, ITEM_IDS,
            "Array modified in Sales.getList when it shouldn't be");
        assertArrayEquals(itemQuantitiesCopy, ITEM_QUANTITIES,
            "Array modified in Sales.getList when it shouldn't be");
        assertArrayEquals(itemNamesLengthBadCopy, itemNamesLengthBad,
            "Array modified in Sales.getList when it shouldn't be");

        // Test invalid length
        exception = assertThrows(IllegalArgumentException.class,
            () -> Sales.searchByName(ITEM_IDS, itemNamesLengthBad, ITEM_QUANTITIES,
            "black pen"),
            "Sales.searchByName(ITEM_IDS, itemNamesLengthBad, ITEM_QUANTITIES, \"black pen\")");
        assertEquals("Invalid array length", exception.getMessage(),
            "Testing Sales.searchByName(ITEM_IDS, itemNamesLengthBad, ITEM_QUANTITIES, "
            + "\"black pen\") - exception message");
        assertArrayEquals(itemIDsCopy, ITEM_IDS,
            "Array modified in Sales.searchByName when it shouldn't be");
        assertArrayEquals(itemQuantitiesCopy, ITEM_QUANTITIES,
            "Array modified in Sales.searchByName when it shouldn't be");
        assertArrayEquals(itemNamesLengthBadCopy, itemNamesLengthBad,
            "Array modified in Sales.searchByName when it shouldn't be");

        // Test invalid length
        exception = assertThrows(IllegalArgumentException.class,
            () -> Sales.getList(ITEM_IDS, ITEM_NAMES, itemQuantitiesLengthBad),
            "Sales.getList(ITEM_IDS, ITEM_NAMES, itemQuantitiesLengthBad)");
        assertEquals("Invalid array length", exception.getMessage(),
            "Testing Sales.getList(ITEM_IDS, ITEM_NAMES, "
            + "itemQuantitiesLengthBad) - exception message");
        assertArrayEquals(itemIDsCopy, ITEM_IDS,
            "Array modified in Sales.getList when it shouldn't be");
        assertArrayEquals(namesCopy, ITEM_NAMES,
            "Array modified in Sales.getList when it shouldn't be");
        assertArrayEquals(itemQuantitiesLengthBadCopy, itemQuantitiesLengthBad,
            "Array modified in Sales.getList when it shouldn't be");

        // Test invalid length
        exception = assertThrows(IllegalArgumentException.class,
            () -> Sales.searchByName(ITEM_IDS, ITEM_NAMES, itemQuantitiesLengthBad,
           "black pen"),
            "Sales.searchByName(ITEM_IDS, ITEM_NAMES, itemQuantitiesLengthBad, \"black pen\")");
        assertEquals("Invalid array length", exception.getMessage(),
            "Testing Sales.searchByName(ITEM_IDS, ITEM_NAMES, itemQuantitiesLengthBad, "
            + "\"black pen\") - exception message");
        assertArrayEquals(itemIDsCopy, ITEM_IDS,
            "Array modified in Sales.searchByName when it shouldn't be");
        assertArrayEquals(namesCopy, ITEM_NAMES,
            "Array modified in Sales.searchByName when it shouldn't be");
        assertArrayEquals(itemQuantitiesLengthBadCopy, itemQuantitiesLengthBad,
            "Array modified in Sales.searchByName when it shouldn't be");

    }

    /**
     * Tests inputInventory
     */
    @Test
    public void testInputInventory() {

        // You do NOT need to add additional inputInventory tests. Just make sure these
        // pass!

        String[] expItemIDs = { "PEO195", "NP1800", "P19076", "PM1889", "DE8079" };
        String[] expItemNames = { "pencil", "notepad", "black pen", "black permanent marker",
            "red dry erase marker" };
        int[] expItemQuantities = { 34, 4, 35, 12, 13 };
        String[] itemIDs = { "", "", "", "", "" };
        String[] itemNames = { "", "", "", "", "" };
        int[] itemQuantities = new int[5];
        String[] itemIDsLengthBad = { "", "", "", "" };
        String[] itemNamesLengthBad = { "", "", "", "", "", "" };
        int[] itemQuantitiesLengthBad = new int[2];
        String simulatedFile = "PEO195,pencil,34\n" + "NP1800,notepad,4\n"
                + "P19076,black pen,35\n" + "PM1889,black permanent marker,12\n"
                + "DE8079,red dry erase marker,13\n";
        String simulatedFileE1 = "\n" + "NP1800,notepad,4\n" + "P19076,black pen,35\n"
                + "PM1889,black permanent marker,12\n" + "DE8079,red dry erase marker,13\n";
        String simulatedFileE2 = "PEO195,pencil,none\n" + "NP1800,notepad,4\n"
                + "P19076,black pen,35\n" + "PM1889,black permanent marker,12\n"
                + "DE8079,red dry erase marker,13\n";
        String simulatedFileE3 = "PEO195,pencil,34\n" + "NP1800,notepad,4\n"
                + "P19076,black pen,-35\n" + "PM1889,black permanent marker,12\n"
                + "DE8079,red dry erase marker,13\n";
        String simulatedFileE4 = "PEO195,pencil,34\n" + "NP1800,notepad,4\n"
                + "P19076,black pen,35\n" + "PM1889,black permanent marker,12\n"
                + "P19076,red dry erase marker,13\n";
        String simulatedFileE5 = "PEO195,pencil,34\n" + "NP1800,notepad,4\n"
                + "P19076,black pen,35\n" + "PM1889,black permanent marker,12\n"
                + "DE8079,red dry erase marker,13, extra\n";
        assertTrue(Sales.inputInventory(new Scanner(simulatedFile), itemIDs, itemNames,
                itemQuantities), "test Sales.inputInventory() method with valid inputs");
        assertArrayEquals(expItemIDs, itemIDs,
                "itemIDs correct after Sales.inputInventory(new Scanner(simulatedFile),"
                        + "itemIDs, itemNames,  itemQuantities");
        assertArrayEquals(expItemNames, itemNames,
                "itemNames correct after Sales.inputInventory(new Scanner(simulatedFile),"
                        + " itemIDs, itemNames, itemQuantities");
        assertArrayEquals(expItemQuantities, itemQuantities,
                "itemQuantities correct after Sales.inputInventory(new Scanner(simulatedFile),"
                        + "itemIDs, itemNames, itemQuantities");

        String[] itemIDs2 = { "", "", "", "", "" };
        String[] itemNames2 = { "", "", "", "", "" };
        int[] itemQuantities2 = new int[5];

        assertFalse(
                Sales.inputInventory(new Scanner(simulatedFileE1), itemIDs2, itemNames2,
                        itemQuantities2),
                "test Sales.inputInventory() method with too few tokens on a line");

        assertFalse(
                Sales.inputInventory(new Scanner(simulatedFileE2), itemIDs2, itemNames2,
                        itemQuantities2),
                "test Sales.inputInventory() method with non-integer quantity");

        assertFalse(
                Sales.inputInventory(new Scanner(simulatedFileE3), itemIDs2, itemNames2,
                        itemQuantities2),
                "test Sales.inputInventory() method with negative quantity");

        assertFalse(
                Sales.inputInventory(new Scanner(simulatedFileE4), itemIDs2, itemNames2,
                        itemQuantities2),
                "test Sales.inputInventory() method with duplicate items id");

        assertFalse(
                Sales.inputInventory(new Scanner(simulatedFileE5), itemIDs2, itemNames2,
                        itemQuantities2),
                "test Sales.inputInventory() method with too many tokens on a line");

        String[] itemIDsLengthBadCopy = { "", "", "", "" };
        String[] itemNamesLengthBadCopy = { "", "", "", "", "", "" };
        int[] itemQuantitiesLengthBadCopy = new int[2];
        String[] itemIDsCopy = { "", "", "", "", "" };
        String[] itemNamesCopy = { "", "", "", "", "" };
        int[] itemQuantitiesCopy = new int[5];

        String[] itemIDs3 = { "", "", "", "", "" };
        String[] itemNames3 = { "", "", "", "", "" };
        int[] itemQuantities3 = new int[5];

        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> Sales.inputInventory(null, itemIDs3, itemNames3, itemQuantities3),
            "Sales.inputInventory(null, itemIDs, itemNames, itemQuantities)");
        assertEquals("Null file", exception.getMessage(),
                "Testing Sales.inputInventory(null, itemIDs, itemNames,"
                        + "itemQuantities) - exception message");
        assertArrayEquals(itemIDsLengthBadCopy, itemIDsLengthBad,
                "Modified array when invalid input: inputInventory");
        assertArrayEquals(itemNamesLengthBadCopy, itemNamesLengthBad,
                "Modified array when invalid input: inputInventory");
        assertArrayEquals(itemQuantitiesLengthBadCopy, itemQuantitiesLengthBad,
                "Modified array when invalid input: inputInventory");
        assertArrayEquals(itemIDsCopy, itemIDs3,
                "itemIDs correct after Sales.inputInventory(new Scanner(simulatedFile),"
                        + "itemIDs, itemNames,  itemQuantities");
        assertArrayEquals(itemNamesCopy, itemNames3,
                "itemNames correct after Sales.inputInventory(new Scanner(simulatedFile),"
                        + " itemIDs, itemNames, itemQuantities");
        assertArrayEquals(itemQuantitiesCopy, itemQuantities3,
                "itemQuantities correct after Sales.inputInventory(new Scanner(simulatedFile),"
                        + "itemIDs, itemNames, itemQuantities");

        exception = assertThrows(IllegalArgumentException.class,
            () -> Sales.inputInventory(new Scanner(simulatedFile), null, itemNames,
                        itemQuantities),
                "Sales.inputInventory(new Scanner(simulatedFile),null, itemNames, itemQuantities)");
        assertEquals("Null array", exception.getMessage(),
                "Testing Sales.inputInventory(new Scanner(simulatedFile), null, itemNames, "
                        + "itemQuantities) - exception message");
        assertArrayEquals(itemIDsLengthBadCopy, itemIDsLengthBad,
                "Modified array when invalid input: inputInventory");
        assertArrayEquals(itemNamesLengthBadCopy, itemNamesLengthBad,
                "Modified array when invalid input: inputInventory");
        assertArrayEquals(itemQuantitiesLengthBadCopy, itemQuantitiesLengthBad,
                "Modified array when invalid input: inputInventory");
        assertArrayEquals(itemIDsCopy, itemIDs3,
                "itemIDs correct after Sales.inputInventory(new Scanner(simulatedFile),"
                        + "itemIDs, itemNames,  itemQuantities");
        assertArrayEquals(itemNamesCopy, itemNames3,
                "itemNames correct after Sales.inputInventory(new Scanner(simulatedFile),"
                        + " itemIDs, itemNames, itemQuantities");
        assertArrayEquals(itemQuantitiesCopy, itemQuantities3,
                "itemQuantities correct after Sales.inputInventory(new Scanner(simulatedFile),"
                        + "itemIDs, itemNames, itemQuantities");

        exception = assertThrows(IllegalArgumentException.class,
            () -> Sales.inputInventory(new Scanner(simulatedFile), itemIDs, null,
                        itemQuantities),
                "Sales.inputInventory(new Scanner(simulatedFile) itemIDs, null, itemQuantities)");
        assertEquals("Null array", exception.getMessage(),
                "Testing Sales.inputInventory(new Scanner(simulatedFile), itemIDs, null, "
                        + "itemQuantities) - exception message");
        assertArrayEquals(itemIDsLengthBadCopy, itemIDsLengthBad,
                "Modified array when invalid input: inputInventory");
        assertArrayEquals(itemNamesLengthBadCopy, itemNamesLengthBad,
                "Modified array when invalid input: inputInventory");
        assertArrayEquals(itemQuantitiesLengthBadCopy, itemQuantitiesLengthBad,
                "Modified array when invalid input: inputInventory");
        assertArrayEquals(itemIDsCopy, itemIDs3,
                "itemIDs correct after Sales.inputInventory(new Scanner(simulatedFile),"
                        + "itemIDs, itemNames,  itemQuantities");
        assertArrayEquals(itemNamesCopy, itemNames3,
                "itemNames correct after Sales.inputInventory(new Scanner(simulatedFile),"
                        + " itemIDs, itemNames, itemQuantities");
        assertArrayEquals(itemQuantitiesCopy, itemQuantities3,
                "itemQuantities correct after Sales.inputInventory(new Scanner(simulatedFile),"
                        + "itemIDs, itemNames, itemQuantities");

        exception = assertThrows(IllegalArgumentException.class,
            () -> Sales.inputInventory(new Scanner(simulatedFile), itemIDs, itemNames, null),
                "Sales.inputInventory(new Scanner(simulatedFile) itemIDs, itemNames, null)");
        assertEquals("Null array", exception.getMessage(),
                "Testing Sales.inputInventory(new Scanner(simulatedFile), itemIDs, "
                        + "itemNames, null) - exception message");
        assertArrayEquals(itemIDsLengthBadCopy, itemIDsLengthBad,
                "Modified array when invalid input: inputInventory");
        assertArrayEquals(itemNamesLengthBadCopy, itemNamesLengthBad,
                "Modified array when invalid input: inputInventory");
        assertArrayEquals(itemQuantitiesLengthBadCopy, itemQuantitiesLengthBad,
                "Modified array when invalid input: inputInventory");
        assertArrayEquals(itemIDsCopy, itemIDs3,
                "itemIDs correct after Sales.inputInventory(new Scanner(simulatedFile),"
                        + "itemIDs, itemNames,  itemQuantities");
        assertArrayEquals(itemNamesCopy, itemNames3,
                "itemNames correct after Sales.inputInventory(new Scanner(simulatedFile),"
                        + " itemIDs, itemNames, itemQuantities");
        assertArrayEquals(itemQuantitiesCopy, itemQuantities3,
                "itemQuantities correct after Sales.inputInventory(new Scanner(simulatedFile),"
                        + "itemIDs, itemNames, itemQuantities");

        exception = assertThrows(IllegalArgumentException.class,
            () -> Sales.inputInventory(new Scanner(simulatedFile), itemIDsLengthBad, itemNames,
                        itemQuantities),
                "Sales.inputInventory(new Scanner(simulatedFile),itemIDsLengthBad, itemNames, "
                        + "itemQuantities)");
        assertEquals("Invalid array length", exception.getMessage(),
                "Testing Sales.inputInventory(new Scanner(simulatedFile), "
                        + "itemIDsLengthBad, itemNames, itemQuantities) - exception message");
        assertArrayEquals(itemIDsLengthBadCopy, itemIDsLengthBad,
                "Modified array when invalid input: inputInventory");
        assertArrayEquals(itemNamesLengthBadCopy, itemNamesLengthBad,
                "Modified array when invalid input: inputInventory");
        assertArrayEquals(itemQuantitiesLengthBadCopy, itemQuantitiesLengthBad,
                "Modified array when invalid input: inputInventory");
        assertArrayEquals(itemIDsCopy, itemIDs3,
                "itemIDs correct after Sales.inputInventory(new Scanner(simulatedFile),"
                        + "itemIDs, itemNames,  itemQuantities");
        assertArrayEquals(itemNamesCopy, itemNames3,
                "itemNames correct after Sales.inputInventory(new Scanner(simulatedFile),"
                        + " itemIDs, itemNames, itemQuantities");
        assertArrayEquals(itemQuantitiesCopy, itemQuantities3,
                "itemQuantities correct after Sales.inputInventory(new Scanner(simulatedFile),"
                        + "itemIDs, itemNames, itemQuantities");

        exception = assertThrows(IllegalArgumentException.class,
            () -> Sales.inputInventory(new Scanner(simulatedFile), itemIDs, itemNamesLengthBad,
                        itemQuantities),
                "Sales.inputInventory(new Scanner(simulatedFile) itemIDs, itemNamesLengthBad, "
                        + "itemQuantities)");
        assertEquals("Invalid array length", exception.getMessage(),
                "Testing Sales.inputInventory(new Scanner(simulatedFile), "
                        + "itemIDs, itemNamesLengthBad, itemQuantities) - exception message");
        assertArrayEquals(itemIDsLengthBadCopy, itemIDsLengthBad,
                "Modified array when invalid input: inputInventory");
        assertArrayEquals(itemNamesLengthBadCopy, itemNamesLengthBad,
                "Modified array when invalid input: inputInventory");
        assertArrayEquals(itemQuantitiesLengthBadCopy, itemQuantitiesLengthBad,
                "Modified array when invalid input: inputInventory");
        assertArrayEquals(itemIDsCopy, itemIDs3,
                "itemIDs correct after Sales.inputInventory(new Scanner(simulatedFile),"
                        + "itemIDs, itemNames,  itemQuantities");
        assertArrayEquals(itemNamesCopy, itemNames3,
                "itemNames correct after Sales.inputInventory(new Scanner(simulatedFile),"
                        + " itemIDs, itemNames, itemQuantities");
        assertArrayEquals(itemQuantitiesCopy, itemQuantities3,
                "itemQuantities correct after Sales.inputInventory(new Scanner(simulatedFile),"
                        + "itemIDs, itemNames, itemQuantities");

        exception = assertThrows(IllegalArgumentException.class,
            () -> Sales.inputInventory(new Scanner(simulatedFile), itemIDs, itemNames,
                        itemQuantitiesLengthBad),
                "Sales.inputInventory(new Scanner(simulatedFile) itemIDs, itemNames, "
                        + "itemQuantitiesLengthBad)");
        assertEquals("Invalid array length", exception.getMessage(),
                "Testing Sales.inputInventory(new Scanner(simulatedFile), "
                        + "itemIDs, itemNames, itemQuantitiesLengthBad) - exception message");
        assertArrayEquals(itemIDsLengthBadCopy, itemIDsLengthBad,
                "Modified array when invalid input: inputInventory");
        assertArrayEquals(itemNamesLengthBadCopy, itemNamesLengthBad,
                "Modified array when invalid input: inputInventory");
        assertArrayEquals(itemQuantitiesLengthBadCopy, itemQuantitiesLengthBad,
                "Modified array when invalid input: inputInventory");
        assertArrayEquals(itemIDsCopy, itemIDs3,
                "itemIDs correct after Sales.inputInventory(new Scanner(simulatedFile),"
                        + "itemIDs, itemNames,  itemQuantities");
        assertArrayEquals(itemNamesCopy, itemNames3,
                "itemNames correct after Sales.inputInventory(new Scanner(simulatedFile),"
                        + " itemIDs, itemNames, itemQuantities");
        assertArrayEquals(itemQuantitiesCopy, itemQuantities3,
                "itemQuantities correct after Sales.inputInventory(new Scanner(simulatedFile),"
                        + "itemIDs, itemNames, itemQuantities");

    }

    /**
     * Tests getNumberOfLines
     */
    @Test
    public void testGetNumberOfLines() {

        // You do NOT need to add additional getNumberOfLines tests. Just make sure
        // these pass!

        String simulatedFile = "1346,NC State pencil,25\n" + "1367,yellow pencil,34\n"
                + "2038,black pen,35\n" + "2897,black permanent marker,12\n"
                + "2898,red dry erase marker,13\n";
        assertEquals(5, Sales.getNumberOfLines(new Scanner(simulatedFile)),
                "Sales.getNumberOfLines(new Scanner(simulatedFile)");

        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> Sales.getNumberOfLines(null), "Sales.getNumberOfLines(null)");
        assertEquals("Null file", exception.getMessage(),
                "Testing Sales.getNumberOfLines(null) - exception message");
    }

    /**
     * Tests outputInventory
     * 
     * @throws FileNotFoundException if unable to construct PrintWriter
     */

    @Test
    public void testOutputInventory() throws FileNotFoundException {

        // You do NOT need to add additional outputInventory tests. Just make sure these
        // pass!

        String expected = "test-files/inventory.txt";
        String actual = "test-files/act_updatedInventory.txt";
        // Delete file if it already exists
        Path path = Path.of(actual);
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            // Nothing needs to be done
            e.printStackTrace();
        }
        PrintWriter out = new PrintWriter(new FileOutputStream(actual));
        Sales.outputInventory(out, UPDATED_ITEM_IDS, UPDATED_ITEM_NAMES, UPDATED_ITEM_QUANTITIES);
        out.close();
        assertFilesEqual(expected, actual, "updatedInventory");

        String[] updatedIDs = { "PE0195", "MP8917", "DP8768", "NP1800",
            "P19076", "P90557", "PM1889", "DE6785",
            "DE8079", "BS3045", "PC8866", "PC9876",
            "T91180", "MT4987" };

        String[] updatedNames = { "pencil", "mechanical pencil",
            "drawing pencils", "notepad", "black pen",
            "blue pen", "black permanent marker",
            "black dry erase marker",
            "red dry erase marker", "box of staples",
            "small paper clips", "large paper clips",
            "tape", "masking tape" };

        int[] updatedQuant = { 34, 8, 5, 4, 35, 16, 12, 8, 13, 15, 23, 12, 20, 45 };
        assertArrayEquals(updatedIDs, UPDATED_ITEM_IDS,
                "Array modified in Sales.outputInventory when it shouldn't be");
        assertArrayEquals(updatedNames, UPDATED_ITEM_NAMES,
                "Array modified in Sales.outputInventory when it shouldn't be");
        assertArrayEquals(updatedQuant, UPDATED_ITEM_QUANTITIES,
                "Array modified in Sales.outputInventory when it shouldn't be");

        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> Sales.outputInventory(null, UPDATED_ITEM_IDS, UPDATED_ITEM_NAMES,
                        UPDATED_ITEM_QUANTITIES),
                "Sales.outputInventory(null, UPDATED_ITEM_IDS, UPDATED_ITEM_NAMES, "
                        + "UPDATED_ITEM_QUANTITIES)");
        assertEquals("Null file", exception.getMessage(), "Testing Null file - exception message");
        assertArrayEquals(updatedIDs, UPDATED_ITEM_IDS,
                "Array modified in Sales.outputInventory when it shouldn't be");
        assertArrayEquals(updatedNames, UPDATED_ITEM_NAMES,
                "Array modified in Sales.outputInventory when it shouldn't be");
        assertArrayEquals(updatedQuant, UPDATED_ITEM_QUANTITIES,
                "Array modified in Sales.outputInventory when it shouldn't be");

        exception = assertThrows(IllegalArgumentException.class,
            () -> Sales.outputInventory(out, null, UPDATED_ITEM_NAMES,
                        UPDATED_ITEM_QUANTITIES),
                "Sales.outputInventory(out, null, UPDATED_ITEM_NAMES, UPDATED_ITEM_QUANTITIES)");
        assertEquals("Null array", exception.getMessage(),
                "Testing Sales.outputInventory(out, null, UPDATED_ITEM_NAMES, "
                        + "UPDATED_ITEM_QUANTITIES) - exception message");
        assertArrayEquals(updatedIDs, UPDATED_ITEM_IDS,
                "Array modified in Sales.outputInventory when it shouldn't be");
        assertArrayEquals(updatedNames, UPDATED_ITEM_NAMES,
                "Array modified in Sales.outputInventory when it shouldn't be");
        assertArrayEquals(updatedQuant, UPDATED_ITEM_QUANTITIES,
                "Array modified in Sales.outputInventory when it shouldn't be");

        exception = assertThrows(IllegalArgumentException.class,
            () -> Sales.outputInventory(out, UPDATED_ITEM_IDS, null, UPDATED_ITEM_QUANTITIES),
                "Sales.outputInventory(out, UPDATED_ITEM_IDS, null, UPDATED_ITEM_QUANTITIES)");
        assertEquals("Null array", exception.getMessage(),
                "Testing Sales.outputInventory(out, UPDATED_ITEM_IDS, null, "
                        + "UPDATED_ITEM_QUANTITIES) - exception message");
        assertArrayEquals(updatedIDs, UPDATED_ITEM_IDS,
                "Array modified in Sales.outputInventory when it shouldn't be");
        assertArrayEquals(updatedNames, UPDATED_ITEM_NAMES,
                "Array modified in Sales.outputInventory when it shouldn't be");
        assertArrayEquals(updatedQuant, UPDATED_ITEM_QUANTITIES,
                "Array modified in Sales.outputInventory when it shouldn't be");

        exception = assertThrows(IllegalArgumentException.class,
            () -> Sales.outputInventory(out, UPDATED_ITEM_IDS, UPDATED_ITEM_NAMES, null),
                "Sales.outputInventory(out, UPDATED_ITEM_IDS, UPDATED_ITEM_NAMES, null)");
        assertEquals("Null array", exception.getMessage(),
                "Testing Sales.outputInventory(out, UPDATED_ITEM_IDS, "
                        + "UPDATED_ITEM_NAMES, null) - exception message");
        assertArrayEquals(updatedIDs, UPDATED_ITEM_IDS,
                "Array modified in Sales.outputInventory when it shouldn't be");
        assertArrayEquals(updatedNames, UPDATED_ITEM_NAMES,
                "Array modified in Sales.outputInventory when it shouldn't be");
        assertArrayEquals(updatedQuant, UPDATED_ITEM_QUANTITIES,
                "Array modified in Sales.outputInventory when it shouldn't be");

        String[] itemIDsLengthBad = { "", "", "", "" };
        String[] itemNamesLengthBad = { "", "", "", "", "", "" };
        int[] itemQuantitiesLengthBad = new int[2];
        String[] itemIDsLengthBadCopy = { "", "", "", "" };
        String[] itemNamesLengthBadCopy = { "", "", "", "", "", "" };
        int[] itemQuantitiesLengthBadCopy = new int[2];

        exception = assertThrows(IllegalArgumentException.class,
            () -> Sales.outputInventory(out, itemIDsLengthBad, UPDATED_ITEM_NAMES,
                        UPDATED_ITEM_QUANTITIES),
                "Sales.outputInventory(out, itemIDsLengthBad, UPDATED_ITEM_NAMES, "
                        + "UPDATED_ITEM_QUANTITIES)");
        assertEquals("Invalid array length", exception.getMessage(),
                "Testing Sales.outputInventory(out, itemIDsLengthBad, UPDATED_ITEM_NAMES, "
                        + "UPDATED_ITEM_QUANTITIES) - exception message");
        assertArrayEquals(updatedIDs, UPDATED_ITEM_IDS,
                "Array modified in Sales.outputInventory when it shouldn't be");
        assertArrayEquals(updatedNames, UPDATED_ITEM_NAMES,
                "Array modified in Sales.outputInventory when it shouldn't be");
        assertArrayEquals(updatedQuant, UPDATED_ITEM_QUANTITIES,
                "Array modified in Sales.outputInventory when it shouldn't be");
        assertArrayEquals(itemIDsLengthBadCopy, itemIDsLengthBad,
                "Array modified in Sales.outputInventory when it shouldn't be");
        assertArrayEquals(itemNamesLengthBadCopy, itemNamesLengthBad,
                "Array modified in Sales.outputInventory when it shouldn't be");
        assertArrayEquals(itemQuantitiesLengthBadCopy, itemQuantitiesLengthBad,
                "Array modified in Sales.outputInventory when it shouldn't be");

        exception = assertThrows(IllegalArgumentException.class,
            () -> Sales.outputInventory(out, UPDATED_ITEM_IDS, itemNamesLengthBad,
                        UPDATED_ITEM_QUANTITIES),
                "Sales.outputInventory(out, UPDATED_ITEM_IDS, itemNamesLengthBad, "
                        + "UPDATED_ITEM_QUANTITIES)");
        assertEquals("Invalid array length", exception.getMessage(),
                "Testing Sales.outputInventory(out, UPDATED_ITEM_IDS, "
                        + "itemNamesLengthBad, UPDATED_ITEM_QUANTITIES) - exception message");
        assertArrayEquals(updatedIDs, UPDATED_ITEM_IDS,
                "Array modified in Sales.outputInventory when it shouldn't be");
        assertArrayEquals(updatedNames, UPDATED_ITEM_NAMES,
                "Array modified in Sales.outputInventory when it shouldn't be");
        assertArrayEquals(updatedQuant, UPDATED_ITEM_QUANTITIES,
                "Array modified in Sales.outputInventory when it shouldn't be");
        assertArrayEquals(itemIDsLengthBadCopy, itemIDsLengthBad,
                "Array modified in Sales.outputInventory when it shouldn't be");
        assertArrayEquals(itemNamesLengthBadCopy, itemNamesLengthBad,
                "Array modified in Sales.outputInventory when it shouldn't be");
        assertArrayEquals(itemQuantitiesLengthBadCopy, itemQuantitiesLengthBad,
                "Array modified in Sales.outputInventory when it shouldn't be");

        exception = assertThrows(IllegalArgumentException.class,
            () -> Sales.outputInventory(out, UPDATED_ITEM_IDS, UPDATED_ITEM_NAMES,
                        itemQuantitiesLengthBad),
                "Sales.outputInventory(out, UPDATED_ITEM_IDS, UPDATED_ITEM_NAMES, "
                        + "itemQuantitiesLengthBad)");
        assertEquals("Invalid array length", exception.getMessage(),
                "Testing Sales.outputInventory(out, UPDATED_ITEM_IDS, "
                        + "UPDATED_ITEM_NAMES, itemQuantitiesLengthBad) - exception message");
        assertArrayEquals(updatedIDs, UPDATED_ITEM_IDS,
                "Array modified in Sales.outputInventory when it shouldn't be");
        assertArrayEquals(updatedNames, UPDATED_ITEM_NAMES,
                "Array modified in Sales.outputInventory when it shouldn't be");
        assertArrayEquals(updatedQuant, UPDATED_ITEM_QUANTITIES,
                "Array modified in Sales.outputInventory when it shouldn't be");
        assertArrayEquals(itemIDsLengthBadCopy, itemIDsLengthBad,
                "Array modified in Sales.outputInventory when it shouldn't be");
        assertArrayEquals(itemNamesLengthBadCopy, itemNamesLengthBad,
                "Array modified in Sales.outputInventory when it shouldn't be");
        assertArrayEquals(itemQuantitiesLengthBadCopy, itemQuantitiesLengthBad,
                "Array modified in Sales.outputInventory when it shouldn't be");

    }

    /**
     * Tests whether files contain the same contents
     * 
     * @param pathToExpected path to file with expected contents
     * @param pathToActual   path to file with actual content
     * @param message        message for test
     * @throws FileNotFoundException if Scanner cannot be constructed with file
     */
    private void assertFilesEqual(String pathToExpected, String pathToActual, String message)
            throws FileNotFoundException {
        try (Scanner expected = new Scanner(new FileInputStream(pathToExpected));
                Scanner actual = new Scanner(new FileInputStream(pathToActual));) {
            while (expected.hasNextLine()) {
                if (!actual.hasNextLine()) { // checks that actual has line as well
                    fail(message + ": Actual missing line(s)");
                } else { // both have another line
                    assertEquals(expected.nextLine(), actual.nextLine(),
                            message + ": Checking line equality");
                }
            }

            if (actual.hasNextLine()) {
                fail(message + ": Actual has extra line(s)");
            }
        }
    }

}