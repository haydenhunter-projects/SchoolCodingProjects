/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

/**
 * Queue interface. Implementations of the Queue interface will have the standard
 * queue methods. Enqueue will add to the back of the Queue, dequeue will remove from the front
 * of the Queue, isEmpty will check if the queue has any objects in it, size returns
 * the number of objects in the Queue, and setCapacity will set the max number of 
 * elements that the Queue can hold. 
 * @author jpkenlin
 *@param <E> The object type that the Queue will hold
 */
public interface Queue<E> {

	/**
	 * Adds the element to the top of the Queue. No mater how the Queue 
	 * is stored (array, linkedList, etc.) the enqueue should add the element 
	 * to the arbitrary "back" of the Queue. Elements can only be added if the 
	 * Queue is not at capacity. 
	 * @param element the element to add to the Queue
	 */
	void enqueue(E element);
	
	/**
	 * Removes the front element and returns it. No mater how the Queue 
	 * is stored (array, linkedList, etc.) the dequeue should remove the element 
	 * at the arbitrary "front" of the Queue. 
	 * @return E element at top of Stack
	 */
	E dequeue();
	
	/**
	 * Checks if the Queue has any elements. If the Queue is holding
	 * any elements, this will return false.
	 * @return true if empty
	 */
	boolean isEmpty();
	
	/**
	 * Returns the number of elements in the Queue.
	 * @return the number of elements in the Queue
	 */
	int size();
	
	/**
	 * Sets the max number of elements that can be added to the Queue.
	 * This will affect the enqueue method because if a user wants to enqueue
	 * an element when the Queue is at capacity the enqueue method must handle
	 * the situation. 
	 * @param capacity Queue capacity
	 */
	void setCapacity(int capacity);
}
