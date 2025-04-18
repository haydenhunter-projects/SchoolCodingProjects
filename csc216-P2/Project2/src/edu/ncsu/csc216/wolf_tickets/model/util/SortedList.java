package edu.ncsu.csc216.wolf_tickets.model.util;

/**
 * The SortedList class implements the ISortedList interface. The generic type
 * should extend the Comparable interface. The SortedList class uses the
 * Comparable.compareTo() method to determine the ordering of elements.
 *
 * @author hchunter
 *
 * @param <E> the list type
 *
 */
public class SortedList<E extends Comparable<E>> implements ISortedList<E> {

	/**
	 * The SortedList class implements the ISortedList interface. The generic type
	 * should extend the Comparable interface. The SortedList class uses the
	 * Comparable.compareTo() method to determine the ordering of elements.
	 */
	private int size;

	/**
	 * the node at the front of the list
	 */
	private ListNode front;

	/**
	 * the default constructor of a sorted list
	 */
	public SortedList() {
		size = 0;
		front = null;
	}

	/**
	 * add(E). Should throw a NullPointerException with message Cannot add null
	 * element. if the parameter is null. Should throw an IllegalArgumentException
	 * with message Cannot add duplicate element if the parameter is a duplicate of
	 * an existing element.
	 *
	 * @param element the element we want to add to the list
	 * @throws NullPointerException     if element is null with message cannot add
	 *                                  null element.
	 * @throws IllegalArgumentException if element is duplicate with message cannot
	 *                                  add duplicate element.
	 */
	@Override
	public void add(E element) {

		if (element == null) {
			// if trying to add a null element throw exception
			throw new NullPointerException("Cannot add null element.");
		}

		ListNode current = front;

		// adding to the front of the list
		if (front == null || element.compareTo(front.data) < 0) {
			front = new ListNode(element, front);
		} else if (contains(element)) {
			// if duplicate throw exception
			throw new IllegalArgumentException("Cannot add duplicate element.");
		} else {
			// add to the middle or end of the list
			current = front;
			while (current.next != null && current.next.data.compareTo(element) < 0) {
				current = current.next;

			}
			current.next = new ListNode(element, current.next);
		}
		// increment size after each node is added to the list
		size++;
	}

	/**
	 * removes an element at a specific index
	 *
	 * @param idx the index of the element we wish to remove
	 * @return the element we removed from the list
	 * @throws throw an IndexOutOfBoundsException with message Invalid index. if the
	 *               index parameter is out of bounds for the list.
	 *
	 */
	@Override
	public E remove(int idx) {
		checkindex(idx);
		E element;

		if (size == 0) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		if (idx == 0) {
			element = front.data;
			front = front.next;
			size--;
			return element;
		} else {
			ListNode temp = front;

			for (int i = 0; i < idx - 1; i++) {
				temp = temp.next;
			}

			element = temp.next.data;
			temp.next = temp.next.next;
			size--;
			return element;
		}
	}

	/**
	 * Checks for a valid index
	 *
	 * @param idx the index we would like to check
	 * @throws throw an IndexOutOfBoundsException with message Invalid index. if the
	 *               index parameter is out of bounds for the list.
	 */
	private void checkindex(int idx) {
		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
	}

	/**
	 * returns true or false if the list contains a specific element
	 *
	 * @param element the element we are searching for in the list
	 * @return the true or false if the list contained the element
	 */
	@Override
	public boolean contains(E element) {

		ListNode temp = front;

		while (temp != null) {
			if (temp.data.equals(element)) {
				return true;
			}
			temp = temp.next;
		}
		return false;
	}

	/**
	 * returns the element at the given index
	 *
	 * @param idx the index of the element
	 * @return the element at the index
	 * @throws throw an IndexOutOfBoundsException with message Invalid index. if the
	 *               index parameter is out of bounds for the list.
	 */
	@Override
	public E get(int idx) {
		checkindex(idx);

		// when list is empty
		if (size == 0) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}

		ListNode temp = front;
		for (int i = 0; i < idx; i++) {
			temp = temp.next;
		}

		return temp.data;

	}

	/**
	 * returns the size of the sorted list
	 *
	 * @return the size of the list
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * The List Node class is an inner class of SortedList and is used to traverse
	 * through a LinkedList
	 */
	public class ListNode {
		/**
		 * the element of a node
		 */
		public E data;

		/**
		 * the next list node in the list
		 */
		ListNode next;

		/**
		 * creates a list node
		 *
		 * @param data the element of a node
		 * @param next points to the next node
		 */
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}

	}

}
