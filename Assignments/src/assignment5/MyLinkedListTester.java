/**
 * 
 */
package assignment5;

import java.util.NoSuchElementException;

import junit.framework.TestCase;

/**
 * @author Maks Cegielski-Johnson
 * @author William Guss
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
		MyLinkedList<String> s = new MyLinkedList<String>();
		assertTrue(s.size == 0);
	}

	/**
	 * Test method for {@link assignment5.MyLinkedList#addFirst(java.lang.Object)}.
	 */
	public void testAddFirst() {
		l.addFirst(5);
		assertTrue(l.size == 1);
		l.addFirst(3);
		assertTrue(l.size == 2);
		assertTrue(l.get(0).equals(new Integer(3)));
	}

	/**
	 * Test method for {@link assignment5.MyLinkedList#addLast(java.lang.Object)}.
	 */
	public void testAddLast() {
		l.addLast(5);
		assertTrue(l.size == 1);
		l.addLast(3);
		assertTrue(l.size == 2);
		assertTrue(l.get(0).equals(new Integer(5)));
		assertTrue(l.get(1).equals(new Integer(3)));
	}

	/**
	 * Test method for {@link assignment5.MyLinkedList#add(int, java.lang.Object)}.
	 */
	public void testAdd() {
		l.add(0,3);
		l.add(1,6);
		l.add(2,1);
		l.add(1,99);
		assertTrue(l.size == 4);
		assertTrue(l.get(1).equals(new Integer(99)));
	}

	/**
	 * Test method for {@link assignment5.MyLinkedList#getFirst()}.
	 */
	public void testGetFirst() {
		l.addFirst(5);
		l.addFirst(7);
		l.addFirst(13);
		assertEquals(l.getFirst(), new Integer(13));
	}

	/**
	 * Test method for {@link assignment5.MyLinkedList#getLast()}.
	 */
	public void testGetLast() {
		l.addFirst(5);
		l.addFirst(7);
		l.addFirst(13);
		assertEquals(l.getLast(), new Integer(5));
	}

	/**
	 * Test method for {@link assignment5.MyLinkedList#get(int)}.
	 */
	public void testGet() {
		for(int i = 0; i<10; i+=2)
			l.addLast(i);
		assertEquals(l.get(3), new Integer(6));
		assertEquals(l.get(0), new Integer(0));
	}

	/**
	 * Test method for {@link assignment5.MyLinkedList#removeFirst()}.
	 */
	public void testRemoveFirst() {
		for(int i = 0; i<=12; i+=3){
			l.addLast(i);
		}
		assertTrue(l.size == 5);
		assertEquals(l.removeFirst(),new Integer(0));
		assertTrue(l.size == 4);
		assertEquals(l.removeFirst(), new Integer(3));
		assertTrue(l.size == 3);
	}

	/**
	 * Test method for {@link assignment5.MyLinkedList#removeLast()}.
	 */
	public void testRemoveLast() {
		for(int i = 0; i<=12; i+=3){
			l.addLast(i);
		}
		assertTrue(l.size == 5);
		assertEquals(l.removeLast(),new Integer(12));
		assertTrue(l.size == 4);
		assertEquals(l.removeLast(), new Integer(9));
		assertTrue(l.size == 3);
	}

	/**
	 * Test method for {@link assignment5.MyLinkedList#remove(int)}.
	 */
	public void testRemoveInt() {
		l.addLast(4);
		l.addLast(8);
		l.addLast(12);
		assertTrue(l.size == 3);
		assertEquals(l.remove(1), new Integer(8));
		assertTrue(l.size == 2);
	}

	/**
	 * Test method for {@link assignment5.MyLinkedList#remove(java.lang.Object)}.
	 */
	public void testRemoveE() {
		l.addLast(5);
		l.addLast(10);
		l.addLast(15);
		assertTrue(l.size == 3);
		assertEquals(l.remove(new Integer(10)), true);
		assertTrue(l.size == 2);
	}

	/**
	 * Test method for {@link assignment5.MyLinkedList#contains(java.lang.Object)}.
	 */
	public void testContains() {
		for(int i = 0; i<= 30; i+=5)
			l.addLast(i);

		assertTrue(l.contains(15));
		assertTrue(!l.contains(17));
		assertTrue(l.contains(0));
		assertTrue(l.contains(30));
		assertTrue(!l.contains(35));
	}

	/**
	 * Test method for {@link assignment5.MyLinkedList#indexOf(java.lang.Object)}.
	 */
	public void testIndexOf() {
		assertEquals(l.indexOf(32), -1);
		l.addLast(2);
		assertEquals(l.indexOf(2), 0);
		l.addLast(4);
		assertEquals(l.indexOf(4), 1);
		l.addLast(8);
		assertEquals(l.indexOf(16), -1);
	}

	/**
	 * Test method for {@link assignment5.MyLinkedList#lastIndexOf(java.lang.Object)}.
	 */
	public void testLastIndexOf() {
		int var = 10;
		int mod = 5;
		for(int i = 0; i < 6; i++){ //{5, 10, 5, 10, 5, 10}
			mod *= -1;
			var += mod;
			l.addLast(var);
		}
		assertEquals(l.lastIndexOf(10), 5);
		assertEquals(l.lastIndexOf(5), 4);
		assertEquals(l.lastIndexOf(12), -1);
	}

	/**
	 * Test method for {@link assignment5.MyLinkedList#size()}.
	 */
	public void testSize() {
		assertEquals(l.size(), 0);
		for(int i = 0; i<10; i++){
			l.addLast(i);
		}
		assertEquals(l.size(), 10);
	}

	/**
	 * Test method for {@link assignment5.MyLinkedList#isEmpty()}.
	 */
	public void testIsEmpty() {
		assertTrue(l.isEmpty());
		l.addLast(1);
		assertTrue(!l.isEmpty());
	}

	/**
	 * Test method for {@link assignment5.MyLinkedList#clear()}.
	 */
	public void testClear() {
		assertTrue(l.isEmpty());
		l.clear();
		assertTrue(l.isEmpty());
		l.addLast(10);
		assertTrue(l.size() == 1);
		l.clear();
		assertTrue(l.isEmpty());
	}

	/**
	 * Test method for {@link assignment5.MyLinkedList#toArray()}.
	 */
	public void testToArray() {
		for(int i = 1; i<=10; i++)
			l.addLast(i);
		Object[] o = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		Object[] o2 = l.toArray();
		for(int i = 0; i < 10; i++)
			assertEquals(o[i], o2[i]);
	}


	/////////////////////////////////TESTING EXCEPTIONS/////////////////////////////////////////////////

	public void testAddException(){
		try{
			l.add(1, 4);
		}catch(IndexOutOfBoundsException e){
			assertTrue(true);
			return;
		}
		fail("Error was not caught");
	}

	public void testGetFirstException(){
		try{
			l.getFirst();
		}catch(NoSuchElementException e){
			assertTrue(true);
			return;
		}
		fail("Error was not caught");
	}

	public void testGetLastException(){
		try{
			l.getLast();
		}catch(NoSuchElementException e){
			assertTrue(true);
			return;
		}
		fail("Error was not caught");
	}

	public void testGetException(){
		try{
			l.get(13);
		}catch(IndexOutOfBoundsException e){
			assertTrue(true);
			return;
		}
		fail("Error was not caught");
	}

	public void testRemoveFirstException(){
		try{
			l.removeFirst();
		}catch(NoSuchElementException e){
			assertTrue(true);
			return;
		}
		fail("Error was not caught");
	}

	public void testRemoveLastException(){
		try{
			l.removeLast();
		}catch(NoSuchElementException e){
			assertTrue(true);
			return;
		}
		fail("Error was not caught");
	}

	public void testRemoveException(){
		try{
			l.remove(13);
		}catch(IndexOutOfBoundsException e){
			assertTrue(true);
			return;
		}
		fail("Error was not caught");
	}


}
