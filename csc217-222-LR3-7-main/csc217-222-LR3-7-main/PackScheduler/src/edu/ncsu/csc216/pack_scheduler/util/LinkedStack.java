package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * Array-based implementation of Stack. Defines the Stack methods as
 * detailed in the Stack interface. Elements are held in an array-based
 * list. The top of the stack is the end of the list. 
 * @author jpkenlin
 * @param <E> the object type that the stack will hold
 */
public class LinkedStack<E> implements Stack<E> {

	/** LinkedList stack  */
	private LinkedAbstractList<E> stack;

	/** the number of elements currently in the stack  */
	private int size;
	
	/**
	 * LinkedStack constructor. Sets the capacity to the entered amount.
	 * The stack list is instantiated and the size is set to 0.
	 * @param capacity the max number of elements that can be added to the stack
	 * @throws IllegalArgumentException if the capacity is less than 0
	 */
	public LinkedStack(int capacity) throws IllegalArgumentException {
		this.size = 0;
		stack = new LinkedAbstractList<E>(capacity);
	}
	
	/** 
	 * Adds the element to the top of the Stack. No mater how the Stack 
	 * is stored (array, linkedList, etc.) the push should add the element 
	 * to the arbitrary "top" of the Stack. Elements can only be added if the 
	 * Stack is not at capacity. 
	 * @param element E the object to add to the Stack
	 * @throws IllegalArgumentException if the capacity has been reached
	 */
	@Override
	public void push(E element) throws IllegalArgumentException {
		stack.add(0, element);
		size++;
		
	}

	/**
	 * Removes the top element and returns it. No mater how the Stack 
	 * is stored (array, linkedList, etc.) the pop should remove the element 
	 * at the arbitrary "top" of the Stack. 
	 * @return E element at top of Stack
	 * @throws EmptyStackException if the stack is empty
	 */
	@Override
	public E pop() throws EmptyStackException {
		if (size == 0) {
			throw new EmptyStackException();
		}
		size--;
		return stack.remove(0);
	}

	/**
	 * Checks if the Stack has any elements. If the Stack is holding
	 * any elements, this will return false.
	 * @return true if empty
	 */
	@Override
	public boolean isEmpty() {
		return stack.size() == 0;
	}

	/**
	 * Gets the number of elements in the Stack.
	 * @return number of elements in the Stack
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Sets the max number of elements that can be added to the Stack.
	 * This will affect the push method because if a user wants to push
	 * an element when the Stack is at capacity the push method must handle
	 * the situation. 
	 * @param capacity Stack capacity
	 * @throws IllegalArgumentException if the entered capacity is less than 0 or 
	 * less than the current size of the stack
	 */
	@Override
	public void setCapacity(int capacity) throws IllegalArgumentException {
		stack.setCapacity(capacity);
	}

}
