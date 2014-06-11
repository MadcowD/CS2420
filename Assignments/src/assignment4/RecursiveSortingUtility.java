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
		mergeSort(list, 0, list.size());
	}
	
	private static <T extends Comparable<? super T>> void mergeSort(ArrayList<T> list, int start, int end){
	

		if(end - start >1){
			mergeSort(list, start, (end+start)/2);
			mergeSort(list,  (end+start)/2, end);
		}
		else return;
		
		T left;
		T right;
		T index;
		for(int i = start, l = start, r = (start+end)/2;i < end;i++)
		{
			left = list.get(l);
			index = list.get(i);
			if(r == end){
				fastSwap(list, left, index, l, i);
				continue;
			}
			right = list.get(r);
			
			if(index.compareTo(right) >= 0
					&& right.compareTo(left) <= 0)
			{
				if(l == i)
					l = r;
				fastSwap(list, right, index, r, i);
				r++;
			}
			else if(index.compareTo(left) >= 0)
			{
				if(l == i)
					l++;
				else
				{
					fastSwap(list, left, index, l, i);
					if(l != r -1)
						l++;
				}
			}
			
			if(r == l)
				r++;
				
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
		normalSwap(list, start, pivPos);

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
			normalSwap(list, left, right);


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
	public static <T extends Comparable<? super T>> int bestPivotStrategy(ArrayList<T> list, int start, int end)
	{
		Random rng = new Random();
		int limit = end - start;
		int first = start + rng.nextInt(limit);

		int second = start + rng.nextInt(limit);
		int third = start + rng.nextInt(limit);

		if(list.get(second).compareTo(list.get(first)) < 0){
			normalSwap(list, first, second);
		}
		if(list.get(third).compareTo(list.get(first)) < 0){
			normalSwap(list, first, third);
		}
		if(list.get(third).compareTo(list.get(second)) < 0){
			normalSwap(list, second, third);
		}

		return second;

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

		for(int i = 0; i<size; i++)
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
			normalSwap(temp, i, rng.nextInt(size));

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

		for(int i = size - 1; i>=0; i--)
			temp.add(i);

		return temp;
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
	
	/**
	 * We don't do checking of indices as to avoid jvm timing issues.private static
	 * @param list
	 * @param index1
	 * @param index2
	 */
	private final static <T> void normalSwap(ArrayList<T> list, int index1, int index2){
		T temp = list.get(index1);
		list.set(index1, list.get(index2));
		list.set(index2, temp);
	}
	
	/**
	 * Swaps two elements given that they have already been accessed by the array
	 * This avoids a second access call.
	 * @param list
	 * @param e1
	 * @param e2
	 * @param index1
	 * @param index2
	 */
	private final static <T> void fastSwap(ArrayList<T> list, T e1, T e2, int index1, int index2){
		list.set(index1, e2);
		list.set(index2, e1);
	}
}
