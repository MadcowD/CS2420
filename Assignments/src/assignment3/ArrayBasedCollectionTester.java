package assignment3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import junit.framework.TestCase;


/**
 * @author Maks Cegielski-Johnson
 * @author William Guss
 * 
 * ArrayBasedCollection testing on Integers using JUnit
 * 
 * TODO ADD ANYMORE COMMENTS?? 
 *
 */
public class ArrayBasedCollectionTester extends TestCase {

	ArrayBasedCollection<Integer> arr;
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
	}

	/**
	 * Tests whether the constructor is working correctly
	 */
	public void testArrayBasedCollection() {
		arr = new ArrayBasedCollection<Integer>();
		assertEquals(arr.size,0); //check if size is zero
		assertNotNull(arr.data);
	}

	/**
	 * Tests whether items added to the list remain unique to make sure multiples are not added
	 * 
	 */ 
	public void testAddUnique(){
		arr = new ArrayBasedCollection<Integer>();
		assertEquals(arr.add(3), true);
		assertEquals(arr.add(3), false);

	}

	/**
	 * Tests whether items added to the list remain in the order in which they were added	 * 
	 * 
	 */
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

	/**
	 * Assures that the data grows by a factor of 2 when reaching capacity
	 */
	public void testAddGrowth(){
		arr = new ArrayBasedCollection<Integer>();
		assertEquals(arr.getBounds(), 10);
		for(int i = 0; i<12; i++){//Adds more than 10 items
			arr.add(i);
		}
		assertEquals(arr.getBounds(), 20);//data should be 20

	}

	/**
	 * Assures that a list is added to the collection and if no elements are added then returns false
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
	 * Assures that a collection containing any items is empty after peforming the clear method
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
	 * Assures that the set of integers {10...19} contains 15 and doesn't contain -5
	 */
	public void testContains() {
		arr = new ArrayBasedCollection<Integer>();
		for(int i = 10; i<20; i++){
			arr.add(i);
		}
		assertEquals(arr.contains(15), true);
		assertEquals(arr.contains(-5), false);
	}

	/**
	 * Assures that contains works on an empty collection
	 * 
	 */
	public void testEmptyContains(){
		arr = new ArrayBasedCollection<Integer>();
		assertEquals(arr.contains(0), false);
	}

	/**
	 * Assures that every item in "even" is contained in the collection and that no items from "big" are in the collection
	 */
	public void testContainsAll() {
		ArrayList<Integer> even = new ArrayList<Integer>();//The set with elements e such that {e = 2k | 1 <= k <=5}
		ArrayList<Integer> big = new ArrayList<Integer>(); //The set with elements m such that {11 <= m <= 20}
		arr = new ArrayBasedCollection<Integer>(); // The set with elements n such that { 1 <= n <= 10}
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
	 * Asserts that isEmpty is true when the collection is empty and false otherwise
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
	 * Asserts that the Iterator functions for the collection are working correctly
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
	 * Asserts that items that are in the collection are removed when called to do so, returning true, and false if 
	 * the element does not exist in the collection
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
	 * Series of tests that asserts that removing all items of a list from the collection works properly
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
	 * Assures that size works correctly after calling some remove and add functions
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
	 * Asserts that the toArray method correctly creates the proper array
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
	 * Assures that the sort method correctly sorts a shuffled list, using a trivial isSorted helper method 
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
		assertEquals(isSorted(unsort), false);//Assures that the list is unsorted and that the isSorted method works
		
		
		ArrayList<Integer> sorted = arr.toSortedList(cmp);
		
		assertEquals(isSorted(sorted), true);//Assuming the previous assertEquals holds, this proves that the collection is sorted correctly
	}
	
	/**
	 * Assures that the binary search correctly finds values that are contained in the list and that are not
	 */
	public void testBinarySearch(){
		arr = new ArrayBasedCollection<Integer>();
		ArrayList<Integer> list = new ArrayList<Integer>();
		IntegerComparator cmp = new IntegerComparator();
		
		for(int i = 0; i<100; i++){
			if(i%5==0)
				list.add(i);
		}
		
		assertEquals(SearchUtil.binarySearch(list, 65, cmp), true); 
		assertEquals(SearchUtil.binarySearch(list, 73, cmp), false);
		assertEquals(SearchUtil.binarySearch(list, 0, cmp), true); 
		assertEquals(SearchUtil.binarySearch(list, 95, cmp), true); 
		
		
		
	}
	
	/**
	 * Helper method that checks whether a given ArrayList is sorted or not
	 * @param list
	 * @return boolean true of false if the list is sorted or not
	 */
	private static boolean isSorted(ArrayList<Integer> list){
		for(int i = 0; i<list.size()-1; i++){
			if(list.get(i) > list.get(i+1))
				return false;
		}
		
		return true;
	}
	
	

}
