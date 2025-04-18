package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.EmptyStackException;

import org.junit.jupiter.api.Test;

/**
 * Test class for LinkedStack
 * 
 * @author jpkenlin
 *
 */
class LinkedStackTest {

	/**
	 * Test method for adding single element
	 */
	@Test
	void testAddSingleElem() {
		LinkedStack<Integer> stack = new LinkedStack<Integer>(10);
		stack.push(5);
		assertEquals(1, stack.size());
		assertEquals(5, stack.pop());
	}

	/**
	 * Test method for adding multiple elements
	 */
	@Test
	void testAddMultElem() {
		LinkedStack<Integer> stack = new LinkedStack<Integer>(10);
		stack.push(1);
		assertEquals(1, stack.size());
		stack.push(2);
		assertEquals(2, stack.size());
		stack.push(3);
		assertEquals(3, stack.size());
		assertEquals(3, stack.pop());
		assertEquals(2, stack.size());
		assertEquals(2, stack.pop());
		assertEquals(1, stack.size());
		assertEquals(1, stack.pop());
		assertEquals(0, stack.size());
	}

	/**
	 * Test method for adding and removing elements in interleaved order
	 */
	@Test
	void testAddMultElemInterleaved() {
		LinkedStack<Integer> stack = new LinkedStack<Integer>(10);
		stack.push(1);
		assertEquals(1, stack.pop());

		stack.push(2);

		stack.push(3);

		assertEquals(3, stack.pop());

		assertEquals(1, stack.size());

	}

	/**
	 * Test method for removing element from empty stack
	 */
	@Test
	void removeEmptyStack() {
		LinkedStack<Integer> stack = new LinkedStack<Integer>(10);
		Exception e1 = assertThrows(EmptyStackException.class, () -> stack.pop());
		assertEquals(null, e1.getMessage());
	}

	/**
	 * Test method for setCapacity
	 */
	@Test
	void testSetCapacity() {
		LinkedStack<Integer> stack = new LinkedStack<Integer>(10);
		stack.push(1);
		stack.push(2);
		assertDoesNotThrow(() -> stack.setCapacity(2));

		Exception e1 = assertThrows(IllegalArgumentException.class, () -> stack.setCapacity(1));
		assertEquals(null, e1.getMessage());
	}

}
