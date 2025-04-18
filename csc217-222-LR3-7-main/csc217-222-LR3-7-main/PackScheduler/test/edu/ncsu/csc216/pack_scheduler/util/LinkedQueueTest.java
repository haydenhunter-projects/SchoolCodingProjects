/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

/**
 * Test class for LinkedQueue
 * @author jpkenlin
 *
 */
class LinkedQueueTest {

	/**
	 * Test method for enqueueing single element
	 */
	@Test
	void testAddSingleElement() {
		LinkedQueue<Integer> queue = new LinkedQueue<Integer>(10);
		
		queue.enqueue(1);
		assertEquals(1, queue.size());
		assertEquals(1, queue.dequeue());
		assertEquals(0, queue.size());
	}
	
	/**
	 * Test method for enqueueing multiple elements
	 */
	@Test
	void testAddMultipleElements() {
		LinkedQueue<Integer> queue = new LinkedQueue<Integer>(10);
		
		queue.enqueue(1);
		assertEquals(1, queue.size());
		queue.enqueue(2);
		assertEquals(2, queue.size());
		queue.enqueue(3);
		assertEquals(3, queue.size());
		assertEquals(1, queue.dequeue());
		assertEquals(2, queue.size());
		assertEquals(2, queue.dequeue());
		assertEquals(1, queue.size());
		assertEquals(3, queue.dequeue());
		assertEquals(0, queue.size());
		assertTrue(queue.isEmpty());
		
	}
	
	/**
	 * Test method for enqueueing and dequeueing elements in interleaved order
	 */
	@Test
	void testInterleavedEnqueueAndDequeue() {
		LinkedQueue<Integer> queue = new LinkedQueue<Integer>(10);
		
		queue.enqueue(1);
		assertEquals(1, queue.size());
		queue.enqueue(2);
		assertEquals(2, queue.size());
		assertEquals(1, queue.dequeue());
		queue.enqueue(3);
		assertEquals(2, queue.size());
		assertEquals(2, queue.dequeue());
		assertEquals(1, queue.size());
		assertEquals(3, queue.dequeue());
	}
	
	/**
	 * Test method for enqueueing element to list already at capacity
	 */
	@Test
	void testAddAtCapacity() {
		LinkedQueue<Integer> queue = new LinkedQueue<Integer>(3);
		
		queue.enqueue(1);
		assertFalse(queue.isEmpty());
		queue.enqueue(2);
		queue.enqueue(3);
		assertThrows(IllegalArgumentException.class, 
				() -> queue.enqueue(4));
		
		LinkedQueue<Integer> queue2 = new LinkedQueue<Integer>(2);
		
		queue2.enqueue(1);
		queue2.enqueue(2);
		assertThrows(IllegalArgumentException.class, 
				() -> queue2.enqueue(3));
		
	}
	
	/**
	 * Test method for dequeueing element from an empty queue
	 */
	@Test
	void testDequeueFromEmptyQueue() {
		LinkedQueue<Integer> queue = new LinkedQueue<Integer>(3);
		
		assertThrows(NoSuchElementException.class, 
				() -> queue.dequeue());
		
		queue.enqueue(1);
		queue.dequeue();
		assertThrows(NoSuchElementException.class, 
				() -> queue.dequeue());
		
	}

	/**
	 * Test method for setCapacity
	 */
	@Test
	void testSetCapacity() {
		LinkedQueue<Integer> queue = new LinkedQueue<Integer>(3);
		
		assertThrows(IllegalArgumentException.class, 
				() -> new LinkedQueue<Integer>(-1));
		
		queue.enqueue(1);
		queue.enqueue(2);
		assertDoesNotThrow( () -> queue.setCapacity(2));
		assertThrows(IllegalArgumentException.class, 
				() -> queue.setCapacity(1));
	}

}
