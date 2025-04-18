/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

/**
 * LinkedList class with recursive implementation. This class implements
 * most of the typical list class methods. This class can add generically to the 
 * end or at a specific index, can get from an index, remove a specific element
 * or a specific index, set a specific index to a new element, check if the list 
 * contains a specific element, return the size, and check if the list is empty.
 * This list does not allow duplicate elements.
 * @author jpkenlin
 * @param <E> the object type that the list will store
 */
public class LinkedListRecursive<E> {
	/** The first element in the list */
	private ListNode front;
	/** The number of elements in the list */
	private int size;

	/**
	 * Constructor for the list. Sets the front element to null and 
	 * the initial size to 0.
	 */
	public LinkedListRecursive() {
		front = null;
		size = 0;
	}
	
	/**
	 * Returns true if the list has no elements in it.
	 * @return true or false based on if elements are in the list
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Returns the number of elements in the list.
	 * @return the number of elements in the list
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Adds the entered element to the end of the list. 
	 * @param element the element to be added to the end of the list
	 * @return true if the element was added successfully
	 * @throws NullPointerException if the element to add is null
	 * @throws IllegalArgumentException if the list already contains the element
	 */
	public boolean add(E element) throws NullPointerException, IllegalArgumentException {
		if(element == null) {
			throw new NullPointerException();
		}
		
		if (contains(element)) {
			throw new IllegalArgumentException();
		}
		else if (size == 0) {
			ListNode update = new ListNode(element, null);
			front = update;
			size++;
			return true;
		}
		front.add(size, element);
		size++;
		return true;
	}
	
	/**
	 * Adds the entered element to the entered index. The element previously at 
	 * the entered index and all elements to the right of it are "shifted" to the right.
	 * @param index the index where the element should be added
	 * @param element the element to be added to the end of the list
	 * @throws NullPointerException if the element to add is null
	 * @throws IllegalArgumentException if the list already contains the element
	 * @throws IndexOutOfBoundsException if the index to add the element is less than 
	 * 0 or greater than the list size
	 */
	public void add(int index, E element) throws NullPointerException, IllegalArgumentException, IndexOutOfBoundsException {
		if(element == null) {
			throw new NullPointerException();
		}
		
		if (contains(element)) {
			throw new IllegalArgumentException();
		}
		else if(index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		else if (size == 0) {
			ListNode update = new ListNode(element, null);
			front = update;
			size++;
			return;
		}
		else if (index == 0) {
			ListNode update = new ListNode(element, front);
			front = update;
			size++;
			return;
		}
		
		
		front.add(index, element);
		size++;
	}
	
	/**
	 * Returns the element at the entered index.
	 * @param index the index of the element to be retrieved
	 * @return the element at the entered index
	 * @throws IllegalArgumentException if the list is empty
	 * @throws IndexOutOfBoundsException if the entered index is less than 0 or greater than 
	 * or equal to the list size
	 */
	public E get(int index) throws IllegalArgumentException, IndexOutOfBoundsException {
		
		if(isEmpty()) {
			throw new IndexOutOfBoundsException();
		}
		else if(size <= index || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		return front.get(index);
	}
	
	/**
	 * Removed the entered element from the list. If the element does not exist in 
	 * the list, false is returned. This includes if the list is empty. Otherwise,
	 * the element is removed from the list and all elements to the right of it are 
	 * "left-shifted" so that there are no gaps in the list.
	 * @param element the element to be removed from the list
	 * @return true if the element is successfully removed from the list
	 * @throws IllegalArgumentException if the entered element to remove is null
	 */
	public boolean remove(E element) throws IllegalArgumentException {
		
		if(element == null) {
			return false;
		}
		
		if(isEmpty()) {
			return false;
		}
		
		if(front.data == element) {
			front = front.next;
			size--;
			return true;
		}
		boolean toReturn = front.remove(element);
		if (toReturn) {
			size--;
		}
		return toReturn;
		
	}
	
	/**
	 * Removes and returns the element at the entered index. If the element is 
	 * removed (no exceptions are thrown), all elements to the right of it 
	 * are "left-shifted" so that there are no gaps in the list.
	 * @param index the index of the element to remove
	 * @return the element that was removed
	 * @throws IllegalArgumentException if the list is empty
	 * @throws IndexOutOfBoundsException if the index is less than 0 or greater
	 * than or equal to the size of the list
	 */
	public E remove(int index) throws IllegalArgumentException, IndexOutOfBoundsException {
		if(isEmpty()) {
			throw new IndexOutOfBoundsException();
		}
		else if(size <= index || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		if(index == 0) {
			E temp = front.data;
			front = front.next;
			size--;
			return temp;
		}
		size--;
		return front.remove(index - 1);
	}
	
	/**
	 * Changed the element at the entered index to the entered element. If there was 
	 * an element at the entered index, it is returned and is no longer part of the list.
	 * If the entered index is at the size of the list, no element is removed, the element
	 * is added, and null is returned.
	 * @param index the index whose stored element will change to the entered element
	 * @param element the element that the entered index will now hold
	 * @return the element that was previously stored an the entered element
	 * @throws NullPointerException if the new element to set is null
	 * @throws IndexOutOfBoundsException if the index to change is less than 0 
	 * or greater than the size of the list
	 * @throws IllegalArgumentException if the list already contains the new element
	 */
	public E set(int index, E element) throws NullPointerException, IndexOutOfBoundsException, IllegalArgumentException {
		if(element == null) {
			throw new NullPointerException();
		}
		if(front == null) {
			throw new IndexOutOfBoundsException();
		}
		
		if(size < index || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		if(contains(element)) {
			throw new IllegalArgumentException();
		}
		
		if(index == size) {
			size++;
		}
		
		return front.set(index, element);
	}
	
	/**
	 * Checks if the entered element is already in the list.
	 * @param element the element to search for in the list
	 * @return true if the element is already in the list
	 */
	public boolean contains(E element) {
		if (isEmpty()) {
			return false;
		}
		return front.contains(element);
	}
	
	/**
	 * Private ListNode class for LinkedListRecursive. This ListNode class
	 * handles the recursive functionality for the LinkedListRecursive class.
	 * Methods add(both), get, remove(both), set, and contains from the public
	 * LinkedListRecursive class call the methods in this ListNode class to 
	 * carry out the desired functionality.
	 * @author jpkenlin
	 *
	 */
	private class ListNode {
		/** The object stored in this given element of the list */
		public E data;
		/** The next element in the list */
		public ListNode next;
		
		/**
		 * Constructs the ListNode object with the entered values. 
		 * @param data the data that this ListNode will store
		 * @param next the next ListNode in the list
		 */
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
		
		/**
		 * Adds the entered element at the entered index. Recursively travels
		 * the list to add the element at the desired index. 
		 * @param index the index where the element should be added
		 * @param element the new element to add to the list
		 */
		public void add(int index, E element) {
		
			if(index == 1) {
				this.next = new ListNode(element, this.next);	
			}
			else {
				if (next == null) {
					next = new ListNode(element, null);
				}
				this.next.add(index - 1, element);
			}
			
		}
		
		/**
		 * Returns the element at the entered index. Recursively travels
		 * through the list until the desired index is reached. Then, the element is returned. 
		 * @param index the index of the element that should be returned
		 * @return the element at the entered index
		 */
		public E get(int index) {
			
			if(index == 0) {
				return this.data;
			}
			else {
				return this.next.get(index - 1);
			}
			
		}
		
		/**
		 * Removes and returns the element at the entered index. Recursively travels
		 * through the list until the desired index is reached. Then, the element is 
		 * removed and returned to the caller.
		 * @param index the index of the element that should be removed
		 * @return the element at the entered index
		 */
		public E remove(int index) {
			
			if(index == 0) {
				E temp = this.next.data;
				this.next = this.next.next;
				return temp;
			}
			else {
				return this.next.remove(index - 1);
			}
			
		}
		
		/**
		 * Removes and returns the entered element. Recursively travels
		 * through the list until the desired element is found. Then, the element is 
		 * removed and true is returned to the caller. If the element is not found
		 * when the end of the list is reached, false is returned.
		 * @param element the element that should be removed from the list
		 * @return true if the element was removed from the list
		 */
		public boolean remove(E element) {
			
			if(this.next == null) {
				return false;
			} else if (this.next.data.equals(element)) {
				this.next = this.next.next;
				return true;
			}
			else if (this.next != null) {
				return this.next.remove(element);
			}
			return false;
		
		}
		
		/**
		 * Changes the data stored at the entered index. Recursively travels through
		 * the list to the index that should be changed, and then changes the data 
		 * at that index to the entered element. If the entered index is one 
		 * past the last element in the list, a new ListNode is created and 
		 * added to the end of the list and null is returned.
		 * @param index the index whose element should be updated
		 * @param element the new element that the index will now hold
		 * @return the element that was previously stored in the entered index
		 */
		public E set(int index, E element) {
			
			if(index == 0) {
				E temp = this.data;
				this.data = element;
				return temp;
			}
			else {
				if (this.next == null) {
					this.next = new ListNode(element, null);
					return null;
				}
				return this.next.set(index - 1, element);
			}
		}
		
		/**
		 * Checks if the entered element is in the list. Recursively travels
		 * through the list checking if the data stored in any ListNode matches
		 * the entered element. If no match is found when the end of the list is reached,
		 * false is returned. 
		 * @param element the element to search for in the list
		 * @return true if the element is in the list
		 */
		public boolean contains(E element) {
			if (data.equals(element)) {
				return true;
			}
			else if (next != null) {
				return next.contains(element);
			}
			return false;
		}
	}
	
}