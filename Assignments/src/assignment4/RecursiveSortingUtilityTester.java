
package assignment4;

import java.util.ArrayList;
import junit.framework.TestCase;
import assignment4.AlgorithmTimer.TimeComplexity;

/**
 * @author Maks Cegielski-Johnson
 * @author William Guss
 *
 * JUnit testing for methods contained in the RecursiveSortingUtility class.
 */
public class RecursiveSortingUtilityTester extends TestCase {



	private ArrayList<Integer> tst;//Testing array



	protected void setUp() throws Exception {
		super.setUp();
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
		tst = RecursiveSortingUtility.generateAverageCase(200);
		assertTrue(!isSorted(tst));
		RecursiveSortingUtility.mergeSortDriver(tst);
		assertTrue(isSorted(tst));

		//Best Case
		tst = RecursiveSortingUtility.generateBestCase(200);
		assertTrue(isSorted(tst));
		RecursiveSortingUtility.mergeSortDriver(tst);
		assertTrue(isSorted(tst));

		//Worst Case
		tst = RecursiveSortingUtility.generateWorstCase(200);
		assertTrue(!isSorted(tst));
		RecursiveSortingUtility.mergeSortDriver(tst);
		assertTrue(isSorted(tst));
		
		tst = RecursiveSortingUtility.generateAverageCase(10);
		System.out.println(tst);
		RecursiveSortingUtility.mergeSortDriver(tst);
		System.out.println(tst);
		
		//Empty List
//		ArrayList<Integer> empty = new ArrayList<Integer>();
//		assertTrue(empty.isEmpty());
//		rec.mergeSortDriver(empty);
//		assertTrue(empty.isEmpty());
	}

	/**
	 * Test method for Quicksort, tests whether Quicksort correctly sorts different lists.
	 */
	public void testQuickSortDriver() {
		//Average Case
		tst = RecursiveSortingUtility.generateAverageCase(200);
		assertTrue(!isSorted(tst));
		RecursiveSortingUtility.quickSortDriver(tst);
		assertTrue(isSorted(tst));

		//Best Case
		tst = RecursiveSortingUtility.generateBestCase(200);
		assertTrue(isSorted(tst));
		RecursiveSortingUtility.quickSortDriver(tst);
		assertTrue(isSorted(tst));

		//Worst Case
		tst = RecursiveSortingUtility.generateWorstCase(200);
		assertTrue(!isSorted(tst));
		RecursiveSortingUtility.quickSortDriver(tst);
		assertTrue(isSorted(tst));
		
		
		//Empty List
		ArrayList<Integer> empty = new ArrayList<Integer>();
		assertTrue(empty.isEmpty());
		RecursiveSortingUtility.quickSortDriver(empty);
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

		assertEquals(RecursiveSortingUtility.goodPivotStrategy(tst, 0, 2), 1);
		
		ArrayList<String> singleton = new ArrayList<String>(); //List of size 1
		singleton.add("Please give us extra credit :) haha");
		assertEquals(RecursiveSortingUtility.goodPivotStrategy(tst, 0, 0), 0);
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
		tst = RecursiveSortingUtility.generateBestCase(10);
		assertTrue(isSorted(tst));
	}

	/**
	 * Test method for generating a list for the average complexity.
	 */
	public void testGenerateAverageCase() {
		tst = RecursiveSortingUtility.generateAverageCase(20);

		assertTrue(!isSorted(tst));
	}

	/**
	 * Test method for generating a list for the worst complexity.
	 */
	public void testGenerateWorstCase() {
		tst = RecursiveSortingUtility.generateWorstCase(10);
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
	/**
	 * Test method for checking our Enumerated Time Complexity type method setter.
	 */
	public void testGenerateCase(){
		tst = RecursiveSortingUtility.generateCase(10, TimeComplexity.BEST);
		assertTrue(isSorted(tst));
		
		tst = RecursiveSortingUtility.generateCase(10, TimeComplexity.WORST);
		ArrayList<Integer> back = new ArrayList<Integer>();
		assertTrue(!isSorted(tst));
		for(int i = 9; i>= 0; i--)
			back.add(tst.get(i));
		assertTrue(isSorted(back));
		
		tst = RecursiveSortingUtility.generateCase(10, TimeComplexity.AVERAGE);
		assertTrue(!isSorted(tst));
		
		
		
	}

}
