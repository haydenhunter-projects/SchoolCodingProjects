/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

/**
 * Test class for ArrayQueue
 * @author jpkenlin
 *
 */
class ArrayQueueTest {

	/**
	 * Test method for enqueueing single element
	 */
	@Test
	void testAddSingleElement() {
		ArrayQueue<Integer> queue = new ArrayQueue<Integer>(10);
		
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
		ArrayQueue<Integer> queue = new ArrayQueue<Integer>(10);
		
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
	 * Test method for enqueueing and dequeueing elements in interleaaved order
	 */
	@Test
	void testInterleavedEnqueueAndDequeue() {
		ArrayQueue<Integer> queue = new ArrayQueue<Integer>(10);
		
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
	 * Test method for enqueueing element to a list already at capacity
	 */
	@Test
	void testAddAtCapacity() {
		ArrayQueue<Integer> queue = new ArrayQueue<Integer>(3);
		
		queue.enqueue(1);
		assertFalse(queue.isEmpty());
		queue.enqueue(2);
		queue.enqueue(3);
		assertThrows(IllegalArgumentException.class, 
				() -> queue.enqueue(4));
		
		ArrayQueue<Integer> queue2 = new ArrayQueue<Integer>(2);
		
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
		ArrayQueue<Integer> queue = new ArrayQueue<Integer>(3);
		
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
		ArrayQueue<Integer> queue = new ArrayQueue<Integer>(3);
		
		assertThrows(IllegalArgumentException.class, 
				() -> new ArrayQueue<Integer>(-1));
		
		queue.enqueue(1);
		queue.enqueue(2);
		assertDoesNotThrow( () -> queue.setCapacity(2));
		assertThrows(IllegalArgumentException.class, 
				() -> queue.setCapacity(1));
	}

}
