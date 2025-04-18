/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

/**
 * The LinkedAbstractListTest class tests the LinkedAbstractList class
 * @author nolanhurst
 *
 */
public class LinkedAbstractListTest {
	
		/**
		 * tests the add method
		 */
		@Test
		public void testLinkedAbstractListAdd() {
		LinkedAbstractList<String> a = new LinkedAbstractList<String>(11);
		
		Exception e1 = assertThrows(IndexOutOfBoundsException.class, () -> a.add(5, "a"));
		assertEquals(null, e1.getMessage());
				
		a.add(0, "a");
		
		assertEquals("a", (String) a.get(0));
		
		a.add(1, "c");
		assertEquals("c", (String) a.get(1));
		assertEquals("a", (String) a.get(0));
		
		a.add(1, "b");
		assertEquals("a", (String) a.get(0));
		assertEquals("b", (String) a.get(1));
		assertEquals("c", (String) a.get(2));
		
		a.add(1, "d");
		a.add(1, "e");
		a.add(1, "f");
		a.add(1, "g");
		a.add(1, "h");
		a.add(1, "i");
		a.add(1, "j");
		a.add(1, "k");
		assertEquals(11, a.size());
		
		LinkedAbstractList<String> b = new LinkedAbstractList<String>(11);
		b.add(0, "apple");
		b.add(0, "orange");
		assertEquals("orange", b.get(0));
		b.add(2, "banana");
		
		assertThrows(IllegalArgumentException.class, () -> b.add(1, "apple"));
		
	}
	
	/**
	 * Test ArrayList remove method 
	 */
	@Test
	public void testRemove() {
		LinkedAbstractList<Integer> a = new LinkedAbstractList<Integer>(11);
		
		Exception e1 = assertThrows(IndexOutOfBoundsException.class, () -> a.remove(5));
		assertEquals(null, e1.getMessage());
		
		a.add(0, 5);
		a.add(1, 1);
		a.add(2, 2);
		assertEquals(5, (int) a.get(0));
		assertEquals(1, (int) a.get(1));
		assertEquals(2, (int) a.get(2));
		
		assertEquals(1, (int) a.remove(1));
		assertEquals(2, (int) a.get(1));
		
		a.add(1, 1);
		a.add(3, 3);
		a.add(4, 4);
		a.add(5, 7);
		a.add(6, 6);
		
		assertEquals(4, (int) a.remove(4));
		assertEquals(7, (int) a.get(4));
		
	}
	
	/**
	 * Test ArrayList set method
	 */
	@Test
	public void testSet() {
		LinkedAbstractList<Integer> a = new LinkedAbstractList<Integer>(11);
		a.add(0, 5);
		assertEquals(5, (int) a.set(0, 2));
		assertEquals(2, (int) a.get(0));
		
		
		a.add(1, 7);
		a.add(2, 3);
		assertEquals(7, (int) a.set(1, 4));
		assertEquals(4, (int) a.get(1));
	}
	
	/**
	 * Tests exceptions for get and add
	 */
	@Test
	public void testExceptions() {
		LinkedAbstractList<Integer> list = new LinkedAbstractList<Integer>(5);
			
			list.add(0, 1);
			list.add(1, 2);
			list.add(2, 3);
			Exception e1 = assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
			assertEquals(null, e1.getMessage());
			
			Exception e2 = assertThrows(NullPointerException.class, () -> list.add(3, null));
			assertEquals(null, e2.getMessage());
			
			
			
		
	}
}
