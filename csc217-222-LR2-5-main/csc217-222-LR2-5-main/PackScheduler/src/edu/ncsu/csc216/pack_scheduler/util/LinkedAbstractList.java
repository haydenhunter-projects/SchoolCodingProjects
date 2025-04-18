/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * The design of the course roll functionality 
 * calls for a custom implementation of an array 
 * list that doesn’t allow for null elements or duplicate 
 * elements as defined by the equals() method, and has a capacity.
 * @author nolanhurst
 * @author hchunter
 * @param <E> the list type
 *
 */
public class LinkedAbstractList<E> extends AbstractList<E> {
	
	/**
	 * the front element in a list
	 */
	private ListNode front;
	/**
	 * the size of the list
	 */
	private int size;
	/**
	 * the capacity of the list
	 */
	private int capacity;
	
	/**
	 * The constructor of a linked abstract list
	 * @throws IllegalArgumentException when capacity is less than 0 and capacity is less than size
	 * @param capacity the capacity of the list
	 */
	public LinkedAbstractList(int capacity) {
		this.front = null;
		this.size = 0;
		
		if (capacity < 0) {
			throw new IllegalArgumentException();
		}
		
		if (capacity < size) {
			throw new IllegalArgumentException();
		}
		
		this.capacity = capacity;
	}
	
	/**
	 * the add method will add an element at a specific index
	 * @param idx the index you want to add the element at
	 * @param e the element you want to add the index
	 * @throws NullPointerException when the element is null, IndexOutOfBoundsException when the index is less than 0 or greater than size, 
	 * IllegalArgumentException if you are trying to set the element equal to itself or when capacity equals size
	 */
	@Override
	public void add(int idx, E e) {
		if(e == null) {
			throw new NullPointerException();
			
		}
		
		if (idx < 0 || idx > size()) {
			throw new IndexOutOfBoundsException();
		}

		
		if(capacity == size) {
			throw new IllegalArgumentException();
		}
		
		ListNode checker = front;
		for (int k = 0; k < size(); k++) {
			if (checker.data.equals(e)) {
				throw new IllegalArgumentException();
			}
			checker = checker.next;
		}
		
		ListNode newNode = new ListNode(e);
		
		if (idx == 0) {
			if (size() > 0) {
				ListNode check = front;
				for (int j = 0; j < size(); j++) {
					if (check.data.equals(e)) {
						throw new IllegalArgumentException();
					}
					check = check.next;
				}
				front = new ListNode(e, front);
			} else {
				front = newNode;
			}
		} else {
			ListNode current = front;
			ListNode nextNode = current.next;
			for (int i = 0; i < idx - 1; i++) {
				
				current = current.next;
				nextNode = current.next;
			}
			
			if (nextNode != null) {
				current.next = newNode;
				newNode.next = nextNode;
			} else {
				current.next = newNode;
			} 
			
			
		
		}
				
		size++;
	}
	
	
	/**
	 * the list node class is a helper class for the abstract linked list
	 * @author hchunter
	 *
	 */
	private class ListNode {
		/**
		 * the element of a node
		 */
		private E data;
		/**
		 * next points to the next node in the index
		 */
		ListNode next;
		
		/**
		 * a constructor of a node with an element
		 * @param data the element 
		 */
		public ListNode(E data) {
			this.data = data;
		}
		
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}

	}
	
	/**
	 * removes an element at a given index
	 * @param idx the index of the element to be deleted
	 * @throws IndexOutOfBoundsException when index is less than 0 or greater than the size of the list
	 * @return the element you removed at the index
	 */
	@Override
	public E remove(int idx) {
		if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException();
		}
		
		E returnE;
		
		if (idx == 0) {
			returnE = front.data;
			front = front.next;
		}
		else {
			ListNode previous = front;
			ListNode current = previous.next;
			ListNode nextNode = null;
			
			
			if (current != null) {
				nextNode = current.next;
			}
			
			for (int i = 0; i < idx - 1; i++) {
				previous = previous.next;
				current = previous.next;
				if (current != null) {
					nextNode = current.next;
				}
			}
			
			returnE = current.data;
			
			previous.next = nextNode;
		}
		
		size--;
		return returnE;
	}

	/**
	 * overrides the element at a given index
	 * @param idx the index of the element you are overwriting.
	 * @param e the element to set
	 * @throws NullPointerException when the element is null, IndexOutOfBoundsException when the index is less than 0 or greater than size, 
	 * IllegalArgumentException if you are trying to set the element equal to itself
	 * @return the element you set at the index
	 */
	@Override
	public E set(int idx, E e) {
		if (e == null) {
			throw new NullPointerException();
		}
		if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException();
		}
		ListNode current = front;
		for (int i = 0; i < size(); i++) {
			if (e.equals(current.data)) {
				throw new IllegalArgumentException();
			}
			current = current.next;
		}
		
		current = front;
		for (int j = 0; j < idx; j++) {
			current = current.next;
		}
		
		E returnE = current.data;
		current.data = e;
		
		
		return returnE;
	}
	

	/**
	 * returns the size of the list
	 * @return the size of the list
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * returns an element in a list
	 * @param index the index of the element you want returned
	 * @throws IndexOutOfBoundsException when index is less than 0 or greater than the size of the list.
	 * @return the element at a specific index
	 */
	@Override
	public E get(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		
		ListNode current = front;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		
		return current.data;
	}
	
}
