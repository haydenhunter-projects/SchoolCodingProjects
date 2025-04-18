package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LinkedListRecursiveTest {

	/**
	 * Test method for ArrayList constructor. 
	 */
	@Test
	void testLinkedList() {
		LinkedListRecursive<String> a1 = new LinkedListRecursive<String>();
		
		assertEquals(0, a1.size());
	}
	
	/**
	 * Test method for add methods
	 */
	@Test
	void testAdd() {
		LinkedListRecursive<String> a1 = new LinkedListRecursive<String>();
		a1.add(0, "L");
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
		
		assertThrows(NullPointerException.class, 
				() -> a1.add(null));
		assertThrows(IllegalArgumentException.class, 
				() -> a1.add("G"));
		
		a1.add("I");
		
		assertEquals(13, a1.size());
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
		assertEquals("I", a1.get(12));
		
		assertThrows(IndexOutOfBoundsException.class, 
				() -> a1.add(47, "z"));
		assertThrows(IndexOutOfBoundsException.class, 
				() -> a1.add(-47, "z"));
		assertThrows(NullPointerException.class, 
				() -> a1.add(12, null));
		assertThrows(IllegalArgumentException.class, 
				() -> a1.add(12, "P"));
		
		LinkedListRecursive<String> a2 = new LinkedListRecursive<String>();
		a2.add("A");
		
		assertEquals("A", a2.get(0));
		
	}
	
	/**
	 * Test method for remove methods
	 */
	@Test
	void testRemove() {
		LinkedListRecursive<String> a1 = new LinkedListRecursive<String>();
		
		assertThrows(IndexOutOfBoundsException.class, 
				() -> a1.remove(0));
		
		a1.add(0, "L");
		a1.add(1, "W");
		a1.add(1, "O");
		a1.add(0, "P");
		
		assertThrows(IndexOutOfBoundsException.class, 
				() -> a1.remove(100));
		assertThrows(IndexOutOfBoundsException.class, 
				() -> a1.remove(-100));
		
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
		
		assertFalse(a1.remove("L"));
		
		a1.add(0, "L");
		a1.add(1, "W");
		a1.add(1, "O");
		a1.add(0, "P");
		
		assertFalse(a1.remove(null));
		assertTrue(a1.remove("P"));
		assertEquals(3, a1.size());
		assertEquals("L", a1.get(0));
		assertEquals("O", a1.get(1));
		assertEquals("W", a1.get(2));
		assertTrue(a1.remove("O"));
		assertEquals("L", a1.get(0));
		assertEquals("W", a1.get(1));
		assertEquals(2, a1.size());
		assertFalse(a1.remove("M"));
		assertTrue(a1.remove("W"));
		assertEquals(1, a1.size());
		assertEquals("L", a1.get(0));
		assertTrue(a1.remove("L"));
		assertEquals(0, a1.size());
	}
	
	/**
	 * Test method for set
	 */
	@Test
	void testSet() {
		LinkedListRecursive<String> a1 = new LinkedListRecursive<String>();
		assertThrows(IndexOutOfBoundsException.class, 
				() -> a1.get(0));
		a1.add(0, "L");
		a1.add(1, "W");
		a1.add(1, "O");
		a1.add(0, "P");
		assertThrows(IndexOutOfBoundsException.class, 
				() -> a1.get(10));
		assertThrows(IndexOutOfBoundsException.class, 
				() -> a1.get(-10));
		assertEquals("P", a1.set(0, "F"));
		assertEquals("F", a1.get(0));
		assertEquals("L", a1.get(1));
		assertEquals("O", a1.get(2));
		assertEquals("W", a1.get(3));
		assertEquals(4, a1.size());
		
		assertEquals("O", a1.set(2, "K"));
		assertEquals("F", a1.get(0));
		assertEquals("L", a1.get(1));
		assertEquals("K", a1.get(2));
		assertEquals("W", a1.get(3));
		assertEquals(4, a1.size());
		
		assertEquals("W", a1.set(3, "M"));
		assertEquals("F", a1.get(0));
		assertEquals("L", a1.get(1));
		assertEquals("K", a1.get(2));
		assertEquals("M", a1.get(3));
		assertEquals(4, a1.size());
		
		assertEquals(null, a1.set(4, "Z"));
		assertEquals("F", a1.get(0));
		assertEquals("L", a1.get(1));
		assertEquals("K", a1.get(2));
		assertEquals("M", a1.get(3));
		assertEquals("Z", a1.get(4));
		assertEquals(5, a1.size());
		
		assertThrows(NullPointerException.class, 
				() -> a1.set(0, null));
		assertThrows(IllegalArgumentException.class, 
				() -> a1.set(0, "M"));
		assertThrows(IndexOutOfBoundsException.class, 
				() -> a1.set(10, "J"));
		assertThrows(IndexOutOfBoundsException.class, 
				() -> a1.set(-1, "J"));
	}

}
