package edu.ncsu.csc216.wolf_tickets.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;

/**
 * tests swap list class
 *
 * @author hchunter
 */
public class SwapListTest {

	/**
	 * tests SwapList add
	 */
	@Test
	public void testAdd() {
		SwapList<Integer> list = new SwapList<>();

		assertEquals(0, list.size());

		list.add(1);
		assertEquals(1, list.size());
		assertEquals(1, list.get(0));

		list.add(2);
		assertEquals(2, list.size());
		assertEquals(2, list.get(1));

		list.add(3);
		assertEquals(3, list.size());
		assertEquals(3, list.get(2));

	}

	/**
	 * tests SwapList remove
	 */
	@Test
	public void testRemove() {
		SwapList<Integer> list = new SwapList<>();

		assertEquals(0, list.size());

		list.add(1);
		assertEquals(1, list.get(0));

		// test removing the only element in the list
		list.remove(0);
		assertEquals(0, list.size());

		// test removing index 0 with 2 elements
		list.add(2);
		assertEquals(2, list.get(0));
		list.add(3);
		assertEquals(3, list.get(1));

		list.remove(0);
		assertEquals(3, list.get(0));

		// test removing middle index
		list.add(2);
		list.add(4);

		// list is 3,2,4
		list.remove(1);

		// list should be 3,4
		assertEquals(2, list.size());
		assertEquals(3, list.get(0));
		assertEquals(4, list.get(1));

		Exception e1 = assertThrows(IndexOutOfBoundsException.class, () -> list.remove(3));
		assertEquals("Invalid index.", e1.getMessage());
	}

	/**
	 * tests SwapList move up
	 */
	@Test
	public void testMoveUp() {
		SwapList<Integer> list = new SwapList<>();

		assertEquals(0, list.size());

		list.add(1);
		list.add(2);
		list.add(3);

		// list is 1,2,3
		list.moveUp(1);

		// list should be 2,1,3
		assertEquals(3, list.size());

		assertEquals(2, list.get(0));
		assertEquals(1, list.get(1));
		assertEquals(3, list.get(2));

		// change list to 2,3,1
		list.moveUp(2);

		assertEquals(2, list.get(0));
		assertEquals(3, list.get(1));
		assertEquals(1, list.get(2));

		// move up front of list and assure no change
		list.moveUp(0);

		assertEquals(2, list.get(0));
		assertEquals(3, list.get(1));
		assertEquals(1, list.get(2));

		// move up multiple elements
		list.moveUp(1);
		// list is now 3,2,1
		assertEquals(3, list.get(0));
		assertEquals(2, list.get(1));
		assertEquals(1, list.get(2));

		list.moveUp(2);
		// list is now 3,1,2
		assertEquals(3, list.get(0));
		assertEquals(1, list.get(1));
		assertEquals(2, list.get(2));

		Exception e1 = assertThrows(IndexOutOfBoundsException.class, () -> list.moveUp(3));
		assertEquals("Invalid index.", e1.getMessage());
	}

	/**
	 * tests SwapList move down
	 */
	@Test
	public void testMoveDown() {
		SwapList<Integer> list = new SwapList<>();

		assertEquals(0, list.size());

		list.add(1);
		list.add(2);
		list.add(3);

		// list is 1,2,3
		list.moveDown(1);

		// list should be 1,3,2
		assertEquals(1, list.get(0));
		assertEquals(3, list.get(1));
		assertEquals(2, list.get(2));

		// try moving down last element
		// list is 1,3,2
		list.moveDown(2);
		// list should still be 1,3,2
		assertEquals(1, list.get(0));
		assertEquals(3, list.get(1));
		assertEquals(2, list.get(2));

		// change list to 3,1,2
		list.moveDown(0);
		assertEquals(3, list.get(0));
		assertEquals(1, list.get(1));
		assertEquals(2, list.get(2));

		// change list to 3,2,1
		list.moveDown(1);
		assertEquals(3, list.get(0));
		assertEquals(2, list.get(1));
		assertEquals(1, list.get(2));

		Exception e1 = assertThrows(IndexOutOfBoundsException.class, () -> list.moveDown(3));
		assertEquals("Invalid index.", e1.getMessage());
	}

	/**
	 * tests SwapList move to front
	 */
	@Test
	public void testMoveToFront() {
		SwapList<Integer> list = new SwapList<>();

		assertEquals(0, list.size());

		list.add(1);
		list.add(2);
		list.add(3);

		// list is 1,2,3
		list.moveToFront(2);

		// new list should be 3,1,2
		assertEquals(3, list.get(0));
		assertEquals(1, list.get(1));
		assertEquals(2, list.get(2));

		list.moveToFront(1);
		// new list should be 1,3,2

		assertEquals(1, list.get(0));
		assertEquals(3, list.get(1));
		assertEquals(2, list.get(2));

		list.moveToFront(0);
		// new list should be unchanged 2,3,1
		assertEquals(1, list.get(0));
		assertEquals(3, list.get(1));
		assertEquals(2, list.get(2));

		// try to move an index that does not exist
		Exception e1 = assertThrows(IndexOutOfBoundsException.class, () -> list.moveToFront(3));
		assertEquals("Invalid index.", e1.getMessage());
	}

	/**
	 * tests SwapList move to back
	 */
	@Test
	public void testMoveToBack() {
		SwapList<Integer> list = new SwapList<>();

		assertEquals(0, list.size());

		list.add(1);
		list.add(2);
		list.add(3);

		// list is 1,2,3

		list.moveToBack(2);
		// new list should be unchanged 1,2,3
		assertEquals(1, list.get(0));
		assertEquals(2, list.get(1));
		assertEquals(3, list.get(2));

		list.moveToBack(0);
		// new list should be 2,3,1
		assertEquals(2, list.get(0));
		assertEquals(3, list.get(1));
		assertEquals(1, list.get(2));

		list.moveToBack(1);
		// new list should be unchanged 2,1,3
		assertEquals(2, list.get(0));
		assertEquals(1, list.get(1));
		assertEquals(3, list.get(2));

		// try to move an index that does not exist
		Exception e1 = assertThrows(IndexOutOfBoundsException.class, () -> list.moveToBack(3));
		assertEquals("Invalid index.", e1.getMessage());
	}

	/**
	 * tests SwapList checkCapacity
	 */
	@Test
	public void testCheckCapacity() {
		SwapList<Integer> list = new SwapList<>();

		// should grow capacity, since default is 10
		for (int i = 0; i < 11; i++) {
			list.add(i);
		}

		// check to make sure growing capacity did not overwrite list
		for (int i = 0; i < 11; i++) {
			assertEquals(i, list.get(i));
		}

	}
}
