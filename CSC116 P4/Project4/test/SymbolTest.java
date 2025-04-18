
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests Symbol class
 * 
 * @author Suzanne Balik
 * @author Michelle Glatz
 * @author Hayden Hunter
 */
public class SymbolTest {

    /** Symbol giraffe for testing */
    private Symbol giraffe;

    /** Symbol cat for testing */
    private Symbol cat;

    /**
     * Create Symbols for testing
     */
    @BeforeEach
    public void setUp() {
        giraffe = new Symbol("giraffe", 25);
        cat = new Symbol("cat", 10);
    }

    /**
     * tests getName
     */
    @Test
    public void testGetNameGiraffe() {
        assertEquals("giraffe", giraffe.getName(), "giraffe name");
    }

    /**
     * tests getName
     */
    @Test
    public void testGetNameCat() {
        assertEquals("cat", cat.getName(), "cat name");
    }

    /**
     * tests getPoints
     */
    @Test
    public void testGetPointsGiraffe() {
        assertEquals(25, giraffe.getPoints(), "giraffe points");
    }

    /**
     * tests getPoints
     */
    @Test
    public void testGetPointsCat() {
        assertEquals(10, cat.getPoints(), "cat points");
    }

    /**
     * tests toString
     */
    @Test
    public void testToStringGiraffe() {
        assertEquals("giraffe 25 false", giraffe.toString(), "giraffe toString");
    }

    /**
     * tests toString
     */
    @Test
    public void testToStringCat() {
        assertEquals("cat 10 false", cat.toString(), "cat toString");
    }

    /**
     * tests hasBeenClickedOn
     */
    @Test
    public void testHasBeenClickedOnGiraffe() {
        assertFalse(giraffe.hasBeenClickedOn(), "giraffe hasBeenClickedOn");
    }

    /**
     * tests hasBeenClickedOn
     */
    @Test
    public void testHasBeenClickedOnCat() {
        assertFalse(cat.hasBeenClickedOn(), "cat hasBeenClickedOn");
    }

    /**
     * tests setHasBeenClickedOn
     */
    @Test
    public void testSetHasBeenClickedOnGiraffe() {
        giraffe.setHasBeenClickedOn(true);
        assertTrue(giraffe.hasBeenClickedOn(), "giraffe setHasBeenClickedOn true");
        giraffe.setHasBeenClickedOn(false);
        assertFalse(giraffe.hasBeenClickedOn(), "giraffe setHasBeenClickedOn false");
    }

    /**
     * tests setHasBeenClickedOn
     */
    @Test
    public void testSetHasBeenClickedOnCat() {
        cat.setHasBeenClickedOn(true);
        assertTrue(cat.hasBeenClickedOn(), "cat setHasBeenClickedOn true");
        cat.setHasBeenClickedOn(false);
        assertFalse(cat.hasBeenClickedOn(), "cat setHasBeenClickedOn false");
    }

    /**
     * tests the equal method for giraffe
     */
    @Test
    public void testEqualsGiraffe() {
        assertTrue(giraffe.equals(giraffe), "giraffe equals with same instance");
        assertTrue(giraffe.equals(new Symbol("giraffe", 25)),
                "giraffe equals with different instances");
        assertFalse(giraffe.equals(new Symbol("cow", 25)), "giraffe with different name");
        assertFalse(giraffe.equals(new Symbol("giraffe", 4)), "giraffe with different points");
        assertFalse(giraffe.equals(new Symbol("horse", 5)),
                "giraffe with different name and points");
        assertFalse(giraffe.equals(null), "giraffe compared to null object");
        assertFalse(giraffe.equals("giraffe"), "giraffe compared to String");
    }

    /**
     * tests the equal method for cat
     */
    @Test
    public void testEqualsCat() {
        assertTrue(cat.equals(cat), "cat equals with same instance");
        assertTrue(cat.equals(new Symbol("cat", 10)),
                "cat equals with different instances");
        assertFalse(cat.equals(new Symbol("monkey", 18)), "cat with different name");
        assertFalse(cat.equals(new Symbol("whale", 99)), "cat with different points");
        assertFalse(cat.equals(new Symbol("squirrel", 5)),
                "cat with different name and points");
        assertFalse(cat.equals(null), "cat compared to null object");
        assertFalse(cat.equals("cat"), "cat compared to String");
    }

    /**
     * Tests exceptions
     */
    @Test
    public void testExceptions() {

        // Testing constructor with null name
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> new Symbol(null, 1), "Constructor name null");
        assertEquals("Null name", exception.getMessage(),
                "Testing null name message");

        // Testing constructor with 0 points
        exception = assertThrows(IllegalArgumentException.class,
            () -> new Symbol("snake", 0), "Constructor points 0");
        assertEquals("Invalid points", exception.getMessage(),
                "Testing points 0 message");

        // Testing constructor with negative points
        exception = assertThrows(IllegalArgumentException.class,
            () -> new Symbol("frog", -5), "Constructor points -5");
        assertEquals("Invalid points", exception.getMessage(),
                "Testing negative points message");

    }

}