/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test class for LinkedAbstractList
 * @author koeke
 *
 */
class LinkedAbstractListTest {


	/**
	 * Test method for LinkedAbstractList constructor. 
	 */
	@Test
	void testLinkedAbstractList() {
		LinkedAbstractList<String> a1 = new LinkedAbstractList<String>(10);
		
		assertEquals(0, a1.size());
	}
	
	/**
	 * Test method for the add method
	 */
	@Test
	void testAdd() {
		LinkedAbstractList<String> a1 = new LinkedAbstractList<String>(100);
		assertThrows(NullPointerException.class, 
				() -> a1.add(null));
		assertThrows(IndexOutOfBoundsException.class, 
				() -> a1.add(-1, "P"));
		assertThrows(IndexOutOfBoundsException.class, 
				() -> a1.add(10, "P"));
		a1.add(0, "L");
		assertThrows(IllegalArgumentException.class, 
				() -> a1.add(0, "L"));
		assertEquals(1, a1.size());
		assertEquals("L", a1.get(0));
		a1.add(1, "W");
		assertEquals(2, a1.size());
		assertEquals("W", a1.get(1));
		a1.add(1, "O");
		assertEquals(3, a1.size());
		assertEquals("O", a1.get(1));
		assertEquals("W", a1.get(2));
		a1.add(0, "P");
		assertEquals(4, a1.size());
		assertEquals("P", a1.get(0));
		assertEquals("L", a1.get(1));
		assertEquals("O", a1.get(2));
		assertEquals("W", a1.get(3));
		a1.add(4, "A");
		a1.add(5, "B");
		a1.add(6, "C");
		a1.add(7, "D");
		a1.add(8, "E");
		a1.add(9, "F");
		a1.add(10, "G");
		a1.add(11, "H");
		assertEquals(12, a1.size());
		assertEquals("P", a1.get(0));
		assertEquals("L", a1.get(1));
		assertEquals("O", a1.get(2));
		assertEquals("W", a1.get(3));
		assertEquals("A", a1.get(4));
		assertEquals("B", a1.get(5));
		assertEquals("C", a1.get(6));
		assertEquals("D", a1.get(7));
		assertEquals("E", a1.get(8));
		assertEquals("F", a1.get(9));
		assertEquals("G", a1.get(10));
		assertEquals("H", a1.get(11));
		
		assertThrows(IndexOutOfBoundsException.class, 
				() -> a1.add(47, "z"));
		assertThrows(IndexOutOfBoundsException.class, 
				() -> a1.add(-47, "z"));
		assertThrows(NullPointerException.class, 
				() -> a1.add(12, null));
		assertThrows(IllegalArgumentException.class, 
				() -> a1.add(12, "P"));
		
	}
	
	/**
	 * Test method for the remove method
	 */
	@Test
	void testRemove() {
		LinkedAbstractList<String> a1 = new LinkedAbstractList<String>(100);
		a1.add(0, "L");
		a1.add(1, "W");
		a1.add(1, "O");
		a1.add(0, "P");
		assertEquals("P", a1.remove(0));
		assertEquals(3, a1.size());
		assertEquals("L", a1.get(0));
		assertEquals("O", a1.get(1));
		assertEquals("W", a1.get(2));
		assertEquals("O", a1.remove(1));
		assertEquals("L", a1.get(0));
		assertEquals("W", a1.get(1));
		assertEquals(2, a1.size());
		assertEquals("W", a1.remove(1));
		assertEquals(1, a1.size());
		assertEquals("L", a1.get(0));
		assertEquals("L", a1.remove(0));
		assertEquals(0, a1.size());
	}
	
	/**
	 * Test method for the set method
	 */
	@Test
	void testSet() {
		LinkedAbstractList<String> a1 = new LinkedAbstractList<String>(100);
		a1.add(0, "L");
		a1.add(1, "W");
		a1.add(1, "O");
		a1.add(0, "P");
		a1.set(0, "F");
		assertEquals("F", a1.get(0));
		assertEquals("L", a1.get(1));
		assertEquals("O", a1.get(2));
		assertEquals("W", a1.get(3));
		assertEquals(4, a1.size());
	}
	
	/**
	 * Test method for the capacity feature
	 */
	@Test
	void testCapacity() {
		LinkedAbstractList<String> a1 = new LinkedAbstractList<String>(3);
		a1.add(0, "L");
		a1.add(1, "W");
		a1.add(1, "O");
		assertThrows(IllegalArgumentException.class, 
				() -> a1.add(0, "P"));
		a1.remove(0);
		a1.add(0, "T");
		assertEquals("T", a1.get(0));
		assertEquals("O", a1.get(1));
		assertEquals("W", a1.get(2));
	}

}
