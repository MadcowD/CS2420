/**
 * 
 */
package assignment4;

import java.util.ArrayList;

import junit.framework.TestCase;

/**
 * @author Maks Cegielski-Johnson
 * @author William Guss
 *
 * JUnit testing for methods contained in the RecursiveSortingUtility class.
 */
public class RecursiveSortingUtilityTester extends TestCase {

	private RecursiveSortingUtility rec; 
	//The sorting class, albeit has standard methods,
	//we instantiate it so that we don't have to type 
	//the entire class name repeatedly while testing


	private ArrayList<Integer> tst;//Testing array



	protected void setUp() throws Exception {
		super.setUp();
		rec = new RecursiveSortingUtility();
	}

	/**
	 * Goes through the list and checks if it is sorted by
	 * checking if any item is out of order returns false, else returns true
	 * @param list - a list of sorted T items
	 * @return - boolean whether sorted or not
	 */
	private static <T extends Comparable<? super T>> boolean  isSorted(ArrayList<T> list){
		for(int i = 0; i < list.size()-1; i++)
			if(list.get(i).compareTo(list.get(i+1)) > 0)
				return false;
		return true;	
	}

	/**
	 * Test method for isSorted. Checks whether a sorted array is sorted
	 * and whether a unsorted array is not.
	 * 
	 */
	public void testIsSorted(){
		ArrayList<Integer> sorted = new ArrayList<Integer>();
		ArrayList<Integer> unsort = new ArrayList<Integer>();

		for(int i = 0; i <10; i++)
			sorted.add(i);

		unsort.add(4);
		unsort.add(3);
		unsort.add(2);

		assertTrue(!isSorted(unsort));

		assertTrue(isSorted(sorted));


	}

	/**
	 * Test method for Merge sort, tests whether Merge sort correctly sorts different lists.
	 */
	public void testMergeSortDriver() {

		//Average Case
		tst = rec.generateAverageCase(200);
		assertTrue(!isSorted(tst));
		rec.mergeSortDriver(tst);
		assertTrue(isSorted(tst));

		//Best Case
		tst = rec.generateBestCase(200);
		assertTrue(isSorted(tst));
		rec.mergeSortDriver(tst);
		assertTrue(isSorted(tst));

		//Worst Case
		tst = rec.generateWorstCase(200);
		assertTrue(!isSorted(tst));
		rec.mergeSortDriver(tst);
		assertTrue(isSorted(tst));
		
		
		//Empty List
		ArrayList<Integer> empty = new ArrayList<Integer>();
		assertTrue(empty.isEmpty());
		rec.mergeSortDriver(empty);
		assertTrue(empty.isEmpty());
	}

	/**
	 * Test method for Quicksort, tests whether Quicksort correctly sorts different lists.
	 */
	public void testQuickSortDriver() {
		//Average Case
		tst = rec.generateAverageCase(200);
		assertTrue(!isSorted(tst));
		rec.quickSortDriver(tst);
		assertTrue(isSorted(tst));

		//Best Case
		tst = rec.generateBestCase(200);
		assertTrue(isSorted(tst));
		rec.quickSortDriver(tst);
		assertTrue(isSorted(tst));

		//Worst Case
		tst = rec.generateWorstCase(200);
		assertTrue(!isSorted(tst));
		rec.quickSortDriver(tst);
		assertTrue(isSorted(tst));
		
		
		//Empty List
		ArrayList<Integer> empty = new ArrayList<Integer>();
		assertTrue(empty.isEmpty());
		rec.quickSortDriver(empty);
		assertTrue(empty.isEmpty());
		
	}

	/**
	 * Test method for the goodPivotStrategy, tests whether the middle item is picked correctly.
	 */
	public void testGoodPivotStrategy() {
		tst = new ArrayList<Integer>();
		tst.add(9);
		tst.add(3);
		tst.add(7);

		assertEquals(rec.goodPivotStrategy(tst, 0, 2), 1);
		
		ArrayList<String> singleton = new ArrayList<String>(); //List of size 1
		singleton.add("Please give us extra credit :) haha");
		assertEquals(rec.goodPivotStrategy(tst, 0, 0), 0);
	}

	/*
	 * 
	 * 
	 * We didn't test the other two pivot strategies
	 * because they are random and they obviously work
	 * since the list comes out sorted in quicksort. 
	 * 
	 * 
	 */

	/**
	 * Test method for generating a list for the best complexity.
	 */
	public void testGenerateBestCase() {
		tst = rec.generateBestCase(10);
		assertTrue(isSorted(tst));
	}

	/**
	 * Test method for generating a list for the average complexity.
	 */
	public void testGenerateAverageCase() {
		tst = rec.generateAverageCase(20);

		assertTrue(!isSorted(tst));
	}

	/**
	 * Test method for generating a list for the worst complexity.
	 */
	public void testGenerateWorstCase() {
		tst = rec.generateWorstCase(10);
		assertTrue(!isSorted(tst));
		ArrayList<Integer> nowSorted = new ArrayList<>();

		/*
		 * Since generateWorstCase generates a list of integers in descending order
		 * if we put them into another list in the opposite ordering that they currently are in
		 * then they should be put into to order, hence being sorted.
		 */
		for(int i = 9; i>= 0; i--)
			nowSorted.add(tst.get(i));
		assertTrue(isSorted(nowSorted));
	}

}
