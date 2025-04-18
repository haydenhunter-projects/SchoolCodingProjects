package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * Array-based implementation of Stack. Defines the Stack methods as
 * detailed in the Stack interface. Elements are held in an array-based
 * list. The top of the stack is the end of the list. 
 * @author smithej
 * @param <E> the object type that the stack will hold
 */
public class ArrayStack<E> implements Stack<E> {
	/** number of elements currently in the list */
	private int size;
	/** max number of elements that the list can hold */
	private int capacity;
	/** array based stack */
	private ArrayList<E> arrayStack;
	
	
	/**
	 * ArrayStack constructor. Sets the capacity to the entered amount.
	 * The stack list is instantiated and the size is set to 0.
	 * 
	 * @param capacity the max number of elements that can be added to the stack
	 * @throws IllegalArgumentException if the capacity is less than 0
	 */
	public ArrayStack(int capacity) throws IllegalArgumentException {
		this.size = 0;
		setCapacity(capacity);
		arrayStack = new ArrayList<E>();
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
		// TODO Auto-generated method stub
		if(size == capacity) {
			throw new IllegalArgumentException();
		}
		arrayStack.add(size, element);
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
		// TODO Auto-generated method stub
		if(size == 0) {
			throw new EmptyStackException();
		}
		size = size - 1;
		return arrayStack.remove(size);
	}

	/**
	 * Checks if the Stack has any elements. If the Stack is holding
	 * any elements, this will return false.
	 * @return true if empty
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}

	/**
	 * Gets the number of elements in the Stack.
	 * @return number of elements in the Stack
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
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
		if(capacity < size || capacity < 0) {
			throw new IllegalArgumentException();
		}
		this.capacity = capacity;
		
	}

}
