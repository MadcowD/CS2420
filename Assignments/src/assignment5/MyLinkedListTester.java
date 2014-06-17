/**
 * 
 */
package assignment5;

import junit.framework.TestCase;

/**
 * @author Maks
 *
 */
public class MyLinkedListTester extends TestCase {
	
	private MyLinkedList<Integer> l = new MyLinkedList<Integer>();

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
	}

	/**
	 * Test method for {@link assignment5.MyLinkedList#MyLinkedList()}.
	 */
	public void testMyLinkedList() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link assignment5.MyLinkedList#addFirst(java.lang.Object)}.
	 */
	public void testAddFirst() {
		l.addFirst(5);
		assertTrue(l.size == 1);
		l.addFirst(3);
		assertTrue(l.size == 2);
		assertTrue(l.get(0)==new Integer(3));
	}

	/**
	 * Test method for {@link assignment5.MyLinkedList#addLast(java.lang.Object)}.
	 */
	public void testAddLast() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link assignment5.MyLinkedList#add(int, java.lang.Object)}.
	 */
	public void testAdd() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link assignment5.MyLinkedList#getFirst()}.
	 */
	public void testGetFirst() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link assignment5.MyLinkedList#getLast()}.
	 */
	public void testGetLast() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link assignment5.MyLinkedList#get(int)}.
	 */
	public void testGet() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link assignment5.MyLinkedList#removeFirst()}.
	 */
	public void testRemoveFirst() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link assignment5.MyLinkedList#removeLast()}.
	 */
	public void testRemoveLast() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link assignment5.MyLinkedList#remove(int)}.
	 */
	public void testRemoveInt() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link assignment5.MyLinkedList#remove(java.lang.Object)}.
	 */
	public void testRemoveE() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link assignment5.MyLinkedList#contains(java.lang.Object)}.
	 */
	public void testContains() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link assignment5.MyLinkedList#indexOf(java.lang.Object)}.
	 */
	public void testIndexOf() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link assignment5.MyLinkedList#lastIndexOf(java.lang.Object)}.
	 */
	public void testLastIndexOf() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link assignment5.MyLinkedList#size()}.
	 */
	public void testSize() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link assignment5.MyLinkedList#isEmpty()}.
	 */
	public void testIsEmpty() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link assignment5.MyLinkedList#clear()}.
	 */
	public void testClear() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link assignment5.MyLinkedList#toArray()}.
	 */
	public void testToArray() {
		fail("Not yet implemented");
	}

}
