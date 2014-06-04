/**
 * 
 */
package assignment3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import junit.framework.TestCase;

/**
 * @author Maks Cegielski-Johnson
 * @author William Guss
 * 
 * ArrayBasedCollection testing using JUnit
 * 
 * TODO ADD ANYMORE COMMENTS?? 
 *
 */
public class ArrayBasedCollectionTester extends TestCase {

	ArrayBasedCollection<Integer> arr;
	ArrayBasedCollection<String> str;
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
	}

	/**
	 * Test method for {@link assignment3.ArrayBasedCollection#ArrayBasedCollection()}.
	 */
	public void testArrayBasedCollection() {
		arr = new ArrayBasedCollection<Integer>();
		assertEquals(arr.size,0);
		assertNotNull(arr.data);
	}

	/**
	 * Test method for {@link assignment3.ArrayBasedCollection#add(java.lang.Object)}.
	 */
	public void testAddString() {
		str = new ArrayBasedCollection<String>();

	}

	
	public void testAddUnique(){
		arr = new ArrayBasedCollection<Integer>();
		assertEquals(arr.add(3), true);
		assertEquals(arr.add(3), false);

	}

	public void testAddOrder(){
		arr = new ArrayBasedCollection<Integer>();
		arr.add(3);
		arr.add(5);
		arr.add(68);
		arr.add(666);// \m/
		arr.add(420); //lol
		assertEquals(arr.get(0), new Integer(3));
		assertEquals(arr.get(1), new Integer(5));
		assertEquals(arr.get(2), new Integer(68));
		assertEquals(arr.get(3), new Integer(666));
		assertEquals(arr.get(4), new Integer(420));
		assertTrue(!arr.get(0).equals(5));

	}

	public void testAddGrowth(){
		arr = new ArrayBasedCollection<Integer>();
		assertEquals(arr.getBounds(), 10);
		for(int i = 0; i<12; i++){
			arr.add(i);
		}
		assertEquals(arr.getBounds(), 20);

	}

	/**
	 * Test method for {@link assignment3.ArrayBasedCollection#addAll(java.util.Collection)}.
	 */
	public void testAddAll() {
		arr = new ArrayBasedCollection<Integer>();
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i<10; i++){
			list.add(i);
		}

		assertEquals(true, arr.addAll(list));
		assertEquals(false, arr.addAll(list));

	}

	/**
	 * Test method for {@link assignment3.ArrayBasedCollection#clear()}.
	 */
	public void testClear() {
		arr = new ArrayBasedCollection<Integer>();
		for(int i = 0; i<10; i++){
			arr.add(i);
		}
		assertEquals(arr.size(), 10);
		arr.clear();
		assertEquals(arr.size(), 0);
	}

	/**
	 * Test method for {@link assignment3.ArrayBasedCollection#contains(java.lang.Object)}.
	 */
	public void testContains() {
		arr = new ArrayBasedCollection<Integer>();
		for(int i = 10; i<20; i++){
			arr.add(i);
		}
		assertEquals(arr.contains(15), true);
		assertEquals(arr.contains(-5), false);
	}

	public void testEmptyContains(){
		arr = new ArrayBasedCollection<Integer>();
		assertEquals(arr.contains(0), false);
	}

	/**
	 * Test method for {@link assignment3.ArrayBasedCollection#containsAll(java.util.Collection)}.
	 */
	public void testContainsAll() {
		ArrayList<Integer> even = new ArrayList<Integer>();
		ArrayList<Integer> big = new ArrayList<Integer>();
		arr = new ArrayBasedCollection<Integer>();
		for(int i = 1; i<=20; i++){
			if(i<=10){
				arr.add(i);
				if(i%2 == 0)
					even.add(i);
			}
			else{
				big.add(i);
			}
		}

		assertEquals(arr.containsAll(even), true);
		assertEquals(arr.containsAll(big), false);
		
		even.add(13);
		assertEquals(arr.containsAll(even),false);
	}

	/**
	 * Test method for {@link assignment3.ArrayBasedCollection#isEmpty()}.
	 */
	public void testIsEmpty() {
		arr = new ArrayBasedCollection<Integer>();
		assertTrue(arr.isEmpty());
		arr.add(1);
		assertTrue(!arr.isEmpty());
		arr.clear();
		assertTrue(arr.isEmpty());
	}

	/**
	 * Test method for {@link assignment3.ArrayBasedCollection#iterator()}.
	 */
	public void testIterator() {
		arr = new ArrayBasedCollection<Integer>();
		for(int i = 0; i<10; i++){
			arr.add(i);
		}
		Iterator it = arr.iterator(); //IF I PUT <INTEGER> THEN COMPILER ERROR?
		if(arr.size() != 10){
			System.out.println("Why doesn't this equal 10");
		}
		
		assertTrue(it.hasNext());
		assertEquals(it.next(), 0);
		assertEquals(it.next(), 1);
		it.remove();
		assertEquals(it.next(), 2);
		assertEquals(arr.size(), 9);
		
		
		
	}

	/**
	 * Test method for {@link assignment3.ArrayBasedCollection#remove(java.lang.Object)}.
	 */
	public void testRemove() {
		arr = new ArrayBasedCollection<Integer>();
		for(int i = 1; i<=10; i++){
			arr.add(i);
		}
		assertTrue(arr.contains(5));
		assertTrue(arr.remove(5));
		assertTrue(!arr.contains(5));
		assertTrue(!arr.remove(111));
	}

	/**
	 * Test method for {@link assignment3.ArrayBasedCollection#removeAll(java.util.Collection)}.
	 */
	public void testRemoveAll() {
		arr = new ArrayBasedCollection<Integer>();
		ArrayList<Integer> even = new ArrayList<Integer>();
		for(int i = 1; i<=10; i++){
			arr.add(i);
			if(i%2==0)
				even.add(i);
		}
		assertEquals(arr.size(),10);
		assertTrue(arr.contains(2));
		assertTrue(arr.removeAll(even)); 
		assertEquals(arr.size(), 5);
		assertTrue(!arr.contains(2));

		
	}

	/**
	 * Test method for {@link assignment3.ArrayBasedCollection#retainAll(java.util.Collection)}.
	 */
	public void testRetainAll() {
		arr = new ArrayBasedCollection<Integer>();
		ArrayList<Integer> even = new ArrayList<Integer>();
		for(int i = 1; i<=10; i++){
			arr.add(i);
			if(i%2==0)
				even.add(i);
		}
		assertEquals(arr.size(),10);
		assertTrue(arr.contains(2));
		assertTrue(arr.retainAll(even)); 
		assertEquals(arr.size(), 5);
		assertTrue(arr.contains(2));
	}

	/**
	 * Test method for {@link assignment3.ArrayBasedCollection#size()}.
	 */
	public void testSize() {
		arr = new ArrayBasedCollection<Integer>();
		assertEquals(arr.size(), 0);
		arr.add(2);
		assertEquals(arr.size(), 1);
		arr.remove(3);
		assertEquals(arr.size(), 1);
		arr.remove(2);
		assertEquals(arr.size(), 0);
	}

	/**
	 * Test method for {@link assignment3.ArrayBasedCollection#toArray()}.
	 */
	public void testToArray() {
		arr = new ArrayBasedCollection<Integer>();
		for(int i = 0; i<10; i++)
			arr.add(i);
		Object[] comp = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		for(int i = -0; i < comp.length; i++)
			assertEquals(comp[i],arr.toArray()[i]);
		
		
	}


	/**
	 * Test method for {@link assignment3.ArrayBasedCollection#toSortedList(java.util.Comparator)}.
	 */
	public void testToSortedList() {
		arr = new ArrayBasedCollection<Integer>();
		IntegerComparator cmp = new IntegerComparator();
		for(int i = 10; i>0; i--){
			if(i%2 == 0)
				arr.add(i);
		}
		for(int i = 1; i<=10; i++){
			if(i%2 != 0)
				arr.add(i);
		}
		
		ArrayList<Integer> unsort = new ArrayList<Integer>();
		
		for(int i : arr)
			unsort.add(i);
		assertEquals(isSorted(unsort), false);
		
		
		ArrayList<Integer> sorted = arr.toSortedList(cmp);
		
		assertEquals(isSorted(sorted), true);
		//TODO WHATDO????? HALP
	}
	
	/**
	 * TESTING FOR BINARY SEARCH
	 */
	public void testBinarySearch(){
		arr = new ArrayBasedCollection<Integer>();
		ArrayList<Integer> list = new ArrayList<Integer>();
		IntegerComparator cmp = new IntegerComparator();
		
		for(int i = 0; i<100; i++){
			if(i%5==0)
				list.add(i);
		}
		
		assertEquals(SearchUtil.binarySearch(list, 65, cmp), true); //TODO HELP ME WITH THIS
		assertEquals(SearchUtil.binarySearch(list, 73, cmp), false);
		
		
		
	}
	
	/**
	 * Checks whether a given ArrayList is sorted or not
	 * @param list
	 * @return
	 */
	private static boolean isSorted(ArrayList<Integer> list){
		for(int i = 0; i<list.size()-1; i++){
			if(list.get(i) > list.get(i+1))
				return false;
		}
		
		return true;
	}
	
	

}
