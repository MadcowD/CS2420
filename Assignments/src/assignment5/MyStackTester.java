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
public class MyStackTester extends TestCase {
	
	MyStack<Integer> s = new MyStack<Integer>();

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
	}

	/**
	 * Test method for {@link assignment5.MyStack#MyStack()}.
	 */
	public void testMyStack() {
		MyStack<String> stack = new MyStack<String>();
		assertEquals(stack.size(), 0);
		assertTrue(stack.isEmpty());
	}

	/**
	 * Test method for {@link assignment5.MyStack#clear()}.
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
	 * Test method for {@link assignment5.MyStack#isEmpty()}.
	 */
	public void testStackIsEmpty() {
		assertTrue(s.isEmpty());
		s.push(3);
		assertTrue(!s.isEmpty());
		s.clear();
		assertTrue(s.isEmpty());
		
	}

	/**
	 * Test method for {@link assignment5.MyStack#peek()}.
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
	 * Test method for {@link assignment5.MyStack#pop()}.
	 */
	public void testPop() {
		s.push(11);
		s.push(111);
		s.push(1111);
		assertEquals(s.pop(), new Integer(1111));
	}
	
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
	 * Test method for {@link assignment5.MyStack#push(java.lang.Object)}.
	 */
	public void testPush() {
		s.push(3);
		assertEquals(s.peek(), new Integer(3));
		s.push(5);
		assertEquals(s.peek(), new Integer(5));
		
	}

	/**
	 * Test method for {@link assignment5.MyStack#size()}.
	 */
	public void testStackSize() {
		assertEquals(s.size(), 0);
		s.push(3);
		assertEquals(s.size(), 1);
		s.clear();
		assertEquals(s.size(), 0);
	}

	/**
	 * Test method for {@link assignment5.MyStack#toArray()}.
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
