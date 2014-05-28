package lab2;

import junit.framework.TestCase;

public class TestFindSmallestDiff extends TestCase {

	private int[] arr1, arr2, arr3, arr4, arr5;

	protected void setUp() throws Exception {
		super.setUp();


		arr1 = new int[0];
		arr2 = new int[] { 3, 3, 3 };
		arr3 = new int[] { 52, 4, -8, 0, -17 };
		arr4 = new int[] {-3, 9, 100, 45, 99, 105};
		arr5 = new int[] {1,2,3,4,5,6,7,8,9,10};
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Tests for incompatiable arrays and if -1 is not called
	 * test fails./
	 */
	public void testFindSmallestDiff1() 
	{
		assertEquals(-1, DiffUtil.findSmallestDiff(arr1));
	}

	/**
	 * Tests for 0 difference given by array of same value elements.
	 */
	public void testFindSmallestDiff2() 
	{
		assertEquals(0, DiffUtil.findSmallestDiff(arr2));
	}

	/**
	 * Tests for accurate calculation of smallest difference given the algorithm.
	 */
	public void testFindSmallestDiff3() 
	{
		assertEquals(4, DiffUtil.findSmallestDiff(arr3));
	}

	/**
	 * Tests for correctness in the calculation of the smallest difference for array 4
	 */
	public void testFindSmallestDiff4(){
		assertEquals(1, DiffUtil.findSmallestDiff(arr4));
	}
	
	public void testFindSmallestDiff5(){
		assertEquals(1, DiffUtil.findSmallestDiff(arr5));
	}

}
