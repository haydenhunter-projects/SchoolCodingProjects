/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * Array-based implementation of Queue. Defines the Queue methods as
 * detailed in the Queue interface. Elements are held in an array-based
 * list. The front of the queue is the end of the list and the back of the 
 * queue is the front of the list. 
 * @author jpkenlin
 *@param <E> the object type that the queue will hold
 */
public class ArrayQueue<E> implements Queue<E> {
	
	/** number of elements currently in the list */
	private int size;
	/** max number of elements that the list can hold */
	private int capacity;
	/** array based stack */
	private ArrayList<E> arrayQueue;
	
	/**
	 * ArrayQueue constructor. Sets the capacity to the entered amount.
	 * The queue list is instantiated and the size is set to 0.
	 * @param capacity the max number of elements that the queue can hold
	 * @throws IllegalArgumentException if the entered capacity is less than 0
	 */
	public ArrayQueue(int capacity) throws IllegalArgumentException {
		if (capacity < 0) {
			throw new IllegalArgumentException();
		}
		this.capacity = capacity;
		this.size = 0;
		arrayQueue = new ArrayList<E>();
	}

	/**
	 * Adds the element to the top of the Queue. No mater how the Queue 
	 * is stored (array, linkedList, etc.) the enqueue should add the element 
	 * to the arbitrary "back" of the Queue. Elements can only be added if the 
	 * Queue is not at capacity. 
	 * @param element the element to add to the Queue
	 * @throws IllegalArgumentException if the capacity has been reached
	 */
	@Override
	public void enqueue(E element) throws IllegalArgumentException {
		if (size < capacity) {
			arrayQueue.add(0, element);
			size++;
		}
		else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Removes the front element and returns it. No mater how the Queue 
	 * is stored (array, linkedList, etc.) the dequeue should remove the element 
	 * at the arbitrary "front" of the Queue. 
	 * @return E element at top of Stack
	 * @throws NoSuchElementException if the queue is empty
	 */
	@Override
	public E dequeue() throws NoSuchElementException {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		size--;
		return arrayQueue.remove(size);
	}

	/**
	 * Checks if the Queue has any elements. If the Queue is holding
	 * any elements, this will return false.
	 * @return true if empty
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Returns the number of elements in the Queue.
	 * @return the number of elements in the Queue
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Sets the max number of elements that can be added to the Queue.
	 * This will affect the enqueue method because if a user wants to enqueue
	 * an element when the Queue is at capacity the enqueue method must handle
	 * the situation. 
	 * @param capacity Queue capacity
	 */
	@Override
	public void setCapacity(int capacity) {
		if (capacity < this.size) { 
			throw new IllegalArgumentException();
		}
		this.capacity = capacity;
		
	}

}
