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
 *	Testing class for the two Linked Structures, MyLinkedList and MyStack, testing various situations, including throwing exceptions.
 */
public class LinkedStructureTester extends TestCase {

	private MyLinkedList<Integer> l = new MyLinkedList<Integer>();
	MyStack<Integer> s = new MyStack<Integer>();

	protected void setUp() throws Exception {
		super.setUp();
	}

	/**
	 * Tests whether a new MyLinkedList is empty when created
	 */
	public void testMyLinkedList() {
		MyLinkedList<String> s = new MyLinkedList<String>();
		assertTrue(s.size == 0);
	}

	/**
	 * Tests the addFirst method
	 */
	public void testAddFirst() {
		l.addFirst(5);
		assertTrue(l.size == 1);
		l.addFirst(3);
		assertTrue(l.size == 2);
		assertTrue(l.get(0).equals(new Integer(3)));
	}

	/**
	 * Tests the addLast method
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
	 * Tests the index based add method
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
	 * Tests the getFirst method
	 */
	public void testGetFirst() {
		l.addFirst(5);
		l.addFirst(7);
		l.addFirst(13);
		assertEquals(l.getFirst(), new Integer(13));
	}

	/**
	 * Tests the getLast method
	 */
	public void testGetLast() {
		l.addFirst(5);
		l.addFirst(7);
		l.addFirst(13);
		assertEquals(l.getLast(), new Integer(5));
	}

	/**
	 * Tests the index based get method
	 */
	public void testGet() {
		for(int i = 0; i<10; i+=2)
			l.addLast(i);
		assertEquals(l.get(3), new Integer(6));
		assertEquals(l.get(0), new Integer(0));
	}

	/**
	 * Tests the removeFirst method
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
	 * Tests the removeLast method
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
	 * Tests the remove method for a passed in index
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
	 * Tests the remove method for a passed in object <E>
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
	 * Tests the contains method
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
	 * Tests the indexOf method
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
	 * Tests the lastIndexOf method
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
	 * Tests the size method for MyLinkedList
	 */
	public void testSize() {
		assertEquals(l.size(), 0);
		for(int i = 0; i<10; i++){
			l.addLast(i);
		}
		assertEquals(l.size(), 10);
	}

	/**
	 * Tests the isEmpty method for MyLinkedList
	 */
	public void testIsEmpty() {
		assertTrue(l.isEmpty());
		l.addLast(1);
		assertTrue(!l.isEmpty());
	}

	/**
	 * Tests the clear method for MyLinkedList
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
	 * Tests the toArray method for MyLinkedList
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

	/**
	 * Testing the IndexOutOfBoundsException for the index based add method
	 */
	public void testAddException(){
		try{
			l.add(1, 4);
		}catch(IndexOutOfBoundsException e){
			assertTrue(true);
			return;
		}
		fail("Error was not caught");
	}

	/**
	 * Testing the NoSuchElementException for the getFirst method on an empty list
	 */
	public void testGetFirstException(){
		try{
			l.getFirst();
		}catch(NoSuchElementException e){
			assertTrue(true);
			return;
		}
		fail("Error was not caught");
	}

	/**
	 * Testing the NoSuchElementException for the getLast method on an empty list
	 */
	public void testGetLastException(){
		try{
			l.getLast();
		}catch(NoSuchElementException e){
			assertTrue(true);
			return;
		}
		fail("Error was not caught");
	}

	/**
	 * Testing the IndexOutOfBoundsException for the index based get method
	 */
	public void testGetException(){
		try{
			l.get(13);
		}catch(IndexOutOfBoundsException e){
			assertTrue(true);
			return;
		}
		fail("Error was not caught");
	}

	/**
	 * Testing the NoSuchElementException for removeFirst on an empty list
	 */
	public void testRemoveFirstException(){
		try{
			l.removeFirst();
		}catch(NoSuchElementException e){
			assertTrue(true);
			return;
		}
		fail("Error was not caught");
	}

	/**
	 * Testing the NoSuchElementException for removeLast on an empty list
	 */
	public void testRemoveLastException(){
		try{
			l.removeLast();
		}catch(NoSuchElementException e){
			assertTrue(true);
			return;
		}
		fail("Error was not caught");
	}

	/**
	 * Testing IndexOutOfBoundsException for the index based remove
	 */
	public void testRemoveException(){
		try{
			l.remove(13);
		}catch(IndexOutOfBoundsException e){
			assertTrue(true);
			return;
		}
		fail("Error was not caught");
	}
	
	/////////////////////////////////////STACK TESTING////////////////////////////////////////////////////////////////
	///////////////////////////////////////        /////////////////////////////////////////////////////////////////////
	
	/**
	 * Tests the MyStack constructor
	 */
	public void testMyStack() {
		MyStack<String> stack = new MyStack<String>();
		assertEquals(stack.size(), 0);
		assertTrue(stack.isEmpty());
	}

	/**
	 * Tests the clear method for MyStack
	 */
	public void testStackClear() {
		assertEquals(s.size(), 0);
		s.push(3);
		s.push(2);
		s.push(9);
		assertEquals(s.size(), 3);
		s.clear();
		assertEquals(s.size(), 0);
	}

	/**
	 * Tests the isEmpty method for MyStack
	 */
	public void testStackIsEmpty() {
		assertTrue(s.isEmpty());
		s.push(3);
		assertTrue(!s.isEmpty());
		s.clear();
		assertTrue(s.isEmpty());
		
	}

	/**
	 * Tests the peek method
	 */
	public void testPeek() {
		s.push(13);
		assertEquals(s.peek(), new Integer(13));
		s.push(23);
		s.push(33);
		assertEquals(s.peek(), new Integer(33));
		s.pop();
		assertEquals(s.peek(), new Integer(23));
	}
	
	/**
	 * Tests the NoSuchElementException for the peek method on an empty stack
	 */
	public void testPeekException(){
		try{
			s.peek();
		}catch(NoSuchElementException e){
			assertTrue(true);
			return;
		}
		fail("Exception was not caught");
	}

	/**
	 * Tests the pop method
	 */
	public void testPop() {
		s.push(11);
		s.push(111);
		s.push(1111);
		assertEquals(s.pop(), new Integer(1111));
	}
	
	/**
	 * Tests the NoSuchElementException for the pop method on an empty stack
	 */
	public void testPopException(){
		try{
			s.pop();
		}catch(NoSuchElementException e){
			assertTrue(true);
			return;
		}
		fail("Exception was not caught");
	}

	/**
	 * Tests the push method
	 */
	public void testPush() {
		s.push(3);
		assertEquals(s.peek(), new Integer(3));
		s.push(5);
		assertEquals(s.peek(), new Integer(5));
		
	}

	/**
	 * Tests the size method for MyStack
	 */
	public void testStackSize() {
		assertEquals(s.size(), 0);
		s.push(3);
		assertEquals(s.size(), 1);
		s.clear();
		assertEquals(s.size(), 0);
	}

	/**
	 * Tests the toArray method for MyStack
	 */
	public void testStackToArray() {
		for(int i = 0; i<5; i++)
			s.push(i);
		Object[] o = {0, 1, 2, 3, 4};
		Object[] o2 = s.toArray();
		int i = 0;
		for(Object n : o){	
			assertEquals(o2[i++], n);
		}
	}
}
