/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * List class implementing a LinkedList configuration. LinkedAbstractList holds 
 * objects in linked ListNode objects. LinkedAbstractList requires a capacity to be 
 * constructed, and the list will only hold the entered number of objects. LinkedAbstractList
 * can add, remove, set, and get objects to/from the list. LinkedAbstractList can also return the 
 * number of objects stored in the list with size. 
 * @author Travis Koekemoer
 * @param <E> the object type that the list will store
 */
public class LinkedAbstractList<E> extends AbstractList<E> {
	/** The number of objects stored in the list */
	private int size;
	/** The max number of objects that can be added to the list */
	private int capacity;
	/** The first ListNode stored in the list */
	private ListNode front;
	/** the last ListNode stored in the list */
	private ListNode back;
	
	private class ListNode {
		/** The object stored in the ListNode */
		E data;
		/** The next ListNode in the list */
		ListNode next;
		
		/**
		 * Constructor of ListNode object that does not provide the next value.
		 * @param data the object to store in the ListNode
		 */
		public ListNode(E data) {
			this.data = data;
			this.next = null;
		}
		
		/**
		 * Constructor of ListNode object that provides the next value.
		 * @param data the object to store in the ListNode
		 * @param next the next ListNode object in the list
		 */
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
		
	}
	
	/**
	 * Constructor for LinkedAbstractList. Receives the capacity of the list
	 * and sets it so that only that number of objects can be added to the list.
	 * @param capacity the max number of objects that can be stored in the list.
	 * @throws IllegalArgumentException if the capacity is less than 0 or less
	 * than the size.
	 */
	public LinkedAbstractList(int capacity) throws IllegalArgumentException {
		
		if (capacity < 0 || capacity < size()) {
			throw new IllegalArgumentException();
		}
		this.size = 0;
		this.capacity = capacity;
		this.front = null;	
		this.back = null;
	}
	
	/**
	 * Adds the entered object to the list at the received index. Objects already at the entered
	 * index and to the right will be right-shifted so that no data is lost. 
	 * @param idx the index where the object will be added
	 * @param value the object that will be added to the list
	 * @throws NullPointerException if the value of the entered object is null
	 * @throws IllegalArgumentException if the object to be added to the list is already in the list
	 * @throws IndexOutOfBoundsException if the entered index is larger than the size or smaller than 0
	 */
	@Override
	public void add(int idx, E value) throws NullPointerException, IllegalArgumentException, IndexOutOfBoundsException {
		if (value == null) {
			throw new NullPointerException();
		}
		ListNode temp = front;
		for (int i = 0; i < size; i++) {
			if (temp.data.equals(value)) {
				throw new IllegalArgumentException();
			}
			temp = temp.next;
		}
		if (idx > size() || idx < 0) {
			throw new IndexOutOfBoundsException();		
		}
		if (size == capacity) {
			throw new IllegalArgumentException();
		}
		
		if (idx == 0 && size == 0) {
			ListNode add = new ListNode(value);
			front = add;
			back = add;
		}
		else if (idx == 0) {
			ListNode add = new ListNode(value, front);
			front = add;
			
		}
		//new code
		else if(size + 1 == idx){
			ListNode add = new ListNode(value, front);
			back.next = add;
		} else {
			temp = front;
			for (int i = 0; i < idx - 1; i++) {
				temp = temp.next;
			}
			ListNode add = new ListNode(value, temp.next);
			temp.next = add;
		}
		size++;
	}
	
	/**
	 * Removes the object at the entered index. If the object is not in the list
	 * nothing happens. 
	 * @param idx the index of the object to remove
	 * @throws IndexOutOfBoundsException if the index is at or equal to the size 
	 * or less than 0. 
	 */
	@Override
	public E remove(int idx) throws IndexOutOfBoundsException {
		if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException();
		}
		ListNode temp;
		if (idx == 0) {
			temp = front;
			front = front.next;
			size--;
			return temp.data;
		}
		temp = front;
		for (int i = 0; i < idx - 1; i++) {
			//may not work
			if(i == size) {
				back = temp;
			}
			temp = temp.next;
			
		}
		E data = temp.next.data;
		temp.next = temp.next.next;
		size--;
		return data;
	}
	
	/**
	 * Adds the entered object to the list at the received index. This will replace the object
	 * already stored at the index. 
	 * @param idx the index where the object will be added
	 * @param value the object that will be added to the list
	 * @throws NullPointerException if the value of the entered object is null
	 * @throws IllegalArgumentException if the object to be added to the list is already in the list
	 * @throws IndexOutOfBoundsException if the entered index is larger than or equal to the size or smaller than 0
	 */
	@Override
	public E set(int idx, E value) throws NullPointerException, IllegalArgumentException, IndexOutOfBoundsException {
		if (value == null) {
			throw new NullPointerException();
		}
		ListNode temp = front;
		for (int i = 0; i < size; i++) {
			if (temp.data.equals(value)) {
				throw new IllegalArgumentException();
			}
			temp = temp.next;
		}
		if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException();
		}
		temp = front;
		if (idx == 0) {
			ListNode add = new ListNode(value, front.next);
			front = add;
			E data = temp.data;
			return data;
		}
		for (int i = 0; i < idx - 1; i++) {
			temp = temp.next;
		}
		ListNode set = new ListNode(value, temp.next.next);
		E data = temp.next.data;
		temp.next = set;
		return data;
	}

	/**
	 * Returns the number of objects in the list
	 * @return the number of objects in the list
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Returns the object at the entered index. 
	 * @param idx the index of the object that will be added
	 * @throws IndexOutOfBoundsException if the index requested is at or equal to size
	 * of less than 0.
	 * @return the object at the entered index
	 */
	@Override
	public E get(int idx) throws IndexOutOfBoundsException {
		if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException();
		}
		ListNode temp = front;
		if (size - idx == 1) {
			for (int i = 0; i < size - 1; i++) {
				temp = temp.next;
			}
			return temp.data;
		}
		for (int i = 0; i < idx; i++) {
			temp = temp.next;
		}
		return temp.data;
	}
	
	
	/**
	 * Changes the capacity of the list. 
	 * @param cap new capacity 
	 * @throws IllegalArgumentException if the new capacity is less than 0 or
	 * less than the amount of elements already in the list
	 */
	public void setCapacity(int cap) throws IllegalArgumentException {
		if(cap < 0 || cap < size()) {
			throw new IllegalArgumentException();
		}
		this.capacity = cap;
	}

}
