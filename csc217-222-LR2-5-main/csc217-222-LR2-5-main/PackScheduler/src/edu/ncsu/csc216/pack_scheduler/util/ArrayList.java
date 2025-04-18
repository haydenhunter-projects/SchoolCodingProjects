/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**n
* Array List class 
* @author nolanhurst
* @author hchunter
* @param <E> type of array list
*/
public class ArrayList<E> extends AbstractList<E> {
	/** Base size of list */
	private final static int INIT_SIZE = 10;
	
	/** list that contains the ArrayList */
	private E[] list;
	
	/** size of the array list */
	private int size;
	
	/**
	 * Array List constructor that makes the list was unknown type and sets size to 0
	 * 
	 */
	@SuppressWarnings("unchecked")
	public ArrayList() {
		list = (E[]) new Object[INIT_SIZE];
		size = 0;
	}
	
	/**
	 * returns the size of the array list
	 * @return size field of the array
	 */
	public int size() {
		return size;
	}
	/**
	 * Add method for array list. Will add the object to the first index that is null
	 * and will move other objects over if needed.
	 * @param i index where the object should be added
	 * @param e object being added
	 */
	public void add(int i, E e) {
		
		if(e == null) {
			throw new NullPointerException();
			
		}
		
		if (i < 0 || i > size()) {
			throw new IndexOutOfBoundsException();
		}
		
		for (int l = 0; l < size; l++) {
			if (list[l].equals(e)) {
				throw new IllegalArgumentException("Duplicate element");
			}
		}
		
		if(list.length == size) {
			growArray();
		}
		
			
		for (int j = i; j < size; j++) {
			list[j + 1] = list[j];
		}
				
		size++;
		list[i] = e;
			
		
		
	}
	
	/**
	 * Grow array makes the array have twice the size it was before and retains the 
	 * place of the previous list
	 */
	@SuppressWarnings("unchecked")
	private void growArray() {
		E[] newList = (E[]) new Object[INIT_SIZE * 2];
		
		for (int i = 0; i < list.length; i++) {
			newList[i] = list[i];
		}
		list = newList;
	}
	
	/**
	 *  gets the object at given index 
	 * @param i index of object to get
	 * @return the object at the index
	 */
	public E get(int i) {
		if (i < 0 || i >= size) {
			throw new IndexOutOfBoundsException();
		}
		return list[i];
	}
	
	/**
	 * remove method removes the object at given index and then moves all the elements
	 * to the index before it.
	 * @param idx index of object to remove
	 * @return the object that was removed
	 */
	public E remove(int idx) {
		
		if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException();
		}
		
		E returnE = list[idx];
		for (int i = idx; i < list.length; i++) {
			if (i != idx) {
				list[i - 1] = list[i];
			} 
		}
		size--;
		return returnE;
	}
	
	/**
	 * Set method for array list takes an index and an object then adds object to the 
	 * given index if the object is not a duplicate element
	 * @param idx index of element
	 * @param e element to set
	 * @return returns the object that is being overridden
	 */
	public E set(int idx, E e) {
		if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException();
		}
		
		if (e == null) {
			throw new NullPointerException();
		}
		
		for (int i = 0; i < list.length; i++) {
			if (list[i] == e) { 
				throw new IllegalArgumentException("Duplicate element.");
			}
		}
		
		E returnE = list[idx];
		
		list[idx] = e;
		
		return returnE;
	}
	
}
