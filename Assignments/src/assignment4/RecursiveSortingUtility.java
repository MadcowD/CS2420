package assignment4;

import java.util.ArrayList;
import java.util.Random;

import assignment4.AlgorithmTimer.TimeComplexity;

/**
 * @author Paymon Saebi
 * @author Maks Cegielski-Johnson
 * @author William Guss
 * 
 * This sorting utility class provides static methods for recursive sorting 
 * 
 * Merge sort methods for threshold setting, driving, recursing, and merging
 * Quicksort methods for driving, recursing, and finding different pivots
 * Input generators for creating ascending, descending, and permuted lists  
 */
public class RecursiveSortingUtility 
{	
	
	private static int mergesortThreshold = 100;
	private static int quicksortThreshold = 100;

	/**
	 * Helper method for setting the switching threshold for merge sort
	 *  
	 * @param desiredThreshold - merge sort switching threshold
	 */
	public static void setMergeSortThreshold(int desiredThreshold)
	{
		mergesortThreshold = desiredThreshold;
	}
	
	/**
	 * Helper method for setting the switching threshold for quicksort
	 *  
	 * @param desiredThreshold - quicksort switching threshold
	 */
	public static void setQuickSortThreshold(int desiredThreshold){
		quicksortThreshold = desiredThreshold;
	}

	/**
	 * Iterative insertion sort method 
	 *
	 * @param list  - input ArrayList of objects, must have a Comparable implementation
	 * @param start - start index of the subarray of objects
	 * @param end   - end index of the subarray of objects
	 */
	private static <T extends Comparable<? super T>> void insertionSortIterative(ArrayList<T> list, int start, int end)
	{
		int j;
		for(int i = start; i <= end; i++){
			T val = list.get(i);
			for(j = i; j>0 && val.compareTo(list.get(j-1)) < 0; j--){
				list.set(j, list.get(j-1));
			}
			list.set(j, val);
		}
	}

	/**
	 * Recursive merge sort driver method
	 *
	 * @param list - input ArrayList of objects, must have a Comparable implementation
	 */
	public static <T extends Comparable<? super T>> void mergeSortDriver(ArrayList<T> list)
	{

		ArrayList<T> temp = new ArrayList<T>(list.size());
		for(int i = 0; i<list.size(); i++){
			temp.add(null);
		}

		mergeSortRecursive(list, temp, 0, list.size()-1);
	}

	/**
	 * Recursive merge sort algorithm method 
	 * 
	 * @param list   - input ArrayList of T objects that must have a Comparable implementation
	 * @param temp   - temporary ArrayList of T objects to help with merging
	 * @param start  - start index of the subarray of objects
	 * @param end    - end index of the subarray of objects
	 */
	private static <T extends Comparable<? super T>> void mergeSortRecursive(ArrayList<T> list, ArrayList<T> temp, int start, int end)
	{
		if(start < end){
			if(end - start < mergesortThreshold)
				insertionSortIterative(list, start, end);
			else{
				int center = (start + end)/2;
				mergeSortRecursive(list, temp, start, center);
				mergeSortRecursive(list, temp, center+1, end);

				mergeSortedPortions(list, temp, start, center+1, end);
			}
		}
	}

	/**
	 * Recursive merge sort helper method 
	 * 
	 * @param list   - input ArrayList of T objects that must have a Comparable implementation
	 * @param temp   - temporary ArrayList in  which the result with be placed
	 * @param start  - start index of the subarray of objects
	 * @param middle - middle index of the subarray of objects
	 * @param end    - end index of the subarray of objects
	 */
	private static <T extends Comparable<? super T>> void mergeSortedPortions(ArrayList<T> list, ArrayList<T> temp, int start, int middle, int end)
	{
		int right = middle-1;
		int numberElements = end - start + 1;
		int tempPos = start;


		while(start <= right && middle<=end)
			if(list.get(start).compareTo(list.get(middle)) <= 0)
				temp.set(tempPos++, list.get(start++));
			else
				temp.set(tempPos++, list.get(middle++));

		while(start <= right)
			temp.set(tempPos++, list.get(start++));
		while(middle <= end)
			temp.set(tempPos++, list.get(middle++));

		for(int i = 0; i<numberElements; i++,end--){
			list.set(end, temp.get(end));
		}
	}

	/**
	 * Recursive quicksort driver method
	 * 
	 * @param list - input ArrayList of T objects that must have a Comparable implementation
	 */
	public static <T extends Comparable<? super T>> void quickSortDriver(ArrayList<T> list)
	{
		if(list.size() <= 1)
			return;

		quickSortRecursive(list, 0, list.size()-1);	
	}

	/**
	 * Recursive quicksort algorithm method
	 *  
	 * @param list  - input ArrayList of T objects that must have a Comparable implementation
	 * @param start - start index of the subarray of objects 
	 * @param end   - end index of the subarray of objects
	 */
	private static <T extends Comparable<? super T>> void quickSortRecursive(ArrayList<T> list, int start, int end)
	{
		if(end-start <= 0)
			return;
		if(end - start < quicksortThreshold)
			insertionSortIterative(list, start, end);
		else{

			int middle = partition(list, start, end);
			quickSortRecursive(list, start, middle-1);
			quickSortRecursive(list, middle+1, end);
		}

	}


	private static <T extends Comparable<? super T>> int partition(ArrayList<T> list, int start, int end){
		int pivPos = bestPivotStrategy(list, start, end);
		T pivot = list.get(pivPos);
		swapElements(list, start, pivPos);

		int left = start+1;
		int right = end;
		while(true){
			while(left <= right)
				if(list.get(left).compareTo(pivot) < 0){
					left++;
				}
				else{
					break;
				}
			while(right > left){
				if(list.get(right).compareTo(pivot) > 0)
					right--;
				else
					break;
			}
			if(left >= right)
				break;
			swapElements(list, left, right);


		}
		list.set(start, list.get(left-1));
		list.set(left-1, pivot);
		return left-1;

	}

	/**
	 * Recursive quicksort helper method
	 * 
	 * @param list  - input ArrayList of T objects that must have a Comparable implementation
	 * @param start - start index of the subarray  of objects
	 * @param end   - end index of the subarray  of objects
	 * 
	 * @return index of chosen pivot
	 */
	public static <T extends Comparable<? super T>> int goodPivotStrategy(ArrayList<T> list, int start, int end)
	{
		int middle = (start + end)/2;


		return middle;
	}

	/**
	 * Recursive quicksort helper method
	 * 
	 * @param list  - input ArrayList of T objects that must have a Comparable implementation
	 * @param start - start index of the subarray  of objects
	 * @param end   - end index of the subarray  of objects
	 * 
	 * @return index of chosen pivot
	 */
	public static <T extends Comparable<? super T>> int betterPivotStrategy(ArrayList<T> list, int start, int end)
	{
		Random rng = new Random();

		return start + rng.nextInt(end-start);

	}

	/**
	 * Recursive quicksort helper method
	 * 
	 * @param list  - input ArrayList of T objects that must have a Comparable implementation
	 * @param start - start index of the subarray  of objects
	 * @param limit   - end index of the subarray  of objects
	 * 
	 * @return index of chosen pivot
	 */
	public static <T extends Comparable<? super T>> int newbestPivotStrategy(ArrayList<T> list, int start, int end)
	{
		Random rng = new Random();
		int limit = end - start;
		int first = start + rng.nextInt(limit);

		int second = start + rng.nextInt(limit);
		int third = start + rng.nextInt(limit);

		if(list.get(second).compareTo(list.get(first)) < 0){
			swapElements(list, first, second);
		}
		if(list.get(third).compareTo(list.get(first)) < 0){
			swapElements(list, first, third);
		}
		if(list.get(third).compareTo(list.get(second)) < 0){
			swapElements(list, second, third);
		}

		return second;

	}
	
	
	public static <T extends Comparable<? super T>> int bestPivotStrategy(ArrayList<T> list, int start, int end){
		int middle = (end + start)/2;
		
		if(list.get(middle).compareTo(list.get(start)) < 0){
			swapElements(list, start, middle);
		}
		if(list.get(end).compareTo(list.get(start)) < 0){
			swapElements(list, start, end);
		}
		if(list.get(end).compareTo(list.get(middle)) < 0){
			swapElements(list, middle, end);
		}

		return middle;
		
	}

	/**
	 * Best case input generation helper method
	 *  
	 * @param size size of the returned ArrayList
	 * 
	 * @return an ArrayList of integers in sorted, ascending order. 
	 */
	public static ArrayList<Integer> generateBestCase(int size)
	{
		ArrayList<Integer> temp = new ArrayList<Integer>();

		for(int i = 1; i<=size; i++)
			temp.add(i);

		return temp;
	}

	/**
	 * Average case input generation helper method
	 * 
	 * @param size the size of the returned ArrayList
	 * 
	 * @return An ArrayList of random integers from 0-size in permuted order
	 */
	public static ArrayList<Integer> generateAverageCase(int size)
	{
		Random rng = new Random();
		ArrayList<Integer> temp = generateBestCase(size);

		for(int i = 0; i< size; i++)
			swapElements(temp, i, rng.nextInt(size));

		return temp;	
	}

	/**
	 * Worst case nput generation helper method	 
	 * 
	 * @param size the size of the returned ArrayList
	 * 
	 * @return An ArrayList of integers in descending order
	 */
	public static ArrayList<Integer> generateWorstCase(int size)
	{
		ArrayList<Integer> temp = new ArrayList<Integer>();

		for(int i = size; i>0; i--)
			temp.add(i);

		return temp;
	}	

	/**
	 * ArrayList elements swapping Helper method
	 * 
	 * @param list  - input ArrayList of objects, must have a Comparable implementation
	 * @param left  - index of the left element
	 * @param right - index of the right element
	 */
	private static <T extends Comparable<? super T>> void swapElements (ArrayList<T> list, int left, int right)
	{
		T temp = list.get(left);
		list.set(left, list.get(right));
		list.set(right, temp);
	}
	
	/**
	 * Generates a list based on a given complexity case.
	 * @param n The number of elements to generate.
	 * @param complexity The time complexity desired.
	 */
	public static ArrayList<Integer> generateCase(int n, TimeComplexity complexity){
		switch(complexity){
		case WORST:
			return generateWorstCase(n);
		case AVERAGE:
			return generateAverageCase(n);
		case BEST:
			return generateBestCase(n);
		default:
			return generateAverageCase(n);
		}
	}
}
