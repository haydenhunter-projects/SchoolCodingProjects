package edu.ncsu.csc216.pack_scheduler.util;

import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * List class implementing a LinkedList configuration. LinkedList implements 
 * AbstractSequentialList. LinkedList holds objects in linked ListNode objects. LinkedList
 * can add, remove, and set objects to/from the list. LinkedList can also return the 
 * number of objects stored in the list with size. LinkedList also has an iterator
 * method that calls the LinkedListIterator and returns the constructed object. The 
 * Iterator can be used to get objects at specific indices in the list.
 * @author smithej
 *
 * @param <E> the type of the objects that will be stored in the list
 */
public class LinkedList<E> extends java.util.AbstractSequentialList<E> {

	/** The number of elements in the list */
	private int size;
	/** the first element in the list */
	private ListNode front;
	/** The lase element in the list*/
	private ListNode back;
	
	/** 
	 * Constructor for the LinkedList. Does not take in any parameters
	 * and sets the initial size of the list to 0.
	 */
	public LinkedList() {
		front = new ListNode(null);
		back = new ListNode(null);
		front.next = back;
		back.prev = front;
		size = 0;
		
	}
	
	/** 
	 * Returns the number of elements in the list.
	 * @return the size
	 */
	@Override
	public int size() {
		return size;
	}

	/** 
	 * Inserts the new element at the entered index. This does not replace
	 * any element, it simply inserts the element at the entered index. If there
	 * was an element already at that index, that element and any elements higher
	 * on the list are shifted up one index to make room for the new element.
	 * @param index the index where the element should be inserted
	 * @param element the new element to be added
	 * @throws NullPointerException if the entered element is null
	 * @throws IllegalArgumentException  if the element to be added already exists in the list
	 * @throws IndexOutOfBoundsException if the entered index is less than 0 or greater than the size of the list
	 */
	@Override
	public void add(int index, E element) throws NullPointerException, IllegalArgumentException, IndexOutOfBoundsException {
		if (element == null) {
			throw new NullPointerException();
		}
		if (size > 0 && contains(element)) {
			throw new IllegalArgumentException();
		}
		if(index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		}

		ListIterator<E> iterator = listIterator(index);
		iterator.add(element);
	}
	
	/**
	 * Test to check if an element is in the list. If the element is in
	 * the list, true is returned, if not, false is returned. 
	 * @return true if the element is in the list
	 */
	@Override
	public boolean contains(Object element) {
		ListIterator<E> iterator = listIterator(0);
		while (iterator.hasNext()) {
			if (iterator.next() == element) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Replaces the element at the entered index. The entered element is used to 
	 * replace the element at the entered index. This can be used to add an element
	 * to the end of the list even though there is "technically" no element there. 
	 * @return E element the element that the new element replaced
	 * @throws IllegalArgumentException if the entered element already exists in the list
	 * @throws IndexOutOfBoundsException if the entered index is less than 0 or greater than the size of the list
	 */
	@Override
	public E set(int index, E element) throws IllegalArgumentException, IndexOutOfBoundsException {
		ListIterator<E> iterator1 = listIterator(0);
		int counter = 0;
		while (iterator1.hasNext()) {
			if (iterator1.next() == element) {
				if (counter == index) {
					break;
				}
				else {
					throw new IllegalArgumentException();
				}
			}
			counter++;
		}
//		if(contains(element)) {
//			throw new IllegalArgumentException();
//		}
		if(index < 0) {
			throw new IndexOutOfBoundsException();
		}
		E returnElement = null;
		if (index >= size) {
			if (size == 0) {
				throw new IndexOutOfBoundsException();
			}
			ListIterator<E> iterator = listIterator(index);
			iterator.add(element);
		}
		else {
			ListIterator<E> iterator = listIterator(index);
			returnElement = iterator.next();
			iterator.set(element);
		}
		return returnElement;
	}
	
	/**
	 * Removes the element at the entered index. The element at the entered index is 
	 * removed from the list and returned. 
	 * @return E the element that was removed from the list
	 * @throws IndexOutOfBoundsException if the index that should have its element removed
	 * is less than 0 or greater than or equal to the size of the list
	 */
	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		ListIterator<E> iterator = listIterator(index);
		E returnElement = iterator.next();
		iterator.remove();
		return returnElement;
	}

	/**
	 * Initializes the iterator object. The iterator object is constructed
	 * and sits between the element at the entered index and the previous element.
	 * Thus, if next() is called, the returned element would be the element 
	 * at the index that was entered into the constructor. 
	 * @return ListIterator the ListIterator object
	 */
	@Override
	public ListIterator<E> listIterator(int index) {
		if(index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		LinkedListIterator iterator = new LinkedListIterator(index);
		return iterator;
	}
	
	
	
	
	/**
	 * The private ListNode class. This class for ListNodes can be used for doubly-linked lists
	 * since each node has a next and a previous field. 
	 * @author smithej
	 *
	 */
	private class ListNode {
		/** data stored in the node */
		public E data;
		/** the next ListNode */
		public ListNode next;
		/** The previous ListNode */
		public ListNode prev;
		
		/**
		 * Constructor with only entered data. Constructs a ListNode
		 * object with the entered data. The next and previous references
		 * are set to null.
		 * @param data the data to store in the node
		 */
		public ListNode(E data) {
			this.data = data;
			next = null;
			prev = null;
		}
		
		/**
		 * Constructor with data, a next reference, and a previous reference.
		 * Constructs a ListNode object and sets each of the fields to their
		 * entered values.
		 * @param data the data to store in the node
		 * @param prev the previous ListNode object in the list
		 * @param next the next ListNode object in the list
		 */
		public ListNode(E data, ListNode prev, ListNode next) {
			this.data = data;
			this.next = next;
			this.prev = prev;
		}
		
		
		
	}
	
	/**
	 * Iterator class for the LinkedList. The iterator handles most normal 
	 * functions of manipulating a list. Users can add, remove, set, get (through
	 * next() and previous()), and check if the next and previous elements have values.
	 * @author jpkenlin
	 */
	private class LinkedListIterator implements ListIterator<E> {
		/** The ListNode at the "front" of the iterator */
		public ListNode next;
		/** The ListNode at the "back" of the iterator */
		public ListNode prev;
		/** The index value of the previous node */
		public int previousIndex;
		/** The index value of the next node */
		public int nextIndex;
		/** If next() of previous() is called the element that was moved across is stored in lastRetrieved */
		public ListNode lastRetrieved;
		
		/**
		 * Constructor for the iterator. The iterator will move until
		 * the next field holds the node at the entered index. The next
		 * next() call will return the element at the index that was entered
		 * to this constructor. 
		 * @param index the index that the will be at the "front" of the iterator
		 */
		public LinkedListIterator(int index) {
			this.next = front.next;
			this.prev = front;
			this.previousIndex = -1;
			this.nextIndex = 0;
			lastRetrieved = null;
			
			
			for(int i = 0; i < index; i++) {
				prev = next;
				next = next.next;
				previousIndex++;
				nextIndex++;
			}
		}
		
		/**
		 * Returns true of the next element's data is not null.
		 * @return boolean true of the next element is not null
		 */
		@Override
		public boolean hasNext() {
			return next.data != null;
		}

		/**
		 * Moves the iterator one position towards the back. Returns the data
		 * in the next element and moves the iterator so that the element whose
		 * data was just returned is not the previous element. 
		 * @return the data in the next element
		 * @throws NoSuchElementException if the next element's data is null
		 */
		@Override
		public E next() throws NoSuchElementException {
			
			if(next.data == null) {
				throw new NoSuchElementException();
			}
			
			E temp = next.data;
			prev = next;
			next = next.next;
			nextIndex++;
			previousIndex++;
			lastRetrieved = prev;
			return temp;
		}

		/**
		 * Returns true of the previous element's data is not null.
		 * @return boolean true of the previous element is not null
		 */
		@Override
		public boolean hasPrevious() {
			return prev.data != null;
		}

		/**
		 * Moves the iterator one position towards the front. Returns the data
		 * in the previous element and moves the iterator so that the element whose
		 * data was just returned is not the next element. 
		 * @return the data in the previous element
		 * @throws NoSuchElementException if the previous element's data is null
		 */
		@Override
		public E previous() {
			// TODO Auto-generated method stub
			if(prev.data == null) {
				throw new NoSuchElementException();
			}
			E temp = prev.data;
			next = prev;
			prev = prev.prev;
			previousIndex--;
			nextIndex--;
			lastRetrieved = next;
			return temp;
		}

		/**
		 * Returns the index of the element that is next in the list.
		 * @return int the index of the element that is next in the list
		 */
		@Override
		public int nextIndex() {
			return nextIndex;
		}

		/**
		 * Returns the index of the element that is previous in the list.
		 * @return int the index of the element that is previous in the list
		 */
		@Override
		public int previousIndex() {
			return previousIndex;
		}

		/**
		 * Removes the element stored in lastRetrieved. If either next() or previous() 
		 * was the last method called, then this method returns the value in the lastRetreived
		 * variable. If next() was the last called, then lastRetrieved holds what is currently
		 * in the previous index. If previous was the last called, then lastRetrieved holds
		 * what is currently in the next index. If the last called method was not one of these
		 * (or hasPrevious or hasNext) then lastRetrieved is null and this method will throw an
		 * exception.
		 * @throws IllegalStateException if the lastRetrieved variable is null. This means
		 * that next() or previous() was not the most recently called method
		 */
		@Override
		public void remove() throws IllegalStateException {			
			if(lastRetrieved == null) {
				throw new IllegalStateException();
			}
			
			if(lastRetrieved == next) {
				next = next.next;
			}
			else if(lastRetrieved == prev) {
				prev = prev.prev;
				prev.next = prev.next.next;
			}
			
			lastRetrieved = null;
			size--;
		}

		/**
		 * Replaces the data in lastRetrieved with the entered element. If either next() or previous() 
		 * was the last method called, then this method replaces the data in the lastRetreived
		 * variable. If next() was the last called, then lastRetrieved holds what is currently
		 * in the previous index. If previous was the last called, then lastRetrieved holds
		 * what is currently in the next index. If the last called method was not one of these
		 * (or hasPrevious or hasNext) then lastRetrieved is null and this method will throw an
		 * exception.
		 * @throws NullPointerException if the entered element to set is null
		 * @throws IllegalArgumentException if the lastRetrieved variable is null. This means
		 * that next() or previous() was not the most recently called method
		 */
		@Override
		public void set(E e) throws NullPointerException, IllegalArgumentException {
			if(e == null) {
				throw new NullPointerException();
			}
			
			if(lastRetrieved == null) {
				throw new IllegalStateException();
			}
			lastRetrieved.data = e;
			
		}

		/**
		 * Adds the entered element to the list. The element is added as the 
		 * new previous value for the iterator but does not delete the current
		 * previous value. The next next() call will still be the same but the 
		 * next previous() call will now return the entered element. 
		 */
		@Override
		public void add(E e) {
			if(e == null) {
				throw new NullPointerException();
			}
			ListNode n = new ListNode(e, prev, next);
			prev.next = n;
			size++;
			nextIndex++;
			previousIndex++;
			lastRetrieved = null;
		}
		
	}

}
