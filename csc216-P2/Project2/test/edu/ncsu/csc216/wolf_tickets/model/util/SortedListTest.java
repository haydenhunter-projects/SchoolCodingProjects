package edu.ncsu.csc216.wolf_tickets.model.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * tests SortedList class
 *
 * @author hchunter
 *
 */
public class SortedListTest {

	/**
	 * tests SortedList add
	 */
	@Test
	public void testAdd() {

		SortedList<String> list = new SortedList<>();

		list.add("apple");
		list.add("carrot");
		list.add("banana");

		Exception e1 = assertThrows(NullPointerException.class, () -> list.add(null));
		assertEquals("Cannot add null element.", e1.getMessage());
//
		Exception e2 = assertThrows(IllegalArgumentException.class, () -> list.add("apple"));
		assertEquals("Cannot add duplicate element.", e2.getMessage());
//

		assertEquals(3, list.size());

		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(1));
		assertEquals("carrot", list.get(2));

		list.add("watermelon");
		list.add("apricot");
		assertEquals(5, list.size());

		assertEquals("apple", list.get(0));
		assertEquals("apricot", list.get(1));
		assertEquals("banana", list.get(2));
		assertEquals("carrot", list.get(3));
		assertEquals("watermelon", list.get(4));

	}

	/**
	 * tests SortedList remove
	 */
	@Test
	public void testRemove() {

		SortedList<String> list = new SortedList<>();

		Exception e1 = assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
		assertEquals("Invalid index.", e1.getMessage());

		Exception e2 = assertThrows(IndexOutOfBoundsException.class, () -> list.remove(100));
		assertEquals("Invalid index.", e2.getMessage());

		list.add("apple");
		list.add("carrot");
		list.add("banana");
		list.add("watermelon");
		list.add("apricot");

		assertEquals(5, list.size());

		// list is apple, apricot, banana, carrot, watermelon
		assertEquals("apple", list.remove(0));

		// list is apricot, banana, carrot, watermelon
		assertEquals("apricot", list.get(0));
		assertEquals("banana", list.get(1));
		assertEquals("carrot", list.get(2));
		assertEquals("watermelon", list.get(3));

		// list is apricot, banana, carrot, watermelon
		assertEquals("carrot", list.remove(2));

		// list is apricot, banana, watermelon
		assertEquals("apricot", list.get(0));
		assertEquals("banana", list.get(1));
		assertEquals("watermelon", list.get(2));
	}

	/**
	 * tests SortedList contains
	 */
	@Test
	public void testContains() {

		SortedList<String> list = new SortedList<>();

		list.add("apple");
		list.add("carrot");
		list.add("banana");

		assertEquals(3, list.size());

		// list contains apple, banana, carrot
		assertTrue(list.contains("apple"));
		assertTrue(list.contains("banana"));
		assertTrue(list.contains("carrot"));
		assertFalse(list.contains("orange"));
		assertFalse(list.contains("1"));
		assertFalse(list.contains("zyx"));
		assertFalse(list.contains("bomb"));
	}

}
