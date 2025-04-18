package edu.ncsu.csc216.wolf_tickets.model.util;

/**
 * The SwapList class implements the ISwapList interface. The interface provides
 * Javadoc that describes what each method should do and the exceptions that
 * should be thrown. The SwapList allows duplicate elements.
 *
 * @author hchunter
 *
 * @param <E> the list type
 */
public class SwapList<E> implements ISwapList<E> {

	/**
	 * stores the value of the default size of a swap list
	 */
	private static final int INITIAL_CAPACITY = 10;

	/**
	 * stores a generic type array
	 */
	private E[] list;

	/**
	 * stores the number of elements in a swap list
	 */
	private int size;

	/**
	 * the default constructor of a swap list
	 */
	@SuppressWarnings("unchecked")
	public SwapList() {
		this.size = 0;
		this.list = (E[]) new Object[INITIAL_CAPACITY];
	}

	/**
	 * add(E). Should throw a NullPointerException with message Cannot add null
	 * element. if the parameter is null.
	 *
	 * @param element the element we wish to add to the list
	 * @throws NullPointerException if element is null
	 */
	@Override
	public void add(E element) {
		checkCapacity(size);
		if (element == null) {
			throw new NullPointerException("Cannot add null element.");
		}

		list[size] = element;

		size++;
	}

	/**
	 * checks the capacity of the list
	 *
	 * @param size the current size of the list
	 */
	private void checkCapacity(int size) {

		if (size >= INITIAL_CAPACITY) {
			int newSize = size + 1;
			@SuppressWarnings("unchecked")
			E[] newList = (E[]) new Object[newSize];

			for (int i = 0; i < size; i++) {
				newList[i] = list[i];
			}
			this.list = newList;
		}

	}

	/**
	 * removes an element at a specific index
	 *
	 * @param idx the index of the element we wish to remove
	 * @return the element we removed
	 * @throws IndexOutOfBoundsException with message Invalid index. if the index
	 *                                   parameter is out of bounds for the list.
	 *
	 */
	@Override
	public E remove(int idx) {
		checkCapacity(size);
		checkindex(idx);

		E temp = list[idx];
		if (idx < size) {
			for (int i = idx; i < size; i++) {
				list[i] = list[i + 1];
			}
		}
		list[size - 1] = null;
		size--;

		return temp;

	}

	/**
	 * checks the index
	 *
	 * @param idx the index we are checking
	 * @throws throw an IndexOutOfBoundsException with message Invalid index. if the
	 *               index parameter is out of bounds for the list.
	 */
	private void checkindex(int idx) {
		if (idx >= size || idx < 0) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
	}

	/**
	 * moves a element up the list
	 *
	 * @param idx the index of the element we are moving up the list
	 * @throws IndexOutOfBoundsException with message Invalid index. if the index
	 *                                   parameter is out of bounds for the list.
	 */
	@Override
	public void moveUp(int idx) {
		checkCapacity(size);
		checkindex(idx);

		if (idx != 0) {
			E moveUP = list[idx];
			E moveDown = list[idx - 1];

			if (idx < size) {

				for (int i = idx; i < size; i++) {
					list[i + 1] = list[i + 1];
				}
			}
			list[idx - 1] = moveUP;
			list[idx] = moveDown;
		}

	}

	/**
	 * moves a element down the list
	 *
	 * @param idx the index of the element we are moving down the list
	 * @throws IndexOutOfBoundsException with message Invalid index. if the index
	 *                                   parameter is out of bounds for the list.
	 */
	@Override
	public void moveDown(int idx) {
		checkCapacity(size);
		checkindex(idx);

		if (idx != size - 1) {
			E moveDown = list[idx];
			E moveUp = list[idx + 1];

			if (idx < size) {
				for (int i = idx; i < size; i++) {
					list[i + 1] = list[i + 1];
				}
			}

			list[idx + 1] = moveDown;
			list[idx] = moveUp;
		}

	}

	/**
	 * moves a element to the front of the list
	 *
	 * @param idx the index of the element we are moving to the front of the list
	 * @throws IndexOutOfBoundsException with message Invalid index. if the index
	 *                                   parameter is out of bounds for the list.
	 */
	@Override
	public void moveToFront(int idx) {
		checkCapacity(size);
		checkindex(idx);

		if (idx != 0) {
			E moveFront = list[idx];

			if (idx < size) {

				for (int i = idx; i > 0; i--) {
					list[i] = list[i - 1];
				}
			}

			list[0] = moveFront;

		}

	}

	/**
	 * moves a element to the back of the list
	 *
	 * @param idx the index of the element we are moving to the back of the list
	 * @throws IndexOutOfBoundsException with message Invalid index. if the index
	 *                                   parameter is out of bounds for the list.
	 */
	@Override
	public void moveToBack(int idx) {
		checkCapacity(size);
		checkindex(idx);

		if (idx != size - 1) {
			E moveBack = list[idx];
			E moveUp = list[idx + 1];

			if (idx < size) {
				for (int i = idx; i < size; i++) {
					list[i] = list[i + 1];
				}
			}

			list[size - 1] = moveBack;
			list[idx] = moveUp;
		}

	}

	/**
	 * returns an element at a specific index in the list
	 *
	 * @param idx the index of the element we want returned
	 * @return the element at the specific index
	 * @throws IndexOutOfBoundsException with message Invalid index. if the index
	 *                                   parameter is out of bounds for the list.
	 */
	@Override
	public E get(int idx) {
		checkCapacity(size);
		checkindex(idx);

		return list[idx];
	}

	/**
	 * returns the size of the list
	 *
	 * @return size the size of the list
	 */
	@Override
	public int size() {
		return size;
	}

}
