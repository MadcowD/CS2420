package assignment3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * @author Paymon Saebi
 * @author
 * @author
 * 
 * Systematically test the ArrayBasedCollection class
 */
public class DEPRICATION
{
	
	private static void err(String s){
		System.out.println("ERROR: " + s );
		
	}
	/**
	 *
	 */
	public static void main(String[] args)
	{		
		// TODO add any preparations needed for testing your implementation
		ArrayBasedCollection<Integer> integer = new ArrayBasedCollection<Integer>();
		ArrayBasedCollection<String> string = new ArrayBasedCollection<String>();
		
		
		tester(integer, string);
	}
	
	/**
	 *
	 */
	public static void tester(ArrayBasedCollection<Integer> integer, ArrayBasedCollection<String> string)
	{	
		if(integer.size() != 0 || string.size()!= 0)
			err("list size not zero");
		if(!integer.isEmpty() || !string.isEmpty())
			err("lists should be empty");
		if(integer.contains(4) || string.contains("pls no"))
			err("shouldn't contain anything");
		integer.add(5);
		integer.add(19);
		integer.add(2);
		integer.add(-3);
		if(integer.size() != 4)
			err("integer is the wrong size");
		if(integer.add(2))
			err("adding a copy to integer");
		if(!integer.contains(19))
			err("integer should contain 19");
		if(integer.contains(300))
			err("integer should not contain 300");
		ArrayList<Integer> testList = new ArrayList<Integer>();
		testList.add(5);
		testList.add(19);
		testList.add(-3);
		testList.add(9);
		if(integer.containsAll(testList))
			err("integer should not contain all");
		testList.add(2);
		
		Iterator<Integer> it = integer.iterator();
		integer.add(4);
		while(it.hasNext()){
			System.out.println(it.next());
		}
		
		
		
		
		
		
		
		System.out.println("NICE TESTING DUDE");
	}
	
	
	public static int[] randomInts(int size)
	{
		Random rand = new Random();
		int retval[] = new int[size];
		for(int i=0; i < size; i++)
			retval[i] = rand.nextInt(size);
		return retval;
	}
	public static int[] permuteInts(int size)
	{
		Random rand = new Random();
		int retval[] = ascendingInts(size);
		for(int i=0; i < size; i++)
			swap(retval, i, rand.nextInt(size));
		return retval;
	}

	// Generate an array of ascending integers
	public static int[] ascendingInts(int size)
	{
		int retval[] = new int[size];
		for(int i=0; i < size; i++)
			retval[i] = i;
		return retval;
	}
	
	// Generate an array of descending integers
	public static int[] descendingInts(int size)
	{
		int retval[] = new int[size];
		for(int i=0; i< size; i++)
			retval[i] = size - i - 1;
		return retval;
	} 

	// Swaps two items in the given array
	public static void swap(int[] arr, int i, int j)
	{
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	public static void printArray(int[] nums)
	{
		for(int k=0; k < nums.length; k++)
			System.out.print(nums[k] + " ");	
		System.out.println();
	}
	
	public static int[] copyArray(int[] nums)
	{
		int retval[] = new int[nums.length];		
		for(int i = 0; i < nums.length; i++)
			retval[i] = nums[i];		
		return retval;
	}

	// Determines if an array is sorted
	public static boolean isSorted(int[] nums)
	{
		for(int i=0; i < nums.length-1; i++)
			if(nums[i] > nums[i+1])
				return false;
		return true;
	}
}
