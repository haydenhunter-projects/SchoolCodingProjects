package edu.ncsu.csc217.collections.list;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test methods from the SortedList class
 * 
 * @author Hari Vemulapalli
 * @author Victor Hermida
 * @author Hayden Hunter
 *
 */
public class SortedListTest {

	/**
	 * Tests to make sure list is initialized to 0 Test to make sure the list is
	 * empty
	 * 
	 */
	@Test
	public void testSortedList() {
		SortedList<String> list = new SortedList<String>();
		assertEquals(0, list.size());
		assertFalse(list.contains("apple"));

		list.add("banana");
		list.add("orange");
		list.add("apple");
		list.add("grape");
		list.add("plum");
		list.add("grapefruit");
		list.add("mango");
		list.add("pineapple");
		list.add("watermelon");
		list.add("dragonfruit");
		list.add("kiwi");

		assertEquals(11, list.size()); // checks to make sure list has size 11

		// TODO Test that the list grows by adding at least 11 elements
		// Remember the list's initial capacity is 10

	}

	/**
	 * Tests the add functionality for SortedList. It is based on the order of the
	 * alphabet where a particular element would be set in the list
	 */
	@Test
	public void testAdd() {
		SortedList<String> list = new SortedList<String>();

		list.add("banana");
		assertEquals(1, list.size());
		assertEquals("banana", list.get(0));

		list.add("apple");
		assertEquals(2, list.size());
		assertEquals("apple", list.get(0));

		list.add("zucchini");
		assertEquals(3, list.size());
		assertEquals("zucchini", list.get(2));

		list.add("grape");
		assertEquals(4, list.size());
		assertEquals("grape", list.get(2));

		Exception e1 = assertThrows(NullPointerException.class, () -> list.add(null));

		Exception s1 = assertThrows(IllegalArgumentException.class, () -> list.add("banana"));

		assertEquals("Element already in list.", s1.getMessage());
		assertEquals(null, e1.getMessage());

	}

	/**
	 * Tests the getter method for SortedList where we would retrieve an element
	 * from a given index
	 */
	@Test
	public void testGet() {
		SortedList<String> list = new SortedList<String>();

		// Since get() is used throughout the tests to check the
		// contents of the list, we don't need to test main flow functionality
		// here. Instead this test method should focus on the error
		// and boundary cases.

		Exception f1 = assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));

		// TODO Test getting an element from an empty list
		assertEquals(null, f1.getMessage());

		// TODO Add some elements to the list
		list.add("apple");
		list.add("banana");
		list.add("cherry");
		// TODO Test getting an element at an index < 0
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));

		// TODO Test getting an element at size
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(list.size()));
	}

	/**
	 * Tests the remove functionality of the SortedList class Involves empty list,
	 * removing the first, last, and specified elements, as well as in a specified
	 * index
	 */
	@Test
	public void testRemove() {
		SortedList<String> list = new SortedList<String>();

		// TODO Test removing from an empty list
		Exception e1 = assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
		assertEquals(null, e1.getMessage());
		// TODO Add some elements to the list - at least 4
		list.add("banana");
		list.add("grape");
		list.add("apple");
		list.add("kiwi");
		list.add("zuccini");
		list.add("dragonfruit");
		// List has apple, banana, dragonfruit, kiwi, grape, and zuccini
		assertEquals(6, list.size());
		// TODO Test removing an element at an index < 0
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
		// TODO Test removing an element at size
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(list.size()));
		// TODO Test removing a middle element
		list.remove(list.size() / 2);
		// Removes kiwi
		assertEquals(5, list.size());
		// TODO Test removing the last element
		list.remove(list.size() - 1);
		// Removes zuccini
		assertEquals(4, list.size());
		// TODO Test removing the first element
		list.remove(0);
		// Removes apple
		assertEquals(3, list.size());
		// TODO Test removing the last element
		list.remove(list.size() - 1);
		// Removes grape
		assertEquals(2, list.size());
	}

	/**
	 * Tests IndexOf method for a SortedList
	 * 
	 * 
	 */
	@Test
	public void testIndexOf() {
		SortedList<String> list = new SortedList<String>();

		// check that list is empty
		assertTrue(list.isEmpty());

		// Test indexOf on an empty list
		assertEquals(-1, list.indexOf("vegetable"));

		// Add some elements
		list.add("banana");
		list.add("grape");
		list.add("apple");
		list.add("kiwi");
		list.add("zucchini");
		list.add("dragonfruit");

		// check that the size of list is what we intended
		assertEquals(6, list.size());

		// Test various calls to indexOf for elements in the list and not in the list
		assertEquals(1, list.indexOf("banana"));
		assertEquals(3, list.indexOf("grape"));
		assertEquals(-1, list.indexOf("broccoli"));
		assertEquals(0, list.indexOf("apple"));
		assertEquals(4, list.indexOf("kiwi"));
		assertEquals(-1, list.indexOf("pear"));
		assertEquals(5, list.indexOf("zucchini"));
		assertEquals(2, list.indexOf("dragonfruit"));
		assertEquals(-1, list.indexOf("asparagus"));

		// remove all elements from the list we added 6 elements.
		list.clear();

		assertTrue(list.isEmpty()); // check that the list is now empty by checking last element in sorted list.

		assertEquals(-1, list.indexOf("null")); // check the index of null
	}

	/**
	 * tests the Clear method for a SortedList.
	 * 
	 * Remove all elements from a list.
	 */
	@Test
	public void testClear() {
		SortedList<String> list = new SortedList<String>();

		// Add some elements
		list.add("banana");
		list.add("grape");
		list.add("apple");
		list.add("kiwi");
		list.add("zucchini");
		list.add("dragonfruit");

		// Clear the list
		list.clear();

		// Test that the list is empty
		assertTrue(list.isEmpty());

	}

	/**
	 * Tests the isEmpty method for a SortedList
	 * 
	 * The isEmpty method will check the list for any elements. Method returns true
	 * if there are zero elements in a list and false if there are more than zero
	 * elements in a list.
	 */
	@Test
	public void testIsEmpty() {
		SortedList<String> list = new SortedList<String>();

		// Test that the list starts empty
		assertTrue(list.isEmpty());
		// Add at least one element
		list.add("banana");
		list.add("grape");
		list.add("apple");
		list.add("kiwi");
		list.add("zucchini");
		list.add("dragonfruit");
		// Check that the list is no longer empty
		assertFalse(list.isEmpty());
	}

	/**
	 * Tests to see if a particular element is included in a list
	 */
	@Test
	public void testContains() {
		SortedList<String> list = new SortedList<String>();

		// TODO Test the empty list case
		assertFalse(list.contains("banana"));
		// TODO Add some elements
		list.add("banana");
		list.add("grape");
		list.add("kiwi");
		// TODO Test some true and false cases
		assertTrue(list.contains("grape"));
		assertFalse(list.contains("dragonfruit"));
	}

	/**
	 * We are testing to see if three Strings equal to each other or not from the
	 * SortedList list1, list2, and list3
	 */
	@Test
	public void testEquals() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();

		// adding the fruits to the lists
		list1.add("apple");
		list2.add("apple");
		list3.add("kiwi");

		// testing to see if list1 is the same as list2
		assertTrue(list1.equals(list2));
		// testing to see if list1 is the same as list3
		assertFalse(list1.equals(list3));
	}

	/**
	 * We are testing to see if three Strings are the same as the hasCode from the
	 * SortedList list1, list2, and list3
	 */
	@Test
	public void testHashCode() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();

		// adding the fruits to the lists
		list1.add("grape");
		list2.add("grape");
		list3.add("bannana");

		// testing to see if list1 hasCode is the same as list2 hasCode
		assertEquals(list1.hashCode(), list2.hashCode());
	}

}
