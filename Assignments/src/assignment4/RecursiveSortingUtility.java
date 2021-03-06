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

	public static void main(String[] args){
		ArrayList<Integer> tst = RecursiveSortingUtility.generateAverageCase(10);
		System.out.println(tst);
		RecursiveSortingUtility.mergeSortDriver(tst);
		System.out.println(tst);
	}


	private static int mergesortThreshold = 25;
	private static int quicksortThreshold = 14;
	public static int choose = 3;

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
			for(j = i; j>start && val.compareTo(list.get(j-1)) < 0; j--){
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
		for(int i = 0; i < list.size(); i++)
			temp.add(null);
		
		mergeSort(list, temp, 0, list.size()-1);
	}


	
	private static <T extends Comparable<? super T>> void merge(ArrayList<T> list, ArrayList<T> temp, int start, int end){
		int middle = (start +end)/2 +1;
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

	private static <T extends Comparable<? super T>> void mergeSort(ArrayList<T> list, ArrayList<T> temp, int start, int end){
		if(start < end){
			if(end - start < mergesortThreshold)
				insertionSortIterative(list, start, end);
			else{
				int center = (start + end)/2;
				mergeSort(list, temp, start, center);
				mergeSort(list, temp, center+1, end);

				merge(list, temp, start, end);
			}
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
		
		
		int pivPos = bestPivotStrategy(list,start,end);
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

	public static Random rng = new Random();
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
		

		return start + rng.nextInt(end-start);

	}

	/**
	 * Recursive quicksort helper method
	 * EMPLOYING ADAPTIVE METHOD AS SEEN IN: J. L. BENTLEY ADN M.D. McIlroy
	 * @param list  - input ArrayList of T objects that must have a Comparable implementation
	 * @param start - start index of the subarray  of objects
	 * @param limit   - end index of the subarray  of objects
	 * 
	 * @return index of chosen pivot
	 */
	public static <T extends Comparable<? super T>> int bestPivotStrategy(ArrayList<T> list, int start, int end){
		int middle = (end + start)/2;

		if(list.get(middle).compareTo(list.get(start)) < 0){
			normalSwap(list, start, middle);
		}
		if(list.get(end).compareTo(list.get(start)) < 0){
			normalSwap(list, start, end);
		}
		if(list.get(end).compareTo(list.get(middle)) < 0){
			normalSwap(list, middle, end);
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

		for(int i = size; i>0; i--)
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
}
