package edu.ncsu.csc216.pack_scheduler.util;

/**
 * Stack interface. Implementations of the Stack interface will have the standard
 * stack methods. Push will add to the top of the Stack, pop will remove from the top
 * of the Stack, isEmpty will check if the stack has any objects in it, size returns
 * the number of objects in the Stack, and setCapacity will set the max number of 
 * elements that the Stack can hold. 
 * @author smithej
 *
 * @param <E> The object type that the Stack will hold
 */
public interface Stack<E> {
	/** 
	 * Adds the element to the top of the Stack. No mater how the Stack 
	 * is stored (array, linkedList, etc.) the push should add the element 
	 * to the arbitrary "top" of the Stack. Elements can only be added if the 
	 * Stack is not at capacity. 
	 * @param element E the object to add to the Stack
	 */
	void push(E element);
	
	
	/**
	 * Removes the top element and returns it. No mater how the Stack 
	 * is stored (array, linkedList, etc.) the pop should remove the element 
	 * at the arbitrary "top" of the Stack. 
	 * @return E element at top of Stack
	 */
	E pop();
	
	
	/**
	 * Checks if the Stack has any elements. If the Stack is holding
	 * any elements, this will return false.
	 * @return true if empty
	 */
	boolean isEmpty();
	
	/**
	 * Gets the number of elements in the Stack.
	 * @return number of elements in the Stack
	 */
	int size();
	
	/**
	 * Sets the max number of elements that can be added to the Stack.
	 * This will affect the push method because if a user wants to push
	 * an element when the Stack is at capacity the push method must handle
	 * the situation. 
	 * @param capacity Stack capacity
	 */
	void setCapacity(int capacity);
}
