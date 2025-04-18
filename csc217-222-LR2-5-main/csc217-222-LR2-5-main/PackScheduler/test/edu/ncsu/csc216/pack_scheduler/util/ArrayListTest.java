/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

/**
 * Test for Array list class
 * @author nolanhurst
 *
 */
public class ArrayListTest {
	
	/**
	 * Test ArrayList
	 */
	@Test
	public void testArrayList() {
		ArrayList<Integer> a = new ArrayList<Integer>();
		assertEquals(0, a.size());
		
		ArrayList<String> b = new ArrayList<String>();
		assertEquals(0, b.size());
	}

	/**
	 * Test ArrayList add method
	 */
	@Test
	public void testAdd() {
		
		ArrayList<Integer> a = new ArrayList<Integer>();
		
		Exception e1 = assertThrows(IndexOutOfBoundsException.class, () -> a.add(5, 0));
		assertEquals(null, e1.getMessage());
				
		a.add(0, 5);
		
		assertEquals(5, (int) a.get(0));
		
		a.add(1, 3);
		assertEquals(3, (int) a.get(1));
		assertEquals(5, (int) a.get(0));
		
		a.add(1, 4);
		assertEquals(5, (int) a.get(0));
		assertEquals(4, (int) a.get(1));
		assertEquals(3, (int) a.get(2));
		
		a.add(1, 1);
		a.add(1, 2);
		a.add(1, 6);
		a.add(1, 7);
		a.add(1, 8);
		a.add(1, 9);
		a.add(1, 10);
		a.add(1, 11);
		assertEquals(11, a.size());
		
		ArrayList<String> b = new ArrayList<String>();
		b.add(0, "a");
		assertEquals("a", b.get(0));

	}
	
	/**
	 * Test ArrayList remove method 
	 */
	@Test
	public void testRemove() {
		ArrayList<Integer> a = new ArrayList<Integer>();
		
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
		
	}
	
	/**
	 * Test ArrayList set method
	 */
	@Test
	public void testSet() {
		ArrayList<Integer> a = new ArrayList<Integer>();
		a.add(0, 5);
		assertEquals(5, (int) a.set(0, 2));
		assertEquals(2, (int) a.get(0));
	}
	
	
}
