package edu.ncsu.csc217.collections.list;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;

/**
 * Tests the SortedList.
 * 
 * @author Thien Nguyen, Jerolyn ClementRaj, Eric Smith
 */
public class SortedListTest {

	/**
	 * Tests constructing a Sorted List
	 */
	@Test
	public void testSortedList() {
		SortedList<String> list = new SortedList<String>();
		assertEquals(0, list.size());
		assertFalse(list.contains("apple"));

		// Remember the list's initial capacity is 10

		// Test that the list grows by adding at least 11 elements
		list.add("banana");
		list.add("apple");
		list.add("mango");
		list.add("melon");
		list.add("watermelon");
		list.add("orange");
		list.add("blueberry");
		list.add("avocado");
		list.add("pineapple");
		list.add("strawberry");
		list.add("durian");
		assertEquals(11, list.size());

	}

	/**
	 * Tests add() method.
	 */
	@Test
	public void testAdd() {
		SortedList<String> list = new SortedList<String>();

		list.add("banana");
		assertEquals(1, list.size());
		assertEquals("banana", list.get(0));

		// Test adding to the front, middle and back of the list
		list.add("apple");
		assertEquals(2, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(1));

		list.add("orange");
		assertEquals(3, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(1));
		assertEquals("orange", list.get(2));

		list.add("durian");
		assertEquals(4, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(1));
		assertEquals("durian", list.get(2));
		assertEquals("orange", list.get(3));

		// Test adding a null element
		assertThrows(NullPointerException.class, () -> list.add(null));

		// Test adding a duplicate element
		assertThrows(IllegalArgumentException.class, () -> list.add("banana"));
	}

	/**
	 * Tests get() method.
	 */
	@Test
	public void testGet() {
		SortedList<String> list = new SortedList<String>();

		// Since get() is used throughout the tests to check the
		// contents of the list, we don't need to test main flow functionality
		// here. Instead this test method should focus on the error
		// and boundary cases.
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
		list.add("banana");
		list.add("watermelon");
		list.add("apple");
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(list.size()));

	}

	/**
	 * Tests remove() method.
	 */
	@Test
	public void testRemove() {
		SortedList<String> list = new SortedList<String>();

		// Test removing from an empty list
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
		// Add some elements to the list - at least 4
		list.add("banana");
		list.add("watermelon");
		list.add("apple");
		list.add("orange");
		list.add("pineapple");
		// Test removing an element at an index < 0
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
		// Test removing an element at size
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(list.size()));
		// Test removing a middle element
		assertEquals("orange", list.remove(2));
		// Test removing the last element
		assertEquals("watermelon", list.remove(3));
		// Test removing the first element
		assertEquals("apple", list.remove(0));
		// Test removing the last element
		assertEquals("pineapple", list.remove(1));
	}

	/**
	 * Tests indexOf() method.
	 */
	@Test
	public void testIndexOf() {
		SortedList<String> list = new SortedList<String>();

		// Test indexOf on an empty list
		assertEquals(-1, list.indexOf("orange"));
		// Add some elements
		list.add("banana");
		list.add("watermelon");
		list.add("apple");
		list.add("orange");
		// Test various calls to indexOf for elements in the list
		// and not in the list
		assertEquals(0, list.indexOf("apple"));
		assertEquals(-1, list.indexOf("pineapple"));
		assertEquals(3, list.indexOf("watermelon"));
		assertEquals(1, list.indexOf("banana"));
		// Test checking the index of null
		assertThrows(NullPointerException.class, () -> list.indexOf(null));

	}

	/**
	 * Tests clear() method.
	 */
	@Test
	public void testClear() {
		SortedList<String> list = new SortedList<String>();

		// Add some elements
		list.add("banana");
		list.add("watermelon");
		list.add("apple");
		list.add("orange");
		// Clear the list
		list.clear();
		// Test that the list is empty
		assertEquals(0, list.size());
	}

	/**
	 * Tests isEmpty() method.
	 */
	@Test
	public void testIsEmpty() {
		SortedList<String> list = new SortedList<String>();

		// Test that the list starts empty
		assertTrue(list.isEmpty());
		// Add at least one element
		list.add("banana");
		list.add("watermelon");
		// Check that the list is no longer empty
		assertFalse(list.isEmpty());
	}

	/**
	 * Tests contains() method.
	 */
	@Test
	public void testContains() {
		SortedList<String> list = new SortedList<String>();

		// Test the empty list case
		assertFalse(list.contains("banana"));
		// Add some elements
		list.add("banana");
		list.add("watermelon");

		// Test some true and false cases
		assertTrue(list.contains("banana"));
		assertTrue(list.contains("watermelon"));
		assertFalse(list.contains("hi"));
		assertFalse(list.contains("melon"));
		assertFalse(list.contains("orange"));
	}

	/**
	 * Tests equals() method.
	 */
	@Test
	public void testEquals() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();

		// Make two lists the same and one list different
		list1.add("banana");
		list2.add("banana");
		list3.add("apple");

		list1.add("watermelon");
		list2.add("watermelon");
		list3.add("orange");

		// Test for equality and non-equality
		assertTrue(list1.equals(list2));
		assertFalse(list2.equals(list3));
		assertFalse(list3.equals(list1));
	}

	/**
	 * Tests hashCode() method.
	 */
	@Test
	public void testHashCode() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();

		// Make two lists the same and one list different
		list1.add("banana");
		list2.add("banana");
		list3.add("orange");

		list1.add("watermelon");
		list2.add("watermelon");
		list3.add("apple");
		// Test for the same and different hashCodes
		assertEquals(list1.hashCode(), list2.hashCode());
		assertNotEquals(list1.hashCode(), list3.hashCode());
		assertNotEquals(list3.hashCode(), list2.hashCode());
	}

}
