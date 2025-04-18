/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * Custom ArrayList class. This ArrayList can hold any form of java Objects.
 * This ArrayList has methods to get the size, add elements at any index (default is 
 * the end of the list), remove elements, set the element an at index, and get the element
 * at a specific index. 
 * @author jpkenlin
 * @param <E> the Object type that will fill the ArrayList
 */
public class ArrayList<E> extends AbstractList<E> {

	/** Size for the array when it is initialized */
	private static final int INIT_SIZE = 10;
	/** Generic type array */
	private E[] list;
	/** The number of elements in the List */
	private int size;
	
	/**
	 * constructor for ArrayList
	 */
	@SuppressWarnings("unchecked")
	public ArrayList() {
		//E item = (E) new Object();
		list = (E[]) new Object[INIT_SIZE];
	}
	
	/**
	 * Returns the number of elements in the List
	 * @return the size
	 */
	public int size() {
		return size;
	}
	
	@Override
	public void add(int index, E value) {
		if (value == null) {
			throw new NullPointerException();
		}
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		for (int i = 0; i < size; i++) {
			if (value.equals(list[i])) {
				throw new IllegalArgumentException();
			}
		}
		if (index == list.length) {
			growArray();
			list[index] = value;
		}
		if (index == size) {
			list[size] = value;
		}
		if (index < size) {
			for (int i = size - 1; i >= index; i--) {
				list[i + 1] = list[i];
			}
			list[index] = value;
		}
		size++;
		
		
	}
	
	
	@SuppressWarnings("unchecked")
	private void growArray() {
		E[] tempList = (E[]) new Object[list.length * 2];
		for (int i = 0; i < size; i++) {
			tempList[i] = list[i];
		}
		list = tempList;
	}
	
	@Override
	public E remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		E temp = list[index];
		if (index < size) {
			for (int i = index; i < size - 1; i++) {
				list[i] = list[i + 1];
			}
		}
		list[size - 1] = null;
		size--;
		return temp;
		
	}
	
	@Override
	public E set(int index, E value) {
		if (value == null) {
			throw new NullPointerException();
		}
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		for (int i = 0; i < size; i++) {
			if (value.equals(list[i])) {
				throw new IllegalArgumentException();
			}
		}
		E temp = list[index];
		list[index] = value;
		return temp;
		
		
		
	}
	
	@Override
	public E get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}		
		return list[index];
	}
}
